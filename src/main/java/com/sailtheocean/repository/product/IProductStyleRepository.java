package com.sailtheocean.repository.product;

import com.sailtheocean.domain.product.ProductStyle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by fan on 24/08/15.
 */
public interface IProductStyleRepository extends JpaRepository<ProductStyle, Integer>{

    @Modifying
    @Query("select p from ProductStyle p where p.product.id =:uuid")
    @Transactional
    List<ProductStyle> findStylesByProductId(@Param("uuid") Integer id);
}
