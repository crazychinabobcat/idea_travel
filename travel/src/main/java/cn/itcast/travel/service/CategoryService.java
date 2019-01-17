package cn.itcast.travel.service;

import cn.itcast.travel.domain.Category;

import java.util.List;

/**
 * ClassName:Lynx
 * Description:
 *
 * @Date:2019/1/14 22:29
 * @Author:chinabobcat2008@gmail.com
 */
public interface CategoryService {
    List<Category> findAll();
}
