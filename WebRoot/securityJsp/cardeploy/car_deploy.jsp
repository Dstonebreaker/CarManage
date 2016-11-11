<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.framework.util.SecurityUtil"%>
<%
	String contextPath = request.getContextPath();
	SecurityUtil securityUtil = new SecurityUtil(session);
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">
      var grid;
	  var addFun = function ($dialog, $grid, $pjq) {
	    var dictIdUserType = $('#dictIdUserType').combobox('getValue');
		var dictIdUse = $('#dictIdUse').combobox('getValue');
		if (dictIdUserType=="") {
			parent.$.messager.alert('提示', '人员类别不能为空!', 'info');
			return false;
		}
		if (dictIdUse=="") {
		 	parent.$.messager.alert('提示', '车辆用途不能为空!', 'info');
			return false;
		 }
     	  var rows = $('#gridList').datagrid('getChecked');
            if ($('form').form('validate')) {   
				url = sys.contextPath + '/car/deploy!save.cxl';
                parent.$.messager.progress({
                    text: '数据处理中....'
                });
                var datastrs = JSON.stringify(rows);	
                $.post(url, $.extend(sys.serializeObject($('form')),{
                     datastr : datastrs
                     }),
                 function (result) {
                    parent.$.messager.progress('close');
                    if (result.success) {
                        parent.$.messager.alert('提示', result.msg, 'info');
                        $grid.datagrid('reload');
                        $dialog.dialog('destroy');
                    } else {
                        parent.$.messager.alert('提示', result.msg, 'error');
                    }
                }, 'json');
            }
        };
        function searchCost() {
        var orgIdManager = $('#orgIdManager').combobox('getValue');	
		if (orgIdManager=="") {
			parent.$.messager.alert('提示', '产权单位不能为空!', 'info');
			return false;
		}
	     	grid.datagrid('load', sys.serializeObject($('#searchForm')));
	    };
         $(function() {  
           $('#orgIdManager').combobox({
                url: sys.contextPath+'/maintain/sysOrganization!doNotNeedSecurity_getOrgManger.cxl',
                panelHeight:'200',
                required:true,
                editable: false,
                valueField: 'orgIdManager',
                textField:  'orgName',
                onSelect: function (record) {   
                 grid  = $('#gridList').datagrid({
                   title: '定编车辆信息',
                   url: sys.contextPath + '/car/deploy!doNotNeedSecurity_getOrgcarList.cxl?id='+ record.orgIdManager,
                   striped: true,
                   idField: 'carId',
                   rownumbers: true,
                   sortOrder: 'desc',
                   singleSelect: false,
                   pageSize:40,
                   columns: [[   
                    {
                    width: '100',
                    checkbox : true,
                    field: 'carId'            
                  },  
                        
                  {
                    width: '100',
                    title: '车牌号',
                    sortable: true,
                    field: 'carNo',
                    formatter: function(value,row,index){
				     if (row.checked){
				        $('#gridList').datagrid('checkRow', index);
				       } 
				       return value;
			        }   
                    
                  },
                  {
                    width: '100',
                    title: '发动机编号号',
                    sortable: true,
                    field: 'carEngineNo'
                  },
                  {
                    width: '100',
                    title: '车辆识别号',
                    sortable: true,
                    field: 'carIdentifyNo'
                   }
                    
                  ]],
                 	onBeforeLoad : function(row, param) {
					    parent.$.messager.progress({
						text : '数据加载中....'
						}); 
					},
				onLoadSuccess : function(row, data) {
                 parent.$.messager.progress('close');            
					}
                });  
          
            }
         });
                
      });
        function doClear() {
			$("#searchForm").form('clear');
		}
</script>
<style>
.textbox {
	height: 20px;
	width: 100px;
}
</style>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div id="toolbar" >
		 <form id="searchForm"name="searchForm" method="post" target="report">
			<table>
					<tr>
				    <td>产权单位</td>
					<td  ><input id="orgIdManager" name="orgIdManager" class="easyui-combobox textbox "/></td>	
					<td>人员类别</td>
					<td><input  id ="dictIdUserType" name="dictIdUserType"
						class="easyui-combobox textbox " 
						data-options="panelHeight:'200',editable:false,
						valueField: 'dictId',
						textField: 'dictName',
						url:'<%=contextPath%>/maintain/dictionaryManage!doNotNeedSecurity_getByClassId.cxl?dictClassId=UserType'
						" /></td>
					<td>车辆用途</td>
					  <td><input id ="dictIdUse" name="dictIdUse"
						class="easyui-combobox textbox " 
						data-options="panelHeight:'200',editable:false,
						valueField: 'dictId',
						textField: 'dictName',
						url:'<%=contextPath%>/maintain/dictionaryManage!doNotNeedSecurity_getByClassId.cxl?dictClassId=CarUsingType'
						" /></td>
                	<td><a onclick="searchCost()" href="javascript:void(0);" class="easyui-linkbutton"
                     data-options="iconCls:'ext-icon-search',plain:true">查询</a></td>
                     <td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="doClear()">清空条件</a>
								</td>
			  <%
                  if (securityUtil.havePermission("/car/carUsedCost!save")) {
              %>
              <td><a href="javascript:void(0);" class="easyui-linkbutton"
                     data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addFun();">定编</a></td>
              <%
                  }
              %>
	                 </tr>
		   </table>
		   <table  id='gridList' class="easyui-datagrid">
		   </table>
		</form>	
	</div>
</body>
</html>