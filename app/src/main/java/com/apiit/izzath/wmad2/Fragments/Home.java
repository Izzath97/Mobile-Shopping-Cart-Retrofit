package com.apiit.izzath.wmad2.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.apiit.izzath.wmad2.Activities.adapter;
import com.apiit.izzath.wmad2.Models.Product;
import com.apiit.izzath.wmad2.Models.TagProduct;
import com.apiit.izzath.wmad2.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Izzath on 4/25/2018.
 */

public class Home extends Fragment implements SearchView.OnQueryTextListener {

    ArrayList<String> productlist = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapters;
    private SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home, container, false);


        List<Product> products = Product.listAll(Product.class);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapters = new adapter(products, getContext());
        recyclerView.setAdapter(adapters);


        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search, menu);
        MenuItem menuItem=menu.findItem(R.id.search);
        SearchView searchView=(SearchView)menuItem.getActionView();
        searchView.setOnQueryTextListener(this);

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String userinput=newText.toLowerCase();
        List<Product> newproduct=new ArrayList<>();

        List<TagProduct> productList=TagProduct.findWithQuery(TagProduct.class,"SELECT * FROM TAG_PRODUCT WHERE tag like '%"+userinput+"%'");

        for (TagProduct tag:productList
             ) {
            if(userinput.contains(tag.getTag())){
        newproduct.add(tag.getProduct());
            }
        }
        adapters=new adapter(newproduct,getContext());
        recyclerView.setAdapter(adapters);
return  true;
    }
}