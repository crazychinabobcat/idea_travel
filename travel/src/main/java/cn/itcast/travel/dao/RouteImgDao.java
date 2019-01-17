package cn.itcast.travel.dao;

import cn.itcast.travel.domain.RouteImg;

import java.util.List;

/**
 * ClassName:Lynx
 * Description:
 *
 * @Date:2019/1/15 21:04
 * @Author:chinabobcat2008@gmail.com
 */
public interface RouteImgDao {

    //根据线路routeid查询图片
    List<RouteImg> findByRid(int rid);
}
