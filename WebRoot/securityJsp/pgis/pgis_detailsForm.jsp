<!-- http://localhost:8080/CarManage/securityJsp/accident/AddAccident.jsp -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.framework.util.SecurityUtil"%>
<%@page import="com.framework.util.WebMsgUtil"%>
<%
	String contextPath = request.getContextPath();
	SecurityUtil securityUtil = new SecurityUtil(session);
	
	String id = request.getParameter("id");
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>

</head>
<body>

<style type="text/css">
	.table tr{
		height: 30px;
	}
	
</style>

<form method="post">
	
	<table class="table" style="width: 100%">
		<tr>
			<td class="tdName">车牌号:</td>
			<td>
					<input id="carNo" name="carNo" />
			</td>
			
			<td class="tdName">驾驶员:</td>
			<td>
				<input id="pilotname" name="pilotname" />
			</td>
			
		</tr>
		<tr>
			<td class="tdName">驾驶员手机号:</td>
			<td>
				<input id="stafPhonePilot" name="stafPhonePilot" />
			</td>
			
			<td class="tdName">车辆识别号:</td>
			<td>
				<input id="carIdentifyNo" name="carIdentifyNo"/>
			</td>
		</tr>
		
		<tr>
			<td class="tdName">种类:</td>
			<td>
				<input name="carKind" id="carKind"/>
			</td>
			
			<td class="tdName">用途:</td>
			<td>
				<input id="usingKind" name="usingKind"/>
			</td>
		</tr>
		<tr>
			<td class="tdName">车型:</td>
			<td>
				<input id="modelName" name="modelName"/>
			</td>
			
			<td class="tdName">单位:</td>
			<td>
				<input id="orgName" name="orgName"/>
			</td>
		</tr>
		
		
	</table>

</form>
<script type="text/javascript">
$(function () {
	var id = "<%=id%>";
	
    parent.$.messager.progress({
		text : '数据加载中....'
	});
    
	 if(id != "null"){
	    	$.post(sys.contextPath + '/pgis/carManage!doNotNeedSecurity_getcarInfo.cxl', {
	    			carId : id
			}, function(result) {
				if (result) {
					$('form').form('load', {
						'carNo' : result.carNo,
						'pilotname' : result.pilotname,
						'stafPhonePilot' : result.stafPhonePilot,
						'carIdentifyNo' : result.carIdentifyNo,
						'carKind' : result.carKind,
						'usingKind' : result.usingKind,
						'modelName' : result.modelName,
						'orgName' : result.orgName,
					});
				}
			}, 'json');

	    }
	 
	 parent.$.messager.progress('close');
});



</script>



</body>
</html>