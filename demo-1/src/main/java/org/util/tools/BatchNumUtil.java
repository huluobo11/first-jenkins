package org.util.tools;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 */
public class BatchNumUtil {
	private static BatchNumUtil commonUtil = new BatchNumUtil();
	private static long startNum=1L;
	private static int codeNum=4;
	private BatchNumUtil() {

	}

	public static BatchNumUtil getInstance() {
		if (commonUtil == null) {
			commonUtil = new BatchNumUtil();
		}
		return commonUtil;
	}

	public synchronized String getNum()  {
		if(startNum==9999){
			startNum=1L;
		}
		return autoGenericCode(startNum++);
	}
	
	public  String todayTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(new Date());
	}
	
	public  String getUniqueId() {
		return todayTime()+BatchNumUtil.getInstance().getNum();
	}
	
	public static String autoGenericCode(long num) {
        return String.format("%0" + codeNum + "d", num);
    }
	
}
