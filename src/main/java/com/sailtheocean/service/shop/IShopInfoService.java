package com.sailtheocean.service.shop;

import com.sailtheocean.domain.shop.ShopInfo;

import java.util.List;

/**
 * Created by fan on 23/08/15.
 */
public interface IShopInfoService {
    /**
     * get all shops
     * @return
     */
    List<ShopInfo> getShops();
    /**
     * find shop by id
     * @return
     */
    ShopInfo getShop(Integer id);
    /**
     * add shop
     * @param shopInfo
     */
    public void saveShop(ShopInfo shopInfo);
    /**
     * delete shop by id
     * @param id
     */
    public void deleteShopById(Integer id);
}
