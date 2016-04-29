package com.ppandroid.whitefm;

import android.app.Application;
import android.content.Context;

/**
 * Created by yeqinfu on 16-4-29.
 */
public class MApplication extends Application{
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
    }
    public  static Context getContext(){
        return context;
    }
}
