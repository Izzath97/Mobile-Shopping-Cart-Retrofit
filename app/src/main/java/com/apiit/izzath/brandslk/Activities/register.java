package com.apiit.izzath.brandslk.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
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

public class register extends AppCompatActivity {
 EditText name,password1,email,age,password2,number,userName;
String Rname,Rpassword1,Rpassword2,Remail,Rage;
    boolean check=true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        name=(EditText)findViewById(R.id.name);
        password1=(EditText)findViewById(R.id.password1);
        password2=(EditText)findViewById(R.id.password2);
        age=(EditText)findViewById(R.id.age);
        email=(EditText)findViewById(R.id.email);
        number=(EditText)findViewById(R.id.contact);
        userName=(EditText)findViewById(R.id.userName);
        final Button register=findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initialize();
                if(!validate()){
                    Toast.makeText(register.this, "SignUp has Failed", Toast.LENGTH_SHORT).show();
                }
                else{

                    retrofit2.Retrofit retrofit=new retrofit2.Retrofit.Builder()
                            .baseUrl(UserService.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    UserService userService=retrofit.create(UserService.class);

                    Call<Register> pp=userService.getUser(name.getText().toString());
                    pp.enqueue(new Callback <Register>() {
                        @Override
                        public void onResponse(Call <Register> call, Response<Register> response) {
                            Register  r=response.body();
                            if(r!=null){
                                check=false;
                                Toast.makeText(register.this, "Please Enter Another UserName", Toast.LENGTH_SHORT).show();
                                name.setError("User Already Exist");
                            }
                            else{
                                String status="Shopper";
                                Register register=new Register(Rname,Rpassword1,Remail,Rname,"","",status);
                                addUser(register);
                                Intent in=new Intent(getApplicationContext(),login.class);
                                startActivity(in);
                            }
                        }
                        @Override
                        public void onFailure(Call<Register> call, Throwable thtow) {
                            Register register=new Register(Rname,Rpassword1,Remail,Rname,"","","Shopper");
                            addUser(register);
                            Intent in=new Intent(getApplicationContext(),login.class);
                            startActivity(in);
                        }
                    });
                }
            }
        });
        }

        public void initialize(){
            Rname=name.getText().toString();
            Rpassword1=password1.getText().toString();
            Rpassword2=password2.getText().toString();
            Remail=email.getText().toString();
            Rage=age.getText().toString();

        }

        public boolean validate(){
            boolean valid=true;
            if(Rname.isEmpty()||Rname.length()>29){
                name.setError("Please Enter a Valid Name");
                valid=false;
            }
            if(Rpassword1.isEmpty()||Rpassword1.length()>20){
                password1.setError("Please Enter a Valid Password");
                valid=false;
            }
            if(Rpassword2.isEmpty()||Rpassword2.length()>20){
                password2.setError("Please Enter a Valid Password");
                valid=false;
            }
            if(Remail.isEmpty()|| Patterns.EMAIL_ADDRESS.matcher(Remail).equals(true)){
                email.setError("Please Enter a Valid Email Address");
                valid=false;
            }
            if(Rage.isEmpty()||Rage.length()>3){
               age.setError("Please Enter a Valid Age");
                valid=false;
            }
            if(!Rpassword1.equals(Rpassword2)){
              //  Toast.makeText(this, "Passowords Doesnt Match", Toast.LENGTH_SHORT).show();
                password2.setError("Passwords Doesnt Match");
                valid=false;
            }


          return valid;
        }

public void login(String username, String password){




}
public void addUser(Register register){
    retrofit2.Retrofit retrofit=new retrofit2.Retrofit.Builder()
            .baseUrl(UserService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    UserService userService=retrofit.create(UserService.class);
    Call<Register> call2=userService.addUser(register);
    call2.enqueue(new Callback<Register>() {
        @Override
        public void onResponse(Call<Register> calls, Response<Register> r) {
            Toast.makeText(getApplicationContext(), "User successfully created", Toast.LENGTH_LONG).show();

        }
        @Override
        public void onFailure(Call<Register> calls, Throwable t) {
            Toast.makeText(getApplicationContext(), "User successfully created", Toast.LENGTH_LONG).show();
        }
    });

}
}
