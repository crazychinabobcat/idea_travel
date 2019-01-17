package cn.itcast.travel.service;

import cn.itcast.travel.domain.User;

/**
 * ClassName:Lynx
 * Description:
 *
 * @Date:2019/1/13 13:33
 * @Author:chinabobcat2008@gmail.com
 */
public interface UserService {
    boolean registUser(User user) throws  Exception;

    boolean activeUser(String activeCode)throws  Exception;

    User loginUser(User user)throws  Exception;
}
