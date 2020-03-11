package com.apiit.izzath.brandslk.Interface;

import com.apiit.izzath.brandslk.Models.Register;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserService {

    //USER SERVICE

    String BASE_URL = "http://10.3.4.151:8080/";

    @GET("getAllUsers")
    Call<List<Register>> getUsers();

    @GET("login/{username}")
    Call<Register> getUser(@Path("username") String username);

    @POST("/login")
    Call<Register> addUser(@Body Register u);

    @PUT("/users/{username}")
    Call<Register> updateUser(@Path("username") String username, @Body Register user );

}
