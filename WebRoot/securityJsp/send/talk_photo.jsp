<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ page import="com.framework.util.WebMsgUtil"%>
<%@ page import="static com.framework.util.WebMsgUtil.IMGTYPE_PERSON"%>
<%@ page import="static com.framework.util.WebMsgUtil.IMGTYPE_CARD"%>
<%@ page import="com.framework.util.SecurityUtil"%>

<%
    String contextPath = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + contextPath + "/";
    String type = request.getParameter("type");
    SecurityUtil securityUtil = new SecurityUtil(session);
%>
<html>
<head>

<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">
        var index;
        var strCardFile;
 var data ;
        $(function () {
            try {
                destroy();
                CmCaptureOcx.Initial();
                var total = CmCaptureOcx.GetDevCount();
                CmCaptureOcx.BestSize();

                for (i = 0; i < total; i++) {
                    var DevEle = CmCaptureOcx.GetDevFriendName(i);
                    if (DevEle == "S520-2" && '<%=type%>' == '<%=IMGTYPE_PERSON%>') {
                        index = i;
                        CmCaptureOcx.CusCrop(true);
                        CmCaptureOcx.StartRun(index);
                        CmCaptureOcx.SetResolution(5);
                        CmCaptureOcx.SetFileType(3);
                        return;
                    } else if (DevEle.indexOf("CamScanner") > 0 && '<%=type%>' == '<%=IMGTYPE_CARD%>') {
                        index = i;
                        CmCaptureOcx.AutoCrop(true);
                        CmCaptureOcx.StartRun(index);
                        CmCaptureOcx.SetResolution(4);
                        CmCaptureOcx.SetFileType(3);
                        return;
                    }
                }

            } catch (e) {

                parent.$.messager.alert('错误', '没有加载高拍仪控件', 'error');
            }
        });
        var successful = false;
        var photourl;
        var submitForm = function ($dialog) {
			if(data==undefined){
			 alert("数据获取中!请稍后再试");
			return;
			}
            $.messager.progress({
                text: '正在上传图片请稍等....'
            });
            var url = sys.contextPath + '/car/send!doNotNeedSecurity_upload.cxl';
           
            $('#main').hide();
            $.post(url, {data: data, type: '<%=type%>'}, function (result) {
                if (result.success) {
                    $.messager.alert('提示', result.msg, 'info');
                    destroy();
                    $dialog.dialog('destroy');
                } else {
                    $.messager.alert('提示', result.msg, 'error');
                    $.messager.progress('close');
                }
            },'json');

        };

        var talkphoto = function () {
            CmCaptureOcx.SetJpgQuanlity(10);
            if ('<%=type%>' == '<%=IMGTYPE_PERSON%>') {
			strCardFile = "e:\\test\\personImage";
			strCardFile += ".jpg";
			CmCaptureOcx.CaptureImage(strCardFile);
		} else {
			strCardFile = "e:\\test\\cardImage";
			strCardFile += ".jpg";
			CmCaptureOcx.CaptureImage(strCardFile);
		}
		CmCaptureOcx.PreviewFile(strCardFile);
		setTimeout("getData()",1);
		
	};
	//获取base64数据
	var getData=function(){
		data = CmCaptureOcx.CaptureToBase64();
		};
	
	var destroy = function() {
		try {
			CmCaptureOcx.Destory();
		} catch (e) {
			console.info(e.message);
		}
	};
	var getObjectURL = function(file) {
		var url = null;
		if (window.createObjectURL != undefined) { // basic
			url = window.createObjectURL(file);
		} else if (window.URL != undefined) { // mozilla(firefox)
			url = window.URL.createObjectURL(file);
		} else if (window.webkitURL != undefined) { // webkit or chrome
			url = window.webkitURL.createObjectURL(file);
		}
		return url;
	}
</script>
</head>

<div id="main">
	<%
		if (securityUtil.havePermission("dytm")) {
	%>
	<object id="PSKPrn"
		classid="clsid:81C07687-3353-4ABA-B108-94BCE81E5CBA"
		codebase="<%=basePath%>public/ocx/print/PSKPrn.ocx#version=1,0,0,1"
		width="0" height="0"> </object>
	<%
		}
	%>
	<OBJECT id="CmCaptureOcx" style="margin:2%;width:95%;height:85%;"
		classid="clsid:3CA842C5-9B56-4329-A7CA-35CA77C7128D"
		codebase="<%=contextPath%>public/ocx/cmcaptrue/CmCaptureOcx.ocx#version=1,0,0,1">
	</OBJECT>


	<%--<img id="img" src="<%=contextPath%>/public/style/images/pic_none.png" /></td>--%>


</div>
</body>
</html>
