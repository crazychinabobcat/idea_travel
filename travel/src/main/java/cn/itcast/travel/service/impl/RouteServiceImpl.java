package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.RouteImgDao;
import cn.itcast.travel.dao.SellerDao;
import cn.itcast.travel.dao.impl.FavoriteDaoIpml;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.dao.impl.RouteImgDaoImpl;
import cn.itcast.travel.dao.impl.SellerDaoImpl;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.service.RouteService;

import java.util.List;

/**
 * ClassName:Lynx
 * Description:
 *
 * @Date:2019/1/15 13:38
 * @Author:chinabobcat2008@gmail.com
 */
public class RouteServiceImpl implements RouteService {
    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname) {
        //封装PageBean
        PageBean<Route> pb = new PageBean<Route>();
        //设置当前页码
        pb.setCurrentPage(currentPage);
        //设置当前每页显示条数
        pb.setPageSize(pageSize);

        RouteDao routeDao = new RouteDaoImpl();
        //设置总记录数
        int totalCount = routeDao.findTotalCount(cid,rname);
        pb.setTotalCount(totalCount);
        //设置当前页显示的数据集合
        int start = (currentPage-1)*pageSize;
        List<Route> list = routeDao.findBypage(cid,start,pageSize,rname);
        pb.setList(list);

        //设置一下总页数
        int totalPage = totalCount % pageSize ==0?totalCount/pageSize:(totalCount/pageSize)+1;
        pb.setTotalPage(totalPage);

        return pb;
    }

    @Override
    public Route findOne(String rid) throws Exception {
        //根据id去route表中查询
        RouteDao routeDao = new RouteDaoImpl();
        Route route = routeDao.findOne(Integer.parseInt(rid));
        //根据routeid 查询图片信息
        RouteImgDao routeImgDao = new RouteImgDaoImpl();
        List<RouteImg> routeImgList = routeImgDao.findByRid(route.getRid());
        //将集合设置到Route对象
        route.setRouteImgList(routeImgList);
        //根据route的sid查询卖家的信息
        SellerDao sellerDao = new SellerDaoImpl();
        Seller seller = sellerDao.findByid(route.getSid());
        route.setSeller(seller);

        //查询收藏次数哦
        FavoriteDao favoriteDao = new FavoriteDaoIpml();
        int count = favoriteDao.findCountByRid(route.getRid());
        route.setCount(count);
        return route;
    }
}
