<%@page import="com.framework.util.ConfigUtil"%>
<%@page import="com.framework.util.WebMsgUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	String id = request.getParameter("id");
	String sendId = request.getParameter("sendId");
	String f = request.getParameter("f");
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
</head>
<body class="easyui-layout" data-options="fit:true,border:false" style="overflow-y:auto">
<style>
	.textbox{
		height:20px;
	}
</style>
   <fieldset id="apply_set">
   <legend><b>申请单信息</b></legend>
   <div id="apply_iframe" style="height:auto">
   	<form method="post" id="applyForm">
		<div id="applylight" style="margin-bottom:10px;margin-top:10px">
			<table width="100%" align="center">
				<tr>
					<td>申请编号：</td>
					<td>
						<input class="textbox" name="apply.applyNo" readonly="readonly" width="250px"/>
					</td>
					<td>组织单位：</td>
					<td>
						<input class="textbox" name="apply.orgId" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td>申请车辆种类：</td>
					<td>
						<input class="textbox" name="apply.dictIdCarType" readonly="readonly"/>
					</td>
					<td>用车区域：</td>
					<td>
						<input class="textbox" name="apply.dictIdRegion" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td>预计用车时间：</td>
					<td>
						<input class="textbox" name="applyUsingTime" readonly="readonly"/>
					</td>
					<td>预计还车时间：</td>
					<td>
						<input class="textbox" name="applyRemandTime" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td>用车理由：</td>
					<td colspan="5">
						<input class="easyui-textbox textbox" name="apply.useCarReson"
							data-options="width:516" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td>申请固定车辆：</td>
					<td>
						<input class="textbox" name="apply.carId" readonly="readonly"/>
					</td>
					<td>是否保密：</td>
					<td>
						<input class="textbox" name="apply.dictIdIsSecret" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td>警牌/民牌：</td>
					<td>
						<input class="textbox" name="apply.carPoliceUsed"/>
					</td>
					<td>乘车人数：</td>
					<td>
						<input class="textbox" name="apply.applyUsedPeople" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td>申请人：</td>
					<td>
						<input class="textbox" name="apply.userIdLinkman" readonly="readonly"/>
					</td>
					<td>申请人手机：</td>
					<td>
						<input class="textbox" name="apply.stafPhoneLinkman" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td>登记人：</td>
					<td>
						<input class="easyui-textbox textbox" name="apply.userIdCreate" readonly="readonly"/>
					</td>
					<td>登记时间：</td>
					<td>
						<input class="easyui-textbox textbox" name="timeCreate" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td>备注：</td>
					<td colspan="5">
						<input name="apply.applyMemo" class="easyui-textbox" readonly="readonly"
							data-options="multiline:true,validType:'maxLength[190]',width:'516'" style="height:50px">
					</td>
				</tr>
				<tr class="deptApproval">
				<tr>
					<td>审批状态：</td>
					<td colspan="5">
						<input class="easyui-textbox textbox" name="checkStatus" readonly="readonly"/>
					</td>
				</tr>
				<tr class="deptApproval">
					<td>处长审批人：</td>
					<td>
						<input class="easyui-textbox textbox" name="apply.userIdCheck1" readonly="readonly"/>
					</td>
					<td>处长审批时间：</td>
					<td>
						<input class="easyui-textbox textbox" name="timeCheck1" readonly="readonly"/>
					</td>
				</tr>
				<tr class="deptApproval">
					<td>处长审批意见：</td>
					<td colspan="5">
						<input name="apply.applyCheck1" class="easyui-textbox" readonly="readonly"
							data-options="multiline:true,validType:'maxLength[95]',width:'516'" style="height:50px">
					</td>
				</tr>
				<tr class="officeApproval">
					<td>处审批人：</td>
					<td>
						<input class="easyui-textbox textbox" name="apply.userIdCheck2" readonly="readonly"/>
					</td>
					<td>处审批时间：</td>
					<td>
						<input class="easyui-textbox textbox" name="timeCheck2" readonly="readonly"/>
					</td>
				</tr>
				<tr class="officeApproval">
					<td>处审批意见：</td>
					<td colspan="5">
						<input name="apply.applyCheck2" class="easyui-textbox" readonly="readonly"
							data-options="multiline:true,validType:'maxLength[95]',width:'516'" style="height:50px">
					</td>
				</tr>
				<tr class="userSign">
					<td>处长签名：</td>
					<td colspan="5">
						<img alt="处长手工签名" id="userSignImg" width="150px" height="100px">
					</td>
				</tr>
			</table>
		</div>
		</form>
   </div>
   </fieldset>
   <fieldset id="send_set">
   <legend><b>派车单信息</b></legend>
   <div id="send_iframe">
       <form method="post" id="detailForm">
		<div id="light" style="margin-top:10px">
			<table width="100%" align="center">
				<tr>
					<td>派车单号：</td>
					<td>
						<input class="textbox" id="sendNo" name="sendNo" readonly="readonly"/>
					</td>
					<td>领车单位：</td>
					<td>
						<input class="textbox" id="sendOrgName" name="orgName" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td>车牌号：</td>
					<td>
						<input class="textbox" id="carNo" name="carNo" readonly="readonly"/>
					</td>
					<td>车辆sim卡号：</td>
					<td>
						<input class="textbox" id="simNo" name="simNo" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td>出车油量：</td>
					<td>
						<input class="textbox" id="sendOil" name="sendOil" readonly="readonly"/>
					</td>
					<td>出车里程：</td>
					<td>
						<input class="textbox" id="sendMileage" name="sendMileage"  readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td>驾驶员：</td>
					<td>
						<input class="textbox" id="userIdPilot" name="userIdPilot" readonly="readonly"/>
					</td>
					<td>领车人：</td>
					<td>
						<input class="textbox" id="userIdHandler" name="userIdHandler" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td>驾驶员手机：</td>
					<td>
						<input class="textbox" id="stafPhonePilot" name="stafPhonePilot" readonly="readonly"/>
					</td>
					<td>驾驶员准驾车型：</td>
					<td>
						<input class="textbox" id="dictIdDrivingLlicenseKind" name="dictIdDrivingLlicenseKind" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td>领车人拍照：</td>
					<td><img alt="领车人拍照" src="<%=basePath %>public/style/images/pic_none.png" id="personimg" width="100px" height="100px"></td>
					<td>派车文件：</td>
					<td><img alt="领车人拍照" src="<%=basePath %>public/style/images/pic_none.png" id="cardimg" width="100px" height="100px"></td>
				</tr>
				<tr>
					<td>备注</td>
					<td colspan="3">
						<input id="sendMemo" name="sendMemo" class="easyui-textbox" readonly="readonly"
							data-options="multiline:true,validType:'maxLength[190]',width:'540'" style="height:50px">
					</td>
				</tr>
				<tr>
					<td>派车登记人：</td>
					<td>
						<input class="textbox" id="sendUserNameCreate" name="sendUserNameCreate" readonly="readonly"/>
					</td>
					<td>派车登记时间：</td>
					<td>
						<input class="textbox" id="sendTimeCreate" name="sendTimeCreate" readonly="readonly"/>
					</td>
				</tr>
			</table>
			<!-- <table id="dg" width="100%" align="center"></table> -->
		</div>
		</form>
   </div>
   </fieldset>
   <fieldset id="return_set">
   <legend><b>还车单信息</b></legend>
   <div id="return_iframe">
       <form method="post" id="returnForm">
		<div style="margin-top:10px">
			<table width="100%" align="center">
				<tr>
					<td>还车油量：</td>
					<td>
						<input class="textbox" id="returnOil" name="returnOil" readonly="readonly"/>
					</td>
					<td>还车里程：</td>
					<td>
						<input class="textbox" id="returnMileage" name="returnMileage"  readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td>本次出车耗油量：</td>
					<td>
						<input class="textbox" id="oil" name="oil" readonly="readonly"/>
					</td>
					<td>本次出车里程：</td>
					<td>
						<input class="textbox" id="mileage" name="mileage"  readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td>还车人拍照：</td>
					<td><img alt="还车人拍照" src="<%=basePath %>public/style/images/pic_none.png" width="100px" height="100px"></td>
					<td>还车文件：</td>
					<td><img alt="还车人拍照" src="<%=basePath %>public/style/images/pic_none.png" width="100px" height="100px"></td>
				</tr>
				<tr>
					<td>还车登记人：</td>
					<td>
						<input class="textbox" id="retUserNameCreate" name="retUserNameCreate" readonly="readonly"/>
					</td>
					<td>还车登记时间：</td>
					<td>
						<input class="textbox" id="retTimeCreate" name="retTimeCreate"  readonly="readonly"/>
					</td>
				</tr>
			</table>
		</div>
		</form>
	</div>
   </fieldset>
