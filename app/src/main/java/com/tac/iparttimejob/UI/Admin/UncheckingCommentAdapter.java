package com.tac.iparttimejob.UI.Admin;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tac.iparttimejob.Class.AssessmentAtoO;
import com.tac.iparttimejob.Class.AssessmentOtoA;
import com.tac.iparttimejob.UI.Utils.DataType;
import com.tac.iparttimejob.R;

import java.util.List;

/**
 * Created by AiProgram on 2016/11/13.
 */

public class UncheckingCommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //保存点击事件的监听器,点击事件是用接口配合内部接口传递出去的
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    List<AssessmentOtoA> otoAList;
    List<AssessmentAtoO> atoOList;
    int listType;

    //传入的是静态对象时本地对象无法与之同步,这里list类型不同，加入listType区分
    public UncheckingCommentAdapter(List dataList,int listType){
        this.listType=listType;
        if(listType==DataType.COMMENT_O_TO_A)
        otoAList=(List<AssessmentOtoA>) dataList;
        else atoOList=(List<AssessmentAtoO>) dataList;
    }

    //不同 列表项对应不同ViewHolder
    public class AtoOViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_comment_giver;
        private TextView tv_comment_receiver;
        private TextView tv_commen_info;
        public AtoOViewHolder(View itemView) {
            super(itemView);
            tv_comment_giver=(TextView) itemView.findViewById(R.id.tv_job_title);
            tv_comment_receiver=(TextView) itemView.findViewById(R.id.tv_comment_receiver);
            tv_commen_info=(TextView) itemView.findViewById(R.id.tv_job_info);
        }
    }

    public class OtoAViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_comment_giver;
        private TextView tv_comment_receiver;
        private TextView tv_commen_info;
        public OtoAViewHolder(View itemView) {
            super(itemView);
            tv_comment_giver=(TextView) itemView.findViewById(R.id.tv_job_title);
            tv_comment_receiver=(TextView) itemView.findViewById(R.id.tv_comment_receiver);
            tv_commen_info=(TextView) itemView.findViewById(R.id.tv_job_info);
        }
    }

    //设置不同ViewHolder的不同布局及信息显示
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder,final int position) {
        String owner;
        String applicant;
        String commentInfo;
        switch (listType){
            case DataType.COMMENT_A_TO_O:{
                owner=atoOList.get(position).getOnwername();
                applicant=atoOList.get(position).getApplicantname();
                commentInfo=atoOList.get(position).getAtooComment();

                AtoOViewHolder viewHolder=(AtoOViewHolder) holder;
                viewHolder.tv_comment_giver.setText(applicant);
                viewHolder.tv_comment_receiver.setText(owner);
                viewHolder.tv_commen_info.setText(commentInfo);
                viewHolder.tv_comment_receiver.setVisibility(View.GONE);
            }break;
            case DataType.COMMENT_O_TO_A:{
                //OwnerName获取出现问题，暂时这样做
                owner=otoAList.get(position).getApplicantname();
                applicant=otoAList.get(position).getApplicantname();
                commentInfo=otoAList.get(position).getOtoaComment();

                OtoAViewHolder viewHolder=(OtoAViewHolder) holder;
                viewHolder.tv_comment_giver.setText(owner);
                viewHolder.tv_comment_receiver.setText(applicant);
                viewHolder.tv_commen_info.setText(commentInfo);
                viewHolder.tv_comment_receiver.setVisibility(View.GONE);
            }break;
        }
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
        if(listType==DataType.COMMENT_O_TO_A)
            return otoAList.size();
        else
            return atoOList.size();
    }

    //根据布局类别创建不同ViewHolder
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        switch (listType){
            case DataType.COMMENT_A_TO_O:{
                viewHolder=new AtoOViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_unchecking_comment,parent,false));
            }break;
            case DataType.COMMENT_O_TO_A:{
                viewHolder=new OtoAViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_unchecking_comment,parent,false));
            }break;
            default:
                viewHolder=new AtoOViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_unchecking_comment,parent,false));
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
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }
}
