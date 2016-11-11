<%@page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.net.URLDecoder"%>
<%@ page import="com.framework.util.WebMsgUtil" %>

<%
    String contextPath = request.getContextPath();
%>
<%
    String sendId = request.getParameter("sendId");
    String str=request.getParameter("tcarNo");  
	String tcarNo="";
	if (str != null) {
        tcarNo = URLDecoder.decode(str, "utf-8");
    }
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
        var submitForm = function ($dialog, $grid, $pjq) {
            if ($('form').form('validate')) {
           if ($(':input[name="carUsedCost.usedCostId"]').val().length > 0) {
				url = sys.contextPath + '/car/carUsedCost!update.cxl';
			} else {
				url = sys.contextPath + '/car/carUsedCost!save.cxl';
			}
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
          $('#sendNo').combobox({
                url: sys.contextPath+'/car/carUsedCost!doNotNeedSecurity_getUsercarList.cxl',
                panelHeight:'200',
                required:true,
                editable: false,
                valueField: 'sendId',
                textField:  'sendNo',
                onBeforeLoad: function (row, param) {  
                $('#sendNo').combobox('setValue', '<%=sendId%>');         
                        },                  
                formatter: function (value, row) {  
	                    if( value.sendId=='<%=sendId%>'){
	                        $("#carNo").val(value.carNo);   
                            $("#carId").val(value.carId); 
	                    }
	                    
                    } 
            });        
        }); 
       $(function() {			
		if ($(':input[name="carUsedCost.usedCostId"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(sys.contextPath + '/car/carUsedCost!getById.cxl', {
				id : $(':input[name="carUsedCost.usedCostId"]').val()
			}, function(result) {
				if (result.usedCostId != undefined) {
					$('form').form('load', {
						'carUsedCost.usedCostId':result.usedCostId,
						'carUsedCost.usedCost' : result.usedCost,
						'carUsedCost.sendId' : result.sendId,
						'carUsedCost.dictIduseCostType' : result.dictIduseCostType	
					});
				}
				parent.$.messager.progress('close');
			}, 'json');
		}
	});
     
    </script>
</head>
<body>
<form method="post" class="form">
    <legend>费用管理基本信息</legend>
    <input  id="carNo"   name="carUsedCost.carNo" type="hidden"/>
     <input id="carId"   name="carUsedCost.carId" type="hidden"/>
    <input name="carUsedCost.usedCostId"  value="<%=id%>" type="hidden" />
    <table class="table" style="width: 100%;padding:50px;border:0px">
        <tr>
            <td>派车单号:</td>
            <td><input  id="sendNo" name="carUsedCost.sendId"  readonly="readonly"/></td>
            <td>费用金额:</td>
            <td><input    name="carUsedCost.usedCost"class="easyui-textbox" data-options="required:true,validType:'maxLength[8]'"/>元</td>
        </tr>
        <tr>
            <td>费用类型:</td>
            <td><input name="carUsedCost.dictIduseCostType"
						class="easyui-combobox textbox " 
						data-options="required:true,panelHeight:'200',editable:false,
						valueField: 'dictId',
						textField: 'dictName',
						url: sys.contextPath+'/maintain/dictionaryManage!doNotNeedSecurity_getByClassId.cxl?dictClassId=UsedCostType'
						" /></td>
        </tr>
    </table>
</form>
</body>
</html>