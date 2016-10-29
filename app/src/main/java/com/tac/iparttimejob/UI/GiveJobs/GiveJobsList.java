package com.tac.iparttimejob.UI.GiveJobs;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tac.iparttimejob.R;
import com.tac.iparttimejob.UI.Utils.DataType;
import com.tac.iparttimejob.UI.Utils.MyGiveJobAdapter;
import com.tac.iparttimejob.UI.Utils.RefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by AiProgram on 2016/10/21.
 */

/**
 * 这是一个Fragment，用来显示
 * 我要招聘->未失效发布招聘信息列表
 *
 */
public class GiveJobsList extends Fragment{

    private RefreshRecyclerView rv_give_jobs;
    private SwipeRefreshLayout srl_give_jobs;
    private TabLayout tabLayout;
    private Handler handler=new Handler();
    private MyGiveJobAdapter validJobAdapter;
    private MyGiveJobAdapter UnvalidJobAdapter;


    //分别储存进行中和失效的信息
    private List<String> validJobList=new ArrayList<>();
    private List<String> UnvalidJooList=new ArrayList<>();

    //防止多次初始化
    boolean inited=false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_give_jobs_valid_list,container,false);
        return view;
    }

    @Override
    /*耗时初始化操作放在这里*/
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(inited==false) {
            initData(DataType.VALID_JOB_LIST);
            initData(DataType.UNVALID_JOB_LIST);
            inited=true;
        }
        initView();
        initRefreshListener();
    }


    //初始化数据,引入网络操作后修改
    private void initData(int pageNum) {

        if(pageNum==DataType.VALID_JOB_LIST){
            for(int i=1;i<=50;i++)
                validJobList.add("有效招聘   "+i);
        }
        else if(pageNum==DataType.UNVALID_JOB_LIST){
            for(int i=1;i<50;i++)
                UnvalidJooList.add("无效招聘  "+i);
        }
    }


    //初始化各种View
    private void initView() {
        View fragmentView=getView();
        rv_give_jobs=(RefreshRecyclerView) fragmentView.findViewById(R.id.rv_give_jobs);
        srl_give_jobs=(SwipeRefreshLayout) fragmentView.findViewById(R.id.srl_give_jobs);
        tabLayout = (TabLayout)fragmentView.findViewById(R.id.tabLayout_give_jobs_top);
        validJobAdapter=new MyGiveJobAdapter(validJobList);
        UnvalidJobAdapter=new MyGiveJobAdapter(UnvalidJooList);

        //设置ToolBar
            tabLayout.addTab(tabLayout.newTab().setText("进行中"));
            tabLayout.addTab(tabLayout.newTab().setText("已失效"));

        //设置Toolbar主标题
        Toolbar mToolbar=(Toolbar)fragmentView.findViewById(R.id.toolbar_give_jobs_top);
//        mToolbar.setTitle("用户名");
        //设置Toolbar副标题
//        mToolbar.setSubtitle("Sub title");

        //TabLayout的切换监听
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //切换的时候更新RecyclerView
                if(tab.getPosition()==DataType.VALID_JOB_LIST){
                    rv_give_jobs.setAdapter(validJobAdapter);
                    rv_give_jobs.notifyData();
                }
                else if(tab.getPosition()==DataType.UNVALID_JOB_LIST){
                    rv_give_jobs.setAdapter(UnvalidJobAdapter);
                    rv_give_jobs.notifyData();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //设置RecycleView
        rv_give_jobs.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rv_give_jobs.setItemAnimator(new DefaultItemAnimator());
        rv_give_jobs.setAdapter(validJobAdapter);
        //设置RecyclerVi可以上拉刷新
        rv_give_jobs.setLoadMoreEnable(true);
    }



    /*
    初始化刷新监听器
    */
    private void initRefreshListener(){
        rv_give_jobs.setOnLoadMoreListener(new RefreshRecyclerView.OnLoadMoreListener() {
            @Override
            public void loadMoreListener() {
                //完成上拉加载更多
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        int page=tabLayout.getSelectedTabPosition();
                        pullUpRefresh(page);
                        rv_give_jobs.notifyData();
                    }
                },200);

            }
        });

        srl_give_jobs.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //完成下拉刷新全部
                srl_give_jobs.setRefreshing(false);
            }
        });
    }

    /*
    下拉刷新,输入表示进行中或者已失效
     */
    private void pullDownRefresh(int pageNum){

    }

    /*
    上拉刷新，输入表示同上
     */
    private void pullUpRefresh(int pageNum){
        for(int i=1;i<=10;i++)
        if(pageNum==DataType.VALID_JOB_LIST) {
            validJobList.add("刷新 pager" + pageNum + " 第" + i + "个item");
        }
        else if(pageNum==DataType.UNVALID_JOB_LIST){
            UnvalidJooList.add("刷新 pager" + pageNum+ " 第" + i + "个item");
        }
    }

}
