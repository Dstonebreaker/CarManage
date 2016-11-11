<%@page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="com.framework.util.ConfigUtil" %>
<%@page import="com.system.entity.maintain.SessionInfo" %>
<%@page import="com.framework.util.WebMsgUtil" %>
<%@page import="com.framework.util.SecurityUtil" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
    SecurityUtil securityUtil = new SecurityUtil(session);
%>

<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">

    <title>派车记录表</title>

    <jsp:include page="../../inc.jsp"></jsp:include>

</head>

<body class="easyui-layout" data-options="fit:true,border:false">
<div data-options="region:'north',border:true" class="datagrid-toolbar">
	<table style="width:100%">
			<tr>
				<td>
		    <form method="post" id="searchForm" name="searchForm" target="report">
		         派车时间：<input id="startTime" class="easyui-datebox" name="startTime" data-options="required:true,editable:false,validType:'md[\'2016-04-18 12:18\']'" style="width:150px"/>
			<!-- 结束时间：<input id="endTime" class="easyui-datebox" name="endTime" data-options="editable:false,validType:'md[\'2016-04-18 12:18\']'" style="width:150px"/> -->
		        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'ext-icon-zoom'"
		           onclick="searchData()">查询</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'ext-icon-zoom_out'"
		           onclick="doClear()">清空条件</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'ext-icon-zoom_out'"
		           onclick="prn1_preview()">打印</a>
		    </form>
				</td>
			</tr>
		</table>
</div>
<form id="sendreport" name="sendreport" method="post" target="report" style="display:none">
</form>
<div id="content" data-options="region:'center',fit:true,border:false" style="height:100%;margin:0px;padding:0px">

   <iframe name="report" width="100%" height="650" scrolling="auto" src="" style="border:0px"></iframe>

</div>

</body>
<script>

formatterDate = function(date) {
	var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
	var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0"
	+ (date.getMonth() + 1);
	return date.getFullYear() + '-' + month + '-' + day;
	};

	window.onload = function () { 
	$('#startTime').datebox('setValue', formatterDate(new Date()));
	};
	function doClear() {
        $("#searchForm").form('clear');
    }
	
	 function searchData(){
		 if ($("#searchForm").form("validate")){
			document.searchForm.action='<%=basePath%>car/sendRecord.cxl';
			document.searchForm.submit();
		 }
		}
    var LODOP; //声明为全局变量
    function prn1_preview() {
    	$.messager.confirm('确认', '是否打印派车记录表?', function (r) {
            if (r) {
            	CreateOneFormPage();
                LODOP.PREVIEW();
            }
        });
        
    };
    function prn1_print() {
        CreateOneFormPage();
        LODOP.PRINT();
    };
    function prn1_printA() {
        CreateOneFormPage();
        LODOP.PRINTA();
    };
    function CreateOneFormPage() {
        myIpAddress = '<%=basePath%>';
        LODOP = getLodop();
        LODOP.PRINT_INIT("");
        LODOP.SET_PRINT_PAGESIZE(2,0,0,"");
		LODOP.SET_PREVIEW_WINDOW(0,0,0,0,0,"");
		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",1);//横向时的正向显示
        strHtml = document.getElementsByTagName("iframe")[0].contentWindow.document.documentElement.innerHTML;
        strHtml = strHtml.replace('<!--[if IE]-->', '').replace('<!--[endif]-->', '');
        LODOP.ADD_PRINT_HTM(0, 0, "100%", "100%", strHtml);
    };
</script>
</html>
