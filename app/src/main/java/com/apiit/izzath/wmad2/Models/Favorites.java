package com.apiit.izzath.wmad2.Models;

import com.orm.SugarRecord;

/**
 * Created by Izzath on 6/10/2018.
 */

public class Favorites extends SugarRecord<Favorites> {

    Product product;
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
