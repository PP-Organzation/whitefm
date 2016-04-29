package com.ppandroid.whitefm.http.utils;

import com.ppandroid.whitefm.http.CustomMultiPartEntity;
import com.ppandroid.whitefm.http.HttpControl;
import com.ppandroid.whitefm.http.HttpProgressCallBack;
import com.ppandroid.whitefm.http.bean.FileBodyModel;
import com.ppandroid.whitefm.http.bean.HttpDeleteWithBody;
import com.ppandroid.whitefm.utils.Utils_Debug;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpUtils {

    private static final int connection_timeout = 30 * 1000;
    private static final int read_timeout = 30 * 1000;

    private ExecutorService cachedThreadPool = Executors.newFixedThreadPool(20);

    private static HttpUtils instance = new HttpUtils();

    private HttpUtils() {
    }

    public static HttpUtils getInstance() {
        return instance;
    }

    /**
     * @Description GET获取（同步方式）
     */
    public String syncGet(final String urlStr, final TreeMap<String, Object> params,String httpType) {
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
        
        return syncGet(buffer.toString(),httpType);
    }

    public String syncGet(final String urlStr,String httpType) {
        try {
            Utils_Debug.i(urlStr);
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(false);
            connection.setDefaultUseCaches(false);
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("Charsert", "UTF-8");
            connection.setConnectTimeout(connection_timeout);
            connection.setReadTimeout(read_timeout);
            connection.setRequestMethod(httpType);
            
            if (connection.getResponseCode() == HttpStatus.SC_OK) {
                InputStream inStream = connection.getInputStream();
                if (inStream != null) {
                    String response = InputStreamUtils.InputStreamTOString(inStream, HTTP.UTF_8);
                    Utils_Debug.v(response);
                    return response;
                }
            }else{
            	Utils_Debug.i(String.format(Locale.getDefault(), "%d", connection.getResponseCode()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Description Post方式
     * @param params 请求参数为key-value的形式
     */
    public String syncPost(final String url, final Map<String, Object> params) {
        try {
            Utils_Debug.i(String.format("%s", url));
            HttpParams httpParameters = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParameters, 30 * 1000);
            HttpConnectionParams.setSoTimeout(httpParameters, 30 * 1000);
            HttpClient httpClient = new DefaultHttpClient(httpParameters);
            HttpPost post = new HttpPost(url);
            if (params != null && params.size() > 0) {
                Iterator<String> iterator = params.keySet().iterator();
                List<BasicNameValuePair> requestParam = new ArrayList<BasicNameValuePair>();
                StringBuilder builder = new StringBuilder();
                while (iterator.hasNext()) {
                    String param = iterator.next();
                    String value = String.valueOf(params.get(param));
                    BasicNameValuePair basicNameValuePair = new BasicNameValuePair(param, value);
                    requestParam.add(basicNameValuePair);
                    builder.append(basicNameValuePair.toString()+",");
                }
                Utils_Debug.i(builder.toString());
                post.setEntity(new UrlEncodedFormEntity(requestParam, "UTF-8"));
            }
            HttpResponse response = httpClient.execute(post);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String res = EntityUtils.toString(entity, "UTF-8");
                Utils_Debug.i(String.format("%s", res));
                if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                    return res;
                }else{
                    throw new IllegalArgumentException("Post Failed !!");
                }
            } else {
                throw new IllegalArgumentException("post response is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Description Post方式
     * @param params 请求参数为json的形式
     */
    public String syncPost(final String url, final String params) {
        try {
            Utils_Debug.i(String.format("%s", url));
            Utils_Debug.i("params:"+params);
            HttpParams httpParameters = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParameters, 30 * 1000);
            HttpConnectionParams.setSoTimeout(httpParameters, 30 * 1000);
            HttpClient httpClient = new DefaultHttpClient(httpParameters);
            HttpPost post = new HttpPost(url);
            post.setHeader("Accept", "application/json");
            post.setHeader("Content-type", "application/json");
            if (params != null) {
                StringEntity entity = new StringEntity(params, HTTP.UTF_8);
                post.setEntity(entity);
            }
            
            HttpResponse response = httpClient.execute(post);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String res = EntityUtils.toString(entity, "UTF-8");
                Utils_Debug.i(String.format("%s", res));
                if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                    return res;
                }else{
                    throw new IllegalArgumentException("Post Failed !!");
                }
            } else {
                throw new IllegalArgumentException("post response is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void exePostUploadFile(String url, String fileParam, String filePath, HttpControl control,
            HttpProgressCallBack callBack) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put(fileParam, new File(filePath));
        exePostUploadFile(url, params, control, callBack);
    }

    public void exePostUploadFile(final String url, final Map<String, Object> params, final HttpControl control,
            final HttpProgressCallBack callBack) {
        cachedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                syncUploadFile(url, params, control, callBack);
            }
        });

    }

    public void exeDownloadFile(final String downloadUrl, final File saveFilePath, final HttpControl control,
            final HttpProgressCallBack callBack) {
        cachedThreadPool.execute(new Runnable() {
            public void run() {
                syncExeDownloadFile(downloadUrl, saveFilePath, control, callBack);
            }
        });
    }

    /**
     * 同步下载方法
     * 
     * @param downloadUrl
     * @param saveFilePath
     * @param control
     * @param callBack
     * @return true下载成功，false下载失败
     */
    public boolean syncExeDownloadFile(final String downloadUrl, final File saveFilePath, final HttpControl control,
            final HttpProgressCallBack callBack) {
        int fileSize = -1;
        int downFileSize = 0;
        int progress = 0;
        try {
            URL url = new URL(downloadUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(connection_timeout);
            conn.setReadTimeout(read_timeout);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                fileSize = conn.getContentLength();
                InputStream is = conn.getInputStream();
                FileOutputStream fos = new FileOutputStream(saveFilePath);
                byte[] buffer = new byte[1024];
                int i = 0;
                int tempProgress = -1;
                while ((i = is.read(buffer)) != -1) {
                    if (control != null && control.isCancel()) {
                        conn.disconnect();
                        if (callBack != null) callBack.onComplete(
                                new IllegalArgumentException(HttpControl.task_cancel), null);
                        break;
                    } else {
                        downFileSize = downFileSize + i;
                        progress = (int) (downFileSize * 100.0 / fileSize);
                        fos.write(buffer, 0, i);

                        if (downFileSize == fileSize) {
                            if (callBack != null) callBack.onComplete(null, "");
                        } else if (tempProgress != progress) {
                            progress = progress > 100 ? 100 : progress;
                            if (callBack != null) callBack.onLoading(progress);
                            tempProgress = progress;
                        }
                    }
                }
                fos.flush();
                fos.close();
                if (is != null) {
                    is.close();
                }
                return true;
            } else {
                if (callBack != null) callBack.onComplete(new IllegalArgumentException("Connection Faild"), null);

            }
        } catch (Exception e) {
            e.printStackTrace();
            if (callBack != null) callBack.onComplete(e, null);

        }

        return false;
    }

    /**
     * 同步的上传方法
     * @param url
     * @param params
     * @param control
     * @param callBack
     */
    public void syncUploadFile(final String url, final Map<String, Object> params, final HttpControl control,
            final HttpProgressCallBack callBack) {
        String serverResponse = null;
        HttpParams httpParameters = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParameters, 15 * 60 * 1000);
        HttpConnectionParams.setSoTimeout(httpParameters, 15 * 60 * 1000);
        HttpClient httpClient = new DefaultHttpClient(httpParameters);
        HttpContext httpContext = new BasicHttpContext();
        final HttpPost httpPost = new HttpPost(url);
        try {
            CustomMultiPartEntity multipartContent = new CustomMultiPartEntity();
            // We use FileBody to transfer an image
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                if (entry.getValue() instanceof File) {
                	//Utils_Debug.d(entry.getKey() + ":" + ((File) entry.getValue()).getAbsolutePath());
                    multipartContent.addPart(entry.getKey(), new FileBody((File) entry.getValue()));
                } else if (entry.getValue() instanceof FileBodyModel) {
                    FileBodyModel fileModel = (FileBodyModel) entry.getValue();
                    multipartContent.addPart(entry.getKey(), new FileBody(fileModel.getFile(), //
                            fileModel.getRemoteFileName(), fileModel.getMimeType(), fileModel.getCharset()));
                } else if (entry.getValue() instanceof String) {
                	//Utils_Debug.d(entry.getKey() + ":" + (String) entry.getValue());
                    multipartContent.addPart(entry.getKey(), new StringBody((String) entry.getValue()));
                } else if (entry.getValue() instanceof Double) {
                    multipartContent.addPart(entry.getKey(), new StringBody(String.valueOf(entry.getValue())));
                } else if (entry.getValue() instanceof Integer) {
                    multipartContent.addPart(entry.getKey(), new StringBody(String.valueOf(entry.getValue())));
                } else if (entry.getValue() instanceof Float) {
                    multipartContent.addPart(entry.getKey(), new StringBody(String.valueOf(entry.getValue())));
                } else if (entry.getValue() instanceof Long) {
                    multipartContent.addPart(entry.getKey(), new StringBody(String.valueOf(entry.getValue())));
                } else if (entry.getValue() instanceof Boolean) {
                    multipartContent.addPart(entry.getKey(), new StringBody(String.valueOf(entry.getValue())));
                } else if (entry.getValue() instanceof Byte) {
                    multipartContent.addPart(entry.getKey(), new StringBody(entry.getValue().toString()));
                } else {
                    throw new IllegalStateException("Can not  resolve the param:" + entry.getValue().toString());
                }
            }

            final long totalSize = multipartContent.getContentLength();
            httpPost.setEntity(multipartContent);

            multipartContent.setListener(new CustomMultiPartEntity.ProgressListener() {
                @Override
                public void transferred(long num) {
                    if (!control.isCancel()) {
                        int progress = (int) ((num / (float) totalSize) * 100);
                        progress = progress > 100 ? 100 : progress;
                        if (callBack != null) {
                        	callBack.onLoading(progress);
                        }
                    } else {
                        httpPost.abort();
                        if (callBack != null) {
                        	callBack.onComplete(new IllegalArgumentException(HttpControl.task_cancel), null);
                        }
                    }
                }
            });
            HttpResponse response = httpClient.execute(httpPost, httpContext);

            serverResponse = EntityUtils.toString(response.getEntity());
            if (callBack != null) {
            	callBack.onComplete(null, serverResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (callBack != null) {
            	callBack.onComplete(e, null);
            }
        }
    }

    public void exeBackgroundTask(Runnable backGroungTask) {
        cachedThreadPool.execute(backGroungTask);
    }

    /**
     * HTTP PUT
     * @param url
     * @param params
     * @return
     */
	public String exeHttpPut(String url, TreeMap<String, Object> params) {
		try {
			Utils_Debug.i(String.format("%s", url));
			HttpPut httpPut = new HttpPut(url);
			HttpParams httpParameters = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParameters, 30 * 1000);
			HttpConnectionParams.setSoTimeout(httpParameters, 30 * 1000);
			httpPut.setHeader("Content-type", "application/x-www-form-urlencoded");
			HttpClient httpClient = new DefaultHttpClient(httpParameters);
			if (params != null && params.size() > 0) {
				Iterator<String> iterator = params.keySet().iterator();
				List<BasicNameValuePair> requestParam = new ArrayList<BasicNameValuePair>();
				StringBuilder builder = new StringBuilder();
				while (iterator.hasNext()) {
					String param = iterator.next();
					String value = String.valueOf(params.get(param));
					BasicNameValuePair basicNameValuePair = new BasicNameValuePair(param, value);
					requestParam.add(basicNameValuePair);
					builder.append(basicNameValuePair.toString() + ",");
				}
				Utils_Debug.i("params:"+builder.toString());
				httpPut.setEntity(new UrlEncodedFormEntity(requestParam, "UTF-8"));
			}
			HttpResponse response = httpClient.execute(httpPut);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
            	int errorCode = response.getStatusLine().getStatusCode();
                String res = EntityUtils.toString(entity, "UTF-8");
                Utils_Debug.i(String.format("%s", res));
                if(errorCode == HttpStatus.SC_OK){
                    return res;
                }else{
                    throw new IllegalArgumentException("Put Failed , errorCode = " + errorCode);
                }
            } else {
                throw new IllegalArgumentException("Put response is null");
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * HTTP DELETE
	 * @param url
	 * @param params
	 * @return
	 */
	public String exeHttpDelete(String url,TreeMap<String, Object> params){
		try {
			HttpDeleteWithBody deleteRequest = new HttpDeleteWithBody(url);
			deleteRequest.setHeader("Content-type", "application/x-www-form-urlencoded");
			HttpParams httpParameters = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParameters, 30 * 1000);
			HttpConnectionParams.setSoTimeout(httpParameters, 30 * 1000);
			HttpClient httpClient = new DefaultHttpClient(httpParameters);
			if (params != null && params.size() > 0) {
				Iterator<String> iterator = params.keySet().iterator();
				List<BasicNameValuePair> requestParam = new ArrayList<BasicNameValuePair>();
				while (iterator.hasNext()) {
					String param = iterator.next();
					String value = String.valueOf(params.get(param));
					requestParam.add(new BasicNameValuePair(param, value));
				}
				deleteRequest.setEntity(new UrlEncodedFormEntity(requestParam, "UTF-8"));
			}
			HttpResponse response = httpClient.execute(deleteRequest);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String res = EntityUtils.toString(entity, "UTF-8");
                Utils_Debug.i(String.format("%s", res));
                if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                    return res;
                }else{
                	Utils_Debug.i(String.format(Locale.getDefault(), "%d", response.getStatusLine().getStatusCode()));
                    throw new IllegalArgumentException("Delete Failed !!");
                }
            } else {
                throw new IllegalArgumentException("Delete response is null");
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
