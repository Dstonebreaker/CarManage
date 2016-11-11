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
			title : '添加车型',
			url : sys.contextPath + '/securityJsp/carModel/car_model_form.jsp',
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
			title : '查看车型信息',
			url : sys.contextPath + '/securityJsp/carModel/car_model_details.jsp?id=' + id
		});
	};
	var editFun = function(id) {
		var dialog = parent.sys.modalDialog({
			width:800,
			height:500,
			title : '编辑车型信息',
			url : sys.contextPath + '/securityJsp/carModel/car_model_form.jsp?id=' + id,
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
				$.post(sys.contextPath + '/car/Model!delete.cxl', {
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
			url : sys.contextPath + '/car/Model!grid.cxl?',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			idField : 'modelId',
			pageSize : 30,
			pageList : [ 10, 20, 30, 40, 50 ],
			sortOrder : 'asc',
			frozenColumns : [ [ {
				width : '100',
				title : '车辆品牌',
				field : 'carBrand',
				sortable : true
			} ] ],
			columns : [ [ {
				
				title : '车系',
				field : 'carSeries',
				sortable : true
			},{
				
				title : '车型',
				field : 'modelName',
				sortable : true
			},
			{
				
				title : '产地',
				field : 'productionArea',
				sortable : true
		
			},{
				
				title : '燃油类型',
				field : 'oilType',
				sortable : true				
			},{
				
				title : '是否带 T',
				field : 'isT',
				sortable : true
			},{
				
				title : '载客量（人）',
				field : 'modelPeople',
				sortable : true
			},{
				
				title : '载重量（吨）',
				field : 'modelLoad',
				sortable : true
			},
			{
				
				title : '保养周期',
				field : 'modelMaintenanceMonth',
				sortable : true
			},{
				title : '操作',
				field : 'action',
				width : '200',
				formatter : function(value, row) {				
					var str = '';	
					<%if (securityUtil.havePermission("/car/Model!getById")) {%>
						str += sys.formatString('<a href="javascript:void(0)" class="btn1" onclick="showFun(\'{0}\');">查看</a>', row.modelId);<%}%>
					<%if (securityUtil.havePermission("/car/Model!update")) {%>
						str += sys.formatString('<a href="javascript:void(0)" class="btn2" onclick="editFun(\'{0}\');">编辑</a>', row.modelId);<%}%>
					<%if (securityUtil.havePermission("/car/Model!delete")) {%>
						str += sys.formatString('<a href="javascript:void(0)" class="btn3" onclick="removeFun(\'{0}\');">删除</a>', row.modelId);<%}%>
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
					<td>车辆品牌</td>
					<td><input 
						name="QUERY_t#dictIdBrand_S_LK"
						class="easyui-combobox textbox"
						data-options="panelHeight:'200',editable:false,
						valueField: 'dictId',
						textField: 'dictName',
						url: sys.contextPath+'/maintain/dictionaryManage!doNotNeedSecurity_getByClassId.cxl?dictClassId=CarBrand'
						"/></td>
					<td>车系</td>
					<td><input
						name="QUERY_t#dictIdCarSeries_S_LK"
					   class="easyui-combobox textbox"
						data-options="panelHeight:'200',editable:false,
						valueField: 'dictId',
						textField: 'dictName',
						url: sys.contextPath+'/maintain/dictionaryManage!doNotNeedSecurity_getByClassId.cxl?dictClassId=CarSeries'
						"/></td>
					<td>车型</td>
					<td><input name="QUERY_t#modelName_S_LK"
						   class="easyui-validatebox textbox"/>
					</td>
					<td>产地</td>
					<td><input name="QUERY_t#dictIdProductionArea_S_EQ"
						  class="easyui-combobox textbox"
						data-options="panelHeight:'200',editable:false,
						valueField: 'dictId',
						textField: 'dictName',
						url: sys.contextPath+'/maintain/dictionaryManage!doNotNeedSecurity_getByClassId.cxl?dictClassId=ProductionArea'
						"/>
					</td>
					<td>燃油类型</td>
					<td><input name="QUERY_t#dictIdUseOilType_S_EQ"
						  class="easyui-combobox textbox"
						data-options="panelHeight:'200',editable:false,
						valueField: 'dictId',
						textField: 'dictName',
						url: sys.contextPath+'/maintain/dictionaryManage!doNotNeedSecurity_getByClassId.cxl?dictClassId=OilType'
						"/>
					</td>
					<td>是否带T</td>
					<td><input name="QUERY_t#dictIdIsT_S_EQ"
						class="easyui-combobox textbox"
						data-options="panelHeight:'200',editable:false,
						valueField: 'dictId',
						textField: 'dictName',
						url: sys.contextPath+'/maintain/dictionaryManage!doNotNeedSecurity_getByClassId.cxl?dictClassId=IsT'
						"/>
					</td>				
					<td><a href="javascript:void(0);" class="easyui-linkbutton"
						data-options="iconCls:'ext-icon-zoom',plain:true"
						onclick="doSerch();">查询</a></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true"  onclick="doClear()">清空条件</a></td>
						<%
					if (securityUtil.havePermission("/car/Model!save")) {
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