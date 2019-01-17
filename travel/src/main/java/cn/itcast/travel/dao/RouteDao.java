package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;

import java.util.List;

/**
 * ClassName:Lynx
 * Description:
 *
 * @Date:2019/1/15 13:43
 * @Author:chinabobcat2008@gmail.com
 */
public interface RouteDao {

    //根据cid查询记录总数
    int findTotalCount(int cid,String rname);

    //根据cid start pageSize 查询当前页的数据集合
    List<Route> findBypage(int cid , int start, int pageSize,String rname);

    //根据id查询
    Route findOne(int rid);
}
