package cn.eleven.order.action;

import cn.eleven.cart.pojo.Cart;
import cn.eleven.cart.pojo.CartItem;
import cn.eleven.order.pojo.Order;
import cn.eleven.order.pojo.OrderItem;
import cn.eleven.order.service.OrderService;
import cn.eleven.user.pojo.User;
import cn.eleven.utils.PageBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import java.util.Date;

/**
 * Created by User on 2017/10/6.
 */
public class OrderAction extends ActionSupport implements ModelDriven<Order>{

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

    public String save(){
        //保存到数据库
        order.setOrdertime(new Date());
        order.setState(1); //1:未付款 2：已付款未发货，3：已发货未确认收货 4：交易结束
        Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
        if (cart == null){
            this.addActionError("您还没有购物，请先购物！");
            return "msg";
        }
        order.setTotal(cart.getTotal());
        for (CartItem cartItem: cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setCount(cartItem.getCount());
            orderItem.setSubtotal(cartItem.getSubtotal());
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setOrder(order);
            order.getOrderItems().add(orderItem);
        }
        User user = (User) ServletActionContext.getRequest().getSession().getAttribute("exitUser");
        if (user == null){
            this.addActionError("您还没有登陆，请登陆后再下单！");
            return "login";
        }
        order.setUser(user);
        orderService.save(order);
        //将订单对象显示到页面上
        cart.clearCart();
        return "saveSuccess";
    }

    public String findByUid(){
        User user = (User) ServletActionContext.getRequest().getSession().getAttribute("exitUser");
        PageBean<Order> pageBean =  orderService.fingByUid(user.getUid(),page);
        ServletActionContext.getContext().getValueStack().set("pageBean",pageBean);
        return "findByUidSuccess";
    }

    public String findByOid(){
        order =   orderService.findByOid(order.getOid());
        return "findByOidSuccess";
    }
}
