package com.apiit.izzath.brandslk.Interface;

import com.apiit.izzath.brandslk.Models.Reviews;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ReviewService {

    String BASE_URL = "http://10.3.4.151:8080/";

    @POST("reviews")
    Call<Reviews> addReview(@Body Reviews review);

    @GET("reviews")
    Call<List<Reviews>> getAllReviews();

    @GET("reviews/{pid}")
    Call<List<Reviews>> getReview(@Path("pid") int productId);






}
