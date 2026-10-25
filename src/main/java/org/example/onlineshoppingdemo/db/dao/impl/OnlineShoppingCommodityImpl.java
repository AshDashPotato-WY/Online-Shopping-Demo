package org.example.onlineshoppingdemo.db.dao.impl;

import jakarta.annotation.Resource;
import org.example.onlineshoppingdemo.db.dao.OnlineShoppingCommodityDao;
import org.example.onlineshoppingdemo.db.mappers.OnlineShoppingCommodityMapper;
import org.example.onlineshoppingdemo.db.po.OnlineShoppingCommodity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OnlineShoppingCommodityImpl implements OnlineShoppingCommodityDao {

    @Resource
    private OnlineShoppingCommodityMapper mapper;

    @Override
    public int insertCommodity(OnlineShoppingCommodity onlineShoppingCommodity) {
        return mapper.insert(onlineShoppingCommodity);
    }

    @Override
    public OnlineShoppingCommodity selectByCommodityId(Long commodityId) {
        return mapper.selectByPrimaryKey(commodityId);
    }

    @Override
    public List<OnlineShoppingCommodity> selectByUserId(Long userId) {
        return mapper.queryCommoditiesByUserId(userId);
    }

    @Override
    public List<OnlineShoppingCommodity> listItems() {
        return mapper.listCommodities();
    }

    @Override
    public int updateCommodity(OnlineShoppingCommodity commodity) {
        return mapper.updateByPrimaryKeySelective(commodity);
    }
}
