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

    <jsp:include page="../../inc.jsp"></jsp:include>
    <script type="text/javascript">
        var grid;
        var tree;
        $(function () {

            tree = $('#gridList').datagrid({
                title: '车辆信息',
                url: sys.contextPath + '/fixedcost!doNotNeedSecurity_getcarList.cxl',
                queryParams: {
                    "QUERY_t#dictIdFlag_S_EQ": '<%=WebMsgUtil.YOUXIAO%>'
                },
                striped: true,
                idField: 'carId',
                rownumbers: true,
                sortName: 'timeCreate',
                sortOrder: 'desc',
                singleSelect: true,
                toolbar: '#westtoolbar',
                frozenColumns: [[{
                    width: '110',
                    title: '车牌号',
                    sortable: true,
                    field: 'carNo'
                }]],
                pageSize:20,
                columns: [[
                    {
                        width: '100',
                        title: '操作',
                        field: 'action',
                        formatter: function (value, row) {
//                            debugger;
                            var result = sys.formatString('<a href="javascript:void(0)" class="btn00" onclick="renewalFun(\'{0}\',\'{1}\');">添加费用</a>', row.carId, row.carNo);
                            return result;
                        }
                    }
                ]], onSelect: function (index, row) {
                    grid.datagrid({
                        queryParams: {
                            "QUERY_t#dictIdFlag_S_EQ": '<%=WebMsgUtil.YOUXIAO%>',
                            "QUERY_t#carId_S_EQ": row.carId
                        }
                    });
                },
                onLoadSuccess: function (row, data) {
                    $('.btn00').linkbutton({
                        text: '添加费用',
                        plain: true,
                        height: 18,
                        iconCls: 'ext-icon-add'
                    });
                }
            });

            grid = $('#grid').datagrid(
                    {
                        title: '',
                        url: sys.contextPath + '/fixedcost!grid.cxl',
                        queryParams: {
                            "QUERY_t#dictIdFlag_S_EQ": '<%=WebMsgUtil.YOUXIAO%>'
                        },
                        striped: true,
                        idField: 'insuId',
                        rownumbers: true,
                        pagination: true,
                        sortName: 'timeCreate',
                        sortOrder: 'desc',
                        pageSize:20,
                        /* frozenColumns: [[{
                            width: '150',
                            title: '保险单号',
                            sortable: true,
                            field: 'insuNo'
                        }]], */
                        columns: [[
				    
							 {
			                    width: '150',
			                    title: '车牌号',
			                    sortable: false,
			                    field: 'carNo'
			                },
                            {
                                width: '150',
                                title: '费用类型',
                                sortable: false,
                                field: 'dictCostName'
                            },
                            {
                                width: '150',
                                title: '费用',
                                sortable: true,
                                field: 'cost'
                            },
                            {
                                width: '150',
                                title: '开始日期',
                                sortable: true,
                                field: 'startTime',
                                formatter: function (value, row) {
                                        if (value != undefined) {
                                            return value.substr(0, 10);
                                        }
                                 }
                            },
                            {
                                width: '150',
                                title: '截止日期',
                                sortable: true,
                                field: 'overTime',
                                formatter: function (value, row) {
                                    if (value != undefined) {
                                        return value.substr(0, 10);
                                    }
                                }
                            },
                            {
                                title: '操作',
                                field: 'action',
                                width: '200',
                                formatter: function (value, row) {
                                    var str = '';
                                    <%if (securityUtil.havePermission("/car/vinsurance!getById")) {%>
                                    //先不加查看，因为所有的数据都已在list表上显示了
                                    /* str += sys.formatString('<a href="javascript:void(0)" class="btn1" onclick="showFun(\'{0}\');">查看</a>', row.insuId); */
                                    <%}%>
                                    <%if (securityUtil.havePermission("/fixedcost!update")) {%>
                                    str += sys.formatString('<a href="javascript:void(0)" class="btn2" onclick="editFun(\'{0}\',\'{1}\',\'{2}\');">编辑</a>', row.costId,row.carId,row.carNo);
                                    <%}%>
                                    <%if (securityUtil.havePermission("/fixedcost!delete")) {%>
                                    str += sys.formatString('<a href="javascript:void(0)" class="btn3" onclick="removeFun(\'{0}\');">删除</a>', row.costId);
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

      
        var editFun = function (id,carId, carNo) {
        	var carNo_new=encodeURI(encodeURI(carNo));
            var dialog = parent.sys.modalDialogNew({
                title: '编辑费用信息',
                width:500,
                height: 200,
                href: sys.contextPath
                + '/securityJsp/fixedcost/fixedcostAddForm.jsp?carId=' + carId + "&carNo=" + carNo_new + "&costId=" + id + "&param=" + "edit",
                buttons: [ {
    				text : '保存',
    				handler : function() {
    					
    					var dataform = dialog.find("#form");
    		    		if (dataform.form('validate')) {
    		    			parent.$.messager.progress({
    		    				text : '数据处理中....'
    		    			});
    		    			
    						var params = dataform.serialize();//    				
    		    			var url = sys.contextPath + '/fixedcost!update.cxl';    		    			
    		    			$.post(url, params, function(result) {
    		    				parent.$.messager.progress('close');
    		    				if (result.success) {
    		    					parent.$.messager.alert('提示','保存成功','info');
    		    					dialog.dialog('destroy');
    		    					reloadGrid();
    		    				} else {
    		    					if(result.msg != ""){
    		    						parent.$.messager.alert('提示',result.msg, 'error');
    		    					}else{
    		    						parent.$.messager.alert('提示',"未知错误", 'error');
    		    					}
    	    						
    		    				}
    		    			}, 'json');
    		    		}
    				}
    			},{
    				text : '关闭',
    				handler : function() {
    					//dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
    					dialog.dialog('destroy');
    			} } ]
            });
        };

        
        //添加
        var renewalFun = function (carId, carNo) {
            var dialog = parent.sys.modalDialogNew({
                title: '添加费用',
                width:500,
                height: 200,
                href: sys.contextPath
                + '/securityJsp/fixedcost/fixedcostAddForm.jsp?carId=' + carId + "&carNo=" + encodeURI(encodeURI(carNo)),
                buttons : [ {
    				text : '保存',
    				handler : function() {
    					//检查两个时间是否正确
    					
    					startBox = dialog.find("#startTime");
    					overBox = dialog.find("#overTime");
    					
    					var startTime = startBox.datebox('getValue');
		                var startTime_ = new Date(startTime.replace(/-/g, "/"));
		                var overTime = overBox.datebox('getValue');
		                var overTime_ = new Date(overTime.replace(/-/g, "/"));
		                if (Date.parse(startTime_) > Date.parse(overTime_)) {
		                	parent.$.messager.alert('提示', "结束时间应大于开始时间", 'error');
		                    return;
		                }		                 					
    					var dataform = dialog.find("#form");
    		    		if (dataform.form('validate')) {
    		    			parent.$.messager.progress({
    		    				text : '数据处理中....'
    		    			});
    		    			
    						var params = dataform.serialize();
    		    			var url = sys.contextPath + '/fixedcost!save.cxl';    		    			
    		    			$.post(url, params, function(result) {
    		    				parent.$.messager.progress('close');
    		    				if (result.success) {
    		    					parent.$.messager.alert('提示','保存成功','info');
    		    					dialog.dialog('destroy');
    		    					//grid.datagrid('load');
    		    					reloadGrid();
    		    				} else {
    		    					if(result.msg != ""){
    		    						parent.$.messager.alert('提示',result.msg, 'error');
    		    					}else{
    		    						parent.$.messager.alert('提示',"未知错误", 'error');
    		    					}
    	    						
    		    				}
    		    			}, 'json');
    		    		}
    				}
    			},{
    				text : '关闭',
    				handler : function() {
    					//dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
    					dialog.dialog('destroy');
    			} } ]
            
            });
        };
        
        
        var removeFun = function (id) {
            parent.$.messager.confirm('提示', '您确定要删除此记录？', function (r) {
                if (r) {
                	
                	parent.$.messager.progress({
    					text : '数据处理中....'
    				});
                    $.post(sys.contextPath + '/fixedcost!delete.cxl', {
                        id: id
                    }, function (result) {
                    	parent.$.messager.progress('close');
                    	
                    	if (result.success) {
    						grid.datagrid('load');
    						flag = 'info';
    					} else {
    						flag = 'error';
    					}
                    	reloadGrid();
    					parent.$.messager.alert('提示', result.msg, flag);
                    }, 'json');
                }
            });
        };
        
        var searchFun = function () {
            tree.datagrid({
                queryParams: {
                    "QUERY_t#dictIdFlag_S_EQ": '<%=WebMsgUtil.YOUXIAO%>',
                    "QUERY_t#carNo_S_LK": $('#input_value').val()
                }
            });
        };
        var reloadGrid=function () {
            grid.datagrid({
                queryParams: {
                    "QUERY_t#dictIdFlag_S_EQ": '<%=WebMsgUtil.YOUXIAO%>'

                }
            });
            
            grid.datagrid('clearSelections');
        };
    </script>
</head>

<body class="easyui-layout" data-options="fit:true,border:false">
<div id="toolbar" style="display: none;">
    <table>
        <tr>
             <%-- <%
                  if (securityUtil.havePermission("/car/insurance!save")) {
              %>
              <td><a href="javascript:void(0);" class="easyui-linkbutton"
                     data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addFun();">添加</a></td>
              <%
                  }
              %>--%>
            <td><a onclick="reloadGrid();" href="javascript:void(0);" class="easyui-linkbutton"
                   data-options="plain:true,iconCls:'ext-icon-arrow_refresh'">刷新</a></td>
        </tr>
    </table>
</div>
<div data-options="region:'west'" style="width:275px">
    <div id="westtoolbar" style="display: none;">
        <table>
            <tr>
                <td><input id="input_value" class="easyui-validatebox textbox"  style="width:140px"/></td>
                <td> <a onclick="searchFun();" href="javascript:void(0);" class="easyui-linkbutton"
                        data-options="iconCls:'ext-icon-search'">查询</a></td>
            </tr>
        </table>


    </div>
    <table id='gridList' class="easyui-datagrid"></table>
</div>
<div data-options="region:'center',fit:true,border:false">
    <table id="grid" data-options="fit:true,border:false"></table>
</div>
</body>
</html>
