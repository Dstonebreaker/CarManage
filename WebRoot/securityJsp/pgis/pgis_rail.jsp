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
<title>Χ������</title>
<!-- <?import namespace="v" implementation="#default#VML" ?>
	<style>v/:rect,v/:rect,v/:imagedata{display:inline-block}</style> -->
<jsp:include page="../../inc.jsp"></jsp:include>
<!-- ��ͼ -->
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
	var mainMenu;//Χ���б�
	var orgId;

	//��ͼ����
	var mapApp = null;
	var g_overlay = null;
	var g_edit = false;
	var title_timeOut;
	var cb_name;
	var type=0;
	$(function() {
		//sys.contextPath + '/pgis/pgis_SysOrganizationAction!doNotNeedSecurity_getSelfAndSubSyorganizationsTreeByMapOrgList.cxl'
		/* ��ർ���� */
		
		$('#layout_south').hide();
		mainMenu = $('#mainMenu')
				.datagrid(
						{
							url : sys.contextPath
									+ '/pgis/carFenceDefine!grid.cxl',
							striped : true,//������
							rownumbers : false,//
							pagination : false,//��ҳ
							singleSelect : true,//���Ϊtrue����ֻ����ѡ��һ�С�
							idField : 'dictIdRegion',
							sortName : 'name',
							sortOrder : 'desc',
							frozenColumns : [ [ {
								width : '100',
								title : 'Χ��',
								sortable : true,
								field : 'name'
							} ] ],
							columns : [ [ {
								width : '120',
								title : '����',
								field : 'action',
								formatter : function(value, row,index) {
									var result = "";
								<%if (securityUtil.havePermission("/pgis/carFenceDefine!update")) {%>
										result += sys.formatString(
													'<a href="javascript:void(0)" class="btn1" onclick="editFun(\'{0}\');">�༭</a>',
													index);
								<%}%>
	
								<%if (securityUtil.havePermission("/pgis/carFenceDefine!delete")) {%>
										result += sys.formatString(
													'<a href="javascript:void(0)" class="btn2" onclick="deleteFun(\'{0}\');">ɾ��</a>',
													row.dictIdRegion);
								<%}%>
										return result;
								}
							} ] ],
							onClick : function(node) {

							},
							onLoadSuccess : function(data) {
								$('.btn1').linkbutton({text:'�༭', plain:true, height:18, iconCls:'ext-icon-page_white_edit'});
								$('.btn2').linkbutton({text:'ɾ��', plain:true, height:18, iconCls:'ext-icon-delete'});
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
			//��ͼ����
			mapApp.zoomTo(14);
			mapApp.centerAtLatLng(125.09211, 46.59558);
			mapApp.hideCopyright();//���ذ�Ȩ��Ϣ
			//mapApp.hideMapScale();//���ر�������Ϣ
			mapApp.switchMapServer(0);//���Ͻǵ�ͼ������л�
			mapApp.showMapControl();
			mapApp.showMapServer();
			
		} else if (mapApp == null) {
			var pEle = document.getElementById("map");
			//pEle.innerHTML="<p>ĿǰEzMap��ͼ���治֧����ʹ�õ�����������ǵ�ǰ֧���������������:</p><ul><li><a href='http://www.microsoft.com/windows/ie/downloads/default.asp'>IE</a> 5.5+ (Windows)</li>";
		}
	});//onload����=======================================================================

		
		
	//ɾ��
	var deleteFun = function(id) {
		parent.$.messager.confirm('ѯ��', '��ȷ��Ҫɾ���˼�¼��', function(r) {
			if (r) {
				$.post(sys.contextPath + '/pgis/carFenceDefine!delete.cxl', {
					id : id
				}, function() {
					 if (result.success) {
                		 $('#layout_south').hide();
                		 mainMenu.datagrid('reload');
     					$pjq.messager.alert('��ʾ',result.msg,'info');
     				} else {
     					$pjq.messager.alert('��ʾ',result.msg, 'error');
     				}
					
				}, 'json');
			}
		});
	};
	
	//�༭
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
	
	
	//���
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
	//���
	var clearFun = function() {
		mapApp.clear();
		$('#dataInputx').textbox('setValue',"");
	};
	//����
	var saveFun=function(){
		
		var cb_value=$('#cb_name').combobox('getValue');
		if(cb_value==undefined&&cb_value==""){
			$.messager.alert('��ʾ', "��ѡ����������!", 'error');
			return;
		}
		var tx_value=$('#dataInputx').textbox('getValue');
		if(tx_value==undefined&&tx_value==""){
			$.messager.alert('��ʾ', "�뻭Χ��ͼ��!", 'error');
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
     					$.messager.alert('��ʾ',result.msg,'info');
     				} else {
     					$.messager.alert('��ʾ',result.msg, 'error');
     				}
                 },'json');
	};
	//��Բ
	var cycleFun=function(){
		mapApp.clear();
		$('#dataInputx').textbox('setValue',"");
		mapApp.changeDragMode('drawCircle',dataInputx,dataInputy,callbackFun);
		fenceKind=2;
	};
	//������
	var rectangleFun=function(){
		mapApp.clear();
		$('#dataInputx').textbox('setValue',"");
		mapApp.changeDragMode('drawRect',dataInputx,dataInputy,callbackFun);
		fenceKind=1;
	};
	//�������
	var polygonFun=function(){
		mapApp.clear();
		$('#dataInputx').textbox('setValue',"");
		mapApp.changeDragMode('drawPolygon',dataInputx,dataInputy,callbackFun);
		fenceKind=1;
	};
	//�ص�
	var callbackFun=function(str){
		$('#dataInputx').textbox("setValue",str);
		//$.messager.alert('��ʾ', "��������:"+str, 'info');
	};
	//�ر�
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
	<!-- ������ -->
	<div id="toolbar" style="display: none;">
		<table>
			<tr>
				<%
					if (securityUtil.havePermission("/pgis/carFenceDefine!save")) {
				%>
				<td><a href="javascript:void(0);" class="easyui-linkbutton"
					data-options="iconCls:'ext-icon-note_add',plain:true"
					onclick="addFun();">���</a></td>
				<%
					}
				%>
			</tr>
		</table>

	</div>
	<div data-options="region:'west'" title="����Χ��" style="width: 240px;">

		<div id="mainMenu"></div>
	</div>
	<!-- ��ͼ -->
	<div data-options="region:'center',fit:true"
		style="overflow: hidden;padding: 5px;">
		<div id="map"
			style="height: 100%;width: 86%;margin: 0px; padding: 0px;"></div>
	</div>
	<div id="layout_south"  data-options="region:'south'" style="height: 5%;width: 100%;">
		<table>
			<tr>
			<td>����</td>
				<td><select id="cb_name" class="easyui-combobox"  name="fenceDefine.dictIdRegion"  style="width:200px"
					></select></td>
				<td><a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'ext-icon-disk',plain:true" onclick="saveFun();">����</a></td>
				<td><a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'ext-icon-arrow_refresh',plain:true" onclick="clearFun();">���</a></td>
				<td><a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'ext-icon-cancel',plain:true" onclick="closeFun();">�ر�</a></td>
				<td><a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'ext-icon-pencil',plain:true" onclick="cycleFun();">��Բ��</a></td>
				<td><a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'ext-icon-pencil',plain:true" onclick="rectangleFun();">������</a></td>
				<td><a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'ext-icon-pencil',plain:true" onclick="polygonFun();">�������</a></td>
				<td><input class="easyui-textbox" id="dataInputx" style="width:300px" />
				<input class="easyui-textbox" id="dataInputy"  type="hidden" />
				</td>
				
				
			</tr>
		</table>
	</div>
</body>
</HTML>