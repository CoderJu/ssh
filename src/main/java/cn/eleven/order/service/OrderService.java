package cn.eleven.order.service;

import cn.eleven.order.dao.OrderDao;
import cn.eleven.order.pojo.Order;
import cn.eleven.utils.PageBean;

import java.util.List;

/**
 * Created by User on 2017/10/6.
 */
public class OrderService {
    private OrderDao orderDao;

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void save(Order order) {
        orderDao.save(order);
    }

    public PageBean<Order> fingByUid(Integer uid, Integer page) {
        PageBean<Order> pageBean = new PageBean<Order>();
        pageBean.setPage(page);
        int limit = 4;
        pageBean.setLimit(limit);

        Integer totalCount = null;
        totalCount = orderDao.findByCount(uid);
        pageBean.setTotalCount(totalCount);

        Integer totalPage =null;
        if (totalCount % limit == 0){
            totalPage = totalCount / limit;
        }else {
            totalPage = totalCount / limit +1;
        }
        pageBean.setTotalPage(totalPage);
        int begin = (page-1)*limit;
        List<Order> list = orderDao.findByPageUid(uid,begin,limit);
        pageBean.setList(list);
        return pageBean;
    }

    public Order findByOid(Integer oid) {
        return orderDao.findByOid(oid);
    }
}
