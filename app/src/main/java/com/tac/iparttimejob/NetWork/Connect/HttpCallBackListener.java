package com.tac.iparttimejob.NetWork.Connect;

/**
 * Created by wait。 on 2016/10/24.
 * http回调函数
 */
public interface HttpCallBackListener {
    void onFinish(String result);
    void onError(String error);
}