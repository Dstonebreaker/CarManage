<%@page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.framework.util.WebMsgUtil" %>
<%@ page import="java.net.URLDecoder"%>

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
    String carId = request.getParameter("carId");
    String str=request.getParameter("carNo");
	String carNo="";
	if (str != null) {
        carNo = URLDecoder.decode(str, "utf-8");
    }
    if (carId == null) {
        carId = "";
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
                var insuStartDateStr = $('#insuStartDate').datebox('getValue');
                var insuStartDate = new Date(insuStartDateStr.replace(/-/g, "/"));
                var insuOverDateStr = $('#insuOverDate').datebox('getValue');
                var insuOverDate = new Date(insuOverDateStr.replace(/-/g, "/"));
                if (Date.parse(insuStartDate) > Date.parse(insuOverDate)) {
                    $pjq.messager.alert('提示', "开保时间应小于过保时间", 'error');
                    return;
                }

                var url;
                if ($(':input[name="insurance.insuId"]').val().length > 0) {
                    url = sys.contextPath + '/car/insurance!update.cxl';
                } else {
                    url = sys.contextPath + '/car/insurance!save.cxl';
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
        var carcombobox;
        var isinit = true;
        $(function () {
            if ($(':input[name="insurance.insuId"]').val().length > 0) {
                $('#insuranceType').combobox({'readonly': true});
            }
            else {
                carcombobox = $('#car').combobox({
                    valueField: 'carId',
                    textField: 'carNo',
                    data: [{carId: '<%=carId%>', carNo: '<%=carNo%>'}]
                });
                carcombobox.combobox('setValue','<%=carId%>');

            }
            $('#insuranceType').combobox({
                onSelect: function (record) {
                    $.post(
                            sys.contextPath + '/car/insurance!doNotNeedSecurity_getMaxTime.cxl' ,
                            {
                                id: $(':input[name="insurance.insuId"]').val(),
                                carId: '<%=carId%>',
                                typeId:record.dictId
                            }, function (result) {
                                if (result == "null") {
                                    $('#lastTime').val("");
                                } else {
                                    $('#lastTime').val(result);
                                    $('#insuStartDate').datebox('setValue', result);
                                }
                            });
                }
            });
            parent.$.messager.progress({
                text: '数据加载中....'
            });
            $.post(
                    sys.contextPath + '/car/vinsurance!getById.cxl',
                    {
                        id: $(':input[name="insurance.insuId"]').val()
                    },
                    function (result) {
                        if (result.insuId != undefined) {
                            carcombobox = $('#car').combobox({
                                valueField: 'carId',
                                textField: 'carNo',
                                data: [{carId: result.carId, carNo: result.carNo}]
                            });

                            $('form')
                                    .form(
                                            'load',
                                            {
                                                'insurance.insuId': result.insuId,
                                                'insurance.insuNo': result.insuNo,
                                                'insurance.carId': result.carId,
                                                'insurance.carNo': result.carNo,
                                                'insurance.dictIdInsuranceType': result.dictIdInsuranceType,
                                                'insurance.dictIdInsuranceCorp': result.dictIdInsuranceCorp,
                                                'insurance.insuStartDate':  result.insuStartDate,
                                                'insurance.insuOverDate': result.insuOverDate,
                                                'insurance.insuMoney': result.insuMoney,
                                                'insurance.userIdCreate': result.userIdCreate,
                                                'insurance.timeCreate': result.timeCreate,
                                                'insurance.lastTime': result.lastTime
                                            });

                        }
                        parent.$.messager.progress('close');
                    }, 'json');
        });
        var startTime = function (date) {
            var lastTimeStr = $('#lastTime').val();
            if (lastTimeStr.length > 0) {
                var insuStartDateStr = $('#insuStartDate').datebox('getValue');
                var insuStartDate = new Date(insuStartDateStr.replace(/-/g, "/"));
                var lastTime = new Date(lastTimeStr.replace(/-/g, "/"));
                if (Date.parse(insuStartDate) < Date.parse(lastTime)) {
                    parent.$.messager.alert('提示', "开保时间不小于上一次到保时间", 'error');
                    $('#insuStartDate').datebox('setValue', lastTimeStr);
                }
            }
        };
        var overTime = function (date) {
//            debugger;
            var insuStartDateStr = $('#insuStartDate').datebox('getValue');
            var insuStartDate = new Date(insuStartDateStr.replace(/-/g, "/"));
            var insuOverDateStr = $('#insuOverDate').datebox('getValue');
            var insuOverDate = new Date(insuOverDateStr.replace(/-/g, "/"));
            if (Date.parse(insuStartDate) > Date.parse(insuOverDate)) {
                parent.$.messager.alert('提示', "开保时间应小于过保时间", 'error');
                $('#insuOverDate').datebox('setValue', insuStartDateStr);
            }
        };
    </script>
</head>
<body>
<form method="post" class="form">

    <legend>保险基本信息</legend>
    <table class="table" style="width: 100%;padding:50px;border:0px">
        <tr>
            <td>编号</td>
            <td><input name="insurance.insuId" class="easyui-textbox" value="<%=id%>" readonly="readonly"/></td>
            <td>保单号码</td>
            <td><input name="insurance.insuNo" class="easyui-textbox"
                       data-options="required:true,validType:'length[0,30]'"/></td>
        </tr>
        <tr>
            <td>车牌</td>
            <td><select id='car' class="easyui-combobox" readonly="true" name="insurance.carId"
                        data-options=""></select> <input
                    id='carNo' value="<%=carNo%>" name="insurance.carNo" type="hidden"/></td>
            <td>保险类型</td>
            <td><select id="insuranceType" name="insurance.dictIdInsuranceType" class="easyui-combobox"
                        data-options="required:true,editable:false,valueField:'dictId',textField:'dictName',url:'<%=contextPath%>/maintain/dictionaryManage!doNotNeedSecurity_getByClassId.cxl?dictClassId=<%=WebMsgUtil.CLASS_BXType%>',panelHeight:'200'"></select>
            </td>

        </tr>
        <tr>
            <td>保险公司</td>
            <td><select name="insurance.dictIdInsuranceCorp" class="easyui-combobox"
                        data-options="required:true,editable:false,valueField:'dictId',textField:'dictName',url:'<%=contextPath%>/maintain/dictionaryManage!doNotNeedSecurity_getByClassId.cxl?dictClassId=<%=WebMsgUtil.CLASS_BXCONP%>',panelHeight:'200'"></select>
            </td>

            <td>开保日期</td>
            <td><input class="easyui-datebox" id="insuStartDate" name="insurance.insuStartDate" editable="false"
                       value='<%=date%>' data-options="required:true,onSelect:startTime"></td>
        </tr>
        <tr>
            <td>过保日期</td>
            <td><input class="easyui-datebox" id="insuOverDate" name="insurance.insuOverDate" editable="false"
                       data-options="required:true,onSelect: overTime"></td>
            <td>金额</td>
            <td><input name="insurance.insuMoney" class="easyui-numberbox"
                       data-options="required:true,max:10000000,min:0"/> <input name="insurance.userIdCreate"
                                                                                type="hidden"/> <input
                    name="insurance.timeCreate" type="hidden"/><input id="lastTime" name="insurance.lastTime"
                                                                      type="hidden"/></td>
        </tr>
    </table>
</form>
</body>
</html>