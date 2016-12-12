package com.tac.iparttimejob.UI.GiveAndReceiveJobs;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tac.iparttimejob.Class.AssessmentAtoO;
import com.tac.iparttimejob.Class.AssessmentOtoA;
import com.tac.iparttimejob.R;
import com.tac.iparttimejob.UI.Utils.DataType;

import java.util.List;

/**
 * Created by 守候。 on 2016/11/12.
 */
//对应类
public class AssessAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    //保存点击事件的监听器,点击事件是用接口配合内部接口传递出去的
    private OnContentClickListener mOnContentClicListener;
    private OnSueClickListener mOnSueClickListener;

    List<AssessmentOtoA> otoAList;
    List<AssessmentAtoO> atoOList;
    private int type;


    //传入的是静态对象时本地对象无法与之同步,这里list类型不同，加入listType区分
    public AssessAdapter(int type,List dataList){
        this.type=type;
        if(type==DataType.COMMENT_A_TO_O)
            atoOList=(List<AssessmentAtoO>) dataList;
        else
            otoAList=(List<AssessmentOtoA>) dataList;
    }

    //不同 列表项对应不同ViewHolder
    public class AssessmentViewHolder extends RecyclerView.ViewHolder{
        TextView tv_username_comment;
        TextView tv_point_comment;
        TextView tv_content_comment;
        TextView tv_sue_comment;
        public AssessmentViewHolder(View itemView) {
            super(itemView);
            tv_username_comment=(TextView) itemView.findViewById(R.id.tv_username_comment);
            tv_point_comment=(TextView) itemView.findViewById(R.id.tv_point_comment);
            tv_content_comment=(TextView) itemView.findViewById(R.id.tv_content_comment);
            tv_sue_comment=(TextView) itemView.findViewById(R.id.tv_sue_comment);
        }
    }

    //设置不同ViewHolder的不同布局及信息显示
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder,final int position) {
        String username;
        String point;
        String content;

        switch (type){
            case DataType.COMMENT_A_TO_O:{
                username=atoOList.get(position).getApplicantname();
                point=atoOList.get(position).getTac_applicants().getPoint()+"";
                content=atoOList.get(position).getAtooComment();
            }break;
            case DataType.COMMENT_O_TO_A:{
                username=otoAList.get(position).getOnwername();
                point=otoAList.get(position).getTac_applicants().getPoint()+"";
                content=otoAList.get(position).getOtoaComment();
            }break;
            default:{
                username=atoOList.get(position).getApplicantname();
                point=atoOList.get(position).getTac_applicants().getPoint()+"";
                content=atoOList.get(position).getAtooComment();
            }
        }

        AssessmentViewHolder viewHolder=(AssessmentViewHolder) holder;
        viewHolder.tv_username_comment.setText(username);
        viewHolder.tv_content_comment.setText(content);
        viewHolder.tv_point_comment.setText(point);


        viewHolder.tv_username_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnContentClicListener.onContentClick(holder.itemView,position);
            }
        });

        viewHolder.tv_sue_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnSueClickListener.onSueClick(holder.itemView,position);
            }
        });
    }

    //item总数
    @Override
    public int getItemCount() {
        if(type==DataType.COMMENT_A_TO_O)
        return atoOList.size();
        else
            return otoAList.size();
    }

    //根据布局类别创建不同ViewHolder
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        viewHolder=new AssessmentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment,parent,false));
        return viewHolder;
    }

    //区分使用哪一种item布局
    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    //用于实现RecyclerView的点击事件的接口

    public interface OnContentClickListener{
        void onContentClick(View view,int position);
    }

    public interface OnSueClickListener{
        void onSueClick(View view,int position);
    }


    public void setOnContentClickListener(OnContentClickListener mOnContentClicListener){
        this.mOnContentClicListener=mOnContentClicListener;
    }

    public void setOnSueClickListener(OnSueClickListener mOnSueClickListener){
        this.mOnSueClickListener=mOnSueClickListener;
    }

}
