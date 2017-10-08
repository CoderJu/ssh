package cn.eleven.product.action;

import cn.eleven.category.pojo.Category;
import cn.eleven.category.service.CategoryService;
import cn.eleven.categorySecond.pojo.CategorySecond;
import cn.eleven.product.pojo.Product;
import cn.eleven.product.service.ProductService;
import cn.eleven.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.xml.internal.ws.api.policy.PolicyResolver;
import org.apache.struts2.ServletActionContext;

import java.util.List;

/**
 * Created by User on 2017/9/8.
 */
public class ProductAction extends ActionSupport implements ModelDriven<Product> {

    private Product product = new Product();
    private ProductService productService;
    private CategoryService categoryService;
    private Integer cid;
    private int page;
    private Integer csid;

    public Integer getCsid() {
        return csid;
    }

    public void setCsid(Integer csid) {
        this.csid = csid;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    public ProductService getProductService() {
        return productService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public Product getModel() {
        return product;
    }

    public String findById(){
        product = productService.findById(product.getPid());
        return "findByPid";
    }

    public String findByCid(){
        Object set = ActionContext.getContext().getSession().get("categoryList");
        PageBean<Product> pageBean = productService.findByPageByCid(cid,page);
       // List<Category> categories = categoryService.findAll();//获取一级分类
        //pageBean存入值栈
        ActionContext.getContext().getValueStack().set("pageBean",pageBean);
        return "findByCid";
    }

    public String findByCsid(){
        PageBean<Product> pageBean = productService.findByPageByCsid(csid,page);
        ActionContext.getContext().getValueStack().set("pageBean",pageBean);
        return "findByCsid";
    }
}
