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
import com.apiit.izzath.wmad2.Models.Register;
import com.apiit.izzath.wmad2.R;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Izzath on 5/29/2018.
 */

public class Checkout extends Fragment{
    TextView txtquantity,txtprice,txtname,txtemail;
private   double price;
private int quantity;
Button buy;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.checkout, container, false);

        SharedPreferences sp= getActivity().getSharedPreferences(login.MyPREFERENCES, Context.MODE_PRIVATE);
        Long id=sp.getLong("id",10);
        Register user=Register.findById(Register.class,id);
        final List<Cart> car= Cart.findWithQuery(Cart.class, "Select * from Cart where user = ? and status = ? ", id.toString(),"Pending");

        for (Cart cart:car) {
        double prices= ( (cart.getProduct().getPrice())*(cart.getQuantity()) );
        price=price+prices;
        quantity=quantity+cart.getQuantity();

        }
        txtname=(TextView)view.findViewById(R.id.name);
        txtemail=(TextView)view.findViewById(R.id.email);
        txtname.setText( "Name   :"+user.getName());
        txtemail.setText("E-Mail :"+user.getEmail());
        txtquantity=(TextView)view.findViewById(R.id.cartname);
        txtprice=(TextView)view.findViewById(R.id.allprice);
        txtprice.setText("Rs :"+ String.valueOf(price));
        txtquantity.setText("Total Items :"+String.valueOf(quantity));
        buy=(Button)view.findViewById(R.id.pay);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date=new Date(Calendar.DATE);
                SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy");
                String postedDate = format.format(date);
                for (Cart cart:car) {
                    Purchase purchase=new Purchase();
                    Calendar calendar = Calendar.getInstance();
                    cart.setStatus("Purchased");
                    cart.save();
                    purchase.setCart(cart);
                    purchase.setDate(date);
                    purchase.save();
                    List<Purchase> pp2=Purchase.listAll(Purchase.class);
                    List<Purchase> pps2=Purchase.listAll(Purchase.class);

                }



            }
        });
        return  view;
    }

    }
