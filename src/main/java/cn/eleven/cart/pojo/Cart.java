package cn.eleven.cart.pojo;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 购物车对象
 * Created by User on 2017/10/6.
 */
public class Cart {
    //Map的key就是商品的ID value是购物项
    private Map<Integer,CartItem> map = new LinkedHashMap<Integer, CartItem>();
    private double total;

    public double getTotal() {
        return total;
    }
    public Collection<CartItem> getCartItems(){
        return map.values();
    }

    /***
     * 购物车的功能
     * 1、添加购物车
     * 2、移除购物项
     * 3、清空购物车
     */
    public void addCart(CartItem cartItem){
        //判断是否存在
        //存在就不需要添加，加上对应数据，如果不存在就直接添加
       Integer pid =  cartItem.getProduct().getPid();
       if (map.containsKey(pid)){
          CartItem cartItem_ =  map.get(pid);
           cartItem_.setCount((cartItem.getCount()+cartItem_.getCount()));
       }else {
           map.put(pid,cartItem);
       }
       total += cartItem.getSubtotal();

    }

    /**
     * 移除购物项
     * @param pid
     */
    public void removeCart(Integer pid){
        //将购物项移除购物车 移除时会返回对象
        CartItem cartItem =  map.remove(pid);
        //总计-小计为新的总计
        double subTotal = cartItem.getSubtotal();
        total -= subTotal;
    }

    /**
     * 清空购物车
     * 清空所有购物项，将总计设置为0
     */
    public void clearCart(){
        //清除购物项
        map.clear();
        //总计设置为0
        total=0;
    }


}
