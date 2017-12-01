package com.sailtheocean.service.product;

import com.sailtheocean.domain.product.ProductType;

import java.util.List;

/**
 * Created by fan on 25/08/15.
 */
public interface IProductTypeService {
    /**
     * get product types(category) by shop id
     * @return
     */
    List<ProductType> getTypes(Integer shopinfoid);

    /**
     * get product type by type id
     * @param typeid
     * @return
     */
    ProductType getProductTypeById(Integer typeid);

    /**
     * add product type
     * @param productType
     */
    public void addType(ProductType productType);

    /**
     * delete product type by type id
     * @param typeid
     */
    public void deleteProductType(Integer typeid);
}
