package com.tac.iparttimejob.UI.GiveAndReceiveJobs;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import com.tac.iparttimejob.Class.RecuitResult;
import com.tac.iparttimejob.R;
import com.tac.iparttimejob.UI.Utils.DataType;



/**
 * Created by AiProgram on 2016/10/29.
 */

/*
这是自定义的RecyclerView的适配器
 */
public class MyGiveJobAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    int listType;
    List<RecuitResult.Recuit> dataList;

    //保存点击事件的监听器,点击事件是用接口配合内部接口传递出去的
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    //传入的是静态对象时本地对象无法与之同步
    public MyGiveJobAdapter(List<RecuitResult.Recuit>dataList){
        this.dataList=dataList;
    }

    /*
    RecyclerView中控制子item的类
    */
    //不同ViewHolder对应不同布局
    public class  CheckingViewHolder extends RecyclerView.ViewHolder{
        private TextView  tv_job_title;
        private TextView tv_job_info;
        private TextView tv_green_label;
        public CheckingViewHolder(View itemView) {
            super(itemView);
            tv_job_title=(TextView) itemView.findViewById(R.id.tv_job_title);
            tv_job_info=(TextView)  itemView.findViewById(R.id.tv_job_info);
            tv_green_label=(TextView) itemView.findViewById(R.id.tv_green_label);
        }

    }
    public class RejectedViewHolder extends RecyclerView.ViewHolder{
        private TextView  tv_job_title;
        private TextView tv_job_info;
        private TextView tv_red_label;

        public RejectedViewHolder(View itemView){
            super(itemView);
            tv_job_title=(TextView) itemView.findViewById(R.id.tv_job_title);
            tv_job_info=(TextView)  itemView.findViewById(R.id.tv_job_info);
            tv_red_label=(TextView) itemView.findViewById(R.id.tv_red_label);
        }
    }
    public class UndergoingViewHolder extends RecyclerView.ViewHolder{
        private TextView  tv_job_title;
        private TextView tv_job_info;
        private TextView tv_deadline;
        public UndergoingViewHolder(View itemView){
            super(itemView);
            tv_job_title=(TextView) itemView.findViewById(R.id.tv_job_title);
            tv_job_info=(TextView)  itemView.findViewById(R.id.tv_job_info);
            tv_deadline=(TextView)  itemView.findViewById(R.id.tv_deadline);
        }
    }
    public class FinshedViewHolder extends RecyclerView.ViewHolder{
        private TextView  tv_job_title;
        private TextView tv_job_info;
        private TextView tv_deadline;
        public FinshedViewHolder(View itemVIew){
            super(itemVIew);
            tv_job_title=(TextView) itemView.findViewById(R.id.tv_job_title);
            tv_job_info=(TextView)  itemView.findViewById(R.id.tv_job_info);
            tv_deadline=(TextView)  itemView.findViewById(R.id.tv_deadline);
        }
    }
    public class CanceledViewHolder extends RecyclerView.ViewHolder{
        private TextView  tv_job_title;
        private TextView tv_job_info;
        private TextView tv_red_label;
        public CanceledViewHolder(View itemVIew){
            super(itemVIew);
            tv_job_title=(TextView) itemView.findViewById(R.id.tv_job_title);
            tv_job_info=(TextView)  itemView.findViewById(R.id.tv_job_info);
            tv_red_label=(TextView) itemView.findViewById(R.id.tv_red_label);
        }
    }


    /*
    返回目前RecyclerView的item个数
    */
    @Override
    public int getItemCount() {
        //判断数据来源
//        if (listType==DataType.VALID_JOB_LIST){
//            if (Object.inRecuitObjectList == null || Object.inRecuitObjectList.size() == 0)
//                return 0;
//            return Object.inRecuitObjectList.size();
//        }else {
//            if (Object.notRecuitObjectList == null || Object.notRecuitObjectList.size() == 0)
//                return 0;
//            return Object.notRecuitObjectList.size();
//        }
        if (dataList == null || dataList.size() == 0)
            return 0;
        return dataList.size();
    }


    /*按位置设置item内容*/
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        //holder.tv_valid_jobs.setText(dataList.get(position));

        int status=getItemViewType(position);
        String jobTitle;
        String jobInfo;
        String deadline;

        //判断数据来源
