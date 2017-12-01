package com.sailtheocean.repository.shop;

import com.sailtheocean.domain.shop.ShopInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by fan on 23/08/15.
 */
public interface IShopInfoRepository extends JpaRepository<ShopInfo, Integer> {

}
