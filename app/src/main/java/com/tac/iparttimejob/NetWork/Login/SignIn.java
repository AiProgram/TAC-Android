package com.tac.iparttimejob.NetWork.Login;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tac.iparttimejob.Class.LoginResult;
import com.tac.iparttimejob.Class.RegisterResult;
import com.tac.iparttimejob.NetWork.Connect.HttpAddress;
import com.tac.iparttimejob.NetWork.Connect.HttpCallBackListener;
import com.tac.iparttimejob.NetWork.Connect.HttpPost;

import java.util.Map;

import static com.tac.iparttimejob.Class.Object.loginObject;
import static com.tac.iparttimejob.Class.Object.userObject;


/**
 * Created by 守候。 on 2016/10/26.
 */

public class SignIn extends HttpPost{
    public static  void login(Map<String,String>params, final HttpCallBackListener listener)
    {
            post(HttpAddress.HOST+HttpAddress.LOGIN_ADDRESSS, params, new HttpCallBackListener() {
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
                            Log.d("Object:",loginResult.getMessage());
                            Log.d("Object:",loginResult.getData().getPhone());
                           // loginObject.setNickname("eee");
                            loginObject.setPhone(loginResult.getData().getPhone());
                            loginObject.setAccount(loginResult.getData().getAccount());
                            loginObject.setEmail(loginResult.getData().getEmail());
                            loginObject.setImage(loginResult.getData().getImage());
                            loginObject.setKind(loginResult.getData().getKind());
                            loginObject.setName(loginResult.getData().getName());
                            loginObject.setNickname(loginResult.getData().getNickname());
                            loginObject.setPasswd(loginResult.getData().getPasswd());
                            loginObject.setType(loginResult.getData().getType());
                            loginObject.setUserid(loginResult.getData().getUserid());

                            userObject.setPhone(loginResult.getData().getPhone());
                            userObject.setAccount(loginResult.getData().getAccount());
                            userObject.setEmail(loginResult.getData().getEmail());
                            userObject.setImage(loginResult.getData().getImage());
                            userObject.setKind(loginResult.getData().getKind());
                            userObject.setName(loginResult.getData().getName());
                            userObject.setNickname(loginResult.getData().getNickname());
                            userObject.setPasswd(loginResult.getData().getPasswd());
                            userObject.setType(loginResult.getData().getType());
                            userObject.setUserid(loginResult.getData().getUserid());
                            listener.onFinish("登录成功");

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

