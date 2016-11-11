<%@page import="com.framework.util.WebMsgUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	String id = request.getParameter("id");
	String f = request.getParameter("f");
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
<style>
	.textbox{
		height:20px;
	}
	td{
	font-size:12px;
	}
</style>

		<form method="post" id="detailForm">
		<div id="light" style="margin-bottom:10px;margin-top:10px">
			<input type="hidden" id="applyId" name="apply.applyId" value="">
			<input type="hidden" id="dictIdCheckStatus" name="apply.dictIdCheckStatus" value="">
			<input type="hidden" id="preApplyId" name="apply.preApplyId" value="">
			<table width="100%" align="center">
				<tr>
					<td>申请编号：</td>
					<td>
						<input class="textbox" id="applyNo" name="apply.applyNo" readonly="readonly" width="250px"/>
					</td>
					<td>组织单位：</td>
					<td>
						<input class="textbox" id="orgId" name="apply.orgId" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td>申请车辆种类：</td>
					<td>
						<input class="textbox" id="dictIdCarType" name="apply.dictIdCarType" readonly="readonly"/>
					</td>
					<td>用车区域：</td>
					<td>
						<input class="textbox" id="dictIdRegion" name="apply.dictIdRegion" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td>预计用车时间：</td>
					<td>
						<input class="textbox" id="applyUsingTime" name="applyUsingTime" readonly="readonly"/>
					</td>
					<td>预计还车时间：</td>
					<td>
						<input class="textbox" id="applyRemandTime" name="applyRemandTime" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td>用车理由：</td>
					<td colspan="5">
						<input class="easyui-textbox textbox" id="applyUseCarReson" name="apply.useCarReson"
							data-options="width:516" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td>申请固定车辆：</td>
					<td>
						<input class="textbox" id="carId" name="apply.carId" readonly="readonly"/>
					</td>
					<td>是否保密：</td>
					<td>
						<input class="textbox" id="dictIdIsSecret" name="apply.dictIdIsSecret" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td>警牌/民牌：</td>
					<td>
						<input class="textbox" id="carPoliceUsed" name="apply.carPoliceUsed"/>
					</td>
					<td>乘车人数：</td>
					<td>
						<input class="textbox" id="applyUsedPeople" name="apply.applyUsedPeople" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td>申请人：</td>
					<td>
						<input class="textbox" id="userIdLinkman" name="apply.userIdLinkman" readonly="readonly"/>
					</td>
					<td>申请人手机：</td>
					<td>
						<input class="textbox" id="stafPhoneLinkman" name="apply.stafPhoneLinkman" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td>登记人：</td>
					<td>
						<input class="easyui-textbox textbox" id="userIdCreate" name="apply.userIdCreate" readonly="readonly"/>
					</td>
					<td>登记时间：</td>
					<td>
						<input class="easyui-textbox textbox" id="timeCreate" name="timeCreate" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td>备注：</td>
					<td colspan="5">
						<input id="applyMemo" name="apply.applyMemo" class="easyui-textbox" readonly="readonly"
							data-options="multiline:true,validType:'maxLength[190]',width:'516'" style="height:50px">
					</td>
				</tr>
				<tr class="deptApproval">
				<tr>
					<td>审批状态：</td>
					<td colspan="5">
						<input class="easyui-textbox textbox" id="checkStatus" name="checkStatus" readonly="readonly"/>
					</td>
				</tr>
				<tr class="deptApproval">
					<td>处长审批人：</td>
					<td>
						<input class="easyui-textbox textbox" id="userIdCheck1" name="apply.userIdCheck1" readonly="readonly"/>
					</td>
					<td>处长审批时间：</td>
					<td>
						<input class="easyui-textbox textbox" id="timeCheck1" name="timeCheck1" readonly="readonly"/>
					</td>
				</tr>
				<tr class="deptApproval">
					<td>处长审批意见：</td>
					<td colspan="5">
						<input id="applyCheck1" name="apply.applyCheck1" class="easyui-textbox" readonly="readonly"
							data-options="multiline:true,validType:'maxLength[95]',width:'516'" style="height:50px">
					</td>
				</tr>
				<tr class="officeApproval">
					<td>处审批人：</td>
					<td>
						<input class="easyui-textbox textbox" id="userIdCheck2" name="apply.userIdCheck2" readonly="readonly"/>
					</td>
					<td>处审批时间：</td>
					<td>
						<input class="easyui-textbox textbox" id="timeCheck2" name="timeCheck2" readonly="readonly"/>
					</td>
				</tr>
				<tr class="officeApproval">
					<td>处审批意见：</td>
					<td colspan="5">
						<input id="applyCheck2" name="apply.applyCheck2" class="easyui-textbox" readonly="readonly"
							data-options="multiline:true,validType:'maxLength[95]',width:'516'" style="height:50px">
					</td>
				</tr>
				<tr class="editApproval">
					<td>审批意见：</td>
					<td colspan="5">
						<input id="applyCheck" name="apply.applyCheck2" class="easyui-textbox"
							data-options="multiline:true,validType:'maxLength[95]',width:'516',prompt:'可在此输入审批意见...'" style="height:50px">
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
<script type="text/javascript">
$(function() {
	/* parent.$.messager.progress({
		text : '数据加载中....'
	}); */
	var id = "<%=id%>";
	if (id != "null" && id.length > 0) {
		$.post(sys.contextPath + '/car/apply!getByViewId.cxl', {
			id : id
		}, function(result) {
			if (result.applyId != undefined) {
				$('form').form('load', {
					'apply.applyId' : result.applyId,
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
					'apply.userIdCreate' : result.userNameCreate,
					'apply.dictIdCheckStatus' : result.checkStatus,
					'apply.userIdCheck1' : result.userNameCheck1,
					'apply.applyCheck1' : result.applyCheck1,
					'timeCheck1' : result.timeCheck1,
					'apply.userIdCheck2' : result.userNameCheck2,
					'apply.applyCheck2' : result.applyCheck2,
					'timeCheck2' : result.timeCheck2,
					'timeCreate' : result.timeCreate,
					'applyUsingTime' : result.applyUsingTime,
					'applyRemandTime' : result.applyRemandTime,
					'checkStatus' : result.checkStatus,
					'apply.preApplyId' : result.preApplyId
				});
				if (result.userNameCheck1 == undefined || result.userNameCheck1 == "") {
					$('.deptApproval').hide();
					$('.userSign').hide();
				} else if (result.dictIdCheckStatus == "<%=WebMsgUtil.CHECK_STATUS_REFUSE2%>") {
					$('.editApproval').hide();
				}
				if (result.userNameCheck2 == undefined || result.userNameCheck2 == "") {
					$('.officeApproval').hide();
				} else {
					$('.editApproval').hide();
				}
				if ("<%=f%>" == "details") {
					$('.editApproval').hide();
				}
				$('#userSignImg').attr('src', '<%=basePath%>maintain/sysUser!doNotNeedSecurity_getimage.cxl?id=' + result.userIdCheck1);
			}
			parent.$.messager.progress('close');
		}, 'json');
	} else {
		/* $("#wfdId").val("");
		$('#tbdiv').attr("hidden","hidden");
		$(".htd").attr("hidden","hidden"); */
		parent.$.messager.progress('close');
	}
	
});

