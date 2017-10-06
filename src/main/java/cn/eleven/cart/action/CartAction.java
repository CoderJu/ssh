package cn.eleven.cart.action;

import cn.eleven.cart.pojo.Cart;
import cn.eleven.cart.pojo.CartItem;
import cn.eleven.product.service.ProductService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

/**
 * Created by User on 2017/10/6.
 */
public class CartAction extends ActionSupport  {
    private Integer pid;
    private Integer count;
    private ProductService productService;
    private Cart cart;

    public Integer getPid() {
        return pid;
    }
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public ProductService getProductService() {
        return productService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public String addCart() {
        CartItem cartItem = new CartItem();
        cartItem.setCount(count);
        cartItem.setProduct(productService.findById(pid));
        Cart cart = getCart();
        cart.addCart(cartItem);
        return "addCart";
    }
    public String clearCart() {
        Cart cart = getCart();
        cart.clearCart();
        return "clearCart";
    }

    public String deleteCart(){
        Cart cart = getCart();
        cart.removeCart(pid);
        return "deleteCart";
    }

    public String showCart(){
        return "showCart";
    }

    public Cart getCart() {
      Cart cart = (Cart) ServletActionContext.getRequest().getSession()
                .getAttribute("cart");
      if (cart == null){
          cart  = new Cart();
          ServletActionContext.getRequest().getSession().setAttribute("cart",cart);
      }
        return cart;
    }
}
