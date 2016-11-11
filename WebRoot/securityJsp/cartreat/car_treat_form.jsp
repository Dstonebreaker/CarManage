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
			if ($(':input[name="carTreat.treatId"]').val().length > 0) {
				url = sys.contextPath + '/car/treat!update.cxl';
			} else {
				url = sys.contextPath + '/car/treat!save.cxl';
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
                     
			if ($(':input[name="carTreat.treatId"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(sys.contextPath + '/car/treat!getById.cxl', {
				id : $(':input[name="carTreat.treatId"]').val()
			}, function(result) {
				
				if (result.treatId != undefined) {
					$('#car').combobox('loadData',[{carId:result.carId,carNo:result.carNo}]);
					 $('#car').combobox('readonly',true);
					$('form').form('load', {
						'carTreat.treatId' : result.treatId,										
						'carTreat.carId' : result.carId,											
						'carTreat.dictIdTreatMode':result.dictIdTreatMode,
						'carTreat.treatReason':result.treatReason,
						'carTreat.treatTime':result.treatTime
						
					
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
			<legend>车辆基本信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<td>编号:</td>
					<td><input name="carTreat.treatId" value="<%=id%>"  class="easyui-validatebox textbox" readonly="readonly"  /></td>
					<td>车牌号:</td>
					<td><input id="car" name="carTreat.carId" 
					class="easyui-combobox textbox"
					data-options="panelHeight:'200',required:true,editable:false,
						valueField: 'carId',
						textField: 'carNo',
						url: sys.contextPath+'/car/carManage!doNotNeedSecurity_getUsercarList.cxl'
						" /></td>
				</tr>
				<tr>
				<td>处置方式:</td>
					<td><input id="carTreat_dictIdTreatMode" name="carTreat.dictIdTreatMode" class="easyui-combobox textbox"
						data-options="panelHeight:'200',required:true,editable:false,
						valueField: 'dictId',
						textField: 'dictName',
						url: sys.contextPath+'/maintain/dictionaryManage!doNotNeedSecurity_getByClassId.cxl?dictClassId=TreatMode'
						" /></td>				    
					<td>处置日期:</td>
					<td><input name="carTreat.treatTime" class="easyui-datebox"  data-options="required:true,editable:false" /></td>
					</tr>		
				<tr>
				<td>处置原因	</td>
				<td colspan="3"><input name="carTreat.treatReason" class="easyui-textbox" 
							data-options="multiline:true,validType:'maxLength[400]',width:'540'" style="height:50px" /></td>	
				</tr>
				 
			
					
			</table>
		</fieldset>

	</form>
	
</body>
</html>