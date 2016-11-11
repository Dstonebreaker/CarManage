<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.system.entity.maintain.SessionInfo"%>
<%
	String contextPath = request.getContextPath();
	SessionInfo sessionInfo = (SessionInfo) session.getAttribute("sessionInfo");
	
%>
<!DOCTYPE html>
<html>
<head>
<title>大庆市公安局车辆调度管理系统</title>
<object  id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0> 
       <embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed>
</object>
<jsp:include page="../inc.jsp"></jsp:include>
<script type="text/javascript">
	var mainMenu;
	var mainTabs;

	$(function() {
		/* 解除登录 */
		var loginFun = function() {
			if ($('#loginDialog form').form('validate')) {
				$('#loginBtn').linkbutton('disable');
				$.post(sys.contextPath + '/maintain/sysUser!doNotNeedSessionAndSecurity_unLock.cxl', $('#loginDialog form').serialize(), function(result) {
					if (result.success) {
						$('#loginDialog').dialog('close');
					} else {
						$.messager.alert('提示', result.msg, 'error', function() {
							$('#loginDialog form :input:eq(1)').focus();
						});
					}
					$('#loginBtn').linkbutton('enable');
				}, 'json');
			}
		};
		
		$('#loginDialog').show().dialog({
			modal : true,
			closable : false,
			iconCls : 'ext-icon-lock_open',
			buttons : [ {
				id : 'loginBtn',
				text : '登录',
				handler : function() {
					loginFun();
				}
			} ],
			onOpen : function() {
				$('#loginDialog form :input[name="sysUser.userPwd"]').val('');				
			}
		}).dialog('close');
		
		$('#contactDialog').show().dialog({
			modal : true,
			closable : false,
			iconCls : 'ext-icon-lock_open',
			buttons : [ {
				id : 'contactBtn',
				text : '验证',
				handler : function() {
					contactFun();
				}
			} ],
			onOpen : function() {
				$('#contactDialog form :input[name="code"]').val('');
			}
		}).dialog('close');
	

		$('#passwordDialog').show().dialog({
			modal : true,
			closable : true,
			iconCls : 'ext-icon-lock_edit',
			buttons : [ {
				text : '修改',
				handler : function() {
					if ($('#passwordDialog form').form('validate')) {
						$.post(sys.contextPath + '/maintain/sysUser!doNotNeedSecurity_updateCurrentuserPwd.cxl', {
							'sysUser.userPwd' : $('#pwd').val()
						}, function(result) {
							if (result.success) {
								$.messager.alert('提示', '密码修改成功！', 'info');
								$('#passwordDialog').dialog('close');
							}
						}, 'json');
					}
				}
			} ],
			onOpen : function() {
				$('#passwordDialog form :input').val('');
			}
		}).dialog('close');
		
		/* 左侧导航栏 */
		mainMenu = $('#mainMenu').tree({
			url : sys.contextPath + '/maintain/sysResource!doNotNeedSecurity_getMainMenu.cxl',
			parentField : 'pid',
			onClick : function(node) {
				if (node.attributes.url) {
					var src = sys.contextPath + node.attributes.url;
					if (!sys.startWith(node.attributes.url, '/')) {
						src = node.attributes.url;
					}
					if (node.attributes.target && node.attributes.target.length > 0) {
						window.open(src, node.attributes.target);
					} else {
						var tabs = $('#mainTabs');
						var opts = {
							title : node.text,
							closable : true,
							iconCls : node.iconCls,
							content : sys.formatString('<iframe src="{0}" allowTransparency="true" style="border:0;width:100%;height:99%;" frameBorder="0"></iframe>', src),
							border : false,
							fit : true
						};
						if(tabs.tabs('exists', opts.title)) {
							tabs.tabs('select', opts.title);
						} else {
							tabs.tabs('add', opts);
						}
					}
				}
			}
		});

		$('#mainLayout').layout('panel', 'center').panel({
			onResize : function(width, height) {
				sys.setIframeHeight('centerIframe', $('#mainLayout').layout('panel', 'center').panel('options').height - 5);
			}
		});

		/* 主 tab */
		mainTabs = $('#mainTabs').tabs({
			fit : true,
			border : false,
			tools : [ {
				text : '刷新',
				iconCls : 'ext-icon-arrow_refresh',
				handler : function() {
					var panel = mainTabs.tabs('getSelected').panel('panel');
					var frame = panel.find('iframe');
					try {
						if (frame.length > 0) {
							for (var i = 0; i < frame.length; i++) {
								frame[i].contentWindow.document.write('');
								frame[i].contentWindow.close();
								frame[i].src = frame[i].src;
							}
							if (navigator.userAgent.indexOf("MSIE") > 0) {// IE特有回收内存方法
								try {
									CollectGarbage();
								} catch (e) {
								}
							}
						}
					} catch (e) {
					}
				}
			}, {
				text : '关闭',
				iconCls : 'ext-icon-cross',
				handler : function() {
					var index = mainTabs.tabs('getTabIndex', mainTabs.tabs('getSelected'));
					var tab = mainTabs.tabs('getTab', index);
					if (tab.panel('options').closable) {
						mainTabs.tabs('close', index);
					} else {
						$.messager.alert('提示', '[' + tab.panel('options').title + ']不可以被关闭！', 'error');
					}
				}
			} ]
		});

	});
	//页面上点击更多打开选项卡
 var tabs=function(url,titles){
		if (url) {
			var src = sys.contextPath + url;
			var tabc = $('#mainTabs');
			
				var opts = {
					title : titles,
					closable : true,
					content : sys.formatString('<iframe src="{0}" allowTransparency="true" style="border:0;width:100%;height:99%;" frameBorder="0"></iframe>', src),
					border : false,
					fit : true
				};
				if (tabc.tabs('exists', opts.title)) {
					tabc.tabs('select', opts.title);
				} else {
					tabc.tabs('add', opts);
				}
				
		}
	};
	
	
	
