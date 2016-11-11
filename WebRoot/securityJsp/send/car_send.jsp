<%@page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="com.framework.util.ConfigUtil" %>
<%@page import="com.system.entity.maintain.SessionInfo" %>
<%@page import="com.framework.util.WebMsgUtil" %>
<%@page import="com.framework.util.SecurityUtil" %>

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
<%if (securityUtil.havePermission("dytm")) {%>
<object
        id="PSKPrn"
        classid="clsid:81C07687-3353-4ABA-B108-94BCE81E5CBA"
        codebase="<%=basePath%>public/ocx/print/PSKPrn.ocx#version=1,0,0,1"
        width="0"
        height="0"
>
</object>
<% }%>
<div id="tb" style="padding:5px 5px;">
    <form method="post" id="searchForm">
        单据编号：<input class="easyui-textbox" id="applyNo" name="QUERY_t#applyNo_S_LK"
                    data-options="prompt:'在此输入完整或部分单据编号...'" style="width:200px"></input>
        车辆种类：<input id="dictIdCarType" name="QUERY_t#dictIdCarType_S_EQ">
        组织：<input id="orgId" name="QUERY_t#orgId_S_EQ">
        <br>
        是否保密：<input id="dictIdIsSecret" name="QUERY_t#dictIdIsSecret_S_EQ">
        用车区域：<input id="dictIdRegion" name="QUERY_t#dictIdRegion_S_EQ">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'ext-icon-zoom'"
           onclick="doSearch()">查询</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'ext-icon-zoom_out'"
           onclick="doClear()">清空条件</a>
    </form>
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'ext-icon-note_add'"
       onclick="sendWD()">无单派车</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'ext-icon-note_add'"
       onclick="sendJJ()">紧急派车</a>

</div>
<div data-options="region:'center',fit:true,border:false" style="height:100%;margin:0px;padding:0px">
    <table id="dg" class="easyui-datagrid" style="width:100%;height:100%;margin:0px;padding:0px">
    </table>
</div>
<form id="sendreport" name="sendreport" method="post" target="report" style="display:none">
</form>
<div id="content" style="display:none">
    <div class="main_4">
        <iframe name="report" width="100%" height="650" scrolling="auto" src="" style="border:0px"></iframe>
    </div>
</div>

