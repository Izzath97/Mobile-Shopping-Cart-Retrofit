package com.apiit.izzath.wmad2.Activities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import com.apiit.izzath.wmad2.Models.Purchase;

/**
 * Created by Izzath on 5/31/2018.
 */

public class CheckoutAdapter extends ArrayAdapter<Purchase> {
    public CheckoutAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }



}
