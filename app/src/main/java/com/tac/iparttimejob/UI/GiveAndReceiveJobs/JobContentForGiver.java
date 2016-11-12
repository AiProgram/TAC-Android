package com.tac.iparttimejob.UI.GiveAndReceiveJobs;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.tac.iparttimejob.Class.Object;
import com.tac.iparttimejob.R;
import com.tac.iparttimejob.UI.Utils.DataType;
import com.tac.iparttimejob.UI.Utils.MyToolBarLayout;

/**
 * Created by AiProgram on 2016/10/21.
 */

public class JobContentForGiver extends AppCompatActivity{
    //共有控件
    private MyToolBarLayout myToolBarLayout;

    private TextView title_content;
    private TextView tv_author;
    private TextView tv_numbers;
    private TextView tv_deadline;
    private TextView tv_payment;
    private TextView tv_phone_number;
    private TextView tv_email;
    private TextView tv_workplace;
    private TextView tv_detail;

    //validJobContent独有
    private TextView tv_days_left;
    private Button btn_cancel_recruit;
    private Button btn_signed_list;

    //unvalidJobContent独有
    private TextView tv_unvalid_warning;
    private Button btn_choosed_list;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.layout_valid_job_content);
        //根据状态设置布局
        switch (Object.recuitObject.getStatus()){
            case DataType.JOB_STATUS_UNDERGONING:{
                setContentView(R.layout.layout_valid_job_content);
            }break;
            case DataType.JOB_STATUS_CANCELED:{
                setContentView(R.layout.layout_unvalid_job_content);
            }break;
            case DataType.JOB_STATUS_CHECKING:{
                setContentView(R.layout.layout_valid_job_content);
            }break;
            case DataType.JOB_STATUS_FINSHED:{
                setContentView(R.layout.layout_unvalid_job_content);
            }break;
            case DataType.JOB_STATUS_REJECTED:{
                setContentView(R.layout.layout_unvalid_job_content);
            }break;
            default:setContentView(R.layout.layout_valid_job_content);
        }



        //初始化获得所有公共控件
        getViews();

        //初始化共有控件
        initCommonViews();

        //初始化独有控件
        initUniqueViews();


    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {

    }

    private void getViews(){
        myToolBarLayout=(MyToolBarLayout) findViewById(R.id.common_toolbar);

        title_content=(TextView) findViewById(R.id.title_content);
        tv_author=(TextView)  findViewById(R.id.tv_author);
        tv_numbers=(TextView) findViewById(R.id.tv_numbers);
        tv_deadline=(TextView) findViewById(R.id.tv_deadline);
        tv_payment=(TextView) findViewById(R.id.tv_payment);
        tv_phone_number=(TextView) findViewById(R.id.tv_phone_number);
        tv_email=(TextView) findViewById(R.id.tv_email);
        tv_workplace=(TextView) findViewById(R.id.tv_workplace);
        tv_detail=(TextView) findViewById(R.id.tv_detail);
    }

    private void initCommonViews(){
        myToolBarLayout.setTitle("招聘详情");

        title_content.setText(Object.recuitObject.getTitle());
        tv_author.setText(Object.recuitObject.getOwner());
        tv_deadline.setText(Object.recuitObject.getDealdine());
        tv_phone_number.setText(Object.recuitObject.getPhone());
        tv_workplace.setText(Object.recuitObject.getWorkplace());
        tv_detail.setText(Object.recuitObject.getWorkInfo());


        //服务器暂时不提供的设置为消失
//        tv_numbers.setVisibility(TextView.GONE);
//        tv_payment.setVisibility(TextView.GONE);
//        tv_email.setVisibility(TextView.GONE);

    }

    private void initUniqueViews(){
        switch (Object.recuitObject.getStatus()){
            case DataType.JOB_STATUS_UNDERGONING:{

            }break;
            case DataType.JOB_STATUS_CANCELED:{

            }break;
            case DataType.JOB_STATUS_CHECKING:{

            }break;
            case DataType.JOB_STATUS_FINSHED:{

            }break;
            case DataType.JOB_STATUS_REJECTED:{

            }break;
        }
    }

}
