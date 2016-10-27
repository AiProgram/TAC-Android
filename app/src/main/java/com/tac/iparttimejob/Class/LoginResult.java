package com.tac.iparttimejob.Class;

/**
 * Created by 守候。 on 2016/10/26.
 */

public class LoginResult {


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    boolean success;
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PersonalResume getPersonalResume() {
        return personalResume;
    }

    public void setPersonalResume(PersonalResume personalResume) {
        this.personalResume = personalResume;
    }

    private PersonalResume personalResume;
    class PersonalResume{
        private String userID;
        private String name;
        private String phoneNumber;
        private String eMail;
        private String information;
        private String informationDetail;
        private String doProject;
        private String goodAt;


    }
}
