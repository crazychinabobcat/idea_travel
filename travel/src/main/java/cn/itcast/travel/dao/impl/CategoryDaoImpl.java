package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * ClassName:Lynx
 * Description:
 *
 * @Date:2019/1/14 22:25
 * @Author:chinabobcat2008@gmail.com
 */
public class CategoryDaoImpl implements CategoryDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Category> findAll() {
        String sql = "select * from tab_category";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Category>(Category.class));
    }
}
