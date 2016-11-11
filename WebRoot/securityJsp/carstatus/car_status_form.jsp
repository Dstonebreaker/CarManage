<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<%
	String id = request.getParameter("id");
	if (id == null) {
		id = "";
	}
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">
	var submitForm = function($dialog, $grid, $pjq) {
		if ($('form').form('validate')) {			
			var url;
			if ($(':input[name="carStatus.carId"]').val().length > 0) {
				
			//	var urltemp='http://192.168.0.201/outService/gps/updateObdNoAndCarId.do';
			
				url = sys.contextPath + '/car/carStatusEdit!update.cxl';	
			
				$.post(url, sys.serializeObject($('form')), function(result) {
				if (result.success) {	
/* 					var obdNo=$("#carStatus_simNo").val();
					var carId=$("#carStatus_carId").val();								
					$.post(urltemp,{
						code:'6c7566ed',
						obdNo:obdNo,
						carId:carId
					},function(result){
						$grid.datagrid('reload');
						$dialog.dialog('destroy');
					},'json');
				
				 */					
						$grid.datagrid('reload');
						$dialog.dialog('destroy');
					
				} else {
					$pjq.messager.alert('提示', result.msg, 'error');
				}
			}, 'json');
			
				
				
				
				
		}
		}
	};
	$(function() {          
				
		if ($(':input[name="carStatus.carId"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(sys.contextPath + '/car/carStatusEdit!getById.cxl', {
				id : $(':input[name="carStatus.carId"]').val()
			}, function(result) {			
				if (result.carId != undefined) {
					$('form').form('load', {
						'carStatus.carId' : result.carId,
						'carStatus.simNo' : result.simNo,
						'carStatus.keyNo' : result.keyNo,
						'carStatus.obdNo' : result.obdNo
						
					});
				}		
				parent.$.messager.progress('close');
			}, 'json');
		}
	});
</script>
<style>
.textbox {
	height: 20px;
	width: 200px;
}
</style>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<form method="post" class="form">	
		<fieldset>
			<legend>车辆状态信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<td>编号:</td>
					<td><input id="carStatus_carId" name="carStatus.carId"  class="easyui-validatebox textbox" value="<%=id%>" readonly="readonly" /></td>
					<td>SIM卡号:</td>
					<td><input id ="carStatus_simNo" name = "carStatus.simNo" data-options="required:true" class="easyui-validatebox textbox"  /></td>
					
				</tr>
				<tr>
					<td>key号:</td>
					<td><input name = "carStatus.keyNo"  data-options="required:true" class="easyui-validatebox textbox"  /></td>
					
					<td>OBD号:</td>
					<td><input name = "carStatus.obdNo" data-options="required:true" class="easyui-validatebox textbox" /></td>
				</tr>
									
			</table>
		</fieldset>
	</form>
</body>
</html>