package com.ppandroid.whitefm.http;

/**
 * Created by yeqinfu on 16-4-29.
 */
public class HttpFactory {
    private volatile  static HttpFactory instance;
    private volatile static IHttpTask httpTask;
    private HttpFactory(){

    }
    public static HttpFactory getInstance(){
        if (instance==null){
            synchronized ((HttpFactory.class)){
                if (instance==null){
                    instance=new HttpFactory();
                }
            }
        }
        return instance;
    }

    public  IHttpTask getHttpTask(){
        if (httpTask==null){
            synchronized ((HttpTaskImpl.class)){
                if (httpTask==null){
                    httpTask=new HttpTaskImpl();
                }
            }
        }
        return httpTask;
    }


}
