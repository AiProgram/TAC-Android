package com.tac.iparttimejob.NetWork.Login;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tac.iparttimejob.Class.LoginResult;
import com.tac.iparttimejob.Class.RegisterResult;
import com.tac.iparttimejob.NetWork.Connect.HttpAddress;
import com.tac.iparttimejob.NetWork.Connect.HttpCallBackListener;
import com.tac.iparttimejob.NetWork.Connect.HttpPost;

import java.util.Map;

/**
 * Created by 守候。 on 2016/10/26.
 */

public class SignIn extends HttpPost{
    public static  void login(Map<String,String>params, final HttpCallBackListener listener)
    {
            post(HttpAddress.LOGIN_ADDRESSS, params, new HttpCallBackListener() {
                @Override
                public void onFinish(String result) {
                    LoginResult loginResult=null;
                    try {
                        loginResult=new Gson().fromJson(result,LoginResult.class);
                    }
                    catch (JsonSyntaxException e) {
                        //错误
                    }
                    if(loginResult!=null)
                    {
                        if(loginResult.isSuccess())
                        {
                            listener.onFinish("");
                        }
                        else
                        {
                            listener.onError(loginResult.getMessage());
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

