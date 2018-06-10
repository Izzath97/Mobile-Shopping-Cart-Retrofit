package com.apiit.izzath.wmad2.Fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apiit.izzath.wmad2.Models.Product;
import com.apiit.izzath.wmad2.R;

import java.util.List;

/**
 * Created by Izzath on 5/1/2018.
 */

public class default_home extends Fragment {

    public static final String men1 = "8" ;
    public static final String women1 = "10" ;

    private ViewPager mViewPager;




    private   List<Product> products = Product.listAll(Product.class);
    private SectionsPagerAdapter mSectionsPagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.default_home, container, false);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getActivity().getSupportFragmentManager());
        mViewPager = (ViewPager)view. findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout)view. findViewById(R.id.tabwid);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));



        // Inflate the layout for this fragment


        return view;
    }

    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position){
                case 0:
                    Home home=new Home();
                    return  home;
                case 1:
                    MenHome ad1=new MenHome();
                    return  ad1;
                case 2:
                    WomenHome ad2=new WomenHome();
                    return  ad2;
                default:

                    return  null;


            }

        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }



}


