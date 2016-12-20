package com.tac.iparttimejob.UI.MyManager;

import android.app.ProgressDialog;
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
import com.tac.iparttimejob.NetWork.Query.QueryInformation;
import com.tac.iparttimejob.R;
import com.tac.iparttimejob.UI.EventBusEvent.SetResumeEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by AiProgram on 2016/11/15.
 */

public class SetResume extends AppCompatActivity{

    private MaterialEditText et_set_name;
    private MaterialEditText et_set_phone;
    private MaterialEditText et_set_email;
    private MaterialEditText et_set_single_info;
    private MaterialEditText et_set_project;
    private MaterialEditText et_set_good_at;
    private MaterialEditText et_set_project_adress;
    private EditText et_set_introduce_detail;
    private Button btn_confirm_resume;
    private Button btn_cancel_set_resume;

    private String name;
    private String phone;
    private String email;
    private String singleInfo;
    private String project;
    private String goodAt;
    private String projectAddress;
    private String detail;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resume);

        getViews();
        initVIews();
        initListener();
    }

    private void getViews(){
        et_set_name=(MaterialEditText) findViewById(R.id.et_set_name);
        et_set_phone=(MaterialEditText) findViewById(R.id.et_set_phone);
        et_set_email=(MaterialEditText) findViewById(R.id.et_set_email);
        et_set_single_info=(MaterialEditText) findViewById(R.id.tv_single_info);
        et_set_project=(MaterialEditText) findViewById(R.id.et_set_project);
        et_set_good_at=(MaterialEditText) findViewById(R.id.et_set_good_at);
        et_set_project_adress=(MaterialEditText) findViewById(R.id.et_set_project_adress);
        et_set_introduce_detail=(EditText) findViewById(R.id.et_set_introduce_detail);
        btn_cancel_set_resume=(Button) findViewById(R.id.btn_cancel_set_resume);
        btn_confirm_resume=(Button) findViewById(R.id.btn_confirm_resume);
    }

    private void getInput(){
        name=et_set_name.getText().toString();
        phone=et_set_phone.getText().toString();
        email=et_set_email.getText().toString();
        singleInfo=et_set_single_info.getText().toString();
        project=et_set_project.getText().toString();
        goodAt=et_set_good_at.getText().toString();
        projectAddress=et_set_project_adress.getText().toString();
        detail=et_set_introduce_detail.getText().toString();

        //拼接接口中没提供的信息，后期改为StringBuilder构建
        detail="做过的项有:   "+project+"\n"+"项目地址:   "+projectAddress+"\n"+"擅长   "+goodAt+"\n"+detail;
    }

    //初始化文字信息等
    private void initVIews(){
        Map<String,String> getResume=new LinkedHashMap<>();
        getResume.put("userid",Object.userObject.getUserid());
        QueryInformation.getPersonalResume(getResume, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        et_set_name.setText(Object.resumeObject.getName());
                        et_set_phone.setText(Object.resumeObject.getPhone());
                        et_set_email.setText(Object.resumeObject.getEmail());
                        et_set_single_info.setText(Object.resumeObject.getSingleResume());
                        et_set_good_at.setText(Object.resumeObject.getGoodat());
                        //et_set_introduce_detail.setText(Object.resumeObject.getDetailResume());
                    }
                });
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    private void initListener(){
        btn_confirm_resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog progressDialog = ProgressDialog.show(SetResume.this,"提示","正在更新简历",false);
                getInput();
                //设置简历,接口存在问题，等待修改
                final Map<String,String> setResume=new LinkedHashMap<String, String>();
                setResume.put("userid", Object.userObject.getUserid());
                setResume.put("name",Object.userObject.getName());
                setResume.put("nickname",name);
                setResume.put("phone",phone);
                setResume.put("email",email);
                setResume.put("singleResume",singleInfo);
                setResume.put("detailResume",detail);
                EditInformation.setUpdateRecruit(setResume, new HttpCallBackListener() {
                    @Override
                    public void onFinish(String result) {
                        //提示更新完成
                        //退出界面,当提示完善时可以不退出
                        finish();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //修改成功后发布事件
                                EventBus.getDefault().post(new SetResumeEvent());
                                progressDialog.dismiss();
                                Toast.makeText(SetResume.this,"更新简历成功",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onError(String error) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                                Toast.makeText(SetResume.this,"更新简历失败",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });
        btn_cancel_set_resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
