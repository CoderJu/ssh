package cn.eleven.cart.pojo;

import cn.eleven.product.pojo.Product;

/**
 * 购物项对象
 * Created by User on 2017/10/6.
 */
public class CartItem {
    private Product product;
    private int count; //数量
    private double subtotal; //小计

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    //计算小计
    public double getSubtotal() {
        return count * product.getShop_price();
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}
