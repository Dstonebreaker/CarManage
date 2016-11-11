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
	var showFun = function(id) {
		var dialog = parent.sys.modalDialog({
			width:800,
			height:500,
			title : '查看车况信息',
			url : sys.contextPath + '/securityJsp/analysis/car_situation_form.jsp?id=' + id
		});
	};

	function doClear() {
		$("#searchForm").form('clear');
	};


	$(function() {
		
		 var managerOrg = $('#car_orgIdManager').combobox({
		        url: sys.contextPath + '/maintain/sysOrganization!doNotNeedSecurity_getOrg.cxl?userId=0' ,
		        panelHeight: '200',		     
		        editable: false,
		        valueField: 'orgIdManager',
		        textField: 'orgName'

		    });
		grid = $('#grid').datagrid({
			title : '',
			url : sys.contextPath + '/car/carInfoLast!grid.cxl',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			idField : 'obdNo',
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
				title : '单位',
				field : 'orgName',
				sortable : true
			},{
				
				title : '车况数据最近上传时间',
				field : 'obdTime',
				sortable : true
			},			
			{
				title : '操作',
				field : 'action',
				width : '100',
				formatter : function(value, row) {				
					var str = '';					
					<%if (securityUtil.havePermission("/car/carInfoLast!getById")) {%>
						str += sys.formatString('<a href="javascript:void(0)" class="btn1" onclick="showFun(\'{0}\');">详情</a>', row.obdNo);<%}%>
					
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
				$('.btn1').linkbutton({text:'车况详情', plain:true, height:18, iconCls:'ext-icon-book_open'});		
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
									
					<td>产权单位</td>
					<td><input id="car_orgIdManager" name="QUERY_t#orgIdManager_S_EQ" class="easyui-combobox textbox"/>
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