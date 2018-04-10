package common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期的工具类
 * 
 */
public class DateUtil {

	private static SimpleDateFormat YYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat YYYYMMDDHHMMSS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private final static int MIN = 60 * 1000;

	private DateUtil() {
	}

	/**
	 * 获取时间 间隔
	 * 
	 * @param level
	 *            时间粒度 0-分钟 1-5分钟 2-10分钟 3-15分钟 4-30分钟 5-小时 6-天 7-2分钟
	 * @return
	 */
	public static int getTimeInterval(int level) {
		int time_interval = 0;
		switch (level) {
		case 0:
			time_interval = 1 * MIN;
			break;
		case 1:
			time_interval = 5 * MIN;
			break;
		case 2:
			time_interval = 10 * MIN;
			break;
		case 3:
			time_interval = 15 * MIN;
			break;
		case 4:
			time_interval = 30 * MIN;
			break;
		case 5:
			time_interval = 60 * MIN;
			break;
		case 6:
			time_interval = 24 * 60 * MIN;
			break;
		case 7:
			time_interval = 2 * MIN;
			break;
		default:
			time_interval = 60 * MIN;
			break;
		}
		return time_interval;
	}

	/**
	 * 判断两个日期相距的间隔(相隔多少天, 多少秒, 多少小时等)
	 * 
	 * @param date1
	 *            参照的日期
	 * @param date2
	 *            目标日期
	 * @param level
	 *            间隔标准
	 * @return
	 */
	public static int getDistance(Date date1, Date date2, int level) {
		return getDistance(date1.getTime(), date2.getTime(), 6);
	}

	/**
	 * 判断两个日期相距的间隔(相隔多少天, 多少秒, 多少小时等)
	 * 
	 * @param date1
	 *            参照的日期
	 * @param date2
	 *            目标日期
	 * @param level
	 *            间隔标准, 表示返回的是多少天, 多少秒, 多少小时等
	 * @return
	 */
	public static int getDistance(long date1, long date2, int level) {
		long interval = date1 - date2;
		int time_interval = getTimeInterval(level);
		int dis = (int) (interval / time_interval);
		return dis;
	}

	/**
	 * 如果一个日期是空，则抛出RuntimeException(msg)异常
	 * 
	 * @param date
	 * @param msg
	 */
	public static void checkIsNull(Date date, String msg) {

		if (date == null) {

			throw new RuntimeException(msg);
		}

	}

	/**
	 * 获取beginTime后跨了机构24点才到了endTime, 也就是跨的天数
	 * 
	 * @param beginTime
	 * @param endTime
	 * @return 返回跨的天数
	 */
	public static int getSpanDayNum(Date beginTime, Date endTime) {
		return getSpanDayNum(beginTime.getTime(), endTime.getTime());
	}

	/**
	 * 获取beginTime后跨了机构24点才到了endTime, 也就是跨的天数
	 * 
	 * @param beginTime
	 * @param endTime
	 * @return 返回跨的天数
	 */
	public static int getSpanDayNum(long beginTime, long endTime) {
		Calendar c1 = Calendar.getInstance();
		c1.setTimeInMillis(beginTime);
		Calendar c2 = Calendar.getInstance();
		c2.setTimeInMillis(endTime);
		// 获取c1当天时间的24点， 也就是第二天的零点
		Calendar c3 = Calendar.getInstance();
		c3.set(c1.get(Calendar.YEAR), c1.get(Calendar.MONTH), c1.get(Calendar.DAY_OF_MONTH) + 1, 0, 0, 0);
		long a = c3.getTimeInMillis();// c1第二天零点的毫秒数
		long b = 24 * 3600 * 1000;// 一天的毫秒数
		int count = 0;
		// 如果当天的24点还比c2的毫秒数小， 说明有跨天， 跨天数加一
		for (int i = 0; a + i * b < c2.getTimeInMillis(); i++) {
			count++;
		}
		return count;
	}

