package com.ppandroid.whitefm.db;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table DBMODEL.
 */
public class DBModel {

    private Long id;
    private String httpUrl;
    private String extraKey;
    private String json;
    private java.util.Date lastestTime;

    public DBModel() {
    }

    public DBModel(Long id) {
        this.id = id;
    }

    public DBModel(Long id, String httpUrl, String extraKey, String json, java.util.Date lastestTime) {
        this.id = id;
        this.httpUrl = httpUrl;
        this.extraKey = extraKey;
        this.json = json;
        this.lastestTime = lastestTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHttpUrl() {
        return httpUrl;
    }

    public void setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl;
    }

    public String getExtraKey() {
        return extraKey;
    }

    public void setExtraKey(String extraKey) {
        this.extraKey = extraKey;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public java.util.Date getLastestTime() {
        return lastestTime;
    }

    public void setLastestTime(java.util.Date lastestTime) {
        this.lastestTime = lastestTime;
    }

}
