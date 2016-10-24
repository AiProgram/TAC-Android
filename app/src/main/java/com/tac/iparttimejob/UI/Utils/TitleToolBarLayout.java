package com.tac.iparttimejob.UI.Utils;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tac.iparttimejob.R;

/**
 * Created by AiProgram on 2016/10/23.
 */

public class TitleToolBarLayout extends LinearLayout{

    public TitleToolBarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.layout_with_common_toolbar, this);
        ImageButton backButton = (ImageButton) findViewById(R.id.btn_back_common_toolbar);

        backButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity) getContext()).finish();
            }
        });
    }

    public void setTitleText(String titleText){
        TextView TitleTextView = (TextView) findViewById(R.id.title_common_toolbar);
        TitleTextView.setText(titleText);
    }
}
