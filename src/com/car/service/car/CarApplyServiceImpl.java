package com.car.service.car;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.entity.car.TCarApply;
import com.car.entity.car.TCarApplySendStatus;
import com.car.entity.car.TMessage;
import com.framework.dao.BaseDaoI;
import com.framework.util.WebMsgUtil;
import com.system.entity.maintain.SessionInfo;
import com.system.entity.maintain.VSysOrganizationChecker;
import com.system.service.base.BaseServiceImpl;
import com.system.service.maintain.ITsysDicService;

@Service
public class CarApplyServiceImpl extends BaseServiceImpl<TCarApply> implements ICarApplyService {

	@Autowired
	private BaseDaoI<TCarApply> applyDao;
	@Autowired
	private ITsysDicService dicService;
	@Autowired
	private IVCarManageService carservice;
	@Autowired
	private BaseDaoI<TCarApplySendStatus> sendStatusDao;
	@Autowired
	private BaseDaoI<VSysOrganizationChecker> organizationCheckerDao;
	@Autowired
	private IMessageSercice messageSercice;

	public Serializable save(TCarApply o, String dictIdApplySendStatus) {
		 SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm" );
		Serializable id = applyDao.save(o);
		TCarApplySendStatus sendStatus = new TCarApplySendStatus();
		sendStatus.setId(UUID.randomUUID().toString());
		sendStatus.setApplyId(o.getApplyId());
		sendStatus.setDictIdApplySendStatus(dictIdApplySendStatus);
		sendStatusDao.save(sendStatus);

		if (!StringUtils.isBlank(o.getPreApplyId())) {
			String hql = "update TCarApply set newApplyId=:newApplyId where applyId=:applyId";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("newApplyId", o.getApplyId());
			params.put("applyId", o.getPreApplyId());
			applyDao.executeHql(hql, params);
		}

		// 短信队列
		TMessage message = new TMessage();
		message.setMessId(UUID.randomUUID().toString());
		message.setMessType(new Integer(1));// 消息类型 1.发送；2.接收
		message.setDictIdMessageKind(WebMsgUtil.MESSAGE_KindSQYC);// 消息业务
		message.setBusinessId(o.getApplyId());// 业务id
		message.setMessKey(o.getApplyNo());// 发送短信Key
		
		String str =dicService.getById((WebMsgUtil.MESSAGE_KindSQYC)).getDictMemo()
		.replace("#No#", o.getApplyNo())
		.replace("#Ct#", dicService.getById(o.getDictIdCarType()).getDictName())
		.replace("#Rn#", dicService.getById(o.getDictIdUseCarReson()).getDictName())
		.replace("#Cu#", sdf.format(o.getApplyUsingTime()))
		.replace("#Cr#", sdf.format(o.getApplyRemandTime()));			
					
		message.setMessInfo(str);// 短信内容
		
	    String hql = "from VSysOrganizationChecker where orgid=:orgid";
	    Map<String, Object> params = new HashMap<String, Object>();
	    params.put("orgid", o.getOrgId());
	    List<VSysOrganizationChecker> tempList = organizationCheckerDao.find(hql, params);
	    String stringPNo = "";//电话号码
	    String stringTemp="";
	    if(tempList!=null){
	    for (VSysOrganizationChecker vSysOrganizationChecker : tempList) {
	    	if(vSysOrganizationChecker.getUserphone()!=null&&!"".equals(vSysOrganizationChecker.getUserphone())){	    		
	    		stringPNo+=vSysOrganizationChecker.getUserphone()+";";
	    		
	    		}
		   }
	    }
	    if(!"".equals(stringPNo)){
	    	stringTemp = stringPNo.substring(0,stringPNo .length()-1);
	    }	 
		message.setMessPhones(stringTemp);// 手机号
		message.setMessTime(new Date());// 保存时间
		message.setMessStatus(new Integer(1));// 状态
		messageSercice.addSend(message);
		// messageSercice.addSend(message)

/*		
		 * String sql =
		 * "insert into t_message (messId,messInfo,messPhones,messTime,messStatus) SELECT uuid(),:info,userPhone,:time,0 "
		 * + "FROM ((((((t_sys_user u " +
		 * "LEFT JOIN t_sys_user_organization uo ON ((u.userId = uo.userId))) "
		 * + "LEFT JOIN t_sys_organization o ON ((uo.orgId = o.orgId))) " +
		 * "LEFT JOIN t_sys_user_role ur ON ((u.userId = ur.userId))) LEFT JOIN t_sys_role r ON ((ur.roleId = r.roleId))) "
		 * + "LEFT JOIN t_sys_role_resource rr ON ((r.roleId = rr.roleId))) " +
		 * "LEFT JOIN t_sys_resource re ON ((rr.resoId = re.resoId))) WHERE re.resoId = :resoId AND o.orgId = :orgId"
		 * ; Map<String, Object> params = new HashMap<String, Object>();
		 * params.put("info", "有新的用车申请，请登录系统查看，申请单号是：" + o.getApplyNo());
		 * params.put("time", new Date()); params.put("resoId",
		 * WebMsgUtil.DEPT_APPROVAL_RESOURCE_ID); params.put("orgId",
		 * o.getOrgId()); applyDao.executeSql(sql, params);*/
		 

		return id;
	}

