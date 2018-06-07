package com.apiit.izzath.wmad2.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
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

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Product product=listitem.get(position);
        holder.cat.setText(product.getName());
        holder.dec.setText(product.getShortDescription());

        Picasso.get().load(product.getScaledImage()).into(holder.img);

        holder.price.setText("Rs: "+Double.toString(product.getPrice()));
        if(product.getQuantity()==0){
            product.setActive(false);
        }
        if(product.isActive()){
            holder.status.setText("Available");
            holder.status.setTextColor(ContextCompat.getColor(context,R.color.green));
        }
        else{
            holder.status.setText("Unavilable");
            holder.status.setTextColor(ContextCompat.getColor(context,R.color.colorAccent));

        }



        //set click listneer
        holder.cardview.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Long ee=product.getId();
        Bundle bundle = new Bundle();
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
    public TextView cat,status;
    CardView cardview;

        public ViewHolder(View itemView) {
            super(itemView);
            img=(ImageView)itemView.findViewById(R.id.imageView3);
            cat=(TextView)itemView.findViewById(R.id.textView8);
            dec=(TextView)itemView.findViewById(R.id.description);
            price=(TextView)itemView.findViewById(R.id.price);
            cardview=(CardView)itemView.findViewById(R.id.cardview1);
            status=(TextView)itemView.findViewById(R.id.status);

        }
    }

}
