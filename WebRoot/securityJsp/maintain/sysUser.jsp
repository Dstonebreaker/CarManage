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
	var treeObj;
	var addFun = function() {
		var dialog = parent.sys.modalDialog({
			title : '添加用户信息',
			url : sys.contextPath + '/securityJsp/maintain/sysUserForm.jsp',
			buttons : [ {
				text : '添加',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	var showFun = function(userId) {
		var dialog = parent.sys.modalDialog({
			title : '查看用户信息',
			url : sys.contextPath + '/securityJsp/maintain/sysUserForm.jsp?id=' + userId
		});
	};
	var editFun = function(userId) {
		var dialog = parent.sys.modalDialog({
			title : '编辑用户信息',
			url : sys.contextPath + '/securityJsp/maintain/sysUserForm.jsp?id=' + userId,
			buttons : [ {
				text : '编辑',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	var removeFun = function(userId) {
		parent.$.messager.confirm('询问', '您确定要删除此记录？', function(r) {
			if (r) {
				$.post(sys.contextPath + '/maintain/sysUser!delete.cxl', {
					id : userId
				}, function() {
					grid.datagrid('reload');
				}, 'json');
			}
		});
	};
	var resetPwd = function(userId) {
		parent.$.messager.confirm('询问', '您确定要重置此用户的密码？', function(r) {
			if (r) {
				$.post(sys.contextPath + '/maintain/sysUser!resetuserPwd.cxl', {
					id : userId
				}, function(result) {
					$.messager.alert('提示', result.msg, 'info');
				}, 'json');
			}
		});
	};
	var grantRoleFun = function(userId) {
		var dialog = parent.sys.modalDialog({
			title : '修改角色',
			url : sys.contextPath + '/securityJsp/maintain/sysUserRoleGrant.jsp?id=' + userId,
			buttons : [ {
				text : '修改',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	var grantOrganizationFun = function(userId) {
		var dialog = parent.sys.modalDialog({
			title : '修改机构',
			url : sys.contextPath + '/securityJsp/maintain/sysUserOrganizationGrant.jsp?id=' + userId,
			buttons : [ {
				text : '修改',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	$(function() {
		treeObj = $('#orgTree').tree({
			url : sys.contextPath + '/maintain/sysOrganization!doNotNeedSecurity_getSyorganizationsTree.cxl?id=',
			parentField : 'pid',
			cascadeCheck : false,
			formatter : function(node) {
				return node.text;
			},
			onLoadSuccess : function(node, data) {	
				parent.$.messager.progress('close');
			},
			onBeforeExpand : function(node, param) {
				$(this).tree('options').url = sys.contextPath + '/maintain/sysOrganization!doNotNeedSecurity_getSyorganizationsTree.cxl?id=' + node.id;
				parent.$.messager.progress({
					text : '数据加载中....'
				});
			},
			onSelect : function(node) {
				grid.datagrid('load', {code:node.id});
			}
		});
		grid = $('#grid').datagrid({
			title : '',
			url : sys.contextPath + '/maintain/sysUser!doNotNeedSecurity_getSyUserGrid.cxl',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			idField : 'id',
			sortName : 'timeCreate',
			sortOrder : 'desc',
			pageSize : 20,
			pageList : [ 10, 20, 30, 40, 50],
			frozenColumns : [ [ {
				width : '10%',
				title : '警员号',
				field : 'userLogin',
				sortable : true
			}, {
				width : '10%',
				title : '姓名',
				field : 'userName',
				sortable : true
			} ] ],
			
			columns : [ [ {
				width : '10%',
				title : '创建时间',
				field : 'timeCreate',
				sortable : true
			},{
				width : '10%',
				title : '联系电话',
				field : 'userPhone',
				sortable : true
			},{
				width : '5%',
				title : '准驾车型',
				field : 'dictName',
				sortable : true
			}
			,{
				width : '10%',
				title : '修改时间',
				field : 'timeUpdate',
				sortable : true
			}, {
				title : '操作',
				field : 'action',
				width : '60%',
				formatter : function(value, row) {
					var str = '';
					<%if (securityUtil.havePermission("/maintain/sysUser!getById")) {%>
						str += sys.formatString('<a href="javascript:void(0)" class="btn1" onclick="showFun(\'{0}\');">查看</a>', row.userId);
					<%}%>
					<%if (securityUtil.havePermission("/maintain/sysUser!update")) {%>
						str += sys.formatString('<a href="javascript:void(0)" class="btn2" onclick="editFun(\'{0}\');">编辑</a>', row.userId);
					<%}%>
					<%if (securityUtil.havePermission("/maintain/sysUser!resetuserPwd")) {%>
						str += sys.formatString('<a href="javascript:void(0)" class="btn3" onclick="resetPwd(\'{0}\');">重置密码</a>', row.userId);
					<%}%>
					<%if (securityUtil.havePermission("/maintain/sysUser!grantRole")) {%>
						str += sys.formatString('<a href="javascript:void(0)" class="btn4" onclick="grantRoleFun(\'{0}\');">用户角色</a>', row.userId);
					<%}%>
					<%if (securityUtil.havePermission("/maintain/sysUser!grantOrganization")) {%>
						str += sys.formatString('<a href="javascript:void(0)" class="btn5" onclick="grantOrganizationFun(\'{0}\');">所属单位</a>', row.userId);
					<%}%>						
					<%if (securityUtil.havePermission("/maintain/sysUser!delete")) {%>
						str += sys.formatString('<a href="javascript:void(0)" class="btn6" onclick="removeFun(\'{0}\');">删除</a>', row.userId);
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
				$('.btn3').linkbutton({text:'重置密码', plain:true, height:18, iconCls:'ext-icon-cog'});
				$('.btn4').linkbutton({text:'用户角色', plain:true, height:18, iconCls:'ext-icon-user'});
				$('.btn5').linkbutton({text:'所属单位', plain:true, height:18, iconCls:'ext-icon-group'});
				$('.btn6').linkbutton({text:'删除', plain:true, height:18, iconCls:'ext-icon-delete'});
				parent.$.messager.progress('close');
			}
		});
	});
	function doClear() {
		$("#searchForm").form('clear');
	}
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
								<td>警员号</td>
								<td><input name="QUERY_t#userLogin_S_LK" style="width: 80px;" /></td>
								<td>创建时间</td>
								<td><input name="QUERY_t#timeCreate_D_GE" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 120px;" />-<input name="QUERY_t#timeCreate_D_LE" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 120px;" /></td>
								<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="grid.datagrid('load',sys.serializeObject($('#searchForm')));">查询</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="doClear()">清空条件</a></td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
			<tr>
				<td>
					<table>
						<tr>
							<%if (securityUtil.havePermission("/maintain/sysUser!save")) {%>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addFun();">添加</a></td>
							<%}%>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	<div data-options="region:'west'" title="单位列表" style="width:220px;">
		<ul id='orgTree'></ul>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>