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
	var addFun = function() {
		var dialog = parent.sys.modalDialog({
			width:800,
			height:500,
			title : '添加处置',
			url : sys.contextPath + '/securityJsp/cartreat/car_treat_form.jsp',
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
			title : '查看处置信息',
			url : sys.contextPath + '/securityJsp/cartreat/car_treat_detail.jsp?id=' + id
		});
	};
	var editFun = function(id) {
		var dialog = parent.sys.modalDialog({
			width:800,
			height:500,
			title : '编辑处置信息',
			url : sys.contextPath + '/securityJsp/cartreat/car_treat_form.jsp?id=' + id,
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
			},
			{
				
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
				width : '300',
				formatter : function(value, row) {
				
					var str = '';	
					if(row.dictIdTreatResult=='<%=WebMsgUtil.TREAT_RESULT_SHZ%>'||row.dictIdTreatResult=='<%=WebMsgUtil.TREAT_RESULT_SPJJ%>'){<%if (securityUtil.havePermission("/car/carManage!getById")) {%>
					str += sys.formatString('<a href="javascript:void(0)" class="btn1" onclick="showFun(\'{0}\');">查看</a>',row.treatId);<%}%>
				<%if (securityUtil.havePermission("/car/treat!update")) {%>			
					str += sys.formatString('<a href="javascript:void(0)" class="btn2" onclick="editFun(\'{0}\');">编辑</a>',row.treatId);<%}%>
				<%if (securityUtil.havePermission("/car/treat!delete")) {%>
					str += sys.formatString('<a href="javascript:void(0)" class="btn3" onclick="removeFun(\'{0}\');" >删除</a>',row.treatId);<%}%>}
					else{
						<%if (securityUtil.havePermission("/car/treat!getById")) {%>
						str += sys.formatString('<a href="javascript:void(0)" class="btn1" onclick="showFun(\'{0}\');">查看</a>',row.treatId);<%}%>
					<%if (securityUtil.havePermission("/car/treat!update")) {%>			
						str += sys.formatString('<a href="javascript:void(0)" class="btn2" onclick="editFun(\'{0}\');" data-options="plain:true,disabled:true">编辑</a>',row.treatId);<%}%>
					<%if (securityUtil.havePermission("/car/treat!delete")) {%>
						str += sys.formatString('<a href="javascript:void(0)" class="btn3" onclick="removeFun(\'{0}\');" data-options="plain:true,disabled:true" >删除</a>',row.treatId);<%}%>
					}
					if(row.dictIdTreatResult=='<%=WebMsgUtil.TREAT_RESULT_CZZ%>'){						
						<%if (securityUtil.havePermission("/car/treat!dealWith")) {%>
						str += sys.formatString('<a href="javascript:void(0)" class="btn4" onclick="dealwith(\'{0}\');">处置完结</a>', row.treatId);<%}%>
					}else{
						<%if (securityUtil.havePermission("/car/treat!dealWith")) {%>
						str += sys.formatString('<a href="javascript:void(0)" class="btn4" onclick="dealwith(\'{0}\');" data-options="plain:true,disabled:true" >处置完结</a>', row.treatId);<%}%>
						
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
				$('.btn2').linkbutton({text:'编辑', plain:true, height:18, iconCls:'ext-icon-pencil'});				
				$('.btn3').linkbutton({text:'删除', plain:true, height:18, iconCls:'ext-icon-delete'});
				$('.btn4').linkbutton({text:'处置完结', plain:true, height:18, iconCls:'ext-icon-joystick'});	
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
						<%
					if (securityUtil.havePermission("/car/treat!save")) {
				%>
				<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addFun();">申请</a></td>
				<%
					}
				%>
					<td><a href="javascript:void(0);" class="easyui-linkbutton"
									data-options="iconCls:'ext-icon-page_excel',plain:true"
									onclick="exportFile()">导出</a>
							   </td>
				</tr>
				
			</table>
		</form>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>