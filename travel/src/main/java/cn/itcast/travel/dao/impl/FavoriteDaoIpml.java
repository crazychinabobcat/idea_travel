package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;

/**
 * ClassName:Lynx
 * Description:
 *
 * @Date:2019/1/16 11:52
 * @Author:chinabobcat2008@gmail.com
 */
    public class FavoriteDaoIpml implements FavoriteDao {
   private  JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public Favorite findByRidandUid(int rid, int uid) {
        Favorite favorite = null;
        try{
            String sql = "select * from tab_favorite where rid = ? and uid = ?";
            favorite = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<Favorite>(Favorite.class),rid,uid);

        }catch (Exception e) {
            e.printStackTrace();
        }
        return favorite;
    }



    @Override
    public int findCountByRid(int rid) throws Exception {
        String sql = "select count(*) from tab_favorite where rid =? ";
        return jdbcTemplate.queryForObject(sql, Integer.class,rid);
    }

    @Override
    public void add(int rid, int uid) throws Exception {
        String sql = "insert into tab_favorite values(?,?,?)";
        jdbcTemplate.update(sql,rid,new Date(),uid);
    }
}
