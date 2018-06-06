package com.apiit.izzath.wmad2.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.apiit.izzath.wmad2.Models.Product;
import com.apiit.izzath.wmad2.Models.Register;
import com.apiit.izzath.wmad2.Models.Reviews;
import com.apiit.izzath.wmad2.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
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
    Product productss;
    ArrayList<Reviews> reviews=new ArrayList<Reviews>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.detail_product, container, false);
        final   Bundle bundles = this.getArguments();
        final Long productid1=bundles.getLong("productid");
        id=productid1;
        if (bundles != null) {

            ImageView img=(ImageView)view.findViewById(R.id.imageView7);
            final Product product=Product.findById(Product.class,productid1);
            productss=product;}
        Button cart=(Button)view.findViewById(R.id.cart);
        Button buy=(Button)view.findViewById(R.id.buy);
        Button share=(Button)view.findViewById(R.id.button2);
        testing=(TextView)view.findViewById(R.id.name);
        price=(TextView)view.findViewById(R.id.price);
        quantityview=(TextView)view.findViewById(R.id.qname);
        quantity=(EditText)view.findViewById(R.id.allquantity);
        description=(TextView)view.findViewById(R.id.description);
        longDescription =(TextView)view.findViewById(R.id.longDescription);
        comment=(EditText)view.findViewById(R.id.comment);
        bcomment=(Button)view.findViewById(R.id.bcomment);
        rb=(RatingBar)view.findViewById(R.id.ratingBar);

        ListView listview=(ListView)view.findViewById(R.id.reviewview);
        SharedPreferences sp = getActivity().getSharedPreferences(login.MyPREFERENCES, Context.MODE_PRIVATE);
        final Long userids = sp.getLong("id", 10);
        List<Reviews> reee=Reviews.listAll(Reviews.class);
     //   List<Reviews> reviewsd= Reviews.findWithQuery(Reviews.class, "Select * from Reviews where user = ? and product = ? ", userid.toString(),productss.toString());

        for (Reviews rv:reee
             ) {
            if(rv.getUser().getId().equals(userids)&&rv.getProduct().getId().equals(productss.getId())){
                reviews.add(rv);

            }
        }

        ReviewAdapter rv=new ReviewAdapter(getContext(),reviews);
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
                Register uss=Register.findById(Register.class,userids);
                String commnt=comment.getText().toString();
                Reviews reviews=new Reviews(uss,productss,value,commnt);
                reviews.save();

            }
        });

        final   Bundle bundle = this.getArguments();

        final Long productid=bundle.getLong("productid");
        id=productid;
        if (bundle != null) {

        ImageView img=(ImageView)view.findViewById(R.id.imageView7);
       final Product product=Product.findById(Product.class,productid);
       productss=product;
            price.setText(Double.toString(product.getPrice()));
            testing.setText(product.getName());
            description.setText(product.getShortDescription());
            longDescription.setText(product.getLongDescription());
            Picasso.get().load(product.getScaledImage()).into(img);
            quantityview.setText(Integer.toString(product.getQuantity()));
            }

          cart.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(quantity.getText().toString().isEmpty()){
                quantity.setError("Enter Quantity to ADD TO Cart");

            }
            else {
                Long productids = Long.valueOf(10);
                Long cartid = Long.valueOf(10);
                SharedPreferences sp = getActivity().getSharedPreferences(login.MyPREFERENCES, Context.MODE_PRIVATE);
                Long userid = sp.getLong("id", 10);
                Product products = Product.findById(Product.class, productid);          //Product Object is returned
                Register rg = Register.findById(Register.class, userid);
                user=rg;//User object is returned
                List<Cart> cart1 = Cart.listAll(Cart.class);     //List of Cart is returned
                for (Cart cart : cart1) {
                    if (cart.getProduct().getId().equals(products.getId()) && cart.getRegister().getId().equals(userid)&&cart.getStatus().equals("Pending")) {
                        productids = cart.getProduct().getId();
                        cartid = cart.getId();
                    }
                }
                // Cart cca=  Cart.findById(Cart.class,productids);
                if (products.getId().equals(productids)) {
                    Cart cartfind = Cart.findById(Cart.class, cartid);
                    Toast.makeText(getContext(), "The Product is Already Added to the Cart", Toast.LENGTH_SHORT).show();
                    int quantitycart = cartfind.getQuantity();
                    final int enterquantity = Integer.parseInt(quantity.getText().toString());
                    // int quantity1 = Integer.parseInt(qaa);
                    if (enterquantity <= products.getQuantity()) {
                        cartfind.setQuantity(quantitycart + enterquantity);
                        cartfind.setStatus("Pending");
                        cartfind.setTotal((quantitycart + enterquantity)*products.getPrice());
                        cartfind.save();
                        int updatequantity = ((products.getQuantity()) - (enterquantity));
                        products.setQuantity(updatequantity);
                        products.save();
                    } else {
                        quantity.setError("Invalid Quantity");

                    }
                } else {
                    int enterquantity = Integer.parseInt(quantity.getText().toString());
                    if (enterquantity <= products.getQuantity()) {
                        Cart item = new Cart(rg, products, sp.getLong("id", 10), enterquantity, "Pending",(enterquantity*products.getPrice()));
                        item.save();
                        int updatequantity = ((products.getQuantity()) - (enterquantity));
                        products.setQuantity(updatequantity);
                        products.save();
                    } else {
                        quantity.setError("Invalid Quantity");

                    }

                }


                List<Cart> cc = Cart.listAll(Cart.class);

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
    void sss(View  view){

        Product ll=Product.findById(Product.class,id);
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        String sharing=ll.getShortDescription().toString();
        share.putExtra(Intent.EXTRA_SUBJECT, sharing);

        share.putExtra(Intent.EXTRA_TEXT, sharing);
        startActivity(Intent.createChooser(share, "Share Via"));


    }


}
