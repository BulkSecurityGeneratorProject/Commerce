package com.sailtheocean.domain.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sailtheocean.domain.shop.ShopInfo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by fan on 24/08/15.
 * Product Brand
 */
@Entity
public class Brand implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /** brand name **/
    private String name;
    /** brand visible **/
    private Boolean visible = true;
    /** brand logo img path eg:/images/brand/2015/07/20/ooo.gif **/
    private String logopath;
    /** brand create Date **/
    private Date createDate = new Date();
    /** brands belong to shop **/
    private ShopInfo shopInfo;

    public Brand(){}
    public Brand(Integer id){}
    public Brand(String name, String logopath) {
        this.name = name;
        this.logopath = logopath;
    }

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    @Column(length=40, nullable=false)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable=false)
    public Boolean getVisible() {
        return visible;
    }
    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    @Column(length=300)
    public String getLogopath() {
        return logopath;
    }
    public void setLogopath(String logopath) {
        this.logopath = logopath;
    }

    @Temporal(TemporalType.DATE)
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @JsonIgnore
    @ManyToOne(cascade=CascadeType.REFRESH,optional=false)
    @JoinColumn(name="shopinfoid")
    public ShopInfo getShopInfo() {
        return shopInfo;
    }
    public void setShopInfo(ShopInfo shopInfo) {
        this.shopInfo = shopInfo;
    }
}