<script type="text/javascript">
$(function() {
	/* parent.$.messager.progress({
		text : '数据加载中....'
	}); */
	$('#oil').numberbox({
		required:true,
		precision:2
	});
	$('#mileage').numberbox({
		required:true,
		precision:2
	});
	
	var id = "<%=id%>";
	var sendId = "<%=sendId%>";
	if ((id != "null" && id.length > 0) 
			|| (sendId != "null" && sendId.length > 0)) {
		var url;
		var params;
		if (id != "null" && id.length > 0) {
			url = sys.contextPath + '/car/sendStatus!getById.cxl';
			params = {
					id : id
			};
		} else {
			url = sys.contextPath + '/car/sendStatus!getBySendId.cxl';
			params = {
					id : sendId
			};
		}
		$.post(url, params, function(result) {
			if (result.id != undefined) {
				/* var oil = result.sendOil.toFixed(2) - result.returnOil.toFixed(2);
				var mileage = result.returnMileage.toFixed(2) - result.sendMileage.toFixed(2); */
				var oil;
				var mileage;
				if (result.sendOil != undefined && result.sendOil != null && result.sendOil != ''
						&& result.returnOil != undefined && result.returnOil != null && result.returnOil != '') {
					oil = result.sendOil.toFixed(2) - result.returnOil.toFixed(2);
					oil = oil.toFixed(2);
				}
				if (result.returnMileage != undefined && result.returnMileage != null && result.returnMileage != ''
						&& result.sendMileage != undefined && result.sendMileage != null && result.sendMileage != '') {
					 mileage= result.returnMileage.toFixed(2) - result.sendMileage.toFixed(2);
					 mileage = mileage.toFixed(2);
				}
				
				
				$('form').form('load', {
					'sendNo' : result.sendNo,
					'orgName' : result.orgName,
					'userIdHandler' : result.userNameHandler,
					'carNo' : result.carNo,
					'simNo' : result.simNo,
					'sendOil' : result.sendOil,
					'sendMileage' : result.sendMileage,
					'userIdPilot' : result.pilotName,
					'stafPhonePilot' : result.stafPhonePilot,
					'dictIdDrivingLlicenseKind' : result.drivingLlicenseKind,
					'sendMemo' : result.sendMemo,
					'sendUserNameCreate' : result.sendUserNameCreate,
					'sendTimeCreate' : result.sendTimeCreate,
					'returnOil' : result.returnOil,
					'returnMileage' : result.returnMileage,
					'oil' : oil,
					'mileage' : mileage,
					'retUserNameCreate' : result.retUserNameCreate,
					'retTimeCreate' : result.retTimeCreate
				});
				if (result.sendFile != undefined && result.sendFile.length > 0) {
					$('#cardimg').attr("src", '<%=basePath%>' + result.sendFile);
				}
				if (result.sendHandlerPicture != undefined && result.sendHandlerPicture.length > 0) {
					$('#personimg').attr("src", '<%=basePath%>' + result.sendHandlerPicture);
				}
				if (result.applyId == undefined) {
					$('#apply_set').remove();
				} else {
					/* $("#apply_iframe").html("<iframe src='" + sys.contextPath 
							+ "/securityJsp/apply/car_apply_approval_form.jsp?f=details&id=" + result.applyId
							+ "' frameborder='no' border=0 style='width: 100%; height: 99%;'></iframe>"); */
					if (result.applyId != undefined) {
        				$('#applyForm').form('load', {
        					'apply.applyNo' : result.applyNo,
        					'apply.dictIdCarType' : result.carType,
        					'apply.orgId' : result.orgName,
        					'apply.carId' : result.carNo,
        					'apply.userIdLinkman' : result.linkManName,
        					'apply.stafPhoneLinkman' : result.stafPhoneLinkman,
        					'apply.carPoliceUsed' : result.carPoliceUsed,
        					'apply.applyUsedPeople' : result.applyUsedPeople,
        					'apply.useCarReson' : result.useCarReson,
        					'apply.dictIdRegion' : result.region,
        					'apply.userIdPilot' : result.pilotName,
        					'apply.stafPhonePilot' : result.stafPhonePilot,
        					'apply.dictIdDrivingLlicenseKind' : result.drivingLlicenseKind,
        					'apply.dictIdIsSecret' : result.isSecret,
        					'apply.applyMemo' : result.applyMemo,
        					'apply.userIdCreate' : result.applyUserNameCreate,
        					'apply.userIdCheck1' : result.userNameCheck1,
        					'apply.applyCheck1' : result.applyCheck1,
        					'timeCheck1' : result.timeCheck1,
        					'apply.userIdCheck2' : result.userNameCheck2,
        					'apply.applyCheck2' : result.applyCheck2,
        					'timeCheck2' : result.timeCheck2,
        					'timeCreate' : result.applyTimeCreate,
        					'applyUsingTime' : result.applyUsingTime,
        					'applyRemandTime' : result.applyRemandTime,
        					'checkStatus' : result.checkStatus
        				});
        				if (result.userNameCheck1 == undefined || result.userNameCheck1 == "") {
        					$('.deptApproval').hide();
        					$('.userSign').hide();
        				}
        				if (result.userNameCheck2 == undefined || result.userNameCheck2 == "") {
        					$('.officeApproval').hide();
        				}
        				$('#userSignImg').attr('src', '<%=basePath%>maintain/sysUser!doNotNeedSecurity_getimage.cxl?id=' + result.userIdCheck1);
        			}
				}
				if (result.sendId == undefined) {
					$('#send_set').remove();
				}
				if (result.returnId == undefined) {
					$('#return_set').remove();
				}
			}
			parent.$.messager.progress('close');
		}, 'json');
	} else {
		if ('<%=f%>' == 'warn') {
			$("#apply_iframe").remove();
		}
		parent.$.messager.progress('close');
	}
	
});
	 
</script>
</body>
</html>