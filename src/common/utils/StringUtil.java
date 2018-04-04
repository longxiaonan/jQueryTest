package common.utils;

import java.util.Date;
import java.util.List;

/**
 * 字符串的工具类
 * 
 * @author lijian@cstonline.cn
 * 
 * @date 2014年5月27日 下午3:23:38
 */
public class StringUtil {

	private StringUtil() {
	}

	/**
	 * 如果一个字符串是空或者空字符串，返回true，否则返回false
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isNullOrBlank(String s) {

		return s == null ? true : ("".equals(s.trim()) ? true : false);

	}

	/**
	 * 如果一个字符串是空或者空字符串，则抛出RuntimeException(msg)异常
	 * 
	 * @param s
	 */
	public static void checkIsNullOrBlank(String s, String msg) {

		if (isNullOrBlank(s)) {
			throw new RuntimeException(msg);
		}
	}
	
	/**
	 * 如果一个日期是空，则抛出RuntimeException(msg)异常
	 * 
	 * @param date
	 */
	public static void checkDateIsNull(Date date, String msg) {

		if (date == null) {
			throw new RuntimeException(msg);
		}
	}
	

	/**
	 * 把List数组中的值，按照part进行拼接，如：data为:[1,2,3],part:,，则最后返回为：1,2,3
	 * 
	 * @param data
	 * @param part
	 * @return
	 */
	public static String splice(List<String> data, String part) {

		if (data == null || data.isEmpty() || isNullOrBlank(part)) {
			return null;
		}

		StringBuilder sb = new StringBuilder();

		sb.append(data.get(0));

		for (int i = 1, j = data.size(); i < j; i++) {
			sb.append(part);
			sb.append(data.get(i));
		}

		return sb.toString();
	}
	
	/**
	 * 把数组中的值，按照part进行拼接，如：data为:[1,2,3],part:,，则最后返回为：1,2,3
	 * @param data
	 * @param part
	 * @return
	 */
	public static String splice(String[] data,String part) {
		
		if(data == null || data.length <= 0 || isNullOrBlank(part)) {
			return null;
		}
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(data[0]);
		
		for(int i = 1,j=data.length;i<j;i++) {
			sb.append(part);
			sb.append(data[i]);
		}
		
		return sb.toString();
	}

	
	public static String bytesToString(byte[] data) {
		
		if(data == null || data.length <= 0) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < data.length; i++) {
			sb.append(" " + String.format("%1$02x", data[i]));
		}
		
		return sb.toString().toUpperCase().trim();
	}

	/**
	 * just for test
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// checkIsNullOrBlank("  dd", "aa不能为空");

		/*List<String> data = new ArrayList<String>();
		data.add("A");
		data.add("B");
		data.add("哈哈");
		System.out.println(splice(data, "#"));
		
		String[] data2 = new String[]{"a","b","c"};
		System.out.println(splice(data2, ","));*/
		
		byte[] arr = new byte[]{31,37,32,0x2e,31,36,0x2e,31,0x2e,31,3,34};
		
		System.out.println(bytesToString(arr));
		
		System.out.println(String.format("%1$02x%2$02x", 0x01,0x02).toUpperCase());
		
	}
}
