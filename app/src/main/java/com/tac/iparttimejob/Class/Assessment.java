package com.tac.iparttimejob.Class;

import org.w3c.dom.ProcessingInstruction;

/**
 * Created by 守候。 on 2016/11/2.
 */

public class Assessment {


    public int getCommentid() {
        return commentid;
    }

    public void setCommentid(int commentid) {
        this.commentid = commentid;
    }

    public Enroll getTac_applicants() {
        return tac_applicants;
    }

    public void setTac_applicants(Enroll tac_applicants) {
        this.tac_applicants = tac_applicants;
    }

    public int getOwnerid() {
        return ownerid;
    }

    public void setOwnerid(int ownerid) {
        this.ownerid = ownerid;
    }

    public String getOnwername() {
        return onwername;
    }

    public void setOnwername(String onwername) {
        this.onwername = onwername;
    }

    public int getApplicantid() {
        return applicantid;
    }

    public void setApplicantid(int applicantid) {
        this.applicantid = applicantid;
    }

    public String getApplicantname() {
        return applicantname;
    }

    public void setApplicantname(String applicantname) {
        this.applicantname = applicantname;
    }

    public int getRecruitid() {
        return recruitid;
    }

    public void setRecruitid(int recruitid) {
        this.recruitid = recruitid;
    }

    public int getAtooTipoff() {
        return atooTipoff;
    }

    public void setAtooTipoff(int atooTipoff) {
        this.atooTipoff = atooTipoff;
    }

    public String getAtooComment() {
        return AtooComment;
    }

    public void setAtooComment(String atooComment) {
        AtooComment = atooComment;
    }

    public float getAtooPoint() {
        return atooPoint;
    }

    public void setAtooPoint(float atooPoint) {
        this.atooPoint = atooPoint;
    }

    public int getAtppStatus() {
        return atppStatus;
    }

    public void setAtppStatus(int atppStatus) {
        this.atppStatus = atppStatus;
    }

    public String getAtooChecktime() {
        return atooChecktime;
    }

    public void setAtooChecktime(String atooChecktime) {
        this.atooChecktime = atooChecktime;
    }

    public String getAtootime() {
        return atootime;
    }

    public void setAtootime(String atootime) {
        this.atootime = atootime;
    }

    private int commentid;
    private Enroll tac_applicants;
    private int ownerid;
    private String onwername;
    private int applicantid;
    private String applicantname;
    private int recruitid;
    private int atooTipoff ;
    private  String AtooComment;
    private float atooPoint;
    private int atppStatus;
    private  String atooChecktime;
    private  String atootime;


}
