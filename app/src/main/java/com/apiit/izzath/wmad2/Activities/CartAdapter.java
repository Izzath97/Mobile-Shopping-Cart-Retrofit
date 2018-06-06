package com.apiit.izzath.wmad2.Activities;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.apiit.izzath.wmad2.Models.Cart;
import com.apiit.izzath.wmad2.Models.Product;
import com.apiit.izzath.wmad2.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Izzath on 5/15/2018.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private List<Cart> cart;
    private Context context;



    public CartAdapter(List<Cart> cart, Context context) {
        this.cart = cart;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_item,parent,false);
        return  new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Cart cc = cart.get(position);
        //holder.cat.setText(cc.getProduct().getShortDescription());
        holder.quantity.setText(cc.getProduct().getShortDescription());
        //   holder.price.setText(product.getQuantity());
        Picasso.get().load(cc.getProduct().getScaledImage()).into(holder.img);
        //  holder.price.setText(((String) product.getPrice()));
        holder.price.setText("Rs: " + Double.toString(cc.getProduct().getPrice()));
        holder.qan.setText("Quantity : "+Integer.toString(cc.getQuantity()));
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cc.setStatus("Canceled");


                Product product=Product.findById(Product.class,cc.getProduct().getId());
                int quantity=cc.getQuantity()+product.getQuantity();
                product.setQuantity(quantity);
                product.save();
                cc.setQuantity(0);
                cc.setTotal(0);
                cc.save();
            }
        });
    }



        @Override
    public int getItemCount() {
            return cart.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView img;
        public TextView price,quantity,qan;
        public Button delete;

        CardView cardview;

        public ViewHolder(View itemView) {
            super(itemView);
            img=(ImageView)itemView.findViewById(R.id.cartImage);
            qan=(TextView)itemView.findViewById(R.id.allquantity);
            quantity=(TextView)itemView.findViewById(R.id.cartprice) ;
            price=(TextView)itemView.findViewById(R.id.cartprice);
            delete=(Button)itemView.findViewById(R.id.delete);
            cardview=(CardView)itemView.findViewById(R.id.cartview);
            //   quantity=(TextView)itemView.findViewById(R.id.quantity);

        }
    }


}
