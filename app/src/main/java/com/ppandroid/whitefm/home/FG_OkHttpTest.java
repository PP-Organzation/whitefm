package com.ppandroid.whitefm.home;

import android.widget.TextView;

import com.antonioleiva.mvpexample.app.R;
import com.ppandroid.whitefm.base.FG_Base;
import com.ppandroid.whitefm.http.ET_HttpError;
import com.ppandroid.whitefm.http.bean.ET_Base;
import com.ppandroid.whitefm.http.bean.HM_HttpTask;
import com.ppandroid.whitefm.okhttp.IOKHttpListener;

import butterknife.Bind;

/**
 * Created by yeqinfu on 16-5-18.
 */
public class FG_OkHttpTest extends FG_Base implements IOKHttpListener{

	@Override
	public int getFragmentLayout() {
		return R.layout.fg_okhttp_test;
	}

	@Override
	public void afterViews() {
        startHttpTasK(this);
	}


    @Override
    public HM_HttpTask setHttpHM() {
        return null;
    }
    @Override
    public void onResponse(ET_Base et_base) {

    }


    @Override
    public void onFailure(ET_HttpError et_httpError) {

    }
    @Bind(R.id.tv_test)
    TextView	tv_test;

}
