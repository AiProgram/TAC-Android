package com.tac.iparttimejob.Class;

import java.util.List;

/**
 * Created by 守候。 on 2016/10/31.
 */

public class EnrollList {
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

    public List<Enroll> getData() {
        return data;
    }

    public void setData(List<Enroll> data) {
        this.data = data;
    }

    private List<Enroll> data;
}
