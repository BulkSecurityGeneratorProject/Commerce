package com.sailtheocean.domain.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sailtheocean.domain.shop.ShopInfo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by fan on 24/08/15.
 * Product Info
 */
@Entity
public class ProductInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /** product code **/
    private String code;
    /** product name **/
    private String name;
    /** product brand **/
    private Brand brand;
    /** product model **/
    private String model;
    /** product base price **/
    private float baseprice;
    /** product market price **/
    private float marketprice;
    /** product sell price **/
    private float sellprice;
    /** product weight, unit:gram **/
    private Integer weight;
    /** product description **/
    private String description;
    /** product buy explain **/
    private String buyexplain;
    /** product visible **/
    private boolean visible = true;
    /** product type **/
    private ProductType producttype;
    /** product create date **/
    private Date createdate = new Date();
    /** product click count **/
    private Integer clickcount = 1;
    /** product sell count **/
    private Integer sellcount = 0;
    /** product commend **/
    private boolean commend = false;
    /** product sex request **/
    private Sex sexrequest = Sex.NONE;
    /** product style **/
    private Set<ProductStyle> styles = new HashSet<ProductStyle>();
    /** products belong to shop **/
    private ShopInfo shopInfo;


    public ProductInfo() {}
    public ProductInfo(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    @Column(length=30)
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    @Column(length=50, nullable=false)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name="brandid")
    public Brand getBrand() {
        return brand;
    }
    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @Column(length=20)
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }

    @Column(nullable=false)
    public float getBaseprice() {
        return baseprice;
    }
    public void setBaseprice(float baseprice) {
        this.baseprice = baseprice;
    }

    @Column(nullable=false)
    public float getMarketprice() {
        return marketprice;
    }
    public void setMarketprice(float marketprice) {
        this.marketprice = marketprice;
    }

    @Column(nullable=false)
    public float getSellprice() {
        return sellprice;
    }
    public void setSellprice(float sellprice) {
        this.sellprice = sellprice;
    }
    public Integer getWeight() {
        return weight;
    }
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Column(nullable=false, length=250)
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @Column(length=30)
    public String getBuyexplain() {
        return buyexplain;
    }
    public void setBuyexplain(String buyexplain) {
        this.buyexplain = buyexplain;
    }

    @Column(nullable=false)
    public boolean isVisible() {
        return visible;
    }
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @ManyToOne(cascade=CascadeType.REFRESH,optional=false)
    @JoinColumn(name="typeid")
    public ProductType getProducttype() {
        return producttype;
    }
    public void setProducttype(ProductType producttype) {
        this.producttype = producttype;
    }

    @Temporal(TemporalType.DATE)
    public Date getCreatedate() {
        return createdate;
    }
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    @Column(nullable=false)
    public Integer getClickcount() {
        return clickcount;
    }
    public void setClickcount(Integer clickcount) {
        this.clickcount = clickcount;
    }

    @Column(nullable=false)
    public Integer getSellcount() {
        return sellcount;
    }
    public void setSellcount(Integer sellcount) {
        this.sellcount = sellcount;
    }

    @Column(nullable=false)
    public boolean isCommend() {
        return commend;
    }
    public void setCommend(boolean commend) {
        this.commend = commend;
    }

    @Enumerated(EnumType.STRING)
    @Column(length=5,nullable=false)
    public Sex getSexrequest() {
        return sexrequest;
    }
    public void setSexrequest(Sex sexrequest) {
        this.sexrequest = sexrequest;
    }

    @JsonIgnore
    @OneToMany(cascade={CascadeType.REMOVE,CascadeType.PERSIST} ,mappedBy="product")
    public Set<ProductStyle> getStyles() {
        return styles;
    }
    public void setStyles(Set<ProductStyle> styles)
    {
        this.styles = styles;
    }

    @ManyToOne(cascade=CascadeType.REFRESH,optional=false)
    @JoinColumn(name="shopinfoid")
    public ShopInfo getShopInfo() {
        return shopInfo;
    }
    public void setShopInfo(ShopInfo shopInfo) {
        this.shopInfo = shopInfo;
    }
}
