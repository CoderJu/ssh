package cn.eleven.product.action;

import cn.eleven.product.pojo.Product;
import cn.eleven.product.service.ProductService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * Created by User on 2017/9/8.
 */
public class ProductAction extends ActionSupport implements ModelDriven<Product> {

    private Product product = new Product();
    private ProductService productService;

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
}
