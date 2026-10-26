package org.example.onlineshoppingdemo.controller;

import jakarta.annotation.Resource;
import org.example.onlineshoppingdemo.db.dao.OnlineShoppingCommodityDao;
import org.example.onlineshoppingdemo.db.dao.OnlineShoppingOrderDao;
import org.example.onlineshoppingdemo.db.po.OnlineShoppingOrder;
import org.example.onlineshoppingdemo.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.Map;

@Controller
public class OrderController {
    @Resource
    private OrderService orderService;

    @Resource
    private OnlineShoppingCommodityDao commodityDao;

    @RequestMapping("commodity/buy/{userId}/{commodityId}")
    public String buyCommodity(@PathVariable long userId,
                               @PathVariable long commodityId,
                               Map<String, Object> resultMap) {
        OnlineShoppingOrder onlineShoppingOrder =
                orderService.createOnlineShoppingOrder(userId, commodityId);
        // order creation succeeds
        if (onlineShoppingOrder != null) {
            resultMap.put("resultInfo", "Place order success! Order number:" + onlineShoppingOrder.getOrderNo());
            resultMap.put("orderNo", onlineShoppingOrder.getOrderNo());
        } else {
            resultMap.put("resultInfo", "Order create failed, check log for detail");
            resultMap.put("orderNo", "");
        }
        return "order_result";
    }

    @GetMapping("commodity/orderQuery/{orderNum}")
    public String orderQuery(@PathVariable String orderNum,
                             Map<String, Object> resultMap) {
        OnlineShoppingOrder onlineShoppingOrder = orderService.queryOrder(orderNum);
        resultMap.put("order", onlineShoppingOrder);
        resultMap.put("commodity", onlineShoppingOrder.getCommodityId());
        return "order_check";
    }

    @RequestMapping("/commodity/payOrder/{orderNum}")
    public String payOrder(@PathVariable String orderNum,
                           Map<String, Object> resultMap) {
        OnlineShoppingOrder onlineShoppingOrder = orderService.queryOrder(orderNum);
        onlineShoppingOrder.setPayTime(new Date());
        onlineShoppingOrder.setOrderStatus(2);
        orderService.updateOrder(onlineShoppingOrder);
        return orderQuery(orderNum, resultMap);
//        resultMap.put("order", onlineShoppingOrder);
    }

}
