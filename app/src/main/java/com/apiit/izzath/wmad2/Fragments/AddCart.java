package com.apiit.izzath.wmad2.Fragments;

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

import com.apiit.izzath.wmad2.Activities.CartAdapter;
import com.apiit.izzath.wmad2.Activities.login;
import com.apiit.izzath.wmad2.Models.Cart;
import com.apiit.izzath.wmad2.R;

import java.util.List;

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
        Long id = sp.getLong("id", 10);
        List<Cart> car = Cart.findWithQuery(Cart.class, "Select * from Cart where user = ? and status = ? ", id.toString(), "Pending");
        card = new CartAdapter(car, getContext());
        cartView.setAdapter(card);

        final List<Cart> cartlist = Cart.findWithQuery(Cart.class, "Select * from Cart where user = ? and status = ? ", id.toString(), "Pending");

        for (Cart cart : cartlist) {
            double prices = ((cart.getProduct().getPrice()) * (cart.getQuantity()));
            price = price + prices;
            quantity = quantity + cart.getQuantity();

        }

        txtquantity = (TextView) view.findViewById(R.id.cartname);
        txtprice = (TextView) view.findViewById(R.id.allprice);
        txtprice.setText("Rs :" + String.valueOf(price));
        txtquantity.setText("Total Items :" + String.valueOf(quantity));

        if(cartlist.isEmpty()){
            checkout.setEnabled(false);
        }

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
