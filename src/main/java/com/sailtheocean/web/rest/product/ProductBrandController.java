package com.sailtheocean.web.rest.product;

import com.sailtheocean.domain.product.Brand;
import com.sailtheocean.domain.shop.ShopInfo;
import com.sailtheocean.service.product.IProductBrandService;
import com.sailtheocean.service.shop.IShopInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * Created by fan on 24/08/15.
 */
@RestController
@RequestMapping("/product")
public class ProductBrandController {

    @Autowired
    IProductBrandService productBrandService;
    @Autowired
    IShopInfoService shopInfoService;

    /**
     * get product brand list
     * @param shopinfoid
     * @return
     */
    @RequestMapping(value = "/getProductBrandList", method = RequestMethod.GET)
    public List<Brand> getBrandList(@RequestParam("shopinfoid")Integer shopinfoid) {
        return productBrandService.getBrands(shopinfoid);
    }

    /**
     * get pagination product brand list
     * @param shopinfoid
     * @param pageNumber
     * @return
     */
    @RequestMapping(value = "/getPaginationBrandList", method = RequestMethod.GET)
    public Page<Brand> getPaginationBrandList(@RequestParam("shopinfoid") Integer shopinfoid, @RequestParam("pageNumber") Integer pageNumber){

        ShopInfo shopInfo = shopInfoService.getShop(shopinfoid);

        return productBrandService.getPaginationBrands(shopInfo, pageNumber);
    }

    /**
     * add product brand
     * @param logofile
     * @param shopinfoid
     * @param name
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/addBrand", method = RequestMethod.POST)
    public Page<Brand> addBrand(@RequestParam("file") MultipartFile logofile, @RequestParam("shopinfoid") Integer shopinfoid, @RequestParam("name") String name, HttpServletRequest request, HttpServletResponse response){

        Brand brand = new Brand();
        ShopInfo shopInfo = new ShopInfo();

        String logopathdir ="/images/logo";
        String logorealpathdir = request.getSession().getServletContext().getRealPath(logopathdir);
        String logofileFileName = logofile.getOriginalFilename();

        byte[] bytes;

        if(logofile!=null){
            File logosavedir = new File(logorealpathdir);
            if(!logosavedir.getParentFile().exists())
                logosavedir.mkdirs();
            String ext = logofileFileName.substring(logofileFileName.lastIndexOf("."));
            String imagename = UUID.randomUUID().toString() + ext;
            String logopath = logopathdir + "/" + imagename;

            File savefile = new File(logosavedir, imagename);

            try {
                bytes = logofile.getBytes();
                BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(savefile));
                stream.write(bytes);
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(name!=null&&!name.trim().equals("")){
                brand.setName(name);
            }

            if(shopinfoid!=null){
                shopInfo = shopInfoService.getShop(shopinfoid);
                brand.setShopInfo(shopInfo);
            }

            brand.setLogopath(logopath);

            System.out.println(logopath);
            System.out.println(logorealpathdir + "/" + imagename);
        }

        productBrandService.addProductBrand(brand);

        return getPaginationBrandList(shopinfoid, 1);
    }

    /**
     * edit product brand
     * @param logofile
     * @param shopinfoid
     * @param id
     * @param name
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/editBrand", method = RequestMethod.POST)
    public Page<Brand> editBrand(@RequestParam("file") MultipartFile logofile, @RequestParam("shopinfoid") Integer shopinfoid, @RequestParam("id") Integer id, @RequestParam("name") String name, HttpServletRequest request, HttpServletResponse response){

        Brand brand = productBrandService.getBrand(id);
        ShopInfo shopInfo = new ShopInfo();

        String logopathdir ="/images/logo";
        String logorealpathdir = request.getSession().getServletContext().getRealPath(logopathdir);
        String logofileFileName = logofile.getOriginalFilename();

        byte[] bytes;

        if(logofile!=null){
            File logosavedir = new File(logorealpathdir);
            if(!logosavedir.getParentFile().exists())
                logosavedir.mkdirs();
            String ext = logofileFileName.substring(logofileFileName.lastIndexOf("."));
            String imagename = UUID.randomUUID().toString() + ext;
            String logopath = logopathdir + "/" + imagename;

            File savefile = new File(logosavedir, imagename);

            try {
                bytes = logofile.getBytes();
                BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(savefile));
                stream.write(bytes);
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(name!=null&&!name.trim().equals("")){
                brand.setName(name);
            }

            if(shopinfoid!=null){
                shopInfo = shopInfoService.getShop(shopinfoid);
                brand.setShopInfo(shopInfo);
            }

            brand.setLogopath(logopath);

            System.out.println(logopath);
            System.out.println(logorealpathdir+"/"+imagename);
        }

        productBrandService.addProductBrand(brand);

        return getPaginationBrandList(shopinfoid, 1);
    }

    /**
     * delete product brand
     * @param brand
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/deleteBrand", method = RequestMethod.POST)
    public Page<Brand> deleteBrand(@RequestBody Brand brand, HttpServletRequest request, HttpServletResponse response){

        System.out.println("Rand needed to be deleted is :" + brand);

        Brand newBrand =  productBrandService.getBrand(brand.getId());

        productBrandService.deleteBrand(brand.getId());

        return getPaginationBrandList(newBrand.getShopInfo().getId(), 1);
    }
}
