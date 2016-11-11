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
	var editFun = function(faultCode) {
		var dialog = parent.sys.modalDialog({
			title : '编辑设置信息',
			url : sys.contextPath + '/securityJsp/maintain/sysObdCodeForm.jsp?id=' + faultCode,
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
			url : sys.contextPath + '/maintain/ObdCode!grid.cxl',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			idField : 'faultCode',
			sortOrder : 'asc',
			frozenColumns : [ [ {
				width : '100',
				title : '故障码',
				field : 'faultCode',
				sortable : true
			} ] ],
			columns : [ [ {
				width : '100',
				title : '故障码名称',
				field : 'faultNameChinese',
				sortable : true
			}, {
				width : '60',
				title : '修改时间',
				field : 'timeUpdate'
			},  {
				title : '操作',
				field : 'action',
				width : '230',
				formatter : function(value, row) {
					var str = '';
					<%if (securityUtil.havePermission("/maintain/ObdCode!update")) {%>
						str += sys.formatString('<a href="javascript:void(0)" class="btn2" onclick="editFun(\'{0}\');">编辑</a>', row.faultCode);
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