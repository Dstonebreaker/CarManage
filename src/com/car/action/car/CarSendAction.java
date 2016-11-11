package com.car.action.car;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.car.entity.car.TCar;
import com.car.entity.car.TCarSend;
import com.car.entity.car.VCarApplySendStatus;
import com.car.entity.car.VCarModel;
import com.car.entity.car.VCarSend;
import com.car.entity.car.VCarStatus;
import com.car.service.car.ICarApplySendStatusViewService;
import com.car.service.car.ICarSendService;
import com.car.service.car.ICarSendViewService;
import com.car.service.car.ICarStatusViewService;
import com.car.service.car.ICarservice;
import com.car.service.car.IVCarModelService;
import com.framework.util.ConfigUtil;
import com.framework.util.HqlFilter;
import com.framework.util.WebMsgUtil;
import com.system.action.base.BaseAction;
import com.system.entity.easyui.Grid;
import com.system.entity.easyui.Json;
import com.system.entity.maintain.SessionInfo;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

@Namespace("/car")
@Action("send")
public class CarSendAction extends BaseAction<TCarSend> {

	/**
     *
     */
	private static final long serialVersionUID = -363411147867129439L;
	private TCarSend send;
	private String carJson;
	// 派车单报表
	private HashMap<String, String> parameter;
	private List<VCarApplySendStatus> myDataSource;
	private String data;
	private String type;
	private String startTime;
	private String endTime;

	@Autowired
	private ICarSendViewService sendViewService;

	@Autowired
	private ICarStatusViewService carStatusViewService;

	@Autowired
	private IVCarModelService carModelService;

	@Autowired
	private ICarservice carservice;

	@Autowired
	private ICarApplySendStatusViewService sendStatusViewService;

	@Autowired
	public void setService(ICarSendService service) {
		super.setService(service);
	}

	public void gridView() {
		Grid grid = new Grid();
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		grid.setTotal(sendViewService.countByFilter(hqlFilter));
		grid.setRows(sendViewService.findByFilter(hqlFilter, page, rows));
		writeJson(grid);
	}

	public void doNotNeedSecurity_getReturnCar() {
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		HqlFilter carhqlFilter = new HqlFilter();
		carhqlFilter.addFilter("QUERY_t#cardId_S_EQ", id);
		TCar car = carservice.getByFilter(carhqlFilter);
		if (car != null) {
			HqlFilter hqlFilter = new HqlFilter();
			hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ", WebMsgUtil.YOUXIAO);
			hqlFilter.addFilter("QUERY_t#dictIdApplySendStatus_S_EQ", WebMsgUtil.CARSTATUS_FREE);
			hqlFilter.addFilter("QUERY_t#orgIdManager_S_EQ", sessionInfo.getOrganization().getOrgIdManager());
			hqlFilter.addFilter("QUERY_t#carId_S_EQ", car.getCarId());
			VCarSend tCarSend = sendViewService.getByFilter(hqlFilter);
			writeJson(tCarSend);
		} else {
			writeJson(car);
		}

	}

	public void save() {
		Json json = new Json();
		try {
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
			VCarStatus car = JSON.parseObject(carJson, VCarStatus.class);
			send.setSendId(UUID.randomUUID().toString());
			send.setSendNo(getNumber("sendNo"));
			send.setSendTime(new Date());
			send.setCarId(car.getCarId());
			send.setCarNo(car.getCarNo());
			// send.setSimNo(car.getSimNo());
			send.setObdNo(car.getObdNo());
			send.setDictIdFlag(WebMsgUtil.YOUXIAO);
			send.setUserIdCreate(sessionInfo.getUser().getUserId());
			send.setTimeCreate(new Date());
			((ICarSendService) service).saveSend(send);

			json.setMsg(send.getSendId() + "#" + send.getSendNo());
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("单据保存异常！");
			json.setSuccess(false);
			e.printStackTrace();
		}
		writeJson(json);
	}

