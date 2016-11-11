var sys = sys || {};

/**
 * 去字符串空格
 * 
 * @author 陈晓亮
 */
sys.trim = function(str) {
	return str.replace(/(^\s*)|(\s*$)/g, '');
};
sys.ltrim = function(str) {
	return str.replace(/(^\s*)/g, '');
};
sys.rtrim = function(str) {
	return str.replace(/(\s*$)/g, '');
};

/**
 * 判断开始字符是否是XX
 * 
 * @author 陈晓亮
 */
sys.startWith = function(source, str) {
	var reg = new RegExp("^" + str);
	return reg.test(source);
};
/**
 * 判断结束字符是否是XX
 * 
 * @author 陈晓亮
 */
sys.endWith = function(source, str) {
	var reg = new RegExp(str + "$");
	return reg.test(source);
};

/**
 * iframe自适应高度
 * 
 * @author 陈晓亮
 * 
 * @param iframe
 */
sys.autoIframeHeight = function(iframe) {
	iframe.style.height = iframe.contentWindow.document.body.scrollHeight + "px";
};

/**
 * 设置iframe高度
 * 
 * @author 陈晓亮
 * 
 * @param iframe
 */
sys.setIframeHeight = function(iframe, height) {
	iframe.height = height;
};