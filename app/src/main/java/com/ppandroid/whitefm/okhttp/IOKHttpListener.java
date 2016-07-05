package com.ppandroid.whitefm.okhttp;

import com.ppandroid.whitefm.http.ET_HttpError;
import com.ppandroid.whitefm.http.bean.BN_BaseBody;

/**
 * Created by yeqinfu on 16-5-18.
 */
public interface IOKHttpListener<T extends BN_BaseBody> {
    HM_OKHttpTask setHttpHM();
    void onResponse(T body);
    void onFailure(ET_HttpError et_httpError);
}
