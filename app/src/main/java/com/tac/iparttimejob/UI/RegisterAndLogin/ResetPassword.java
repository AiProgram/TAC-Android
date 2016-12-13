package com.tac.iparttimejob.UI.RegisterAndLogin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tac.iparttimejob.Class.Object;
import com.tac.iparttimejob.NetWork.Connect.HttpCallBackListener;
import com.tac.iparttimejob.NetWork.Edit.EditInformation;
import com.tac.iparttimejob.NetWork.Query.QueryInformation;
import com.tac.iparttimejob.R;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by AiProgram on 2016/12/5.
 * 这是用于找回密码的类
 * 只允许在验证正确的情况下修改而不允许查看原密码
 */

public class ResetPassword extends AppCompatActivity {
    private EditText et_username_reset_password;
    private EditText et_email_reset_password;
    private EditText et_verification_code;
    private EditText et_password_reset;
    private EditText et_confirm_reset_password;
    private Button bt_send_verification_code;
    private Button bt_reset_password;
    private Button bt_cancel_reset_password;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_reset_password);
        getViews();
        initViews();
        initListener();
    }

    private void getViews(){
        et_username_reset_password=(EditText) findViewById(R.id.et_username_reset_password);
        et_email_reset_password=(EditText)   findViewById(R.id.et_email_reset_password);
        et_verification_code=(EditText) findViewById(R.id.et_verification_code);
        et_password_reset=(EditText) findViewById(R.id.et_password_reset);
        et_confirm_reset_password=(EditText) findViewById(R.id.et_confirm_reset_password);
        bt_send_verification_code=(Button) findViewById(R.id.bt_send_verification_code);
        bt_reset_password=(Button) findViewById(R.id.bt_reset_password);
        bt_cancel_reset_password=(Button) findViewById(R.id.bt_cancel_reset_password);
    }

    private void initViews(){

    }

    private void initListener(){
        //获取验证码
        bt_send_verification_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=et_email_reset_password.getText().toString();
                Map<String,String> getCert=new LinkedHashMap<String, String>();
                getCert.put("getterEmail",email);
                QueryInformation.getEmailInformation(getCert, new HttpCallBackListener() {
                    @Override
                    public void onFinish(String result) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(ResetPassword.this,"验证码发送成功",Toast.LENGTH_SHORT).show();
                                bt_send_verification_code.setText("验证码已发送");
                                bt_send_verification_code.setEnabled(false);
                            }
                        });
                    }

                    @Override
                    public void onError(String error) {
                        Toast.makeText(ResetPassword.this,"验证码发送失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        //修改密码
        bt_reset_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkEmail();
            }
        });
    }

    //检验是不是注册邮箱
    private void checkEmail(){
        String username=et_username_reset_password.getText().toString().trim();
        final String email=et_email_reset_password.getText().toString().trim();
        Map<String,String> getInfor=new LinkedHashMap<>();
        getInfor.put("username",username);
        QueryInformation.getInformationByUserName(getInfor, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                if(Object.getuserObject.getEmail().equals(email)){
                    editPassword();
                }
                else{
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(ResetPassword.this,"输入邮箱不是注册邮箱",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onError(String error) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ResetPassword.this,"验证邮箱对应性失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void editPassword(){
        String password="";
        String confirmPassword="";
        String vertificationCode="";
        String username="";
        password=et_password_reset.getText().toString().trim();
        confirmPassword=et_confirm_reset_password.getText().toString().trim();
        vertificationCode=et_verification_code.getText().toString().trim();
        username=et_username_reset_password.getText().toString().trim();
        //检测两次输入密码是否一致
        if(password.equals(confirmPassword)){
            //验证验证码
            if(vertificationCode.equals(Object.emailData)) {
                Map<String, String> resetPassword = new LinkedHashMap<String, String>();
                resetPassword.put("username", username);
                resetPassword.put("password", password);
                EditInformation.reSetUserPassword(resetPassword, new HttpCallBackListener() {
                    @Override
                    public void onFinish(String result) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(ResetPassword.this, "重置成功，请重新登录", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
                    }

                    @Override
                    public void onError(String error) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(ResetPassword.this, "重置失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }else{
                Toast.makeText(ResetPassword.this,"验证码错误",Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(ResetPassword.this,"两次输入密码不一致",Toast.LENGTH_SHORT).show();
            et_confirm_reset_password.setText("");
        }
    }
}
