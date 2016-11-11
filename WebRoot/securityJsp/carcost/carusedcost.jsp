<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.framework.util.SecurityUtil"%>
<%@ page import="com.framework.util.WebMsgUtil"%>
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
<title></title>
<head>
<base href="<%=basePath%>">

<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">
	  var grid;
        var tree;
        $(function () {
            tree = $('#gridList').datagrid({
                title: '已归还车辆信息',
                url: sys.contextPath + '/car/carUsedCost!doNotNeedSecurity_getCarReturnInfo.cxl',
                striped: true,
                idField: 'returnId',
                rownumbers: true,
                sortOrder: 'desc',
                singleSelect: true,
                toolbar: '#westtoolbar',
                frozenColumns: [[{
                    width: '110',
                    title: '派车单号',
                    sortable: true,
                    field: 'sendNo'
                }]],
                pageSize:20,
                columns: [[               
                {
                        width: '100',
                        title: '操作',
                        field: 'action',
                        formatter: function (value, row) {
                            var result = sys.formatString('<a href="javascript:void(0)" class="btn00" onclick="addFun(\'{0}\',\'{1}\');">添加费用</a>', row.sendId, row.tcarNo);
                            return result;
                        }
                    }
                ]],
                onSelect: function (index, row) {
                 parent.$.messager.progress({
					text : '数据加载中....'
								}); 
                    grid.datagrid({
                        queryParams: {
                            "QUERY_t#sendId_S_EQ": row.sendId
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
                     parent.$.messager.progress('close');
                }
            });

            grid = $('#grid').datagrid(
                    {
                        title: '',
                        url: sys.contextPath + '/car/carUsedCost!grid.cxl',
                        striped: true,
                        idField: 'usedCostId',
                        rownumbers: true,
                        pagination: true,
                        sortName: 'timeCreate',
                        sortOrder: 'desc',
                        pageSize:20,
                        columns: [[
                            {
                                width: '100',
                                title: '派车单号',
                                sortable: true,
                                field: 'sendNo'
                            },
                            {
                                width: '100',
                                title: '管理单位',
                                sortable: true,
                                field: 'orgName'           
                            }, 
                            {
                                width: '100',
                                title: '车牌号',
                                sortable: true,
                                field: 'carNo'
                            },
                            {
                                width: '100',
                                title: '费用金额(元)',
                                sortable: true,
                                field: 'usedCost'
                            },
                            {
                                width: '80',
                                title: '费用类型',
                                sortable: true,
                                field: 'dictName'
                            },
                            
                            {
                                 width: '100',
                                 title: '派车时间',
                                 sortable: true,
                                 field: 'sendTime'
                            },
                            {
                                 width: '100',
                                 title: '还车时间',
                                 sortable: true,
                                 field: 'returnTime'
                            },
                            {
                                width: '100',
                                title: '登记时间',
                                sortable: true,
                                field: 'timeCreate'
                            }, 
                            {
                                title: '操作',
                                field: 'action',
                                width: '400',
                                formatter: function (value, row) {
                                    var str = '';
                                    <%if (securityUtil.havePermission("/car/carUsedCost!getById")) {%>
                                    str += sys.formatString('<a href="javascript:void(0)" class="btn1" onclick="showFun(\'{0}\');">查看</a>', row.usedCostId);
                                    <%}%>
                                    <%if (securityUtil.havePermission("/car/carUsedCost!update")) {%>
                                    str += sys.formatString('<a href="javascript:void(0)" class="btn2" onclick="editFun(\'{0}\');">编辑</a>', row.usedCostId);
                                    <%}%>
                                    <%if (securityUtil.havePermission("/car/carUsedCost!delete")) {%>
                                    str += sys.formatString('<a href="javascript:void(0)" class="btn3" onclick="removeFun(\'{0}\');">删除</a>', row.usedCostId);
                                    <%}%>
                                    return str;
                                }
                            }]],
                        toolbar: '#toolbar',
                        onBeforeLoad: function (row, param) {
                             parent.$.messager.progress({
                             text : '数据加载中....'
                             }); 
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
                            parent.$.messager.progress('close');
                        }
                    });

        });
        var addFun = function (sendId,tcarNo) {
            var dialog = parent.sys.modalDialog({
                title: '添加费用信息',
                url: sys.contextPath + '/securityJsp/carcost/carusercost_form.jsp?sendId=' + sendId + "&tcarNo=" + encodeURI(encodeURI(tcarNo)),
                buttons: [{
                    text: '添加',
                    handler: function () {
                        dialog.find('iframe').get(0).contentWindow.submitForm(
                                dialog, grid, parent.$);
                    }
                }]
            });
        };
        var editFun = function (usedCostId) {
            var dialog = parent.sys.modalDialog({
                title: '编辑费用信息',
                url: sys.contextPath
                + '/securityJsp/carcost/carusercost_form.jsp?id=' + usedCostId,
                buttons: [{
                    text: '编辑',
                    handler: function () {
                        dialog.find('iframe').get(0).contentWindow.submitForm(
                                dialog, grid, parent.$);
                    }
                }]
            });
        };
        var removeFun = function (usedCostId) {
            parent.$.messager.confirm('询问', '您确定要删除此记录？', function (r) {
                if (r) {
                    $.post(sys.contextPath + '/car/carUsedCost!delete.cxl', {
                        id: usedCostId
                    }, function () {
                        grid.datagrid('reload');
                    }, 'json');
                }
            });
        };
        var showFun = function (usedCostId) {
            var dialog = parent.sys.modalDialog({
                title: '查看费用信息',
                url: sys.contextPath
                + '/securityJsp/carcost/carusercost_form.jsp?id=' +usedCostId
            });
        };
    var searchFun = function () {
            tree.datagrid({
                queryParams: {
                    "QUERY_t#returnFlag_S_EQ": '<%=WebMsgUtil.YOUXIAO%>',
                    "QUERY_t#sendNo_S_LK": $('#input_value').val()
                }
            });
        };
    function searchCost() {
		var startTime = $('#startTime').datebox('getValue');
		var endTime = $('#endTime').datebox('getValue');
		if (endTime != "" && startTime != "" && startTime >endTime) {
			parent.$.messager.alert('提示', '结束时间不能早于开始时间!', 'info');
			return false;
		}
		grid.datagrid('load', sys.serializeObject($('#searchForm')));
	};
	function doClear() {
		$("#searchForm").form('clear');
	};
	function exportFile(){
			document.searchForm.action=sys.contextPath +'/car/carUsedCost!doNotNeedSecurity_exports.cxl';
		    document.searchForm.submit();
	};
	var LODOP; //声明为全局变量 
	function prn1_preview() {
	$("#tab").html("");
	var data =  $('#grid').datagrid('getData');
	var str = '';
	var strs ="<tr><td width='15%'>派车单号</td><td width='15%'>产权单位</td><td width='10%'>车牌号</td><td width='10%'>费用金额(元)</td><td  width='10%'>费用类型</td><td width='10%'>派车时间</td><td width='15%'>还车时间</td><td width='15%'>登记时间</td></tr>" ;
	var i=0;
	for(i;i<data.total;i++){
	 var cost=data.rows[i]; 
	 str = "<tr><td>"+cost.sendNo+"</td><td>"+cost.orgName+"</td><td>"+cost.carNo+"</td><td>"+cost.usedCost+"</td><td>" +cost.dictName+"</td><td>"+
	 cost.sendTime+"</td><td>"+cost.returnTime+"</td><td>"+cost.timeCreate+"</td></tr>" ;
	 strs=strs+str;
	}
	$("#tab").html(strs);		
		CreateOneFormPage();	
		LODOP.PREVIEW();	
	};
	function prn1_print() {		
		CreateOneFormPage();
		LODOP.PRINT();	
	};
	function prn1_printA() {		
		CreateOneFormPage();
		LODOP.PRINTA(); 	
	};	
	function CreateOneFormPage(){
		myIpAddress = '<%=basePath%>';
		LODOP=getLodop();  
		LODOP.PRINT_INIT("费用管理");
		LODOP.SET_PRINT_PAGESIZE(1,1380,880,"");
		LODOP.ADD_PRINT_TEXT(80,"45%",400,35,"费用管理");
		LODOP.SET_PRINT_STYLEA(0,"FontSize",30);

		LODOP.SET_PRINT_PAGESIZE(2,0,0,"");
		LODOP.SET_PREVIEW_WINDOW(0,0,0,0,0,"");
		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",1);//横向时的正向显示
		LODOP.ADD_PRINT_HTM(160,"1%","100%","100%",document.getElementById("print").innerHTML);
	};
    </script>
<style>
.textbox {
	height: 20px;
	width: 100px;
}
</style>
</head>

<body class="easyui-layout" data-options="fit:true,border:false">
	<div id="toolbar" style="display: none;">
		 <form id="searchForm"name="searchForm" method="post" target="report">
			<table>
					<tr>
				 <td>管理单位</td>
					<td><input name="orgIdManager"
						class="easyui-combobox textbox " 
						data-options="panelHeight:'200',editable:false,
						valueField: 'orgIdManager',
						textField: 'orgName',
						url: sys.contextPath+'/maintain/sysOrganization!doNotNeedSecurity_getOrgManger.cxl'
						" /></td>
					<td>费用类型</td>
					<td><input name="dictIduseCostType"
						class="easyui-combobox textbox " 
						data-options="panelHeight:'200',editable:false,
						valueField: 'dictId',
						textField: 'dictName',
						url: sys.contextPath+'/maintain/dictionaryManage!doNotNeedSecurity_getByClassId.cxl?dictClassId=UsedCostType'
						" /></td>
					<td>车牌号</td>
					<td><input class="easyui-validatebox textbox" 
						name="QUERY_t#carNo_S_LK"
						data-options="validType:['maxLength[15]']" /></td>
				    </tr>
                     <tr>
					<td>派车日期</td>
					<td><input id="startTime" name="startTime"
						class="easyui-datebox textbox" data-options="editable:false" /></td>
				    
					<td>还车日期</td>
					<td><input id="endTime" name="endTime"
						class="easyui-datebox textbox" data-options="editable:false" /></td>
				   
					<td><a onclick="searchCost()" href="javascript:void(0);" class="easyui-linkbutton"
                   data-options="iconCls:'ext-icon-zoom',plain:true">查询</a></td> 
                          <td><a onclick="doClear()" href="javascript:void(0);" class="easyui-linkbutton"
                   data-options="plain:true,iconCls:'ext-icon-zoom_out'">清空条件</a></td>    
                 
             <%
                  if (securityUtil.havePermission("/car/carUsedCost!save")) {
              %>
      <!--           <td><a href="javascript:void(0);" class="easyui-linkbutton"
                     data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addFun();">添加</a></td>-->
              <%
                  }
              %>  
     
                   <td><a href="javascript:void(0);" class="easyui-linkbutton"
									data-options="iconCls:'ext-icon-page_excel',plain:true"
									onclick="exportFile()">导出</a>
									<a href="javascript:void(0);" class="easyui-linkbutton"
									data-options="iconCls:'ext-icon-page_excel',plain:true"
									onclick="prn1_preview()">打印</a>
					 </td>
        </tr>
		   </table>
		</form>	
	</div>
	<div data-options="region:'west'" style="width:250px">
	<div id="westtoolbar" style="display: none;">
        <table>
            <tr>
                <td><input id="input_value" class="easyui-validatebox textbox"  style="width:140px"/></td>
                <td> <a onclick="searchFun();" href="javascript:void(0);" class="easyui-linkbutton"
                        data-options="iconCls:'ext-icon-search'">查询</a></td>
            </tr>
        </table>
    </div>
    <table id='gridList' class="easyui-datagrid">
    
    </table>
</div>
<div data-options="region:'center',fit:true,border:false">
    <table id="grid" data-options="fit:true,border:false"></table>
</div>
    <div  id="print"  style="display:none;width: 100%">
	  <table id="tab" border="1">
	  </table>
	</div>
</body>
</html>