var apprAccess = function ($dialog, $grid, $pjq) {
	if ($("#detailForm").form('validate')) {
		$pjq.messager.progress({
			text : '数据处理中....'
		});
		var params = "apply.applyId=" + $("#applyId").val();
		params += "&apply.applyCheck2=" + $("#applyCheck").textbox('getText');
		params += "&apply.dictIdCheckStatus=<%=WebMsgUtil.CHECK_STATUS_AGREE1%>";
		var url = sys.contextPath + '/car/apply!approval.cxl';
		$.post(url, params, function(result) {
			$pjq.messager.progress('close');
			if (result.success) {
				$dialog.dialog('destroy');
				$grid.datagrid('reload');
				$pjq.messager.alert('提示',result.msg,'info');
			} else {
				$pjq.messager.alert('提示',result.msg, 'error');
			}
		}, 'json');
	}
};

var apprRefuse = function($dialog, $grid, $pjq) {
	if(typeof JSON == 'undefined'){
		$('head').append($("<script type='text/javascript' src='../../public/jslib/json2.js'>"));
	}
	if ($("#detailForm").form('validate')) {
		$pjq.messager.progress({
			text : '数据处理中....'
		});
		var params = "apply.applyId=" + $("#applyId").val();
		params += "&apply.applyCheck2=" + $("#applyCheck").textbox('getText');
		params += "&apply.dictIdCheckStatus=<%=WebMsgUtil.CHECK_STATUS_REFUSE1%>";
		var url = sys.contextPath + '/car/apply!approval.cxl';
		$.post(url, params, function(result) {
			$pjq.messager.progress('close');
			if (result.success) {
				$dialog.dialog('destroy');
				$grid.datagrid('reload');
				$pjq.messager.alert('提示',result.msg,'info');
			} else {
				$pjq.messager.alert('提示',result.msg, 'error');
			}
		}, 'json');
	}
};
	 
</script>
</body>
</html>