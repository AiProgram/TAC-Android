package com.tac.iparttimejob.UI.MyManager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.tac.iparttimejob.Class.Application;

import com.tac.iparttimejob.Class.Object;
import com.tac.iparttimejob.R;
/**
 * Created by AiProgram on 2016/11/16.
 */

public class ShowResume extends AppCompatActivity {
    private TextView tv_username_resume;
    private TextView tv_name_resume;
    private TextView tv_phone_resume;
    private TextView tv_email_resume;
    private TextView tv_single_info_resume;
    private TextView tv_detail_resume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resume_for_others);

        getViews();

        initViews();

        initListener();
    }

    private void getViews(){
        tv_username_resume=(TextView) findViewById(R.id.tv_username_resume);
        tv_name_resume=(TextView) findViewById(R.id.tv_name_resume);
        tv_phone_resume=(TextView) findViewById(R.id.tv_phone_resume);
        tv_email_resume=(TextView) findViewById(R.id.tv_email_resume);
        tv_single_info_resume=(TextView) findViewById(R.id.tv_single_info_resume);
        tv_detail_resume=(TextView)  findViewById(R.id.tv_detail_resume);
    }

    private void initViews(){
        tv_username_resume.setText(Object.getResumeObject.getName());
        tv_name_resume.setText(Object.getResumeObject.getNickname());
        tv_phone_resume.setText(Object.getResumeObject.getPhone());
        tv_email_resume.setText(Object.getResumeObject.getEmail());
        tv_single_info_resume.setText(Object.getResumeObject.getSingleResume());
        tv_detail_resume.setText(Object.getResumeObject.getDetailResume());
    }

    private void initListener(){

    }
}
