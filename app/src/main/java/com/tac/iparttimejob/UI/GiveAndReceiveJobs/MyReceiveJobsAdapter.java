package com.tac.iparttimejob.UI.GiveAndReceiveJobs;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tac.iparttimejob.Class.Application;
import com.tac.iparttimejob.Class.RecuitResult;
import com.tac.iparttimejob.R;
import com.tac.iparttimejob.UI.Utils.DataType;

import java.util.List;

/**
 * Created by AiProgram on 2016/11/10.
 */

public class MyReceiveJobsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //保存点击事件的监听器,点击事件是用接口配合内部接口传递出去的
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    List<RecuitResult.Recuit> unsignedList;
    List<Application> signedList;
    int listType;

    //传入的是静态对象时本地对象无法与之同步,这里list类型不同，加入listType区分
    public MyReceiveJobsAdapter(List dataList,int listType){
        this.listType=listType;
        switch (listType){
            case DataType.UNSIGNED_JOB_LIST:{
                unsignedList=(List<RecuitResult.Recuit>) dataList;
            }break;
            case DataType.SIGNED_JOB_LIST:{
                signedList=(List<Application>) dataList;
            }break;
            default:
                unsignedList=(List<RecuitResult.Recuit>) dataList;
        }
    }

    //不同 列表项对应不同ViewHolder
    public class UnsignedViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_job_title;
        private TextView  tv_job_info;
        private TextView tv_deadline;
        private TextView tv_recruitor_name;
        public UnsignedViewHolder(View itemView) {
            super(itemView);
            tv_job_title=(TextView) itemView.findViewById(R.id.tv_job_title);
            tv_job_info=(TextView)  itemView.findViewById(R.id.tv_job_info);
            tv_deadline=(TextView)  itemView.findViewById(R.id.tv_deadline);
            tv_recruitor_name=(TextView) itemView.findViewById(R.id.tv_recruitor_name);
        }
    }

    public class SignedViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_job_title;
        private TextView  tv_job_info;
        private TextView tv_deadline;
        private TextView tv_recruitor_name;
        public SignedViewHolder(View itemView) {
            super(itemView);
            tv_job_title=(TextView) itemView.findViewById(R.id.tv_job_title);
            tv_job_info=(TextView)  itemView.findViewById(R.id.tv_job_info);
            tv_deadline=(TextView)  itemView.findViewById(R.id.tv_deadline);
            tv_recruitor_name=(TextView) itemView.findViewById(R.id.tv_recruitor_name);
        }
    }

    //设置不同ViewHolder的不同布局及信息显示
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder,final int position) {
        String jobTitle;
        String jobInfo;
        String deadline;
        String recruitorName;
        switch (listType){
            case DataType.SIGNED_JOB_LIST:{
                jobTitle=signedList.get(position).getTitle();
                jobInfo=signedList.get(position).getTac_recruit().getWorkInfo();
                deadline=signedList.get(position).getTac_recruit().getDealdine();
                recruitorName=signedList.get(position).getWonername();
                SignedViewHolder viewHolder=(SignedViewHolder)holder;
                viewHolder.tv_job_title.setText(jobTitle);
                viewHolder.tv_job_info.setText(jobInfo);
                viewHolder.tv_deadline.setText(deadline);
                viewHolder.tv_recruitor_name.setText(recruitorName);
            }break;
            case DataType.UNSIGNED_JOB_LIST:{
                jobTitle=unsignedList.get(position).getTitle();
                jobInfo=unsignedList.get(position).getWorkInfo();
                deadline=unsignedList.get(position).getDealdine();
                recruitorName=unsignedList.get(position).getOwner();
                UnsignedViewHolder viewHolder=(UnsignedViewHolder) holder;
                viewHolder.tv_job_title.setText(jobTitle);
                viewHolder.tv_job_info.setText(jobInfo);
                viewHolder.tv_deadline.setText(deadline);
                viewHolder.tv_recruitor_name.setText(recruitorName);
            }break;
        }
        //绑定是绑定点击监听器
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position =holder.getLayoutPosition();
                mOnItemClickListener.onItemClick(holder.itemView,position);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                int postion=holder.getLayoutPosition();
                mOnItemLongClickListener.onItemLongClick(holder.itemView,position);
                //拦截事件的继续传递
                return true;
            }
        });
    }

    //item总数
    @Override
    public int getItemCount() {
        int size=0;
        switch (listType){
            case DataType.SIGNED_JOB_LIST:{
                size=signedList.size();
            }break;
            case DataType.UNSIGNED_JOB_LIST:{
                size=unsignedList.size();
            }break;
        }
        return size;
    }

    //根据布局类别创建不同ViewHolder
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        switch (listType){
            case DataType.SIGNED_JOB_LIST:{
                viewHolder=new SignedViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_job_from_recruitor,parent,false));
            }break;
            case DataType.UNSIGNED_JOB_LIST:{
                viewHolder=new UnsignedViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_job_from_recruitor,parent,false));
            }break;
            default:
                viewHolder=new UnsignedViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_job_from_recruitor,parent,false));
        }
        return viewHolder;
    }

    //区分使用哪一种item布局
    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    //用于实现RecyclerView的点击事件的接口
    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener{
        void onItemLongClick(View view,int position);
    }


    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener) {
       this. mOnItemLongClickListener = mOnItemLongClickListener;
    }
}
