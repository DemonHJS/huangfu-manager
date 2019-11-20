package cn.huangfu.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.Map;

/**
 * @Author: 黄家胜
 * @Date: 2019/3/24 10:30
 * @version: ${version}
 * @Description:
 */
public class JsoupUtils {

    public static final Log log = LogFactory.getLog(JsoupUtils.class);


    /**
     * Post请求
     * @param url 请求地址
     * @param params 请求参数
     * @param encode 编码格式
     * @param timeout 请求超时时间，可为空
     * @return
     */
    public static String sendPost(String url, Map<String,String> params, String encode,int timeout){
        String res;
        try {
            log.info(url);
            if(StringsUtils.empty(timeout)){
                timeout = 8000;
            }
            Response response= Jsoup.connect(url).data(params).ignoreContentType(true).postDataCharset(encode).timeout(timeout).method(Method.POST).execute();
            res = new String(response.bodyAsBytes(),encode);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return res;
    }

    /**
     * Get请求
     * @param url 请求地址
     * @param params 请求参数
     * @param encode 编码格式
     * @param timeout 请求超时时间，可为空
     * @return
     */
    public static String sendGet(String url,Map<String,String> params,String encode,int timeout){
        String res;
        try {
            log.info(url);
            if(StringsUtils.empty(timeout)){
                timeout = 8000;
            }
            Response response= Jsoup.connect(url).data(params).ignoreContentType(true).postDataCharset(encode).timeout(timeout).method(Method.GET).execute();
            res = new String(response.bodyAsBytes(),encode);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return res;
    }

}
