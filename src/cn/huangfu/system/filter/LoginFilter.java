package cn.huangfu.system.filter;

import cn.huangfu.system.entity.SysUser;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author: HuangJiaSheng
 * @Date: 2019/11/12 16:49
 * @Description:
 *
 * 登录验证过滤器
 *  1. 案例1_登录验证
 * 		* 需求：
 * 			1. 访问day17_case案例的资源。验证其是否登录
 * 			2. 如果登录了，则直接放行。
 * 			3. 如果没有登录，则跳转到登录页面，提示"您尚未登录，请先登录"。
 */
//@WebFilter("/*")
public class LoginFilter implements Filter {


    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //0.强制转换
        HttpServletRequest request = (HttpServletRequest) req;
        //获取Session对象
        HttpSession session = ((HttpServletRequest) req).getSession();

        //获取请求虚拟资源路径
        String servletPath = request.getServletPath();
        //判断是否是登录资源
        if(servletPath.contains("/login.jsp") || servletPath.contains("/loginServlet")
                || servletPath.contains("/css/") || servletPath.contains("/js/")
                || servletPath.contains("/fonts/") || servletPath.contains("/checkCodeServlet") ){
            chain.doFilter(req, resp);
        }else {
            //获取user对象
            SysUser user = (SysUser) session.getAttribute("user");
            //用户对象是否为空
            if (user == null) {
                request.setAttribute("login_msg", "您尚未登录，请登录");
                //转发到登录页面
                request.getRequestDispatcher("/login.jsp").forward(request, resp);
            }else {
                chain.doFilter(req, resp);
            }
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

    @Override
    public void destroy() {
    }

}
