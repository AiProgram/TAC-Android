package com.tac.iparttimejob.UI.GiveAndReceiveJobs;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.tac.iparttimejob.Class.Application;
import com.tac.iparttimejob.Class.RecuitResult;
import com.tac.iparttimejob.UI.Utils.DataType;

import java.util.List;

/**
 * Created by AiProgram on 2016/11/10.
 */

public class MyReceiveJobsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //保存点击事件的监听器,点击事件是用接口配合内部接口传递出去的
    private MyGiveJobAdapter.OnItemClickListener mOnItemClickListener;
    private MyGiveJobAdapter.OnItemLongClickListener mOnItemLongClickListener;

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

    //设置不同ViewHolder的不同布局及信息显示
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    //item总数
    @Override
    public int getItemCount() {
        return 0;
    }

    //根据布局类别创建不同ViewHolder
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
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


    public void setOnItemClickListener(MyGiveJobAdapter.OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemLongClickListener(MyGiveJobAdapter.OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }
}
