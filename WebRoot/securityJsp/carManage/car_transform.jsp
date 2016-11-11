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
			url : sys.contextPath + '/securityJsp/carManage/car_transform_form.jsp',
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
			url : sys.contextPath + '/securityJsp/carManage/car_transform_detail.jsp?id=' + id
		});
	};
	var editFun = function(id) {
		var dialog = parent.sys.modalDialog({
			width:800,
			height:500,
			title : '编辑车型信息',
			url : sys.contextPath + '/securityJsp/carManage/car_transform_form.jsp?id=' + id,
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
				$.post(sys.contextPath + '/car/carTransform!delete.cxl', {
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
			url : sys.contextPath + '/car/carTransform!grid.cxl?',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			idField : 'transformId',
			pageSize : 30,
			pageList : [ 10, 20, 30, 40, 50 ],
			sortOrder : 'asc',
			frozenColumns : [ [
			    {
				width : '100',
				title : '车改编号',
				field : 'transformNo',
				sortable : true                    
			    },{
				width : '100',
				title : '车牌号',
				field : 'carNo',
				sortable : true
			} ] ],
			columns : [ [ {
				
				title : '车改前单位',
				field : 'beforeOrgName',
				sortable : true
			},{
				
				title : '车改后单位',
				field : 'afterOrgName',
				sortable : true
			},
			{
				
				title : '处置方式',
				field : 'disposalTypeName',
				sortable : true
		
			},{
				
				title : '处理方式',
				field : 'handleTypeName',
				sortable : true				
			},{
				
				title : '创建人',
				field : 'createUserName',
				sortable : true
			},{
				
				title : '修改人',
				field : 'updateUserName',
				sortable : true
			},{
				title : '操作',
				field : 'action',
				width : '200',
				formatter : function(value, row) {				
					var str = '';	
					<%if (securityUtil.havePermission("/car/carTransform!getById")) {%>
						str += sys.formatString('<a href="javascript:void(0)" class="btn1" onclick="showFun(\'{0}\');">查看</a>', row.transformId);<%}%>
					<%if (securityUtil.havePermission("/car/carTransform!update")) {%>
						str += sys.formatString('<a href="javascript:void(0)" class="btn2" onclick="editFun(\'{0}\');">编辑</a>', row.transformId);<%}%>
					<%if (securityUtil.havePermission("/car/carTransform!delete")) {%>
						str += sys.formatString('<a href="javascript:void(0)" class="btn3" onclick="removeFun(\'{0}\');">删除</a>', row.transformId);<%}%>
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
					<td>车牌号:</td>
					<td><input name="QUERY_t#carNo_S_EQ" 
					class="easyui-validatebox textbox"
				/></td>			
					<td>车改前单位:</td>
					<td><input name="QUERY_t#transformBeforeOrgId_S_EQ" class="easyui-combobox textbox"
						data-options="panelHeight:'200',editable:false,
						  valueField: 'orgIdManager',
                			textField: 'orgName',
						  url: sys.contextPath + '/maintain/sysOrganization!doNotNeedSecurity_getOrg.cxl?userId=1' 
						" /></td>				    
				    <td>车改后单位:</td>
					<td><input name="QUERY_t#transformAfterOrgId_S_EQ" class="easyui-combobox textbox"
						data-options="panelHeight:'200',editable:false,
						  valueField: 'orgIdManager',
                			textField: 'orgName',
						  url: sys.contextPath + '/maintain/sysOrganization!doNotNeedSecurity_getOrg.cxl?userId=1' 
						" /></td>			    
					<td>处置方式:</td>
					<td><input name="QUERY_t#dictIdDisposalType_S_EQ"  class="easyui-combobox textbox"
						data-options="panelHeight:'200',editable:false,
						valueField: 'dictId',
						textField: 'dictName',
						url: sys.contextPath+'/maintain/dictionaryManage!doNotNeedSecurity_getByClassId.cxl?dictClassId=DisposalType'
						" /></td>				    
					<td>处理方式:</td>
					<td><input  name="QUERY_t#dictIdHandleType_S_EQ" class="easyui-combobox textbox"
						data-options="panelHeight:'200',editable:false,
						valueField: 'dictId',
						textField: 'dictName',
						url: sys.contextPath+'/maintain/dictionaryManage!doNotNeedSecurity_getByClassId.cxl?dictClassId=HandleType'
						" />
					</td>			
					<td><a href="javascript:void(0);" class="easyui-linkbutton"
						data-options="iconCls:'ext-icon-zoom',plain:true"
						onclick="doSerch();">查询</a></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true"  onclick="doClear()">清空条件</a></td>
						<%
					if (securityUtil.havePermission("/car/carTransform!save")) {
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