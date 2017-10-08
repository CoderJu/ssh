package cn.eleven.order.action;

import cn.eleven.cart.pojo.Cart;
import cn.eleven.cart.pojo.CartItem;
import cn.eleven.order.pojo.Order;
import cn.eleven.order.pojo.OrderItem;
import cn.eleven.order.service.OrderService;
import cn.eleven.user.pojo.User;
import cn.eleven.utils.PageBean;
import cn.eleven.utils.PaymentUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import java.io.IOException;
import java.util.Date;

/**
 * Created by User on 2017/10/6.
 */
public class OrderAction extends ActionSupport implements ModelDriven<Order>{

    private OrderService orderService;
    private Order order = new Order();
    private Integer page;
    private String pd_FrpId;

    // 接收付款成功后的参数:
    private String r3_Amt;
    private String r6_Order;

    public String getR3_Amt() {
        return r3_Amt;
    }

    public void setR3_Amt(String r3_Amt) {
        this.r3_Amt = r3_Amt;
    }

    public String getR6_Order() {
        return r6_Order;
    }

    public void setR6_Order(String r6_Order) {
        this.r6_Order = r6_Order;
    }

    public String getPd_FrpId() {
        return pd_FrpId;
    }

    public void setPd_FrpId(String pd_FrpId) {
        this.pd_FrpId = pd_FrpId;
    }

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

    public String payOrder(){
        //将订单存储到order表中
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>"+order.getOid());
        Order currOrder =  orderService.findByOid(order.getOid());
        currOrder.setAddr(order.getAddr());
        currOrder.setPhone(order.getPhone());
        currOrder.setName(order.getName());
        orderService.update(currOrder);

        // 2.完成付款:
        // 付款需要的参数:
        String p0_Cmd = "Buy"; // 业务类型:
        String p1_MerId = "10001126856";// 商户编号:
        String p2_Order = order.getOid().toString();// 订单编号:
        String p3_Amt = "0.01"; // 付款金额:
        String p4_Cur = "CNY"; // 交易币种:
        String p5_Pid = ""; // 商品名称:
        String p6_Pcat = ""; // 商品种类:
        String p7_Pdesc = ""; // 商品描述:
        String p8_Url = "http://127.0.0.1:8080/order_callBack.action"; // 商户接收支付成功数据的地址:
        String p9_SAF = ""; // 送货地址:
        String pa_MP = ""; // 商户扩展信息:
        String pd_FrpId = this.pd_FrpId;// 支付通道编码:
        String pr_NeedResponse = "1"; // 应答机制:
        String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl"; // 秘钥
        String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
                p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
                pd_FrpId, pr_NeedResponse, keyValue); // hmac
        // 向易宝发送请求:
        StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
        sb.append("p0_Cmd=").append(p0_Cmd).append("&");
        sb.append("p1_MerId=").append(p1_MerId).append("&");
        sb.append("p2_Order=").append(p2_Order).append("&");
        sb.append("p3_Amt=").append(p3_Amt).append("&");
        sb.append("p4_Cur=").append(p4_Cur).append("&");
        sb.append("p5_Pid=").append(p5_Pid).append("&");
        sb.append("p6_Pcat=").append(p6_Pcat).append("&");
        sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
        sb.append("p8_Url=").append(p8_Url).append("&");
        sb.append("p9_SAF=").append(p9_SAF).append("&");
        sb.append("pa_MP=").append(pa_MP).append("&");
        sb.append("pd_FrpId=").append(pd_FrpId).append("&");
        sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
        sb.append("hmac=").append(hmac);

        // 重定向:向易宝出发:
        try {
            ServletActionContext.getResponse().sendRedirect(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return NONE;
    }

    // 付款成功后跳转回来的路径:
    public String callBack(){
        // 修改订单的状态:
        Order currOrder = orderService.findByOid(Integer.parseInt(r6_Order));
        // 修改订单状态为2:已经付款:
        currOrder.setState(2);
        orderService.update(currOrder);
        this.addActionMessage("支付成功!订单编号为: "+r6_Order +" 付款金额为: "+r3_Amt);
        return "msg";
    }

    public String updateState(){
        order = orderService.findByOid(order.getOid());
        order.setState(4);
        orderService.update(order);
        return "updateState";
    }
}
