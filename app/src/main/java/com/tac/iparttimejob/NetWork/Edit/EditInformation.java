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

    //创建简历
    public static void setCreatPersonalResume(Map<String,String>params,final HttpCallBackListener listener)
    {
        post(HttpAddress.HOST + HttpAddress.SET_CTEAT_PERSONAL_RESUME, params, new HttpCallBackListener() {
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
    //更新简历
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
    //创建招聘
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
//更新账户信息
    public static void setUserInformation(Map<String,String>params,final HttpCallBackListener listener)
    {
        post(HttpAddress.HOST + HttpAddress.SET_USER_INFORMATION, params, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                ReturnMessage returnMessage=null;
                try {

                    returnMessage=new Gson().fromJson(result,ReturnMessage.class);
                }
                catch (JsonSyntaxException e) {
                    Log.d("gsonErr:",e.toString());
                    //错误
                }
                if(returnMessage!=null)
                {
                    if(returnMessage.isSuccess())
                    {

                        listener.onFinish("修改成功");
                    }
                    else
                    {
                        listener.onError(returnMessage.getMessage());
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
    //反馈
    public static void setAdvice(Map<String,String>params,final HttpCallBackListener listener){
        post(HttpAddress.HOST + HttpAddress.SET_ADVICE, params, new HttpCallBackListener() {
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
                        listener.onFinish("反馈成功");
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
    //上传头像
    public static void setImage(Map<String,String>params,final HttpCallBackListener listener){
        post(HttpAddress.HOST + HttpAddress.SET_IMAGE, params, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                ReturnMessage returnMessage=null;
                try {
                    returnMessage=new Gson().fromJson(result,ReturnMessage.class);
                }
                catch (JsonSyntaxException e) {
                    Log.d("gsonErr:",e.toString());
                    //错误
                }
                if(returnMessage!=null)
                {
                    if(returnMessage.isSuccess())
                    {
                        listener.onFinish("上传成功");
                    }
                    else
                    {
                        listener.onError(returnMessage.getMessage());
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
    //改密码
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
    //举报招聘者对我评价
    public static void setOtoaReport(Map<String,String>params,final HttpCallBackListener listener){
        post(HttpAddress.HOST + HttpAddress.SET_TIPOTOACOMMENT, params, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                ReturnMessage returnMessage=null;
                try {
                    returnMessage=new Gson().fromJson(result,ReturnMessage.class);
                }
                catch (JsonSyntaxException e) {
                    Log.d("gsonErr:",e.toString());
                    //错误
                }
                if(returnMessage!=null)
                {
                    if(returnMessage.isSuccess())
                    {
                        listener.onFinish("举报成功");
                    }
                    else
                    {
                        listener.onError(returnMessage.getMessage());
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
    //举报应聘者对我评价
    public static void setAtooReport(Map<String,String>params,final HttpCallBackListener listener){
        post(HttpAddress.HOST + HttpAddress.SET_TIPATOOCOMMENT, params, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                ReturnMessage returnMessage=null;
                try {
                    returnMessage=new Gson().fromJson(result,ReturnMessage.class);
                }
                catch (JsonSyntaxException e) {
                    Log.d("gsonErr:",e.toString());
                    //错误
                }
                if(returnMessage!=null)
                {
                    if(returnMessage.isSuccess())
                    {
                        listener.onFinish("举报成功");
                    }
                    else
                    {
                        listener.onError(returnMessage.getMessage());
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
                ReturnMessage returnMessage=null;
                try {
                    returnMessage=new Gson().fromJson(result,ReturnMessage.class);
                }
                catch (JsonSyntaxException e) {
                    Log.d("gsonErr:",e.toString());
                    //错误
                }
                if(returnMessage!=null)
                {
                    if(returnMessage.isSuccess())
                    {

                        listener.onFinish("选择成功");
                    }
                    else
                    {
                        listener.onError(returnMessage.getMessage());
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
    //取消应聘申请
    public static void setCancelChooseEnroll(Map<String,String>params,final HttpCallBackListener listener){
        post(HttpAddress.HOST + HttpAddress.SET_CANCEL_ENROLL, params, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                ReturnMessage returnMessage=null;
                try {
                    returnMessage=new Gson().fromJson(result,ReturnMessage.class);
                }
                catch (JsonSyntaxException e) {
                    Log.d("gsonErr:",e.toString());
                    //错误
                }
                if(returnMessage!=null)
                {
                    if(returnMessage.isSuccess())
                    {

                        listener.onFinish("取消成功");
                    }
                    else
                    {
                        listener.onError(returnMessage.getMessage());
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
                Log.d("result:",result);
                ReturnMessage returnMessage=null;
                try {
                    returnMessage=new Gson().fromJson(result,ReturnMessage.class);
                }
                catch (JsonSyntaxException e) {
                    Log.d("gsonErr:",e.toString());
                    //错误
                }
                if(returnMessage!=null)
                {
                    if(returnMessage.isSuccess())
                    {
                        listener.onFinish("申请成功");
                    }
                    else
                    {
                        listener.onError(returnMessage.getMessage());
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
                ReturnMessage returnMessage=null;
                try {
                    returnMessage=new Gson().fromJson(result,ReturnMessage.class);
                }
                catch (JsonSyntaxException e) {
                    Log.d("gsonErr:",e.toString());
                    //错误
                }
                if(returnMessage!=null)
                {
                    if(returnMessage.isSuccess())
                    {
                        listener.onFinish("取消成功");
                    }
                    else
                    {
                        listener.onError(returnMessage.getMessage());
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
        //评价
    public static void setAssessment(Map<String,String>params,final HttpCallBackListener listener){
        post(HttpAddress.HOST + HttpAddress.SET_ASSESSMENT, params, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                ReturnMessage returnMessage=null;
                try {
                    returnMessage=new Gson().fromJson(result,ReturnMessage.class);
                }
                catch (JsonSyntaxException e) {
                    Log.d("gsonErr:",e.toString());
                    //错误
                }
                if(returnMessage!=null)
                {
                    if(returnMessage.isSuccess())
                    {
                        listener.onFinish("评价成功");
                    }
                    else
                    {
                        listener.onError(returnMessage.getMessage());
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
///审核招聘不通过并提交不通过理由
    public static void changeRecruitReason(Map<String,String>params,final HttpCallBackListener listener){
        post(HttpAddress.HOST + HttpAddress.RECRUIT_REASON, params, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                ReturnMessage returnMessage=null;
                try {
                    returnMessage=new Gson().fromJson(result,ReturnMessage.class);
                }
                catch (JsonSyntaxException e) {
                    Log.d("gsonErr:",e.toString());
                    //错误
                }
                if(returnMessage!=null)
                {
                    if(returnMessage.isSuccess())
                    {
                        listener.onFinish("审核招聘成功");
                    }
                    else
                    {
                        listener.onError(returnMessage.getMessage());
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
    //更改otoa评论状态
    public static void setOtoaAssementStatus(Map<String,String>params,final HttpCallBackListener listener){
        post(HttpAddress.HOST + HttpAddress.SET_OTOA_ASSEMENT_STATUS, params, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                ReturnMessage returnMessage=null;
                try {
                    returnMessage=new Gson().fromJson(result,ReturnMessage.class);
                }
                catch (JsonSyntaxException e) {
                    Log.d("gsonErr:",e.toString());
                    //错误
                }
                if(returnMessage!=null)
                {
                    if(returnMessage.isSuccess())
                    {
                        listener.onFinish("修改成功");
                    }
                    else
                    {
                        listener.onError(returnMessage.getMessage());
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
    //更改atoo评论状态
    public static void setAtooAssementStatus(Map<String,String>params,final HttpCallBackListener listener){
        post(HttpAddress.HOST + HttpAddress.SET_ATOO_ASSEMENT_STATUS, params, new HttpCallBackListener() {
            @Override
            public void onFinish(String result) {
                ReturnMessage returnMessage=null;
                try {
                    returnMessage=new Gson().fromJson(result,ReturnMessage.class);
                }
                catch (JsonSyntaxException e) {
                    Log.d("gsonErr:",e.toString());
                    //错误
                }
                if(returnMessage!=null)
                {
                    if(returnMessage.isSuccess())
                    {
                        listener.onFinish("修改成功");
                    }
                    else
                    {
                        listener.onError(returnMessage.getMessage());
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
