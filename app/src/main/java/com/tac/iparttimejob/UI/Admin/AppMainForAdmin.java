package com.tac.iparttimejob.UI.Admin;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;


import com.tac.iparttimejob.R;

/**
 * Created by AiProgram on 2016/11/12.
 */

public class AppMainForAdmin extends AppCompatActivity {
    private ViewPager vp_admin_app_main;
    private TabLayout tl_admin_app_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_app_main_for_admin);

        getViews();

        initViews();
    }

    private void getViews(){
        vp_admin_app_main=(ViewPager) findViewById(R.id.vp_admin_app_main);
        tl_admin_app_main=(TabLayout) findViewById(R.id.tl_admin_app_main);
    }

    private void initViews(){
        MainPagerAdapterForAdmin mainPagerAdapterForAdmin=new MainPagerAdapterForAdmin(getSupportFragmentManager());
        vp_admin_app_main.setAdapter(mainPagerAdapterForAdmin);
        vp_admin_app_main.setOffscreenPageLimit(2);

        tl_admin_app_main.setupWithViewPager(vp_admin_app_main);

        TabLayout.Tab checkJob=tl_admin_app_main.getTabAt(0);
        TabLayout.Tab checkComment=tl_admin_app_main.getTabAt(1);
        TabLayout.Tab checkFeedback=tl_admin_app_main.getTabAt(2);

        checkJob.setIcon(R.drawable.ic_description_black_24dp);
        checkComment.setIcon(R.drawable.ic_comment_black_24dp);
        checkFeedback.setIcon(R.drawable.ic_feedback_black_24dp);
    }
}
