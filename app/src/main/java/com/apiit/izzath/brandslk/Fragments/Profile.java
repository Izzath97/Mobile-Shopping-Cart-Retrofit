package com.apiit.izzath.brandslk.Fragments;

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

import com.apiit.izzath.brandslk.Activities.login;
import com.apiit.izzath.brandslk.Interface.UserService;
import com.apiit.izzath.brandslk.Models.Register;
import com.apiit.izzath.brandslk.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;


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

        SharedPreferences sp= this.getActivity().getApplicationContext().getSharedPreferences(login.MyPREFERENCES, Context.MODE_PRIVATE);
        String username=sp.getString("User","");
        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(UserService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserService userService = retrofit.create(UserService.class);
        Call<Register> call2 = userService.getUser(username);
        call2.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {

                if (response.isSuccessful()) {
                }
                final Register users = response.body();
                name.setText(users.getFirstName());
                email.setText(users.getEmailAddress());

            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                System.out.println("Failed to Load Products");
                call.cancel();
            }
        });


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
