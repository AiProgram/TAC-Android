package com.tac.iparttimejob.UI.GiveAndReceiveJobs;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tac.iparttimejob.Class.AssessmentAtoO;
import com.tac.iparttimejob.Class.AssessmentOtoA;
import com.tac.iparttimejob.Class.Object;
import com.tac.iparttimejob.NetWork.Connect.HttpCallBackListener;
import com.tac.iparttimejob.NetWork.Edit.EditInformation;
import com.tac.iparttimejob.R;
import com.tac.iparttimejob.UI.Utils.DataType;
import com.tac.iparttimejob.UI.Utils.RefreshRecyclerView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.tac.iparttimejob.NetWork.Query.QueryInformation;

import static com.tac.iparttimejob.Class.Object.userObject;

/**
 * Edit by AiProgram on 2016/11/18.
 * 评价，必须传入int型ype，可取DataType.COMMENT_A_TO_O或者DataType.COMMENT_O_TO_A
 */
public class AssessList extends AppCompatActivity {
    private RefreshRecyclerView rv_comments_from_recruitor;
    private SwipeRefreshLayout srl_comment_list;
    private TextView title_comment_list;

    private Handler handler=new Handler();

    private AssessAdapter assessAdapter;
    private AssessAdapter oToAAdapter;

    private List<AssessmentAtoO> aToOList=new ArrayList<>();
    private List<AssessmentOtoA> oToAList=new ArrayList<>();

    private int type=DataType.COMMENT_A_TO_O;

    //防止多次初始化
    boolean inited=false;

