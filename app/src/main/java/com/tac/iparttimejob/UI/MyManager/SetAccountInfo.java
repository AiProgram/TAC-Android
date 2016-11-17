package com.tac.iparttimejob.UI.MyManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.tac.iparttimejob.NetWork.Connect.HttpCallBackListener;
import com.tac.iparttimejob.R;
import com.tac.iparttimejob.UI.Utils.ImageUtils;
import com.tac.iparttimejob.Class.Object;
import com.tac.iparttimejob.UI.Utils.BitmapAndStringConverter;
import com.tac.iparttimejob.NetWork.Edit.EditInformation;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import static com.tac.iparttimejob.Class.Object.image1;

/**
 * Created by AiProgram on 2016/11/5.
 */

public class SetAccountInfo extends AppCompatActivity{

    private ImageView iv_user_head_image;
    private Button    btn_upload_image;
    private EditText  et_set_phone;
    private Button    btn_change_password;
    private Button    btn_confirm_change;
    private Button    btn_cancle_change;
    private TextView  tv_account;
    private TextView  tv_email;
    private PopupWindow mPopWindow;

    private String    newPhone;
    private Bitmap    userHeadImage;
    private String    imageString;

    private View.OnClickListener popOnclickListener;

    //相册等操作的常量
    private final static int TAKE_PHOTO_REQUEST = 1;
    private final static int LOCAL_PICS_REQUEST = 2;
    private final static int UPLOAD_PIC_REQUEST = 3;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_set_account_info);

        getViews();

        initListener();

        initViews();
    }

    private void getViews(){
        iv_user_head_image=(ImageView) findViewById(R.id.iv_user_head_image);
        btn_upload_image=(Button) findViewById(R.id.btn_upload_image);
        et_set_phone=(EditText) findViewById(R.id.et_set_phone);
        btn_change_password=(Button) findViewById(R.id.btn_change_password);
        btn_confirm_change=(Button)  findViewById(R.id.btn_confirm_change);
        btn_cancle_change=(Button)   findViewById(R.id.btn_cancle_change);
        tv_account=(TextView)  findViewById(R.id.tv_account);
        tv_email=(TextView)  findViewById(R.id.tv_email);
    }

    private void initListener(){
        btn_upload_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转进入选择裁剪图片页面并获得结果返回
                showPopUpWindow();
            }
        });

        //统一管理弹出按钮的点击事件
        popOnclickListener=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    //点击拍照事件
                    case R.id.btn_take_photo:{
                        Intent photoIn = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(photoIn,TAKE_PHOTO_REQUEST);
                    };break;
                    //点击选自相册事件
                    case R.id.btn_select_from_picture:{
                        Intent picsIn = new Intent(Intent.ACTION_GET_CONTENT);
                        picsIn.setType("image/*");//设置选择的数据类型为图片类型
                        startActivityForResult(picsIn, LOCAL_PICS_REQUEST);
                    };break;
                    //取消上传
                    case R.id.btn_cancel_image_upload:{
                        mPopWindow.dismiss();
                    };break;
                }
            }
        };

        btn_change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //弹出修改密码Dialog
                showPasswordDialog();
            }
        });

        btn_confirm_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //尝试修改信息

            }
        });

        btn_cancle_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //退出
                finish();
            }
        });
    }

    //下方弹出头像选择方式的按钮
    public void showPopUpWindow(){
        //设置contentView
        View contentView = LayoutInflater.from(SetAccountInfo.this).inflate(R.layout.popuplayout_select_image, null);
        mPopWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        //可以获得焦点
        mPopWindow.setFocusable(true);

        //设置各个控件的点击响应
        Button btn_take_photo=(Button) contentView.findViewById(R.id.btn_take_photo);
        Button btn_select_from_picture=(Button) contentView.findViewById(R.id.btn_select_from_picture);
        Button btn_cancel_image_upload=(Button) contentView.findViewById(R.id.btn_cancel_image_upload);

        btn_take_photo.setOnClickListener(popOnclickListener);
        btn_select_from_picture.setOnClickListener(popOnclickListener);
        btn_cancel_image_upload.setOnClickListener(popOnclickListener);

        //显示PopupWindow
        View rootview = LayoutInflater.from(SetAccountInfo.this).inflate(R.layout.layout_set_account_info, null);
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

    //拍照或选择相册后，数据在这里处理
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (null == data) {
            return;
        }
        switch (requestCode) {
            case TAKE_PHOTO_REQUEST: {
                Bundle bundle = data.getExtras();//获取到图片数据
                if (null != bundle) {
                    Bitmap bm = bundle.getParcelable("data");
                    //把图片展示在ImView上
//                    upload_image.setImageBitmap(bm);
                    //对图片剪
                    Uri uri = ImageUtils.saveBitmapToSdCard(bm);
                    startImageCrop(uri);
                }
            }
                break;
            case LOCAL_PICS_REQUEST: {
                Uri uri = data.getData();//从图片的Uri是以cotent://格式开头的
                //获取到图片
                Bitmap bm = ImageUtils.uri2Bitmap(SetAccountInfo.this, uri);
                //把图片展示在ImView上
//                upload_image.setImageBitmap(bm);
                //把拍照的图片保存到本地并转换成文件格式的Uri
                Uri fileUri = ImageUtils.saveBitmapToSdCard(bm);
                //对图片剪
                startImageCrop(fileUri);
            }
                break;
            case UPLOAD_PIC_REQUEST: {
                //把裁剪后的图片展示出来
                Bundle b = data.getExtras();
                userHeadImage = b.getParcelable("data");
                //图片展示出来
                iv_user_head_image.setImageBitmap(userHeadImage);
                imageString = BitmapAndStringConverter.convertIconToString(userHeadImage);
                Log.i("原始字符串",imageString);
                //留待操作上传头像接口
                upLoadUserImgae(imageString);
                break;
            }
        }
    }

    /**
     * @param
     * @description 图片裁剪
     * @time 2016/7/11 10:07
     */
    private void startImageCrop(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");//设置Uri及类型
        intent.putExtra("crop", "true");//
        intent.putExtra("aspectX", 1);//X方向上的比例
        intent.putExtra("aspectY", 1);//Y方向上的比例
        intent.putExtra("outputX", 64);//裁剪区的X方向宽
        intent.putExtra("outputY", 64);//裁剪区的Y方向宽
        intent.putExtra("scale", true);//是否保留比例
        intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
        intent.putExtra("return-data", true);//是否将数据保留在Bitmap中返回dataParcelable相应的Bitmap数据
        startActivityForResult(intent, UPLOAD_PIC_REQUEST);

    }

    //初始化各个控件，包括文字等
    private void initViews(){
        tv_account.setText(Object.userObject.getName());
        tv_email.setText(Object.userObject.getEmail());
    }

    //设置密码采用弹出Dialog的形式
    private void showPasswordDialog(){
        final View dialog=LayoutInflater.from(this).inflate(R.layout.dialog_reset_password,null);

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("修改您的密码");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //确定修改密码事件
                EditText et_reset_password=(EditText) dialog.findViewById(R.id.et_reset_password);
                EditText et_confirm_password=(EditText) dialog.findViewById(R.id.et_confirm_password);
                String password=et_reset_password.getText().toString();
                String confirmedPassword=et_confirm_password.getText().toString();
                if(password.equals(confirmedPassword)){
                    //调用修改密码接口
                }else{
                    //提示两次密码不一致
                    Toast.makeText(SetAccountInfo.this,"两次输入密码不一致",Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //取消修改事件
            }
        });
        //设置Dialog布局
        builder.setView(dialog);
        builder.show();
    }

    //上传头像
    private void upLoadUserImgae(String imageString){
        //头像文件名
        String fileNmae=Object.userObject.getName()+".jpg";

        final Map<String,String> setUserImage=new LinkedHashMap<>();
        setUserImage.put("fileName",fileNmae);
        setUserImage.put("picture",imageString);
        image1=imageString;
        EditInformation.setImage(setUserImage, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(SetAccountInfo.this,"设置成功",Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @Override
            public void onError(String error) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(SetAccountInfo.this,"设置失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
