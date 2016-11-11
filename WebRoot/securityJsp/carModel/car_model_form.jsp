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
			if ($(':input[name="carModel.modelId"]').val().length > 0) {
				url = sys.contextPath + '/car/Model!update.cxl';
			} else {
				url = sys.contextPath + '/car/Model!save.cxl';
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
	
            var carBrand = $('#brand').combobox({
               url: sys.contextPath+'/maintain/dictionaryManage!doNotNeedSecurity_getByClassId.cxl?dictClassId=CarBrand',
                panelHeight:'200',
                required:true,
                editable: false,
                valueField: 'dictId',
                textField: 'dictName',
                onSelect: function (record) {                
                	 $('#series').combobox({
                        disabled: false,
                        editable: false,
                        url: sys.contextPath+'/maintain/dictionaryManage!doNotNeedSecurity_getSeriesByBrand.cxl?brandId='+record.dictId,
                        valueField: 'dictIdRelate',
                        textField: 'dictName',
                        onLoadSuccess:function(){
                       	 $('#series').combobox('clear');                    	 
                        }
                    }).combobox('clear');
                }
            
            });
            var carSeries = $('#series').combobox({
            	prompt:'请先填写车辆品牌' ,
                disabled: true,
                valueField: 'dictId',
                textField: 'dictName'
            });
           
       
		
		
		
		if ($(':input[name="carModel.modelId"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(sys.contextPath + '/car/Model!getById.cxl', {
				id : $(':input[name="carModel.modelId"]').val()
			}, function(result) {
				if (result.modelId != undefined) {
					$('form').form('load', {
						'carModel.modelId' : result.modelId,
						'carModel.dictIdBrand' : result.dictIdBrand,
						'carModel.dictIdCarSeries' : result.dictIdCarSeries,
						'carModel.modelName' : result.modelName,
						'carModel.dictIdProductionArea':result.dictIdProductionArea,
						'carModel.dictIdUseOilType':result.dictIdUseOilType,
						'carModel.dictIdIsT':result.dictIdIsT,
						'carModel.modelPeople':result.modelPeople,
						'carModel.modelLoad':result.modelLoad,
						'carModel.modelAirDisplacement':result.modelAirDisplacement,
						'carModel.modelMemo':result.modelMemo,
						'carModel.modelMaintenanceMonth':result.modelMaintenanceMonth
					});
				}
				 $('#series').combobox({
                     disabled: false,
                     url: sys.contextPath+'/maintain/dictionaryManage!doNotNeedSecurity_getSeriesByBrand.cxl?brandId='+result.dictIdBrand,
                     valueField: 'dictIdRelate',
                     textField: 'dictName',
                     onLoadSuccess:function(){
                    	 $('#series').combobox('setValue', result.dictIdCarSeries);                    	 
                     }
                 });
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
			<legend>车型基本信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<td>编号:</td>
					<td><input name="carModel.modelId"  class="easyui-validatebox textbox" value="<%=id%>" readonly="readonly" /></td>
					<td>车辆品牌:</td>
					<td><input id = "brand" name="carModel.dictIdBrand" /></td>
					
				</tr>
				<tr>
					<td>车系:</td>
					<td><input id ="series" name="carModel.dictIdCarSeries" data-options="required:true" /></td>
					
					<td>车型:</td>
					<td><input name="carModel.modelName" class="easyui-validatebox textbox"  data-options="required:true,validType:'maxLength[50]'" /></td>
				</tr>
								<tr>
					<td>产地:</td>
					<td><input name="carModel.dictIdProductionArea" class="easyui-combobox textbox"
						data-options="panelHeight:'200',required:true,editable:false,
						valueField: 'dictId',
						textField: 'dictName',
						url: sys.contextPath+'/maintain/dictionaryManage!doNotNeedSecurity_getByClassId.cxl?dictClassId=ProductionArea'
						"/></td>
					<td>燃油类型:</td>
					<td><input name="carModel.dictIdUseOilType" class="easyui-combobox textbox"
						data-options="panelHeight:'200',required:true,editable:false,
						valueField: 'dictId',
						textField: 'dictName',
						url: sys.contextPath+'/maintain/dictionaryManage!doNotNeedSecurity_getByClassId.cxl?dictClassId=OilType'
						"/></td>
				</tr>
					<tr>
					<td>是否带 T:</td>
					<td><input name="carModel.dictIdIsT" class="easyui-combobox textbox"
						data-options="panelHeight:'200',required:true,editable:false,
						valueField: 'dictId',
						textField: 'dictName',
						url: sys.contextPath+'/maintain/dictionaryManage!doNotNeedSecurity_getByClassId.cxl?dictClassId=IsT'
						"/></td>
					<td>载客量(人):</td>
					<td><input name="carModel.modelPeople" class="easyui-validatebox textbox" data-options="required:true,validType:['intOrFloat','maxLength[9]']" /></td>
				</tr>
				<tr>
					<td>载重量(吨):</td>
					<td><input name="carModel.modelLoad" class="easyui-validatebox textbox"data-options="required:true,min:0,max:200,validType:['intOrFloat','maxLength[8]']"  /></td>
					<td>排气量(升):</td>
					<td><input name="carModel.modelAirDisplacement"  class="easyui-validatebox textbox"data-options="required:true,min:0,max:200,validType:['intOrFloat','maxLength[8]']"/></td>
				</tr>
				<tr>
				<td>保养周期(月):</td>
					<td><input name="carModel.modelMaintenanceMonth" class="easyui-validatebox textbox" data-options="required:true,validType:['intOrFloat','maxLength[8]']" /></td>
								
				</tr>
				<tr>
					<td>备注:</td>	
				<td colspan="3"><input  name="carModel.modelMemo"   class="easyui-textbox" 
							data-options="multiline:true,validType:'maxLength[400]',width:'525'" style="height:50px"/></td>	</tr>			
			</table>
		</fieldset>
	</form>
</body>
</html>