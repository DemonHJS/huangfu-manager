package cn.huangfu.system.filter;

import javax.servlet.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * 敏感词汇过滤器
 */
//@WebFilter("/*")
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

        ServletRequest proxy_req = (ServletRequest)Proxy.newProxyInstance(req.getClass().getClassLoader()
                , req.getClass().getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //判断是否是getParameter方法
                        if(method.getName().equals("getParameter")){
                            //反射执行方法
                            String value = (String) method.invoke(proxy, args);
                            //过滤替换
                            for (String s : list) {
                                if(value.contains(s)) {
                                    value = value.replaceAll(s, "***");
                                }
                            }
                            return value;
                        }
                        return method.invoke(proxy, args);
                    }
                });

        //2.放行
        chain.doFilter(proxy_req, resp);
    }


    /**
     * 敏感词汇集合
     */
    private List<String> list = new ArrayList<>();

    /**
     * 服务器启动时调用，初始化
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig config) throws ServletException {
        //读取配置敏感词汇文件
        BufferedReader br = null;
        try {
            br = new BufferedReader(
                    new FileReader(SensitiveWordsFilter.class.getClassLoader().getResource("sensitiveWords.xml").getPath()));
            String s;
            while((s= br.readLine())!=null){
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(br !=null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public void destroy() {

    }

}
