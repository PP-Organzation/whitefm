package com.ppandroid.whitefm.okhttp;

import com.ppandroid.whitefm.http.ET_HttpError;
import com.ppandroid.whitefm.http.bean.ET_Base;
import com.ppandroid.whitefm.http.bean.HM_HttpTask;

/**
 * Created by yeqinfu on 16-5-18.
 */
public interface IOKHttpListener {
    HM_HttpTask setHttpHM();
    void onResponse(ET_Base et_base);
    void onFailure(ET_HttpError et_httpError);
}
