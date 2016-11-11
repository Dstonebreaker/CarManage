<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.framework.util.SecurityUtil"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String contextPath = request.getContextPath();
SecurityUtil securityUtil = new SecurityUtil(session);
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>公务用车月统计表</title>
<jsp:include page="../../inc.jsp"></jsp:include>

<script type="text/javascript"> 
$(function() {
	
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
 function exportFile(){

     var startTime = $('#startTime').datebox('getValue');
     var endTime = $('#endTime').datebox('getValue');
     var orgID= $('#orgId').combotree('getValue');
     var searchType= $('#searchType').combobox('getValue');
     if(orgID==''||searchType==''){
   	  parent.$.messager.alert('提示', '请填选择使用单位和是否查询所有下属单位!', 'info');
    	 return false;
   	  
     }
     if(endTime==''||startTime==''){
    	 parent.$.messager.alert('提示', '请填写开始和结束时间!', 'info');
    	 return false;
     }
     if (endTime != "" && startTime != "" && startTime >endTime) {
         parent.$.messager.alert('提示', '结束时间不能早于开始时间!', 'info');
         return false;
     }
        document.searchForm.action=sys.contextPath + '/export/TypeMonthSummary!doNotNeedSecurity_export.cxl';
		document.searchForm.submit();
		
	};
 function searchData(){

     var startTime = $('#startTime').datebox('getValue');
     var endTime = $('#endTime').datebox('getValue');
     var orgID= $('#orgId').combotree('getValue');
     var searchType= $('#searchType').combobox('getValue');
     if(orgID==''||searchType==''){
   	  parent.$.messager.alert('提示', '请填选择使用单位和是否查询所有下属单位!', 'info');
    	 return false;
   	  
     }
     if(endTime==''||startTime==''){
    	 parent.$.messager.alert('提示', '请填写开始和结束时间!', 'info');
    	 return false;
     }
     if (endTime != "" && startTime != "" && startTime >endTime) {
         parent.$.messager.alert('提示', '结束时间不能早于开始时间!', 'info');
         return false;
     }
   	    document.searchForm.action='<%=basePath%>car/typeMonthSummaryRecord.cxl';
		document.searchForm.submit();
	};
    function doClear() {
        $("#searchForm").form('clear');
    }
</script>
  </head>

  <body class="easyui-layout" data-options="fit:true,border:false">
	<div region="north"  data-options="border:true" class="datagrid-toolbar">
		<table style="width:100%">
			<tr>
				<td>
				 <form id="searchForm" name="searchForm" method="post" target="monthReport">				
					<table>
						<tr>
						     <td>管理单位</td>
			                <td><input id="orgId" name="orgId" /></td> 
			             <td>是否查询所有下属单位</td>
			                <td><input id="searchType" name="searchType" class="easyui-combobox textbox"  data-options="panelHeight:'auto',editable:false,
								valueField:'value',
								textField:'text',						
								data : [{
									value: '0',
									text: '只查询本单位'					
								},{
									value: '1',
									text: '查询本单位及下属子机构'
								}]	" /></td>               
			                <td>开始时间：</td>
			                <td><input id="startTime" name="startTime" class="easyui-datebox textbox"
			                           data-options="editable:false"/></td>
			                <td>结束时间：</td>
			                <td><input id="endTime" name="endTime" class="easyui-datebox textbox" data-options="editable:false"/>
			                </td>
			               <td colspan="3"><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="searchData()">查询</a>
							<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'ext-icon-zoom_out'" onclick="doClear()">清空条件</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="exportFile();">导出</a></td>
           				 </tr>
					</table>
					</form>
				</td>
			</tr>
		</table>
	</div>	
		<div id="content" data-options="region:'center',fit:true,border:false" style="height:100%;margin:0px;padding:0px">
		
			<iframe name="monthReport" width="100%" height="100%" scrolling="auto" src="" style="border:0px"></iframe>
		
	</div>

</body>
</html>
