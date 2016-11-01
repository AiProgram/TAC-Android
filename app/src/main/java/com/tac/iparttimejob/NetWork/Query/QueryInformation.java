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
    //查看个人简历
    public static void getPersonalResume(Map<String,String> params, final HttpCallBackListener listener)
    {
        post(HttpAddress.HOST + HttpAddress.GET_PERSONAL_RESUME, params, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                ResumeResult resumeResult=null;
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

                            resumeObject.setUser(resumeResult.getUser());
                            resumeObject.setResumeid(resumeResult.getResumeid());
                            resumeObject.setNickname(resumeResult.getNickname());
                            resumeObject.setName(resumeResult.getName());
                            resumeObject.setPhone(resumeResult.getPhone());
                            resumeObject.setEmail(resumeResult.getEmail());
                            resumeObject.setSingleResume(resumeResult.getSingleResume());
                            resumeObject.setDetailResume(resumeResult.getDetailResume());
                            listener.onFinish("成功");

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
                        emailData=emailResult.getData();
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
                        recuitObject.setUser(recuitResult.getData().getUser());
                        recuitObject.setRecruitID(recuitResult.getData().getRecruitID());
                        recuitObject.setOwner(recuitResult.getData().getOwner());
                        recuitObject.setTitle(recuitResult.getData().getTitle());
                        recuitObject.setWorkPlace(recuitResult.getData().getWorkPlace());
                        recuitObject.setDeadLine(recuitResult.getData().getDeadLine());
                        recuitObject.setPhone(recuitResult.getData().getPhone());
                        recuitObject.setEmail(recuitResult.getData().getEmail());
                        recuitObject.setNeedPeopleNum(recuitResult.getData().getNeedPeopleNum());
                        recuitObject.setStatus(recuitResult.getData().getStatus());
                        recuitObject.setApplyPeopleNum(recuitResult.getData().getApplyPeopleNum());
                        recuitObject.setRequrire(recuitResult.getData().getRequrire());
                        recuitObject.setSingleInfo(recuitResult.getData().getSingleInfo());
                        recuitObject.setWorkInfo(recuitResult.getData().getWorkInfo());
                        recuitObject.setDisplayTime(recuitResult.getData().getDisplayTime());
                        recuitObject.setIsOK(recuitResult.getData().getIsOK());
                        recuitObject.setReason(recuitResult.getData().getReason());
                        recuitObject.setOkTime(recuitResult.getData().getOkTime());

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
                        recuitResultList.getData().addAll(inRecuitObjectList);
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
                        recuitResultList.getData().addAll(notRecuitObjectList);
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
                        enrollList.getData().addAll(enrollObjectList);
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
                        enrollList.getData().addAll(enrollChooseObjectList);
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
                        chooseApplication.getData().addAll(chooseApplicationList);
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
                        applicationObject.setUser(recuitResult.getData().getUser());
                        applicationObject.setRecruitID(recuitResult.getData().getRecruitID());
                        applicationObject.setOwner(recuitResult.getData().getOwner());
                        applicationObject.setTitle(recuitResult.getData().getTitle());
                        applicationObject.setWorkPlace(recuitResult.getData().getWorkPlace());
                        applicationObject.setDeadLine(recuitResult.getData().getDeadLine());
                        applicationObject.setPhone(recuitResult.getData().getPhone());
                        applicationObject.setEmail(recuitResult.getData().getEmail());
                        applicationObject.setNeedPeopleNum(recuitResult.getData().getNeedPeopleNum());
                        applicationObject.setStatus(recuitResult.getData().getStatus());
                        applicationObject.setApplyPeopleNum(recuitResult.getData().getApplyPeopleNum());
                        applicationObject.setRequrire(recuitResult.getData().getRequrire());
                        applicationObject.setSingleInfo(recuitResult.getData().getSingleInfo());
                        applicationObject.setWorkInfo(recuitResult.getData().getWorkInfo());
                        applicationObject.setDisplayTime(recuitResult.getData().getDisplayTime());
                        applicationObject.setIsOK(recuitResult.getData().getIsOK());
                        applicationObject.setReason(recuitResult.getData().getReason());
                        applicationObject.setOkTime(recuitResult.getData().getOkTime());

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
                        applicationList.getData().addAll(applicationObjectList);
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
