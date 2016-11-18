package com.tac.iparttimejob.UI.RegisterAndLogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.os.Handler;


import com.tac.iparttimejob.R;
/**
 * Created by AiProgram on 2016/11/18.
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        initViews();
        setContentView(R.layout.layout_splash_screen);
    }

    private void initViews(){
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashActivity.this,Login.class);
                startActivity(intent);
                finish();
            }
        },2000);
    }
}
