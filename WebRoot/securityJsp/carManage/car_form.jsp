<%@ page import="com.framework.util.SecurityUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String contextPath = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + contextPath + "/";
%>
<%
    SecurityUtil securityUtil = new SecurityUtil(session);
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
                if ($(':input[name="car.carId"]').val().length > 0) {
                    url = sys.contextPath + '/car/carManage!update.cxl';
                } else {
                    url = sys.contextPath + '/car/carManage!save.cxl';
                }
                $.post(url, sys.serializeObject($('form')), function (result) {
                    if (result.success) {
                        $grid.datagrid('reload');
                        $dialog.dialog('destroy');
                    } else {
                        $pjq.messager.alert('提示', result.msg, 'error');
                    }
                }, 'json');
            }
        };
        $(function () {
            var userId = "<%=request.getParameter("userId")%>";


            $('#car_userIdMaster').combobox({
                url: sys.contextPath + '/car/carManage!doNotNeedSecurity_getUserMaseterListById.cxl?userId=' + userId,
                panelHeight: '200',
                editable: false,
                valueField: 'userId',
                textField: 'userName'
            });
            var carBrand = $('#brand').combobox({
                url: sys.contextPath + '/maintain/dictionaryManage!doNotNeedSecurity_getByClassId.cxl?dictClassId=CarBrand',
                panelHeight: '200',
                required: true,
                editable: false,
                valueField: 'dictId',
                textField: 'dictName',
                onSelect: function (record) {

                    $('#modelId').combobox({
                        disabled: false,
                        required: true,
                        editable: false,
                        url: sys.contextPath + '/car/Model!doNotNeedSessionAndSecurity_getModelByBrandId.cxl?brandId=' + record.dictId,
                        valueField: 'modelId',
                        textField: 'modelName',
                        onLoadSuccess: function () {
                            $('#modelId').combobox('clear');
                        }
                    }).combobox('clear');
                }

            });
            var carSeries = $('#modelId').combobox({
                prompt: '请先填写车辆品牌',
                required: true,
                disabled: true,
                valueField: 'modelId',
                textField: 'modelName'
            });


            var managerOrg = $('#car_orgIdManager').combobox({
                url: sys.contextPath + '/maintain/sysOrganization!doNotNeedSecurity_getOrg.cxl?userId=' + userId,
                panelHeight: '200',
                required: true,
                editable: false,
                valueField: 'orgIdManager',
                textField: 'orgName',
                onSelect: function (record) {
                    $('#car_orgIdUse').combotree({
                        disabled: false,
                        editable: false,
                        panelWidth: 300,
                        idField: 'id',
                        textField: 'text',
                        parentField: 'pid',
                        url: sys.contextPath + '/maintain/sysOrganization!doNotNeedSecurity_orgComboTree.cxl?id=' + record.orgIdManager,
                        onBeforeLoad: function (row, param) {
                            parent.$.messager.progress({
                                text: '数据加载中....'
                            });
                            if (row) {
                                $('#car_orgIdUse').combotree('tree').tree('options').url = sys.contextPath + '/maintain/sysOrganization!doNotNeedSecurity_comboTree.cxl?id=' + row.id;
                            }
                        },
                        onLoadSuccess: function (row, data) {
                            parent.$.messager.progress('close');
                        }
                    }).combotree('clear');
                }

            });
            var useOrg = $('#car_orgIdUse').combotree({
                disabled: true,
                prompt: '请先填写管理单位',
                idField: 'id',
                textField: 'text',
                parentField: 'pid'

            });


            if ($(':input[name="car.carId"]').val().length > 0) {
                parent.$.messager.progress({
                    text: '数据加载中....'
                });
                $.post(sys.contextPath + '/car/carManage!getById.cxl', {
                    id: $(':input[name="car.carId"]').val()
                }, function (result) {

                    if (result.carId != undefined) {
                        $('form').form('load', {
                            'car.carId': result.carId,
                            'car.carNo': result.carNo,
                            'car.orgIdManager': result.orgIdManager,
                            'car.orgIdUse': result.orgIdUse,
                            'car.modelId': result.modelId,
                            'carTime': result.carTime,
                            'car.carMemo': result.carMemo,
                            'car.carIdentifyNo': result.carIdentifyNo,
                            'car.carEngineNo': result.carEngineNo,
                            'car.dictIdColor': result.dictIdColor,
                            'car.dictIdCarType': result.dictIdCarType,
                            'car.dictIdEnvironmentProtection': result.dictIdEnvironmentProtection,
                            'carRegisterDate': result.carRegisterDate,
                            'car.carFileNo': result.carFileNo,
                            'carScrap': result.carScrap,
                            'car.dictIdUsingKind': result.dictIdUsingKind,
                            'car.dictIdSpecialCar': result.dictIdSpecialCar,
                            'car.carAssets': result.carAssets,
                            'car.carAssetsNo': result.carAssetsNo,
                            'car.carAssetsMoney': result.carAssetsMoney,
                            'car.carFinance': result.carFinance,
                            'dictIdBrand': result.dictBrand,
                            'car.carPoliceUsed': result.carPoliceUsed,
                            'car.dictIdFunds': result.dictIdFunds,
                            'car.carIsSettle': result.carIsSettle,
                            'car.userIdMaster': result.userIdMaster,
                            'car.carStatus.simNo': result.carStatus.simNo,
                            'car.carStatus.keyNo': result.carStatus.keyNo,
                            'car.carStatus.obdNo': result.carStatus.obdNo,
                            'car.carStatus.carId': result.carStatus.carId,
                            'car.cardId':result.cardId,
                            'car.carGarage':result.carGarage,
                            'car.mileageCalibration':result.mileageCalibration,
                            'car.oilCalibration':result.oilCalibration
                        });
                        if(result.cardId!=undefined&&result.cardId.length>0){
                            $('#readcard').linkbutton({text:'清空'});
                        }else {
                            $('#readcard').linkbutton({text:'读卡'});
                        }
                    }
                    $('#modelId').combobox({
                        disabled: false,
                        required: true,
                        editable: false,
                        url: sys.contextPath + '/car/Model!doNotNeedSessionAndSecurity_getModelByBrandId.cxl?brandId=' + result.dictBrand,
                        valueField: 'modelId',
                        textField: 'modelName',
                        onLoadSuccess: function () {
                            $('#modelId').combobox('setValue', result.modelId);
                            $('#car_orgIdUse').combotree('setValue', result.orgIdUse);
                        }
                    });
                    parent.$.messager.progress('close');
                }, 'json');
            }
        });

        //读取或清空card数据
        var readCard=function () {
            if ($('#cardId').textbox('getValue').length>0){
                $('#cardId').textbox('setValue',"");
                $('#readcard').linkbutton({text:'读卡'});
                return;
            }
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
                    $('#cardId').textbox('setValue',ret);
                    $('#readcard').linkbutton({text:'清空'});
                }
                else {
                    parent.$.messager.alert("提示", "读卡失败", 'error');
                }
                try {
                    CZx_32Ctrl.HBClose();
                } catch (e) {
                }
            }
        };

    </script>
    <style>
        .textbox {
            height: 20px;
            width: 200px;
        }
    </style>
