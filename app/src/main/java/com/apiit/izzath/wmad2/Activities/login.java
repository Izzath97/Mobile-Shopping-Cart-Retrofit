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

            String users=etusername.getText().toString();
            String pass=etpassword.getText().toString();
            for (Register rg:regiss) {
                if((users.equals(rg.getName().toString())) && (pass.equals(rg.getPassword1().toString()))) {

                    SharedPreferences sp =getApplicationContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("User",users);
                    editor.putLong("id",rg.getId());
                    editor.commit();
                    Toast.makeText(com.apiit.izzath.wmad2.Activities.login.this, "Logged In", Toast.LENGTH_SHORT).show();
                    // check=true;
                    Intent login = new Intent(getApplicationContext(),Drawer.class);
                    startActivity(login);

                }
                else {

                    //Toast.makeText(com.apiit.izzath.wmad2.Activities.login.this, "Please Try Again", Toast.LENGTH_SHORT).show();
                }
            }

        }
    });


    }


//public void validate(String username,String password) throws SQLException {
    //    boolean check=false;
//List<Register> regiss=Register.listAll(Register.class);
//regiss.getClass();
//Register rgg=new Register();
    //List<Register> user1= Register.find(Register.class,"name=?",username);
  //  ResultSet rs = (ResultSet) Register.findWithQuery(Register.class,"Select * from Register where name=?",username);
  //  String name2=rs.getString("name");
  //  String password2=rs.getString("password1");
       // List<Register> password1= Register.find(Register.class,"password1=?",password);
   // for (Register rg:regiss) {
     //   if((username.equals(rg.getName().toString())) && (password.equals(rg.getPassword1().toString()))) {
       //     Toast.makeText(com.apiit.izzath.wmad2.Activities.login.this, "Pass", Toast.LENGTH_SHORT).show();
            // check=true;
         //   Intent login = new Intent(com.apiit.izzath.wmad2.Activities.login.this, home.class);
          //  startActivity(login);
      //  }
           //  else{
          //      Toast.makeText(com.apiit.izzath.wmad2.Activities.login.this, "Please Try Again", Toast.LENGTH_SHORT).show();
         //   }


   // }
 //   if(check==true){
  //      Intent login=new Intent(com.apiit.izzath.wmad2.Activities.login.this,home.class);
  //      startActivity(login);

   // }



//}




}
