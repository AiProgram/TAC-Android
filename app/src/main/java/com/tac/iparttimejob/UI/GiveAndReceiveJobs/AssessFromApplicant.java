package com.tac.iparttimejob.UI.GiveAndReceiveJobs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tac.iparttimejob.Class.Assessment;
import com.tac.iparttimejob.Class.Object;
import com.tac.iparttimejob.NetWork.Connect.HttpCallBackListener;
import com.tac.iparttimejob.R;
import com.tac.iparttimejob.UI.Utils.RefreshRecyclerView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.tac.iparttimejob.NetWork.Query.QueryInformation;

import static com.tac.iparttimejob.Class.Object.atooCommentObjectList;

/**
 * Created by 守候。 on 2016/11/12.
 * 来自应聘者的评价
 */
//这里是查看评价 剩下不会了
public class AssessFromApplicant extends Fragment {
    private RefreshRecyclerView rv_applicant_assessment;
    private TextView textView_applicant_assessment;
    private AssessAdapter assessAdapter;
    List<Assessment> assessmentList=new ArrayList<>();
    int page=1;
    int rows=10;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_applicant_assess,container,false);
        return view;
    }
    @Override
    /*耗时初始化操作放在这里*/
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();

    }

    private void initView() {
        View fragmentView = getView();
        rv_applicant_assessment = (RefreshRecyclerView) fragmentView.findViewById(R.id.rv_comments_from_workers);
        textView_applicant_assessment=(TextView)fragmentView.findViewById(R.id.assess_title);
        assessAdapter=new AssessAdapter(assessmentList);



    }
    private void initData(){
        atooCommentObjectList=new ArrayList<>();
        assessmentList.clear();
        refresh();
    }
    public void refresh(){
        Map<String,String> getList=new LinkedHashMap<>();
        getList.put("userid", Object.loginObject.getUserid());
        getList.put("page",(page)+"");
        getList.put("rows",(rows)+"");
        QueryInformation.getAtooComment(getList, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                cloneList();
                rv_applicant_assessment.notifyData();
            }

            @Override
            public void onError(String error) {
                    Log.d("refreshErr:",error);

            }
        });
    }
    public void cloneList(){
        assessmentList.clear();
        for(int i = 0; i< atooCommentObjectList.size(); i++)
            assessmentList.add(atooCommentObjectList.get(i));
    }
}
