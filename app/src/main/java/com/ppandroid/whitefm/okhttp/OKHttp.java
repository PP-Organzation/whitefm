package com.ppandroid.whitefm.okhttp;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by yeqinfu on 16-5-18.
 */
public class OKHttp {
	public static void startHttpTasK(IOKHttpListener listener) {
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

			}
		});
	}
}
