package com.ppandroid.whitefm.okhttp;

import com.ppandroid.whitefm.http.ET_HttpError;
import com.ppandroid.whitefm.http.bean.BN_BaseBody;
import com.ppandroid.whitefm.http.bean.HM_Base;

/**
 * Created by yeqinfu on 16-5-18.
 */
public interface IOKHttpListener {
    HM_Base setHttpHM();
    void onResponse(BN_BaseBody body);
    void onFailure(ET_HttpError et_httpError);
}
