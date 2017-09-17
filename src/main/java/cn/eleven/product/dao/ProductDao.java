package cn.eleven.product.dao;


import cn.eleven.product.pojo.Product;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created by User on 2017/9/8.
 */
public class ProductDao  extends HibernateDaoSupport{

    public List<Product> findAllHot() {
        DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
        criteria.add(Restrictions.eq("is_hot",1));
        criteria.addOrder(Order.asc("pdate"));
        List<Product> list = (List<Product>) this.getHibernateTemplate().findByCriteria(criteria,0,10);
        return list;
    }
}
