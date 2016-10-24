package com.tac.iparttimejob.NetWork.SignUp;

import com.tac.iparttimejob.NetWork.Connect.HttpCallBackListener;
import com.tac.iparttimejob.NetWork.Connect.HttpPost;
import com.tac.iparttimejob.NetWork.Connect.HttpAddress;
import java.util.Map;

/**
 * Created by wait。 on 2016/10/24.
 */

public class SignUp extends HttpPost{
    public static  void register(Map<String,String>params, final HttpCallBackListener listener){

        HttpPost.post(HttpAddress.SIGNUP_ADDRESS,params,new  HttpCallBackListener(){
            @Override
            public void onFinish(String result)
            {

            }
            @Override
            public void onError(String error)
            {

            }

        });
    }
}
