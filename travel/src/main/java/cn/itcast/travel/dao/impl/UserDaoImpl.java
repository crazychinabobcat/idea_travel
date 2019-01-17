package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * ClassName:Lynx
 * Description:
 *
 * @Date:2019/1/13 13:39
 * @Author:chinabobcat2008@gmail.com
 */
public class UserDaoImpl implements UserDao {
    private  JdbcTemplate template =new JdbcTemplate(JDBCUtils.getDataSource());


    @Override
    public User findByUsername(String username) throws  Exception  {
        User user = null;
        try {
            String sql = "select * from tab_user where username = ?";
            user =template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),username);
        }catch (Exception e){

        }

        return user;
    }

    @Override
    public void save(User user) throws Exception  {
        String  sql = "insert into tab_user values(?,?,?,?,?,?,?,?,?,?)";
        template.update(sql,user.getUid(),user.getUsername(),user.getPassword(),user.getName(),user.getBirthday(),user.getSex(),user.getTelephone(),user.getEmail(),user.getStatus(),user.getCode());

    }

    //查找code
    @Override
    public User findBuCode(String activeCode) throws Exception {
        User user = null;
        try {
            String sql = "select * from tab_user where code =?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),activeCode);
        }catch (Exception e){

        }

        return user;
    }


    //激活用户状态
    @Override
    public void updateStatus(User user) throws Exception {
        String sql = "update tab_user set status ='Y' where uid = ?";
        template.update(sql ,user.getUid());
    }

    @Override
    public User findByUsernameAndPassword(User user) throws Exception {
        User user1 = null;
        try {
            String sql = "select * from tab_user where username =? and password = ?";
            user1 =template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),user.getUsername(),user.getPassword());
        }catch (Exception e){

        }

        return user1;
    }
}
