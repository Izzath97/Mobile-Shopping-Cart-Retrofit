package com.apiit.izzath.brandslk.Models;


public class Cart  {
private int id;
private Register user;
private Product product;
private String status;
private int quantity;
private Order order;



    public Register getUser() {
        return user;
    }

    public void setUser(Register user) {
        this.user = user;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRegister(Register register) {
        this.user = register;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Register getRegister() {
        return user;
    }

    public Product getProduct() {
        return product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public Cart(Register register, Product product, int quantity, String status) {
        this.user = register;
        this.product = product;
        this.quantity = quantity;
        this.status=status;
    }

    public Cart() {
    }


}
