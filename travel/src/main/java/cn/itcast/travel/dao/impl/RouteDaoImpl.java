package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:Lynx
 * Description:
 *
 * @Date:2019/1/15 13:43
 * @Author:chinabobcat2008@gmail.com
 */
public class RouteDaoImpl implements RouteDao {
        private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public int findTotalCount(int cid,String rname) {
       // String sql = "select count(*) from tab_route where cid= ?";
        String sql = "select count(*) from tab_route WHERE 1=1 ";
        StringBuilder stringBuilder = new StringBuilder(sql);
        List params = new ArrayList();
        //判断参数是否有值
        if (cid !=0) {
            stringBuilder.append(" and cid = ? ");
            params.add(cid);
        }

        if (rname !=null && rname.length()>0 && !rname.equals("null")){
            stringBuilder.append(" and rname like ? ");
            params.add("%"+rname+"%");
        }
        sql =stringBuilder.toString();

        return jdbcTemplate.queryForObject(sql,Integer.class,params.toArray());

    }

    @Override
    public List<Route> findBypage(int cid, int start, int pageSize,String rname) {
       // String sql = "select * from tab_route where cid =? limit ?,?";

        String sql = "select * from tab_route where 1 = 1 ";
        StringBuilder stringBuilder = new StringBuilder(sql);
        List  parms = new ArrayList();
        if (cid != 0){
            stringBuilder.append(" and cid = ? ");
            parms.add(cid);
        }

        if (rname !=null && rname.length()>0&& ! rname.equals("null")){
            stringBuilder.append(" and rname like ? ");
            parms.add("%"+rname+"%");
        }
        stringBuilder.append(" limit ? ,? ");
        sql = stringBuilder.toString();

        parms.add(start);
        parms.add(pageSize);

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Route>(Route.class),parms.toArray());
    }

    @Override
    public Route findOne(int rid) {

        String sql = "select * from tab_route where rid =?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Route>(Route.class),rid);

    }
}
