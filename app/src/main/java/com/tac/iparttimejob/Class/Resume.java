package com.tac.iparttimejob.Class;

/**
 * Created by 守候。 on 2016/11/1.
 */

public class Resume {
    public UserResult.User getTac_user() {
        return tac_user;
    }

    public void setTac_user(UserResult.User tac_user) {
        this.tac_user = tac_user;
    }

    private UserResult.User tac_user;

    public int getResumeid() {
        return resumeid;
    }

    public void setResumeid(int resumeid) {
        this.resumeid = resumeid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    private int resumeid;
    private String nickname;
    private  String name;
    private String phone;
    private String email;
    private String singleResume;
    private String detailResume;

    public String getHaddone() {
        return haddone;
    }

    public void setHaddone(String haddone) {
        this.haddone = haddone;
    }

    public String getGoodat() {
        return goodat;
    }

    public void setGoodat(String goodat) {
        this.goodat = goodat;
    }

    private String haddone;
    private String goodat;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    private String url;
    private int kind;
}
