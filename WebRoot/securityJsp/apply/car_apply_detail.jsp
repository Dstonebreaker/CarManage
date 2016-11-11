<%@page import="com.system.entity.maintain.SessionInfo"%>
<%@page import="com.framework.util.ConfigUtil"%>
<%@page import="com.framework.util.WebMsgUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	String id = request.getParameter("id");
	String f = request.getParameter("f");
	SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
<style>
	.textbox{
		height:20px;
	}
</style>

		<form method="post" id="detailForm">
		<div id="light" style="margin-bottom:10px;margin-top:10px">
			<input type="hidden" id="applyId" name="apply.applyId" value="">
			<input type="hidden" id="dictIdCheckStatus" name="apply.dictIdCheckStatus" value="">
			<input type="hidden" id="applyType" name="apply.applyType" value="">
			<input type="hidden" id="preApplyId" name="apply.preApplyId" value="">
			<table width="100%" align="center">
				<tr>
					<td>申请编号：</td>
					<td>
						<input class="textbox" id="applyNo" name="apply.applyNo"/>
					</td>
					<td>组织单位：</td>
					<td>
						<input class="textbox" id="orgId" name="apply.orgId"/>
						<input class="textbox" id="orgName" name="orgName"/>
					</td>
				</tr>
				<tr>
					<td>车辆种类：</td>
					<td>
						<input class="textbox" id="dictIdCarType" name="apply.dictIdCarType"/>
					</td>
					<td>用车区域：</td>
					<td>
						<input class="textbox" id="dictIdRegion" name="apply.dictIdRegion"/>
					</td>
				</tr>
				<tr>
					<td>预计用车时间：</td>
					<td>
						<input class="textbox" id="applyUsingTime" name="apply.applyUsingTime" validType="dateRex['#applyUsingTime','#applyRemandTime']"/>
					</td>
					<td>预计还车时间：</td>
					<td>
						<input class="textbox" id="applyRemandTime" name="apply.applyRemandTime" validType="dateRex['#applyUsingTime','#applyRemandTime']"/>
					</td>
				</tr>
				<tr>
					<td>用车理由：</td>
					<td colspan="5">
						<input class="textbox" id="dictIdUseCarReson" name="apply.dictIdUseCarReson"/>
					</td>
				</tr>
				<tr>
					<td>申请固定车辆：</td>
					<td>
						<input class="textbox" id="carId" name="apply.carId"/>
					</td>
					<td>是否保密：</td>
					<td>
						<input class="textbox" id="dictIdIsSecret" name="apply.dictIdIsSecret"/>
					</td>
				</tr>
				<!-- <tr>
					<td>驾驶员：</td>
					<td>
						<input class="textbox" id="userIdPilot" name="apply.userIdPilot"/>
					</td>
					<td>驾驶员手机：</td>
					<td>
						<input class="textbox" id="stafPhonePilot" name="apply.stafPhonePilot"/>
					</td>
				</tr> -->
				<tr>
					<td>警牌/民牌：</td>
					<td>
						<input class="textbox" id="carPoliceUsed" name="apply.carPoliceUsed"/>
					</td>
					<td>乘车人数：</td>
					<td>
						<input class="textbox" id="applyUsedPeople" name="apply.applyUsedPeople"/>
					</td>
				</tr>
				<tr>
					<td>申请人：</td>
					<td>
						<input class="textbox" id="userIdLinkman" name="apply.userIdLinkman"/>
					</td>
					<td>申请人手机：</td>
					<td>
						<input class="textbox" id="stafPhoneLinkman" name="apply.stafPhoneLinkman"/>
					</td>
				</tr>
				<%
				if (id != null && !"".equals(id) && !"re".equals(f)) {
					%>
					<tr>
						<td>登记人：</td>
						<td>
							<input class="easyui-textbox textbox" id="userNameCreate" name="apply.userNameCreate" readonly="readonly"/>
							<input type="hidden" id="userIdCreate" name="apply.userIdCreate" readonly="readonly"/>
						</td>
						<td>登记时间：</td>
						<td>
							<input class="easyui-textbox textbox" id="timeCreate" name="apply.timeCreate" readonly="readonly"/>
						</td>
					</tr>
					<%
				}
				%>
				<tr>
					<td>备注：</td>
					<td colspan="5">
						<input id="applyMemo" name="apply.applyMemo" class="easyui-textbox" 
							data-options="multiline:true,validType:'maxLength[190]',width:'516'" style="height:50px">
					</td>
				</tr>
			</table>
		</div>
		</form>
