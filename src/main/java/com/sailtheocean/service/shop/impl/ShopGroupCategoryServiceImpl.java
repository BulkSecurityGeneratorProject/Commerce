package com.sailtheocean.service.shop.impl;

import com.sailtheocean.domain.shop.ShopCategory;
import com.sailtheocean.domain.shop.ShopGroup;
import com.sailtheocean.repository.shop.IShopCategoryRepository;
import com.sailtheocean.repository.shop.IShopGroupRepository;
import com.sailtheocean.service.shop.IShopGroupCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fan on 23/08/15.
 */
@Service("shopGroupCategoryService")
public class ShopGroupCategoryServiceImpl  implements IShopGroupCategoryService {
    @Autowired
    private IShopGroupRepository shopGroupRespository;
    @Autowired
    private IShopCategoryRepository shopCategoryRespository;

    @Override
    public List<ShopGroup> getShopGroups() {
        return shopGroupRespository.findAll();
    }
    @Override
    public ShopGroup getShopGroupById(Integer id) {
        return shopGroupRespository.findOne(id);
    }
    @Override
    public void saveShopGroup(ShopGroup shopGroup) {
        shopGroupRespository.save(shopGroup);
    }
    @Override
    public void deleteShopGroupById(Integer id) {
        shopGroupRespository.delete(id);
    }


    @Override
    public List<ShopCategory> getShopCategories() {
        return shopCategoryRespository.findAll();
    }
    @Override
    public List<ShopCategory> getShopCategoriesById(Integer id) {
        return  shopCategoryRespository.findAllById(id);
    }
    @Override
    public void saveShopCategory(ShopCategory shopCategory) {
        shopCategoryRespository.save(shopCategory);
    }
    @Override
    public ShopCategory getShopCategoryById(Integer id) {
        return shopCategoryRespository.findOne(id);
    }
    @Override
    public void deleteShopCategoryById(Integer id) {
        shopCategoryRespository.delete(id);
    }
}
