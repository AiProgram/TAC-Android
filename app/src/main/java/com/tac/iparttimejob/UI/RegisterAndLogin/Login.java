package com.tac.iparttimejob.UI.RegisterAndLogin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tac.iparttimejob.Class.LoginResult;
import com.tac.iparttimejob.Class.UserResult;
import com.tac.iparttimejob.NetWork.Connect.HttpCallBackListener;
import com.tac.iparttimejob.NetWork.Login.SignIn;
import com.tac.iparttimejob.R;
import com.tac.iparttimejob.UI.GiveAndReceiveJobs.AppMain;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.tac.iparttimejob.Class.Object.loginObject;
import static com.tac.iparttimejob.Class.Object.userObject;

/**
 * Created by AiProgram on 2016/10/28.
 */

public class Login extends AppCompatActivity {

    private String account, password;
    private String FILE = "saveUserNamePwd";//用于保存SharedPreferences的文件
    private SharedPreferences pref = null;
    private SharedPreferences.Editor editor;
    private EditText et_input_password;
    private EditText et_input_account;
    private CheckBox cb_remember_password;
    private TextView tv_forget_password;
    private Button   btn_login;
    private Button   btn_register;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        //获取所有控件
        et_input_account = (EditText) findViewById(R.id.et_input_account);
        et_input_password = (EditText) findViewById(R.id.et_input_password);
        cb_remember_password = (CheckBox) findViewById(R.id.cb_remember_login);
        tv_forget_password = (TextView) findViewById(R.id.tv_forget_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_register = (Button) findViewById(R.id.btn_register);
        pref = getSharedPreferences(FILE, MODE_PRIVATE);
        boolean isMemory = pref.getBoolean("remember_password",false);

        //进入界面时，这个if用来判断SharedPreferences里面name和password有没有数据，有的话则直接打在EditText上面
        if (isMemory) {
            account = pref.getString("name", "");
            password = pref.getString("password", "");
            et_input_account.setText(account);
            et_input_password.setText(password);
            cb_remember_password.setChecked(true);
        }
        editor = pref.edit();
        editor.putString(account, et_input_account.toString());
        editor.putString(password, et_input_password.toString());
        editor.commit();
        //登录按钮点击事件
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginObject=new LoginResult.LoginUser();
                userObject=new UserResult.User();
                //登录操作
                 account=et_input_account.getText().toString();
                 password=et_input_password.getText().toString();
                final Map<String,String> login=new LinkedHashMap<String, String>();
             //   记得在结束调试时修改
               login.put("name",account);
               login.put("psw",password);

                remember();
                //近公测试使用
                //login.put("name","HZS");
               // login.put("psw","123456");

                SignIn.login(login, new HttpCallBackListener() {
                    @Override
                    public void onFinish(String result) {

                        Intent intent=new Intent(Login.this, AppMain.class);
                        startActivity(intent);
                        //非UI线程只能这样更新UI
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Login.this,"登录成功",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onError(final String error) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Login.this,error,Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
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
    //记住密码
    private void remember(){

        //记住密码
        if (cb_remember_password.isChecked()) {
            if (pref == null) {
                pref = getSharedPreferences(FILE, MODE_PRIVATE);
            }
            editor = pref.edit();
            editor.putString("name", et_input_account.getText().toString());
            editor.putString("password", et_input_password.getText().toString());
            editor.putBoolean("remember_password", true);
            editor.commit();
        } else {
            editor.clear();
            editor.commit();
        }
    }
}