	@Override
	public void doApproval(TCarApply apply, SessionInfo sessionInfo) {
		TCarApply ca = applyDao.getById(TCarApply.class, apply.getApplyId());
		ca.setDictIdCheckStatus(apply.getDictIdCheckStatus());

		if (StringUtils.isBlank(ca.getUserIdCheck1())) {
			ca.setApplyCheck1(apply.getApplyCheck2());
			ca.setUserIdCheck1(sessionInfo.getUser().getUserId());
			ca.setTimeCheck1(new Date());

			if (WebMsgUtil.CHECK_STATUS_AGREE1.equals(ca.getDictIdCheckStatus())) {
				String hql = "from TCarApplySendStatus where applyId=:applyId";
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("applyId", ca.getApplyId());
				TCarApplySendStatus sendStatus = sendStatusDao.getByHql(hql, params);
				sendStatus.setDictIdApplySendStatus(WebMsgUtil.APPLYSENDSTATUS_DPC);
				sendStatusDao.update(sendStatus);
			}

			/*
			 * // 短信队列 -- 科室审批通过，将处领导加入队列 if
			 * (WebMsgUtil.CHECK_STATUS_AGREE1.equals
			 * (ca.getDictIdCheckStatus())) { String sql =
			 * "insert into t_message (messId,messInfo,messPhones,messTime,messStatus) SELECT uuid(),:info,userPhone,:time,0 "
			 * + "FROM ((((((t_sys_user u " +
			 * "LEFT JOIN t_sys_user_organization uo ON ((u.userId = uo.userId))) "
			 * + "LEFT JOIN t_sys_organization o ON ((uo.orgId = o.orgId))) " +
			 * "LEFT JOIN t_sys_user_role ur ON ((u.userId = ur.userId))) LEFT JOIN t_sys_role r ON ((ur.roleId = r.roleId))) "
			 * +
			 * "LEFT JOIN t_sys_role_resource rr ON ((r.roleId = rr.roleId))) "
			 * +
			 * "LEFT JOIN t_sys_resource re ON ((rr.resoId = re.resoId))) WHERE re.resoId = :resoId AND o.orgIdManager = :orgId"
			 * ; Map<String, Object> params = new HashMap<String, Object>();
			 * params.put("info", "有新的用车申请，已通过" +
			 * sessionInfo.getOrganization().getOrgName() + "的" +
			 * sessionInfo.getUser().getUserName() + "审批通过，请登录系统查看，申请单号是：" +
			 * ca.getApplyNo()); params.put("time", new Date());
			 * params.put("resoId", WebMsgUtil.OFFICE_APPROVAL_RESOURCE_ID);
			 * params.put("orgId",
			 * sessionInfo.getOrganization().getOrgIdManager());
			 * applyDao.executeSql(sql, params); }
			 */
		} else {
			ca.setApplyCheck2(apply.getApplyCheck2());
			ca.setUserIdCheck2(sessionInfo.getUser().getUserId());
			ca.setTimeCheck2(new Date());

			/*
			 * if
			 * (WebMsgUtil.CHECK_STATUS_AGREE2.equals(ca.getDictIdCheckStatus(
			 * ))) { TMessage m = new TMessage();
			 * m.setMessId(UUID.randomUUID().toString()); m.setMessStatus(0);
			 * m.setMessTime(new Date()); m.setMessInfo("您单号为" + ca.getApplyNo()
			 * + "的用车申请经处领导通过，可以前去领用车辆了。");
			 * m.setMessPhones(ca.getStafPhoneLinkman());
			 * 
			 * }
			 */
		}
		applyDao.update(ca);
	}

	@Override
	public void delete(String id) {
		TCarApply apply = applyDao.getById(TCarApply.class, id);
		String hql = "delete from TCarApplySendStatus where applyId=:applyId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("applyId", apply.getApplyId());
		sendStatusDao.executeHql(hql, params);
		applyDao.delete(apply);
	}
	
	public Serializable doInfoCollection(TCarApply o, String sendStatusId) {
		Serializable id = applyDao.save(o);
		
		TCarApplySendStatus sendStatus = sendStatusDao.getById(TCarApplySendStatus.class, sendStatusId);
		sendStatus.setApplyId(id.toString());
		sendStatusDao.update(sendStatus);
		return id;
	}

}
