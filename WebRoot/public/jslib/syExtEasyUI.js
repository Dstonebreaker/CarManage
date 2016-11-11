var sys = sys || {};

/**
 * 更改easyui加载panel时的提示文字
 * 
 * @author 陈晓亮
 * 
 * @requires jQuery,EasyUI
 */
$.extend($.fn.panel.defaults, {
	loadingMessage : '加载中....'
});

/**
 * 更改easyui加载grid时的提示文字
 * 
 * @author 陈晓亮
 * 
 * @requires jQuery,EasyUI
 */
$.extend($.fn.datagrid.defaults, {
	loadMsg : '数据加载中....'
});

/**
 * panel关闭时回收内存，主要用于layout使用iframe嵌入网页时的内存泄漏问题
 * 
 * @author 陈晓亮
 * 
 * @requires jQuery,EasyUI
 * 
 */
$.extend($.fn.panel.defaults, {
	onBeforeDestroy : function() {
		var frame = $('iframe', this);
		try {
			if (frame.length > 0) {
				for (var i = 0; i < frame.length; i++) {
					frame[i].src = '';
					frame[i].contentWindow.document.write('');
					frame[i].contentWindow.close();
				}
				frame.remove();
				if (navigator.userAgent.indexOf("MSIE") > 0) {// IE特有回收内存方法
					try {
						CollectGarbage();
					} catch (e) {
					}
				}
			}
		} catch (e) {
		}
	}
});

/**
 * 防止panel/window/dialog组件超出浏览器边界
 * 
 * @author 陈晓亮
 * 
 * @requires jQuery,EasyUI
 */
sys.onMove = {
	onMove : function(left, top) {
		var l = left;
		var t = top;
		if (l < 1) {
			l = 1;
		}
		if (t < 1) {
			t = 1;
		}
		var width = parseInt($(this).parent().css('width')) + 14;
		var height = parseInt($(this).parent().css('height')) + 14;
		var right = l + width;
		var buttom = t + height;
		var browserWidth = $(window).width();
		var browserHeight = $(window).height();
		if (right > browserWidth) {
			l = browserWidth - width;
		}
		if (buttom > browserHeight) {
			t = browserHeight - height;
		}
		$(this).parent().css({/* 修正面板位置 */
			left : l,
			top : t
		});
	}
};
$.extend($.fn.dialog.defaults, sys.onMove);
$.extend($.fn.window.defaults, sys.onMove);
$.extend($.fn.panel.defaults, sys.onMove);

/**
 * 
 * 通用错误提示
 * 
 * 用于datagrid/treegrid/tree/combogrid/combobox/form加载数据出错时的操作
 * 
 * @author 陈晓亮
 * 
 * @requires jQuery,EasyUI
 */
sys.onLoadError = {
	onLoadError : function(XMLHttpRequest) {
		if (parent.$ && parent.$.messager) {
			parent.$.messager.progress('close');
			parent.$.messager.alert('错误', XMLHttpRequest.responseText);
		} else {
			$.messager.progress('close');
			$.messager.alert('错误', XMLHttpRequest.responseText);
		}
	}
};
$.extend($.fn.datagrid.defaults, sys.onLoadError);
$.extend($.fn.treegrid.defaults, sys.onLoadError);
$.extend($.fn.tree.defaults, sys.onLoadError);
$.extend($.fn.combogrid.defaults, sys.onLoadError);
$.extend($.fn.combobox.defaults, sys.onLoadError);
$.extend($.fn.form.defaults, sys.onLoadError);

/**
 * 扩展combobox在自动补全模式时，检查用户输入的字符是否存在于下拉框中，如果不存在则清空用户输入
 * 
 * @author 陈晓亮
 * 
 * @requires jQuery,EasyUI
 */
