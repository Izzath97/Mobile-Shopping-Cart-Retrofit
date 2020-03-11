package com.apiit.izzath.brandslk.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.apiit.izzath.brandslk.Adapters.CartAdapter;
import com.apiit.izzath.brandslk.Activities.login;
import com.apiit.izzath.brandslk.Interface.CartService;
import com.apiit.izzath.brandslk.Models.Cart;
import com.apiit.izzath.brandslk.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Izzath on 5/7/2018.
 */

public class AddCart extends Fragment {
    private RecyclerView cartView;
    private RecyclerView.Adapter card;
    private Button checkout;
    TextView txtquantity, txtprice;
    private double price;
    private int quantity;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_cart, container, false);

        checkout = (Button) view.findViewById(R.id.checkout);
        cartView = (RecyclerView) view.findViewById(R.id.cartview);
        cartView.setHasFixedSize(true);
        cartView.setLayoutManager(new LinearLayoutManager(getContext()));
        SharedPreferences sp = getActivity().getSharedPreferences(login.MyPREFERENCES, Context.MODE_PRIVATE);
        String username = sp.getString("User", "");
        txtquantity = (TextView) view.findViewById(R.id.cartname);
        txtprice = (TextView) view.findViewById(R.id.allprice);
        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(CartService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CartService cartService = retrofit.create(CartService.class);

        Call<List<Cart>> call = cartService.getCartItems(username);

        call.enqueue(new Callback<List<Cart>>() {
            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                Cart c = null;
                List<Cart> cart = response.body();
                card = new CartAdapter(cart, getContext());
                cartView.setAdapter(card);

                        for (Cart carts : cart) {
            double prices = ((carts.getProduct().getPrice()) * (carts.getQuantity()));
            price = price + prices;
            quantity = quantity + carts.getQuantity();

        }
                txtprice.setText("Rs :" + String.valueOf(price));
                txtquantity.setText("Total Items :" + String.valueOf(quantity));

            }

            @Override
            public void onFailure(Call<List<Cart>> call, Throwable t) {

            }
        });



       // List<Cart> car = Cart.findWithQuery(Cart.class, "Select * from Cart where user = ? and status = ? ", id.toString(), "Pending");


        //final List<Cart> cartlist = Cart.findWithQuery(Cart.class, "Select * from Cart where user = ? and status = ? ", id.toString(), "Pending");



//        if(cartlist.isEmpty()){
////            checkout.setEnabled(false);
////        }

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Checkout();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.aaa, fragment).commit();
            }
        });

        return view;
    }


}
