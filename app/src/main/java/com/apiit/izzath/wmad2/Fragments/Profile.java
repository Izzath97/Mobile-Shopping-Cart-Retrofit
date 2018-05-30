package com.apiit.izzath.wmad2.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.apiit.izzath.wmad2.Activities.login;
import com.apiit.izzath.wmad2.Models.Register;
import com.apiit.izzath.wmad2.R;

import java.util.List;

/**
 * Created by Izzath on 5/7/2018.
 */

public class Profile extends Fragment {
    Button edit;
    TextView name,email;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.profile, container, false);


        edit=(Button) view.findViewById(R.id.button3);
        name=(TextView )view.findViewById(R.id.name);
        email=(TextView)view.findViewById(R.id.email);

      //  user=Register.listAll(Register.class);
        SharedPreferences sp= this.getActivity().getApplicationContext().getSharedPreferences(login.MyPREFERENCES, Context.MODE_PRIVATE);

        List<Register> user= Register.findWithQuery(Register.class, "Select * from Register where name = ?",sp.getString("User","Name"));


        for (Register user1:user) {
                name.setText(sp.getString("User","Name"));
                email.setText(user1.getEmail().toString());



        }

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment=new ManageAccounts();
                FragmentManager fm=getActivity().getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.aaa,fragment).commit();
            }
        });


        return  view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Profile");
    }


}
