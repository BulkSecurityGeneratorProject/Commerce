package com.sailtheocean.domain.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sailtheocean.domain.shop.ShopInfo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by fan on 24/08/15.
 * Product Type
 */
@Entity
public class ProductType implements Serializable {

    private static final long serialVersionUID = 1L;

    /** type id **/
    private Integer id;
    /** type name **/
    private String name;
    /** type description **/
    private String note;
    /** type editing **/
    private Boolean editing = false;
    /** type adding **/
    private Boolean adding = false;
    /** type visible **/
    private Boolean visible = true;
    /** child type **/
    private Set<ProductType> childtypes = new HashSet<ProductType>();
    /** father type **/
    private ProductType parent;
    /** products belong to type **/
    private Set<ProductInfo> products = new HashSet<ProductInfo>();
    /** product types belong to shop **/
    private ShopInfo shopInfo;

    public ProductType() {}
    public ProductType(Integer id) {
        this.id = id;
    }
    public ProductType(String name, String note) {
        this.name = name;
        this.note = note;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    @Column(length=36, nullable=false)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(length=200)
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }

    @Column(nullable=false)
    public Boolean getEditing() {
        return editing;
    }
    public void setEditing(Boolean editing) {
        this.editing = editing;
    }

    @Column(nullable=false)
    public Boolean getAdding() {
        return adding;
    }
    public void setAdding(Boolean adding) {
        this.adding = adding;
    }

    @Column(nullable=false)
    public Boolean getVisible() {
        return visible;
    }
    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    @OneToMany(cascade={CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy="parent", fetch = FetchType.EAGER)
    public Set<ProductType> getChildtypes() {
        return childtypes;
    }
    public void setChildtypes(Set<ProductType> childtypes) {
        this.childtypes = childtypes;
    }

    @JsonIgnore
    @ManyToOne(cascade=CascadeType.REFRESH, optional=true)
    @JoinColumn(name="parentid")
    public ProductType getParent() {
        return parent;
    }
    public void setParent(ProductType parent) {
        this.parent = parent;
    }

    @JsonIgnore
    @OneToMany(mappedBy="producttype", cascade=CascadeType.REMOVE)
    public Set<ProductInfo> getProducts() {
        return products;
    }
    public void setProducts(Set<ProductInfo> products) {
        this.products = products;
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
