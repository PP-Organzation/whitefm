package com.ppandroid.whitefm.okhttp;

import android.content.Context;

import com.ppandroid.whitefm.http.bean.HM_Base;
import com.ppandroid.whitefm.http.bean.HttpType;

/**
 * Created by yeqinfu on 16-5-23.
 */
public class HM_OKHttpTask {
	/** task id */
	public int		taskId;
	/** 上下文对象，不能为空 */
	public Context	context;

	/** HTTP请求方式：GET,POST,DELETE,PUT等 */
	public HttpType	httpType	= HttpType.GET;

	/** HTTP请求的URL，不能为空 */
	public String	url;
    /**	HTTP请求需要的参数，最终都会通过工具转换成TreeMap，可以为空	*/
    public HM_Base httpParams;

    public HM_OKHttpTask(int taskId, Context context, HttpType httpType, String url) {
        this.taskId = taskId;
        this.context = context;
        this.httpType = httpType;
        this.url = url;
    }
}
