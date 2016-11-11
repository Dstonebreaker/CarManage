<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.framework.util.SecurityUtil"%>
<%@ page import="com.system.entity.maintain.SysOrganization"%>
<%@ page import="com.system.entity.maintain.SessionInfo"%>
<%@ page import="com.framework.util.ConfigUtil"%>
<%
	String contextPath = request.getContextPath();
	SecurityUtil securityUtil = new SecurityUtil(session);
	SessionInfo sessionInfo = (SessionInfo) session
			.getAttribute(ConfigUtil.getSessionInfoName());
	String acceptid="";
	String organization="";
	if(sessionInfo!=null){
	acceptid=sessionInfo.getUser().getUserId();	
	SysOrganization sysOrganization = sessionInfo.getUser()
			.getSysOrganization().iterator().next();
	if (sysOrganization == null) {
		organization = "";

	} else {
		organization = sysOrganization.getOrgId();
           }
	}
%>

<!DOCTYPE>
<html>
<head>


<title>首页</title>
<style type="text/css">
#winpop { width:200px; height:0px; position:absolute; right:0; bottom:0; border:1px solid #666; margin:0; padding:1px; overflow:hidden; display:none;}
#winpop .title { width:100%; height:22px; line-height:20px;  font-weight:bold; text-align:center; font-size:12px;}
#winpop .con { width:100%; height:90px; line-height:80px; font-weight:bold; font-size:12px; color:#FF0000; text-decoration:underline; text-align:center}
#silu { font-size:12px; color:#666; position:absolute; right:0; text-align:right; text-decoration:underline; line-height:22px;}
.close { position:absolute; right:4px; top:-1px; color:#FFF; cursor:pointer}
</style>
<script type="text/javascript">
function tips_pop(){
  var MsgPop=document.getElementById("winpop");
  var popH=parseInt(MsgPop.style.height);//将对象的高度转化为数字
   if (popH==0){
   MsgPop.style.display="block";//显示隐藏的窗口
  show=setInterval("changeH('up')",2);
   }
  else { 
   hide=setInterval("changeH('down')",2);
  }
}
function changeH(str) {
 var MsgPop=document.getElementById("winpop");
 var popH=parseInt(MsgPop.style.height);
 if(str=="up"){
  if (popH<=100){
  MsgPop.style.height=(popH+4).toString()+"px";
  }
  else{  
  clearInterval(show);
  }
 }
 if(str=="down"){ 
  if (popH>=4){  
  MsgPop.style.height=(popH-4).toString()+"px";
  }
  else{ 
  clearInterval(hide);   
  MsgPop.style.display="none";  //隐藏DIV
  }
 }
}
window.onload=function(){//加载
document.getElementById('winpop').style.height='0px';
setTimeout("tips_pop()",800);//3秒后调用tips_pop()这个函数
}
</script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<jsp:include page="inc.jsp"></jsp:include>
<script type="text/javascript">
  $(function() {
	    $.post(sys.contextPath + '/maintain/sysRemind!doNotNeedSecurity_getCount.cxl', {
			}, 
			function(result) {
				if (result.countExamine!= undefined) {
					$('form').form('load', {
						'sysRemind.countExamine':result.countExamine,
						'sysRemind.countInsurance' : result.countInsurance,
						'sysRemind.countMaintenance' : result.countMaintenance	
					});
					$("#examId").text(result.countExamine);
                    $("#InsId").text(result.countInsurance);
                    $("#mainId").text(result.countMaintenance);
				}
				parent.$.messager.progress('close');
			}, 'json');	
	});
//打开申请用车
	var openApply=function () {
		opentab("/securityJsp/apply/car_apply.jsp","用车申请");
	};
	var openApproval=function () {
		opentab("/securityJsp/apply/car_apply_approval_office.jsp","处用车审批");
	};
	var openCarSend=function () {
		opentab("/securityJsp/send/car_send.jsp","车辆派遣");
	};
	var openCarMaintenan=function () {
		opentab("/securityJsp/carManage/carmaintenance.jsp","保养记录");
	};
	var openUser=function () {
		opentab("/securityJsp/maintain/sysUser.jsp","人员管理");
	};
	
	var openExam=function () {
		opentab("/securityJsp/carexamine/alert_examine.jsp","年审预警");
	};
	var openInsurance=function () {
		opentab("/securityJsp/carinsurance/carrenewalremind.jsp","续费提醒");
	};
	var openMaintenance=function () {
		opentab("/securityJsp/carManage/carmaintenanceremind.jsp","保养提醒");
	};
	var opentab=function(url,titles) {
		window.parent.tabs(url,titles);

	};
	
</script>

<style type="text/css">
body{margin:0; padding:0;font-size:13px; font-family:"微软雅黑"; background:url(img/bg_00.jpg); background-attachment:fixed; background-position:center  }
.content_0{width:850px; height:300px;  top:25%; left:50%; position:absolute;  margin-top:0px; margin-left:-350px}
.btn{width:150px; height:170px; float:left; margin-bottom:100px; margin-right:120px}
.btn_img{width:150px; height:150px; }
.btn_text{width:150px; height:20px; text-align:center;}
.btn_text a{width:120px; color:#555; text-decoration:none; text-align:center}
.btn_text a:hover{color:#f60; text-decoration:underline; }

</style>
</head>


<body>
 
    <div style="width:600px; height:120px;margin-left:77%">
    <form  class="form">	
		<table id="grid">
		<tr><td style="width:300px; height:30px;" align="center" colspan="3">提醒信息</td></tr>
		 <%if (securityUtil.havePermission("/securityJsp/carexamine/alert_examine.jsp")) {%>
		<tr>
		<td style="width:100px; height:30px;" align="center">年审预警:</td>
		<td style="width:100px; height:30px;" align="center">
		<input name="sysRemind.countExamine" readOnly="true" style="border-style:none"/>
		</td>
		<td style="width:100px; height:30px;" align="center" onclick="openExam()"><a href="javascript:void(0);" class="easyui-linkbutton">查看</a></td>
		</tr>
		 <%} %>
        <%if (securityUtil.havePermission("/securityJsp/carinsurance/carrenewalremind.jsp")) {%>
		<tr>
		<td style="width:100px; height:30px;" align="center">续费提醒:</td>
		<td style="width:100px; height:30px;" align="center">
		<input name="sysRemind.countInsurance" readOnly="true" style="border-style:none" />
		</td>
		<td style="width:100px; height:30px;" align="center"onclick="openInsurance()"><a href="javascript:void(0);"class="easyui-linkbutton">查看</a></td>
		</tr>
		 <%} %>
        <%if (securityUtil.havePermission("/securityJsp/carManage/carmaintenanceremind.jsp")) {%>
		<tr>
		<td style="width:100px; height:30px;" align="center">保养提醒:</td>
		<td style="width:100px; height:30px;" align="center">
		<input  name="sysRemind.countMaintenance" readOnly="true" style="border-style:none"/>
		</td>
		<td style="width:100px; height:30px;" align="center"onclick="openMaintenance()"><a href="javascript:void(0);"class="easyui-linkbutton">查看</a></td>
		</tr>
		 <%} %>
		</table>
	</form>
	</div>
	<div class="content_0">
	<%if (securityUtil.havePermission("/securityJsp/apply/car_apply.jsp")) {%>
	<div class="btn" onclick="openApply();">
    	<div class="btn_img"><img src="img/car_0.png" /></div>
        <div class="btn_text"><a  href="javascript:void(0);"><span>用车申请</span></a></div>
    </div>
    <%} %>
    <%if (securityUtil.havePermission("/securityJsp/apply/car_apply_approval_office.jsp")) {%>
    <div class="btn" onclick="openApproval();">
    	<div class="btn_img"><img src="img/shenpi.png" /></div>
        <div class="btn_text"><a  href="javascript:void(0);"><span>审批</span></a></div>
    </div>
    <%} %>
    <%if (securityUtil.havePermission("/securityJsp/send/car_send.jsp")) {%>
    <div class="btn"onclick="openCarSend();">
    	<div class="btn_img"><img src="img/yongche.png" /></div>
        <div class="btn_text"><a  href="javascript:void(0);"><span>派车</span></a></div>
    </div>
    <%} %>
        <%if (securityUtil.havePermission("/securityJsp/carManage/carmaintenance.jsp")) {%>
    <div class="btn"  onclick="openCarMaintenan();">
    	<div class="btn_img"><img src="img/baoyang.png" /></div>
        <div class="btn_text"><a  href="javascript:void(0);"><span>保养记录</span></a></div>
    </div>
    <%} %>
    <%if (securityUtil.havePermission("/securityJsp/maintain/sysUser.jsp")) {%>
    <div class="btn" onclick="openUser();">
    	<div class="btn_img"><img src="img/renyuan.png" /></div>
        <div class="btn_text"><a  href="javascript:void(0);"><span>人员管理</span></a></div>
    </div>
    <%} %></div>
 
 <div id="winpop">
  <div class="title">提醒消息！<span class="close" onclick="tips_pop()">×</span></div>
    <div>
    <table>
     <%if (securityUtil.havePermission("/securityJsp/carexamine/alert_examine.jsp")){%>
    <tr><td>年审预警消息(<span id="examId" ></span>)条</td></tr>
     <%} %>
     <%if (securityUtil.havePermission("/securityJsp/carinsurance/carrenewalremind.jsp")){%>
    <tr><td>续费提醒消息(<span id="InsId" ></span>)条</td></tr>
     <%} %>
   <%if (securityUtil.havePermission("/securityJsp/carManage/carmaintenanceremind.jsp")){%>
    <tr><td>保养提醒消息(<span id="mainId" ></span>)条</td></tr>
     <%} %>
    </table>
    </div>
     
</div>
</body>
</html>