</body>
<script>
    var grid;
    function doSearch() {
        grid.datagrid('load', $.extend(sys.serializeObject($("#searchForm")), {
            'QUERY_t#dictIdApplySendStatus_S_IN': '<%=WebMsgUtil.APPLYSENDSTATUS_DPC%>,<%=WebMsgUtil.APPLYSENDSTATUS_YSCPCD%>',
            'QUERY_t#orgIdManager_S_EQ': '<%=sessionInfo.getOrganization().getOrgIdManager()%>'
        }));
    }
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
        // 是否保密
        $('#dictIdIsSecret').combobox({
            editable: false,
            valueField: 'dictId',
            textField: 'dictName',
            url: sys.contextPath + '/maintain/dictionaryManage!doNotNeedSecurity_findAll.cxl',
            queryParams: {
                'QUERY_t#dictFlag_S_EQ': '<%=WebMsgUtil.YOUXIAO %>',
                'QUERY_t#dictClasId_S_EQ': 'IsSecret'
            },
            panelHeight: 'auto',
            panelMaxHeight: '350px'
        });
        // 区域
        $('#dictIdRegion').combobox({
            editable: false,
            valueField: 'dictId',
            textField: 'dictName',
            url: sys.contextPath + '/maintain/dictionaryManage!doNotNeedSecurity_findAll.cxl',
            queryParams: {
                'QUERY_t#dictFlag_S_EQ': '<%=WebMsgUtil.YOUXIAO %>',
                'QUERY_t#dictClasId_S_EQ': 'Region'
            },
            panelHeight: 'auto',
            panelMaxHeight: '350px'
        });

        grid = $('#dg').datagrid(
                {
                    url: sys.contextPath + '/car/apply!grid.cxl',
                    queryParams: {
                        'QUERY_t#dictIdApplySendStatus_S_IN': '<%=WebMsgUtil.APPLYSENDSTATUS_DPC%>,<%=WebMsgUtil.APPLYSENDSTATUS_YSCPCD%>',
                        'QUERY_t#orgIdManager_S_EQ': '<%=sessionInfo.getOrganization().getOrgIdManager()%>'
                    },
                    striped: true,
                    rownumbers: true,
                    pagination: true,
                    singleSelect: true,
                    idField: 'applyId',
                    sortName: 'applyNo',
                    sortOrder: 'desc',
                    pageSize: 30,
                    toolbar: '#tb',
                    method: 'get',
                    pageList: [10, 20, 30, 40, 50],
                    columns: [[{
                        width: '100',
                        title: '编号',
                        field: 'applyNo',
                        sortable: true
                    }, {
                        width: '100',
                        title: '车辆种类',
                        field: 'carType',
                        sortable: true
                    }, {
                        width: '150',
                        title: '组织',
                        field: 'orgName',
                        sortable: true
                    }, {
                        width: '70',
                        title: '警牌/民牌',
                        field: 'carPoliceUsed',
                        sortable: true

                    }, {
                        width: '70',
                        title: '乘车人数',
                        field: 'applyUsedPeople',
                        sortable: false
                    }, {
                        width: '150',
                        title: '预计用车时间',
                        field: 'applyUsingTime',
                        sortable: true
                    }, {
                        width: '150',
                        title: '预计还车时间',
                        field: 'applyRemandTime',
                        sortable: true
                    }, {
                        width: '70',
                        title: '用车区域',
                        field: 'region',
                        sortable: true
                    }, {
                        width: '80',
                        title: '是否保密',
                        field: 'isSecret',
                        sortable: true
                    }, {
                        width: '150',
                        title: '单据状态',
                        field: 'sendStatus',
                        sortable: true
                    }, {
                        width: '150',
                        title: '派车类型',
                        field: 'applyTypeName',
                        sortable: true
                    }, {
                        title: '操作',
                        field: 'options',
                        width: '100',
                        formatter: function (value, row) {
                            var str = sys.formatString('<a href="javascript:void(0)" class="btn1" onclick="send(\'{0}\',\'{1}\')" data-options="plain:true">派车</a>', row.applyId, row.dictIdApplySendStatus);
                            return str;
                        }
                    }]],
                    onBeforeLoad: function (param) {
                        /* parent.$.messager.progress({
                         text : '数据加载中....'
                         }); */
                    },
                    onLoadSuccess: function (data) {
                        $('.btn1').linkbutton({text: '派车', plain: true, height: 18, iconCls: 'ext-icon-car'});
                    }
                });
    });

    function sendreportForm(id) {
        <%--$('#ff').form('submit', {
           url:'<%=basePath%>car/sendReport.cxl?id='+id,
           onSubmit: function(){

           },
           success:function(data){
               prn1_preview();
           }
       }); --%>


        document.sendreport.action = '<%=basePath%>car/sendReport.cxl?id=' + id;
        document.sendreport.submit();
        parent.$.messager.confirm('确认', '是否打印派车单?', function (r) {
            if (r) {
                prn1_preview();
            }
        });
        //setTimeout(prn1_preview(), 5000);
        //setInterval(prn1_preview(),"50000");
    }

    var send = function (id, status) {
        if (status == '<%=WebMsgUtil.APPLYSENDSTATUS_YSCPCD%>') {
            var dialog = parent.sys.modalDialog({
                title: '车辆派遣',
                url: sys.contextPath + '/securityJsp/send/car_send_show.jsp?f=new&applyId=' + id,
                width: 750,
                height: 655,
                onClose: function () {
                    dialog.dialog('destroy');
                },
                buttons: [
                    {
                        text: '打印派车单',
                        handler: function () {
                            var sendId = dialog.find('iframe').get(0).contentWindow.getSendId();
                            if (sendId == undefined || sendId == '') {
                                parent.$.messager.progress('close');
                                parent.$.messager.alert('提示', '派车单据未保存，无法打印！', 'error');
                                return;
                            }
                            //var sendId = dialog.find("#sendId").val();
                            //alert(sendId);
                            sendreportForm(sendId);
                        }
                    }, {
                        text: '打印条码',
                        handler: function () {
                            var sendId = dialog.find('iframe').get(0).contentWindow.getSendId();
                            if (sendId == undefined || sendId == '') {
                                parent.$.messager.progress('close');
                                parent.$.messager.alert('提示', '派车单据未保存，无法打印！', 'error');
                                return;
                            }
                            var code = dialog.find('iframe').get(0).contentWindow.getSendNo();
                            printcode(code);
                        }
                    }, {
                        text: '派车',
                        handler: function () {
                            dialog.find('iframe').get(0).contentWindow.submitSend(dialog, grid, parent.$);
                        }
                    }, {
                        text: '关闭',
                        handler: function () {
                            //dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
                            dialog.dialog('destroy');
                        }
                    }]
            });
        } else {
            var dialog = parent.sys.modalDialog({
                title: '车辆派遣',
                url: sys.contextPath + '/securityJsp/send/car_apply_send_detail.jsp?f=new&id=' + id,
                width: 750,
                height: 655,
                onClose: function () {
                    dialog.dialog('destroy');
                },
                buttons: [{
                    text: '保存',
                    handler: function () {
                        dialog.find('iframe').get(0).contentWindow.submitSave(dialog, grid, parent.$);
                    }
                },
                    {
                        text: '打印派车单',
                        handler: function () {
                            var sendId = dialog.find('iframe').get(0).contentWindow.getSendId();
                            if (sendId == undefined || sendId == '') {
                                parent.$.messager.progress('close');
                                parent.$.messager.alert('提示', '派车单据未保存，无法打印！', 'error');
                                return;
                            }
                            //alert(sendId);
                            sendreportForm(sendId);
                        }
                    }, {
                        text: '打印条码',
                        handler: function () {
                            var sendId = dialog.find('iframe').get(0).contentWindow.getSendId();
                            if (sendId == undefined || sendId == '') {
                                parent.$.messager.progress('close');
                                parent.$.messager.alert('提示', '派车单据未保存，无法打印！', 'error');
                                return;
                            }
                            var code = dialog.find('iframe').get(0).contentWindow.getSendNo();
                            printcode(code);
                        }
                    }, {
                        text: '派车',
                        handler: function () {
                            dialog.find('iframe').get(0).contentWindow.submitSend(dialog, grid, parent.$);
                        }
                    }, {
                        text: '关闭',
                        handler: function () {
                            //dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
                            dialog.dialog('destroy');
                        }
                    }]
            });
        }
    };


    var printcode = function (code) {
        try {
         if(!<%=securityUtil.havePermission("dytm")%>){
         		parent.$.messager.alert('提示', "没有打印条码权限！", 'error');
         return ;
         }
            OpenPrinter();
            //PSKPrn.PTKDrawBar2DQR(50, 50, 200, 200, 0, 50, 2, 0,0, "54545451157878451231321");//QR码
            PSKPrn.PTKDrawBarcode(120, 60, 0, '1', 3, 5, 200, 66, code);//N的ASCII码是78 不显示数据，B的ASCII码是66  显示数据
            PSKPrn.PTKPrintLabel(1, 1);
            ClosePrinter();
        } catch (e) {
           parent.$.messager.alert('提示', "连接不到打印机", 'error');
        }
    };

    function OpenPrinter() {
   
        PSKPrn.OpenPort("POSTEK G3000");
        PSKPrn.PTKClearBuffer();
        PSKPrn.PTKSetPrintSpeed(4);
        PSKPrn.PTKSetDarkness(10);
        PSKPrn.PTKSetLabelHeight(350, 16);
        PSKPrn.PTKSetLabelWidth(550);

    }
    function ClosePrinter() {
        PSKPrn.ClosePort();
    }

    var sendWD = function () {
        var dialog = parent.sys.modalDialog({
            title: '无单派车信息',
            url: sys.contextPath + '/securityJsp/apply/car_apply_detail.jsp?f=send',
            width: 750,
            height: 500,
            onClose: function () {
                dialog.dialog('destroy');
            },
            buttons: [{
                text: '保存',
                handler: function () {
                    dialog.find('iframe').get(0).contentWindow.submitSaveWD(dialog, grid, parent.$);
                }
            }, {
                text: '关闭',
                handler: function () {
                    //dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
                    dialog.dialog('destroy');
                }
            }]
        });
    };

    var sendJJ = function () {
        var dialog = parent.sys.modalDialog({
            title: '紧急派车',
            url: sys.contextPath + '/securityJsp/send/car_apply_send_detail.jsp?f=warn',
            width: 750,
            height: 655,
            onClose: function () {
                dialog.dialog('destroy');
            },
            buttons: [{
                text: '提交',
                handler: function () {
                    dialog.find('iframe').get(0).contentWindow.submitSaveJJ(dialog, grid, parent.$);
                }
            }, {
                text: '关闭',
                handler: function () {
                    //dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
                    dialog.dialog('destroy');
                }
            }]
        });
    };

    var LODOP; //声明为全局变量
    function prn1_preview() {
        CreateOneFormPage();
        LODOP.PREVIEW();
    }
    ;
    function prn1_print() {
        CreateOneFormPage();
        LODOP.PRINT();
    }
    ;
    function prn1_printA() {
        CreateOneFormPage();
        LODOP.PRINTA();
    }
    ;
    function CreateOneFormPage() {
        myIpAddress = '<%=basePath%>';
        LODOP = getLodop();
        LODOP.PRINT_INIT("打印控件功能演示_Lodop功能_打印Iframe");
        strHtml = document.getElementsByTagName("iframe")[0].contentWindow.document.documentElement.innerHTML;
        strHtml = strHtml.replace('<!--[if IE]-->', '').replace('<!--[endif]-->', '');
        LODOP.ADD_PRINT_HTM(0, 0, "100%", "100%", strHtml);

    }
    ;
</script>
</html>
