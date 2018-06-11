package com.apiit.izzath.wmad2.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.apiit.izzath.wmad2.Activities.ReviewAdapter;
import com.apiit.izzath.wmad2.Activities.login;
import com.apiit.izzath.wmad2.Models.Cart;
import com.apiit.izzath.wmad2.Models.Favorites;
import com.apiit.izzath.wmad2.Models.Product;
import com.apiit.izzath.wmad2.Models.Register;
import com.apiit.izzath.wmad2.Models.Reviews;
import com.apiit.izzath.wmad2.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Izzath on 5/2/2018.
 */

public class detailScreen extends Fragment {
    TextView testing,price,description,longDescription,quantityview;
    EditText quantity,comment;
    Long id;
    RatingBar rb;
    Button bcomment;
    float value;
    Register user;
    Product products;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.detail_product, container, false);
        final   Bundle bundles = this.getArguments();
        Button cart=(Button)view.findViewById(R.id.cart);
        final Long productid1=bundles.getLong("productid");
        id=productid1;
        if (bundles != null) {

            final Product product=Product.findById(Product.class,productid1);
            products=product;
            if(product.isActive()==false){
                cart.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.colorAccent));
                cart.setEnabled(false);

            }
        }

        // Add to Cart Button
        Button wish=(Button)view.findViewById(R.id.wish);         //Wish List Button
        Button share=(Button)view.findViewById(R.id.button2);   //Share Button
        testing=(TextView)view.findViewById(R.id.name);
        price=(TextView)view.findViewById(R.id.price);
        quantityview=(TextView)view.findViewById(R.id.qname);
        quantity=(EditText)view.findViewById(R.id.cartname);
        description=(TextView)view.findViewById(R.id.description);
        longDescription =(TextView)view.findViewById(R.id.longDescription);
        comment=(EditText)view.findViewById(R.id.comment);
        bcomment=(Button)view.findViewById(R.id.bcomment);
        rb=(RatingBar)view.findViewById(R.id.ratingBar);

        final ListView listview=(ListView)view.findViewById(R.id.reviewview);     // ListView For view Reviews

        List<Reviews> reviews= Reviews.findWithQuery(Reviews.class, "Select * from Reviews where   product = ? ",products.getId().toString());
        final ReviewAdapter rv=new ReviewAdapter(getContext(),reviews);
        listview.setAdapter(rv);

     rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
        @Override
        public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
        value=v;
        }
        });

     bcomment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
     SharedPreferences sp = getActivity().getSharedPreferences(login.MyPREFERENCES, Context.MODE_PRIVATE);
     final Long userids = sp.getLong("id", 10);
     SimpleDateFormat format =new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
     Register uss=Register.findById(Register.class,userids);
     String commnt=comment.getText().toString();
     Reviews reviews=new Reviews(uss,products,value,commnt);
     reviews.save();
     List<Reviews> reviewss=
     Reviews.findWithQuery(Reviews.class, "Select * from Reviews where   product = ? ",products.getId().toString());
     final ReviewAdapter rv=new ReviewAdapter(getContext(),reviewss);
     listview.setAdapter(rv);
            }
        });


  wish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
  Boolean check=false;
  SharedPreferences sp = getActivity().getSharedPreferences(login.MyPREFERENCES, Context.MODE_PRIVATE);
  final Long userids = sp.getLong("id", 10);
  Register uss=Register.findById(Register.class,userids);
  List <Favorites> fav=Favorites.listAll(Favorites.class);
  for (Favorites fa:fav
  ) {
  if(  fa.getProduct().getId().equals(products.getId())&&fa.getUser().getId().equals(userids)){
  check=true;
   }
     }
      if(check.equals(true)){
   Toast.makeText(getContext(), "The Product is already Added to Favorites", Toast.LENGTH_SHORT).show();
       }
        else if (check==false){
          Favorites favorites=new Favorites();
          favorites.setProduct(products);
          favorites.setUser(uss);
          favorites.save();
          }
                }
            });




            ImageView img=(ImageView)view.findViewById(R.id.imageView7);
            final Product product=Product.findById(Product.class,id);
            products=product;
            price.setText(Double.toString(product.getPrice()));
            testing.setText(product.getName());
            description.setText(product.getShortDescription());
            longDescription.setText(product.getLongDescription());
            Picasso.get().load(product.getScaledImage()).into(img);
            quantityview.setText(Integer.toString(product.getQuantity()));
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Dialog dialog=new Dialog(getContext());
                    dialog.setTitle("Purchase History Detail");
                    dialog.setContentView(R.layout.image);
                    ImageView date=(ImageView) dialog.findViewById(R.id.dimage);
                    Picasso.get().load(product.getFullImage()).into(date);
                    dialog.show();
                }
            });



        cart.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(quantity.getText().toString().isEmpty()){
                    quantity.setError("Enter Quantity to ADD TO Cart");
            }
            else
                {
                Long productids = Long.valueOf(10);
                Long cartid = Long.valueOf(10);
                SharedPreferences sp = getActivity().getSharedPreferences(login.MyPREFERENCES, Context.MODE_PRIVATE);
                Long userid = sp.getLong("id", 10);
                Product products = Product.findById(Product.class, id);          //Product Object is returned
                Register rg = Register.findById(Register.class, userid);
                user=rg;//User object is returned
                List<Cart> cart1 = Cart.listAll(Cart.class);     //List of Cart is returned
                for (Cart cart : cart1) {
                    if (cart.getProduct().getId().equals(products.getId()) && cart.getRegister().getId().equals(userid)&&cart.getStatus().equals("Pending")) {
                        productids = cart.getProduct().getId();
                        cartid = cart.getId();
                    }
                }
                final int enterquantity = Integer.parseInt(quantity.getText().toString());
                if(enterquantity <= products.getQuantity()){
                    if (products.getId().equals(productids)) {
                        Cart cartfind = Cart.findById(Cart.class, cartid);
                        Toast.makeText(getContext(), "The Product Successfully Added to the Cart", Toast.LENGTH_SHORT).show();
                        int quantitycart = cartfind.getQuantity();
                        cartfind.setQuantity(quantitycart + enterquantity);
                        cartfind.setStatus("Pending");
                        cartfind.setTotal((quantitycart + enterquantity)*products.getPrice());
                        cartfind.save();
                    }
                    else {
                        Cart item = new Cart(rg, products, sp.getLong("id", 10), enterquantity, "Pending",(enterquantity*products.getPrice()));
                        item.save();
                        Toast.makeText(getContext(), "The Product Successfully Added to the Cart", Toast.LENGTH_SHORT).show();
                    }

                    int updatequantity = ((products.getQuantity()) - (enterquantity));
                    products.setQuantity(updatequantity);
                    products.save();

                }

                else {
                        quantity.setError("Invalid Quantity");

                    }
                }
        }
        });




        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Product ll=Product.findById(Product.class,id);
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                String sharing=ll.getFullImage().toString();
                share.putExtra(Intent.EXTRA_SUBJECT, sharing);
                share.putExtra(Intent.EXTRA_TEXT, sharing);
                startActivity(Intent.createChooser(share, "Share Via"));
            }
        });



        return  view;


    }



}