</script>
</head>
<body id="mainLayout" class="easyui-layout">
	<!-- 最上面的 -->
	<div data-options="region:'north',href:'<%=contextPath%>/securityJsp/north.jsp'" style="height: 70px; overflow: hidden;" class="logo"></div>
	<!-- 导航栏 -->
	<div data-options="region:'west',href:'',split:true" title="导航" style="width: 200px; padding: 10px;">
		<ul id="mainMenu"></ul>
	</div>
	<!-- tab -->
	<div data-options="region:'center'" style="overflow: hidden;">
		<div id="mainTabs">
			<div title="首页" data-options="iconCls:'ext-icon-heart'">
				<iframe src="<%=contextPath%>/welcome.jsp" allowTransparency="true" style="border: 0; width: 100%; height: 99%;" frameBorder="0"></iframe>
			</div>
		</div>
	</div>
	
	<!-- 最下面 -->
	<div data-options="region:'south',href:'<%=contextPath%>/securityJsp/south.jsp',border:false" style="height: 30px; overflow: hidden;"></div>

	<!-- 隐藏的部分 -->
	<div id="loginDialog" title="解锁登录" style="display: none;">
		<form method="post" class="form" onsubmit="return false;">
			<table class="table">
				<tr>
					<th width="50">登录名</th>
					<td><%=sessionInfo.getUser().getUserLogin()%><input name="sysUser.userLogin" readonly="readonly" type="hidden" value="<%=sessionInfo.getUser().getUserLogin()%>" /></td>
				</tr>
				<tr>
					<th>密码</th>
					<td><input name="sysUser.userPwd" type="password" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
			</table>
		</form>
	</div>
	

	<div id="passwordDialog" title="修改密码" style="display: none;">
		<form method="post" class="form" onsubmit="return false;">
			<table class="table">
				<tr>
					<th>新密码</th>
					<td><input id="pwd" name="sysUser.userPwd" type="password" class="easyui-validatebox" data-options="required:true,validType:'length[6,20]'" /></td>
				</tr>
				<tr>
					<th>重复密码</th>
					<td><input type="password" class="easyui-validatebox" data-options="required:true,validType:'eqPwd[\'#pwd\']'" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>