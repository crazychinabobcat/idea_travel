package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Favorite;

/**
 * ClassName:Lynx
 * Description:
 *
 * @Date:2019/1/16 11:52
 * @Author:chinabobcat2008@gmail.com
 */
public interface FavoriteDao {
    Favorite findByRidandUid(int rid , int uid)throws  Exception;

    int findCountByRid(int rid)throws  Exception;

    void add(int rid, int uid)throws  Exception;
}
