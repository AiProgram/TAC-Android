package com.tac.iparttimejob.Class;

/**
 * Created by 守候。 on 2016/10/26.
 */

public class UserResult {
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private boolean success;
    private String  message;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private User user;

    public static class User {
        public String getUserID() {
            return userID;
        }

        public void setUserID(String userID) {
            this.userID = userID;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
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

        private String userID;
        private String account;
        private String nickName;
        private String name;
        private String phone;
        private String passwd;
        private String email;
        private String image;
        private int type;

        public int getKind() {
            return kind;
        }

        public void setKind(int kind) {
            this.kind = kind;
        }

        private int kind;


    }
}
