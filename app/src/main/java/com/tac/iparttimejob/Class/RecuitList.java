package com.tac.iparttimejob.Class;

import java.lang.*;
import java.lang.Object;
import java.util.List;

/**
 * Created by 守候。 on 2016/10/31.
 */

public class RecuitList {
    private boolean success;
    private String  message;


    public List<RecuitResult.Recuit> getData() {
        return data;
    }

    public void setData(List<RecuitResult.Recuit> data) {
        this.data = data;
    }

    List<RecuitResult.Recuit> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
