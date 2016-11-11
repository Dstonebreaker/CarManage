<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.framework.util.SecurityUtil" %>
<%@ page import="com.framework.util.WebMsgUtil" %>
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
                                url: sys.contextPath + '/car/vclaims!grid.cxl',
                                queryParams: {
                                    "QUERY_t#dictIdFlag_S_EQ": '<%=WebMsgUtil.YOUXIAO%>'
                                },
                                striped: true,
                                idField: 'claimsId',
                                rownumbers: true,
                                pagination: true,
                                sortName: 'timeCreate',
                                sortOrder: 'desc',
                                pageSize: 20,
                                frozenColumns: [[{
                                    width: '150',
                                    title: '单号',
                                    sortable: true,
                                    field: 'claimsNo'
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
                                        title: '保险单号',
                                        sortable: true,
                                        field: 'insuNo'
                                    },
                                    {
                                        width: '100',
                                        title: '事故地点',
                                        sortable: true,
                                        field: 'acciAddress'
                                    },
                                    {
                                        width: '100',
                                        title: '事故类型',
                                        sortable: true,
                                        field: 'acciType'
                                    },
                                    {
                                        width: '100',
                                        title: '理赔状态',
                                        sortable: true,
                                        field: 'insuranceStatus',
                                  
                                    },
                                    {
                                        width: '100',
                                        title: '事故时间',
                                        sortable: true,
                                        field: 'acciTime'
                                    },
                                    {
                                        width: '100',
                                        title: '登记时间',
                                        field: 'timeCreate',
                                        sortable: true
                                    },
                                    {
                                        title: '操作',
                                        field: 'action',
                                        width: '400',
                                        formatter: function (value, row) {
                                            var str = '';
                                            <%if (securityUtil.havePermission("/car/claims!getById")) {%>
                                            str += sys.formatString('<a href="javascript:void(0)" class="btn1" onclick="showFun(\'{0}\');">查看</a>', row.claimsId);
                                            <%}%>
                                            <%if (securityUtil.havePermission("/car/claims!update")) {%>
                                            str += sys.formatString('<a href="javascript:void(0)" class="btn2" onclick="editFun(\'{0}\');">编辑</a>', row.claimsId);
                                            <%}%>
                                            <%if (securityUtil.havePermission("/car/claims!delete")) {%>
                                            str += sys.formatString('<a href="javascript:void(0)" class="btn3" onclick="removeFun(\'{0}\');">删除</a>', row.claimsId);
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
                title: '添加理赔信息',
                url: sys.contextPath + '/securityJsp/carinsurance/claims_form.jsp',
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
                title: '编辑理赔信息',
                url: sys.contextPath
                + '/securityJsp/carinsurance/claims_form.jsp?id=' + id,
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
                    $.post(sys.contextPath + '/car/claims!delete.cxl', {
                        id: id
                    }, function () {
                        grid.datagrid('load');
                    }, 'json');
                }
            });
        };
        var searchFun = function () {
            grid.datagrid({
                queryParams: {
                    "QUERY_t#dictIdFlag_S_EQ": '<%=WebMsgUtil.YOUXIAO%>',
                    "QUERY_t#carNo_S_LK": $('#input_carNo').val(),
                    "QUERY_t#insuNo_S_LK": $('#input_insuNo').val()
                }
            });
        };
        var reload=function () {
            $('#input_carNo').textbox("setValue","");
            $('#input_insuNo').textbox("setValue","");
            grid.datagrid({queryParams:{  "QUERY_t#dictIdFlag_S_EQ": '<%=WebMsgUtil.YOUXIAO%>'}});

        };
        var showFun = function (id) {
            var dialog = parent.sys.modalDialog({
                title: '查看理赔信息',
                url: sys.contextPath
                + '/securityJsp/carinsurance/claims_form.jsp?id=' + id
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
            <td>车牌</td>
            <td><input id="input_carNo" class="easyui-textbox" style="width:120px"/></td>
            <td>保单</td>
            <td><input id="input_insuNo" class="easyui-textbox" style="width:120px"/></td>
            <td><a onclick="searchFun();" href="javascript:void(0);" class="easyui-linkbutton"
                   data-options="iconCls:'ext-icon-search',plain:true">查询</a></td>
            <%
                if (securityUtil.havePermission("/car/claims!save")) {
            %>
            <td><a href="javascript:void(0);" class="easyui-linkbutton"
                   data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addFun();">添加</a></td>
            <%
                }
            %>
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
