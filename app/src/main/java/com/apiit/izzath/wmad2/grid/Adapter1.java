package com.apiit.izzath.wmad2.grid;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.apiit.izzath.wmad2.Models.Favorites;
import com.apiit.izzath.wmad2.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Izzath on 4/21/2018.
 */

public class Adapter1 extends BaseAdapter {
    private  List <Favorites> favorites;
    private Context mContext;



    public Adapter1( List <Favorites> favorites, Context c) {
       this.favorites = favorites;
        mContext = c;
    }



    public int getCount() {
        return favorites.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

   Favorites fav;
    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        if(favorites.size()>position) {
            fav= favorites.get(position);
            //
        }


        ImageView imageView;

        if (convertView == null) {

            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(400, 400));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(6, 4, 3, 8);
        } else {
            imageView = (ImageView) convertView;
        }


        Picasso.get().load(fav.getProduct().getScaledImage()).into(imageView);

        return imageView;
    }



    private Integer[] mThumbIds = {
            R.drawable.launch_screen,R.drawable.launch_screen,R.drawable.launch_screen,R.drawable.launch_screen,R.drawable.launch_screen,
            R.drawable.launch_screen,R.drawable.launch_screen,R.drawable.launch_screen,R.drawable.launch_screen,
            R.drawable.launch_screen,R.drawable.launch_screen,R.drawable.launch_screen,R.drawable.launch_screen,
            R.drawable.launch_screen,R.drawable.launch_screen,R.drawable.launch_screen

    };
}