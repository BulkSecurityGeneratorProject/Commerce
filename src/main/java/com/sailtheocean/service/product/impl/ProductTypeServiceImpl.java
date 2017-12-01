package com.sailtheocean.service.product.impl;

import com.sailtheocean.domain.product.ProductType;
import com.sailtheocean.repository.product.IProductTypeRepository;
import com.sailtheocean.service.product.IProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fan on 25/08/15.
 */
@Service("productTypeService")
public class ProductTypeServiceImpl implements IProductTypeService {

    @Autowired
    private IProductTypeRepository productTypeRepository;

    @Override
    public List<ProductType> getTypes(Integer shopinfoid) {
        return productTypeRepository.findCategoriesHasNoParent(shopinfoid);
    }

    @Override
    public ProductType getProductTypeById(Integer typeid) {
        return productTypeRepository.findOne(typeid);
    }

    @Override
    public void addType(ProductType productType) {
        productTypeRepository.save(productType);
    }

    @Override
    public void deleteProductType(Integer typeid) {
        productTypeRepository.delete(typeid);
    }
}
