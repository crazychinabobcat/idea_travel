package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Category;

import java.util.List;

/**
 * ClassName:Lynx
 * Description:
 *
 * @Date:2019/1/14 22:24
 * @Author:chinabobcat2008@gmail.com
 */
public interface CategoryDao {

    //查询所有的分类
    List<Category> findAll();
}
