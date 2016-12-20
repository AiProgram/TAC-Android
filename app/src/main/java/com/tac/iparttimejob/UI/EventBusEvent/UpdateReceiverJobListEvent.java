package com.tac.iparttimejob.UI.EventBusEvent;

/**
 * Created by AiProgram on 2016/12/20.
 */

public class UpdateReceiverJobListEvent {
    public int listType;
    public UpdateReceiverJobListEvent(int listType){
        this.listType=listType;
    }
}
