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
import android.widget.EditText;
import android.widget.Toast;

import com.apiit.izzath.wmad2.Models.Register;
import com.apiit.izzath.wmad2.R;
import com.orm.SugarRecord;

import java.util.List;

public class ManageAccounts extends Fragment {
    EditText name,email,currentPassword,confirmPassword;
    Context con=getActivity();
    Button update;
    List<Register> user;
    Long id;
    String pass;
    public static final String MyPREFERENCES = "MyPrefs" ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_manage_accounts, container, false);
        SharedPreferences sp= this.getActivity().getApplicationContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        name=(EditText)view.findViewById(R.id.name);
        email=(EditText)view.findViewById(R.id.email);
        update=(Button)view.findViewById(R.id.update);
        currentPassword=(EditText)view.findViewById(R.id.password);
        confirmPassword=(EditText)view.findViewById(R.id.password2);

        name.setHint(sp.getString("User", "empty"));
        user  = Register.findWithQuery(Register.class, "Select * from Register where name = ?",sp.getString("User","Name"));


        for (Register user1:user) {
            this.id=user1.getId();
            if(user1.getName().equals(sp.getString("User",""))){
                email.setHint(user1.getEmail().toString());
                this.pass=user1.getPassword1();

            }

        }

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pass.equals(currentPassword.getText().toString())){


                    Register rff= SugarRecord.findById(Register.class,id);

                    rff.setName(name.getText().toString());
                    rff.setEmail(email.getText().toString());
                    rff.setPassword1(confirmPassword.getText().toString());
                    rff.save();
                    Toast.makeText(getContext(), "Sucessfullt Updated Profile", Toast.LENGTH_SHORT).show();
                    Fragment fragment=new Profile();
                    FragmentManager fm=getActivity().getSupportFragmentManager();
                    fm.beginTransaction().replace(R.id.aaa,fragment).commit();

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





