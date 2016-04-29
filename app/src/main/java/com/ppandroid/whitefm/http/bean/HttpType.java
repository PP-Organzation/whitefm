package com.ppandroid.whitefm.http.bean;

/**
 * Created by yeqinfu on 16-4-29.
 */
public enum HttpType {
    /**	GET */
    GET,
    /** Post方式，stringentity方式传参	*/
    POST_STR_ENTITY,
    /**	Post方式，key-value方式传参	*/
    POST_KEY_VALUE,
    /**	PUT */
    PUT,
    /**	DELETE */
    DELETE,
}
