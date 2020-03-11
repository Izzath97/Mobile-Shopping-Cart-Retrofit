package com.apiit.izzath.brandslk.Interface;

import com.apiit.izzath.brandslk.Models.Favorites;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WishListService {

    String BASE_URL = "http://10.3.4.151:8080/";


    @POST("/addwishlist")
    Call<Favorites> addWishList(@Body Favorites f);

    @GET("/getWishList/{user}")
    Call<List<Favorites>> getWishList(@Path("user") String username);

}
