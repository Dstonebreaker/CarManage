<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.framework.util.SecurityUtil"%>
<%@ page import="com.framework.util.WebMsgUtil"%>
<%
	String path = request.getContextPath();
	SecurityUtil securityUtil = new SecurityUtil(session);
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
    String dictId = request.getParameter("dictId");
    if (dictId == null) {
        dictId = "";
    }
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<head>
<base href="<%=basePath%>">

<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">
    var grid;
    var dictId = "<%=dictId%>";
    var addFun = function() {
        var dictId = "<%=dictId%>";
		var dialog = parent.sys.modalDialog({
			title : '添加变更信息',
			url : sys.contextPath + '/securityJsp/carchange/carchange_form.jsp?id='+dictId,
			buttons : [ {
				text : '添加',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	$(function() {
		grid = $('#grid')
				.datagrid(
						{
							title : '',
							url : sys.contextPath + '/car/change!grid.cxl?dictId='+dictId,
							striped : true,
							idField : 'carId',
							rownumbers : true,
							pagination : true,
							sortName : 'timeCreate',
							sortOrder : 'desc',
							columns : [ [
							 <%if ("CHA0003".equals(dictId)) {%>
									{
										width : '100',
										title : '原车牌号',
										sortable : true,
										field : 'changeCarNoOld'
									},{
										width : '100',
										title : '现车牌号',
										sortable : true,
										field : 'changeCarNoNew'
									},
								<%}%>
								<%if ("CHA0002".equals(dictId)) {%>
							    	{
										width : '100',
										title : '车牌号',
										sortable : true,
										field : 'carNo'
									},
									{
										width : '120',
										title : '原使用单位',
										sortable : true,
										field : 'useOldOrgName'
									},
									{
										width : '120',
										title : '现使用单位',
										sortable : true,
										field : 'useNewOrgName'
									},
								<%}%>
							    <%if ("CHA0001".equals(dictId)) {%>
							        {
										width : '100',
										title : '车牌号',
										sortable : true,
										field : 'carNo'
									},
									{
										width : '120',
										title : '原管理单位',
										sortable : true,
										field : 'manageOldOrgName'
									},
									{
										width : '120',
										title : '现管理单位',
										sortable : true,
										field : 'manageNewOrgName'
									},
								<%}%>
									{
										width : '100',
										title : '原车管员',
										sortable : true,
										field : 'masterOldName'
									},
									{
										width : '100',
										title : '现车管员',
										sortable : true,
										field : 'masterNewName'
									},
									{
										width : '100',
										title : '登记人',
										sortable : true,
										field : 'createName'
									},
									{
										width : '100',
										title : '登记时间',
										sortable : true,
										field : 'timeCreate',
								     	formatter: function (value, row) {
                                           if (value != undefined) {
                                           return value.substr(0, 10);
                                            }
                                        }
									}
									] ],
							toolbar : '#toolbar',
							onBeforeLoad : function(row, param) {
								 parent.$.messager.progress({
									text : '数据加载中....'
								}); 
							},
							onLoadSuccess : function(row, data) {
						
                                 parent.$.messager.progress('close');
							}
						});

	});
      function searchCar(){
		grid.datagrid('load',sys.serializeObject($('#searchForm')));
		
	}
	function doClear() {
			$("#searchForm").form('clear');
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
		 <form id="searchForm"name="searchForm" method="post" target="report">
			<table>
					<tr>
					<%if ("CHA0001".equals(dictId)) {%>
								<td>现管理单位</td>
								<td><input  name="changeManagerNew" class="easyui-combobox textbox "
						data-options="panelHeight:'200',editable:false,
						valueField: 'orgIdManager',
						textField: 'orgName',
						url: sys.contextPath+'/maintain/sysOrganization!doNotNeedSecurity_getOrgManger.cxl'
						" /></td>
						<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="searchCar()">查询</a>
								<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="doClear()">清空条件</a>
								</td>
					<%}%>
					<%if ("CHA0003".equals(dictId)) {%>
								<td>现车牌号</td>
								<td><input class="easyui-validatebox textbox"
						name="QUERY_t#changeCarNoNew_S_LK"
						data-options="validType:['maxLength[15]']" /></td>
						<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="searchCar()">查询</a>
								<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="doClear()">清空条件</a>
								</td>
					<%}%>
				<%if (securityUtil.havePermission("/car/change!save")) {%>
				<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addFun();">添加</a></td>
				<%}%>
				</tr>  
		   </table>
		</form>	
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>
