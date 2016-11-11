<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.system.entity.maintain.SessionInfo"%>
<%@ page import="com.system.entity.maintain.SysRole"%>
<%@ page import="com.system.entity.maintain.SysOrganization"%>
<%@ page import="com.system.entity.maintain.SysResource"%>
<%@ page import="com.system.entity.easyui.Tree"%>
<%@ page import="com.framework.util.DateUtil"%>
<%@ page import="com.framework.util.BeanUtils"%>
<%@ page import="com.framework.util.ConfigUtil"%>
<%@ page import="com.framework.util.StringUtil"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Set"%>
<%@ page import="java.util.HashSet"%>
<%
	String contextPath = request.getContextPath();
	SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
	Set<SysRole> roles = sessionInfo.getUser().getSysRoles();//用户的角色
	Set<SysOrganization> organizations = sessionInfo.getUser().getSysOrganization();//用户的机构
	List<SysResource> resources = new ArrayList<SysResource>();//用户可访问的资源
	for (SysRole role : roles) {
		resources.addAll(role.getSysResources());
	}
	resources = new ArrayList<SysResource>(new HashSet<SysResource>(resources));//去重
	List<Tree> resourceTree = new ArrayList<Tree>();
	for (SysResource resource : resources) {
		Tree node = new Tree();
		BeanUtils.copyNotNullProperties(resource, node);
		node.setText(resource.getResoName());
	    node.setPid(resource.getResoIdParent());
		node.setId(resource.getResoId());
		resourceTree.add(node);
	}
	String resourceTreeJson = com.alibaba.fastjson.JSON.toJSONString(resourceTree);
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../inc.jsp"></jsp:include>
<%
	out.println("<script>var resourceTreeJson = '" + resourceTreeJson + "';</script>");
%>
<script type="text/javascript">
	$(function() {
		$('#resources').tree({
			parentField : 'pid',
			data : eval("(" + resourceTreeJson + ")")
		});
	});
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',fit:true,border:false">
		<table style="width: 100%;">
			<tr>
				<td><fieldset>
						<legend>用户信息</legend>
						<table class="table" style="width: 100%;">
							<tr>
								<th>用户ID</th>
								<td><%=sessionInfo.getUser().getUserId()%></td>
								<th>登录名</th>
								<td><%=sessionInfo.getUser().getUserLogin()%></td>
							
							</tr>
							<tr>
								<th>姓名</th>
								<td><%=sessionInfo.getUser().getUserName()%></td>
								<th>联系电话</th>
								<td><%=sessionInfo.getUser().getUserPhone()%></td>
							</tr>
							<tr>
								<th>Mac地址</th>
								<td><%=sessionInfo.getUser().getUserMac()%></td>
							</tr>
							<tr>
								<th>创建时间</th>
								<td><%=DateUtil.dateToString(sessionInfo.getUser().getTimeCreate())%></td>
								<th>最后修改时间</th>
								<td><%=DateUtil.dateToString(sessionInfo.getUser().getTimeUpdate())%></td>
							</tr>
						</table>
					</fieldset></td>
			</tr>
			<tr>
				<td>
					<fieldset>
						<legend>权限信息</legend>
						<table class="table" style="width: 100%;">
							<thead>
								<tr>
									<th>角色</th>
									<th>机构</th>
									<th>权限</th>
								</tr>
							</thead>
							<tr>
								<td valign="top">
									<%
										out.println("<ul>");
										for (SysRole role : roles) {
											out.println(StringUtil.formateString("<li title='{1}'>{0}</li>", role.getRoleName(), role.getRoleMemo()));
										}
										out.println("</ul>");
									%>
								</td>
								<td valign="top">
									<%
										out.println("<ul>");
										for (SysOrganization organization : organizations) {
											out.println(StringUtil.formateString("<li>{0}</li>", organization.getOrgName()));
										}
										out.println("</ul>");
									%>
								</td>
								<td valign="top"><ul id="resources"></ul></td>
							</tr>
						</table>
					</fieldset>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>