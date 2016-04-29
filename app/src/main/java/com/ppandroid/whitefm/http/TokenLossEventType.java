package com.ppandroid.whitefm.http;

public class TokenLossEventType {

	public static final int token_lose = 1001003;
	
	public static final int token_null = 1001002;
	
	public int apiStatus;

	public TokenLossEventType(int apiStatus) {
		super();
		this.apiStatus = apiStatus;
	}
	
}
