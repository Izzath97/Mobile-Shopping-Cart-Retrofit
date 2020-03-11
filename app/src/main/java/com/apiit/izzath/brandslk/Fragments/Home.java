package com.apiit.izzath.brandslk.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apiit.izzath.brandslk.Adapters.adapter;
import com.apiit.izzath.brandslk.Interface.JsonPlaceHolderApi;
import com.apiit.izzath.brandslk.Models.Product;
import com.apiit.izzath.brandslk.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Home extends Fragment implements SearchView.OnQueryTextListener {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapters;
    private SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home, container, false);
        com.apiit.izzath.brandslk.Models.Retrofit retrofit = new com.apiit.izzath.brandslk.Models.Retrofit();

        recyclerView = (RecyclerView) view.findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.returnApi();

        Call<List<Product>> p = jsonPlaceHolderApi.getProducts();
        p.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                final List<Product> product = response.body();
                adapters = new adapter(product, getContext());
                recyclerView.setAdapter(adapters);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });

        return view;
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String userinput = newText.toLowerCase();
        List<Product> newproduct = new ArrayList<>();

//        List<TagProduct> productList = TagProduct.findWithQuery(TagProduct.class, "SELECT * FROM TAG_PRODUCT WHERE tag like '%" + userinput + "%'");
//
//        for (TagProduct tag : productList
//        ) {
//            if (userinput.contains(tag.getTag())) {
//                newproduct.add(tag.getProduct());
//            }
//        }
        adapters = new adapter(newproduct, getContext());
        recyclerView.setAdapter(adapters);
        return true;
    }
}