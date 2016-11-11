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
			if ($(':input[name="carModelMaintenance.mmainId"]').val().length > 0) {
				url = sys.contextPath + '/car/ModelMaintenance!update.cxl';
			} else {
				url = sys.contextPath + '/car/ModelMaintenance!save.cxl';
			}
			$.post(url, sys.serializeObject($('form')), function(result) {
				if (result.success) {
					$grid.datagrid('load');
					$dialog.dialog('destroy');
				} else {
					$pjq.messager.alert('提示', result.msg, 'error');
				}
			}, 'json');
		}
	};
	$(function() {
	        
		if ($(':input[name="carModelMaintenance.mmainId"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(sys.contextPath + '/car/ModelMaintenance!getById.cxl', {
				id : $(':input[name="carModelMaintenance.mmainId"]').val()
			}, function(result) {
				if (result.mmainId != undefined) {
					$('form').form('load', {
						'carModelMaintenance.mmainId' : result.mmainId,
						'carModelMaintenance.modelId' : result.modelId,
						'carModelMaintenance.mmainMileage' : result.mmainMileage,
						'carModelMaintenance.mmainInfo' : result.mmainInfo,
						'carModelMaintenance.dictIdFlag': result.dictIdFlag
					});
				}
				parent.$.messager.progress('close');
			}, 'json');
		}
		  $("#number").textbox('textbox').bind('keyup', function(e){
			        $("#number").textbox('setValue', $(this).val().replace(/\D/g,''));
		   });
	});
</script>
</head>
<body>
	<form method="post" class="form">
		<fieldset>
			<legend>维保模型基本信息</legend>
			<td><input name="carModelMaintenance.dictIdFlag"  type="hidden" /></td>	
			<table class="table" style="width: 100%;">
				<tr>
					<td>编号:</td>
					<td><input name="carModelMaintenance.mmainId" value="<%=id%>" readonly="readonly" /></td>
					<td>车辆种类:</td>
					<td><input name="carModelMaintenance.modelId" class="easyui-combobox textbox"
						data-options="panelHeight:'200',required:true,editable:false,
						valueField: 'modelId',
						textField: 'modelName',
						url: sys.contextPath+'/car/Model!doNotNeedSecurity_getCarMode.cxl'
						"/></td>
				</tr>
				<tr>
					<td>保养里程:</td>
					<td><input name="carModelMaintenance.mmainMileage" id="number" class="easyui-textbox"  data-options="required:true,validType:'maxLength[8]'" /></td>
					<td>保养内容:</td>
					<td><input name="carModelMaintenance.mmainInfo" class="easyui-validatebox" data-options="required:true" /></td>	
				</tr>
						
			</table>
		</fieldset>
	</form>
</body>
</html>