package com.ppandroid.whitefm.home;

import android.widget.TextView;

import com.antonioleiva.mvpexample.app.R;
import com.ppandroid.whitefm.base.FG_Base;
import com.ppandroid.whitefm.http.ET_HttpError;
import com.ppandroid.whitefm.http.bean.BN_BaseBody;
import com.ppandroid.whitefm.http.bean.HM_Base;
import com.ppandroid.whitefm.okhttp.IOKHttpListener;

import java.io.IOException;

import butterknife.Bind;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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
		//创建okHttpClient对象
		OkHttpClient mOkHttpClient = new OkHttpClient();
		//创建一个Request
		final Request request = new Request.Builder().url("http://h5-api.pre.qw.com/h5/maicromall/order/queryLC").build();
		//new call
		Call call = mOkHttpClient.newCall(request);
		//请求加入调度
		call.enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {

			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
                tv_test.setText(response.body().string());

			}
		});

	}


    @Override
    public HM_Base setHttpHM() {
        return null;
    }

    @Override
    public void onResponse(BN_BaseBody body) {

    }

    @Override
    public void onFailure(ET_HttpError et_httpError) {

    }
    @Bind(R.id.tv_test)
    TextView	tv_test;

}
