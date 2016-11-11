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
<%if (securityUtil.havePermission("dytm")){%>
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
        申请单号：<input class="easyui-textbox" id="applyNo" name="QUERY_t#applyNo_S_LK"
                    data-options="prompt:'在此输入完整或部分单据编号...'" style="width:200px"/>
        派车单号：<input class="easyui-textbox" id="sendNo" name="QUERY_t#sendNo_S_LK"
                    data-options="prompt:'在此输入完整或部分单据编号...'" style="width:200px"/>
        领车人：<input class="easyui-textbox" id="userNameHandler" name="QUERY_t#userNameHandler_S_LK"
                   data-options="prompt:'在此输入完整或部分领车人姓名...'" style="width:200px"/>
        状态：<input class="easyui-textbox" id="dictIdApplySendStatus" name="QUERY_t#dictIdApplySendStatus_S_EQ"
                  style="width:200px" />
        <br>
        车辆种类：<input id="dictIdCarType" name="QUERY_t#sendDictIdCarType_S_EQ">
        组织：<input id="orgId" name="QUERY_t#orgId_S_EQ">
        是否保密：<input id="dictIdIsSecret" name="QUERY_t#dictIdIsSecret_S_EQ">
        用车区域：<input id="dictIdRegion" name="QUERY_t#dictIdRegion_S_EQ">
        车牌号：<input class="easyui-textbox" id="carNo" name="QUERY_t#carNo_S_LK" data-options="prompt:'在此输入完整或部分车牌号...'"
                   style="width:200px" />
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'ext-icon-zoom'"
           onclick="doSearch()">查询</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'ext-icon-zoom_out'"
           onclick="doClear()">清空条件</a>
    </form>
</div>
<div data-options="region:'center',fit:true,border:false" style="height:100%;margin:0px;padding:0px">
    <table id="dg" style="width:100%;height:100%;margin:0px;padding:0px">
    </table>
</div>
</body>
<script>
    var grid;
    function doSearch() {
        grid.datagrid('load', $.extend(sys.serializeObject($("#searchForm")), {
            'QUERY_t#orgPath_S_RLK': '<%=sessionInfo.getOrganization().getOrgPath()%>'
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
        $('#dictIdApplySendStatus').combobox({
            editable: false,
            valueField: 'dictId',
            textField: 'dictName',
            url: sys.contextPath + '/maintain/dictionaryManage!doNotNeedSecurity_findAll.cxl',
            queryParams: {
                'QUERY_t#dictFlag_S_EQ': '<%=WebMsgUtil.YOUXIAO %>',
                'QUERY_t#dictClasId_S_EQ': 'ApplySendStatus'
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
                    url: sys.contextPath + '/car/sendStatus!grid.cxl',
                    queryParams: {
                        'QUERY_t#orgPath_S_RLK': '<%=sessionInfo.getOrganization().getOrgPath()%>'
                    },
                    striped: true,
                    rownumbers: true,
                    pagination: true,
                    singleSelect: true,
                    idField: 'id',
                    sortName: 'sendNo',
                    sortOrder: 'desc',
                    pageSize: 30,
                    toolbar: '#tb',
                    method: 'get',
                    pageList: [10, 20, 30, 40, 50],
                    columns: [[{
                        width: '100',
                        title: '申请单号',
                        field: 'applyNo',
                        sortable: true
                    }, {
                        width: '100',
                        title: '派遣单号',
                        field: 'sendNo',
                        sortable: true
                    }, {
                        width: '100',
                        title: '派车方式',
                        field: 'carSendType',
                        sortable: true,
                        formatter: function (value, row) {
                            if (value == undefined || value == '') {
                                return row.applyTypeName;
                            }
                            return value;
                        }
                    }, {
                        width: '110',
                        title: '申请审批状态',
                        field: 'checkStatus',
                        sortable: true,
                        formatter: function (value, row) {
                            if (row.dictIdCarSendType == '<%=WebMsgUtil.CARSENDTYPE_WD%>'
                            		|| row.dictIdCarSendType == '<%=WebMsgUtil.CARSENDTYPE_JJ%>') {
                                return '';
                            }
                            return value;
                        }
                    }, {
                        width: '80',
                        title: '车牌号',
                        field: 'carNo',
                        sortable: true
                    }, {
                        width: '80',
                        title: '状态',
                        field: 'sendStatus',
                        sortable: true
                    }, {
                        width: '70',
                        title: '申请人',
                        field: 'applyUserNameCreate',
                        sortable: false
                    }, {
                        width: '150',
                        title: '申请时间',
                        field: 'applyTimeCreate',
                        sortable: true
                    }, {
                        width: '150',
                        title: '领车人',
                        field: 'userNameHandler',
                        sortable: true
                    }, {
                        width: '150',
                        title: '领车时间',
                        field: 'sendTimeCreate',
                        sortable: true
                    }, {
                        width: '150',
                        title: '还车时间',
                        field: 'retTimeCreate',
                        sortable: true
                    }, {
                        width: '80',
                        title: '是否保密',
                        field: 'isSecret',
                        sortable: true
                    }, {
                        title: '操作',
                        field: 'options',
                        width: '250',
                        formatter: function (value, row) {
                            var str = sys.formatString('<a href="javascript:void(0)" class="btn1" onclick="details(\'{0}\')" data-options="plain:true">详情</a>', row.id);
                            if (row.dictIdApplySendStatus=='<%=WebMsgUtil.APPLYSENDSTATUS_YHC%>'
                            		&& (row.applyId == undefined || row.applyId == '')) {
	                            str += sys.formatString('<a href="javascript:void(0)" class="btn3" onclick="infoCollection(\'{0}\')" data-options="plain:true">信息补录</a>', row.id);
							} else {
	                            str += sys.formatString('<a href="javascript:void(0)" class="btn3" onclick="" data-options="plain:true,disabled:true">信息补录</a>');
							}
                            <%if (securityUtil.havePermission("dytm")) {%>
                            if (row.sendNo!=undefined&&row.sendNo!="") {
                                str += sys.formatString('<a href="javascript:void(0)" class="btn2" onclick="printcode(\'{0}\')" data-options="plain:true">打印条码</a>', row.sendNo);
                            }
                            <%}%>
                             <%if (securityUtil.havePermission("/securityJsp/pgis/pgis_trailForm.jsp")) {%>
                            if (row.sendNo!=undefined&&row.sendNo!="") {
                                str += sys.formatString('<a href="javascript:void(0)" class="btn4" onclick="showTrail(\'{0}\')" data-options="plain:true">轨迹查看</a>', row.carId);
                            }
                            <%}%>
                            return str;
                        }
                    }]],
                    onBeforeLoad: function (param) {
                        /* parent.$.messager.progress({
                         text : '数据加载中....'
                         }); */
                    },
                    onLoadSuccess: function (data) {
                        $('.btn1').linkbutton({text: '详情', plain: true, height: 18, iconCls: 'ext-icon-zoom'});
                        $('.btn2').linkbutton({text: '打印条码', plain: true, height: 18, iconCls: 'ext-icon-printer'});
                        $('.btn3').linkbutton({text: '信息补录', plain: true, height: 18, iconCls: 'ext-icon-printer'});
                         $('.btn4').linkbutton({text: '轨迹查看', plain: true, height: 18, iconCls: 'ext-icon-zoom'});
                    }
                });
    });
    
     //车辆轨迹
    function showTrail(carId){

		var dialog = parent.sys.modalDialog({
			title : '详情',
			url : sys.contextPath + '/securityJsp/pgis/pgis_trailForm.jsp?id='+carId,
			width: 750,
			height: 550,
			onClose:function(){dialog.dialog('destroy');},
			buttons : [ {
				text : '关闭',
				handler : function() {
					//dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
					dialog.dialog('destroy');
				}
			} ]
		});
    	
    }
    
    var infoCollection = function(id){
    	var dialog = parent.sys.modalDialog({
			title : '信息补录',
			url : sys.contextPath + '/securityJsp/apply/car_apply_detail.jsp?f=send',
			width: 750,
			height: 500,
			onClose:function(){dialog.dialog('destroy');},
			buttons : [ {
				text : '保存',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitInfoCollection(dialog, grid, parent.$, id);
				}
			},{
				text : '关闭',
				handler : function() {
					//dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
					dialog.dialog('destroy');
			} } ]
		});
    };
    var details = function (id) {
        var dialog = parent.sys.modalDialog({
            title: '详情',
            url: sys.contextPath + '/securityJsp/apply/car_query_detail.jsp?id=' + id,
            width: 750,
            height: 550,
            onClose: function () {
                dialog.dialog('destroy');
            },
            buttons: [{
                text: '关闭',
                handler: function () {
                    //dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
                    dialog.dialog('destroy');
                }
            }]
        });
    };
    var printcode=function (code) {
        try {
        OpenPrinter();
        //PSKPrn.PTKDrawBar2DQR(50, 50, 200, 200, 0, 50, 2, 0,0, "54545451157878451231321");//QR码
        PSKPrn.PTKDrawBarcode(120, 60, 0, '1', 3, 5, 200, 66, code);//N的ASCII码是78 不显示数据，B的ASCII码是66  显示数据
        PSKPrn.PTKPrintLabel(1,1);
        ClosePrinter();
        }catch (e){
            $.messager.alert('提示', "连接不到打印机", 'error');
        }
    };

    function OpenPrinter()
    {
        PSKPrn.OpenPort("POSTEK G3000");
        PSKPrn.PTKClearBuffer();
        PSKPrn.PTKSetPrintSpeed(4);
        PSKPrn.PTKSetDarkness(10);
        PSKPrn.PTKSetLabelHeight(350, 16);
        PSKPrn.PTKSetLabelWidth(550);

    }
    function ClosePrinter()
    {
        PSKPrn.ClosePort();
    }
</script>
</html>
