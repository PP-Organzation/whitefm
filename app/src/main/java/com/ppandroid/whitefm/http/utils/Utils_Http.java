package com.ppandroid.whitefm.http.utils;

import android.content.res.Resources;

import com.antonioleiva.mvpexample.app.R;
import com.google.gson.Gson;
import com.ppandroid.whitefm.MApplication;
import com.ppandroid.whitefm.constant.ConstantParams;
import com.ppandroid.whitefm.db.DBModel;
import com.ppandroid.whitefm.db.DBModelDaoInfc;
import com.ppandroid.whitefm.http.ET_HttpError;
import com.ppandroid.whitefm.http.TokenLossEventType;
import com.ppandroid.whitefm.http.bean.BN_BaseBody;
import com.ppandroid.whitefm.http.bean.ET_Base;
import com.ppandroid.whitefm.http.bean.HM_DBquery;
import com.ppandroid.whitefm.http.bean.HM_HttpTask;
import com.ppandroid.whitefm.http.bean.HttpType;
import com.ppandroid.whitefm.utils.Utils_Md5;
import com.ppandroid.whitefm.utils.Utils_ObjToMap;
import com.ppandroid.whitefm.utils.Utils_SharedPreferences;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.util.Date;
import java.util.Iterator;
import java.util.TreeMap;


public class Utils_Http {

    /**
     * @Description ????????HTTP post??StringEntity??????????<br />
     *              ????????????StringEntity??????
     * @param params ???????????????????<b>version</b>??????????????
     */
	public static TreeMap<String, Object> httpStrParams(TreeMap<String, Object> params) {
		if (params == null) {
			params = new TreeMap<String, Object>();
		}
		Utils_SharedPreferences sp_login = new Utils_SharedPreferences(MApplication.getContext(),//
				ConstantParams.sharePreferenceFileName);
		String token = sp_login.getString(ConstantParams.sp_token_key, ""); // ??Token

		/*Utils_SharedPreferences sp_location = new Utils_SharedPreferences(MApplication.getContext(), ConstantParams.LOCATION);
		String cityName = sp_location.getString(ConstantParams.LOCATION_CITYNAME, "");//??????
		String lat = sp_location.getString(ConstantParams.LOCATION_LAT, "");//??
		String lng = sp_location.getString(ConstantParams.LOCATION_LNG, "");//??
		*//** ???? Id*//*
		Utils_SharedPreferences sharedPreferencesREG = new Utils_SharedPreferences(MApplication.getContext(), ConstantParams.sharePreferenceJPush);
		String pushDeviceCode = sharedPreferencesREG.getString(ConstantParams.sp_registrationId_key, "");

		if (!params.containsKey("deviceType")) {//???? 1 ??android
			params.put("deviceType", 1);
		}
		if (!params.containsKey("deviceCode")) { //???
			params.put("deviceCode", Utils_Common.getDeviceId(MApplication.getContext()));
		}
		if (!params.containsKey("pushDeviceCode")) {//????Id
			params.put("pushDeviceCode", pushDeviceCode);
		}*/
		if (!params.containsKey("token")) {//????
			params.put("token", token);
		}


		/*Utils_SharedPreferences commonSp = new Utils_SharedPreferences(MApplication.getContext(),ConstantParams.APP_COMMON_SP);
		String buildChannel = commonSp.getString(ConstantParams.APP_COMMON_CHANNEL_NAME,"");

		if (!params.containsKey("buildChannel")) {//app???
			params.put("buildChannel", buildChannel);
		}
		if (!params.containsKey("_cityName")) {//???????
			params.put("_cityName", cityName);
		}
		if (!params.containsKey("lng")) {//????
			params.put("lng", lng);
		}
		if (!params.containsKey("lat")) {//????
			params.put("lat", lat);
		}*/

		Resources resources = MApplication.getContext().getResources();
		int versionId = resources.getIdentifier("version_name", "string", MApplication.getContext().getPackageName());
		params.put("version", resources.getString(versionId));
		params.put("timestamp", System.currentTimeMillis());
		StringBuilder sBuilder = new StringBuilder("ppandroid");
		Iterator<String> iter = params.keySet().iterator();
		while (iter.hasNext()) {
			String name = (String) iter.next().trim();
			sBuilder.append(name).append(params.get(name));
		}
		sBuilder.append("ppandroid");
		String sign = Utils_Md5.MD5Sign(sBuilder.toString());
		params.put("sign", sign);
		return params;
	}

