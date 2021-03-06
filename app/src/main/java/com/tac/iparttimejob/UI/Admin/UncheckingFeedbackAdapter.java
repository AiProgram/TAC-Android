package com.tac.iparttimejob.UI.Admin;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tac.iparttimejob.Class.Advice;
import com.tac.iparttimejob.R;

import java.util.List;

/**
 * Created by AiProgram on 2016/11/13.
 */

public class UncheckingFeedbackAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    //保存点击事件的监听器,点击事件是用接口配合内部接口传递出去的
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    List<Advice.suggesstion> adviceList;


    //传入的是静态对象时本地对象无法与之同步,这里list类型不同，加入listType区分
    public  UncheckingFeedbackAdapter(List dataList){

        adviceList=(List<Advice.suggesstion>) dataList;
    }

    //不同 列表项对应不同ViewHolder
    public class AdviceAdapter extends RecyclerView.ViewHolder{
        private TextView tv_username_feedback;
        private TextView tv_phone_feedback;
        private TextView tv_detail_feedback;

        public AdviceAdapter(View itemView) {
            super(itemView);
            tv_username_feedback=(TextView) itemView.findViewById(R.id.tv_username_comment);
            tv_phone_feedback=(TextView)  itemView.findViewById(R.id.tv_point_comment);
            tv_detail_feedback=(TextView)  itemView.findViewById(R.id.tv_detail_feedback);
        }
    }

    //设置不同ViewHolder的不同布局及信息显示
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder,final int position) {
        String username="";
        String phone="";
        String detail="";
        String postTime="";
        username=adviceList.get(position).getUsername();
        phone=adviceList.get(position).getPhone();
        detail=adviceList.get(position).getAdvice();
        postTime=adviceList.get(position).getTime();

        AdviceAdapter viewHolder=(AdviceAdapter) holder;
        viewHolder.tv_username_feedback.setText(username);
        viewHolder.tv_phone_feedback.setText(phone);
        viewHolder.tv_detail_feedback.setText(detail);

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
        return adviceList.size();
    }

    //根据布局类别创建不同ViewHolder
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        viewHolder=new AdviceAdapter(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_unchecking_feedback,parent,false));
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
