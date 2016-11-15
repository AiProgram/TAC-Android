package com.tac.iparttimejob.UI.MyManager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.tac.iparttimejob.Class.Object;
import com.tac.iparttimejob.NetWork.Connect.HttpCallBackListener;
import com.tac.iparttimejob.NetWork.Edit.EditInformation;
import com.tac.iparttimejob.R;

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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resume);

        getViews();
        initListener();
    }

    private void getViews(){
        et_set_name=(MaterialEditText) findViewById(R.id.et_set_name);
        et_set_phone=(MaterialEditText) findViewById(R.id.et_set_phone);
        et_set_email=(MaterialEditText) findViewById(R.id.et_set_email);
        et_set_single_info=(MaterialEditText) findViewById(R.id.et_set_single_info);
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
        detail="做过的项有:   "+project+"\n"+"项目地址:   "+projectAddress+"\n"+detail;
    }

    private void initListener(){
        btn_confirm_resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getInput();
                //设置简历
                Map<String,String> setResume=new LinkedHashMap<String, String>();
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
                    }

                    @Override
                    public void onError(String error) {

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
