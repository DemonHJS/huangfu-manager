/**
 * 增强JQeury对象自身的功能  $/jQuery
 */
var hf_extend = $.extend({
    /**
     * 给地址智能加上随机数
     * @param url
     * @returns
     */
    hf_randomUrl : function(url){
        if (url.indexOf("?") > 0) {
            //包含？ 说明是这种格式的/yyglAction.do?method=getYy     /#/g这种写法标识替换所有#，g表示全局的意思，全局搜索。
            return encodeURI(url + "&randomUrl&=" + Math.random()).replace(/#/g, "%23");
        } else {
            return encodeURI(url + "?randomUrl=" + Math.random()).replace(/#/g, "%23");
        }
    }
    /**
     * 获取项目Http路径
     * @returns
     */
    ,hf_getRootPath : function(){
        //获取当前网址，如： http://localhost:8088/test/test.jsp
        var curPath = window.document.location.href;
        //获取主机地址之后的目录，如： test/test.jsp
        var pathName = window.document.location.pathname;
        var pos = curPath.indexOf(pathName);
        //获取主机地址，如： http://localhost:8088
        var localhostPath = curPath.substring(0, pos);
        //获取带"/"的项目名，如：/test
        var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
        return (localhostPath + projectName);
    }
    /**
     * 是否为空
     * @param str
     * @returns {Boolean}
     */
    ,hf_isEmpty : function(str){
        if (str === null || str === "" || str === undefined || str === "null" || str === 'undefined' || str.toString().length === 0) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * 比较两个字符是否相等
     * @param first
     * @param second
     * @returns {boolean}
     */
    ,hf_equals : function (first, second) {
        if (first == null && second == null) {
            return true;
        }
        if (first == null || second == null) {
            return false;
        }
        return first.toString() === second.toString();
    }
    /**
     * 判断当前浏览类型
     * @returns {String}
     */
    ,hf_browserType : function () {
        var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
        var isOpera = userAgent.indexOf("Opera") > -1; //判断是否Opera浏览器
        var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera; //判断是否IE<11浏览器
        var isEdge = userAgent.indexOf("Windows NT 6.1; Trident/7.0;") > -1 && !isIE; //判断是否IE的Edge浏览器
        var isFF = userAgent.indexOf("Firefox") > -1; //判断是否Firefox浏览器
        var isSafari = userAgent.indexOf("Safari") > -1 && userAgent.indexOf("Chrome") == -1; //判断是否Safari浏览器
        var isChrome = userAgent.indexOf("Chrome") > -1 && userAgent.indexOf("Safari") > -1; //判断Chrome浏览器
        var isIE11 = userAgent.indexOf("Trident") > -1 && userAgent.indexOf("rv:11.0") > -1;//判断IE11浏览器
        if (isIE) {
            var reIE = new RegExp("MSIE (\\d+\\.\\d+);");
            reIE.test(userAgent);
            var fIEVersion = parseFloat(RegExp["$1"]);
            if (fIEVersion == 7) {
                return "IE7";
            } else if (fIEVersion == 8) {
                return "IE8";
            } else if (fIEVersion == 9) {
                return "IE9";
            } else if (fIEVersion == 10) {
                return "IE10";
            } else {
                return "0"
            }//IE版本过低
        }//isIE end
        if (isIE11) {
            return "IE11";
        }
        if (isFF) {
            return "FF";
        }
        if (isOpera) {
            return "Opera";
        }
        if (isSafari) {
            return "Safari";
        }
        if (isChrome) {
            return "Chrome";
        }
        if (isEdge) {
            return "Edge";
        }
    }
    /**
     * 时间格式化
     * @param fmt
     * @returns {*}
     * @constructor
     */
    ,hf_dateFormat : function (fmt) {
        var o = {
            "M+": this.getMonth() + 1,                 //月份
            "d+": this.getDate(),                    //日
            "h+": this.getHours(),                   //小时
            "m+": this.getMinutes(),                 //分
            "s+": this.getSeconds(),                 //秒
            "q+": Math.floor((this.getMonth() + 3) / 3), //季度
            "S": this.getMilliseconds()             //毫秒
        };
        if (/(y+)/.test(fmt))
            fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt))
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    }
    /**
     * 拼接参数
     * @param param json对象
     * @returns {string}
     */
    ,hf_concatParams : function (param) {
        var i = 0,
            url = '';
        for (var key in param) {
            if (isEmpty(param[key])) {
                continue;
            }
            if (i === 0) {
                url += '?' + key + '=' + param[key];
            } else {
                url += '&' + key + '=' + param[key];
            }
            i++;
        }
        return url;
    }
    ,hf_ajaxData: function(url, jsonData ,typeStr ,isAsync , dataTypeStr,successFunc,errorFunc) {
        if(hf_extend.hf_isEmpty(isAsync)){
            isAsync = true;
        }
        if(hf_extend.hf_isEmpty(typeStr)){
            typeStr = "POST";
        }
        if(hf_extend.hf_isEmpty(dataTypeStr)){
            dataTypeStr = "json";
        }
        if(hf_extend.hf_isEmpty(jsonData)){
            jsonData = {};
        }
        /*var jsonpStr = '';
        //判断是否跨域请求
        if (isSameDomain) {
            dataTypeStr = "jsonp";
            jsonpStr = "jsoncallback";
        }*/
        $.ajax({
            async: isAsync,
            url: url,
            type: typeStr,
            dataType: dataTypeStr,
            //jsonp的值自定义,如果使用jsoncallback,那么服务器端,要返回一个jsoncallback的值对应的对象.
            /*jsonp: jsonpStr,*/
            //要传递的参数,没有传参时，也一定要写上
            data: jsonData,
            //timeout: 20000,
            //服务器段返回的对象包含name,data属性.
            success: function (result) {
                if (result.success || result.code === 1) {
                    successFunc(result);
                }else{
                    if(result.code===3){
                        top.layer.confirm(result.msg,{
                                btn: ['确定']
                            },function (index, layero) {
                                top.layer.close(index);
                                window.location.href=baseUrl+"/login.jsp";
                            }
                        );
                    }else{
                        if(typeof errorFunc === 'undefined' || errorFunc == undefined ){
                            top.layer.msg(result.msg);
                        }else{
                            try {
                                errorFunc(result);
                            }
                            catch(err){
                                top.layer.msg(result.msg);
                            }
                        }
                    }
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                try{
                    top.layer.msg("请求数据失败,失败原因："+jqXHR.responseJSON.msg);
                }catch (e) {
                    top.layer.msg("请求数据失败,失败原因："+jqXHR.statusText);
                }

            }
        });
    }
    ,hf_ajaxData_post:function (url, jsonData ,isAsync , dataTypeStr,successFunc,errorFunc) {
        hf_extend.hf_ajaxData(url, jsonData ,"POST" ,isAsync , dataTypeStr,successFunc,errorFunc);
    }
    ,hf_ajaxData_get:function (url, jsonData ,isAsync , dataTypeStr,successFunc,errorFunc) {
        hf_extend.hf_ajaxData(url, jsonData ,"GET" ,isAsync , dataTypeStr,successFunc,errorFunc);
    }
});