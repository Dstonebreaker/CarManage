<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="com.system.entity.maintain.SessionInfo" %>
<%@page import="com.framework.util.ConfigUtil" %>
<%@page import="com.framework.util.WebMsgUtil" %>
<%@ page import="static com.framework.util.WebMsgUtil.IMGTYPE_CARD" %>
<%@ page import="static com.framework.util.WebMsgUtil.IMGTYPE_PERSON" %>
<%@ page import="com.framework.util.SecurityUtil" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    String id = request.getParameter("id");
    String f = request.getParameter("f");
    SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
    SecurityUtil securityUtil = new SecurityUtil(session);
%>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <jsp:include page="../../inc.jsp"></jsp:include>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
<style>
    .textbox {
        height: 20px;
    }
</style>
<%
    if (securityUtil.havePermission("dk")) {
%>
<OBJECT classid=clsid:730BF2F0-EAE2-46C5-BA06-5ABFC9AB8A0A width=0 height=0 align="center"
        codebase="<%=basePath%>public/ocx/readcard/zx_32.cab#version=1,0,0,1" id="CZx_32Ctrl" HSPACE=0 VSPACE=0>
</OBJECT>
<% }%>
<div id="tabs" class="easyui-tabs" style="height:680px">
    <div title="派车信息">
        <form method="post" id="detailForm">
            <div id="light" style="margin-top:10px">
                <input type="hidden" id="sendId" name="send.sendId" value="">
                <input type="hidden" id="applyId" name="send.applyId" value="">
                <%--<input type="hidden" id="userIdCreate" name="send.userIdCreate" value="">--%>
                <%--<input type="hidden" id="timeCreate" name="send.timeCreate" value="">--%>
                <input type="hidden" id="dictIdCarSendType" name="send.dictIdCarSendType" value="">
                <table width="100%" align="center">
                    <tr>
                        <td>派车单号：</td>
                        <td>
                            <input class="easyui-textbox" id="sendNo" name="send.sendNo"/>
                        </td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>申请单位：</td>
                        <td>
                            <input class="textbox" id="orgId" name="send.orgId"/>
                            <input class="textbox" id="orgName" name="orgName"/>
                        </td>
                        <td>领车人：</td>
                        <td>
                            <input class="textbox" id="userIdHandler" name="send.userIdHandler"/>
                        </td>
                    </tr>
                    <tr>
                        <td>出车里程(KM)：</td>
                        <td>
                            <input class="textbox" id="sendMileage" name="send.sendMileage"/>
                        </td>
                        <td>出车油量(L)：</td>
                        <td>
                            <input class="textbox" id="sendOil" name="send.sendOil"/>
                        </td>
                    </tr>
                    <tr>
                        <td>驾驶员：</td>
                        <td>
                            <input class="textbox" id="userIdPilot" name="send.userIdPilot"/>
                        </td>
                        <td>驾驶员手机：</td>
                        <td>
                            <input class="textbox" id="stafPhonePilot" name="send.stafPhonePilot"/>
                        </td>
                    </tr>
                    <tr>
                        <td>驾驶员准驾车型：</td>
                        <td>
                            <input class="textbox" id="dictIdDrivingLlicenseKind"
                                   name="send.dictIdDrivingLlicenseKind"/>
                        </td>
                    </tr>
                    <tr>
                        <td>领车人拍照：</td>
                        <td><img alt="领车人照片" id="personimg" onclick="personphoto();"
                                 src="<%=basePath %>public/style/images/pic_none.png" width="150px" height="100px">
                            <input class="textbox" id="sendHandlerPicture" name="send.sendHandlerPicture" type="hidden">
                        </td>
                        <td>派车证件：</td>
                        <td><img alt="派车证件" id="cardimg" onclick="carphoto();"
                                 src="<%=basePath %>public/style/images/pic_none.png" width="150px" height="100px">
                            <input id="sendFile" name="send.sendFile" type="hidden"></td>
                    </tr>
                    <tr>
                        <td>备注</td>
                        <td colspan="3">
                            <input id="sendMemo" name="send.sendMemo" class="easyui-textbox"
                                   data-options="multiline:true,validType:'maxLength[190]',width:'540'"
                                   style="height:50px">
                        </td>
                    </tr>
                    <%
                        if (f != null && !f.equals("new") && !f.equals("warn")) {
                    %>
                    <tr>
                        <td>登记人：</td>
                        <td>
                            <input class="textbox" id="userIdCreate" name="send.userIdCreate" readonly="readonly"/>
                        </td>
                        <td>登记时间：</td>
                        <td>
                            <input class="textbox" id="timeCreate" name="send.timeCreate" readonly="readonly"/>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </table>
                <!-- <table id="dg" width="100%" align="center"></table> -->
            </div>
            <div data-options="region:'center',fit:true,border:false" style="height:240px;margin:0px;padding:0px">
                <table id="dg" class="easyui-datagrid" style="width:100%;height:100%;margin:0px;padding:0px">
                </table>
            </div>
        </form>
        <div id="tb" style="padding:5px 5px;">
            <form method="post" id="searchForm">
                车牌号/钥匙号：<input class="easyui-textbox" id="anyNo" name="anyNo"
                               data-options="prompt:'在此输入完整或部分车牌号/钥匙号...'" style="width:200px"/>
                <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'ext-icon-zoom'"
                   onclick="doSearch()">查询</a>
                <%
                    if (securityUtil.havePermission("dk")) {
                %>
                <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'ext-icon-zoom'"
                   onclick="readCard()">读卡</a>
                <% }%>
                <a href="javascript:void(0)" class="easyui-linkbutton"
                   data-options="plain:true,iconCls:'ext-icon-zoom_out'"
                   onclick="doClear()">清空条件</a>
            </form>
        </div>
    </div>
    <div title="申请信息" id="apply_iframe">
		<form method="post" id="applyForm">
		<div id="applylight" style="margin-bottom:10px;margin-top:10px">
			<table width="100%" align="center">
				<tr>
					<td>申请编号：</td>
					<td>
						<input class="textbox" name="apply.applyNo" readonly="readonly" width="250px"/>
					</td>
					<td>组织单位：</td>
					<td>
						<input class="textbox" name="apply.orgId" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td>申请车辆种类：</td>
					<td>
						<input class="textbox" name="apply.dictIdCarType" readonly="readonly"/>
					</td>
					<td>用车区域：</td>
					<td>
						<input class="textbox" name="apply.dictIdRegion" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td>预计用车时间：</td>
					<td>
						<input class="textbox" name="applyUsingTime" readonly="readonly"/>
					</td>
					<td>预计还车时间：</td>
					<td>
						<input class="textbox" name="applyRemandTime" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td>用车理由：</td>
					<td colspan="5">
						<input class="easyui-textbox textbox" name="apply.useCarReson"
							data-options="width:516" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td>申请固定车辆：</td>
					<td>
						<input class="textbox" name="apply.carId" readonly="readonly"/>
					</td>
					<td>是否保密：</td>
					<td>
						<input class="textbox" name="apply.dictIdIsSecret" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td>警牌/民牌：</td>
					<td>
						<input class="textbox" name="apply.carPoliceUsed"/>
					</td>
					<td>乘车人数：</td>
					<td>
						<input class="textbox" name="apply.applyUsedPeople" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td>申请人：</td>
					<td>
						<input class="textbox" name="apply.userIdLinkman" readonly="readonly"/>
					</td>
					<td>申请人手机：</td>
					<td>
						<input class="textbox" name="apply.stafPhoneLinkman" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td>登记人：</td>
					<td>
						<input class="easyui-textbox textbox" name="apply.userIdCreate" readonly="readonly"/>
					</td>
					<td>登记时间：</td>
					<td>
						<input class="easyui-textbox textbox" name="timeCreate" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td>备注：</td>
					<td colspan="5">
						<input name="apply.applyMemo" class="easyui-textbox" readonly="readonly"
							data-options="multiline:true,validType:'maxLength[190]',width:'516'" style="height:50px">
					</td>
				</tr>
				<tr class="deptApproval">
				<tr>
					<td>审批状态：</td>
					<td colspan="5">
						<input class="easyui-textbox textbox" name="checkStatus" readonly="readonly"/>
					</td>
				</tr>
				<tr class="deptApproval">
					<td>处长审批人：</td>
					<td>
						<input class="easyui-textbox textbox" name="apply.userIdCheck1" readonly="readonly"/>
					</td>
					<td>处长审批时间：</td>
					<td>
						<input class="easyui-textbox textbox" name="timeCheck1" readonly="readonly"/>
					</td>
				</tr>
				<tr class="deptApproval">
					<td>处长审批意见：</td>
					<td colspan="5">
						<input name="apply.applyCheck1" class="easyui-textbox" readonly="readonly"
							data-options="multiline:true,validType:'maxLength[95]',width:'516'" style="height:50px">
					</td>
				</tr>
				<tr class="officeApproval">
					<td>处审批人：</td>
					<td>
						<input class="easyui-textbox textbox" name="apply.userIdCheck2" readonly="readonly"/>
					</td>
					<td>处审批时间：</td>
					<td>
						<input class="easyui-textbox textbox" name="timeCheck2" readonly="readonly"/>
					</td>
				</tr>
				<tr class="officeApproval">
					<td>处审批意见：</td>
					<td colspan="5">
						<input name="apply.applyCheck2" class="easyui-textbox" readonly="readonly"
							data-options="multiline:true,validType:'maxLength[95]',width:'516'" style="height:50px">
					</td>
				</tr>
				<tr class="userSign">
					<td>处长签名：</td>
					<td colspan="5">
						<img alt="处长手工签名" id="userSignImg" width="150px" height="100px">
					</td>
				</tr>
			</table>
		</div>
		</form>
    </div>
</div>
<script type="text/javascript">
    var params;
    var sendinfo;//申请单信息
    //读卡查询车辆
    var readCard = function () {
		if(!<%=securityUtil.havePermission("dk")%>){
		 parent.$.messager.alert("提示", "没有读卡权限", 'error');
		return;
		}
        var st;
        try {
            st= CZx_32Ctrl.HBOpen();
        }catch (e){
            parent.$.messager.alert("提示", "打开设备失败", 'error');
        }
        if ((st == 0 || st < 0) && CZx_32Ctrl.lErrorCode != 0) {
            parent.$.messager.alert("提示", "初始化读卡器失败", 'error');
        }
        else {
            var ret = CZx_32Ctrl.RfCard(st, 1);
            if (ret.length==0){
                ret= CZx_32Ctrl.RfCard(st, 1);
            }
            if (CZx_32Ctrl.lErrorCode == 0) {
                CZx_32Ctrl.DevBeep(st,10, 10, 1);
                $('#dg').datagrid({
                    url: sys.contextPath + '/car/vstatus!gridByManager.cxl',
                    queryParams: {
                        'anyNo':ret,
                        'orgId': sendinfo.orgId,
                        'dictIdCarType': sendinfo.dictIdCarType
                    },
                    onLoadSuccess: function (data) {
                        if(data.length==1){
                            $('#dg').selectRow(0);
                        }
                    }
                });
            }
            else {
                parent.$.messager.alert("提示", "读卡失败", 'error');
            }
            try {
                CZx_32Ctrl.HBClose();
            } catch (e) {
            parent.$.messager.alert("提示", "读卡失败", 'error');
            }
        }

    };

    function doSearch() {
        $('#dg').datagrid('load', $.extend(params, {
            'anyNo': $('#anyNo').textbox('getValue')
        }));
    }
    function doClear() {
        $("#searchForm").form('clear');
    }
    function personphoto() {
        var dialog = parent.sys.modalDialog({
            title: '领车人拍照',
            url: sys.contextPath
            + '/securityJsp/send/talk_photo.jsp?type=<%=IMGTYPE_PERSON%>',
            onClose: function () {
                dialog.dialog('destroy');

            },
            onDestroy: function () {
                var reurl = sys.contextPath + "/car/send!doNotNeedSecurity_getsessionInfo.cxl";
                $.post(reurl, {type: '<%=IMGTYPE_PERSON%>'}, function (result) {
                    var photourl = sys.contextPath + result.msg;
                    $('#personimg').attr("src", photourl);
                    $('#sendHandlerPicture').val(result.msg);
                }, 'json');

            },
            buttons: [{
                text: '拍照',
                handler: function () {
                    dialog.find('iframe').get(0).contentWindow.talkphoto();
                }
            }, {
                text: '保存',
                handler: function () {
                    dialog.find('iframe').get(0).contentWindow.submitForm(
                            dialog);
                }
            }]
        });
    }
    function carphoto() {
        var dialog = parent.sys.modalDialog({
            title: '驾驶证拍照',
            url: sys.contextPath
            + '/securityJsp/send/talk_photo.jsp?type=<%=IMGTYPE_CARD%>',
            onClose: function () {
                dialog.dialog('destroy');
            },
            onDestroy: function () {
                var reurl = sys.contextPath + "/car/send!doNotNeedSecurity_getsessionInfo.cxl";
                $.post(reurl, {type: '<%=IMGTYPE_CARD%>'}, function (result) {
                    var photourl = sys.contextPath + result.msg;
                    $('#cardimg').attr("src", photourl);
                    $('#sendFile').val(result.msg);
                }, 'json');

            },
            buttons: [{
                text: '拍照',
                handler: function () {
                    dialog.find('iframe').get(0).contentWindow.talkphoto();
                }
            }, {
                text: '保存',
                handler: function () {
                    dialog.find('iframe').get(0).contentWindow.submitForm(
                            dialog);


                }
            }]
        });
    }


    $(function () {
        $.extend($.fn.validatebox.defaults.rules, {
            phoneRex: {
                validator: function (value) {
                    var rex = /^1[3-8]+\d{9}$/;
                    //var rex=/^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
                    //区号：前面一个0，后面跟2-3位数字 ： 0\d{2,3}
                    //电话号码：7-8位数字： \d{7,8}
                    //分机号：一般都是3位数字： \d{3,}
                    //这样连接起来就是验证电话的正则表达式了：/^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/
                    var rex2 = /^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
                    if (rex.test(value) || rex2.test(value)) {
                        // alert('t'+value);
                        return true;
                    } else {
                        //alert('false '+value);
                        return false;
                    }

                },
                message: '请输入正确电话或手机格式'
            }
        });
        /* parent.$.messager.progress({
         text : '数据加载中....'
         }); */
        $('#sendNo').textbox({
            required: true,
            validType: 'maxLength[35]',
            disabled: true
        });
        $('#stafPhonePilot').textbox({
            required: true,
            validType: 'phoneRex'
        });
        /* $('#dictIdDrivingLlicenseKind').textbox({
         required:true,
         validType: 'maxLength[35]',
         disabled : true
         }); */
        $('#sendMileage').numberbox({
            required: true,
            min: 0,
            precision: 2
        });
        $('#sendOil').numberbox({
            required: true,
            min: 0,
            precision: 2
        });
        if ('<%=f%>' != 'warn') {
            $('#orgId').hide();
            $('#orgName').textbox({
                disabled: true
            });
        } else {
            $('#orgName').hide();
            $('#orgId').combotree({
                required: true,
                editable: false,
                panelWidth: 300,
                panelHeight: 'auto',
                panelMaxHeight: '350px',
                idField: 'id',
                textField: 'text',
                parentField: 'pid',
                url: sys.contextPath + '/maintain/sysOrganization!doNotNeedSecurity_comboTreeByManager.cxl?orgIdManager=<%=sessionInfo.getOrganization().getOrgIdManager()%>&id=',
                onBeforeLoad: function (row, param) {
                    parent.$.messager.progress({
                        text: '数据加载中....'
                    });
                    if (row) {
                        $('#orgId').combotree('tree').tree('options').url = sys.contextPath
                                + '/maintain/sysOrganization!doNotNeedSecurity_comboTreeByManager.cxl?orgIdManager=<%=sessionInfo.getOrganization().getOrgIdManager()%>&id='
                                + row.id;
                    }
                },
                onSelect: function (record) {
                    $('#userIdHandler').combobox({
                        required: true,
                        editable: false,
                        valueField: 'userId',
                        textField: 'userName',
                        url: sys.contextPath + '/maintain/sysUser!doNotNeedSecurity_findVUserList.cxl',
                        queryParams: {
                            'QUERY_t#dictIdFlag_S_EQ': '<%=WebMsgUtil.YOUXIAO %>',
                            'QUERY_t#orgId_S_EQ': record.id
                        },
                        panelHeight: 'auto',
                        panelMaxHeight: '350px'
                    });
                    $('#userIdPilot').combobox({
                        required: true,
                        editable: false,
                        valueField: 'userId',
                        textField: 'userName',
                        url: sys.contextPath + '/maintain/sysUser!doNotNeedSecurity_findVUserList.cxl',
                        queryParams: {
                            'QUERY_t#dictIdFlag_S_EQ': '<%=WebMsgUtil.YOUXIAO %>',
                            'QUERY_t#orgId_S_EQ': record.id
                        },
                        panelHeight: 'auto',
                        panelMaxHeight: '350px',
                        onSelect: function (record) {
                            $('#stafPhonePilot').textbox('setValue', record.userPhone);
                            $('#dictIdDrivingLlicenseKind').combobox('setValue', record.dictIdDrivingLlicenseKind);
                        }
                    });
                    params = {
                        'orgId': record.id
                    };
                    $('#dg').datagrid({
                        url: sys.contextPath + '/car/vstatus!gridByManager.cxl',
                        queryParams: {
                            'orgId': record.id
                        }
                    });
                },
                onLoadSuccess: function (row, data) {
                    parent.$.messager.progress('close');
                }
            });
        }
        $('#userIdHandler').combobox({
            required: true,
            editable: false,
            valueField: 'userId',
            textField: 'userName',
            url: sys.contextPath + '/maintain/sysUser!doNotNeedSecurity_findAll.cxl',
            queryParams: {
                'QUERY_t#dictIdFlag_S_EQ': '<%=WebMsgUtil.YOUXIAO %>'
            },
            panelHeight: 'auto',
            panelMaxHeight: '350px'
        });
        $('#userIdPilot').combobox({
            required: true,
            editable: false,
            valueField: 'userId',
            textField: 'userName',
            url: sys.contextPath + '/maintain/sysUser!doNotNeedSecurity_findVUserList.cxl',
            queryParams: {
                'QUERY_t#dictIdFlag_S_EQ': '<%=WebMsgUtil.YOUXIAO %>'
            },
            panelHeight: 'auto',
            panelMaxHeight: '350px',
            onSelect: function (record) {
                $('#stafPhonePilot').textbox('setValue', record.userPhone);
                $('#dictIdDrivingLlicenseKind').combobox('setValue', record.dictIdDrivingLlicenseKind);
            }
        });
        $('#dictIdDrivingLlicenseKind').combobox({
            required: true,
            editable: false,
            valueField: 'dictId',
            textField: 'dictName',
            url: sys.contextPath + '/maintain/dictionaryManage!doNotNeedSecurity_findAll.cxl',
            queryParams: {
                'QUERY_t#dictFlag_S_EQ': '<%=WebMsgUtil.YOUXIAO %>',
                'QUERY_t#dictClasId_S_EQ': 'DrivingLlicenseKind'
            },
            panelHeight: 'auto',
            panelMaxHeight: '350px'
        });

        var id = "<%=id%>";
        if (id != "null" && id.length > 0) {
            $.post(sys.contextPath + '/car/apply!getByViewId.cxl', {
                id: id
            }, function (result) {
                sendinfo=result;
                if (result.applyId != undefined) {
                    $('form').form('load', {
                        'send.applyId': result.applyId,
                        'send.sendNo': result.sendNo,
                        'send.orgId': result.orgId,
                        'send.userIdPilot': result.userIdPilot,
                        'send.stafPhonePilot': result.stafPhonePilot,
                        'send.dictIdDrivingLlicenseKind': result.dictIdDrivingLlicenseKind,
                        'orgName': result.orgName,
                        'send.dictIdCarSendType': result.applyType
                    });
                    if (result.sendFile != undefined && result.sendFile.length > 0) {
                        $('#cardimg').attr("src", '<%=basePath%>' + result.sendFile);
                    }
                    if (result.sendHandlerPicture != undefined && result.sendHandlerPicture.length > 0) {
                        $('#personimg').attr("src", '<%=basePath%>' + result.sendHandlerPicture);
                    }
                    $('#userIdHandler').combobox({
                        required: true,
                        editable: false,
                        valueField: 'userId',
                        textField: 'userName',
                        url: sys.contextPath + '/maintain/sysUser!doNotNeedSecurity_findVUserList.cxl',
                        queryParams: {
                            'QUERY_t#dictIdFlag_S_EQ': '<%=WebMsgUtil.YOUXIAO %>',
                            'QUERY_t#orgId_S_EQ': result.orgId
                        },
                        panelHeight: 'auto',
                        panelMaxHeight: '350px'
                    });
                    $('#userIdPilot').combobox({
                        required: true,
                        editable: false,
                        valueField: 'userId',
                        textField: 'userName',
                        url: sys.contextPath + '/maintain/sysUser!doNotNeedSecurity_findVUserList.cxl',
                        queryParams: {
                            'QUERY_t#dictIdFlag_S_EQ': '<%=WebMsgUtil.YOUXIAO %>',
                            'QUERY_t#orgId_S_EQ': result.orgId
                        },
                        panelHeight: 'auto',
                        panelMaxHeight: '350px',
                        onSelect: function (record) {
                            $('#stafPhonePilot').textbox('setValue', record.userPhone);
                            $('#dictIdDrivingLlicenseKind').combobox('setValue', record.dictIdDrivingLlicenseKind);
                        }
                    });

                    var url;
                    if (result.carId == undefined || result.carId == null || result.carId == '') {
                        url = sys.contextPath + '/car/vstatus!gridByManager.cxl';
                        params = {
                            'orgId': result.orgId,
                            'dictIdCarType': result.dictIdCarType,
                            'carPoliceUsed': result.carPoliceUsed
                        };
                    } else {
                        url = sys.contextPath + '/car/vstatus!gridForSend.cxl';
                        params = {
                            'dictIdCarStatus': '<%=WebMsgUtil.CARSTATUS_FREE %>',
                            'carId': result.carId,
                            'carPoliceUsed': result.carPoliceUsed
                        };
                    }

                    // 车辆列表
                    $('#dg').datagrid(
                            {
                                url: url,
                                queryParams: params,
                                striped: true,
                                rownumbers: true,
                                //pagination: true,
                                singleSelect: true,
                                checkOnSelect: true,
                                selectOnCheck: true,
                                toolbar: '#tb',
                                idField: 'carId',
                                sortName: 'carNo',
                                sortOrder: 'asc',
                                //pageSize: 10,
                                method: 'get',
                                /* pageList: [10, 20, 30, 40, 50, 100, 200, 300,
                                    400, 500], */
                                columns: [[{
                                    field: 'ck',
                                    checkbox: true
                                }, {
                                    width: '100',
                                    title: '车辆资产编码',
                                    field: 'carAssetsNo',
                                    sortable: true
                                }, {
                                    width: '100',
                                    title: '车牌号',
                                    field: 'carNo',
                                    sortable: true
                                }, {
                                    width: '100',
                                    title: '车辆种类',
                                    field: 'carType',
                                    sortable: true
                                }, {
                                    width: '100',
                                    title: '型号',
                                    field: 'modelName',
                                    sortable: true
                                }, {
                                    width: '100',
                                    title: '车身颜色',
                                    field: 'color',
                                    sortable: true
                                }, {
                                    width: '100',
                                    title: '环保标志',
                                    field: 'environmentProtection',
                                    sortable: true
                                    /* },{
                                     title : '操作',
                                     field : 'options',
                                     width : '200',
                                     formatter : function(value, row) {
                                     var str = sys.formatString('<a href="javascript:void(0)" class="btn1" onclick="send(\'{0}\')" data-options="plain:true">派车</a>', row.carId);
                                     return str;
                                     } */
                                }]],
                                onSelect : function (index, row) {
                                	$('#sendMileage').numberbox('setValue', row.carMileage);
                                	$('#sendOil').numberbox('setValue', row.carOil * 0.001);
                                },
                                onBeforeLoad: function (param) {
                                    /* parent.$.messager.progress({
                                     text : '数据加载中....'
                                     }); */
                                },
                                onLoadSuccess: function (data) {
                                    //$('.btn1').linkbutton({text:'派车', plain:true, height:18, iconCls:'ext-icon-car'});
                                    if (data.total < 1 && result.carId != undefined && result.carId != null && result.carId != '') {
                                        parent.$.messager.alert('提示', '选择的固定车辆已被派出，将筛选所有符合条件的车辆！', 'info');
                                        $('#dg').datagrid({
                                            url: sys.contextPath + '/car/vstatus!gridByManager.cxl',
                                            queryParams: {
                                                'orgId': result.orgId,
                                                'dictIdCarType': result.dictIdCarType
                                            },
                                            onLoadSuccess: function (data) {

                                            }
                                        });
                                    }
                                }
                            });
                    /* $("#apply_iframe").html("<iframe src='" + sys.contextPath
                            + "/securityJsp/apply/car_apply_approval_form.jsp?f=details&id=" + result.applyId
                            + "' frameborder='no' border=0 style='width: 100%; height: 99%;'></iframe>"); */
                    $.post(sys.contextPath + '/car/apply!getByViewId.cxl', {
            			id : result.applyId
            		}, function(result) {
            			if (result.applyId != undefined) {
            				$('#applyForm').form('load', {
            					'apply.applyNo' : result.applyNo,
            					'apply.dictIdCarType' : result.carType,
            					'apply.orgId' : result.orgName,
            					'apply.carId' : result.carNo,
            					'apply.userIdLinkman' : result.linkManName,
            					'apply.stafPhoneLinkman' : result.stafPhoneLinkman,
            					'apply.carPoliceUsed' : result.carPoliceUsed,
            					'apply.applyUsedPeople' : result.applyUsedPeople,
            					'apply.useCarReson' : result.useCarReson,
            					'apply.dictIdRegion' : result.region,
            					'apply.userIdPilot' : result.pilotName,
            					'apply.stafPhonePilot' : result.stafPhonePilot,
            					'apply.dictIdDrivingLlicenseKind' : result.drivingLlicenseKind,
            					'apply.dictIdIsSecret' : result.isSecret,
            					'apply.applyMemo' : result.applyMemo,
            					'apply.userIdCreate' : result.userNameCreate,
            					'apply.userIdCheck1' : result.userNameCheck1,
            					'apply.applyCheck1' : result.applyCheck1,
            					'timeCheck1' : result.timeCheck1,
            					'apply.userIdCheck2' : result.userNameCheck2,
            					'apply.applyCheck2' : result.applyCheck2,
            					'timeCheck2' : result.timeCheck2,
            					'timeCreate' : result.timeCreate,
            					'applyUsingTime' : result.applyUsingTime,
            					'applyRemandTime' : result.applyRemandTime,
            					'checkStatus' : result.checkStatus
            				});
            				if (result.userNameCheck1 == undefined || result.userNameCheck1 == "") {
            					$('.deptApproval').hide();
            					$('.userSign').hide();
            				}
            				if (result.userNameCheck2 == undefined || result.userNameCheck2 == "") {
            					$('.officeApproval').hide();
            				}
            				$('#userSignImg').attr('src', '<%=basePath%>maintain/sysUser!doNotNeedSecurity_getimage.cxl?id=' + result.userIdCheck1);
            			}
            		}, 'json');
                }
            }, 'json');
        } else {
            if ('<%=f%>' == 'warn') {
                params = {
                    'dictIdCarStatus': '<%=WebMsgUtil.CARSTATUS_FREE %>'
                };
                $('#dg').datagrid(
                        {
                            url: sys.contextPath + '/car/vstatus!gridForSend.cxl',
                            queryParams: params,
                            striped: true,
                            rownumbers: true,
                            pagination: true,
                            singleSelect: true,
                            checkOnSelect: true,
                            selectOnCheck: true,
                            idField: 'carId',
                            sortName: 'carNo',
                            sortOrder: 'asc',
                            toolbar: '#tb',
                            //pageSize: 10,
                            method: 'get',
                            /* pageList: [10, 20, 30, 40, 50, 100, 200, 300,
                                400, 500], */
                            columns: [[{
                                field: 'ck',
                                checkbox: true
                            }, {
                                width: '100',
                                title: '车辆资产编码',
                                field: 'carAssetsNo',
                                sortable: true
                            }, {
                                width: '100',
                                title: '车牌号',
                                field: 'carNo',
                                sortable: true
                            }, {
                                width: '100',
                                title: '车辆种类',
                                field: 'carType',
                                sortable: true
                            }, {
                                width: '100',
                                title: '型号',
                                field: 'modelName',
                                sortable: true
                            }, {
                                width: '100',
                                title: '车身颜色',
                                field: 'color',
                                sortable: true
                            }, {
                                width: '100',
                                title: '环保标志',
                                field: 'environmentProtection',
                                sortable: true
                                /* },{
                                 title : '操作',
                                 field : 'options',
                                 width : '200',
                                 formatter : function(value, row) {
                                 var str = sys.formatString('<a href="javascript:void(0)" class="btn1" onclick="send(\'{0}\')" data-options="plain:true">派车</a>', row.carId);
                                 return str;
                                 } */
                            }]],
                            onSelect : function (index, row) {
                            	$('#sendMileage').numberbox('setValue', row.carMileage);
                            	$('#sendOil').numberbox('setValue', row.carOil * 0.001);
                            },
                            onBeforeLoad: function (param) {
                                /* parent.$.messager.progress({
                                 text : '数据加载中....'
                                 }); */
                            },
                            onLoadSuccess: function (data) {
                                //$('.btn1').linkbutton({text:'派车', plain:true, height:18, iconCls:'ext-icon-car'});
                            }
                        });
                //$("#apply_iframe").remove();
                $("#tabs").tabs('close', '申请信息');
            }
            parent.$.messager.progress('close');
        }

    });

    var submitSave = function ($dialog, $grid, $pjq) {
        var sendId = $("#sendId").val();
        if (sendId != undefined && sendId != '') {
            $pjq.messager.progress('close');
            $pjq.messager.alert('提示', '不可重复保存！', 'error');
            return;
        }
        if (typeof JSON == 'undefined') {
            $('head').append($("<script type='text/javascript' src='../../public/jslib/json2.js'>"));
        }

        var dataform = $("#detailForm");
        if (dataform.form('validate')) {
            $pjq.messager.progress({
                text: '数据处理中....'
            });
            var row = $("#dg").datagrid('getSelected');
            if (row == undefined || row == null) {
                $pjq.messager.progress('close');
                $pjq.messager.alert('提示', '未选择车辆！', 'error');
                return;
            }
            var params = $("#applyId").serialize();
            if ($("#orgName").textbox('getValue') == undefined
                    || $("#orgName").textbox('getValue') == '') {
                params += "&send.orgId=" + $("#orgId").combobox('getValue');
            } else {
                params += "&send.orgId=" + $("#orgId").val();
            }
            params += "&send.userIdHandler=" + $("#userIdHandler").combobox('getValue');
            params += "&send.dictIdCarSendType=" + $("#dictIdCarSendType").val();
            params += "&send.sendMemo=" + $("#sendMemo").textbox('getText');
            params += "&send.sendMileage=" + $("#sendMileage").numberbox('getValue');
            params += "&send.sendOil=" + $("#sendOil").numberbox('getValue');
            params += "&send.dictIdDrivingLlicenseKind=" + $("#dictIdDrivingLlicenseKind").combobox('getValue');
            params += "&send.userIdPilot=" + $("#userIdPilot").combobox('getValue');
            params += "&send.stafPhonePilot=" + $("#stafPhonePilot").textbox('getText');
            params += "&send.sendHandlerPicture=" + $('#sendHandlerPicture').val();
            params += "&send.sendFile=" + $('#sendFile').val();
            params += "&carJson=" + JSON.stringify(row);
            var url = sys.contextPath + '/car/send!save.cxl';
            $.post(url, params, function (result) {
                $pjq.messager.progress('close');
                if (result.success) {
                    $grid.datagrid('reload');
                    var recode = result.msg.split("#");
                    $("#sendId").val(recode[0]);
                    $("#sendNo").textbox('setValue',recode[1]);
                    $pjq.messager.alert('提示', '单据已保存！', 'info');
                    //sendreportForm(id);
                } else {
                    $pjq.messager.alert('提示', result.msg, 'error');
                }
            }, 'json');
        }
    };

    var submitSend = function ($dialog, $grid, $pjq) {
        var sendId = $("#sendId").val();
        if (sendId == undefined || sendId == '') {
            $pjq.messager.progress('close');
            $pjq.messager.alert('提示', '派车单据未保存，无法派车！', 'error');
            return;
        }
        if (typeof JSON == 'undefined') {
            $('head').append($("<script type='text/javascript' src='../../public/jslib/json2.js'>"));
        }
        if ($("#detailForm").form('validate')) {
            var params = "id=" + sendId;
            var url = sys.contextPath + '/car/send!send.cxl';
            $.post(url, params, function (result) {
                $pjq.messager.progress('close');
                if (result.success) {
                    $grid.datagrid('reload');
                    $pjq.messager.alert('提示', result.msg, 'info');
                    //sendreportForm(id);
                } else {
                    $pjq.messager.alert('提示', result.msg, 'error');
                }
            }, 'json');
        }
    };

    var submitSaveJJ = function ($dialog, $grid, $pjq) {
        if (typeof JSON == 'undefined') {
            $('head').append($("<script type='text/javascript' src='../../public/jslib/json2.js'>"));
        }

        if ($("#detailForm").form('validate')) {
            $pjq.messager.progress({
                text: '数据处理中....'
            });
            var row = $("#dg").datagrid('getSelected');
            if (row == undefined || row == null) {
                $pjq.messager.progress('close');
                $pjq.messager.alert('提示', '未选择车辆！', 'error');
                return;
            }
            var params = $("#applyId").serialize();
            params += "&send.orgId=" + $("#orgId").combobox('getValue');
            params += "&send.userIdHandler=" + $("#userIdHandler").combobox('getValue');
            params += "&send.dictIdCarSendType=CarSendTypeJJ";
            params += "&send.sendMemo=" + $("#sendMemo").textbox('getText');
            params += "&send.sendMileage=" + $("#sendMileage").numberbox('getValue');
            params += "&send.sendOil=" + $("#sendOil").numberbox('getValue');
            params += "&send.dictIdDrivingLlicenseKind=" + $("#dictIdDrivingLlicenseKind").combobox('getValue');
            params += "&send.userIdPilot=" + $("#userIdPilot").combobox('getValue');
            params += "&send.stafPhonePilot=" + $("#stafPhonePilot").textbox('getText');
            params += "&carJson=" + JSON.stringify(row);
            var url = sys.contextPath + '/car/send!sendJJ.cxl';
            $.post(url, params, function (result) {
                $pjq.messager.progress('close');
                if (result.success) {
                    $dialog.dialog('destroy');
                    $grid.datagrid('reload');
                    $pjq.messager.alert('提示', result.msg, 'info');
                } else {
                    $pjq.messager.alert('提示', result.msg, 'error');
                }
            }, 'json');
        }
    };

    function getSendId() {
        return $("#sendId").val();
    }
    function getSendNo() {
    var result=$("#sendNo").textbox('getValue');
        return result;
    }
    // 外部获取值实例
    function getElementValue(elementId, type) {
        if (type == 'textbox') {
            return $("#" + elementId).textbox('getValue');
        }
    }

</script>
</body>
</html>