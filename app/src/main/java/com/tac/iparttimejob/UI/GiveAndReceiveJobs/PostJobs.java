package com.tac.iparttimejob.UI.GiveAndReceiveJobs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tac.iparttimejob.R;

/**
 * Created by AiProgram on 2016/10/21.
 */

public class PostJobs extends AppCompatActivity{
    private EditText et_input_username;
    private EditText et_input_title;
    private EditText et_input_workplace;
    private EditText et_input_deadline;
    private EditText et_input_phone;
    private EditText et_input_detail;
    private EditText et_input_post_time;
    private Button btn_post_job;
    private Button btn_cancel_post;

    private AppCompatActivity activity=this;

    private String username;
    private String title;
    private String workplace;
    private String deadline;
    private String phone;
    private String detail;
    private String displaytime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_jobs);

        //初始化各个控件
        getViews();
        //初始化监听器
        initListener();
    }

    public void getViews(){
        et_input_username=(EditText) findViewById(R.id.et_input_username);
        et_input_title=(EditText)  findViewById(R.id.et_input_title);
        et_input_workplace=(EditText) findViewById(R.id.et_input_workplace);
        et_input_deadline=(EditText) findViewById(R.id.et_input_deadline);
        et_input_phone=(EditText)  findViewById(R.id.et_input_phone);
        et_input_detail=(EditText) findViewById(R.id.et_input_detail);
        et_input_post_time=(EditText) findViewById(R.id.et_input_post_time);
        btn_post_job=(Button) findViewById(R.id.btn_post_job);
        btn_cancel_post=(Button) findViewById(R.id.btn_cancel_post);
    }
    public void initListener(){
        btn_post_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //发布事件
                getInput();
                //检查输入格式，预留
                if(checkInput()){
                    postJobs();
                }else{
                    //输入报错
                }
            }
        });

        btn_cancel_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //返回
                activity.finish();
            }
        });
    }

    public void getInput(){
        username=et_input_username.getText().toString();
        title=et_input_title.getText().toString();
        workplace=et_input_workplace.getText().toString();
        deadline=et_input_deadline.getText().toString();
        phone=et_input_phone.getText().toString();
        detail=et_input_detail.getText().toString();
        displaytime=et_input_post_time.getText().toString();
    }

    public boolean checkInput(){
        return true;
    }

    public void postJobs(){

    }
}
