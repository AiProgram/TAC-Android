package com.tac.iparttimejob.NetWork.Edit;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tac.iparttimejob.Class.EnrollList;
import com.tac.iparttimejob.Class.RecuitResult;
import com.tac.iparttimejob.Class.ReturnMessage;
import com.tac.iparttimejob.Class.UserResult;
import com.tac.iparttimejob.NetWork.Connect.HttpAddress;
import com.tac.iparttimejob.NetWork.Connect.HttpCallBackListener;
import com.tac.iparttimejob.NetWork.Connect.HttpPost;

import java.util.Map;

/**
 * Created by 守候。 on 2016/10/26.
 */

public class EditInformation extends HttpPost {
    public static void setCreatPersonalResume(Map<String,String>params,final HttpCallBackListener listener)
    {
        post(HttpAddress.HOST + HttpAddress.SET_CANCEL_APPLICATION, params, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                ReturnMessage resumeResult=null;
                try {

                    resumeResult=new Gson().fromJson(result,ReturnMessage.class);
                }
                catch (JsonSyntaxException e) {
                    Log.d("gsonErr:",e.toString());
                    //错误
                }
                if(resumeResult!=null)
                {
                    if(resumeResult.isSuccess())
                    {

                        listener.onFinish("创建成功");
                    }
                    else
                    {
                        listener.onError(resumeResult.getMessage());
                    }
                }
                else listener.onError(GSON_ERR);
            }

            @Override
            public void onError(String error) {

                Log.d("postErr:",error);
                listener.onError(error);
            }
        });
    }
    public static void setUpdateRecruit(Map<String,String>params,final HttpCallBackListener listener)
    {
        post(HttpAddress.HOST + HttpAddress.SET_UPDATE_PERSONAL_RESUME, params, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                ReturnMessage resumeResult=null;
                try {

                    resumeResult=new Gson().fromJson(result,ReturnMessage.class);
                }
                catch (JsonSyntaxException e) {
                    Log.d("gsonErr:",e.toString());
                    //错误
                }
                if(resumeResult!=null)
                {
                    if(resumeResult.isSuccess())
                    {

                        listener.onFinish("更新成功");
                    }
                    else
                    {
                        listener.onError(resumeResult.getMessage());
                    }
                }
                else listener.onError(GSON_ERR);
            }

            @Override
            public void onError(String error) {
                Log.d("postErr:",error);
                listener.onError(error);
            }
        });
    }
    public static void setCreatRecuit(Map<String,String>params,final HttpCallBackListener listener)
    {
        post(HttpAddress.HOST + HttpAddress.SET_CREAT_RECRUIT, params, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                ReturnMessage resumeResult=null;
                try {

                    resumeResult=new Gson().fromJson(result,ReturnMessage.class);
                }
                catch (JsonSyntaxException e) {
                    Log.d("gsonErr:",e.toString());
                    //错误
                }
                if(resumeResult!=null)
                {
                    if(resumeResult.isSuccess())
                    {

                        listener.onFinish("创建成功");
                    }
                    else
                    {
                        listener.onError(resumeResult.getMessage());
                    }
                }
                else listener.onError(GSON_ERR);
            }

            @Override
            public void onError(String error) {
                Log.d("postErr:",error);
                listener.onError(error);
            }
        });
    }
    public static void setUserInformation(Map<String,String>params,final HttpCallBackListener listener)
    {
        post(HttpAddress.HOST + HttpAddress.SET_USER_INFORMATION, params, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                ReturnMessage userResult=null;
                try {

                    userResult=new Gson().fromJson(result,ReturnMessage.class);
                }
                catch (JsonSyntaxException e) {
                    Log.d("gsonErr:",e.toString());
                    //错误
                }
                if(userResult!=null)
                {
                    if(userResult.isSuccess())
                    {

                        listener.onFinish("");
                    }
                    else
                    {
                        listener.onError(userResult.getMessage());
                    }
                }
                else listener.onError(GSON_ERR);
            }

            @Override
            public void onError(String error) {

                Log.d("postErr:",error);
                listener.onError(error);
            }
        });
    }
    public static void setRecuitInformation(Map<String,String>params,final HttpCallBackListener listener){
        post(HttpAddress.HOST + HttpAddress.SET_RECRUIT_INFORMATION, params, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                ReturnMessage recuitResult=null;
                try {
                    recuitResult=new Gson().fromJson(result,ReturnMessage.class);
                }
                catch (JsonSyntaxException e) {
                    Log.d("gsonErr:",e.toString());
                    //错误
                }
                if(recuitResult!=null)
                {
                    if(recuitResult.isSuccess())
                    {
                        listener.onFinish("创建成功");
                    }
                    else
                    {
                        listener.onError(recuitResult.getMessage());
                    }
                }
                else listener.onError(GSON_ERR);
            }

            @Override
            public void onError(String error) {
                Log.d("postErr:",error);
                listener.onError(error);
            }
        });


    }
    public static void reSetUserPassword(Map<String,String>params,final HttpCallBackListener listener){
        post(HttpAddress.HOST + HttpAddress.RESETPASSWD, params, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                ReturnMessage userPasswd=null;
                try {
                    userPasswd=new Gson().fromJson(result,ReturnMessage.class);
                }
                catch (JsonSyntaxException e) {
                    Log.d("gsonErr:",e.toString());
                    //错误
                }
                if(userPasswd!=null)
                {
                    if(userPasswd.isSuccess())
                    {
                        listener.onFinish("修改成功");
                    }
                    else
                    {
                        listener.onError(userPasswd.getMessage());
                    }
                }
                else listener.onError(GSON_ERR);
            }

            @Override
            public void onError(String error) {
                Log.d("postErr:",error);
                listener.onError(error);
            }
        });
    }
    //修改招聘状态
    public static void setRecruitStatus(Map<String,String>params,final HttpCallBackListener listener){
        post(HttpAddress.HOST + HttpAddress.SET_RECRUIIT_STATUS, params, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                ReturnMessage status=null;
                try {
                    status=new Gson().fromJson(result,ReturnMessage.class);
                }
                catch (JsonSyntaxException e) {
                    Log.d("gsonErr:",e.toString());
                    //错误
                }
                if(status!=null)
                {
                    if(status.isSuccess())
                    {
                        listener.onFinish("修改成功");
                    }
                    else
                    {
                        listener.onError(status.getMessage());
                    }
                }
                else listener.onError(GSON_ERR);
            }

            @Override
            public void onError(String error) {
                Log.d("postErr:",error);
                listener.onError(error);
            }
        });
    }
    //选择应聘申请
    public static void setChooseEnroll(Map<String,String>params,final HttpCallBackListener listener){
        post(HttpAddress.HOST + HttpAddress.SET_CHOOSE_ENROLL, params, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                ReturnMessage status=null;
                try {
                    status=new Gson().fromJson(result,ReturnMessage.class);
                }
                catch (JsonSyntaxException e) {
                    Log.d("gsonErr:",e.toString());
                    //错误
                }
                if(status!=null)
                {
                    if(status.isSuccess())
                    {

                        listener.onFinish("选择成功");
                    }
                    else
                    {
                        listener.onError(status.getMessage());
                    }
                }
                else listener.onError(GSON_ERR);
            }

            @Override
            public void onError(String error) {
                Log.d("postErr:",error);
                listener.onError(error);
            }
        });
    }


    //申请应聘
    public static void setCreatApplication(Map<String,String>params,final HttpCallBackListener listener){
        post(HttpAddress.HOST + HttpAddress.SET_CREAT_APPLICATION, params, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                ReturnMessage status=null;
                try {
                    status=new Gson().fromJson(result,ReturnMessage.class);
                }
                catch (JsonSyntaxException e) {
                    Log.d("gsonErr:",e.toString());
                    //错误
                }
                if(status!=null)
                {
                    if(status.isSuccess())
                    {
                        listener.onFinish("申请成功");
                    }
                    else
                    {
                        listener.onError(status.getMessage());
                    }
                }
                else listener.onError(GSON_ERR);
            }

            @Override
            public void onError(String error) {
                Log.d("postErr:",error);
                listener.onError(error);
            }
        });
    }
    //取消应聘
    public static void setCancelApplication(Map<String,String>params,final HttpCallBackListener listener){
        post(HttpAddress.HOST + HttpAddress.SET_CANCEL_APPLICATION, params, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                ReturnMessage status=null;
                try {
                    status=new Gson().fromJson(result,ReturnMessage.class);
                }
                catch (JsonSyntaxException e) {
                    Log.d("gsonErr:",e.toString());
                    //错误
                }
                if(status!=null)
                {
                    if(status.isSuccess())
                    {
                        listener.onFinish("取消成功");
                    }
                    else
                    {
                        listener.onError(status.getMessage());
                    }
                }
                else listener.onError(GSON_ERR);
            }

            @Override
            public void onError(String error) {
                Log.d("postErr:",error);
                listener.onError(error);
            }
        });
    }


}
