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
import android.widget.TextView;
import android.widget.Toast;

import com.apiit.izzath.wmad2.Activities.login;
import com.apiit.izzath.wmad2.Models.Cart;
import com.apiit.izzath.wmad2.Models.Product;
import com.apiit.izzath.wmad2.Models.Register;
import com.apiit.izzath.wmad2.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Izzath on 5/2/2018.
 */

public class detailScreen extends Fragment {
    TextView testing,price,description,longDescription,quantityview;
    EditText quantity;
    Long id;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.detail_product, container, false);

        Button cart=(Button)view.findViewById(R.id.cart);
        Button buy=(Button)view.findViewById(R.id.buy);
        Button share=(Button)view.findViewById(R.id.button2);
        testing=(TextView)view.findViewById(R.id.name);
        price=(TextView)view.findViewById(R.id.price);
        quantityview=(TextView)view.findViewById(R.id.qname);
        quantity=(EditText)view.findViewById(R.id.allquantity);
        description=(TextView)view.findViewById(R.id.description);
        longDescription =(TextView)view.findViewById(R.id.longDescription);


     final   Bundle bundle = this.getArguments();

        final Long productid=bundle.getLong("productid");
        id=productid;
        if (bundle != null) {

        ImageView img=(ImageView)view.findViewById(R.id.imageView7);
       final Product product=Product.findById(Product.class,productid);
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
            Long productids= Long.valueOf(10);
           Long cartid= Long.valueOf(10);
            SharedPreferences sp= getActivity().getSharedPreferences(login.MyPREFERENCES, Context.MODE_PRIVATE);
            Long userid=sp.getLong("id",10);
            Product products=Product.findById(Product.class,productid);          //Product Object is returned
            Register rg=Register.findById(Register.class,userid);   //User object is returned

            List <Cart> ccr=  Cart.listAll(Cart.class);     //List of Cart is returned

            for (Cart cart:ccr) {
                if (cart.getProduct().getId().equals(products.getId())&&cart.getRegister().getId().equals(userid)){
           productids=cart.getProduct().getId();
           cartid=cart.getId();
                }
            }
            Cart cca=  Cart.findById(Cart.class,productids);
            if(products.getId().equals(productids)){

              Cart camel=Cart.findById(Cart.class,cartid);
                Toast.makeText(getContext(), "The Product is Already Added to the Cart", Toast.LENGTH_SHORT).show();
                int quantity12=camel.getQuantity();
               final int qaa= Integer.parseInt(quantity.getText().toString());
               // int quantity1 = Integer.parseInt(qaa);
               camel.setQuantity(quantity12+qaa);
               camel.save();
            int updatequantity=  (( products.getQuantity())-(qaa));
            products.setQuantity(updatequantity);
            products.save();

            }
            else {

                int qaa= Integer.parseInt(quantity.getText().toString());
                Cart item=new Cart(rg,products,sp.getLong("id",10),qaa,"Pending");
                item.save();
                int updatequantity=  (( products.getQuantity())-(qaa));
                products.setQuantity(updatequantity);
                products.save();

            }


    List<Cart> cc=Cart.listAll(Cart.class);


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
