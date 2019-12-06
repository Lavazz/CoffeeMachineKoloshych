package by.trjava.kaloshych.builder.impl;

import by.trjava.kaloshych.builder.OrderBuilder;
import by.trjava.kaloshych.entity.CartUser;
import by.trjava.kaloshych.entity.Order;

import java.util.Date;

public class OrderBuilderImpl implements OrderBuilder {
    private int idOrder;
    private CartUser cartUser;
    private Date dateOrder;
    private double totalCost;

    public OrderBuilderImpl(){}

    public OrderBuilderImpl(int idOrder){
        this.idOrder=idOrder;
    }

    @Override
   public Order build(){
        Order order=new Order();
        order.setIdOrder(idOrder);
        order.setCartUser(cartUser);
        order.setDateOrder(dateOrder);
        order.setTotalCost(totalCost);
        return order;
   }

    @Override
    public OrderBuilder withCartUser(CartUser cartUser){
        this.cartUser=cartUser;
        return this;
    }

    @Override
    public OrderBuilder withDateOrder(Date dateOrder){
        this.dateOrder=dateOrder;
        return this;
    }

    @Override
    public OrderBuilder withTotalCost(double totalCost){
        this.totalCost=totalCost;
        return this;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public CartUser getCartUser() {
        return cartUser;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public double getTotalCost() {
        return totalCost;
    }
}