	public void send() {
		Json json = new Json();

		try {
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
			/*
			 * VCarStatus car = JSON.parseObject(carJson, VCarStatus.class);
			 * //send.setSendId(UUID.randomUUID().toString());
			 * //send.setSendNo(getNumber("sendNo")); send.setSendTime(new
			 * Date()); send.setCarId(car.getCarId());
			 * send.setCarNo(car.getCarNo()); // send.setSimNo(car.getSimNo());
			 * send.setObdNo(car.getObdNo());
			 * send.setDictIdFlag(WebMsgUtil.YOUXIAO);
			 * send.setUserIdCreate(sessionInfo.getUser().getUserId());
			 * send.setTimeCreate(new Date()); ((ICarSendService)
			 * service).saveSend(send); ((ICarSendService)
			 * service).doSend(send);
			 */

			TCarSend s = service.getById(id);
			VCarSend vs = sendViewService.getById(id);
			if (WebMsgUtil.APPLYSENDSTATUS_DHC.equals(vs.getDictIdApplySendStatus())) {
				json.setMsg("单据已派遣，可选择打印！");
				json.setSuccess(true);
			} else {
				s.setSendTime(new Date());
				s.setTimeUpdate(new Date());
				s.setUserIdUpdate(sessionInfo.getUser().getUserId());
				((ICarSendService) service).doSend(s);
				json.setMsg("派遣成功！");
				json.setSuccess(true);
			}
		} catch (Exception e) {
			json.setMsg("派车异常！");
			json.setSuccess(false);
			e.printStackTrace();
		}
		writeJson(json);
	}

