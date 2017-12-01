package com.sailtheocean.web.rest.shop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sailtheocean.domain.shop.ShopCategory;
import com.sailtheocean.domain.shop.ShopInfo;
import com.sailtheocean.service.shop.IShopGroupCategoryService;
import com.sailtheocean.service.shop.IShopInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by fan on 23/08/15.
 */
@RestController
@RequestMapping("/shop")
public class ShopInfoController {
    @Autowired
    IShopInfoService shopInfoService;
    @Autowired
    IShopGroupCategoryService shopGroupCategoryService;

    /**
     * get shops
     * @return
     */
    @RequestMapping(value = "/getShops", method = RequestMethod.GET)
    public String getShops() {
        return setShopJsonList();
    }

    /**
     * get all shops categories and their group json list
     * @return
     */
    @RequestMapping(value = "/getShopCategories", method = RequestMethod.GET)
    public String getShopCategories() {

        List<ShopCategory> shopCategories = shopGroupCategoryService.getShopCategories();
        String categories = "";

        for(ShopCategory shopCategory : shopCategories){
            categories = categories + "{" +
                "\"id\": " + shopCategory.getId() + "," +
                "\"name\": " + "\"" + shopCategory.getName() + "\"" + "," +
                "\"group\": " + "\"" + shopCategory.getShopGroup().getName() + "\"" +
                "}" + ",";
        }
        categories = categories.substring(0, categories.length()-1);
        categories = "[" + categories + "]";
        return categories;
    }

    /**
     * add shop
     * @param request
     * @param response
     * @param shopInfo
     * @return
     */
    @RequestMapping(value = "/addShop", method = RequestMethod.POST)
    public String addShop(HttpServletRequest request, HttpServletResponse response, @RequestBody ShopInfo shopInfo) {

        ShopCategory shopCategory = new ShopCategory();
        shopCategory.setId(Integer.parseInt(shopInfo.getDescription()));

        shopInfo.setShopCategory(shopCategory);

        shopInfoService.saveShop(shopInfo);

        return setShopJsonList();
    }

    /**
     * modify shop
     * @param request
     * @param response
     * @param shopInfo
     * @return
     */
    @RequestMapping(value = "/editShop", method = RequestMethod.POST)
    public String editShop(HttpServletRequest request, HttpServletResponse response, @RequestBody ShopInfo shopInfo) {

        ShopCategory shopCategory = new ShopCategory();
        shopCategory.setId(Integer.parseInt(shopInfo.getDescription()));

        shopInfo.setShopCategory(shopCategory);

        shopInfoService.saveShop(shopInfo);

        return setShopJsonList();
    }

    /**
     * set shop invisible
     * @param request
     * @param response
     * @param shopInfo
     * @return
     */
    @RequestMapping(value = "/disableShop", method = RequestMethod.POST)
    public String disableShop(HttpServletRequest request, HttpServletResponse response, @RequestBody ShopInfo shopInfo) {
        ShopCategory shopCategory = new ShopCategory();
        shopCategory.setId(Integer.parseInt(shopInfo.getDescription()));

        shopInfo.setShopCategory(shopCategory);

        shopInfoService.saveShop(shopInfo);
        return setShopJsonList();
    }

    /**
     * set shop visible
     * @param request
     * @param response
     * @param shopInfo
     * @return
     */
    @RequestMapping(value = "/enableShop", method = RequestMethod.POST)
    public String enableShop(HttpServletRequest request, HttpServletResponse response, @RequestBody ShopInfo shopInfo) {
        ShopCategory shopCategory = new ShopCategory();
        shopCategory.setId(Integer.parseInt(shopInfo.getDescription()));

        shopInfo.setShopCategory(shopCategory);

        shopInfoService.saveShop(shopInfo);
        return setShopJsonList();
    }

    /**
     * delete shop
     * @param request
     * @param response
     * @param shopInfo
     * @return
     */
    @RequestMapping(value = "/deleteShop", method = RequestMethod.POST)
    public String deleteShop(HttpServletRequest request, HttpServletResponse response, @RequestBody ShopInfo shopInfo) {

        shopInfoService.deleteShopById(shopInfo.getId());

        return setShopJsonList();
    }

    /**
     * set up shops json list
     * @return
     */
    private String setShopJsonList(){
        String shopList = "";
        String shop = "";
        List<ShopInfo> shopInfos = shopInfoService.getShops();
        ObjectMapper mapper = new ObjectMapper();

        for(ShopInfo shopInfo : shopInfos){
            try {
                shop = mapper.writeValueAsString(shopInfo).substring(0, mapper.writeValueAsString(shopInfo).length()-1)
                    + ",\"shopCategory\": " + shopInfo.getShopCategory().getId() + "}";
            } catch (Exception e) {
                e.printStackTrace();
            }
            shopList = shopList + shop + ",";
        }
        shopList = shopList.substring(0, shopList.length()-1);
        shopList = "[" + shopList + "]";
        return shopList;
    }
}
