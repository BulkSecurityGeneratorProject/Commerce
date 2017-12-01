package com.sailtheocean.web.rest.product;

import com.sailtheocean.domain.product.Brand;
import com.sailtheocean.domain.product.ProductInfo;
import com.sailtheocean.domain.shop.ShopInfo;
import com.sailtheocean.service.product.IProductBrandService;
import com.sailtheocean.service.product.IProductInfoService;
import com.sailtheocean.service.shop.IShopInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 */
@RestController
@RequestMapping("/product")
public class ProductInfoController {

    @Autowired
    IProductInfoService productInfoService;
    @Autowired
    IShopInfoService shopInfoService;
    @Autowired
    IProductBrandService brandService;

    /**
     * get all products by shop id
     * @return
     */
    @RequestMapping(value = "/getProducts", method = RequestMethod.GET)
    List<ProductInfo> getProductList(@RequestParam("shopinfoid") Integer shopinfoid){
        return productInfoService.getProducts(shopinfoid);
    }

    /**
     * get pagination product by shop id
     * @param pageNumber
     * @return
     */
    @RequestMapping(value = "/getPaginationProducts", method = RequestMethod.GET)
    Page<ProductInfo> getPaginationProductList(@RequestParam("shopinfoid") Integer shopinfoid, @RequestParam("pageNumber") Integer pageNumber){

        ShopInfo shopInfo = shopInfoService.getShop(shopinfoid);

        return productInfoService.getPaginationProducts(shopInfo, pageNumber);
    }

    /**
     * add product
     * @param productInfo
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public Page<ProductInfo> addProduct(@RequestBody ProductInfo productInfo, HttpServletRequest request, HttpServletResponse response){

        ShopInfo shopInfo = shopInfoService.getShop(productInfo.getShopInfo().getId());

        Brand brand = brandService.getBrand(productInfo.getBrand().getId());


        productInfo.setBrand(brand);
        productInfo.setShopInfo(shopInfo);

        productInfoService.addProduct(productInfo);

        return getPaginationProductList(productInfo.getShopInfo().getId(), 1);
    }

    /**
     * delete product
     * @param productInfo
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/deleteProduct", method = RequestMethod.POST)
    public Page<ProductInfo> deleteProduct(@RequestBody ProductInfo productInfo, HttpServletRequest request, HttpServletResponse response){

        productInfoService.deleteProductByID(productInfo.getId());

        return getPaginationProductList(productInfo.getShopInfo().getId(), 1);
    }

    /**
     * change product commend status
     * @param productInfo
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/changeProductCommendStatus", method = RequestMethod.POST)
    public Page<ProductInfo> changeProductCommendStatus(@RequestBody ProductInfo productInfo, HttpServletRequest request, HttpServletResponse response){

        ProductInfo info = productInfoService.findProductByID(productInfo.getId());
        info.setCommend(productInfo.isCommend());

        productInfoService.addProduct(info);

        return getPaginationProductList(productInfo.getShopInfo().getId(), 1);
    }

    /**
     * change product visible status
     * @param productInfo
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/changeProductVisibleStatus", method = RequestMethod.POST)
    public Page<ProductInfo> changeProductVisibleStatus(@RequestBody ProductInfo productInfo, HttpServletRequest request, HttpServletResponse response){

        ProductInfo info = productInfoService.findProductByID(productInfo.getId());
        info.setVisible(productInfo.isVisible());

        productInfoService.addProduct(info);

        return getPaginationProductList(productInfo.getShopInfo().getId(), 1);
    }
}
