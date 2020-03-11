package com.apiit.izzath.brandslk.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.apiit.izzath.brandslk.Models.Reviews;
import com.apiit.izzath.brandslk.R;

import java.util.List;

/**
 * Created by Izzath on 6/6/2018.
 */

public class ReviewAdapter extends ArrayAdapter<Reviews> {
    RatingBar rb;
    TextView comment,username,timestamp;


    public ReviewAdapter(@NonNull Context context, List<Reviews> review) {
        super(context, R.layout.review_custom,review);

    }
    public View getView(int position, View convertView, ViewGroup parent){
        final Reviews rev=getItem(position);
        LayoutInflater layoutInflater=LayoutInflater.from(getContext());
        View views=layoutInflater.inflate(R.layout.review_custom,parent,false);
        username=(TextView)views.findViewById(R.id.username);
        rb=(RatingBar)views.findViewById(R.id.ratingBar2);
        comment=(TextView)views.findViewById(R.id.comment);
        timestamp=views.findViewById(R.id.time);
        timestamp.setText(rev.getDate());
        rb.setRating(rev.getValue());
        comment.setText(rev.getReview());
        username.setText(rev.getUser().getFirstName());

        return  views;
    }

}
