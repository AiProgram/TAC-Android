package com.tac.iparttimejob.NetWork.Login;

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
                            loginObject.setPhone(loginResult.getLoginUser().getPhone());
                            loginObject.setAccount(loginResult.getLoginUser().getAccount());
                            loginObject.setEmail(loginResult.getLoginUser().getEmail());
                            loginObject.setImage(loginResult.getLoginUser().getImage());
                            loginObject.setKind(loginResult.getLoginUser().getKind());
                            loginObject.setName(loginResult.getLoginUser().getName());
                            loginObject.setNickName(loginResult.getLoginUser().getNickName());
                            loginObject.setPasswd(loginResult.getLoginUser().getPasswd());
                            loginObject.setType(loginResult.getLoginUser().getType());
                            loginObject.setUserID(loginResult.getLoginUser().getUserID());

                            userObject.setPhone(loginResult.getLoginUser().getPhone());
                            userObject.setAccount(loginResult.getLoginUser().getAccount());
                            userObject.setEmail(loginResult.getLoginUser().getEmail());
                            userObject.setImage(loginResult.getLoginUser().getImage());
                            userObject.setKind(loginResult.getLoginUser().getKind());
                            userObject.setName(loginResult.getLoginUser().getName());
                            userObject.setNickName(loginResult.getLoginUser().getNickName());
                            userObject.setPasswd(loginResult.getLoginUser().getPasswd());
                            userObject.setType(loginResult.getLoginUser().getType());
                            userObject.setUserID(loginResult.getLoginUser().getUserID());
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

