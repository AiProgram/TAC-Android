package com.tac.iparttimejob.UI.GiveAndReceiveJobs;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;


import com.tac.iparttimejob.Class.Enroll;
import com.tac.iparttimejob.NetWork.Connect.HttpCallBackListener;
import com.tac.iparttimejob.NetWork.Edit.EditInformation;
import com.tac.iparttimejob.NetWork.Query.QueryInformation;
import com.tac.iparttimejob.R;
import com.tac.iparttimejob.UI.MyManager.ShowResume;
import com.tac.iparttimejob.UI.Utils.RefreshRecyclerView;
import com.tac.iparttimejob.Class.Object;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by 守候。 on 2016/11/12.
 * 报名列表
 * 要求调用本activity时必须传入"recruitid"
 */

public class EnrollList extends AppCompatActivity {
    private RefreshRecyclerView rv_enroll_list;
    private SwipeRefreshLayout srl_enroll_list;

    private Handler handler=new Handler();

    private MyEnrollListAdapter enrollListAdapter;

    private List<Enroll> enrollList=new ArrayList<>();

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
        enrollListAdapter =new MyEnrollListAdapter(enrollList);
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
                Map<String,String> getResume=new LinkedHashMap<String, String>();
                getResume.put("username",enrollList.get(position).getTac_user().getName());
                QueryInformation.getUserInformationByName(getResume, new HttpCallBackListener() {
                    @Override
                    public void onFinish(String result) {
                        Intent intent=new Intent(EnrollList.this, ShowResume.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onError(String error) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(EnrollList.this,"获取简历失败",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });

        enrollListAdapter.setOnCheckBoxClickListener(new MyEnrollListAdapter.onCheckBoxClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //更新选择状态，修改checkBox状态,这里暂时不在本处改动
            }
        });

        enrollListAdapter.setOnCheckedChangeListener(new MyEnrollListAdapter.onCheckedChangeListener() {

            @Override
            public void onCheckedChange(final CompoundButton compoundButton, boolean b, int position) {
                Map<String,String> changeSelection=new LinkedHashMap<String, String>();
                changeSelection.put("recruitid",enrollList.get(position).getTac_recruit().getRecruitid());
                changeSelection.put("userid",enrollList.get(position).getTac_user().getUserid());
                if(b==true){
                    EditInformation.setChooseEnroll(changeSelection, new HttpCallBackListener() {
                        @Override
                        public void onFinish(String result) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    compoundButton.setChecked(true);
                                }
                            });

                        }

                        @Override
                        public void onError(String error) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    compoundButton.setChecked(false);
                                    Toast.makeText(EnrollList.this,"操作失败",Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    });
                }else{
                    EditInformation.setCancelChooseEnroll(changeSelection, new HttpCallBackListener() {
                        @Override
                        public void onFinish(String result) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    compoundButton.setChecked(false);
                                }
                            });
                        }

                        @Override
                        public void onError(String error) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    compoundButton.setChecked(true);
                                    Toast.makeText(EnrollList.this,"操作失败",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
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
        Object.enrollObjectList=new ArrayList<>();

        srl_enroll_list.setRefreshing(true);

        //清空再全部刷新
        enrollList.clear();

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

        //分未报名和已报名列表
        QueryInformation.getEnrollList(getList, new HttpCallBackListener() {
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
                        Toast.makeText(EnrollList.this, "下拉刷新失败", Toast.LENGTH_SHORT).show();
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
        QueryInformation.getEnrollList(getList, new HttpCallBackListener() {
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
                        Toast.makeText(EnrollList.this, "上拉更多失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    //静态数组传入有问题，这里使用手动复制方法
    private void cloneEnrollList(){
        enrollList.clear();
        for(int i=0;i<Object.enrollObjectList.size();i++) {
            enrollList.add(Object.enrollObjectList.get(i));

        }
    }


    //上拉更多时使用
    private void addEnrollList(){
        //由于继续获得数据会把结果清空加入新数据，再次直接添加即可
        for(int i=0;i<Object.enrollObjectList.size();i++){
            enrollList.add(Object.enrollObjectList.get(i));
        }
    }
}
