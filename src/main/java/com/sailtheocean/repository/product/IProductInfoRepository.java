package com.sailtheocean.repository.product;

import com.sailtheocean.domain.product.Brand;
import com.sailtheocean.domain.product.ProductInfo;
import com.sailtheocean.domain.shop.ShopInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by fan on 24/08/15.
 */
public interface IProductInfoRepository extends JpaRepository<ProductInfo, Integer>{

    Page<ProductInfo> findByshopInfo(ShopInfo shopInfo, Pageable pageable);

    @Modifying
    @Query("select p from ProductInfo p where p.shopInfo.id =:id")
    List<ProductInfo> findAllProductsByShopId(@Param("id") Integer id);
}
