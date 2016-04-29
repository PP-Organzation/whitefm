package com.ppandroid.whitefm.http.bean;

import android.content.Context;

/**
 * Created by yeqinfu on 16-4-29.
 */
public class HM_HttpTask {
    /**	上下文对象，不能为空	*/
    public Context context;

    /**	HTTP请求方式：GET,POST,DELETE,PUT等*/
    public HttpType httpType;

    /**	HTTP请求的URL，不能为空	*/
    public String url;

    /**	HTTP请求需要的参数，最终都会通过工具转换成TreeMap，可以为空	*/
    public HM_Base httpParams;

    /**	是否要存DB	*/
    public boolean needSaveDB;

    /** 在有网络的情况下是否要将缓存中的数据POST给UI	*/
    public boolean needGetDBData;

    /** 封装取得的HTTP数据对象，该对象保存业务bean 	*/
    public ET_Base etHttpResponse;

    /** 封装取得的缓存数据对象，该对象保存业务bean 	*/
    public ET_DataBase etDataBase;

    /**	特殊逻辑处理参数：比如token失效是否要跳到登陆界面了等等		*/
    public ET_SpecialLogic etSpecialLogic;

    /**	DB查询数据依赖条件	*/
    public HM_DBquery dBquery;
}
