package com.sailtheocean.repository.shop;

import com.sailtheocean.domain.shop.ShopCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by fan on 23/08/15.
 */
public interface IShopCategoryRepository  extends JpaRepository<ShopCategory, Integer> {
    @Modifying
    @Query("select s from ShopCategory s where s.shopGroup.id =:uuid")
    @Transactional
    List<ShopCategory> findAllById(@Param("uuid") Integer id);
}
