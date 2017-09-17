package cn.eleven.index.action;

import cn.eleven.category.pojo.Category;
import cn.eleven.category.service.CategoryService;
import cn.eleven.product.pojo.Product;
import cn.eleven.product.service.ProductService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;

/**
 * 首页访问的Action
 * Created by User on 2017/8/29.
 */
public class IndexAction extends ActionSupport{

    private CategoryService categoryService;
    private ProductService productService;

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public String execute() throws Exception {
        List<Category> categoryList =  categoryService.findAll();
        //将一级分类存入到session的泛微
        ActionContext.getContext().getSession().put("categoryList",categoryList);
        List<Product> hotProductsList =  productService.findAllHot();
        //存到stack中
        ActionContext.getContext().getValueStack().set("hotProductsList",hotProductsList);
        return "index";
    }

}
