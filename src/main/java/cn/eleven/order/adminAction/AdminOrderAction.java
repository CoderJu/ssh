package cn.eleven.order.adminAction;

import cn.eleven.order.pojo.Order;
import cn.eleven.order.pojo.OrderItem;
import cn.eleven.order.service.OrderService;
import cn.eleven.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import java.util.List;

/**
 * Created by User on 2017/10/8.
 */
public class AdminOrderAction extends ActionSupport implements ModelDriven<Order> {
    private OrderService orderService;
    private Order order = new Order();
    private Integer page;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Order getModel() {
        return order;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public String findAllByPage(){
        PageBean<Order> pageBean = orderService.findAllByPage(page);
        ActionContext.getContext().getValueStack().set("pageBean",pageBean);
        return "findAllByPage";
    }

    public String findOrderItem(){
        System.out.println(">>>>>>>>>>>>>>>>>>>>"+order.getOid());
        List<OrderItem> list =  orderService.findOrderItem(order.getOid());
        ActionContext.getContext().getValueStack().set("list",list);
        return "findOrderItem";
    }

    public String updateState(){
       order =  orderService.findByOid(order.getOid());
       order.setState(3);
       orderService.update(order);
       return "updateState";
    }

    public String findByState(){
        PageBean<Order> pageBean = orderService.findAllByPageState(page,order.getState());
        ActionContext.getContext().getValueStack().set("pageBean",pageBean);
        return "findByState";
    }
}
