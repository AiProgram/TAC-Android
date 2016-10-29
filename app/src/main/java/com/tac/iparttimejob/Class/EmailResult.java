package com.tac.iparttimejob.Class;

import android.support.annotation.VisibleForTesting;

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

    public String getEmailData() {
        return EmailData;
    }

    public void setEmailData(String emailData) {
        EmailData = emailData;
    }

    private boolean success;
    private String  message;
    private String  EmailData;
}
