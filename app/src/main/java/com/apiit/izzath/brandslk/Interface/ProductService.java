package com.apiit.izzath.brandslk.Interface;

import com.apiit.izzath.brandslk.Models.OrderItemWrapper;
import com.apiit.izzath.brandslk.Models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProductService {

    String BASE_URL = "http://10.3.4.151:8080/";

    //PRODUCT SERVICE
    @GET("products")
    Call<List<Product>> getProducts();

    @GET("products/{productId}")
    Call<Product> getProduct(@Path("productId") int productId);

    @PUT("products")
    Call<Void> updateProducts(@Body OrderItemWrapper orderItemWrapper);

}
