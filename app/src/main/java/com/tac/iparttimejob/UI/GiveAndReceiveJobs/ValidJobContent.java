package com.tac.iparttimejob.UI.GiveAndReceiveJobs;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.tac.iparttimejob.R;
import com.tac.iparttimejob.UI.Utils.TitleToolBarLayout;

/**
 * Created by AiProgram on 2016/10/21.
 */

public class ValidJobContent extends AppCompatActivity{
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        setContentView(R.layout.layout_valid_job_content);

        TitleToolBarLayout titleToolBarLayout=(TitleToolBarLayout)findViewById(R.id.title_valid_job_content);
        titleToolBarLayout.setTitleText("招聘详情");
    }
}
