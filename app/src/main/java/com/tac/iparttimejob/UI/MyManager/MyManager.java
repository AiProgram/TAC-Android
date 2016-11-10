package com.tac.iparttimejob.UI.MyManager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tac.iparttimejob.Class.Object;
import com.tac.iparttimejob.NetWork.Connect.HttpCallBackListener;
import com.tac.iparttimejob.NetWork.Edit.EditInformation;
import com.tac.iparttimejob.R;
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
    private TextView tv_username;
    private TextView tv_single_info;
    private RelativeLayout rl_user_filed;
    private RelativeLayout rl_my_manager;
    private ExpandableListView elv;
    private MyELVAdapter adapter;

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
        tv_username=(TextView) fragmentView.findViewById(R.id.tv_single_info);
        rl_user_filed=(RelativeLayout) fragmentView.findViewById(R.id.rl_user_filed);
        rl_my_manager=(RelativeLayout) fragmentView.findViewById(R.id.rl_my_manager);
        elv=(ExpandableListView) fragmentView.findViewById(R.id.elv_function_my_manager);


        getUserHeadImage();

        //为ExpandableListVIew设置adapter
        adapter=new MyELVAdapter(this.getContext());
        elv.setAdapter(adapter);

        initELVListener();
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
                    }
                    case 1:{
                        //转到个人简历
                    }
                    case 3:{
                        //转到反馈见面
                    }
                }
                return false;
            }
        });
    }

    private void getUserHeadImage(){
        Map<String,String> getUserImage=new LinkedHashMap<>();
        //头像文件名
        String fileNmae=Object.userObject.getName()+".jpg";
        getUserImage.put("fileName",fileNmae);

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
            public void onError(String error) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(),"获取头像失败",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}
