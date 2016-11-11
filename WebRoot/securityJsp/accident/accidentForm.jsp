

<!-- http://localhost:8080/CarManage/securityJsp/accident/AddAccident.jsp -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.framework.util.SecurityUtil"%>
<%@page import="com.framework.util.WebMsgUtil"%>
<%@ page import="java.net.URLDecoder"%>
<%
	String contextPath = request.getContextPath();
	SecurityUtil securityUtil = new SecurityUtil(session);
	
	String id = request.getParameter("id");
	String type = request.getParameter("type");
	
	String str=request.getParameter("carNo");  
	String carNo= URLDecoder.decode(str, "utf-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<style>
.textbox {
	height: 40px;
	width: 200px;
}
</style>
</head>
 <body class="easyui-layout" data-options="fit:true,border:false">
<!-- 
<style type="text/css">
	.table tr{
		height: 30px;
	}
	
	#bttomDiv{
		text-align: center;
		padding-top: 50px;
	}
	#bttomDiv a{
		width: 100px;
	}
	#acciExplain{
		width: 400px;
		height: 60px;
	}
	/* table .tdName{
		text-align:right
	} */
	
	
</style> -->


<form method="post" class="form" id="form">
	
	<input type="hidden" id="accidentId" name="accident.acciId" value="">

	<fieldset>
			<legend>事故基本信息</legend>
			<table class="table" style="width: 100%;">
	
		<tr>
			<td>车牌号:</td>
			<td>
					<input id="carNo" name="accident.carId" class="easyui-validatebox textbox"  />
			</td>
			
			<td>驾驶员:</td>
			<td>
				<input id="stafNames" name="accident.stafName" class="easyui-textbox" data-options="required:true"  />
			</td>
			
		</tr>
		<tr>
			<td >事故时间:</td>
			<td>
				<input id="acciTime" name="accident.acciTime"  class="easyui-textbox" />
			</td>
			
			<td >事故地点:</td>
			<td>
				<input id="acciAddress" name="accident.acciAddress" class="easyui-textbox" data-options="required:true" />
			</td>
		</tr>
		<tr>
			<td >事故状态:</td>
			<td>
							<input name="accident.dictIdAcciState" id="ictIdAcciState" class="easyui-validatebox textbox" />
			</td>
			
			<td >事故类型:</td>
			<td>
				<input id="ictIdAcciType" name="accident.dictIdAcciType" class="easyui-validatebox textbox" />
			</td>
		</tr>
		<tr>
			<td >事故责任:</td>
			<td>
				<input id="dictIdAccidentRisk" name="accident.dictIdAccidentRisk" class="easyui-validatebox textbox" />
			</td>
			
			<td>责任比例:</td>
			<td>
				<input id="acciRiskProportion" name="accident.acciRiskProportion" type="number" />
			</td>
		</tr>
		<tr>
			<td >私人理赔金额:</td>
			<td>
				<input id="acciSelfMoney" name="accident.acciSelfMoney" type="number"/>
			</td>
			
			<td >保险理赔金额:</td>
			<td>
				<input id="acciInsuranceMoney" name="accident.acciInsuranceMoney" type="number"/>
			</td>
		</tr>
		<tr>
			
			
			<td >扣分:</td>
			<td  >
				<input id="acciDeductMark" name="accident.acciDeductMark" type="number" />
			</td>
		</tr>
		
		<tr>
			<td >事故说明:</td>
			<td colspan="3">
			<input id="acciExplain"  name="accident.acciExplain"  class="easyui-textbox" 
							data-options="multiline:true,required:true,validType:'maxLength[400]',width:'525'" style="height:50px"/>
			<!-- 	<textarea id="acciExplain" style="width: 85%"  class="easyui-textbox"  name="accident.acciExplain"  ></textarea> -->
			</td>
		</tr>
		
	</table>
	</fieldset>
</form>


<script type="text/javascript">
var form;

