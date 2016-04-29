package com.ppandroid.whitefm.http;

import com.antonioleiva.mvpexample.app.R;
import com.ppandroid.whitefm.MApplication;
import com.ppandroid.whitefm.http.bean.ET_Base;
import com.ppandroid.whitefm.http.bean.HM_HttpTask;
import com.ppandroid.whitefm.http.bean.HttpType;
import com.ppandroid.whitefm.http.utils.HttpUtils;
import com.ppandroid.whitefm.http.utils.Utils_Http;
import com.ppandroid.whitefm.utils.NetworkUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.TreeMap;

/**
 * Created by yeqinfu on 16-4-29.
 */
public class HttpTaskImpl implements IHttpTask{
    @Override
    public void doImageTask(HM_HttpTask httpTaskParams) {

    }

    @Override
    public void doTask(final HM_HttpTask httpTaskParams) {

        HttpUtils.getInstance().exeBackgroundTask(new Runnable() {
            public void run() {
                boolean isUIGetDBData = false;
                if (NetworkUtils.isNetworkAvaiable(MApplication.getContext())) { // 有网络
                    if(httpTaskParams.needSaveDB && httpTaskParams.needGetDBData){// 先把缓存数据显示到UI
                        isUIGetDBData = getCacheData(httpTaskParams);
                    }

                    ET_Base etHttpResponse = Utils_Http.exeHttpTask(httpTaskParams);
                    if (etHttpResponse != null) {// result 为OK的时候不为空
                        if (etHttpResponse.httpResponse.getApiStatus() == TokenLossEventType.token_lose //
                                || etHttpResponse.httpResponse.getApiStatus() == TokenLossEventType.token_null) {// token失效

                            if (httpTaskParams.etSpecialLogic == null || !httpTaskParams.etSpecialLogic.isCheckToken()) {// 跳转到登陆界面
                                /********************************************************************************
                                 * 特殊逻辑：如果在返回的字段中apiStatus为1001002或1001003，则跳转到登陆界面
                                 ********************************************************************************/
                                EventBus.getDefault().post(new TokenLossEventType(etHttpResponse.httpResponse.getApiStatus()));
                            } else {// 不需要跳转到登陆界面
                                EventBus.getDefault().post(etHttpResponse);
                            }
                        } else {// 正常逻辑
                            EventBus.getDefault().post(etHttpResponse);
                        }
                    }
                } else {// 没有网络
                    if(httpTaskParams.needSaveDB){// 有缓存
                        if(httpTaskParams.needGetDBData){// 缓存数据已经显示在UI中了，不需要处理了
                            // DO Nothing
                        }else{// 取缓存中的数据给UI显示
                            isUIGetDBData = getCacheData(httpTaskParams);
                        }
                    }
                    // FIXME 现在是不管有没有缓存数据，都会告诉UI网络异常，可以解决不管有没有数据都要提示无网络的需求
                    // 如果需求是有网络数据则不需要弹出无网络的提示，那么可以根据请求的ID来选择性的弹提示还不不弹提示
                    EventBus.getDefault().post(new ET_HttpError(httpTaskParams.etHttpResponse.taskId,
                            Integer.parseInt(MApplication.getContext().getResources().getString(R.string.errorcode_9001)),
                            MApplication.getContext().getResources().getString(R.string.errorcode_9001_desc),isUIGetDBData));
                }
            }
        });

    }

    @Override
    public void doTask(TreeMap<String, Object> httpParams, HttpType httpType, String url) {

    }


    /**
     * 根据条件来获取缓存数据并且POST给UI显示
     * @param httpTaskParams
     * @return 是否已经把缓存数据推送给UI了，true:给UI成功，false：没有给UI
     * @author LuoZheng
     * @date 2015年7月13日 上午11:52:01
     */
    private boolean getCacheData(final HM_HttpTask httpTaskParams) {
       /* Utils_SharedPreferences sharedPreferences = new Utils_SharedPreferences(httpTaskParams.context,//
                ConstantParams.sharePreferenceFileName);
        String userPassportId = sharedPreferences.getString(ConstantParams.sp_passportid_key, ""); // 用户账号Id
        if (httpTaskParams.dBquery == null) {
            httpTaskParams.dBquery = new HM_DBquery();
        }
        httpTaskParams.dBquery.userId = userPassportId;

        Gson gson = new Gson();
        DBModel dbModel = DBModelDaoInfc.getInstance().querySingle(httpTaskParams.context, //
                new Property[] { DBModelDao.Properties.HttpUrl, DBModelDao.Properties.ExtraKey },//
                new Object[] { httpTaskParams.url, gson.toJson(httpTaskParams.dBquery) });

        if (dbModel != null && httpTaskParams.etDataBase !=null && httpTaskParams.etHttpResponse != null) {// 有缓存数据
            String json = dbModel.getJson();
            httpTaskParams.etDataBase.taskId = httpTaskParams.etHttpResponse.taskId;
            httpTaskParams.etDataBase.httpResponse = gson.fromJson(json, httpTaskParams.etHttpResponse.httpResponse.getClass());
            EventBus.getDefault().post(httpTaskParams.etDataBase);
            return true;
        }*/
        return false;
    }
}
