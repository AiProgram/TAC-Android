package com.tac.iparttimejob.UI.Admin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tac.iparttimejob.Class.Object;
import com.tac.iparttimejob.NetWork.Connect.HttpCallBackListener;
import com.tac.iparttimejob.NetWork.Edit.EditInformation;
import com.tac.iparttimejob.R;
import com.tac.iparttimejob.UI.Utils.DataType;

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
        //薪酬以及剩余天数暂时无效
        title_content.setText(Object.recuitObject.getTitle());
        tv_author.setText(Object.recuitObject.getOwner());
        tv_displaytime.setText(Object.recuitObject.getDisplaytime());
        tv_numbers.setText(Object.recuitObject.getNeedpeopleNum()+"");
        tv_deadline.setText(Object.recuitObject.getDealdine());
        tv_phone_number.setText(Object.recuitObject.getPhone());
        tv_email.setText(Object.recuitObject.getEmail());
        tv_workplace.setText(Object.recuitObject.getWorkplace());
        tv_detail_resume.setText(Object.recuitObject.getWorkInfo());
    }

    private void initListener(){
        btn_reject_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //驳回
                Map<String,String> rejectJob=new LinkedHashMap<String, String>();
                rejectJob.put("recruitid",Object.recuitObject.getRecruitid());
                rejectJob.put("status", DataType.JOB_STATUS_REJECTED+"");
                EditInformation.setRecruitStatus(rejectJob, new HttpCallBackListener() {
                    @Override
                    public void onFinish(String result) {
                        //驳回成功
                        finish();
                    }

                    @Override
                    public void onError(String error) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //提示失败
                                Toast.makeText(JobContentForAdmin.this,"驳回失败",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });

        btn_accept_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //通过
                Map<String,String> acceptJob=new LinkedHashMap<String, String>();
                acceptJob.put("recruitid",Object.recuitObject.getRecruitid());
                acceptJob.put("status", DataType.JOB_STATUS_UNDERGONING+"");
                EditInformation.setRecruitStatus(acceptJob, new HttpCallBackListener() {
                    @Override
                    public void onFinish(String result) {
                        finish();
                    }

                    @Override
                    public void onError(String error) {
                        Toast.makeText(JobContentForAdmin.this,"操作失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
