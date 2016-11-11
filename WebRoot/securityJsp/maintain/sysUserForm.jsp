<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + contextPath + "/";
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
	var uploader;//上传对象
	var submitForm = function($dialog, $grid, $pjq) {
	if ($('form').form('validate')) {			
		var form = $("#fileForm");
		var url;
		if ($(':input[name="sysUser.userId"]').val().length > 0) {
			url = sys.contextPath + '/maintain/sysUser!update.cxl';
		} else {
			url = sys.contextPath + '/maintain/sysUser!save.cxl';
		}
		parent.$.messager.progress({
			text: '数据处理中....'
		});
		form.form({
				url : url,
				onSubmit : function(){	
				},	
				success : function(result){					
				var result2=JSON.parse(result);
					parent.$.messager.progress('close');
					if(result2.success) {
						$grid.datagrid('load');
						$dialog.dialog('destroy');		    	
			    		$pjq.messager.alert('提示', '保存成功!', 'info');}
			    		else {
			    			
							$pjq.messager.alert('提示', result2.msg, 'error');
						}
					}
			});
			
			form.submit();
		}
	};
	<%--var submitForm = function($dialog, $grid, $pjq) {
		if ($('form').form('validate')) {
			if (uploader.files.length > 0) {
				uploader.start();
				uploader.bind('StateChanged', function(uploader) {// 在所有的文件上传完毕时，提交表单
					if (uploader.files.length === (uploader.total.uploaded + uploader.total.failed)) {
						submitNow($dialog, $grid, $pjq);
					}
				});
			} else {
				submitNow($dialog, $grid, $pjq);
			}
		}
	}; --%>
	$(function() {

		if ($(':input[name="sysUser.userId"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(sys.contextPath + '/maintain/sysUser!getById.cxl', {
				id : $(':input[name="sysUser.userId"]').val()
			}, function(result) {
				if (result.userId != undefined) {
					$('form').form('load', {
						'sysUser.userId' : result.userId,
						'sysUser.userName' : result.userName,
						'sysUser.userLogin' : result.userLogin,
						'sysUser.userPhone' : result.userPhone,
						'sysUser.userMac' : result.userMac,
						'sysUser.dictIdDrivingLlicenseKind' : result.dictIdDrivingLlicenseKind,
						'sysUser.userSign' : result.userSign,
						'sysUser.dictIdUserType': result.dictIdUserType,
						'sysUser.dictIdFlag' : result.dictIdFlag
					});			
					$('#imgage').attr('src','<%=basePath%>'+result.userSign);
				}
				parent.$.messager.progress('close');
			}, 'json');
		}
	});	
	$(function() {
      var Imgage = document.getElementById('imgage');
      var TR = document.getElementById('tr');
   //   Imgage.src == '' ? TR.style.display = 'none' : TR.style.display = 'block';
  //     debugger;	
	});	  
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<form  id="fileForm"  method="post" class="form" enctype="multipart/form-data">
		<fieldset>
			<legend>用户基本信息</legend>
			<input name="sysUser.dictIdFlag" type="hidden"/>
			<table class="table" style="width: 100%;">
				<tr>
					<td>编号:</td>
					<td><input name="sysUser.userId" value="<%=id%>" readonly="readonly" /></td>
					<td>警员号:</td>
					<td><input name="sysUser.userLogin" class="easyui-validatebox" data-options="required:true,validType:'maxLength[64]'" /></td>
				</tr>
				<tr>
         			<td>姓名:</td>
					<td><input name="sysUser.userName" class="easyui-validatebox" data-options="required:true,validType:'maxLength[64]'" /></td>
					<td>手机号:</td>
					<td><input name="sysUser.userPhone" class="easyui-validatebox" data-options="validType:'mobile'" /></td>
				</tr>
				<tr>
					<td>手工签名:</td>
					<td>
					<input class="easyui-filebox" data-options="buttonText:'选择图片'" id="datafile1" name="file" />
					</td>
					<td>准驾车型:</td>
					 <td><select name="sysUser.dictIdDrivingLlicenseKind" class="easyui-combobox" data-options="required:true,editable:false,valueField:'dictId',textField:'dictName',url:'<%=contextPath%>/maintain/dictionaryManage!doNotNeedSecurity_getByClassId.cxl?dictClassId=DrivingLlicenseKind',panelHeight:'auto'" style="width: 155px;"></select></td>					
			   </tr>	
			   <tr>
			         <td>人员类型:</td>
					 <td><select name="sysUser.dictIdUserType" class="easyui-combobox" data-options="editable:false,valueField:'dictId',textField:'dictName',url:'<%=contextPath%>/maintain/dictionaryManage!doNotNeedSecurity_getByClassId.cxl?dictClassId=UserType',panelHeight:'auto'" style="width: 155px;"></select></td>					
			   </tr>		   
	         <%if (id!="") {%>
			      <tr>
			        <td>签名图片:</td>
					<td colspan="3">
					<img  src="<%=basePath%>maintain/sysUser!doNotNeedSecurity_getimage.cxl?id=<%=id%>" width="500px" height="60px">
				    </td>
			   </tr>	
	        <%}%>		
			</table>
		</fieldset>
	</form>
</body>
</html>