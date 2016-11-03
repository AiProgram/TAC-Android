package com.tac.iparttimejob.Class;


/**
 * Created by 守候。 on 2016/10/27.
 */

public class EmailResult {
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    private String  data;
}
