package com.apiit.izzath.wmad2.Models;

import com.orm.SugarRecord;

/**
 * Created by Izzath on 5/14/2018.
 */

public class TagProduct extends SugarRecord<TagProduct> {

    Product product;
    private String tag;

    public TagProduct(Product product, String tag) {
        this.product = product;
        this.tag = tag;
    }

    public TagProduct() {
    }

    public Product getProduct() {
        return product;
    }

    public String getTag() {
        return tag;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
