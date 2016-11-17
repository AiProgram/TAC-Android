package com.tac.iparttimejob.UI.MyManager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tac.iparttimejob.Class.Object;
import com.tac.iparttimejob.NetWork.Connect.HttpCallBackListener;
import com.tac.iparttimejob.NetWork.Edit.EditInformation;
import com.tac.iparttimejob.R;
import com.tac.iparttimejob.UI.RegisterAndLogin.Login;
import com.tac.iparttimejob.UI.Utils.BlurBitmap;
import com.tac.iparttimejob.UI.Utils.RoundImageView;
import com.tac.iparttimejob.NetWork.Query.QueryInformation;
import com.tac.iparttimejob.UI.Utils.BitmapAndStringConverter;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Created by AiProgram on 2016/10/30.
 */

public class MyManager extends Fragment {
    private RoundImageView riv_user_head;
    private TextView tv_username_my_manager;
    private TextView tv_single_info;
    private RelativeLayout rl_user_filed;
    private RelativeLayout rl_my_manager;
    private ExpandableListView elv;
    private MyELVAdapter adapter;
    private Button btn_exit_login;

    private Bitmap userHeadImage;


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_my_manager,container,false);
        return view;
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View fragmentView=getView();
        riv_user_head=(RoundImageView) fragmentView.findViewById(R.id.riv_user_head);
        tv_single_info=(TextView) fragmentView.findViewById(R.id.tv_single_info);
        tv_username_my_manager=(TextView) fragmentView.findViewById(R.id.tv_username_my_manager);
        rl_user_filed=(RelativeLayout) fragmentView.findViewById(R.id.rl_user_filed);
        rl_my_manager=(RelativeLayout) fragmentView.findViewById(R.id.rl_my_manager);
        elv=(ExpandableListView) fragmentView.findViewById(R.id.elv_function_my_manager);
        btn_exit_login=(Button) fragmentView.findViewById(R.id.btn_exit_login);


        //初始化退出登录监听器
        btn_exit_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //退出登录事件
            }
        });

        //为ExpandableListVIew设置adapter
        adapter=new MyELVAdapter(this.getContext());
        elv.setAdapter(adapter);

        initELVListener();

        //初始化头像、用户名、简介等
        initViews();
    }

    public void initViews(){
        //设置头像
        getUserHeadImage();

        //获得简历中的一句简介
        Map<String,String> getSingleInfo=new LinkedHashMap<>();
        getSingleInfo.put("userid",Object.userObject.getUserid());
        QueryInformation.getPersonalResume(getSingleInfo, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_single_info.setText(Object.resumeObject.getSingleResume());
                    }
                });
            }

            @Override
            public void onError(String error) {

            }
        });

        //设置用户名
        tv_username_my_manager.setText(Object.userObject.getName());
    }

    //初始化ELV监听器
    public void initELVListener(){
        elv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                switch (i){
                    case 2:{
                        if(i1==0){
                            //转到招聘者的评价界面
                        }
                        else if(i1==1){
                            //转到应聘者界面
                        }
                    }break;
                    default:
                }

                return false;
            }
        });

        elv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                switch (i){
                    case 0:{
                        //转到账号信息
                        Intent intent=new Intent(getActivity(),SetAccountInfo.class);
                        startActivity(intent);
                    }break;
                    case 1:{
                        //转到个人简历
                        Intent intent=new Intent(getActivity(),SetResume.class);
                        startActivity(intent);
                    }break;
                    case 3:{
                        //转到反馈见面
                    }break;
                }
                return false;
            }
        });
    }

    private void getUserHeadImage(){
        Map<String,String> getUserImage=new LinkedHashMap<>();
        //头像文件名
        //String fileNmae=Object.userObject.getName()+".jpg";
        String fileName="ZJM.jpg";
        getUserImage.put("fileName",fileName);

        QueryInformation.getImage(getUserImage, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                userHeadImage=BitmapAndStringConverter.convertStringToIcon(Object.userImage);

                //设置用户头像和高斯背景
                //头像的大小应为640X640，在选择头像上传时解决
                Bitmap bitmap= BlurBitmap.blurBitmap(userHeadImage,25,getContext());
                final BitmapDrawable bitmapDrawable=new BitmapDrawable(bitmap);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(),"获取头像成功"+Object.userImage.length(),Toast.LENGTH_SHORT).show();
                        rl_user_filed.setBackgroundDrawable(bitmapDrawable);
                        riv_user_head.setImageBitmap(userHeadImage);
                    }
                });
            }

            @Override
            public void onError(final String error) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Toast.makeText(getActivity(),"获取头像失败",Toast.LENGTH_SHORT).show();
                        Log.i("getimageerror","获取头像错误");
                    }
                });

            }
        });
    }
}
