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

import com.car.entity.car.VCarMaintenanceSearch;
import com.car.service.car.IVCarMaintenanceSearchService;
import com.framework.util.ConfigUtil;
import com.framework.util.ExPortExcel;
import com.framework.util.HqlFilter;
import com.system.action.base.BaseAction;
import com.system.entity.easyui.Grid;
import com.system.entity.maintain.ExportExcelBean;
import com.system.entity.maintain.SessionInfo;

@Namespace("/car")
@Action("carmaintenancesearch")
public class CarMaintenanceSearchAction extends
		BaseAction<VCarMaintenanceSearch> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1861357259989396550L;
	private String fileName;
	private InputStream inputStream;
	private String startTime;
	private String endTime;
	private String orgIdManager;
	@Autowired
	public IVCarMaintenanceSearchService carMaintenanceSearchService;

	@Autowired
	public void setService(
			IVCarMaintenanceSearchService carMaintenanceSearchService) {
		// TODO Auto-generated method stub
		super.setService(service);
	}

	public void grid() {
		Grid<VCarMaintenanceSearch> grid = new Grid<VCarMaintenanceSearch>();
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		if (!(startTime == null || "".equals(startTime))) {
			// Date date=new Date(startTime);
			hqlFilter.addFilter("QUERY_t#timeCreate_D_GE", startTime);
		}
		if (!(endTime == null || "".equals(endTime))) {
			hqlFilter.addFilter("QUERY_t#timeCreate_D_LE", endTime);

		}
		if (!(orgIdManager == null || "".equals(orgIdManager))) {
			hqlFilter.addFilter("QUERY_t#orgIdManager_S_LK", orgIdManager);
		}
		grid.setTotal(carMaintenanceSearchService.countByFilter(hqlFilter));
		grid.setRows(carMaintenanceSearchService.findByFilter(hqlFilter, page,
				rows));
		writeJson(grid);
	}

	public void doNotNeedSecurity_exports() {
		try {
			ExportExcelBean exportbean = new ExportExcelBean();
			DateFormat format2 = new SimpleDateFormat("yyyyMMdd");
			byte[] fb = (format2.format(new Date()) + "保养记录导出.xls").getBytes();
			fileName = new String(fb, 0, fb.length, "ISO-8859-1");
			exportbean.setTitle(format2.format(new Date()) + "保养记录导出");
			List<String> titlelist = new ArrayList<String>();
			titlelist.add("车牌");//
			titlelist.add("入厂里程");//
			titlelist.add("保养里程");//
			titlelist.add("下次保养里程");//
			titlelist.add("保养花费");//
			titlelist.add("保养内容");//
			titlelist.add("保养厂家");//
			titlelist.add("经办人");//
			titlelist.add("登记时间");//
			titlelist.add("产权单位");//
			exportbean.setTitlelist(titlelist);
			List<List<Object>> content = new ArrayList<List<Object>>();
			HqlFilter hqlFilter = new HqlFilter(getRequest());
			if (!(startTime == null || "".equals(startTime))) {
				hqlFilter.addFilter("QUERY_t#timeCreate_D_GE", startTime);
			}
			if (!(endTime == null || "".equals(endTime))) {
				hqlFilter.addFilter("QUERY_t#timeCreate_D_LE", endTime);
			}
			if (!(orgIdManager == null || "".equals(orgIdManager))) {
				hqlFilter.addFilter("QUERY_t#orgIdManager_S_LK", orgIdManager);
			}
			hqlFilter.addSort("timeCreate");
			List<VCarMaintenanceSearch> carMaintenanceSearchs = carMaintenanceSearchService
					.findByFilter(hqlFilter);
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (VCarMaintenanceSearch carMaintenanceSearch : carMaintenanceSearchs) {
				DecimalFormat dFormat=new DecimalFormat("0.####");
				List<Object> obj = new ArrayList<Object>();
				obj.add(carMaintenanceSearch.getCarNo());//
				obj.add(dFormat.format(carMaintenanceSearch.getMainInMileage()));//
				obj.add(dFormat.format(carMaintenanceSearch.getMainMileage()));//
				if (carMaintenanceSearch.getMainNextMileage() != null) {
					obj.add(dFormat.format(carMaintenanceSearch.getMainNextMileage()));//
				} else {
					obj.add("");
				}
				obj.add(dFormat.format(carMaintenanceSearch.getMainMoney()));//
				obj.add(carMaintenanceSearch.getMainCondition());//
				obj.add(carMaintenanceSearch.getDictName());//
				obj.add(carMaintenanceSearch.getUserName());//
				obj.add(format.format(carMaintenanceSearch.getTimeCreate()));
				obj.add(carMaintenanceSearch.getOrgName());//
				content.add(obj);
			}
			exportbean.setContent(content);
			exportbean.setCellRangeRight(9);
			exportbean.setColWidth(new Integer[] {5000,3000,4000,4000,3000,4000,3000,3000,4000,3000});
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

}
