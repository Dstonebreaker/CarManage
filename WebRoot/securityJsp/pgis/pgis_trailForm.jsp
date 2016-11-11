<%@ page language="java" contentType="text/html; charset=GB2312" pageEncoding="GB2312"%>
<%@ page import="com.system.entity.maintain.SessionInfo"%>
<%@page import="com.framework.util.WebMsgUtil"%>
<%
	String contextPath = request.getContextPath();
	SessionInfo sessionInfo = (SessionInfo) session.getAttribute("sessionInfo");
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	
	String id = request.getParameter("id");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-Strict.dtd">

<HTML xmlns="http://www.w3.org/1999/xhtml" xmlns:v = "urn:schemas-microsoft-com:vml">

<head>

<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>�����й����ֳ������ȹ���ϵͳ</title>

<!-- <?import namespace="v" implementation="#default#VML"?> -->

<jsp:include page="../../inc.jsp"></jsp:include>
<!-- ��ͼ -->
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
	
	
	var mapApp=null;	//��ͼ����
	var carId = "<%=id%>";		//��������carId;
	
	$(function() {

		//��ʼ��map=============================================
		//������ʾ��
		$('#alertCarInfo').hide();
		
		if(typeof EzMap =="undefined"){
			window.setTimeout("onLoad()",10);
			return;
		}
	  	if(_compatIE()){
			mapApp = new EzMap(document.getElementById("map"));

			//��ͼ����
			//mapApp.centerAndZoom(new Point(118.39459,35.09203), 1);
			mapApp.zoomTo(16);
			mapApp.centerAtLatLng(125.09211,46.59558);
			mapApp.hideCopyright();//���ذ�Ȩ��Ϣ
			mapApp.hideMapScale();//���ر�������Ϣ
			mapApp.switchMapServer(0);//���Ͻǵ�ͼ������л�
			//mapApp.pan(125.08047,46.60021);
			
			
			//��ͼ�����¼�
			mapApp.addMapChangeListener(function(){
				
				/* console.info("asdf");
				console.info(mapApp.getZoomLevel());
				console.info(mapApp.getBoundsLatLng());
				console.info(mapApp.getSpanLatLng()); */
			});

			/*======================================= �������� ============================================*/
			
			//var path = getRandPoints().join(",");
			//drawingLineByPath("");
			//newCarMarkerByPath(path);
			//debugger;
			//newCarMarkerByPath("125.08047,46.60021,125.08126,46.59997,125.08218,46.59973,125.08322,46.59936,125.08389,46.59918,125.08493,46.59893,125.08578,46.59838,125.08639,46.59802,125.08718,46.59759,125.08773,46.59722,125.08853,46.59698,125.08969,46.59637,125.09024,46.59588,125.09127,46.59558,125.09194,46.5949,125.09268,46.59466,125.09329,46.59417,125.09408,46.59393,125.09469,46.59338,125.09579,46.59277,125.09689,46.59228,125.0978,46.59167,125.09878,46.59112,125.09963,46.59082,125.10055,46.59039,125.10122,46.58984,125.10208,46.58935,125.10299,46.58886,125.10397,46.58843,125.10482,46.58789,125.10568,46.58734,125.10659,46.58691,125.10733,46.58642,125.1083,46.58593,125.10903,46.58551,125.10971,46.58496,125.11056,46.58453,125.11129,46.5841,125.11233,46.58355,125.11367,46.58288,125.11453,46.58227,125.1155,46.5816,125.11611,46.58123,125.11691,46.58093");
			//drawingLineByPath(getRandPoints().join(","));
		}else if(mapApp==null){
			var pEle=document.getElementById("map");
			//pEle.innerHTML="<p>ĿǰEzMap��ͼ���治֧����ʹ�õ�����������ǵ�ǰ֧���������������:</p><ul><li><a href='http://www.microsoft.com/windows/ie/downloads/default.asp'>IE</a> 5.5+ (Windows)</li>";
		}
	})//onload����=======================================================================
	
	
	
		
	//�켣
	function trail(carId){
		console.info("�켣"+carId);
		
		//��������г�����켣��;
		mapApp.clear();
		//���ع켣
		//url��Ҫ�޸ģ���mongodb��ɣ�
		$.post(sys.contextPath + '/pgis/carManage!doNotNeedSecurity_getcarList.cxl', 'carId='+carId, function(result) {
					if (result.success) {
						//����ɹ���
						//result������Ӧ��Ϊ��γ���ַ���,�����ַ�����ʾ�켣��С�����ƶ�
						//����
						drawingLineByPath(result.obj);
						//С���ƶ�
						newCarMarkerByPath(result.obj);
					} 
						
				}, 'json').error(function() { alert("���糬ʱ"); });
		
	}
	
	
	
	
	//������map����
	
	function newCarIcon(){
		var icon = new Icon();
		icon.image = "../../img/car_006.png";
		
		return icon;
	}
	
	//���ڻ���켣����С�����Ź켣��һ��,�����������������켣���ݵ�С��;
	function newCarMarkerByPath(path){
		icon = newCarIcon();
		var marker = new Marker(new Point(125.09211,46.59558),icon);
		//marker.isClick = false;
		
		mapApp.addOverlay(marker);
		
		marker.setInterval(1);
	 	marker.setPath(0,700,path);
		marker.play();
	 	return marker;
	}
	//��С����by��
	function newCarMarkerByPoint(point){
		icon = newCarIcon();
		var marker = new Marker(point,icon);
		
		marker.isClick = false;
		
		mapApp.addOverlay(marker);
		
		marker.addListener("click",function(){
			//marker.openInfoWindowHtml("��ȡweindows");
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
	
	//����,�켣
	function drawingLineByPath(path){

		var strPoint=getRandPoints().join(",");
		var pLine=new Polyline(strPoint,"#ff0000", 3,0.9,1);
		//�ƶ�������������
		var mbr = pLine.getMBR();
		mapApp.centerAtMBR(mbr.minX,mbr.minY,mbr.maxX,mbr.maxY);
		//��zoomʹ����ȫ����ʾ
		while(!mapApp.getBoundsLatLng().containsBounds(mbr)){
			mapApp.zoomOut();
		} 
		
		mapApp.addOverlay(pLine);
	}
	
	
	
	
	
	//FeatureObject  
	//CircleԲ
	//EditObject �༭��
	
	//��������
	function getRandPolygon(){
		var pPoints=getRandPoints();
		var pPoint=pPoints.shift();
		pPoints.push(pPoint);
		pPoints.unshift(pPoint);
		return pPoints;
	}
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

</head>
<body id="mainLayout" class="easyui-layout">

		<!-- ��ͼ -->
		<div id="map" style="height: 100%;width: 100%;margin: 0px; padding: 0px;"></div>
		
	
</body>
</html>