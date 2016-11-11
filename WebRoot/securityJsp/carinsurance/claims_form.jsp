<%@page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.framework.util.WebMsgUtil" %>

<%
    String contextPath = request.getContextPath();
    String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            .format(new Date());
    System.out.print(contextPath);
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

                var url;
                if ($(':input[name="carClaims.claimsId"]').val().length > 0) {
                    url = sys.contextPath + '/car/claims!update.cxl';
                } else {
                    url = sys.contextPath + '/car/claims!save.cxl';
                }
                parent.$.messager.progress({
                    text: '数据处理中....'
                });
                $.post(url, sys.serializeObject($('form')), function (result) {

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
        $(function () {
            if ($(':input[name="carClaims.claimsId"]').val().length > 0) {
                parent.$.messager.progress({
                    text: '数据加载中....'
                });
                $.post(
                        sys.contextPath + '/car/claims!getById.cxl',
                                {
                                    id: $(':input[name="carClaims.claimsId"]')
                                            .val()
                                },
                                function (result) {
                                    if (result.insuId != undefined) {
                                        $('form')
                                                .form(
                                                        'load',
                                                        {
                                                            'carClaims.claimsId': result.claimsId,
                                                            'carClaims.insuId': result.insuId,
                                                            'carClaims.claimsNo': result.claimsNo,
                                                            'carClaims.accidentId': result.accidentId,
                                                            'carClaims.dictIdResultStatus': result.dictIdResultStatus,
                                                            'carClaims.userIdCreate': result.userIdCreate,
                                                            'carClaims.timeCreate': result.timeCreate

                                                        });
                                    }
                                    parent.$.messager.progress('close');
                                }, 'json');
            }
        });
          $(function () { 
              $('#accidentId').combobox({  
                url: sys.contextPath+'/car/claims!doNotNeedSecurity_getAcciList.cxl',
                panelHeight:'200',
                required:true,
                editable: false,
                valueField:'acciId',
                textField:'carNo',
                onSelect: function (record) { 
                     $('#insuId').combobox({  
                     url: sys.contextPath+'/car/claims!doNotNeedSecurity_getInsuranceList.cxl?id='+ record.carId,
                     panelHeight:'200',
                     required:true,
                     editable: false,
                     valueField:'insuId',
                     textField:'insuNo',
                     });  
                },
                onLoadSuccess : function(row, data) {                				
                	 $('#insuId').combobox({  
                     url: sys.contextPath+'/car/claims!doNotNeedSecurity_getInsuranceList.cxl',
                     panelHeight:'200',
                     required:true,
                     editable: false,
                     valueField:'insuId',
                     textField:'insuNo',
                       }); 
                			}
              });   
          
          
            
          });
    </script>
</head>
<body>
<form method="post" class="form">

    <legend>理赔基本信息</legend>
    <table class="table" style="width: 100%;padding:50px;border:0px">
        <tr>
            <td>编号</td>
            <td><input name="carClaims.claimsId" class="easyui-textbox" value="<%=id%>" readonly="readonly"/></td>
            <td>单号</td>
            <td><input name="carClaims.claimsNo" class="easyui-textbox" readonly="readonly" data-options="maxLength:30"/></td>
        </tr>
        <tr>
            <td>事故车牌号</td>
            <td>
            <input id="accidentId"   name="carClaims.accidentId"/>
            </td>
            <td>保险单号</td>
            <td>
            <input id="insuId" name="carClaims.insuId" />
            </td>
        </tr>
        <tr>
            <td>理赔状态</td>
            <td><select name="carClaims.dictIdResultStatus" class="easyui-combobox"
                        data-options="required:true,editable:false,valueField:'dictId',textField:'dictName',url:'<%=contextPath%>/maintain/dictionaryManage!doNotNeedSecurity_getByClassId.cxl?dictClassId=<%=WebMsgUtil.CLASS_ClaType%>',panelHeight:'200'"></select><input
                        name="carClaims.userIdCreate" type="hidden"/> <input name="carClaims.timeCreate" type="hidden"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>