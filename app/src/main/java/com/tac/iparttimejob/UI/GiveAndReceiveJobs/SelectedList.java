package com.tac.iparttimejob.UI.GiveAndReceiveJobs;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.tac.iparttimejob.Class.Enroll;
import com.tac.iparttimejob.NetWork.Connect.HttpCallBackListener;
import com.tac.iparttimejob.NetWork.Query.QueryInformation;
import com.tac.iparttimejob.R;
import com.tac.iparttimejob.UI.Utils.RefreshRecyclerView;
import com.tac.iparttimejob.Class.Object;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by AiProgram on 2016/11/16.
 */

public class SelectedList extends AppCompatActivity{
    private RefreshRecyclerView rv_enroll_list;
    private SwipeRefreshLayout srl_enroll_list;

    private Handler handler=new Handler();

    private MyEnrollListAdapter enrollListAdapter;

    private List<Enroll> selectedList=new ArrayList<>();

    private String recruitid="0";//默认初始值

    //防止多次初始化
    boolean inited=false;

    //一次获得的数量以及显示列表指针
    int page=1;
    int rows=20;
    int pointer=0;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //可以与选择人的界面共用
        setContentView(R.layout.enroll_list);

        recruitid=getIntent().getStringExtra("recruitid");

        getViews();

        if(inited==false) {
            initData();
            inited=true;
        }

        //注意注意,initViews必须在initListener之前调用，否则出错
        initViews();

        initListener();
    }

    //获取所有控件
    private void getViews(){
        rv_enroll_list=(RefreshRecyclerView) findViewById(R.id.rv_enroll_list);
        srl_enroll_list=(SwipeRefreshLayout) findViewById(R.id.srl_enroll_list);
    }

    //初始化文字显示等
    private void initViews(){
        enrollListAdapter =new MyEnrollListAdapter(selectedList);
        //设置RecycleView
        rv_enroll_list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv_enroll_list.setItemAnimator(new DefaultItemAnimator());
        rv_enroll_list.setAdapter(enrollListAdapter);
        //设置RecyclerVi可以上拉刷新
        rv_enroll_list.setLoadMoreEnable(true);
    }

    //初始化各事件监听器
    private void initListener(){

        //列表点击事件
        enrollListAdapter.setOnContentClickListener(new MyEnrollListAdapter.onContentClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //跳转至该人的简历
            }
        });

        rv_enroll_list.setOnLoadMoreListener(new RefreshRecyclerView.OnLoadMoreListener() {
            @Override
            public void loadMoreListener() {
                //完成上拉加载更多
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullUpRefresh();
                        rv_enroll_list.notifyData();
                    }
                },0);

            }
        });

        srl_enroll_list.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //完成下拉刷新全部
                pullDownRefresh();
                rv_enroll_list.notifyData();
                srl_enroll_list.setRefreshing(false);
            }
        });
    }

    //首次进入页面时初始化数据
    private void initData(){
        Object.enrollChooseObjectList=new ArrayList<>();

        srl_enroll_list.setRefreshing(true);

        //清空再全部刷新
        selectedList.clear();

        //利用下拉刷新接口刷新
        pullDownRefresh();

        srl_enroll_list.setRefreshing(false);
    }

    //下拉刷新
    private void pullDownRefresh(){

        Map<String,String> getList=new LinkedHashMap<>();
        page=1;
        getList.put("page",(page)+"");
        getList.put("rows",(rows)+"");
        getList.put("recruitid",recruitid);

        //分已报名列表
        QueryInformation.getChooseEnrollList(getList, new HttpCallBackListener() {
            @Override
            public void onFinish(final String result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Toast.makeText(getActivity(), "下拉刷新成功", Toast.LENGTH_SHORT).show();
                    }
                });
                cloneEnrollList();
                rv_enroll_list.notifyData();
                page++;
            }

            @Override
            public void onError(String error) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(SelectedList.this, "下拉刷新失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    //上拉加载更多
    private void pullUpRefresh(){
        Map<String,String> getList=new LinkedHashMap<>();
        getList.put("page",(page)+"");
        getList.put("rows",(rows)+"");
        getList.put("recruitid",recruitid);


        //获得新的上拉更多的数据前禁止上拉更多
        rv_enroll_list.setLoadMoreEnable(false);
        QueryInformation.getChooseEnrollList(getList, new HttpCallBackListener() {
            @Override
            public void onFinish(final String result) {
                rv_enroll_list.setLoadMoreEnable(true);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Toast.makeText(getActivity(), "上拉更多成功", Toast.LENGTH_SHORT).show();
                    }
                });

                addEnrollList();
                rv_enroll_list.notifyData();
                page++;
            }

            @Override
            public void onError(String error) {
                rv_enroll_list.setLoadMoreEnable(true);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(SelectedList.this, "上拉更多失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    //静态数组传入有问题，这里使用手动复制方法
    private void cloneEnrollList(){
        selectedList.clear();
        for(int i=0;i<Object.enrollChooseObjectList.size();i++)
            selectedList.add(Object.enrollChooseObjectList.get(i));
    }


    //上拉更多时使用
    private void addEnrollList(){
        //由于继续获得数据会把结果清空加入新数据，再次直接添加即可
        for(int i=0;i<Object.enrollChooseObjectList.size();i++){
            selectedList.add(Object.enrollChooseObjectList.get(i));
        }
    }
}