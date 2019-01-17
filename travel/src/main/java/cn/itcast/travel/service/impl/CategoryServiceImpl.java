package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.dao.impl.CategoryDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * ClassName:Lynx
 * Description:
 *
 * @Date:2019/1/14 22:29
 * @Author:chinabobcat2008@gmail.com
 */
public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao = new CategoryDaoImpl();
    @Override
    public List<Category> findAll() {
        List<Category> cs = null;
        //从redis 中查询
        Jedis jedis = JedisUtil.getJedis();
        //Set<String> categorys =jedis.zrange("category",0,-1);
        Set<Tuple> categorys = jedis.zrangeWithScores("category",0,-1);
        //判断查询的集合是否为空
        if (categorys == null || categorys.size() == 0){
            System.out.println("从数据查询的数据");
            cs = categoryDao.findAll();
                    //如果为空从数据库查询，存入redis
            for(int i = 0 ; i<cs.size();i++){

                jedis.zadd("category",cs.get(i).getCid(),cs.get(i).getCname());
            }
        }else {
            System.out.println("从redis中取的数据");
            cs = new ArrayList<Category>();
            for (Tuple tuple :categorys) {
                Category category = new Category();
                category.setCname(tuple.getElement());
                category.setCid((int)tuple.getScore());
                cs.add(category);
            }
        }


        return cs;
    }
}
