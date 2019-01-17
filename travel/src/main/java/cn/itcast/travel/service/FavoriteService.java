package cn.itcast.travel.service;

/**
 * ClassName:Lynx
 * Description:
 *
 * @Date:2019/1/16 11:50
 * @Author:chinabobcat2008@gmail.com
 */
public interface FavoriteService {

    boolean isFavorite(String  rid, int uid) throws  Exception;

    void add(String rid, int uid)throws  Exception;
}
