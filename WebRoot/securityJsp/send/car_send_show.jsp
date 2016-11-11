<%@page import="com.framework.util.ConfigUtil"%>
<%@page import="com.framework.util.WebMsgUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	String id = request.getParameter("id");
	String sendId = request.getParameter("sendId");
	String applyId = request.getParameter("applyId");
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
   <div id="apply_iframe" style="height:500px">
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
						<input class="easyui-textbox" id="sendNo" name="sendNo" readonly="readonly"/>
                		<input type="hidden" id="sendId" name="sendId" value="">
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
					<td><img alt="领车人拍照" id="personimg" src="<%=basePath %>public/style/images/pic_none.png" width="100px" height="100px"></td>
					<td>派车证件：</td>
					<td><img alt="派车证件" id="cardimg" src="<%=basePath %>public/style/images/pic_none.png" width="100px" height="100px"></td>
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
<script type="text/javascript">
$(function() {
	/* parent.$.messager.progress({
		text : '数据加载中....'
	}); */
	var id = "<%=id%>";
	var sendId = "<%=sendId%>";
	var applyId = "<%=applyId%>";
	if ((id != "null" && id.length > 0)
			|| (sendId != "null" && sendId.length > 0)
			|| (applyId != "null" && applyId.length > 0)) {
		var url;
		var params;
		if (id != "null" && id.length > 0) {
			url = sys.contextPath + '/car/sendStatus!getById.cxl';
			params = {
					id : id
			};
		} else if (sendId != "null" && sendId.length > 0) {
			url = sys.contextPath + '/car/sendStatus!getBySendId.cxl';
			params = {
					id : sendId
			};
		} else {
			url = sys.contextPath + '/car/sendStatus!getByApplyId.cxl';
			params = {
					id : applyId
			};
		}
		$.post(url, params, function(result) {
			if (result.id != undefined) {
				var oil = result.sendOil - result.returnOil;
				var mileage = result.returnMileage - result.sendMileage;
				$('#sendId').val(result.sendId);
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
					'sendTimeCreate' : result.sendTimeCreate
				});
				if (result.sendFile != undefined && result.sendFile != '') {
					$('#cardimg').attr("src",'<%=basePath%>'+result.sendFile);
				}
				if (result.sendHandlerPicture != undefined && result.sendHandlerPicture != '') {
					$('#personimg').attr("src",'<%=basePath%>'+result.sendHandlerPicture);
				}
				if (result.applyId == undefined) {
					$('#apply_set').remove();
				} else {
					$("#apply_iframe").html("<iframe src='" + sys.contextPath
							+ "/securityJsp/apply/car_apply_approval_form.jsp?f=details&id=" + result.applyId
							+ "' frameborder='no' border=0 style=' width: 100%; height: 99%;'></iframe>");
				}
				if (result.sendId == undefined) {
					$('#send_set').remove();
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

var submitSend = function($dialog, $grid, $pjq) {
	var sendId = $("#sendId").val();
	if (sendId == undefined || sendId == '') {
		$pjq.messager.progress('close');
		$pjq.messager.alert('提示','派车单据未保存，无法派车！', 'error');
		return;
	}
	if(typeof JSON == 'undefined'){
		$('head').append($("<script type='text/javascript' src='../../public/jslib/json2.js'>"));
	}
	if ($("#detailForm").form('validate')) {
		var params = "id=" + sendId;
		var url = sys.contextPath + '/car/send!send.cxl';
		$.post(url, params, function(result) {
			$pjq.messager.progress('close');
			if (result.success) {
				$grid.datagrid('reload');
				$pjq.messager.alert('提示',result.msg,'info');
				//sendreportForm(id);
			} else {
				$pjq.messager.alert('提示',result.msg, 'error');
			}
		}, 'json');
	}
};

function getSendId() {
	return $("#sendId").val();
}
 function getSendNo() {
    var result=$("#sendNo").textbox('getValue');
        return result;
    }
// 外部获取值实例
function getElementValue(elementId, type) {
	if (type == 'textbox') {
		return $("#" + elementId).textbox('getValue');
	}
}
</script>
</body>
</html>