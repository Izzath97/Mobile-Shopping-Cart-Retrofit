package com.apiit.izzath.wmad2.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.apiit.izzath.wmad2.R;

public class testing extends AppCompatActivity {
TextView testing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);
      //  testing=(TextView)findViewById(R.id.testing);

        SharedPreferences sp= this.getSharedPreferences(login.MyPREFERENCES, Context.MODE_PRIVATE);

Intent intent=getIntent();
Long asd=intent.getLongExtra("cream",10);

testing.setText(asd.toString());
//        List<Product> user= Product.findWithQuery(Product.class, "Select * from Product where Name = ?","T-Shirt");


     //   for (Product user1:user) {
       //     testing.setText(user1.getShortDescription());
           // name.setText(sp.getString("User","Name"));
           // email.setText(user1.getEmail().toString());



      //  }
        /*
//        Product.saveInTx(products);
Intent intent=getIntent();
Long aaa=intent.getLongExtra("asd",10);
String title=intent.getStringExtra("cream");
testing.setText(String.valueOf(aaa));
//testing.setText(aaa.toString());
*/




    }


    @Override
    public void onBackPressed() {
            Intent login = new Intent(getApplicationContext(),Drawer.class);
            startActivity(login);


        super.onBackPressed();
    }
}
