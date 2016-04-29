package com.ppandroid.whitefm.http.bean;

/**
 * Created by yeqinfu on 16-4-29.
 */
public class ET_SpecialLogic {
    /**
     * 用于判断token失效的时候是跳转到登陆界面还是不要跳转到登陆界面
     */
    private boolean isCheckToken = true;

    public ET_SpecialLogic(boolean isCheckToken) {
        super();
        this.isCheckToken = isCheckToken;
    }

    public boolean isCheckToken() {
        return isCheckToken;
    }

    public void setCheckToken(boolean isCheckToken) {
        this.isCheckToken = isCheckToken;
    }

}
