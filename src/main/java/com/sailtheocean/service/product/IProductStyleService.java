package com.sailtheocean.service.product;

import com.sailtheocean.domain.product.ProductInfo;
import com.sailtheocean.domain.product.ProductStyle;

import java.util.List;

/**
 * Created by fan on 26/08/15.
 */
public interface IProductStyleService {
    /**
     * get product styles by product
     * @return
     */
    public List<ProductStyle> getStylesByProduct(ProductInfo productInfo);
    /**
     * add style
     * @param productStyle
     */
    public void addStyle(ProductStyle productStyle);
    /**
     * find product style by id
     * @param id
     * @return
     */
    ProductStyle findStyleByID(Integer id);
    /**
     * delete product style
     * @param
     */
    public void deleteStyle(ProductStyle productStyle);
}
