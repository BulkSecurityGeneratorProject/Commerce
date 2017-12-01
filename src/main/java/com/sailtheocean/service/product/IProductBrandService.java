package com.sailtheocean.service.product;

import com.sailtheocean.domain.product.Brand;
import com.sailtheocean.domain.shop.ShopInfo;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by fan on 24/08/15.
 */
public interface IProductBrandService {
    /**
     * get all brands by shopinfo id
     * @param shopinfoid
     * @return
     */
    public List<Brand> getBrands(Integer shopinfoid);
    /**
     *  get pagination brands by shopinfo id
     * @return
     */
    public Page<Brand> getPaginationBrands(ShopInfo shopInfo, Integer pageNumber);
    /**
     * get brand by brand id
     * @param brandid
     * @return
     */
    public Brand getBrand(Integer brandid);
    /**
     *  add product brand
     * @param brand
     */
    public void addProductBrand(Brand brand);

    /**
     * delete brand by brand id
     * @param id
     */
    public void deleteBrand(Integer id);
}
