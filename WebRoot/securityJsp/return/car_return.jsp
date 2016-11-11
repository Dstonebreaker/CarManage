<%@page import="com.framework.util.ConfigUtil" %>
<%@page import="com.system.entity.maintain.SessionInfo" %>
<%@page import="com.framework.util.WebMsgUtil" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page import="com.framework.util.SecurityUtil" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
    SecurityUtil securityUtil = new SecurityUtil(session);
%>

<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">

    <title>My JSP 'Info_report.jsp' starting page</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <jsp:include page="../../inc.jsp"></jsp:include>

</head>

<body class="easyui-layout" data-options="fit:true,border:false">
<%
    if (securityUtil.havePermission("dk")) {
%>
<OBJECT classid=clsid:730BF2F0-EAE2-46C5-BA06-5ABFC9AB8A0A width=0 height=0 align="center"
        codebase="<%=basePath%>public/ocx/readcard/zx_32.cab#version=1,0,0,1" id="CZx_32Ctrl" HSPACE=0 VSPACE=0>
</OBJECT>
<% }%>
<div id="tb" style="padding:5px 5px;">
    <form method="post" id="searchForm">
        单据编号：<input class="easyui-textbox" id="sendNo" name="QUERY_t#sendNo_S_LK"
                    data-options="prompt:'在此输入完整或部分单据编号...'" style="width:200px"/>
        车辆种类：<input id="dictIdCarType" name="QUERY_t#dictIdCarType_S_EQ">
        <br>
        组织：<input id="orgId" name="QUERY_t#orgId_S_EQ">
        领车人：<input class="easyui-textbox" id="userNameHandler" name="QUERY_t#userNameHandler_S_LK"
                   data-options="prompt:'在此输入完整或部分领车人姓名...'" style="width:200px"/>
        车牌号：<input class="easyui-textbox" id="carNo" name="QUERY_t#carNo_S_LK" data-options="prompt:'在此输入完整或部分车牌号...'"
                   style="width:200px"/>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'ext-icon-zoom'"
           onclick="doSearch()">查询</a>
        <%
            if (securityUtil.havePermission("dk")) {
        %>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'ext-icon-car'"
           onclick="returncar()">还车</a>
        <% }%>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'ext-icon-zoom_out'"
           onclick="doClear()">清空条件</a>
    </form>
</div>
<div data-options="region:'center',fit:true,border:false" style="height:100%;margin:0px;padding:0px">
    <table id="dg" class="easyui-datagrid" style="width:100%;height:100%;margin:0px;padding:0px">
    </table>
