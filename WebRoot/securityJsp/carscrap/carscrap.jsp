<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.framework.util.SecurityUtil"%>
<%@ page import="com.framework.util.WebMsgUtil"%>
<%
	String path = request.getContextPath();
	SecurityUtil securityUtil = new SecurityUtil(session);
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	String dictId = request.getParameter("dictId");
	if (dictId == null) {
		dictId = "";
	}
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<head>
<base href="<%=basePath%>">

<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">
	var grid;
	var addFun = function() {
		var dialog = parent.sys.modalDialog({
			title : '添车辆报废信息',
			url : sys.contextPath + '/securityJsp/carscrap/carscrap_form.jsp',
			buttons : [ {
				text : '添加',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(
							dialog, grid, parent.$);
				}
			} ]
		});
	};
	var updateFun = function(scrapId) {
		var dialog = parent.sys.modalDialog({
			title : '编辑车辆报废信息',
			url : sys.contextPath + '/securityJsp/carscrap/carscrap_form.jsp?id='+scrapId,
			buttons : [ {
				text : '编辑',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(
							dialog, grid, parent.$);
				}
			} ]
		});
	};
	var sureFun = function(scrapId) {
		parent.$.messager.confirm('询问', '您确定要进行报废？', function(r) {
			if (r) {
				$.post(sys.contextPath + '/car/scrap!sure.cxl', {
					id : scrapId
				}, function(result) {
					parent.$.messager.progress('close');
					if (result.success) {
						grid.datagrid('reload');
						parent.$.messager.alert('提示', result.msg, 'info');
					} else {
						parent.$.messager.alert('提示', result.msg, 'error');
					}
				}, 'json');
			}
		});
	};
	var deleteFun = function(scrapId) {
		parent.$.messager.confirm('询问', '您确定要进行删除？', function(r) {
			if (r) {
				$.post(sys.contextPath + '/car/scrap!delete.cxl', {
					id : scrapId
				}, function(result) {
				 debugger;
					parent.$.messager.progress('close');
					if (result.success) {
						grid.datagrid('reload');
						parent.$.messager.alert('提示',result.msg, 'info');
					} else {
						parent.$.messager.alert('提示','删除失败', 'error');
					}
				}, 'json');
			}
		});
	};
	$(function() {
		grid = $('#grid')
				.datagrid(
						{
							title : '',
							id : 'print',
							url : sys.contextPath + '/car/scrap!grid.cxl',
							striped : true,
							idField : 'scrapId',
							rownumbers : true,
							pagination : true,
							sortName : 'timeCreate',
							sortOrder : 'desc',
							columns : [ [
									{
										width : '100',
										title : '报废编号',
										sortable : true,
										field : 'scrapNo'
									},
									{
										width : '80',
										title : '车牌号',
										sortable : true,
										field : 'carNo'
									},
									{
										width : '100',
										title : '产权单位',
										sortable : true,
										field : 'orgName'
									},
									{
										width : '100',
										title : '报废结果',
										sortable : true,
										field : 'dictName'
									},
									{
										width : '100',
										title : '已使用年限',
										sortable : true,
										field : 'scrapUsedYear'
									},
									{
										width : '100',
										title : '报废日期',
										sortable : true,
										field : 'scrapTime',
										formatter : function(value, row) {
											if (value != undefined) {
												return value.substr(0, 10);
											}
										}
									},
									{
										width : '100',
										title : '报废人',
										sortable : true,
										field : 'scrapName'
									},
									{
										width : '100',
										title : '登记时间',
										sortable : true,
										field : 'timeCreate',
										formatter : function(value, row) {
											if (value != undefined) {
												return value.substr(0, 10);
											}
										}
									},
									{
										title : '操作',
										field : 'options',
										width : '200',
										formatter : function(value, row) {
											var str = '';
											if(row.dictIdScrapResult=='CLBF0001'){
                                 <%if (securityUtil.havePermission("/car/scrap!sure")) {%>
	                              str = sys.formatString(	'<a href="javascript:void(0)" class="btn1" onclick="sureFun(\'{0}\')" data-options="plain:true">确认报废</a>',	row.scrapId);
                                 <%}%>
	
                                <%if (securityUtil.havePermission("/car/scrap!update")) {%>
	                              str += sys.formatString(	'<a href="javascript:void(0)" class="btn2" onclick="updateFun(\'{0}\')" data-options="plain:true">编辑</a>',row.scrapId);
	                              <%}%>
	
                                <%if (securityUtil.havePermission("/car/scrap!delete")) {%>
	                             str += sys.formatString('<a href="javascript:void(0)" class="btn3" onclick="deleteFun(\'{0}\')" data-options="plain:true">删除</a>',row.scrapId);
                                <%}%>
                                     } else{
                                       <%if (securityUtil.havePermission("/car/scrap!sure")) {%>
	                              str = sys.formatString(	'<a href="javascript:void(0)" class="btn1" onclick="sureFun(\'{0}\')" data-options="plain:true,disabled:true">确认报废</a>',	row.scrapId);
                                 <%}%>
	
                                <%if (securityUtil.havePermission("/car/scrap!update")) {%>
	                              str += sys.formatString(	'<a href="javascript:void(0)" class="btn2" onclick="updateFun(\'{0}\')" data-options="plain:true,disabled:true">编辑</a>',row.scrapId);
	                              <%}%>
	
                                <%if (securityUtil.havePermission("/car/scrap!delete")) {%>
	                             str += sys.formatString('<a href="javascript:void(0)" class="btn3" onclick="deleteFun(\'{0}\')" data-options="plain:true,disabled:true">删除</a>',row.scrapId);
                                <%}%>
                                     
                                     }
	                             return str;
										}
									} ] ],
							toolbar : '#toolbar',
							onBeforeLoad : function(row, param) {
								parent.$.messager.progress({
									text : '数据加载中....'
								});
							},
							onLoadSuccess : function(row, data) {
								$('.btn1').linkbutton({
									text : '确认报废',
									plain : true,
									height : 18,
									iconCls : 'ext-icon-tick'
								});
								$('.btn2').linkbutton({
									text : '编辑',
									plain : true,
									height : 18,
									iconCls : 'ext-icon-page_white_edit'
								});
								$('.btn3').linkbutton({
									text : '删除',
									plain : true,
									height : 18,
									iconCls : 'ext-icon-delete'
								});
								parent.$.messager.progress('close');
							}
						});

	});
	function searchCar() {
		var startTime = $('#startTime').datebox('getValue');
		var endTime = $('#endTime').datebox('getValue');
		if (endTime != "" && startTime != "" && startTime >= endTime) {
			parent.$.messager.alert('提示', '结束时间不能早于或等于开始时间!', 'info');
			return false;
		}
		grid.datagrid('load', sys.serializeObject($('#searchForm')));
	}
	function doClear() {
		$("#searchForm").form('clear');
	}
	function exportFile(){
			document.searchForm.action=sys.contextPath +'/car/scrap!doNotNeedSecurity_exports.cxl';
		    document.searchForm.submit();
	}
	var LODOP; //声明为全局变量 
	function prn1_preview() {
	$("#tab").html("");
	var data =  $('#grid').datagrid('getData');
	var str = '';
	var strs ="<tr><td style='width: 15%'>报废编号</td><td style='width: 10%'>车牌号</td><td style='width:15%'>产权单位</td><td style='width: 10%'>报废结果</td><td style='width: 10%'>已使用年限</td><td style='width: 15%'>报废日期</td><td style='width: 10%'>报废人</td><td style='width: 15%'>登记时间</td></tr>" ;
	var i=0;
	for(i;i<data.total;i++){
	 var scrap=data.rows[i]; 
	 str = "<tr><td>"+scrap.scrapNo+"</td><td>"+scrap.carNo+"</td><td>"+scrap.orgName+"</td><td>"+scrap.dictName+"</td><td>" +scrap.scrapUsedYear+"</td><td>"+
	 scrap.scrapTime+"</td><td>"+scrap.scrapName+"</td><td>"+scrap.timeCreate+"</td></tr>" ;
	 strs=strs+str;
	}
	$("#tab").html(strs);		
		CreateOneFormPage();	
		LODOP.PREVIEW();	
	};
	function prn1_print() {		
		CreateOneFormPage();
		LODOP.PRINT();	
	};
	function prn1_printA() {		
		CreateOneFormPage();
		LODOP.PRINTA(); 	
	};	
	function CreateOneFormPage(){
		myIpAddress = '<%=basePath%>';
		LODOP=getLodop();  
		LODOP.PRINT_INIT("车辆报废");
		LODOP.SET_PRINT_PAGESIZE(1,1380,880,"");
		LODOP.ADD_PRINT_TEXT(80,"45%",400,35,"车辆报废");
		LODOP.SET_PRINT_STYLEA(0,"FontSize",30);

		LODOP.SET_PRINT_PAGESIZE(2,0,0,"");
		LODOP.SET_PREVIEW_WINDOW(0,0,0,0,0,"");
		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",1);//横向时的正向显示
		LODOP.ADD_PRINT_HTM(160,"1%","100%","100%",document.getElementById("print").innerHTML);

	};
	
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
		<form id="searchForm" name="searchForm" method="post" target="report">
			<table>
				<tr>
					<td>产权单位</td>
					<td><input name="orgIdManager"
						class="easyui-combobox textbox "
						data-options="panelHeight:'200',editable:false,
						valueField: 'orgIdManager',
						textField: 'orgName',
						url: sys.contextPath+'/maintain/sysOrganization!doNotNeedSecurity_getOrgManger.cxl'
						" /></td>
					<td>车牌号</td>
					<td><input class="easyui-validatebox textbox"
						name="QUERY_t#carNo_S_LK"
						data-options="validType:['maxLength[15]']" /></td>
						<td>开始日期</td>
					<td><input id="startTime" name="startTime"
						class="easyui-datebox textbox" data-options="editable:false" /></td>
					<td>结束日期</td>
					<td><input id="endTime" name="endTime"
						class="easyui-datebox textbox" data-options="editable:false" /></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton"
						data-options="iconCls:'ext-icon-zoom',plain:true"
						onclick="searchCar()">查询</a> <a href="javascript:void(0);"
						class="easyui-linkbutton"
						data-options="iconCls:'ext-icon-zoom_out',plain:true"
						onclick="doClear()">清空条件</a></td>
					
					<%
						if (securityUtil.havePermission("/car/scrap!save")) {
					%>
					<td><a href="javascript:void(0);" class="easyui-linkbutton"
						data-options="iconCls:'ext-icon-note_add',plain:true"
						onclick="addFun();">添加</a></td>
					<%
						}
					%>
					<td><a href="javascript:void(0);" class="easyui-linkbutton"
									data-options="iconCls:'ext-icon-page_excel',plain:true"
									onclick="exportFile()">导出</a>
									<a href="javascript:void(0);" class="easyui-linkbutton"
									data-options="iconCls:'ext-icon-page_excel',plain:true"
									onclick="prn1_preview()">打印</a>
					 </td>
				</tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
    <div  id="print" style="display:none;width: 100%" >
	  <table style="width: 100%" id="tab" border="1">
	  </table>
	</div>
	
</body>
</html>
