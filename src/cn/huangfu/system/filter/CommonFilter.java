package cn.huangfu.system.filter;

import cn.huangfu.common.bean.WebResult;
import cn.huangfu.common.constant.Constants;
import cn.huangfu.common.util.JacksonUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @Author: HuangJiaSheng
 * @Date: 2019/11/12 16:49
 * @Description:
 *
 * 过滤器
 */
@WebFilter("/*")
public class CommonFilter implements Filter {


    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //设置请求字符集
        req.setCharacterEncoding(Constants.UTF8);
        //设置客户端接收内容和编码格式
        resp.setContentType("text/html;charset=utf-8");
        //放行
        chain.doFilter(req, resp);

        //获取响应数据，定义公共请求返回数据
        WebResult webResult = (WebResult) req.getAttribute("webResult");
        if(webResult!=null){
            resp.getWriter().write(JacksonUtils.toJson(webResult));
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

    @Override
    public void destroy() {
    }

}
