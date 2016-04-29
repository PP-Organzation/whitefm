/**
 * @Description 保存一些简单的数据
 * @author jxb
 * @date 2015年3月14日 下午15:19:40
 */
package com.ppandroid.whitefm.utils;

import java.util.Map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.ppandroid.whitefm.constant.ConstantParams;

@SuppressLint("CommitPrefEdits") public class Utils_SharedPreferences {
	
	private Context context;
	private SharedPreferences mKV;
	private Editor mEditor;
	
	/**
	 * ?????
	 * @param context
	 *            ????????Context.MODE_APPEND, Context.MODE_PRIVATE,
	 *            Context.WORLD_READABLE, Context.WORLD_WRITEABLE.
	 */
	public Utils_SharedPreferences(Context context) {
		mKV = context.getSharedPreferences(ConstantParams.sharePreferenceFileName, Context.MODE_PRIVATE);
		mEditor = mKV.edit();
		this.context = context;
	}

	public Utils_SharedPreferences(Context context, String fileName) {
		mKV = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
		mEditor = mKV.edit();
		this.context = context;
	}

	/**
	 * ??????boolean???
	 *
	 * @param key
	 *            ??
	 * @param defValue
	 *            ????????????
	 * @return ???????????????????
	 */
	public boolean getBoolean(String key, boolean defValue) {
		return mKV.getBoolean(key, defValue);
	}
	/**
	 * ??????int???
	 * @param key
	 *            ??
	 * @param defValue
	 *            ????????????
	 * @return ???????????????????
	 */
	public int getInt(String key, int defValue) {
		return mKV.getInt(key, defValue);
	}
	/**
	 * ??????long???
	 * @param key
	 *            ??
	 * @param defValue
	 *            ????????????
	 * @return ???????????????????
	 */
	public long getLong(String key, long defValue) {
		return mKV.getLong(key, defValue);
	}
	/**
	 * ??????float???
	 * @param key
	 *            ??
	 * @param defValue
	 *            ????????????
	 * @return ???????????????????
	 */
	public float getFloat(String key, float defValue) {
		return mKV.getFloat(key, defValue);
	}
	/**
	 * ??????String???
	 * @param key
	 *            ??
	 * @param defValue
	 *            ????????????
	 * @return ???????????????????
	 */
	public String getString(String key, String defValue) {
		return mKV.getString(key, defValue);
	}
	/**
	 * ????????
	 * @return ??????????
	 */
	public Map<String, ?> getAll() {
		return mKV.getAll();
	}
	/**
	 * ???????????{@linkplain #commit()}???????<br/>
	 * ???????value??boolean, byte(?????int??),int, long, float,
	 * String?????????toString()?????????
	 * @param key
	 *            ????
	 * @param value
	 *            ??
	 * @return ???KV???
	 */
	public Utils_SharedPreferences put(String key, Object value) {
		if (value instanceof Boolean) {
			mEditor.putBoolean(key, (Boolean) value);
		} else if (value instanceof Integer || value instanceof Byte) {
			mEditor.putInt(key, (Integer) value);
		} else if (value instanceof Long) {
			mEditor.putLong(key, (Long) value);
		} else if (value instanceof Float) {
			mEditor.putFloat(key, (Float) value);
		} else if (value instanceof String) {
			mEditor.putString(key, (String) value);
		} else if (value == null) {
			mEditor.putString(key, "");
		} else {
			mEditor.putString(key, value.toString());
		}
		mEditor.commit();
		return this;
	}
	/**
	 * ????????
	 * @return ???KV???
	 */
	public Utils_SharedPreferences clear() {
		mEditor.clear();
		mEditor.commit();
		return this;
	}
	/**
	 * ????????
	 * @param key
	 *            ???????
	 * @return ???????????true, ????false.
	 */
	public boolean contains(String key) {
		return mKV.contains(key);
	}
}