<script type="text/javascript">
$(function() {
	$.extend($.fn.validatebox.defaults.rules, {
		phoneRex: {
			validator: function(value){
			var rex=/^1[3-8]+\d{9}$/;
			//var rex=/^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
			//区号：前面一个0，后面跟2-3位数字 ： 0\d{2,3}
			//电话号码：7-8位数字： \d{7,8}
			//分机号：一般都是3位数字： \d{3,}
			 //这样连接起来就是验证电话的正则表达式了：/^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/		 
			var rex2=/^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
			if(rex.test(value)||rex2.test(value))
			{
			  // alert('t'+value);
			  return true;
			}else
			{
			 //alert('false '+value);
			   return false;
			}
			  
			},
			message: '请输入正确电话或手机格式'
		},
		dateRex: {
	        validator: function(value,param){
	        	var d1 = $(param[0]).datetimebox('getValue');
	        	var d2 = $(param[1]).datetimebox('getValue');
	        	
	            if (d1 == undefined || d1 == "") {
					return true;
				}
	            if (d2 == undefined || d2 == "") {
					return true;
				}
	            return d1 <= d2;
	        },
	        message: '还车时间必须在用车时间之后！'
	    }
	});

	/* parent.$.messager.progress({
		text : '数据加载中....'
	}); */
	$('#applyNo').textbox({
		required:true,
		validType: 'maxLength[35]',
		disabled : true
	});
	/* $('#orgName').textbox({
		disabled : true
	}); */
	$('#applyUsedPeople').numberbox({
		min:0,
		required:true
	});
	/* $('#stafPhonePilot').textbox({
		required:true,
		validType:'phoneRex'
	}); */
	$('#stafPhoneLinkman').textbox({
		required:true,
		validType:'phoneRex'
	});
	$('#applyUsingTime').datetimebox({
		required: true,
		editable : false,
		showSeconds: false
	});
	$('#applyRemandTime').datetimebox({
		required: true,
		editable : false,
		showSeconds: false
	});
	$('#dictIdUseCarReson').combobox({
		required:true,
		editable : false,
		width : 516,
		valueField : 'dictId',
		textField : 'dictName',
		url : sys.contextPath + '/maintain/dictionaryManage!doNotNeedSecurity_findAll.cxl',
		queryParams : {
			'QUERY_t#dictFlag_S_EQ' : '<%=WebMsgUtil.YOUXIAO %>',
			'QUERY_t#dictClasId_S_EQ' : 'UseCarReson'
		},
		panelHeight : 'auto',
		panelMaxHeight : '350px'
	});
	/* $('#dictIdDrivingLlicenseKind').textbox({
		required:true,
		disabled : true
	}); */
	if ('<%=f%>' != 'send') {
		$('#orgId').hide();
		$('#orgName').textbox({
			disabled : true
		});
	} else {
		$('#orgName').hide();
		$('#orgId').combotree({
			required:true,
			editable : false,
			panelWidth : 225,
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
				$('#carId').combobox({
					editable : false,
					valueField : 'carId',
					textField : 'carNo',
					url : sys.contextPath + '/car/vstatus!doNotNeedSecurity_findAll.cxl',
					queryParams : {
						'QUERY_t#dictIdFlag_S_EQ' : '<%=WebMsgUtil.YOUXIAO %>',
						'QUERY_t#orgIdUse_S_EQ' : record.id,
						'QUERY_t#dictIdCarStatus_S_EQ' : '<%=WebMsgUtil.CARSTATUS_FREE %>'
					},
					panelHeight : 'auto',
					panelMaxHeight : '350px'
				});
				<%-- $('#userIdPilot').combobox({
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
						$('#stafPhonePilot').textbox('setValue', record.userPhone);
						$('#dictIdDrivingLlicenseKind').combobox('setValue', record.dictIdDrivingLlicenseKind);
					}
				}); --%>
				$('#userIdLinkman').combobox({
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
						$('#stafPhoneLinkman').textbox('setValue', record.userPhone);
					}
				});
				$('#dictIdCarType').combobox('setValue', '');
				$('#dictIdCarType').combobox({
					onSelect : function(r) {
						$('#carId').combobox({
							editable : false,
							valueField : 'carId',
							textField : 'carNo',
							url : sys.contextPath + '/car/vstatus!doNotNeedSecurity_findAll.cxl',
							queryParams : {
								'QUERY_t#dictIdFlag_S_EQ' : '<%=WebMsgUtil.YOUXIAO %>',
								'QUERY_t#orgIdUse_S_EQ' : record.id,
								'QUERY_t#dictIdCarStatus_S_EQ' : '<%=WebMsgUtil.CARSTATUS_FREE %>',
								'QUERY_t#dictIdCarType_S_EQ' : r.dictId
							},
							panelHeight : 'auto',
							panelMaxHeight : '350px'
						});
					}
				});
			},
			onLoadSuccess : function(row, data) {
				parent.$.messager.progress('close');
			}
		});
	}
	
	
	$('#carPoliceUsed').combobox({
		editable : false,
		required:true,
		valueField : 'id',
		textField : 'text',
		panelHeight : 'auto',
		panelMaxHeight : '350px',
		data : [
		{
			id : '警牌',
			text : '警牌'
		},{
			id : '民牌',
			text : '民牌'
		}]
	});
	$('#carId').combobox({
		editable : false,
		valueField : 'carId',
		textField : 'carNo',
		url : sys.contextPath + '/car/vstatus!doNotNeedSecurity_findAll.cxl',
		queryParams : {
			'QUERY_t#dictIdFlag_S_EQ' : '<%=WebMsgUtil.YOUXIAO %>',
			'QUERY_t#orgIdUse_S_EQ' : '<%=sessionInfo.getOrganization().getOrgId() %>',
			'QUERY_t#dictIdCarStatus_S_EQ' : '<%=WebMsgUtil.CARSTATUS_FREE %>'
		},
		panelHeight : 'auto',
		panelMaxHeight : '350px'
	});
	$('#dictIdCarType').combobox({
		required:true,
		editable : false,
		valueField : 'dictId',
		textField : 'dictName',
		url : sys.contextPath + '/maintain/dictionaryManage!doNotNeedSecurity_findAll.cxl',
		queryParams : {
			'QUERY_t#dictFlag_S_EQ' : '<%=WebMsgUtil.YOUXIAO %>',
			'QUERY_t#dictClasId_S_EQ' : 'CarType'
		},
		panelHeight : 'auto',
		panelMaxHeight : '350px',
		onSelect : function(record) {
			$('#carId').combobox({
				editable : false,
				valueField : 'carId',
				textField : 'carNo',
				url : sys.contextPath + '/car/vstatus!doNotNeedSecurity_findAll.cxl',
				queryParams : {
					'QUERY_t#dictIdFlag_S_EQ' : '<%=WebMsgUtil.YOUXIAO %>',
					'QUERY_t#orgIdUse_S_EQ' : '<%=sessionInfo.getOrganization().getOrgId() %>',
					'QUERY_t#dictIdCarStatus_S_EQ' : '<%=WebMsgUtil.CARSTATUS_FREE %>',
					'QUERY_t#dictIdCarType_S_EQ' : record.dictId
				},
				panelHeight : 'auto',
				panelMaxHeight : '350px'
			});
		}
	});
	$('#dictIdRegion').combobox({
		required:true,
		editable : false,
		valueField : 'dictId',
		textField : 'dictName',
		url : sys.contextPath + '/maintain/dictionaryManage!doNotNeedSecurity_findAll.cxl',
		queryParams : {
			'QUERY_t#dictFlag_S_EQ' : '<%=WebMsgUtil.YOUXIAO %>',
			'QUERY_t#dictClasId_S_EQ' : 'Region'
		},
		panelHeight : 'auto',
		panelMaxHeight : '350px'
	});
	$('#dictIdIsSecret').combobox({
		required:true,
		editable : false,
		valueField : 'dictId',
		textField : 'dictName',
		url : sys.contextPath + '/maintain/dictionaryManage!doNotNeedSecurity_findAll.cxl',
		queryParams : {
			'QUERY_t#dictFlag_S_EQ' : '<%=WebMsgUtil.YOUXIAO %>',
			'QUERY_t#dictClasId_S_EQ' : 'IsSecret'
		},
		panelHeight : 'auto',
		panelMaxHeight : '350px'
	});
	<%-- $('#userIdPilot').combobox({
		required:true,
		editable : false,
		valueField : 'userId',
		textField : 'userName',
		url : sys.contextPath + '/maintain/sysUser!doNotNeedSecurity_findVUserList.cxl',
		queryParams : {
			'QUERY_t#dictIdFlag_S_EQ' : '<%=WebMsgUtil.YOUXIAO %>',
			'QUERY_t#orgId_S_EQ' : '<%=sessionInfo.getOrganization().getOrgId() %>'
		},
		panelHeight : 'auto',
		panelMaxHeight : '350px',
		onSelect : function(record) {
			$('#stafPhonePilot').textbox('setValue', record.userPhone);
			$('#dictIdDrivingLlicenseKind').combobox('setValue', record.dictIdDrivingLlicenseKind);
		}
	}); --%>
	<%-- $('#dictIdDrivingLlicenseKind').combobox({
		required:true,
		editable : false,
		disabled : true,
		valueField : 'dictId',
		textField : 'dictName',
		url : sys.contextPath + '/maintain/dictionaryManage!doNotNeedSecurity_findAll.cxl',
		queryParams : {
			'QUERY_t#dictFlag_S_EQ' : '<%=WebMsgUtil.YOUXIAO %>',
			'QUERY_t#dictClasId_S_EQ' : 'DrivingLlicenseKind'
		},
		panelHeight : 'auto',
		panelMaxHeight : '350px'
	}); --%>
	$('#userIdLinkman').combobox({
		required:true,
		editable : false,
		valueField : 'userId',
		textField : 'userName',
		url : sys.contextPath + '/maintain/sysUser!doNotNeedSecurity_findVUserList.cxl',
		queryParams : {
			'QUERY_t#dictIdFlag_S_EQ' : '<%=WebMsgUtil.YOUXIAO %>',
			'QUERY_t#orgId_S_EQ' : '<%=sessionInfo.getOrganization().getOrgId() %>'
		},
		panelHeight : 'auto',
		panelMaxHeight : '350px',
		onSelect : function(record) {
			if (record.userPhone == undefined || record.userPhone == '') {
				return;
			}
			$('#stafPhoneLinkman').textbox('setValue', record.userPhone);
		}
	});

	var id = "<%=id%>";
	if (id != "null" && id.length > 0) {
		$.post(sys.contextPath + '/car/apply!getByViewId.cxl', {
			id : id
		}, function(result) {
			if (result.applyId != undefined) {
				
				if('<%=f%>' == 're') {
					result.preApplyId = result.applyId;
					result.applyId = '';
					result.applyNo = '';
				}
				$('form').form('load', {
					'apply.applyId' : result.applyId,
					'apply.applyNo' : result.applyNo,
					'apply.dictIdCarType' : result.dictIdCarType,
					'apply.orgId' : result.orgId,
					'apply.carId' : result.carId,
					'apply.carPoliceUsed' : result.carPoliceUsed,
					'apply.userIdLinkman' : result.userIdLinkman,
					'apply.stafPhoneLinkman' : result.stafPhoneLinkman,
					'apply.applyUsedPeople' : result.applyUsedPeople,
					'apply.dictIdUseCarReson' : result.dictIdUseCarReson,
					'apply.dictIdRegion' : result.dictIdRegion,
					/* 'apply.userIdPilot' : result.userIdPilot,
					'apply.dictIdDrivingLlicenseKind' : result.dictIdDrivingLlicenseKind,
					'apply.stafPhonePilot' : result.stafPhonePilot, */
					'apply.dictIdIsSecret' : result.dictIdIsSecret,
					'apply.applyMemo' : result.applyMemo,
					'apply.userIdCreate' : result.userIdCreate,
					'apply.userNameCreate' : result.userNameCreate,
					'apply.dictIdCheckStatus' : result.dictIdCheckStatus,
					'apply.timeCreate' : result.timeCreate,
					'apply.applyUsingTime' : result.applyUsingTime,
					'apply.applyRemandTime' : result.applyRemandTime,
					'apply.applyType' : result.applyType,
					'apply.preApplyId' : result.preApplyId,
					'orgName' : result.orgName
				});
				if ('<%=request.getParameter("f")%>' == 'show') {
					$("#applyNo").textbox('disable');
					$("#dictIdCarType").combobox('disable');
					$("#orgId").combotree('disable');
					$("#carId").combobox('disable');
					$("#userIdLinkman").combobox('disable');
					$("#stafPhoneLinkman").textbox('disable');
					$("#applyUsedPeople").textbox('disable');
					$("#dictIdUseCarReson").textbox('disable');
					$("#dictIdRegion").combobox('disable');
					$("#userIdPilot").combobox('disable');
					$("#stafPhonePilot").textbox('disable');
					//$("#dictIdDrivingLlicenseKind").combobox('disable');
					$("#dictIdIsSecret").combobox('disable');
					$("#applyMemo").textbox('disable');
					$("#userIdCreate").combobox('disable');
					$("#dictIdCheckStatus").combobox('disable');
					$("#applyUsingTime").datetimebox('disable');
					$("#applyRemandTime").datetimebox('disable');
				}
			}
			parent.$.messager.progress('close');
		}, 'json');
	} else {
		if ('<%=f%>' != 'send') {
			$('#orgName').textbox('setValue', '<%=sessionInfo.getOrganization().getOrgName()%>');
		}
		/* $("#wfdId").val("");
		$('#tbdiv').attr("hidden","hidden");
		$(".htd").attr("hidden","hidden"); */
		parent.$.messager.progress('close');
	}
	
});

