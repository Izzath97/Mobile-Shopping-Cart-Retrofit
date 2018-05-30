package com.apiit.izzath.wmad2.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.apiit.izzath.wmad2.Fragments.detailScreen;
import com.apiit.izzath.wmad2.Models.Product;
import com.apiit.izzath.wmad2.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Izzath on 4/16/2018.
 */

public class adapter extends RecyclerView.Adapter<adapter.ViewHolder> {
private List<Product> listitem;
private Context context;



    public adapter(List<Product> listitem, Context context) {
        this.listitem = listitem;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_item,parent,false);
        return  new ViewHolder(v);
       
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Product product=listitem.get(position);
        holder.cat.setText(product.getName());
        holder.dec.setText(product.getShortDescription());
     //   holder.price.setText(product.getQuantity());
        Picasso.get().load(product.getScaledImage()).into(holder.img);
      //  holder.price.setText(((String) product.getPrice()));
        holder.price.setText("Rs: "+Double.toString(product.getPrice()));
       // holder.quantity.setText(product.getQuantity());


        //set click listneer
holder.cardview.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {


        Long ee=product.getId();
        String name=product.getName();
        Intent intent=new Intent(context,testing.class);


        Bundle bundle = new Bundle();
       // context.startActivity(intent);
      //  bundle.putString("Name12",product.getName());
      //  bundle.putString("ppname",product.getShortDescription());
        bundle.putLong("productid",ee);
        Fragment fragment=new detailScreen();
        FragmentManager fm=((FragmentActivity)context).getSupportFragmentManager();
        fragment.setArguments(bundle);
        fm.beginTransaction().replace(R.id.aaa,fragment).commit();


    }
});

    }

    @Override
    public int getItemCount() {
        return listitem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
    public ImageView img;
    public TextView price,dec,quantity;
    public TextView cat;
    CardView cardview;

        public ViewHolder(View itemView) {
            super(itemView);
            img=(ImageView)itemView.findViewById(R.id.imageView3);
            cat=(TextView)itemView.findViewById(R.id.textView8);
            dec=(TextView)itemView.findViewById(R.id.description);
            price=(TextView)itemView.findViewById(R.id.price);
            cardview=(CardView)itemView.findViewById(R.id.cardview1);
         //   quantity=(TextView)itemView.findViewById(R.id.quantity);

        }
    }

}