$.extend($.fn.combobox.defaults, {
	onShowPanel : function() {
		var _options = $(this).combobox('options');
		if (_options.mode == 'remote') {/* 如果是自动补全模式 */
			var _value = $(this).combobox('textbox').val();
			var _combobox = $(this);
			if (_value.length > 0) {
				$.post(_options.url, {
					q : _value
				}, function(result) {
					if (result && result.length > 0) {
						_combobox.combobox('loadData', result);
					}
				}, 'json');
			}
		}
	},
	onHidePanel : function() {
		var _options = $(this).combobox('options');
		if (_options.mode == 'remote') {/* 如果是自动补全模式 */
			var _data = $(this).combobox('getData');/* 下拉框所有选项 */
			var _value = $(this).combobox('getValue');/* 用户输入的值 */
			var _b = false;/* 标识是否在下拉列表中找到了用户输入的字符 */
			for (var i = 0; i < _data.length; i++) {
				if (_data[i][_options.valueField] == _value) {
					_b = true;
				}
			}
			if (!_b) {/* 如果在下拉列表中没找到用户输入的字符 */
				$(this).combobox('setValue', '');
			}
		}
	}
});

/**
 * 扩展combogrid在自动补全模式时，检查用户输入的字符是否存在于下拉框中，如果不存在则清空用户输入
 * 
 * @author 陈晓亮
 * 
 * @requires jQuery,EasyUI
 */
$.extend($.fn.combogrid.defaults, {
	onShowPanel : function() {
		var _options = $(this).combogrid('options');
		if (_options.mode == 'remote') {/* 如果是自动补全模式 */
			var _value = $(this).combogrid('textbox').val();
			if (_value.length > 0) {
				$(this).combogrid('grid').datagrid("load", {
					q : _value
				});
			}
		}
	},
	onHidePanel : function() {
		var _options = $(this).combogrid('options');
		if (_options.mode == 'remote') {/* 如果是自动补全模式 */
			var _data = $(this).combogrid('grid').datagrid('getData').rows;/* 下拉框所有选项 */
			var _value = $(this).combogrid('getValue');/* 用户输入的值 */
			var _b = false;/* 标识是否在下拉列表中找到了用户输入的字符 */
			for (var i = 0; i < _data.length; i++) {
				if (_data[i][_options.idField] == _value) {
					_b = true;
				}
			}
			if (!_b) {/* 如果在下拉列表中没找到用户输入的字符 */
				$(this).combogrid('setValue', '');
			}
		}
	}
});

/**
 * 扩展validatebox，添加新的验证功能
 * 
 * @author 陈晓亮
 * 
 * @requires jQuery,EasyUI
 */
