package com.car.action.car;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.car.entity.car.TCar;
import com.car.entity.car.TCarStatus;
import com.car.entity.car.VCarManage;
import com.car.entity.car.VCarUsedCost;
import com.car.service.car.ICarModelService;
import com.car.service.car.ICarStatuService;
import com.car.service.car.ICarservice;
import com.car.service.car.IVCarManageService;
import com.car.service.car.IVCarMasterService;
import com.framework.util.BeanUtils;
import com.framework.util.ConfigUtil;
import com.framework.util.ExPortExcel;
import com.framework.util.HqlFilter;
import com.framework.util.WebMsgUtil;
import com.system.action.base.BaseAction;
import com.system.entity.easyui.Grid;
import com.system.entity.easyui.Json;
import com.system.entity.maintain.ExportExcelBean;
import com.system.entity.maintain.SessionInfo;

@Namespace("/car")
@Action("carManage")
public class CarAction extends BaseAction<TCar> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7652165247907652259L;
	private TCar car;
	private int  userId=0;
	@Autowired
	private ICarservice carservice;
	@Autowired
	private IVCarMasterService carMasterService;
	@Autowired
	private IVCarManageService vCarManageService;
	@Autowired
	private ICarStatuService carStatuService;
	@Autowired
	private ICarModelService carModelService;
	private String carTime;// 车辆出厂日期
	private String startTime;
	private String endTime;
	private String carScrap;// 报废日期
	private String carRegisterDate;// 初次登记时间
	private String simNumber;// sim卡号
	private String fileName;
	private InputStream inputStream;

	@Autowired
	public void setService(ICarservice service) {
		// TODO Auto-generated method stub
		super.setService(service);
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public void grid() {
		Grid<VCarManage> grid = new Grid<VCarManage>();
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		if(userId==0){			
			hqlFilter.addFilter("QUERY_t#orgIdManager_S_EQ", sessionInfo.getOrganization().getOrgIdManager());
		}
//		if (startTime != null) {
//			hqlFilter.addFilter("QUERY_t#timeCreate_D_GE", startTime);
//		}
//		if (endTime != null) {
//			hqlFilter.addFilter("QUERY_t#timeCreate_D_LE", endTime);
//		}
		hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ", WebMsgUtil.YOUXIAO);
		grid.setTotal(vCarManageService.countByFilter(hqlFilter));
		grid.setRows(vCarManageService.findByFilter(hqlFilter, page, rows));
		writeJson(grid);

	}

	/**
	 * 新建车辆档案
	 * 
	 */
	public void save() {
		Json json = new Json();
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if (car != null) {
				HqlFilter hqlFilter = new HqlFilter(getRequest());
				hqlFilter.addFilter("QUERY_t#carNo_S_EQ", car.getCarNo());
				hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ",
						WebMsgUtil.YOUXIAO);
				TCar tCar = carservice.getByFilter(hqlFilter);
				if (tCar != null) {
					json.setMsg("新建车辆失败，车牌号已存在！");
				} else {					
					HqlFilter hqlFilter2 = new HqlFilter(getRequest());
					hqlFilter2.addFilter("QUERY_t#carFileNo_S_EQ", car.getCarFileNo());
					hqlFilter2.addFilter("QUERY_t#dictIdFlag_S_EQ",	WebMsgUtil.YOUXIAO);
					TCar tempCar = carservice.getByFilter(hqlFilter2);
					if(tempCar!=null){
						json.setMsg("新建车辆失败，内部档案号已存在！");
						
					}				
						else {					
							HqlFilter hqlFilter3 = new HqlFilter(getRequest());
							hqlFilter3.addFilter("QUERY_t#carEngineNo_S_EQ", car.getCarEngineNo());
							hqlFilter3.addFilter("QUERY_t#dictIdFlag_S_EQ",	WebMsgUtil.YOUXIAO);
							TCar tempCar3 = carservice.getByFilter(hqlFilter3);
							if(tempCar3!=null){
								json.setMsg("新建车辆失败，发动机号已存在！");
								
							}else{					
								HqlFilter hqlFilter4 = new HqlFilter(getRequest());
								hqlFilter4.addFilter("QUERY_t#carIdentifyNo_S_EQ", car.getCarIdentifyNo());
								hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ",	WebMsgUtil.YOUXIAO);
								TCar tempCar4= carservice.getByFilter(hqlFilter4);
								if(tempCar4!=null){
									json.setMsg("新建车辆失败，车辆识别号已存在！");
									
								}else{	
									car.setCarId(UUID.randomUUID().toString());
									car.setCarTime(sdf.parse(carTime));// 出厂日期
									car.setCarScrap(sdf.parse(carScrap));// 报废时间
									car.setCarRegisterDate(sdf.parse(carRegisterDate));// 初次登记时间
									car.setTimeCreate(new Date());
									car.setDictIdFlag(WebMsgUtil.YOUXIAO);
									car.setUserIdCreate(sessionInfo.getUser().getUserId());
									car.setIsDeploy(new Integer(0));
								
									if(car.getCarStatus()==null){
										
										TCarStatus status = new TCarStatus();
										status.setCarId(car.getCarId());
										status.setTimeUpdate(new Date());
										status.setDictIdCarStatus(WebMsgUtil.CARSTATUS_FREE);
										status.setUserIdUpdate(sessionInfo.getUser().getUserId());
										car.setCarStatus(status);
									
									}else{
									// 车辆状态（只有新建车辆时保存车辆初始状态置空闲）
									//TCarStatus status = new TCarStatus();
									car.getCarStatus().setCarId(car.getCarId());
									car.getCarStatus().setDictIdCarStatus(WebMsgUtil.CARSTATUS_FREE);
									//status.setSimNo(simNumber);
				//					car.getCarStatus().setSimNo(car.getCarStatus().getSimNo());
				//					status.setKeyNo(car.getCarStatus().getKeyNo());
				//					status.setObdNo(car.getCarStatus().getObdNo());
									car.getCarStatus().setTimeUpdate(new Date());
									car.getCarStatus().setUserIdUpdate(sessionInfo.getUser().getUserId());
									//carStatuService.save(status);//保存车辆状态
									}
									carservice.save(car);//保存车辆
								
									json.setSuccess(true);
							}
						}
					}
				}
			
			}

		} catch (Exception e) {
			e.printStackTrace();
			json.setMsg("未知异常");
			json.setSuccess(false);

		}

		writeJson(json);
	}

	/**
	 * 更新车辆档案
	 */
	public void update() {
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());

		Json json = new Json();
		json.setMsg("更新失败！");
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if (car != null && !StringUtils.isBlank(car.getCarId())) {

				HqlFilter hqlFilter = new HqlFilter(getRequest());
				hqlFilter.addFilter("QUERY_t#carId_S_NE", car.getCarId());
				hqlFilter.addFilter("QUERY_t#carNo_S_EQ", car.getCarNo());
				hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ",WebMsgUtil.YOUXIAO);
				TCar cartemp = carservice.getByFilter(hqlFilter);
				if (cartemp != null) {
					json.setMsg("更新车辆失败，车牌号已存在！");
				} else {					
							HqlFilter hqlFilter2 = new HqlFilter(getRequest());
							hqlFilter2.addFilter("QUERY_t#carId_S_NE", car.getCarId());
							hqlFilter2.addFilter("QUERY_t#carFileNo_S_EQ", car.getCarFileNo());
							hqlFilter2.addFilter("QUERY_t#dictIdFlag_S_EQ",	WebMsgUtil.YOUXIAO);
							TCar tempCar = carservice.getByFilter(hqlFilter2);
							if(tempCar!=null){
								json.setMsg("更新车辆失败，内部档案号已存在！");
								
							}else{	
					
					TCarStatus carStatus = carStatuService.getById(car.getCarId());
					BeanUtils.copyNotNullProperties(car.getCarStatus(), carStatus, new String[] {"carId","carMileage","dictIdCarStatus","isDeploy"});
					carStatus.setUserIdUpdate(sessionInfo.getUser().getUserId());
					carStatus.setTimeUpdate(new Date());
					carStatuService.update(carStatus);
					
					
					
					TCar t = carservice.getById(car.getCarId());
					BeanUtils.copyNotNullProperties(car, t, new String[] {
							"userIdCreate", "timeCreate", "dictIdFlag","carStatus" });
					t.setUserIdUpdate(sessionInfo.getUser().getUserId());
					t.setTimeUpdate(new Date());
					t.setCarTime(sdf.parse(carTime));// 出厂日期
					t.setCarScrap(sdf.parse(carScrap));// 报废时间
					t.setCarRegisterDate(sdf.parse(carRegisterDate));// 初次登记时间
					carservice.update(t);
					json.setSuccess(true);
					json.setMsg("更新成功！");
							}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		writeJson(json);
	}

	/**
	 * 删除车辆
	 * 
	 * @return
	 */
	public void delete() {
		Json json = new Json();
		try {
			TCar cartemp = service.getById(id);
			cartemp.setDictIdFlag(WebMsgUtil.WUXIAO);
			carservice.update(cartemp);
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
		}
		writeJson(json);
	}

	/**
	 * 获得一个对象
	 */
	public void getById() {
		if (!StringUtils.isBlank(id)) {
			TCar carTemp = service.getById(id);
			carTemp.setDictBrand(carModelService.getById(carTemp.getModelId())
					.getDictIdBrand());
			writeJson(carTemp);
		} else {
			Json j = new Json();
			j.setMsg("主键不可为空！");
			writeJson(j);
		}
	}

	/**
	 * 获得车列表
	 */
	public void doNotNeedSecurity_getcarList() {

		HqlFilter hqlFilter = new HqlFilter(getRequest());
		hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ", WebMsgUtil.YOUXIAO);
		writeJson(carservice.findByFilter(hqlFilter));
	}
	/**
	 * 根据产权单获得车列表
	 */
	public void doNotNeedSecurity_getOrgcarList() {
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		hqlFilter.addFilter("QUERY_t#orgIdManager_S_EQ", id);
		hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ", WebMsgUtil.YOUXIAO);
		writeJson(carservice.findByFilter(hqlFilter));
	}
	/**
	 * 获得当前用户产权单位车列表
	 */
	public void doNotNeedSecurity_getUsercarList() {
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		hqlFilter.addFilter("QUERY_t#orgIdManager_S_EQ", sessionInfo.getOrganization().getOrgIdManager());
		hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ", WebMsgUtil.YOUXIAO);
		hqlFilter.addFilter("QUERY_t#carStatus.dictIdCarStatus_S_EQ", WebMsgUtil.CARSTATUS_FREE);
		writeJson(carservice.findByFilter(hqlFilter));
	}
	/**
	 * 获得当前登录用户查询对应的车辆产权单位用户及其下属单位所有用户
	 */
	public void doNotNeedSecurity_getUserMaseterListById() {
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		if(userId==0){			
			hqlFilter.addFilter("QUERY_t#orgIdManager_S_EQ", sessionInfo.getOrganization().getOrgIdManager());
			}
	
		writeJson(carMasterService.findByFilter(hqlFilter));
	}
	/**
	 * 执行存储过程
	 */
	public void doNotNeedSecurity_runCall(){
		Json json = new Json();
		json.setSuccess(true);
		try{  
		carservice.doRunCall();
		}catch(Exception e){
			json.setSuccess(false);
			e.printStackTrace();
		}
		writeJson(json);
				
	}
	public void doNotNeedSecurity_exports(){		
		try {
			ExportExcelBean exportbean = new ExportExcelBean();
			DateFormat format2 = new SimpleDateFormat("yyyyMMdd");
			byte[] fb = (format2.format(new Date()) + "车辆管理报表.xls").getBytes();
			fileName = new String(fb, 0, fb.length, "ISO-8859-1");
			exportbean.setTitle(format2.format(new Date()) + "车辆管理报表");
			List<String> titlelist = new ArrayList<String>();
			titlelist.add("车辆管理单位");//
			titlelist.add("固定使用单位");//可能为空
			titlelist.add("车牌号");//
			titlelist.add("车辆识别号");//
			titlelist.add("发动机号");//
			titlelist.add("车身颜色");//
			titlelist.add("是否落户");//
			titlelist.add("车辆种类");//
			titlelist.add("环保标志");//
			titlelist.add("初次登记日期");//
			titlelist.add("内部档案号");//
			titlelist.add("强制报废日期");//
			titlelist.add("出厂日期");//
			titlelist.add("用途分类");//
			titlelist.add("特种车");//
			titlelist.add("已记固定资产");//
			titlelist.add("车辆资产编码");//可能为空
			titlelist.add("资产金额");//可能为空
			titlelist.add("财政在编");//可能为空
			titlelist.add("车辆品牌");//
			titlelist.add("车系");//
			titlelist.add("车型");//
			titlelist.add("产地");//
			titlelist.add("燃油类型");//
			titlelist.add("是否带T");//
			titlelist.add("载客量(人)");//
			titlelist.add("载重量(吨)");//
			titlelist.add("排气量(升)");//
			titlelist.add("保养周期(月)");//
			titlelist.add("车辆状态");//
			exportbean.setTitlelist(titlelist);
			List<List<Object>> content = new ArrayList<List<Object>>();
			HqlFilter hqlFilter = new HqlFilter(getRequest(),"b");
//			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
//					ConfigUtil.getSessionInfoName());
//			if(userId==0){			
//				hqlFilter.addFilter("QUERY_t#orgIdManager_S_EQ", sessionInfo.getOrganization().getOrgIdManager());
//			}
			hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ", WebMsgUtil.YOUXIAO);
			List<VCarManage>  vCarManages = vCarManageService.findByFilter(hqlFilter);
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			for (VCarManage vCarManage : vCarManages) {
				List<Object> obj = new ArrayList<Object>();
				obj.add(vCarManage.getOrgName());//
				if(vCarManage.getUseOrgName()!=null){
					obj.add(vCarManage.getUseOrgName());//
				}else {
					obj.add("");//
				}
				obj.add(vCarManage.getCarNo());//
				obj.add(vCarManage.getCarIdentifyNo());//
			    obj.add(vCarManage.getCarEngineNo());//
				obj.add(vCarManage.getCarColor());//
				obj.add(vCarManage.getCarIsSettle());//
				obj.add(vCarManage.getCarKind());//
				obj.add(vCarManage.getEnvironmentProtection());//
				obj.add(vCarManage.getCarRegisterDate());//
				obj.add(vCarManage.getCarFileNo());//
				obj.add(vCarManage.getCarScrap());//
				obj.add(vCarManage.getCarTime());//
				obj.add(vCarManage.getUsingKind());//
				obj.add(vCarManage.getSpecialCar());//
				obj.add(vCarManage.getCarAssets());//
				if(vCarManage.getCarAssetsNo()!=null){
					obj.add(vCarManage.getCarAssetsNo());//
				}else {
					obj.add("");//
				}
				if(vCarManage.getCarAssetsMoney()!=null){
					obj.add(vCarManage.getCarAssetsMoney());//
				}else {
					obj.add("");//
				}
				if(vCarManage.getCarFinance()!=null){
					obj.add(vCarManage.getCarFinance());//
				}else {
					obj.add("");//
				}
				obj.add(vCarManage.getCarBrand());//
				obj.add(vCarManage.getCarSeries());//
				obj.add(vCarManage.getModelName());//
				obj.add(vCarManage.getArea());//
				obj.add(vCarManage.getOil());//
				obj.add(vCarManage.getIsT());//
				obj.add(vCarManage.getModelPeople());//
				obj.add(vCarManage.getModelLoad());//
				obj.add(vCarManage.getAirDisplacement());//
				obj.add(vCarManage.getModelMaintenanceMonth());//
				String str="CarStatusFree";
				if(str.equals(vCarManage.getDictIdCarStatus())){
					obj.add("空闲");//
				}else {
					obj.add("外派");//
				}
				
				content.add(obj);
			}
			exportbean.setContent(content);
			exportbean.setCellRangeRight(27);
			exportbean.setColWidth(new Integer[] {4000,4000,3000,3000,3000,3000,3000,3000,3000,4000,3500,4000,4000,3000,3000,4000,4000,3000,3000,3000,3000,3000,3000,3000,3000,3000,3500,3000});
			inputStream = ExPortExcel.exportExcel(exportbean);
			byte[] buffer = new byte[inputStream.available()];
			inputStream.read(buffer);
			inputStream.close();
			getResponse().reset();
			// 先去掉文件名称中的空格,然后转换编码格式为utf-8,保证不出现乱码,这个文件名称用于浏览器的下载框中自动显示的文件名
			getResponse().addHeader("Content-Disposition",
					"attachment;filename=" + fileName);
			getResponse().setContentType("application/xls");
			getResponse().addHeader("Content-Length", "" + buffer.length);
			OutputStream os = new BufferedOutputStream(getResponse()
					.getOutputStream());
			getResponse().setContentType("application/octet-stream");
			os.write(buffer, 0, buffer.length);// 输出文件
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html");
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println("<html>");
				out.println("<body bgcolor=\"white\">");
				out.println("<span class=\"bold\">导出文件失败！</span>");
				out.println("</body>");
				out.println("</html>");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		// return "export";··
	}
			
	public TCar getCar() {
		return car;
	}

	public void setCar(TCar car) {
		this.car = car;
	}

	public String getCarTime() {
		return carTime;
	}

	public void setCarTime(String carTime) {
		this.carTime = carTime;
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

	public String getCarScrap() {
		return carScrap;
	}

	public void setCarScrap(String carScrap) {
		this.carScrap = carScrap;
	}

	public String getCarRegisterDate() {
		return carRegisterDate;
	}

	public void setCarRegisterDate(String carRegisterDate) {
		this.carRegisterDate = carRegisterDate;
	}

	public String getSimNumber() {
		return simNumber;
	}

	public void setSimNumber(String simNumber) {
		this.simNumber = simNumber;
	}
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
}
