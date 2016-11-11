<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<%
	String resoId = request.getParameter("resoId");
	if (resoId == null) {
		resoId = "";
		out.print(resoId);
	}
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">
	var submitForm = function($dialog, $grid, $pjq, $mainMenu) {
		if ($('form').form('validate')) {
			var url;
			if ($(':input[name="sysResource.resoId"]').val().length > 0) {
				url = sys.contextPath + '/maintain/sysResource!update.cxl';
			} else {
				url = sys.contextPath + '/maintain/sysResource!save.cxl';
			}
			parent.$.messager.progress({
				text: '数据处理中....'
			});
			$.post(url, sys.serializeObject($('form')), function(result) {
				if (result.success) {
					parent.$.messager.progress('close');
				$pjq.messager.alert('提示', result.msg, 'info');
					$grid.treegrid('reload');
					$dialog.dialog('destroy');
					$mainMenu.tree('reload');
				} else {
					$pjq.messager.alert('提示', result.msg, 'error');
				}
			}, 'json');
		}
	};
	
	$(function() {
		if ($(':input[name="sysResource.resoId"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(sys.contextPath + '/maintain/sysResource!getById.cxl', {
			id : $(':input[name="sysResource.resoId"]').val(),
			}, function(result) {
				if (result.resoId != undefined) {
					$('form').form('load', {
						'sysResource.resoId' : result.resoId,
						'sysResource.resoName' : result.resoName,
						'sysResource.resoNo' : result.resoNo,
						'sysResource.resoUrl' : result.resoUrl,
						'sysResource.dictIdResoType' : result.dictIdResoType,
						'sysResource.resoIdParent' : result.resoIdParent,
						'sysResource.resoIndex' : result.resoIndex,	
						'sysResource.timeCreate' : result.timeCreate,	
						'sysResource.dictIdFlag' : result.dictIdFlag,	
						'sysResource.userIdCreate' : result.userIdCreate,	
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
			<legend>资源基本信息</legend>
			<input type="hidden" name="sysResource.timeCreate">
			<input type="hidden" name="sysResource.dictIdFlag">
			<input type="hidden" name="sysResource.userIdCreate">
			<table class="table" style="width: 100%;">
		      	<tr>
				<td><input name="sysResource.resoId"id="resoId" value="<%=resoId%>" type="hidden" /></td>
		      	</tr>
				<tr>
					<td>编号:</td>
					<td><input name="sysResource.resoNo" class="easyui-validatebox" data-options="required:true,validType:'maxLength[100]'" /></td>
					<td>资源名称:</td>
					<td><input name="sysResource.resoName" class="easyui-validatebox" data-options="required:true,validType:'maxLength[100]'" /></td>
				</tr>
				<tr>
					<td>资源路径:</td>
					<td><input name="sysResource.resoUrl" class="easyui-validatebox" data-options="validType:'maxLength[200]'" /></td>
					<td>资源类型:</td>
				 <td><select name="sysResource.dictIdResoType" class="easyui-combobox" data-options="required:true,editable:false,valueField:'dictId',textField:'dictName',url:'<%=contextPath%>/maintain/dictionaryManage!doNotNeedSecurity_getByClassId.cxl?dictClassId=Resource',panelHeight:'auto'" style="width: 155px;"></select></td>
				</tr>
				<tr>
					<td>上级资源:</td>
					<td><select id="resoIdParent" name="sysResource.resoIdParent" class="easyui-combotree" data-options="editable:false,idField:'id',textField:'text',parentField:'pid',url:'<%=contextPath%>/maintain/sysResource!doNotNeedSecurity_getMainMenu.cxl'" style="width: 155px;"></select><img class="iconImg ext-icon-cross" onclick="$('resoIdParent').combotree('clear');" title="清空" /></td>
					<td>显示顺序:</td>
					<td><input name="sysResource.resoIndex" class="easyui-numberspinner" data-options="required:true,min:0,max:100000,editable:false" style="width: 155px;" value="100" /></td>
					
				</tr>
				
				
			</table>
		</fieldset>
	</form>
</body>
</html>