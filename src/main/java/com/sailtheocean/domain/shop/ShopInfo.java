package com.sailtheocean.domain.shop;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sailtheocean.domain.product.Brand;
import com.sailtheocean.domain.product.ProductInfo;
import com.sailtheocean.domain.product.ProductType;
import com.sailtheocean.domain.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by fan on 23/08/15.
 * Shop Information
 */
@Entity
@Table(name="shop")
public class ShopInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /** shop ID **/
    private Integer id;
    /** shop name **/
    private String name;
    /** shop valid start date **/
    private Date startDate = new Date();
    /** shop valid end date **/
    private Date endDate = new Date();
    /** shop create date **/
    private Date createdate = new Date();
    /** shop address **/
    private String address;
    /** shop description **/
    private String description;
    /** shop visibility **/
    private boolean visible = true;

    /** shop category **/
    private ShopCategory shopCategory;
    /** shop has brands **/
    private Set<Brand> brands = new HashSet<Brand>();
    /** shop has product categories **/
    private Set<ProductType> productTypes = new HashSet<ProductType>();
    /** shop has products **/
    private Set<ProductInfo> productInfos = new HashSet<ProductInfo>();
    /** shop employee **/
    private Set<User> users = new HashSet<User>();

    public ShopInfo(){}
    public ShopInfo(Integer id){
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    @Column(length=50,nullable=false,name="shop_name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Temporal(TemporalType.DATE)
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    @Temporal(TemporalType.DATE)
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    @Temporal(TemporalType.DATE)
    public Date getCreatedate() {
        return createdate;
    }
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
    @Column(length=50,nullable=false,name="shop_address")
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    @Column(length=300,name="shop_description")
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    @Column(nullable=false)
    public boolean isVisible() {
        return visible;
    }
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @JsonIgnore
    @ManyToOne(cascade=CascadeType.REFRESH,optional=false)
    @JoinColumn(name="categoryid")
    public ShopCategory getShopCategory() {
        return shopCategory;
    }
    public void setShopCategory(ShopCategory shopCategory) {
        this.shopCategory = shopCategory;
    }

    @JsonIgnore
    @OneToMany(mappedBy="shopInfo", fetch = FetchType.EAGER, cascade=CascadeType.REMOVE)
    public Set<Brand> getBrands() {
        return brands;
    }
    public void setBrands(Set<Brand> brands) {
        this.brands = brands;
    }

    @JsonIgnore
    @OneToMany(mappedBy="shopInfo", fetch = FetchType.EAGER, cascade=CascadeType.REMOVE)
    public Set<ProductType> getProductTypes() {
        return productTypes;
    }
    public void setProductTypes(Set<ProductType> productTypes) {
        this.productTypes = productTypes;
    }

    @JsonIgnore
    @OneToMany(mappedBy="shopInfo", fetch = FetchType.EAGER, cascade=CascadeType.REMOVE)
    public Set<ProductInfo> getProductInfos() {
        return productInfos;
    }
    public void setProductInfos(Set<ProductInfo> productInfos) {
        this.productInfos = productInfos;
    }

    @JsonIgnore
    @OneToMany(mappedBy="shopInfo", fetch = FetchType.EAGER, cascade=CascadeType.REMOVE)
    public Set<User> getUsers() {
        return users;
    }
    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
