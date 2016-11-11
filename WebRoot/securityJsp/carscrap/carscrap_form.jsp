<%@page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.framework.util.WebMsgUtil" %>

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
        var submitForm = function ($dialog, $grid, $pjq) {
            if ($('form').form('validate')) {
           if ($(':input[name="carScrap.scrapId"]').val().length > 0) {
				url = sys.contextPath + '/car/scrap!update.cxl';
			} else {
				url = sys.contextPath + '/car/scrap!save.cxl';
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
          $('#carId').combobox({
                url: sys.contextPath+'/car/carManage!doNotNeedSecurity_getUsercarList.cxl',
                panelHeight:'200',
                required:true,
                editable: false,
                valueField: 'carId',
                textField:  'carNo',
                onSelect: function (record) {                                    
                          
                }       
            });        
        }); 
       $(function() {			
		if ($(':input[name="carScrap.scrapId"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(sys.contextPath + '/car/scrap!getById.cxl', {
				id : $(':input[name="carScrap.scrapId"]').val()
			}, function(result) {
				if (result.scrapId != undefined) {
					$('form').form('load', {
						'carScrap.scrapId':result.scrapId,
						'carScrap.carId' : result.carId,
						'carScrap.scrapUsedYear' : result.scrapUsedYear	
					});
				}
				parent.$.messager.progress('close');
			}, 'json');
		}
	});
        $(function() {
	 $("#number").textbox('textbox').bind('keyup', function(e){
     $("#number").textbox('setValue', $(this).val().replace(/\D/g,''));
		  });
	});
    </script>
</head>
<body>
<form method="post" class="form">
    <legend>车辆报废基本信息</legend>
    <input name="carScrap.scrapId"  value="<%=id%>" type="hidden" />
    <table class="table" style="width: 100%;padding:50px;border:0px">
        <tr>
            <td>车牌</td>
            <td><input  id="carId"   name="carScrap.carId"/></td>
             <td>已使用年限</td>
            <td><input  id="number"  name="carScrap.scrapUsedYear"class="easyui-textbox" data-options="required:true,validType:'maxLength[6]'"/>年</td>
        </tr>
    </table>
</form>
</body>
</html>