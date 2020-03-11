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
import android.widget.EditText;
import android.widget.Toast;

import com.apiit.izzath.brandslk.Interface.UserService;
import com.apiit.izzath.brandslk.Models.Register;
import com.apiit.izzath.brandslk.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class ManageAccounts extends Fragment {
    EditText name,email,currentPassword,confirmPassword;
    Context con=getActivity();
    Button update;
    Register user;
    String pass;
    public static final String MyPREFERENCES = "MyPrefs" ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_manage_accounts, container, false);
        final SharedPreferences sp= this.getActivity().getApplicationContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        name=(EditText)view.findViewById(R.id.name);
        email=(EditText)view.findViewById(R.id.email);
        update=(Button)view.findViewById(R.id.update);
        currentPassword=(EditText)view.findViewById(R.id.password);
        confirmPassword=(EditText)view.findViewById(R.id.password2);



        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(UserService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserService userService = retrofit.create(UserService.class);
        Call<Register> call2 = userService.getUser(sp.getString("User", "empty"));
        call2.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {

                if (response.isSuccessful()) {
                }
                final Register users = response.body();
                name.setHint(users.getFirstName());
                email.setHint(users.getEmailAddress());
                pass=users.getPassword();
                user=users;
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                System.out.println("Failed to Load Products");
                call.cancel();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pass.equals(currentPassword.getText().toString())){
                    retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                            .baseUrl(UserService.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    UserService userService = retrofit.create(UserService.class);
                    Call<Register> call2 = userService.updateUser(sp.getString("",""),user);
                    call2.enqueue(new Callback<Register>() {
                        @Override
                        public void onResponse(Call<Register> call, Response<Register> response) {

                            if (response.isSuccessful()) {
                            }
                            Toast.makeText(getContext(), "Sucessfullt Updated Profile", Toast.LENGTH_SHORT).show();
                    Fragment fragment=new Profile();
                    FragmentManager fm=getActivity().getSupportFragmentManager();
                    fm.beginTransaction().replace(R.id.aaa,fragment).commit();
                        }

                        @Override
                        public void onFailure(Call<Register> call, Throwable t) {
                            System.out.println("Failed to Load Products");
                            call.cancel();
                        }
                    });

                }
            }
        });


        return  view;
    }





    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Manage Accounts");
    }


    }





