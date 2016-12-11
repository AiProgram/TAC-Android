package com.tac.iparttimejob.UI.Admin;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tac.iparttimejob.UI.MyManager.MyManager;

/**
 * Created by AiProgram on 2016/11/12.
 */

public class MainPagerAdapterForAdmin extends FragmentPagerAdapter {
    private String[] functionName=new String[]{"审核招聘","审核评价","查看反馈"};

    public MainPagerAdapterForAdmin(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            //审核招聘fragment
            case 0:return new UncheckingJobList();
            //审核评价fragment
            case 1:return new UncheckingCommentList();
            //查看反馈fragment
            case 2:return new UncheckingFeedbackList();
            default:return null;
        }
    }

    @Override
    public int getCount() {
        return functionName.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return functionName[position];
    }
}
