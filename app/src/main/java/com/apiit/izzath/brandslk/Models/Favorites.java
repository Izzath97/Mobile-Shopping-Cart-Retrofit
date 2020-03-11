package com.apiit.izzath.brandslk.Models;

import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

public class Favorites extends SugarRecord<Favorites> {
    @SerializedName("product")
    Product product;
    @SerializedName("user")
    Register user;

    public Favorites() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Register getUser() {
        return user;
    }

    public void setUser(Register user) {
        this.user = user;
    }

    public Favorites(Product product, Register user) {
        this.product = product;
        this.user = user;
    }
}
