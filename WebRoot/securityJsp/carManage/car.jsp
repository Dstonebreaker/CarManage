<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.framework.util.SecurityUtil" %>
<%@ page import="com.framework.util.WebMsgUtil" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    SecurityUtil securityUtil = new SecurityUtil(session);
%>
<%
    String userId = request.getParameter("user");
    if (userId == null) {
        userId = "";
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <jsp:include page="../../inc.jsp"></jsp:include>
    <script type="text/javascript">
        var grid;
        function doClear() {
            $("#searchForm").form('clear');
        }
        //读卡查询车辆
        var readCard = function () {

            var st;
            try {
            st= CZx_32Ctrl.HBOpen();
            }catch (e){
                $.messager.alert("提示", "打开设备失败", 'error');
            }
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
                    $('#grid').datagrid({
                        url: sys.contextPath + '/car/carManage!grid.cxl?userId=' + '<%=userId%>',
                        queryParams: {
                            'QUERY_t#cardId_S_EQ':ret
                        }
                    });
                }
                else {
                    $.messager.alert("提示", "读卡失败", 'error');
                }
                try {
                    CZx_32Ctrl.HBClose();
                } catch (e) {
                }
            }

        };

        var addFun = function (userId) {
            var dialog = parent.sys.modalDialog({
                width: 800,
                height: 600,
                title: '添加车辆',
                url: sys.contextPath + '/securityJsp/carManage/car_add.jsp?userId=' + userId,
                buttons: [{
                    text: '添加',
                    handler: function () {
                        dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
                    }
                }]
            });
        };
        var showFun = function (id, userId) {
            var dialog = parent.sys.modalDialog({
                width: 800,
                height: 600,
                title: '查看车辆信息',
                url: sys.contextPath + '/securityJsp/carManage/car_details.jsp?id=' + id + '&userId=' + userId
            });
        };
        var editFun = function (id, userId) {
            var dialog = parent.sys.modalDialog({
                width: 800,
                height: 600,
                title: '编辑车辆信息',
                url: sys.contextPath + '/securityJsp/carManage/car_form.jsp?from=edit&id=' + id + '&userId=' + userId,
                buttons: [{
                    text: '编辑',
                    handler: function () {
                        dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
                    }
                }]
            });
        };
        var removeFun = function (id) {
            parent.$.messager.confirm('询问', '您确定要删除此记录？', function (r) {
                if (r) {
                    $.post(sys.contextPath + '/car/carManage!delete.cxl', {
                        id: id
                    }, function (result) {
                        parent.$.messager.progress('close');
                        if (result.success) {
                            grid.datagrid('reload');
                            parent.$.messager.alert('提示', '删除成功', 'info')
                        } else {
                            parent.$.messager.alert('提示', '删除失败', 'error');
                        }
                    }, 'json');
                }
            });
        };


        $(function () {
            var userId = "<%=request.getParameter("user")%>";

            $('#car_orgIdManager').combobox({
                url: sys.contextPath + '/maintain/sysOrganization!doNotNeedSecurity_getOrg.cxl?userId='+userId,
                panelHeight: '200',
                editable: false,
                valueField: 'orgIdManager',
                textField: 'orgName'
            });  

           /*   $('#car_orgIdManager').combotree({
				editable:false,
				panelWidth:300,
				idField:'id',
				textField:'text',
				parentField:'pid',
				url:sys.contextPath + '/maintain/sysOrganization!doNotNeedSecurity_comboTreeCurrentSession.cxl',
				onBeforeLoad : function(row, param) {
					parent.$.messager.progress({
						text : '数据加载中....'
					});
					if(row) {
						$('#car_orgIdManager').combotree('tree').tree('options').url = sys.contextPath + '/maintain/sysOrganization!doNotNeedSecurity_comboTree.cxl?id=' + row.id;
					}
				},
				onLoadSuccess : function(row, data) {
					$('#sysOrganization_orgIdOrgParent').combotree('setValue', {
                        id: data.orgIdOrgParent
                     });
					parent.$.messager.progress('close');
				}
			});  */
            /* $('#car_orgId').combotree({
                editable: false,
                panelWidth: 300,
                idField: 'id',
                textField: 'text',
                parentField: 'pid',
                url: sys.contextPath + '/maintain/sysOrganization!doNotNeedSecurity_comboTree.cxl?id=',
                onBeforeLoad: function (row, param) {
                    parent.$.messager.progress({
                        text: '数据加载中....'
                    });
                    if (row) {
                        $('#car_orgId').combotree('tree').tree('options').url = sys.contextPath + '/maintain/sysOrganization!doNotNeedSecurity_comboTree.cxl?id=' + row.id;
                    }
                },
                onLoadSuccess: function (row, data) {
                    parent.$.messager.progress('close');
                }
            }); */
            grid = $('#grid').datagrid({
                title: '',
                url: sys.contextPath + '/car/carManage!grid.cxl?userId=' + userId,
                striped: true,
                rownumbers: true,
                pagination: true,
                singleSelect: true,
                idField: 'carId',
                pageSize: 30,
                sortName: 'carRegisterDate',
                sortOrder: 'asc',
                frozenColumns: [[

                    {
                        width: '100',
                        title: '车辆管理单位',
                        field: 'orgName',
                        sortable: true
                    }]],
                columns: [[
					{
					    title: '固定使用单位',
					    field: 'useOrgName',
					    sortable: true
					},     
                    {
                        title: '车牌号',
                        field: 'carNo',
                        sortable: true
                    }, {

                        title: '车辆识别号',
                        field: 'carIdentifyNo',
                        sortable: true

                    }, {

                        title: '发动机号',
                        field: 'carEngineNo',
                        sortable: true

                    },{

                        title: '车身颜色',
                        field: 'carColor',
                        sortable: true

                    }, {

                        title: '是否落户',
                        field: 'carIsSettle',
                        sortable: true

                    }, {

                        title: '车辆种类',
                        field: 'carKind',
                        sortable: true

                    },{

                        title: '环保标志',
                        field: 'environmentProtection',
                        sortable: true

                    }, {

                        title: '初次登记日期',
                        field: 'carRegisterDate',
                        sortable: true,
                        formatter: function (value, row) {
                            if (value != undefined) {
                                return value.substr(0, 10);
                            }
                        }
                    },{

                        title: '内部档案号',
                        field: 'carFileNo',
                        sortable: true
                    },{

                        title: '强制报废日期',
                        field: 'carScrap',
                        sortable: true,
                        formatter: function (value, row) {
                            if (value != undefined) {
                                return value.substr(0, 10);
                            }
                        }
                    },{

                        title: '出厂日期',
                        field: 'carTime',
                        sortable: true,
                        formatter: function (value, row) {
                            if (value != undefined) {
                                return value.substr(0, 10);
                            }
                        }
                    },{

                        title: '用途分类',
                        field: 'usingKind',
                        sortable: true
                    },{

                        title: '特种车',
                        field: 'specialCar',
                        sortable: true               
                    },{

                        title: '已记固定资产',
                        field: 'carAssets',
                        sortable: true               
                    },{

                        title: '车辆资产编码',
                        field: 'carAssetsNo',
                        sortable: true               
                    },{

                        title: '资产金额',
                        field: 'carAssetsMoney',
                        sortable: true               
                    },{

                        title: '财政在编',
                        field: 'carFinance',
                        sortable: true               
                    },{

                        title: '车辆品牌',
                        field: 'carBrand',
                        sortable: true

                    },{

                        title: '车系',
                        field: 'carSeries',
                        sortable: true

                    },{

                        title: '车型',
                        field: 'modelName',
                        sortable: true

                    },{

                        title: '产地',
                        field: 'area',
                        sortable: true

                    },{

                        title: '燃油类型',
                        field: 'oil',
                        sortable: true

                    },{

                        title: '是否带 T',
                        field: 'isT',
                        sortable: true

                    }, {

                        title: '载客量（人）',
                        field: 'modelPeople',
                        sortable: true

                    }, {

                        title: '载重量（吨）',
                        field: 'modelLoad',
                        sortable: true

                    },{

                        title: '排气量（升）',
                        field: 'airDisplacement',
                        sortable: true


                    },{

                        title: '保养周期（月）',
                        field: 'modelMaintenanceMonth',
                        sortable: true
                    },
                    {
                        title: '车辆状态',
                        field: 'statusName',
                        sortable: true                  
                    },
                    {
                        title: '操作',
                        field: 'action',
                        width: '200',
                        formatter: function (value, row) {
                            var str = '';
                            if (row.dictIdCarStatus == "<%=WebMsgUtil.CARSTATUS_FREE%>") {<%if (securityUtil.havePermission("/car/carManage!getById")) {%>
                                str += sys.formatString('<a href="javascript:void(0)" class="btn1" onclick="showFun(\'{0}\',\'{1}\');">查看</a>', row.carId, userId);
                                <%}%>
                                <%if (securityUtil.havePermission("/car/carManage!update")) {%>
                                str += sys.formatString('<a href="javascript:void(0)" class="btn2" onclick="editFun(\'{0}\',\'{1}\');">编辑</a>', row.carId, userId);
                                <%}%>
                                <%if (securityUtil.havePermission("/car/carManage!delete")) {%>
                                str += sys.formatString('<a href="javascript:void(0)" class="btn3" onclick="removeFun(\'{0}\');" >删除</a>', row.carId);
                                <%}%>
                            } else {
                                <%if (securityUtil.havePermission("/car/carManage!getById")) {%>
                                str += sys.formatString('<a href="javascript:void(0)" class="btn1" onclick="showFun(\'{0}\',\'{1}\');">查看</a>', row.carId, userId);
                                <%}%>
                                <%if (securityUtil.havePermission("/car/carManage!update")) {%>
                                str += sys.formatString('<a href="javascript:void(0)" class="btn2" onclick="editFun(\'{0}\');" data-options="plain:true,disabled:true">编辑</a>', row.carId);
                                <%}%>
                                <%if (securityUtil.havePermission("/car/carManage!delete")) {%>
                                str += sys.formatString('<a href="javascript:void(0)" class="btn3" onclick="removeFun(\'{0}\');" data-options="plain:true,disabled:true" >删除</a>', row.carId);
                                <%}%>
                            }

                            //
                            return str;
                        }
                    }]],
                toolbar: '#toolbar',
                onBeforeLoad: function (param) {
                    parent.$.messager.progress({
                        text: '数据加载中....'
                    });
                },
                onLoadSuccess: function (data) {
                    $('.btn1').linkbutton({text: '查看', plain: true, height: 18, iconCls: 'ext-icon-search'});
                    $('.btn2').linkbutton({text: '编辑', plain: true, height: 18, iconCls: 'ext-icon-pencil'});
                    $('.btn3').linkbutton({text: '删除', plain: true, height: 18, iconCls: 'ext-icon-delete'});
                    parent.$.messager.progress('close');
                }
            });
        });
        var doSerch = function () {

           /*  var startTime = $('#startTime').datebox('getValue');
            var endTime = $('#endTime').datebox('getValue');
            if (endTime != "" && startTime != "" && startTime >= endTime) {
                parent.$.messager.alert('提示', '结束时间不能早于或等于开始时间!', 'info');
                return false;
            } */
            grid.datagrid('load', sys.serializeObject($('#searchForm')));
        };


        //测试存储过程
        var runCall = function runCall() {

            $.post(sys.contextPath + '/car/carManage!doNotNeedSecurity_runCall.cxl', function (result) {
                if (result.success) {
                    parent.$.messager.alert('提示', '执行存储过程成功', 'info')
                } else {
                    parent.$.messager.alert('提示', '执行存储过程失败', 'error');
                }
            }, 'json');


        };
        function exportFile(){
			document.searchForm.action=sys.contextPath +'/car/carManage!doNotNeedSecurity_exports.cxl';
		    document.searchForm.submit();
	   }
    </script>
    <style>
        .textbox {
            height: 20px;
            width: 100px;
        }
    </style>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
