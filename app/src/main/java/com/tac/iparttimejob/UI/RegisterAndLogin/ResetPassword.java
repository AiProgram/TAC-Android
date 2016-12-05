package com.tac.iparttimejob.UI.RegisterAndLogin;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
    private EditText editText;
    private Button button;
    private Button bt_reset_password;
    private EditText et_reset_password;
    private EditText et_confirm_reset_password;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_email);
        getViews();
        initViews();
        initListener();
    }

    private void getViews(){
        editText=(EditText) findViewById(R.id.editText);
        button=(Button) findViewById(R.id.button);
        bt_reset_password=(Button) findViewById(R.id.bt_reset_password);
        et_reset_password=(EditText) findViewById(R.id.et_reset_password);
        et_confirm_reset_password=(EditText) findViewById(R.id.et_confirm_reset_password);
    }

    private void initViews(){

    }

    private void initListener(){
        //获取验证码
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=editText.getText().toString();
                Map<String,String> getCert=new LinkedHashMap<String, String>();
                getCert.put("getterEmail",email);
                QueryInformation.getEmailInformation(getCert, new HttpCallBackListener() {
                    @Override
                    public void onFinish(String result) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(ResetPassword.this,"验证码发送成功",Toast.LENGTH_SHORT).show();
                                button.setText(Object.emailData);
                            }
                        });
                    }

                    @Override
                    public void onError(String error) {

                    }
                });
            }
        });

        //修改密码
        bt_reset_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password="";
                String confirmPassword="";
                password=et_reset_password.getText().toString().trim();
                confirmPassword=et_confirm_reset_password.getText().toString().trim();
                //检测两次输入密码是否一致
                if(password.equals(confirmPassword)){
                    String username="";
                    Map<String,String> resetPassword=new LinkedHashMap<String, String>();
                    resetPassword.put("username",username);
                    resetPassword.put("password",password);
                    EditInformation.reSetUserPassword(resetPassword, new HttpCallBackListener() {
                        @Override
                        public void onFinish(String result) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(ResetPassword.this,"重置成功，请重新登录",Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            });
                        }

                        @Override
                        public void onError(String error) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(ResetPassword.this,"重置失败",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
                else{
                    Toast.makeText(ResetPassword.this,"两次输入密码不一致",Toast.LENGTH_SHORT).show();
                    et_confirm_reset_password.setText("");
                }
            }
        });
    }
}
