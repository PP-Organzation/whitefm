package com.ppandroid.whitefm.db;

import android.content.Context;

import com.ppandroid.whitefm.db.base.GreenDaoInterfaceImpl;
import com.ppandroid.whitefm.utils.Utils_Debug;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.query.QueryBuilder;
import de.greenrobot.dao.query.WhereCondition;

@SuppressWarnings("unchecked")
public class GreenDaoInfcDefaultImpl<K> extends GreenDaoInterfaceImpl<K, Long> {

    private String className;

    public GreenDaoInfcDefaultImpl(String className) {
        super(className);
        this.className = className;
    }

    /**
     * @Description ??????????????
     */
    public void save(Context context, List<K> list) {
        save(context, list, false);
    }

    /**
     * @Description ??????????????????
     */
    public void save(Context context, List<K> list, boolean deleteAll) {
        AbstractDao<K, Long> dao = (AbstractDao<K, Long>) DbManager.getInstance(context).getDao(className);
        if (deleteAll) {
            dao.deleteAll();
        }
        dao.insertInTx(list);
    }

    /**
     * @Description ???????????????????
     */
    public void save(Context context, K k) {
        save(context, k, false);
    }

    /**
     * @Description ??????????????????
     */
    public void save(Context context, K k, boolean deleteAll) {
        AbstractDao<K, Long> dao = (AbstractDao<K, Long>) DbManager.getInstance(context).getDao(className);
        if (deleteAll) {
            dao.deleteAll();
        }
        dao.insert(k);
    }

    /**
     * @Description ????????<br />
     *              ??WhereCondition?????????????????null???????<br />
     *              ?????{@link GreenDaoInfcDefaultImpl#query(Context, Property[], Object[])}
     */
    @Deprecated
    public List<K> query(Context context, WhereCondition wc, WhereCondition... wcs) {
        AbstractDao<K, Long> dao = (AbstractDao<K, Long>) DbManager.getInstance(context).getDao(className);
        QueryBuilder<K> queryBuilder = dao.queryBuilder();
        if (wc != null && (wcs == null || wcs.length == 0)) {
            queryBuilder.where(wc);
        } else if (wc != null && wcs != null) {
            queryBuilder.where(wc, wcs);
        }

        return queryBuilder.build().list();
    }

    /**
     * @Description ????????,????????
     */
    public List<K> query(Context context, Property[] columns, Object[] columnValues) {
        Utils_Debug.d(className);
        AbstractDao<K, Long> dao = (AbstractDao<K, Long>) DbManager.getInstance(context).getDao(className);
        QueryBuilder<K> queryBuilder = dao.queryBuilder();
        if (columns == null || columns.length == 0) {
            // ??????
        } else {
            if (columns.length != columnValues.length) {
                // ??????
                throw new IllegalArgumentException("query condition is error !!");
            } else {
                List<WhereCondition> wcs = new ArrayList<WhereCondition>();
                for (int i = 0, len = columns.length; i < len; i++) {
                    Property p = columns[i];
                    Object o = columnValues[i];
                    if (p != null && p.name != null && o != null) {
                        WhereCondition wc = p.eq(o);
                        wcs.add(wc);
                    }
                }
                if(wcs.size() == 1){
                    queryBuilder.where(wcs.get(0));
                }else{
                    WhereCondition[] wcsArrs = new WhereCondition[wcs.size()-1];
                    wcsArrs = wcs.subList(1, wcs.size()).toArray(wcsArrs);
                    queryBuilder.where(wcs.get(0), wcsArrs);
                }
            }
        }

        return queryBuilder.build().list();
    }

    /**
     * @Description ?????
     */
    public List<K> query(Context context) {
        return query(context, null);
    }

    /**
     * @Description ??????
     */
    @Deprecated
    public K querySingle(Context context, WhereCondition wc, WhereCondition... wcs) {
        List<K> ks = query(context, wc, wcs);
        if (ks != null && ks.size() == 1) { return ks.get(0); }
        return null;
    }

    /**
     * @Description ??????
     */
    public K querySingle(Context context, Property[] columns, Object[] columnValues) {
        List<K> ks = query(context, columns, columnValues);
        if (ks != null && ks.size() == 1) { return ks.get(0); }
        return null;
    }

    /**
     * @Description ?????????
     */
    public K querySingle(Context context) {
        return querySingle(context, new Property[]{}, new Object[]{});
    }
}
