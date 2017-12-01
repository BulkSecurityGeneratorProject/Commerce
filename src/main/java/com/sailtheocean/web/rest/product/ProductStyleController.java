package com.sailtheocean.web.rest.product;

import com.sailtheocean.domain.product.ProductInfo;
import com.sailtheocean.domain.product.ProductStyle;
import com.sailtheocean.service.product.IProductStyleService;
import org.springframework.beans.factory.annotation.Autowired;
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
 * Created by fan on 26/08/15.
 */
@RestController
@RequestMapping("/product")
public class ProductStyleController {

    @Autowired
    IProductStyleService productStyleService;

    /**
     * get product style list by product
     * @param productInfo
     * @return
     */
    @RequestMapping(value = "/getProductStyleListByProduct", method = RequestMethod.POST)
    public List<ProductStyle> getStyleListByProduct(@RequestBody ProductInfo productInfo){

        ProductInfo product = new ProductInfo(productInfo.getId());

        return productStyleService.getStylesByProduct(product);
    }

    /**
     * add product style
     * @param logofile
     * @param name
     * @param productid
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/addStyle", method = RequestMethod.POST)
    public List<ProductStyle> addStyle(@RequestParam("file") MultipartFile logofile, @RequestParam("name") String name, @RequestParam("product") Integer productid, HttpServletRequest request, HttpServletResponse response){

        ProductStyle productStyle = new ProductStyle();

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
                productStyle.setName(name);
            }

            productStyle.setImagename(imagename);
            productStyle.setProduct(new ProductInfo(productid));

            System.out.println(logopath);
            System.out.println(logorealpathdir+"/"+imagename);
        }
        productStyleService.addStyle(productStyle);

        return getStyleListByProduct(new ProductInfo(productid));
    }

    /**
     * edit product style
     * @param logofile
     * @param name
     * @param productid
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/editStyle", method = RequestMethod.POST)
    public List<ProductStyle> editStyle(@RequestParam("file") MultipartFile logofile, @RequestParam("id") Integer styleid, @RequestParam("name") String name, @RequestParam("product") Integer productid, HttpServletRequest request, HttpServletResponse response){

        ProductStyle productStyle = productStyleService.findStyleByID(styleid);

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
                productStyle.setName(name);
            }

            productStyle.setImagename(imagename);
            productStyle.setProduct(new ProductInfo(productid));

            System.out.println(logopath);
            System.out.println(logorealpathdir+"/"+imagename);
        }
        productStyleService.addStyle(productStyle);

        return getStyleListByProduct(new ProductInfo(productid));
    }

    /**
     * delete product style
     * @param productStyle
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/deleteStyle", method = RequestMethod.POST)
    public List<ProductStyle> deleteStyle(@RequestBody ProductStyle productStyle, HttpServletRequest request, HttpServletResponse response){

        ProductStyle style = productStyleService.findStyleByID(productStyle.getId());

        ProductInfo productInfo = style.getProduct();

        productStyleService.deleteStyle(style);

        return getStyleListByProduct(productInfo);
    }

    /**
     * change product style visibility
     * @param productStyle
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/changeStyleVisible", method = RequestMethod.POST)
    public List<ProductStyle> changeStyleVisible(@RequestBody ProductStyle productStyle, HttpServletRequest request, HttpServletResponse response){

        ProductStyle style = productStyleService.findStyleByID(productStyle.getId());

        ProductInfo productInfo = style.getProduct();

        style.setVisible(productStyle.getVisible());

        productStyleService.addStyle(style);

        return getStyleListByProduct(productInfo);
    }
}
