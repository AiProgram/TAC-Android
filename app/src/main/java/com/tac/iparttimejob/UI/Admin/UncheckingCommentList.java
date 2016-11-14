package com.tac.iparttimejob.UI.Admin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tac.iparttimejob.Class.Assessment;
import com.tac.iparttimejob.R;
import com.tac.iparttimejob.UI.Utils.RefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AiProgram on 2016/11/12.
 */

public class UncheckingCommentList extends Fragment {
    private RefreshRecyclerView rv_unchecking_comment_list;
    private SwipeRefreshLayout  srl_unchecking_comment_list;
    private TabLayout           tl_check_comments;

    private List<Assessment> OtoAList=new ArrayList<>();
    private List<Assessment> AtoOlist=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_unchecking_comment_list,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getViews();
    }

    private void getViews(){
            View fragmentView=getView();

            rv_unchecking_comment_list=(RefreshRecyclerView) fragmentView.findViewById(R.id.rv_unchecking_comment_list);
            srl_unchecking_comment_list=(SwipeRefreshLayout) fragmentView.findViewById(R.id.srl_unchecking_comment_list);
            tl_check_comments=(TabLayout) fragmentView.findViewById(R.id.tl_check_comments);
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

    private void cloneOtoACommentList(){

    }

    private void cloneAtoOCommentList(){

    }

    private void addOtoACommentList(){

    }

    private void addAtoOCommentList(){

    }

    //这里和其他List不同，点击事件单一
    private void itemClicked(){

    }
}
