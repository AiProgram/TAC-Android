package com.tac.iparttimejob.Class;

import java.util.List;

/**
 * Created by 守候。 on 2016/10/27.
 */
//List 需要清空时请在主程序行清空.
public class Object {

    //用户信息返回
    public static User                  userObject;
    //个人资料
    public static ResumeResult.Resume   resumeObject;
    //招聘信息
    public static RecuitResult.Recuit   recuitObject;
    //登录信息含个人资料
    public static User                   loginObject;
    //邮件验证码
    public static String                emailData;
    //正在进行的招聘列表
    public static List<RecuitResult.Recuit> inRecuitObjectList;
    //失效的招聘列表
    public static List<RecuitResult.Recuit> notRecuitObjectList;
    //报名列表
    public static List<Enroll>         enrollObjectList;
    //选中的报名列表
    public static List<Enroll>         enrollChooseObjectList;
    //
    //查看招聘列表
    public static List<Application>         chooseApplicationList;
    //查看申请列表
    public static List<Application>         applicationObjectList;
    //查看某个招聘
    public static RecuitResult.Recuit             applicationObject;




}
