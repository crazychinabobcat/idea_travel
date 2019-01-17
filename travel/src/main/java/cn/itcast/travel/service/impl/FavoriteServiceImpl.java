package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.dao.impl.FavoriteDaoIpml;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.service.FavoriteService;

/**
 * ClassName:Lynx
 * Description:
 *
 * @Date:2019/1/16 11:50
 * @Author:chinabobcat2008@gmail.com
 */
public class FavoriteServiceImpl implements FavoriteService {

    FavoriteDao favoriteDao = new FavoriteDaoIpml();

    @Override
    public boolean isFavorite(String rid, int uid) throws  Exception{
      Favorite favorite = favoriteDao.findByRidandUid(Integer.parseInt(rid),uid);
        return favorite !=null;
    }

    @Override
    public void add(String rid, int uid) throws Exception {
        favoriteDao.add(Integer.parseInt(rid),uid);
    }
}
