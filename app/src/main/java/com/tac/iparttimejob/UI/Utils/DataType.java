package com.tac.iparttimejob.UI.Utils;

/**
 * Created by AiProgram on 2016/10/29.
 */

public class DataType {
    //用于区分用户的类别
    public final static int ADMIN_USER=1;
    public final static int NORMAL_USER=0;

    //用来区分RecyclerView的显示大类型,
    public final static int VALID_JOB_LIST=0;
    public final static int UNVALID_JOB_LIST=1;

    public final static int UNSIGNED_JOB_LIST=0;
    public final static int SIGNED_JOB_LIST=1;

    //用来区分招聘的状态
    public final static int JOB_STATUS_CHECKING=0;
    public final static int JOB_STATUS_REJECTED=1;
    public final static int JOB_STATUS_UNDERGONING=2;
    public final static int JOB_STATUS_FINSHED=3;
    public final static int JOB_STATUS_CANCELED=4;

    //用来区分Dialog类型
    public final static int DIALOG_DATE_PICKER_DEADLINE=1;
    public final static int DIALOG_DATE_PICKER_POST_TIME=2;

    //请求招聘详情的不同
    public final static int RECRUIT_STATUS_VALID=0;
    public final static int RECRUIT_STATUS_UNVALID=1;
}