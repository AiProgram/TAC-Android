package com.tac.iparttimejob.UI.GiveAndReceiveJobs;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.Toast;

import com.tac.iparttimejob.Class.Application;
import com.tac.iparttimejob.Class.Object;
import com.tac.iparttimejob.Class.RecuitResult;
import com.tac.iparttimejob.NetWork.Connect.HttpCallBackListener;
import com.tac.iparttimejob.NetWork.Connect.HttpPost;
import com.tac.iparttimejob.NetWork.Query.QueryInformation;
import com.tac.iparttimejob.R;
import com.tac.iparttimejob.UI.Utils.DataType;
import com.tac.iparttimejob.UI.Utils.RefreshRecyclerView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.tac.iparttimejob.NetWork.Query.QueryInformation.getApplicantList;
import static com.tac.iparttimejob.NetWork.Query.QueryInformation.getApplicationList;
import static com.tac.iparttimejob.NetWork.Query.QueryInformation.getInRecruitList;

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
    int unsignedPage=1;
    int signedPage=1;
    int rows=10;
    int pointer=0;



    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_receive_jobs_list,container,false);
        return view;
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getViews();

        if(inited==false) {
            initData(DataType.SIGNED_JOB_LIST);
            initData(DataType.UNSIGNED_JOB_LIST);
            inited=true;
        }

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
         signedListAdapter =new MyReceiveJobsAdapter(signeList, DataType.SIGNED_JOB_LIST);
         unsignedListAdapter=new MyReceiveJobsAdapter(unsignedList,DataType.UNSIGNED_JOB_LIST);

        tl_receive_jobs_top.addTab(tl_receive_jobs_top.newTab().setText("未申请"));
        tl_receive_jobs_top.addTab(tl_receive_jobs_top.newTab().setText("已申请"));

        //设置RecycleView
        rv_receive_jobs.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rv_receive_jobs.setItemAnimator(new DefaultItemAnimator());
        rv_receive_jobs.setAdapter(unsignedListAdapter);
        //设置RecyclerVi可以上拉刷新
        rv_receive_jobs.setLoadMoreEnable(true);
        srl_receive_jobs.setColorSchemeColors(R.color.srlColor);
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


        //有效列表点击事件
        signedListAdapter.setOnItemClickListener(new MyReceiveJobsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //点击跳转
                jumpToJobContent(DataType.SIGNED_JOB_LIST,position);
            }
        });
        //失效列表点击事件
        unsignedListAdapter.setOnItemClickListener(new MyReceiveJobsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //点击跳转
                jumpToJobContent(DataType.UNSIGNED_JOB_LIST,position);
            }
        });

        rv_receive_jobs.setOnLoadMoreListener(new RefreshRecyclerView.OnLoadMoreListener() {
            @Override
            public void loadMoreListener() {
                //完成上拉加载更多
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        int page=tl_receive_jobs_top.getSelectedTabPosition();
                        pullUpRefresh(page);
                        rv_receive_jobs.notifyData();
                    }
                },0);

            }
        });

        srl_receive_jobs.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //完成下拉刷新全部
                pullDownRefresh(tl_receive_jobs_top.getSelectedTabPosition());
                rv_receive_jobs.notifyData();
                srl_receive_jobs.setRefreshing(false);
            }
        });
    }

    //首次进入页面时初始化数据
    private void initData(int pageNum){
        Object.chooseApplicationList=new ArrayList<>();
        Object.applicationObjectList=new ArrayList<>();

        srl_receive_jobs.setRefreshing(true);

        if(pageNum==DataType.SIGNED_JOB_LIST){
            //清空再全部刷新
            signeList.clear();

            //利用下拉刷新接口刷新
            pullDownRefresh(pageNum);
        }
        else if(pageNum==DataType.UNSIGNED_JOB_LIST){
            unsignedList.clear();
            pullDownRefresh(pageNum);
        }

        srl_receive_jobs.setRefreshing(false);
    }

    //下拉刷新
    private void pullDownRefresh(int pageNum){

        Map<String,String> getList=new LinkedHashMap<>();

        switch (pageNum){
            case DataType.SIGNED_JOB_LIST:{
                signedPage=1;
                getList.put("userid",Object.userObject.getUserid());
                getList.put("page",(signedPage)+"");
                getList.put("rows",(rows)+"");
        }break;
            case DataType.UNSIGNED_JOB_LIST:{
                unsignedPage=1;
                getList.put("page",(unsignedPage)+"");
                getList.put("rows",(rows)+"");
                getList.put("rstatus","1");
            }break;
        }

        //分未报名和已报名列表
        if(pageNum==DataType.UNSIGNED_JOB_LIST) {
            getApplicationList(getList, new HttpCallBackListener() {
                @Override
                public void onFinish(final String result) {
                    cloneUnsignedList();
                    unsignedPage++;
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Toast.makeText(getActivity(), "下拉刷新成功", Toast.LENGTH_SHORT).show();
                            rv_receive_jobs.notifyData();
                        }
                    });
                }

                @Override
                public void onError(String error) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(), "下拉刷新失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }else if (pageNum==DataType.SIGNED_JOB_LIST){
            getApplicantList(getList, new HttpCallBackListener() {
                @Override
                public void onFinish(String result) {
                    cloneSignedList();
                    signedPage++;
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // Toast.makeText(getActivity(), "下拉刷新成功", Toast.LENGTH_SHORT).show();
                            rv_receive_jobs.notifyData();
                        }
                    });
                }

                @Override
                public void onError(String error) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(), "下拉刷新失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
    }

    //上拉加载更多
    private void pullUpRefresh(int pageNum){
        Map<String,String> getList=new LinkedHashMap<>();
        switch (pageNum){
            case DataType.UNSIGNED_JOB_LIST:{
                getList.put("page",(unsignedPage)+"");
                getList.put("rows",(rows)+"");
            }break;
            case DataType.SIGNED_JOB_LIST:{
                getList.put("userid",Object.loginObject.getUserid());
                getList.put("page",(signedPage)+"");
                getList.put("rows",(rows)+"");
            }break;
        }


        //获得新的上拉更多的数据前禁止上拉更多
        rv_receive_jobs.setLoadMoreEnable(false);
        //分有效和无效刷新列表
        if(pageNum==DataType.UNSIGNED_JOB_LIST) {

            getApplicationList(getList, new HttpCallBackListener() {
                @Override
                public void onFinish(final String result) {
                    rv_receive_jobs.setLoadMoreEnable(true);
                    addUnsignedList();
                    unsignedPage++;
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Toast.makeText(getActivity(), "上拉更多成功", Toast.LENGTH_SHORT).show();
                            rv_receive_jobs.notifyData();
                        }
                    });
                }

                @Override
                public void onError(String error) {
                    rv_receive_jobs.setLoadMoreEnable(true);

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(), "上拉更多失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }else if (pageNum==DataType.SIGNED_JOB_LIST){
           getApplicantList(getList, new HttpCallBackListener() {
                @Override
                public void onFinish(String result) {
                    rv_receive_jobs.setLoadMoreEnable(true);
                    addSignedList();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Toast.makeText(getActivity(), "上拉更多成功", Toast.LENGTH_SHORT).show();
                            signedPage++;
                            rv_receive_jobs.notifyData();
                        }
                    });
                    Log.i("已申请页面数",signedPage+"");
                }

                @Override
                public void onError(String error) {
                    rv_receive_jobs.setLoadMoreEnable(true);

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(), "上拉更多失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
    }

    //静态数组传入有问题，这里使用手动复制方法
    private void cloneSignedList(){
        signeList.clear();
        for(int i=0;i<Object.applicationObjectList.size();i++) {
            signeList.add(Object.applicationObjectList.get(i));
            //测试完毕请删除
        }
    }

    private void cloneUnsignedList(){
        unsignedList.clear();
        for(int i=0;i<Object.chooseApplicationList.size();i++) {
            unsignedList.add(Object.chooseApplicationList.get(i));
        }
    }

    //上拉更多时使用
    private void addSignedList(){
        //由于继续获得数据会把结果清空加入新数据，再次直接添加即可
        for(int i=0;i<Object.applicationObjectList.size();i++){
          signeList.add(Object.applicationObjectList.get(i));
        }
        Object.applicationObjectList.clear();
    }

    private void addUnsignedList(){
        for(int i=0;i<Object.chooseApplicationList.size();i++){
            unsignedList.add(Object.chooseApplicationList.get(i));
        }
    }

    //跳转事件先加载，较为复杂,这里封装起来，等待编写
    private void jumpToJobContent(int listType,int position){
        Map<String,String> getList=new LinkedHashMap<>();
        //应聘者招聘详情尚未编写完成，这里需要传入是否选择了该招聘的标志，因为查看时无法获得
        final Intent intent=new Intent(getActivity(),JobContentForReceiver.class);
        switch (listType){
            case DataType.UNSIGNED_JOB_LIST:{
                intent.putExtra("type",DataType.UNSIGNED_JOB_LIST);
                getList.put("recruitid",unsignedList.get(position).getRecruitid());
            }break;
            case DataType.SIGNED_JOB_LIST:{
                intent.putExtra("type",DataType.SIGNED_JOB_LIST);
                //这里recruitid返回的是int
                getList.put("recruitid",signeList.get(position).getTac_recruit().getRecruitid()+"");
                intent.putExtra("applicantsid",signeList.get(position).getApplicantsid());
            }break;
        }
        //获取到信息以后再跳转
        QueryInformation.getRecruitInformation(getList, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(intent);
                        getActivity().overridePendingTransition(R.transition.zoom_in,R.transition.zoom_out);
                    }
                });

            }

            @Override
            public void onError(String error) {

            }
        });
    }
}
