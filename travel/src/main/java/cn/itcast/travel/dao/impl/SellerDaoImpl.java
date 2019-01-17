package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.SellerDao;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * ClassName:Lynx
 * Description:
 *
 * @Date:2019/1/15 21:10
 * @Author:chinabobcat2008@gmail.com
 */
public class SellerDaoImpl implements SellerDao {

    private JdbcTemplate jdbcTemplate =new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public Seller findByid(int id) {
        String sql = "select * from tab_seller where sid = ?";
        return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<Seller>(Seller.class),id);

    }
}
