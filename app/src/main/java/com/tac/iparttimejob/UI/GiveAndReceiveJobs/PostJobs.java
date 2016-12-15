package com.tac.iparttimejob.UI.GiveAndReceiveJobs;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.tac.iparttimejob.NetWork.Connect.HttpCallBackListener;
import com.tac.iparttimejob.R;
import com.tac.iparttimejob.NetWork.Edit.EditInformation;
import com.tac.iparttimejob.UI.Utils.DataType;
import com.tac.iparttimejob.UI.Utils.FormatedTimeGeter;

import static com.tac.iparttimejob.Class.Object.loginObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by AiProgram on 2016/10/21.
 *
 * 这是用来填写发布招聘的类
 */

public class PostJobs extends AppCompatActivity{
    private EditText et_input_username;
    private EditText et_input_title;
    private EditText et_input_workplace;
    private Button btn_input_deadline;
    private EditText et_input_phone;
    private EditText et_input_detail;
    private Button btn_input_post_time;
    private Button btn_post_job;
    private Button btn_cancel_post;
    private EditText et_input_num;
    private EditText et_input_money;

    private AppCompatActivity activity=this;

    private String username;
    private String title;
    private String workplace;
    private String deadline;
    private String phone;
    private String detail;
    private String displaytime;
    private String num;
    private String money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_post_jobs);

        //初始化各个控件
        getViews();
        //初始化监听器
        initListener();
    }


    public void getViews(){
        et_input_username=(EditText) findViewById(R.id.et_input_username);
        et_input_title=(EditText)  findViewById(R.id.et_input_title);
        et_input_workplace=(EditText) findViewById(R.id.et_input_workplace);
        btn_input_deadline=(Button) findViewById(R.id.btn_input_deadline);
        et_input_phone=(EditText)  findViewById(R.id.et_input_phone);
        et_input_detail=(EditText) findViewById(R.id.et_input_detail);
        btn_input_post_time=(Button) findViewById(R.id.btn_input_post_time);
        btn_post_job=(Button) findViewById(R.id.btn_post_job);
        btn_cancel_post=(Button) findViewById(R.id.btn_cancel_post);
        et_input_num=(EditText)  findViewById(R.id.et_input_num);
        et_input_money=(EditText) findViewById(R.id.et_input_money);
    }
    public void initListener(){
        btn_post_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //发布事件
                getInput();
                //检查输入格式，预留
                if(checkInput()){
                    postJobs();
                }
            }
        });

        btn_cancel_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //返回
                activity.finish();
            }
        });

        btn_input_deadline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(DataType.DIALOG_DATE_PICKER_DEADLINE);
            }
        });

        btn_input_post_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(DataType.DIALOG_DATE_PICKER_POST_TIME);
            }
        });
    }

    //取得所有的输入
    public void getInput(){
        username=et_input_username.getText().toString();
        title=et_input_title.getText().toString();
        workplace=et_input_workplace.getText().toString();
        phone=et_input_phone.getText().toString();
        detail=et_input_detail.getText().toString();
        deadline=btn_input_deadline.getText().toString();
        displaytime=btn_input_post_time.getText().toString();
        num=et_input_num.getText().toString();
        money=et_input_money.getText().toString();

        //将薪水和详情拼接起来
        if(money.isEmpty())
            money="w未知";
        detail="薪水 :"+money+"\n"+detail;
    }

    //检查输入是否合格
    public boolean checkInput(){
        //获得当前时间
        //检查输入是否有空
        if(username.isEmpty()||title.isEmpty()||workplace.isEmpty()||phone.isEmpty()||detail.isEmpty()
                ||deadline.isEmpty()||displaytime.isEmpty()||num.isEmpty()||money.isEmpty()){
            Toast.makeText(PostJobs.this,"输入不得有空",Toast.LENGTH_SHORT).show();
            return false;
        }
        //检查发布及截至时间时间
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        try{
            Date currentTime=format.parse(FormatedTimeGeter.getFormatedDate());
            Date postTime=format.parse(displaytime);
            Date endTime=format.parse(deadline);
            if(postTime.compareTo(currentTime)<0){
                Toast.makeText(PostJobs.this,"发布时间不得早于当前时间",Toast.LENGTH_SHORT).show();
                return false;
            }
            if(endTime.compareTo(postTime)<0){
                Toast.makeText(PostJobs.this,"截止时间不得早于发布时间",Toast.LENGTH_SHORT).show();
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    //操作过于复杂，整合成一个函数
    public void postJobs(){
        Log.i("post name",loginObject.getName());
        Map<String,String> post=new LinkedHashMap<>();
        post.put("userid", loginObject.getUserid());
        post.put("username",loginObject.getName());
        post.put("title",title);
        post.put("workplace",workplace);
        post.put("deadline",deadline);
        post.put("phone",phone);
        post.put("workInfo",detail);
        post.put("displaytime",displaytime);
        post.put("needpeopleNum",num);
        EditInformation.setCreatRecuit(post, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //UI提示发布成功
                        Toast.makeText(PostJobs.this,"发布成功",Toast.LENGTH_SHORT).show();
                        //后续预计操作正在进行列表添加项目
                        finish();
                    }
                });
            }

            @Override
            public void onError(String error) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(PostJobs.this,"发布失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    //展示日期的选取Dialog
    private void showDatePickerDialog(int dialogType){
        Calendar calendar=Calendar.getInstance();
        switch (dialogType){
            case DataType.DIALOG_DATE_PICKER_DEADLINE:{

                new DatePickerDialog(PostJobs.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        // TODO Auto-generated method stub
                       String mYear = year+"";
                       String mMonth = month+"";
                       String mDay = day+"";
                        //更新EditText控件日期 小于10加0
                        btn_input_deadline.setText(new StringBuilder().append(year).append("-")
                                .append((month + 1) < 10 ? "0" + (month + 1) : (month + 1))
                                .append("-")
                                .append((day < 10) ? "0" + day : day) );
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH) ).show();

            }break;
            case DataType.DIALOG_DATE_PICKER_POST_TIME:{

                new DatePickerDialog(PostJobs.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        // TODO Auto-generated method stub
                        String mYear = year+"";
                        String mMonth = month+"";
                        String mDay = day+"";
                        //更新EditText控件日期 小于10加0
                        btn_input_post_time.setText(new StringBuilder().append(year).append("-")
                                .append((month + 1) < 10 ? "0" + (month + 1) : (month + 1))
                                .append("-")
                                .append((day < 10) ? "0" + day : day) );
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH) ).show();

            }break;
        }
    }
}
