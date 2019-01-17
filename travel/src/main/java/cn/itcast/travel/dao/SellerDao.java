package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Seller;

/**
 * ClassName:Lynx
 * Description:
 *
 * @Date:2019/1/15 21:10
 * @Author:chinabobcat2008@gmail.com
 */
public interface SellerDao {
    //根据id查询对象
    Seller findByid(int id);
}
