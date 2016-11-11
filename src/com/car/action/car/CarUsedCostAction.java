package com.car.action.car;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.car.entity.car.TCarUsedCost;
import com.car.entity.car.VCarScrap;
import com.car.entity.car.VCarUsedCost;
import com.car.service.car.ICarUsedCostService;
import com.car.service.car.IVCarReturnInfoService;
import com.car.service.car.IVCarUsedCostService;
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
@Action("carUsedCost")
public class CarUsedCostAction extends BaseAction<TCarUsedCost> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8448920771497801204L;
	@Autowired
	private IVCarReturnInfoService vcarReturnInfoService;
	@Autowired
	private IVCarUsedCostService vcarUsedCostService;
	@Autowired
	private ICarUsedCostService carUsedCostService;

	@Autowired
	public void setService(ICarUsedCostService service) {
		super.setService(service);
	}

	private String orgIdManager;
	private String startTime;
	private String endTime;
	private String dictIduseCostType;
	private TCarUsedCost carUsedCost;
	private String fileName;
	private InputStream inputStream;
	

	public void grid() {
		Grid<VCarUsedCost> grid = new Grid<VCarUsedCost>();
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		if (!(orgIdManager == null || "".equals(orgIdManager))) {
			hqlFilter.addFilter("QUERY_t#orgIdManager_S_EQ", orgIdManager);
		}
		if (!(dictIduseCostType == null || "".equals(dictIduseCostType))) {
			hqlFilter.addFilter("QUERY_t#dictIduseCostType_S_EQ",
					dictIduseCostType);
		}
		
		if (!(startTime == null || "".equals(startTime))) {		
			hqlFilter.addFilter("QUERY_t#sendTime_D_GE", startTime);
		}
		if (!(endTime == null || "".equals(endTime))) {
			hqlFilter.addFilter("QUERY_t#returnTime_D_LE", endTime);	
		}
		hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ", WebMsgUtil.YOUXIAO);
		hqlFilter.addFilter("QUERY_t#orgIdManager_S_EQ", sessionInfo.getOrganization().getOrgIdManager());
		grid.setTotal(vcarUsedCostService.countByFilter(hqlFilter));
		grid.setRows(vcarUsedCostService.findByFilter(hqlFilter, page, rows));
		writeJson(grid);
	}

	public void doNotNeedSecurity_getCarReturnInfo() {
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		hqlFilter.addFilter("QUERY_t#orgIdManager_S_EQ", sessionInfo
				.getOrganization().getOrgIdManager());
		hqlFilter.addFilter("QUERY_t#returnFlag_S_EQ", WebMsgUtil.YOUXIAO);
		hqlFilter.addFilter("QUERY_t#sendFlag_S_EQ", WebMsgUtil.YOUXIAO);
		writeJson(vcarReturnInfoService.findByFilter(hqlFilter));
	}

	public void save() {
		Json json = new Json();
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		if (carUsedCost != null) {
			HqlFilter hqlFilter = new HqlFilter(getRequest());
			hqlFilter.addFilter("QUERY_t#sendId_S_EQ", carUsedCost.getSendId());
			hqlFilter.addFilter("QUERY_t#dictIduseCostType_S_EQ",
					carUsedCost.getDictIduseCostType());
			TCarUsedCost carUsedCosts = carUsedCostService
					.getByFilter(hqlFilter);
			if (carUsedCosts != null) {
				json.setSuccess(false);
				json.setMsg("该费用记录已存在");
			} else {
				carUsedCost.setUserIdCreate(sessionInfo.getUser().getUserId());
				carUsedCost.setTimeCreate(new Date());
				try {
					carUsedCostService.save(carUsedCost);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				json.setSuccess(true);
				json.setMsg("添加成功");
			}

		} else {
			json.setSuccess(false);
			json.setMsg("添加失败");
		}
		writeJson(json);
	}

	public void update() {
		Json json = new Json();
	   if (carUsedCost != null) {
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		hqlFilter.addFilter("QUERY_t#usedCostId_S_NE",carUsedCost.getUsedCostId());
		hqlFilter.addFilter("QUERY_t#sendId_S_EQ", carUsedCost.getSendId());
		hqlFilter.addFilter("QUERY_t#dictIduseCostType_S_EQ",
				carUsedCost.getDictIduseCostType());
		TCarUsedCost carUsedCosts = carUsedCostService
				.getByFilter(hqlFilter);
		if (carUsedCosts != null) {
			json.setSuccess(false);
			json.setMsg("该费用记录已存在");
		} else {
			HqlFilter hqlFilters = new HqlFilter(getRequest());
			hqlFilters.addFilter("QUERY_t#usedCostId_S_EQ",carUsedCost.getUsedCostId());
			TCarUsedCost carUsedCostInfo = carUsedCostService.getByFilter(hqlFilters);
			carUsedCostInfo.setDictIduseCostType(carUsedCost.getDictIduseCostType());
			carUsedCostInfo.setSendId(carUsedCost.getSendId());
			carUsedCostInfo.setUsedCost(carUsedCost.getUsedCost());
			carUsedCostService.update(carUsedCostInfo);
			json.setSuccess(true);
			json.setMsg("更新成功");
		}

	} else {
		json.setSuccess(false);
		json.setMsg("更新失败");
	}
		writeJson(json);
	}
	/**
	 * 获得当前用户产权单位车列表
	 */
	public void doNotNeedSecurity_getUsercarList() {
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		hqlFilter.addFilter("QUERY_t#orgIdManager_S_EQ", sessionInfo
				.getOrganization().getOrgIdManager());
		hqlFilter.addFilter("QUERY_t#returnFlag_S_EQ", WebMsgUtil.YOUXIAO);
		hqlFilter.addFilter("QUERY_t#sendFlag_S_EQ", WebMsgUtil.YOUXIAO);
		writeJson(vcarReturnInfoService.findByFilter(hqlFilter));
	}
	 public void doNotNeedSecurity_exports(){   	
	    	try {
				ExportExcelBean exportbean = new ExportExcelBean();
				DateFormat format2 = new SimpleDateFormat("yyyyMMdd");
				byte[] fb = (format2.format(new Date()) + "费用记录导出.xls").getBytes();
				fileName = new String(fb, 0, fb.length, "ISO-8859-1");
				exportbean.setTitle(format2.format(new Date()) + "费用记录导出");
				List<String> titlelist = new ArrayList<String>();
				titlelist.add("派车单号");//
				titlelist.add("产权单位");//
				titlelist.add("车牌号");//
				titlelist.add("费用金额(元)");//
				titlelist.add("费用类型");//
				titlelist.add("派车时间");//
				titlelist.add("还车时间");//
				titlelist.add("登记时间");//
				exportbean.setTitlelist(titlelist);				
				List<List<Object>> content = new ArrayList<List<Object>>();
				SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
						ConfigUtil.getSessionInfoName());
				HqlFilter hqlFilter = new HqlFilter(getRequest());
				if (!(orgIdManager == null || "".equals(orgIdManager))) {
					hqlFilter.addFilter("QUERY_t#orgIdManager_S_EQ", orgIdManager);
				}
				if (!(dictIduseCostType == null || "".equals(dictIduseCostType))) {
					hqlFilter.addFilter("QUERY_t#dictIduseCostType_S_EQ",
							dictIduseCostType);
				}
				if (!(startTime == null || "".equals(startTime))) {
					hqlFilter.addFilter("QUERY_t#sendTime_D_GE", startTime);
				}
				if (!(endTime == null || "".equals(endTime))) {
					hqlFilter.addFilter("QUERY_t#returnTime_D_LE", endTime);
				}
				hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ", WebMsgUtil.YOUXIAO);
				hqlFilter.addFilter("QUERY_t#orgIdManager_S_EQ", sessionInfo.getOrganization().getOrgIdManager());
				List<VCarUsedCost>  vCarUsedCosts = vcarUsedCostService.findByFilter(hqlFilter);
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				for (VCarUsedCost VCarUsedCost : vCarUsedCosts) {
					List<Object> obj = new ArrayList<Object>();
					obj.add(VCarUsedCost.getSendNo());//
					obj.add(VCarUsedCost.getOrgName());//
					obj.add(VCarUsedCost.getCarNo());//
					DecimalFormat dFormat=new DecimalFormat("0.####");
					obj.add(dFormat.format(VCarUsedCost.getUsedCost()));//
					obj.add(VCarUsedCost.getDictName());//
					obj.add(format.format(VCarUsedCost.getSendTime()));//
					obj.add(format.format(VCarUsedCost.getReturnTime()));//
					obj.add(format.format(VCarUsedCost.getTimeCreate()));//
					content.add(obj);
				}
				exportbean.setContent(content);
				exportbean.setCellRangeRight(7);
				exportbean.setColWidth(new Integer[] {4000,4000,4000,4000,3000,5000,5000,5000});
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
	public String getOrgIdManager() {
		return orgIdManager;
	}

	public void setOrgIdManager(String orgIdManager) {
		this.orgIdManager = orgIdManager;
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

	public String getDictIduseCostType() {
		return dictIduseCostType;
	}

	public void setDictIduseCostType(String dictIduseCostType) {
		this.dictIduseCostType = dictIduseCostType;
	}

	public TCarUsedCost getCarUsedCost() {
		return carUsedCost;
	}

	public void setCarUsedCost(TCarUsedCost carUsedCost) {
		this.carUsedCost = carUsedCost;
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
