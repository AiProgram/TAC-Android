package com.tac.iparttimejob.UI.MyManager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tac.iparttimejob.R;
import com.tac.iparttimejob.UI.Utils.BlurBitmap;
import com.tac.iparttimejob.UI.Utils.RoundImageView;


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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_my_manager,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View fragmentView=getView();
        riv_user_head=(RoundImageView) fragmentView.findViewById(R.id.riv_user_head);
        tv_single_info=(TextView) fragmentView.findViewById(R.id.tv_single_info);
        tv_username=(TextView) fragmentView.findViewById(R.id.tv_single_info);
        rl_user_filed=(RelativeLayout) fragmentView.findViewById(R.id.rl_user_filed);
        rl_my_manager=(RelativeLayout) fragmentView.findViewById(R.id.rl_my_manager);
        elv=(ExpandableListView) fragmentView.findViewById(R.id.elv_function_my_manager);

        //设置用户头像和高斯背景
        //头像的大小应为640X640，在选择头像上传时解决
        Bitmap bitmap = BitmapFactory.decodeResource((getActivity()).getResources(), R.drawable.user_head_image);
        bitmap= BlurBitmap.blurBitmap(bitmap,25,getContext());
        BitmapDrawable bitmapDrawable=new BitmapDrawable(bitmap);
       // bitmapDrawable.setTileModeY( Shader.TileMode.CLAMP);
        rl_user_filed.setBackgroundDrawable(bitmapDrawable);

        adapter=new MyELVAdapter(this.getContext());
        elv.setAdapter(adapter);

    }
}
