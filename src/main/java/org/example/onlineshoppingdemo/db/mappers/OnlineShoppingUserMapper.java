package org.example.onlineshoppingdemo.db.mappers;

import org.example.onlineshoppingdemo.db.po.OnlineShoppingUser;
import org.springframework.stereotype.Repository;

@Repository
public interface OnlineShoppingUserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(OnlineShoppingUser record);

    int insertSelective(OnlineShoppingUser record);

    OnlineShoppingUser selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(OnlineShoppingUser record);

    int updateByPrimaryKey(OnlineShoppingUser record);
}