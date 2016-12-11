package com.tac.iparttimejob.UI.MyManager;


import com.tac.iparttimejob.UI.MyManager.ManagerFunction;
import com.tac.iparttimejob.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by AiProgram on 2016/10/31.
 */
//用于提供给ExpandaListView数据的类
public class ManagerFunctionGeter {

    //储存所有功能的建以及相互的附属关系
     List <ManagerFunction> functionTitle =new ArrayList<>();
    //储存所有的功能对应所需要数据
     HashMap <String,List<ManagerFunction>> functionChild=new HashMap<>();

    public ManagerFunctionGeter(){
        initData();
    }

    public void initData(){
        int iconId;

        iconId=R.drawable.ic_account_circle_black_24dp;
        ManagerFunction fAccountInfo=new ManagerFunction(iconId,"账号信息");
        List<ManagerFunction>  fAIChild=new ArrayList<>();
        functionTitle.add(fAccountInfo);
        functionChild.put("账号信息",fAIChild);

        iconId=R.drawable.ic_description_black_24dp;
        ManagerFunction fResume=new ManagerFunction(iconId,"个人简历");
        List<ManagerFunction>  fRChild=new ArrayList<>();
        functionTitle.add(fResume);
        functionChild.put("个人简历",fRChild);

        iconId=R.drawable.ic_comment_black_24dp;
        ManagerFunction fComments=new ManagerFunction(iconId,"对我的评价");
        List<ManagerFunction>  fCChild=new ArrayList<>();
        ManagerFunction fComment_1=new ManagerFunction(iconId,"招聘者的评价");
        fCChild.add(fComment_1);
        ManagerFunction fComment_2=new ManagerFunction(iconId,"应聘者的评价");
        fCChild.add(fComment_2);
        functionTitle.add(fComments);
        functionChild.put("对我的评价",fCChild);

        iconId=R.drawable.ic_feedback_black_24dp;
        ManagerFunction fFeedback=new ManagerFunction(iconId,"反馈");
        List<ManagerFunction>  fFChild=new ArrayList<>();
        functionTitle.add(fFeedback);
        functionChild.put("反馈",fFChild);
    }

    public List<ManagerFunction> getFunctionTitle() {
        return functionTitle;
    }

    public HashMap<String, List<ManagerFunction>> getFunctionChild() {
        return functionChild;
    }
}
