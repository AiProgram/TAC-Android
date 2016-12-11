package com.tac.iparttimejob.UI.MyManager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tac.iparttimejob.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by AiProgram on 2016/10/31.
 */

public class MyELVAdapter extends BaseExpandableListAdapter {
    public void test(){
    }

    //储存所有功能的建以及相互的附属关系
    List <ManagerFunction> functionTitle =new ArrayList<>();
    //储存所有的功能对应所需要数据
    HashMap <String,List<ManagerFunction>> functionChild=new HashMap<String,List<ManagerFunction>>();
    private Context mContext;

    public MyELVAdapter(Context context){
        ManagerFunctionGeter managerFunctionGeter=new ManagerFunctionGeter();
        functionTitle=managerFunctionGeter.getFunctionTitle();
        functionChild=managerFunctionGeter.getFunctionChild();
        mContext=context;
        test();
    }

    //获得子项View
    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        //		String data = this.expandableListDetail.get(this.expandableListTitle.get(groupPosition)).get(childPosition);
        ManagerFunction function = (ManagerFunction) this.getChild(i, i1);
        String functionName=function.functionName;
        int id=function.iconID;
        if(view == null)
        {
            view = View.inflate(mContext, R.layout.item_group_my_manager, null);
        }
        TextView tv = (TextView) view.findViewById(R.id.tv_functionNmae);
        tv.setText(functionName);
        ImageView iv=(ImageView) view.findViewById(R.id.iv_function_icon);
        iv.setImageResource(id);
        return view;
    }

    //子项是否可选
    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    //父项数量
    @Override
    public int getGroupCount() {
        return functionTitle.size();
    }

    //子项数量
    @Override
    public int getChildrenCount(int i) {
         return functionChild.get(functionTitle.get(i).functionName).size();
    }

    //一般为否
    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public Object getGroup(int i) {
        return functionTitle.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return functionChild.get(functionTitle.get(i).functionName).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        ManagerFunction function = (ManagerFunction) this.getGroup(i);
        String functionName=function.functionName;
        int id=function.iconID;
        if(view == null)
        {
            view = View.inflate(mContext, R.layout.item_group_my_manager, null);
        }
        TextView tv = (TextView) view.findViewById(R.id.tv_functionNmae);
        tv.setText(functionName);
        ImageView iv=(ImageView) view.findViewById(R.id.iv_function_icon);
        iv.setImageResource(id);
        return view;
    }

}
