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
import com.car.entity.car.TCarTreat;
import com.car.entity.car.VCarTreat;
import com.car.service.car.ICarTreatService;
import com.car.service.car.ICarservice;
import com.car.service.car.IVCarTreatService;
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
@Action("treat")
public class CarTreatAction extends BaseAction<TCarTreat> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6176047098201716991L;


	@Autowired
	public void setService(ICarTreatService service) {
		// TODO Auto-generated method stub
		super.setService(service);
	}

	@Autowired
	private ICarTreatService carTreatService;
	@Autowired
	private IVCarTreatService vCarTreatService;
	@Autowired
	private ICarservice carservice;
	private VCarTreat vCarTreat;
	private TCarTreat carTreat;
	private String fileName;
	private InputStream inputStream;
	private int treatAction = 0;

	public int getTreatAction() {
		return treatAction;
	}

	public void setTreatAction(int treatAction) {
		this.treatAction = treatAction;
	}

	public VCarTreat getvCarTreat() {
		return vCarTreat;
	}

	public void setvCarTreat(VCarTreat vCarTreat) {
		this.vCarTreat = vCarTreat;
	}

	public TCarTreat getCarTreat() {
		return carTreat;
	}

	public void setCarTreat(TCarTreat carTreat) {
		this.carTreat = carTreat;
	}

	public String getStringDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 由年月日时分秒+3位随机数 生成流水号
	 * 
	 * @return
	 */
	public String Getnum() {
		String t = getStringDate();
		int x = (int) (Math.random() * 900) + 100;
		String serial = t + carTreat.getDictIdTreatMode() + x;
		return serial;
	}

	public void grid() {
		Grid<VCarTreat> grid = new Grid<VCarTreat>();
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		hqlFilter.addFilter("QUERY_t#orgIdManager_S_EQ",sessionInfo.getOrganization().getOrgIdManager());
		hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ", WebMsgUtil.YOUXIAO);
		grid.setTotal(vCarTreatService.countByFilter(hqlFilter));
		grid.setRows(vCarTreatService.findByFilter(hqlFilter, page, rows));
		writeJson(grid);

	}

	/**
	 * 保存记录
	 */
	public void save() {
		Json json = new Json();
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());

		try {
			if (carTreat != null) {
				HqlFilter hqlFilter = new HqlFilter(getRequest());
				hqlFilter.addFilter("QUERY_t#carId_S_EQ", carTreat.getCarId());
				hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ",
						WebMsgUtil.YOUXIAO);
				TCarTreat carTreatTemp = carTreatService.getByFilter(hqlFilter);
				if (carTreatTemp != null) {
					json.setMsg("申请失败，该车牌号已登记！");
				} else {

					carTreat.setTreatId(UUID.randomUUID().toString());
					carTreat.setDictIdFlag(WebMsgUtil.YOUXIAO);// 标志位
					carTreat.setDictIdTreatResult(WebMsgUtil.TREAT_RESULT_SHZ);// 审核中
					carTreat.setUserIdCreate(sessionInfo.getUser().getUserId());// 申请人
					carTreat.setTreatUnit(sessionInfo.getOrganization()
							.getOrgId());// 申请单位
					carTreat.setTimeCreate(new Date());// 申请时间
					carTreat.setTreatNo( Getnum());// 申请单编号
					carTreatService.save(carTreat);
					json.setSuccess(true);
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
	 * 更新
	 */
	public void update() {
		Json json = new Json();
		json.setMsg("更新失败！");
		try {
			if (carTreat != null && !StringUtils.isBlank(carTreat.getTreatId())) {

				HqlFilter hqlFilter = new HqlFilter(getRequest());
				hqlFilter.addFilter("QUERY_t#treatId_S_NE",
						carTreat.getTreatId());
				hqlFilter.addFilter("QUERY_t#carId_S_EQ", carTreat.getCarId());
				hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ",
						WebMsgUtil.YOUXIAO);
				TCarTreat treatTemp = carTreatService.getByFilter(hqlFilter);
				if (treatTemp != null) {
					json.setMsg("更新失败，车牌号已存在！");
				} else {

					TCarTreat t = carTreatService
							.getById(carTreat.getTreatId());
					BeanUtils.copyNotNullProperties(carTreat, t, new String[] {
							"userIdCreate", "timeCreate", "dictIdFlag",
							"dictIdTreatResult", "treatNo", "treatUnit" });
					carTreatService.update(t);
					json.setSuccess(true);
					json.setMsg("更新成功！");

				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		writeJson(json);
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	public void delete() {
		Json json = new Json();
		try {
			TCarTreat treatTemp = service.getById(id);
			treatTemp.setDictIdFlag(WebMsgUtil.WUXIAO);
			carTreatService.update(treatTemp);
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
		}
		writeJson(json);
	}

	/**
	 * 导出列表
	 */
	public void doNotNeedSecurity_exports() {

		try {
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
					ConfigUtil.getSessionInfoName());
			ExportExcelBean exportbean = new ExportExcelBean();
			DateFormat format2 = new SimpleDateFormat("yyyyMMdd");
			byte[] fb = (format2.format(new Date()) + "车辆处置记录导出.xls")
					.getBytes();
			fileName = new String(fb, 0, fb.length, "ISO-8859-1");
			exportbean.setTitle(format2.format(new Date()) + "车辆处置记录导出");
			List<String> titlelist = new ArrayList<String>();
			titlelist.add("申请单编号");
			titlelist.add("申请单位");
			titlelist.add("申请时间");
			titlelist.add("车牌号");
			titlelist.add("车型");
			titlelist.add("申请人");
			titlelist.add("车辆识别号");
			titlelist.add("发动机号");
			titlelist.add("资产金额");
			titlelist.add("处置方式");
			titlelist.add("处置结果");
			titlelist.add("处置原因");
			exportbean.setTitlelist(titlelist);
			List<List<Object>> content = new ArrayList<List<Object>>();
			HqlFilter hqlFilter = new HqlFilter(getRequest());
			hqlFilter.addFilter("QUERY_t#orgIdManager_S_EQ",sessionInfo.getOrganization().getOrgIdManager());
			hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ", WebMsgUtil.YOUXIAO);
			List<VCarTreat> carTreats = vCarTreatService
					.findByFilter(hqlFilter);
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			for (VCarTreat carTreat : carTreats) {
				List<Object> obj = new ArrayList<Object>();
				obj.add(carTreat.getTreatNo());// 申请单编号
				obj.add(carTreat.getOrgName());// 申请单位
				obj.add(format.format(carTreat.getTimeCreate()));// 申请时间
				obj.add(carTreat.getCarNo());// 车牌号
				obj.add(carTreat.getModelName());// 车型
				obj.add(carTreat.getCreatUserName());// 申请人
				obj.add(carTreat.getCarIdentifyNo());// 车辆识别号
				obj.add(carTreat.getCarEngineNo());// 发动机号
				if(carTreat.getCarAssetsMoney()!=null){
					obj.add(carTreat.getCarAssetsMoney());// 资产金额
				}else{
					obj.add("");// 资产金额
				}			
				obj.add(carTreat.getModeName());// 处置方式
				obj.add(carTreat.getResultName());// 处置结果
				if(carTreat.getTreatReason()!=null){
					obj.add(carTreat.getTreatReason());// 处置原因
				}else{
					obj.add("");// 处置原因
				}
				content.add(obj);
			}
			exportbean.setContent(content);
			exportbean.setCellRangeRight(11);
			exportbean.setColWidth(new Integer[] {8000,4000,6000,4000,3000,5000,5000,5000,5000,5000,5000,5000});
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
	}

	public void apply() {
		Json json = new Json();

		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		try {
			if (carTreat != null && !StringUtils.isBlank(carTreat.getTreatId())) {

				TCarTreat t = carTreatService.getById(carTreat.getTreatId());
				t.setTreatVerifyOpinion(carTreat.getTreatVerifyOpinion());
				t.setTreatVerifyUserId(sessionInfo.getUser().getUserId());// 审批人员
				t.setTreatVerifyTime(new Date());// 审批时间
				if (treatAction == 1) {

					t.setDictIdTreatResult(WebMsgUtil.TREAT_RESULT_CZZ);// 审批同意修改状态
				} else {

					t.setDictIdTreatResult(WebMsgUtil.TREAT_RESULT_SPJJ);// 审核拒绝
				}

				carTreatService.update(t);
				json.setSuccess(true);
				json.setMsg("审批成功！");

			}
		} catch (Exception e) {
			json.setMsg("审批失败！");
			e.printStackTrace();

		}
		writeJson(json);

	}
	
	/**
	 * 获得一个对象
	 */
	public void getById() {
		
		try{
		if (!StringUtils.isBlank(id)) {
			HqlFilter hqlFilter = new HqlFilter(getRequest());
			hqlFilter.addFilter("QUERY_t#treatId_S_EQ",id);
			writeJson(vCarTreatService.getByFilter(hqlFilter));
		} else {
			Json j = new Json();
			j.setMsg("主键不可为空！");
			writeJson(j);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 处置完结
	 * 
	 * @return
	 */
	public void dealWith() {
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		Json json = new Json();
		try {
			TCarTreat treatTemp = service.getById(id);
			TCar carTempCar = carservice.getById(treatTemp.getCarId());
			if(treatTemp.getDictIdTreatMode().equals("TreatModePM")){//拍卖
				carTempCar.getCarStatus().setDictIdCarStatus(WebMsgUtil.CARSTATUS_AUCTION);//保存车辆状态为已拍卖
				
			}else{
				carTempCar.getCarStatus().setDictIdCarStatus(WebMsgUtil.CARSTATUS_SEALED);//保存车辆状态为暂时封存
				
			}
			carservice.update(carTempCar);
			treatTemp.setTreatUserId(sessionInfo.getUser().getUserId());
			treatTemp.setDictIdTreatResult(WebMsgUtil.TREAT_RESULT_CZWJ);// 处置完结
			carTreatService.update(treatTemp);
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
		}
		writeJson(json);
	}

}
