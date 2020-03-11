package com.apiit.izzath.brandslk.Models;

import java.util.List;

public class Order {

    private Integer id;
    private String order_date;
    private String status;
    private String comment;
    private Register user;
    private List<Cart> orderItems;


    public Order() {
    }

    public Order(String order_date, String status, String comment, Register user, List<Cart> orderItems) {
        this.order_date = order_date;
        this.status = status;
        this.comment = comment;
        this.user = user;
        this.orderItems = orderItems;
    }

    public Order(List<Cart> orderItems) {
        this.orderItems = orderItems;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Register getUser() {
        return user;
    }

    public void setUser(Register user) {
        this.user = user;
    }

    public List<Cart> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<Cart> orderItems) {
        this.orderItems = orderItems;
    }

}
