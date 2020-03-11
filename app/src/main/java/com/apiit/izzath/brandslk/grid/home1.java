package com.apiit.izzath.brandslk.grid;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.apiit.izzath.brandslk.Activities.login;
import com.apiit.izzath.brandslk.Adapters.Adapter1;
import com.apiit.izzath.brandslk.Interface.WishListService;
import com.apiit.izzath.brandslk.Models.Favorites;
import com.apiit.izzath.brandslk.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class home1 extends Fragment {

    GridView gridview;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.home1, container, false);
        SharedPreferences sp = getActivity().getSharedPreferences(login.MyPREFERENCES, Context.MODE_PRIVATE);
         String user = sp.getString("User", "");

        retrofit2.Retrofit retrofit=new retrofit2.Retrofit.Builder()
                .baseUrl(WishListService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WishListService wishListService=retrofit.create(WishListService.class);

        Call<List<Favorites>> call=wishListService.getWishList(user);
        gridview = (GridView)view. findViewById(R.id.gridview1);
        call.enqueue(new Callback<List<Favorites>>() {
            @Override
            public void onResponse(Call<List<Favorites>> call, Response<List<Favorites>> response) {
                List<Favorites>  wishList=response.body();

                gridview.setAdapter(new Adapter1(wishList,getContext()));

            }
            @Override
            public void onFailure(Call<List<Favorites>> call, Throwable t) {

            }
        });


       // List <Favorites> favorites=Favorites.find(Favorites.class,"user = ?",userid.toString());


        return view;
    }


}
