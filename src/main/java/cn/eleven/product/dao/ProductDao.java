package cn.eleven.product.dao;


import cn.eleven.product.pojo.Product;
import cn.eleven.utils.PageHibernateCallback;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateCallback;
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

    public List<Product> findAllNew() {
        DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
        criteria.addOrder(Order.desc("pdate"));
        List<Product> list = (List<Product>) this.getHibernateTemplate().findByCriteria(criteria,0,10);
        return list;

    }

    public Product findById(Integer pid) {
        return this.getHibernateTemplate().get(Product.class,pid);
    }

    public int findCountByCid(Integer cid) {
        String hql = "select count(*) from Product p where p.categorySecond.category.cid = ?";
        List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql,cid);
        if (list != null && list.size()>0){
            return list.get(0).intValue();
        }
            return 0;
    }

    public List<Product> findByPageCid(Integer cid, int begin, int limit) {
        String hql = "select p from Product p join p.categorySecond cs join cs.category c  where c.cid =?";
        List<Product> list = this.getHibernateTemplate()
                .execute(new PageHibernateCallback<Product>(hql,new Object[]{cid},begin,limit));
        if (list != null && list.size() >0){
            return list;
        }
        return null;
    }

    public int findCountByCsid(Integer csid) {
        String hql ="select count(*) from Product p  where p.categorySecond.csid = ?";
        List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql,csid);
        if (list != null && list.size()>0){
            return list.get(0).intValue();
        }
        return 0;
    }

    public List<Product> findByPageCsid(Integer csid, int begin, int limit) {
        String hql = "select p from Product p join p.categorySecond cs where cs.csid = ?";
        List<Product> list =  this.getHibernateTemplate()
                .execute( new PageHibernateCallback<Product>(hql,new Object[]{csid},begin,limit));
        if (list != null && list.size() >0){
            return list;
        }
        return null;
    }
}
