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
	$(function() {
		grid = $('#grid')
				.datagrid(
						{
							title : '',
							url : sys.contextPath + '/car/carmaintenancesearch!grid.cxl',
							queryParams: {
								"QUERY_t#dictIdFlag_S_EQ":'<%=WebMsgUtil.YOUXIAO%>'
							},
							striped : true,
							idField : 'mainId',
							rownumbers : true,
							pagination : true,
							sortName : 'timeCreate',
							sortOrder : 'desc',
							columns : [ [
									{
										width : '150',
										title : '车牌',
										sortable : true,
										field : 'carNo'
									},
									{
										width : '150',
										title : '管理单位',
										field : 'orgName',
										sortable : true
									},
									{
										width : '150',
										title : '入厂里程',
										sortable : true,
										field : 'mainInMileage'
									},
									{
										width : '150',
										title : '保养里程',
										sortable : true,
										field : 'mainMileage'
									},
									{
										width : '150',
										title : '下次保养里程',
										sortable : true,
										field : 'mainNextMileage'
									},
									{
										width : '150',
										title : '保养时间',
										sortable : true,
										field : 'mainTime',
										hidden : true
									},
									{
										width : '150',
										title : '下次保养时间',
										sortable : true,
										field : 'mainNextTime',
										formatter: function (value, row) {
                                          if (value!=undefined) {
                                           return value.substr(0, 10);
                                          }
                                         }
									},
									{
										width : '150',
										title : '保养花费',
										sortable : true,
										field : 'mainMoney'
									},
									{
										width : '150',
										title : '保养内容',
										sortable : true,
										field : 'mainCondition'
									},{
										width : '150',
										title : '保养厂家',
										sortable : true,
										field : 'dictName'
									},
									{
										width : '150',
										title : '经办人',
										sortable : true,
										field : 'userName'
									},
									{
										width : '150',
										title : '登记时间',
										field : 'timeCreate',
										sortable : true
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
						
						
						$('#orgId').combotree({
		editable:false,	
		panelWidth:170,		
		cascadeCheck : false,
		idField:'id',
		textField:'text',	
		parentField:'pid',
		url:sys.contextPath + '/maintain/sysOrganization!doNotNeedSecurity_comboTreeCurrentSession.cxl?ids=',
		onBeforeLoad : function(row, param) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			if(row) {
				$('#orgId').combotree('tree').tree('options').url = sys.contextPath + '/maintain/sysOrganization!doNotNeedSecurity_comboTree.cxl?id=' + row.id;
			}
		},
		onLoadSuccess : function(row, data) {
			parent.$.messager.progress('close');
		},
		onCheck:function(node,checked){ 		
				 if(checked){
					var nodes = $('#orgId').combotree('getChecked');
					if(nodes.length > 1){
						for (var i = 0; i < nodes.length; i++) {
							
							$('#orgId').combotree('uncheck',nodes[i].target);
						}
						$('#orgId').combotree('check',node.target);
					}		
				}	
		    }
	}); 

	});
      function searchCar(){
	    var starttime = $('#startTime').datebox('getValue');
		var endtime = $('#endTime').datebox('getValue');		
		if (starttime == "" && endtime != "") {
			alert("不能只选择结束时间！");
			return false;
		}
		if (starttime != "" && endtime != "" && starttime > endtime) {
			alert("开始时间不能大于结束时间！");
			return false;
		}
		grid.datagrid('load',sys.serializeObject($('#searchForm')));
		
	}
	 function exportFile(){
			document.searchForm.action=sys.contextPath +'/car/carmaintenancesearch!doNotNeedSecurity_exports.cxl';
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
		 <form id="searchForm"name="searchForm" method="post" target="report">
			<table>
					<tr>
								<td>管理单位</td>
						      <td><input id="orgId" name="orgIdManager" /></td> 
								<td>创建时间</td>
								<td><input id="startTime" name=startTime  class="easyui-datebox textbox" data-options="editable:false" />-<input id="endTime"name=endTime 
								class="easyui-datebox textbox" data-options="editable:false" /></td>
								<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="searchCar()">查询</a>
								<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');grid.datagrid('load',{});">清空条件</a></td>
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
