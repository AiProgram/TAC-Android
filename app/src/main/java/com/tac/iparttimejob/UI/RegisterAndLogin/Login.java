package com.tac.iparttimejob.UI.RegisterAndLogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.tac.iparttimejob.R;

/**
 * Created by AiProgram on 2016/10/28.
 */

public class Login extends AppCompatActivity {

    private EditText et_input_password;
    private EditText et_input_account;
    private CheckBox cb_remember_password;
    private TextView tv_forget_password;
    private Button   btn_login;
    private Button   btn_register;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        //获取所有控件
        et_input_account = (EditText) findViewById(R.id.et_input_account);
        et_input_password = (EditText) findViewById(R.id.et_input_password);
        cb_remember_password = (CheckBox) findViewById(R.id.cb_remember_login);
        tv_forget_password = (TextView) findViewById(R.id.tv_forget_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_register = (Button) findViewById(R.id.btn_register);

        //登录按钮点击事件
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //登录操作

            }
        });

        //注册按钮检测事件
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //转到注册界面
                Intent intent=new Intent(Login.this,Register.class);
                startActivity(intent);
            }
        });

        //记住登录单选按钮
        cb_remember_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    //自动登录事件
                }else{
                    //取消自动登录
                }
            }
        });

        //忘记密码
        tv_forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //转到找回密码界面
            }
        });
    }
}
