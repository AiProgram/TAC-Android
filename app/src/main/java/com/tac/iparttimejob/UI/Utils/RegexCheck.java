package com.tac.iparttimejob.UI.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by AiProgram on 2016/10/27.
 */

/**
 *
 * 这个类包含所有输入的正则表达式检测方法
 *
 */
public class RegexCheck {
    private String regex;
    private Pattern pattern;
    private Matcher matcher;

    //检查账户
    public boolean checkAccount(String Account){
        //限制为包含数字或字母或下划线,4到20个字符
        regex="^[a-zA-Z][a-zA-Z0-9_]{3,19}$";

        pattern=Pattern.compile(regex);
        matcher=pattern.matcher(Account);
        return matcher.matches();
    }

    //检查密码
    public boolean checkPassword(String Password){
        //以字母开头，长度在8到20之间，只能包含字母、数字和下划线
        regex="^[a-zA-Z]\\w{7,19}$";

        pattern=Pattern.compile(regex);
        matcher=pattern.matcher(Password);
        return matcher.matches();

    }

    //检查电话号
    public boolean checkPhoneNumbers(String PhoneNumbers){
        //国内电话
        regex="\\d{3}\\d{8}|\\d{4}\\d{7}";

        pattern=Pattern.compile(regex);
        matcher=pattern.matcher(PhoneNumbers);
        return matcher.matches();

    }

    //检查email
    public boolean checkEmail(String Email){
        //普通邮箱限制
        regex="[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";

        pattern=Pattern.compile(regex);
        matcher=pattern.matcher(Email);
        return matcher.matches();
    }

    //检查昵称
    public boolean checkNickName(String NickName){
        //包含数字汉字字母下划线，4到25字符
        regex="^[\\u4E00-\\u9FA5A-Za-z0-9_]{4,25}$";

        pattern=Pattern.compile(regex);
        matcher=pattern.matcher(NickName);
        return matcher.matches();
    }

    //检查名字
    public boolean checkName(String Name){
        //包含数字汉字字母，4到20个字符
        regex="^[\\u4E00-\\u9FA5A-Za-z0-9]{4,20}$";

        pattern=Pattern.compile(regex);
        matcher=pattern.matcher(Name);
        return matcher.matches();
    }

    //检查是否为数字
    public boolean checkNumber(String Number){
        //纯粹的数字,不带符号位等
        regex="^[0-9]*$";

        pattern=Pattern.compile(regex);
        matcher=pattern.matcher(Number);
        return matcher.matches();
    }

    //检查招聘消息标题
    public boolean checkJobTitle(String jobTitle){
        //包含数字、汉字、字母，共2到30个字符
        regex="^[\\u4E00-\\u9FA5A-Za-z0-9]{2,30}$";

        pattern=Pattern.compile(regex);
        matcher=pattern.matcher(jobTitle);
        return matcher.matches();
    }

    //检查一句招聘
    public boolean checkSingleJobContent(String singleJobContent){
        //不限内容，长度2到70
        int len=singleJobContent.length();
        if(len>=2&&len<=70)
            return true;
        else
            return false;
    }

    //检查招聘消息详情
    public boolean checkJobDetail(String jobDetail){
        //不限字符，长度10到1800
        int len=jobDetail.length();
        if (len>=10&&len<=1800)
            return true;
        else
            return false;

    }

    //检查用户一句简介
    public boolean checkSingleInfo(String singleInfo){
        //字符不限，长度2到180
        int len=singleInfo.length();
        if(len>=2&&len<=180)
            return true;
        else
            return false;

    }

    //检查擅长简介
    public boolean checkGoodAtInfo(String goodAtInfo){
        //字符不限，长度2到180
        int len=goodAtInfo.length();
        if (len>=2&&len<=180)
            return true;
        else
            return false;
    }

    //检查评论
    public boolean checkComment(String comment){

        //字符不限，长度2到480
        int len=comment.length();
        if (len>=2&&len<=480)
            return true;
        else
            return false;
    }

    //检查评分
    public boolean checkCommentGrade(String commentGrade){
        //非负整数，0到100之间
        if(checkNumber(commentGrade)){
            int grade=new Integer(commentGrade).intValue();
            if(grade>=0&&grade<=100)
                return true;
        }
        return false;
    }

    //检查用户反馈
    public boolean checkFeadback(String Feadback){
        //字符不限，长度2到980
        int len=Feadback.length();
        if (len<=980&&len>=2)
            return true;
        else
            return false;
    }
}

