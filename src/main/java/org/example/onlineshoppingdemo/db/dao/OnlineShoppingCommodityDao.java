package org.example.onlineshoppingdemo.db.dao;

import org.example.onlineshoppingdemo.db.po.OnlineShoppingCommodity;

import java.util.List;

public interface OnlineShoppingCommodityDao {
    int insertCommodity(OnlineShoppingCommodity onlineShoppingCommodity);
    OnlineShoppingCommodity selectByCommodityId(Long commodityId);
    List<OnlineShoppingCommodity> selectByUserId(Long userId);
    List<OnlineShoppingCommodity> listItems();
    int updateCommodity(OnlineShoppingCommodity commodity);
}
