package com.tac.iparttimejob.UI.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tac.iparttimejob.Class.RecuitResult;
import com.tac.iparttimejob.NetWork.Connect.HttpCallBackListener;
import com.tac.iparttimejob.NetWork.Query.QueryInformation;
import com.tac.iparttimejob.R;
import com.tac.iparttimejob.UI.GiveAndReceiveJobs.JobContentForReceiver;
import com.tac.iparttimejob.UI.Utils.RefreshRecyclerView;
import com.tac.iparttimejob.Class.Object;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by AiProgram on 2016/11/12.
 */

public class UncheckingJobList extends Fragment{

    private RefreshRecyclerView rv_unchecking_job_list;
    private SwipeRefreshLayout srl_unchecking_job_list;

    private Handler handler=new Handler();

    private UncheckingJobAdapter jobAdapter;

    private List<RecuitResult.Recuit> jobList=new ArrayList<>();

    //防止多次初始化
    boolean inited=false;

    //一次获得的数量以及显示列表指针
    int page=1;
    int rows=20;
    int pointer=0;



    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_unchecking_job_list,container,false);
        return view;
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

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
        View fragmentView=getView();
        rv_unchecking_job_list=(RefreshRecyclerView) fragmentView.findViewById(R.id.rv_unchecking_job_list);
        srl_unchecking_job_list=(SwipeRefreshLayout) fragmentView.findViewById(R.id.srl_unchecking_job_list);
    }

    //初始化文字显示等
    private void initViews(){
        jobAdapter =new UncheckingJobAdapter(jobList);
        //设置RecycleView
        rv_unchecking_job_list.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rv_unchecking_job_list.setItemAnimator(new DefaultItemAnimator());
        rv_unchecking_job_list.setAdapter(jobAdapter);
        //设置RecyclerVi可以上拉刷新
        rv_unchecking_job_list.setLoadMoreEnable(true);
    }

    //初始化各事件监听器
    private void initListener(){

        //列表点击事件
        jobAdapter.setOnItemClickListener(new UncheckingJobAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //点击跳转
                jumpToJobContent(position);
            }
        });

        rv_unchecking_job_list.setOnLoadMoreListener(new RefreshRecyclerView.OnLoadMoreListener() {
            @Override
            public void loadMoreListener() {
                //完成上拉加载更多
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullUpRefresh();
                        rv_unchecking_job_list.notifyData();
                    }
                },0);

            }
        });

        srl_unchecking_job_list.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //完成下拉刷新全部
                pullDownRefresh();
                rv_unchecking_job_list.notifyData();
                srl_unchecking_job_list.setRefreshing(false);
            }
        });
    }

    //首次进入页面时初始化数据
    private void initData(){
        Object.RecuitObjectlistForManager=new ArrayList<>();

        srl_unchecking_job_list.setRefreshing(true);

            //清空再全部刷新
            jobList.clear();

            //利用下拉刷新接口刷新
            pullDownRefresh();

        srl_unchecking_job_list.setRefreshing(false);
    }

    //下拉刷新
    private void pullDownRefresh(){

        Map<String,String> getList=new LinkedHashMap<>();
        page=1;
        getList.put("page",(page)+"");
        getList.put("rows",(rows)+"");
        getList.put("rstatus","0");
<<<<<<< HEAD

=======
>>>>>>> 3771caa18094e30515592f144f9d9f0885905b8f
        //分未报名和已报名列表
            QueryInformation.getRecruitListForManager(getList, new HttpCallBackListener() {
                @Override
                public void onFinish(final String result) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(), "下拉刷新成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                    cloneJobList();
                    rv_unchecking_job_list.notifyData();
                    page++;
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

    //上拉加载更多
    private void pullUpRefresh(){
        Map<String,String> getList=new LinkedHashMap<>();
        getList.put("page",(page)+"");
        getList.put("rows",(rows)+"");
        getList.put("rstatus","0");
<<<<<<< HEAD

=======
>>>>>>> 3771caa18094e30515592f144f9d9f0885905b8f

        //获得新的上拉更多的数据前禁止上拉更多
        rv_unchecking_job_list.setLoadMoreEnable(false);
        QueryInformation.getRecruitListForManager(getList, new HttpCallBackListener() {
                @Override
                public void onFinish(final String result) {
                    rv_unchecking_job_list.setLoadMoreEnable(true);

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Toast.makeText(getActivity(), "上拉更多成功", Toast.LENGTH_SHORT).show();
                        }
                    });

                    addJobList();
                    rv_unchecking_job_list.notifyData();
                    page++;
                }

                @Override
                public void onError(String error) {
                    rv_unchecking_job_list.setLoadMoreEnable(true);

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(), "上拉更多失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
    }

    //静态数组传入有问题，这里使用手动复制方法
    private void cloneJobList(){
        jobList.clear();
        for(int i=0;i<Object.RecuitObjectlistForManager.size();i++)
            jobList.add(Object.RecuitObjectlistForManager.get(i));
    }


    //上拉更多时使用
    private void addJobList(){
        //由于继续获得数据会把结果清空加入新数据，再次直接添加即可
        for(int i=0;i<Object.RecuitObjectlistForManager.size();i++){
            jobList.add(Object.RecuitObjectlistForManager.get(i));
        }
    }



    //跳转事件先加载，较为复杂,这里封装起来，等待编写
    private void jumpToJobContent(int position){
        Map<String,String> getJob=new LinkedHashMap<>();
        final Intent intent=new Intent(getActivity(),JobContentForAdmin.class);
        //等待编写
    }
}

