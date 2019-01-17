package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.FavoriteService;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.FavoriteServiceImpl;
import cn.itcast.travel.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName:Lynx
 * Description:
 *
 * @Date:2019/1/15 13:22
 * @Author:chinabobcat2008@gmail.com
 */
@WebServlet(name = "RouteServlet",urlPatterns = "/RouteServlet")
public class RouteServlet extends BaseServlet {


    //根据cid查询分类信息分页
    public String pageQuery(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        String currentPageStr = req.getParameter("currentPage");
        String pageSizeStr = req.getParameter("pageSize");
        String cidStr = req.getParameter("cid");

        req.setCharacterEncoding("utf-8");
        String rname = req.getParameter("rname");
       // rname = new String(rname.getBytes("iso-8859-1"),"utf-8");

        int cid = 0;

        if (cidStr != null&& cidStr.length()>0 &&!cidStr.equals("null")){
            cid = Integer.parseInt(cidStr);
        }

        int currentPage = 0;
        if (currentPageStr != null &&currentPageStr.length()>0){
            currentPage = Integer.parseInt(currentPageStr);
        }else{
            currentPage = 1;
        }

        int pageSize = 0;
        if (pageSizeStr != null && pageSizeStr.length()>0){
            pageSize = Integer.parseInt(pageSizeStr);
        }else{
           pageSize = 5;
        }

        RouteService routeService = new RouteServiceImpl();
        PageBean<Route> pb = routeService.pageQuery(cid, currentPage, pageSize,rname);

        writeValue(pb,resp);
        return null;

    }


    //根据id查询详细分类旅游信息
    public String finOne(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        //接收id
        String rid = req.getParameter("rid");
        //调用service 查询route对象
        RouteService routeService = new RouteServiceImpl();
        Route route = routeService.findOne(rid);
        //转为json写会客户端
        writeValue(route,resp);
        return null;
    }



    public String isFavorite(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        String rid = req.getParameter("rid");
        User user = (User) req.getSession().getAttribute("user");
        int uid;
        if (user == null){
            //用户没有登录
            uid = 0;
        }else {
            //用户已经登录
            uid = user.getUid();
        }
        //调用FavoriteService查询是否收藏
        FavoriteService favoriteService = new FavoriteServiceImpl();
        boolean flag =favoriteService.isFavorite(rid,uid);
        writeValue(flag,resp);
        return  null;
    }


    public String addFavorite(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        String  rid = req.getParameter("rid");
        User user = (User)req.getSession().getAttribute("user");
        int uid;
        if(user == null){
            return null;
        }else{
            uid = user.getUid();
        }

        FavoriteService favoriteService = new FavoriteServiceImpl();
        favoriteService.add(rid,uid);
        return  null;
    }

}
