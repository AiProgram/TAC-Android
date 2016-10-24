package com.tac.iparttimejob.UI.GiveJobs;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tac.iparttimejob.R;

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
public class GiveJobsValidList extends Fragment{

    private List<String> mData;

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
        initData(1);
        initView();
    }



    private void initData(int pager) {
        mData = new ArrayList();
        for (int i = 1; i < 50; i++) {
            mData.add("pager" + pager + " 第" + i + "个item");
        }
    }

    private void initView() {
        View fragmentView=getView();
        //设置ToolBar
        TabLayout tabLayout = (TabLayout)fragmentView.findViewById(R.id.tabLayout_give_jobs_top);
        for (int i = 1; i < 20; i++) {
            tabLayout.addTab(tabLayout.newTab().setText("TAB" + i));
        }

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
                initData(tab.getPosition() + 1);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //设置RecycleView
        RecyclerView recyclerView = (RecyclerView)fragmentView.findViewById(R.id.recyclerView_give_jobs_valid_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }


    private RecyclerView.Adapter mAdapter = new RecyclerView.Adapter<MyViewHolder>() {


        /*
        RecyclerView强制使用ViewHolder
         */
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            /*指定item布局文件*/
            return new MyViewHolder(getActivity().getLayoutInflater().inflate(R.layout.layout_valid_jobs_item, parent, false));
        }


        /*
        RecyclerView用来设置item内容的回调
         */
        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.tv.setText(mData.get(position));
        }

        /*
        返回item数量
         */
        @Override
        public int getItemCount() {
            return mData.size();
        }
    };


    /*
    自定义ViewHolder
     */
    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_valid_jobs);
        }
    }
}
