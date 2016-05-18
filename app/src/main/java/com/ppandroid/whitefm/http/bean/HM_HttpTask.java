package com.ppandroid.whitefm.http.bean;

import android.content.Context;

/**
 * Created by yeqinfu on 16-4-29.
 */
public class HM_HttpTask {
    /**	上下文对象，不能为空	*/
    public Context context;

    /**	HTTP请求方式：GET,POST,DELETE,PUT等*/
    public HttpType httpType=HttpType.GET;

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
    /**
     *
     * @param context
     * @param httpType
     * @param url
     * @param httpParams
     * @param etHttpResponse
     */
    public HM_HttpTask(Context context, HttpType httpType, String url, HM_Base httpParams,ET_Base etHttpResponse){
        this.context = context;
        this.httpType = httpType;
        this.url = url;
        this.httpParams = httpParams;
        this.needSaveDB = false;
        this.needGetDBData = false;
        this.etHttpResponse = etHttpResponse;
        this.etDataBase = null;
        this.etSpecialLogic = null;
        this.dBquery = null;
    }
    /**
     * @param context 不能为NULL
     * @param httpType 请求方式，可以为NULL，默认为GET
     * @param url 请求URL，不能为NULL
     * @param httpParams 请求参数，可以为NULL
     * @param needSaveDB 是否要存储DB，如果不要缓存，则为false
     * @param needGetDBData 有网络的情况是否需要先显示缓存，如果不需要，则为false
     * @param etHttpResponse HTTP请求结果存放对象
     * @param etDataBase 缓存结果存放对象
     * @param etSpecialLogic 特殊业务逻辑，目前只有TOKEN失效，如果希望TOKEN失效跳转到登陆界面，则可以传NULL
     * @param dBquery 查询DB数据需要的条件，目前对passportID做了统一处理，如果只要根据passportID来查询，则可以传NULL
     * @author LuoZheng
     * @date 2015年7月13日 下午1:56:45
     */
    public HM_HttpTask(Context context, HttpType httpType, String url, HM_Base httpParams, boolean needSaveDB, boolean needGetDBData,
                       ET_Base etHttpResponse, ET_DataBase etDataBase, ET_SpecialLogic etSpecialLogic, HM_DBquery dBquery) {
        super();
        this.context = context;
        this.httpType = httpType;
        this.url = url;
        this.httpParams = httpParams;
        this.needSaveDB = needSaveDB;
        this.needGetDBData = needGetDBData;
        this.etHttpResponse = etHttpResponse;
        this.etDataBase = etDataBase;
        this.etSpecialLogic = etSpecialLogic;
        this.dBquery = dBquery;
    }

    /**
     * 没有缓存数据
     * @param context
     * @param httpType
     * @param url
     * @param httpParams
     * @param etHttpResponse
     * @param etSpecialLogic
     * @author LuoZheng
     * @date 2015年7月13日 下午2:07:41
     */
    public HM_HttpTask(Context context, HttpType httpType, String url, HM_Base httpParams,ET_Base etHttpResponse,ET_SpecialLogic etSpecialLogic) {
        this(context,httpType,url,httpParams,false,false,etHttpResponse,null,etSpecialLogic,null);
    }

    public HM_HttpTask(Context context, HttpType httpType, String url) {
        this(context,httpType,url,null,false,false,null,null,null,null);
    }

}
