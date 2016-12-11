package com.tac.iparttimejob.UI.SignUpAndLogin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tac.iparttimejob.NetWork.Connect.HttpCallBackListener;
import com.tac.iparttimejob.NetWork.SignUp.SignUp;
import com.tac.iparttimejob.R;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by AiProgram on 2016/10/25.
 */

public class RegisterActivity extends AppCompatActivity {
    private EditText editText;
    private Button button;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);
        button=(Button)findViewById(R.id.btn_accept_register);

        //下面代码测试正确.按照下面格式写即可
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String,String> params=new LinkedHashMap<String, String>();
                params.put("name","3");
                params.put("password","520520");
                params.put("phone","132202121");
                params.put("email","9924542@qq.com");
                SignUp.register(params, new HttpCallBackListener() {
                    @Override
                    public void onFinish(String result) {
                        Log.d("zjm","注册成功");
                        Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onError(String error) {

                        Log.d("zjm","注册失败"+error);
                        Toast.makeText(RegisterActivity.this,"注册失败--"+error,Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

    }
}
