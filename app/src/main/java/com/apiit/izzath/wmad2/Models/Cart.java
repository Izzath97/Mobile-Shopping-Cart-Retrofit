package com.apiit.izzath.wmad2.Models;

import com.orm.SugarRecord;

/**
 * Created by Izzath on 5/13/2018.
 */

public class Cart extends SugarRecord<Cart> {
Register register;
Product product;
Long user;
int quantity;


    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public void setRegister(Register register) {
        this.register = register;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Register getRegister() {
        return register;
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


    public Cart(Register register, Product product, Long user,int quantity) {
        this.register = register;
        this.product = product;
        this.user=user;
        this.quantity = quantity;
    }

    public Cart() {
    }


}
