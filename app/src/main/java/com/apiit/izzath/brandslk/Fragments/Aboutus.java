package com.apiit.izzath.brandslk.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.apiit.izzath.brandslk.Activities.MapsActivity;
import com.apiit.izzath.brandslk.R;

/**
 * Created by Izzath on 6/10/2018.
 */

public class Aboutus extends Fragment  {

Button locate,call,email;
EditText details;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.aboutus, container, false);

     locate=(Button)view.findViewById(R.id.locate);
     call=(Button)view.findViewById(R.id.call);
     email=(Button)view.findViewById(R.id.email);
        details=(EditText)view.findViewById(R.id.message);
       final String infor=details.getText().toString();
locate.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(getContext(), MapsActivity.class);
        startActivity(intent);
    }
});


     call.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent intent = new Intent(Intent.ACTION_DIAL);
             intent.setData(Uri.parse("tel:" + "0776307850"));
             startActivity(intent);
         }
     });


     email.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + "styleomega@gmail.com"));
             intent.putExtra(Intent.EXTRA_SUBJECT, "Product Feedback");
             intent.putExtra(Intent.EXTRA_TEXT, infor);
             startActivity(intent);
         }
     });

        return  view;
    }


    }
