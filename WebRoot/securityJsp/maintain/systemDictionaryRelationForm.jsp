<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<%
	String id = request.getParameter("id");
	String ids =  request.getParameter("ids");
	if (id == null) {
		id = "";
	}
	if (ids == null) {
		ids = "";
	}
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">
	var uploader;//上传对象
	var submitForm = function($dialog, $grid, $pjq) {
	if ($('form').form('validate')) {			
		var form = $("#fileForm");
		var url;
		if ($(':input[name="dictId"]').val().length > 0) {
			url = sys.contextPath + '/maintain/dictionaryRelation!update.cxl';
		} else {
			url = sys.contextPath + '/maintain/dictionaryRelation!save.cxl';
		}
		parent.$.messager.progress({
			text: '数据处理中....'
		});
		$.post(url, sys.serializeObject($('form')), function(result) {
			parent.$.messager.progress('close');
				if (result.success) {
				$pjq.messager.alert('提示', result.msg, 'info');
					$dialog.dialog('destroy');
				    $grid.datagrid('reload');
				} else {
				$pjq.messager.alert('提示', result.msg, 'error');
				}
			}, 'json');
		}
	};
	$(function() {

		if ($(':input[name="dictId"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(sys.contextPath + '/maintain/dictionaryRelation!getById.cxl', {
				id : $(':input[name="dictId"]').val(),
				ids : $(':input[name="dictIdRelate"]').val()
			}, function(result) {
				if (result.dictId != undefined) {
					$('form').form('load', {
						'sysDictionaryRelation.dictId' : result.dictId,
						'sysDictionaryRelation.dictClasId' : result.dictClasId,
						'sysDictionaryRelation.dictIdRelate' : result.dictIdRelate,
						'sysDictionaryRelation.dictClasIdRelate' : result.dictClasIdRelate,
						'sysDictionaryRelation.dictIdFlag' : result.dictIdFlag
					});					
				}
				parent.$.messager.progress('close');
			}, 'json');
		}
	});
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<form  id="fileForm"  method="post" class="form" enctype="multipart/form-data">
		<fieldset>
			<legend>品牌车系基本信息</legend>
			<input name="sysDictionaryRelation.dictIdFlag" type="hidden"/>
			<input name="sysDictionaryRelation.dictClasId" type="hidden"/>
			<input name="sysDictionaryRelation.dictClasIdRelate" type="hidden"/>
			<table class="table" style="width: 100%;">
		     	<tr>
					<td>品牌编号:</td>
					<td><input name="dictId" value="<%=id%>" readonly="readonly" /></td>
					<td>车系编号:</td>
					<td><input name="dictIdRelate" value="<%=ids%>" readonly="readonly" /></td>
				</tr> 
				<tr>
         			<td>品牌:</td>
				    <td><select name="sysDictionaryRelation.dictId" class="easyui-combobox" data-options="required:true,editable:false,valueField:'dictId',
									 textField:'dictName',url:'<%=contextPath%>/maintain/dictionaryManage!doNotNeedSecurity_getByClassId.cxl?dictClassId=CarBrand',
									 panelHeight:'auto'" style="width: 155px;"></select></td>
					<td>车系:</td>
				    <td><select name="sysDictionaryRelation.dictIdRelate" class="easyui-combobox" data-options="required:true,editable:false,valueField:'dictId',
									 textField:'dictName',url:'<%=contextPath%>/maintain/dictionaryManage!doNotNeedSecurity_getByClassId.cxl?dictClassId=CarSeries',
									 panelHeight:'auto'" style="width: 155px;"></select></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>