package com.system.action.maintain;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.car.service.car.ICarservice;
import com.framework.util.BeanUtils;
import com.framework.util.ConfigUtil;
import com.framework.util.DateUtil;
import com.framework.util.HqlFilter;
import com.framework.util.IpUtil;
import com.framework.util.MD5Util;
import com.framework.util.WebMsgUtil;
import com.mysql.jdbc.Statement;
import com.opensymphony.xwork2.ActionContext;
import com.system.action.base.BaseAction;
import com.system.entity.easyui.Grid;
import com.system.entity.easyui.Json;
import com.system.entity.maintain.SessionInfo;
import com.system.entity.maintain.SysOrganization;
import com.system.entity.maintain.SysRole;
import com.system.entity.maintain.SysUser;
import com.system.entity.maintain.VSysUser;
import com.system.service.maintain.ISysUserRoleService;
import com.system.service.maintain.IVSysUserService;
import com.system.service.maintain.SysOrganizationServiceI;
import com.system.service.maintain.SysUserServiceI;

/**
 * 用户
 * 
 * action访问地址是/maintain/sysUser.cxl
 * 
 * @author 陈晓亮
 * 
 */
@Namespace("/maintain")
@Action(value = "sysUser")
public class SysUserAction extends BaseAction<SysUser> {

	private static final long serialVersionUID = -5392446650474540828L;

	private SysUser sysUser;
	private String code;
	private File file;
	private String fileFileName;
	@Autowired
	private SysOrganizationServiceI orgService;
	/**
	 * 注入业务逻辑，使当前action调用service.xxx的时候，直接是调用基础业务逻辑
	 * 
	 * 如果想调用自己特有的服务方法时，请使用((TServiceI) service).methodName()这种形式强转类型调用
	 * 
	 * @param service
	 */
	@Autowired
	public ICarservice carservice;

	@Autowired
	public void setService(SysUserServiceI service) {
		this.service = service;
	}

	@Autowired
	public IVSysUserService VSysUserService;
	@Autowired
	public ISysUserRoleService iSysUserRoleService;

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public static BASE64Encoder encoder = new sun.misc.BASE64Encoder();
	public static BASE64Decoder decoder = new sun.misc.BASE64Decoder();

	/**
	 * 注销系统
	 */
	public void doNotNeedSessionAndSecurity_logout() {
		if (getSession() != null) {
			getSession().invalidate();
		}
		Json j = new Json();
		j.setSuccess(true);
		writeJson(j);
	}

	/**
	 * 注册
	 */
	synchronized public void doNotNeedSessionAndSecurity_reg() {
		Json json = new Json();
		HqlFilter hqlFilter = new HqlFilter();
		hqlFilter.addFilter("QUERY_t#loginid_S_EQ", sysUser.getUserLogin());
		SysUser user = service.getByFilter(hqlFilter);
		if (user != null) {
			json.setMsg("用户名已存在！");
			writeJson(json);
		} else {
			SysUser u = new SysUser();
			u.setUserLogin(sysUser.getUserLogin());
			u.setUserPwd(MD5Util.md5(sysUser.getUserPwd()));
			service.save(u);
			doNotNeedSessionAndSecurity_login();
		}
	}

	/**
	 * 登录
	 */
	public void doNotNeedSessionAndSecurity_login() {
		HqlFilter hqlFilter = new HqlFilter();
		hqlFilter.addFilter("QUERY_t#userLogin_S_EQ", sysUser.getUserLogin());
		hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ", WebMsgUtil.YOUXIAO);
		hqlFilter.addFilter("QUERY_t#userPwd_S_EQ", MD5Util.md5(sysUser.getUserPwd()));
		SysUser user = service.getByFilter(hqlFilter);
		Json json = new Json();
		if (user != null) {
			if (user.getSysOrganization().size() < 1) {
				json.setSuccess(false);
				json.setMsg("用户尚未授权归属单位，请联系管理员！");
			} else {
				json.setSuccess(true);

				SessionInfo sessionInfo = new SessionInfo();
				Hibernate.initialize(user.getSysRoles());
				Hibernate.initialize(user.getSysOrganization());
				for (SysRole role : user.getSysRoles()) {
					Hibernate.initialize(role.getSysResources());
				}
				for (SysOrganization organization : user.getSysOrganization()) {
					sessionInfo.setOrganization(organization);
				}
				user.setIp(IpUtil.getIpAddr(getRequest()));
				user.setUserSign(null);
				sessionInfo.setUser(user);
				getSession().setAttribute(ConfigUtil.getSessionInfoName(), sessionInfo);

			}
		} else {
			json.setSuccess(false);
			json.setMsg("警员号或密码错误！");
		}
		writeJson(json);
	}

