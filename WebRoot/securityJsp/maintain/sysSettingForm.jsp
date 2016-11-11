<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<%
	String settId = request.getParameter("id");
	if (settId == null) {
		settId = "";
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
			if ($(':input[name="sysSetting.settId"]').val().length > 0) {
				url = sys.contextPath + '/maintain/sysSetting!update.cxl';
			} 
				parent.$.messager.progress({
				text: '数据处理中....'
			});
			$.post(url, sys.serializeObject($('form')), function(result) {
			parent.$.messager.progress('close');
				if (result.success) {
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
		if ($(':input[name="sysSetting.settId"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(sys.contextPath + '/maintain/sysSetting!getById.cxl', {
				id : $(':input[name="sysSetting.settId"]').val()
			}, function(result) {
				if (result.settId != undefined) {
					$('form').form('load', {
						'sysSetting.settId' : result.settId,
						'sysSetting.settName' : result.settName,
						'sysSetting.settValue' : result.settValue,
						'sysSetting.settUnit' : result.settUnit
					});
				}
				parent.$.messager.progress('close');
			}, 'json');
		}
		
	});
	$(function() {
	 $("#number").textbox('textbox').bind('keyup', function(e){
     $("#number").textbox('setValue', $(this).val().replace(/\D/g,''));
		  });
	});
</script>
</head>
<body>
	<form method="post" class="form">
		<fieldset>
			<legend>设置基本信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<td>编号:</td>
					<td><input name="sysSetting.settId" value="<%=settId%>" readonly="readonly" /></td>
					<td>设置名称:</td>
					<td><input name="sysSetting.settName" readonly="readonly" /></td>
				</tr>
				<tr>
					<td>设置内容:</td>
					<td><input name="sysSetting.settValue" id="number" class="easyui-textbox" data-options="required:true,validType:'maxLength[11]'" /></td>
					<td>内容单位:</td>
					<td><input name="sysSetting.settUnit" readonly="readonly"/></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>