package com.sailtheocean.service.shop.impl;

import com.sailtheocean.domain.shop.ShopInfo;
import com.sailtheocean.repository.shop.IShopInfoRepository;
import com.sailtheocean.service.shop.IShopInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fan on 23/08/15.
 */
@Service("shopInfoService")
public class ShopInfoServiceImpl  implements IShopInfoService {
    @Autowired
    private IShopInfoRepository shopInfoRespository;

    @Override
    public List<ShopInfo> getShops() {
        return shopInfoRespository.findAll();
    }

    @Override
    public ShopInfo getShop(Integer id) {
        return shopInfoRespository.findOne(id);
    }

    @Override
    public void saveShop(ShopInfo shopInfo) {
        shopInfoRespository.save(shopInfo);
    }
    @Override
    public void deleteShopById(Integer id) {
        shopInfoRespository.delete(id);
    }
}