	/**
	 * 解锁登录
	 */
	public void doNotNeedSessionAndSecurity_unLock() {
		HqlFilter hqlFilter = new HqlFilter();
		hqlFilter.addFilter("QUERY_t#userLogin_S_EQ", sysUser.getUserLogin());
		hqlFilter.addFilter("QUERY_t#userPwd_S_EQ", MD5Util.md5(sysUser.getUserPwd()));
		SysUser user = service.getByFilter(hqlFilter);
		Json json = new Json();
		if (user != null) {
			json.setSuccess(true);
			SessionInfo sessionInfo = new SessionInfo();
			Hibernate.initialize(user.getSysRoles());
			Hibernate.initialize(user.getSysOrganization());
			for (SysRole role : user.getSysRoles()) {
				Hibernate.initialize(role.getSysResources());
			}
			for (SysOrganization organization : user.getSysOrganization()) {
				// Hibernate.initialize(organization.getSysResources());
				sessionInfo.setOrganization(organization);
			}
			user.setIp(IpUtil.getIpAddr(getRequest()));
			sessionInfo.setUser(user);
			getSession().setAttribute(ConfigUtil.getSessionInfoName(), sessionInfo);
		} else {
			json.setMsg("密码错误！");
		}
		writeJson(json);
	}

	/**
	 * 修改自己的密码
	 */
	public void doNotNeedSecurity_updateCurrentuserPwd() {
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		Json json = new Json();
		SysUser user = service.getById(sessionInfo.getUser().getUserId());
		user.setUserPwd(MD5Util.md5(sysUser.getUserPwd()));
		user.setTimeUpdate(new Date());
		service.update(user);
		json.setSuccess(true);
		json.setMsg("修改成功");
		writeJson(json);
	}

	/**
	 * 重置用户密码
	 */
	/**
	 * 
	 */
	public void resetuserPwd() {
		SysUser sessionInfo = ((SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName())).getUser();
		Json json = new Json();
		SysUser user = service.getById(id);
		user.setUserPwd(MD5Util.md5("123456"));
		user.setTimeUpdate(new Date());
		user.setUserIdUpdate(sessionInfo.getUserId());
		service.update(user);
		json.setSuccess(true);
		json.setMsg("重置成功！默认密码：123456");
		writeJson(json);
	}

	public void delete() {
		SysUser sessionInfo = ((SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName())).getUser();
		Json json = new Json();
		SysUser user = service.getById(id);
		user.setDictIdFlag("WX");
		user.setTimeUpdate(new Date());
		user.setUserIdUpdate(sessionInfo.getUserId());
		service.update(user);
		String sql = "delete from t_sys_user_role where userId='" + id + "'";
		// Map<String, Object> params = new LinkedHashMap<String, Object>();
		// params.put("userId", id);
		try {
			iSysUserRoleService.executeSql(sql);
			carservice.doRunCall();
		} catch (Exception e) {
			e.printStackTrace();
		}

		json.setSuccess(true);
		json.setMsg("删除成功！");
		writeJson(json);
	}

	/**
	 * 修改用户角色
	 */
	public void grantRole() {
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		Json json = new Json();
		((SysUserServiceI) service).grantRole(id, ids);
		carservice.doRunCall();// rucall 方法
		json.setSuccess(true);
		json.setMsg("修改成功");
		writeJson(json);
	}

	/**
	 * 修改用户机构
	 */
	public void grantOrganization() {
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		Json json = new Json();
		((SysUserServiceI) service).grantOrganization(id, ids);
		carservice.doRunCall();// rucall 方法
		json.setSuccess(true);
		json.setMsg("修改成功");
		writeJson(json);
	}

