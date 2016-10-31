package com.tac.iparttimejob.UI.GiveAndReceiveJobs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.tac.iparttimejob.R;

/**
 * Created by AiProgram on 2016/10/21.
 */

public class AppMain extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_app_main);

        initView();
    }
    private void initView(){
        ViewPager mainViewPager=(ViewPager)findViewById(R.id.viewpager_main);
        MainPagerAdapter mainPagerAdapter=new MainPagerAdapter(getSupportFragmentManager());
        mainViewPager.setAdapter(mainPagerAdapter);

        TabLayout mainTabLayout=(TabLayout)findViewById(R.id.tabLayout_give_jobs_bottom);
        mainTabLayout.setupWithViewPager(mainViewPager);

        TabLayout.Tab one=mainTabLayout.getTabAt(0);
        TabLayout.Tab two=mainTabLayout.getTabAt(1);
        TabLayout.Tab three=mainTabLayout.getTabAt(2);

        one.setIcon(R.drawable.user_head_image);
        two.setIcon(R.drawable.user_head_image);
        three.setIcon(R.drawable.user_head_image);
    }
}