var submitUpdate = function($dialog, $grid, $pjq) {
	if(typeof JSON == 'undefined'){
		$('head').append($("<script type='text/javascript' src='../../public/jslib/json2.js'>"));
	}
	
	if ($("#detailForm").form('validate')) {
		$pjq.messager.progress({
			text : '数据处理中....'
		});
		var params = $("#light input").serialize();
		params += "&apply.applyNo=" + $("#applyNo").textbox('getValue');
		params += "&apply.orgId=" + $("#orgId").val();
		var url = sys.contextPath + '/car/apply!update.cxl';
		$.post(url, params, function(result) {
			$pjq.messager.progress('close');
			if (result.success) {
				$dialog.dialog('destroy');
				$grid.datagrid('reload');
				$pjq.messager.alert('提示','保存成功','info');
			} else {
				$pjq.messager.alert('提示', result.msg, 'error');
			}
		}, 'json');
	}
};

var submitSave = function($dialog, $grid, $pjq) {
	if ($("#detailForm").form('validate')) {
		$pjq.messager.progress({
			text : '数据处理中....'
		});
		var params = $("#light input").serialize();
		var url = sys.contextPath + '/car/apply!save.cxl';
		$.post(url, params, function(result) {
			$pjq.messager.progress('close');
			if (result.success) {
				$dialog.dialog('destroy');
				$grid.datagrid('reload');
				$pjq.messager.alert('提示','保存成功','info');
			} else {
				$pjq.messager.alert('提示','保存失败', 'error');
			}
		}, 'json');
	}
};

