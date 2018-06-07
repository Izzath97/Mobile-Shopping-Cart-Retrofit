package com.apiit.izzath.wmad2.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabItem;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TabWidget;

import com.apiit.izzath.wmad2.Activities.adapter;
import com.apiit.izzath.wmad2.Models.Product;
import com.apiit.izzath.wmad2.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Izzath on 5/1/2018.
 */

public class default_home extends Fragment {

    public static final String men1 = "8" ;
    public static final String women1 = "10" ;
    private   List<Product> product;


    private TabWidget ttb;
    private TabItem all, men, women;
    private RecyclerView recyclerView;
    private  RecyclerView.Adapter adapter;

    Button test;
    private List<Product> storeMen=new ArrayList<>();
    private List<Product> storeWomen=new ArrayList<>();
    private   List<Product> products = Product.listAll(Product.class);


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.default_home, container, false);


        all = (TabItem) view.findViewById(R.id.all);
        men = (TabItem) view.findViewById(R.id.men);
        women = (TabItem) view.findViewById(R.id.women);


        test=(Button)view.findViewById(R.id.test);

        // Inflate the layout for this fragment
        recyclerView = (RecyclerView) view.findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
/*
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               List<TagProduct> tag=TagProduct.findWithQuery(TagProduct.class,"Select * from TAG_PRODUCT where tag = ? ","8");
                for (TagProduct aa:tag) {
                    if(aa.getTag().equals(men1)){

                        storeMen.add(aa.getProduct()) ;

                    }

                }

                product=storeMen;
               // adapter = new adapter(storeMen, getContext());
              //  adapter.notifyDataSetChanged();
            }
        });
        */
        product=products;
        adapter = new adapter(product, getContext());
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Style Omega");
    }


}


