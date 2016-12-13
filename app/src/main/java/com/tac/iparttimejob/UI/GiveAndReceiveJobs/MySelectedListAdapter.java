package com.tac.iparttimejob.UI.GiveAndReceiveJobs;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.tac.iparttimejob.Class.Enroll;
import com.tac.iparttimejob.R;

import java.util.List;

/**
 * Created by AiProgram on 2016/11/16.
 */

public class MySelectedListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    //保存点击事件的监听器,点击事件是用接口配合内部接口传递出去的
    private onContentClickListener mOnContentClickListener;
    //private  onCheckBoxClickListener mOnCheckBoxClickListener;

    private List<Enroll> selectedList;


    //传入的是静态对象时本地对象无法与之同步,这里list类型不同，加入listType区分
    public MySelectedListAdapter(List dataList){

        selectedList =(List<Enroll>) dataList;
    }

    //不同 列表项对应不同ViewHolder
    public class EnrollViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_applicant_name;
        private TextView tv_assess_point;
        private TextView tv_single_info;
        private CheckBox cb_choose;
        public EnrollViewHolder(View itemView) {
            super(itemView);
            tv_applicant_name=(TextView) itemView.findViewById(R.id.tv_applicant_name);
            tv_assess_point=(TextView)   itemView.findViewById(R.id.tv_assess_point);
            tv_single_info=(TextView)    itemView.findViewById(R.id.tv_single_info);
            cb_choose=(CheckBox)         itemView.findViewById(R.id.cb_choose);
        }
    }

    //设置不同ViewHolder的不同布局及信息显示
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder,final int position) {
        EnrollViewHolder viewHolder=(EnrollViewHolder) holder;
        viewHolder.tv_applicant_name.setText(selectedList.get(position).getApplicantname());
        viewHolder.tv_assess_point.setText(selectedList.get(position).getPoint()+"");
        viewHolder.tv_single_info.setText(selectedList.get(position).getSingleresume());

        viewHolder.cb_choose.setEnabled(false);
        viewHolder.cb_choose.setSelected(true);

        //分别设置内容以及checkbox的点击事件
        viewHolder.tv_applicant_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnContentClickListener.onItemClick(holder.itemView,position);
            }
        });

    }

    //item总数
    @Override
    public int getItemCount() {
        return selectedList.size();
    }

    //根据布局类别创建不同ViewHolder
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        viewHolder=new EnrollViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selected_list,parent,false));
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


    public void setOnContentClickListener(onContentClickListener mOnContentClickListener){
        this.mOnContentClickListener = mOnContentClickListener;
    }

}
