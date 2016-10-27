package com.tac.iparttimejob.NetWork.SignUp;

import com.google.gson.JsonSyntaxException;
import com.tac.iparttimejob.Class.RegisterResult;
import com.tac.iparttimejob.NetWork.Connect.HttpCallBackListener;
import com.tac.iparttimejob.NetWork.Connect.HttpPost;
import com.tac.iparttimejob.NetWork.Connect.HttpAddress;
import java.util.Map;
import com.google.gson.Gson;
/**
 * Created by wait。 on 2016/10/24.
 */

public class SignUp extends HttpPost{
    public static  void register(Map<String,String>params, final HttpCallBackListener listener){

             HttpPost.post(HttpAddress.SIGNUP_ADDRESS,params,new  HttpCallBackListener() {
            @Override
            public void onFinish(String result)
            {
                RegisterResult registerResult=null;
                try {
                  registerResult=new Gson().fromJson(result,RegisterResult.class);
                }
                catch (JsonSyntaxException e) {
                    //错误
                }
                if(registerResult!=null)
                {
                    if(registerResult.isSuccess())
                    {
                        listener.onFinish("");
                    }
                    else
                    {
                        listener.onError(registerResult.getMessage());
                    }
                }
                else listener.onError(GSON_ERR);
            }
            @Override
            public void onError(String error)
            {
                listener.onError(error);
            }

        });
    }
}
