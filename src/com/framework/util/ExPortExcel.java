package com.framework.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;

import com.system.entity.maintain.ExportExcelBean;



public class ExPortExcel {
	private static InputStream excelStream;

	public static InputStream exportExcel(ExportExcelBean exportbean) {
		try {
			// 获取数据--------------------------------------------
			String sheetname = exportbean.getSheetname();// sheet名
			String title = exportbean.getTitle();// 大标题
			List<String> titlelist = exportbean.getTitlelist();// 标题列
			List<List<Object>> contentlist = exportbean.getContent();// 内容
			List<Integer> indexlist = exportbean.getShowindex();// 选择显示的列索引
			// 获取数据--------------------------------------------
			if (sheetname == null || sheetname.equals("")) {
				sheetname = "sheet1";
			}
			HSSFWorkbook exworkbook = new HSSFWorkbook();
			HSSFSheet exsheet = exworkbook.createSheet(sheetname);
			HSSFRow exrow = null;
			HSSFCell excell = null;
			// 大标题设置-------------------------------------------------
			exrow = exsheet.createRow(0);
			excell = exrow.createCell(0);
			HSSFFont font = exworkbook.createFont();// 大标题字体
			font.setFontHeight((short) 500);
			HSSFCellStyle style = exworkbook.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);
			style.setFont(font);
			excell.setCellStyle(style);
			if (exportbean.getCellRangeRight()>0){
				exsheet.addMergedRegion(new CellRangeAddress(0, 2, 0, exportbean.getCellRangeRight()));
			}
			// exsheet.setColumnWidth(0, 8000);
			// exsheet.setColumnWidth(1, 3000);
			excell.setCellValue(title);
			// 大标题设置-------------------------------------------------
			HSSFCellStyle style2 = exworkbook.createCellStyle();// 表头样式
			HSSFFont font2 = exworkbook.createFont();// 表头字体
			font2.setFontHeight((short) 250);
			font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font2.setColor(HSSFColor.BLACK.index);
			style2.setFont(font2);
//			style2.setWrapText(true);
			style2.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);
			style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
			style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
			if (titlelist != null && contentlist != null) {
				if (indexlist == null) {
					// 表头数据写入---------------------------------------------------
					exrow = exsheet.createRow(3);
					for (int i = 0; i < titlelist.size(); i++) {
						excell = exrow.createCell(i);
						excell.setCellStyle(style2);
						excell.setCellValue(titlelist.get(i));
					}
					// 内容数据写入---------------------------------------------------
					for (int i = 0; i < contentlist.size(); i++) {
						List<Object> rowlist = contentlist.get(i);
						exrow = exsheet.createRow(i + 4);
						for (int j = 0; j < rowlist.size(); j++) {
							excell = exrow.createCell(j);
							excell.setCellType(HSSFCell.CELL_TYPE_STRING);
							excell.setCellValue(rowlist.get(j) + "");
							/*try {
								excell.setCellValue(Integer.valueOf(rowlist
										.get(j)
										+ ""));
							} catch (Exception e) {
								excell.setCellValue(rowlist.get(j) + "");
							}*/
						}
					}
				} else {
					// 表头数据写入---------------------------------------------------
					exrow = exsheet.createRow(3);
					for (int i = 0; i < indexlist.size(); i++) {
						excell = exrow.createCell(i);
						excell.setCellStyle(style2);
						excell.setCellValue(titlelist.get(indexlist.get(i)));
					}
					// 内容数据写入---------------------------------------------------
					for (int i = 0; i < contentlist.size(); i++) {
						List<Object> rowlist = contentlist.get(i);
						exrow = exsheet.createRow(i + 4);
						for (int j = 0; j < indexlist.size(); j++) {
							excell = exrow.createCell(j);
							excell.setCellType(HSSFCell.CELL_TYPE_STRING);
							excell.setCellValue(rowlist.get(indexlist.get(j))+ "");
						}
					}
				}
			}
			// 创建部门名称一列的内容的对其方式
			HSSFCellStyle cellStyle = exworkbook.createCellStyle();
			cellStyle.setAlignment(CellStyle.ALIGN_CENTER);// 水平居中
			cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 竖直居中
			cellStyle.setWrapText(true);

			if (exportbean.getColWidth()!=null && exportbean.getColWidth().length>0)
			{
				Integer[] widths = exportbean.getColWidth();
				for (int i =0;i<exportbean.getColWidth().length;i++){
					exsheet.setColumnWidth(i,widths[i]);
				}
			}
			else{
				// 设置列的样式
				for (int cindex = 0; cindex < exrow.getLastCellNum(); cindex++) {
//					exsheet.setColumnWidth(cindex, 5000);
					exsheet.autoSizeColumn(cindex);
				}
			}
			// 导出处理---------------------------------------------
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			exworkbook.write(baos);
			baos.flush();
			byte[] contentbyte = baos.toByteArray();
			excelStream = new ByteArrayInputStream(contentbyte, 0,
					contentbyte.length);
			// 导出处理---------------------------------------------
		} catch (Exception e) {
			e.printStackTrace();
		}
		return excelStream;
	}
}
