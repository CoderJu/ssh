package cn.eleven.categorySecond.pojo;

import cn.eleven.category.pojo.Category;
import cn.eleven.product.pojo.Product;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by User on 2017/10/3.
 */
public class CategorySecond {
    private Integer csid;
    private String csname;
    private Category category;
    private Set<Product> products = new HashSet<Product>();

    public Integer getCsid() {
        return csid;
    }

    public void setCsid(Integer csid) {
        this.csid = csid;
    }

    public String getCsname() {
        return csname;
    }

    public void setCsname(String csname) {
        this.csname = csname;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
