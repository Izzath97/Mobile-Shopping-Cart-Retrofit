package com.apiit.izzath.wmad2.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.apiit.izzath.wmad2.Activities.adapter;
import com.apiit.izzath.wmad2.Models.Product;
import com.apiit.izzath.wmad2.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Izzath on 4/25/2018.
 */

public class Home extends android.support.v4.app.Fragment{

    ListView mlistview;
    TextView text;
    private List<Product> listitems;
    ArrayList<String> productlist = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.home, container, false);

        // Inflate the layout for this fragment
        recyclerView = (RecyclerView)view.findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        listitems = new ArrayList<>();

        Gson gson=new Gson();

        String jsoni="[{\"Id\":1,\"Name\":\"T-Shirt\",\"ShortDescription\":\"V_Neck T_Shirt\",\"LongDescription\":\"Blue V-Neck T-Shirt\",\"Catagory\":1,\"Sub-Catogary\":\"Shirt\",\"Price\":750,\"Quantity\":50,\"Active\":true,\"ScaledImage\":\"http://via.placeholder.com/200x200?text=Product-01\",\"FullImage\":\"http://via.placeholder.com/300x350?text=Product-01\"},{\"Id\":2,\"Name\":\"T-Shirt\",\"ShortDescription\":\"R_Neck T_Shirt\",\"LongDescription\":\"Black R-Neck T-Shirt\",\"Catagory\":1,\"Sub-Catogary\":\"Shirt\",\"Price\":750,\"Quantity\":30,\"Active\":true,\"ScaledImage\":\"http://via.placeholder.com/200x200?text=Product-02\",\"FullImage\":\"http://via.placeholder.com/300x350?text=Product-02\"},{\"Id\":3,\"Name\":\"Shirt\",\"ShortDescription\":\"Plain Shirt\",\"LongDescription\":\"Plain L/S Shirt\",\"Catagory\":2,\"Sub-Catogary\":\"Shirt\",\"Price\":1750,\"Quantity\":20,\"Active\":true,\"ScaledImage\":\"http://via.placeholder.com/200x200?text=Product-03\",\"FullImage\":\"http://via.placeholder.com/300x350?text=Product-03\"},{\"Id\":4,\"Name\":\"Product #4 name\",\"ShortDescription\":\"Product #4 short description goes here.\",\"LongDescription\":\"Product #4 long description goes here.\",\"Catagory\":1,\"Sub-Catogary\":\"Shirt\",\"Price\":100,\"Quantity\":50,\"Active\":true,\"ScaledImage\":\"http://www.freepngimg.com/png/17322-t-shirt-png-file\",\"FullImage\":\"http://www.freepngimg.com/png/17322-t-shirt-png-file\"},{\"Id\":5,\"Name\":\"Product #5 name\",\"ShortDescription\":\"Product #5 short description goes here.\",\"LongDescription\":\"Product #5 long description goes here.\",\"Catagory\":1,\"Sub-Catogary\":\"Shirt\",\"Price\":100,\"Quantity\":50,\"Active\":true,\"ScaledImage\":\"http://www.freepngimg.com/artistic/color-effects\",\"FullImage\":\"http://www.freepngimg.com/artistic/color-effects\"},{\"Id\":6,\"Name\":\"Product #6 name\",\"ShortDescription\":\"http://www.freepngimg.com/download/tshirt/4-2-t-shirt-png-file.png\",\"LongDescription\":\"http://www.freepngimg.com/download/tshirt/4-2-t-shirt-png-file.png\",\"Catagory\":1,\"Sub-Catogary\":\"Shirt\",\"Price\":100,\"Quantity\":50,\"Active\":true,\"ScaledImage\":\"http://www.freepngimg.com/download/tshirt/4-2-t-shirt-png-file.png\",\"FullImage\":\"http://www.freepngimg.com/download/tshirt/4-2-t-shirt-png-file.png\"},{\"Id\":7,\"Name\":\"Product #7 name\",\"ShortDescription\":\"Product #7 short description goes here.\",\"LongDescription\":\"Product #7 long description goes here.\",\"Catagory\":1,\"Sub-Catogary\":\"Shirt\",\"Price\":100,\"Quantity\":50,\"Active\":true,\"ScaledImage\":\"http://via.placeholder.com/200x200?text=Product-07\",\"FullImage\":\"http://via.placeholder.com/300x350?text=Product-07\"},{\"Id\":8,\"Name\":\"Product #8 name\",\"ShortDescription\":\"Product #8 short description goes here.\",\"LongDescription\":\"Product #8 long description goes here.\",\"Catagory\":1,\"Sub-Catogary\":\"Shirt\",\"Price\":100,\"Quantity\":50,\"Active\":true,\"ScaledImage\":\"http://via.placeholder.com/200x200?text=Product-08\",\"FullImage\":\"http://via.placeholder.com/300x350?text=Product-08\"},{\"Id\":9,\"Name\":\"Product #9 name\",\"ShortDescription\":\"Product #9 short description goes here.\",\"LongDescription\":\"Product #9 long description goes here.\",\"Catagory\":1,\"Sub-Catogary\":\"Shirt\",\"Price\":100,\"Quantity\":50,\"Active\":true,\"ScaledImage\":\"http://via.placeholder.com/200x200?text=Product-09\",\"FullImage\":\"http://via.placeholder.com/300x350?text=Product-09\"},{\"Id\":10,\"Name\":\"Product #10 name\",\"ShortDescription\":\"Product #10 short description goes here.\",\"LongDescription\":\"Product #10 long description goes here.\",\"Catagory\":1,\"Sub-Catogary\":\"Shirt\",\"Price\":100,\"Quantity\":50,\"Active\":true,\"ScaledImage\":\"http://via.placeholder.com/200x200?text=Product-10\",\"FullImage\":\"http://via.placeholder.com/300x350?text=Product-10\"}]";

        Type listType = new TypeToken<List<Product>>() {}.getType();
        final List<Product> products = gson.fromJson(jsoni ,listType);

        adapter = new adapter(products,getContext() );
        recyclerView.setAdapter(adapter);
return  view;
         }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Frargment1");
    }


}
