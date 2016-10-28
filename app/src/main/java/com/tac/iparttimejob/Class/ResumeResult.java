package com.tac.iparttimejob.Class;

/**
 * Created by 守候。 on 2016/10/26.
 */

public class ResumeResult {

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    private boolean success;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String  message;





public class  Resume {

    public String getResumeID() {
        return resumeID;
    }

    public void setResumeID(String resumeID) {
        this.resumeID = resumeID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getNikeName() {
        return nikeName;
    }

    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getHadDone() {
        return hadDone;
    }

    public void setHadDone(int hadDone) {
        this.hadDone = hadDone;
    }

    public String getGoodAt() {
        return goodAt;
    }

    public void setGoodAt(String goodAt) {
        this.goodAt = goodAt;
    }

    public String getSingleResume() {
        return singleResume;
    }

    public void setSingleResume(String singleResume) {
        this.singleResume = singleResume;
    }

    public String getDetailResume() {
        return detailResume;
    }

    public void setDetailResume(String detailResume) {
        this.detailResume = detailResume;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String resumeID;
    private String userID;
    private String nikeName;
    private String name;
    private String phone;
    private String email;
    private int hadDone;
    private String goodAt;
    private String singleResume;
    private String detailResume;
    private String url;
}

}
