package com.apiit.izzath.brandslk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class button extends AppCompatActivity {
    Button male,female;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
        male=(Button) findViewById(R.id.male);
        female=(Button) findViewById(R.id.female);

    }

    public void onGenderButtonClicked(View view) {
        if(female.isPressed()){
            female.setBackgroundColor(getResources().getColor(R.color.greyone));
            male.setBackgroundColor(getResources().getColor(R.color.lightgray));
            Toast.makeText(this, "Female", Toast.LENGTH_SHORT).show();
            //  radioPressed = true;
        } else if (male.isPressed()){
            male.setBackgroundColor(getResources().getColor(R.color.greyone));
            female.setBackgroundColor(getResources().getColor(R.color.lightgray));

            Toast.makeText(this, "Male", Toast.LENGTH_SHORT).show();
            //radioPressed = true;
        } else {
            // radioPressed = false;
        }

    }
}
