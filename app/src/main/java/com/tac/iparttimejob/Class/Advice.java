package com.tac.iparttimejob.Class;

/**
 * Created by 守候。 on 2016/11/2.
 */

public class Advice {
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

    public suggesstion getData() {
        return data;
    }

    public void setData(suggesstion data) {
        this.data = data;
    }

    private boolean success;
    private String message;
    private suggesstion data;
    public class suggesstion{
        public int getAdviceid() {
            return adviceid;
        }

        public void setAdviceid(int adviceid) {
            this.adviceid = adviceid;
        }

        private int adviceid;

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAdvice() {
            return advice;
        }

        public void setAdvice(String advice) {
            this.advice = advice;
        }

        private int userid;
        private String username;
        private String time;
        private String phone;
        private String advice;
    }

}