	public void sendJJ() {
		Json json = new Json();

		try {
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
			VCarStatus car = JSON.parseObject(carJson, VCarStatus.class);
			send.setSendId(UUID.randomUUID().toString());
			send.setSendNo(getNumber("sendNo"));
			send.setSendTime(new Date());
			send.setCarId(car.getCarId());
			send.setCarNo(car.getCarNo());
			// send.setSimNo(car.getSimNo());
			send.setObdNo(car.getObdNo());
			send.setDictIdFlag(WebMsgUtil.YOUXIAO);
			send.setUserIdCreate(sessionInfo.getUser().getUserId());
			send.setTimeCreate(new Date());
			((ICarSendService) service).saveSend(send);
			((ICarSendService) service).doSend(send);
			json.setMsg("派遣成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("派车异常！");
			json.setSuccess(false);
			e.printStackTrace();
		}
		writeJson(json);
	}

	/**
	 * 生成报表
	 * 
	 * @return
	 */
	public String sendReport() {
		try {
			parameter = new HashMap<String, String>();

			HqlFilter hqlFilter = new HqlFilter();
			hqlFilter.addFilter("QUERY_t#sendId_S_EQ", id);
			VCarApplySendStatus sendStatus = sendStatusViewService.getByFilter(hqlFilter);

			VCarStatus car = carStatusViewService.getById(sendStatus.getCarId());

			VCarModel model = carModelService.getById(car.getModelId());

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
			HttpServletRequest request = ServletActionContext.getRequest();
			String path = request.getContextPath();
			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
			// 设置报表的标题
			parameter.put("tableTitle", "市区公安机关车辆使用派车单");
			parameter.put("makeUnit", "市公安局车改办");
			parameter.put("sendNo", sendStatus.getSendNo());// 派车单号
			parameter.put("unit", sendStatus.getOrgName());// 单位
			parameter.put("passengerNumber", String.valueOf(sendStatus.getApplyUsedPeople()));// 乘车人数
			parameter.put("proposerPhone", sendStatus.getStafPhoneLinkman());// 申请人手机
			parameter.put("proposer", sendStatus.getLinkManName());// 申请人（科所队长）
			parameter.put("reason", sendStatus.getUseCarReson());// 用车理由
			parameter.put("specialDemand", sendStatus.getCarPoliceUsed());// 特殊需求
			parameter.put("driverSign", sendStatus.getPilotName());// 驾驶员签名
			parameter.put("driverPhone", sendStatus.getStafPhonePilot());// 驾驶员手机
			parameter.put("brandNumber", sendStatus.getCarNo());// 车辆号牌
			parameter.put("brand", model.getCarBrand());// 车辆品牌
			parameter.put("sendTime", sdf.format(sendStatus.getSendTime()));// 派车时间
			parameter.put("beforeKilometres", String.valueOf(sendStatus.getSendMileage()));// 出车前公里数
			parameter.put("isGood", sendStatus.getSendMemo());// 车辆是否完好
			parameter.put("beforeOil", String.valueOf(sendStatus.getSendOil()));// 出车前油量
			parameter.put("controlCenterSign", sendStatus.getSendUserNameCreate());// 调度中心查验人签名
			parameter.put("pickUpDriverSign", sendStatus.getUserNameHandler());// 接车驾驶员签名
			parameter.put("quche_image_url", basePath + sendStatus.getSendHandlerPicture());// 取车驾驶员照片
			parameter.put("card_image_url", basePath + sendStatus.getSendFile());// 驱车驾驶员驾驶证照片
			// parameter.put("quche_image_url",
			// "http://192.168.0.121:8080/CarManage/img/car_006.png");//取车驾驶员照片
			// parameter.put("card_image_url",
			// "http://192.168.0.121:8080/CarManage/img/car_006.png");//驱车驾驶员驾驶证照片

		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 生成派车记录表
	 * 
	 * @return
	 */
	public String sendRecord() {
		try {
			parameter = new HashMap<String, String>();

			HqlFilter hqlFilter = new HqlFilter();
			startTime += " 00:00:00";
			hqlFilter.addFilter("QUERY_t#sendTime_D_GE", startTime);
			if (endTime != null && !endTime.equals("")) {
				endTime += " 23:59:59";
				hqlFilter.addFilter("QUERY_t#sendTime_D_LE", endTime);
			} else {
				endTime = startTime.substring(0, 10) + " 23:59:59";
				hqlFilter.addFilter("QUERY_t#sendTime_D_LE", endTime);
			}
			myDataSource = sendStatusViewService.findByFilter(hqlFilter);
			long total = sendStatusViewService.countByFilter(hqlFilter);

			hqlFilter.addFilter("QUERY_t#dictIdApplySendStatus_S_EQ", "ApplySendStatusYHC");
			long number_YHC = sendStatusViewService.countByFilter(hqlFilter);

			HqlFilter hqlFilter1 = new HqlFilter();
			endTime = "";
			hqlFilter1.addFilter("QUERY_t#sendTime_D_GE", startTime);
			if (endTime != null && !endTime.equals("")) {
				endTime += " 23:59:59";
				hqlFilter1.addFilter("QUERY_t#sendTime_D_LE", endTime);
			} else {
				endTime = startTime.substring(0, 10) + " 23:59:59";
				hqlFilter1.addFilter("QUERY_t#sendTime_D_LE", endTime);
			}
			hqlFilter1.addFilter("QUERY_t#dictIdApplySendStatus_S_EQ", "ApplySendStatusDHC");
			long number_DHC = sendStatusViewService.countByFilter(hqlFilter1);

			String year = startTime.substring(0, 4) + "年";
			String month = Integer.parseInt(startTime.substring(5, 7)) + "月";
			String day = Integer.parseInt(startTime.substring(8, 10)) + "日";

			// 设置报表的标题
			parameter.put("tableTitle", month + day + "派车记录表");
			String reportDetail = month + day + "共派车" + total + "台，回" + number_YHC + "台，" + number_DHC + "台未回";
			parameter.put("carCollect", reportDetail);

			// myDataSource = new ArrayList<VCarApplySendStatus>();
			// for (int i = 0; i < sendStatusList.size(); i++) {
			// VCarApplySendStatus sendStatus = new VCarApplySendStatus();
			// sendStatus.setCarGarage(signList.get(i).getSignTime());
			// omSign.setUserName(signList.get(i).getUserName());
			// myDataSource.add(omSign);
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 保存图片
	 * 
	 * @return 图片存放路径
	 */
	public void doNotNeedSecurity_upload() {
		Json json = new Json();
		try {
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
			String result = getFileName(type, data);
			if (WebMsgUtil.IMGTYPE_PERSON.equals(type)) {
				sessionInfo.setPersonImagePath(result);
			} else {
				sessionInfo.setCardImagePath(result);
			}
			json.setMsg("上传成功！");
			json.setSuccess(true);

		} catch (Exception e) {
			e.printStackTrace();
			json.setMsg("上传失败！请重试");
			json.setSuccess(false);
		}
		writeJson(json);
	}

	public void doNotNeedSecurity_getsessionInfo() {
		Json json = new Json();
		try {
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
			if (WebMsgUtil.IMGTYPE_PERSON.equals(type)) {
				json.setMsg(sessionInfo.getPersonImagePath());
			} else {
				json.setMsg(sessionInfo.getCardImagePath());
			}
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
		}
		writeJson(json);
	}

	public TCarSend getSend() {
		return send;
	}

	public void setSend(TCarSend send) {
		this.send = send;
	}

	public String getCarJson() {
		return carJson;
	}

	public void setCarJson(String carJson) {
		this.carJson = carJson.replaceAll("&quot;", "\"").replaceAll("&#39;", "\'").replaceAll("&lt;", "<").replaceAll("&gt;", ">");
	}

	public HashMap<String, String> getParameter() {
		return parameter;
	}

	public void setParameter(HashMap<String, String> parameter) {
		this.parameter = parameter;
	}

	public List<VCarApplySendStatus> getMyDataSource() {
		return myDataSource;
	}

	public void setMyDataSource(List<VCarApplySendStatus> myDataSource) {
		this.myDataSource = myDataSource;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
