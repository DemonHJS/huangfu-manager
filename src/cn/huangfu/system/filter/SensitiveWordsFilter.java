package cn.huangfu.system.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Map;

/**
 * 敏感词汇过滤器
 */
@WebFilter("/*")
public class SensitiveWordsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //1.创建ServletRequest动态代理,增强对象
        /*
            三个参数：
                1. 类加载器：真实对象.getClass().getClassLoader()
                2. 接口数组：真实对象.getClass().getInterfaces()
                3. 处理器：new InvocationHandler()
         */
        //获取域对象系统配置数据
        Map<String,Object> smap = (Map<String, Object>) req.getServletContext().getAttribute("systemConfig");
        List<String> list = (List<String>) smap.get("systemConfig");
        if(list !=null && list.size()>0) {
            ServletRequest proxy_req = (ServletRequest) Proxy.newProxyInstance(req.getClass().getClassLoader()
                    , req.getClass().getInterfaces(), (proxy, method, args) -> {
                        //判断是否是getParameter方法
                        if ("getParameter".equals(method.getName())) {
                            //反射执行方法
                            String value = (String) method.invoke(proxy, args);
                            //过滤替换
                            for (String s : list) {
                                if (value.contains(s)) {
                                    value = value.replaceAll(s, "***");
                                }
                            }
                            return value;
                        }
                        return method.invoke(proxy, args);
                    });
            //2.放行
            chain.doFilter(proxy_req, resp);
        }else {
            chain.doFilter(req, resp);
        }

    }



    /**
     * 服务器启动时调用，初始化
     * @param config
     */
    @Override
    public void init(FilterConfig config) {

    }

    @Override
    public void destroy() {

    }

}
