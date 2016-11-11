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
	$(function() {          
				
		if ($(':input[name="carInfo.obdNo"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(sys.contextPath + '/car/carInfoLast!getById.cxl', {
				id : $(':input[name="carInfo.obdNo"]').val()
			}, function(result) {
				
				if (result.obdNo != undefined) {
					$('form').form('load', {
						'carInfo.obdNo' : result.obdNo,
						'carInfo.carNo' : result.carNo,
						'carInfo.orgName' : result.orgName,
						'carInfo.obdMileage' : result.obdMileage,//里程
						'carInfo.obdOil' : result.obdOil,//剩余油量
						'carInfo.obdUsedOil' : result.obdUsedOil,//历史总油耗
						'carInfo.obdSpeed' : result.obdSpeed,//车速
						'carInfo.obdCoolant' : result.obdCoolant,   //水温						
						'carInfo.obdAirDamper' : result.obdAirDamper,//节气门开度 
						'carInfo.obdAirFlow' : result.obdAirFlow,//进气流量
						'carInfo.obdAirTemperature' : result.obdAirTemperature,//进气温度
						'carInfo.obdAirPressure' : result.obdAirPressure,//进气压力
						'carInfo.obdBattery' : result.obdBattery,//电瓶电压
						'carInfo.obdAccident' : result.obdAccident,//故障码个数
						'carInfo.carIdentifyNo' : result.carIdentifyNo, //车架号
						'carInfo.obdDriverDuration' : result.obdDriverDuration //历史总驾驶时长
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
		
		     	<tr><b>车辆信息:</b></tr>
				<tr>
					<td>OBD号:</td>
					<td><input id="carStatus_carId" name="carInfo.obdNo"  class="easyui-validatebox textbox" value="<%=id%>" readonly="readonly" /></td>
					<td>单位名称:</td>
					<td><input id ="carStatus_simNo" name = "carInfo.orgName" class="easyui-validatebox textbox" readonly="readonly"  /></td>			
									
				</tr>
				<tr>
					<td>车牌号:</td>
					<td><input id="carStatus_carId" name="carInfo.carNo"  class="easyui-validatebox textbox" readonly="readonly" /></td>
					<td>车架号:</td>
					<td><input name = "carInfo.carIdentifyNo"  class="easyui-validatebox textbox" readonly="readonly"  /></td>					
					
				</tr>	
			
				<tr><td><b>车况信息:</b></td></tr>
				
				<tr>
					<td>剩余油量:</td>
					<td><input name = "carInfo.obdOil"  class="easyui-validatebox textbox"  readonly="readonly" /></td>
					
					<td>历史总油耗:</td>
					<td><input name = "carInfo.obdUsedOil" class="easyui-validatebox textbox" readonly="readonly" /></td>
				</tr>
					<tr>
					<td>车速:</td>
					<td><input name = "carInfo.obdSpeed"  class="easyui-validatebox textbox" readonly="readonly"  /></td>
					
					<td>水温:</td>
					<td><input name = "carInfo.obdCoolant" class="easyui-validatebox textbox" readonly="readonly" /></td>
				</tr>
					<tr>
					<td>节气门开度 :</td>
					<td><input name = "carInfo.obdAirDamper"  class="easyui-validatebox textbox" readonly="readonly"  /></td>
					
					<td>进气流量:</td>
					<td><input name = "carInfo.obdAirFlow" class="easyui-validatebox textbox" readonly="readonly" /></td>
				</tr>
					<tr>
					<td>进气温度:</td>
					<td><input name = "carInfo.obdAirTemperature"  class="easyui-validatebox textbox"  readonly="readonly" /></td>
					
					<td>进气压力:</td>
					<td><input name = "carInfo.obdAirPressure" class="easyui-validatebox textbox" readonly="readonly" /></td>
				</tr>
					<tr>
					<td>电瓶电压:</td>
					<td><input name = "carInfo.obdBattery"  class="easyui-validatebox textbox"  readonly="readonly" /></td>
					
					<td>故障码个数:</td>
					<td><input name = "carInfo.obdAccident" class="easyui-validatebox textbox" readonly="readonly" /></td>
				</tr>
					<tr>
					<td>里程:</td>
					<td><input id ="carStatus_simNo" name = "carInfo.obdMileage" class="easyui-validatebox textbox" readonly="readonly"  /></td>
					<td>历史总驾驶时长:</td>
					<td><input name = "carInfo.obdDriverDuration" class="easyui-validatebox textbox" readonly="readonly" /></td>
				</tr>
			
									
			</table>
		</fieldset>
	</form>
</body>
</html>