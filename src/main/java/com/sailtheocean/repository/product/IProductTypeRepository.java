package com.sailtheocean.repository.product;

import com.sailtheocean.domain.product.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by fan on 24/08/15.
 */
public interface IProductTypeRepository extends JpaRepository<ProductType, Integer>{

    @Modifying
    @Query("select p from ProductType p where p.parent is null and p.shopInfo.id =:id ")
    @Transactional
    List<ProductType> findCategoriesHasNoParent(@Param("id") Integer id);
}
