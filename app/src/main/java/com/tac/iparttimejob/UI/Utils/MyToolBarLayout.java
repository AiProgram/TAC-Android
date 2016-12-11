package com.tac.iparttimejob.UI.Utils;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.DialogTitle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tac.iparttimejob.R;

/**
 * Created by AiProgram on 2016/11/8.
 */

public class MyToolBarLayout extends RelativeLayout{
    public MyToolBarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.layout_with_common_toolbar,this);
        initListener();
    }
    public void setTitle(String titleText){
        TextView title=(TextView) findViewById(R.id.title_common_toolbar);
        title.setText(titleText);
    }
    private void initListener(){
        Button btnBack=(Button)  findViewById(R.id.btn_back_common_toolbar);
        btnBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity)getContext()).finish();
            }
        });
    }
}