    //一次获得的数量以及显示列表指针
    int atooPage=1;
    int otoaPage=1;
    int rows=20;
    int pointer=0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_assessment_list);

        Bundle bundle=new Bundle();
        bundle=getIntent().getExtras();
        type=bundle.getInt("type");
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
        rv_comments_from_recruitor=(RefreshRecyclerView) findViewById(R.id.rv_comments_list);
        srl_comment_list=(SwipeRefreshLayout) findViewById(R.id.srl_comment_list);
        title_comment_list=(TextView) findViewById(R.id.title_comment_list);
    }

    //初始化文字显示等
    private void initViews(){
        if(DataType.COMMENT_A_TO_O==type) {
            assessAdapter = new AssessAdapter(type, aToOList);
            title_comment_list.setText("应聘者的评价");
        }else {
            assessAdapter = new AssessAdapter(type, oToAList);
            title_comment_list.setText("招聘者的评价");
        }
        //设置RecycleView
        rv_comments_from_recruitor.setLayoutManager(new LinearLayoutManager(AssessList.this, LinearLayoutManager.VERTICAL, false));
        rv_comments_from_recruitor.setItemAnimator(new DefaultItemAnimator());
        rv_comments_from_recruitor.setAdapter(assessAdapter);
        //设置RecyclerVi可以上拉刷新
        rv_comments_from_recruitor.setLoadMoreEnable(true);

    }

    //初始化各事件监听器
    private void initListener(){

        //列表点击事件
        assessAdapter.setOnContentClickListener(new AssessAdapter.OnContentClickListener() {
            @Override
            public void onContentClick(View view, int position) {
                //跳转至个人简历
            }
        });

        //举报评价事件
        assessAdapter.setOnSueClickListener(new AssessAdapter.OnSueClickListener() {
            @Override
            public void onSueClick(View view, int position) {
                Map<String,String> sue=new LinkedHashMap<String, String>();
                //举报改评价,来自招聘者
                switch (type){
                    case DataType.COMMENT_A_TO_O:{
                    sue.put("commentid",aToOList.get(position).getCommentid()+"");
                        EditInformation.setAtooReport(sue, new HttpCallBackListener() {
                            @Override
                            public void onFinish(String result) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(AssessList.this,"举报成功，感谢您的反馈",Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }

                            @Override
                            public void onError(String error) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(AssessList.this,"举报失败",Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
                    };break;
                    case DataType.COMMENT_O_TO_A:{
                        sue.put("commentid",oToAList.get(position).getCommentid()+"");
                        EditInformation.setOtoaReport(sue, new HttpCallBackListener() {
                            @Override
                            public void onFinish(String result) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(AssessList.this,"举报成功，感谢您的反馈",Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }

                            @Override
                            public void onError(String error) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(AssessList.this,"举报失败",Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
                    };break;
                }
            }
        });

        rv_comments_from_recruitor.setOnLoadMoreListener(new RefreshRecyclerView.OnLoadMoreListener() {
            @Override
            public void loadMoreListener() {
                //完成上拉加载更多
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullUpRefresh(type);
                        rv_comments_from_recruitor.notifyData();
                    }
                },0);

            }
        });

        srl_comment_list.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //完成下拉刷新全部
                pullDownRefresh(type);
                rv_comments_from_recruitor.notifyData();
                srl_comment_list.setRefreshing(false);
            }
        });
    }

    //首次进入页面时初始化数据
    private void initData(){
        Object.atooAssessmentObjectList=new ArrayList<>();
        Object.otoaAssessmentObjectList=new ArrayList<>();

        srl_comment_list.setRefreshing(true);

        //清空再全部刷新
        aToOList.clear();
        oToAList.clear();

        //利用下拉刷新接口刷新
        pullDownRefresh(type);

        srl_comment_list.setRefreshing(false);
    }

    //下拉刷新
    private void pullDownRefresh(int type){

        Map<String,String> getList=new LinkedHashMap<>();
        switch (type){
            case DataType.COMMENT_A_TO_O:{
                atooPage=1;
                getList.put("ownerid",userObject.getUserid());
                getList.put("page",(atooPage)+"");
                getList.put("rows",(rows)+"");

                //分atoo和otoa列表,接口编写反了
                QueryInformation.getAtooComment(getList, new HttpCallBackListener() {
                    @Override
                    public void onFinish(final String result) {
                        cloneAtoOList();

                        atooPage++;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //Toast.makeText(AssessList.this, "下拉刷新成功", Toast.LENGTH_SHORT).show();
                                rv_comments_from_recruitor.notifyData();
                            }
                        });
                    }

                    @Override
                    public void onError(final String error) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(AssessList.this, "下拉刷新失败"+error, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }break;
            case DataType.COMMENT_O_TO_A:{
                otoaPage=1;
                getList.put("page",(otoaPage)+"");
                getList.put("rows",(rows)+"");
                getList.put("applicantid",userObject.getUserid());
                //分atoo和otoa列表
                QueryInformation.getOtoaComment(getList, new HttpCallBackListener() {
                    @Override
                    public void onFinish(final String result) {
                        cloneAtoOList();
                        otoaPage++;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //Toast.makeText(AssessList.this, "下拉刷新成功", Toast.LENGTH_SHORT).show();
                                rv_comments_from_recruitor.notifyData();
                            }
                        });
                    }

                    @Override
                    public void onError(final String error) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(AssessList.this, "下拉刷新失败"+error, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }break;
        }

    }

    //上拉加载更多
    private void pullUpRefresh(int type){
        Map<String,String> getList=new LinkedHashMap<>();
        switch (type){
           case  DataType.COMMENT_A_TO_O:{
                getList.put("page",(atooPage)+"");
                getList.put("rows",(rows)+"");
                getList.put("ownerid",userObject.getUserid());
                //获得新的上拉更多的数据前禁止上拉更多
                rv_comments_from_recruitor.setLoadMoreEnable(false);
                QueryInformation.getAtooComment(getList, new HttpCallBackListener() {
                    @Override
                    public void onFinish(final String result) {
                        rv_comments_from_recruitor.setLoadMoreEnable(true);
                        addAtoOList();
                        atooPage++;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //Toast.makeText(getActivity(), "上拉更多成功", Toast.LENGTH_SHORT).show();
                                rv_comments_from_recruitor.notifyData();
                            }
                        });
                    }

                    @Override
                    public void onError(String error) {
                        rv_comments_from_recruitor.setLoadMoreEnable(true);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(AssessList.this, "上拉更多失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }break;
           case  DataType.COMMENT_O_TO_A:{
                getList.put("page",(otoaPage)+"");
                getList.put("rows",(rows)+"");
                getList.put("applicantid",userObject.getUserid());
                //获得新的上拉更多的数据前禁止上拉更多
                rv_comments_from_recruitor.setLoadMoreEnable(false);
                QueryInformation.getOtoaComment(getList, new HttpCallBackListener() {
                    @Override
                    public void onFinish(final String result) {
                        rv_comments_from_recruitor.setLoadMoreEnable(true);
                        addOtoAList();
                        otoaPage++;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //Toast.makeText(getActivity(), "上拉更多成功", Toast.LENGTH_SHORT).show();
                                rv_comments_from_recruitor.notifyData();
                            }
                        });
                    }

                    @Override
                    public void onError(String error) {
                        rv_comments_from_recruitor.setLoadMoreEnable(true);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(AssessList.this, "上拉更多失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }break;
        }
    }

    //静态数组传入有问题，这里使用手动复制方法
    private void cloneAtoOList(){
        aToOList.clear();
        for(int i=0;i<Object.atooCommentObjectList.size();i++)
            aToOList.add(Object.atooCommentObjectList.get(i));
    }
    private void cloneOtoAList(){
        oToAList.clear();
        for(int i=0;i<Object.otoaCommentObjectList.size();i++)
            oToAList.add(Object.otoaCommentObjectList.get(i));
    }

    //上拉更多时使用
    private void addAtoOList(){
        //由于继续获得数据会把结果清空加入新数据，再次直接添加即可
        for(int i=0;i<Object.atooCommentObjectList.size();i++){
            aToOList.add(Object.atooCommentObjectList.get(i));
        }
    }

    private void addOtoAList(){
        //由于继续获得数据会把结果清空加入新数据，再次直接添加即可
        for(int i=0;i<Object.otoaCommentObjectList.size();i++){
            oToAList.add(Object.otoaCommentObjectList.get(i));
        }
    }

    //跳转事件先加载，较为复杂,这里封装起来，等待编写
    private void jumpToJobContent(int position) {
    }
}
