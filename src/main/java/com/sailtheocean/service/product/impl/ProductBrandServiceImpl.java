package com.sailtheocean.service.product.impl;

import com.sailtheocean.domain.product.Brand;
import com.sailtheocean.domain.shop.ShopInfo;
import com.sailtheocean.repository.product.IProductBrandRepository;
import com.sailtheocean.service.product.IProductBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fan on 24/08/15.
 */
@Service("productBrandService")
public class ProductBrandServiceImpl implements IProductBrandService {

    private static final int PAGE_SIZE = 2;

    @Autowired
    private IProductBrandRepository productBrandRepository;

    @Override
    public List<Brand> getBrands(Integer shopinfoid) {
        return productBrandRepository.findAllBrandsByShopId(shopinfoid);
    }

    @Override
    public Page<Brand> getPaginationBrands(ShopInfo shopInfo, Integer pageNumber) {

        PageRequest request = new PageRequest(pageNumber -1, PAGE_SIZE, Sort.Direction.DESC, "id");

        return productBrandRepository.findByshopInfo(shopInfo, request);
    }

    @Override
    public Brand getBrand(Integer brandid) {
        return productBrandRepository.findOne(brandid);
    }

    @Override
    public void addProductBrand(Brand brand) {
        productBrandRepository.save(brand);
    }

    @Override
    public void deleteBrand(Integer id) {
        productBrandRepository.delete(id);
    }
}
