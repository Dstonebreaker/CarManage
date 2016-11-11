package com.system.service.base;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.dao.BaseDaoI;
import com.framework.util.MD5Util;
import com.system.entity.maintain.SysOrganization;
import com.system.entity.maintain.SysResource;
//import com.system.entity.maintain.SysResourcetype;
import com.system.entity.maintain.SysRole;
import com.system.entity.maintain.SysUser;

import com.alibaba.fastjson.JSON;

/**
 * 初始化数据库
 * 
 * @author 陈晓亮
 * 
 */
@Service
public class InitServiceImpl implements InitServiceI {

	private static final Logger logger = Logger.getLogger(InitServiceImpl.class);

	private static final String FILEPATH = "initDataBase.xml";

	@Autowired
	private BaseDaoI baseDao;

	@Override
	synchronized public void initDb() {
		try {
			Document document = new SAXReader().read(Thread.currentThread().getContextClassLoader().getResourceAsStream(FILEPATH));

			initResourcetype(document);// 初始化资源类型

			initResource(document);// 初始化资源

			initRole(document);// 初始化角色

			initRoleResource(document);// 初始化角色和资源的关系

			initOrganization(document);// 初始化机构

			initOrganizationResource(document);// 初始化机构和资源的关系

			initUser(document);// 初始化用户

			initUserRole(document);// 初始化用户和角色的关系

			initUserOrganization(document);// 初始化用户和机构的关系

		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	private void initResourcetype(Document document) {
//		List childNodes = document.selectNodes("//resourcetypes/resourcetype");
//		for (Object obj : childNodes) {
//			Node node = (Node) obj;
//			SysResourcetype type = (SysResourcetype) baseDao.getById(SysResourcetype.class, node.valueOf("@id"));
//			if (type == null) {
//				type = new SysResourcetype();
//			}
//			type.setId(node.valueOf("@id"));
//			type.setName(node.valueOf("@name"));
//			type.setDescription(node.valueOf("@description"));
//			type.setCreateDate(new Date());
//			type.setDelFlag(0);
//			logger.info(JSON.toJSONStringWithDateFormat(type, "yyyy-MM-dd HH:mm:ss"));
//			baseDao.saveOrUpdate(type);
//		}
	}

	private void initResource(Document document) {
//		List childNodes = document.selectNodes("//resources/resource");
//		for (Object obj : childNodes) {
//			Node node = (Node) obj;
//			SysResource resource = (SysResource) baseDao.getById(SysResource.class, node.valueOf("@id"));
//			if (resource == null) {
//				resource = new SysResource();
//			}
//			resource.setId(node.valueOf("@id"));
//			resource.setName(node.valueOf("@name"));
//			resource.setUrl(node.valueOf("@url"));
//			resource.setDescription(node.valueOf("@description"));
//			resource.setIconCls(node.valueOf("@iconCls"));
//			resource.setIndexNum(Integer.parseInt(node.valueOf("@seq")));
//
//			if (!StringUtils.isBlank(node.valueOf("@target"))) {
//				resource.setTarget(node.valueOf("@target"));
//			} else {
//				resource.setTarget("");
//			}
//
//			if (!StringUtils.isBlank(node.valueOf("@pid"))) {
//				resource.setSysResource((SysResource) baseDao.getById(SysResource.class, node.valueOf("@pid")));
//			} else {
//				resource.setSysResource(null);
//			}
//
//			Node n = node.selectSingleNode("resourcetype");
//			SysResourcetype type = (SysResourcetype) baseDao.getById(SysResourcetype.class, n.valueOf("@id"));
//			if (type != null) {
//				resource.setSysResourcetype(type);
//			}
//			resource.setCreateDate(new Date());
//			resource.setDelFlag(0);
//			logger.info(JSON.toJSONStringWithDateFormat(resource, "yyyy-MM-dd HH:mm:ss"));
//			baseDao.saveOrUpdate(resource);
//		}
	}

	private void initRole(Document document) {
//		List childNodes = document.selectNodes("//roles/role");
//		for (Object obj : childNodes) {
//			Node node = (Node) obj;
//			SysRole role = (SysRole) baseDao.getById(SysRole.class, node.valueOf("@id"));
//			if (role == null) {
//				role = new SysRole();
//			}
//			role.setId(node.valueOf("@id"));
//			role.setRoleName(node.valueOf("@name"));
//			role.setDescription(node.valueOf("@description"));
//			role.setIndexNum(Integer.parseInt(node.valueOf("@seq")));
//			role.setCreateDate(new Date());
//			role.setDelFlag(0);
//			logger.info(JSON.toJSONStringWithDateFormat(role, "yyyy-MM-dd HH:mm:ss"));
//			baseDao.saveOrUpdate(role);
//		}
	}

	private void initRoleResource(Document document) {
		List<Node> childNodes = document.selectNodes("//roles_resources/role");
		for (Node node : childNodes) {
			SysRole role = (SysRole) baseDao.getById(SysRole.class, node.valueOf("@id"));
			if (role != null) {
				role.setSysResources(new HashSet());
				List<Node> cNodes = node.selectNodes("resource");
				for (Node n : cNodes) {
					SysResource resource = (SysResource) baseDao.getById(SysResource.class, n.valueOf("@id"));
					if (resource != null) {
						role.getSysResources().add(resource);
					}
				}
				logger.info(JSON.toJSONStringWithDateFormat(role, "yyyy-MM-dd HH:mm:ss"));
				baseDao.saveOrUpdate(role);
			}
		}

		SysRole role = (SysRole) baseDao.getById(SysRole.class, "0");// 将角色为0的超级管理员角色，赋予所有权限
		if (role != null) {
			role.getSysResources().addAll(baseDao.find("from SysResource t"));
			logger.info(JSON.toJSONStringWithDateFormat(role, "yyyy-MM-dd HH:mm:ss"));
			baseDao.saveOrUpdate(role);
		}
	}

	private void initOrganization(Document document) {
//		List childNodes = document.selectNodes("//organizations/organization");
//		for (Object obj : childNodes) {
//			Node node = (Node) obj;
//			SysOrganization organization = (SysOrganization) baseDao.getById(SysOrganization.class, node.valueOf("@id"));
//			if (organization == null) {
//				organization = new SysOrganization();
//			}
//			organization.setId(node.valueOf("@id"));
//			organization.setOrgname(node.valueOf("@name"));
//			organization.setOrgAddress(node.valueOf("@address"));
//			organization.setIndexNum(Integer.parseInt(node.valueOf("@seq")));
//			organization.setIconCls(node.valueOf("@iconCls"));
//			organization.setCreateDate(new Date());
//			organization.setDelFlag(0);
//			if (!StringUtils.isBlank(node.valueOf("@pid"))) {
//				organization.setSysOrganization((SysOrganization) baseDao.getById(SysOrganization.class, node.valueOf("@pid")));
//			} else {
//				organization.setSysOrganization(null);
//			}
//
//			logger.info(JSON.toJSONStringWithDateFormat(organization, "yyyy-MM-dd HH:mm:ss"));
//			baseDao.saveOrUpdate(organization);
//		}
	}

	private void initOrganizationResource(Document document) {
//		List<Node> childNodes = document.selectNodes("//organizations_resources/organization");
//		for (Node node : childNodes) {
//			SysOrganization organization = (SysOrganization) baseDao.getById(SysOrganization.class, node.valueOf("@id"));
//			if (organization != null) {
//				organization.setSysResources(new HashSet());
//				List<Node> cNodes = node.selectNodes("resource");
//				for (Node n : cNodes) {
//					SysResource resource = (SysResource) baseDao.getById(SysResource.class, n.valueOf("@id"));
//					if (resource != null) {
//						organization.getSysResources().add(resource);
//					}
//				}
//				logger.info(JSON.toJSONStringWithDateFormat(organization, "yyyy-MM-dd HH:mm:ss"));
//				baseDao.saveOrUpdate(organization);
//			}
//		}
	}

	private void initUser(Document document) {
//		List<Node> childNodes = document.selectNodes("//users/user");
//		for (Node node : childNodes) {
//			SysUser user = (SysUser) baseDao.getById(SysUser.class, node.valueOf("@id"));
//			if (user == null) {
//				user = new SysUser();
//			}
//			user.setId(node.valueOf("@id"));
//			user.setUserName(node.valueOf("@name"));
//			user.setLoginid(node.valueOf("@loginname"));
//			user.setPassword(MD5Util.md5(node.valueOf("@pwd")));
//			user.setSex(node.valueOf("@sex"));
//			user.setAge(Integer.valueOf(node.valueOf("@age")));
//			user.setCreateDate(new Date());
//			user.setDelFlag(0);
//			logger.info(JSON.toJSONStringWithDateFormat(user, "yyyy-MM-dd HH:mm:ss"));
//			List<SysUser> ul = baseDao.find("from SysUser u where u.loginid = '" + user.getLoginid() + "' and u.id != '" + user.getId() + "'");
//			for (SysUser u : ul) {
//				baseDao.delete(u);
//			}
//			baseDao.saveOrUpdate(user);
//		}
	}

	private void initUserRole(Document document) {
		List<Node> childNodes = document.selectNodes("//users_roles/user");
		for (Node node : childNodes) {
			SysUser user = (SysUser) baseDao.getById(SysUser.class, node.valueOf("@id"));
			if (user != null) {
				user.setSysRoles(new HashSet());
				List<Node> cNodes = node.selectNodes("role");
				for (Node n : cNodes) {
					SysRole role = (SysRole) baseDao.getById(SysRole.class, n.valueOf("@id"));
					if (role != null) {
						user.getSysRoles().add(role);
					}
				}
				logger.info(JSON.toJSONStringWithDateFormat(user, "yyyy-MM-dd HH:mm:ss"));
				baseDao.saveOrUpdate(user);
			}
		}

		SysUser user = (SysUser) baseDao.getById(SysUser.class, "0");// 用户为0的超级管理员，赋予所有角色
		if (user != null) {
			user.getSysRoles().addAll(baseDao.find("from SysRole"));
			logger.info(JSON.toJSONStringWithDateFormat(user, "yyyy-MM-dd HH:mm:ss"));
			baseDao.saveOrUpdate(user);
		}
	}

	private void initUserOrganization(Document document) {
		List<Node> childNodes = document.selectNodes("//users_organizations/user");
		for (Node node : childNodes) {
			SysUser user = (SysUser) baseDao.getById(SysUser.class, node.valueOf("@id"));
			if (user != null) {
				user.setSysOrganization(new HashSet());
				List<Node> cNodes = node.selectNodes("organization");
				for (Node n : cNodes) {
					SysOrganization organization = (SysOrganization) baseDao.getById(SysOrganization.class, n.valueOf("@id"));
					if (organization != null) {
						user.getSysOrganization().add(organization);
					}
				}
				logger.info(JSON.toJSONStringWithDateFormat(user, "yyyy-MM-dd HH:mm:ss"));
				baseDao.saveOrUpdate(user);
			}
		}

		SysUser user = (SysUser) baseDao.getById(SysUser.class, "0");// 用户为0的超级管理员，赋予所有机构
		if (user != null) {
			user.getSysOrganization().addAll(baseDao.find("from SysOrganization"));
			logger.info(JSON.toJSONStringWithDateFormat(user, "yyyy-MM-dd HH:mm:ss"));
			baseDao.saveOrUpdate(user);
		}
	}

}