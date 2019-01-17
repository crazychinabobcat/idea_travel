package cn.itcast.travel.service;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;

/**
 * ClassName:Lynx
 * Description:
 *
 * @Date:2019/1/15 13:38
 * @Author:chinabobcat2008@gmail.com
 */
public interface RouteService {


    PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname) throws  Exception;

    Route findOne(String rid)throws  Exception;
}
