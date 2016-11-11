package com.system.action.maintain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.catalina.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.car.service.car.ICarservice;
import com.framework.util.BeanUtils;
import com.framework.util.ConfigUtil;
import com.framework.util.HqlFilter;
import com.framework.util.WebMsgUtil;
import com.system.action.base.BaseAction;
import com.system.entity.easyui.Json;
import com.system.entity.easyui.Tree;
import com.system.entity.maintain.SessionInfo;
import com.system.entity.maintain.SysOrganization;
import com.system.entity.maintain.SysUser;
import com.system.entity.maintain.SysUserOrganization;
import com.system.entity.maintain.VCarManagerOrganization;
import com.system.entity.maintain.VSysOrganization;
import com.system.service.maintain.ISysUserOrganizationService;
import com.system.service.maintain.IVCarManagerOrganizationService;
import com.system.service.maintain.SysOrganizationServiceI;
import com.system.service.maintain.VISysOrganizationService;

/**
 * 机构
 * 
 * 访问地址：/maintain/sysOrganization.cxl
 * 
 * @author 许中
 * 
 */
@Namespace("/maintain")
@Action(value = "sysOrganization")
public class SysOrganizationAction extends BaseAction<SysOrganization> {

	private static final long serialVersionUID = 7268152761952337349L;

	private String orgIdManager;

	private SysOrganization sysOrganization;
	private int userId;

	public SysOrganization getSysOrganization() {
		return sysOrganization;
	}

	public void setSysOrganization(SysOrganization sysOrganization) {
		this.sysOrganization = sysOrganization;
	}

	private String timeCreate;

	public String getTimeCreate() {
		return timeCreate;
	}

