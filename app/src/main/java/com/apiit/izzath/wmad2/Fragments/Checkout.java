package com.apiit.izzath.wmad2.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.apiit.izzath.wmad2.Activities.login;
import com.apiit.izzath.wmad2.Models.Cart;
import com.apiit.izzath.wmad2.Models.Purchase;
import com.apiit.izzath.wmad2.Models.Reviews;
import com.apiit.izzath.wmad2.R;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Izzath on 5/29/2018.
 */

public class Checkout extends Fragment{
    TextView txtquantity,txtprice;
private   double price;
private int quantity;
Button buy;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.checkout, container, false);

        SharedPreferences sp= getActivity().getSharedPreferences(login.MyPREFERENCES, Context.MODE_PRIVATE);
        Long id=sp.getLong("id",10);
        final List<Cart> car= Cart.findWithQuery(Cart.class, "Select * from Cart where user = ? and status = ? ", id.toString(),"Pending");

        for (Cart cart:car) {
        double prices= ( (cart.getProduct().getPrice())*(cart.getQuantity()) );
        price=price+prices;
        quantity=quantity+cart.getQuantity();



        }

        txtquantity=(TextView)view.findViewById(R.id.allquantity);
        txtprice=(TextView)view.findViewById(R.id.allprice);
        txtprice.setText("Rs :"+ String.valueOf(price));
        txtquantity.setText("Total Items :"+String.valueOf(quantity));
        List<Reviews> reviews=Reviews.listAll(Reviews.class);
        List<Reviews> reviews1=Reviews.listAll(Reviews.class);
        buy=(Button)view.findViewById(R.id.pay);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Purchase purchase=new Purchase();
                for (Cart cart:car) {
                    Calendar calendar = Calendar.getInstance();

//                    purchase.setDate((Date) calendar.getTime());
//                    purchase.setTime((Time) calendar.getTime());
                    cart.setStatus("Purchased");
                    cart.save();
                    purchase.setCart(cart);
                    purchase.save();
                   //asda
                    List<Purchase> pp=Purchase.listAll(Purchase.class);
                    List<Purchase> pp2=Purchase.listAll(Purchase.class);

                }



            }
        });
        return  view;
    }

    }
