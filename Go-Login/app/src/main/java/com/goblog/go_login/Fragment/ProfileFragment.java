package com.goblog.go_login.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goblog.go_login.R;


/**
 * Created by haikal on 4/3/2016.
 */
public class ProfileFragment extends Fragment  {
        public static int int_items = 2 ;
        private TabLayout tabLayout;
        private ViewPager viewPager ;
        public ProfileFragment(){  }
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
            tabLayout = (TabLayout) rootView.findViewById(R.id.tabLayout);
            viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);

            viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));

            tabLayout.post(new Runnable() {
                @Override
                public void run() {
                    tabLayout.setupWithViewPager(viewPager);
                }
            });
            return rootView;

        }
    class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm){
            super(fm);
        }
        public Fragment getItem(int position){
            switch (position){
                case 0 : return new HomeFragment();
                case 1 :return new JurusanFragment();
            }
            return null;
        }
        public int getCount(){
            return  int_items;
        }
        public CharSequence getPageTitle(int position){
            switch (position){
                case 0 : return "Menu";
                case 1 :return "Category";
            }
            return null;
        }
    }
}