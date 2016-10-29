package com.tac.iparttimejob.NetWork.Query;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tac.iparttimejob.Class.EmailResult;
import com.tac.iparttimejob.Class.RecuitResult;
import com.tac.iparttimejob.Class.ResumeResult;
import com.tac.iparttimejob.Class.UserResult;
import com.tac.iparttimejob.NetWork.Connect.HttpAddress;
import com.tac.iparttimejob.NetWork.Connect.HttpCallBackListener;
import com.tac.iparttimejob.NetWork.Connect.HttpPost;

import java.util.Map;

import static com.tac.iparttimejob.Class.Object.emailData;
import static com.tac.iparttimejob.Class.Object.recuitObject;
import static com.tac.iparttimejob.Class.Object.resumeObject;
import static com.tac.iparttimejob.Class.Object.userObject;

/**
 * Created by 守候。 on 2016/10/26.
 */

public class QueryInformation extends HttpPost{
    public static void getPersonalResume(Map<String,String> params, final HttpCallBackListener listener)
    {
        post(HttpAddress.HOST + HttpAddress.GET_PERSONAL_RESUME, params, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                ResumeResult resumeResult=null;
                ResumeResult.Resume resume=null;
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
                        try {

                            resume=new Gson().fromJson(result,ResumeResult.Resume.class);

                        }
                        catch (JsonSyntaxException e) {
                            //错误
                        }
                        if(resume!=null) {
                            resumeObject.setDetailResume(resume.getDetailResume());
                            resumeObject.setEmail(resume.getEmail());
                            resumeObject.setGoodAt(resume.getGoodAt());
                            resumeObject.setHadDone(resume.getHadDone());
                            resumeObject.setName(resume.getName());
                            resumeObject.setNikeName(resume.getNikeName());
                            resumeObject.setPhone(resume.getPhone());
                            resumeObject.setResumeID(resume.getResumeID());
                            resumeObject.setSingleResume(resume.getSingleResume());
                            resumeObject.setUserID(resume.getUserID());
                            resumeObject.setUrl(resume.getUrl());
                            listener.onFinish("");
                        }
                        else
                        {
                            listener.onError(GSON_ERR);
                        }
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
                Log.d("POST错误:",error);
                listener.onError(error);
            }
        });
    }


    public static void getUserInformation(Map<String,String> params, final HttpCallBackListener listener)
    {
        post(HttpAddress.HOST + HttpAddress.GET_USER_INFORMATION, params, new HttpCallBackListener() {
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
                            userObject.setPhone(userResult.getUser().getPhone());
                            userObject.setUserID(userResult.getUser().getUserID());
                            userObject.setAccount(userResult.getUser().getAccount());
                            userObject.setEmail(userResult.getUser().getEmail());
                            userObject.setImage(userResult.getUser().getImage());
                            userObject.setName(userResult.getUser().getName());
                            userObject.setNickName(userResult.getUser().getNickName());
                            userObject.setPasswd(userResult.getUser().getPasswd());
                            userObject.setAccount(userResult.getUser().getAccount());
                            userObject.setType(userResult.getUser().getType());

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
                Log.d("POST错误:",error);
                listener.onError(error);
            }
        });
    }
    public static void getRecuitInformation(Map<String,String> params, final HttpCallBackListener listener)
    {
        post(HttpAddress.HOST + HttpAddress.GET_RECUIT_INFORMATION, params, new HttpCallBackListener() {
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
                            recuitObject.setPhone(recuitResult.getRecuit().getPhone());
                            recuitObject.setApplyPeopleNum(recuitResult.getRecuit().getApplyPeopleNum());
                            recuitObject.setDeadLine(recuitResult.getRecuit().getDeadLine());
                            recuitObject.setDisplayTime(recuitResult.getRecuit().getDisplayTime());
                            recuitObject.setEmail(recuitResult.getRecuit().getEmail());
                            recuitObject.setIsOK(recuitResult.getRecuit().getIsOK());
                            recuitObject.setOkTime(recuitResult.getRecuit().getDisplayTime());
                            recuitObject.setOwner(recuitResult.getRecuit().getOwner());
                            recuitObject.setOwnerID(recuitResult.getRecuit().getOwnerID());
                            recuitObject.setReason(recuitResult.getRecuit().getReason());
                            recuitObject.setRecruitID(recuitResult.getRecuit().getRecruitID());
                            recuitObject.setRequrire(recuitResult.getRecuit().getRequrire());
                            recuitObject.setSingleInfo(recuitResult.getRecuit().getSingleInfo());
                            recuitObject.setStatus(recuitResult.getRecuit().getStatus());
                            recuitObject.setTitle(recuitResult.getRecuit().getTitle());
                            recuitObject.setWorkInfo(recuitResult.getRecuit().getWorkInfo());
                            recuitObject.setWorkPlace(recuitResult.getRecuit().getWorkPlace());
                            recuitObject.setNeedPeopleNum(recuitResult.getRecuit().getNeedPeopleNum());

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
                Log.d("POST错误:",error);
                listener.onError(error);

            }
        });
    }
    public static void getEmailInformation(Map<String,String> params, final HttpCallBackListener listener)
    {
        post(HttpAddress.HOST + HttpAddress.GET_RECUIT_INFORMATION, params, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                EmailResult emailResult=null;

                try {

                    emailResult=new Gson().fromJson(result,EmailResult.class);
                }
                catch (JsonSyntaxException e) {
                    //错误
                }
                if(emailResult!=null)
                {
                    if(emailResult.isSuccess())
                    {
                        emailData=emailResult.getEmailData();
                        listener.onFinish("发送成功");

                    }
                    else
                    {
                        listener.onError(emailResult.getMessage());
                    }
                }
                else listener.onError(GSON_ERR);
            }

            @Override
            public void onError(String error) {

                Log.d("POST错误:",error);
                listener.onError(error);
            }
        });
    }
}
