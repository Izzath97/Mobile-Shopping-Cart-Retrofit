package com.apiit.izzath.brandslk.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
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
 * Created by Izzath on 6/10/2018.
 */

public class CancelAdapter extends ArrayAdapter<Cart> {

    TextView name,price,quantity,total;
    ImageView image;
    Button add;
    Long id;

    public CancelAdapter(@NonNull Context context, @NonNull List<Cart> objects) {
        super(context, R.layout.custom_cancel,objects);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        final Cart cart=getItem(position);
        LayoutInflater layoutInflater=LayoutInflater.from(getContext());

        View view=layoutInflater.inflate(R.layout.custom_cancel,parent,false);
        name=(TextView)view.findViewById(R.id.name);
        price=(TextView)view.findViewById(R.id.price);
        quantity=(TextView)view.findViewById(R.id.quantity);
        total=(TextView)view.findViewById(R.id.total);
        image =(ImageView)view.findViewById(R.id.image);
        add=(Button)view.findViewById(R.id.buy);
/*
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Product product=Product.findById(Product.class,cart.getProduct().getId());

          if(cart.getQuantity()<=product.getQuantity()){

            List <Cart> c=Cart.listAll(Cart.class);
              SharedPreferences sp = getContext().getSharedPreferences(login.MyPREFERENCES, Context.MODE_PRIVATE);
              Long userid = sp.getLong("id", 10);
              for (Cart cc:c) {
                  if(cc.getProduct().getId().equals(cart.getProduct().getId())&&cc.getStatus().equals("Pending")&&cc.getRegister().getId().equals(userid)){
                   id=cc.getId();
                  }
              }
              if(id!=null){

                  Cart ccc=Cart.findById(Cart.class,id);
                  if(ccc.getStatus().equals("Pending")){
                      int totalquantity=cart.getQuantity()+ccc.getQuantity();
                      ccc.setTotal(cart.getTotal()+ ccc.getTotal());
                      ccc.setQuantity(totalquantity);
                      cart.setStatus("Added");
                      ccc.save();

                  }else {
                      cart.setStatus("Pending");
                      cart.save();
                  }
                  product.setQuantity(product.getQuantity()-cart.getQuantity());
                  product.save();
              }


          }else {
              Toast.makeText(getContext(), "Product Out of Stock", Toast.LENGTH_SHORT).show();

          }


            }
        });
*/
        name.setText(cart.getProduct().getName());
        double productprice=cart.getProduct().getPrice();
        price.setText("Price :"+String.valueOf(productprice));
        int productquantity=cart.getQuantity();
        quantity.setText("Quantity :"+String.valueOf(productquantity));
        double fulltotal=productprice*productquantity;
        total.setText("Rs :"+String.valueOf(fulltotal));
        Picasso.get().load(cart.getProduct().getScaledImage()).into(image);

        return  view;
    }


}
