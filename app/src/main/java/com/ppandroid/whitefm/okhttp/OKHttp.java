package com.ppandroid.whitefm.okhttp;

import com.antonioleiva.mvpexample.app.R;
import com.ppandroid.whitefm.MApplication;
import com.ppandroid.whitefm.http.ET_HttpError;
import com.ppandroid.whitefm.utils.Utils_ObjToMap;

import org.apache.http.protocol.HTTP;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.TreeMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by yeqinfu on 16-5-18.
 */
public class OKHttp {
    private volatile  static OKHttp instance;
    public OkHttpClient	client	= new OkHttpClient();
    private OKHttp(){

    }
    public static OKHttp getInstance(){
        if (instance==null){
            synchronized ((OKHttp.class)){
                if (instance==null){
                    instance=new OKHttp();
                }
            }
        }
        return instance;
    }


	public void startHttpTasK(IOKHttpListener listener) {
		if (listener.setHttpHM() == null) {
			throw new IllegalArgumentException("Logic Bean cannot be null !");
		}
        TreeMap<String, Object> params = Utils_ObjToMap.obj2map(listener.setHttpHM().httpParams);


        Response response=null;
		try {
			switch (listener.setHttpHM().httpType) {
			case GET:
				response=execGetTask(syncGet(listener.setHttpHM().url,params));
				break;
			case POST_KEY_VALUE:

				break;
			default:
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
        new ET_HttpError(listener.setHttpHM().etHttpResponse.taskId,
                Integer.parseInt(MApplication.getContext().getResources().getString(R.string.errorcode_9002)),
                MApplication.getContext().getResources().getString(R.string.errorcode_9002_desc));



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

    /**
     * get 请求
     * @param url
     * @return
     * @throws IOException
     */
	private Response execGetTask(String url) throws IOException {
		Request request = new Request.Builder().url(url).build();
		Response response = client.newCall(request).execute();
		return response;
	}



    /**
     * @Description GET获取（同步方式）
     * get 拼接
     */
    public String syncGet(final String urlStr, final TreeMap<String, Object> params) {
        StringBuffer buffer = new StringBuffer(urlStr);
        if (params != null && params.size() > 0) {
            buffer.append("?");
            Iterator<String> iterator = params.keySet().iterator();
            while (iterator.hasNext()) {
                String paramKey = iterator.next();
                String paramsValue = String.valueOf(params.get(paramKey));
                try {
                    buffer.append(paramKey).append("=").append(URLEncoder.encode(paramsValue, HTTP.UTF_8)).append("&");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            buffer.deleteCharAt(buffer.length() - 1);
        }
        return buffer.toString();
    }

}