	/**
	 * 获取指定interval的那天的最后的时间 比如time为2017-12-21 22:00:01, interval为1,
	 * 表示获取到22日那天的23:59； interval为-1, 表示获取到20日那天的23:59如果； interval为0，
	 * 表示获取到当天的23:59。同理， interval还可以为其他的整数
	 */
	public static long getEndTime(long time, int interval) {
		Calendar c1 = Calendar.getInstance();
		c1.setTimeInMillis(time);
		// 获取c1当天时间的24点， 也就是第二天的零点
		Calendar c3 = Calendar.getInstance();
		c3.set(c1.get(Calendar.YEAR), c1.get(Calendar.MONTH), c1.get(Calendar.DAY_OF_MONTH) + interval, 23, 59, 59);
		return c3.getTimeInMillis();
	}
	public static long getEndTime(Date time, int interval) {
		return getEndTime(time.getTime(),interval);
	}
	
	/**
	 * 获取当前时间的前一天的开始时间，如：今天为2014-05-20 11:11:11，返回时间为：2014-05-19 00:00:00
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static Date getBeforeCurrentDayStartTime() {

		try {

			Calendar cal = Calendar.getInstance();

			SimpleDateFormat YYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");

			cal.add(Calendar.DAY_OF_MONTH, -1);

			SimpleDateFormat YYYYMMDDHHMMSS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			String strTime = YYYYMMDD.format(cal.getTime()) + " 00:00:00";

			return YYYYMMDDHHMMSS.parse(strTime);

		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	/**
	 * 获取当前时间的前一天的最晚时间，如：今天为2014-05-20 11:11:11，返回时间为：2014-05-19 23:59:59
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static Date getBeforeCurrentDayEndTime() {

		try {

			Calendar cal = Calendar.getInstance();

			SimpleDateFormat YYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");

			cal.add(Calendar.DAY_OF_MONTH, -1);

			SimpleDateFormat YYYYMMDDHHMMSS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			String strTime = YYYYMMDD.format(cal.getTime()) + " 23:59:59";

			return YYYYMMDDHHMMSS.parse(strTime);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	/** 获取当前时间的最早时间，如：date为2014-05-20 11:11:11，返回时间为：2014-05-20 00:00:00 */
	public static Date getDayStartTime(Date date) {
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			String strTime = YYYYMMDD.format(cal.getTime()) + " 00:00:00";
			return YYYYMMDDHHMMSS.parse(strTime);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public static Date getDayStartTime(long date) {
		return getDayStartTime(new Date(date));
	}

	/** 获取指定时间的最晚时间，如：date为2014-05-20 11:11:11，返回时间为：2014-05-20 23:59:59 */
	public static Date getDayEndTime(Date date) {
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			String strTime = YYYYMMDD.format(cal.getTime()) + " 23:59:59";
			return YYYYMMDDHHMMSS.parse(strTime);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/** 获取指定时间为日期的的23点55分的时间 */
	public static Date getDay2355(Date date) {
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			String strTime = YYYYMMDD.format(cal.getTime()) + " 23:55:00";
			return YYYYMMDDHHMMSS.parse(strTime);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/** 获取指定时间为日期的的23点55分的时间 */
	public static Date getDay2355(long date) {
		return getDay2355(new Date(date));
	}

	/** 获取指定时间的最晚时间，如：date为2014-05-20 11:11:11，返回时间为：2014-05-20 23:59:59 */
	public static Date getDayEndTime(long date) {
		return getDayEndTime(new Date(date));
	}

	public static Date getDay2359(long date) {
		return getDayEndTime(new Date(date));
	}

	public static Date getDay2359(Date date) {
		return getDayEndTime(date);
	}

	/** 获取当前时间的最早时间，如：今天为2014-05-20 11:11:11，返回时间为：2014-05-20 00:00:00 */
	public static Date getCurrentDayStartTime() {
		try {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat YYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat YYYYMMDDHHMMSS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String strTime = YYYYMMDD.format(cal.getTime()) + " 00:00:00";
			return YYYYMMDDHHMMSS.parse(strTime);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获取当前时间的最晚时间，如：今天为2014-05-20 11:11:11，返回时间为：2014-05-20 23:59:59
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static Date getCurrentDayEndTime() {
		try {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat YYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat YYYYMMDDHHMMSS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String strTime = YYYYMMDD.format(cal.getTime()) + " 23:59:59";
			return YYYYMMDDHHMMSS.parse(strTime);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	/**
	 * 以yyyy-MM-dd HH:mm:ss进行格式化
	 * 
	 * @param date
	 * @return
	 */
	public static String formatYYYYMMDDHHMMSS(Date date) {

		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}

	/**
	 * 以yyyy-MM-dd进行格式化
	 * 
	 * @param date
	 * @return
	 */
	public static String formatYYYYMMDD(Date date) {

		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

	/**
	 * 以yyyyMMdd进行格式化
	 * 
	 * @param date
	 * @return
	 */
	public static String formatYYYYMMDDWithoutLine(Date date) {

		return new SimpleDateFormat("yyyyMMdd").format(date);
	}

	/**
	 * 把距离1970年的毫秒数，格式化成yyyy-MM-dd HH:mm:ss字符串表现形式
	 * 
	 * @param time
	 *            距离1970年的毫秒数
	 * @return
	 */
	public static String formatYYYYMMDDHHMMSS(long time) {

		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(time));
	}

	/**
	 * 以yyyy-MM-dd HH:mm:ss进行格式化
	 * 
	 * @param s
	 * @return
	 */
	public static Date parseYYYYMMDDHHMMSS(String s) {

		try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	/**
	 * 以yyyy-MM-dd进行格式化
	 * 
	 * @param s
	 * @return
	 */
	public static Date parseYYYYMMDD(String s) {

		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	/**
	 * 返回当前时间距离传递参数的日期，相隔多少秒,比如当前时间为：2014-05-22 00:00:00 ,传递的参数为：2014-05-23
	 * 00:00:00，那就返回一天的秒数：60*60*24=86400秒
	 * 
	 * @param d
	 *            未来的某个时间
	 * @return
	 */
	public static long currentTimeSeparatedSecond(Date d) {

		if (d == null) {
			throw new IllegalArgumentException("传递的日期不能为空");
		}

		return (d.getTime() - new Date().getTime()) / 1000;
	}

	/**
	 * java.util.Date转成java.sql.Timestamp
	 * 
	 * @param d
	 * @return
	 */
	public static java.sql.Timestamp parseToTimestamp(Date d) {

		if (d == null) {
			throw new IllegalArgumentException("传递的日期不能为空");
		}

		return new java.sql.Timestamp(d.getTime());
	}

	/**
	 * 计算某个日期加(或者减)天数的日期，如果想减，则传递负值，如：-7 [7天前的日期]，如果想加，传递正值，如：7 [7天后的数据]
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date distanceDaysOfDate(Date date, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, days);
		return c.getTime();
	}

	public static void main(String[] args) throws Exception {
		
		
		Date date = new Date();
		long 	endTime = getEndTime(date,-2);
		System.out.println(new Date(endTime));
		/*
		 * Date date = new Date();
		 * 
		 * System.out.println(DateUtil.formatYYYYMMDDHHMMSS(date));
		 * 
		 * TimeUnit.SECONDS.sleep(6);
		 * 
		 * 
		 * System.out.println(DateUtil.formatYYYYMMDDHHMMSS(distanceDaysOfDate(
		 * new Date(),7)));
		 */

		/*
		 * Date d = getBeforeCurrentDayStartTime();
		 * 
		 * System.out.println(d);
		 * 
		 * System.out.println(formatYYYYMMDDHHMMSS(d));
		 * 
		 * System.out.println("--------------------");
		 * 
		 * Date d2 = getBeforeCurrentDayEndTime(); System.out.println(d2);
		 * 
		 * System.out.println(formatYYYYMMDDHHMMSS(d2));
		 * 
		 * long d1Time = d.getTime();
		 * 
		 * long d2Time = d2.getTime();
		 * 
		 * System.out.println("d1Time:" + d1Time); System.out.println("d2Time:"
		 * + d2Time); System.out.println("d2Time-d1Time:" + (d2Time-d1Time));
		 * 
		 * 
		 * 
		 * System.out.println("end:" +
		 * currentTimeSeparatedSecond(parseYYYYMMDDHHMMSS("2014-05-23 00:00:00"
		 * )));
		 * 
		 * System.out.println("getCurrentDayEndTime():" +
		 * getCurrentDayEndTime());
		 */

		/*
		 * Date d = parseYYYYMMDD("2014-07-02"); System.out.println(d);
		 */

		// System.out.println(formatYYYYMMDDHHMMSS(1000 * 60 * 10));
		// //格式化：1970-01-01 08:10:00 ,因为我们这里是东八区，所以加了八个小时

	}

}
