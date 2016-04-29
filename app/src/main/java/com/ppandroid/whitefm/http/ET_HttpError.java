package com.ppandroid.whitefm.http;


import com.ppandroid.whitefm.http.bean.BN_BaseBody;

/**
 * HTTP??????????????????
 */
public class ET_HttpError {

	public int						taskId;

	/**
	 * 9001 ????<br>
	 * 9002 ?????<br>
	 * --???????????
	 */
	public int						errorCode;

	public String errorDescription;

	/**
	 *
	 * ?????????????????????<br>
	 * -- True: ????????????;<br>
	 * -- False: ?????;
	 * 
	 */
	public boolean					isUIGetDbData;

	public BN_BaseBody httpResponse;

	public ET_HttpError(int taskId, int errorCode, String errorDescription) {
		this.taskId = taskId;
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}

	public ET_HttpError(int taskId, int errorCode, String errorDescription, boolean isUIGetDbData) {
		this.taskId = taskId;
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
		this.isUIGetDbData = isUIGetDbData;
	}
}
