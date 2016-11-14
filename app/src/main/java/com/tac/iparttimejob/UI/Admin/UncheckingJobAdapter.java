package com.tac.iparttimejob.UI.Admin;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tac.iparttimejob.Class.RecuitResult;
import com.tac.iparttimejob.R;
import com.tac.iparttimejob.UI.Utils.DataType;

import java.util.List;

/**
 * Created by AiProgram on 2016/11/13.
 */

public class UncheckingJobAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    //保存点击事件的监听器,点击事件是用接口配合内部接口传递出去的
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    List<RecuitResult.Recuit> jobList;


    //传入的是静态对象时本地对象无法与之同步,这里list类型不同，加入listType区分
    public UncheckingJobAdapter(List dataList){

        jobList=(List<RecuitResult.Recuit>) dataList;
    }

    //不同 列表项对应不同ViewHolder
    public class JobViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_job_title;
        private TextView tv_job_info;
        private TextView tv_deadline;
        private TextView tv_recruitor_name;

        public JobViewHolder(View itemView) {
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
        String jobTitel;
        String jobInfo;
        String deadline;
        String recruitorName;
        jobTitel=jobList.get(position).getTitle();
        jobInfo=jobList.get(position).getWorkInfo();
        deadline=jobList.get(position).getDealdine();
        recruitorName=jobList.get(position).getOwner();

        JobViewHolder viewHolder=(JobViewHolder) holder;
        viewHolder.tv_job_title.setText(jobTitel);
        viewHolder.tv_job_info.setText(jobInfo);
        viewHolder.tv_deadline.setText(deadline);
        viewHolder.tv_recruitor_name.setText(recruitorName);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onItemClick(holder.itemView,position);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mOnItemLongClickListener.onItemLongClick(holder.itemView,position);
                //拦截事件的继续传递
                return true;
            }
        });
    }

    //item总数
    @Override
    public int getItemCount() {
        return jobList.size();
    }

    //根据布局类别创建不同ViewHolder
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        viewHolder=new JobViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_unchecking_job,parent,false));
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
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }
}
