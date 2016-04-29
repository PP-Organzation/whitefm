package com.ppandroid.whitefm.http;

import com.ppandroid.whitefm.http.bean.HM_HttpTask;
import com.ppandroid.whitefm.http.bean.HttpType;

import java.util.TreeMap;

/**
 * Created by yeqinfu on 16-4-29.
 */
public interface IHttpTask {
     void doImageTask(HM_HttpTask httpTaskParams);

     void doTask(HM_HttpTask httpTaskParams);
    /**
     * Android和js交互的http请求处理
     */
     void doTask(TreeMap<String, Object> httpParams, HttpType httpType, String url);
}
