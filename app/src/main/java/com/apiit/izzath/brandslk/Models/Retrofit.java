package com.apiit.izzath.brandslk.Models;

import com.apiit.izzath.brandslk.Interface.JsonPlaceHolderApi;

import retrofit2.converter.gson.GsonConverterFactory;

public  class Retrofit {

    public JsonPlaceHolderApi returnApi(){

        retrofit2.Retrofit retrofit=new retrofit2.Retrofit.Builder()
                .baseUrl("http://10.3.4.151:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi=retrofit.create(JsonPlaceHolderApi.class);
        return  jsonPlaceHolderApi;
    }
}
