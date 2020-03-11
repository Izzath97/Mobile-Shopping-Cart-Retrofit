package com.apiit.izzath.brandslk.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.apiit.izzath.brandslk.Models.Cart;
import com.apiit.izzath.brandslk.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Izzath on 6/6/2018.
 */

public class PurchaseAdapter extends ArrayAdapter<Cart> {
List <Cart> list;
TextView name,price,quantity,total;
ImageView image;
CardView history;
    public PurchaseAdapter(@NonNull Context context,@NonNull List<Cart> objects) {
        super(context,R.layout.custom_checkout,objects);

        this.list=objects;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        final Cart cart=getItem(position);
        LayoutInflater layoutInflater=LayoutInflater.from(getContext());
        View view=layoutInflater.inflate(R.layout.custom_checkout,parent,false);
        name=(TextView)view.findViewById(R.id.name);
        price=(TextView)view.findViewById(R.id.price);
        quantity=(TextView)view.findViewById(R.id.quantity);
        total=(TextView)view.findViewById(R.id.total);
        image=(ImageView)view.findViewById(R.id.image);
        history=(CardView)view.findViewById(R.id.history);
        Picasso.get().load(cart.getProduct().getScaledImage()).into(image);
        name.setText(cart.getProduct().getName().toString());
        double productprice=cart.getProduct().getPrice();
        price.setText(String.valueOf(productprice));
        int productquantity=cart.getQuantity();
        quantity.setText(String.valueOf(productquantity));
        double fulltotal=productprice*productquantity;
        total.setText(String.valueOf(fulltotal));

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog=new Dialog(getContext());
                dialog.setTitle("Purchase History Detail");
                dialog.setContentView(R.layout.purchasehistory);
                TextView name=(TextView)dialog.findViewById(R.id.name);
                TextView card=(TextView)dialog.findViewById(R.id.card);
                TextView address=(TextView)dialog.findViewById(R.id.address);
                TextView date=(TextView)dialog.findViewById(R.id.date);
                TextView time=(TextView)dialog.findViewById(R.id.time);
                TextView quantity=(TextView)dialog.findViewById(R.id.quantity);
                TextView total=(TextView)dialog.findViewById(R.id.total);
                ImageView img=(ImageView)dialog.findViewById(R.id.imageView5);
                Button close=(Button)dialog.findViewById(R.id.close);
                Picasso.get().load(cart.getProduct().getScaledImage()).into(img);
                card.setText(cart.toString());
                //address.setText(cart.getAddress());
                int productquantity=cart.getQuantity();
                quantity.setText(String.valueOf(productquantity));
                double productprice=cart.getProduct().getPrice();
                name.setText( cart.getProduct().getName());
                double fulltotal=productprice*productquantity;
                total.setText(String.valueOf(fulltotal));

                dialog.show();
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });
        return  view;
    }


}
