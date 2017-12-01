package com.sailtheocean.service.product;

import com.sailtheocean.domain.product.Brand;
import com.sailtheocean.domain.product.ProductInfo;
import com.sailtheocean.domain.shop.ShopInfo;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by fan on 25/08/15.
 */
public interface IProductInfoService {
    /**
     * get all products by shop id
     * @param shopinfoid
     * @return
     */
    List<ProductInfo> getProducts(Integer shopinfoid);

    /**
     * get pagination products by shop id
     * @param shopInfo
     * @param pageNumber
     * @return
     */
    public Page<ProductInfo> getPaginationProducts(ShopInfo shopInfo, Integer pageNumber);

    /**
     * find product by product id
     * @param id
     * @return
     */
    public ProductInfo findProductByID(Integer id);

    /**
     * add product
     * @param productInfo
     */
    public void addProduct(ProductInfo productInfo);

    /**
     * delete product by product id
     * @param id
     */
    public void deleteProductByID(Integer id);
}
