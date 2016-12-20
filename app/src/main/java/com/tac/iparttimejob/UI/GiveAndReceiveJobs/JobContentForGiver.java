package com.tac.iparttimejob.UI.GiveAndReceiveJobs;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tac.iparttimejob.Class.Object;
import com.tac.iparttimejob.NetWork.Connect.HttpCallBackListener;
import com.tac.iparttimejob.NetWork.Query.QueryInformation;
import com.tac.iparttimejob.R;
import com.tac.iparttimejob.UI.EventBusEvent.UpdateGiverJobListEvent;
import com.tac.iparttimejob.UI.MyManager.ShowResume;
import com.tac.iparttimejob.UI.Utils.DataType;
import com.tac.iparttimejob.UI.Utils.FormatedTimeGeter;
import com.tac.iparttimejob.UI.Utils.MyToolBarLayout;
import com.tac.iparttimejob.NetWork.Edit.EditInformation;
import com.tac.iparttimejob.UI.Utils.TextUitls;

import org.greenrobot.eventbus.EventBus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.tac.iparttimejob.Class.Object.resumeObject;

/**
 * Created by AiProgram on 2016/10/21.
 * 这是用于展示我要招聘的所有版本招聘详情的类
 */

public class JobContentForGiver extends AppCompatActivity{
    //共有控件
    private MyToolBarLayout myToolBarLayout;

    private TextView title_content;
    private TextView tv_author;
    private TextView tv_numbers;
    private TextView tv_deadline;
    private TextView tv_payment;
    private TextView tv_phone_number;
    private TextView tv_email;
    private TextView tv_workplace;
    private TextView tv_detail;
    private TextView tv_displaytime;

    //validJobContent独有
    private TextView tv_days_left;
    private Button btn_cancel_recruit;
    private Button btn_signed_list;

    //unvalidJobContent独有
    private TextView tv_unvalid_warning;
    private Button btn_choosed_list;

