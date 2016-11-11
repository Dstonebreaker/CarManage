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
		if ($(':input[name="datadic.dictId"]').val().length > 0) {
			url = sys.contextPath + '/maintain/dictionaryManage!update.cxl';
		} else {
			url = sys.contextPath + '/maintain/dictionaryManage!save.cxl';
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
if ($(':input[name="datadic.dictId"]').val().length > 0) {
	parent.$.messager.progress({
		text : '数据加载中....'
	});
	$.post(sys.contextPath + '/maintain/dictionaryManage!getById.cxl', {
		id : $(':input[name="datadic.dictId"]').val()
	}, function(result) {
		$('form').form('load', {
			'datadic.dictId' : result.dictId,
			'datadic.dictClasId' : result.dictClasId,
			'datadic.dictNo' : result.dictNo,
			'datadic.dictIndex' : result.dictIndex,
			'datadic.dictName' : result.dictName,
			'datadic.dictPyCode' : result.dictPyCode,
			'datadic.professionId' : result.professionId
		});
		if(result.dictClasId=="Fault"){
			
			  $('#professionId').show();			
		}	
		parent.$.messager.progress('close');
	}, 'json');
}
		$('#dictClasId').combobox({								
				panelHeight:'200',
				required:true,
				editable:false,
				valueField: 'dictClasId',
				textField: 'dictClasName',
				queryParams : {'QUERY_t#dictClasIsSelf_I_EQ' : '1'},
				url: sys.contextPath+'/maintain/dictionaryClasssManage!doNotNeedSecurity_getClassNameByClassId.cxl?',
			    onSelect:function(rec){
			    	    
			   			if(rec.dictClasName =='故障'){					   			  
			   			   $('#professionId').show();
			   			    $('#profession').combobox({			   			    	
			   			 	required:true
			   			    });			   						   			
			   			}else{
			   			  
			   			   $('#professionId').hide();	
			   			}				  												
				} 
		});

}); 
</script>
</head>
<body>
	<form method="post" class="form">
		<fieldset>
			<legend>字典信息</legend>

			<table class="table" style="width: 100%;">
				<tr>
					<td>编号:</td>
					<td><input name="datadic.dictId" value="<%=id%>"
						readonly="readonly" /></td>
						<td>字典编号:</td>
					<td><input name="datadic.dictNo" class="easyui-validatebox"
						data-options="required:true,validType:'maxLength[64]'" /></td>
				</tr>
				<tr>
					<td>字典分类:</td>
					<td><input id="dictClasId"  name="datadic.dictClasId" /></td>
					<td>排序:</td>
					<td><input name="datadic.dictIndex"
						class="easyui-numberspinner"
						data-options="required:true,min:0,max:100000"
						style="width: 155px;" value="100" /></td>

				</tr>
				<tr>
					<td>字典名称:</td>
					<td><input name="datadic.dictName" class="easyui-validatebox"
						data-options="required:true,validType:'maxLength[15]'"  /></td>
					<td>字典拼音码:</td>
					<td><input name="datadic.dictPyCode"
						class="easyui-validatebox"
						data-options="required:true,validType:'maxLength[15]'" /></td>
				</tr>
				
				


			</table>
		</fieldset>
	</form>
</body>
</html>