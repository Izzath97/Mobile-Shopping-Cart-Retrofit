package com.apiit.izzath.brandslk.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apiit.izzath.brandslk.Adapters.adapter;
import com.apiit.izzath.brandslk.Interface.JsonPlaceHolderApi;
import com.apiit.izzath.brandslk.Models.Product;
import com.apiit.izzath.brandslk.Models.Retrofit;
import com.apiit.izzath.brandslk.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Izzath on 6/9/2018.
 */

public class WomenHome extends Fragment {


    public static final String women1 = "women" ;

    ArrayList<Product> storeWomen = new ArrayList<Product>();
    private RecyclerView recyclerView;
    private  RecyclerView.Adapter adapters;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.home, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Retrofit retrofit=new Retrofit();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.returnApi();
        Call<List<Product>> p = jsonPlaceHolderApi.getProducts();
        p.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                final List<Product> product = response.body();
                for (Product p:product) {
                    if(p.getCatagory().equals(women1)){

                        storeWomen.add(p) ;

                    }


                }
                adapters = new adapter(storeWomen, getContext());
                recyclerView.setAdapter(adapters);


            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });

        // Inflate the layout for this fragment



        return  view;
    }
}
