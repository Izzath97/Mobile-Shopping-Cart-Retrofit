package com.apiit.izzath.brandslk.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.apiit.izzath.brandslk.Activities.login;
import com.apiit.izzath.brandslk.R;

/**
 * Created by Izzath on 6/10/2018.
 */

public class CancelOrders extends Fragment {

        ListView listview;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cancelorders, container, false);

        listview=(ListView)view.findViewById(R.id.listview);
        SharedPreferences sp = getActivity().getSharedPreferences(login.MyPREFERENCES, Context.MODE_PRIVATE);
        Long id = sp.getLong("id", 10);
     //   List<Cart> car = Cart.findWithQuery(Cart.class, "Select * from Cart where user = ? and status = ? ", id.toString(), "Canceled");
      //  CancelAdapter cancelAdapter=new CancelAdapter(getContext(),car);
       // listview.setAdapter(cancelAdapter);




        return  view;
    }
    }
