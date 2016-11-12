package com.tac.iparttimejob.UI.GiveAndReceiveJobs;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tac.iparttimejob.Class.RecuitResult;
import com.tac.iparttimejob.Class.Assessment;
import com.tac.iparttimejob.R;

import java.util.List;

/**
 * Created by 守候。 on 2016/11/12.
 */

public class AssessAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    public  AssessAdapter(List<Assessment> dataList) {
        this.dataList = dataList;
    }
    /*
    RecyclerView中控制子item的类
    */
    //不同ViewHolder对应不同布局
    public class  AssessmentViewHolder extends RecyclerView.ViewHolder{
        private TextView user;
        private TextView assessment;
        private TextView point;
        public AssessmentViewHolder(View itemView) {
            super(itemView);
            user=(TextView) itemView.findViewById(R.id.user1);
            assessment=(TextView)  itemView.findViewById(R.id.uer1_assess);
            point=(TextView) itemView.findViewById(R.id.uer1_score_num);
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
    private List<Assessment>  dataList;
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        int status=getItemViewType(position);
        int user;
        String assessment;
        float point;
        user = dataList.get(position).getCommentid();
        assessment = dataList.get(position).getAtooComment();
        point = dataList.get(position).getAtooPoint();

    }
    //设置recyclerView的item布局
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder myViewHolder;
        myViewHolder=new AssessmentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment,parent,false));
        return myViewHolder;
    }

}
