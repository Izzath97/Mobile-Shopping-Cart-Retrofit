package com.apiit.izzath.brandslk.Models;


import java.util.List;

public class OrderItemWrapper {

    List<Cart> orderItemList;

    public List<Cart> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<Cart> orderItemList) {
        this.orderItemList = orderItemList;
    }
}
