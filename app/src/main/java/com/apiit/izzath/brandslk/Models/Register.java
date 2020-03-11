package com.apiit.izzath.brandslk.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Register {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("emailAddress")
    @Expose
    private String emailAddress;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("residentAddress")
    @Expose
    private String residentAddress;
    @SerializedName("contactNumber")
    @Expose
    private String contactNumber;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("profileimg")
    @Expose
    private String profileimg;


    public Register(String username, String password, String emailAddress, String firstName, String residentAddress, String profileimg,String status) {
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.residentAddress = residentAddress;
        this.profileimg = profileimg;
        this.status=status;
    }

    public Register(Integer id, String username, String password, String emailAddress, String firstName, String residentAddress, String contactNumber, String status, String profileimg) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.residentAddress = residentAddress;
        this.contactNumber = contactNumber;
        this.status = status;
        this.profileimg = profileimg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getResidentAddress() {
        return residentAddress;
    }

    public void setResidentAddress(String residentAddress) {
        this.residentAddress = residentAddress;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProfileimg() {
        return profileimg;
    }

    public void setProfileimg(String profileimg) {
        this.profileimg = profileimg;
    }


    public Register(String name, String password1, String password2, String email, String age) {
        this.username = name;
        this.password = password1;
        this.password = password2;
        this.emailAddress = email;
        //this = age;
    }

    public Register() {
    }


}
