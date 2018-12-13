package org.util.tools;

import java.security.MessageDigest;

public class EncoderUtil {
	 
	private static final String ALGORITHM = "MD5";
	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5','6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
    public static String encode(String algorithm, String str) {
        if (str == null) {
            return null;
        }
        try {
        	MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        	messageDigest.update(str.getBytes());
        	return getFormattedText(messageDigest.digest());
        	} catch (Exception e) {
        		throw new RuntimeException(e);
        	}
		}
	public static String encodeByMD5(String str) {
		if (str == null) {
			return null;
		}
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
			messageDigest.update(str.getBytes());
			return getFormattedText(messageDigest.digest());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	private static String getFormattedText(byte[] bytes) {
		int len = bytes.length;
		StringBuilder buf = new StringBuilder(len * 2);
		// 把密文转换成十六进制的字符串形式
		for (int j = 0; j < len; j++) {
			buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
			buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
		}
		return buf.toString();
	}
	public static void main(String[] args) {
		
		System.out.println("111111 MD5  :"+ EncoderUtil.encodeByMD5("111111"));
	    System.out.println("111111 MD5  :"+EncoderUtil.encode("MD5", "111111"));
	    System.out.println("111111 SHA1 :"+EncoderUtil.encode("SHA1", "13927161031393517055wolf55"));
	    System.out.println("111111 SHA1 :"+"8e039988080d1b03bba507754d88c6adb1527c93");
	 }
}