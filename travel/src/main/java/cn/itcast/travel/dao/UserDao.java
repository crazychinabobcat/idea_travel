package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

/**
 * ClassName:Lynx
 * Description:
 *
 * @Date:2019/1/13 13:39
 * @Author:chinabobcat2008@gmail.com
 */
public interface UserDao {
    /*
    * 根据用户名查询用户信息
    * */
     User findByUsername(String username) throws  Exception;

     void  save(User user) throws  Exception;

    User findBuCode(String activeCode)throws  Exception;

    void updateStatus(User user)throws  Exception;

    User findByUsernameAndPassword(User user)throws  Exception;
}
