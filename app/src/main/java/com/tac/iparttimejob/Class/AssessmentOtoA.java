package com.tac.iparttimejob.Class;

import org.w3c.dom.ProcessingInstruction;

/**
 * Created by 守候。 on 2016/11/2.
 */

public class AssessmentOtoA {


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
    private int otoaTipoff ;
    private  String otoaComment;

    public int getOtoaTipoff() {
        return otoaTipoff;
    }

    public void setOtoaTipoff(int otoaTipoff) {
        this.otoaTipoff = otoaTipoff;
    }

    public String getOtoaComment() {
        return otoaComment;
    }

    public void setOtoaComment(String otoaComment) {
        this.otoaComment = otoaComment;
    }

    public float getOtoaPoint() {
        return otoaPoint;
    }

    public void setOtoaPoint(float otoaPoint) {
        this.otoaPoint = otoaPoint;
    }

    public int getOtoaStatus() {
        return otoaStatus;
    }

    public void setOtoaStatus(int otoaStatus) {
        this.otoaStatus = otoaStatus;
    }

    public String getOtoaChecktime() {
        return otoaChecktime;
    }

    public void setOtoaChecktime(String otoaChecktime) {
        this.otoaChecktime = otoaChecktime;
    }

    public String getOtoatime() {
        return otoatime;
    }

    public void setOtoatime(String otoatime) {
        this.otoatime = otoatime;
    }

    private float otoaPoint;

    private int otoaStatus;
    private  String otoaChecktime;
    private  String otoatime;


}
