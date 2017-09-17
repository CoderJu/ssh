package cn.eleven.product.service;

import cn.eleven.product.dao.ProductDao;
import cn.eleven.product.pojo.Product;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by User on 2017/9/8.
 */
@Transactional
public class ProductService {

    private ProductDao productDao;

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }


    public List<Product> findAllHot() {
        return productDao.findAllHot();
    }
}
