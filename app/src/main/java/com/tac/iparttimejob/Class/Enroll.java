package com.tac.iparttimejob.Class;

/**
 * Created by 守候。 on 2016/10/31.
 */

public class Enroll {
    public int getApplicantsid() {
        return applicantsid;
    }

    public void setApplicantsid(int applicantsid) {
        this.applicantsid = applicantsid;
    }

    public String getApplicantname() {
        return applicantname;
    }

    public void setApplicantname(String applicantname) {
        this.applicantname = applicantname;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public float getPoint() {
        return point;
    }

    public void setPoint(float point) {
        this.point = point;
    }

    public String getSingleresume() {
        return singleresume;
    }

    public void setSingleresume(String singleresume) {
        this.singleresume = singleresume;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getApplicanttime() {
        return applicanttime;
    }

    public void setApplicanttime(String applicanttime) {
        this.applicanttime = applicanttime;
    }

    public RecuitResult.Recuit getRecuit() {
        return recuit;
    }

    public void setRecuit(RecuitResult.Recuit recuit) {
        this.recuit = recuit;
    }

    public int getOwnerid() {
        return ownerid;
    }

    public void setOwnerid(int ownerid) {
        this.ownerid = ownerid;
    }

    public String getOwnwername() {
        return ownwername;
    }

    public void setOwnwername(String ownwername) {
        this.ownwername = ownwername;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSingleinfo() {
        return singleinfo;
    }

    public void setSingleinfo(String singleinfo) {
        this.singleinfo = singleinfo;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public int getChoosen() {
        return choosen;
    }

    public void setChoosen(int choosen) {
        this.choosen = choosen;
    }



    private int applicantsid;
    private String applicantname;
    private User user;
    private float point;
    private String singleresume;
    private String image;
    private String applicanttime;
    private RecuitResult.Recuit recuit;
    private int ownerid;
    private String ownwername;
    private String title;
    private String singleinfo;
    private String deadline;
    private int choosen;

}
