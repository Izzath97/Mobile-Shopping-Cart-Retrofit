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
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Cart carts = cart.get(position);

        holder.name.setText(carts.getProduct().getShortDescription());
        Picasso.get().load(carts.getProduct().getScaledImage()).into(holder.img);
        holder.price.setText("Rs: " + Double.toString(carts.getProduct().getPrice()));
        holder.quantity.setText("Quantity : "+Integer.toString(carts.getQuantity()));

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carts.setStatus("Canceled");
                Product product=Product.findById(Product.class,carts.getProduct().getId());
                int quantity=carts.getQuantity()+product.getQuantity();
                product.setQuantity(quantity);
                product.save();
                carts.save();
           notifyItemRemoved(position);

            }
        });

    }

        @Override
    public int getItemCount() {
            return cart.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView img;
        public TextView price,quantity,name;
        public Button delete;

        CardView cardview;

        public ViewHolder(View itemView) {
            super(itemView);
            img=(ImageView)itemView.findViewById(R.id.cartImage);
            name=(TextView)itemView.findViewById(R.id.cartname);
            price=(TextView)itemView.findViewById(R.id.cartprice) ;
            quantity=(TextView)itemView.findViewById(R.id.cartquantity);
            delete=(Button)itemView.findViewById(R.id.delete);
            cardview=(CardView)itemView.findViewById(R.id.cartview);
            //   quantity=(TextView)itemView.findViewById(R.id.quantity);

        }
    }


}
