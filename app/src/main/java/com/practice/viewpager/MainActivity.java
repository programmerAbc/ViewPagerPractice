package com.practice.viewpager;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.mainVp)
    ViewPager mainVp;
    MyViewPagerAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        adapter = new MyViewPagerAdapter(this,getSupportFragmentManager());
        mainVp.setAdapter(adapter);
        tabLayout.setupWithViewPager(mainVp, false);
        adapter.populateTabLayout(tabLayout);
    }

}
