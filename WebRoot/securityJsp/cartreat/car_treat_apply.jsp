<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.framework.util.SecurityUtil"%>
<%@ page import="com.framework.util.WebMsgUtil"%>
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
	var showFun = function(id) {
		var dialog = parent.sys.modalDialog({
			width:800,
			height:500,
			title : '查看车型信息',
			url : sys.contextPath + '/securityJsp/cartreat/car_treat_apply_detail.jsp?id=' + id
		});
	};
	var apply = function(id) {
		var dialog = parent.sys.modalDialog({
			width:800,
			height:500,
			title : '编辑车型信息',
			url : sys.contextPath + '/securityJsp/cartreat/car_treat_apply_form.jsp?id=' + id,
			buttons : [ {
				text : '提交',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	var reApply= function(id) {
		var dialog = parent.sys.modalDialog({
			width:800,
			height:500,
			title : '编辑车型信息',
			url : sys.contextPath + '/securityJsp/cartreat/car_treat_apply_form.jsp?id=' + id,
			buttons : [ {
				text : '提交',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	var dealwith= function(id){
		parent.$.messager.confirm('询问', '您确定该车辆处置完结？', function(r) {
			if (r) {
				$.post(sys.contextPath + '/car/treat!dealWith.cxl', {
					id : id
				}, function(result) {
					parent.$.messager.progress('close');
					if (result.success) {
						grid.datagrid('reload');
						parent.$.messager.alert('提示', '处置完结', 'info')
					} else {
						parent.$.messager.alert('提示', '处置失败', 'error');
					}
				}, 'json');
			}
		});
		
	};

	var removeFun = function(id) {
		parent.$.messager.confirm('询问', '您确定要删除此记录？', function(r) {
			if (r) {
				$.post(sys.contextPath + '/car/treat!delete.cxl', {
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
			url : sys.contextPath + '/car/treat!grid.cxl?',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			idField : 'treatId',
			pageSize : 30,
			sortOrder : 'asc',
			frozenColumns : [ [ {
				width : '100',
				title : '申请单位',
				field : 'orgName',
				sortable : true
			} ] ],
			columns : [ [ 
			{
				
				title : '申请单编号',
				field : 'treatNo',
				sortable : true
			},{
				
				title : '申请时间',
				field : 'timeCreate',
				sortable : true,
		        formatter: function (value, row) {
                    if (value!=undefined) {
                        return value.substr(0, 10);
                    }
                }
			},{
				
				title : '车牌号',
				field : 'carNo',
				sortable : true
			},{
				
				title : '车型',
				field : 'modelName',
				sortable : true	
			},
			{
				
				title : '申请人',
				field : 'creatUserName',
				sortable : true
		
			},{
				
				title : '车辆识别号',
				field : 'carIdentifyNo',
				sortable : true				
			},{
				
				title : '发动机号',
				field : 'carEngineNo',
				sortable : true
			},{
				
				title : '购车日期',
				field : 'carRegisterDate',
				sortable : true,
		        formatter: function (value, row) {
                    if (value!=undefined) {
                        return value.substr(0, 10);
                    }
                }
			},{
				
				title : '资产金额',
				field : 'carAssetsMoney',
				sortable : true
			},					
			{
				
				title : '处置方式',
				field : 'modeName',
				sortable : true
			},{
				
				title : '处置时间',
				field : 'treatTime',
				sortable : true,
		        formatter: function (value, row) {
                    if (value!=undefined) {
                        return value.substr(0, 10);
                    }
                }
			},
			{
				
				title : '处置结果',
				field : 'resultName',
				sortable : true
			},{
				
				title : '处置原因',
				field : 'treatReason',
				sortable : true
			},{
				title : '操作',
				field : 'action',
				width : '200',
				formatter : function(value, row) {				
					var str = '';	
					<%if (securityUtil.havePermission("/car/treat!getById")) {%>
						str += sys.formatString('<a href="javascript:void(0)" class="btn1" onclick="showFun(\'{0}\');">查看</a>', row.treatId);<%}%>				
					if(row.dictIdTreatResult=='<%=WebMsgUtil.TREAT_RESULT_SHZ%>'){
						
						<%if (securityUtil.havePermission("/car/treat!apply")) {%>
						str += sys.formatString('<a href="javascript:void(0)" class="btn2" onclick="apply(\'{0}\');">审批</a>', row.treatId);<%}%>
					}else{
						<%if (securityUtil.havePermission("/car/treat!apply")) {%>
						str += sys.formatString('<a href="javascript:void(0)" class="btn2" onclick="apply(\'{0}\');" data-options="plain:true,disabled:true" >审批</a>', row.treatId);<%}%>
					}
					
					if(row.dictIdTreatResult=='<%=WebMsgUtil.TREAT_RESULT_SPJJ%>'){
						
						<%if (securityUtil.havePermission("/car/treat!apply")) {%>
						str += sys.formatString('<a href="javascript:void(0)" class="btn3" onclick="reApply(\'{0}\');" >重新审批</a>', row.treatId);<%}%>
					}else{
						<%if (securityUtil.havePermission("/car/treat!apply")) {%>
						str += sys.formatString('<a href="javascript:void(0)" class="btn3" onclick="reApply(\'{0}\');" data-options="plain:true,disabled:true" >重新审批</a>', row.treatId);<%}%>
					
					}
						
			
						
						
					
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
				$('.btn2').linkbutton({text:'审批', plain:true, height:18, iconCls:'ext-icon-key_add'});	
				$('.btn3').linkbutton({text:'重新审批', plain:true, height:18, iconCls:'ext-icon-key_go'});	
			
				parent.$.messager.progress('close');
			}
		});
	});
	 var doSerch= function(){			
			grid.datagrid('load',sys.serializeObject($('#searchForm')));
		};
		 function exportFile(){
				document.searchForm.action=sys.contextPath +'/car/treat!doNotNeedSecurity_exports.cxl';
			    document.searchForm.submit();
		}
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
		<form id="searchForm" name="searchForm" >
			<table>
				<tr>
					<td>处置方式</td>
					<td><input 
						name="QUERY_t#dictIdTreatMode_S_LK" class="easyui-combobox textbox"
						data-options="panelHeight:'200',editable:false,
						valueField: 'dictId',
						textField: 'dictName',
						url: sys.contextPath+'/maintain/dictionaryManage!doNotNeedSecurity_getByClassId.cxl?dictClassId=TreatMode'
						" /></td>
					<td>处置结果</td>
					<td><input
						name="QUERY_t#dictIdTreatResult_S_LK"
					    class="easyui-combobox textbox"
						data-options="panelHeight:'200',editable:false,
						valueField: 'dictId',
						textField: 'dictName',
						url: sys.contextPath+'/maintain/dictionaryManage!doNotNeedSecurity_getByClassId.cxl?dictClassId=TreatResult'
						" /></td>
					<td>车牌号</td>
					<td><input  name="QUERY_t#carNo_S_LK"
						class="easyui-validatebox textbox"
						data-options="validType:['maxLength[15]']"/>
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