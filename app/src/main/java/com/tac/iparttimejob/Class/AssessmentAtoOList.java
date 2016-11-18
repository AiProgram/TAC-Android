package com.tac.iparttimejob.Class;

import java.util.List;

/**
 * Created by 守候。 on 2016/11/18.
 */

public class AssessmentAtoOList {
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


    public List<AssessmentAtoO> getData() {
        return data;
    }

    public void setData(List<AssessmentAtoO> data) {
        this.data = data;
    }

    private List<AssessmentAtoO> data;
}
