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
	var showFun = function(settId) {
		var dialog = parent.sys.modalDialog({
			title : '查看设置信息',
			url : sys.contextPath + '/securityJsp/maintain/sysSettingForm.jsp?id=' + settId
		});
	};
	var editFun = function(settId) {
		var dialog = parent.sys.modalDialog({
			title : '编辑设置信息',
			url : sys.contextPath + '/securityJsp/maintain/sysSettingForm.jsp?id=' + settId,
			buttons : [ {
				text : '编辑',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	$(function() {
	
		grid = $('#grid').datagrid({
			title : '',
			url : sys.contextPath + '/maintain/sysSetting!grid.cxl',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			idField : 'settId',
			sortOrder : 'asc',
			frozenColumns : [ [ {
				width : '100',
				title : '编号',
				field : 'settId',
				sortable : true
			} ] ],
			columns : [ [ {
				width : '100',
				title : '设置名称',
				field : 'settName',
				sortable : true
			}, {
				width : '100',
				title : '设置内容',
				field : 'settValue',
				sortable : true
			}, {
				width : '60',
				title : '内容单位',
				field : 'settUnit'
			},  {
				title : '操作',
				field : 'action',
				width : '230',
				formatter : function(value, row) {
					var str = '';
					<%if (securityUtil.havePermission("/maintain/sysSetting!getById")) {%>
						str += sys.formatString('<a href="javascript:void(0)" class="btn1" onclick="showFun(\'{0}\');">查看</a>', row.settId);
					<%}%>
					<%if (securityUtil.havePermission("/maintain/sysSetting!update")) {%>
						str += sys.formatString('<a href="javascript:void(0)" class="btn2" onclick="editFun(\'{0}\');">编辑</a>', row.settId);
					<%}%>
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
				$('.btn1').linkbutton({text:'查看', plain:true, height:18, iconCls:'ext-icon-search'});
				$('.btn2').linkbutton({text:'编辑', plain:true, height:18, iconCls:'ext-icon-pencil'});
				parent.$.messager.progress('close');
			}
		});
	});
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>