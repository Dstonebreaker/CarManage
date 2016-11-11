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
    String carId = request.getParameter("carId");
    String carNo = request.getParameter("carNo");
    if (carId == null) {
        carId = "";
    }
    if (carNo == null) {
        carNo = "";
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
                if ($(':input[name="carMaintenance.mainId"]').val().length > 0) {
                    url = sys.contextPath + '/car/carmaintenance!update.cxl';
                } else {
                    url = sys.contextPath + '/car/carmaintenance!save.cxl';
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
        var modelMaintenance;
        $(function () {
            carcombobox = $('#car')
                    .combobox(
                            {
                                required: true,
                                editable: false,
                                valueField: 'carId',
                                textField: 'carNo',
                                url: sys.contextPath
                                + '/car/insurance!doNotNeedSecurity_getcarList.cxl',
                                panelHeight: '200',
                                onSelect: function (data) {
//                                if (!isinit) {
                                    $('#carNo').val(data.carNo);
                                    $('#mainCondition').val(data.mmainInfo);
                                    modelMaintenance.combogrid({
                                        url: sys.contextPath + '/car/carmaintenance!doNotNeedSecurity_modelGrid.cxl',
                                        queryParams: {
                                            "QUERY_t#dictIdFlag_S_EQ": '<%=WebMsgUtil.YOUXIAO%>',
                                            "QUERY_t#modelId_S_EQ": data.modelId
                                        }
                                    });
//                                    isinit = false;
//                                }
                                }
                            });

            modelMaintenance = $('#modelMaintenance').combogrid({
                required: true,
                striped: true,
                panelWidth: 300,
                idField: 'mmainMileage',
                textField: 'mmainMileage',
                rownumbers: true,
                sortName: 'timeCreate',
                sortOrder: 'desc',
                singleSelect: true,
                columns: [[
                    {
                        width: '100',
                        title: '模型',
                        sortable: true,
                        field: 'modelName'
                    },
                    {
                        width: '100',
                        title: '信息',
                        field: 'mmainInfo'
                    }, {
                        width: '80',
                        title: '里程(km)',
                        field: 'mmainMileage'
                    }
                ]],
                onSelect: function (record,rowData) {
                    $('#mmainId').val(rowData.mmainId);
                }
            });

            if ($(':input[name="carMaintenance.mainId"]').val().length > 0) {
                parent.$.messager.progress({
                    text: '数据加载中....'
                });
                $
                        .post(
                                sys.contextPath + '/car/carmaintenance!getById.cxl',
                                {
                                    id: $(':input[name="carMaintenance.mainId"]').val()
                                },
                                function (result) {
                                    if (result.mainId != undefined) {
                                    	 $('#car').combobox('loadData',[{carId:result.carId,carNo:result.carNo}]);
                                    	 $('#car').combobox('readonly',true);
                                        $('form')
                                                .form(
                                                        'load',
                                                        {
                                                            'carMaintenance.mainId': result.mainId,
                                                            'carMaintenance.carId': result.carId,
                                                            'carMaintenance.carNo': result.carNo,
                                                            'carMaintenance.mainInMileage': result.mainInMileage,
                                                            'carMaintenance.mainMileage':result.mainMileage,
                                                            'carMaintenance.mmainId': result.mmainId,
                                                            'carMaintenance.dictIdMaintenanceFactory': result.dictIdMaintenanceFactory,
                                                            'carMaintenance.mainTime': result.mainTime,
                                                            'carMaintenance.userIdHandler': result.userIdHandler,
                                                            'carMaintenance.mainMoney': result.mainMoney,
                                                            'carMaintenance.userIdCreate': result.userIdCreate,
                                                            'carMaintenance.timeCreate': result.timeCreate,
                                                            'carMaintenance.mainCondition':result.mainCondition

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

    <legend>保险基本信息</legend>
    <table class="table" style="width: 100%;padding:50px;border:0px">
        <tr>
            <td>编号</td>
            <td><input name="carMaintenance.mainId" class="easyui-textbox" value="<%=id%>" readonly="readonly"/></td>
            <td>车牌</td>
            <td><select id='car' class="easyui-combobox" name="carMaintenance.carId"></select> <input
                    id='carNo' name="carMaintenance.carNo" type="hidden"/></td>
        </tr>
        <tr>
            <td>入场里程</td>
            <td><input name="carMaintenance.mainInMileage" class="easyui-numberbox"
                       data-options="required:true,max:10000000,min:0"/>km
            </td>

            <td>保养里程</td>
            <td><select name="carMaintenance.mainMileage" id="modelMaintenance" class="easyui-combobox"
                        data-options="require:true"></select>km<input name="carMaintenance.mmainId" id="mmainId" type="hidden"/>
                <input name="carMaintenance.mainCondition" id="mainCondition" type="hidden"/>
            </td>

        </tr>
        <tr>
            <td>保养厂家</td>
            <td><select name="carMaintenance.dictIdMaintenanceFactory" class="easyui-combobox"
                        data-options="required:true,editable:false,valueField:'dictId',textField:'dictName',url:'<%=contextPath%>/maintain/dictionaryManage!doNotNeedSecurity_getByClassId.cxl?dictClassId=<%=WebMsgUtil.CLASS_RepairFactory%>',panelHeight:'200'"></select>
            </td>

            <td>保养时间</td>
            <td><input class="easyui-datebox" name="carMaintenance.mainTime" editable="false"
                       data-options="required:true"></td>
        </tr>
        <tr>
            <td>经办人</td>
            <td>
                <select name="carMaintenance.userIdHandler" class="easyui-combobox"
                        data-options="required:true,valueField:'userId',textField:'userName',url:'<%=contextPath%>/car/carManage!doNotNeedSecurity_getUserMaseterListById.cxl',panelHeight:'200'"></select>
            </td>
            <td>金额</td>
            <td><input name="carMaintenance.mainMoney" class="easyui-numberbox"
                       data-options="required:true,max:10000000,min:0"/>
                <input name="carMaintenance.userIdCreate" type="hidden"/>
                <input name="carMaintenance.timeCreate" type="hidden"/></td>
        </tr>
    </table>
</form>
</body>
</html>