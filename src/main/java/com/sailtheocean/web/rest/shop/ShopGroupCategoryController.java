package com.sailtheocean.web.rest.shop;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sailtheocean.domain.shop.ShopCategory;
import com.sailtheocean.domain.shop.ShopGroup;
import com.sailtheocean.service.shop.IShopGroupCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fan on 23/08/15.
 */
@RestController
@RequestMapping("/shop")
public class ShopGroupCategoryController {

    private final Logger log = LoggerFactory.getLogger(ShopGroupCategoryController.class);

    @Autowired
    IShopGroupCategoryService shopGroupCategoryService;

    /**
     * add shop group
     * @param request
     * @param response
     * @param shopGroup
     * @return
     */
    @RequestMapping(value = "/addGroup", method = RequestMethod.POST)
    public String addGroup(HttpServletRequest request, HttpServletResponse response, @RequestBody ShopGroup shopGroup){

        shopGroupCategoryService.saveShopGroup(shopGroup);

        return setShopGroupCategoryJsonList();
    }

    /**
     * modify shop group
     * @param request
     * @param response
     * @param shopGroup
     * @return
     */
    @RequestMapping(value = "/editGroup", method = RequestMethod.POST)
    public String editGroup(HttpServletRequest request, HttpServletResponse response, @RequestBody ShopGroup shopGroup){

        ShopGroup group = shopGroupCategoryService.getShopGroupById(shopGroup.getId());

        group.setName(shopGroup.getName());

        shopGroupCategoryService.saveShopGroup(group);

        return setShopGroupCategoryJsonList();
    }

    /**
     * delete shop group
     * @param request
     * @param response
     * @param shopGroup
     * @return
     */
    @RequestMapping(value = "/deleteGroup", method = RequestMethod.POST)
    public String deleteGroup(HttpServletRequest request, HttpServletResponse response, @RequestBody ShopGroup shopGroup){

        shopGroupCategoryService.deleteShopGroupById(shopGroup.getId());

        return setShopGroupCategoryJsonList();
    }

    /**
     * add shop group category
     * @param request
     * @param response
     * @param shopCategory
     * @return
     */
    @RequestMapping(value = "/addGroupCategorty", method = RequestMethod.POST)
    public String addGroupCategorty(HttpServletRequest request, HttpServletResponse response, @RequestBody ShopCategory shopCategory){

        // 此处临时用sortOrder传递groupid数据，须解决
        int groupid = shopCategory.getSortOrder();

        ShopGroup shopGroup = new ShopGroup();
        shopGroup.setId(groupid);

        shopCategory.setShopGroup(shopGroup);

        shopGroupCategoryService.saveShopCategory(shopCategory);

        return setShopGroupCategoryJsonList();
    }

    /**
     * modify shop category
     * @param request
     * @param response
     * @param shopCategory
     * @return
     */
    @RequestMapping(value = "/editGroupCategorty", method = RequestMethod.POST)
    public String editGroupCategorty(HttpServletRequest request, HttpServletResponse response, @RequestBody ShopCategory shopCategory){

        ShopCategory category = shopGroupCategoryService.getShopCategoryById(shopCategory.getId());

        category.setName(shopCategory.getName());

        shopGroupCategoryService.saveShopCategory(category);

        return setShopGroupCategoryJsonList();
    }

    /**
     * delete shop category
     * @param request
     * @param response
     * @param shopCategory
     * @return
     */
    @RequestMapping(value = "/deleteGroupCategorty", method = RequestMethod.POST)
    public String deleteGroupCategorty(HttpServletRequest request, HttpServletResponse response, @RequestBody ShopCategory shopCategory){

        shopGroupCategoryService.deleteShopCategoryById(shopCategory.getId());

        return setShopGroupCategoryJsonList();
    }

    /**
     * get shop group & category
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getGroupCategory", method = RequestMethod.GET)
    @Timed
    public ResponseEntity<String> getGroupCategory(HttpServletRequest request, HttpServletResponse response){

        HttpHeaders responseHeaders = new HttpHeaders();
        List<MediaType> mediaTypes = new ArrayList<MediaType>();
        mediaTypes.add(MediaType.ALL);
        responseHeaders.setAccept(mediaTypes);
        responseHeaders.add("Access-Control-Allow-Origin", "*");

        log.debug("REST request to get all shop Groups and Categories");

        return new ResponseEntity<String>(setShopGroupCategoryJsonList(), responseHeaders, HttpStatus.OK);
    }

    /**
     * set up shop group & category json list
     * @return
     */
    private String setShopGroupCategoryJsonList(){
        String groupCategoryList = "";
        String groupCategory = "";

        List<ShopGroup> shopGroups = new ArrayList<ShopGroup>();
        shopGroups = shopGroupCategoryService.getShopGroups();
        ObjectMapper mapper = new ObjectMapper();

        for(ShopGroup shopGroup : shopGroups){
            try {
                groupCategory = mapper.writeValueAsString(shopGroup).substring(0, mapper.writeValueAsString(shopGroup).length()-1) + ",\"categories\": " + mapper.writeValueAsString(shopGroupCategoryService.getShopCategoriesById(shopGroup.getId())) + ",\"newCategoryName\": \"\"" + "}";
            } catch (Exception e) {
                e.printStackTrace();
            }
            groupCategoryList = groupCategoryList + groupCategory + ",";
        }

        groupCategoryList = groupCategoryList.substring(0, groupCategoryList.length()-1);
        groupCategoryList = "[" + groupCategoryList + "]";

        return groupCategoryList;
    }
}
