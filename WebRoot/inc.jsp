<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="org.apache.commons.lang3.StringUtils"%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();%>
<%String contextPath = request.getContextPath();%>
<%String version = "20140807";%>

<%
Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
Cookie[] cookies = request.getCookies();
if (null != cookies) {
	for (Cookie cookie : cookies) {
		cookieMap.put(cookie.getName(), cookie);
	}
}
String easyuiTheme = "bootstrap";//指定如果用户未选择样式，那么初始化一个默认样式
if (cookieMap.containsKey("easyuiTheme")) {
	Cookie cookie = (Cookie) cookieMap.get("easyuiTheme");
	easyuiTheme = cookie.getValue();
}
%>

<script type="text/javascript">
var sys = sys || {};
sys.contextPath = '<%=contextPath%>';
sys.basePath = '<%=basePath%>';
sys.version = '<%=version%>';
sys.pixel_0 = '<%=basePath%>/public/style/images/pixel_0.gif';//0像素的背景，一般用于占位
</script>

<%-- 引入my97日期时间控件 --%>
<script type="text/javascript" src="<%=basePath%>/public/jslib/My97DatePicker/WdatePicker.js" charset="utf-8"></script>

<%-- 引入ueditor控件 --%>
<%-- <script type="text/javascript" charset="utf-8">window.UEDITOR_HOME_URL = '<%=basePath%>/public/jslib/ueditor1_4_3-utf8-jsp/';</script>
<script src="<%=basePath%>/public/jslib/ueditor1_4_3-utf8-jsp/ueditor.config.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>/public/jslib/ueditor1_4_3-utf8-jsp/ueditor.all.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>/public/jslib/ueditor1_4_3-utf8-jsp/ueditor.parse.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>/public/jslib/ueditor1_4_3-utf8-jsp/ueditor.parse.min.js" type="text/javascript" charset="utf-8"></script>
--%>

<%-- KindEditor --%>
<script type="text/javascript" src="<%=basePath%>/public/jslib/kindeditor-4.1.10/kindeditor.js"></script>

<%-- 引入jQuery --%>
<%
String User_Agent = request.getHeader("User-Agent");
if (StringUtils.indexOfIgnoreCase(User_Agent, "MSIE") > -1 && (StringUtils.indexOfIgnoreCase(User_Agent, "MSIE 6") > -1 || StringUtils.indexOfIgnoreCase(User_Agent, "MSIE 7") > -1 || StringUtils.indexOfIgnoreCase(User_Agent, "MSIE 8") > -1)) {
	out.println("<script src='" + basePath + "/public/jslib/jquery-1.9.1.js' type='text/javascript' charset='utf-8'></script>");
}else {
	out.println("<script src='" + basePath + "/public/jslib/jquery-2.0.3.js' type='text/javascript' charset='utf-8'></script>");
}
%>
<%-- 引入jquery扩展 --%>
<script src="<%=basePath%>/public/jslib/syExtJquery.js?version=<%=version%>" type="text/javascript" charset="utf-8"></script>

<%-- 引入Highcharts --%>
<script src="<%=basePath%>/public/jslib/Highcharts-3.0.6/js/highcharts.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>/public/jslib/Highcharts-3.0.6/js/modules/exporting.js" type="text/javascript" charset="utf-8"></script>
<%-- 引入Highcharts扩展 --%>
<script src="<%=basePath%>/public/jslib/syExtHighcharts.js?version=<%=version%>" type="text/javascript" charset="utf-8"></script>

<%-- 引入plupload --%>
<script type="text/javascript" src="<%=basePath%>/public/jslib/plupload-2.1.2/js/plupload.full.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/public/jslib/plupload-2.1.2/js/i18n/zh_CN.js"></script>
<%-- 引入json2 --%>
<script type="text/javascript" src="<%=basePath%>/public/jslib/json2.js"></script>
<%-- 引入json2 --%>
<%-- 引入EasyUI --%>
<link id="easyuiTheme" rel="stylesheet" href="<%=basePath%>/public/jslib/jquery-easyui-1.5/themes/<%=easyuiTheme%>/easyui.css" type="text/css">
<link rel="stylesheet" href="<%=basePath%>/public/jslib/jquery-easyui-1.5/themes/icon.css" type="text/css">
<script type="text/javascript" src="<%=basePath%>/public/jslib/jquery-easyui-1.5/jquery.min.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=basePath%>/public/jslib/jquery-easyui-1.5/jquery.easyui.min.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=basePath%>/public/jslib/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>
<%-- <script type="text/javascript" src="<%=basePath%>/public/jslib/jquery-easyui-1.5/jquery.easyui.patch.js"></script> --%>

<%-- 引入EasyUI Portal插件 --%>
<link rel="stylesheet" href="<%=basePath%>/public/jslib/jquery-easyui-portal/portal.css" type="text/css">
<script type="text/javascript" src="<%=basePath%>/public/jslib/jquery-easyui-portal/jquery.portal.js" charset="utf-8"></script>
<%-- 引入easyui扩展 --%>
<script src="<%=basePath%>/public/jslib/syExtEasyUI.js?version=<%=version%>" type="text/javascript" charset="utf-8"></script>

<%-- 引入扩展图标 --%>
<link rel="stylesheet" href="<%=basePath%>/public/style/syExtIcon.css?version=<%=version%>" type="text/css">

<%-- 引入自定义样式 --%>
<link rel="stylesheet" href="<%=basePath%>/public/style/syExtCss.css?version=<%=version%>" type="text/css">

<%-- 引入javascript扩展 --%>
<script src="<%=basePath%>/public/jslib/syExtJavascript.js?version=<%=version%>" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="<%=basePath%>/securityJsp/LodopFuncs.js"></script>
<!-- 引入 echarts.js -->
<script type="text/javascript" src="<%=basePath%>/public/jslib/echarts.min.js"></script>