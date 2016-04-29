package com.ppandroid.whitefm.http.bean;

/**
 * Created by yeqinfu on 16-4-29.
 */
public class ET_Base {
    public int taskId;

    public BN_BaseBody httpResponse;

    public ET_Base(int taskId, BN_BaseBody httpResponse) {
        super();
        this.taskId = taskId;
        this.httpResponse = httpResponse;
    }
}
