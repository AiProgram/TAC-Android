package com.tac.iparttimejob.UI.MyManager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.util.Base64;
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
import com.tac.iparttimejob.Class.ResumeResult;
import com.tac.iparttimejob.NetWork.Connect.HttpCallBackListener;
import com.tac.iparttimejob.NetWork.Edit.EditInformation;
import com.tac.iparttimejob.R;
import com.tac.iparttimejob.UI.EventBusEvent.SetAccountInfoEvent;
import com.tac.iparttimejob.UI.EventBusEvent.SetResumeEvent;
import com.tac.iparttimejob.UI.GiveAndReceiveJobs.AssessList;
import com.tac.iparttimejob.UI.RegisterAndLogin.Login;
import com.tac.iparttimejob.UI.Utils.BlurBitmap;
import com.tac.iparttimejob.UI.Utils.DataType;
import com.tac.iparttimejob.UI.Utils.RoundImageView;
import com.tac.iparttimejob.NetWork.Query.QueryInformation;
import com.tac.iparttimejob.UI.Utils.BitmapAndStringConverter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.tac.iparttimejob.Class.Object.getResumeObject;
import static com.tac.iparttimejob.Class.Object.image1;
import static com.tac.iparttimejob.Class.Object.userImage;


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

    private Bitmap HeadImage;

    @Override
    public void onDestroy() {
        super.onDestroy();
        //撤销EventBus注册，必须有
        EventBus.getDefault().unregister(this);
    }

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

        btn_exit_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //退出登录
                Intent intent=new Intent(getActivity(),Login.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        //注册EventBUs
        EventBus.getDefault().register(this);
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
                            Intent intent=new Intent(getActivity(), AssessList.class);
                            Bundle bundle=new Bundle();
                            bundle.putInt("type", DataType.COMMENT_O_TO_A);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                        else if(i1==1){
                            //转到应聘者界面
                            Intent intent=new Intent(getActivity(), AssessList.class);
                            Bundle bundle=new Bundle();
                            bundle.putInt("type", DataType.COMMENT_A_TO_O);
                            intent.putExtras(bundle);
                            startActivity(intent);
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
                        getActivity().overridePendingTransition(R.transition.fade_in,R.transition.fade_out);
                    }break;
                    case 1:{
                        //转到个人简历
                        Intent intent=new Intent(getActivity(),SetResume.class);
                        startActivity(intent);
                        getActivity().overridePendingTransition(R.transition.fade_in,R.transition.fade_out);
                    }break;
                    case 3:{
                        //转到反馈见面
                        Intent intent=new Intent(getActivity(),Feedback.class);
                        startActivity(intent);
                        getActivity().overridePendingTransition(R.transition.fade_in,R.transition.fade_out);
                    }break;
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
                userImage=userImage.replaceAll(" ","+");
                HeadImage=BitmapAndStringConverter.convertStringToIcon(userImage);
                //userHeadImage=BitmapAndStringConverter.convertStringToIcon(Object.image1);
                Bitmap  blurBitmap=HeadImage.copy(Bitmap.Config.ARGB_8888,true);
                 blurBitmap= BlurBitmap.blurBitmap(blurBitmap,25,getContext());
                final BitmapDrawable blurDrawabel=new BitmapDrawable(blurBitmap);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(),"获取头像成功",Toast.LENGTH_SHORT).show();
                        rl_user_filed.setBackgroundDrawable(blurDrawabel);
                        riv_user_head.setImageBitmap(HeadImage);
                    }
                });
                Log.i("图片字符串", userImage.length()+"");
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

    //EventBus刷新事件接收函数
    //当修改头像或者账号信息成功后刷新信息
    @Subscribe(threadMode=ThreadMode.MAIN)
    public void onRefreshContent(SetAccountInfoEvent event){
        getUserHeadImage();
    }

    //跟新简历后刷新
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSetResume(SetResumeEvent event){
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
    }
}
