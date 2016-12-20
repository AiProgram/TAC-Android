package com.tac.iparttimejob.UI.GiveAndReceiveJobs;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.tac.iparttimejob.Class.Enroll;
import com.tac.iparttimejob.Class.Object;
import com.tac.iparttimejob.NetWork.Connect.HttpCallBackListener;
import com.tac.iparttimejob.NetWork.Edit.EditInformation;
import com.tac.iparttimejob.NetWork.Query.QueryInformation;
import com.tac.iparttimejob.R;
import com.tac.iparttimejob.UI.Utils.BitmapAndStringConverter;
import com.tac.iparttimejob.UI.Utils.BlurBitmap;
import com.tac.iparttimejob.UI.Utils.DataType;
import com.tac.iparttimejob.UI.Utils.RoundImageView;

import org.w3c.dom.Text;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.tac.iparttimejob.Class.Object.userImage;

/**
 * Created by AiProgram on 2016/11/15.
 */

public class MyEnrollListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    //保存点击事件的监听器,点击事件是用接口配合内部接口传递出去的
    private onContentClickListener mOnContentClickListener;
    private  onCheckBoxClickListener mOnCheckBoxClickListener;
    private onCheckedChangeListener mOnCheckedChangeListener;

    private List<Enroll> enrollList;
    private Context context;


    //传入的是静态对象时本地对象无法与之同步,这里list类型不同，加入listType区分
    public MyEnrollListAdapter(List dataList, Context context){
        enrollList =(List<Enroll>) dataList;
        this.context=context;
    }

    //不同 列表项对应不同ViewHolder
    public class EnrollViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_applicant_name;
        private TextView tv_assess_point;
        private CheckBox cb_choose;
        private RoundImageView iv_user_enroll_list;
        public EnrollViewHolder(View itemView) {
            super(itemView);
            tv_applicant_name=(TextView) itemView.findViewById(R.id.tv_applicant_name);
            tv_assess_point=(TextView)   itemView.findViewById(R.id.tv_assess_point);
            cb_choose=(CheckBox)         itemView.findViewById(R.id.cb_choose);
            iv_user_enroll_list=(RoundImageView) itemView.findViewById(R.id.iv_user_enroll_list);
        }
    }

    //设置不同ViewHolder的不同布局及信息显示
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder,final int position) {
        final EnrollViewHolder viewHolder=(EnrollViewHolder) holder;
        viewHolder.tv_applicant_name.setText(enrollList.get(position).getApplicantname());
        viewHolder.tv_assess_point.setText(enrollList.get(position).getPoint()+"");
        getUserHeadImage((EnrollViewHolder) holder,position);

        //如果之前 已经被选中就设置为选中
        if(enrollList.get(position).getChoosen()== DataType.ENROLL_STATUS_SELECTED)
            viewHolder.cb_choose.setChecked(true);
        if (enrollList.get(position).getChoosen()==DataType.ENROLL_STATUS_CANCELED) {
            viewHolder.cb_choose.setEnabled(false);
            viewHolder.cb_choose.setText("已失效");
        }
        Log.i("申请列表", enrollList.get(position).getChoosen()+" "+enrollList.get(position).getApplicantname());

        //分别设置内容以及checkbox的点击事件
        viewHolder.tv_applicant_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnContentClickListener.onItemClick(holder.itemView,position);
            }
        });

        //暂时无用
        viewHolder.cb_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnCheckBoxClickListener.onItemClick(holder.itemView,position);
            }
        });

        viewHolder.cb_choose.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mOnCheckedChangeListener.onCheckedChange(compoundButton,b,position);
            }
        });
    }

    //item总数
    @Override
    public int getItemCount() {
        return enrollList.size();
    }

    //根据布局类别创建不同ViewHolder
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        viewHolder=new EnrollViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_enroll_list,parent,false));
        return viewHolder;
    }

    //区分使用哪一种item布局
    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    //用于实现RecyclerView的点击事件的接口
    public interface onContentClickListener{
        void onItemClick(View view, int position);
    }

    public interface onCheckBoxClickListener{
        void onItemClick(View view,int position);
    }

    public interface onCheckedChangeListener{
        void onCheckedChange(CompoundButton compoundButton, boolean b,int position);
    }

    public void setOnContentClickListener(onContentClickListener mOnContentClickListener){
        this.mOnContentClickListener = mOnContentClickListener;
    }

    public void setOnCheckBoxClickListener(onCheckBoxClickListener mOnCheckBoxClickListener) {
        this.mOnCheckBoxClickListener = mOnCheckBoxClickListener;
    }

    public void setOnCheckedChangeListener(onCheckedChangeListener mOnCheckedChangedListener){
        this.mOnCheckedChangeListener=mOnCheckedChangedListener;
    }

    //获取用户头像
    private void getUserHeadImage(final EnrollViewHolder viewHolder, int position){
        Map<String,String> getUserImage=new LinkedHashMap<>();
        //头像文件名
        String fileNmae=enrollList.get(position).getApplicantname()+".jpg";
        getUserImage.put("fileName",fileNmae);

        QueryInformation.getImage(getUserImage, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                userImage=userImage.replaceAll(" ","+");
                final Bitmap HeadImage= BitmapAndStringConverter.convertStringToIcon(userImage);
                //userHeadImage=BitmapAndStringConverter.convertStringToIcon(Object.image1);
                Bitmap blurBitmap=HeadImage.copy(Bitmap.Config.ARGB_8888,true);
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context,"获取头像成功",Toast.LENGTH_SHORT).show();
                        viewHolder.iv_user_enroll_list.setImageBitmap(HeadImage);
                        Log.i("图片字符串", userImage.length()+"");
                    }
                });
            }

            @Override
            public void onError(final String error) {
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context,"获取头像失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
