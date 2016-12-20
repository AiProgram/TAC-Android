package com.tac.iparttimejob.UI.Admin;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.tac.iparttimejob.Class.AssessmentAtoO;
import com.tac.iparttimejob.Class.AssessmentOtoA;
import com.tac.iparttimejob.NetWork.Connect.HttpCallBackListener;
import com.tac.iparttimejob.NetWork.Edit.EditInformation;
import com.tac.iparttimejob.R;
import com.tac.iparttimejob.UI.Utils.DataType;
import com.tac.iparttimejob.UI.Utils.FormatedTimeGeter;
import com.tac.iparttimejob.UI.Utils.RefreshRecyclerView;
import com.tac.iparttimejob.Class.Object;
import com.tac.iparttimejob.NetWork.Query.QueryInformation;

import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by AiProgram on 2016/11/12.
 */

public class UncheckingCommentList extends Fragment {
    private RefreshRecyclerView rv_unchecking_comment_list;
    private TabLayout tl_check_comments;
    private SwipeRefreshLayout srl_unchecking_comment_list;
    private ViewSwitcher viewSwitcher;
    private RelativeLayout empty_view;

    private Handler handler=new Handler();

    private UncheckingCommentAdapter AtoOAdapter;
    private UncheckingCommentAdapter OtoAAdapter;

    private List<AssessmentAtoO> aToOList=new ArrayList<>();
    private List<AssessmentOtoA> oToAList=new ArrayList<>();

    int listType=DataType.COMMENT_A_TO_O;
    //防止多次初始化
    boolean inited=false;

