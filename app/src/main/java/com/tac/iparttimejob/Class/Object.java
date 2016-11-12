package com.tac.iparttimejob.Class;

import java.util.List;

/**
 * Created by 守候。 on 2016/10/27.
 */
//List 需要清空时请在主程序行清空.
public class Object {

    //用户信息返回
    public static UserResult.User       userObject=null;
    public static UserResult.User       getUserObject=null;
    //个人资料
    public static Resume             resumeObject=null;
    //招聘信息
    public static RecuitResult.Recuit   recuitObject=null;
    //登录信息含个人资料
    public static LoginResult.LoginUser loginObject;
    //邮件验证码
    public static String                emailData;
    public static String                userImage;
    //正在进行的招聘列表
    public static List<RecuitResult.Recuit> recuitObjectList;



    public static List<RecuitResult.Recuit> inRecuitObjectList;
    //失效的招聘列表
    public static List<RecuitResult.Recuit> notRecuitObjectList;
    //报名列表
    public static List<Enroll>         enrollObjectList;
    //选中的报名列表
    public static List<Enroll>         enrollChooseObjectList;
    //

    //个人
    //查看招聘列表
    public static List<RecuitResult.Recuit>  chooseApplicationList;

    //查看申请列表
    public static List<Application>         applicationObjectList;
    //查看某个招聘
    public static RecuitResult.Recuit             applicationObject;
    //查看应聘者对我评价
    public static List<Assessment>   atooCommentObjectList;
    //查看招聘者对我评价
    public static List<Assessment>   otoaCommentObjectList;
    //管理员
    //查看招聘信息
    public static List<RecuitResult.Recuit> RecuitObjectlistForManager;
    //查看atoo评论列表
    public static List<Assessment>   atooAssessmentObjectList;
    public static List<Assessment>   otoaAssessmentObjectList;
    public static List<Assessment>   atooAssessmentByIDObjectList;
    public static List<Assessment>   otoaAssessmentByIDObjectList;
    public static Advice.suggesstion suggesstion;




}