	/*public static MedicineBaseModel exeHttpTask(Context context,String url,HttpParamsModel httpModel,//
			MedicineBaseModel logicModel,final HttpType httpType) {
		if (logicModel == null) {
			throw new IllegalArgumentException("Logic Bean cannot be null !");
		}
		TreeMap<String, Object> params = ObjToMap.obj2map(httpModel);
		TreeMap<String, Object> realParams = Utils_Http.httpStrParams(params);
		try {
			Gson gson = new Gson();
			String httpResult = null;
			switch (httpType) {
			case GET:
				httpResult = HttpUtils.getInstance().syncGet(url, realParams, "GET");
				break;
			case POST_KEY_VALUE:
				httpResult = HttpUtils.getInstance().syncPost(url,realParams);
				break;
			case POST_STR_ENTITY:
				httpResult = HttpUtils.getInstance().syncPost(url, gson.toJson(realParams));
				break;
			case PUT:
				httpResult = HttpUtils.getInstance().exeHttpPut(url, realParams);
				break;
			case DELETE:
				httpResult = HttpUtils.getInstance().exeHttpDelete(url, realParams);
				break;
			default:
				httpResult = HttpUtils.getInstance().syncPost(url, gson.toJson(realParams));
				break;
			}
			MedicineBaseModel httpLogicModel = gson.fromJson(httpResult, logicModel.getClass());
			if (httpLogicModel.getResult().equals("OK")) {
				httpLogicModel.setResultCode(MedicineBaseModel.SUCCESS);
				httpLogicModel.setEventType(logicModel.getEventType());
				if(context != null){
					Utils_SharedPreferences sharedPreferences = new Utils_SharedPreferences(MApplication.getContext(),//
							ConstantParams.sharePreferenceFileName);
					String userPassportId = sharedPreferences.getString(ConstantParams.sp_passportid_key, ""); // ????Id
					if(httpModel == null){
						httpModel = new HttpParamsModel();
					}
					httpModel.userId = userPassportId;
					DBModel dbModel = new DBModel(null, url, gson.toJson(httpModel), httpResult, new Date());
					DBModelDaoInfc.getInstance().update(context, dbModel);
				}
				return httpLogicModel;
			}else {
				httpLogicModel.setResultCode(MedicineBaseModel.FAIL);
				httpLogicModel.setEventType(logicModel.getEventType());
				return httpLogicModel;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		logicModel.setResultCode(MedicineBaseModel.ERROR);
		logicModel.setEventType(logicModel.getEventType());
		return logicModel;
	}
*/

