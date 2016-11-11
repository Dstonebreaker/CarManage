<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<%
	String id = request.getParameter("id");
	if (id == null) {
		id = "";
	}
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">
	var submitForm = function($dialog, $grid, $pjq) {
		var nodes = $('#tree').tree('getChecked', [ 'checked', 'indeterminate' ]);
		var ids = [];
		for (var i = 0; i < nodes.length; i++) {
			ids.push(nodes[i].id);
		}
			parent.$.messager.progress({
				text: '数据处理中....'
			});
		$.post(sys.contextPath + '/maintain/sysRole!grant.cxl', {
			id : $(':input[name="sysRole.roleId"]').val(),
			ids : ids.join(',')
		}, function(result) {
		parent.$.messager.progress('close');
			if (result.success) {
				$dialog.dialog('destroy');
			} else {
				$pjq.messager.alert('提示', result.msg, 'error');
			}
			$pjq.messager.alert('提示', '授权成功！', 'info');
		}, 'json');
	};
	$(function() {
		parent.$.messager.progress({
			text : '数据加载中....'
		});
		$('#tree').tree({
			url : sys.contextPath + '/maintain/sysResource!doNotNeedSecurity_getResourcesTree.cxl',
			parentField : 'pid',
			checkbox : true,
			formatter : function(node) {
				return node.text;
			},
			onLoadSuccess : function(node, data) {
				$.post(sys.contextPath + '/maintain/sysResource!doNotNeedSecurity_getRoleResources.cxl', {
					id : $(':input[name="sysRole.roleId"]').val()
				}, function(result) {
					if (result) {
						for (var i = 0; i < result.length; i++) {
							var node = $('#tree').tree('find', result[i].resoId);
							if (node) {
								var isLeaf = $('#tree').tree('isLeaf', node.target);
								if (isLeaf) {
									$('#tree').tree('check', node.target);
								}
							}
						}
					}
					parent.$.messager.progress('close');
				}, 'json');
			}
		});
	});
</script>
</head>
<body>
	<input name="sysRole.roleId" value="<%=id%>" readonly="readonly" type="hidden" />
	<fieldset>
		<legend>角色授权</legend>
		<ul id="tree"></ul>
	</fieldset>
</body>
</html>