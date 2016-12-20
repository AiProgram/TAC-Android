package com.tac.iparttimejob.UI.EventBusEvent;

/**
 * Created by AiProgram on 2016/12/20.
 */

public class UpdateGiverJobListEvent {
    public UpdateGiverJobListEvent(int listType){
        this.listType=listType;
    }
    public int listType;
}
