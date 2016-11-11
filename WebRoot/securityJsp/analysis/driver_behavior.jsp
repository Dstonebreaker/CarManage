<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.framework.util.SecurityUtil"%>
<%@page import="com.framework.util.WebMsgUtil"%>
<%@page import="com.system.entity.maintain.SessionInfo"%>
<%@page import="com.framework.util.ConfigUtil"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String contextPath = request.getContextPath();
SecurityUtil securityUtil = new SecurityUtil(session);
SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>驾驶行为分析</title>
<jsp:include page="../../inc.jsp"></jsp:include>

<script type="text/javascript">


$(function() {
	var date = [];
    var times_violent = [];
    var times_adrupt = [];
    var times_turn = [];
    var times_overSpeed = [];
    var times_tired = [];
	// 基于准备好的dom，初始化echarts实例
	var myChart = echarts.init(document.getElementById('echartsContent'));
	 
	// 指定图表的配置项和数据
	option = {
	    title: {
	        text: '驾驶行为分析'
	    },
	    tooltip: {
	        trigger: 'axis'
	    },
	    legend: {
	        data:['急加速','急减速','急转弯','超速','疲劳驾驶']
	    },
	    grid: {
	        left: '3%',
	        right: '4%',
	        bottom: '3%',
	        containLabel: true
	    },
	    toolbox: {
	        feature: {
	            saveAsImage: {}
	        }
	    },
	    xAxis: {
	        type: 'category',
	        boundaryGap: false,
	        data: date
	    },
	    yAxis: {
	        type: 'value'
	    },
	    series: [
	        {
	            name:'急加速',
	            type:'line',
	            data:times_violent
	        },
	        {
	            name:'急减速',
	            type:'line',
	            data:times_adrupt
	        },
	        {
	            name:'急转弯',
	            type:'line',
	            data:times_turn
	        },
	        {
	            name:'超速',
	            type:'line',
	            data:times_overSpeed
	        },
	        {
	            name:'疲劳驾驶',
	            type:'line',
	            data:times_tired
	        }
	    ]
	};


	
	
	$('#orgId').combotree({
		required:true,
		editable : false,
		panelWidth : 300,
		panelHeight:'auto',
		panelMaxHeight:'350px',
		idField:'id',
		textField:'text',
		parentField:'pid',
		url : sys.contextPath + '/maintain/sysOrganization!doNotNeedSecurity_comboTreeByManager.cxl?orgIdManager=<%=sessionInfo.getOrganization().getOrgIdManager()%>&id=',
		onBeforeLoad : function(row, param) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			if (row) {
				$('#orgId').combotree('tree').tree('options').url = sys.contextPath
						+ '/maintain/sysOrganization!doNotNeedSecurity_comboTreeByManager.cxl?orgIdManager=<%=sessionInfo.getOrganization().getOrgIdManager()%>&id='
						+ row.id;
			}
		},
		onSelect : function(record) {
			
			$('#driverId').combobox({
				required:true,
				editable : false,
				valueField : 'userId',
				textField : 'userName',
				url : sys.contextPath + '/maintain/sysUser!doNotNeedSecurity_findVUserList.cxl',
				queryParams : {
					'QUERY_t#dictIdFlag_S_EQ' : '<%=WebMsgUtil.YOUXIAO %>',
					'QUERY_t#orgId_S_EQ' : record.id
				},
				panelHeight : 'auto',
				panelMaxHeight : '350px',
				onSelect : function(record) {
					
				}
			});
		},
		onLoadSuccess : function(row, data) {
			parent.$.messager.progress('close');
		}
	});
	
	
	grid = $('#dg').datagrid(
            {
            	queryParams : sys.serializeObject($('#searchForm')),
                url: sys.contextPath + '/analysis/behavior!grid.cxl',
                striped: true,
                rownumbers: true,
                pagination: false,
                singleSelect: true,
                idField: 'id',
                sortName: 'obdTime',
                sortOrder: 'desc',
                toolbar: '#tb',
                method: 'get',
                columns: [[{
                    width: '200',
                    title: '时间',
                    field: 'obdTime',
                    sortable: true
                }, {
                    width: '180',
                    title: '总里程(公里)',
                    field: 'distance',
                    align:'right',
                    sortable: true
                }, {
                    width: '160',
                    title: '急加速',
                    field: 'violent',
                    align:'right',
                    sortable: true        
                }, {
                    width: '160',
                    title: '急减速',
                    field: 'adrupt',
                    sortable: true        
                }, {
                    width: '160',
                    title: '急转弯',
                    field: 'turn',
                    align:'right',
                    sortable: true
                }, {
                    width: '160',
                    title: '超速',
                    field: 'overSpeed',
                    align:'right',
                    sortable: true
                }, {
                    width: '160',
                    title: '疲劳驾驶',
                    field: 'tired',
                    align:'right',
                    sortable: true
                },{
                	field:'tatal_score',
                	title:'合计',
                	align:'right',
                	width:200,
                	formatter:function(value, row){
                		return parseInt(row.violent)+
                		parseInt(row.adrupt)+
                		parseInt(row.turn)+
                		parseInt(row.overSpeed)+
                		parseInt(row.tired);
                }
                	
                }]],
                onBeforeLoad: function (param) {
                    /* parent.$.messager.progress({
                     text : '数据加载中....'
                     }); */
                },
                onLoadSuccess: function (data) {
                	date.length = 0;
                	times_violent.length = 0;
                	times_adrupt.length = 0;
                	times_turn.length = 0;
                	times_overSpeed.length = 0;
                	times_tired.length = 0;
                	
                	var rows = $('#dg').datagrid('getRows');
                	for (var i = 0; i < rows.length; i++) {
                		date.push(rows[i]['obdTime']);
                		times_violent.push(rows[i]['violent']);
                		times_adrupt.push(rows[i]['adrupt']);
                	    times_turn.push(rows[i]['turn']);
                	    times_overSpeed.push(rows[i]['overSpeed']);
                	    times_tired.push(rows[i]['tired']);
                	}
					console.log(times_violent);
                	// 使用刚指定的配置项和数据显示图表。
                	myChart.setOption(option);
                }
            });
	
});  
function doClear() {
    $("#searchForm").form('clear');
}
</script>
  </head>

  <body class="easyui-layout" data-options="border:false">
	<div data-options="region:'north',border:true" class="datagrid-toolbar">
		<table style="width:100%">
			<tr>
				<td>
				 <form id="searchForm" name="searchForm" method="post">				
					<table>
						<tr>
						            
			                <td>统计日期：</td>
			                <td><input id="startTime" name="QUERY_t#obdTime_S_GE" class="easyui-datebox textbox"
			                           data-options="editable:false"/></td>
			                <td><input id="endTime" name="QUERY_t#obdTime_S_LE" class="easyui-datebox textbox" data-options="editable:false"/>
			                </td>
			                <td>单位：</td>
			                <td><input class="textbox" id="orgId" name="orgId"/>
							<td>驾驶员：</td>
							<td><input class="textbox" id="driverId" name="QUERY_t#driverId_S_EQ"/></td> 
			               <td colspan="3"><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="grid.datagrid('load',sys.serializeObject($('#searchForm')));">查询</a>
							<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'ext-icon-zoom_out'" onclick="doClear()">清空条件</a></td>
           				 </tr>
					</table>
					</form>
				</td>
			</tr>
		</table>
	</div>

			<div id="echartsContent" data-options="region:'center',border:false" style="height:40%;margin:0px;padding:0px">

		</div>
		
		<div id="content" data-options="region:'south',border:false" style="height:40%;margin:0px;padding:0px">
			<h2>驾驶行为详细信息</h2>
			<table id="dg" style="width:100%;height:100%;margin:0px;padding:0px"></table>
		</div>

</body>
</html>
