package com.ppandroid.whitefm.okhttp;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.ppandroid.whitefm.http.bean.BN_BaseBody;
import com.ppandroid.whitefm.http.bean.ET_Base;
import com.ppandroid.whitefm.http.utils.HttpUtils;
import com.ppandroid.whitefm.utils.Utils_ObjToMap;

import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by yeqinfu on 16-5-18.
 */
public class OKHttp {
	private volatile static OKHttp	instance;
	public OkHttpClient				client;
	private Handler					handler;

	private OKHttp() {
		client = new OkHttpClient();
		handler = new Handler(Looper.getMainLooper());
	}

	public static OKHttp getInstance() {
		if (instance == null) {
			synchronized ((OKHttp.class)) {
				if (instance == null) {
					instance = new OKHttp();
				}
			}
		}
		return instance;
	}

	public void startHttpTasK(final IOKHttpListener listener) {
		HttpUtils.getInstance().exeBackgroundTask(new Runnable() {
			public void run() {
				if (listener.setHttpHM() == null) {
					throw new IllegalArgumentException("Logic Bean cannot be null !");
				}
				TreeMap<String, Object> params = Utils_ObjToMap.obj2map(listener.setHttpHM().httpParams);
				try {
					Response response = null;
					switch (listener.setHttpHM().httpType) {
					case GET:
						response = execGetTask(get(listener.setHttpHM().url, params));
						break;
					case POST_KEY_VALUE:
						response = execPostTask(post(listener.setHttpHM().url, params));
						break;
					default:
						break;
					}
                    if (response!=null){
                        JSONObject jsonOBJ = new JSONObject(response.toString());
                        Gson gson = new Gson();
                        Object body = jsonOBJ.get("body");
                        String bodyJson = body.toString();
                        BN_BaseBody httpResponse=new BN_BaseBody();
                        ET_Base et=new ET_Base(listener.setHttpHM().taskId,httpResponse);
                   /*     httpTaskParams.etHttpResponse.httpResponse = gson.fromJson(bodyJson, httpTaskParams.etHttpResponse.httpResponse.getClass());
                        listener.onResponse();*/


                        handler.post(new Runnable() {
                            @Override
                            public void run() {

                            }
                        });
                    }

				} catch (IOException e) {
					e.printStackTrace();
				} catch (JSONException e) {
                    e.printStackTrace();
                }
            }
		});

	}

	/**
	 * get 请求
	 * 
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
	 * post 请求
	 * 
	 * @return
	 * @throws IOException
	 */
	private Response execPostTask(Request request) throws IOException {
		Response response = client.newCall(request).execute();
		return response;
	}

	/**
	 * @Description GET获取（同步方式） get 拼接
	 */
	public String get(final String urlStr, final TreeMap<String, Object> params) {
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

	public static final MediaType	JSON	= MediaType.parse("application/json; charset=utf-8");

	String post(String url, String json) throws IOException {
		RequestBody body = RequestBody.create(JSON, json);
		Request request = new Request.Builder().url(url).post(body).build();
		Response response = client.newCall(request).execute();
		return response.body().string();
	}

	/**
	 * post请求 map为body
	 *
	 * @param url
	 * @param map
	 */
	public Request post(String url, Map<String, Object> map) {
		/**
		 * 创建请求的参数body
		 */
		FormBody.Builder builder = new FormBody.Builder();
		/**
		 * 遍历key
		 */
		if (null != map) {
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				builder.add(entry.getKey(), entry.getValue().toString());
			}
		}
		RequestBody body = builder.build();
		Request request = new Request.Builder().url(url).post(body).build();
		return request;

	}
	/**
	 * 统一json格式 { apiStatus:0为成功 其他为错误 错误码另定义 apiMassage:"" body:{ 成功返回值 } }
	 */

}
