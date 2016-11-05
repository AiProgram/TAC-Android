package com.tac.iparttimejob.UI.MyManager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.tac.iparttimejob.R;
import com.tac.iparttimejob.UI.Utils.ImageUtils;

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
    private PopupWindow mPopWindow;

    private String    newPhone;

    private View.OnClickListener popOnclickListener;

    //相册等操作的常量
    private final static int TAKE_PHOTO_REQUEST = 1;
    private final static int LOCAL_PICS_REQUEST = 2;
    private final static int UPLOAD_PIC_REQUEST = 3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_set_account_info);

        getViews();

        initListener();

    }

    private void getViews(){
        iv_user_head_image=(ImageView) findViewById(R.id.iv_user_head_image);
        btn_upload_image=(Button) findViewById(R.id.btn_upload_image);
        et_set_phone=(EditText) findViewById(R.id.et_set_phone);
        btn_change_password=(Button) findViewById(R.id.btn_change_password);
        btn_confirm_change=(Button)  findViewById(R.id.btn_confirm_change);
        btn_cancle_change=(Button)   findViewById(R.id.btn_cancle_change);
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
            case TAKE_PHOTO_REQUEST:
                Bundle bundle = data.getExtras();//获取到图片数据
                if (null != bundle) {
                    Bitmap bm = bundle.getParcelable("data");
                    //把图片展示在ImView上
//                    upload_image.setImageBitmap(bm);
                    //对图片剪
                    Uri uri = ImageUtils.saveBitmapToSdCard(bm);
                    startImageCrop(uri);
                }
                break;
            case LOCAL_PICS_REQUEST:
                Uri uri = data.getData();//从图片的Uri是以cotent://格式开头的
                //获取到图片
                Bitmap bm = ImageUtils.uri2Bitmap(SetAccountInfo.this, uri);
                //把图片展示在ImView上
//                upload_image.setImageBitmap(bm);
                //把拍照的图片保存到本地并转换成文件格式的Uri
                Uri fileUri = ImageUtils.saveBitmapToSdCard(bm);
                //对图片剪
                startImageCrop(fileUri);
                break;
            case UPLOAD_PIC_REQUEST:
                //把裁剪后的图片展示出来
                Bundle b = data.getExtras();
                Bitmap bitmap = b.getParcelable("data");
                //图片展示出来
                iv_user_head_image.setImageBitmap(bitmap);
                break;
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
        intent.putExtra("outputX", 480);//裁剪区的X方向宽
        intent.putExtra("outputY", 480);//裁剪区的Y方向宽
        intent.putExtra("scale", true);//是否保留比例
        intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
        intent.putExtra("return-data", true);//是否将数据保留在Bitmap中返回dataParcelable相应的Bitmap数据
        startActivityForResult(intent, UPLOAD_PIC_REQUEST);

    }
}
