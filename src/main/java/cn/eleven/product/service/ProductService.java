package cn.eleven.product.service;

import cn.eleven.product.dao.ProductDao;
import cn.eleven.product.pojo.Product;
import cn.eleven.utils.PageBean;
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

    public List<Product> findAllNew() {
        return productDao.findAllNew();
    }

    public Product findById(Integer pid) {
        return productDao.findById(pid);
    }

    public PageBean<Product> findByPageByCid(Integer cid, int page) {
        PageBean<Product> pageBean = new PageBean<Product>();
        int limit = 8;
        pageBean.setLimit(limit);
        pageBean.setPage(page);
        int totalCount = productDao.findCountByCid(cid);
        pageBean.setTotalCount(totalCount);
        int totalPage = 0;
       // Math.ceil();
        if (totalCount % limit == 0){
            totalPage = totalCount / limit;
        }else{
            totalPage = totalCount / limit + 1;
        }
        pageBean.setTotalPage(totalPage);
        //开始
        int begin = (page - 1 )* limit;

        List<Product> list = productDao.findByPageCid(cid,begin,limit);
        pageBean.setList(list);
        return pageBean;
    }
}