//        if(listType==DataType.VALID_JOB_LIST) {
//            jobTitle = Object.inRecuitObjectList.get(position).getTitle();
//            jobInfo = Object.inRecuitObjectList.get(position).getWorkInfo();
//            deadline = Object.inRecuitObjectList.get(position).getDealdine();
//        }
//        else {
//            jobTitle = Object.notRecuitObjectList.get(position).getTitle();
//            jobInfo = Object.notRecuitObjectList.get(position).getWorkInfo();
//            deadline = Object.notRecuitObjectList.get(position).getDealdine();
//        }
        jobTitle = dataList.get(position).getTitle();
        jobInfo = dataList.get(position).getWorkInfo();
        deadline = dataList.get(position).getDealdine();

        switch (status){
            case DataType.JOB_STATUS_CHECKING:{
                CheckingViewHolder viewHolder=(CheckingViewHolder) holder;
                viewHolder.tv_green_label.setText("正在审核");
                viewHolder.tv_job_title.setText(jobTitle);
                viewHolder.tv_job_info.setText(jobInfo);
            };break;
            case DataType.JOB_STATUS_REJECTED:{
                RejectedViewHolder viewHolder=(RejectedViewHolder) holder;
                viewHolder.tv_red_label.setText("被驳回");
                viewHolder.tv_job_title.setText(jobTitle);
                viewHolder.tv_job_info.setText(jobInfo);
            };break;
            case DataType.JOB_STATUS_UNDERGONING:{
                UndergoingViewHolder viewHolder=(UndergoingViewHolder) holder;
                viewHolder.tv_job_title.setText(jobTitle);
                viewHolder.tv_job_info.setText(jobInfo);
                viewHolder.tv_deadline.setText(deadline);
            };break;
            case DataType.JOB_STATUS_FINSHED:{
                FinshedViewHolder viewHolder=(FinshedViewHolder) holder;
                viewHolder.tv_deadline.setText(deadline);
                viewHolder.tv_job_title.setText(jobTitle);
                viewHolder.tv_job_info.setText(jobInfo);
            };break;
            case DataType.JOB_STATUS_CANCELED:{
                CanceledViewHolder viewHolder=(CanceledViewHolder) holder;
                viewHolder.tv_red_label.setText("已取消");
                viewHolder.tv_job_title.setText(jobTitle);
                viewHolder.tv_job_info.setText(jobInfo);
            };break;
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

    //设置recyclerView的item布局
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder myViewHolder;

        //根据ViewType传入ViewHolder
        switch (viewType){
            case DataType.JOB_STATUS_CHECKING:{
               myViewHolder=new CheckingViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_job_green,parent,false));
            };break;
            case DataType.JOB_STATUS_REJECTED:{
                myViewHolder=new RejectedViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_job_red,parent,false));
            };break;
            case DataType.JOB_STATUS_UNDERGONING:{
                myViewHolder=new UndergoingViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_valid_job,parent,false));
            };break;
            case DataType.JOB_STATUS_FINSHED:{
                myViewHolder=new FinshedViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_unvalid_job,parent,false));
            };break;
            case DataType.JOB_STATUS_CANCELED:{
                myViewHolder=new CanceledViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_job_red,parent,false));
            };break;
            default:myViewHolder=new UndergoingViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_valid_job,parent,false));
        }
        return myViewHolder;
    }


    @Override
    public int getItemViewType(int position) {
        //返回招聘状态，用于控制不同子项布局
        //判断数据来源
//        if(listType==DataType.VALID_JOB_LIST)
//            return Object.inRecuitObjectList.get(position).getStatus();
//        else
//            return Object.notRecuitObjectList.get(position).getStatus();
        return dataList.get(position).getStatus();
    }

    //用于实现RecyclerView的点击事件的接口
    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    public interface OnItemLongClickListener{
        void onItemLongClick(View view,int position);
    }


    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }
}
