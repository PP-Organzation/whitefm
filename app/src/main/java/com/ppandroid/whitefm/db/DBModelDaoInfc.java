package com.ppandroid.whitefm.db;

import android.content.Context;

import java.util.List;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.query.WhereCondition;

public class DBModelDaoInfc extends GreenDaoInfcDefaultImpl<DBModel> {

    private static DBModelDaoInfc instance;

    private DBModelDaoInfc() {
        super(DBModelDao.class.getName());
    }

    public static DBModelDaoInfc getInstance() {
        if (instance == null) {
            instance = new DBModelDaoInfc();
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
    public void update(Context context,DBModel model, WhereCondition... wcs) {
        AbstractDao<DBModel, Long> dao = (AbstractDao<DBModel, Long>) DbManager//
                .getInstance(context).getDao(DBModelDao.class.getName());
        List<DBModel> dbModels = query(context, //
                new Property[]{DBModelDao.Properties.HttpUrl,DBModelDao.Properties.ExtraKey},//
                new Object[]{model.getHttpUrl(),model.getExtraKey()});//
        
        if (dbModels != null && dbModels.size() > 0) {
            dao.deleteInTx(dbModels);
        }
        
        dao.insert(model);
    }
}
