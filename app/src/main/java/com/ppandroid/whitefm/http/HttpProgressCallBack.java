package com.ppandroid.whitefm.http;

public interface HttpProgressCallBack extends HttpResponseCallBack{
    
    public void onLoading(int progress);
    
}
