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
			title : '添加机构信息',
			url : sys.contextPath + '/securityJsp/maintain/sysOrganizationForm.jsp',
			buttons : [ {
				text : '添加',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	var showFun = function(orgId) {
		var dialog = parent.sys.modalDialog({
			title : '查看机构信息',
			url : sys.contextPath + '/securityJsp/maintain/sysOrganizationForm.jsp?id=' + orgId
		});
	};
	var editFun = function(orgId) {
		var dialog = parent.sys.modalDialog({
			title : '编辑机构信息',
			url : sys.contextPath + '/securityJsp/maintain/sysOrganizationForm.jsp?id=' + orgId,
			buttons : [ {
				text : '编辑',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	var removeFun = function(orgId) {
	     $.post(sys.contextPath + '/maintain/sysOrganization!doNotNeedSecurity_haveSubOrg.cxl', {
					id : orgId
				}, function(result) {
					var msginfo = "您确定要删除此记录？";
					if(result=='e'){
						parent.$.messager.alert('提示', '删除失败!', 'error');
						return false;
					}else if(result=='y'){
						msginfo = "该机构含有子机构，您确定删除所有记录吗？";
					}else if(result=='n'){
						msginfo = "您确定要删除此记录？";
					}		
					parent.$.messager.confirm('询问', msginfo, function(r) {
						if (r) {
							$.post(sys.contextPath + '/maintain/sysOrganization!delete.cxl', {
								id : orgId	
							}, function(result) {
							if (result.success) {
			    	   parent.$.messager.alert('提示',result.msg, 'info');
					     grid.treegrid({url : sys.contextPath + '/maintain/sysOrganization!getChildrenTreeGrid.cxl?'});
					     $dialog.dialog('destroy');
			          	} else {
					       parent.$.messager.alert('提示', result.msg, 'error');
			            	}
						//	grid.treegrid({url : sys.contextPath + '/maintain/sysOrganization!getChildrenTreeGrid.cxl?'});
							}, 'json');
						}
					});	
				
			}, 'json');		
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
			url : sys.contextPath + '/maintain/sysOrganization!getChildrenTreeGrid.cxl?',
			idField : 'orgId',
			treeField : 'orgName',
			parentField : 'orgIdOrgParent',
			rownumbers : true,
			pagination : false,
			sortOrder : 'asc',
			frozenColumns : [ [ {
				width : '200',
				title : '单位名称',
				field : 'orgName'
			} ] ],
			columns : [ [ {
				width : '150',
				title : '图标名称',
				field : 'orgIconic'
			}, {
				width : '150',
				title : '单位简称',
				field : 'orgNameAbbr'
			}, {
				width : '150',
				title : '车辆管理单位',
				field : 'orgManagerName'
			},{
				width : '200',
				title : '机构地址',
				field : 'orgAddress'
			}, {
				width : '150',
				title : '单位联系人',
				field : 'orgLinkman'
			},{
				width : '150',
				title : '单位联系电话',
				field : 'orgLinkPhone'
			}, {
				width : '150',
				title : '创建时间',
				field : 'timeCreate',
				hidden : true
			}, {
				width : '150',
				title : '修改时间',
				field : 'timeCreate',
				hidden : true
			}, {
				title : '操作',
				field : 'action',
				width : '230',
				formatter : function(value, row) {
					var str = '';
					<%if (securityUtil.havePermission("/maintain/sysOrganization!getById")) {%>
						str += sys.formatString('<a href="javascript:void(0)" class="btn1" onclick="showFun(\'{0}\');">查看</a>', row.orgId);
					<%}%>
					<%if (securityUtil.havePermission("/maintain/sysOrganization!update")) {%>
						str += sys.formatString('<a href="javascript:void(0)" class="btn2" onclick="editFun(\'{0}\');">编辑</a>', row.orgId);
					<%}%>
					<%if (securityUtil.havePermission("/maintain/sysOrganization!delete")) {%>
						str += sys.formatString('<a href="javascript:void(0)" class="btn4" onclick="removeFun(\'{0}\');">删除</a>', row.orgId);
					<%}%>
					return str;
				}
			} ] ],
			toolbar : '#toolbar',
			onBeforeLoad : function(row, param) {
				parent.$.messager.progress({
					text : '数据加载中....'
				});
				if(row) {
					$(this).treegrid('options').url = sys.contextPath + '/maintain/sysOrganization!getChildrenTreeGrid.cxl?id=' + row.orgId;
				}
			},
			onLoadSuccess : function(row, data) {
				$('.btn1').linkbutton({text:'查看', plain:true, height:18, iconCls:'ext-icon-search'});
				$('.btn2').linkbutton({text:'编辑', plain:true, height:18, iconCls:'ext-icon-pencil'});
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
				<%if (securityUtil.havePermission("/maintain/sysOrganization!save")) {%>
				<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addFun();">添加</a></td>
				<%}%>
				<td><div class="datagrid-btn-separator"></div></td>
				<td><a onclick="redoFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'ext-icon-resultset_next'">展开</a><a onclick="undoFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'ext-icon-resultset_previous'">折叠</a></td>
				<td><div class="datagrid-btn-separator"></div></td>
				<td><a onclick="grid.treegrid({url : sys.contextPath + '/maintain/sysOrganization!getChildrenTreeGrid.cxl?'});" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'ext-icon-arrow_refresh'">刷新</a></td>
			</tr>
		</table>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>