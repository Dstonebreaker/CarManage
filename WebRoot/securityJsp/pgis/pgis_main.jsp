<%@ page language="java" contentType="text/html; charset=GB2312" pageEncoding="GB2312"%>
<%@ page import="com.system.entity.maintain.SessionInfo"%>
<%@page import="com.framework.util.WebMsgUtil"%>
<%
	String contextPath = request.getContextPath();
	SessionInfo sessionInfo = (SessionInfo) session.getAttribute("sessionInfo");
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-Strict.dtd">

<HTML xmlns="http://www.w3.org/1999/xhtml" xmlns:v = "urn:schemas-microsoft-com:vml">

<head>

<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>大庆市公安局车辆调度管理系统</title>

<!-- <?import namespace="v" implementation="#default#VML"?> -->

<jsp:include page="../../inc.jsp"></jsp:include>
<!-- 地图 -->
<script language="javascript" src="http://10.117.10.84:80/PGIS_S_TileMap/js/EzMapAPI.js"></script>
<!-- bookstrap -->
<link rel="stylesheet" href="<%=basePath%>/public/jslib/bookStrap/css/bootstrap.min.css"/>
<script src="<%=basePath%>/public/jslib/bookStrap/js/bootstrap.min.js"></script>

<style type="text/css">
	v\:* {
		BEHAVIOR: url(#default#VML);
	}
</style>

<script type="text/javascript" reload="true" id="www">
	var mainMenu;
	var grid;
	var orgId;
	
	
	var mapApp=null;	//地图变量
	var title_timeOut;	//点击小车后显示的车辆信息
	var showCar_Id;		//刷新小车用的线程的id； 
	var refreshTime = 20000;	//车辆刷新时间--20秒;
	
	$(function() {
		//初始化map=============================================
		//隐藏提示框
		$('#alertCarInfo').hide();
		
		if(typeof EzMap =="undefined"){
			window.setTimeout("onLoad()",10);
			return;
		}
	  	if(_compatIE()){
			mapApp = new EzMap(document.getElementById("map"));

			//地图设置
			//mapApp.centerAndZoom(new Point(118.39459,35.09203), 1);
			mapApp.zoomTo(16);
			mapApp.centerAtLatLng(125.09211,46.59558);
			mapApp.hideCopyright();//隐藏版权信息
			mapApp.hideMapScale();//隐藏比例尺信息
			mapApp.switchMapServer(0);//右上角地图服务的切换
			//mapApp.pan(125.08047,46.60021);
			
			
			//地图监听事件
			mapApp.addMapChangeListener(function(){
				
				//如果showCar_Id不为空，当移动地图是需要刷新，
				if(showCar_Id){
					//$('#carNo_carList').val(rowData.carId);
					if($('#carNo_carList').val()){
						showCar($("#searchForm_carNo_carList"));
					}
				}
			});

			/*======================================= 测试数据 ============================================*/
			
			//var path = getRandPoints().join(",");
			//drawingLineByPath(path);
			//newCarMarkerByPath(path);
			//debugger;
			//newCarMarkerByPath("125.08047,46.60021,125.08126,46.59997,125.08218,46.59973,125.08322,46.59936,125.08389,46.59918,125.08493,46.59893,125.08578,46.59838,125.08639,46.59802,125.08718,46.59759,125.08773,46.59722,125.08853,46.59698,125.08969,46.59637,125.09024,46.59588,125.09127,46.59558,125.09194,46.5949,125.09268,46.59466,125.09329,46.59417,125.09408,46.59393,125.09469,46.59338,125.09579,46.59277,125.09689,46.59228,125.0978,46.59167,125.09878,46.59112,125.09963,46.59082,125.10055,46.59039,125.10122,46.58984,125.10208,46.58935,125.10299,46.58886,125.10397,46.58843,125.10482,46.58789,125.10568,46.58734,125.10659,46.58691,125.10733,46.58642,125.1083,46.58593,125.10903,46.58551,125.10971,46.58496,125.11056,46.58453,125.11129,46.5841,125.11233,46.58355,125.11367,46.58288,125.11453,46.58227,125.1155,46.5816,125.11611,46.58123,125.11691,46.58093");
			//drawingLineByPath(getRandPoints().join(","));
			
			//drawingLineByPath("");
			
			//addCircle(new Point(125.10490417480469,46.603271484375),"0.002.5");
		}else if(mapApp==null){
			var pEle=document.getElementById("map");
			//pEle.innerHTML="<p>目前EzMap地图引擎不支持你使用的浏览器，我们当前支持如下浏览器类型:</p><ul><li><a href='http://www.microsoft.com/windows/ie/downloads/default.asp'>IE</a> 5.5+ (Windows)</li>";
		}
		
		//左侧导航栏 
		mainMenu = $('#mainMenu').tree({
			url : sys.contextPath + '/pgis/pgis_SysOrganizationAction!doNotNeedSecurity_getSelfAndSubSyorganizationsTreeByMapOrgList.cxl',
			parentField : 'pid',
			onClick : function(node) {
				//点击单位后 ，加载相应的车辆
				if (node.id) {
					orgId = node.id;
					//给要上传的from赋值
					$('#carOrg_search').val(node.id);
					
					//地图的边界
					var mbr = mapApp.getBoundsLatLng();
					$('#ALong').val(mbr.minX);
					$('#ALat').val(mbr.minY);
					$('#BLong').val(mbr.maxX);
					$('#BLat').val(mbr.maxY);
					
					$('#zoom').val(mbr.getZoomLevel());
					//加载车辆到地图上;
					showCar($("#searchForm_org"));
					//加载车辆到车辆列表
					search();
				}
			}
		});

		$('#mainLayout').layout('panel', 'center').panel({
			onResize : function(width, height) {
				sys.setIframeHeight('centerIframe', $('#mainLayout').layout('panel', 'center').panel('options').height - 5);
			}
		});
		
		//车牌号，搜索栏上的
		$('#carNo_search').combobox({
			editable : true,
			disabled : false,
			valueField : 'carId',
			textField : 'carNo',
			url : sys.contextPath+'/pgis/carManage!doNotNeedSecurity_getcarList.cxl',
			panelHeight : 'auto',
			panelMaxHeight : '350px'
		});
		
		//车列表
		grid = $('#carForm').datagrid(
				{
				url : sys.contextPath + '/pgis/carManage!doNotNeedSecurity_getcarList.cxl',
				striped : true,//斑马线
				rownumbers : false,//
				pagination : false,//分页
				singleSelect : true,//如果为true，则只允许选择一行。
				idField : 'carId',
				sortName : 'carNo',
				sortOrder : 'desc',
				//pageSize : 30,
				//toolbar:'#tb',
				method:'get',
				columns : [ [ {
							title : '',
							field : 'options',
							width : '200',
							formatter : function(value, row) {
								var str = sys.formatString('<div id="carNoText">{0}</div>',row.carNo);
								str += sys.formatString('<div id="carOrgText">所属单位:    {0}</div>',row.orgName);
								
								str += sys.formatString('<div> <a href="javascript:void(0)" class="btn1"  onclick="details(\'{0}\')" data-options="plain:true">详情</a>', row.carId);
								//str += sys.formatString('<a href="javascript:void(0)" class="btn2" onclick="location(\'{0}\')" data-options="plain:true">定位</a>', row.carId);
								str += sys.formatString('<a href="javascript:void(0)" class="btn3" onclick="trail(\'{0}\')" data-options="plain:true">轨迹</a> </div>', row.carId);
								return str;
							}
							} ] ],
							onBeforeLoad : function(param) {
								parent.$.messager.progress({
									text : '数据加载中....'
								});
							},
							onLoadSuccess : function(data) {
								//$('.btn1').linkbutton({text:'定位', plain:true, height:18, iconCls:'ext-icon-zoom'});
								$('.btn3').linkbutton({text:'轨迹', plain:true, height:18, iconCls:'ext-icon-page_white_edit',iconAlign:'left'});
								$('.btn1').linkbutton({text:'详情', plain:true, height:18, iconCls:'ext-icon-delete',iconAlign:'right'}); 
								parent.$.messager.progress('close');
							},
							
							//点击定位到相应的车辆
							onClickRow : function(rowIndex, rowData){
								//console.info(rowData);
								$('#carNo_carList').val(rowData.carId);
								showCar($("#searchForm_carNo_carList"));
							}
							
				});
		
		
		
		
	})//onload结束=======================================================================
	
	
	
		
	
	//定位
	function location(){
		
	}
	//详情
	function details(carId){
		console.info("详情"+carId);
		
		var dialog = parent.sys.modalDialog({
			title : '详情',
			url : sys.contextPath + '/securityJsp/pgis/pgis_detailsForm.jsp?id=' + carId,
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
	//轨迹
	function trail(carId){
		console.info("轨迹"+carId);
		
		//先清除所有车辆或轨迹线;
		mapApp.clear();
		//加载轨迹
		//url需要修改，等mongodb完成，
		$.post(sys.contextPath + '/pgis/carManage!doNotNeedSecurity_getcarList.cxl', 'carId='+carId, function(result) {
					if (result) {
						//请求成功，
						//result的内容应该为经纬度字符串,根据字符串显示轨迹和小车的移动
						//画线
						drawingLineByPath(result);
						//小车移动
						newCarMarkerByPath(result);
					} 
						
				}, 'json').error(function() { alert("网络超时"); });
		
	}
	
	//车辆加载
	//点击单位时
	function search(){
		grid.datagrid('load', sys.serializeObject($("#searchForm_org")));
	}
	
	//弹车辆信息窗口
	function alertCarInfo(){
		$('#alertCarInfo').show();
		//$('#alertCarInfo').hide();
		
		clearTimeout(title_timeOut);
		title_timeOut = setTimeout(function(){
			$('#alertCarInfo').hide();
		},5000);
		//$('#alertCarInfo').css("left","200px");
	}
	
	//改变layout的大小
	/* $('#mainLayout').layout('panel', 'center').panel({
		onResize : function(width, height) {
			sys.setIframeHeight('centerIframe', $('#mainLayout').layout('panel', 'center').panel('options').height - 5);
		}
	}); */
	
	
	
	//以下是map部分====================================================================
	
	function newCarIcon(kind,outRegion){
		var icon = new Icon();
		switch(kind){
		case '轿车':
			//等于0代表没有出边界
			if(outRegion == 0){
				icon.image = "../../img/jc_l.png";
			}else{
				icon.image = "../../img/jc_h.png";
			}
			break;
		case '越野车':
			if(outRegion == 0){
				icon.image = "../../img/yy_l.png";
			}else{
				icon.image = "../../img/yy_h.png";
			}
			break;
			
		case '多用途乘用车':
			if(outRegion == 1){
				icon.image = "../../img/dyt_h.png";
			}else{
				icon.image = "../../img/dyt_l.png";
			}
			break;
		//轨迹线上泡的车
		case 'asdf':
				icon.image = "../../img/car_006.png";
			break;
			
		default:
			if(outRegion == 1){
				icon.image = "../../img/zk_h.png";
			}else{
				icon.image = "../../img/zk_l.png";
			}
			break;
		
		}
		return icon;
	}
	
	//画圆,用于显示聚合
	function addCircle(point,radius){
		
		if(!radius){
			radius=0.01;
		}
		//画圆
		var circle = new Circle(point.x+","+point.y+","+radius, "#4169E1", 2, 1, "#7195FC");
		mapApp.addOverlay(circle);
		//添加文本
		addText("56",point);
		
	}
	//加入文本
	function addText(text,point){
		  var pTitle = new Title(text,18,null,"宋体",null,"#7195FC",null,0);
		  pTitle.setPoint(point);
		  pTitle.bIsTransparent=true;
		  mapApp.addOverlay(pTitle);
	}
	
	
	//用于画完轨迹后让小车沿着轨迹跑一边,方法里的数据适配与轨迹上泡的小车;
	function newCarMarkerByPath(path){
		var icon = newCarIcon("asdf","");
		var marker = new Marker(new Point(125.09211,46.59558),icon);
		//marker.isClick = false;
		
		mapApp.addOverlay(marker);
		
		//添加点击事件
		/* marker.addListener("click",function(){
			//marker.openInfoWindowHtml("获取weindows");
			if(marker.isClick){
				marker.scale(1);
				marker.isClick = false;
			}else{
				alertCarInfo();
				marker.scale(3);
				marker.isClick = true;
			}
		}); */
		marker.setInterval(1);
	 	marker.setPath(0,700,path);
		marker.play();
	 	return marker;
	}
	//代小车，by点
	function newCarMarkerByPoint(obj){
		var icon = newCarIcon(obj.carKind,obj.isOutRegion);
		var marker = new Marker(new Point(obj.obdGpsLongitude,obj.obdGpsLatitude),icon);
		
		marker.isClick = false;
		
		mapApp.addOverlay(marker);
		
		marker.addListener("click",function(){
			//marker.openInfoWindowHtml("获取weindows");
			if(marker.isClick){
				marker.scale(1);
				marker.isClick = false;
			}else{
				alertCarInfo();
				marker.scale(3);
				marker.isClick = true;
			}
		});
		return marker;
	}
	
	//画线,轨迹
	function drawingLineByPath(path){

		var strPoint=getRandPoints().join(",");
		var pLine=new Polyline(strPoint,"#ff0000", 3,0.9,1);
		//移动到线条的中心
		var mbr = pLine.getMBR();
		mapApp.centerAtMBR(mbr.minX,mbr.minY,mbr.maxX,mbr.maxY);
		//调zoom使线条全部显示
		while(!mapApp.getBoundsLatLng().containsBounds(mbr)){
			mapApp.zoomOut();
		} 
		
		mapApp.addOverlay(pLine);
	}
	
	
	//加载小车，并且自动刷新;
	function showCar(form) {
		clearInterval(showCar_Id);
		//先执行一遍;
		showCarFunction(form);
		//定时器
		showCar_Id = setInterval(function(){
			showCarFunction(form);
		}, refreshTime);
		
	}
	//showCar中调用的，用于显示车辆
	function showCarFunction(form) {
		//先清除所有车辆.轨迹线;
		mapApp.clear();
		//加载车辆
		$.post(sys.contextPath + '/pgis/carManage!doNotNeedSecurity_getcarList.cxl', form.serialize(), function(result) {
					if (result) {
						//请求成功
						//如果result.amount不为null，代表加载过来的是聚合数据
						console.info(result);
						if(result.amount){
							//显示聚合
							var a = 0;
							for (;a < result.length;a++) {
								if(result[a].carLong){
									addCircle(new Point(result[a].carLong,result[a].carLat),null);
								}
							}
						}else{
							//显示所有加载过来的车辆;
							var a = 0;
							for (;a < result.length;a++) {
								if(result[a].obdGpsLongitude){
									newCarMarkerByPoint(result[a]);
								}
								if(result.length == 1){
									mapApp.centerAtLatLng(result[a].obdGpsLongitude,result[a].obdGpsLatitude);
								}
							}
						}
					} 
						
				}, 'json').error(function() { alert("网络超时"); });
	}
	
	
	
	
	
	//FeatureObject  
	//Circle圆
	//EditObject 编辑类
	
	//测试用类
	function getRandPolygon(){
		var pPoints=getRandPoints();
		var pPoint=pPoints.shift();
		pPoints.push(pPoint);
		pPoints.unshift(pPoint);
		return pPoints;
	}
	//测试用类
	function getRandPoints(){
	      var bounds = getMapApp().getBoundsLatLng();
	      var width = bounds.maxX - bounds.minX;
	      var height = bounds.maxY - bounds.minY;
	      points=new Array();
	      for (var i = 0; i < 5; i++) {
	        points.push(new Point(bounds.minX + width * Math.random(),
	                              bounds.minY + height * Math.random()));
	      }
	      points.sort(function(p1, p2) { return p1.x - p2.x; });
	      return points; 
	}
</script>

<style type="text/css">

	*{
		margin: 0px;
		padding: 0px;
	}

	.title_carNo{
		font-size: 18px;
		font-weight: 800;
	}
	.title_span{
		font-size: 14px;
		font-weight: 500;
	}
	#d_carBrand{
		margin-top: 10px;
	}
	
</style>
</head>
<body id="mainLayout" class="easyui-layout">
	<form method="post" id="searchForm_org">
		<input id="carOrg_search" name="orgId" type="hidden">
		<input id="ALong" name="ALong" type="hidden">
		<input id="ALat" name="ALat" type="hidden">
		<input id="BLong" name="BLong" type="hidden">
		<input id="BLat" name="BLat" type="hidden">
		<!-- 用于判断显示聚合还是车 -->
		<input id="zoom" name="zoom" type="hidden">
	</form>
	
	<form method="post" id="searchForm_carNo_carList">
		<input id="carNo_carList" name="QUERY_t#carId_S_EQ">
	</form>


	<!-- 导航栏 -->
	<!-- <div data-options="region:'west',href:'',split:true" title="组织单位" style="width: 240px;overflow:hidden; padding: 0px;"> -->
	<div data-options="region:'west',href:'' " title="组织单位" style="width: 240px;overflow:hidden; padding: 0px;">
		<!-- 组织单位菜单 -->
		<div id="mainMenu" style="height: 200px;overflow: auto;overflow-x:hidden"></div>
		
		<!-- 画中间的线 -->
		<div style="font-weight: 800;font-size: 15.5px;margin: 30px 0px 5px 4px;">车辆列表</div>
		<hr style="background-color: gray; height: 1px;margin: 0px;padding: 0px"></hr>
		<!-- 搜索框 -->
		<div style="width: 100%;margin-left: 5px;margin-top: 5px;">
			<form method="post" id="searchForm_carNo">
				<input id="carNo_search" name="QUERY_t#carId_S_EQ">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'ext-icon-zoom'" 
					onclick="search_button()" style="display: inline">查询</a>
			</form>
		</div>
		
		
		<table id="carForm" class="easyui-datagrid" style="width:100%;height:440px;margin:0px;padding:0px">
		</table>
		
	</div>
	
	
	<!-- 地图 -->
	<div data-options="region:'center',fit:true" style="overflow: hidden;padding: 5px;">
		<%-- <iframe src="<%=contextPath%>/securityJsp/PGIS/pgis_map.jsp" allowTransparency="true" style="border: 0; width: 80%; height: 100%;" frameBorder="0"></iframe> --%>
			<!-- 车辆提示框 -->
		<div id="alertCarInfo" class="panel panel-info" style="position: fixed; z-index: 20; width: 250px">
			<div class="panel-body">
				<div>
					<span id="t_carNo" class="title_carNo">黑EY0312</span>
				</div>
				<div id="d_carBrand">
					<span class="title_span">车辆品牌：</span><span id="t_carBrand">大众</span>
					
				</div>
				<div>
					<span class="title_span">驾驶员：</span><span id="t_carPilot">张三</span>
				</div>
				<div>
					<span class="title_span left_span">出车时间：</span><span id="t_carTime">2016.9.10</span>
				</div>
				
			</div>
		</div>
		
		<div id="map" style="height: 100%;width: 86%;margin: 0px; padding: 0px;"></div>
		<!-- 车辆提示框 -->
		
	</div>
	
	
	
	
	<script type="text/javascript">
	function search_button(){
		grid.datagrid('load', sys.serializeObject($("#searchForm_carNo")));
		
	}
	</script>
	
</body>
</html>