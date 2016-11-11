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
	var mainMenu;
	var grid;
	var orgId;
	
	
	var mapApp=null;	//��ͼ����
	var title_timeOut;	//���С������ʾ�ĳ�����Ϣ
	var showCar_Id;		//ˢ��С���õ��̵߳�id�� 
	var refreshTime = 20000;	//����ˢ��ʱ��--20��;
	
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
				
				//���showCar_Id��Ϊ�գ����ƶ���ͼ����Ҫˢ�£�
				if(showCar_Id){
					//$('#carNo_carList').val(rowData.carId);
					if($('#carNo_carList').val()){
						showCar($("#searchForm_carNo_carList"));
					}
				}
			});

			/*======================================= �������� ============================================*/
			
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
			//pEle.innerHTML="<p>ĿǰEzMap��ͼ���治֧����ʹ�õ�����������ǵ�ǰ֧���������������:</p><ul><li><a href='http://www.microsoft.com/windows/ie/downloads/default.asp'>IE</a> 5.5+ (Windows)</li>";
		}
		
		//��ർ���� 
		mainMenu = $('#mainMenu').tree({
			url : sys.contextPath + '/pgis/pgis_SysOrganizationAction!doNotNeedSecurity_getSelfAndSubSyorganizationsTreeByMapOrgList.cxl',
			parentField : 'pid',
			onClick : function(node) {
				//�����λ�� ��������Ӧ�ĳ���
				if (node.id) {
					orgId = node.id;
					//��Ҫ�ϴ���from��ֵ
					$('#carOrg_search').val(node.id);
					
					//��ͼ�ı߽�
					var mbr = mapApp.getBoundsLatLng();
					$('#ALong').val(mbr.minX);
					$('#ALat').val(mbr.minY);
					$('#BLong').val(mbr.maxX);
					$('#BLat').val(mbr.maxY);
					
					$('#zoom').val(mbr.getZoomLevel());
					//���س�������ͼ��;
					showCar($("#searchForm_org"));
					//���س����������б�
					search();
				}
			}
		});

		$('#mainLayout').layout('panel', 'center').panel({
			onResize : function(width, height) {
				sys.setIframeHeight('centerIframe', $('#mainLayout').layout('panel', 'center').panel('options').height - 5);
			}
		});
		
		//���ƺţ��������ϵ�
		$('#carNo_search').combobox({
			editable : true,
			disabled : false,
			valueField : 'carId',
			textField : 'carNo',
			url : sys.contextPath+'/pgis/carManage!doNotNeedSecurity_getcarList.cxl',
			panelHeight : 'auto',
			panelMaxHeight : '350px'
		});
		
		//���б�
		grid = $('#carForm').datagrid(
				{
				url : sys.contextPath + '/pgis/carManage!doNotNeedSecurity_getcarList.cxl',
				striped : true,//������
				rownumbers : false,//
				pagination : false,//��ҳ
				singleSelect : true,//���Ϊtrue����ֻ����ѡ��һ�С�
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
								str += sys.formatString('<div id="carOrgText">������λ:    {0}</div>',row.orgName);
								
								str += sys.formatString('<div> <a href="javascript:void(0)" class="btn1"  onclick="details(\'{0}\')" data-options="plain:true">����</a>', row.carId);
								//str += sys.formatString('<a href="javascript:void(0)" class="btn2" onclick="location(\'{0}\')" data-options="plain:true">��λ</a>', row.carId);
								str += sys.formatString('<a href="javascript:void(0)" class="btn3" onclick="trail(\'{0}\')" data-options="plain:true">�켣</a> </div>', row.carId);
								return str;
							}
							} ] ],
							onBeforeLoad : function(param) {
								parent.$.messager.progress({
									text : '���ݼ�����....'
								});
							},
							onLoadSuccess : function(data) {
								//$('.btn1').linkbutton({text:'��λ', plain:true, height:18, iconCls:'ext-icon-zoom'});
								$('.btn3').linkbutton({text:'�켣', plain:true, height:18, iconCls:'ext-icon-page_white_edit',iconAlign:'left'});
								$('.btn1').linkbutton({text:'����', plain:true, height:18, iconCls:'ext-icon-delete',iconAlign:'right'}); 
								parent.$.messager.progress('close');
							},
							
							//�����λ����Ӧ�ĳ���
							onClickRow : function(rowIndex, rowData){
								//console.info(rowData);
								$('#carNo_carList').val(rowData.carId);
								showCar($("#searchForm_carNo_carList"));
							}
							
				});
		
		
		
		
	})//onload����=======================================================================
	
	
	
		
	
	//��λ
	function location(){
		
	}
	//����
	function details(carId){
		console.info("����"+carId);
		
		var dialog = parent.sys.modalDialog({
			title : '����',
			url : sys.contextPath + '/securityJsp/pgis/pgis_detailsForm.jsp?id=' + carId,
			width: 750,
			height: 550,
			onClose:function(){dialog.dialog('destroy');},
			buttons : [ {
				text : '�ر�',
				handler : function() {
					//dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
					dialog.dialog('destroy');
				}
			} ]
		});
	}
	//�켣
	function trail(carId){
		console.info("�켣"+carId);
		
		//��������г�����켣��;
		mapApp.clear();
		//���ع켣
		//url��Ҫ�޸ģ���mongodb��ɣ�
		$.post(sys.contextPath + '/pgis/carManage!doNotNeedSecurity_getcarList.cxl', 'carId='+carId, function(result) {
					if (result) {
						//����ɹ���
						//result������Ӧ��Ϊ��γ���ַ���,�����ַ�����ʾ�켣��С�����ƶ�
						//����
						drawingLineByPath(result);
						//С���ƶ�
						newCarMarkerByPath(result);
					} 
						
				}, 'json').error(function() { alert("���糬ʱ"); });
		
	}
	
	//��������
	//�����λʱ
	function search(){
		grid.datagrid('load', sys.serializeObject($("#searchForm_org")));
	}
	
	//��������Ϣ����
	function alertCarInfo(){
		$('#alertCarInfo').show();
		//$('#alertCarInfo').hide();
		
		clearTimeout(title_timeOut);
		title_timeOut = setTimeout(function(){
			$('#alertCarInfo').hide();
		},5000);
		//$('#alertCarInfo').css("left","200px");
	}
	
	//�ı�layout�Ĵ�С
	/* $('#mainLayout').layout('panel', 'center').panel({
		onResize : function(width, height) {
			sys.setIframeHeight('centerIframe', $('#mainLayout').layout('panel', 'center').panel('options').height - 5);
		}
	}); */
	
	
	
	//������map����====================================================================
	
	function newCarIcon(kind,outRegion){
		var icon = new Icon();
		switch(kind){
		case '�γ�':
			//����0����û�г��߽�
			if(outRegion == 0){
				icon.image = "../../img/jc_l.png";
			}else{
				icon.image = "../../img/jc_h.png";
			}
			break;
		case 'ԽҰ��':
			if(outRegion == 0){
				icon.image = "../../img/yy_l.png";
			}else{
				icon.image = "../../img/yy_h.png";
			}
			break;
			
		case '����;���ó�':
			if(outRegion == 1){
				icon.image = "../../img/dyt_h.png";
			}else{
				icon.image = "../../img/dyt_l.png";
			}
			break;
		//�켣�����ݵĳ�
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
	
	//��Բ,������ʾ�ۺ�
	function addCircle(point,radius){
		
		if(!radius){
			radius=0.01;
		}
		//��Բ
		var circle = new Circle(point.x+","+point.y+","+radius, "#4169E1", 2, 1, "#7195FC");
		mapApp.addOverlay(circle);
		//����ı�
		addText("56",point);
		
	}
	//�����ı�
	function addText(text,point){
		  var pTitle = new Title(text,18,null,"����",null,"#7195FC",null,0);
		  pTitle.setPoint(point);
		  pTitle.bIsTransparent=true;
		  mapApp.addOverlay(pTitle);
	}
	
	
	//���ڻ���켣����С�����Ź켣��һ��,�����������������켣���ݵ�С��;
	function newCarMarkerByPath(path){
		var icon = newCarIcon("asdf","");
		var marker = new Marker(new Point(125.09211,46.59558),icon);
		//marker.isClick = false;
		
		mapApp.addOverlay(marker);
		
		//��ӵ���¼�
		/* marker.addListener("click",function(){
			//marker.openInfoWindowHtml("��ȡweindows");
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
	//��С����by��
	function newCarMarkerByPoint(obj){
		var icon = newCarIcon(obj.carKind,obj.isOutRegion);
		var marker = new Marker(new Point(obj.obdGpsLongitude,obj.obdGpsLatitude),icon);
		
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
	
	
	//����С���������Զ�ˢ��;
	function showCar(form) {
		clearInterval(showCar_Id);
		//��ִ��һ��;
		showCarFunction(form);
		//��ʱ��
		showCar_Id = setInterval(function(){
			showCarFunction(form);
		}, refreshTime);
		
	}
	//showCar�е��õģ�������ʾ����
	function showCarFunction(form) {
		//��������г���.�켣��;
		mapApp.clear();
		//���س���
		$.post(sys.contextPath + '/pgis/carManage!doNotNeedSecurity_getcarList.cxl', form.serialize(), function(result) {
					if (result) {
						//����ɹ�
						//���result.amount��Ϊnull��������ع������Ǿۺ�����
						console.info(result);
						if(result.amount){
							//��ʾ�ۺ�
							var a = 0;
							for (;a < result.length;a++) {
								if(result[a].carLong){
									addCircle(new Point(result[a].carLong,result[a].carLat),null);
								}
							}
						}else{
							//��ʾ���м��ع����ĳ���;
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
						
				}, 'json').error(function() { alert("���糬ʱ"); });
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
	//��������
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
		<!-- �����ж���ʾ�ۺϻ��ǳ� -->
		<input id="zoom" name="zoom" type="hidden">
	</form>
	
	<form method="post" id="searchForm_carNo_carList">
		<input id="carNo_carList" name="QUERY_t#carId_S_EQ">
	</form>


	<!-- ������ -->
	<!-- <div data-options="region:'west',href:'',split:true" title="��֯��λ" style="width: 240px;overflow:hidden; padding: 0px;"> -->
	<div data-options="region:'west',href:'' " title="��֯��λ" style="width: 240px;overflow:hidden; padding: 0px;">
		<!-- ��֯��λ�˵� -->
		<div id="mainMenu" style="height: 200px;overflow: auto;overflow-x:hidden"></div>
		
		<!-- ���м���� -->
		<div style="font-weight: 800;font-size: 15.5px;margin: 30px 0px 5px 4px;">�����б�</div>
		<hr style="background-color: gray; height: 1px;margin: 0px;padding: 0px"></hr>
		<!-- ������ -->
		<div style="width: 100%;margin-left: 5px;margin-top: 5px;">
			<form method="post" id="searchForm_carNo">
				<input id="carNo_search" name="QUERY_t#carId_S_EQ">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'ext-icon-zoom'" 
					onclick="search_button()" style="display: inline">��ѯ</a>
			</form>
		</div>
		
		
		<table id="carForm" class="easyui-datagrid" style="width:100%;height:440px;margin:0px;padding:0px">
		</table>
		
	</div>
	
	
	<!-- ��ͼ -->
	<div data-options="region:'center',fit:true" style="overflow: hidden;padding: 5px;">
		<%-- <iframe src="<%=contextPath%>/securityJsp/PGIS/pgis_map.jsp" allowTransparency="true" style="border: 0; width: 80%; height: 100%;" frameBorder="0"></iframe> --%>
			<!-- ������ʾ�� -->
		<div id="alertCarInfo" class="panel panel-info" style="position: fixed; z-index: 20; width: 250px">
			<div class="panel-body">
				<div>
					<span id="t_carNo" class="title_carNo">��EY0312</span>
				</div>
				<div id="d_carBrand">
					<span class="title_span">����Ʒ�ƣ�</span><span id="t_carBrand">����</span>
					
				</div>
				<div>
					<span class="title_span">��ʻԱ��</span><span id="t_carPilot">����</span>
				</div>
				<div>
					<span class="title_span left_span">����ʱ�䣺</span><span id="t_carTime">2016.9.10</span>
				</div>
				
			</div>
		</div>
		
		<div id="map" style="height: 100%;width: 86%;margin: 0px; padding: 0px;"></div>
		<!-- ������ʾ�� -->
		
	</div>
	
	
	
	
	<script type="text/javascript">
	function search_button(){
		grid.datagrid('load', sys.serializeObject($("#searchForm_carNo")));
		
	}
	</script>
	
</body>
</html>