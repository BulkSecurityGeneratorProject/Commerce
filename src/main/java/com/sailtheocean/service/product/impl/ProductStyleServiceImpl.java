package com.sailtheocean.service.product.impl;

import com.sailtheocean.domain.product.ProductInfo;
import com.sailtheocean.domain.product.ProductStyle;
import com.sailtheocean.repository.product.IProductStyleRepository;
import com.sailtheocean.service.product.IProductStyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fan on 26/08/15.
 */
@Service("productStyleService")
public class ProductStyleServiceImpl  implements IProductStyleService {

    @Autowired
    private IProductStyleRepository productStyleRepository;

    @Override
    public List<ProductStyle> getStylesByProduct(ProductInfo productInfo) {
        return productStyleRepository.findStylesByProductId(productInfo.getId());
    }

    @Override
    public void addStyle(ProductStyle productStyle) {
        productStyleRepository.save(productStyle);
    }

    @Override
    public ProductStyle findStyleByID(Integer id) {
        return productStyleRepository.findOne(id);
    }

    @Override
    public void deleteStyle(ProductStyle productStyle) {
        productStyleRepository.delete(productStyle);
    }
}
