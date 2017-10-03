package cn.eleven.categorySecond.pojo;

import cn.eleven.category.pojo.Category;

/**
 * Created by User on 2017/10/3.
 */
public class CategorySecond {
    private Integer csid;
    private String csname;
    private Category category;

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
}
