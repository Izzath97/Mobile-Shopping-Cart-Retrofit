package com.apiit.izzath.brandslk.Interface;

import com.apiit.izzath.brandslk.Models.Favorites;
import com.apiit.izzath.brandslk.Models.Product;
import com.apiit.izzath.brandslk.Models.Register;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface JsonPlaceHolderApi {

    //WishList
    @POST("/addwishlist")
    Call<Favorites> addWishList(@Body Favorites f);




    //PRODUCT SERVICE
    @GET("products")
    Call<List<Product>> getProducts();

    @GET("products/{productId}")
    Call<Product> getProduct(@Path("productId") int productId);






    //USER SERVICE

    @GET("getAllUsers")
    Call<List<Register>> getUsers();

    @GET("login/{username}")
    Call<Register> getUser(@Path("username") String username);

    @POST("/login")
    Call<Register> addUser(@Body Register u);



}
