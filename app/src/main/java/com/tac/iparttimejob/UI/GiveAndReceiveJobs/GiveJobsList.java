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

import com.tac.iparttimejob.Class.Object;
import com.tac.iparttimejob.Class.RecuitResult;
import com.tac.iparttimejob.NetWork.Connect.HttpCallBackListener;
import com.tac.iparttimejob.NetWork.Query.QueryInformation;
import com.tac.iparttimejob.R;
import com.tac.iparttimejob.UI.Utils.DataType;
import com.tac.iparttimejob.UI.Utils.RefreshRecyclerView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.tac.iparttimejob.Class.Object.inRecuitObjectList;
import static com.tac.iparttimejob.Class.Object.notRecuitObjectList;
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

    List<RecuitResult.Recuit> validList=new ArrayList<>();
    List<RecuitResult.Recuit> unValidList=new ArrayList<>();


    //防止多次初始化
    boolean inited=false;

    //一次获得的数量以及显示列表指针
    int validPage=1;//失效和未失效的列表使用 不同计数器
    int unvalidPage=1;
    int rows=10;
    int pointer=0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_give_jobs_list,container,false);
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
        inRecuitObjectList=new ArrayList<>();
        notRecuitObjectList=new ArrayList<>();

        srl_give_jobs.setRefreshing(true);

        if(pageNum==DataType.VALID_JOB_LIST){
            //清空再全部刷新
            validList.clear();

            //利用下拉刷新接口刷新
            pullDownRefresh(pageNum);
        }
        else if(pageNum==DataType.UNVALID_JOB_LIST){
            unValidList.clear();
            pullDownRefresh(pageNum);
        }

        srl_give_jobs.setRefreshing(false);
    }


    //初始化各种View
    private void initView() {
        View fragmentView=getView();
        rv_give_jobs=(RefreshRecyclerView) fragmentView.findViewById(R.id.rv_give_jobs);
        srl_give_jobs=(SwipeRefreshLayout) fragmentView.findViewById(R.id.srl_give_jobs);
        tabLayout = (TabLayout)fragmentView.findViewById(R.id.tl_receive_jobs_top);
        imgbtn_add_jobs=(ImageButton) fragmentView.findViewById(R.id.imgbtn_add_jobs) ;

        validJobAdapter=new MyGiveJobAdapter(validList);
        UnvalidJobAdapter=new MyGiveJobAdapter(unValidList);

        //设置ToolBar
            tabLayout.addTab(tabLayout.newTab().setText("进行中"));
            tabLayout.addTab(tabLayout.newTab().setText("已失效"));

        //设置Toolbar主标题
        Toolbar mToolbar=(Toolbar)fragmentView.findViewById(R.id.toolbar_receive_jobs_top);
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

        //有效列表点击事件
        validJobAdapter.setOnItemClickListener(new MyGiveJobAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //点击跳转
                jumpToJobContent(DataType.VALID_JOB_LIST,position);
            }
        });
        //失效列表点击事件
        UnvalidJobAdapter.setOnItemClickListener(new MyGiveJobAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //点击跳转
                jumpToJobContent(DataType.UNVALID_JOB_LIST,position);
            }
        });
    }

    //跳转事件比较复杂，封装起来
    private void jumpToJobContent(int listType,int position){
        String recruitid;
        final Intent intent=new Intent(getActivity(),JobContentForGiver.class);
        switch (listType){
            case DataType.VALID_JOB_LIST:{
                recruitid=validList.get(position).getRecruitid();
            }break;
            case DataType.UNVALID_JOB_LIST:{
                recruitid=unValidList.get(position).getRecruitid();
            }break;
            default:recruitid=validList.get(position).getRecruitid();
        }

        Log.i("recruitid",recruitid);
        //先获得信息再跳转
        Map<String,String>getJob=new LinkedHashMap<>();
        getJob.put("recruitid",recruitid);
        QueryInformation.getRecruitInformation(getJob, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                //成功提示
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Toast.makeText(getActivity(),"获取详情成功",Toast.LENGTH_SHORT).show();
                    }
                });
                startActivity(intent);
            }

            @Override
            public void onError(String error) {
                //失败提示
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       // Toast.makeText(getActivity(),"获取详情失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
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

        Map<String,String> getList=new LinkedHashMap<>();
        //失效 和未失效不应使用统一page计数器
        switch (pageNum){
            case DataType.VALID_JOB_LIST:{
                validPage=1;
                getList.put("userid",Object.loginObject.getUserid());
                getList.put("page",(validPage)+"");
                getList.put("rows",(rows)+"");
            }break;
            case DataType.UNVALID_JOB_LIST:{
                unvalidPage=1;
                getList.put("userid",Object.loginObject.getUserid());
                getList.put("page",(unvalidPage)+"");
                getList.put("rows",(rows)+"");
            }break;
        }

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
                    cloneValidList();
                    rv_give_jobs.notifyData();
                    validPage++;
                }

                @Override
                public void onError(String error) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Toast.makeText(getActivity(), "下拉刷新失败", Toast.LENGTH_SHORT).show();
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
                    cloneUnValidList();
                    rv_give_jobs.notifyData();
                    unvalidPage++;
                }

                @Override
                public void onError(String error) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                           // Toast.makeText(getActivity(), "下拉刷新失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
        Log.i("validListSize", inRecuitObjectList.size()+"");
        Log.i("unValidListSize",notRecuitObjectList.size()+"");
    }

    /*
    上拉刷新，输入表示同上
     */
    private void pullUpRefresh(int pageNum){
        Map<String,String> getList=new LinkedHashMap<>();
        switch (pageNum){
            case DataType.VALID_JOB_LIST:{
                getList.put("userid",Object.loginObject.getUserid());
                getList.put("page",(validPage)+"");
                getList.put("rows",(rows)+"");
            }break;
            case DataType.UNVALID_JOB_LIST:{
                getList.put("userid",Object.loginObject.getUserid());
                getList.put("page",(unvalidPage)+"");
                getList.put("rows",(rows)+"");
            }break;
        }


        //获得新的上拉更多的数据前禁止上拉更多
        rv_give_jobs.setLoadMoreEnable(false);
        //分有效和无效刷新列表
        if(pageNum==DataType.VALID_JOB_LIST) {

            getInRecruitList(getList, new HttpCallBackListener() {
                @Override
                public void onFinish(final String result) {
                    rv_give_jobs.setLoadMoreEnable(true);

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Toast.makeText(getActivity(), "上拉更多成功", Toast.LENGTH_SHORT).show();
                        }
                    });

                    addValidList();
                    rv_give_jobs.notifyData();
                    validPage++;
                }

                @Override
                public void onError(String error) {
                    rv_give_jobs.setLoadMoreEnable(true);

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Toast.makeText(getActivity(), "上拉更多失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }else if (pageNum==DataType.UNVALID_JOB_LIST){
            QueryInformation.getNotRecruitList(getList, new HttpCallBackListener() {
                @Override
                public void onFinish(String result) {
                    rv_give_jobs.setLoadMoreEnable(true);

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Toast.makeText(getActivity(), "上拉更多成功", Toast.LENGTH_SHORT).show();
                        }
                    });

                    addUnvalidList();
                    rv_give_jobs.notifyData();
                    unvalidPage++;
                }

                @Override
                public void onError(String error) {
                    rv_give_jobs.setLoadMoreEnable(true);

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                           // Toast.makeText(getActivity(), "上拉更多失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }

        Log.i("validListSize",inRecuitObjectList.size()+"");
        Log.i("unValidListSize",notRecuitObjectList.size()+"");
    }

    public void cloneValidList(){
        validList.clear();
        for(int i=0;i<Object.inRecuitObjectList.size();i++)
            validList.add(Object.inRecuitObjectList.get(i));
    }

    public void cloneUnValidList(){
        unValidList.clear();
        for(int i=0;i<Object.notRecuitObjectList.size();i++)
            unValidList.add(Object.notRecuitObjectList.get(i));
    }

    public void addValidList(){
        //由于继续获得数据会把结果清空加入新数据，再次直接添加即可
        for(int i=0;i<inRecuitObjectList.size();i++){
            validList.add(inRecuitObjectList.get(i));
        }
    }

    public void addUnvalidList(){
            for(int i=0;i<notRecuitObjectList.size();i++){
                unValidList.add(notRecuitObjectList.get(i));
            }
    }

    public void test(){
//        for(int i=0;i<validJobList.size();i++){
//            Log.i("validJobs",validJobList.get(i).getRecruitid()+" "+validJobList.get(i).getStatus()+"");
//        }
//        for(int i=0;i<unvalidJooList.size();i++){
//            Log.i("unvalidJobs",unvalidJooList.get(i).getRecruitid()+" "+unvalidJooList.get(i).getStatus()+"");
//        }
    }

}
