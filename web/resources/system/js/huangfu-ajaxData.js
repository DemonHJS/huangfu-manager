/***
 *
 * @param url 请求地址
 * @param functionName 成功执行方法
 * @param jsonData 传入数据
 * @param isAsync 同步或异步 默认false
 * @param errorFunc 失败自定义操作
 */

//不跨域get请求
function getJsonAjaxData(url, functionName, jsonData, isAsync,errorFunc) {
    sendNetWord(url, functionName, jsonData, "GET",false,isAsync,errorFunc);
}
//不跨域post请求
function postJsonAjaxData(url, functionName, jsonData, isAsync,errorFunc) {
    sendNetWord(url, functionName, jsonData, "POST",false,isAsync,errorFunc);
}

//跨域get请求
function getJsonpAjaxData(url, functionName, jsonData, isAsync,errorFunc) {
    sendNetWord(url, functionName, jsonData, "GET",true,isAsync,errorFunc);
}
//跨域post请求
function postJsonpAjaxdata(url, functionName, jsonData, isAsync,errorFunc) {
    sendNetWord(url, functionName, jsonData, "POST", true,isAsync,errorFunc)
}

//var actBaseUrl = getRootPathAjax();
function sendNetWord(url, functionName, jsonData, typeStr, isSameDomain,isAsync,errorFunc) {
    var dataTypeStr = 'json';
    var jsonpStr = '';
    var asyncBool=true;
    //判断是否跨域请求
    if (isSameDomain) {
        dataTypeStr = "jsonp";
        jsonpStr = "jsoncallback";
    }
    if (!isAsync) {
        asyncBool=false;
    }

    $.ajax({
        async: asyncBool,
        url: url,
        type: typeStr,
        dataType: dataTypeStr,
        //jsonp的值自定义,如果使用jsoncallback,那么服务器端,要返回一个jsoncallback的值对应的对象. 
        jsonp: jsonpStr,
        //要传递的参数,没有传参时，也一定要写上 
        data: jsonData,
        //timeout: 20000,
        //返回Json类型 
        //contentType: "application/json;utf-8",
        //服务器段返回的对象包含name,data属性. 
        success: function (result) {
            if (result.success || result.code === 0) {
                functionName(result.data,result.msg);
            }else{
                if(result.code===9){
                    top.layer.confirm(result.msg,{
                        btn: ['确定']
                    },function (index, layero) {
                         top.layer.close(index);
                         window.location.href=baseUrl+"/login";
                        }
                    );
                }else{
                    if(typeof errorFunc === 'undefined' || errorFunc == undefined ){
                        top.layer.msg("请求数据返回数据:"+result.msg);
                    }else{
                        try {
                            errorFunc(result);
                        }
                        catch(err){
                            top.layer.msg("请求数据返回数据:"+result.msg);
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

/**
 * 获取项目名称
 * @returns
 */
function getRootPathAjax() {
    //获取当前网址，如： http://localhost:8088/test/test.jsp
    var curPath = window.document.location.href;
    //获取主机地址之后的目录，如： test/test.jsp
    var pathName = window.document.location.pathname;
    var pos = curPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8088
    var localhostPaht = curPath.substring(0, pos);
    //获取带"/"的项目名，如：/test
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return (localhostPaht + projectName);
}
