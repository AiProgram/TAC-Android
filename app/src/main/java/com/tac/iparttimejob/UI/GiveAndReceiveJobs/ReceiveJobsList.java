package com.tac.iparttimejob.UI.GiveAndReceiveJobs;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import com.tac.iparttimejob.Class.Application;
import com.tac.iparttimejob.Class.RecuitResult;
import com.tac.iparttimejob.R;
import com.tac.iparttimejob.UI.Utils.DataType;
import com.tac.iparttimejob.UI.Utils.RefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AiProgram on 2016/11/10.
 * 这是我要应聘列表的显示模块
 */

public class ReceiveJobsList extends Fragment{

    private RefreshRecyclerView rv_receive_jobs;
    private TabLayout tl_receive_jobs_top;
    private SwipeRefreshLayout srl_receive_jobs;

    private Handler handler=new Handler();

    private MyReceiveJobsAdapter signedListAdapter;
    private MyReceiveJobsAdapter unsignedListAdapter;

    private List<Application> signeList=new ArrayList<>();
    private List<RecuitResult.Recuit> unsignedList=new ArrayList<>();

    //防止多次初始化
    boolean inited=false;

    //一次获得的数量以及显示列表指针
    int page=1;
    int rows=10;
    int pointer=0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_receive_jobs_list,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getViews();

        //注意注意,initViews必须在initListener之前调用，否则出错
        initViews();

        initListener();
    }

    //获取所有控件
    private void getViews(){
        View fragmentView=getView();
        rv_receive_jobs=(RefreshRecyclerView) fragmentView.findViewById(R.id.rv_receive_jobs);
        tl_receive_jobs_top=(TabLayout) fragmentView.findViewById(R.id.tl_receive_jobs_top);
        srl_receive_jobs=(SwipeRefreshLayout) fragmentView.findViewById(R.id.srl_receive_jobs);
    }

    //初始化文字显示等
    private void initViews(){
        MyReceiveJobsAdapter signedAdapter=new MyReceiveJobsAdapter(signeList, DataType.SIGNED_JOB_LIST);
        MyReceiveJobsAdapter unsignedAdapter=new MyReceiveJobsAdapter(unsignedList,DataType.UNSIGNED_JOB_LIST);

        tl_receive_jobs_top.addTab(tl_receive_jobs_top.newTab().setText("未申请"));
        tl_receive_jobs_top.addTab(tl_receive_jobs_top.newTab().setText("已申请"));
    }

    //初始化各事件监听器
    private void initListener(){

        //上方TabLayout切换事件
        tl_receive_jobs_top.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case DataType.UNSIGNED_JOB_LIST:{
                        rv_receive_jobs.setAdapter(unsignedListAdapter);
                        rv_receive_jobs.notifyData();
                    }break;
                    case DataType.SIGNED_JOB_LIST:{
                        rv_receive_jobs.setAdapter(signedListAdapter);
                        rv_receive_jobs.notifyData();
                    }break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    //首次进入页面时初始化数据
    private void initData(){

    }

    //下拉刷新
    private void pullDownRefresh(){

    }

    //上拉加载更多
    private void pullUpRefresh(){

    }

    //静态数组传入有问题，这里使用手动复制方法
    private void cloneSignedList(){

    }

    private void cloneUnsignedList(){

    }

    //上拉更多时使用
    private void addSigned(){

    }

    private void addUnsignedList(){

    }

    //跳转事件先加载，较为复杂,这里封装起来
    private void jumpToJobContent(){

    }
}
