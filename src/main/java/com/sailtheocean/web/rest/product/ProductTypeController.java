package com.sailtheocean.web.rest.product;

import com.sailtheocean.domain.product.ProductType;
import com.sailtheocean.domain.shop.ShopInfo;
import com.sailtheocean.service.product.IProductTypeService;
import com.sailtheocean.service.shop.IShopInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by fan on 25/08/15.
 */
@RestController
@RequestMapping("/product")
public class ProductTypeController {

    @Autowired
    IProductTypeService productTypeService;
    @Autowired
    IShopInfoService shopInfoService;

    /**
     * get product category json list
     * @return
     */
    @RequestMapping(value = "/getCategoryList", method = RequestMethod.GET)
    public List<ProductType> getCategoryList(@RequestParam Integer shopinfoid){

        List<ProductType> productTypes = productTypeService.getTypes(shopinfoid);

        return productTypes;
    }

    /**
     * 添加商品类别，若类别的父类别属性为空，则该类别为root节点
     * @param productType
     * @return
     */
    @RequestMapping(value = "/addCategory", method = RequestMethod.POST)
    public List<ProductType> addCategory(@RequestBody ProductType productType){

        // add child category
        if(productType.getNote() != null && !"".equals(productType.getNote())){
            ProductType parent = productTypeService.getProductTypeById(Integer.parseInt(productType.getNote()));
            productType.setParent(parent);
        }

        ShopInfo shopInfo = shopInfoService.getShop(productType.getShopInfo().getId());
        productType.setShopInfo(shopInfo);

        productTypeService.addType(productType);

        return getCategoryList(shopInfo.getId());
    }

    /**
     * modify product type(category)
     * @return
     */
    @RequestMapping(value = "/editCategory", method = RequestMethod.POST)
    public List<ProductType> editCategory(@RequestBody ProductType productType){

        ProductType type = productTypeService.getProductTypeById(productType.getId());

        type.setName(productType.getName());

        productTypeService.addType(type);

        return getCategoryList(type.getShopInfo().getId());
    }

    /**
     * delete product category
     * @param productType
     * @return
     */
    @RequestMapping(value = "/deleteCategory", method = RequestMethod.POST)
    public List<ProductType> deleteCategory(@RequestBody ProductType productType){

        productTypeService.deleteProductType(productType.getId());

        return getCategoryList(productType.getShopInfo().getId());
    }
}
