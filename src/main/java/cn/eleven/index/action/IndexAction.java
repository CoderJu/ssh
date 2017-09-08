package cn.eleven.index.action;

import cn.eleven.category.pojo.Category;
import cn.eleven.category.service.CategoryService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;

/**
 * 首页访问的Action
 * Created by User on 2017/8/29.
 */
public class IndexAction extends ActionSupport{

    private CategoryService categoryService;

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public String execute() throws Exception {
        List<Category> categoryList =  categoryService.findAll();
        //将一级分类存入到session的泛微
        ActionContext.getContext().getSession().put("categoryList",categoryList);
        return "index";
    }

}
