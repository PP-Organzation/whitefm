package com.ppandroid.whitefm.db;

import android.content.Context;

import com.ppandroid.whitefm.db.base.GreenDaoHelper;

import java.util.HashMap;

import de.greenrobot.dao.AbstractDao;


/**
 * @Description ???DAO????
 */
@SuppressWarnings("rawtypes")
public class DbManager {
	private static DbManager instance;
	private GreenDaoHelper greenDaoHelper;
	/** ??????DAO */
	private HashMap<String, AbstractDao> daoMaps;

	private DbManager(Context context) {
		//GreenDaoHelper.registerDao(DBModelDao.class.getName(), DBModelDaoInfc.getInstance());
		/*GreenDaoHelper.registerDao(MessagesDao.class.getName(), MessagesDaoInfc.getInstance());
		GreenDaoHelper.registerDao(P2PMsgListInfoDao.class.getName(), P2PMsgListMgr.getInstance());
		GreenDaoHelper.registerDao(MsgDetailDao.class.getName(), MsgDetailDaoInfc.getInstance());*/

		daoMaps = new HashMap<String, AbstractDao>();
		greenDaoHelper = GreenDaoHelper.getInstance(context);

	}

	public static DbManager getInstance(Context context) {
		if (instance == null) {
			synchronized (DbManager.class) {
				if (instance == null) {
					instance = new DbManager(context);
				}
			}
		}
		return instance;
	}

	/**
	 * @Description ????????DAO
	 * @author LuoZheng
	 * @date 2015?3?11? ??3:17:21
	 */
	public AbstractDao getDao(String daoName) {
		AbstractDao dao = daoMaps.get(daoName);
		if (dao == null) {
			dao = greenDaoHelper.getGreenDaoSession().getAbstractDao(daoName);
			daoMaps.put(daoName, dao);
		}
		return dao;
	}

}
