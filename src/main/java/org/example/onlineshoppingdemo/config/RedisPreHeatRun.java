package org.example.onlineshoppingdemo.config;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.onlineshoppingdemo.db.dao.OnlineShoppingCommodityDao;
import org.example.onlineshoppingdemo.db.po.OnlineShoppingCommodity;
import org.example.onlineshoppingdemo.service.RedisService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class RedisPreHeatRun implements ApplicationRunner {
    // TODO: Reading from MYSQL, sync up to Redis
    @Resource
    OnlineShoppingCommodityDao commodityDao;

    @Resource
    RedisService redisService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<OnlineShoppingCommodity> onlineShoppingCommodities = commodityDao.listItems();
        for (OnlineShoppingCommodity commodity : onlineShoppingCommodities) {
            String redisKey = "online_shopping_commodity:" + commodity.getCommodityId();
            redisService.setValue(redisKey, commodity.getAvailableStock().toString());
            log.info("preHeat Staring: Initialize redis stock with commodityId:" + commodity.getCommodityId());
        }
    }
}
