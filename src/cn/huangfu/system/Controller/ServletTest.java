package cn.huangfu.system.Controller;

import cn.huangfu.system.dao.SysUserDao;
import cn.huangfu.system.dao.impl.SysUserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: HuangJiaSheng
 * @Date: 2019/11/14 20:39
 * @Description:
 */
@WebServlet("/ServletTest")
public class ServletTest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SysUserDao sud = new SysUserDaoImpl();

        System.out.println(sud.findAll());

    }
}
