package com.tac.iparttimejob.Class;

import com.tac.iparttimejob.R;

/**
 * Created by 守候。 on 2016/10/26.
 */

public class RecuitResult {
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

    private String message;

    public Recuit getData() {
        return data;
    }

    public void setData(Recuit data) {
        this.data = data;
    }

    private Recuit data;


    public static class Recuit {
        public String getRecruitid() {
            return recruitid;
        }

        public void setRecruitid(String recruitid) {
            this.recruitid = recruitid;
        }

        public String getOwnerid() {
            return ownerid;
        }

        public void setOwnerid(String ownerid) {
            this.ownerid = ownerid;
        }

        public String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getWorkplace() {
            return workplace;
        }

        public void setWorkplace(String workplace) {
            this.workplace = workplace;
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

        public int getNeedpeopleNum() {
            return needpeopleNum;
        }

        public void setNeedpeopleNum(int needpeopleNum) {
            this.needpeopleNum = needpeopleNum;
        }

        public String getRequrire() {
            return requrire;
        }

        public void setRequrire(String requrire) {
            this.requrire = requrire;
        }

        public String getSingleInfo() {
            return singleInfo;
        }

        public void setSingleInfo(String singleInfo) {
            this.singleInfo = singleInfo;
        }

        public String getWorkInfo() {
            return workInfo;
        }

        public void setWorkInfo(String workInfo) {
            this.workInfo = workInfo;
        }

        public String getDisplaytime() {
            return displaytime;
        }

        public void setDisplaytime(String displaytime) {
            this.displaytime = displaytime;
        }

        public int getIsOk() {
            return isOk;
        }

        public void setIsOk(int isOk) {
            this.isOk = isOk;
        }

        public String getOktime() {
            return oktime;
        }

        public void setOktime(String oktime) {
            this.oktime = oktime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getApplypeopleNum() {
            return applypeopleNum;
        }

        public void setApplypeopleNum(int applypeopleNum) {
            this.applypeopleNum = applypeopleNum;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }
        public UserResult.User getTac_user() {
            return tac_user;
        }

        public void setTac_user(UserResult.User tac_user) {
            this.tac_user = tac_user;
        }






        private String workplace;

        public String getDealdine() {
            return dealdine;
        }

        public void setDealdine(String dealdine) {
            this.dealdine = dealdine;
        }

        private UserResult.User tac_user;
        private String recruitid;
        private String ownerid;
        private String owner;
        private String title;
        private String dealdine;
        private String phone;
        private String email;
        private int needpeopleNum;
        private String requrire;
        private String singleInfo;
        private String workInfo;
        private String displaytime;
        private int isOk;
        private String oktime;
        private int status;
        private int applypeopleNum;
        private String reason;
    }
}
