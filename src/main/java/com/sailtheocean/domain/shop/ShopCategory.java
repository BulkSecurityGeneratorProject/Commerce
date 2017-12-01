package com.sailtheocean.domain.shop;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by fan on 23/08/15.
 * Shop Category
 */
@Entity
@Table(name="shop_category")
public class ShopCategory  implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /** shop category name **/
    private String name;
    /** shop category type **/
    private String type;
    /** shop category sort order **/
    private Integer sortOrder;
    /** shop category belongs to shop group **/
    private ShopGroup shopGroup;
    /** shop category has shops **/
    private Set<ShopInfo> shops = new HashSet<ShopInfo>();

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    @Column(length=50,nullable=false,name="shop_category_name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Column(length=50,nullable=false,name="shop_category_type")
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    @Column(length=5,nullable=false,name="shop_group_sortorder")
    public Integer getSortOrder() {
        return sortOrder;
    }
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    @ManyToOne(cascade=CascadeType.REFRESH,optional=false)
    @JoinColumn(name="shopgroupid")
    public ShopGroup getShopGroup() {
        return shopGroup;
    }
    public void setShopGroup(ShopGroup shopGroup) {
        this.shopGroup = shopGroup;
    }
    @OneToMany(cascade=CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy="shopCategory")
    public Set<ShopInfo> getShops() {
        return shops;
    }
    public void setShops(Set<ShopInfo> shops) {
        this.shops = shops;
    }
}
