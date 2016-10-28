package com.tac.iparttimejob.NetWork.Query;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tac.iparttimejob.Class.RecuitResult;
import com.tac.iparttimejob.Class.ResumeResult;
import com.tac.iparttimejob.Class.UserResult;
import com.tac.iparttimejob.NetWork.Connect.HttpAddress;
import com.tac.iparttimejob.NetWork.Connect.HttpCallBackListener;
import com.tac.iparttimejob.NetWork.Connect.HttpPost;

import java.util.Map;

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
                UserResult.User user =null;
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
                        try {

                            user=new Gson().fromJson(result,UserResult.User.class);

                        }
                        catch (JsonSyntaxException e) {
                            //错误
                        }
                        if(user!=null) {
                            userObject.setPhone(user.getPhone());
                            userObject.setUserID(user.getUserID());
                            userObject.setAccount(user.getAccount());
                            userObject.setEmail(user.getEmail());
                            userObject.setImage(user.getImage());
                            userObject.setName(user.getName());
                            userObject.setNickName(user.getNickName());
                            userObject.setPasswd(user.getPasswd());
                            userObject.setAccount(user.getAccount());
                            userObject.setType(user.getType());

                            listener.onFinish("");
                        }
                        else
                        {
                            listener.onError(GSON_ERR);
                        }
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
    public static void getRecuitInformation(Map<String,String> params, final HttpCallBackListener listener)
    {
        post(HttpAddress.HOST + HttpAddress.GET_RECUIT_INFORMATION, params, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                RecuitResult recuitResult=null;
                RecuitResult.Recuit recuit =null;
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
                        try {

                            recuit=new Gson().fromJson(result, RecuitResult.Recuit.class);

                        }
                        catch (JsonSyntaxException e) {
                            //错误
                        }
                        if(recuit!=null) {
                            recuitObject.setPhone(recuit.getPhone());
                            recuitObject.setApplyPeopleNum(recuit.getApplyPeopleNum());
                            recuitObject.setDeadLine(recuit.getDeadLine());
                            recuitObject.setDisplayTime(recuit.getDisplayTime());
                            recuitObject.setEmail(recuit.getEmail());
                            recuitObject.setIsOK(recuit.getIsOK());
                            recuitObject.setOkTime(recuit.getDisplayTime());
                            recuitObject.setOwner(recuit.getOwner());
                            recuitObject.setOwnerID(recuit.getOwnerID());
                            recuitObject.setReason(recuit.getReason());
                            recuitObject.setRecruitID(recuit.getRecruitID());
                            recuitObject.setRequrire(recuit.getRequrire());
                            recuitObject.setSingleInfo(recuit.getSingleInfo());
                            recuitObject.setStatus(recuit.getStatus());
                            recuitObject.setTitle(recuit.getTitle());
                            recuitObject.setWorkInfo(recuit.getWorkInfo());
                            recuitObject.setWorkPlace(recuit.getWorkPlace());
                            recuitObject.setNeedPeopleNum(recuit.getNeedPeopleNum());

                            listener.onFinish("");
                        }
                        else
                        {
                            listener.onError(GSON_ERR);
                        }
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
