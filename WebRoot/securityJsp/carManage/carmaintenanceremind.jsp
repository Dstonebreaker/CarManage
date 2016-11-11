<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.framework.util.SecurityUtil" %>
<%@ page import="com.framework.util.WebMsgUtil" %>
<%@ page import="com.framework.util.ConfigUtil" %>
<%
    String contextPath = request.getContextPath();
    SecurityUtil securityUtil = new SecurityUtil(session);
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + contextPath + "/";
%>

<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">

    <title></title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
        <link rel="stylesheet" type="text/css" href="styles.css">
        -->
    <jsp:include page="../../inc.jsp"></jsp:include>
    <script type="text/javascript">
        var grid;
        $(function () {
            grid = $('#grid')
                    .datagrid(
                            {
                                title: '',
                                url: sys.contextPath + '/car/vcarmaintenance!grids.cxl',
                                queryParams: {                                                                               
                                },
                                striped: true,
                                idField: 'mainId',
                                rownumbers: true,
                                pagination: true,
                                sortName: 'day',
                                sortOrder: 'asc',
                                frozenColumns: [[{
                                    width: '100',
                                    title: '车牌号',
                                    sortable: true,
                                    field: 'carNo'
                                }]],
                                columns: [[
                                    {
                                        width: '100',
                                        title: '类型',
                                        sortable: true,
                                        field: 'carModelName'
                                    },
                                    {
                                        width: '150',
                                        title: '下次保养里程km',
                                        sortable: true,
                                        field: 'mainNextMileage'
                                    },
                                    {
                                        width: '100',
                                        title: '下次保养日期',
                                        sortable: true,
                                        field: 'mainNextTime',
                                        formatter: function (value, row) {
                                            if (value != undefined) {
                                                return value.substr(0, 10);
                                            }
                                        }

                                    },
                                    {
                                        width: '100',
                                        title: '保养金额(元)',
                                        sortable: true,
                                        field: 'mainMoney'
                                    }, {
                                        width: '150',
                                        title: '保养器件',
                                        sortable: true,
                                        field: 'mainCondition'

                                    },
                                    {
                                        width: '150',
                                        title: '距下次保养天数',
                                        field: 'day',
                                        sortable: true
                                    },
                                    {
                                        title: '操作',
                                        field: 'action',
                                        width: '200',
                                        formatter: function (value, row) {
                                            var str = '';
                                            <%if (securityUtil.havePermission("/car/carmaintenance!getById")) {%>
                                            str += sys.formatString('<a href="javascript:void(0)" class="btn1" onclick="showFun(\'{0}\');">查看</a>', row.mainId);
                                            <%}%>
                                            <%--<%if (securityUtil.havePermission("/car/carmaintenance!update")) {%>--%>
                                            <%--str += sys.formatString('<a href="javascript:void(0)" class="btn2" onclick="editFun(\'{0}\');">编辑</a>', row.mainId);--%>
                                            <%--<%}%>--%>
                                            <%--<%if (securityUtil.havePermission("/car/carmaintenance!delete")) {%>--%>
                                            <%--str += sys.formatString('<a href="javascript:void(0)" class="btn3" onclick="removeFun(\'{0}\');">删除</a>', row.mainId);--%>
                                            <%--<%}%>--%>
                                            return str;
                                        }
                                    }]],
                                toolbar: '#toolbar',
                                onBeforeLoad: function (row, param) {
                                    /* parent.$.messager.progress({
                                     text : '数据加载中....'
                                     }); */
                                },
                                onLoadSuccess: function (row, data) {
                                    $('.btn1').linkbutton({
                                        text: '查看',
                                        plain: true,
                                        height: 18,
                                        iconCls: 'ext-icon-search'
                                    });
                                    $('.btn2').linkbutton({
                                        text: '编辑',
                                        plain: true,
                                        height: 18,
                                        iconCls: 'ext-icon-pencil'
                                    });
                                    $('.btn3').linkbutton({
                                        text: '删除',
                                        plain: true,
                                        height: 18,
                                        iconCls: 'ext-icon-delete'
                                    });
                                    //parent.$.messager.progress('close');
                                }
                            });
                    
        });

        var addFun = function () {
            var dialog = parent.sys.modalDialog({
                title: '添加保养信息',
                url: sys.contextPath + '/securityJsp/carManage/carmaintenance_form.jsp',
                buttons: [{
                    text: '添加',
                    handler: function () {
                        dialog.find('iframe').get(0).contentWindow.submitForm(
                                dialog, grid, parent.$);
                    }
                }]
            });
        };
        var editFun = function (id) {
            var dialog = parent.sys.modalDialog({
                title: '编辑保养信息',
                url: sys.contextPath
                + '/securityJsp/carManage/carmaintenance_form.jsp?id=' + id,
                buttons: [{
                    text: '编辑',
                    handler: function () {
                        dialog.find('iframe').get(0).contentWindow.submitForm(
                                dialog, grid, parent.$);
                    }
                }]
            });
        };
        var removeFun = function (id) {
            parent.$.messager.confirm('询问', '您确定要删除此记录？', function (r) {
                if (r) {
                    $.post(sys.contextPath + '/car/insurance!delete.cxl', {
                        id: id
                    }, function () {
                        grid.datagrid('reload');
                    }, 'json');
                }
            });
        };
        var showFun = function (id) {
            var dialog = parent.sys.modalDialog({
                title: '查看保养信息',
                url: sys.contextPath
                + '/securityJsp/carManage/carmaintenance_form.jsp?id=' + id
            });
        };
        var searchFun = function () {
            grid.datagrid({
                queryParams: {
                    "QUERY_t#dictIdFlag_S_EQ": '<%=WebMsgUtil.YOUXIAO%>',
                    "QUERY_t#carNo_S_LK": $('#input_carNo').val(),
                    "QUERY_t#carmodelId_S_LK": $('#input_type').combobox("getValue")
                }
            });
        };
        var reload = function () {
            $('#input_carNo').textbox("setValue", "");
            $('#input_type').textbox("setValue", "");
            grid.datagrid({queryParams: {"QUERY_t#dictIdFlag_S_EQ": '<%=WebMsgUtil.YOUXIAO%>'}});

        };
        function doClear() {
		$("#searchForm").form('clear');
	}
    </script>
</head>

<body class="easyui-layout" data-options="fit:true,border:false">
<div id="toolbar" style="display: none;">
	<form id="searchForm">
    <table>
        <tr>
            <td>车牌</td>
            <td><input id="input_carNo" class="easyui-textbox" style="width:120px"/></td>
            <td>车系</td>
            <td><input id="input_type" class="easyui-combobox"  data-options="editable:false,valueField:'modelId',textField:'modelName',url:'<%=contextPath%>/car/Model!doNotNeedSecurity_getCarMode.cxl',panelHeight:'200'" style="width:120px"/></td>
            <td><a onclick="searchFun();" href="javascript:void(0);" class="easyui-linkbutton"
                   data-options="iconCls:'ext-icon-search',plain:true">查询</a></td>
            
           <td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true"  onclick="doClear()">清空条件</a></td>
        </tr>
    </table>
    </form>
</div>
<div data-options="region:'center',fit:true,border:false">
    <table id="grid" data-options="fit:true,border:false"></table>
</div>
</body>
</html>