<%
    if (securityUtil.havePermission("dk")) {
%>
<OBJECT classid=clsid:730BF2F0-EAE2-46C5-BA06-5ABFC9AB8A0A width=0 height=0 align="center"
        codebase="<%=basePath%>public/ocx/readcard/zx_32.cab#version=1,0,0,1" id="CZx_32Ctrl" HSPACE=0 VSPACE=0>
</OBJECT>
<% }%>
<div id="toolbar" style="display: none;">
    <form id="searchForm" name="searchForm" method="post" target="report">
        <table>
            <tr>
                 <td>车牌号</td>
                <td><input class="easyui-validatebox textbox"
                           name="QUERY_t#carNo_S_LK"
                           data-options="validType:['maxLength[15]']"/></td> 
                <td>管理单位</td>
                <td><select id="car_orgIdManager" name="QUERY_t#orgIdManager_S_EQ" class="easyui-combotree" style="width: 155px;"></select></td>
                <!-- <td><input id="car_orgIdManager" name="QUERY_t#orgIdManager_S_EQ" class="easyui-combobox textbox"/></td> -->
                <!-- <td>IC卡号</td>
                <td><input name="QUERY_t#cardId_S_EQ" class="easyui-validatebox textbox"/></td> -->
                <td>是否警牌:</td>
                <td><select id="carPoliceUsed" name="QUERY_t#carPoliceUsed_S_EQ" data-options="editable:false" class="easyui-combobox" style="width:100px;">
				    <option value="警牌">警牌</option>
				    <option value="民牌">民牌</option>
				</select></td>
                <td>车辆品牌:</td>
                <td><input name="QUERY_t#dictIdBrand_S_EQ" class="easyui-combobox textbox"
                           data-options="panelHeight:'200',editable:false,
						valueField: 'dictId',
						textField: 'dictName',
						url: sys.contextPath+'/maintain/dictionaryManage!doNotNeedSecurity_getByClassId.cxl?dictClassId=CarBrand'
						"/></td>
				<td>载客人数:</td>
            	<td><input name="QUERY_t#modelPeople_S_EQ" class="easyui-textbox" data-options="maxLength:30" style="width:100px;"/></td>
                <td>颜色:</td>
                <td><input name="QUERY_t#dictIdColor_S_EQ" class="easyui-combobox textbox"
                           data-options="panelHeight:'200',editable:false,
						valueField: 'dictId',
						textField: 'dictName',
						url: sys.contextPath+'/maintain/dictionaryManage!doNotNeedSecurity_getByClassId.cxl?dictClassId=CarColor'
						"/></td>
				</tr>	
				<tr>
				<td>使用年限:</td>
				<td><select id="limitYear" name="QUERY_t#limitYear_I_GE"  class="easyui-combobox" data-options="editable:false" style="width:100px;">
				    <option value="0">请选择</option>
				    <option value="3">3年以上</option>
				    <option value="5">5年以上</option>
				    <option value="10">10年以上</option>
				    <option value="15">15年以上</option>
				</select></td>			
                <td>是否特种车辆:</td>
                <td><input name="QUERY_t#dictIdSpecialCar_S_EQ" class="easyui-combobox textbox"
                           data-options="panelHeight:'200',editable:false,
						valueField: 'dictId',
						textField: 'dictName',
						url: sys.contextPath+'/maintain/dictionaryManage!doNotNeedSecurity_getByClassId.cxl?dictClassId=SpecialCar'
						"/></td>
				<td>是否落户:</td>
                <td><select id="carIsSettle" name="QUERY_t#carIsSettle_S_EQ" data-options="editable:false" class="easyui-combobox" style="width:100px;">
				    <option value="是">是</option>
				    <option value="否">否</option>
				</select></td>
                <!-- <td>所属车型</td>
                <td><input name="QUERY_t#modelId_S_EQ"
                           class="easyui-combobox textbox"
                           data-options="panelHeight:'200',editable:false,
						valueField: 'modelId',
						textField: 'modelName',
						url: sys.contextPath+'/car/Model!doNotNeedSessionAndSecurity_getAllModel.cxl?'
						">
                </td> -->
                <td>车辆状态:</td>
                <td><input name="QUERY_t#dictIdCarStatus_S_EQ" class="easyui-combobox textbox"
                           data-options="panelHeight:'200',editable:false,
						valueField: 'dictId',
						textField: 'dictName',
						url: sys.contextPath+'/maintain/dictionaryManage!doNotNeedSecurity_getByClassId.cxl?dictClassId=CarStatus'
						"/></td>
                <!-- <td>登记开始时间：</td>
                <td><input id="startTime" name="startTime" class="easyui-datebox textbox"
                           data-options="editable:false"/></td>
                <td>登记结束时间：</td>
                <td><input id="endTime" name="endTime" class="easyui-datebox textbox" data-options="editable:false"/>
                </td> -->
                <td><a href="javascript:void(0);" class="easyui-linkbutton"
                       data-options="iconCls:'ext-icon-zoom',plain:true"
                       onclick="doSerch();">查询</a></td>
                <%
                    if (securityUtil.havePermission("dk")) {
                %>
                <td>
                    <a href="javascript:void(0)" class="easyui-linkbutton"
                       data-options="plain:true,iconCls:'ext-icon-zoom'"
                       onclick="readCard()">读卡</a>
                </td>
                <% }%>
                <td><a href="javascript:void(0)" class="easyui-linkbutton"
                       data-options="plain:true,iconCls:'ext-icon-zoom_out'" onclick="doClear()">清空条件</a></td>

                <%
                    if (securityUtil.havePermission("/car/carManage!save")) {
                %>
                <td><a href="javascript:void(0);" class="easyui-linkbutton"
                       data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addFun(<%=userId%>);">添加</a></td>
                <%
                    }
                %>
                  <td><a href="javascript:void(0);" class="easyui-linkbutton"
									data-options="iconCls:'ext-icon-page_excel',plain:true"
									onclick="exportFile()">导出</a>
				  </td>
                <!-- 				<td><a href="javascript:void(0);" class="easyui-linkbutton"
                                        data-options="iconCls:'ext-icon-zoom',plain:true"
                                        onclick="runCall();">测试存储过程</a></td> -->
            </tr>

        </table>
    </form>
</div>
<div data-options="region:'center',fit:true,border:false">
    <table id="grid" data-options="fit:true,border:false"></table>
</div>
</body>
</html>