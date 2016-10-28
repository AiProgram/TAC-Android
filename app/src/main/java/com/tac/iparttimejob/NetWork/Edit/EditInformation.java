package com.tac.iparttimejob.NetWork.Edit;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tac.iparttimejob.Class.RecuitResult;
import com.tac.iparttimejob.Class.ResumeResult;
import com.tac.iparttimejob.Class.UserResult;
import com.tac.iparttimejob.NetWork.Connect.HttpAddress;
import com.tac.iparttimejob.NetWork.Connect.HttpCallBackListener;
import com.tac.iparttimejob.NetWork.Connect.HttpPost;

import java.util.Map;

/**
 * Created by 守候。 on 2016/10/26.
 */

public class EditInformation extends HttpPost {
    public static void setPersonalResume(Map<String,String>params,final HttpCallBackListener listener)
    {
        post(HttpAddress.HOST + HttpAddress.SET_PERSONAL_RESUME, params, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                ResumeResult resumeResult=null;
                try {

                    resumeResult=new Gson().fromJson(result,ResumeResult.class);
                }
                catch (JsonSyntaxException e) {
                    //错误
                }
                if(resumeResult!=null)
                {
                    if(resumeResult.isSuccess())
                    {

                        listener.onFinish("");
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
                listener.onError(error);
            }
        });
    }
    public static void setUserInformation(Map<String,String>params,final HttpCallBackListener listener)
    {
        post(HttpAddress.HOST + HttpAddress.SET_USER_INFORMATION, params, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                UserResult userResult=null;
                try {

                    userResult=new Gson().fromJson(result,UserResult.class);
                }
                catch (JsonSyntaxException e) {
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
                listener.onError(error);
            }
        });
    }
    public static void setRecuitInformation(Map<String,String>params,final HttpCallBackListener listener){
        post(HttpAddress.HOST + HttpAddress.SET_RECUIT_INFORMATION, params, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                RecuitResult recuitResult=null;
                try {
                    recuitResult=new Gson().fromJson(result,RecuitResult.class);
                }
                catch (JsonSyntaxException e) {
                    //错误
                }
                if(recuitResult!=null)
                {
                    if(recuitResult.isSuccess())
                    {
                        listener.onFinish("");
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
                listener.onError(error);
            }
        });


    }

}
