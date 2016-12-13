package com.tac.iparttimejob.UI.MyManager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.tac.iparttimejob.Class.Object;
import com.tac.iparttimejob.NetWork.Connect.HttpCallBackListener;
import com.tac.iparttimejob.NetWork.Edit.EditInformation;
import com.tac.iparttimejob.R;
import com.tac.iparttimejob.UI.Utils.FormatedTimeGeter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by AiProgram on 2016/12/2.
 */

public class Feedback extends AppCompatActivity {

    private EditText et_set_advice;
    private Button btn_send_feedback;
    private Button btn_cancel_feedback;

    private String feedback;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_send_feedback);
        getViews();
        initViews();
        initListeners();
    }

    private void getViews(){
        et_set_advice=(EditText) findViewById(R.id.et_set_advice);
        btn_send_feedback=(Button) findViewById(R.id.btn_send_feedback);
        btn_cancel_feedback=(Button) findViewById(R.id.btn_cancel_feedback);
    }

    private void initViews(){
        //暂时为空
    }

    private void initListeners(){
        btn_send_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //发送反馈
                feedback=et_set_advice.getText().toString();
                Map<String,String>advice=new LinkedHashMap<String, String>();
                advice.put("userid", Object.userObject.getUserid());
                advice.put("username",Object.userObject.getName());
                advice.put("time",FormatedTimeGeter.getFormatedDate());
                //后续可以提供另外的联系号码填写
                advice.put("phone",Object.userObject.getPhone());
                advice.put("advice",feedback);
                EditInformation.setAdvice(advice, new HttpCallBackListener() {
                    @Override
                    public void onFinish(String result) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Feedback.this,"感谢您的建议",Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
                    }

                    @Override
                    public void onError(String error) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Feedback.this,"提交失败",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });

        btn_cancel_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //取消反馈
                finish();
            }
        });
    }
}

