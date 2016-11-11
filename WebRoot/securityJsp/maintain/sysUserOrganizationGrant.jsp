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
		var node;
		node = $('#tree').tree('getChecked');
		var ids = [];
		if(node.length>0){
		 ids.push(node[0].id);
		}
	parent.$.messager.progress({
		text: '数据处理中....'
	});
		$.post(sys.contextPath + '/maintain/sysUser!grantOrganization.cxl', {
			id : $(':input[name="sysUser.userId"]').val(),
			ids : ids.join(',')
		}, function(result) {
			parent.$.messager.progress('close');
			if (result.success) {
				$dialog.dialog('destroy');
			} else {
				$pjq.messager.alert('提示', result.msg, 'error');
			}
			$pjq.messager.alert('提示', '修改成功！', 'info');
		}, 'json');
	};
	$(function() {	
	 var  userId = $(':input[name="sysUser.userId"]').val();
	 parent.$.messager.progress({
			text : '数据加载中....'
		});
		$('#tree').tree({
			url : sys.contextPath + '/maintain/sysOrganization!doNotNeedSecurity_getUserSyorganizationsTree.cxl?ids='+userId+'&id=',
			checkbox : true,
			parentField : 'pid',
			cascadeCheck : false,
			formatter : function(node) {
				return node.text;
			},
			onLoadSuccess : function(node, data) {	
				$.post(sys.contextPath + '/maintain/sysOrganization!doNotNeedSecurity_getSyorganizationByUserId.cxl', {
					id : $(':input[name="sysUser.userId"]').val()
				}, function(result) {
					if (result) {					
						for (var i = 0; i < result.length; i++) {
							var node = $('#tree').tree('find', result[i].orgId);//
							if (node) {
								$('#tree').tree('select', node.target);
								$('#tree').tree('check',node.target);
							}
						}
					}
				}, 'json');
				parent.$.messager.progress('close');
			},
			  onCheck:function(node,checked){ 	
				 if(checked){
					var nodes = $('#tree').tree('getChecked');
					if(nodes.length > 1){
						for (var i = 0; i < nodes.length; i++) {		
							$('#tree').tree('uncheck',nodes[i].target);
						}
						$('#tree').tree('check',node.target);
					}		
				}	
		    }, 
			onBeforeExpand : function(node, param) {
			    debugger;
				$(this).tree('options').url = sys.contextPath + '/maintain/sysOrganization!doNotNeedSecurity_getSyorganizationsTree.cxl?ids='+ userId+'&id='+ node.id;
			}
		});
	});
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<input name="sysUser.userId" value="<%=id%>" readonly="readonly" type="hidden" />
	<fieldset>
		<legend>所属机构</legend>
		<ul id="tree"></ul>
	</fieldset>
</body>
</html>