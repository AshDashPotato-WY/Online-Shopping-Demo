package org.example.onlineshoppingdemo.db.dao;

import org.example.onlineshoppingdemo.db.po.OnlineShoppingOrder;

public interface OnlineShoppingOrderDao {
    int insertOrder(OnlineShoppingOrder order);

    OnlineShoppingOrder queryOrderByOrderNum(String orderNum);

    int updateOrder(OnlineShoppingOrder onlineShoppingOrder);
}
