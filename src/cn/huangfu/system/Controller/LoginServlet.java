package cn.huangfu.system.Controller;

import cn.huangfu.common.bean.WebResult;
import cn.huangfu.common.constant.Constants;
import cn.huangfu.system.entity.SysUser;
import cn.huangfu.system.service.SysUserService;
import cn.huangfu.system.service.impl.SysUserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @Author: HuangJiaSheng
 * @Date: 2019/11/14 21:11
 * @Description:
 *  登录类
 *
 */
@WebServlet("/login/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String validateCode = request.getParameter("validateCode");
        //验证码校验
        HttpSession session = request.getSession();
        String checkCode = (String) session.getAttribute(Constants.LOGIN_CODE);
        //确保验证码一次性
        session.removeAttribute(Constants.LOGIN_CODE);
        if(checkCode == null || !checkCode.equalsIgnoreCase(validateCode)){
            //验证码不正确
            request.setAttribute("webResult",new WebResult(5));
            return;
        }

        Map<String, String[]> map = request.getParameterMap();
        //封装User对象
        SysUser user = new SysUser();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //调用Service查询
        SysUserService service = new SysUserServiceImpl();
        SysUser loginUser = service.login(user);
        //判断是否登录成功

        if(loginUser != null){
            //登录成功
            //将用户存入session
            session.setAttribute("user",loginUser);
            request.setAttribute("webResult",new WebResult(1));
        }else{
            //登录失败
            request.setAttribute("webResult",new WebResult(2));
        }
    }

}
