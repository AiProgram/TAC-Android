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
    private Recuit recuit;

    public Recuit getRecuit() {
        return recuit;
    }

    public void setRecuit(Recuit recuit) {
        this.recuit = recuit;
    }

    public class Recuit {
        private String recruitID;
        private String ownerID;
        private String owner;
        private String title;

        public String getRecruitID() {
            return recruitID;
        }

        public void setRecruitID(String recruitID) {
            this.recruitID = recruitID;
        }

        public String getOwnerID() {
            return ownerID;
        }

        public void setOwnerID(String ownerID) {
            this.ownerID = ownerID;
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

        public String getWorkPlace() {
            return workPlace;
        }

        public void setWorkPlace(String workPlace) {
            this.workPlace = workPlace;
        }

        public String getDeadLine() {
            return deadLine;
        }

        public void setDeadLine(String deadLine) {
            this.deadLine = deadLine;
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

        public int getNeedPeopleNum() {
            return needPeopleNum;
        }

        public void setNeedPeopleNum(int needPeopleNum) {
            this.needPeopleNum = needPeopleNum;
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

        public String getDisplayTime() {
            return displayTime;
        }

        public void setDisplayTime(String displayTime) {
            this.displayTime = displayTime;
        }

        public int getIsOK() {
            return isOK;
        }

        public void setIsOK(int isOK) {
            this.isOK = isOK;
        }

        public String getOkTime() {
            return okTime;
        }

        public void setOkTime(String okTime) {
            this.okTime = okTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getApplyPeopleNum() {
            return applyPeopleNum;
        }

        public void setApplyPeopleNum(int applyPeopleNum) {
            this.applyPeopleNum = applyPeopleNum;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        private String workPlace;
        private String deadLine;
        private String phone;
        private String email;
        private int needPeopleNum;
        private String requrire;
        private String singleInfo;
        private String workInfo;
        private String displayTime;
        private int isOK;
        private String okTime;
        private int status;
        private int applyPeopleNum;
        private String reason;
    }
}
