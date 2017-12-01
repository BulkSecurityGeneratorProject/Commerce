package com.sailtheocean.service.shop;

import com.sailtheocean.domain.shop.ShopCategory;
import com.sailtheocean.domain.shop.ShopGroup;

import java.util.List;

/**
 * Created by fan on 23/08/15.
 */
public interface IShopGroupCategoryService {
    /**
     * get all shop groups
     * @return
     */
    List<ShopGroup> getShopGroups();
    /**
     * add shop group
     * @param shopGroup
     */
    public void saveShopGroup(ShopGroup shopGroup);
    /**
     * find shop group by id
     * @param id
     * @return
     */
    ShopGroup getShopGroupById(Integer id);
    /**
     * delete shop group by id
     * @param id
     */
    public void deleteShopGroupById(Integer id);

    /**
     * get all shop categories
     * @return
     */
    List<ShopCategory> getShopCategories();
    /**
     * get all shop categories by group id
     * @param id
     * @return
     */
    List<ShopCategory> getShopCategoriesById(Integer id);
    /**
     * add shop category
     * @param shopCategory
     */
    public void saveShopCategory(ShopCategory shopCategory);
    /**
     * find shop category by id
     * @param id
     * @return
     */
    ShopCategory getShopCategoryById(Integer id);
    /**
     * delete shop category by id
     * @param id
     */
    public void deleteShopCategoryById(Integer id);
}
