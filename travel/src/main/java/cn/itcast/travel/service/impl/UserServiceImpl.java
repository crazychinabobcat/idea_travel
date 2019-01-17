package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;

/**
 * ClassName:Lynx
 * Description:
 *
 * @Date:2019/1/13 13:36
 * @Author:chinabobcat2008@gmail.com
 */
public class UserServiceImpl implements UserService {

    UserDao userDao = new UserDaoImpl();

    @Override
    public boolean registUser(User user) throws Exception {
        //根据用户名查询用户对象
        User u = userDao.findByUsername(user.getUsername());
        if(u!=null){
            return  false;
        }
        //保存用户信息
        user.setCode(UuidUtil.getUuid());
        user.setStatus("N");
        userDao.save(user);
        String content="<a href ='http://127.0.0.1:8080/travel/ActiveUserServlet?method=activeUser&code="+user.getCode() +"'>【山猫旅游网】</a>";
        MailUtils.sendMail(user.getEmail(),content,"账号激活");
        return true;
    }


    //激活用户
    @Override
    public boolean activeUser(String activeCode) throws Exception {
        User user = userDao.findBuCode(activeCode);
        if(user !=null){
            userDao.updateStatus(user);
            return  true;
        }
        return false;
    }


    //用户登录
    @Override
    public User loginUser(User user) throws Exception {
        return userDao.findByUsernameAndPassword(user);

    }
}
