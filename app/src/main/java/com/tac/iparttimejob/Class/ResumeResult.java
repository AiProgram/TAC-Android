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
    Resume data;

    public Resume getData() {
        return data;
    }

    public void setData(Resume data) {
        this.data = data;
    }
}
