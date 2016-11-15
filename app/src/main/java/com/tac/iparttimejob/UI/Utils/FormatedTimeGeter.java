package com.tac.iparttimejob.UI.Utils;

import java.text.SimpleDateFormat;

/**
 * Created by AiProgram on 2016/11/15.
 */

public class FormatedTimeGeter {
    //获取形如"年-月-日"的日期
    public static String getFormatedDate(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String date=sdf.format(new java.util.Date());
        return date;
    }
}
