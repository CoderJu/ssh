package cn.eleven.category.pojo;

import cn.eleven.categorySecond.pojo.CategorySecond;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by User on 2017/9/8.
 */
public class Category {
    private Integer cid;
    private String cname;
    //一级分类中存放二级分类的集合
    private Set<CategorySecond> categorySeconds = new HashSet<CategorySecond>();

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Set<CategorySecond> getCategorySeconds() {
        return categorySeconds;
    }

    public void setCategorySeconds(Set<CategorySecond> categorySeconds) {
        this.categorySeconds = categorySeconds;
    }


    @Override
    public String toString() {
        return "Category{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", categorySeconds=" + categorySeconds +
                '}';
    }
}
