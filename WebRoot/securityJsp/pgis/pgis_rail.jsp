<%@ page language="java" contentType="text/html; charset=GB2312"
	pageEncoding="GB2312"%>
<%@ page import="com.system.entity.maintain.SessionInfo"%>
<%@page import="com.framework.util.WebMsgUtil"%>
<%@ page import="com.framework.util.SecurityUtil"%>
<%
	String contextPath = request.getContextPath();
	SessionInfo sessionInfo = (SessionInfo) session
			.getAttribute("sessionInfo");
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
	SecurityUtil securityUtil = new SecurityUtil(session);
%>
<!DOCTYPE html>
<HTML xmlns="http://www.w3.org/1999/xhtml" xmlns:v =
"urn:schemas-microsoft-com:vml"> 
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/> 
<title>围栏管理</title>
<!-- <?import namespace="v" implementation="#default#VML" ?>
	<style>v/:rect,v/:rect,v/:imagedata{display:inline-block}</style> -->
<jsp:include page="../../inc.jsp"></jsp:include>
<!-- 地图 -->
<script language="javascript"
	src="http://10.117.10.84/PGIS_S_TileMap/js/EzMapAPI.js"></script>
<!-- bookstrap -->
<%-- <link rel="stylesheet" href="<%=basePath%>/public/jslib/bookStrap/css/bootstrap.min.css"> --%>
<%-- <script src="<%=basePath%>/public/jslib/bookStrap/js/bootstrap.min.js"></script> --%>



  <style type="text/css">
 	v\:* {
	BEHAVIOR: url(#default#VML);
	position:absolude;
	}
 </style>  
 
 

<script type="text/javascript" reload="true" id="www">
	var mainMenu;//围栏列表
	var orgId;

	//地图变量
	var mapApp = null;
	var g_overlay = null;
	var g_edit = false;
	var title_timeOut;
	var cb_name;
	var type=0;
	$(function() {
		//sys.contextPath + '/pgis/pgis_SysOrganizationAction!doNotNeedSecurity_getSelfAndSubSyorganizationsTreeByMapOrgList.cxl'
		/* 左侧导航栏 */
		
		$('#layout_south').hide();
		mainMenu = $('#mainMenu')
				.datagrid(
						{
							url : sys.contextPath
									+ '/pgis/carFenceDefine!grid.cxl',
							striped : true,//斑马线
							rownumbers : false,//
							pagination : false,//分页
							singleSelect : true,//如果为true，则只允许选择一行。
							idField : 'dictIdRegion',
							sortName : 'name',
							sortOrder : 'desc',
							frozenColumns : [ [ {
								width : '100',
								title : '围栏',
								sortable : true,
								field : 'name'
							} ] ],
							columns : [ [ {
								width : '120',
								title : '操作',
								field : 'action',
								formatter : function(value, row,index) {
									var result = "";
								<%if (securityUtil.havePermission("/pgis/carFenceDefine!update")) {%>
										result += sys.formatString(
													'<a href="javascript:void(0)" class="btn1" onclick="editFun(\'{0}\');">编辑</a>',
													index);
								<%}%>
	
								<%if (securityUtil.havePermission("/pgis/carFenceDefine!delete")) {%>
										result += sys.formatString(
													'<a href="javascript:void(0)" class="btn2" onclick="deleteFun(\'{0}\');">删除</a>',
													row.dictIdRegion);
								<%}%>
										return result;
								}
							} ] ],
							onClick : function(node) {

							},
							onLoadSuccess : function(data) {
								$('.btn1').linkbutton({text:'编辑', plain:true, height:18, iconCls:'ext-icon-page_white_edit'});
								$('.btn2').linkbutton({text:'删除', plain:true, height:18, iconCls:'ext-icon-delete'});
								parent.$.messager.progress('close');
							},
							toolbar : '#toolbar'
						});

		$('#mainLayout').layout('panel', 'center').panel(
				{
					onResize : function(width, height) {
						sys.setIframeHeight('centerIframe',
								$('#mainLayout').layout('panel', 'center')
										.panel('options').height - 5);
					}
				});

		if (_compatIE()) {
			mapApp = new EzMap(document.getElementById("map"));
			//地图设置
			mapApp.zoomTo(14);
			mapApp.centerAtLatLng(125.09211, 46.59558);
			mapApp.hideCopyright();//隐藏版权信息
			//mapApp.hideMapScale();//隐藏比例尺信息
			mapApp.switchMapServer(0);//右上角地图服务的切换
			mapApp.showMapControl();
			mapApp.showMapServer();
			
		} else if (mapApp == null) {
			var pEle = document.getElementById("map");
			//pEle.innerHTML="<p>目前EzMap地图引擎不支持你使用的浏览器，我们当前支持如下浏览器类型:</p><ul><li><a href='http://www.microsoft.com/windows/ie/downloads/default.asp'>IE</a> 5.5+ (Windows)</li>";
		}
	});//onload结束=======================================================================

		
		
	//删除
	var deleteFun = function(id) {
		parent.$.messager.confirm('询问', '您确定要删除此记录？', function(r) {
			if (r) {
				$.post(sys.contextPath + '/pgis/carFenceDefine!delete.cxl', {
					id : id
				}, function() {
					 if (result.success) {
                		 $('#layout_south').hide();
                		 mainMenu.datagrid('reload');
     					$pjq.messager.alert('提示',result.msg,'info');
     				} else {
     					$pjq.messager.alert('提示',result.msg, 'error');
     				}
					
				}, 'json');
			}
		});
	};
	
	//编辑
	var editFun = function(index) {
		mapApp.clear();
		type=1;
		var row=mainMenu.datagrid('getData').rows[index];
		$('#layout_south').show();
		$('#cb_name').combobox({
			valueField:'dictId',
			textField:'dictName',
			readonly:true
        });
		$('#cb_name').combobox('loadData',[{dictId:row.dictIdRegion,dictName:row.name}]);
		$('#cb_name').combobox('setValue',row.dictIdRegion);
		
		if(row.fenceKind==1){
			var subfrist=row.points.substr(row.points.indexOf("((")+2);
			var subsecond=subfrist.substr(0,subfrist.indexOf("))"));
			var reg=new RegExp(" ","g");
			var pPoints=subsecond.replace(reg,",");
			$('#dataInputx').textbox('setValue',pPoints);
			var pPolygon=new Polygon(pPoints,"#cc00ff", 3,0.5,"blue");
			mapApp.addOverlay(pPolygon);
		}else{
			try{
				var subfrist=row.fenceCenter.substr(row.fenceCenter.indexOf("(")+1);
				var subsecond=subfrist.substr(0,subfrist.indexOf(")"));
				var pPoints=subsecond.replace(" ",",")+","+(row.fenceRadius/100);
				$('#dataInputx').textbox('setValue',pPoints);
				var pCircle=new Circle(pPoints,"#cc00ff", 2,0.5,"blue");
				mapApp.addOverlay(pCircle);
			}catch(e){
				
			}
		}
	};
	
	
	//添加
	var addFun=function(){
		type=0;
		$('#layout_south').show();
		$('#cb_name').combobox({required:true,
			editable:false,
			valueField:'dictId',
			textField:'dictName',
			url:'<%=contextPath%>/pgis/carFenceDefine!doNotNeedSecurity_getRegion.cxl',
			panelHeight:'200'});
	};
	var fenceKind;
	//清除
	var clearFun = function() {
		mapApp.clear();
		$('#dataInputx').textbox('setValue',"");
	};
	//保存
	var saveFun=function(){
		
		var cb_value=$('#cb_name').combobox('getValue');
		if(cb_value==undefined&&cb_value==""){
			$.messager.alert('提示', "请选择区域名称!", 'error');
			return;
		}
		var tx_value=$('#dataInputx').textbox('getValue');
		if(tx_value==undefined&&tx_value==""){
			$.messager.alert('提示', "请画围栏图形!", 'error');
			return;
		}
		
		 $.post(
                 sys.contextPath + '/pgis/carFenceDefine!save.cxl' ,
                 {dictIdRegion:cb_value,
                	 fenceKind: fenceKind,
                     points:tx_value,
                     type:type
                 }, function (result) {
                	 if (result.success) {
                		 $('#layout_south').hide();
                		 mainMenu.datagrid('reload');
                		 $('#cb_name').combobox('readonly',false);
     					$.messager.alert('提示',result.msg,'info');
     				} else {
     					$.messager.alert('提示',result.msg, 'error');
     				}
                 },'json');
	};
	//画圆
	var cycleFun=function(){
		mapApp.clear();
		$('#dataInputx').textbox('setValue',"");
		mapApp.changeDragMode('drawCircle',dataInputx,dataInputy,callbackFun);
		fenceKind=2;
	};
	//画矩形
	var rectangleFun=function(){
		mapApp.clear();
		$('#dataInputx').textbox('setValue',"");
		mapApp.changeDragMode('drawRect',dataInputx,dataInputy,callbackFun);
		fenceKind=1;
	};
	//画多边形
	var polygonFun=function(){
		mapApp.clear();
		$('#dataInputx').textbox('setValue',"");
		mapApp.changeDragMode('drawPolygon',dataInputx,dataInputy,callbackFun);
		fenceKind=1;
	};
	//回调
	var callbackFun=function(str){
		$('#dataInputx').textbox("setValue",str);
		//$.messager.alert('提示', "坐标数据:"+str, 'info');
	};
	//关闭
	var closeFun=function(){
		$('#layout_south').hide();
		$('#dataInputx').textbox("setValue","");
		$('#cb_name').combobox('readonly',false);
	};
</script>
</head>
<body id="mainLayout" class="easyui-layout">
 <OBJECT id="min" type="application/x-oleobject" classid="clsid:adb880a6-d8ff-11cf-9377-00aa003b7a11">
	<PARAM name="Command" value="Minimize">
  </OBJECT>
  <OBJECT id="max" type="application/x-oleobject" classid="clsid:adb880a6-d8ff-11cf-9377-00aa003b7a11">
	<PARAM name="Command" value="Maximize">
  </OBJECT>
	<!-- 导航栏 -->
	<div id="toolbar" style="display: none;">
		<table>
			<tr>
				<%
					if (securityUtil.havePermission("/pgis/carFenceDefine!save")) {
				%>
				<td><a href="javascript:void(0);" class="easyui-linkbutton"
					data-options="iconCls:'ext-icon-note_add',plain:true"
					onclick="addFun();">添加</a></td>
				<%
					}
				%>
			</tr>
		</table>

	</div>
	<div data-options="region:'west'" title="电子围栏" style="width: 240px;">

		<div id="mainMenu"></div>
	</div>
	<!-- 地图 -->
	<div data-options="region:'center',fit:true"
		style="overflow: hidden;padding: 5px;">
		<div id="map"
			style="height: 100%;width: 86%;margin: 0px; padding: 0px;"></div>
	</div>
	<div id="layout_south"  data-options="region:'south'" style="height: 5%;width: 100%;">
		<table>
			<tr>
			<td>区域</td>
				<td><select id="cb_name" class="easyui-combobox"  name="fenceDefine.dictIdRegion"  style="width:200px"
					></select></td>
				<td><a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'ext-icon-disk',plain:true" onclick="saveFun();">保存</a></td>
				<td><a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'ext-icon-arrow_refresh',plain:true" onclick="clearFun();">清除</a></td>
				<td><a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'ext-icon-cancel',plain:true" onclick="closeFun();">关闭</a></td>
				<td><a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'ext-icon-pencil',plain:true" onclick="cycleFun();">画圆型</a></td>
				<td><a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'ext-icon-pencil',plain:true" onclick="rectangleFun();">画矩形</a></td>
				<td><a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'ext-icon-pencil',plain:true" onclick="polygonFun();">画多边形</a></td>
				<td><input class="easyui-textbox" id="dataInputx" style="width:300px" />
				<input class="easyui-textbox" id="dataInputy"  type="hidden" />
				</td>
				
				
			</tr>
		</table>
	</div>
</body>
</HTML>