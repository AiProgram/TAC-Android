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
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.tac.iparttimejob.Class.RecuitResult;
import com.tac.iparttimejob.NetWork.Connect.HttpCallBackListener;
import com.tac.iparttimejob.NetWork.Query.QueryInformation;
import com.tac.iparttimejob.R;
import com.tac.iparttimejob.UI.EventBusEvent.UpdateAdminJobListEvent;
import com.tac.iparttimejob.UI.GiveAndReceiveJobs.JobContentForReceiver;
import com.tac.iparttimejob.UI.Utils.DataType;
import com.tac.iparttimejob.UI.Utils.RefreshRecyclerView;
import com.tac.iparttimejob.Class.Object;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
    private ViewSwitcher viewSwitcher;
    private RelativeLayout empty_view;

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
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

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

        EventBus.getDefault().register(this);
    }

    //获取所有控件
    private void getViews(){
        View fragmentView=getView();
        rv_unchecking_job_list=(RefreshRecyclerView) fragmentView.findViewById(R.id.rv_unchecking_job_list);
        srl_unchecking_job_list=(SwipeRefreshLayout) fragmentView.findViewById(R.id.srl_unchecking_job_list);
        viewSwitcher=(ViewSwitcher) fragmentView.findViewById(R.id.vs_empty_list);
        empty_view=(RelativeLayout) fragmentView.findViewById(R.id.empty_view);
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

        empty_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pullDownRefresh();
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

        //分未报名和已报名列表
            QueryInformation.getRecruitListForManager(getList, new HttpCallBackListener() {
                @Override
                public void onFinish(final String result) {
                    cloneJobList();
                    page++;
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(), "招聘审核列表刷新成功", Toast.LENGTH_SHORT).show();
                            rv_unchecking_job_list.notifyData();
                            chooseView();
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

    //上拉加载更多
    private void pullUpRefresh(){
        Map<String,String> getList=new LinkedHashMap<>();
        getList.put("page",(page)+"");
        getList.put("rows",(rows)+"");
        getList.put("rstatus","0");


        //获得新的上拉更多的数据前禁止上拉更多
        rv_unchecking_job_list.setLoadMoreEnable(false);
        QueryInformation.getRecruitListForManager(getList, new HttpCallBackListener() {
                @Override
                public void onFinish(final String result) {
                    rv_unchecking_job_list.setLoadMoreEnable(true);
                    addJobList();
                    page++;
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Toast.makeText(getActivity(), "上拉更多成功", Toast.LENGTH_SHORT).show();
                            rv_unchecking_job_list.notifyData();
                        }
                    });
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
        getJob.put("recruitid",jobList.get(position).getRecruitid());
        final Intent intent=new Intent(getActivity(),JobContentForAdmin.class);
        QueryInformation.getRecruitInformation(getJob, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                startActivity(intent);
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    //选择显示空的view还是rv,当列表没有项时显示提示为空的VIew
    private void chooseView(){
        //判断数量应该对应判断
            if (jobList.size() > 0) {
                if (R.id.srl_unchecking_job_list == viewSwitcher.getNextView().getId()) {
                    viewSwitcher.showNext();
                }
            } else if (R.id.empty_view == viewSwitcher.getNextView().getId()) {
                viewSwitcher.showNext();
            }
        }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUpdateJobList(UpdateAdminJobListEvent event){
        pullDownRefresh();
    }

}

