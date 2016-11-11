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
	function doClear() {
		$("#searchForm").form('clear');
	}
	var addFun = function() {
	
		var dialog = parent.sys.modalDialog({
			width:800,
			height:500,
			title : '添加年审记录',
			url : sys.contextPath + '/securityJsp/carexamine/car_examine_form.jsp?from=add',
			buttons : [ {
				text : '添加',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	var showFun = function(id) {
		var dialog = parent.sys.modalDialog({
			width:800,
			height:500,
			title : '查看年审信息',
			url : sys.contextPath + '/securityJsp/carexamine/car_examine_details.jsp?id=' + id
		});
	};
	var editFun = function(id) {
		var dialog = parent.sys.modalDialog({
			width:800,
			height:500,
			title : '编辑年审记录',
			url : sys.contextPath + '/securityJsp/carexamine/car_examine_form.jsp?id=' + id,
			buttons : [ {
				text : '编辑',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	var removeFun = function(id) {
		parent.$.messager.confirm('询问', '您确定要删除此记录？', function(r) {
			if (r) {
				$.post(sys.contextPath + '/car/Examine!delete.cxl', {
					id : id
				}, function(result) {
					parent.$.messager.progress('close');
					if (result.success) {
						grid.datagrid('reload');
						parent.$.messager.alert('提示', '删除成功', 'info')
					} else {
						parent.$.messager.alert('提示', '删除失败', 'error');
					}
				}, 'json');
			}
		});
	};


	$(function() {
		grid = $('#grid').datagrid({
			title : '',
			url : sys.contextPath + '/car/Examine!grid.cxl?',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			idField : 'examId',
			pageSize : 30,
			sortOrder : 'asc',
			frozenColumns : [ [ {
				width : '100',
				title : '车牌号',
				field : 'carNo',
				sortable : true
			} ] ],
			columns : [ [
		    {
				width : '100',
				title : '车辆管理单位',
				field : 'orgName',
				sortable : true
	
			},{
				width : '100',
				title : '审核周期(年)',
				field : 'examCycle',
				sortable : true
	
			},{
				width : '200',
				title : '生效日期',
				field : 'examValidStartTime',
				sortable : true,
	              formatter: function (value, row) {
                      if (value!=undefined) {
                          return value.substr(0, 10);
                      }
                  }
			},
			{
				width : '200',
				title : '终止日期',
				field : 'examValidOverTime',
				sortable : true,
	              formatter: function (value, row) {
                      if (value!=undefined) {
                          return value.substr(0, 10);
                      }
                  }
		
			},{
				width : '200',
				title : '金额',
				field : 'examMoney',
				sortable : true			
			},{
				
				title : '经办人',
				field : 'userName',
				sortable : true	
				
			
			},{
				title : '操作',
				field : 'action',
				width : '200',
				formatter : function(value, row) {				
					var str = '';	
					<%if (securityUtil.havePermission("/car/Examine!getById")) {%>
						str += sys.formatString('<a href="javascript:void(0)" class="btn1" onclick="showFun(\'{0}\');">查看</a>', row.examId);<%}%>
					<%if (securityUtil.havePermission("/car/Examine!update")) {%>
						str += sys.formatString('<a href="javascript:void(0)" class="btn2" onclick="editFun(\'{0}\');">编辑</a>', row.examId);<%}%>
					<%if (securityUtil.havePermission("/car/Examine!delete")) {%>
						str += sys.formatString('<a href="javascript:void(0)" class="btn3" onclick="removeFun(\'{0}\');">删除</a>', row.examId);<%}%>
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
				$('.btn3').linkbutton({text:'删除', plain:true, height:18, iconCls:'ext-icon-delete'});
				parent.$.messager.progress('close');
			}
		});
	});
	 var doSerch= function(){
			var startTime= $('#startTime').datebox('getValue');
			var endTime= $('#endTime').datebox('getValue');			
			if(endTime!=""&&startTime!=""&&startTime>=endTime){				
				parent.$.messager.alert('提示','结束时间不能早于或等于开始时间!','info');
				return false;
			}
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
					<td><input 
						name="QUERY_t#carNo_S_LK"  class="easyui-validatebox textbox"/></td>
<!-- 					<td>管理单位</td>					
					<td><input id="car_orgIdManager"name="QUERY_t#orgIdManager_S_EQ" class="easyui-combobox textbox "
						data-options="panelHeight:'200',editable:false,
						valueField: 'orgIdManager',
						textField: 'orgName',
						url: sys.contextPath+'/maintain/sysOrganization!doNotNeedSecurity_getOrg.cxl'
						" /></td>	 -->				
					<td>生效日期</td>
					<td><input id ="startTime" name="startTime"
						  class="easyui-datebox textbox" data-options="editable:false" />
					</td>
					<td>终止日期</td>
					<td><input id = "endTime" name="endTime"
						  class="easyui-datebox textbox" data-options="editable:false"  />
					</td>								
					<td><a href="javascript:void(0);" class="easyui-linkbutton"
						data-options="iconCls:'ext-icon-zoom',plain:true"
						onclick="doSerch();">查询</a></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true"  onclick="doClear()">清空条件</a></td>
				<%
					if (securityUtil.havePermission("/car/Examine!save")) {
				%>
				<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addFun();">添加</a></td>
				<%
					}
				%>
				</tr>
				
			</table>
		</form>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>