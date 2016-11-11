package com.framework.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.StrutsResultSupport;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.util.ValueStack;


public class JasperReportByBeanResult extends StrutsResultSupport {
	
	protected String parameters;
	protected String format;
	protected String location;
	protected String dataSource;
	protected String type;
	protected String suffix;
	
	@Override
	protected void doExecute(String arg0, ActionInvocation arg1)
			throws Exception {
	try{
		HttpServletRequest request= ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		ValueStack stack = arg1.getStack();
		format = conditionalParse(format,arg1);
		HashMap hmParams = (HashMap) stack.findValue(parameters);
		List dataList = (List) stack.findValue(dataSource);
		if(null!=type){
			type = conditionalParse(type,arg1);
			String flag = (String) stack.findValue(type);
			if("house".equals(flag)){
				location = location.replace("base", "house");
			}
		}
		JasperReport jasperReport = getCompiledFile(location,request);
		if("HTML".equalsIgnoreCase(format)){
			//JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hmParams,conn);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hmParams,new JRBeanCollectionDataSource(dataList,false));
			generateReportHtml(jasperPrint,request,response);
		
		}else if("PDF".equalsIgnoreCase(format)){
			generateReportPdf(response,hmParams,jasperReport,dataList);
		}else if("XLS".equalsIgnoreCase(format)){
			
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	}

	private void generateReportPdf(HttpServletResponse response,
			HashMap hmParams, JasperReport jasperReport, List beanList) throws JRException, IOException {
		byte[] bytes= null;
		bytes = JasperRunManager.runReportToPdf(jasperReport, hmParams,new JRBeanCollectionDataSource(beanList));
		response.reset();
		response.resetBuffer();
		response.setContentType("application/pdf");
		response.setContentLength(bytes.length);
		ServletOutputStream outputStream = response.getOutputStream();
		outputStream.write(bytes,0,bytes.length);
		outputStream.flush();
		outputStream.close();
		
	}

	private void generateReportXLS(HttpServletResponse response,
			HashMap hmParams, JasperReport jasperReport, Connection conn) {
		
	}
	
	private void generateReportPdf(HttpServletResponse response,
			HashMap hmParams, JasperReport jasperReport, Connection conn) throws JRException, IOException {
		byte[] bytes = null;
		bytes = JasperRunManager.runReportToPdf(jasperReport, hmParams,conn);
		response.reset();
		response.resetBuffer();
		response.setContentType("application/pdf");
		response.setContentLength(bytes.length);
		ServletOutputStream outputStream = response.getOutputStream();
		outputStream.write(bytes,0,bytes.length);
		outputStream.flush();
		outputStream.close();
		
	}

	private void generateReportHtml(JasperPrint jasperPrint,
			HttpServletRequest request, HttpServletResponse response) throws IOException, JRException {
		response.setContentType("text/html;charset=utf-8");
		//response.setCharacterEncoding(arg0)
		Map imagesMap = new HashMap();
		HtmlExporter exporter = new HtmlExporter();
		List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
		jasperPrintList.add(jasperPrint);
		PrintWriter out = response.getWriter();
		exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrintList));
		exporter.setExporterOutput(new SimpleHtmlExporterOutput(out));
		exporter.exportReport();
		
	}

	private JasperReport getCompiledFile(String fileName,
			HttpServletRequest request) throws JRException {
		String absolutlyFileName = request.getSession().getServletContext().getRealPath(fileName);
		File reportFile = new File(absolutlyFileName);
		if(!reportFile.exists()){
			try {
				int index = fileName.lastIndexOf(".");
				String preStr = fileName.substring(0,index);
				JasperCompileManager.compileReportToFile(request.getSession().getServletContext().getRealPath(preStr+".jrxml"),request.getSession().getServletContext().getRealPath(preStr+".jasper"));
			} catch (JRException e) {
				e.printStackTrace();
			}
		}
		JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportFile.getPath());
		return jasperReport;
	}

	/*private Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jaspereport","root","root");
		return conn;
	}*/
	
	public String getParameters() {
		return parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}


}