    //一次获得的数量以及显示列表指针
    int aToOPage=1;
    int oToAPage=1;
    int rows=20;
    int pointer=0;



    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_unchecking_comment_list,container,false);
        return view;
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getViews();

        if(inited==false) {
            initData(DataType.COMMENT_A_TO_O);
            initData(DataType.COMMENT_O_TO_A);
            inited=true;
        }

        //注意注意,initViews必须在initListener之前调用，否则出错
        initViews();

        initListener();
    }

    //获取所有控件
    private void getViews(){
        View fragmentView=getView();
        rv_unchecking_comment_list=(RefreshRecyclerView) fragmentView.findViewById(R.id.rv_unchecking_comment_list);
        tl_check_comments=(TabLayout) fragmentView.findViewById(R.id.tl_check_comments);
        srl_unchecking_comment_list=(SwipeRefreshLayout) fragmentView.findViewById(R.id.srl_unchecking_comment_list);
        viewSwitcher=(ViewSwitcher) fragmentView.findViewById(R.id.vs_empty_list);
        empty_view=(RelativeLayout) fragmentView.findViewById(R.id.empty_view);
    }

    //初始化文字显示等
    private void initViews(){
        AtoOAdapter =new UncheckingCommentAdapter(aToOList, DataType.COMMENT_A_TO_O);
        OtoAAdapter=new UncheckingCommentAdapter(oToAList,DataType.COMMENT_O_TO_A);

        tl_check_comments.addTab(tl_check_comments.newTab().setText("应聘者的评价"));
        tl_check_comments.addTab(tl_check_comments.newTab().setText("招聘者的评价"));

        //设置RecycleView
        rv_unchecking_comment_list.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rv_unchecking_comment_list.setItemAnimator(new DefaultItemAnimator());
        rv_unchecking_comment_list.setAdapter(AtoOAdapter);
        //设置RecyclerVi可以上拉刷新
        rv_unchecking_comment_list.setLoadMoreEnable(true);
    }

    //初始化各事件监听器
    private void initListener(){

        //上方TabLayout切换事件
        tl_check_comments.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case DataType.COMMENT_A_TO_O:{
                        rv_unchecking_comment_list.setAdapter(AtoOAdapter);
                        rv_unchecking_comment_list.notifyData();
                        listType=DataType.COMMENT_A_TO_O;
                    }break;
                    case DataType.COMMENT_O_TO_A:{
                        rv_unchecking_comment_list.setAdapter(OtoAAdapter);
                        rv_unchecking_comment_list.notifyData();
                        listType=DataType.COMMENT_O_TO_A;
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
        AtoOAdapter.setOnItemClickListener(new UncheckingCommentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //点击跳转
                jumpToJobContent(DataType.SIGNED_JOB_LIST,position);
            }
        });
        //失效列表点击事件
        OtoAAdapter.setOnItemClickListener(new UncheckingCommentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //点击跳转
                jumpToJobContent(DataType.UNSIGNED_JOB_LIST,position);
            }
        });

        rv_unchecking_comment_list.setOnLoadMoreListener(new RefreshRecyclerView.OnLoadMoreListener() {
            @Override
            public void loadMoreListener() {
                //完成上拉加载更多
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        int page=tl_check_comments.getSelectedTabPosition();
                        pullUpRefresh(page);
                        rv_unchecking_comment_list.notifyData();
                    }
                },0);

            }
        });

        srl_unchecking_comment_list.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //完成下拉刷新全部
                pullDownRefresh(tl_check_comments.getSelectedTabPosition());
                rv_unchecking_comment_list.notifyData();
                srl_unchecking_comment_list.setRefreshing(false);
            }
        });

        empty_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pullDownRefresh(tl_check_comments.getSelectedTabPosition());
            }
        });
    }

    //首次进入页面时初始化数据
    private void initData(int pageNum){
        Object.atooAssessmentObjectList=new ArrayList<>();
        Object.otoaAssessmentObjectList=new ArrayList<>();

        srl_unchecking_comment_list.setRefreshing(true);

        if(pageNum==DataType.COMMENT_A_TO_O){
            //清空再全部刷新
            aToOList.clear();

            //利用下拉刷新接口刷新
            pullDownRefresh(pageNum);
        }
        else if(pageNum==DataType.COMMENT_O_TO_A){
            oToAList.clear();
            pullDownRefresh(pageNum);
        }

        srl_unchecking_comment_list.setRefreshing(false);
    }

    //下拉刷新
    private void pullDownRefresh(int pageNum){

        Map<String,String> getList=new LinkedHashMap<>();

        switch (pageNum){
            case DataType.COMMENT_A_TO_O:{
                aToOPage=1;
                getList.put("page",(aToOPage)+"");
                getList.put("rows",(rows)+"");
            }break;
            case DataType.COMMENT_O_TO_A:{
                oToAPage=1;
                getList.put("page",(oToAPage)+"");
                getList.put("rows",(rows)+"");
            }break;
        }

        //分谁对谁的评价
        if(pageNum==DataType.COMMENT_A_TO_O) {
            QueryInformation.getAtooAssementForManager(getList, new HttpCallBackListener() {
                @Override
                public void onFinish(final String result) {
                    cloneAtoOList();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(), "评价审核列表刷新成功", Toast.LENGTH_SHORT).show();
                            rv_unchecking_comment_list.notifyData();
                            chooseView();
                        }
                    });
                    aToOPage++;
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
        }else if (pageNum==DataType.COMMENT_O_TO_A){
            QueryInformation.getOtoaAssementForManager(getList, new HttpCallBackListener() {
                @Override
                public void onFinish(String result) {
                    clonOtoAList();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(), "评价审核表刷新成功", Toast.LENGTH_SHORT).show();
                            rv_unchecking_comment_list.notifyData();
                            chooseView();
                        }
                    });
                    oToAPage++;
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
            case DataType.COMMENT_A_TO_O:{
                getList.put("page",(aToOPage)+"");
                getList.put("rows",(rows)+"");
            }break;
            case DataType.COMMENT_O_TO_A:{
                getList.put("page",(oToAPage)+"");
                getList.put("rows",(rows)+"");
            }break;
        }


        //获得新的上拉更多的数据前禁止上拉更多
        rv_unchecking_comment_list.setLoadMoreEnable(false);
        //分有效和无效刷新列表
        if(pageNum==DataType.COMMENT_A_TO_O) {

            QueryInformation.getAtooAssementByIDForManager(getList, new HttpCallBackListener() {
                @Override
                public void onFinish(final String result) {
                    rv_unchecking_comment_list.setLoadMoreEnable(true);
                    addAtoOList();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Toast.makeText(getActivity(), "上拉更多成功", Toast.LENGTH_SHORT).show();
                            rv_unchecking_comment_list.notifyData();
                        }
                    });
                    aToOPage++;
                }

                @Override
                public void onError(String error) {

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(), "上拉更多失败", Toast.LENGTH_SHORT).show();
                            rv_unchecking_comment_list.setLoadMoreEnable(true);
                        }
                    });
                }
            });
        }else if (pageNum==DataType.COMMENT_O_TO_A){
            QueryInformation.getOtoaAssementByIDForManager(getList, new HttpCallBackListener() {
                @Override
                public void onFinish(String result) {
                    rv_unchecking_comment_list.setLoadMoreEnable(true);
                    addOtoAList();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Toast.makeText(getActivity(), "上拉更多成功", Toast.LENGTH_SHORT).show();
                            rv_unchecking_comment_list.notifyData();
                        }
                    });
                    oToAPage++;
                }

                @Override
                public void onError(String error) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(), "上拉更多失败", Toast.LENGTH_SHORT).show();
                            rv_unchecking_comment_list.setLoadMoreEnable(true);
                        }
                    });
                }
            });
        }
    }

    //静态数组传入有问题，这里使用手动复制方法
    private void cloneAtoOList(){
        aToOList.clear();
        for(int i=0;i<Object.atooAssessmentObjectList.size();i++)
            aToOList.add(Object.atooAssessmentObjectList.get(i));
    }

    private void clonOtoAList(){
        oToAList.clear();
        for(int i=0;i<Object.otoaAssessmentObjectList.size();i++)
            oToAList.add(Object.otoaAssessmentObjectList.get(i));
    }

    //上拉更多时使用
    private void addAtoOList(){
        //由于继续获得数据会把结果清空加入新数据，再次直接添加即可
        for(int i=0;i<Object.atooAssessmentObjectList.size();i++){
            aToOList.add(Object.atooAssessmentObjectList.get(i));
        }
    }

    private void addOtoAList(){
        for(int i=0;i<Object.otoaAssessmentObjectList.size();i++){
            oToAList.add(Object.otoaAssessmentObjectList.get(i));
        }
    }

    //跳转事件先加载，较为复杂,这里封装起来，等待编写
    private void jumpToJobContent(int listType,int position){
        Map<String,String> getList=new LinkedHashMap<>();
        //应聘者招聘详情尚未编写完成，这里需要传入是否选择了该招聘的标志，因为查看时无法获得
        switch (listType){
            case DataType.COMMENT_A_TO_O:{
                showCheckDialog(position);
            }break;
            case DataType.COMMENT_O_TO_A:{
                showCheckDialog(position);
            }break;
        }
    }

    //展示审核的Dialog
    private void showCheckDialog(final int position){
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        //初始化公有参数
        final Map<String,String> changeStatus=new LinkedHashMap<>();
        changeStatus.put("commentid",aToOList.get(position).getCommentid()+"");
        changeStatus.put("checktime", FormatedTimeGeter.getFormatedDate());

        //设置来自应聘者的评价
        if(listType==DataType.COMMENT_A_TO_O){
            //builder.setTitle(aToOList.get(position).getApplicantname()+"评价"+aToOList.get(position).getOnwername());
            builder.setTitle(aToOList.get(position).getApplicantname()+"评价");
            builder.setMessage(aToOList.get(position).getAtooComment());

            builder.setPositiveButton("通过审核", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    final ProgressDialog progressDialog = ProgressDialog.show(getActivity(), "提示", "正在通过评价", false);
                    changeStatus.put("status",DataType.COMMENT_STATUS_UNBLOCKED+"");
                    EditInformation.setAtooAssementStatus(changeStatus, new HttpCallBackListener() {
                        @Override
                        public void onFinish(String result) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    progressDialog.dismiss();
                                    Toast.makeText(getActivity(),"操作成功",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                        @Override
                        public void onError(String error) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    progressDialog.dismiss();
                                    Toast.makeText(getActivity(),"操作失败",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
            });

            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });

            builder.setNeutralButton("无法通过", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    final ProgressDialog progressDialog = ProgressDialog.show(getActivity(), "提示", "正在屏蔽评价", false);
                    changeStatus.put("status",DataType.COMMENT_STATUS_BLOCKED+"");
                    EditInformation.setAtooAssementStatus(changeStatus, new HttpCallBackListener() {
                        @Override
                        public void onFinish(String result) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    progressDialog.dismiss();
                                    Toast.makeText(getActivity(),"操作成功",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                        @Override
                        public void onError(String error) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    progressDialog.dismiss();
                                    Toast.makeText(getActivity(),"操作失败",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
            });
        }else{
            //设置来自招聘者的评价
            //builder.setTitle(oToAList.get(position).getOnwername()+"评价"+oToAList.get(position).getApplicantname());
            builder.setTitle(oToAList.get(position).getOnwername()+"评价");
            builder.setMessage(oToAList.get(position).getOtoaComment());
            builder.setPositiveButton("通过审核", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    final ProgressDialog progressDialog = ProgressDialog.show(getActivity(), "提示", "正在通过评价", false);
                    changeStatus.put("status",DataType.COMMENT_STATUS_UNBLOCKED+"");
                    EditInformation.setOtoaAssementStatus(changeStatus, new HttpCallBackListener() {
                        @Override
                        public void onFinish(String result) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    progressDialog.dismiss();
                                    Toast.makeText(getActivity(),"操作成功",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                        @Override
                        public void onError(String error) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    progressDialog.dismiss();
                                    Toast.makeText(getActivity(),"操作失败",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
            });

            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });

            builder.setNeutralButton("无法通过", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    final ProgressDialog progressDialog = ProgressDialog.show(getActivity(), "提示", "正在屏蔽评价", false);
                    changeStatus.put("status",DataType.COMMENT_STATUS_BLOCKED+"");
                    EditInformation.setOtoaAssementStatus(changeStatus, new HttpCallBackListener() {
                        @Override
                        public void onFinish(String result) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    progressDialog.dismiss();
                                    Toast.makeText(getActivity(),"操作成功",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                        @Override
                        public void onError(String error) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    progressDialog.dismiss();
                                    Toast.makeText(getActivity(),"操作失败",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
            });
        }
        builder.show();
    }

    //选择显示空的view还是rv,当列表没有项时显示提示为空的VIew
    private void chooseView(){
        int selectedTab=tl_check_comments.getSelectedTabPosition();
        //判断数量应该对应判断
        if(selectedTab==DataType.COMMENT_A_TO_O){
            if (aToOList.size() > 0) {
                if (R.id.srl_unchecking_comment_list == viewSwitcher.getNextView().getId()) {
                    viewSwitcher.showNext();
                }
            } else if (R.id.empty_view == viewSwitcher.getNextView().getId()) {
                viewSwitcher.showNext();
            }
        }
        else {
            if (oToAList.size() > 0) {
                if (R.id.srl_unchecking_comment_list == viewSwitcher.getNextView().getId()) {
                    viewSwitcher.showNext();
                }
            } else if (R.id.empty_view == viewSwitcher.getNextView().getId()) {
                viewSwitcher.showNext();
            }
        }
    }
}
