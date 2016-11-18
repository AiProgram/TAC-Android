package com.tac.iparttimejob.UI.GiveAndReceiveJobs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tac.iparttimejob.NetWork.Connect.HttpCallBackListener;
import com.tac.iparttimejob.R;
import com.tac.iparttimejob.UI.Utils.DataType;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.tac.iparttimejob.Class.Object.applicationObjectList;
import static com.tac.iparttimejob.Class.Object.recuitObject;
import static com.tac.iparttimejob.Class.Object.userObject;
import com.tac.iparttimejob.NetWork.Edit.EditInformation;
import com.tac.iparttimejob.UI.Utils.FormatedTimeGeter;


/**
 * Created by AiProgram on 2016/11/15.
 */

public class JobContentForReceiver extends AppCompatActivity {

    private int type;
    private int applicantsid;
    //公有控件
    private TextView title_content;
    private TextView tv_author;
    private TextView tv_language_requirment;//暂时无效
    private TextView tv_numbers;
    private TextView tv_deadline;
    private TextView tv_payment;//暂时无效
    private TextView tv_phone_number;
    private TextView tv_email;
    private TextView tv_workplace;
    private TextView tv_detail;

    //非公有控件
    private Button btn_cancel_signUp;
    private Button btn_signUp;
    private TextView tv_days_left;//unsigned所有


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //获得显示的类型
        type=getIntent().getIntExtra("type", DataType.UNSIGNED_JOB_LIST);
        applicantsid=getIntent().getIntExtra("applicantsid",1);
        switch (type){
            case DataType.UNSIGNED_JOB_LIST:{
                setContentView(R.layout.layout_unsigned_content);
            }break;
            case DataType.SIGNED_JOB_LIST:{
                setContentView(R.layout.layout_signed_content);
            }break;
            default:{
                setContentView(R.layout.layout_unsigned_content);
            }
        }

        getCommonViews();

        initCommonViews();

        getAndSetUniqueViews();
    }

    //获取公有控件
    private void getCommonViews(){
        title_content=(TextView) findViewById(R.id.title_content);
        tv_author=(TextView) findViewById(R.id.tv_author);
        tv_language_requirment=(TextView) findViewById(R.id.tv_language_requirment);
        tv_numbers=(TextView) findViewById(R.id.tv_numbers);
        tv_deadline=(TextView) findViewById(R.id.tv_deadline);
        tv_payment=(TextView)  findViewById(R.id.tv_payment);
        tv_phone_number=(TextView) findViewById(R.id.tv_phone_number);
        tv_email=(TextView) findViewById(R.id.tv_email);
        tv_workplace=(TextView) findViewById(R.id.tv_workplace);
        tv_detail=(TextView) findViewById(R.id.tv_detail_resume);
    }

    //初始化公有控件
    private void initCommonViews(){
        title_content.setText(recuitObject.getTitle());
        tv_author.setText(recuitObject.getTac_user().getName());
        tv_numbers.setText(recuitObject.getNeedpeopleNum()+"");
        tv_deadline.setText(recuitObject.getDealdine());
        tv_phone_number.setText(recuitObject.getPhone());
        tv_email.setText(recuitObject.getEmail());
        tv_workplace.setText(recuitObject.getWorkplace());
        tv_detail.setText(recuitObject.getWorkInfo());
    }

    private void getAndSetUniqueViews(){
        switch (type){
            case DataType.UNSIGNED_JOB_LIST:{
                btn_signUp=(Button) findViewById(R.id.btn_signUp);
                btn_signUp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //报名
                        Map<String,String> signUpJob=new LinkedHashMap<String, String>();
                        signUpJob.put("recruitid",recuitObject.getRecruitid());
                        Log.d("owner:",recuitObject.getOwner());
                        signUpJob.put("ownername",recuitObject.getOwner());
                        signUpJob.put("applicanttime", FormatedTimeGeter.getFormatedDate());
                        signUpJob.put("userid",userObject.getUserid());
                        EditInformation.setCreatApplication(signUpJob, new HttpCallBackListener() {
                            @Override
                            public void onFinish(String result) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(JobContentForReceiver.this,"报名成功",Toast.LENGTH_SHORT).show();
                                        //报名成功后不允许重复报名
                                        btn_signUp.setEnabled(false);
                                        btn_signUp.setClickable(false);
                                    }
                                });
                            }

                            @Override
                            public void onError(String error) {
                                Toast.makeText(JobContentForReceiver.this,error,Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }break;
            case DataType.SIGNED_JOB_LIST:{
                btn_cancel_signUp=(Button) findViewById(R.id.btn_cancel_signUp);
                btn_cancel_signUp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //取消报名,缺少applicantid无法继续编写
                        Map<String,String>cancelApplication=new LinkedHashMap<String, String>();
                        cancelApplication.put("applicantsid",applicantsid+"");
                        Log.i("applicationid",applicantsid+"");
                        EditInformation.setCancelApplication(cancelApplication, new HttpCallBackListener() {
                            @Override
                            public void onFinish(String result) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(JobContentForReceiver.this,"取消成功",Toast.LENGTH_SHORT).show();
                                    }
                                });
                                finish();
                            }

                            @Override
                            public void onError(String error) {

                            }
                        });
                    }
                });
            }break;
        }
    }
}
