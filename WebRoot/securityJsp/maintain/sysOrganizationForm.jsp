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
			if ($(':input[name="sysOrganization.orgId"]').val().length > 0) {
				url = sys.contextPath + '/maintain/sysOrganization!update.cxl';
			} else {
				url = sys.contextPath + '/maintain/sysOrganization!save.cxl';
			}
			parent.$.messager.progress({
				text: '数据处理中....'
			});
			$.post(url, sys.serializeObject($('form')), function(result) {
				parent.$.messager.progress('close');
				if (result.success) {
				$pjq.messager.alert('提示', result.msg, 'info');
		     		$grid.treegrid({url : sys.contextPath + '/maintain/sysOrganization!getChildrenTreeGrid.cxl?'});	
					$dialog.dialog('destroy');
				} else {
					$pjq.messager.alert('提示', result.msg, 'error');

				}
			}, 'json');
		}
	};
	var showIcons = function() {
		var dialog = parent.sys.modalDialog({
			title : '浏览小图标',
			url : sys.contextPath + '/public/style/icons.jsp',
			buttons : [ {
				text : '确定',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.selectIcon(dialog, $('#orgIconic'));
				}
			} ]
		});
	};
	$(function() {
	 	$('#switchbutton').switchbutton({
            checked: false,
            onText:'是',
            offText:'否',
            onChange: function(checked){
               console.log(checked);
               if(checked==true){
              	$("#selectid").combobox({  
                    disabled:true  
                });
                }
                else{
                 $("#selectid").combobox({  
                    disabled:false  
                });
                }
            }
        });
	    
		if ($(':input[name="sysOrganization.orgId"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(sys.contextPath + '/maintain/sysOrganization!getById.cxl', {
				id : $(':input[name="sysOrganization.orgId"]').val()
			}, function(result) {
				if (result.orgId != undefined) {
				    
					$('form').form('load', {
						'sysOrganization.orgId' : result.orgId,
						'sysOrganization.orgName' : result.orgName,
						'sysOrganization.orgNameAbbr' : result.orgNameAbbr,
						'sysOrganization.orgAddress' : result.orgAddress,
						'sysOrganization.dictIdOrgType' : result.dictIdOrgType,
						'sysOrganization.orgIconic' : result.orgIconic,
						'sysOrganization.orgIdOrgParent' : result.orgIdOrgParent,
						'sysOrganization.orgLinkman' : result.orgLinkman,
						'sysOrganization.orgLinkPhone' : result.orgLinkPhone,
						'sysOrganization.userIdCreate' : result.userIdCreate,
						'sysOrganization.orgIdManager' : result.orgIdManager,
						'timeCreate' : result.timeCreate,
						'sysOrganization.dictIdFlag' : result.dictIdFlag
					});
					$('#iconCls').attr('class', result.iconCls);//设置背景图标
					if(result.orgId==result.orgIdManager){
					$('#switchbutton').switchbutton({
						checked:true
					});
					}else{
					$('#switchbutton').switchbutton({
						checked:false
					});
					}
					
					$('#sysOrganization_orgIdOrgParent').combotree({
						editable:false,
						panelWidth:300,
						idField:'id',
						textField:'text',
						parentField:'pid',
						url:sys.contextPath + '/maintain/sysOrganization!doNotNeedSecurity_getcomboTree.cxl?ids='+result.orgIdOrgParent,
						onBeforeLoad : function(row, param) {
							parent.$.messager.progress({
								text : '数据加载中....'
							});
							if(row) {
								$('#sysOrganization_orgIdOrgParent').combotree('tree').tree('options').url = sys.contextPath + '/maintain/sysOrganization!doNotNeedSecurity_comboTree.cxl?id=' + row.id;
							}
						},
						onLoadSuccess : function(row, data) {
						$('#sysOrganization_orgIdOrgParent').combotree('setValue', {
	                                           id: result.orgIdOrgParent
                                            });
							parent.$.messager.progress('close');
						}
					});
					
				}
				parent.$.messager.progress('close');
			}, 'json');
		}else{
	       $('#sysOrganization_orgIdOrgParent').combotree({
						editable:false,
						panelWidth:300,
						idField:'id',
						textField:'text',
						parentField:'pid',
						url:sys.contextPath + '/maintain/sysOrganization!doNotNeedSecurity_comboTree.cxl?ids=',
						onBeforeLoad : function(row, param) {
							parent.$.messager.progress({
								text : '数据加载中....'
							});
							if(row) {
								$('#sysOrganization_orgIdOrgParent').combotree('tree').tree('options').url = sys.contextPath + '/maintain/sysOrganization!doNotNeedSecurity_comboTree.cxl?id=' + row.id;
							}
						},
	       	onLoadSuccess : function(row, data) {
							parent.$.messager.progress('close');
						}
					});
		     }
	});
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<form method="post" class="form">
		<fieldset>
			<legend>机构基本信息</legend>
			<input name="sysOrganization.userIdCreate" type="hidden" />
			<input name="timeCreate" type="hidden" />
			<input name="sysOrganization.dictIdFlag" type="hidden" />
			<table class="table" style="width: 100%;">
				<tr>
					<td>编号:</td>
					<td><input name="sysOrganization.orgId" value="<%=id%>" readonly="readonly" /></td>
					<td>单位名称:</td>
					<td><input name="sysOrganization.orgName" class="easyui-validatebox" data-options="required:true,validType:'maxLength[200]'" /></td>
				</tr>
				<tr>
					<td>单位简称:</td>
					<td><input name="sysOrganization.orgNameAbbr" class="easyui-validatebox" data-options="required:true,validType:'maxLength[128]'" /></td>
					<td>单位类型:</td>
					 <td><select name="sysOrganization.dictIdOrgType" class="easyui-combobox" data-options="required:true,editable:false,valueField:'dictId',textField:'dictName',url:'<%=contextPath%>/maintain/dictionaryManage!doNotNeedSecurity_getByClassId.cxl?dictClassId=OrgType',panelMaxHeight:'auto'" style="width: 155px;"></select></td>
				</tr>
				<tr>
					<td>上级单位:</td>
					<td><select id="sysOrganization_orgIdOrgParent" name="sysOrganization.orgIdOrgParent" class="easyui-combotree" style="width: 155px;"></select><img class="iconImg ext-icon-cross" onclick="$('#sysOrganization_orgIdOrgParent').combotree('clear');" title="清空" /></td>
					<td>单位图标:</td>
					<td><input id="orgIconic" name="sysOrganization.orgIconic" readonly="readonly" style="padding-left: 18px; width: 134px;" /><img class="iconImg ext-icon-zoom" onclick="showIcons();" title="浏览图标" />&nbsp;<img class="iconImg ext-icon-cross" onclick="$('#orgIconic').val('');$('#orgIconic').attr('class','');" title="清空" /></td>
				</tr>
				<tr>
				    <td>单位地址:</td>
					<td><input name="sysOrganization.orgAddress" class="easyui-validatebox" data-options="required:true,validType:'maxLength[128]'" /></td>
					<td>单位联系人:</td>
					<td colspan="3"><input name="sysOrganization.orgLinkman" class="easyui-validatebox" data-options="validType:'maxLength[200]'" /></td>					
				</tr>
				<tr>
					<td>车辆管理:</td>
	 <td colspan="2"><input id="switchbutton" class="easyui-switchbutton" >
	 <select  id="selectid" name="sysOrganization.orgIdManager" class="easyui-combobox" data-options="required:true,editable:false,valueField:'orgIdManager',textField:'orgName',url:'<%=contextPath%>/maintain/sysOrganization!doNotNeedSecurity_getOrgManger.cxl',panelMaxHeight:'auto'" style="width: 155px;"></select></td>			
				</tr>
				<tr>
					<td>联系方式:</td>
					<td colspan="3"><input name="sysOrganization.orgLinkPhone" class="easyui-validatebox" data-options="validType:'maxLength[512]'"/></td>					
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>