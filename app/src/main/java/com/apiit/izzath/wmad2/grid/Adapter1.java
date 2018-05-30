package com.apiit.izzath.wmad2.grid;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.apiit.izzath.wmad2.Models.Product;
import com.apiit.izzath.wmad2.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Izzath on 4/21/2018.
 */

public class Adapter1 extends BaseAdapter {
    private List<Product> listitem;
    private Context mContext;



    public Adapter1(List<Product> listitem, home1 c) {
       this.listitem = listitem;
        mContext = c;
    }



    public int getCount() {
        return listitem.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

   Product product;
    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        if(listitem.size()>position) {
            product= listitem.get(position);
            //
        }

        // mThumbIds=product.getScaledImage();
       //List<Product> product= (List<Product>) listitem.get(position);


        ImageView imageView;
       // img=(ImageView)convertView.findViewById(R.id.imageView3);
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(250, 250));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(6, 4, 3, 8);
        } else {
            imageView = (ImageView) convertView;
        }

       // imageView.setImageResource();
        // imageView.setImageDrawable(R.drawable.logo1);
      //  imageView.setImageResource(product.getScaledImage());
        Picasso.get().load(product.getScaledImage()).into(imageView);
     //  imageView.setImageResource(R.drawable.launch_screen);
       // img.setImageDrawable(R.drawable.ic_launcher_foreground);
        return imageView;
    }


    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.launch_screen,R.drawable.launch_screen,R.drawable.launch_screen,R.drawable.launch_screen,R.drawable.launch_screen,
            R.drawable.launch_screen,R.drawable.launch_screen,R.drawable.launch_screen,R.drawable.launch_screen,
            R.drawable.launch_screen,R.drawable.launch_screen,R.drawable.launch_screen,R.drawable.launch_screen,
            R.drawable.launch_screen,R.drawable.launch_screen,R.drawable.launch_screen

    };
}