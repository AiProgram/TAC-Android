package com.tac.iparttimejob.UI.Admin;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tac.iparttimejob.Class.Object;
import com.tac.iparttimejob.NetWork.Connect.HttpCallBackListener;
import com.tac.iparttimejob.NetWork.Edit.EditInformation;
import com.tac.iparttimejob.R;
import com.tac.iparttimejob.UI.GiveAndReceiveJobs.JobContentForGiver;
import com.tac.iparttimejob.UI.Utils.DataType;
import com.tac.iparttimejob.UI.Utils.FormatedTimeGeter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by AiProgram on 2016/11/13.
 */

public class JobContentForAdmin extends AppCompatActivity{
    private TextView title_content;
    private TextView tv_author;
    private TextView tv_displaytime;
    private TextView  tv_numbers;
    private TextView tv_deadline;
    private TextView tv_days_left;
    private TextView tv_payment;
    private TextView tv_phone_number;
    private TextView tv_email;
    private TextView tv_workplace;
    private TextView tv_detail_resume;

    private Button btn_reject_job;
    private Button btn_accept_job;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_unchecking_job_content);

        getViews();

        initViews();

        initListener();
    }

    private void getViews(){
        title_content=(TextView) findViewById(R.id.title_content);
        tv_author=(TextView) findViewById(R.id.tv_author);
        tv_displaytime=(TextView) findViewById(R.id.tv_displaytime);
        tv_numbers=(TextView) findViewById(R.id.tv_numbers);
        tv_deadline=(TextView) findViewById(R.id.tv_deadline);
        tv_days_left=(TextView) findViewById(R.id.tv_days_left);
        tv_payment=(TextView) findViewById(R.id.tv_payment);
        tv_phone_number=(TextView)  findViewById(R.id.tv_phone_number);
        tv_email=(TextView) findViewById(R.id.tv_email);
        tv_workplace=(TextView) findViewById(R.id.tv_workplace);
        tv_detail_resume=(TextView) findViewById(R.id.tv_detail_resume);

        btn_accept_job=(Button) findViewById(R.id.btn_accept_job);
        btn_reject_job=(Button) findViewById(R.id.btn_reject_job);
    }

    private void initViews(){
        int dayDiff=0;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date now = format.parse(FormatedTimeGeter.getFormatedDate());
            Date to=format.parse(Object.recuitObject.getDealdine());
            dayDiff=FormatedTimeGeter.differentDays(now,to);
        }catch (Exception e){
            e.printStackTrace();
        }
        //薪酬以及剩余天数暂时无效
        title_content.setText(Object.recuitObject.getTitle());
        tv_author.setText(Object.recuitObject.getOwner());
        tv_displaytime.setText(Object.recuitObject.getDisplaytime());
        tv_numbers.setText(Object.recuitObject.getNeedpeopleNum()+"");
        tv_deadline.setText(Object.recuitObject.getDealdine());
        tv_phone_number.setText(Object.recuitObject.getPhone());
        tv_email.setText(Object.recuitObject.getTac_user().getEmail());
        tv_workplace.setText(Object.recuitObject.getWorkplace());
        tv_detail_resume.setText(Object.recuitObject.getWorkInfo());
        tv_days_left.setText("剩余"+dayDiff+"天");
    }

    private void initListener(){
        btn_reject_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //驳回
//                Map<String,String> rejectJob=new LinkedHashMap<String, String>();
//                rejectJob.put("recruitid",Object.recuitObject.getRecruitid());
//                rejectJob.put("status", DataType.JOB_STATUS_REJECTED+"");
//                EditInformation.setRecruitStatus(rejectJob, new HttpCallBackListener() {
//                    @Override
//                    public void onFinish(String result) {
//                        //驳回成功
//                        finish();
//                    }
//
//                    @Override
//                    public void onError(String error) {
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                //提示失败
//                                Toast.makeText(JobContentForAdmin.this,"驳回失败",Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }
//                });
                showReasonDialog();
            }
        });

        btn_accept_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog progressDialog = ProgressDialog.show(JobContentForAdmin.this, "提示", "正在通过招聘", false);
                //通过
                Map<String,String> acceptJob=new LinkedHashMap<String, String>();
                acceptJob.put("recruitid",Object.recuitObject.getRecruitid());
                acceptJob.put("status", DataType.JOB_STATUS_UNDERGONING+"");
                EditInformation.setRecruitStatus(acceptJob, new HttpCallBackListener() {
                    @Override
                    public void onFinish(String result) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                                finish();
                            }
                        });
                    }

                    @Override
                    public void onError(String error) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                                Toast.makeText(JobContentForAdmin.this,"操作失败",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });
    }

    //展示拒绝发布理由的输入框
    private void showReasonDialog(){
        //动态加载Dialog布局
        final View dialogView= LayoutInflater.from(this).inflate(R.layout.dialog_reason_input,null);
        EditText et_reject_reason=(EditText) dialogView.findViewById(R.id.et_reject_reason);
        et_reject_reason.setText("");
        et_reject_reason.setHint("输入驳回理由");
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setView(dialogView);
        builder.setTitle("驳回");
        builder.setPositiveButton("提交", new DialogInterface.OnClickListener() {
            //输入驳回理由并提交修改
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                final ProgressDialog progressDialog = ProgressDialog.show(JobContentForAdmin.this, "提示", "正在驳回招聘", false);
                //提交拒绝理由
                EditText et_reject_reason=(EditText) dialogView.findViewById(R.id.et_reject_reason);
                String reason=et_reject_reason.getText().toString();
                Map<String,String> reject=new LinkedHashMap<String, String>();
                reject.put("recruitid",Object.recuitObject.getRecruitid());
                reject.put("status",DataType.JOB_STATUS_REJECTED+"");
                reject.put("reason",reason);
                EditInformation.changeRecruitReason(reject, new HttpCallBackListener() {
                    @Override
                    public void onFinish(String result) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                                Toast.makeText(JobContentForAdmin.this,"修改成功",Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
                    }

                    @Override
                    public void onError(String error) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                                Toast.makeText(JobContentForAdmin.this,"操作失败",Toast.LENGTH_SHORT).show();
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
        builder.show();
    }
}