var submitReApply = function($dialog, $grid, $pjq) {
	if ($("#detailForm").form('validate')) {
		$pjq.messager.progress({
			text : '数据处理中....'
		});
		var params = $("#light input").serialize();
		var url = sys.contextPath + '/car/apply!save.cxl';
		$.post(url, params, function(result) {
			$pjq.messager.progress('close');
			if (result.success) {
				$dialog.dialog('destroy');
				$grid.datagrid('reload');
				$pjq.messager.alert('提示','保存成功','info');
			} else {
				$pjq.messager.alert('提示','保存失败', 'error');
			}
		}, 'json');
	}
};

var submitSaveWD = function($dialog, $grid, $pjq) {
	if ($("#detailForm").form('validate')) {
		$pjq.messager.progress({
			text : '数据处理中....'
		});
		var params = $("#light input").serialize();
		var url = sys.contextPath + '/car/apply!saveWD.cxl';
		$.post(url, params, function(result) {
			$pjq.messager.progress('close');
			if (result.success) {
				$dialog.dialog('destroy');
				$grid.datagrid('reload');
				$pjq.messager.alert('提示',result.msg, 'info');
			} else {
				$pjq.messager.alert('提示',result.msg, 'error');
			}
		}, 'json');
	}
};

var submitInfoCollection = function($dialog, $grid, $pjq, sendStatusId) {
	if ($("#detailForm").form('validate')) {
		$pjq.messager.progress({
			text : '数据处理中....'
		});
		var params = $("#light input").serialize();
		params += "&id=" + sendStatusId;
		var url = sys.contextPath + '/car/apply!infoCollection.cxl';
		$.post(url, params, function(result) {
			$pjq.messager.progress('close');
			if (result.success) {
				$dialog.dialog('destroy');
				$grid.datagrid('reload');
				$pjq.messager.alert('提示',result.msg, 'info');
			} else {
				$pjq.messager.alert('提示',result.msg, 'error');
			}
		}, 'json');
	}
};
	 
</script>
</body>
</html>