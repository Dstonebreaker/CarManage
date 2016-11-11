<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	$(function() {
	$('#functions').combobox(
				{
					url : sys.contextPath
							+ '/sm/systemLogView!doNotNeedSessionAndSecurity_function.cxl',
					valueField : 'logOperateFunction',
					textField : 'logOperateFunction',
					panelHeight : '200',
					width : 100,

				});
		
		grid = $('#grid').datagrid({
			title : '',
			url : sys.contextPath + '/sm/systemLogView!grid.cxl',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			idField : 'logId',
			sortName : 'logOperateTime',
			sortOrder : 'desc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
			frozenColumns : [ [ {
				width : '100',
				title : '操作人',
				field : 'userName',
				sortable : true
			}, {
				width : '150',
				title : '操作功能',
				field : 'logOperateFunction',
				sortable : true
			} ] ],
			columns : [ [ {
				width : '400',
				title : '操作功能描述',
				field : 'logOperateDescription'
			}, {
				width : '150',
				title : '操作时间',
				field : 'logOperateTime',
				sortable : true
			}, {
				width : '100',
				title : 'ip地址',
				field : 'logIp'			
			} ] ],

			toolbar : '#toolbar',
			onBeforeLoad : function(param) {
			
			},
			onLoadSuccess : function(data) {
				
				parent.$.messager.progress('close');
			}
		});
	});
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div id="toolbar" style="display: none;">
		<table>
			<tr>
				<td>
					<form id="searchForm">
						<table>
							<tr>
								<td>操作人员</td>
								<td><input name="QUERY_t#userName_S_LK" style="width: 80px;" />
								</td>
								<td>功能</td>
								<td>
								<input name="QUERY_t#logOperateFunction_S_EQ" id="functions" style="width:100px"/>
								</td>
								<td>ip</td>
								<td><input name="QUERY_t#logIp_S_LK" style="width:80px"/></td>
								<td>操作时间</td>
								<td><input name="QUERY_t#logOperateTime_D_GE" class="Wdate"
									onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
									readonly="readonly" style="width: 120px;" />-<input
									name="QUERY_t#logOperateTime_D_LE" class="Wdate"
									onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
									readonly="readonly" style="width: 120px;" />
								</td>
								<td><a href="javascript:void(0);" class="easyui-linkbutton"
									data-options="iconCls:'ext-icon-zoom',plain:true"
									onclick="grid.datagrid('load',sys.serializeObject($('#searchForm')));">查询</a><a
									href="javascript:void(0);" class="easyui-linkbutton"
									data-options="iconCls:'ext-icon-zoom_out',plain:true"
									onclick="$('#searchForm input').val('');grid.datagrid('load',{});">查询全部</a>
								</td>
							</tr>
						</table>
					</form></td>
			</tr>
			
		</table>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>