package cn.eleven.order.dao;

import cn.eleven.order.pojo.Order;
import cn.eleven.utils.PageHibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by User on 2017/10/6.
 */
@Transactional
public class OrderDao extends HibernateDaoSupport {

    public  void save(Order order) {
        this.getHibernateTemplate().save(order);
    }

    public Integer findByCount(Integer uid) {
        String hql = "select count(*) from Order o where o.user.uid = ?";
        List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql,uid);
        if (list != null && list.size()>0){
            return list.get(0).intValue();
        }
        return 0;
    }

    public List<Order> findByPageUid(Integer uid, int begin, int limit) {
        String hql = "from Order o where o.user.uid = ? order by ordertime";
        List<Order> list =  this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql,new Integer[]{uid},begin,limit));
        return list;
    }

    public Order findByOid(Integer oid) {
        return  this.getHibernateTemplate().get(Order.class,oid);
    }

    public void update(Order currOrder) {
        this.getHibernateTemplate().update(currOrder);
    }
}
