package com.tac.iparttimejob.NetWork.Connect;

import com.tac.iparttimejob.NetWork.SignUp.SignUp;

/**
 * Created by wait on 2016/10/24.
 */

public class HttpAddress {
    public static final String HOST                    ="http://123.207.96.94:8080/TACTAC/";

//登录模块
    public static final String SIGNUP_ADDRESS          ="tacpersonal/register";
    public static final String LOGIN_ADDRESSS          ="tacpersonal/login";
    public static final String EMAIL_ADDRESS           ="tacpersonal/checkEmail";
    public static final String RESETPASSWD             ="tacpersonal/updatePassword";
//创建更新简历
    public static final String SET_CTEAT_PERSONAL_RESUME         ="tacpersonal/createResume";
    public static final String SET_UPDATE_PERSONAL_RESUME       ="tacpersonal/ updateResume";
    //查看简历
    public static final String GET_PERSONAL_RESUME         ="tacpersonal/ getResume";
    //
    public static final String GET_USER_INFORMATION        ="";
    public static final String GET_USER_INFORMATION_BY_NAME        ="tacpersonal/getUserByName";
    //更新用户信息
    public static final String SET_USER_INFORMATION        ="tacpersonal/update";
    //反馈
    public static final String SET_ADVICE     ="tacpersonal/createAdvice";
    //上传头像
    public static final String SET_IMAGE     ="tacpersonal/upFile";
    //下载头像
    public static final String GET_IMAGE     ="tacpersonal/downFile";
//招聘者模块
    //创建招聘
    public static final String SET_CREAT_RECRUIT            ="tacrecruit/createRecruit";


    //查看某条招聘
    public static final String GET_RECRUIT_INFORMATION      ="tacrecruit/getRecruitByID";
    //查看招聘列表
    public static final String GET_RECUIT_LIST          ="tacrecruit/getRecruitForRecruitor";
    public static final String GET_IN_RECUIT_LIST          ="tacrecruit/getRecruiting";
    public static final String GET_NOT_RECUIT_LIST         ="tacrecruit/getRecruited";
    public static final String SET_RECRUIIT_STATUS      ="tacrecruit/changeStatusOfRecruit";
    //查看招聘者评价
    public static final String GET_OTOACOMMENT          ="tacpersonal/getAToOComment";
//举报
    public static final String SET_TIPOTOACOMMENT           ="tacpersonal/tipOToAComment";
    //查看报名列表
    public static final String GET_ENROLL_LIST              ="tacrecruit/getApplicant";
    //查看以选中的报名列表
    public static final String GET_CHOOSE_ENROLL_LIST              ="tacrecruit/ getChoosenApplicant";
    //选择应聘申请
    public static final String SET_CHOOSE_ENROLL      ="tacrecruit/chooseApplicant";




//个人模块

    //查看招聘
    public static final String GET_APPLICATION_LIST         ="tacapplication/chooseApplicant";
    //查看申请列表
    public static final String GET_CHOOSE_APPLICATION_LIST       ="tacapplication/getApplicantForApplicant";
    //查看某条招聘
    public static final String GET_APPLICATION_INFORMATION  = "tacapplication/getRecruitByID";
    //申请应聘
    public static final String SET_CREAT_APPLICATION        ="tacapplication/createApplicants";
    //取消应聘
    public static final String SET_CANCEL_APPLICATION       ="tacapplication/cancelApplicants";
    //申请被选择
    public static final String GET_CHECK_CHOOSEN            ="tacapplication/checkChoosen";
    //评价
    public static final String SET_ASSESSMENT               ="tacapplication/createComment";
    //查看应聘者评价
    public static final String GET_ATOOCOMMENT              ="tacpersonal/getAToOComment";
    //举报
    public static final String SET_TIPATOOCOMMENT           ="tacpersonal/tipAToOComment";


//管理模块

    public static final String GET_RECRUIT_LIST_FOR_MANAGER  ="tacapplication/ getRecruitForManagerAndApplicator";

    public static final String GET_ASSESSMENT_LIST_FOR_MANAGER="tacManager/getaTOoCommentForManager";
//查看评论列表
    public static final String GET_ATOO_ASSEMENT_LIST     ="tacManager/getaTOoCommentForManager";
    public static final String GET_OTOA_ASSEMENT_LIST     ="tacManager/getoTOaCommentForManager";
    public static final String GET_ATOO_ASSEMENT_LIST_BY_ID     ="tacManager/getTac_atoocommentByID";
    public static final String GET_OTOA_ASSEMENT_LIST_BY_ID     ="tacManager/getTac_otoacommentByID";
    //修改评论状态
    public static final String SET_ATOO_ASSEMENT_STATUS ="tacManager/checkaTOoComment";
    public static final String SET_OTOA_ASSEMENT_STATUS="tacManager/checkoTOaComment";
    //查看 建议

    public static final String GET_ADVICE="tacManager/getAdvice";

}

