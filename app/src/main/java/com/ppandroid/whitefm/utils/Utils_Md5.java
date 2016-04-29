package com.ppandroid.whitefm.utils;

import java.security.MessageDigest;

public class Utils_Md5 {
	
	protected final static String SECRET = "ppandroid";

    private static String byte2hex(byte[] b) {
        StringBuffer hs = new StringBuffer();
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) hs.append("0").append(stmp);
            else hs.append(stmp);
        }
        return hs.toString();
    }

    /**
     *
     * md5Digest
     * XMPP ????passport??
     * @param content
     * @return
     * String
     * @exception
     * @since  1.0.0
     */
    public static String md5Digest(String content){
		StringBuffer digestContent = new StringBuffer(SECRET);
		digestContent.append(content).append(SECRET);
		String result = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			result = byte2hex(md.digest(digestContent.toString().getBytes("utf-8")));
		} catch (Exception e) {
			throw new java.lang.RuntimeException("sign error !");
		}
		return result;
	}


    public static String MD5Sign(String content){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return byte2hex(md.digest(content.getBytes("utf-8")));
        } catch (Exception e) {
            throw new java.lang.RuntimeException("sign error !");
        }
    }
}
