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
			if ($(':input[name="carTransform.transformId"]').val().length > 0) {
				url = sys.contextPath + '/car/carTransform!update.cxl';
			} else {
				url = sys.contextPath + '/car/carTransform!save.cxl';
			}
			$.post(url, sys.serializeObject($('form')), function(result) {
				if (result.success) {
					$grid.datagrid('reload');
					$dialog.dialog('destroy');
				} else {
					$pjq.messager.alert('提示', result.msg, 'error');
				}
			}, 'json');
		}
	};
	$(function() {	
                     
			if ($(':input[name="carTransform.transformId"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(sys.contextPath + '/car/carTransform!getById.cxl', {
				id : $(':input[name="carTransform.transformId"]').val()
			}, function(result) {
				
				if (result.transformId != undefined) {
					$('form').form('load', {
						'carTransform.transformId' : result.transformId,										
						'carTransform.carId' : result.carId,											
						'carTransform.transformBeforeOrgId':result.transformBeforeOrgId,
						'carTransform.transformAfterOrgId':result.transformAfterOrgId,
						'carTransform.dictIdDisposalType':result.dictIdDisposalType,
						'carTransform.dictIdHandleType':result.dictIdHandleType
					
				   });
				}
 				parent.$.messager.progress('close');
			}, 'json');
		}
	});
</script>
<style>
.textbox {
	height: 20px;
	width: 200px;
}
</style>
</head>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<form method="post" class="form">
		<fieldset>
			<legend>车改基本信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<td>编号:</td>
					<td><input name="carTransform.transformId" value="<%=id%>"  class="easyui-validatebox textbox" readonly="readonly"  /></td>
					<td>车牌号:</td>
					<td><input name="carTransform.carId" 
					class="easyui-combobox textbox"
					data-options="panelHeight:'200',required:true,editable:false,
						valueField: 'carId',
						textField: 'carNo',
						url: sys.contextPath+'/car/carManage!doNotNeedSecurity_getUsercarList.cxl'
						" /></td>
				</tr>
				<tr>			
				<td>车改前单位:</td>
					<td><input name="carTransform.transformBeforeOrgId" class="easyui-combobox textbox"
						data-options="panelHeight:'200',required:true,editable:false,
						  valueField: 'orgIdManager',
                			textField: 'orgName',
						  url: sys.contextPath + '/maintain/sysOrganization!doNotNeedSecurity_getOrg.cxl?userId=1' 
						" /></td>				    
				<td>车改后单位:</td>
					<td><input name="carTransform.transformAfterOrgId" class="easyui-combobox textbox"
						data-options="panelHeight:'200',required:true,editable:false,
						  valueField: 'orgIdManager',
                			textField: 'orgName',
						  url: sys.contextPath + '/maintain/sysOrganization!doNotNeedSecurity_getOrg.cxl?userId=1' 
						" /></td>				    
						
					</tr>		
				<tr>
				<td>处置方式:</td>
					<td><input name="carTransform.dictIdDisposalType" class="easyui-combobox textbox"
						data-options="panelHeight:'200',required:true,editable:false,
						valueField: 'dictId',
						textField: 'dictName',
						url: sys.contextPath+'/maintain/dictionaryManage!doNotNeedSecurity_getByClassId.cxl?dictClassId=DisposalType'
						" /></td>				    
				<td>处理方式:</td>
					<td><input id="carTransform_dictIdTreatMode" name="carTransform.dictIdHandleType" class="easyui-combobox textbox"
						data-options="panelHeight:'200',required:true,editable:false,
						valueField: 'dictId',
						textField: 'dictName',
						url: sys.contextPath+'/maintain/dictionaryManage!doNotNeedSecurity_getByClassId.cxl?dictClassId=HandleType'
						" /></td>		
					</tr>	
					
							
			</table>
		</fieldset>

	</form>
	
</body>
</html>