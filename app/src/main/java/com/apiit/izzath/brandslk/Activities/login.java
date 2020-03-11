package com.apiit.izzath.brandslk.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.apiit.izzath.brandslk.Interface.JsonPlaceHolderApi;
import com.apiit.izzath.brandslk.Interface.UserService;
import com.apiit.izzath.brandslk.Models.Register;
import com.apiit.izzath.brandslk.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class login extends AppCompatActivity {

    EditText etusername;
    EditText etpassword;
    TextView signup;
    Button login;
    ImageView user ,passs;
    List<Register> regiss;
    public static final String MyPREFERENCES = "MyPrefs" ;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(UserService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi=retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Register>> pp=jsonPlaceHolderApi.getUsers();

        pp.enqueue(new Callback<List<Register>>() {
            @Override
            public void onResponse(Call<List<Register>> call, Response<List<Register>> response) {
                List<Register>  r=response.body();
                regiss=r;
            }
            @Override
            public void onFailure(Call<List<Register>> call, Throwable t) {

            }
        });



//        Call<Post> p=jsonPlaceHolderApi.getPost(1);
//
//        p.enqueue(new Callback<Post>() {
//            @Override
//            public void onResponse(Call<Post> call, Response<Post> response) {
//                Post  products=response.body();
//                Post  products1=response.body();
//                Post  products2=response.body();
//
//
//            }
//            @Override
//            public void onFailure(Call<Post> call, Throwable t) {
//System.out.println("asdasd");
//            }
//        });


        login=(Button)findViewById(R.id.login);
        etusername=(EditText)findViewById(R.id.username);
        etpassword=(EditText)findViewById(R.id.password);
        signup=(TextView)findViewById(R.id.signup);

        signup.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent register=new Intent(login.this,register.class);
        startActivity(register);
    }
});


    login.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            boolean check=false;
            String users=etusername.getText().toString();
            String pass=etpassword.getText().toString();
            if (regiss!=null) {
                for (Register rg : regiss) {
                    if ((users.equals(rg.getUsername().toString())) && (pass.equals(rg.getPassword().toString()))) {
                        check = true;
                        id = rg.getId();
                    }
                }


                if (check) {
                    SharedPreferences sp = getApplicationContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("User", users);
                    editor.putInt("id", id);
                    editor.commit();
                    Toast.makeText(com.apiit.izzath.brandslk.Activities.login.this, "Logged In", Toast.LENGTH_SHORT).show();
                    Intent login = new Intent(getApplicationContext(), Drawer.class);
                    startActivity(login);
                } else {
                    Toast.makeText(login.this, "Enter Valid User Credintials", Toast.LENGTH_SHORT).show();

                }


            }
            else{

                Toast.makeText(login.this, "Please Check ur connection And Try Again Later", Toast.LENGTH_SHORT).show();
            }
        }
    });


    }





}
