<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    
    <title>大庆市公安局车辆调度管理系统</title>
    <jsp:include page="inc.jsp"></jsp:include>


	
	<script type="text/javascript">
		document.onkeydown = function(e) {
			var event = e || window.event;
			var code = event.keyCode || event.which || event.charCode;
			if (code == 13) {
				loginFun();
			}
		}		
		function loginFun() {
			
			if ($('form').form('validate')) {
                $.messager.progress({
                    text : '登录中请稍等....'
                });
				$.post(sys.contextPath + '/maintain/sysUser!doNotNeedSessionAndSecurity_login.cxl', $('form').serialize(), function(result) {
			$.messager.progress('close');
					if (result.success) {
						location.replace(sys.basePath + '/index.jsp');
					} else {
						alert(result.msg);
					}
						
				}, 'json').error(function() { alert("网络超时"); });

			}
		};

		function reset() {
			$('#username').val('');
			$('#password').val('');
		}

        
	</script>
	  <link href="login/css/login_style.css" type="text/css" rel="stylesheet"/>
  </head>
  
  <body>
    <div id="all">
      <div id="login">
    	<form method="post" class="form">
			<div class="login_main">
				<input  style="margin-left:120px;" id="username" name="sysUser.userLogin" class="easyui-validatebox" data-options="required:true"/>
				<input  style="margin-left:120px;"  id="password" class="margintop_1" name="sysUser.userPwd" type="password" class="easyui-validatebox" data-options="required:true"/>
				<input style="width:111px; height:36px; background:url(login/images/reset_1.png)" class="loginbtn_1" onclick="reset();"  type="button"/>
				<input style="width:111px; height:36px; background:url(login/images/submit_1.png)" class="loginbtn_2" onclick="loginFun();" type="button"/>
			</div>
       <%-- <div class="login_text">
            <input name="sysUser.userLogin" class="easyui-validatebox" data-options="required:true"/>
            <input name="sysUser.userPwd" type="password" class="easyui-validatebox" data-options="required:true" style="margin-top:16px;"/>
            <input class="loginbtn_1" type="button" onclick="loginFun()" style="width:50px;"/>
            <input style="width:50px" class="loginbtn_2" type="reset" value="" />
        	<p class="down_text"><a href="http://chrome.360.cn">推荐360浏览器下载</a></p>
        	<p class="down_text"><a href="http://dlsw.baidu.com/sw-search-sp/soft/41/23253/IE8-WindowsXP-x86-CHS.2728888507.exe">XP用户IE浏览器下载</a></p>


        </div>--%>
        </form>

        
      </div>
	</div>
  </body>

</html>