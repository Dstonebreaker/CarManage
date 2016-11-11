<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.framework.util.SecurityUtil"%>
<%
	String contextPath = request.getContextPath();
	SecurityUtil securityUtil = new SecurityUtil(session);
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">
	var grid;
	var editFun = function(id) {
		var dialog = parent.sys.modalDialog({
			width:800,
			height:500,
			title : '编辑车辆状态信息',
			url : sys.contextPath + '/securityJsp/carstatus/car_status_form.jsp?id=' + id,
			buttons : [ {
				text : '编辑',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	function doClear() {
		$("#searchForm").form('clear');
	};


	$(function() {
		grid = $('#grid').datagrid({
			title : '',
			url : sys.contextPath + '/car/carStatusEdit!grid.cxl',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			idField : 'carId',
			pageSize : 30,
			pageList : [ 10, 20, 30, 40, 50 ],
			sortOrder : 'asc',
			frozenColumns : [ [ {
				width : '100',
				title : '车牌号',
				field : 'carNo',
				sortable : true
			} ] ],
			columns : [ [ 
			{				
				title : '类型',
				field : 'carType',
				sortable : true
			},{
				
				title : '车型',
				field : 'modelName',
				sortable : true
			},
			{
				
				title : 'SIM号',
				field : 'simNo',
				sortable : true				
			},
			{
				title : 'KEY号',
				field : 'keyNo',
				sortable : true				
			},
			{
				title : 'OBD号',
				field : 'obdNo',
				sortable : true				
			},
			{
				title : '操作',
				field : 'action',
				width : '100',
				formatter : function(value, row) {				
					var str = '';					
					<%if (securityUtil.havePermission("/car/carStatusEdit!update")) {%>
						str += sys.formatString('<a href="javascript:void(0)" class="btn1" onclick="editFun(\'{0}\');">编辑</a>', row.carId);<%}%>
					
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
				$('.btn1').linkbutton({text:'编辑', plain:true, height:18, iconCls:'ext-icon-pencil'});		
				parent.$.messager.progress('close');
			}
		});
	});
	 var doSerch= function(){			
			grid.datagrid('load',sys.serializeObject($('#searchForm')));
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
		<form id="searchForm">
			<table>
				<tr>
									
					<td>车牌号</td>
					<td><input name="QUERY_t#carNo_S_LK"
						   class="easyui-validatebox textbox"/>
					</td>
												
					<td><a href="javascript:void(0);" class="easyui-linkbutton"
						data-options="iconCls:'ext-icon-zoom',plain:true"
						onclick="doSerch();">查询</a></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true"  onclick="doClear()">清空条件</a></td>
					
				</tr>
				
			</table>
		</form>
	</div> 
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>