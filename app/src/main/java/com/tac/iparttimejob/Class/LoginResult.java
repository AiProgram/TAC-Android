package com.tac.iparttimejob.Class;

import java.io.Serializable;

/**
 * Created by 守候。 on 2016/10/26.
 */
//为了方便传递，我加入了Serializable接口，应该没有问题
public class LoginResult implements Serializable{


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    boolean success;
    String message;


    public LoginUser getData() {
        return data;
    }

    public void setData(LoginUser data) {
        this.data = data;
    }

    private LoginUser data;
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public static class LoginUser {


        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
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

        public String getPasswd() {
            return passwd;
        }

        public void setPasswd(String passwd) {
            this.passwd = passwd;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getKind() {
            return kind;
        }

        public void setKind(int kind) {
            this.kind = kind;
        }

        private String userid;
        private String account;
        private String nickname;
        private String name;
        private String phone;
        private String passwd;
        private String email;
        private String image;
        private int type;
        private int kind;

    }
}
