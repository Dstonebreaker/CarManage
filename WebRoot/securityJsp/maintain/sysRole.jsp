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
			title : '添加角色信息',
			url : sys.contextPath + '/securityJsp/maintain/sysRoleForm.jsp',
			buttons : [ {
				text : '添加',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	var showFun = function(roleId) {
		var dialog = parent.sys.modalDialog({
			title : '查看角色信息',
			url : sys.contextPath + '/securityJsp/maintain/sysRoleForm.jsp?id=' + roleId
		});
	};
	var editFun = function(roleId) {
		var dialog = parent.sys.modalDialog({
			title : '编辑角色信息',
			url : sys.contextPath + '/securityJsp/maintain/sysRoleForm.jsp?id=' + roleId,
			buttons : [ {
				text : '编辑',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	var removeFun = function(roleId) {
		parent.$.messager.confirm('询问', '您确定要删除此记录？', function(r) {
			if (r) {
				$.post(sys.contextPath + '/maintain/sysRole!delete.cxl', {
					id : roleId
				}, function() {
					grid.datagrid('reload');
				}, 'json');
			}
		});
	};
	var grantFun = function(roleId) {
		var dialog = parent.sys.modalDialog({
			title : '角色授权',
			url : sys.contextPath + '/securityJsp/maintain/sysRoleGrant.jsp?id=' + roleId,
			buttons : [ {
				text : '授权',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	$(function() {
	
		grid = $('#grid').datagrid({
			title : '',
			url : sys.contextPath + '/maintain/sysRole!grid.cxl',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			idField : 'roleId',
			sortOrder : 'asc',
			frozenColumns : [ [ {
				width : '100',
				title : '角色名称',
				field : 'roleName',
				sortable : true
			} ] ],
			columns : [ [ {
				width : '150',
				title : '创建时间',
				field : 'timeCreate',
				sortable : true
			}, {
				width : '150',
				title : '修改时间',
				field : 'timeCreate',
				sortable : true
			}, {
				width : '300',
				title : '角色描述',
				field : 'roleMemo',
				sortable : true
			},  {
				title : '操作',
				field : 'action',
				width : '230',
				formatter : function(value, row) {
					var str = '';
					<%if (securityUtil.havePermission("/maintain/sysRole!getById")) {%>
						str += sys.formatString('<a href="javascript:void(0)" class="btn1" onclick="showFun(\'{0}\');">查看</a>', row.roleId);
					<%}%>
					<%if (securityUtil.havePermission("/maintain/sysRole!update")) {%>
						str += sys.formatString('<a href="javascript:void(0)" class="btn2" onclick="editFun(\'{0}\');">编辑</a>', row.roleId);
					<%}%>
					<%if (securityUtil.havePermission("/maintain/sysRole!grant")) {%>
						str += sys.formatString('<a href="javascript:void(0)" class="btn3" onclick="grantFun(\'{0}\');">授权</a>', row.roleId);
					<%}%>
					<%if (securityUtil.havePermission("/maintain/sysRole!delete")) {%>
						str += sys.formatString('<a href="javascript:void(0)" class="btn4" onclick="removeFun(\'{0}\');">删除</a>', row.roleId);
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
				$('.btn3').linkbutton({text:'授权', plain:true, height:18, iconCls:'ext-icon-key'});
				$('.btn4').linkbutton({text:'删除', plain:true, height:18, iconCls:'ext-icon-delete'});
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
				<%if(securityUtil.havePermission("/maintain/sysRole!save")) {%>
				<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addFun();">添加</a></td>
				<%}%>
				<td><div class="datagrid-btn-separator"></div></td>
				<td><input id="searchBox" class="easyui-searchbox" style="width: 150px" data-options="searcher:function(value,name){grid.datagrid('load',{'QUERY_t#roleName_S_LK':value});},prompt:'搜索角色名称'"></input></td>
				<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchBox').searchbox('setValue','');grid.datagrid('load',{});">清空条件</a></td>
			</tr>
		</table>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>