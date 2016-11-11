package com.system.entity.maintain;

import java.util.List;

public class ExportExcelBean implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2229669422539609179L;
	private String title;//大标题
	private String sheetname;//sheet名
	private List<String> titlelist;//列标题名
	private List<List<Object>> content;//内容
	private List<Integer> showindex;//需要显示的列index
	private Integer[] colWidth;
	private int cellRangeRight;
	public int getCellRangeRight() {
		return cellRangeRight;
	}
	public void setCellRangeRight(int cellRangeRight) {
		this.cellRangeRight = cellRangeRight;
	}
	public Integer[] getColWidth() {
		return colWidth;
	}
	public void setColWidth(Integer[] colWidth) {
		this.colWidth = colWidth;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSheetname() {
		return sheetname;
	}
	public void setSheetname(String sheetname) {
		this.sheetname = sheetname;
	}
	public List<String> getTitlelist() {
		return titlelist;
	}
	public void setTitlelist(List<String> titlelist) {
		this.titlelist = titlelist;
	}
	public List<List<Object>> getContent() {
		return content;
	}
	public void setContent(List<List<Object>> content) {
		this.content = content;
	}
	public List<Integer> getShowindex() {
		return showindex;
	}
	public void setShowindex(List<Integer> showindex) {
		this.showindex = showindex;
	}

}
