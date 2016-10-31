package com.tac.iparttimejob.NetWork.Query;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.tac.iparttimejob.Class.ApplicationList;
import com.tac.iparttimejob.Class.EnrollList;
import com.tac.iparttimejob.Class.EmailResult;
import com.tac.iparttimejob.Class.RecuitResult;
import com.tac.iparttimejob.Class.RecuitList;
import com.tac.iparttimejob.Class.ResumeResult;
import com.tac.iparttimejob.Class.ReturnMessage;
import com.tac.iparttimejob.Class.UserResult;
import com.tac.iparttimejob.NetWork.Connect.HttpAddress;
import com.tac.iparttimejob.NetWork.Connect.HttpCallBackListener;
import com.tac.iparttimejob.NetWork.Connect.HttpPost;

import java.lang.reflect.Type;
import java.util.Map;

import static com.tac.iparttimejob.Class.Object.applicationObjectList;
import static com.tac.iparttimejob.Class.Object.enrollChooseObjectList;
import static com.tac.iparttimejob.Class.Object.applicationObject;
import static com.tac.iparttimejob.Class.Object.chooseApplicationList;
import static com.tac.iparttimejob.Class.Object.emailData;
import static com.tac.iparttimejob.Class.Object.enrollObjectList;
import static com.tac.iparttimejob.Class.Object.inRecuitObjectList;
import static com.tac.iparttimejob.Class.Object.notRecuitObjectList;
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
                    Log.d("gsonErr:",e.toString());
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
                            resumeObject.setUser(resume.getUser());
                            resumeObject.setResumeid(resume.getResumeid());
                            resumeObject.setNickname(resume.getNickname());
                            resumeObject.setName(resume.getName());
                            resumeObject.setPhone(resume.getPhone());
                            resumeObject.setEmail(resume.getEmail());
                            resumeObject.setSingleResume(resume.getSingleResume());
                            resumeObject.setDetailResume(resume.getDetailResume());
                            listener.onFinish("成功");
                        }
                        else
                        {
                            listener.onError(GSON_ERR);
                        }
                    }
                    else
                    {
                        Log.d("gsonErr:",resumeResult.getMessage());
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

    //查看用户资料
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
                    Log.d("gsonErr:",e.toString());
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
    //邮件验证码
    public static void getEmailInformation(Map<String,String> params, final HttpCallBackListener listener)
    {
        post(HttpAddress.HOST + HttpAddress.GET_RECRUIT_INFORMATION, params, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                EmailResult emailResult=null;

                try {

                    emailResult=new Gson().fromJson(result,EmailResult.class);
                }
                catch (JsonSyntaxException e) {
                    Log.d("gsonErr:",e.toString());
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
    //查看招聘信息
    public static void getRecruitInformation(Map<String,String>params,final HttpCallBackListener listener){
        post(HttpAddress.HOST + HttpAddress.GET_RECRUIT_INFORMATION, params, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                RecuitResult recuitResult=null;
                try {
                    recuitResult=new Gson().fromJson(result,RecuitResult.class);
                }catch (JsonSyntaxException e) {
                    Log.d("gsonErr:",e.toString());
                    //错误
                }
                if(recuitResult!=null)
                {
                    if(recuitResult.isSuccess())
                    {
                        recuitObject.setUser(recuitResult.getRecuit().getUser());
                        recuitObject.setRecruitID(recuitResult.getRecuit().getRecruitID());
                        recuitObject.setOwner(recuitResult.getRecuit().getOwner());
                        recuitObject.setTitle(recuitResult.getRecuit().getTitle());
                        recuitObject.setWorkPlace(recuitResult.getRecuit().getWorkPlace());
                        recuitObject.setDeadLine(recuitResult.getRecuit().getDeadLine());
                        recuitObject.setPhone(recuitResult.getRecuit().getPhone());
                        recuitObject.setEmail(recuitResult.getRecuit().getEmail());
                        recuitObject.setNeedPeopleNum(recuitResult.getRecuit().getNeedPeopleNum());
                        recuitObject.setStatus(recuitResult.getRecuit().getStatus());
                        recuitObject.setApplyPeopleNum(recuitResult.getRecuit().getApplyPeopleNum());
                        recuitObject.setRequrire(recuitResult.getRecuit().getRequrire());
                        recuitObject.setSingleInfo(recuitResult.getRecuit().getSingleInfo());
                        recuitObject.setWorkInfo(recuitResult.getRecuit().getWorkInfo());
                        recuitObject.setDisplayTime(recuitResult.getRecuit().getDisplayTime());
                        recuitObject.setIsOK(recuitResult.getRecuit().getIsOK());
                        recuitObject.setReason(recuitResult.getRecuit().getReason());
                        recuitObject.setOkTime(recuitResult.getRecuit().getOkTime());

                        listener.onFinish("成功");
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

    //查看正在进行的招聘信息
    public static void getInRecruitList(Map<String,String>params,final HttpCallBackListener listener){
        post(HttpAddress.HOST + HttpAddress.GET_IN_RECUIT_LIST, params, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                RecuitList recuitResultList=null;
                Type type=new TypeToken<RecuitList>(){}.getType();
                try {
                    recuitResultList=new Gson().fromJson(result,type);
                }catch (JsonSyntaxException e) {
                    Log.d("gsonErr:",e.toString());
                    //错误
                }
                if(recuitResultList!=null) {
                    if (recuitResultList.isSuccess()) {
                        recuitResultList.getRecuitList().addAll(inRecuitObjectList);
                        listener.onFinish("成功");
                    } else {
                        listener.onError(recuitResultList.getMessage());
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
    //查看已失效的招聘信息
    public static void getNotRecruitList(Map<String,String>params,final HttpCallBackListener listener){
        post(HttpAddress.HOST + HttpAddress.GET_NOT_RECUIT_LIST, params, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                RecuitList recuitResultList=null;
                Type type=new TypeToken<RecuitList>(){}.getType();
                try {
                    recuitResultList=new Gson().fromJson(result,type);
                }catch (JsonSyntaxException e) {
                    Log.d("gsonErr:",e.toString());
                    //错误
                }
                if(recuitResultList!=null) {
                    if (recuitResultList.isSuccess()) {
                        recuitResultList.getRecuitList().addAll(notRecuitObjectList);
                        listener.onFinish("成功");
                    } else {
                        listener.onError(recuitResultList.getMessage());
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
    //查看报名列表
    public static void getEnrollList(Map<String,String>params,final HttpCallBackListener listener){
        post(HttpAddress.HOST + HttpAddress.GET_ENROLL_LIST, params, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                EnrollList enrollList=null;
                Type type=new TypeToken<RecuitList>(){}.getType();
                try {
                    enrollList=new Gson().fromJson(result,type);
                }catch (JsonSyntaxException e) {
                    Log.d("gsonErr:",e.toString());
                    //错误
                }
                if(enrollList!=null) {
                    if (enrollList.isSuccess()) {
                        enrollList.getEnrolls().addAll(enrollObjectList);
                        listener.onFinish("成功");
                    } else {
                        listener.onError(enrollList.getMessage());
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
    //查看选中报名列表
    public static void getChooseEnrollList(Map<String,String>params,final HttpCallBackListener listener){
        post(HttpAddress.HOST + HttpAddress.GET_CHOOSE_ENROLL_LIST, params, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                EnrollList enrollList=null;
                Type type=new TypeToken<RecuitList>(){}.getType();
                try {
                    enrollList=new Gson().fromJson(result,type);
                }catch (JsonSyntaxException e) {
                    Log.d("gsonErr:",e.toString());
                    //错误
                }
                if(enrollList!=null) {
                    if (enrollList.isSuccess()) {
                        enrollList.getEnrolls().addAll(enrollChooseObjectList);
                        listener.onFinish("成功");
                    } else {
                        listener.onError(enrollList.getMessage());
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





    //查看招聘
    public static void getChooseApplicationList(Map<String,String>params,final HttpCallBackListener listener){
        post(HttpAddress.HOST + HttpAddress.GET_CHOOSE_APPLICATION, params, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                ApplicationList chooseApplication=null;
                Type type=new TypeToken<RecuitList>(){}.getType();
                try {
                    chooseApplication=new Gson().fromJson(result,type);
                }catch (JsonSyntaxException e) {
                    Log.d("gsonErr:",e.toString());
                    //错误
                }
                if(chooseApplication!=null) {
                    if (chooseApplication.isSuccess()) {
                        chooseApplication.getApplications().addAll(chooseApplicationList);
                        listener.onFinish("成功");
                    } else {
                        listener.onError(chooseApplication.getMessage());
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

    //查看招聘信息
    public static void getApplicationInformation(Map<String,String>params,final HttpCallBackListener listener){
        post(HttpAddress.HOST + HttpAddress.GET_APPLICATION_INFORMATION, params, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                RecuitResult recuitResult=null;
                try {
                    recuitResult=new Gson().fromJson(result,RecuitResult.class);
                }catch (JsonSyntaxException e) {
                    Log.d("gsonErr:",e.toString());
                    //错误
                }
                if(recuitResult!=null)
                {
                    if(recuitResult.isSuccess())
                    {
                        applicationObject.setUser(recuitResult.getRecuit().getUser());
                        applicationObject.setRecruitID(recuitResult.getRecuit().getRecruitID());
                        applicationObject.setOwner(recuitResult.getRecuit().getOwner());
                        applicationObject.setTitle(recuitResult.getRecuit().getTitle());
                        applicationObject.setWorkPlace(recuitResult.getRecuit().getWorkPlace());
                        applicationObject.setDeadLine(recuitResult.getRecuit().getDeadLine());
                        applicationObject.setPhone(recuitResult.getRecuit().getPhone());
                        applicationObject.setEmail(recuitResult.getRecuit().getEmail());
                        applicationObject.setNeedPeopleNum(recuitResult.getRecuit().getNeedPeopleNum());
                        applicationObject.setStatus(recuitResult.getRecuit().getStatus());
                        applicationObject.setApplyPeopleNum(recuitResult.getRecuit().getApplyPeopleNum());
                        applicationObject.setRequrire(recuitResult.getRecuit().getRequrire());
                        applicationObject.setSingleInfo(recuitResult.getRecuit().getSingleInfo());
                        applicationObject.setWorkInfo(recuitResult.getRecuit().getWorkInfo());
                        applicationObject.setDisplayTime(recuitResult.getRecuit().getDisplayTime());
                        applicationObject.setIsOK(recuitResult.getRecuit().getIsOK());
                        applicationObject.setReason(recuitResult.getRecuit().getReason());
                        applicationObject.setOkTime(recuitResult.getRecuit().getOkTime());

                        listener.onFinish("成功");
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
    //查看申请列表
    public static void getApplicantList(Map<String,String>params,final HttpCallBackListener listener){
        post(HttpAddress.HOST + HttpAddress.GET_APPLICATION_LIST, params, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                ApplicationList applicationList=null;
                Type type=new TypeToken<RecuitList>(){}.getType();
                try {
                    applicationList=new Gson().fromJson(result,type);
                }catch (JsonSyntaxException e) {
                    Log.d("gsonErr:",e.toString());
                    //错误
                }
                if(applicationList!=null) {
                    if (applicationList.isSuccess()) {
                        applicationList.getApplications().addAll(applicationObjectList);
                        listener.onFinish("成功");
                    } else {
                        listener.onError(applicationList.getMessage());
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
    //查看申请是否成功
    public static void getCheckChoosen(Map<String,String>params,final HttpCallBackListener listener){
        post(HttpAddress.HOST + HttpAddress.GET_CHECK_CHOOSEN, params, new HttpCallBackListener() {
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
                        listener.onFinish("true");
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
