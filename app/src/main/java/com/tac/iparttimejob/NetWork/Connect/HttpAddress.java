package com.tac.iparttimejob.NetWork.Connect;

import com.tac.iparttimejob.NetWork.SignUp.SignUp;

/**
 * Created by wait on 2016/10/24.
 */

public class HttpAddress {
    public static final String HOST                    ="http://123.207.96.94:8080/TACTAC/";
    public static final String SIGNUP_ADDRESS          ="tacpersonal/register";
    public static final String LOGIN_ADDRESSS          ="tacpersonal/login";
    public static final String EMAIL_ADDRESS           ="tacpersonal/checkEmail";
    public static final String RESETPASSWD             ="";

    public static final String SET_CTEAT_PERSONAL_RESUME         ="tacpersonal/createResume";
    public static final String SET_UPDATE_PERSONAL_RESUME       ="tacpersonal/ updateResume";
    public static final String GET_PERSONAL_RESUME         ="tacpersonal/ getResume";
    public static final String GET_USER_INFORMATION        ="";
    public static final String SET_USER_INFORMATION        ="";
    public static final String SET_CREAT_RECRUIT            ="tacrecruit/createRecruit";

    public static final String SET_RECRUIT_INFORMATION      ="tacrecruit/createRecruit";
    //查看某条招聘
    public static final String GET_RECRUIT_INFORMATION      ="tacrecruit/getRecruitByID";
    //查看招聘列表
    public static final String GET_IN_RECUIT_LIST          ="tacrecruit/getRecruiting";
    public static final String GET_NOT_RECUIT_LIST         ="tacrecruit/getRecruited";
    public static final String SET_RECRUIIT_STATUS      ="tacrecruit/changeStatusOfRecruit";
    //查看报名列表
    public static final String GET_ENROLL_LIST              ="tacrecruit/getApplicant";
    //查看以选中的报名列表
    public static final String GET_CHOOSE_ENROLL_LIST              ="tacrecruit/ getChoosenApplicant";
    //选择应聘申请
    public static final String SET_CHOOSE_ENROLL      ="tacrecruit/chooseApplicant";

//个人模块

    //查看申请列表
    public static final String GET_APPLICATION_LIST         ="tacapplication/getApplicantForApplicant";
    //查看招聘
    public static final String GET_CHOOSE_APPLICATION       ="tacapplication/chooseApplicant";
    //查看某条招聘
    public static final String GET_APPLICATION_INFORMATION  = "tacapplication/getRecruitByID";
    //申请应聘
    public static final String SET_CREAT_APPLICATION        ="tacapplication/createApplicants";
    public static final String SET_CANCEL_APPLICATION       ="tacapplication/cancelApplicants";
    public static final String GET_CHECK_CHOOSEN            ="tacapplication/checkChoosen";




}

