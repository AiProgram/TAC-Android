package com.tac.iparttimejob.UI.RegisterAndLogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tac.iparttimejob.Class.Object;
import com.tac.iparttimejob.Class.RecuitResult;
import com.tac.iparttimejob.NetWork.Connect.HttpCallBackListener;
import com.tac.iparttimejob.NetWork.Query.QueryInformation;
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
    private String signUpErr;
    private String vertCode="sample";

    private MaterialEditText et_set_account;
    private MaterialEditText et_set_password;
    private MaterialEditText et_confirm_password;
    private MaterialEditText et_set_phone_number;
    private MaterialEditText et_set_email;

    private Button btn_accpet_register;
    private Button btn_cancel_register;
    private Button bt_send_verification_code;
    private EditText et_verification_code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);

        //控件初始化不能放到别处
        et_set_account=(MaterialEditText) findViewById(R.id.et_set_account);
        et_set_password=(MaterialEditText) findViewById(R.id.et_reset_password);
        et_confirm_password=(MaterialEditText) findViewById(R.id.et_confirm_password);
        et_set_phone_number=(MaterialEditText) findViewById(R.id.et_set_phone_number);
        et_set_email=(MaterialEditText) findViewById(R.id.et_set_email);

        btn_accpet_register=(Button) findViewById(R.id.btn_accept_register);
        btn_cancel_register=(Button) findViewById(R.id.btn_cancel_register);
        bt_send_verification_code=(Button) findViewById(R.id.bt_send_verification_code);
        et_verification_code=(EditText) findViewById(R.id.et_verification_code);

        //注册按钮事件
        btn_accpet_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getInput();
                if(checkInput()&&verifyEmail()){
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
                        public void onError(final String error) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Log.d("signUpErr:",error);
                                    Toast.makeText(Register.this,error,Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    });

                }else{
                    //输入错误提示
                    Log.d("signUpErr:",signUpErr);
                    Toast.makeText(Register.this,signUpErr,Toast.LENGTH_SHORT).show();
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

        bt_send_verification_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=et_set_email.getText().toString().trim();
                Map<String,String> getCert=new LinkedHashMap<String, String>();
                getCert.put("getterEmail",email);
                QueryInformation.getEmailInformation(getCert, new HttpCallBackListener() {
                    @Override
                    public void onFinish(String result) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Register.this,"验证码发送成功",Toast.LENGTH_SHORT).show();
                                bt_send_verification_code.setText("验证码已发送");
                               // bt_send_verification_code.setEnabled(false);
                                vertCode= Object.emailData;
                            }
                        });
                    }

                    @Override
                    public void onError(String error) {
                        Toast.makeText(Register.this,"验证码发送失败",Toast.LENGTH_SHORT).show();
                    }
                });
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
    private boolean checkInput() {
        signUpErr="";
        RegexCheck regexCheck = new RegexCheck();
        if (!regexCheck.checkAccount(account)) {
            if(account.equals("")){
                signUpErr="用户名不能为空";
            }
            else {
                signUpErr="用户名以字母开头,包含数字汉字字母下划线，4到25字符";
            }
            return false;
        }
        //密码格式检测出了点小问题，等待修复
       /* else if(!regexCheck.checkPassword(password)) {
            if(password.equals("")){
                signUpErr="密码不能为空";
            }
            else signUpErr="密码以字母开头，长度在8到20之间，只能包含字母、数字和下划线";
            return false;
        }*/
        else if(!password.equals(passwordConfirmed)) {
            if(passwordConfirmed.equals("")){
                signUpErr="确认密码不能为空";
            }
            else  signUpErr="两次密码不一致";
            return false;
        }
        else if(!regexCheck.checkPhoneNumbers(phoneNumber)) {
            if(phoneNumber.equals("")){
                signUpErr="手机号不能为空";
            }
            else signUpErr="请出入正确的手机号码";
            return false;
        }
        //邮箱格式检查有问题
        else if(!regexCheck.checkEmail(email)){
            if(email.equals("")){
                signUpErr="邮箱不能为空";
            }
            else signUpErr="请出入正确的邮箱";
            return false;
        }
        return true;
    }

    //验证码函数
    private boolean verifyEmail(){
        if(vertCode.equals("sample")){
            signUpErr="请先验证邮箱";
            return false;
        }
        if(!vertCode.equals(et_verification_code.getText().toString().trim())){
            signUpErr="验证码错误";
            return false;
        }
        return true;
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
