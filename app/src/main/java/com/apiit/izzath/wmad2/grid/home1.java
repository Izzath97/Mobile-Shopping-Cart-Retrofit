package com.apiit.izzath.wmad2.grid;

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

import com.apiit.izzath.wmad2.Activities.login;
import com.apiit.izzath.wmad2.Models.Favorites;
import com.apiit.izzath.wmad2.R;

import java.util.List;

public class home1 extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.home1, container, false);
        SharedPreferences sp = getActivity().getSharedPreferences(login.MyPREFERENCES, Context.MODE_PRIVATE);
        final Long userid = sp.getLong("id", 10);

        List <Favorites> favorites=Favorites.find(Favorites.class,"user = ?",userid.toString());
        GridView gridview = (GridView)view. findViewById(R.id.gridview1);
        gridview.setAdapter(new Adapter1(favorites,getContext()));


        return view;
    }


}
