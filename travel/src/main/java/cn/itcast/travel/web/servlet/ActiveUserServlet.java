package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * ClassName:Lynx
 * Description:
 *
 * @Date:2019/1/13 22:32
 * @Author:chinabobcat2008@gmail.com
 */
@WebServlet(name = "ActiveUserServlet",urlPatterns = "/ActiveUserServlet")
public class ActiveUserServlet extends BaseServlet {

    // 默认方法
    public String activeUser(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        String activeCode = req.getParameter("code");
        if (activeCode != null){
            UserService userService = new UserServiceImpl();
            boolean flag = userService.activeUser(activeCode);
            String msg = null;
            if (flag){
                //激活成功
               msg = "激活成功，登录";
               resp.sendRedirect("login.html");
            }else {
                //激活失败
                msg = "激活失败，请联系管理员";
            }
            resp.setContentType("text/html;charest=utf-8");
            resp.getWriter().write(msg);
        }

        return null;
    }






}