	public static ET_Base exeHttpTask(HM_HttpTask httpTaskParams) {
		if (httpTaskParams.etHttpResponse.httpResponse == null) {
			throw new IllegalArgumentException("Logic Bean cannot be null !");
		}
		TreeMap<String, Object> params = Utils_ObjToMap.obj2map(httpTaskParams.httpParams);
		TreeMap<String, Object> realParams = Utils_Http.httpStrParams(params);
		try {
			Gson gson = new Gson();
			String httpResult = null;
			if(httpTaskParams.httpType == null){
				httpTaskParams.httpType = HttpType.GET;
			}
			switch (httpTaskParams.httpType) {
			case GET:
				httpResult = HttpUtils.getInstance().syncGet(httpTaskParams.url, realParams, "GET");
				break;
			case POST_KEY_VALUE:
				httpResult = HttpUtils.getInstance().syncPost(httpTaskParams.url,realParams);
				break;
			case POST_STR_ENTITY:
				httpResult = HttpUtils.getInstance().syncPost(httpTaskParams.url, gson.toJson(realParams));
				break;
			case PUT:
				httpResult = HttpUtils.getInstance().exeHttpPut(httpTaskParams.url, realParams);
				break;
			case DELETE:
				httpResult = HttpUtils.getInstance().exeHttpDelete(httpTaskParams.url, realParams);
				break;
			default:
				httpResult = HttpUtils.getInstance().syncPost(httpTaskParams.url, gson.toJson(realParams));
				break;
			}

			JSONObject jsonOBJ = new JSONObject(httpResult);
			String result = jsonOBJ.optString("result");
			int errorCode = jsonOBJ.optInt("errorCode");
			String errorDescription = jsonOBJ.optString("errorDescription");

			if("OK".equals(result)){// ??????
				Object body = jsonOBJ.get("body");
				String bodyJson = body.toString();
				httpTaskParams.etHttpResponse.httpResponse = gson.fromJson(bodyJson, httpTaskParams.etHttpResponse.httpResponse.getClass());

                // ????
				if(httpTaskParams.needSaveDB){
					Utils_SharedPreferences sharedPreferences = new Utils_SharedPreferences(MApplication.getContext(),//
							ConstantParams.sharePreferenceFileName);
					String userPassportId = sharedPreferences.getString(ConstantParams.sp_passportid_key, ""); // ????Id
					if (httpTaskParams.dBquery == null) {
						httpTaskParams.dBquery = new HM_DBquery();
					}
					httpTaskParams.dBquery.userId = userPassportId;
					DBModel dbModel = new DBModel(null, httpTaskParams.url, gson.toJson(httpTaskParams.dBquery), bodyJson, new Date());
					DBModelDaoInfc.getInstance().update(httpTaskParams.context, dbModel);
				}

				if(httpTaskParams.etHttpResponse.httpResponse.getApiStatus() == BN_BaseBody.resultOk){
					return httpTaskParams.etHttpResponse;
				}else{
					ET_HttpError httpError = new ET_HttpError(httpTaskParams.etHttpResponse.taskId,//
							httpTaskParams.etHttpResponse.httpResponse.getApiStatus(),httpTaskParams.etHttpResponse.httpResponse.getApiMessage());
					httpError.httpResponse = httpTaskParams.etHttpResponse.httpResponse;
					EventBus.getDefault().post(httpError);
					if (httpTaskParams.etHttpResponse.httpResponse.getApiStatus() == TokenLossEventType.token_lose //
							|| httpTaskParams.etHttpResponse.httpResponse.getApiStatus() == TokenLossEventType.token_null) {// token??
						return httpTaskParams.etHttpResponse;
					}else{
						return null;
					}
					//return null;
				}
			}else{// ??????
				EventBus.getDefault().post(new ET_HttpError(httpTaskParams.etHttpResponse.taskId,errorCode,errorDescription));
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

        // ???????
		EventBus.getDefault().post(new ET_HttpError(httpTaskParams.etHttpResponse.taskId,
				Integer.parseInt(MApplication.getContext().getResources().getString(R.string.errorcode_9002)),
				MApplication.getContext().getResources().getString(R.string.errorcode_9002_desc)));
		return null;
	}

	/**
	 *
	 * @description Android?js???http?????
	 *
	 * @param params
	 * @param httpType
	 * @param url
	 * @return
	 * @return String
	 * @exception
	 * @author Wangfulin
	 * @date 2015?8?17?-??10:42:15
	 * @version 2.2
	 */
	public static String exeHttpTask(TreeMap<String, Object> params, HttpType httpType, String url) {
		String httpResult = "";
		TreeMap<String, Object> realParams = Utils_Http.httpStrParams(params);
		try {
			Gson gson = new Gson();
			if(httpType == null){
				httpType = HttpType.GET;
			}
			switch (httpType) {
			case GET:
				httpResult = HttpUtils.getInstance().syncGet(url, realParams, "GET");
				break;
			case POST_KEY_VALUE:
				httpResult = HttpUtils.getInstance().syncPost(url,realParams);
				break;
			case POST_STR_ENTITY:
				httpResult = HttpUtils.getInstance().syncPost(url, gson.toJson(realParams));
				break;
			case PUT:
				httpResult = HttpUtils.getInstance().exeHttpPut(url, realParams);
				break;
			case DELETE:
				httpResult = HttpUtils.getInstance().exeHttpDelete(url, realParams);
				break;
			default:
				httpResult = HttpUtils.getInstance().syncPost(url, gson.toJson(realParams));
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return httpResult;
	}

}
