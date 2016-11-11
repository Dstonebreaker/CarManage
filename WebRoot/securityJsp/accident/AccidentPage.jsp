<%@page import="sun.reflect.ReflectionFactory.GetReflectionFactoryAction"%>
<%@page import="com.framework.util.WebMsgUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.framework.util.SecurityUtil"%>
<%@ page import="com.system.entity.maintain.SessionInfo"%>
<%@ page import="com.framework.util.ConfigUtil"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
SecurityUtil securityUtil = new SecurityUtil(session);
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
	<jsp:include page="../../inc.jsp"></jsp:include>

  </head>
  
 <body class="easyui-layout" data-options="fit:true,border:false">
	<div id="tb" style="padding:5px 5px;">
	<form method="post" id="searchForm">		
		车牌号：<input id="carNo" name="QUERY_t#carId_S_EQ">
		事故日期：<input id="acciTime" name="QUERY_t#acciTime_D_GE">
	
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'ext-icon-zoom'"
				onclick="doSearch()">查询</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'ext-icon-zoom_out'"
				onclick="doClear()">清空条件</a>
					<%if (securityUtil.havePermission("/car/Model!save")) {%>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'ext-icon-note_add'"
				onclick="newForm();">添加</a>
			<%}	%>
	</form>
		
	</div>
	<div data-options="region:'center',fit:true,border:false" style="height:100%;margin:0px;padding:0px">
		<table id="dg" class="easyui-datagrid" style="width:100%;height:100%;margin:0px;padding:0px">
		</table>
	</div>
	</body>
	<script>
		var grid;
		function doSearch(){
			
			grid.datagrid('load', sys.serializeObject($("#searchForm")));
		}
		function doClear() {
			$("#searchForm").form('clear');
		}
		$(function(){
			// 组织
	/* 		$('#ownerCompany').combotree({
				editable : true,
				panelWidth : 200,
				panelHeight:'auto',
				panelMaxHeight:'350px',
				idField:'id',
				valueField :'text',
				textField:'text',
				parentField:'pid',
				url : sys.contextPath + '/maintain/sysOrganization!doNotNeedSecurity_comboTree.cxl?id=',
				onBeforeLoad : function(row, param) {
					parent.$.messager.progress({
						text : '数据加载中....'
					});
					if (row) {
						$('#ownerCompany').combotree('tree').tree('options').url = sys.contextPath
								+ '/maintain/sysOrganization!doNotNeedSecurity_comboTree.cxl?id='
								+ row.id;
					}
				},
				onLoadSuccess : function(row, data) {
					parent.$.messager.progress('close');
				}
			}); */
			 var managerOrg = $('#ownerCompany').combobox({
			        url: sys.contextPath + '/maintain/sysOrganization!doNotNeedSecurity_getOrg.cxl?userId=0' ,
			        panelHeight: '200',		     
			        editable: false,
			        valueField: 'orgName',
			        textField: 'orgName'

			    });
			//车牌号，搜索栏上的
			$('#carNo').combobox({
				editable : false,
				disabled : false,
				valueField : 'carId',
				textField : 'carNo',
				url : sys.contextPath+'/accident!doNotNeedSecurity_getUsercarList.cxl',			
				panelHeight : 'auto',
				panelMaxHeight : '350px'
			});

			//事故时间
			$("#acciTime").datebox({
				editable:false
			});


			//$('#carNo').



			grid = $('#dg').datagrid(
					{
					url : sys.contextPath + '/accident!grid.cxl',
					striped : true,
					rownumbers : true,
					pagination : true,
					singleSelect : true,
					idField : 'acciId',
					sortName : 'acciNo',
					sortOrder : 'desc',
					pageSize : 30,
					toolbar:'#tb',
					method:'get',
					pageList : [ 10, 20, 30, 40, 50, 100, 200, 300,
								400, 500 ],
					columns : [ [ {
								width : '100',
								title : '编号',
								field : 'acciNo',
								sortable : true
								},{
								width : '100',
								title : '车牌号',
								field : 'carNo',
								sortable : true
								},{
								width : '150',
								title : '产权单位',
								field : 'ownerCompany',
								sortable : false
								},{
								width : '120',
								title : '事故类型',
								field : 'acciType',
								sortable : false
								},{
								width : '120',
								title : '事故状态',
								field : 'acciState',
								sortable : false
								},{
								width : '120',
								title : '事故责任',
								field : 'acciRisk',
								sortable : false
								},{
								width : '100',
								title : '责任比例',
								field : 'acciRiskProportion',
								sortable : true
								},{
								width : '100',
								title : '保险理赔金额',
								field : 'acciInsuranceMoney',
								sortable : true
								},{
								width : '100',
								title : '私人理赔金额',
								field : 'acciSelfMoney',
								sortable : true
								},{
								width : '100',
								title : '扣分 ',
								field : 'acciDeductMark',
								sortable : true
								},{
								width : '120',
								title : '事故时间',
								field : 'acciTime',
								sortable : true
								},{
								title : '操作',
								field : 'options',
								width : '200',
								formatter : function(value, row) {
									var str = sys.formatString('<a href="javascript:void(0)" class="btn1" onclick="details(\'{0}\',\'{1}\')" data-options="plain:true">详情</a>', row.acciId,row.carNo);
									<%-- if (row.dictIdCheckStatus == '<%=WebMsgUtil.CHECK_STATUS_START%>') {
										str += sys.formatString('<a href="javascript:void(0)" class="btn2" onclick="edit(\'{0}\')" data-options="plain:true">编辑</a>', row.acciId);
										str += sys.formatString('<a href="javascript:void(0)" class="btn3" onclick="del(\'{0}\')" data-options="plain:true">删除</a>', row.acciId);
									} else {
										str += sys.formatString('<a href="javascript:void(0)" class="btn2" onclick="edit(\'{0}\')" data-options="plain:true,disabled:true">编辑</a>', row.acciId);
										str += sys.formatString('<a href="javascript:void(0)" class="btn3" onclick="del(\'{0}\')" data-options="plain:true,disabled:true">删除</a>', row.acciId);
									} --%>

									
									<%if (securityUtil.havePermission("/accident!delete")) {%>
										str += sys.formatString('<a href="javascript:void(0)" class="btn2" onclick="edit(\'{0}\',\'{1}\')" data-options="plain:true">编辑</a>', row.acciId,row.carNo);
									<%}%>
									
									<%if (securityUtil.havePermission("/accident!delete")) {%>
										str += sys.formatString('<a href="javascript:void(0)" class="btn3" onclick="del(\'{0}\')" data-options="plain:true">删除</a>', row.acciId);
									<%}%>

									return str;
								}
								} ] ],
								onBeforeLoad : function(param) {
									parent.$.messager.progress({
										text : '数据加载中....'
									});
								},
								onLoadSuccess : function(data) {
									$('.btn1').linkbutton({text:'详情', plain:true, height:18, iconCls:'ext-icon-zoom'});
									$('.btn2').linkbutton({text:'编辑', plain:true, height:18, iconCls:'ext-icon-page_white_edit'});
									$('.btn3').linkbutton({text:'删除', plain:true, height:18, iconCls:'ext-icon-delete'});
									parent.$.messager.progress('close');
								}
					});
	});
		
		
		<%-- <%if (securityUtil.havePermission("/accident!update")) {  
			String orgIdManager_cu = ((SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName())).getOrganization().getOrgIdManager();%>
			if(row.orgIdManager == <%=orgIdManager_cu%>){
				str += sys.formatString('<a href="javascript:void(0)" class="btn2" onclick="edit(\'{0}\')" data-options="plain:true">编辑</a>', row.acciId);
			}else{
				str += sys.formatString('<a href="javascript:void(0)" class="btn2" onclick="edit(\'{0}\')" data-options="plain:true,disabled:true">编辑</a>', row.acciId);
			}
		<%}%> --%>
		
		
	var details = function(id,carNo) {
		var carNo_new=encodeURI(encodeURI(carNo));
		var dialog = parent.sys.modalDialog({
			title : '详情',
			url : sys.contextPath + '/securityJsp/accident/accident_detail.jsp?id=' + id + '&carNo=' + carNo_new,
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

		/* dialog({
			buttons : [ {
				text : '关13',
				handler : function() {
					//dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
					dialog.dialog('destroy');
				}
			} ]
		}); */
	};
	var edit = function(id,carNo) {
		var carNo_new=encodeURI(encodeURI(carNo));
		var dialog = parent.sys.modalDialog({
			title : '编辑',
			href : sys.contextPath + '/securityJsp/accident/accidentForm.jsp?id=' + id + '&carNo=' + carNo_new,
			width: 750,
			height: 500,
			onClose:function(){dialog.dialog('destroy');},
			buttons : [ {
				text : '保存',
				handler : function() {

					var dataform = dialog.find("#form");
					//console.info(dataform);

		    		if (dataform.form('validate')) {
		    			parent.$.messager.progress({
		    				text : '数据处理中....'
		    			});

						var params = dataform.serialize();
//						debugger;
		    			var url = sys.contextPath + '/accident!update.cxl';
		    			$.post(url, params, function(result) {
		    				parent.$.messager.progress('close');
		    				if (result.success) {
		    					dialog.dialog('destroy');
		    					grid.datagrid('load');
		    					parent.$.messager.alert('提示','保存成功','info');
		    				} else {
	    						parent.$.messager.alert('提示',result.msg, 'error');
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


	var newForm = function() {
		var dialog = parent.sys.modalDialog({
			title : '新建',
			href : sys.contextPath + '/securityJsp/accident/accidentForm.jsp',
			width: 750,
			height: 400,
			onClose:function(){dialog.dialog('destroy');},
			buttons : [ {
				text : '保存',
				handler : function() {
					/* if(typeof JSON == 'undefined'){
						$('head').append($("<script type='text/javascript' src='../../public/jslib/json2.js'>"));
					} */
					var dataform = dialog.find("#form");
		    		if (dataform.form('validate')) {
		    			parent.$.messager.progress({
		    				text : '数据处理中....'
		    			});
						var params = dataform.serialize();
//						debugger;
						var url = sys.contextPath + '/accident!save.cxl';

		    			$.post(url,params,function(result){
		    				//console.info(result);
		    				parent.$.messager.progress('close');
		    				if(!result.success){
		    					parent.$.messager.alert('提示',result.msg, 'error');
		    				}else{
		    					dialog.dialog('destroy');grid.datagrid('load');
		    					grid.datagrid('load');
		    					$.messager.alert('提示','添加成功!');
		    				}
		    			},'json');
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

	var del = function(id) {
		parent.$.messager.confirm('确认', '是否要删除此条数据?', function(r){
			if (r){
				var url = sys.contextPath + '/accident!delete.cxl';
				parent.$.messager.progress({
					text : '数据处理中....'
				});
				$.post(url, {"id":id}, function(result) {
					parent.$.messager.progress('close');
					if (result.success) {
						grid.datagrid('load');
						flag = 'info';
					} else {
						flag = 'error';
					}
					parent.$.messager.alert('提示', result.msg, flag);
				}, 'json');
			}
		});
	};
</script>
</html>
