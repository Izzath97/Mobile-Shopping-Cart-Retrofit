package com.apiit.izzath.wmad2.Fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apiit.izzath.wmad2.Activities.adapter;
import com.apiit.izzath.wmad2.Models.Product;
import com.apiit.izzath.wmad2.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Izzath on 4/25/2018.
 */

public class Home extends android.support.v4.app.Fragment{

    ArrayList<String> productlist = new ArrayList<>();
    private RecyclerView recyclerView;
    private  RecyclerView.Adapter adapters;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.home, container, false);
      List<Product> products = Product.listAll(Product.class);
        // Inflate the layout for this fragment
        recyclerView = (RecyclerView) view.findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapters = new adapter(products, getContext());
        adapters.notifyDataSetChanged();
        recyclerView.setAdapter(adapters);



return  view;
         }



}
