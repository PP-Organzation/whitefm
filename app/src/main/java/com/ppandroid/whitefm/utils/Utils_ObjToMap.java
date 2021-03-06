package com.ppandroid.whitefm.utils;

import java.lang.reflect.Field;
import java.util.TreeMap;

public class Utils_ObjToMap {

    /**
     * @Description HTTP????????????????????????????????????TreeMap <br />
     *              <b>?????????????????public??????????????</b>
     * @author LuoZheng
     * @date 2015?3?11? ??11:03:47
     */
    public static TreeMap<String, Object> obj2map(Object obj) {
        TreeMap<String, Object> httpParams = new TreeMap<String, Object>();
        try {
            if (obj == null) {

            } else {
            	/**
            	 * getFields()????????????public?????????,???public??????
            	 * getDeclaredFields()?????????????????public?private?proteced??????????????
            	 */
                Field[] fields = obj.getClass().getFields();//obj.getClass().getDeclaredFields();// ???????
                for (int i = 0, len = fields.length; i < len; i++) {
                    Field f = fields[i];
                    String varName = f.getName();// ?????
                    //boolean accessFlag = f.isAccessible();//// ???????????
                    //fields[i].setAccessible(true);// ???????????
                    Object o = f.get(obj);// ??????
                    if(o == null){

                    }else{
                        httpParams.put(varName, o);
                    }
                    //f.setAccessible(accessFlag);// ???????????
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return httpParams;
    }
}
