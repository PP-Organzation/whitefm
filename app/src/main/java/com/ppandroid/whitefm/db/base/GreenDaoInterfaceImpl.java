package com.ppandroid.whitefm.db.base;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import android.database.sqlite.SQLiteDatabase;

import com.android.debugLogUtils.DebugLog;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.internal.DaoConfig;

@SuppressWarnings("unchecked")
public class GreenDaoInterfaceImpl<K, T> implements GreenDaoInterface {

    private String className;

    public GreenDaoInterfaceImpl(String className) {
        super();
        this.className = className;
        DebugLog.v(className);
    }

    @Override
    public AbstractDao<K, T> createDao(DaoConfig arg0, GreenDaoSession arg1) {
        try {
            Constructor<AbstractDao<K, T>> constructor;
            Class<AbstractDao<K, T>> clazz = (Class<AbstractDao<K, T>>) Class.forName(className);
            constructor = clazz.getConstructor(new Class<?>[] { DaoConfig.class, GreenDaoSession.class });
            return (AbstractDao<K, T>) constructor.newInstance(arg0, arg1);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void createTable(SQLiteDatabase arg0, boolean arg1) {
        try {
            Class<AbstractDao<K, T>> clazz = (Class<AbstractDao<K, T>>) Class.forName(className);
            DebugLog.v(clazz.getName());
            Method method = clazz.getMethod("createTable", SQLiteDatabase.class,boolean.class);
            method.invoke(null, arg0,arg1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropTable(SQLiteDatabase arg0, boolean arg1) {
        try {
            Class<AbstractDao<K, T>> clazz = (Class<AbstractDao<K, T>>) Class.forName(className);
            Method method = clazz.getMethod("dropTable", SQLiteDatabase.class,boolean.class);
            method.invoke(null, arg0,arg1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpTable(SQLiteDatabase arg0, int arg1, int arg2) {
    }
}
