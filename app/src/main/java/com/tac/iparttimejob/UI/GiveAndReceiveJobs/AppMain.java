package com.tac.iparttimejob.UI.GiveAndReceiveJobs;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.tac.iparttimejob.R;

/**
 * Created by AiProgram on 2016/10/21.
 */

public class AppMain extends AppCompatActivity {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_app_main);

        initView();
    }
    private void initView(){
        ViewPager mainViewPager=(ViewPager)findViewById(R.id.viewpager_main);
        MainPagerAdapter mainPagerAdapter=new MainPagerAdapter(getSupportFragmentManager());
        mainViewPager.setAdapter(mainPagerAdapter);

        //避免ViewPager销毁fragment
        mainViewPager.setOffscreenPageLimit(2);

        TabLayout mainTabLayout=(TabLayout)findViewById(R.id.tabLayout_give_jobs_bottom);
        mainTabLayout.setupWithViewPager(mainViewPager);

        TabLayout.Tab giveJob=mainTabLayout.getTabAt(0);
        TabLayout.Tab receiveJob=mainTabLayout.getTabAt(1);
        TabLayout.Tab myManager=mainTabLayout.getTabAt(2);

        giveJob.setIcon(R.drawable.ic_search_black_24dp);
        receiveJob.setIcon(R.drawable.ic_group_add_black_24dp);
        myManager.setIcon(R.drawable.ic_settings_black_24dp);
    }
}
