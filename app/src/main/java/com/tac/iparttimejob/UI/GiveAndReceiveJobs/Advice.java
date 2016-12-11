package com.tac.iparttimejob.UI.GiveAndReceiveJobs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tac.iparttimejob.NetWork.Connect.HttpCallBackListener;
import com.tac.iparttimejob.R;

import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.tac.iparttimejob.Class.Object.loginObject;
import static com.tac.iparttimejob.NetWork.Edit.EditInformation.setAdvice;

/**
 * Created by 守候。 on 2016/11/14.
 */

public class Advice extends AppCompatActivity  {
    private TextView title;
    private TextView adviceTitle;
    private TextView advice;
    private Button confirm;
    private Button cancel;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_send_feedback);
        title=(TextView)findViewById(R.id.feedback_title);
        adviceTitle=(TextView)findViewById(R.id.feedback_title2);
        advice=(TextView)findViewById(R.id.et_set_advice);
        confirm=(Button)findViewById(R.id.btn_send_feedback);
        cancel=(Button)findViewById(R.id.btn_cancel_feedback);
        confirm.setOnClickListener(new View.OnClickListener() {
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            String date=sdf.format(new java.util.Date());
            @Override
            public void onClick(View view) {
                if(advice.getText().length()>=200)
                {
                    Toast.makeText(Advice.this,"反馈在100字以内",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Map<String,String> getList=new LinkedHashMap<>();
                    getList.put("userid",loginObject.getUserid());
                    getList.put("username",loginObject.getName());
                    getList.put("time",date);
                    getList.put("phone",loginObject.getPhone());
                    getList.put("advice",advice.getText().toString());
                    setAdvice(getList, new HttpCallBackListener() {
                        @Override
                        public void onFinish(String result) {
                            Toast.makeText(Advice.this,"感谢您的建议",Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(String error) {
                            Toast.makeText(Advice.this,"失败,请稍后重试",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                // 返回
            }
        });
    }
}
