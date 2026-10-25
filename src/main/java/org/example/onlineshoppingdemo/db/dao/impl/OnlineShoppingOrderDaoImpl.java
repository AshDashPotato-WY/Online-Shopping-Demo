package org.example.onlineshoppingdemo.db.dao.impl;

import jakarta.annotation.Resource;
import org.example.onlineshoppingdemo.db.dao.OnlineShoppingOrderDao;
import org.example.onlineshoppingdemo.db.mappers.OnlineShoppingOrderMapper;
import org.example.onlineshoppingdemo.db.po.OnlineShoppingOrder;
import org.springframework.stereotype.Repository;


@Repository
public class OnlineShoppingOrderDaoImpl implements OnlineShoppingOrderDao {
    @Resource
    private OnlineShoppingOrderMapper mapper;

    @Override
    public int insertOrder(OnlineShoppingOrder order) {
        return mapper.insert(order);
    }

    @Override
    public OnlineShoppingOrder queryOrderByOrderNum(String orderNum) {
        return mapper.queryOrderByOrderNum(orderNum);
    }

    @Override
    public int updateOrder(OnlineShoppingOrder onlineShoppingOrder) {
        return  mapper.updateByPrimaryKeySelective(onlineShoppingOrder);
    }
}
