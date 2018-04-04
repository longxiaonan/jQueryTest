package common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
    * @ClassName: TimeUtils
	* @Description:
    * @author 龙小南
    * @email longxiaonan@163.com
    * @date 2017年9月5日 上午11:45:59
    * @version 1.0
    */
public class DateUtils {
	public static void main(String[] args) {
		long t1 = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:hh");
		Date d1 = new Date(t1);
		System.out.println(sdf.format(d1));
	}
}
