<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.framework.util.SecurityUtil" %>
<%@ page import="com.framework.util.WebMsgUtil" %>
<%@ page import="com.framework.util.ConfigUtil" %>
<%
    String path = request.getContextPath();
    SecurityUtil securityUtil = new SecurityUtil(session);
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
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
                                url: sys.contextPath + '/car/vinsurance!grid.cxl',
                                striped: true,
                                idField: 'insuId',
                                rownumbers: true,
                                pagination: true,
                                sortName: 'day',
                                sortOrder: 'asc',
                                frozenColumns: [[{
                                    width: '150',
                                    title: '保险单号',
                                    sortable: true,
                                    field: 'insuNo'
                                }]],
                                columns: [[
                                    {
                                        width: '150',
                                        title: '车牌',
                                        sortable: true,
                                        field: 'carNo'
                                    },
                                    {
                                        width: '150',
                                        title: '保险公司',
                                        sortable: true,
                                        field: 'corpdictName'
                                    },
                                    {
                                        width: '150',
                                        title: '保险类型',
                                        sortable: true,
                                        field: 'typedictName'
                                    },
                                    {
                                        width: '150',
                                        title: '开保日期',
                                        sortable: true,
                                        field: 'insuStartDate',
                                        formatter: function (value, row) {
                                            if (value!=undefined) {
                                                return value.substr(0, 10);
                                            }
                                        }
                                    }, {
                                        width: '150',
                                        title: '登记时间',
                                        sortable: true,
                                        field: 'timeCreate'
                                    },
                                    {
                                        width: '150',
                                        title: '到保日期',
                                        sortable: true,
                                        field: 'insuOverDate',
                                        formatter: function (value, row) {
                                            if (value!=undefined) {
                                                return value.substr(0, 10);
                                            }
                                        }
                                    },
                                    {
                                        width: '100',
                                        title: '保费金额(元)',
                                        field: 'insuMoney',
                                        sortable: true

                                    },{
                                        width: '100',
                                        title: '到保天数(天)',
                                        field: 'day',
                                        sortable: true

                                    },
                                    {
                                        title: '操作',
                                        field: 'action',
                                        width: '200',
                                        formatter: function (value, row) {
                                            var str = '';
                                            <%if (securityUtil.havePermission("/car/vinsurance!getById")) {%>
                                            str += sys.formatString('<a href="javascript:void(0)" class="btn1" onclick="showFun(\'{0}\');">查看</a>', row.insuId);
                                            <%}%>
                                            <%if (securityUtil.havePermission("/car/insurance!update")) {%>
                                            str += sys.formatString('<a href="javascript:void(0)" class="btn2" onclick="renewalFun(\'{0}\',\'{1}\');">续保</a>', row.carId, row.carNo);
                                            <%}%>
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
                                        text: '续保',
                                        plain: true,
                                        height: 18,
                                        iconCls: 'ext-icon-pencil'
                                    });
                                    //parent.$.messager.progress('close');
                                }
                            });

        });

        var addFun = function () {
            var dialog = parent.sys.modalDialog({
                title: '添加保险信息',
                url: sys.contextPath + '/securityJsp/carinsurance/car_form.jsp',
                buttons: [{
                    text: '添加',
                    handler: function () {
                        dialog.find('iframe').get(0).contentWindow.submitForm(
                                dialog, grid, parent.$);
                    }
                }]
            });
        };
        var renewalFun = function (carId, carNo) {
            var dialog = parent.sys.modalDialog({
                title: '续费保险信息',
                url: sys.contextPath
                + '/securityJsp/carinsurance/car_form.jsp?carId=' + carId + "&carNo=" + carNo,
                buttons: [{
                    text: '续保',
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
                title: '查看保险信息',
                url: sys.contextPath
                + '/securityJsp/carinsurance/car_form.jsp?id=' + id
            });
        };

        var reloadGrid=function () {
            $('#input_value').textbox("setValue", "");
            $('#input_insuNo').textbox("setValue", "");
            grid.datagrid({
                queryParams: {
                    "QUERY_t#dictIdFlag_S_EQ": '<%=WebMsgUtil.YOUXIAO%>'

                }
            });
        };
        var searchGridFun = function () {
            grid.datagrid({
                queryParams: {
                    "QUERY_t#dictIdFlag_S_EQ": '<%=WebMsgUtil.YOUXIAO%>',
                    "QUERY_t#insuNo_S_LK": $('#input_insuNo').val()
                }
            });
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

            <td>保险单号</td>
            <td><input id="input_insuNo" class="easyui-textbox" style="width:120px"/></td>
            <td><a onclick="searchGridFun();" href="javascript:void(0);" class="easyui-linkbutton"
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
