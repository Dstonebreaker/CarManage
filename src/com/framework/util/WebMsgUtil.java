package com.framework.util;

public interface WebMsgUtil {

	public static String MSG_SAVE_SUCCESS = "保存成功!";
	public static String MSG_SAVE_FAILE = "保存失败!";
	public static String MSG_UPDATE_SUCCESS = "更新成功!";
	public static String MSG_UPDATE_FAILE = "更新失败!";
	public static String MSG_SUBMIT_SUCCESS = "上报成功!";
	public static String YOUXIAO = "YX";// 有效
	public static String WUXIAO = "WX";// 无效
	public static String CLASS_BXCONP = "BXConp";// 类型 保险公司
	public static String CLASS_BXType = "BXType";// 类型 保险类型
	public static String CLASS_ClaType = "ClaType";// 类型 理赔状态
	public static String CLASS_RepairFactory = "RepairFactory";// 类型 维修厂家
	public static String CLASS_CarSeries = "CarSeries";// 类型 车系
	public static String CHECK_STATUS_START = "CheckStatusStart"; // 未审批
	public static String CHECK_STATUS_AGREE1 = "CheckStatusAgree1"; // 科室审批通过
	public static String CHECK_STATUS_REFUSE1 = "CheckStatusRefuse1"; // 科室审批拒绝
	public static String CHECK_STATUS_AGREE2 = "CheckStatusAgree2"; // 处审批通过
	public static String CHECK_STATUS_REFUSE2 = "CheckStatusRefuse2"; // 处审批拒绝

	public static String APPLYSENDSTATUS_DHC = "ApplySendStatusDHC"; // 待还车
	public static String APPLYSENDSTATUS_DPC = "ApplySendStatusDPC"; // 待派车
	public static String APPLYSENDSTATUS_SQZ = "ApplySendStatusSQZ"; // 申请中
	public static String APPLYSENDSTATUS_YHC = "ApplySendStatusYHC"; // 已还车
	public static String APPLYSENDSTATUS_YSCPCD = "ApplySendStatusYSCPCD"; // 已生成派车单

	public static String CARSTATUS_FREE = "CarStatusFree"; // 车辆状态 - 空闲
	public static String CARSTATUS_OUT = "CarStatusOut"; // 车辆状态 - 外派
	public static String CARSTATUS_SEALED="CarStatusSealed"; //已暂时封存
	public static String CARSTATUS_AUCTION="CarStatusAuction";//已拍卖
	public static String CARSTATUS_SCRAP="CarStatusScrap";//已报废
	

	public static String CARSENDTYPE_JJ = "CarSendTypeJJ"; // 紧急派车
	public static String CARSENDTYPE_WD = "CarSendTypeWD"; // 无单派车
	public static String CARSENDTYPE_YD = "CarSendTypeYD"; // 有单派车

	public static String DEPT_APPROVAL_RESOURCE_ID = "161f060d-7d33-42a4-9459-6860b269034e"; // 处长审批功能操作的资源id
	public static String OFFICE_APPROVAL_RESOURCE_ID = "09c949ac-ac43-4c21-b45d-6e5c686d4938"; // 处领导审批功能操作的资源id

	public static String IMGTYPE_PERSON="person";//人员图片类型
	public static String IMGTYPE_CARD="card";//卡片图片类型
	
	public static String TREAT_RESULT_SHZ = "TreatResultSHZ"; // 审核中
	public static String TREAT_RESULT_SPJJ= "TreatResultSPJJ"; // 审批拒绝
	public static String TREAT_RESULT_CZZ = "TreatResultCZZ"; // 处置中
	public static String TREAT_RESULT_CZWJ= "TreatResultCZWJ"; // 处置完结
	
	public static String  TREAT_MODE_ZSFC = "TreatModeZSFC"; // 暂时封存
	public static String  TREAT_MODE_PM= "TreatModePM"; // 拍卖	
	
	
	public static String  MESSAGE_KindSQYC  = "MessageKindSQYC";//申请用车
	public static String  CLASS_REGION="Region";//区域
	public static String  CAR_NOTSPECIAL  = "d498591a-4bc9-4e1e-91f5-cff34bf6a7cb";//不是特种车辆
	
	public static String DXLX="dxlx";//短信类型
	public static String DXYHM="dxyhm";//短信用戶名
	
	public static String MESSAGE_KindDXJS="MessageKindDXJS";//短信接收
	
}