</head>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
<%
    if (securityUtil.havePermission("dk")) {
%>
<OBJECT classid=clsid:730BF2F0-EAE2-46C5-BA06-5ABFC9AB8A0A width=0 height=0 align="center"
        codebase="<%=basePath%>public/ocx/readcard/zx_32.cab#version=1,0,0,1" id="CZx_32Ctrl" HSPACE=0 VSPACE=0>
</OBJECT>
<% }%>
<form method="post" class="form">
    <fieldset>
        <legend>车辆基本信息</legend>
        <table class="table" style="width: 100%;">
            <tr>
                <td>编号:</td>
                <td><input name="car.carId" value="<%=id%>" class="easyui-validatebox textbox" readonly="readonly"
                           style=" width: 200px;"/></td>
                <td>车牌号:</td>
                <td><input name="car.carNo" class="easyui-validatebox textbox" data-options="required:true"
                           style=" width: 200px;"/></td>
            </tr>
            <tr>
                <td>大驾号:</td>
                <td><input name="car.carIdentifyNo" class="easyui-validatebox textbox" data-options="required:true"
                           style=" width: 200px;"/></td>
                <td>发动机号:</td>
                <td><input name="car.carEngineNo" class="easyui-validatebox textbox" data-options="required:true"
                           style=" width: 200px;"/></td>
            </tr>
            <tr>
                <td>车身颜色:</td>
                <td><input name="car.dictIdColor" class="easyui-combobox textbox"
                           data-options="panelHeight:'200',required:true,editable:false,
						valueField: 'dictId',
						textField: 'dictName',
						url: sys.contextPath+'/maintain/dictionaryManage!doNotNeedSecurity_getByClassId.cxl?dictClassId=CarColor'
						" style=" width: 200px;"/></td>
                <td>车辆种类:</td>
                <td><input name="car.dictIdCarType" class="easyui-combobox textbox"
                           data-options="panelHeight:'200',required:true,editable:false,
						valueField: 'dictId',
						textField: 'dictName',
						url: sys.contextPath+'/maintain/dictionaryManage!doNotNeedSecurity_getByClassId.cxl?dictClassId=CarType'
						" style=" width: 200px;"/></td>
            </tr>
            <tr>
                <td>环保标志:</td>
                <td><input name="car.dictIdEnvironmentProtection" class="easyui-combobox textbox"
                           data-options="panelHeight:'200',required:true,editable:false,
						valueField: 'dictId',
						textField: 'dictName',
						url: sys.contextPath+'/maintain/dictionaryManage!doNotNeedSecurity_getByClassId.cxl?dictClassId=EnvironmentProtection'
						" style=" width: 200px;"/></td>
                <td>初次登记日期:</td>
                <td><input name="carRegisterDate" class="easyui-datebox textbox"
                           data-options="required:true,editable:false" style=" width: 200px;"/></td>
            </tr>
            <tr>
                <td>内部档案号:</td>
                <td><input name="car.carFileNo" class="easyui-validatebox textbox" data-options="required:true"
                           style=" width: 200px;"/></td>
                <td>强制报废日期:</td>
                <td><input name="carScrap" class="easyui-datebox  textbox" data-options="required:true,editable:false"
                           style=" width: 200px;"/></td>
            </tr>
            <tr>
                <td>车辆品牌:</td>
                <td><input id="brand" name="dictIdBrand" style=" width: 200px;"/></td>
                <td>车型:</td>
                <td><input id="modelId" name="car.modelId" style=" width: 200px;"/>
                </td>


            </tr>
            <tr>
                <td>用途分类:</td>
                <td><input name="car.dictIdUsingKind" class="easyui-combobox textbox "
                           data-options="panelHeight:'200',required:true,editable:false,
						valueField: 'dictId',
						textField: 'dictName',
						url: sys.contextPath+'/maintain/dictionaryManage!doNotNeedSecurity_getByClassId.cxl?dictClassId=UsingKind'
						" style=" width: 200px;"/></td>
                <td>特种车:</td>
                <td><input name="car.dictIdSpecialCar" class="easyui-combobox textbox"
                           data-options="panelHeight:'200',required:true,editable:false,
						valueField: 'dictId',
						textField: 'dictName',
						url: sys.contextPath+'/maintain/dictionaryManage!doNotNeedSecurity_getByClassId.cxl?dictClassId=SpecialCar'
						" style=" width: 200px;"/></td>
            </tr>
            <tr>
                <td>已记固定资产:</td>
                <td><input name="car.carAssets" class="easyui-validatebox textbox" data-options="required:true"
                           style=" width: 200px;"/></td>
                <td>车辆资产编码:</td>
                <td><input name="car.carAssetsNo" class="easyui-validatebox   textbox" style=" width: 200px;"/></td>
            </tr>
            <tr>
                <td>资产金额:</td>
                <td><input name="car.carAssetsMoney" class="easyui-validatebox textbox" style=" width: 200px;"/></td>
                <td>财政在编:</td>
                <td><input name="car.carFinance" class="easyui-validatebox   textbox" style=" width: 200px;"/></td>
            </tr>
            <tr>
                <td>管理单位:</td>
                <td><input id="car_orgIdManager" name="car.orgIdManager" style=" width: 200px;"/></td>
                <td>固定使用单位:</td>
                <td><input id="car_orgIdUse" name="car.orgIdUse" style=" width: 200px;"/><img
                        class="iconImg ext-icon-cross"
                        onclick="$('#car_orgIdUse').combotree('clear');"
                        title="清空"/></td>
            </tr>
            <tr>
                <td>警牌(民牌):</td>
                <td><input name="car.carPoliceUsed" class="easyui-combobox textbox"
                           data-options="required:true,panelHeight:'auto',editable:false,
						valueField:'value',
						textField:'text',						
						data : [{
							value: '警牌',
							text: '警牌'						
						},{
							value: '民牌',
							text: '民牌'
						}]
					" style=" width: 200px;"/></td>
                <td>资金来源:</td>
                <td><input name="car.dictIdFunds" class="easyui-combobox textbox"
                           data-options="panelHeight:'200',required:true,editable:false,
						valueField: 'dictId',
						textField: 'dictName',
						url: sys.contextPath+'/maintain/dictionaryManage!doNotNeedSecurity_getByClassId.cxl?dictClassId=FundSource'
						" style=" width: 200px;"/></td>
            </tr>
            <tr>
                <td>是否落户:</td>
                <td><input name="car.carIsSettle" class="easyui-combobox textbox"
                           data-options="required:true,panelHeight:'auto',editable:false,
						valueField:'value',
						textField:'text',						
						data : [{
							value: '是',
							text: '是'						
						},{
							value: '否',
							text: '否'
						}]
					" style=" width: 200px;"/></td>
                <td>保管人:</td>
                <td><input id="car_userIdMaster" name="car.userIdMaster" class="easyui-combobox textbox"
                           style=" width: 200px;"/></td>
            </tr>
            <tr>
                <td>出厂日期:</td>
                <td><input name="carTime" class="easyui-datebox textbox" data-options="required:true,editable:false"
                           style=" width: 200px;"/></td>
                <td>sim卡号:</td>
                <td><input name="car.carStatus.simNo" class="easyui-validatebox textbox" style=" width: 200px;"/></td>
            </tr>
            <tr>
                <td>车辆钥匙号:</td>
                <td><input name="car.carStatus.keyNo" class="easyui-validatebox textbox" style=" width: 200px;"/></td>
                <td>obd编号:</td>
                <td><input name="car.carStatus.obdNo" class="easyui-validatebox   textbox" style=" width: 200px;"/></td>
            </tr>
            <tr>
                <td>IC卡:</td>
                <td><input name="car.cardId" id="cardId" style=" width: 180px;" class="easyui-textbox"/>
                    <%
                        if (securityUtil.havePermission("dk")) {
                    %>
                    <a onclick="readCard();" href="javascript:void(0);" id="readcard" class="easyui-linkbutton"
                        data-options="iconCls:'ext-icon-search',plain:true">读卡</a>
                    <%}%>
                </td>
               <td>库号:</td>
                <td><input name="car.carGarage" class="easyui-validatebox textbox" data-options="required:true" style=" width: 200px;"/></td>
            </tr>
                   <tr>
                <td>里程校准:</td>
                <td><input name="car.mileageCalibration" class="easyui-validatebox textbox" data-options="required:true,validType:'maxLength[9]'"  style=" width: 200px;"/></td>
                <td>油量校准:</td>
                <td><input name="car.oilCalibration" class="easyui-validatebox textbox"  data-options="required:true,validType:['intOrFloat','maxLength[8]']" style=" width: 200px;"/></td>
            </tr>
            <tr>
                <td>备注:</td>
                <td colspan="3"><input name="car.carMemo" class="easyui-textbox"
                                       data-options="multiline:true,validType:'maxLength[400]',width:'565'"
                                       style="height:50px"/></td>
            </tr>

        </table>
    </fieldset>
    <input name="car.carStatus.carId" value="<%=id%>" class="easyui-validatebox   textbox" type="hidden"/>
</form>

</body>
</html>