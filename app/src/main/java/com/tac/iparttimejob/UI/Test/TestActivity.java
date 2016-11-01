package com.tac.iparttimejob.UI.Test;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.os.Handler;

import com.tac.iparttimejob.NetWork.Connect.HttpCallBackListener;
import com.tac.iparttimejob.NetWork.Login.SignIn;
import com.tac.iparttimejob.NetWork.SignUp.SignUp;
import com.tac.iparttimejob.R;
import com.tac.iparttimejob.UI.RegisterAndLogin.Login;
import com.tac.iparttimejob.UI.SignUpAndLogin.RegisterActivity;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Created by AiProgram on 2016/10/31.
 */

public class TestActivity extends AppCompatActivity {
    private String username="HZS";
    private String password="123456";

    private Button btn;
    private Handler handler=new Handler();;

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.layout_test);
        btn=(Button) findViewById(R.id.btn_test);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                test();

            }
        });

    }


    public void test(){

        Map<String,String>login=new LinkedHashMap<String, String>();
        login.put("name",username);
        login.put("psw",password);
        SignIn.login(login, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                Toast.makeText(TestActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                Log.i("Login","成功");
            }

            @Override
            public void onError(String error) {
                Toast.makeText(TestActivity.this,"登录失败，请重试",Toast.LENGTH_SHORT).show();
                Log.i("login","失败");
            }
        });
    }
}
