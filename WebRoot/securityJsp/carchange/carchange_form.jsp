<%@page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.framework.util.WebMsgUtil" %>

<%
    String contextPath = request.getContextPath();
%>
<%
    String dictId = request.getParameter("id");
    if (dictId == null) {
        dictId = "";
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <jsp:include page="../../inc.jsp"></jsp:include>
    <script type="text/javascript">
        var submitForm = function ($dialog, $grid, $pjq) {
            var dictId = "<%=dictId%>";
            if ($('form').form('validate')) {
            var url;
		    	url = sys.contextPath + '/car/change!save.cxl';
                parent.$.messager.progress({
                    text: '数据处理中....'
                });
                $.post(url, sys.serializeObject($('form')),
                 function (result) {
                    parent.$.messager.progress('close');
                    if (result.success) {
                        $pjq.messager.alert('提示', result.msg, 'info');
                        $grid.datagrid('reload');
                        $dialog.dialog('destroy');
                    } else {
                        $pjq.messager.alert('提示', result.msg, 'error');
                    }
                }, 'json');
            }
        };
          $(function() {    
          $('#carId').combobox({
                url: sys.contextPath+'/car/carManage!doNotNeedSecurity_getUsercarList.cxl',
                panelHeight:'200',
                required:true,
                editable: false,
                valueField: 'carId',
                textField:  'carNo',
                onSelect: function (record) {  
                debugger; 
               $('#carNoOld').combobox('setValues',record.carId);
                  if(record.orgIdManager!=undefined){
  	           $('#ManagerOld').combobox('setValues',record.orgIdManager);
  	              }else{
  	           $('#ManagerOld').combobox('setValues',"");
  	              }  
  	              if(record.userIdMaster!=undefined){
  	           $('#manageUserOld').combobox('setValues',record.userIdMaster);
  	              }else{
  	            $('#manageUserOld').combobox('setValues',"");
  	              } 
  	           $('#orgIdOldUse').combotree({
                		    disabled: false,
                			editable:false,
                			panelWidth:300,
                			idField:'id',
                			textField:'text',
                			parentField:'pid',
                			url:sys.contextPath + '/maintain/sysOrganization!doNotNeedSecurity_orgComboTree.cxl?id='+ record.orgIdUse,
                			onBeforeLoad : function(row, param) { 
                			debugger;               			
                				parent.$.messager.progress({
                					text : '数据加载中....'
                				});
                				if(row) {
                				debugger; 
                					$('#orgIdOldUse').combotree('tree').tree('options').url = sys.contextPath + '/maintain/sysOrganization!doNotNeedSecurity_comboTree.cxl?id=' + row.id;
                				}
                			},
                			onLoadSuccess : function(row, data) {                				
                				parent.$.messager.progress('close');
                				if( record.orgIdUse!=undefined){
                				debugger;
                				$('#orgIdOldUse').combotree('setValue', {
                                        	id: record.orgIdUse
                                });
                                }else{
                                $('#orgIdOldUse').combotree('setValue', {
                                        	id: ''
                                });
                                }
                			}
                			
                    });        
                                                   
                            $('#orgIdNewUse').combotree({
                		    disabled: false,
                			editable:false,
                			panelWidth:300,
                			idField:'id',
                			textField:'text',
                			parentField:'pid',
                			url:sys.contextPath + '/maintain/sysOrganization!doNotNeedSecurity_orgComboTree.cxl?id='+ record.orgIdManager,
                			onBeforeLoad : function(row, param) {                			
                				parent.$.messager.progress({
                					text : '数据加载中....'
                				});
                				if(row) {
                					$('#orgIdNewUse').combotree('tree').tree('options').url = sys.contextPath + '/maintain/sysOrganization!doNotNeedSecurity_comboTree.cxl?id=' + row.id;
                				}
                			},
                			onLoadSuccess : function(row, data) {                				
                				parent.$.messager.progress('close');
                			}
                			
                    });         
                }       
            });        
        }); 
    </script>
</head>
<body>
<form method="post" class="form">
    <legend>变更基本信息</legend>
    <input   name="dictId"  value="<%=dictId%>" type="hidden"/>
    <table class="table" style="width: 100%;padding:50px;border:0px">
        <tr>
            <td>车辆:</td>
            <td><input  id="carId"   name="carChange.carId"/></td>
         </tr>
        <%if ("CHA0003".equals(dictId)) {%>
           <tr>
            <td>原车牌号:</td>
            <td><input  id="carNoOld" name="carChange.changeCarNoOld" readonly="readonly"class="easyui-combobox"
						data-options="panelHeight:'200',editable:false,
						valueField: 'carId',
						textField: 'carNo',
						url: sys.contextPath+'/car/carManage!doNotNeedSecurity_getUsercarList.cxl'
						" /></td> 
            <td>现车牌号:</td>
           	<td><input name="carChange.changeCarNoNew"  /></td> 
          </tr> 
     	<%}%>
		<%if ("CHA0001".equals(dictId)) {%>
          <tr>
         <td>车辆原管理单位:</td>
         <td><input  id="ManagerOld" name="carChange.changeManagerOld"  readonly="readonly" class="easyui-combobox"
						data-options="panelHeight:'200',editable:false,
						valueField: 'orgIdManager',
						textField: 'orgName',
						url: sys.contextPath+'/maintain/sysOrganization!doNotNeedSecurity_getOrgManger.cxl'
						" /></td> 
		 <td>车辆现管理单位:</td>
         <td><input  name="carChange.changeManagerNew"   class="easyui-combobox"
						data-options="panelHeight:'200',editable:false,
						valueField: 'orgIdManager',
						textField: 'orgName',
						url: sys.contextPath+'/maintain/sysOrganization!doNotNeedSecurity_getOrgManger.cxl'
						" /></td> 	
        </tr>  
     	<%}%>
	    <%if ("CHA0002".equals(dictId)) {%>
	    <tr>			
           <td>车辆原使用单位:</td>
           <td><input id="orgIdOldUse" name="carChange.changeUseOld"  readonly="readonly" /></td>
           <td>车辆现使用单位:</td>
           <td><input id="orgIdNewUse" name="carChange.changeUseNew" /></td>		
        </tr> 
        <%}%>
        <tr>
         <td>车辆原保管员:</td>
         <td><input id="manageUserOld" name="carChange.userIdMasterOld"  readonly="readonly" class="easyui-combobox"
						data-options="panelHeight:'200',editable:false,
						valueField: 'userId',
						textField:'userName',
						url: sys.contextPath+'/maintain/sysUser!doNotNeedSecurity_findVUserList.cxl'
						" /></td> 
		 <td>车辆现保管员:</td>
         <td><input  name="carChange.userIdMasterNew"   class="easyui-combobox"
						data-options="panelHeight:'200',editable:false,
						valueField: 'userId',
						textField: 'userName',
						url: sys.contextPath+'/car/carManage!doNotNeedSecurity_getUserMaseterListById.cxl'
						" /></td> 	
        </tr>  
    </table>
</form>
</body>
</html>