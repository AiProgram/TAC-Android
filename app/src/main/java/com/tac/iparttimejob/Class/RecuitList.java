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

    public List<RecuitResult.Recuit> getRecuitList() {
        return recuitList;
    }

    public void setRecuitList(List<RecuitResult.Recuit> recuitList) {
        this.recuitList = recuitList;
    }

    List<RecuitResult.Recuit> recuitList;

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
