package org.example.onlineshoppingdemo.controller;

import jakarta.annotation.Resource;
import org.example.onlineshoppingdemo.db.dao.OnlineShoppingCommodityDao;
import org.example.onlineshoppingdemo.db.po.OnlineShoppingCommodity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class CommodityController {

    @Resource
    OnlineShoppingCommodityDao onlineShoppingCommodityDao;

    @RequestMapping("/addItem")
    public String addCommodity(){
        return "add_commodity";
    }

    @PostMapping("/commodities")
    public String HandleAddCommodity(@RequestParam("commodityId") long commodityId,
                                     @RequestParam("commodityName") String commodityName,
                                     @RequestParam("commodityDesc") String commodityDesc,
                                     @RequestParam("price") int price,
                                     @RequestParam("creatorUserId") long creatorUserId,
                                     @RequestParam("availableStock") int availableStock,
                                     Map<String, Object> resultMap) {
        OnlineShoppingCommodity commodity = OnlineShoppingCommodity.builder()
                .commodityName(commodityName)
                .commodityId(commodityId)
                .commodityDesc(commodityDesc)
                .price(price)
                .creatorUserId(creatorUserId)
                .availableStock(availableStock)
                .lockStock(0)
                .totalStock(availableStock)
                .build();
        onlineShoppingCommodityDao.insertCommodity(commodity);
        resultMap.put("abc", commodity);
        return "add_commodity_success";
    }

    @GetMapping("/")
    public String index(Map<String, Object> resultMap) {
        List<OnlineShoppingCommodity> onlineShoppingCommodityList = onlineShoppingCommodityDao.listItems();
        resultMap.put("itemList", onlineShoppingCommodityList);
        return "list_items";
    }

    @GetMapping("/item/{commodityId}")
    public String getItemById(@PathVariable long commodityId,
                              Map<String, Object> resultMap) {
        OnlineShoppingCommodity commodity = onlineShoppingCommodityDao.selectByCommodityId(commodityId);
        resultMap.put("commodity", commodity);
        return "item_detail";
    }

}
