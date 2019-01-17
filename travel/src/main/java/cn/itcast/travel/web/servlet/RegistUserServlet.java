package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * ClassName:Lynx
 * Description:
 *
 * @Date:2019/1/13 13:31
 * @Author:chinabobcat2008@gmail.com
 */
@WebServlet(name = "RegistUserServlet",urlPatterns = "/RegistUserServlet")
public class RegistUserServlet extends BaseServlet {
    //用户注册
    public String registUser(HttpServletRequest req, HttpServletResponse resp)throws Exception {

        String check_Code = req.getParameter("check");
        String server_Code = (String) req.getSession().getAttribute("CHECKCODE_SERVER");
        req.removeAttribute("CHECKCODE_SERVER"); //防止验证码被重复使用


        if (check_Code == null||!check_Code.equalsIgnoreCase(server_Code)){
            //验证码错误
            ResultInfo resultInfo = new ResultInfo();
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("验证码错误");

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(resultInfo);
            resp.setContentType("application/json;charset=utf-8");
            resp.getWriter().write(json);
            return null;
        }



        boolean flag = false;
        try {
            Map<String,String[]> map = req.getParameterMap();
            User user = new User();
            BeanUtils.populate(user,map);
            user.setStatus("Y");
            UserService userService = new UserServiceImpl();
            flag = userService.registUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ResultInfo resultInfo = new ResultInfo();

        if (flag){
            //注册成功
            resultInfo.setFlag(true);


        }else{
            //注册失败
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("注册失败,该用户名已经存在了");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(resultInfo);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json);
        return null;
    }


    public String loginUser(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        String  code = req.getParameter("check");
        String server_Code = (String) req.getSession().getAttribute("CHECKCODE_SERVER");
        req.removeAttribute("CHECKCODE_SERVER"); //防止验证码被重复使用

        if (code ==null ||!code.equalsIgnoreCase(server_Code)){
            ResultInfo resultInfo = new ResultInfo();
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("验证码错误");

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(resultInfo);
            resp.setContentType("application/json;charset=utf-8");
            resp.getWriter().write(json);
            return null;
        }

        Map<String,String []> map = req.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        }catch (Exception e){
            e.printStackTrace();
        }
        ResultInfo resultInfo = new ResultInfo();
        UserService userService = new UserServiceImpl();
        User user1 = userService.loginUser(user);
        if (user1 ==null){

            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("用户名或密码错误");
        }
        if (user1!=null&&"N".equals(user1.getStatus())){
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("您尚未激活，请去邮箱激活账号");
        }

        if (user1!=null && "Y".equals(user1.getStatus())){
            //登陆成功
            req.getSession().setAttribute("user",user1);
            resultInfo.setFlag(true);

        }

        ObjectMapper objectMapper = new ObjectMapper();
        resp.setContentType("application/json;charset=utf-8");
        objectMapper.writeValue(resp.getOutputStream(),resultInfo);
        return null;
    }



    //显示index的名字
    public String findUserName(HttpServletRequest req, HttpServletResponse resp) throws Exception{
     Object user = req.getSession().getAttribute("user");
     ObjectMapper objectMapper = new ObjectMapper();
     resp.setContentType("application/json;charset=utf-8");
     objectMapper.writeValue(resp.getOutputStream(),user);

     return null;

    }


    public void findOne(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        Object user = req.getSession().getAttribute("user");
        writeValue(user,resp);

    }

    public String loginOut(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath()+"/login.html");
        return null;
    }


}
