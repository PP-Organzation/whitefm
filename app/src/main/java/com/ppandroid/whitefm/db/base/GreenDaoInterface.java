package com.ppandroid.whitefm.db.base;

import android.database.sqlite.SQLiteDatabase;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.internal.DaoConfig;

public interface GreenDaoInterface {
    void createTable(SQLiteDatabase db, boolean ifNotExists);

    void dropTable(SQLiteDatabase db, boolean ifExists);

    void onUpTable(SQLiteDatabase db, int oldVersion, int newVersion);

    AbstractDao createDao(DaoConfig config, GreenDaoSession greenDaoSession);
}