    private String recruitid;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.layout_valid_job_content);
        //根据状态设置布局
        switch (Object.recuitObject.getStatus()){
            case DataType.JOB_STATUS_UNDERGONING:{
                setContentView(R.layout.layout_valid_job_content);
            }break;
            case DataType.JOB_STATUS_CANCELED:{
                setContentView(R.layout.layout_unvalid_job_content);
            }break;
            case DataType.JOB_STATUS_CHECKING:{
                setContentView(R.layout.layout_valid_job_content);
            }break;
            case DataType.JOB_STATUS_FINSHED:{
                setContentView(R.layout.layout_unvalid_job_content);
            }break;
            case DataType.JOB_STATUS_REJECTED:{
                setContentView(R.layout.layout_unvalid_job_content);
            }break;
            default:setContentView(R.layout.layout_valid_job_content);
        }



        //初始化获得所有公共控件
        getViews();

        //初始化共有控件
        initCommonViews();

        //初始化独有控件
        initUniqueViews();

        //初始化监听器
        initListeners();

    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {

    }

    private void getViews(){
        myToolBarLayout=(MyToolBarLayout) findViewById(R.id.common_toolbar);

        title_content=(TextView) findViewById(R.id.title_content);
        tv_author=(TextView)  findViewById(R.id.tv_author);
        tv_numbers=(TextView) findViewById(R.id.tv_numbers);
        tv_deadline=(TextView) findViewById(R.id.tv_deadline);
        tv_payment=(TextView) findViewById(R.id.tv_payment);
        tv_phone_number=(TextView) findViewById(R.id.tv_phone_number);
        tv_email=(TextView) findViewById(R.id.tv_email);
        tv_workplace=(TextView) findViewById(R.id.tv_workplace);
        tv_detail=(TextView) findViewById(R.id.tv_detail_resume);
        tv_displaytime=(TextView) findViewById(R.id.tv_displaytime);

        recruitid=Object.recuitObject.getRecruitid();
    }

    //初始化公有控件
    private void initCommonViews(){
        myToolBarLayout.setTitle("招聘详情");

        title_content.setText(Object.recuitObject.getTitle());
        tv_author.setText(Object.recuitObject.getOwner());
        tv_deadline.setText(Object.recuitObject.getDealdine());
        tv_phone_number.setText(Object.recuitObject.getPhone());
        tv_workplace.setText(Object.recuitObject.getWorkplace());
        tv_detail.setText(Object.recuitObject.getWorkInfo());
        tv_numbers.setText(Object.recuitObject.getNeedpeopleNum()+"");
        tv_email.setText(Object.recuitObject.getTac_user().getEmail());
        tv_displaytime.setText(Object.recuitObject.getDisplaytime());

        //服务器暂时不提供的设置为消失
//        tv_payment.setVisibility(TextView.GONE);
        tv_payment.setText("暂时无效");

        //添加下滑性，提醒可以复制
        tv_email.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG ); //下划线
        tv_email.getPaint().setAntiAlias(true);//抗锯齿
        tv_phone_number.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG ); //下划线
        tv_phone_number.getPaint().setAntiAlias(true);//抗锯齿
        tv_author.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG ); //下划线
        tv_author.getPaint().setAntiAlias(true);//抗锯齿
    }

    private void initListeners(){
        tv_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextUitls.copy(tv_email.getText().toString(),JobContentForGiver.this);
                Toast.makeText(JobContentForGiver.this,"已经为您复制Email",Toast.LENGTH_SHORT).show();
            }
        });

        tv_phone_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextUitls.copy(tv_phone_number.getText().toString(),JobContentForGiver.this);
                Toast.makeText(JobContentForGiver.this,"已经为您复制电话号码",Toast.LENGTH_SHORT).show();
            }
        });

        tv_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextUitls.copy(tv_detail.getText().toString(),JobContentForGiver.this);
                Toast.makeText(JobContentForGiver.this,"已经为您复制招聘详情",Toast.LENGTH_SHORT).show();
            }
        });

        tv_author.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转至该人的简历
                final Map<String,String> getResume=new LinkedHashMap<String, String>();
                getResume.put("userid",Object.recuitObject.getTac_user().getUserid());
                QueryInformation.getPersonalResume(getResume, new HttpCallBackListener() {
                    @Override
                    public void onFinish(String result) {
                        Intent intent=new Intent(JobContentForGiver.this, ShowResume.class);
                        startActivity(intent);
                        //Log.i("简历",getResumeObject.getName());
                    }

                    @Override
                    public void onError(final String error) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(JobContentForGiver.this,"获取简历失败",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });
    }

    //初始化每个界面独有的控件
    private void initUniqueViews() {
        int dayDiff=0;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date now = format.parse(FormatedTimeGeter.getFormatedDate());
            Date to=format.parse(Object.recuitObject.getDealdine());
            dayDiff=FormatedTimeGeter.differentDays(now,to);
        }catch (Exception e){
            e.printStackTrace();
        }
        switch (Object.recuitObject.getStatus()){
            case DataType.JOB_STATUS_UNDERGONING:{
                //设置相隔天数
                TextView tv_days_left=(TextView) findViewById(R.id.tv_days_left);
                tv_days_left.setText("剩余"+dayDiff+"天");
                btn_cancel_recruit=(Button) findViewById(R.id.btn_cancel_recruit);
                btn_signed_list=(Button) findViewById(R.id.btn_signed_list);
                btn_cancel_recruit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final ProgressDialog progressDialog = ProgressDialog.show(JobContentForGiver.this, "提示", "正在取消招聘", false);
                        //取消招聘
                        Map<String,String> cancelRecruit=new LinkedHashMap<String, String>();
                        cancelRecruit.put("recruitid",recruitid);
                        cancelRecruit.put("status",DataType.JOB_STATUS_CANCELED+"");
                        EditInformation.setRecruitStatus(cancelRecruit, new HttpCallBackListener() {
                            @Override
                            public void onFinish(String result) {
                                //取消成功时返回主页面，并刷新
                                JobContentForGiver.this.finish();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        EventBus.getDefault().post(new UpdateGiverJobListEvent(DataType.VALID_JOB_LIST));
                                        EventBus.getDefault().post(new UpdateGiverJobListEvent(DataType.UNVALID_JOB_LIST));
                                       progressDialog.dismiss();
                                    }
                                });
                            }

                            @Override
                            public void onError(String error) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        progressDialog.dismiss();
                                        Toast.makeText(JobContentForGiver.this,"取消应聘失败",Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
                    }
                });
                btn_signed_list.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //报名名单
                        Intent intent=new Intent(JobContentForGiver.this,EnrollList.class);
                        intent.putExtra("recruitid",recruitid);
                        startActivity(intent);
                    }
                });
            }break;
            case DataType.JOB_STATUS_CANCELED:{
                btn_choosed_list=(Button)  findViewById(R.id.btn_choosed_list);
                btn_choosed_list.setVisibility(View.GONE);
            }break;
            case DataType.JOB_STATUS_CHECKING:{
                TextView tv_days_left=(TextView) findViewById(R.id.tv_days_left);
                tv_days_left.setText("剩余"+dayDiff+"天");
                btn_cancel_recruit=(Button) findViewById(R.id.btn_cancel_recruit);
                btn_signed_list=(Button) findViewById(R.id.btn_signed_list);
                btn_signed_list.setEnabled(false);
                btn_cancel_recruit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final ProgressDialog progressDialog = ProgressDialog.show(JobContentForGiver.this, "提示", "正在取消招聘", false);
                        //取消招聘
                        Map<String,String> cancelRecruit=new LinkedHashMap<String, String>();
                        cancelRecruit.put("recruitid",recruitid);
                        cancelRecruit.put("status",DataType.JOB_STATUS_CANCELED+"");
                        EditInformation.setRecruitStatus(cancelRecruit, new HttpCallBackListener() {
                            @Override
                            public void onFinish(String result) {
                                //取消成功时返回主页面，并刷新
                                JobContentForGiver.this.finish();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        EventBus.getDefault().post(new UpdateGiverJobListEvent(DataType.VALID_JOB_LIST));
                                        EventBus.getDefault().post(new UpdateGiverJobListEvent(DataType.UNVALID_JOB_LIST));
                                        progressDialog.dismiss();
                                    }
                                });
                            }

                            @Override
                            public void onError(String error) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        progressDialog.dismiss();
                                        Toast.makeText(JobContentForGiver.this,"取消应聘失败",Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
                    }
                });
            }break;
            case DataType.JOB_STATUS_FINSHED:{
                btn_choosed_list=(Button) findViewById(R.id.btn_choosed_list);
                btn_choosed_list.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //选中的报名列表
                        Intent intent=new Intent(JobContentForGiver.this,SelectedList.class);
                        intent.putExtra("recruitid",recruitid);
                        startActivity(intent);
                    }
                });
            }break;
            case DataType.JOB_STATUS_REJECTED:{
                btn_choosed_list=(Button) findViewById(R.id.btn_choosed_list);
                //选中列表这时变成查看被拒绝理由的按钮
                btn_choosed_list.setText("查看驳回理由");
                btn_choosed_list.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //查看被拒绝发布理由
                        showReasonDialog();
                    }
                });
            }break;
        }
    }

    //查看拒绝理由的弹窗
    private void showReasonDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("拒绝理由");
        builder.setMessage(Object.recuitObject.getReason());
       // Log.i("拒绝理由",Object.recuitObject.getReason());
        builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.show();
    }

}
