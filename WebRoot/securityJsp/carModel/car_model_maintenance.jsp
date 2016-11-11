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
	var addFun = function() {
		var dialog = parent.sys.modalDialog({
			width:800,
			height:500,
			title : '添加维保模型',
			url : sys.contextPath + '/securityJsp/carModel/car_model_maintenance_form.jsp',
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
			title : '查看维保模型信息',
			url : sys.contextPath + '/securityJsp/carModel/car_model_maintenance_form.jsp?id=' + id
		});
	};
	var editFun = function(id) {
		var dialog = parent.sys.modalDialog({
			width:800,
			height:500,
			title : '编辑维保模型信息',
			url : sys.contextPath + '/securityJsp/carModel/car_model_maintenance_form.jsp?id=' + id,
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
				$.post(sys.contextPath + '/car/ModelMaintenance!delete.cxl', {
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
			url : sys.contextPath + '/car/ModelMaintenance!grid.cxl?',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			idField : 'mmainId',
			pageSize : 30,
			sortOrder : 'asc',
			frozenColumns : [ [ {
				width : '100',
				title : '车辆种类',
				field : 'modelName',
				sortable : true
			} ] ],
			columns : [ [ {
				
				title : '保养里程',
				field : 'mmainMileage',
				sortable : true,
			},{
				
				title : '保养内容',
				field : 'mmainInfo',
				sortable : true,	
			},
			{
				
				title : '登记时间',
				field : 'timeCreate',
				sortable : true,
		
			},{
				title : '操作',
				field : 'action',
				width : '300',
				formatter : function(value, row) {
					var str = '';				
						str += sys.formatString('<a href="javascript:void(0)" class="btn1" onclick="showFun(\'{0}\');">查看</a>', row.mmainId);
									
						str += sys.formatString('<a href="javascript:void(0)" class="btn2" onclick="editFun(\'{0}\');">编辑</a>', row.mmainId);
										
					   str += sys.formatString('<a href="javascript:void(0)" class="btn3" onclick="removeFun(\'{0}\');">删除</a>', row.mmainId);
			
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
<td><a href="javascript:void(0);" class="easyui-linkbutton"data-options="iconCls:'ext-icon-note_add',plain:true"onclick="addFun('');">添加</a></td>
				</tr>	
			</table>
		</form>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>