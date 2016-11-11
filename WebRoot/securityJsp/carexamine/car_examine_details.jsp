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
	
	$(function() {		
			
		if ($(':input[name="carExamine.examId"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(sys.contextPath + '/car/Examine!getAlertById.cxl', {
				id : $(':input[name="carExamine.examId"]').val()
			}, function(result) {
				if (result.examId != undefined) {
					$('#car').combobox('loadData',[{carId:result.carId,carNo:result.carNo}]);
					$('form').form('load', {
						'carExamine.examId':result.examId,
						'carExamine.carId' : result.carId,
						'carExamine.examCycle' : result.examCycle,
						'carExamine.examValidStartTime' : result.examValidStartTime,						
						'carExamine.examValidOverTime':result.examValidOverTime,
						 'carExamine.examMoney':result.examMoney,
						'carExamine.userId':result.userId,						
						'carExamine.examMemo':result.examMemo
						
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
<body class="easyui-layout" data-options="fit:true,border:false">
	<form method="post" class="form">	
		<fieldset>
			<legend>年审基本信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<td>编号:</td>
					<td><input name="carExamine.examId" class="easyui-validatebox textbox" value="<%=id%>" readonly="readonly"  readonly="readonly"/></td>
					<td>车牌号:</td>
					<td><input id="car" name="carExamine.carId" 
					class="easyui-combobox textbox"
					data-options="panelHeight:'200',required:true,editable:false,
						valueField: 'carId',
						textField: 'carNo',
						url: sys.contextPath+'/car/carManage!doNotNeedSecurity_getUsercarList.cxl'
						"  readonly="readonly"/></td>
					
				</tr>
				<tr>						
					<td>生效日期:</td>
					<td><input id="startTime" name="carExamine.examValidStartTime" class="easyui-datebox textbox" data-options="required:true,editable:false"  readonly="readonly"/></td>
					<td>终止日期:</td>
					<td><input id ="endTime" name="carExamine.examValidOverTime" class="easyui-datebox textbox" data-options="required:true,editable:false" readonly="readonly" /></td>					
				</tr>
				<tr>
				<td>审核周期（年）:</td>
					<td><input  name="carExamine.examCycle" class="easyui-combobox textbox"
						data-options="required:true,panelHeight:'auto',editable:false,
						valueField:'value',
						textField:'text',						
						data : [{
							value: 2,
							text: '二年',
							selected : true
						},{
							value: 1,
							text: '一年'
						},{
							value: 0.5,
							text: '半年'
						}]
					" readonly="readonly" /></td>							
					<td>经办人:</td>
					<td><input name="carExamine.userId" class="easyui-combobox textbox"
					data-options="panelHeight:'200',required:true,editable:false,
						valueField: 'userId',
						textField: 'userName',
						url: sys.contextPath+'/car/Examine!doNotNeedSecurity_getAllUserList.cxl'
						" readonly="readonly"/></td>
				</tr>
					
				<tr>	
				<td>金额:</td>
					<td><input name="carExamine.examMoney" class="easyui-validatebox textbox" data-options="required:true" readonly="readonly" /></td>			
				</tr>	
				<tr>					
					<td>备注:</td>
					<td colspan="3"><input  name="carExamine.examMemo"  class="easyui-textbox" 
							data-options="multiline:true,validType:'maxLength[400]',width:'505'" style="height:50px" readonly="readonly"/></td>	
				</tr>		
			</table>
		</fieldset>
	</form>
</body>
</html>