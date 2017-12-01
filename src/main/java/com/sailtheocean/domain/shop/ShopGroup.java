package com.sailtheocean.domain.shop;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by fan on 23/08/15.
 * Shop Group
 */
@Entity
@Table(name="shop_group")
public class ShopGroup  implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /** shop group name **/
    private String name;
    /** shop group type **/
    private String type;
    /** shop group sort order **/
    private Integer sortOrder;
    /** shop group is editing or not **/
    private Boolean editing = false;
    /** shop group has shop categories **/
    private Set<ShopCategory> categories = new HashSet<ShopCategory>();

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    @Column(length=50,nullable=false,name="shop_group_name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Column(length=50,nullable=false,name="shop_group_type")
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
    @Column(nullable=false)
    public Boolean getEditing() {
        return editing;
    }
    public void setEditing(Boolean editing) {
        this.editing = editing;
    }

    @JsonIgnore
    @OneToMany(mappedBy="shopGroup", cascade=CascadeType.REMOVE)
    public Set<ShopCategory> getCategories() {
        return categories;
    }
    public void setCategories(Set<ShopCategory> categories) {
        this.categories = categories;
    }
}
