package com.tac.iparttimejob.Class;

import java.util.List;

/**
 * Created by 守候。 on 2016/11/2.
 */

public class AssessmentList {
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
    private String message;


    public List<Assessment> getData() {
        return data;
    }

    public void setData(List<Assessment> data) {
        this.data = data;
    }

    private List<Assessment> data;
}
