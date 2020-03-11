package com.apiit.izzath.brandslk.Interface;

import com.apiit.izzath.brandslk.Models.Cart;
import com.apiit.izzath.brandslk.Models.Order;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CartService {

    String BASE_URL = "http://10.3.4.151:8080/";

    @GET("orderItems/cart/orderItem/{username}")
    Call<List<Cart>> getCartItems(@Path("username") String username);

    @POST("oitems")
    Call<Cart> addToCart(@Body Cart orderItem);

    @PUT("orderItems/cart/orderItem/{username}/{pid}/{quantity}")
    Call<Cart>  updateCartItem(@Path("username")String username,@Path("quantity") int quantity,@Path("pid") int pid);

    @DELETE("orderItems/cart/{id}}")
    Call<Cart> deleteFromCart(@Path("id") int id);

    @PUT("orderItems/cart/orderItem/{username}/purchased")
    Call<Void> checkout(@Path("username") String username);

    @GET("orders/orderitems/{username}")
    Call<List<Cart>> getPurchasedItems(@Path("username") String username);


    @POST("orders/{username}")
    Call<Void>  orderConfirmed(@Path("username") String username,@Body Order order);

}