	public void setTimeCreate(String timeCreate) {
		this.timeCreate = timeCreate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * 注入业务逻辑，使当前action调用service.xxx的时候，直接是调用基础业务逻辑
	 * 
	 * 如果想调用自己特有的服务方法时，请使用((TServiceI) service).methodName()这种形式强转类型调用
	 * 
	 * @param service
	 */
	@Autowired
	public void setService(SysOrganizationServiceI service) {
		this.service = service;
	}

	@Autowired
	public ISysUserOrganizationService iSysUserOrganizationService;
	@Autowired
	public ICarservice carservice;
	@Autowired
	public VISysOrganizationService viSysOrganizationService;
	@Autowired
	public IVCarManagerOrganizationService vCarManagerOrganizationService;

	/**
	 * 保存一个机构
	 */
	public void save() {
		Json json = new Json();
		if (sysOrganization != null) {
			HqlFilter hqlFilter = new HqlFilter(getRequest());
			hqlFilter.addFilter("QUERY_t#orgName_S_EQ",
					sysOrganization.getOrgName());
			hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ", WebMsgUtil.YOUXIAO);
			SysOrganization sysOrganizations = ((SysOrganizationServiceI) service)
					.getByFilter(hqlFilter);
			if (sysOrganizations != null) {
				json.setMsg("新建单位失败，单位名字已存在！");
			} else if (sysOrganization.getOrgIconic() == null) {
				json.setMsg("单位图标不能为空！");
			} else {
				SessionInfo sessionInfo = (SessionInfo) getSession()
						.getAttribute(ConfigUtil.getSessionInfoName());
				((SysOrganizationServiceI) service).saveOrganization(
						sysOrganization, sessionInfo.getUser().getUserId());
				carservice.doRunCalls();
				json.setSuccess(true);
				json.setMsg("添加成功");
			}
		}
		writeJson(json);
	}

	/**
	 * 删除一个机构
	 */
	public void delete() {
		Json json = new Json();
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		// SysOrganization organization = service.getById(id);
		String sql = "update t_sys_organization u, (select orgRealPath from t_sys_organization where orgId='"
				+ id
				+ "')o set u.dictIdFlag ='WX' where u.orgRealPath like concat( o.orgRealPath, '%')";
		// String hql =
		// "update SysOrganization set dictIdFlag=:dictIdFlag where orgId=:orgId";
		// Map params = new LinkedHashMap();
		// params.put("dictIdFlag", "WX");
		// params.put("orgId", id);
		try {
			json.setSuccess(true);
			json.setMsg("删除成功");
			service.executeSql(sql);
			carservice.doRunCalls();// rucalls 方法
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg("删除失败");
		}
		writeJson(json);
	}
	/**
	 * 更新机构
	 */
	public void update() {
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		Json json = new Json();
		if (!StringUtils.isBlank(sysOrganization.getOrgId())) {
			if (sysOrganization.getSysOrganization() != null
					&& StringUtils.equals(sysOrganization.getOrgId(),
							sysOrganization.getOrgIdOrgParent())) {
				json.setMsg("父机构不可以是自己！");
			}
			HqlFilter hqlFilter = new HqlFilter(getRequest());
			hqlFilter.addFilter("QUERY_t#orgId_S_NE",
					sysOrganization.getOrgId());
			hqlFilter.addFilter("QUERY_t#orgName_S_EQ",
					sysOrganization.getOrgName());
			hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ", WebMsgUtil.YOUXIAO);
			SysOrganization sysOrganizations = ((SysOrganizationServiceI) service)
					.getByFilter(hqlFilter);
			if (sysOrganizations != null) {
				json.setMsg("更新单位失败，单位名字已存在！");
			} else {
				String idm = sysOrganization.getOrgId();
				HqlFilter hqlFilter1 = new HqlFilter(getRequest());
				hqlFilter1.addFilter("QUERY_t#orgId_S_EQ", idm);
				hqlFilter1.addFilter("QUERY_t#dictIdFlag_S_EQ",
						WebMsgUtil.YOUXIAO);
				SysOrganization sysOrganizationInfo = ((SysOrganizationServiceI) service)
						.getByFilter(hqlFilter1);
				if (("").equals(sysOrganization.getOrgIdManager())
						|| sysOrganization.getOrgIdManager() == null) { // 说明车辆管理是自己
					sysOrganization.setOrgIdManager(sysOrganization.getOrgId());
				}
				BeanUtils.copyNotNullProperties(sysOrganization,
						sysOrganizationInfo, new String[] { "userIdUpdate",
								"timeUpdate" });
				sysOrganizationInfo.setTimeUpdate(new Date());
				sysOrganizationInfo.setUserIdUpdate(sessionInfo.getUser()
						.getUserId());
				// sysOrganizationInfo.setDictIdOrgType(sysOrganization.getDictIdOrgType());
				// sysOrganizationInfo.setOrgAddress(sysOrganization.getOrgAddress());
				// sysOrganizationInfo.setOrgIconic(sysOrganization.getOrgIconic());
				// sysOrganizationInfo.setOrgIdManager(sysOrganization.getOrgIdManager());
				// sysOrganizationInfo.setOrgIdOrgParent(sysOrganization.getOrgIdOrgParent());
				// sysOrganizationInfo.setOrgLinkman(sysOrganization.getOrgLinkman());
				// sysOrganizationInfo.setOrgLinkPhone(sysOrganization.getOrgLinkPhone());
				// sysOrganizationInfo.setOrgName(sysOrganization.getOrgName());
				// sysOrganizationInfo.setOrgNameAbbr(sysOrganization.getOrgNameAbbr());
				// sysOrganizationInfo.setTimeUpdate(sysOrganization.getTimeUpdate());
				// sysOrganizationInfo.setUserIdUpdate(sysOrganization.getUserIdUpdate());
				// sysOrganization.setTimeUpdate(new Date());
				// sysOrganization.setUserIdUpdate(sessionInfo.getUser().getUserId());
				((SysOrganizationServiceI) service)
						.updateOrganization(sysOrganizationInfo);
				carservice.doRunCall();// rucall 方法
				carservice.doRunCalls();// rucalls 方法
				json.setSuccess(true);
				json.setMsg("更新成功");
			}
		}
		writeJson(json);
	}

	/**
	 * 无需权限查找所有对象
	 */
	public void doNotNeedSecurity_findAll() {
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		writeJson(service.findByFilter(hqlFilter));
	}

	public void doNotNeedSecurity_getcomboTree() {
		String realpath = "";
		if (ids != null && !"".equals(ids)) {
			SysOrganization sysOrganizationId = ((SysOrganizationServiceI) service)
					.getById(ids);
			if (sysOrganizationId != null) {
				realpath = sysOrganizationId.getOrgRealPath();
			}
		}
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		List<SysOrganization> organizations = ((SysOrganizationServiceI) service)
				.organizationTreeGrid(hqlFilter, "");
		List<Tree> treelist = new ArrayList<Tree>();
		for (int i = 0; i < organizations.size(); i++) {
			Tree tree = new Tree();
			tree.setText(organizations.get(i).getOrgName());
			tree.setPid(organizations.get(i).getOrgIdOrgParent());
			tree.setId(organizations.get(i).getOrgId());
			treelist.add(tree);
			if (realpath.contains(organizations.get(i).getOrgRealPath())) {
				doNotNeedSecurity_getTree(realpath, organizations.get(i),
						treelist);
			} else {
				BeanUtils.copyNotNullProperties(organizations.get(i), tree);
			}
		}
		writeJson(treelist);
	}

	private void doNotNeedSecurity_getTree(String realpath,
			SysOrganization organizationInfo, List<Tree> treelist) {
		String path = "";
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		List<SysOrganization> list = ((SysOrganizationServiceI) service)
				.organizationTreeGrid(hqlFilter, organizationInfo.getOrgId());
		for (SysOrganization o : list) {
			Tree tree = new Tree();
			tree.setText(o.getOrgName());
			tree.setPid(o.getOrgIdOrgParent());
			tree.setId(o.getOrgId());
			treelist.add(tree);
			path = o.getOrgRealPath();
			if (realpath.contains(path)) {
				if (realpath.equals(path)) {
					BeanUtils.copyNotNullProperties(o, tree);
					tree.setChecked(true);
				} else {
					doNotNeedSecurity_getTree(realpath, o, treelist);
				}
			} else {
				BeanUtils.copyNotNullProperties(o, tree);
			}
		}
	}

	/**
	 * 获得机构下拉树
	 */
	public void doNotNeedSecurity_comboTree() {
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		List<SysOrganization> organizations = ((SysOrganizationServiceI) service)
				.organizationTreeGrid(hqlFilter, id);
		List<Tree> tree = new ArrayList<Tree>();
		for (SysOrganization organization : organizations) {
			Tree node = new Tree();
			BeanUtils.copyNotNullProperties(organization, node);
			node.setText(organization.getOrgName());
			node.setPid(organization.getOrgIdOrgParent());
			node.setId(organization.getOrgId());
			tree.add(node);
		}
		writeJson(tree);
	}

	/**
	 * 获得机构下拉树
	 */
	public void doNotNeedSecurity_comboTreeByManager() {
		try {
			HqlFilter hqlFilter = new HqlFilter(getRequest());
			List<SysOrganization> organizations = ((SysOrganizationServiceI) service)
					.organizationTreeGrid(hqlFilter, id, orgIdManager);
			List<Tree> tree = new ArrayList<Tree>();
			for (SysOrganization organization : organizations) {
				Tree node = new Tree();
				BeanUtils.copyNotNullProperties(organization, node);
				node.setText(organization.getOrgName());
				node.setPid(organization.getOrgIdOrgParent());
				node.setId(organization.getOrgId());
				tree.add(node);
			}
			writeJson(tree);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 根据当前session获得机构下拉树
	 */
	public void doNotNeedSecurity_comboTreeCurrentSession() {
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		List<SysOrganization> organizations = null;
		List<SysOrganization> l = new ArrayList<SysOrganization>();
		HqlFilter hqlFilter = new HqlFilter();
		String orgPath = sessionInfo.getOrganization().getOrgPath();
		String orgRealPath = sessionInfo.getOrganization().getOrgRealPath();
		if (orgPath.equals(orgRealPath)) {
			Map<String, Object> params = new HashMap<String, Object>();
			String hql = "from SysOrganization t where (t.orgIdOrgParent=:id or t.orgId=:orgid) and t.dictIdFlag ='YX'";
			params.put("id", sessionInfo.getOrganization().getOrgId());
			params.put("orgid", sessionInfo.getOrganization().getOrgId());
			organizations = service.find(hql, params);
		} else {
			hqlFilter.addFilter("QUERY_t#orgPath_S_EQ", sessionInfo
					.getOrganization().getOrgPath());
			SysOrganization org = service.getByFilter(hqlFilter);
			Map<String, Object> params = new HashMap<String, Object>();
			String hql = "from SysOrganization t where (t.orgIdOrgParent=:id or t.orgId=:orgid) and t.dictIdFlag ='YX'";
			params.put("id", org.getOrgId());
			params.put("orgid", org.getOrgId());
			organizations = service.find(hql, params);
		}

		if (organizations != null && organizations.size() > 0) {
			for (SysOrganization o : organizations) {
				SysOrganization organization = new SysOrganization();
				BeanUtils.copyProperties(o, organization);
				Set<SysOrganization> set = o.getSysOrganizations();
				if (set != null && !set.isEmpty()) {
					organization.setState("closed");
				}
				l.add(organization);
			}
		}

		List<Tree> tree = new ArrayList<Tree>();
		for (SysOrganization organization : l) {
			Tree node = new Tree();
			BeanUtils.copyNotNullProperties(organization, node);
			node.setText(organization.getOrgName());
			node.setPid(organization.getOrgIdOrgParent());
			node.setId(organization.getOrgId());
			tree.add(node);
		}

		writeJson(tree);

	}

	/**
	 * 获得机构
	 */
	public void doNotNeedSecurity_orgComboTree() {
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		List<SysOrganization> organizations = ((SysOrganizationServiceI) service)
				.organizationTreeGrid(hqlFilter, id);
		SysOrganization eOrganization = ((SysOrganizationServiceI) service)
				.getById(id);
		organizations.add(eOrganization);
		List<Tree> tree = new ArrayList<Tree>();
		for (SysOrganization organization : organizations) {
			Tree node = new Tree();
			BeanUtils.copyNotNullProperties(organization, node);
			node.setText(organization.getOrgName());
			node.setPid(organization.getOrgIdOrgParent());
			node.setId(organization.getOrgId());
			tree.add(node);
		}

		writeJson(tree);
	}

	/**
	 * 用户删单位提示是否有子机构
	 */
	public void doNotNeedSecurity_haveSubOrg() {
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		String str = "";
		if (id == null || "".equals(id)) {
			str = "e";
		} else {
			List<VSysOrganization> organizations = viSysOrganizationService
					.organizationTreeGrid(hqlFilter, id);
			if (organizations == null || organizations.size() < 1) {
				str = "n";
			} else {
				str = "y";
			}
		}
		writeJson(str);
	}

	/**
	 * 获得当前用户能看到的所有机构树
	 */
	public void doNotNeedSecurity_getSyorganizationsTree() {
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		List<SysOrganization> organizations = ((SysOrganizationServiceI) service)
				.organizationTreeGrid(hqlFilter, id);
		List<SysOrganization> l = new ArrayList<SysOrganization>(organizations);
		List<Tree> tree = new ArrayList<Tree>();
		for (SysOrganization organization : l) {
			Tree node = new Tree();
			BeanUtils.copyNotNullProperties(organization, node);
			node.setText(organization.getOrgName());
			node.setPid(organization.getOrgIdOrgParent());
			node.setId(organization.getOrgId());
			HqlFilter hqlFilter1 = new HqlFilter(getRequest());
			hqlFilter1.addFilter("QUERY_t#userId_S_EQ", ids);
			hqlFilter1.addFilter("QUERY_t#orgId_S_EQ", organization.getOrgId());
			SysUserOrganization sysUserOrganization = iSysUserOrganizationService
					.getByFilter(hqlFilter1);
			if (sysUserOrganization != null) {
				node.setChecked(true);
			}
			tree.add(node);
		}
		writeJson(tree);
	}

	public void doNotNeedSecurity_getUserSyorganizationsTree() {
		String realpath = "#############";
		String orgid = "##################";
		if (ids != null && !"".equals(ids)) {
			HqlFilter hqlFilter1 = new HqlFilter(getRequest());
			hqlFilter1.addFilter("QUERY_t#userId_S_EQ", ids);
			SysUserOrganization sysUserOrganization = iSysUserOrganizationService
					.getByFilter(hqlFilter1);
			if (sysUserOrganization != null) {
				orgid = sysUserOrganization.getOrgId();
				SysOrganization sysOrganizationId = ((SysOrganizationServiceI) service)
						.getById(orgid);
				if (sysOrganizationId != null) {
					realpath = sysOrganizationId.getOrgRealPath();
				}
			}
		}
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		List<SysOrganization> organizations = ((SysOrganizationServiceI) service)
				.organizationTreeGrid(hqlFilter, "");
		List<Tree> treelist = new ArrayList<Tree>();
		for (int i = 0; i < organizations.size(); i++) {
			Tree tree = new Tree();
			tree.setText(organizations.get(i).getOrgName());
			tree.setPid(organizations.get(i).getOrgIdOrgParent());
			tree.setId(organizations.get(i).getOrgId());
			treelist.add(tree);
			if (realpath.contains(organizations.get(i).getOrgRealPath())) {
				doNotNeedSecurity_getTree(realpath, organizations.get(i),
						treelist);
			} else {
				BeanUtils.copyNotNullProperties(organizations.get(i), tree);
			}
		}
		writeJson(treelist);
	}

	/**
	 * 获得当前用户所在机构及子机构
	 */
	public void doNotNeedSecurity_getSelfAndSubSyorganizationsTree() {
		List<SysOrganization> organizations = ((SysOrganizationServiceI) service)
				.selfAndSubOrganizationTreeGrid(id);
		writeJson(organizations);
	}

	/**
	 * 获得当前用户的机构
	 */
	public void doNotNeedSecurity_getSyorganizationByUserId() {
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		hqlFilter.addFilter("QUERY_user#userId_S_EQ", id);
		List<SysOrganization> organizations = ((SysOrganizationServiceI) service)
				.findOrganizationByFilter(hqlFilter);
		writeJson(organizations);
	}

	public void getChildrenTreeGrid() {
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		List<VSysOrganization> l = new ArrayList<VSysOrganization>();
		List<VSysOrganization> organizations = viSysOrganizationService
				.organizationTreeGrid(hqlFilter, id);
		for (VSysOrganization organization : organizations) {
			if (organization.getOrgId().equals(organization.getOrgIdManager())) {
				organization.setOrgManagerName(organization.getOrgName());
			}
			l.add(organization);
		}
		writeJson(l);
	}

	/**
	 * 获得二级机构combobox
	 */
	public void getComboboxForLvlTwo() {
		SysOrganization lvlone = ((SysOrganizationServiceI) service)
				.getById("30");
		List<SysOrganization> l = new ArrayList<SysOrganization>(
				lvlone.getSysOrganizations());
		writeJson(l);
	}

	/**
	 * 获得所有车辆管理机构
	 */
	public void doNotNeedSecurity_getOrg() {
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		if (userId == 0) {
			hqlFilter.addFilter("QUERY_t#orgIdManager_S_EQ", sessionInfo
					.getOrganization().getOrgIdManager());
		}
		List<VCarManagerOrganization> organizations = vCarManagerOrganizationService
				.findByFilter(hqlFilter);
		writeJson(organizations);
	}

	/**
	 * 不用userid获得所有车辆管理机构
	 */
	public void doNotNeedSecurity_getOrgManger() {
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		List<VCarManagerOrganization> organizations = vCarManagerOrganizationService
				.findByFilter(hqlFilter);
		writeJson(organizations);
	}

	public void doNotNeedSecurity_getOrgList() {
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		List<VSysOrganization> organizations = viSysOrganizationService
				.findByFilter(hqlFilter);
		writeJson(organizations);
	}

	public String getOrgIdManager() {
		return orgIdManager;
	}

	public void setOrgIdManager(String orgIdManager) {
		this.orgIdManager = orgIdManager;
	}

}