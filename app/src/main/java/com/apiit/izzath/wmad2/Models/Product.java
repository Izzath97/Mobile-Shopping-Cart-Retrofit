package com.apiit.izzath.wmad2.Models;

import com.orm.SugarRecord;

import java.util.ArrayList;

/**
 * Created by Izzath on 4/16/2018.
 */

public class Product extends SugarRecord<Product> {

    private String Name,ShortDescription,LongDescription,Catagory;
    private double Price;
    private int Quantity;
    private boolean Active;
    private String ScaledImage;
    private String FullImage;
    ArrayList<String> Tag;



    public Product( String name, String shortDescription, String longDescription, String catagory, double price, int quantity, boolean active, String scaledImage, String fullImage) {

        Name = name;
        ShortDescription = shortDescription;
        LongDescription = longDescription;
        Catagory = catagory;
        Price = price;
        Quantity = quantity;
        Active = active;
        ScaledImage = scaledImage;
        FullImage = fullImage;
    }

    public Product() {
    }

    public ArrayList<String> getTag() {
        return Tag;
    }

    public void setTag(ArrayList<String> tag) {
        Tag = tag;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setShortDescription(String shortDescription) {ShortDescription = shortDescription;}

    public void setLongDescription(String longDescription) {
        LongDescription = longDescription;
    }

    public void setCatagory(String catagory) {
        Catagory = catagory;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public void setActive(boolean active) {
        Active = active;
    }

    public void setScaledImage(String scaledImage) {
        ScaledImage = scaledImage;
    }

    public void setFullImage(String fullImage) {
        FullImage = fullImage;
    }



    public String getName() {
        return Name;
    }

    public String getShortDescription() {
        return ShortDescription;
    }

    public String getLongDescription() {
        return LongDescription;
    }

    public String getCatagory() {
        return Catagory;
    }

    public double getPrice() {
        return Price;
    }

    public int getQuantity() {
        return Quantity;
    }

    public boolean isActive() {
        return Active;
    }

    public String getScaledImage() {
        return ScaledImage;
    }

    public String getFullImage() {
        return FullImage;
    }
}
