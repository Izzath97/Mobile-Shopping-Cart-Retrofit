package com.apiit.izzath.wmad2.Models;

import com.orm.SugarRecord;

/**
 * Created by Izzath on 6/6/2018.
 */

public class Reviews extends SugarRecord<Reviews>{
    Register user;
    Product product;
    float value;
    String comment;

    public Reviews() {
    }

    public Reviews(Register user, Product product, float value, String comment) {
        this.user = user;
        this.product = product;
        this.value = value;
        this.comment = comment;
    }

    public Register getUser() {
        return user;
    }

    public Product getProduct() {
        return product;
    }

    public float getValue() {
        return value;
    }

    public String getComment() {
        return comment;
    }

    public void setUser(Register user) {
        this.user = user;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
