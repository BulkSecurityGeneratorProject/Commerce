package com.sailtheocean.domain.product;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by fan on 24/08/15.
 * Product Style
 */
@Entity
public class ProductStyle implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /** product style name **/
    private String name;
    /** product style image name **/
    private String imagename;
    /** product style visible **/
    private Boolean visible = true;
    /** product information **/
    private ProductInfo product;

    public ProductStyle() {
    }
    public ProductStyle(Integer id){
        this.id = id;
    }
    public ProductStyle(String name, String imagename) {
        this.name = name;
        this.imagename = imagename;
    }

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    @Column(length=30,nullable=false)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(length=100, nullable=false)
    public String getImagename() {
        return imagename;
    }
    public void setImagename(String imagename) {
        this.imagename = imagename;
    }

    @Column(nullable=false)
    public Boolean getVisible() {
        return visible;
    }
    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    @ManyToOne(cascade= CascadeType.REFRESH,optional=false)
    @JoinColumn(name="productid")
    public ProductInfo getProduct() {
        return product;
    }
    public void setProduct(ProductInfo product) {
        this.product = product;
    }
}
