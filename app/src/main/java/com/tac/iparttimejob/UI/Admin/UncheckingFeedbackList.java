package com.tac.iparttimejob.UI.Admin;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.tac.iparttimejob.Class.Advice;
import com.tac.iparttimejob.Class.Object;
import com.tac.iparttimejob.NetWork.Connect.HttpCallBackListener;
import com.tac.iparttimejob.NetWork.Query.QueryInformation;
import com.tac.iparttimejob.R;
import com.tac.iparttimejob.UI.Utils.RefreshRecyclerView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by AiProgram on 2016/11/12.
 */

public class UncheckingFeedbackList extends Fragment {


    private RefreshRecyclerView rv_unchecking_feedback_list;
    private SwipeRefreshLayout srl_unchecking_feedback_list;
    private ViewSwitcher viewSwitcher;
    private RelativeLayout empty_view;

    private Handler handler=new Handler();

    private UncheckingFeedbackAdapter feedbackAdapter;

    private List<Advice.suggesstion> adviceList=new ArrayList<>();

    //防止多次初始化
    boolean inited=false;

    //一次获得的数量以及显示列表指针
    int page=1;
    int rows=20;
    int pointer=0;



    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_unchecking_feedback_list,container,false);
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
        rv_unchecking_feedback_list=(RefreshRecyclerView) fragmentView.findViewById(R.id.rv_unchecking_feedback_list);
        srl_unchecking_feedback_list=(SwipeRefreshLayout) fragmentView.findViewById(R.id.srl_unchecking_feedback_list);
        viewSwitcher=(ViewSwitcher) fragmentView.findViewById(R.id.vs_empty_list);
        empty_view=(RelativeLayout) fragmentView.findViewById(R.id.empty_view);
    }

    //初始化文字显示等
    private void initViews(){
        feedbackAdapter =new UncheckingFeedbackAdapter(adviceList);
        //设置RecycleView
        rv_unchecking_feedback_list.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rv_unchecking_feedback_list.setItemAnimator(new DefaultItemAnimator());
        rv_unchecking_feedback_list.setAdapter(feedbackAdapter);
        //设置RecyclerVi可以上拉刷新
        rv_unchecking_feedback_list.setLoadMoreEnable(true);
    }

    //初始化各事件监听器
    private void initListener(){

        //列表点击事件
        feedbackAdapter.setOnItemClickListener(new UncheckingFeedbackAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                jumpToJobContent(position);
            }
        });

        rv_unchecking_feedback_list.setOnLoadMoreListener(new RefreshRecyclerView.OnLoadMoreListener() {
            @Override
            public void loadMoreListener() {
                //完成上拉加载更多
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullUpRefresh();
                        rv_unchecking_feedback_list.notifyData();
                    }
                },0);

            }
        });

        srl_unchecking_feedback_list.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //完成下拉刷新全部
                pullDownRefresh();
                rv_unchecking_feedback_list.notifyData();
                srl_unchecking_feedback_list.setRefreshing(false);
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
        Object.suggesstion=new ArrayList<>();

        srl_unchecking_feedback_list.setRefreshing(true);

        //清空再全部刷新
        adviceList.clear();

        //利用下拉刷新接口刷新
        pullDownRefresh();

        srl_unchecking_feedback_list.setRefreshing(false);
    }

    //下拉刷新
    private void pullDownRefresh(){

        Map<String,String> getAdvice=new LinkedHashMap<>();
        page=1;
        getAdvice.put("page",(page)+"");
        getAdvice.put("rows",(rows)+"");


        //分未报名和已报名列表
        QueryInformation.getAdvice(getAdvice, new HttpCallBackListener() {
            @Override
            public void onFinish(final String result) {
                cloneAdviceList();
                page++;
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "反馈列表刷新成功", Toast.LENGTH_SHORT).show();
                        rv_unchecking_feedback_list.notifyData();
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

        //获得新的上拉更多的数据前禁止上拉更多
        rv_unchecking_feedback_list.setLoadMoreEnable(false);
        QueryInformation.getAdvice(getList, new HttpCallBackListener() {
            @Override
            public void onFinish(final String result) {
                rv_unchecking_feedback_list.setLoadMoreEnable(true);
                addAdviceList();
                page++;
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Toast.makeText(getActivity(), "上拉更多成功", Toast.LENGTH_SHORT).show();
                        rv_unchecking_feedback_list.notifyData();
                    }
                });
            }

            @Override
            public void onError(String error) {
                rv_unchecking_feedback_list.setLoadMoreEnable(true);

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
    private void cloneAdviceList(){
        adviceList.clear();
        for(int i=0;i<Object.suggesstion.size();i++)
            adviceList.add(Object.suggesstion.get(i));
    }


    //上拉更多时使用
    private void addAdviceList(){
        //由于继续获得数据会把结果清空加入新数据，再次直接添加即可
        for(int i=0;i<Object.suggesstion.size();i++){
            adviceList.add(Object.suggesstion.get(i));
        }
    }



    //跳转事件先加载，较为复杂,这里封装起来，等待编写
    private void jumpToJobContent(int position){
        showDetailDialog(position);
    }

    //弹窗显示反馈详细信息
    private void showDetailDialog(int position){
        View dialogView=LayoutInflater.from(getActivity()).inflate(R.layout.dialog_feed_back_detail,null);
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setView(dialogView);
        TextView tv_username_feedback=(TextView) dialogView.findViewById(R.id.tv_username_comment);
        TextView tv_phone_feedback=(TextView) dialogView.findViewById(R.id.tv_point_comment);
        TextView tv_email_feedback=(TextView) dialogView.findViewById(R.id.tv_email_feedback);
        TextView tv_time_feedback=(TextView) dialogView.findViewById(R.id.tv_time_feedback);
        TextView tv_detail_feedback=(TextView) dialogView.findViewById(R.id.tv_detail_feedback);

        tv_username_feedback.setText(adviceList.get(position).getUsername());
        tv_phone_feedback.setText(adviceList.get(position).getPhone());
        //email暂时不获取
        tv_email_feedback.setText("用户未填写");
        tv_time_feedback.setText(adviceList.get(position).getTime());
        tv_detail_feedback.setText(adviceList.get(position).getAdvice());
        builder.setNegativeButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setTitle("详情");
        builder.show();
    }

    //选择显示空的view还是rv,当列表没有项时显示提示为空的VIew
    private void chooseView(){
        //判断数量应该对应判断
        if (adviceList.size() > 0) {
            if (R.id.srl_unchecking_feedback_list == viewSwitcher.getNextView().getId()) {
                viewSwitcher.showNext();
            }
        } else if (R.id.empty_view == viewSwitcher.getNextView().getId()) {
            viewSwitcher.showNext();
        }
    }
}