$(function () {
	var id = "<%=id%>";
	var type_ = "<%=type%>";
	form = $("#form");
	//设置日期选择框可以选择到小时
    $("#acciTime").datetimebox({
		editable:false,
		 required:true
	});
	
  	//设置carNo
	 $("#carNo").val("<%=carNo%>");
	
    parent.$.messager.progress({
		text : '数据加载中....'
	});
	$('#carNo').combobox({
		required:true,
		editable : false,
		disabled : true,
		valueField : 'carId',
		textField : 'carNo',
		url : sys.contextPath+'/accident!doNotNeedSecurity_getUsercarList.cxl',
		panelHeight : 'auto',
		panelMaxHeight : '350px',
		
		onLoadSuccess:function(data){
			parent.$.messager.progress('close');
		}
	});
	
	$('#ictIdAcciState').combobox({
		required:true,
		editable : false,
		disabled : false,
		valueField : 'dictId',
		textField : 'dictName',
		url : sys.contextPath+'/maintain/dictionaryManage!doNotNeedSecurity_getByClassId.cxl?dictClassId=AcciState',
		
		panelHeight : 'auto',
		panelMaxHeight : '350px'
	});
	
	//<input id="stafName" name="accident.stafName"
	//class="easyui-validatebox" data-options="required:true,validType:'maxLength[15]'" />
	$('#stafNames').validatebox({
		required:true,
		editable : true,
		validType:'maxLength[15]',
		
		panelHeight : 'auto',
		panelMaxHeight : '350px'
	});
	
	//事故地点
	//<input id="acciAddress" name="accident.acciAddress" class="easyui-validatebox" 
	//data-options="required:true,validType:'maxLength[100]'" />
		$('#acciAddress').validatebox({
		required:true,
		editable : true,
		disabled : false,
		validType:'maxLength[50]',
		
		panelHeight : 'auto',
		panelMaxHeight : '350px'
	});
		//事故类型
		//<input id="dictIdAcciType" name="accident.dictIdAcciType"class="easyui-combobox" data-options="required:true,panelHeight:'auto',editable:false,
			//validType:'maxLength[36]',
			//valueField:'dictId',
			//textField:'dictName',
			//url:sys.contextPath+'/maintain/dictionaryManage!doNotNeedSecurity_getByClassId.cxl?dictClassId=AcciType'" />
	$('#ictIdAcciType').combobox({
		required:true,
		editable : false,
		disabled : false,
		valueField : 'dictId',
		textField : 'dictName',
		url : sys.contextPath+'/maintain/dictionaryManage!doNotNeedSecurity_getByClassId.cxl?dictClassId=AcciType',
		
		panelHeight : 'auto',
		panelMaxHeight : '350px'
	});
	//事故责任
	//<input id="dictIdAccidentRisk" name="accident.dictIdAccidentRisk"class="easyui-combobox" data-options="required:true,panelHeight:'auto',
		//validType:'maxLength[36]',
		//editable:false,
		//valueField:'dictId',
		//textField:'dictName',
		//url:sys.contextPath+'/maintain/dictionaryManage!doNotNeedSecurity_getByClassId.cxl?dictClassId=AccidentRisk'" />
	$('#dictIdAccidentRisk').combobox({
		required:true,
		editable : false,
		disabled : false,
		valueField : 'dictId',
		textField : 'dictName',
		url : sys.contextPath+'/maintain/dictionaryManage!doNotNeedSecurity_getByClassId.cxl?dictClassId=AccidentRisk',
		
		panelHeight : 'auto',
		panelMaxHeight : '350px'
	});
	//责任比例
	//<input id="acciRiskProportion" name="accident.acciRiskProportion" type="number"
	//class="easyui-validatebox" data-options="required:true,validType:'maxLength[2]',
	//prompt:'请填写我方责任百分比'" type="number"/>
	$('#acciRiskProportion').numberbox({
		required:true,
		editable : true,
		disabled : false,
		min:0,
		max:100,
		validType:'maxLength[3]',
		prompt:'请填写我方责任百分比',
		
		panelHeight : 'auto',
		panelMaxHeight : '350px'
	});
	//私人理赔
	//<input id="acciSelfMoney" name="accident.acciSelfMoney" type="number"  class="easyui-validatebox" 
	//data-options="required:true,validType :'maxLength[10]'" />
	$('#acciSelfMoney').numberbox({
		required:true,
		editable : true,
		disabled : false,
		precision:2,
		min:0,
		validType:'maxLength[10]',
		
		panelHeight : 'auto',
		panelMaxHeight : '350px'
	});
	//保险理赔
	//<input id="acciInsuranceMoney" name="accident.acciInsuranceMoney" type="number"  class="easyui-validatebox" 
	//data-options="required:true,validType:'maxLength[10]'" />
	$('#acciInsuranceMoney').numberbox({
		required:true,
		editable : true,
		disabled : false,
		precision:2,
		min:0,
		validType:'maxLength[10]',
		
		panelHeight : 'auto',
		panelMaxHeight : '350px'
	});
	//扣分
	//<input id="acciDeductMark" name="accident.acciDeductMark" class="easyui-validatebox" 
	//data-options="required:true,validType :'maxLength[2]'," type="number" />
	$('#acciDeductMark').numberbox({
		required:true,
		editable : true,
		disabled : false,
		max:12,
		validType:'maxLength[2]',
		
		panelHeight : 'auto',
		panelMaxHeight : '350px'
	});
	//备注
	//<textarea id="acciExplain" style="width: 85%"  name="accident.acciExplain" class="easyui-validatebox"
	//data-options="required:true,validType:'maxLength[100]'" ></textarea>
	$('#acciExplain').validatebox({
		required:true,
		editable : true,
		disabled : false,
		validType:'maxLength[100]',
		
		panelHeight : 'auto',
		panelMaxHeight : '350px'
	});
	
	
	
	
	
	
    
	 if(id != "null"){
	    	$.post(sys.contextPath + '/accident!getById.cxl', {
				id : id
			}, function(result) {
				if (result.acciId != undefined) {
					$('form').form('load', {
						'accident.acciId' : result.acciId,
						/*'accident.carId' : result.carId,*/
						/* 'accident.ownerCompany' : result.ownerCompany, */
						'accident.stafName' : result.stafName,
						'accident.acciAddress' : result.acciAddress,
						'accident.acciTime' : result.acciTime,
						'accident.dictIdAcciType' : result.acciTypeId,
						'accident.dictIdAcciState' : result.acciStateId,
						'accident.dictIdAccidentRisk' : result.acciRiskId,
						'accident.acciRiskProportion' : result.acciRiskProportion,
						'accident.acciInsuranceMoney' : result.acciInsuranceMoney,
						'accident.acciSelfMoney' : result.acciSelfMoney,
						'accident.acciDeductMark' : result.acciDeductMark,
						'accident.acciExplain' : result.acciExplain
					});
				}
			}, 'json');
	    	
	    	
	    	if('show' == type_){
	    		//debugger;
	    		$("#stafNames").validatebox('disable');
	    		$("#carNo").textbox('disable');
	    		$("#ictIdAcciState").combobox('disable');
	    		$("#ictIdAcciType").combobox('disable');
	    		$("#dictIdAccidentRisk").combobox('disable');
	    		$("#acciTime").datetimebox('disable');
	    		
	    		$("#acciAddress").validatebox('disable');
	    		$("#acciRiskProportion").numberbox('disable');
	    		$("#acciSelfMoney").numberbox('disable');
	    		$("#acciInsuranceMoney").numberbox('disable');
	    		$("#acciDeductMark").numberbox('disable');
	    		$("#acciExplain").validatebox('disable');
	    		
	    		
	    		
	    		
	    		
	    		
	    	}
	    	
	    	
	    }
	    
    
    
    
    
    
});



/* function save() {
	var param = form.serialize();
	//console.info(param);
	
	if(!form.form('validate')){
		return false;
	}
	
	var url = sys.contextPath + '/accident!save.cxl';
	$.post(url,param,function(result){
		//console.info(result);
		if(!result.success){
			$.messager.alert('提示',result.msg); 
		}else{
			$.messager.alert('提示','添加成功!'); 
			reset();
		}
	},'json');
		
	
} */


</script>



</body>
</html>