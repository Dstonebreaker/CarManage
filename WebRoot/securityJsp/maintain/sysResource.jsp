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
			title : '添加资源信息',
			url : sys.contextPath + '/securityJsp/maintain/sysResourceForm.jsp',
			buttons : [ {
				text : '添加',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$, parent.mainMenu);
				}
			} ]
		});
	};
	var showFun = function(resoId) {
		var dialog = parent.sys.modalDialog({
			title : '查看资源信息',
			url : sys.contextPath + '/securityJsp/maintain/sysResourceForm.jsp?resoId=' + resoId
		});
	};
	var editFun = function(resoId) {
	
		var dialog = parent.sys.modalDialog({
			title : '编辑资源信息',
			url : sys.contextPath + '/securityJsp/maintain/sysResourceForm.jsp?resoId=' + resoId,
			buttons : [ {
				text : '编辑',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$, parent.mainMenu);
				}
			} ]
		});
	};
	var removeFun = function(resoId) {
		parent.$.messager.confirm('询问', '您确定要删除此记录？', function(r) {
			if (r) {
				$.post(sys.contextPath + '/maintain/sysResource!delete.cxl', {
					id : resoId
				}, function() {
					grid.treegrid('reload');
					parent.mainMenu.tree('reload');
				}, 'json');
			}
		});
	};
	var redoFun = function() {
		var node = grid.treegrid('getSelected');
		if (node) {
			grid.treegrid('expandAll', node.id);
		} else {
			grid.treegrid('expandAll');
		}
	};
	var undoFun = function() {
		var node = grid.treegrid('getSelected');
		if (node) {
			grid.treegrid('collapseAll', node.id);
		} else {
			grid.treegrid('collapseAll');
		}
	};
	$(function() {	
		grid = $('#grid').treegrid({
			title : '',
			url : sys.contextPath + '/maintain/sysResource!treeGrid.cxl',
	        idField : 'resoId',
			treeField : 'resoName',
			parentField : 'resoIdParent',
			rownumbers : true,
			pagination : false,
			sortName : 'resoIndex',
			sortOrder : 'asc',	
			frozenColumns : [ [
			 {
				width : '60',
				title : '资源编号',
				field : 'resoNo'
			}, {
				width : '200',
				title : '资源名称',
				field : 'resoName'
			} ] ],
			columns : [ [ {
				width : '200',
				title : '资源路径',
				field : 'resoUrl',
				formatter : function(value, row) {
					if(value){
					return sys.formatString('<span title="{0}">{1}</span>', value, value);
					}
				}
			}, {
				width : '60',
				title : '资源类型',
				field : 'sysDictionary',
	     		formatter : function(value, row) {
					return value.dictName;
					
				}
			}, {
				width : '150',
				title : '创建时间',
				field : 'timeCreate',
				hidden : true
			}, {
				width : '150',
				title : '修改时间',
				field : 'timeUpdate',
				hidden : true
			},  {
				width : '80',
				title : '排序',
				field : 'resoIndex',
				hidden : true
			}, {
				title : '操作',
				field : 'action',
				width : '180',
				formatter : function(value, row) {
			
					var str = '';
					<%if (securityUtil.havePermission("/maintain/sysResource!getById")) {%>
						str += sys.formatString('<a href="javascript:void(0)" class="btn1" onclick="showFun(\'{0}\');">查看</a>', row.resoId);
					<%}%>
					<%if (securityUtil.havePermission("/maintain/sysResource!update")) {%>
						str += sys.formatString('<a href="javascript:void(0)" class="btn2" onclick="editFun(\'{0}\');">编辑</a>', row.resoId);
					<%}%>
					<%if (securityUtil.havePermission("/maintain/sysResource!delete")) {%>
						str += sys.formatString('<a href="javascript:void(0)" class="btn3" onclick="removeFun(\'{0}\');">删除</a>', row.resoId);
					<%}%>
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
				$('.btn1').linkbutton({text:'查看', plain:true, height:18, iconCls:'ext-icon-search'});
				$('.btn2').linkbutton({text:'编辑', plain:true, height:18, iconCls:'ext-icon-pencil'});
				$('.btn3').linkbutton({text:'删除', plain:true, height:18, iconCls:'ext-icon-delete'});
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
				<%if (securityUtil.havePermission("/maintain/sysResource!save")) {%>
				<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addFun();">添加</a></td>
				<%}%>
				<td><div class="datagrid-btn-separator"></div></td>
				<td><a onclick="redoFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'ext-icon-resultset_next'">展开</a><a onclick="undoFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'ext-icon-resultset_previous'">折叠</a></td>
				<td><div class="datagrid-btn-separator"></div></td>
				<td><a onclick="grid.treegrid('reload');" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'ext-icon-arrow_refresh'">刷新</a></td>
			</tr>
		</table>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>