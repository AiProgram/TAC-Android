package com.tac.iparttimejob.UI.Utils;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import com.tac.iparttimejob.R;



/**
 * Created by AiProgram on 2016/10/29.
 */

/*
这是自定义的RecyclerView的适配器
 */
public class MyGiveJobAdapter extends RecyclerView.Adapter<MyGiveJobAdapter.MyViewHolder>{

    //RecyclerView的数组储存数组
    private List<String>dataList;

    public MyGiveJobAdapter(List<String>dataList){
        this.dataList=dataList;
    }

    /*
    RecyclerView中控制子item的类
    */
    public class  MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_valid_jobs;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_valid_jobs=(TextView)itemView.findViewById(R.id.tv_valid_jobs);
        }

    }

    /*
    返回目前RecyclerView的item个数
    */
    @Override
    public int getItemCount() {
        if(dataList==null||dataList.size()==0)
            return 0;
        return dataList.size();
    }


    /*按位置设置item内容*/
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_valid_jobs.setText(dataList.get(position));
    }

    //设置recyclerView的item布局
    public MyGiveJobAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder=new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_valid_job,parent,false));
        return myViewHolder;
    }

}
