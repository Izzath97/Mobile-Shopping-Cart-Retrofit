package com.apiit.izzath.wmad2.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.apiit.izzath.wmad2.Models.Product;
import com.apiit.izzath.wmad2.R;

import java.util.ArrayList;
import java.util.List;

public class home extends AppCompatActivity {
    ListView mlistview;
    TextView text;
    private List<Product> listitems;
    ArrayList<String> productlist = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listitems = new ArrayList<>();

        //comment



    }


}
