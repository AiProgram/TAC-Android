package com.tac.iparttimejob.UI.GiveAndReceiveJobs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tac.iparttimejob.Class.LoginResult;
import com.tac.iparttimejob.NetWork.Connect.HttpCallBackListener;
import com.tac.iparttimejob.R;
import com.tac.iparttimejob.NetWork.Edit.EditInformation;
import static com.tac.iparttimejob.Class.Object.loginObject;
import static com.tac.iparttimejob.Class.Object.userObject;

import java.util.LinkedHashMap;
import java.util.Map;

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
                    Toast.makeText(PostJobs.this,"请检查您的输入格式",Toast.LENGTH_SHORT).show();
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

    //取得所有的输入
    public void getInput(){
        username=et_input_username.getText().toString();
        title=et_input_title.getText().toString();
        workplace=et_input_workplace.getText().toString();
        deadline=et_input_deadline.getText().toString();
        phone=et_input_phone.getText().toString();
        detail=et_input_detail.getText().toString();
        displaytime=et_input_post_time.getText().toString();
    }

    //检查输入是否合格,待添加
    public boolean checkInput(){
        return true;
    }

    //操作过于复杂，整合成一个函数
    public void postJobs(){
        Log.i("post name",loginObject.getName());
        Map<String,String> post=new LinkedHashMap<>();
        post.put("userid", loginObject.getUserid());
        post.put("username",loginObject.getName());
        post.put("title",title);
        post.put("workplace",workplace);
        post.put("deadline",deadline);
        post.put("phone",phone);
        post.put("workInfo",detail);
        post.put("displaytime",displaytime);
        EditInformation.setCreatRecuit(post, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //UI提示发布成功
                        Toast.makeText(PostJobs.this,"发布成功",Toast.LENGTH_SHORT).show();
                        //后续预计操作正在进行列表添加项目

                    }
                });
            }

            @Override
            public void onError(String error) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(PostJobs.this,"发布失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
