package com.tac.iparttimejob.UI.GiveAndReceiveJobs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tac.iparttimejob.NetWork.Connect.HttpCallBackListener;
import com.tac.iparttimejob.R;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.tac.iparttimejob.NetWork.Edit.EditInformation.setAssessment;

/**
 * Created by 守候。 on 2016/11/12.
 */
//评价 剩下不会写
public class Assess extends AppCompatActivity {
    private TextView title;
    private TextView assess_title;
    private TextView assess;
    private TextView point_title;
    private TextView point;
    private Button comfirm;
    private Button cancel;
    int assessPoint;

    public void setUserKind(int userKind) {
        this.userKind = userKind;
    }
//招聘者0还是应聘者1
    int userKind=0;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_assess);
        title=(TextView)findViewById(R.id.assess_title);
        assess_title=(TextView)findViewById(R.id.assess_tip1);
        assess=(TextView)findViewById(R.id.materialEditText);
        point_title=(TextView)findViewById(R.id.assess_tip2);
        point=(TextView)findViewById(R.id.materialEditText2);
        comfirm=(Button)findViewById(R.id.assess_btn_sure);
        cancel=(Button)findViewById(R.id.assess_btn_cancel);
        comfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(assess.length()>=200)
                {
                    Toast.makeText(Assess.this,"评价不用辣么多",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String p=point.getText().toString();
                    try {
                        assessPoint = Integer.parseInt(p);
                    } catch (NumberFormatException e) {
                        Toast.makeText(Assess.this,"输入的是数字",Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                        return;
                    }
                    if(assessPoint>=1&&assessPoint<=100)
                    {
                            if(userKind==0){
                                Map<String,String> getList=new LinkedHashMap<>();
                               // 这里参数没设置
                                setAssessment(getList, new HttpCallBackListener() {
                                    @Override
                                    public void onFinish(String result) {
                                        Toast.makeText(Assess.this,"评价成功",Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onError(String error) {
                                        Toast.makeText(Assess.this,"失败",Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                    }
                    else
                    {
                        Toast.makeText(Assess.this,"范围是1-100",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    //返回
            }
        });
    }

}
