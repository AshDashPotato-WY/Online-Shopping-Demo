package org.example.onlineshoppingdemo.service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.onlineshoppingdemo.db.dao.OnlineShoppingCommodityDao;
import org.example.onlineshoppingdemo.db.dao.OnlineShoppingOrderDao;
import org.example.onlineshoppingdemo.db.po.OnlineShoppingCommodity;
import org.example.onlineshoppingdemo.db.po.OnlineShoppingOrder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@Slf4j
public class OrderService {
    @Resource
    private OnlineShoppingOrderDao orderDao;

    @Resource
    private OnlineShoppingCommodityDao commodityDao;

    public OnlineShoppingOrder createOnlineShoppingOrder(long userId, long commodityId) {
        // query commodity to make sure it is available >= 0
        OnlineShoppingCommodity commodity = commodityDao.selectByCommodityId(commodityId);
        Integer availableStock = commodity.getAvailableStock();
        if(availableStock >0) {
            // available - 1
            availableStock--;
            commodity.setAvailableStock(availableStock);
            commodityDao.updateCommodity(commodity);
            // create Order instance
            OnlineShoppingOrder order = OnlineShoppingOrder.builder()
                    .commodityId(commodityId)
                    .orderNo(UUID.randomUUID().toString())
                    .userId(userId)
                    .orderAmount(1L)
                    .orderStatus(1)
                    // 1. pending payment
                    // 2. finish payment
                    // 99. overtime order
                    .createTime(new Date())
                    .build();
            // insert order into DB
            orderDao.insertOrder(order);
            return order;
        } else {
            log.warn("commodity out of stock, commodityId:" + commodity.getCommodityId());
            return null;
        }
    }

    public OnlineShoppingOrder queryOrder(String orderNum) {
        return orderDao.queryOrderByOrderNum(orderNum);
    }

    public int updateOrder(OnlineShoppingOrder onlineShoppingOrder) {
        return orderDao.updateOrder(onlineShoppingOrder);
    }
}
