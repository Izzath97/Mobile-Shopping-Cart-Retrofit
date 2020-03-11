package com.apiit.izzath.brandslk.Models;

import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;


public class Product extends SugarRecord<Product> {
    @SerializedName("pid")
    private int pid;
    @SerializedName("productName")
    private String Name;
    @SerializedName("shortDescription")
   private String ShortDescription;
    @SerializedName("longDescription")
   private String LongDescription;
    @SerializedName("catagory")
   private String Catagory;
    @SerializedName("price")
    private double Price;
    @SerializedName("quantity")
    private int Quantity;
    @SerializedName("active")
    private boolean Active;
    @SerializedName("prodImage")
    private String ScaledImage;




    public Product( String name, String shortDescription, String longDescription, String catagory, double price, int quantity, boolean active, String scaledImage, String fullImage) {

        Name = name;
        ShortDescription = shortDescription;
        LongDescription = longDescription;
        Catagory = catagory;
        Price = price;
        Quantity = quantity;
        Active = active;
        ScaledImage = scaledImage;

    }

    public Product() {
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
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


}
