package com.ppandroid.whitefm.http.bean;

/**
 * Created by yeqinfu on 16-4-29.
 */
public class BN_BaseBody {
    public static final int resultOk = 0;

    public String eventType;

    public int apiStatus = -99; //给一个初始值，防止默认为0
    public String apiMessage;
    public int page;
    public int pageSize;
    public int pageSum;

    public static int getResultOk() {
        return resultOk;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public int getApiStatus() {
        return apiStatus;
    }

    public void setApiStatus(int apiStatus) {
        this.apiStatus = apiStatus;
    }

    public String getApiMessage() {
        return apiMessage;
    }

    public void setApiMessage(String apiMessage) {
        this.apiMessage = apiMessage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageSum() {
        return pageSum;
    }

    public void setPageSum(int pageSum) {
        this.pageSum = pageSum;
    }
}
