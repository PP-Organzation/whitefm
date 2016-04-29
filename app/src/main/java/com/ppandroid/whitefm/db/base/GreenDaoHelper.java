package com.ppandroid.whitefm.db.base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;

import de.greenrobot.dao.identityscope.IdentityScopeType;

/**
 * Created by Admin on 2015/1/4.
 */
public class GreenDaoHelper {
    private SQLiteDatabase db;
    private GreenDaoMaster daoMaster;
    private GreenDaoSession greenDaoSession;

    private static GreenDaoHelper helper = null;
    private static String DB_NAME = null;
    private static int schema_version = 1;

    private static HashMap<String,GreenDaoInterface> daoHashMap;

    public static GreenDaoHelper getInstance(Context context) {
        if (helper == null) {
            synchronized (GreenDaoHelper.class) {
                if (helper == null) {
                    if(DB_NAME == null){
                        throw new IllegalArgumentException("Set DataBase Name First!!");
                    }

                    if(daoHashMap == null){
                        throw new IllegalArgumentException("Register AbstractDaoS First!!");
                    }
                    helper = new GreenDaoHelper(context);
                }
            }
        }
        return helper;
    }

    private GreenDaoHelper(Context context) {
        GreenDaoMaster.DevOpenHelper helper = new GreenDaoMaster.DevOpenHelper(context, DB_NAME, null,daoHashMap,schema_version);
        db = helper.getWritableDatabase();
        daoMaster = new GreenDaoMaster(db);
        greenDaoSession = daoMaster.newSession(IdentityScopeType.None);
    }

    public static void release() {
        helper = null;
        if(daoHashMap != null){
            daoHashMap.clear();;
            daoHashMap = null;
        }
    }

    public static void setDbName(String dbName){
    	GreenDaoHelper.DB_NAME = dbName;
    }

    public static void setSchema_version(int schema_version) {
		GreenDaoHelper.schema_version = schema_version;
	}

	public static void registerDao(String className,GreenDaoInterface greenDaoInterface){
        if(daoHashMap == null){
            daoHashMap = new HashMap<String, GreenDaoInterface>();
        }
        daoHashMap.put(className, greenDaoInterface);
    }

    public GreenDaoSession getGreenDaoSession() {
        return greenDaoSession;
    }
}
