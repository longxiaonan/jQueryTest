

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
/**
 * 日期格式处理工具类
 * @ClassName DateUtils
 * @date 2017年9月28日 下午2:41:32
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils{

	private String dirpath = "D:/";
	/**
	 * HH:mm:ss
	 */
	public static final String HHmmss = "HH:mm:ss";
	/**
	 * yyyy-MM
	 */
	public static final String yyyy_MM = "yyyy-MM";
	/**
	 * yy-MM-dd
	 */
	public static final String yy_MM_dd = "yy-MM-dd";
	/**
	 * yyyy-MM-dd
	 */
	public static final String yyyy_MM_dd = "yyyy-MM-dd";

	/**
	 * yy-MM-dd HH:mm:ss
	 */
	public static final String yy_MM_ddHHmmss = "yy-MM-dd HH:mm:ss";
	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static final String yyyy_MM_ddHHmmss = "yyyy-MM-dd HH:mm:ss";
	/**
	 * yyyy-MM-dd HH:mm:ss:SSS
	 */
	public  static final String yyyy_MM_ddHHmmssSSS = "yyyy-MM-dd HH:mm:ss:SSS";
	/**
	 * HHmmssSSS
	 */
	public static final String HHmmssSSS = "HHmmssSSS";
	/**
	 * yyyyMMdd
	 */
	public static final String yyMMdd = "yyyyMMdd";
	/**
	 * yyyyMMddHHmmssSSS
	 */
	public static final String yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";
	/**
	 * yyyyMMddHHmmss
	 */
	public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";

	private DateUtils() {
		
	}
	
	/*@Test
	public void test(){
		Date parseToDate = parseToDate("2017-9-24 00:00:24", DateUtils.yyyy_MM_ddHHmmss);
		System.out.println(parseToDate);
	}*/
	
	@SuppressWarnings("unused")
	private void init() {
		StringBuffer sb = new StringBuffer(getClass().getResource("/_logs/readme.txt").getFile());
		sb.delete(0, 1);
		sb.delete(sb.indexOf("readme.txt"), sb.length());
		dirpath = sb.toString();
	}
	
	/**
	 * 返回日期字符串对应的日期, 调用方式:parseToDate("2017-9-24 00:00:24", DateUtils.yyyy_MM_ddHHmmss);
	 * @param dateString 日期类型字符串,格式要大于2017-9-24，不能是年月
	 * @param format 日期格式
	 * @return 日期字符串对应的日期
	 */
	public static Date parseToDate(String dateString, String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
		simpleDateFormat.applyPattern(format);
		Date date = null;
		if (dateString == null || dateString.length() < 8)
			return null;
		try {
			date = simpleDateFormat.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**不知道有啥用
	 * 返回日期字符串对应format格式的日期字符串
	 * @param dateString 日期字符串类型
	 * @param format 日期格式
	 * @return
	 */
	public static String parseToString(String dateString, String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
		simpleDateFormat.applyPattern(format);
		Date date = null;
		String str = null;
		if (dateString == null || dateString.length() < 8)
			return null;
		try {
			date = simpleDateFormat.parse(dateString);
			str = simpleDateFormat.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 返回输入格式格式format的Date类型
	 * @param date 日期类型
	 * @param format 日期格式
	 * @return
	 */
	public static String dateToString(Date date, String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
		simpleDateFormat.applyPattern(format);
		String str = null;
		if (date == null){
			return null;
		}
		str = simpleDateFormat.format(date);
		return str;
	}
	
	/**
	 * 传入的date按照format格式化
	 * @param date  如：2017-11-24
	 * @param format  如：DateUtils.yyyy_MM_dd
	 * @return   返回Date
	 */
	public static Date StringToDate(String date, String format){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException("字符串转Date出错(StirngToDate())");
		}
	}			
	
	
	/**
	 * 按照传入的时间往前退多少毫秒
	 * @param timeformat  格式化字符串，如：DateUtils.yyyy_MM_ddHHmmss
	 * @param init    传入的字符串时间，如：2017-11-12 10:10:10
	 * @param modify   需要往前进多少毫秒，如：10000（10秒）
	 * @return
	 */
	public static String timeInterval(String timeformat, String init,long modify) {
		SimpleDateFormat dateformat = new SimpleDateFormat(timeformat);
		long dateinit = 0L;
		try {
			dateinit = dateformat.parse(init).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException("timeInterval()出错");
		}
		String time2 = dateformat.format(new Date(dateinit - modify));
		// System.out.println("init-time:"+dateformat.format(new
		// Date(dateinit))+" modify:"+time2);

		return time2;
	}

	/**
	 * 按照传入的时间往前退多少毫秒
	 * @param timeformat  格式化字符串，如：DateUtils.yyyy_MM_ddHHmmss
	 * @param init    传入的Date
	 * @param modify   需要往前进多少毫秒，如：10000（10秒）
	 * @return
	 */
	public static String timeInterval(String timeformat, Date init, long modify) {
		SimpleDateFormat dateformat = new SimpleDateFormat(timeformat);
		long dateinit = 0L;
		try {
			dateinit = dateformat.parse(dateformat.format(init)).getTime();
		} catch (ParseException ex) {
			System.out.println(ex.toString());
		}
		String time2 = dateformat.format(new Date(dateinit - modify));
		// System.out.println("init-time:"+dateformat.format(new
		// Date(dateinit))+" modify:"+time2);

		return time2;
	}

	/**
	 * 获取传入参数距离当前时间的天数
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(24*60*60*1000);
	}

	/**
	 * 获取传入参数距离当前的小时数
	 * @param date
	 * @return
	 */
	public static long pastHour(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*60*1000);
	}
	
	/**
	 * 获取传入参数距离当前时间的分钟数
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*1000);
	}
	
	/**
	 * 算出当前时间往前退或往后进几天
	 * @param style  传入的参数是yyyy-MM-dd才有意义
	 * @param diff  传入的值只能是正负24之内的，包括正负24
	 * @return
	 */
	public static String dayInterval(String style, int diff) {
		SimpleDateFormat sdf = null;
		if (style == null || "".equals(style)) {
			style = "yyyy-MM-dd";
		}
		try {
			sdf = new SimpleDateFormat(style);
		} catch (Exception ex) {
			sdf = new SimpleDateFormat("yyyy-MM-dd");
		}
		String value = sdf.format(new Date(System.currentTimeMillis() + 24 * 60
				* 60 * 1000 * diff));
		return value;
	}
	
	/**
	 * 重复功能  dayInterval(String style, int diff)
	 * @param style
	 * @param diff
	 * @param type
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String dayInterval(String style, int diff ,int type) {
		SimpleDateFormat sdf = null;
		Date init =new Date(System.currentTimeMillis());
		String value = "";
		if (style == null || "".equals(style)) {
			style = "yyyy-MM-dd";
		}
		
		try {
			if(type==1){
				init.setHours(0);
				init.setMinutes(0);
				init.setSeconds(0);
			}
			sdf = new SimpleDateFormat(style);
			value = sdf.format(new Date(init.getTime() + 24 * 60
					* 60 * 1000 * diff));
			
		} catch (Exception ex) {
			sdf = new SimpleDateFormat("yyyy-MM-dd");
			value = sdf.format(new Date(init.getTime() + 24 * 60
					* 60 * 1000 * diff));
		}
		
		return value;
	}

	/**
	 * 校验字符串是否可以按照传入的格式解析（没啥必要）
	 * checkDateValidate("2017-11",DateUtils.yyyy_MM_dd);false
	 * checkDateValidate("2017-11-12",DateUtils.yyyy_MM);true
	 * @param dateStr
	 * @param style
	 * @return
	 */
	@SuppressWarnings({ "unused", "finally" })
	public static boolean checkDateValidate(String dateStr, String style) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
		simpleDateFormat.applyPattern(style);
		Date date = null;
		String str = null;
		boolean flag = false;
		if (dateStr == null || dateStr.length() < 8)
			return flag;
		try {
			date = simpleDateFormat.parse(dateStr);
			str = simpleDateFormat.format(date);
			flag = true;
		} catch (ParseException e) {
			e.printStackTrace();
			flag = false;
		} finally {
			return flag;
		}

	}
	
	/**
	 * 当前时间年或月或日加减
	 * @param c  y,m,d,不分大小写  如：y  当前时间是2017-11-24
	 * @param amount  加减的数目    如：1   结果是2018-11-24
	 * @return
	 */
	public static String getDateStr(char c, int amount) {
		java.util.Calendar cur_date = Calendar.getInstance();
		if (c == 'Y' || c == 'y') {
			cur_date.add(java.util.Calendar.YEAR, amount);
		} else if (c == 'M' || c == 'm') {
			cur_date.add(java.util.Calendar.MONTH, amount);
		} else if (c == 'D' || c == 'd') {
			cur_date.add(java.util.Calendar.DATE, amount);
		}
		int yyyy = cur_date.get(Calendar.YEAR);
		int mm = cur_date.get(Calendar.MONTH) + 1;
		int dd = cur_date.get(Calendar.DATE);
		cur_date = null;
		return (String.valueOf(yyyy) + "-" + (mm < 10 ? "0" : "")
				+ String.valueOf(mm) + "-" + (dd < 10 ? "0" : "") + String
				.valueOf(dd));
	}

	public boolean AddLog(String type, String action, String status,
			String title, String detail) {
		return true;
	}

	public void AddLog(String sMsg) {
		String sFileName = "";
		String sDate = "";
		String sTime = "";
		String sMessage = "";

		if (sMsg.equals("")) {
			return;
		}

		// java.util.Date cur_date = new java.util.Date();
		java.util.Calendar cur_date = Calendar.getInstance();
		int iYear = cur_date.get(Calendar.YEAR);
		int iMonth = cur_date.get(Calendar.MONTH) + 1;
		int iDay = cur_date.get(Calendar.DATE);
		int iHour = cur_date.get(Calendar.HOUR_OF_DAY);
		int iMinute = cur_date.get(Calendar.MINUTE);
		int iSecond = cur_date.get(Calendar.SECOND);
		cur_date = null;

		String sYear = Integer.toString(iYear);
		String sMonth = (iMonth < 10) ? ("0" + Integer.toString(iMonth))
				: Integer.toString(iMonth);
		String sDay = (iDay < 10) ? ("0" + Integer.toString(iDay)) : Integer
				.toString(iDay);
		String sHour = (iHour < 10) ? ("0" + Integer.toString(iHour)) : Integer
				.toString(iHour);
		String sMinute = (iMinute < 10) ? ("0" + Integer.toString(iMinute))
				: Integer.toString(iMinute);
		String sSecond = (iSecond < 10) ? ("0" + Integer.toString(iSecond))
				: Integer.toString(iSecond);

		sDate = sYear + "/" + sMonth + "/" + sDay;
		sTime = sHour + ":" + sMinute + ":" + sSecond;
		sFileName = dirpath + "report_" + sYear + sMonth + sDay + "_Log.log";
		sMessage = "[" + sDate + " " + sTime + "] : " + sMsg;

		PrintWriter log;
		try {
			log = new PrintWriter(new FileWriter(sFileName, true), true);
			log.println(sMessage + "\r"); // auto enter; log.print can't auto
			// enter
			log.close();
		} catch (IOException e) {
			System.err.println("Can't not open file: " + sFileName);
		}
		return;
	}

	/**
	 * ErrLog function
	 * 
	 * @param sMsg
	 */
	public void ErrLog(String sMsg) {
		String sFileName = "";
		String sDate = "";
		String sTime = "";
		String sMessage = "";

		if (sMsg.equals("")) {
			return;
		}

		// java.util.Date cur_date = new java.util.Date();
		java.util.Calendar cur_date = Calendar.getInstance();
		int iYear = cur_date.get(Calendar.YEAR);
		int iMonth = cur_date.get(Calendar.MONTH) + 1;
		int iDay = cur_date.get(Calendar.DATE);
		int iHour = cur_date.get(Calendar.HOUR_OF_DAY);
		int iMinute = cur_date.get(Calendar.MINUTE);
		int iSecond = cur_date.get(Calendar.SECOND);
		cur_date = null;

		String sYear = Integer.toString(iYear);
		String sMonth = (iMonth < 10) ? ("0" + Integer.toString(iMonth))
				: Integer.toString(iMonth);
		String sDay = (iDay < 10) ? ("0" + Integer.toString(iDay)) : Integer
				.toString(iDay);
		String sHour = (iHour < 10) ? ("0" + Integer.toString(iHour)) : Integer
				.toString(iHour);
		String sMinute = (iMinute < 10) ? ("0" + Integer.toString(iMinute))
				: Integer.toString(iMinute);
		String sSecond = (iSecond < 10) ? ("0" + Integer.toString(iSecond))
				: Integer.toString(iSecond);

		sDate = sYear + "/" + sMonth + "/" + sDay;
		sTime = sHour + ":" + sMinute + ":" + sSecond;
		sFileName = dirpath + "report_" + sYear + sMonth + sDay + "_Log.log";
		sMessage = "[" + sDate + " " + sTime + "] : " + sMsg;

		PrintWriter log;
		try {
			log = new PrintWriter(new FileWriter(sFileName, true), true);
			log.println(sMessage + "\r"); // auto enter; log.print can't auto
			// enter
			log.close();
		} catch (IOException e) {
			System.err.println("Can't not open file: " + sFileName);
		}
		return;
	}
	
	/**
	 * 获取当前时间并格式化为:yyyy-M-d HH:mm:ss
	 * @return
	 */
	public static String currentTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d HH:mm:ss");
		String time = dateFormat.format(new Date(System.currentTimeMillis()));
		return time;
	}
	
	
	/**
	 * 获取当前时间并格式化为传入参数的格式
	 * @param style  如：DateUtils.yyyy_MM
	 * @return
	 */
	public static String currentTime(String style) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(style);
		String time = dateFormat.format(new Date(System.currentTimeMillis()));
		return time;
	}

	/**
	 * 
	 * @param dateList list.add("a") list.add("1");
	 * @param hashMap  姓名={paramMap.put("年龄","13")}
	 * @return   {姓名={a=0.0, 1=0.0, 年龄=13}}
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static HashMap mendData(ArrayList dateList, HashMap hashMap) {

		Iterator it = hashMap.keySet().iterator();
		ArrayList keyArray = new ArrayList();
		while (it.hasNext()) {
			keyArray.add((String) it.next());
		}
		for (int i = 0; i < keyArray.size(); i++) {
			String key = (String) keyArray.get(i);
			HashMap dataMap = (HashMap) hashMap.get(key);
			for (int k = 0; k < dateList.size(); k++) {
				// String data[] =
				// (String[])dataMap.get((String)dateList.get(k));
				String dateKey = (String) dateList.get(k);
				if (!dataMap.containsKey(dateKey)) {
					dataMap.put(dateKey, new Float(0));
				}
			}
			hashMap.put(key, dataMap);
		}
		return hashMap;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static HashMap sortData(ArrayList dateList, HashMap hashMap) {

		Iterator it = hashMap.keySet().iterator();
		ArrayList keyArray = new ArrayList();

		while (it.hasNext()) {
			keyArray.add((String) it.next());
		}
		for (int i = 0; i < keyArray.size(); i++) {

			String key = (String) keyArray.get(i);
			HashMap dataMap = (HashMap) hashMap.get(key);
			ArrayList dataList = new ArrayList();
			for (int k = 0; k < dateList.size(); k++) {
				String dateKey = (String) dateList.get(k);
				Float value = (Float) dataMap.get(dateKey);
				dataList.add(value);
			}
			hashMap.put(key, dataList);
		}
		return hashMap;
	}
	
	/**
	 * 参数参数年月，获取该月份的第一天开始时间和上个月第一天的开始时间
	 * @param year  如：2018
	 * @param month  如:1
	 * @return  {start=2018-2-01 00:00:00, end=2018-1-01 00:00:00}
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static HashMap getDateString(String year, String month) {
		HashMap hashMap = new HashMap();

		int yearTemp = Integer.parseInt(year);
		int monthTemp = Integer.parseInt(month) + 1;

		if (monthTemp > 12) {
			monthTemp = monthTemp - 1;
			yearTemp = yearTemp + 1;
		}

		String monthS = String.valueOf(monthTemp);
		String yearS = String.valueOf(yearTemp);

		String start = yearS + "-" + monthS + "-01 00:00:00";
		String end = year + "-" + month + "-01 00:00:00";

		hashMap.put("start", start);
		hashMap.put("end", end);

		return hashMap;
	}
	
	/**
	 * 已过时，请使用getDayList(String dateStart, String dateEnd,String style)
	 * @param dateStart
	 * @param dateEnd
	 * @return
	 */
	@Deprecated
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static ArrayList getDateFromToDate(String dateStart, String dateEnd) {
		ArrayList list = new ArrayList();
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyyMMdd");
		Date date1 = null;
		Date date2 = null;
		try {
			date1 = myFormatter.parse(dateStart);
			date2 = myFormatter.parse(dateEnd);
		} catch (Exception ex1) {
			System.out.println("getDateFromToDate()出错！" + ex1.toString());
		}
		long day = (date1.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000)
				+ 1;
		long date3 = date1.getTime();
		;
		for (int i = 0; i < day; i++) {
			String dateStr = myFormatter.format((new Date(date3)));
			list.add(dateStr);
			date3 = date3 + 24 * 60 * 60 * 1000;
		}
		return list;
	}
	
	/**
	 * 计算开始时间结束时间相差多少天的日期集合
	 * @param dateStart 开始时间，如：2017-11-11
	 * @param dateEnd   结束时间   如：2017-11-13
	 * @param style     将字符串格式化，DateUtils.yyyy_MM_dd
	 * @return   输出结果:[2017-11-11, 2017-11-12, 2017-11-13]
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static ArrayList getDayList(String dateStart, String dateEnd,
			String style) {
		ArrayList list = new ArrayList();
		SimpleDateFormat myFormatter = new SimpleDateFormat(style);
		Date date1 = null;
		Date date2 = null;
		try {
			date1 = myFormatter.parse(dateStart);
			date2 = myFormatter.parse(dateEnd);
		} catch (Exception ex1) {
			System.out.println("日期转换格式应和传入的日期格式一致" + ex1.toString());
		}
		long day = (date2.getTime() - date1.getTime()) / (24 * 60 * 60 * 1000)
				+ 1;
		long date3 = date1.getTime();

		for (int i = 0; i < day; i++) {
			String dateStr = myFormatter.format((new Date(date3)));
			list.add(dateStr);
			date3 = date3 + 24 * 60 * 60 * 1000;
		}
		return list;
	}
	
	/**
	 * 计算开始时间结束时间相差几个月的日期集合
	 * @param beginTime  开始时间  如：2017/10,开始时间和结束时间的格式只能是这样
	 * @param endTime	 结束时间  如：2017/12
	 * @return   输出结果：[2017/10, 2017/11, 2017/12]
	 */
	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	public static ArrayList getMonthList(String beginTime, String endTime) {
		String[] time1 = beginTime.split("/");
		String[] time2 = endTime.split("/");
		int iyear1 = Integer.parseInt(time1[0]);
		int imonth1 = Integer.parseInt(time1[1]);
		int iyear2 = Integer.parseInt(time2[0]);
		int imonth2 = Integer.parseInt(time2[1]);
		int months = (iyear2 - iyear1) * 12 + (imonth2 - imonth1);

		ArrayList list = new ArrayList();
		boolean dif = false;

		for (int i = iyear1; i <= iyear2; i++) {
			for (int k = iyear1; k <= iyear2; k++) {
				if (dif)
					imonth1 = 1;
				if (i == iyear2) {
					for (int j = imonth1; j <= imonth2; j++) {
						String m = String.valueOf(j);
						m = (m.length() == 1) ? "/0" + m : "/" + m;
						String data = String.valueOf(i) + m;
						list.add(data);
					}
				} else if (iyear1 < iyear2) {
					for (int j = imonth1; j <= 12; j++) {
						String m = String.valueOf(j);
						m = (m.length() == 1) ? "/0" + m : "/" + m;
						String data = String.valueOf(iyear1) + m;
						list.add(data);
					}
				} else {
					String data = "2000/01";
					list.add(data);
				}
				i++;
				dif = true;
			}
		}
		return list;

	}
	
	
	/**
	 * 传入年-月，算出中间隔了几个月
	 * @param dateStart  开始时间   年-月
	 * @param dateEnd    结束时间   年-月
	 * @param style      
	 * @return
	 * @throws ParseException
	 * 
	 * 例如传入参数  "2017-11","2018-02","yyyy-MM",
	 * 输出的结果是2017-11,2017-12,2018-01,2018-02
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List getYearAndMonthList(String dateStart, String dateEnd,
			String style) {
		
		List list=new ArrayList();
		
		SimpleDateFormat sdf=new SimpleDateFormat(style);
		
		try {
		
	        Calendar c1=Calendar.getInstance();
	        Calendar c2=Calendar.getInstance();
	        
	        c1.setTime(sdf.parse(dateStart));
	        c2.setTime(sdf.parse(dateEnd));
	        
	        
	        int year =c2.get(Calendar.YEAR)-c1.get(Calendar.YEAR);
	        
	        int monthDistance;
	        //开始日期若小月结束日期
	        if(year<0){
	            year=-year;
	            monthDistance = year*12+c1.get(Calendar.MONTH)-c2.get(Calendar.MONTH);
	        }
	       
	        monthDistance = year*12+c2.get(Calendar.MONTH)-c1.get(Calendar.MONTH);
	        
	        int cyear;
	        int cmonth;
	        for(int i=0;i<monthDistance+1;i++){
	        	//获取开始时间的年份
	        	cyear=c1.get(c1.YEAR);
	        	//这里的月份要加1才是开始时间的月份
	        	c1.add(c1.MONTH, 1);
	        	//这里第一次加完之后是开始时间的月份
	        	cmonth=c1.get(c1.MONTH);
	        	//0代表12月
	        	if(cmonth==0){
	        		cmonth=12;
	        	}
	        	
	        	String yearAndMonth;
	        	if(cmonth<10){
	        		yearAndMonth=cyear+""+"-"+"0"+cmonth+"";
	        	}else{
	        		yearAndMonth=cyear+""+"-"+cmonth+"";
	        	}
	        	
	        	list.add(yearAndMonth);
	        	
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        return list;
	}
	
	/**
	 * 获取当前时间并且格式化为yyyy-M-d HH:mm:ss
	 * @param timeformat 参数不知道有啥用
	 * @return
	 */
	public static String getDateStyle(String timeformat) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d HH:mm:ss");
		String time = dateFormat.format(new Date(System.currentTimeMillis()));
		return time;
	}

	@SuppressWarnings("deprecation")
	public static void time(int year,int month,int day,int hour,int minu,int second){
		Calendar c1;

//		Date data3=new Date(2007,6,23);
//		锟斤拷锟斤拷锟斤拷锟斤拷(同锟较ｏ拷锟斤拷锟铰凤拷要锟斤拷1)
		Date d1=new Date(year,month,day,hour,minu,second);
//		Date d2=new Date(2007,6,23);
//		Date d3=new Date(2007,5,23);
		System.out.println(d1.toLocaleString());
		c1=Calendar.getInstance();

		c1.setTime(d1);
		System.out.println(c1.getTime().toLocaleString());
		System.out.println(c1.getTime().getYear());
	}
	
	/**
	 * 获取传入参数(dateStr)n个月之后或者之前的日期字符串
	 * @param dateStr  如：2017-11-11
	 * @param style   如：DateUtils.yyyy_MM_dd
	 * @param n（正,负,0都可以）     如：3
	 * @return   2018-2-11
	 */
    @SuppressWarnings("static-access")
	public static  String   getNMonthOfTheDay(String dateStr, String style,int n){   
        Calendar   c = Calendar.getInstance();
        if (dateStr==null||dateStr.equals(""))
        	c.setTime(new Date());
        else
        	c.setTime(DateUtils.parseToDate(dateStr, style));
        c.add(c.MONTH,n);   
        return   ""+c.get(c.YEAR)+"-"+(c.get(c.MONTH)+1)+"-"+c.get(c.DATE);   
    }     
    
    /**
     * 获取距离当前时间n个月之前或之后的日期字符串
     * @param n（正,负,0都可以）     如：3
     * @return   2018-2-26(当前时间是2017-11-26)
     */
    @SuppressWarnings("static-access")
	public static  String   getNMonthOfTheSameDay(int n){   
        Calendar   c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(c.MONTH,n);   
        return   ""+c.get(c.YEAR)+"-"+(c.get(c.MONTH)+1)+"-"+c.get(c.DATE);   
  
    } 
    
    
    
    
    
    
    
    
    
    
    
    
    
	/**
	 * 获取当前日期是当前年的第几天
	 * @param offset  往前进或往后退几天
	 * @return
	 */
	public static int getDayOfYear(int offset) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, offset);
		return cal.get(Calendar.DAY_OF_YEAR);
	}
	
	/**
	 * 获取传入的Date是当前年的第几天
	 * @param date 
	 * @return
	 */
	public static int getDayOfYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_YEAR);
	}
	
	/**
	 * 获取传入的Date往前进或往后退几天算得该时间是在当前年的第几天
	 * @param date
	 * @param offset 往前进或往后退几天
	 * @return
	 */
	public static int getDayOfYear(Date date, int offset) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, offset);
		return cal.get(Calendar.DAY_OF_YEAR);
	}
	
	
	/**
	 * 获取传入的date字符串是在当前年的第几天
	 * @param format 格式化date的形式，如yyyy-MM-dd
	 * @param date   传入的时间字符串，如2017-11-23
	 * @return
	 * @throws ParseException
	 */
	public static int getDayOfYear(String format, String date)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(date));
		return cal.get(Calendar.DAY_OF_YEAR);
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 如果一个日期是空，则抛出RuntimeException(msg)异常
	 * @param date
	 * @param msg
	 */
	public static void checkIsNull(Date date,String msg) {
		
		if(date == null) {
			
			throw new RuntimeException(msg);
		}
		
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
	
	/** 获取传入时间的最早时间，如：date为2014-05-20 11:11:11，返回时间为：2014-05-20 00:00:00 */
	public static Date getDayStartTime(Date date){
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			SimpleDateFormat YYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat YYYYMMDDHHMMSS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String strTime = YYYYMMDD.format(cal.getTime()) + " 00:00:00";
			return YYYYMMDDHHMMSS.parse(strTime);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	
	/** 获取当前时间的最早时间，如：今天为2014-05-20 11:11:11，返回时间为：2014-05-20 00:00:00 */
	public static Date getCurrentDayStartTime(){
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
	 * 返回当前时间距离传递参数的日期，相隔多少秒,
	 * 比如当前时间为：2014-05-22 00:00:00 ,
	 * 传递的参数为：2014-05-23 00:00:00,
	 * 那就返回一天的秒数：60*60*24=86400秒
	 * @param d  未来的某个时间
	 * @return
	 */
	public static long currentTimeSeparatedSecond(Date d) {
		
		if(d == null) {
			throw new IllegalArgumentException("传递的日期不能为空");
		}
		
		return (d.getTime() - new Date().getTime())/1000;
	}
	
	/**
	 * 计算某个日期加(或者减)天数的日期，
	 * 如果想减，则传递负值，如：-7 [7天前的日期]，
	 * 如果想加，传递正值，如：7 [7天后的数据]
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date distanceDaysOfDate(Date date,int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, days);
		return c.getTime();
	}
	
	
	/**
	 * 把距离1970年的毫秒数，格式化成yyyy-MM-dd HH:mm:ss字符串表现形式
	 * @param time 距离1970年的毫秒数
	 * @return
	 */
	public static String formatYYYYMMDDHHMMSS(long time) { 
		
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(time));
	}
	
	/**
	 * java.util.Date转成java.sql.Timestamp
	 * @param d
	 * @return
	 */
	public static java.sql.Timestamp parseToTimestamp(Date d) {
		
		if(d == null) {
			throw new IllegalArgumentException("传递的日期不能为空");
		}
		
		return new java.sql.Timestamp(d.getTime());
	}
	
	
	
    
    
}
