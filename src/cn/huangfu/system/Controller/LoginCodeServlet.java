package cn.huangfu.system.Controller;

import cn.huangfu.common.util.ImageUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @Author: HuangJiaSheng
 * @Date: 2019/11/14 21:15
 * @Description:
 * 获取验证码
 */
@WebServlet("/login/LoginCode")
public class LoginCodeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取Session对象
        HttpSession session = request.getSession();

        //设置随机数值r
        String s = "ABCDEFGHEIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        //设置字符 随机设置水平位置
        for (int i = 1; i <=4; i++) {
            //随机获取字符
            code.append(s.charAt(random.nextInt(s.length())));
        }

        //设置验证码Session
        session.setAttribute("code",code);

        //获取流
        BufferedImage bi = ImageUtils.generate(code.toString(), 80, 40);
        //将图片输出的页面
        ImageIO.write(bi,"JPG",response.getOutputStream());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
