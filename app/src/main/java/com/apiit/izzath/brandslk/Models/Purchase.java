package com.apiit.izzath.brandslk.Models;

import com.orm.SugarRecord;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by Izzath on 6/5/2018.
 */

public class Purchase extends SugarRecord<Purchase> {
    Date date;
    Time time;
    Cart cart;
    String number;
    String address;


    public Purchase() {
    }

    public Purchase(Date date, Time time, Cart cart, String number, String address) {
        this.date = date;
        this.time = time;
        this.cart = cart;
        this.number = number;
        this.address = address;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
