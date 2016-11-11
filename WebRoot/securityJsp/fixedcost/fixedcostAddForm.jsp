<%@page import="com.system.action.maintain.SysObdCodeAction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.framework.util.SecurityUtil"%>
<%@ page import="com.system.entity.maintain.SessionInfo"%>
<%@ page import="com.framework.util.ConfigUtil"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.net.URLDecoder"%>
<%
	
	String contextPath = request.getContextPath();
	SecurityUtil securityUtil = new SecurityUtil(session);
	//SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
	String str=request.getParameter("carNo");  
	String carNo= URLDecoder.decode(str, "utf-8");
	String carId = request.getParameter("carId");
	String costId = request.getParameter("costId");
	String param = request.getParameter("param");
	if (carNo == null) {
		carNo = "";
	}
	if (carId == null) {
		carId = "";
	}
	if (costId == null) {
		costId = "";
	}
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<form method="post" class="form" id="form">
	 
			<!-- <legend>生命周期信息</legend> -->
			
			<input name="fixedCost.carId" value="<%=carId%>" readonly="readonly" type="hidden">		
			<input id="costId" name="fixedCost.costId" value="<%=costId%>" readonly="readonly" type="hidden">
			
			

			<table class="table" style="width: 100%;">
				<tr>
				<td>车牌号:</td>
				<%-- <td><input name="fixedCost.carNo" value="<%=carNo%>"  class="easyui-textbox" readonly="readonly" ></td> --%>
				<td><input id="fixedCost.carNo" name="fixedCost.carNo" value="<%=carNo%>"  class="easyui-textbox" readonly="readonly" ></td>
				</tr>
				<tr>
					<td>费用类型：</td>
					<td>
					<input name="fixedCost.dictIdcosttype"
							id="dictIdcosttype"
					 		class="easyui-combobox"
							data-options="required:true,
							panelHeight:'auto',
							editable:false,
							valueField:'dictId',
							textField:'dictName',						
							url:sys.contextPath+'/maintain/dictionaryManage!doNotNeedSecurity_getByClassId.cxl?dictClassId=CostType'"/>
					</td>
					
					<td>费用金额：</td>
					<td><input name="fixedCost.cost" id="cost" class="easyui-numberbox"
						data-options="required:true,validType:['intOrFloat','maxLength[8]']" />
					</td>
					
				</tr>
				
				<tr>
					<td>开始日期：</td>
					<td><input name="fixedCost.startTime"	id="startTime" class="easyui-datebox" data-options="required:true,panelHeight:'auto',editable:false" />
					</td>
					
					<td>截止日期：</td>
					<td><input name="fixedCost.overTime" id="overTime"  class="easyui-datebox" data-options="required:true,panelHeight:'auto',editable:false" />
					</td>
					
				</tr>
				
			</table>
	</form>
	
	
  
	
<script type="text/javascript">
$(function() {	
	
var param = '<%=param%>';



if (param == "edit") {
	
	parent.$.messager.progress({
		text : '数据加载中....'
	});
	
	//加载数据
	$.post(sys.contextPath + '/fixedcost!getById.cxl', {
		id : $("#costId").val()
	}, function(result) {
	    
		$('form').form('load', {
			'fixedCost.dictIdcosttype' : result.dictCostId,
			'fixedCost.cost' : result.cost,
			'fixedCost.startTime' : result.startTime,
			'fixedCost.overTime' : result.overTime
		});

		parent.$.messager.progress('close');
	}, 'json');
  }
  
  
 
  
  
  
  
  
}
); 


</script>
</body>
</html>