package com.sailtheocean.repository.product;

import com.sailtheocean.domain.product.Brand;
import com.sailtheocean.domain.shop.ShopInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by fan on 24/08/15.
 */
public interface IProductBrandRepository extends JpaRepository<Brand, Integer>, PagingAndSortingRepository<Brand, Integer>{

    Page<Brand> findByshopInfo(ShopInfo shopInfo, Pageable pageable);

    @Modifying
    @Query("select b from Brand b where b.shopInfo.id =:id")
    List<Brand> findAllBrandsByShopId(@Param("id") Integer id);
}
