package com.tac.iparttimejob.UI.GiveAndReceiveJobs;


import android.support.v4.app.FragmentPagerAdapter;

import com.tac.iparttimejob.UI.MyManager.MyManager;

/**
 * Created by AiProgram on 2016/10/21.
 */

public class MainPagerAdapter extends FragmentPagerAdapter {
    private String[] functionNames=new String[]{"我要招聘","我要应聘","我的管理"};

    public MainPagerAdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
       switch (position){
           case 1:return new ReceiveJobsList();
           case 2:return new MyManager();
           case 3:return new GiveJobsList();
           default:return new GiveJobsList();
       }
    }

    @Override
    public int getCount() {
        return functionNames.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return functionNames[position];
    }
}
