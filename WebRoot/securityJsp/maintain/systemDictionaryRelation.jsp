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
			url : sys.contextPath + '/securityJsp/maintain/systemDictionaryRelationForm.jsp?',
			buttons : [ {
				text : '添加',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	var showFun = function(dictId,dictIdRelate) {
		var dialog = parent.sys.modalDialog({
			title : '查看品牌车系信息',
			url : sys.contextPath + '/securityJsp/maintain/systemDictionaryRelationForm.jsp?id='+dictId+'&ids='+ dictIdRelate
		});
	};
	var editFun = function(dictId,dictIdRelate) {
		var dialog = parent.sys.modalDialog({
			title : '编辑用户信息',
			url : sys.contextPath + '/securityJsp/maintain/systemDictionaryRelationForm.jsp?id='+dictId+'&ids='+ dictIdRelate,
			buttons : [ {
				text : '编辑',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	var removeFun = function(dictId,dictIdRelate) {
		parent.$.messager.confirm('询问', '您确定要删除此记录？', function(r) {
			if (r) {
				$.post(sys.contextPath + '/maintain/dictionaryRelation!delete.cxl', {
					id : dictId,
					ids :dictIdRelate
				}, function() {
					grid.datagrid('reload');
				}, 'json');
			}
		});
	};
	
	$(function() {
		treeObj = $('#BrandTree').tree({
			url : sys.contextPath + '/maintain/dictionaryManage!doNotNeedSecurity_getCarBrand.cxl?id=',
			parentField : 'pid',
			cascadeCheck : false,
			formatter : function(node) {
				return node.text;
			},
			onSelect : function(node) {
				grid.datagrid('load', {id:node.id});
			}
		});
		grid = $('#grid').datagrid(
     	{
			title : '',
			url : sys.contextPath + '/maintain/dictionaryManage!doNotNeedSecurity_getCarSeriesById.cxl?',
			rownumbers : true,
			singleSelect : true,
			idField : 'id',
			pageSize : 20,
			pageList : [ 10, 20, 30, 40, 50],
			frozenColumns : [ [ {
				width : '100',
				title : '车系',
				field : 'dictName',
				sortable : true
			} ] ],	
	     	columns : [ [ 
			{
				width : '150',
				title : '车系Id',
				field : 'dictIdRelate',
				hidden : true
			}, 
			 {
				title : '操作',
				field : 'action',
				width : '200',
				formatter : function(value, row) {
					var str = '';
					<%if (securityUtil.havePermission("/maintain/dictionaryRelation!getById")) {%>
					     if(row.dictId!=""){
						str += sys.formatString('<a href="javascript:void(0)" class="btn1" onclick="showFun(\'{0}\',\'{1}\');">查看</a>', row.dictId,row.dictIdRelate);
						}
					<%}%>
					<%if (securityUtil.havePermission("/maintain/dictionaryRelation!update")) {%>
					 if(row.dictId!=""){	    				
						str += sys.formatString('<a href="javascript:void(0)" class="btn2" onclick="editFun(\'{0}\',\'{1}\');">编辑</a>',row.dictId,row.dictIdRelate);
						}				
					<%}%>
					<%if (securityUtil.havePermission("/maintain/dictionaryRelation!delete")) {%>
					 if(row.dictId!=""){	
						str += sys.formatString('<a href="javascript:void(0)" class="btn3" onclick="removeFun(\'{0}\',\'{1}\');">删除</a>', row.dictId,row.dictIdRelate);
					 }	
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
				$('.btn3').linkbutton({text:'删除', plain:true, height:18, iconCls:'ext-icon-delete'});
				parent.$.messager.progress('close');
			}
		});
	});
</script>
</head>


<body class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'west'" title="品牌列表" style="width:220px;">
		<ul id='BrandTree'></ul>
	</div>
	<div data-options="region:'center',fit:true,border:false">
	 <table>
			<tr>
				<%if(securityUtil.havePermission("/maintain/dictionaryRelation!save")) {%>
				<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addFun();">添加</a></td>
				<%}%>
			</tr>
    </table>
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>