<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.system.entity.maintain.SessionInfo"%>
<%
	String contextPath = request.getContextPath();
	SessionInfo sessionInfo = (SessionInfo) session.getAttribute("sessionInfo");
%>
<script type="text/javascript" charset="utf-8">
	$(function() {
		/* $.post(sys.contextPath + '/sm/sysUser!doNotNeedSessionAndSecurity_validateMobileno.cxl', function(result) {
		 if (result.success) {
		 $('#contactDialog').dialog('open');
		 }
		 }, 'json'); */

		// 验证用户密码是否为简单密码
		$.post(sys.contextPath + '/maintain/sysUser!doNotNeedSessionAndSecurity_simplePasswordValidate.cxl', function(result) {
			if (result.success) {
				alert("您的密码过于简单，请重置密码！");
				$('#passwordDialogValidate').dialog('open');
			}
		}, 'json');
	});
	var lockWindowFun = function() {
		$.post(sys.contextPath + '/maintain/sysUser!doNotNeedSessionAndSecurity_logout.cxl', function(result) {
			$('#loginDialog').dialog('open');
		}, 'json');
	};
	var logoutFun = function() {
		$.post(sys.contextPath + '/maintain/sysUser!doNotNeedSessionAndSecurity_logout.cxl', function(result) {
			location.replace(sys.contextPath + '/index.jsp');
		}, 'json');
	};
	var showMyInfoFun = function() {
		var dialog = parent.sys.modalDialog({
			title : '我的信息',
			url : sys.contextPath + '/securityJsp/userInfo.jsp'
		});
	};
	var backHomePage = function() {
		location.replace(sys.contextPath + '/securityJsp/homepage.jsp');
	};


</script>
<div style=" background:#2d77b5; height:70px; position:absolute; left:0; top:0; right:0; z-index:-100; width:100%"></div>
<div id="sessionInfoDiv" style="position: absolute; right: 10px; top: 5px;">
	<%
		if (sessionInfo != null) {
			out.print(com.framework.util.StringUtil.formateString("欢迎您，{0}", "来自<b>" + sessionInfo.getOrganization().getOrgName() + "</b>的<b>" + sessionInfo.getUser().getUserName() + "</b>"));
		}
	%>
</div>
<div style="position: absolute; right: 0px; bottom: 0px; z-index:500;">
	<a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_pfMenu',iconCls:'ext-icon-rainbow'">更换皮肤</a> <a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_kzmbMenu',iconCls:'ext-icon-cog'">控制面板</a> <a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_zxMenu',iconCls:'ext-icon-disconnect'">注销</a>
</div>
<div id="layout_north_pfMenu" style="width: 120px; display: none;">
	<div onclick="sys.changeTheme('default');" title="default">default</div>
	<div onclick="sys.changeTheme('gray');" title="gray">gray</div>
	<div onclick="sys.changeTheme('metro');" title="metro">metro</div>
	<div onclick="sys.changeTheme('bootstrap');" title="bootstrap">bootstrap</div>
	<div onclick="sys.changeTheme('black');" title="black">black</div>
	<div onclick="sys.changeTheme('material');" title="material">material</div>
	<%--<div onclick="sys.changeTheme('icons');" title="icons">bootstrap</div>--%>
</div>
<div id="layout_north_kzmbMenu" style="width: 100px; display: none;">
	<div data-options="iconCls:'ext-icon-user_edit'" onclick="$('#passwordDialog').dialog('open');">修改密码</div>
	<div class="menu-sep"></div>
	<!-- <div data-options="iconCls:'ext-icon-lock'" onclick="lockMobileFun();">绑定联系电话</div>   -->
	<div data-options="iconCls:'ext-icon-user'" onclick="showMyInfoFun();">我的信息</div>
</div>
<div id="layout_north_zxMenu" style="width: 100px; display: none;">
	<div data-options="iconCls:'ext-icon-lock'" onclick="lockWindowFun();">锁定窗口</div>
	<div class="menu-sep"></div>
	<%--<div data-options="iconCls:'ext-icon-arrow_redo'" onclick="backHomePage();">返回首页</div>
	<div class="menu-sep"></div>--%>
	<div data-options="iconCls:'ext-icon-door_out'" onclick="logoutFun();">退出系统</div>
</div>