$.extend($.fn.validatebox.defaults.rules, {
	// 判断最小长度
	minLength : {
        validator : function(value, param) {
            return value.length >= param[0];
        },
        message : '最少输入{0}个字符'
    },
    // 验证可以输入的最大字符数
    maxLength:{
		validator : function(value,param){
			return value.length <= param[0];
			
		},
		message : '最多只能输入{0}个字符'
	},
	// 验证电话号码
	phone : {
        validator : function(value) {
        	var reg = /^\d{3,4}-?\d{7,8}$/;
        	return reg.test(value);
        },
        message : "格式不正确,请使用下面格式:020-88888888"
    },
    // 手机号码验证
    mobile:{
    	validator:function(value){
    		var reg = /^(((13[0-9]{1})|(14[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;     
    		return value.length == 11 && reg.test(value);
    	},
    	message:"请正确填写您的手机号码."
    },
    // 验证中文
    CHS:{
    	validator:function(value){
    		return /^[\u0391-\uFFE5]+$/.test(value);
    	},
    	message:"只能输入汉字."
    },
    // 验证英文
    englishCheckSub:{
    	validator:function(value){
    		return /^[a-zA-Z0-9]+$/.test(value);
    	},
    	message:"只能包括英文字母、数字."
    },
    // 身份证号码验证
    idCardNo:{
    	validator:function(value){
    		return isIdCardNo(value); 
    	},
    	message:"请正确输入您的身份证号码."
    },
    // 验证两次密码是否一致功能
	eqPwd : {
		validator : function(value, param) {
			return value == $(param[0]).val();
		},
		message : '密码不一致！'
	}
    ,
 // 验证整数或小数
    intOrFloat: {
        validator: function (value) {
            return /^\d+(\.\d+)?$/i.test(value);
        },
        message: '请输入数字，并确保格式正确'
    },
	//验证数据是否为空
    valueNN:{
		validator:function(value){
			if(value.trim().length==0){
				return false;
			}
			return true;
		},
		message:"请输入不为空格的数据"
	},
    //邮箱验证
    email:{
    	validator:function(value){
    		if (value.length <= 128) {
    			return 	/^[a-zA-Z0-9_+.-]+\@([a-zA-Z0-9-]+\.)+[a-zA-Z0-9]{2,4}$/.test(value);
    		} else {
    			return false;
    		}    		
    	},
    	message:"请正确填写您的邮箱地址.最多只能输入128个字符！"
    },
    decimal:{
    	validator:function(value,param){
    		
    		if(param[0]<=0)
    			return /^[0-9]{1,9}$/.test(value);
    		else if(param[0]==1)
    		return /^([0-9]{1,9})+\.+([0-9]{0,1})$/.test(value);
    		else if(param[0]==2)
        		return /^([0-9]{1,9})+\.+([0-9]{0,2})$/.test(value);
    		else if(param[0]==3)
        		return /^([0-9]{1,9})+\.+([0-9]{0,3})$/.test(value);
    		else if(param[0]==4)
        		return /^([0-9]{1,9})+\.+([0-9]{0,4})$/.test(value);
    		else if(param[0]==5)
        		return /^([0-9]{1,9})+\.+([0-9]{0,5})$/.test(value);
    		
    	}
    ,
	message : '最多只能输入{0}位小数！'
    }
});

/**
 * 扩展tree和combotree，使其支持平滑数据格式
 * 
 * @author 陈晓亮
 * 
 * @requires jQuery,EasyUI
 * 
 */
sys.loadFilter = {
	loadFilter : function(data, parent) {
		var opt = $(this).data().tree.options;
		var idField, textField, parentField;
		if (opt.parentField) {
			idField = opt.idField || 'id';
			textField = opt.textField || 'text';
			parentField = opt.parentField || 'pid';
			var i, l, treeData = [], tmpMap = [];
			for (i = 0, l = data.length; i < l; i++) {
				tmpMap[data[i][idField]] = data[i];
			}
			for (i = 0, l = data.length; i < l; i++) {
				if (tmpMap[data[i][parentField]] && data[i][idField] != data[i][parentField]) {
					if (!tmpMap[data[i][parentField]]['children'])
						tmpMap[data[i][parentField]]['children'] = [];
					data[i]['text'] = data[i][textField];
					tmpMap[data[i][parentField]]['children'].push(data[i]);
				} else {
					data[i]['text'] = data[i][textField];
					treeData.push(data[i]);
				}
			}
			return treeData;
		}
		return data;
	}
};
$.extend($.fn.combotree.defaults, sys.loadFilter);
$.extend($.fn.tree.defaults, sys.loadFilter);

/**
 * 扩展treegrid，使其支持平滑数据格式
 * 
 * @author 陈晓亮
 * 
 * @requires jQuery,EasyUI
 * 
 */
$.extend($.fn.treegrid.defaults, {
	loadFilter : function(data, parentId) {
		var opt = $(this).data().treegrid.options;
		var idField, treeField, parentField;
		if (opt.parentField) {
			idField = opt.idField || 'id';
			treeField = opt.textField || 'text';
			parentField = opt.parentField || 'pid';
			var i, l, treeData = [], tmpMap = [];
			for (i = 0, l = data.length; i < l; i++) {
				tmpMap[data[i][idField]] = data[i];
			}
			for (i = 0, l = data.length; i < l; i++) {
				if (tmpMap[data[i][parentField]] && data[i][idField] != data[i][parentField]) {
					if (!tmpMap[data[i][parentField]]['children'])
						tmpMap[data[i][parentField]]['children'] = [];
					data[i]['text'] = data[i][treeField];
					tmpMap[data[i][parentField]]['children'].push(data[i]);
				} else {
					data[i]['text'] = data[i][treeField];
					treeData.push(data[i]);
				}
			}
			return treeData;
		}
		return data;
	}
});

/**
 * 创建一个模式化的dialog
 * 
 * @author 陈晓亮
 * 
 * @requires jQuery,EasyUI
 * 
 */
sys.modalDialog = function(options) {
	var opts = $.extend({
		title : '&nbsp;',
		width : 640,
		height : 480,
		modal : true,
		onClose : function() {
			$(this).dialog('destroy');
		}
	}, options);
	opts.modal = true;// 强制此dialog为模式化，无视传递过来的modal参数
	if (options.url) {
		opts.content = '<iframe id="" src="' + options.url + '" allowTransparency="true" scrolling="auto" width="100%" height="98%" frameBorder="0" name=""></iframe>';
	}
	return $('<div/>').dialog(opts);
};


/**
 * 创建一个模式化的dialog
 * 
 * @author 
 * 
 * @requires jQuery,EasyUI
 * 
 */
sys.modalDialogTwo = function(options) {
	var opts = $.extend({
		title : '&nbsp;',
		modal : true,
		onClose : function() {
			$(this).dialog('destroy');
		}
	}, options);
	opts.modal = true;// 强制此dialog为模式化，无视传递过来的modal参数
	if (options.url) {
		opts.content = '<iframe id="" src="' + options.url + '" allowTransparency="true" scrolling="auto" width="100%" height="98%" frameBorder="0" name=""></iframe>';
	}
	return $('<div/>').dialog(opts);
};

/**
 * 创建一个模式化的dialog
 * 
 * @author 
 * 
 * @requires jQuery,EasyUI
 * 
 */
sys.modalDialogNew = function(options) {
	
	options.modal = true;
	return $('<div/>').dialog(options);
};


/**
 * 更换主题
 * 
 * @author 陈晓亮
 * @requires jQuery,EasyUI
 * @param themeName
 */
sys.changeTheme = function(themeName) {
	var $easyuiTheme = $('#easyuiTheme');
	var url = $easyuiTheme.attr('href');
	var href = url.substring(0, url.indexOf('themes')) + 'themes/' + themeName + '/easyui.css';
	$easyuiTheme.attr('href', href);

	var $iframe = $('iframe');
	if ($iframe.length > 0) {
		for (var i = 0; i < $iframe.length; i++) {
			var ifr = $iframe[i];
			try {
				$(ifr).contents().find('#easyuiTheme').attr('href', href);
			} catch (e) {
				try {
					ifr.contentWindow.document.getElementById('easyuiTheme').href = href;
				} catch (e) {
				}
			}
		}
	}

	sys.cookie('easyuiTheme', themeName, {
		expires : 7
	});
};

/**
 * 滚动条
 * 
 * @author 陈晓亮
 * @requires jQuery,EasyUI
 */
sys.progressBar = function(options) {
	if (typeof options == 'string') {
		if (options == 'close') {
			$('#syProgressBarDiv').dialog('destroy');
		}
	} else {
		if ($('#syProgressBarDiv').length < 1) {
			var opts = $.extend({
				title : '&nbsp;',
				closable : false,
				width : 300,
				height : 60,
				modal : true,
				content : '<div id="syProgressBar" class="easyui-progressbar" data-options="value:0"></div>'
			}, options);
			$('<div id="syProgressBarDiv"/>').dialog(opts);
			$.parser.parse('#syProgressBarDiv');
		} else {
			$('#syProgressBarDiv').dialog('open');
		}
		if (options.value) {
			$('#syProgressBar').progressbar('setValue', options.value);
		}
	}
};