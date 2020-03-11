package com.apiit.izzath.brandslk.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.apiit.izzath.brandslk.Adapters.PurchaseAdapter;
import com.apiit.izzath.brandslk.Activities.login;
import com.apiit.izzath.brandslk.Interface.CartService;
import com.apiit.izzath.brandslk.Interface.ProductService;
import com.apiit.izzath.brandslk.Models.Cart;
import com.apiit.izzath.brandslk.Models.Purchase;
import com.apiit.izzath.brandslk.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Izzath on 6/6/2018.
 */

public class PurchaseHistory extends Fragment {

ArrayList<Purchase> purchaseArrayAdapter=new ArrayList<Purchase>();
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.purchase, container, false);
        final ListView listView=(ListView)view.findViewById(R.id.listview);
        final SharedPreferences sp= this.getActivity().getApplicationContext().getSharedPreferences(login.MyPREFERENCES, Context.MODE_PRIVATE);
//        Register rgg=Register.findById(Register.class,sp.getLong("id",10));


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ProductService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final CartService orderItem = retrofit.create(CartService.class);

        Call<List<Cart>> call = orderItem.getPurchasedItems(sp.getString("User",""));

        call.enqueue(new Callback<List<Cart>>() {
            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                final List<Cart> orderItems = response.body();


                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(CartService.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                CartService cartService = retrofit.create(CartService.class);
                Call<List<Cart>> call2 = cartService.getPurchasedItems(sp.getString("User",""));

                call2.enqueue(new Callback<List<Cart>>() {
                    @Override
                    public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {

                        List<Cart> orders = response.body();
                        List<String> dates = new ArrayList<String>();


                        final PurchaseAdapter purchaseAdapter=new PurchaseAdapter(getContext(),orders);

                        listView.setAdapter(purchaseAdapter);
                    }

                    @Override
                    public void onFailure(Call<List<Cart>> call, Throwable throwable) {
                        Toast.makeText(getActivity(), throwable.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });

            }



            @Override
            public void onFailure(Call<List<Cart>> call, Throwable throwable) {
                Toast.makeText(getActivity(), throwable.getMessage(), Toast.LENGTH_LONG).show();

            }
        });






        final List<Purchase> purchase=Purchase.listAll(Purchase.class);
        for (Purchase purchases:purchase
             ) {

//            if(purchases.getCart().getRegister().getId().equals(rgg.getId())){
//                 purchaseArrayAdapter.add(purchases);
//        }

        }

        /*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Purchase pp=purchaseAdapter.getItem(i);
                Dialog dialog=new Dialog(getContext());
                dialog.setTitle("Purchase History Detail");
                dialog.setContentView(R.layout.purchasehistory);
                TextView date=(TextView)view.findViewById(R.id.date);
                date.setText( pp.getCart().getProduct().getName());
                dialog.show();
            }
        });
*/

        return  view;
    }

    }
