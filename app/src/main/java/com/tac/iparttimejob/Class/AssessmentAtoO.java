package com.tac.iparttimejob.Class;

/**
 * Created by 守候。 on 2016/11/18.
 */

public class AssessmentAtoO {
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



    private int commentid;
    private Enroll tac_applicants;
    private int ownerid;
    private String onwername;
    private int applicantid;
    private String applicantname;
    private int recruitid;
    private String atooComment;
    private int atooTipoff;
    private float atooPoint;
    private int atooStatus;

    public String getAtooComment() {
        return atooComment;
    }

    public void setAtooComment(String atooComment) {
        this.atooComment = atooComment;
    }

    public int getAtooTipoff() {
        return atooTipoff;
    }

    public void setAtooTipoff(int atooTipoff) {
        this.atooTipoff = atooTipoff;
    }

    public float getAtooPoint() {
        return atooPoint;
    }

    public void setAtooPoint(float atooPoint) {
        this.atooPoint = atooPoint;
    }

    public int getAtooStatus() {
        return atooStatus;
    }

    public void setAtooStatus(int atooStatus) {
        this.atooStatus = atooStatus;
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

    private String atooChecktime;
    private String  atootime;
}