</div>
</body>
<script>
    var grid;
    function doSearch() {
        grid.datagrid('load', $.extend(sys.serializeObject($("#searchForm")), {
            'QUERY_t#dictIdApplySendStatus_S_EQ': '<%=WebMsgUtil.APPLYSENDSTATUS_DHC%>',
            'QUERY_t#orgIdManager_S_EQ': '<%=sessionInfo.getOrganization().getOrgIdManager()%>'
        }));
    }
    //读卡还车
    var returncar = function () {
		if(!<%=securityUtil.havePermission("dk")%>){
		 parent.$.messager.alert("提示", "没有读卡权限", 'error');
		return;
		}
        var st = CZx_32Ctrl.HBOpen();
        if ((st == 0 || st < 0) && CZx_32Ctrl.lErrorCode != 0) {
            $.messager.alert("提示", "初始化读卡器失败", 'error');
        }
        else {
            var ret = CZx_32Ctrl.RfCard(st, 1);
            if (ret.length==0){
                ret= CZx_32Ctrl.RfCard(st, 1);
            }
            if (CZx_32Ctrl.lErrorCode == 0) {
                CZx_32Ctrl.DevBeep(st,10, 10, 1);
                var url = sys.contextPath + "/car/send!doNotNeedSecurity_getReturnCar.cxl";
                $.post(url, {id: ret}, function (record) {
                    if (record=="null"){
                        $.messager.alert("提示","没有该卡车辆", 'error');
                    }else {
                        ret(record.sendId);
                    }
                });
            }
            else {
                $.messager.alert("提示", "读卡失败", 'error');
            }
            try {
                CZx_32Ctrl.HBClose();
            } catch (e) {
            $.messager.alert("提示", "读卡失败", 'error');
            }
        }

    };
    function doClear() {
        $("#searchForm").form('clear');
    }
    $(function () {
        // 车型
        $('#dictIdCarType').combobox({
            editable: false,
            valueField: 'dictId',
            textField: 'dictName',
            url: sys.contextPath + '/maintain/dictionaryManage!doNotNeedSecurity_findAll.cxl',
            queryParams: {
                'QUERY_t#dictFlag_S_EQ': '<%=WebMsgUtil.YOUXIAO %>',
                'QUERY_t#dictClasId_S_EQ': 'CarType'
            },
            panelHeight: 'auto',
            panelMaxHeight: '350px'
        });
        // 组织
        $('#orgId').combotree({
            editable: false,
            panelWidth: 300,
            panelHeight: 'auto',
            panelMaxHeight: '350px',
            idField: 'id',
            textField: 'text',
            parentField: 'pid',
            url: sys.contextPath + '/maintain/sysOrganization!doNotNeedSecurity_comboTree.cxl?id=',
            onBeforeLoad: function (row, param) {
                parent.$.messager.progress({
                    text: '数据加载中....'
                });
                if (row) {
                    $('#orgId').combotree('tree').tree('options').url = sys.contextPath
                            + '/maintain/sysOrganization!doNotNeedSecurity_comboTree.cxl?id='
                            + row.id;
                }
            },
            onLoadSuccess: function (row, data) {
                parent.$.messager.progress('close');
            }
        });

        grid = $('#dg').datagrid(
                {
                    url: sys.contextPath + '/car/send!gridView.cxl',
                    queryParams: {
                        'QUERY_t#dictIdApplySendStatus_S_EQ': '<%=WebMsgUtil.APPLYSENDSTATUS_DHC%>',
                        'QUERY_t#orgIdManager_S_EQ': '<%=sessionInfo.getOrganization().getOrgIdManager()%>'
                    },
                    striped: true,
                    rownumbers: true,
                    pagination: true,
                    singleSelect: true,
                    idField: 'sendId',
                    sortName: 'sendNo',
                    sortOrder: 'asc',
                    pageSize: 30,
                    toolbar: '#tb',
                    method: 'get',
                    pageList: [10, 20, 30, 40, 50],
                    columns: [[{
                        width: '100',
                        title: '编号',
                        field: 'sendNo',
                        sortable: true
                    }, {
                        width: '100',
                        title: '车辆种类',
                        field: 'carTypeName',
                        sortable: true
                    }, {
                        width: '150',
                        title: '组织',
                        field: 'orgName',
                        sortable: true
                    }, {
                        width: '80',
                        title: '领车人',
                        field: 'userNameHandler',
                        sortable: true
                    }, {
                        width: '150',
                        title: '派车时间',
                        field: 'sendTime',
                        sortable: true
                    }, {
                        width: '70',
                        title: '车牌号',
                        field: 'carNo',
                        sortable: true
                    }, {
                        width: '70',
                        title: 'sim卡号',
                        field: 'simNo',
                        sortable: true
                    }, {
                        width: '80',
                        title: '登记人',
                        field: 'userNameCreate',
                        sortable: true
                    }, {
                        width: '150',
                        title: '派车方式',
                        field: 'carSendType',
                        sortable: true
                    }, {
                        title: '操作',
                        field: 'options',
                        width: '200',
                        formatter: function (value, row) {
                            var str = sys.formatString('<a href="javascript:void(0)" class="btn1" onclick="ret(\'{0}\')" data-options="plain:true">还车</a>', row.sendId);
                            return str;
                        }
                    }]],
                    onBeforeLoad: function (param) {
                        /* parent.$.messager.progress({
                         text : '数据加载中....'
                         }); */
                    },
                    onLoadSuccess: function (data) {
                        $('.btn1').linkbutton({text: '还车', plain: true, height: 18, iconCls: 'ext-icon-car'});
                    }
                });
    });
    var ret = function (id) {
        var dialog = parent.sys.modalDialog({
            title: '车辆归还',
            url: sys.contextPath + '/securityJsp/return/car_return_detail.jsp?id=' + id,
            width: 750,
            height: 655,
            onClose: function () {
                dialog.dialog('destroy');
            },
            buttons: [{
                text: '提交',
                handler: function () {
                    dialog.find('iframe').get(0).contentWindow.submitRet(dialog, grid, parent.$, id);
                }
            }, {
                text: '关闭',
                handler: function () {
                    //dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
                    dialog.dialog('destroy');
                }
            }]
        });

        /* parent.$.messager.confirm('确认', '确定还车?', function(r){
         if (r){
         parent.$.messager.progress({
         text : '数据处理中....'
         });
         // 这里的参数ID是派车单的id
         $.post(sys.contextPath + '/car/ret!ret.cxl', {
         id : id
         }, function(result) {
         parent.$.messager.progress('close');
         if (result.success) {
         grid.datagrid('load');
         parent.$.messager.alert('提示',result.msg,'info');
         } else {
         parent.$.messager.alert('提示',result.msg, 'error');
         }
         }, 'json');
         }
         }); */
    };
</script>
</html>
