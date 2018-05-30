package com.apiit.izzath.wmad2.Models;

import com.orm.SugarRecord;

/**
 * Created by Izzath on 4/2/2018.
 */

public class Register extends SugarRecord<Register>{
    private String name;
    private String password1;
    private  String password2;
    private String email;
    private  String age;

    public Register(String name, String password1, String password2, String email, String age) {
        this.name = name;
        this.password1 = password1;
        this.password2 = password2;
        this.email = email;
        this.age = age;
    }

    public Register() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getPassword1() {
        return password1;
    }

    public String getPassword2() {
        return password2;
    }

    public String getEmail() {
        return email;
    }

    public String getAge() {
        return age;
    }
}
