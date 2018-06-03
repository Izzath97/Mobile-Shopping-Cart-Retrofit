package com.apiit.izzath.wmad2.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
    private final int price=0;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_cart, container, false);

        checkout=(Button)view.findViewById(R.id.checkout);
        cartView = (RecyclerView)view.findViewById(R.id.cartview);
        cartView.setHasFixedSize(true);
        cartView.setLayoutManager(new LinearLayoutManager(getContext()));
        //List<Cart> cart=Cart.listAll(Cart.class);
        SharedPreferences sp= getActivity().getSharedPreferences(login.MyPREFERENCES, Context.MODE_PRIVATE);
        Long id=sp.getLong("id",10);
        List<Cart> car= Cart.findWithQuery(Cart.class, "Select * from Cart where user = ? ", id.toString());

        card = new CartAdapter( car,getContext() );
        cartView.setAdapter(card);



        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment=new Checkout();
                FragmentManager fm=getActivity().getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.aaa,fragment).commit();
            }
        });

        return view;
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Cart");
    }
    }
