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
		if ($('form').form('validate')) {
			var url;
			if ($(':input[name="itObdCarFaultCode.faultCode"]').val().length > 0) {
				url = sys.contextPath + '/maintain/ObdCode!update.cxl';
			} 
				parent.$.messager.progress({
				text: '数据处理中....'
			});
			debugger;
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
		if ($(':input[name="itObdCarFaultCode.faultCode"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(sys.contextPath + '/maintain/ObdCode!getById.cxl', {
				id : $(':input[name="itObdCarFaultCode.faultCode"]').val()
			}, function(result) {
				if (result.faultCode != undefined) {
					$('form').form('load', {
						'itObdCarFaultCode.faultCode' : result.faultCode,
						'itObdCarFaultCode.faultNameChinese' :result.faultNameChinese,
						'itObdCarFaultCode.faultIsSend' : result.faultIsSend
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
			<legend>设置基本信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<td>故障码编码号:</td>
					<td><input name="itObdCarFaultCode.faultCode" value="<%=id%>" readonly="readonly" /></td>
					<td>故障码名称:</td>
					<td><input name="itObdCarFaultCode.faultNameChinese"  class="easyui-textbox" data-options="required:true,validType:'maxLength[11]'" /></td>
				</tr>
				<tr>
					<td>是否发送短信:</td>
					 <td><select  name="itObdCarFaultCode.faultIsSend"  class="easyui-combobox" style="width:100px;">
				      <option value="0">不发送</option>
				      <option value="1">发送</option>
				</select></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>