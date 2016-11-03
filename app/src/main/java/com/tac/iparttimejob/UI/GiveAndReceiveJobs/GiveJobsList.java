package com.tac.iparttimejob.UI.GiveAndReceiveJobs;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.tac.iparttimejob.Class.LoginResult;
import com.tac.iparttimejob.Class.Object;
import com.tac.iparttimejob.Class.RecuitResult;
import com.tac.iparttimejob.NetWork.Connect.HttpCallBackListener;
import com.tac.iparttimejob.NetWork.Query.QueryInformation;
import com.tac.iparttimejob.R;
import com.tac.iparttimejob.UI.RegisterAndLogin.Login;
import com.tac.iparttimejob.UI.Utils.DataType;
import com.tac.iparttimejob.UI.Utils.RefreshRecyclerView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.tac.iparttimejob.NetWork.Query.QueryInformation.getInRecruitList;


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
    private ImageButton imgbtn_add_jobs;

    private Handler handler=new Handler();
    private MyGiveJobAdapter validJobAdapter;
    private MyGiveJobAdapter UnvalidJobAdapter;


    //分别储存进行中和失效的信息
    private List<RecuitResult.Recuit> validJobList;
    private List<RecuitResult.Recuit> unvalidJooList;

    //防止多次初始化
    boolean inited=false;

    //一次获得的数量以及显示列表指针
    int page=1;
    int rows=20;
    int pointer=0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_give_jobs_valid_list,container,false);
        return view;
    }

    @Override
    /*耗时初始化操作放在这里*/
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initRefreshListener();
        //获得传递的信息
//        loginResult=(LoginResult) getActivity().getIntent().getExtras().get("LoginResult");

        if(inited==false) {
            initData(DataType.VALID_JOB_LIST);
            initData(DataType.UNVALID_JOB_LIST);
            inited=true;
        }

    }


    //初始化数据,引入网络操作后修改
    private void initData(int pageNum) {
        Object.inRecuitObjectList=new ArrayList<>();
        Object.notRecuitObjectList=new ArrayList<>();
        validJobList=Object.inRecuitObjectList;
        unvalidJooList=Object.notRecuitObjectList;


        srl_give_jobs.setRefreshing(true);

        if(pageNum==DataType.VALID_JOB_LIST){
            //清空再全部刷新
            validJobList.clear();

            //利用下拉刷新接口刷新
            pullDownRefresh(pageNum);
        }
        else if(pageNum==DataType.UNVALID_JOB_LIST){
            unvalidJooList.clear();
            pullDownRefresh(pageNum);
        }

        srl_give_jobs.setRefreshing(false);
    }


    //初始化各种View
    private void initView() {
        View fragmentView=getView();
        rv_give_jobs=(RefreshRecyclerView) fragmentView.findViewById(R.id.rv_give_jobs);
        srl_give_jobs=(SwipeRefreshLayout) fragmentView.findViewById(R.id.srl_give_jobs);
        tabLayout = (TabLayout)fragmentView.findViewById(R.id.tabLayout_give_jobs_top);
        imgbtn_add_jobs=(ImageButton) fragmentView.findViewById(R.id.imgbtn_add_jobs) ;
        validJobAdapter=new MyGiveJobAdapter(validJobList);
        UnvalidJobAdapter=new MyGiveJobAdapter(unvalidJooList);

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

        //添加招聘信息的监听器
        imgbtn_add_jobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //专项填写招聘的页面
                Intent intent=new Intent(getActivity(),PostJobs.class);
                startActivity(intent);
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
                },0);

            }
        });

        srl_give_jobs.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //完成下拉刷新全部
                pullDownRefresh(tabLayout.getSelectedTabPosition());
                rv_give_jobs.notifyData();
                srl_give_jobs.setRefreshing(false);
            }
        });
    }

    /*
    下拉刷新,输入表示进行中或者已失效
     */
    private void pullDownRefresh(int pageNum){
        page=1;
        pointer=0;
        Map<String,String> getList=new LinkedHashMap<>();
        getList.put("userid",Object.loginObject.getUserid());
        getList.put("page",(page++)+"");
        getList.put("rows",(rows)+"");
        //分有效和无效刷新列表
        if(pageNum==DataType.VALID_JOB_LIST) {
            getInRecruitList(getList, new HttpCallBackListener() {
                @Override
                public void onFinish(final String result) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(), "下拉刷新成功", Toast.LENGTH_SHORT).show();
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
        }else if (pageNum==DataType.UNVALID_JOB_LIST){
            QueryInformation.getNotRecruitList(getList, new HttpCallBackListener() {
                @Override
                public void onFinish(String result) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(), "下拉刷新成功", Toast.LENGTH_SHORT).show();
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
        Log.i("validListSize",Object.inRecuitObjectList.size()+"");
        Log.i("unValidListSize",Object.notRecuitObjectList.size()+"");
    }

    /*
    上拉刷新，输入表示同上
     */
    private void pullUpRefresh(int pageNum){
        Map<String,String> getList=new LinkedHashMap<>();
        getList.put("userid",Object.loginObject.getUserid());
        getList.put("page",(page++)+"");
        getList.put("rows",(rows)+"");
        //分有效和无效刷新列表
        if(pageNum==DataType.VALID_JOB_LIST) {
            getInRecruitList(getList, new HttpCallBackListener() {
                @Override
                public void onFinish(final String result) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(), "下拉刷新成功", Toast.LENGTH_SHORT).show();
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
        }else if (pageNum==DataType.UNVALID_JOB_LIST){
            QueryInformation.getNotRecruitList(getList, new HttpCallBackListener() {
                @Override
                public void onFinish(String result) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(), "下拉刷新成功", Toast.LENGTH_SHORT).show();
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
        Log.i("validListSize",validJobList.size()+"");
        Log.i("unValidListSize",unvalidJooList.size()+"");
    }

}
