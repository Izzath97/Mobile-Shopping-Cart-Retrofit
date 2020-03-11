package com.apiit.izzath.brandslk.Models;

import com.orm.SugarRecord;


public class Reviews extends SugarRecord<Reviews>{
    int rid;
    Register user;
    Product product;
    float value;
    String review;
    String date;

    public Reviews() {
    }

    public Reviews(Register user, Product product, float value, String comment,String Date) {
        this.user = user;
        this.product = product;
        this.value = value;
        this.review = comment;
        this.date=Date;
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

    public void setUser(Register user) {
        this.user = user;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setValue(float value) {
        this.value = value;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
