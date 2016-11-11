<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.framework.util.SecurityUtil"%>
<%
	String contextPath = request.getContextPath();
	SecurityUtil securityUtil = new SecurityUtil(session);
	String year = String.valueOf(Calendar.getInstance().get(
			Calendar.YEAR));
	String classId =request.getParameter("dictClassId");
	if(classId==null){
		classId="";
	}
	
	
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>

<script type="text/javascript">
	var grid;
	var id = "";
	var addFun = function() {
		var dialog = parent.sys.modalDialog({
			title : '添加数据字典',
			url : sys.contextPath + '/securityJsp/maintain/dicDataNotSelf_form.jsp',
			buttons : [ {
				text : '添加',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(
							dialog, grid, parent.$);
				}
			} ]
		});
	};
	var editFun = function(id) {
		var dialog = parent.sys.modalDialog({
			title : '修改数据字典',
			url : sys.contextPath + '/securityJsp/maintain/dicDataNotSelf_form.jsp?id=' + id,
			buttons : [ {
				text : '编辑',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(
							dialog, grid, parent.$);
				}
			} ]
		});
	};
	$(function() {
		
		
		$('#dictClasId').combobox({								
			panelHeight:'200',			
			editable:false,
			valueField: 'dictClasId',
			textField: 'dictClasName',
			queryParams : {'QUERY_t#dictClasIsSelf_I_EQ' : '0'},
			url: sys.contextPath+'/maintain/dictionaryClasssManage!doNotNeedSecurity_getClassNameByClassId.cxl?'
	 
	});
	    var classId = document.getElementById("classId").value;	    
		grid = $('#grid')
				.datagrid(
						{
							queryParams : {'QUERY_t#sysDictionaryClass#dictClasIsSelf_I_EQ' : '0'},
							title : '',
							url : sys.contextPath+ '/maintain/dictionaryManage!grid.cxl?dictClassId='+classId,
							striped : true,
							rownumbers : true,
							pagination : true,
							singleSelect : true,
							idField : 'dictId',
							sortName : 'dictIndex',
							sortOrder : 'asc',
							pageSize : 30,
							fitColumns : true,
							pageList : [ 10, 20, 30, 40, 50, ],
							columns : [ [
									{
										title : '字典分类名称',
										field : 'sysDictionaryClass',
										sortable : true,
										width : '100',
										formatter : function(value, row, index) {
											return value.dictClasName;
										}
									},
									{
										title : '编号',
										field : 'dictNo',
										width : '80',
										sortable : true
										
									},
									{
										title : '排序',
										field : 'dictIndex',
										width : '80',
										sortable : true

									},
									{
										title : '名称',
										field : 'dictName',
										width : '80',
										sortable : true
									},
									{
										title : '拼音码',
										field : 'dictPyCode',
										width : '80',
										sortable : true

									},
									{
										title : '创建人名称',
										field : 'createName',
										width : '80',
										sortable : true
									},
									{
										title : '创建时间',
										field : 'timeCreate',
										width : '80',
										sortable : true
									},
									{
										title : '修改人名称',
										field : 'updateName',
										width : '80',
										sortable : true
									},
									{
										title : '修改时间',
										field : 'timeUpdate',
										width : '80',
										sortable : true
									},
									{
										title : '管理操作',
										field : 'action',
										width : '90',
										formatter : function(value, row) {
											var str = '';
											str += sys
													.formatString(
															'<a href="javascript:void(0)" class="btn2" onclick="editFun(\'{0}\');">修改</a>',
															row.dictId);

											str += sys
													.formatString(
															'<a href="javascript:void(0)" class="btn3" onclick="deleteFun(\'{0}\');">删除</a>',
															row.dictId);

											/*str += sys.formatString('<a href="javascript:void(0)" class="btn1" onclick="detailFun(\'{0}\');">查看</a>', row.dataId);
											 */
											return str;
										}
									} ] ],
							toolbar : '#toolbar',
							onBeforeLoad : function(param) {
								 parent.$.messager.progress({
									text : '数据加载中....'
								}); 
							},
							onLoadSuccess : function(data) {
								$('.iconImg').attr('src', sys.pixel_0);
								$('.btn1').linkbutton({
									text : '查看',
									plain : true,
									height : 18,
									iconCls : 'ext-icon-search'
								});
								$('.btn2').linkbutton({
									text : '修改',
									plain : true,
									height : 18,
									iconCls : 'ext-icon-pencil'
								});
								$('.btn3').linkbutton({
									text : '删除',
									plain : true,
									height : 18,
									iconCls : 'ext-icon-search'
								});
								parent.$.messager.progress('close');
							},
							rowStyler : function(index, row) {
								/*if(row.cresult=='9'){
									return 'color:red;';
								}else if(row.cresult=='10'){
									return 'color:blue;';
								}
								 */
							}

						});
	});
	var updateFun = function(id) {

	}
	var deleteFun = function(id) {
		parent.$.messager.confirm('确认', '是否要删除数据?', function(r) {
			if (r) {
			//	var url = sys.contextPath +'/maintain/dictionaryManage!delete.cxl';
				parent.$.messager.progress({
					text : '数据处理中....'
				});
			$.post(sys.contextPath + '/maintain/dictionaryManage!delete.cxl', {
	     	"id" : id},
			//	$.post(url, {"id" : id}, 
				
				function(result) {
//					debugger;
					parent.$.messager.progress('close');
					if (result.success) {
						grid.datagrid('load');
						parent.$.messager.alert('提示', '删除成功', 'info')
					} else {
						parent.$.messager.alert('提示', '删除失败', 'error');
					}
				}, 'json');
			}
		});
	}
	var detailFun = function(id) {

	}
	function doClear() {
		$("#searchForm").form('clear');
	}
</script>
<style>
.textbox {
	height: 20px;
	width: 100px;
}
</style>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">

	<div id="toolbar" style="display: none;">
	<input type="hidden" id ="classId" value="<%=classId%>"/>
		<form id="searchForm">
			<table>
				<tr>
					<td>字典名称:</td>
					<td><input class="easyui-validatebox textbox"
						name="QUERY_t#dictName_S_LK"
						data-options="validType:['maxLength[15]']" /></td>
					<td>字典编号:</td>
					<td><input class="easyui-validatebox textbox"
						name="QUERY_t#dictNo_S_LK"
						data-options="validType:['maxLength[15]']" /></td>
					<td>所属类型:</td>
					<td><input id="dictClasId" name="QUERY_t#dictClasId_S_EQ"
						class="easyui-combobox textbox"/>
					</td>

					<td><a href="javascript:void(0);" class="easyui-linkbutton"
						data-options="iconCls:'ext-icon-zoom',plain:true"
						onclick="grid.datagrid('load',sys.serializeObject($('#searchForm')));">查询</a></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true"  onclick="doClear()">清空条件</a></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton"
						data-options="iconCls:'ext-icon-note_add',plain:true"
						onclick="addFun('');">添加</a></td>
				</tr>
				
			</table>
		</form>
	</div>

	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>