	/**
	 * 新建一个用户
	 */
	public void save() {
		Json json = new Json();
		try {

			if (sysUser != null) {
				HqlFilter hqlFilter = new HqlFilter();
				hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ", WebMsgUtil.YOUXIAO);
				hqlFilter.addFilter("QUERY_t#userLogin_S_EQ", sysUser.getUserLogin());
				SysUser user = service.getByFilter(hqlFilter);
				if (user != null) {
					json.setMsg("新建用户失败，警员号已存在！");
				} else {
					if (file == null) {
						json.setMsg("请上传手工签名图片");
					}
					if (file != null) {
						long size = file.length();
						System.out.println("AAAA" + size);
						if (size > 5242880) { // 图片大小不能超过5M 5242880
							json.setMsg("图片大小超过5M");
						}
						if (!(fileFileName.endsWith(".jpg") || fileFileName.endsWith(".png") || fileFileName.endsWith(".gif"))) {
							json.setMsg("图片格式只能为jpg,png,gif");
						} else {
							BufferedImage bi;
							try {
								bi = ImageIO.read(file);
								ByteArrayOutputStream baos = new ByteArrayOutputStream();
								ImageIO.write(bi, "jpg", baos);
								byte[] bytes = baos.toByteArray();
								sysUser.setUserSign(bytes);
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						SysUser sessionInfo = ((SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName())).getUser();
						sysUser.setUserPwd(MD5Util.md5("123456"));
						sysUser.setTimeCreate(new Date());
						sysUser.setUserIdCreate(sessionInfo.getUserId());
						sysUser.setDictIdFlag("YX");
						service.save(sysUser);
						json.setMsg("新建用户成功！默认密码：123456");
						json.setSuccess(true);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		writeJson(json);
	}

	/**
	 * 显示图片
	 */

	public void doNotNeedSecurity_getimage() {
		ActionContext ctx = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse) ctx.get(ServletActionContext.HTTP_RESPONSE);
		try {
			response.setContentType("image/jpg; charset=UTF-8");
			SysUser user = service.getById(id);
			byte[] bytes = user.getUserSign();
			ServletOutputStream os = response.getOutputStream();
			os.write(bytes);
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 无需权限查找所有对象
	 */
	public void doNotNeedSecurity_findAll() {
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		String[] ex = { "userSign" };
		writeJsonByExcludesProperties(service.findByFilter(hqlFilter), ex);
	}

	/**
	 * 更新一个用户
	 */
	synchronized public void update() {
		Json json = new Json();
		json.setMsg("更新失败！");
		try {
			if (sysUser != null && !StringUtils.isBlank(sysUser.getUserId())) {
				HqlFilter hqlFilter = new HqlFilter();
				hqlFilter.addFilter("QUERY_t#userId_S_NE", sysUser.getUserId());
				hqlFilter.addFilter("QUERY_t#userLogin_S_EQ", sysUser.getUserLogin());
				hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ", WebMsgUtil.YOUXIAO);
				SysUser user = service.getByFilter(hqlFilter);
				if (user != null) {
					json.setMsg("更新用户失败，警员号已存在！");
				} else {
					if (file != null) {
						long size = file.length();
						System.out.println("AAAA" + size);
						if (size > 5242880) { // 图片大小不能超过5M 5242880
							json.setMsg("图片大小超过5M");
						}
						if (!(fileFileName.endsWith(".jpg") || fileFileName.endsWith(".png") || fileFileName.endsWith(".gif"))) {
							json.setMsg("图片格式只能为jpg,png,gif");
						} else {
							BufferedImage bi;
							try {
								bi = ImageIO.read(file);
								ByteArrayOutputStream baos = new ByteArrayOutputStream();
								ImageIO.write(bi, "jpg", baos);
								byte[] bytes = baos.toByteArray();
								sysUser.setUserSign(bytes);
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
					SysUser sessionInfo = ((SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName())).getUser();
					SysUser t = service.getById(sysUser.getUserId());
					BeanUtils.copyNotNullProperties(sysUser, t, new String[] { "userPwd", "timeCreate", "userIdCreate" });
					t.setTimeUpdate(new Date());
					t.setUserIdUpdate(sessionInfo.getUserId());
					service.update(t);
					json.setSuccess(true);
					json.setMsg("更新成功！");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		writeJson(json);
	}

	public void doNotNeedSecurity_getSyUserGrid() {
		Grid<VSysUser> grid = new Grid<VSysUser>();
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		if (code == null || code.equals("")) {
			hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ", "YX");
			grid.setTotal(VSysUserService.countByFilter(hqlFilter));
			List<VSysUser> l = new ArrayList<VSysUser>();
			List<VSysUser> list = VSysUserService.findByFilter(hqlFilter, page, rows);
			for (VSysUser vs : list) {
				vs.setUserSign(null);
				l.add(vs);
			}
			grid.setRows(l);
		} else {
			hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ", "YX");
			hqlFilter.addFilter("QUERY_t#orgId_S_EQ", code);
			grid.setTotal(VSysUserService.countByFilter(hqlFilter));
			List<VSysUser> l = new ArrayList<VSysUser>();
			List<VSysUser> list = VSysUserService.findByFilter(hqlFilter, page, rows);
			for (VSysUser vs : list) {
				vs.setUserSign(null);
				l.add(vs);
			}
			grid.setRows(l);

		}
		writeJson(grid);
	}

	/**
	 * 根据公司ID获取用户信息
	 */
	public void doNotNeedSecurity_getSysUserByOrgId() {
		Grid<SysUser> grid = new Grid<SysUser>();
		if (code == null || code.equals("")) {
			HqlFilter hqlFilter = new HqlFilter(getRequest());
			hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ", "YX");
			grid.setTotal(service.countByFilter(hqlFilter));
			grid.setRows(service.findByFilter(hqlFilter, page, rows));
		} else {
			HqlFilter hqlFilter = new HqlFilter();
			hqlFilter.addFilter("QUERY_t#orgId_S_EQ", code);
			hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ", "YX");
			SysOrganization org = orgService.getByFilter(hqlFilter);
			List<SysUser> l = new ArrayList<SysUser>(org.getSysUsers());
			grid.setTotal((long) l.size());
			grid.setRows(l);
		}
		writeJson(grid);
	}

	/**
	 * 获取前台参数得到数据list
	 */
	public void doNotNeedSecurity_findVUserList() {
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		String[] ex = { "userSign" };
		writeJsonByExcludesProperties(VSysUserService.findByFilter(hqlFilter), ex);
	}
	
	
}