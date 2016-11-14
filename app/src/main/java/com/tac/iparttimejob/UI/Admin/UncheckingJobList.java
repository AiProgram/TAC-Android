package com.tac.iparttimejob.UI.Admin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tac.iparttimejob.Class.RecuitResult;
import com.tac.iparttimejob.R;
import com.tac.iparttimejob.UI.Utils.RefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AiProgram on 2016/11/12.
 */

public class UncheckingJobList extends Fragment{
    private RefreshRecyclerView rv_unchecking_job_list;
    private SwipeRefreshLayout  srl_unchecking_job_list;

    private List<RecuitResult.Recuit> jobList=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_unchecking_job_list,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void getViews(){
        View fragmentView=getView();
        rv_unchecking_job_list=(RefreshRecyclerView) fragmentView.findViewById(R.id.rv_unchecking_job_list);
        srl_unchecking_job_list=(SwipeRefreshLayout) fragmentView.findViewById(R.id.srl_unchecking_job_list);
    }

    private void initViews(){

    }

    private void initListener(){

    }

    private void initData(){

    }

    private void pullDownRefresh(){

    }

    private void pullUpRefresh(){

    }

    private void cloneJobList(){

    }

    private void addJobList(){

    }

    //这里和其他List不同，点击事件单一
    private void itemClicked(){

    }
}

