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

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.car.entity.car.TCarScrap;
import com.car.entity.car.TCarStatus;
import com.car.entity.car.VCarMaintenanceSearch;
import com.car.entity.car.VCarScrap;
import com.car.service.car.ICarApplyService;
import com.car.service.car.ICarScrapService;
import com.car.service.car.ICarStatuService;
import com.car.service.car.ICarservice;
import com.car.service.car.IVCarScrapService;
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
@Action("scrap")
public class CarScrapAction extends BaseAction<TCarScrap> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7261130418529640903L;
	@Autowired
	private IVCarScrapService ivCarScrapService;
	@Autowired
	private ICarScrapService carScrapService;
	@Autowired
	private  ICarStatuService carStatuService;
	@Autowired
	public void setService(ICarScrapService service) {
		super.setService(service);
	}
	private TCarScrap carScrap;
	private String orgIdManager;
	private String startTime;
	private String endTime;
	private String fileName;
	private InputStream inputStream;
	
	
	public void grid() {
		Grid<VCarScrap> grid = new Grid<VCarScrap>();
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		if (!(startTime == null||"".equals(startTime))) {
			hqlFilter.addFilter("QUERY_t#scrapTime_D_GE", startTime);
		}
		if (!(endTime == null||"".equals(endTime))) {
			hqlFilter.addFilter("QUERY_t#scrapTime_D_LE", endTime);
		}
		if (!(orgIdManager == null || "".equals(orgIdManager))) {
			hqlFilter.addFilter("QUERY_t#orgIdManager_S_EQ", orgIdManager);
		}
		hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ", WebMsgUtil.YOUXIAO);
		grid.setTotal(ivCarScrapService.countByFilter(hqlFilter));
		grid.setRows(ivCarScrapService.findByFilter(hqlFilter, page, rows));
		writeJson(grid);
	}
	public void save() {
		Json json = new Json();
		if (carScrap != null) {
			HqlFilter hqlFilter = new HqlFilter(getRequest());
			hqlFilter.addFilter("QUERY_t#carId_S_EQ", carScrap.getCarId());
			TCarScrap carScraps = carScrapService.getByFilter(hqlFilter);
			if (carScraps != null) {
				json.setMsg("报废失败，该车辆已报废！");
			} else {
				try {
					SessionInfo sessionInfo = (SessionInfo) getSession()
							.getAttribute(ConfigUtil.getSessionInfoName());
					carScrap.setScrapNo(Getnum());
					carScrap.setDictIdScrapResult("CLBF0001");
					carScrap.setUserIdCreate(sessionInfo.getUser().getUserId());
					carScrap.setTimeCreate(new Date());
					carScrapService.save(carScrap);
					json.setMsg("保存成功！");
					json.setSuccess(true);
				} catch (Exception e) {
					json.setMsg("保存异常！");
					json.setSuccess(false);
					e.printStackTrace();
				}
			}
		}
		writeJson(json);
	}
    public void sure(){
    	Json json = new Json();
    	SessionInfo sessionInfo = (SessionInfo) getSession()
				.getAttribute(ConfigUtil.getSessionInfoName());
    	HqlFilter hqlFilter = new HqlFilter(getRequest());
		hqlFilter.addFilter("QUERY_t#scrapId_S_EQ", id);
		TCarScrap carScraps = carScrapService.getByFilter(hqlFilter);
		if(carScraps!=null){
		 carScraps.setScrapTime(new Date());
		 carScraps.setScrapUserId(sessionInfo.getUser().getUserId());
		 carScraps.setDictIdScrapResult("CLBF0002");
		 carScrapService.update(carScraps);
		 TCarStatus  carStatus = new TCarStatus();
		 HqlFilter hqlFilters = new HqlFilter(getRequest());
		 hqlFilters.addFilter("QUERY_t#carId_S_EQ", carScraps.getCarId());
		 carStatus = carStatuService.getByFilter(hqlFilters);  
		 carStatus.setDictIdCarStatus("CarStatusScrap");
		 carStatuService.update(carStatus);
		 json.setMsg("确认报废成功！");
		 json.setSuccess(true);
		}else{
	     json.setMsg("确认报废失败！");
		 json.setSuccess(false);
		}
    	writeJson(json);
    }
        public void update(){
        	Json json = new Json();
    		if (carScrap != null) {
    			HqlFilter hqlFilter = new HqlFilter(getRequest());
    			hqlFilter.addFilter("QUERY_t#scrapId_S_NE", carScrap.getScrapId());
    			hqlFilter.addFilter("QUERY_t#carId_S_EQ", carScrap.getCarId());
    			TCarScrap carScrapinfo = carScrapService.getByFilter(hqlFilter);
    			if (carScrapinfo!= null) {
    				json.setMsg("更新失败，该车辆已存在！");
    			} else {
    				HqlFilter hqlFilter1 = new HqlFilter(getRequest());	
    				hqlFilter1.addFilter("QUERY_t#scrapId_S_EQ", carScrap.getScrapId());
        			TCarScrap carScraps = carScrapService.getByFilter(hqlFilter1);
        			carScraps.setCarId(carScrap.getCarId());
        			carScraps.setScrapUsedYear(carScrap.getScrapUsedYear());
        			carScrapService.update(carScraps);
        			json.setMsg("更新成功！");
        			 json.setSuccess(true);
    			}
        	}else {
        		 json.setMsg("更新失败！");
        		 json.setSuccess(false);
			}
    	writeJson(json);
    }
    public void doNotNeedSecurity_exports(){
    	
    	try {
			ExportExcelBean exportbean = new ExportExcelBean();
			DateFormat format2 = new SimpleDateFormat("yyyyMMdd");
			byte[] fb = (format2.format(new Date()) + "报废记录导出.xls").getBytes();
			fileName = new String(fb, 0, fb.length, "ISO-8859-1");
			exportbean.setTitle(format2.format(new Date()) + "报废记录导出");
			List<String> titlelist = new ArrayList<String>();
			titlelist.add("报废编号");//
			titlelist.add("车牌");//
			titlelist.add("产权单位");//
			titlelist.add("报废结果");//
			titlelist.add("已使用年限");//
			titlelist.add("报废日期");//
			titlelist.add("报废人");//
			titlelist.add("登记时间");//
			exportbean.setTitlelist(titlelist);
			
			List<List<Object>> content = new ArrayList<List<Object>>();
			HqlFilter hqlFilter = new HqlFilter(getRequest());
			if (!(startTime == null||"".equals(startTime))) {
				hqlFilter.addFilter("QUERY_t#scrapTime_D_GE", startTime);
			}
			if (!(endTime == null||"".equals(endTime))) {
				hqlFilter.addFilter("QUERY_t#scrapTime_D_LE", endTime);
			}
			if (!(orgIdManager == null || "".equals(orgIdManager))) {
				hqlFilter.addFilter("QUERY_t#orgIdManager_S_EQ", orgIdManager);
			}
			hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ", WebMsgUtil.YOUXIAO);
			List<VCarScrap>  vCarScraps = ivCarScrapService.findByFilter(hqlFilter);
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			for (VCarScrap VCarScrap : vCarScraps) {
				List<Object> obj = new ArrayList<Object>();
				obj.add(VCarScrap.getScrapNo());//
				obj.add(VCarScrap.getCarNo());//
		    	if (VCarScrap.getOrgName() != null) {
					obj.add(VCarScrap.getOrgName());//
				} else {
				   obj.add("");
				}
				obj.add(VCarScrap.getDictName());//
		    	if (VCarScrap.getScrapUsedYear()!= null) {
					obj.add(VCarScrap.getScrapUsedYear());//
				} else {
				   obj.add("");
				}
		    	if (VCarScrap.getScrapTime()!= null) {
					obj.add(VCarScrap.getScrapTime());//
				} else {
				   obj.add("");
				}
		    	if (VCarScrap.getScrapName()!= null) {
					obj.add(VCarScrap.getScrapName());//
				} else {
				   obj.add("");
				}
				obj.add(VCarScrap.getTimeCreate());//
				content.add(obj);
			}
			exportbean.setContent(content);
			exportbean.setCellRangeRight(7);
			exportbean.setColWidth(new Integer[] {5000,3000,4000,3000,3000,8000,3000,3000});
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
		// return "export";··
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
		String serial = t + x;
		return serial;
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

	public String getOrgIdManager() {
		return orgIdManager;
	}

	public void setOrgIdManager(String orgIdManager) {
		this.orgIdManager = orgIdManager;
	}

	public TCarScrap getCarScrap() {
		return carScrap;
	}

	public void setCarScrap(TCarScrap carScrap) {
		this.carScrap = carScrap;
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
