package com.car.action.export;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.car.entity.car.TCar;
import com.car.entity.export.TCarCollectDeploy;
import com.car.service.export.ICarCollectDeployService;
import com.framework.util.ConfigUtil;
import com.framework.util.HqlFilter;
import com.system.action.base.BaseAction;
import com.system.entity.maintain.SessionInfo;

@Namespace("/export")
@Action("SpecialSummary")
public class SpecialCarCountSummaryAction  extends BaseAction<TCar>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2886780834726723693L;
	private String orgId;
	private String searchType;
	private HashMap<String, String> parameter;
	private List<TCarCollectDeploy> myDataSource;
	private String jasperFileName;
	private  String exportFileName="特殊车辆统计表.xls";
	@Autowired
	private ICarCollectDeployService carCollectDeployService;
	
	public String typeSpecialCarSummaryRecord() {
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		parameter = new HashMap<String, String>();
		myDataSource = new ArrayList<TCarCollectDeploy>();
		HqlFilter hqlFilter = new HqlFilter(getRequest());
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if ("1".endsWith(searchType)) {// 本单位及所有下属机构
			int temp = carCollectDeployService.doRunCalls(orgId, sessionInfo
					.getUser().getUserId(), "2", "@flag");
			if (temp == 1) {
				List<TCarCollectDeploy> list = carCollectDeployService.findByFilter(hqlFilter);
				for(TCarCollectDeploy carCollectDeploy:list){
//					carCollectDeploy.setCollectValue(null);
					myDataSource.add(carCollectDeploy);	
				}
			}
		}else{
			int temp = carCollectDeployService.doRunCalls(orgId, sessionInfo
					.getUser().getUserId(), "1", "@flag");
			if (temp == 1) {
				List<TCarCollectDeploy> list = carCollectDeployService.findByFilter(hqlFilter);
				for(TCarCollectDeploy carCollectDeploy:list){
//					carCollectDeploy.setCollectValue(null);
					myDataSource.add(carCollectDeploy);	
				}
			}
		}
		parameter.put("tableTitle", "特殊车辆统计表");
		return SUCCESS;
	}
	public List<TCarCollectDeploy> getDateSource() {
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		List<TCarCollectDeploy> tempList = new ArrayList<TCarCollectDeploy>();
		HqlFilter hqlFilter = new HqlFilter(getRequest());
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if ("1".endsWith(searchType)) {// 本单位及所有下属机构
			int temp = carCollectDeployService.doRunCalls(orgId, sessionInfo
					.getUser().getUserId(), "2", "@flag");
			if (temp == 1) {
				List<TCarCollectDeploy> list = carCollectDeployService.findByFilter(hqlFilter);
				for(TCarCollectDeploy carCollectDeploy:list){
//					carCollectDeploy.setCollectValue(null);
					tempList.add(carCollectDeploy);	
				}
			}
		}else{
			int temp = carCollectDeployService.doRunCalls(orgId, sessionInfo
					.getUser().getUserId(), "1", "@flag");
			if (temp == 1) {
				List<TCarCollectDeploy> list = carCollectDeployService.findByFilter(hqlFilter);
				for(TCarCollectDeploy carCollectDeploy:list){
//					carCollectDeploy.setCollectValue(null);
					tempList.add(carCollectDeploy);	
				}
			}
		}
		return tempList;
	}
	public void doNotNeedSecurity_export() {
		// demo data
		List<TCarCollectDeploy> dataList = getDateSource();
		Map<String, Object> parameter = new HashMap<String, Object>();
		// 设置报表的标题
		parameter.put("tableTitle", "特殊车辆统计表");
		jasperFileName = "/jaspers/carCollectSpecial.jasper";
		try {
			InputStream fis = generalPdfInspectRecordStream(dataList, parameter);
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			getResponse().reset();
			getExportFileName();
			// 先去掉文件名称中的空格,然后转换编码格式为utf-8,保证不出现乱码,这个文件名称用于浏览器的下载框中自动显示的文件名
			getResponse().addHeader("Content-Disposition",
					"attachment;filename=" + exportFileName);
			getResponse().setContentType("application/xls");
			getResponse().addHeader("Content-Length", "" + buffer.length);
			OutputStream os = new BufferedOutputStream(getResponse()
					.getOutputStream());
			getResponse().setContentType("application/octet-stream");
			os.write(buffer, 0, buffer.length);// 输出文件
			os.flush();
			os.close();
			// setInputStream(generalPdfInspectRecordStream(dataList,parameter));
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
	}

	private InputStream generalPdfInspectRecordStream(
			List<TCarCollectDeploy> dataSource, Map<String, Object> params)
			throws JRException {
		HttpServletRequest request = ServletActionContext.getRequest();
		JasperReport jasperReport = getCompiledFile(jasperFileName, request);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
				params, new JRBeanCollectionDataSource(dataSource, false));
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		JRXlsExporter exporter = new JRXlsExporter(
				DefaultJasperReportsContext.getInstance());
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(os));
		exporter.exportReport();
		InputStream is = new ByteArrayInputStream(os.toByteArray());
		return is;
	}

	private JasperReport getCompiledFile(String fileName,
			HttpServletRequest request) throws JRException {
		String filePath = request.getSession().getServletContext()
				.getRealPath(fileName);
		System.out.println(filePath);
		File reportFile = new File(filePath);
		if (!reportFile.exists()) {
			int index = fileName.lastIndexOf(".");
			String preStr = fileName.substring(0, index);
			try {
				JasperCompileManager.compileReportToFile(
						request.getSession().getServletContext()
								.getRealPath(preStr + ".jrxml"),
						request.getSession().getServletContext()
								.getRealPath(fileName));
			} catch (JRException e) {
				e.printStackTrace();
			}
		}
		JasperReport jasperReport = (JasperReport) JRLoader
				.loadObjectFromFile(reportFile.getPath());
		return jasperReport;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public HashMap<String, String> getParameter() {
		return parameter;
	}
	public void setParameter(HashMap<String, String> parameter) {
		this.parameter = parameter;
	}
	public List<TCarCollectDeploy> getMyDataSource() {
		return myDataSource;
	}
	public void setMyDataSource(List<TCarCollectDeploy> myDataSource) {
		this.myDataSource = myDataSource;
	}
	public String getJasperFileName() {
		return jasperFileName;
	}
	public void setJasperFileName(String jasperFileName) {
		this.jasperFileName = jasperFileName;
	}
	public String getExportFileName() {
		try {
			exportFileName = new String(exportFileName.getBytes(), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return exportFileName;
	}

	public void setExportFileName(String exportFileName) {
		this.exportFileName = exportFileName;
	}

}
