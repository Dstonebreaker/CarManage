<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<%
	String roleId = request.getParameter("id");
	if (roleId == null) {
		roleId = "";
	}
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">
	var submitForm = function($dialog, $grid, $pjq) {
		if ($('form').form('validate')) {
			var url;
			if ($(':input[name="sysRole.roleId"]').val().length > 0) {
				url = sys.contextPath + '/maintain/sysRole!update.cxl';
			} else {
				url = sys.contextPath + '/maintain/sysRole!save.cxl';
			}
			parent.$.messager.progress({
				text: '数据处理中....'
			});
			$.post(url, sys.serializeObject($('form')), function(result) {
				if (result.success) {
					parent.$.messager.progress('close');
				$pjq.messager.alert('提示', result.msg, 'info');
					$grid.datagrid('load');
					$dialog.dialog('destroy');
				} else {
					$pjq.messager.alert('提示', result.msg, 'error');
				}
			}, 'json');
		}
	};
	$(function() {
		if ($(':input[name="sysRole.roleId"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(sys.contextPath + '/maintain/sysRole!getById.cxl', {
				id : $(':input[name="sysRole.roleId"]').val()
			}, function(result) {
				if (result.roleId != undefined) {
					$('form').form('load', {
						'sysRole.roleId' : result.roleId,
						'sysRole.roleName' : result.roleName,
						'sysRole.roleMemo' : result.roleMemo,
						'sysRole.userIdCreate' : result.userIdCreate,
						'sysRole.timeCreate' : result.timeCreate,
					});
				}
				parent.$.messager.progress('close');
			}, 'json');
		}
	});
</script>
</head>
<body>
	<form method="post" class="form">
		<fieldset>
			<legend>角色基本信息</legend>
			<input type="hidden" name="sysRole.userIdCreate">
			<input type="hidden" name="sysRole.timeCreate">
			<table class="table" style="width: 100%;">
				<tr>
					<td>编号:</td>
					<td><input name="sysRole.roleId" value="<%=roleId%>" readonly="readonly" /></td>
					<td>角色名称:</td>
					<td><input name="sysRole.roleName" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<td>角色描述:</td>
					<td><input name="sysRole.roleMemo" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>