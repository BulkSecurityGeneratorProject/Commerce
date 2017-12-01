package com.sailtheocean.service.product.impl;

import com.sailtheocean.domain.product.ProductInfo;
import com.sailtheocean.domain.shop.ShopInfo;
import com.sailtheocean.repository.product.IProductInfoRepository;
import com.sailtheocean.service.product.IProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fan on 25/08/15.
 */
@Service("productInfoService")
public class ProductInfoServiceImpl implements IProductInfoService {

    private static final int PAGE_SIZE = 2;

    @Autowired
    private IProductInfoRepository productInfoRepository;

    @Override
    public List<ProductInfo> getProducts(Integer shopinfoid) {
        return null;
    }

    @Override
    public Page<ProductInfo> getPaginationProducts(ShopInfo shopInfo, Integer pageNumber) {

        PageRequest request = new PageRequest(pageNumber -1, PAGE_SIZE, Sort.Direction.DESC, "id");

        return productInfoRepository.findByshopInfo(shopInfo, request);
    }

    @Override
    public ProductInfo findProductByID(Integer id) {
        return productInfoRepository.findOne(id);
    }

    @Override
    public void addProduct(ProductInfo productInfo) {
        productInfoRepository.save(productInfo);
    }

    @Override
    public void deleteProductByID(Integer id) {
        productInfoRepository.delete(id);
    }
}
