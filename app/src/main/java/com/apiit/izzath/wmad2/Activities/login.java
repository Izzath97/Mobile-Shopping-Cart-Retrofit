package com.apiit.izzath.wmad2.Activities;

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

import com.apiit.izzath.wmad2.Models.Register;
import com.apiit.izzath.wmad2.R;

import java.util.List;

public class login extends AppCompatActivity {

    EditText etusername;
    EditText etpassword;
    TextView signup;
    Button login;
    ImageView user ,passs;
    public static final String MyPREFERENCES = "MyPrefs" ;
    Long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        login=(Button)findViewById(R.id.login);
        etusername=(EditText)findViewById(R.id.username);
        etpassword=(EditText)findViewById(R.id.password);
        signup=(TextView)findViewById(R.id.signup);
       // user=(ImageView)findViewById(R.id.passpic);
       // passs=(ImageView)findViewById(R.id.passpic);


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
            List<Register> regiss=Register.listAll(Register.class);
            boolean check=false;
            String users=etusername.getText().toString();
            String pass=etpassword.getText().toString();
            for (Register rg:regiss) {
                if((users.equals(rg.getName().toString())) && (pass.equals(rg.getPassword1().toString()))) {
                    check=true;
                    id=rg.getId();
                }
            }


  if(check){
    SharedPreferences sp =getApplicationContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sp.edit();
    editor.putString("User",users);
    editor.putLong("id",id);
    editor.commit();
    Toast.makeText(com.apiit.izzath.wmad2.Activities.login.this, "Logged In", Toast.LENGTH_SHORT).show();
    Intent login = new Intent(getApplicationContext(),Drawer.class);
    startActivity(login);
           }
  else {
    Toast.makeText(login.this, "Enter Valid User Credintials", Toast.LENGTH_SHORT).show();

        }



        }
    });


    }





}
