package com.apiit.izzath.wmad2.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.apiit.izzath.wmad2.Models.Register;
import com.apiit.izzath.wmad2.R;

import java.util.List;

public class register extends AppCompatActivity {
EditText name,password1,email,age,password2;
String Rname,Rpassword1,Rpassword2,Remail,Rage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        name=(EditText)findViewById(R.id.name);
        password1=(EditText)findViewById(R.id.password1);
        password2=(EditText)findViewById(R.id.password2);
        age=(EditText)findViewById(R.id.age);
        email=(EditText)findViewById(R.id.email);

        }
public void cc(View view){}
        public void rrr(View view){
        boolean check=true;
        initialize();
        if(!validate()){


            Toast.makeText(this, "SignUp has Failed", Toast.LENGTH_SHORT).show();
            }
            else{
            List<Register> regiss=Register.listAll(Register.class);
            for (Register rg:regiss)   {
                if(rg.getName().equals(Rname)){
                    check=false;
                    Toast.makeText(this, "Please Enter Another UserName", Toast.LENGTH_SHORT).show();
                    name.setError("User Already Exist");
                }

            }
            if(check==true){
                Register rg=new Register(name.getText().toString(),password1.getText().toString(),password2.getText().toString(),email.getText().toString(),age.getText().toString());
                //  SugarRecord.saveInTx(rg);

                rg.save();
                Toast.makeText(register.this, "Successfully Registered with the APP", Toast.LENGTH_SHORT).show();
                Intent in=new Intent(getApplicationContext(),login.class);
                startActivity(in);
            }


        }


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
            if(Remail.isEmpty()|| Patterns.EMAIL_ADDRESS.matcher(Remail).matches()){
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

}
