package com.tac.iparttimejob.UI.RegisterAndLogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tac.iparttimejob.NetWork.Connect.HttpCallBackListener;
import com.tac.iparttimejob.NetWork.SignUp.SignUp;
import com.tac.iparttimejob.R;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.tac.iparttimejob.UI.Utils.RegexCheck;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by AiProgram on 2016/10/28.
 */

public class Register extends AppCompatActivity {
    private String account;
    private String password;
    private String passwordConfirmed;
    private String phoneNumber;
    private String email;

    private MaterialEditText et_set_account;
    private MaterialEditText et_set_password;
    private MaterialEditText et_confirm_password;
    private MaterialEditText et_set_phone_number;
    private MaterialEditText et_set_email;

    private Button btn_accpet_register;
    private Button btn_cancel_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);

        //控件初始化不能放到别处
        et_set_account=(MaterialEditText) findViewById(R.id.et_set_account);
        et_set_password=(MaterialEditText) findViewById(R.id.et_set_password);
        et_confirm_password=(MaterialEditText) findViewById(R.id.et_confirm_password);
        et_set_phone_number=(MaterialEditText) findViewById(R.id.et_set_phone_number);
        et_set_email=(MaterialEditText) findViewById(R.id.et_set_email);

        btn_accpet_register=(Button) findViewById(R.id.btn_accept_register);
        btn_cancel_register=(Button) findViewById(R.id.btn_cancel_register);

        //注册按钮事件
        btn_accpet_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getInput();
                if(checkInput()){
                    //完成注册
                    //预留用作验证码函数
                    verifyEmail();
                    Map<String,String>register=new LinkedHashMap<String, String>();
                    register.put("name",account);
                    register.put("password",password);
                    register.put("phone",phoneNumber);
                    register.put("email",email);
                    SignUp.register(register, new HttpCallBackListener() {
                        @Override
                        public void onFinish(String result) {
                            //注册成功事件
                            Intent intent=new Intent(Register.this,Login.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onError(String error) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(Register.this,"注册失败，网络开了点小差",Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    });

                }else{
                    //输入错误提示
                    Toast.makeText(Register.this,"请检查您的输入格式",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_cancel_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //返回登录界面
                Register.this.finish();
            }
        });
    }


    //获得用户输入
    private void getInput(){
        account=et_set_account.getText().toString();
        password=et_set_password.getText().toString();
        passwordConfirmed=et_confirm_password.getText().toString();
        phoneNumber=et_set_phone_number.getText().toString();
        email=et_set_email.getText().toString();
    }

    //检查用户的输入是否合格
    private boolean checkInput(){
        RegexCheck regexCheck=new RegexCheck();
        if(!regexCheck.checkAccount(account)) return false;
        //密码格式检测出了点小问题，等待修复
        //if(!regexCheck.checkPassword(password)) return false;
        if(!password.equals(passwordConfirmed)) return false;
        if(!regexCheck.checkPhoneNumbers(phoneNumber)) return false;
        if(!regexCheck.checkEmail(email)) return false;
        return true;
    }

    //验证码函数
    private void verifyEmail(){

    }


    //测试时使用,log输入
    private void test(){
        Log.i("register","account "+account);
        Log.i("register","password "+password);
        Log.i("register","passwordConfrimed "+passwordConfirmed);
        Log.i("register","phoneNumber"+phoneNumber);
        Log.i("register","email"+email);
    }
}
