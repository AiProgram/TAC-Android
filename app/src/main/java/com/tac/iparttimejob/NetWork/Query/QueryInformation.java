package com.tac.iparttimejob.NetWork.Query;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.tac.iparttimejob.Class.Advice;
import com.tac.iparttimejob.Class.Application;
import com.tac.iparttimejob.Class.Assessment;
import com.tac.iparttimejob.Class.ApplicationList;
import com.tac.iparttimejob.Class.AssessmentList;
import com.tac.iparttimejob.Class.EnrollList;
import com.tac.iparttimejob.Class.EmailResult;
import com.tac.iparttimejob.Class.RecuitResult;
import com.tac.iparttimejob.Class.RecuitList;
import com.tac.iparttimejob.Class.Resume;
import com.tac.iparttimejob.Class.ResumeResult;
import com.tac.iparttimejob.Class.ReturnMessage;
import com.tac.iparttimejob.Class.UserImage;
import com.tac.iparttimejob.Class.UserResult;
import com.tac.iparttimejob.NetWork.Connect.HttpAddress;
import com.tac.iparttimejob.NetWork.Connect.HttpCallBackListener;
import com.tac.iparttimejob.NetWork.Connect.HttpPost;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.tac.iparttimejob.Class.Object.RecuitObjectlistForManager;
import static com.tac.iparttimejob.Class.Object.applicationObjectList;
import static com.tac.iparttimejob.Class.Object.atooAssessmentByIDObjectList;
import static com.tac.iparttimejob.Class.Object.atooAssessmentObjectList;
import static com.tac.iparttimejob.Class.Object.atooCommentObjectList;
import static com.tac.iparttimejob.Class.Object.enrollChooseObjectList;
import static com.tac.iparttimejob.Class.Object.applicationObject;
import static com.tac.iparttimejob.Class.Object.chooseApplicationList;
import static com.tac.iparttimejob.Class.Object.emailData;
import static com.tac.iparttimejob.Class.Object.enrollObjectList;
import static com.tac.iparttimejob.Class.Object.getResumeObject;
import static com.tac.iparttimejob.Class.Object.inRecuitObjectList;
import static com.tac.iparttimejob.Class.Object.notRecuitObjectList;
import static com.tac.iparttimejob.Class.Object.otoaAssessmentByIDObjectList;
import static com.tac.iparttimejob.Class.Object.otoaAssessmentObjectList;
import static com.tac.iparttimejob.Class.Object.otoaCommentObjectList;
import static com.tac.iparttimejob.Class.Object.recuitObject;
import static com.tac.iparttimejob.Class.Object.recuitObjectList;
import static com.tac.iparttimejob.Class.Object.resumeObject;
import static com.tac.iparttimejob.Class.Object.suggesstion;
import static com.tac.iparttimejob.Class.Object.userImage;
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
                        if(resumeObject==null)
                        {
                            resumeObject=new Resume();
                        }

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
    public static void getUserInformationByName(Map<String,String> params, final HttpCallBackListener listener)
    {
        post(HttpAddress.HOST + HttpAddress.GET_USER_INFORMATION_BY_NAME, params, new HttpCallBackListener() {
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
                        if(getResumeObject==null) {
                            getResumeObject=new Resume();
                        }
                        getResumeObject.setUser(resumeResult.getUser());
                        getResumeObject.setResumeid(resumeResult.getResumeid());
                        getResumeObject.setNickname(resumeResult.getNickname());
                        getResumeObject.setName(resumeResult.getName());
                        getResumeObject.setPhone(resumeResult.getPhone());
                        getResumeObject.setEmail(resumeResult.getEmail());
                        getResumeObject.setSingleResume(resumeResult.getSingleResume());
                        getResumeObject.setDetailResume(resumeResult.getDetailResume());

                            listener.onFinish("查看成功");
                    }
                    else
                    {
                        Log.d("gsonErr:",resumeResult.toString());
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
    //邮件验证码
    public static void getEmailInformation(Map<String,String> params, final HttpCallBackListener listener)
    {
        post(HttpAddress.HOST + HttpAddress.EMAIL_ADDRESS, params, new HttpCallBackListener() {
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
    //下载头像
    public static void getImage(Map<String,String> params, final HttpCallBackListener listener)
    {
        post(HttpAddress.HOST + HttpAddress.GET_IMAGE, params, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                UserImage image=null;
                try {

                    image=new Gson().fromJson(result,UserImage.class);
                }
                catch (JsonSyntaxException e) {
                    Log.d("gsonErr:",e.toString());
                    //错误
                }
                if(image!=null)
                {
                    if(image.isSuccess())
                    {
                        //Log.d("image:",image.getData());
                        userImage=image.getData();
                        Log.d("image:",userImage);

                        listener.onFinish("下载成功");

                    }
                    else
                    {
                        listener.onError(image.getMessage());
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
                Log.d("result:",result);
                RecuitResult recuitResult=null;
                if(recuitObject==null){
                    recuitObject=new RecuitResult.Recuit();
                }
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
                        recuitObject.setTac_user(recuitResult.getData().getTac_user());
                        recuitObject.setRecruitid(recuitResult.getData().getRecruitid());
                        recuitObject.setOwner(recuitResult.getData().getOwner());
                        recuitObject.setTitle(recuitResult.getData().getTitle());
                        recuitObject.setWorkplace(recuitResult.getData().getWorkplace());
                        recuitObject.setDealdine(recuitResult.getData().getDealdine());
                        recuitObject.setPhone(recuitResult.getData().getPhone());
                        recuitObject.setEmail(recuitResult.getData().getEmail());
                        recuitObject.setNeedpeopleNum(recuitResult.getData().getNeedpeopleNum());
                        recuitObject.setStatus(recuitResult.getData().getStatus());
                        recuitObject.setApplypeopleNum(recuitResult.getData().getApplypeopleNum());
                        recuitObject.setRequrire(recuitResult.getData().getRequrire());
                        recuitObject.setSingleInfo(recuitResult.getData().getSingleInfo());
                        recuitObject.setWorkInfo(recuitResult.getData().getWorkInfo());
                        recuitObject.setDisplaytime(recuitResult.getData().getDisplaytime());
                        recuitObject.setIsOk(recuitResult.getData().getIsOk());
                        recuitObject.setReason(recuitResult.getData().getReason());
                        recuitObject.setOktime(recuitResult.getData().getOktime());

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
                //Log.d("result:",result);
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
                        inRecuitObjectList=recuitResultList.getData();
                       // recuitResultList.getData().addAll(inRecuitObjectList);

                   //     Log.d("inrecruitObjcet:", inRecuitObjectList.get(0).getDealdine());
                    //    Log.d("inrecruitObjcet:", inRecuitObjectList.get(0).getTac_user().getUserid());
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
                        notRecuitObjectList=recuitResultList.getData();
                       // recuitResultList.getData().addAll(notRecuitObjectList);
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
    //查看招聘信息
    public static void getRecruitList(Map<String,String>params,final HttpCallBackListener listener){
        post(HttpAddress.HOST + HttpAddress.GET_RECUIT_LIST, params, new HttpCallBackListener() {
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
                        recuitObjectList=recuitResultList.getData();
                        // recuitResultList.getData().addAll(notRecuitObjectList);
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
                        enrollObjectList=enrollList.getData();
                        //enrollList.getData().addAll(enrollObjectList);
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
                        enrollChooseObjectList=enrollList.getData();
                       // enrollList.getData().addAll(enrollChooseObjectList);
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





    //查看招聘列表(应聘者)
    public static void getApplicationList(Map<String,String>params,final HttpCallBackListener listener){
        post(HttpAddress.HOST + HttpAddress.GET_APPLICATION_LIST, params, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                Log.d("result:",result);
                RecuitList  chooseApplication=null;
                Type type=new TypeToken<RecuitList>(){}.getType();
                try {
                    chooseApplication=new Gson().fromJson(result,type);
                }catch (JsonSyntaxException e) {
                    Log.d("gsonErr:",e.toString());
                    //错误
                }
                if(chooseApplication!=null) {
                    if (chooseApplication.isSuccess()) {
                        chooseApplicationList=chooseApplication.getData();
                        //chooseApplication.getData().addAll(chooseApplicationList);
                        Log.d("list:",chooseApplicationList.get(0).getDealdine());
                        Log.d("list:",chooseApplicationList.get(0).getDisplaytime());
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
                        applicationObject.setTac_user(recuitResult.getData().getTac_user());
                        applicationObject.setRecruitid(recuitResult.getData().getRecruitid());
                        applicationObject.setOwner(recuitResult.getData().getOwner());
                        applicationObject.setTitle(recuitResult.getData().getTitle());
                        applicationObject.setWorkplace(recuitResult.getData().getWorkplace());
                        applicationObject.setDealdine(recuitResult.getData().getDealdine());
                        applicationObject.setPhone(recuitResult.getData().getPhone());
                        applicationObject.setEmail(recuitResult.getData().getEmail());
                        applicationObject.setNeedpeopleNum(recuitResult.getData().getNeedpeopleNum());
                        applicationObject.setStatus(recuitResult.getData().getStatus());
                        applicationObject.setApplypeopleNum(recuitResult.getData().getApplypeopleNum());
                        applicationObject.setRequrire(recuitResult.getData().getRequrire());
                        applicationObject.setSingleInfo(recuitResult.getData().getSingleInfo());
                        applicationObject.setWorkInfo(recuitResult.getData().getWorkInfo());
                        applicationObject.setDisplaytime(recuitResult.getData().getDisplaytime());
                        applicationObject.setIsOk(recuitResult.getData().getIsOk());
                        applicationObject.setReason(recuitResult.getData().getReason());
                        applicationObject.setOktime(recuitResult.getData().getOktime());

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
        post(HttpAddress.HOST + HttpAddress.GET_CHOOSE_APPLICATION_LIST, params, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                Log.d("result:",result);
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
                        applicationObjectList=applicationList.getData();
                        Log.d("list:",applicationList.getData().get(0).getDealdline());
                        //applicationList.getData().addAll(applicationObjectList);
                        Log.d("list:",applicationObjectList.get(0).getDealdline());
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
    //查看招聘列表
    public static void getRecruitListForManager(Map<String,String>params,final HttpCallBackListener listener){
        post(HttpAddress.HOST + HttpAddress.GET_RECRUIT_LIST_FOR_MANAGER, params, new HttpCallBackListener() {
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

                        RecuitObjectlistForManager=recuitResultList.getData();
                       // recuitResultList.getData().addAll(RecuitObjectlistForManager);
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
    //查看atoo评论列表
  public static void getAtooAssementForManager(Map<String,String>params,final HttpCallBackListener listener){
        post(HttpAddress.HOST + HttpAddress.GET_ATOO_ASSEMENT_LIST, params, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                AssessmentList assement=null;
                Type type=new TypeToken<AssessmentList>(){}.getType();
                try {
                    assement=new Gson().fromJson(result,type);
                }catch (JsonSyntaxException e) {
                    Log.d("gsonErr:",e.toString());
                    //错误
                }
                if(assement!=null) {
                    if (assement.isSuccess()) {
                        atooAssessmentObjectList=assement.getData();

                        //assement.getData().addAll(atooAssessmentObjectList);
                        listener.onFinish("成功");
                    } else {
                        listener.onError(assement.getMessage());
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
    //查看应聘者对我的评价
    public static void getAtooComment(Map<String,String>params,final HttpCallBackListener listener){
        post(HttpAddress.HOST + HttpAddress.GET_ATOOCOMMENT, params, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                AssessmentList assement=null;
                Type type=new TypeToken<AssessmentList>(){}.getType();
                try {
                    assement=new Gson().fromJson(result,type);
                }catch (JsonSyntaxException e) {
                    Log.d("gsonErr:",e.toString());
                    //错误
                }
                if(assement!=null) {
                    if (assement.isSuccess()) {
                        atooCommentObjectList=assement.getData();
                        //assement.getData().addAll(atooAssessmentObjectList);
                        listener.onFinish("成功");
                    } else {
                        listener.onError(assement.getMessage());
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

    //查看otoa评论列表
    public static void getOtoaAssementForManager(Map<String,String>params,final HttpCallBackListener listener){
        post(HttpAddress.HOST + HttpAddress.GET_OTOA_ASSEMENT_LIST, params, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                AssessmentList assement=null;
                Type type=new TypeToken<AssessmentList>(){}.getType();
                try {
                    assement=new Gson().fromJson(result,type);
                }catch (JsonSyntaxException e) {
                    Log.d("gsonErr:",e.toString());
                    //错误
                }
                if(assement!=null) {
                    if (assement.isSuccess()) {
                        otoaAssessmentObjectList=assement.getData();
                        //assement.getData().addAll(otoaAssessmentObjectList);
                        listener.onFinish("成功");
                    } else {
                        listener.onError(assement.getMessage());
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
    //查看招聘者对我评价
    public static void getOtoaComment(Map<String,String>params,final HttpCallBackListener listener){
        post(HttpAddress.HOST + HttpAddress.GET_OTOACOMMENT, params, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                AssessmentList assement=null;
                Type type=new TypeToken<AssessmentList>(){}.getType();
                try {
                    assement=new Gson().fromJson(result,type);
                }catch (JsonSyntaxException e) {
                    Log.d("gsonErr:",e.toString());
                    //错误
                }
                if(assement!=null) {
                    if (assement.isSuccess()) {
                        otoaCommentObjectList=assement.getData();
                        //assement.getData().addAll(otoaAssessmentObjectList);
                        listener.onFinish("成功");
                    } else {
                        listener.onError(assement.getMessage());
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
    //查看atoo评论列表By iD
    public static void getAtooAssementByIDForManager(Map<String,String>params,final HttpCallBackListener listener){
        post(HttpAddress.HOST + HttpAddress.GET_ATOO_ASSEMENT_LIST_BY_ID, params, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                AssessmentList assement=null;
                Type type=new TypeToken<AssessmentList>(){}.getType();
                try {
                    assement=new Gson().fromJson(result,type);
                }catch (JsonSyntaxException e) {
                    Log.d("gsonErr:",e.toString());
                    //错误
                }
                if(assement!=null) {
                    if (assement.isSuccess()) {
                        atooAssessmentByIDObjectList=assement.getData();
                        //assement.getData().addAll(atooAssessmentByIDObjectList);
                        listener.onFinish("成功");
                    } else {
                        listener.onError(assement.getMessage());
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
    //查看otoa评论列表By iD
    public static void getOtoaAssementByIDForManager(Map<String,String>params,final HttpCallBackListener listener){
        post(HttpAddress.HOST + HttpAddress.GET_OTOA_ASSEMENT_LIST_BY_ID, params, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                AssessmentList assement=null;
                Type type=new TypeToken<RecuitList>(){}.getType();
                try {
                    assement=new Gson().fromJson(result,type);
                }catch (JsonSyntaxException e) {
                    Log.d("gsonErr:",e.toString());
                    //错误
                }
                if(assement!=null) {
                    if (assement.isSuccess()) {
                        otoaAssessmentByIDObjectList=assement.getData();
                        //assement.getData().addAll(otoaAssessmentByIDObjectList);
                        listener.onFinish("成功");
                    } else {
                        listener.onError(assement.getMessage());
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
    public static void getAdvice(Map<String,String>params,final HttpCallBackListener listener){
        post(HttpAddress.HOST + HttpAddress.GET_ADVICE, params, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                Advice advice=null;
                try {
                    advice=new Gson().fromJson(result,Advice.class);
                }catch (JsonSyntaxException e) {
                    Log.d("gsonErr:",e.toString());
                    //错误
                }
                if(advice!=null) {
                    if (advice.isSuccess()) {
                        suggesstion.setPhone(advice.getData().getPhone());
                        suggesstion.setAdvice(advice.getData().getAdvice());
                        suggesstion.setAdviceid(advice.getData().getAdviceid());
                        suggesstion.setTime(advice.getData().getTime());
                        suggesstion.setUserid(advice.getData().getUserid());
                        suggesstion.setUsername(advice.getData().getUsername());
                        listener.onFinish("成功");
                    } else {
                        listener.onError(advice.getMessage());
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
