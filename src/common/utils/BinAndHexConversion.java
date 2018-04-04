package common.utils;

import java.util.Arrays;
import java.util.List;

import com.google.common.base.Splitter;

public class BinAndHexConversion {
	private static String hexStr = "0123456789ABCDEF";
	private static String[] binaryArray = { "0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000",
			"1001", "1010", "1011", "1100", "1101", "1110", "1111" };

	/**
	 * 
	 * @param str
	 * @return 二进制数组转换为二进制字符串 2-2
	 */
	public static String byteArr2BinStr(byte[] bArray) {

		String outStr = "";
		int pos = 0;
		for (byte b : bArray) {
			// 高四位
			pos = (b & 0xF0) >> 4;
			outStr += binaryArray[pos];
			// 低四位
			pos = b & 0x0F;
			outStr += binaryArray[pos];
		}
		return outStr;
	}

	/**
	 * 
	 * @param bytes
	 * @return 将二进制数组转换为十六进制字符串 2-16
	 */
	public static String byteArr2HexStr(byte[] bArray) {

		String result = "";
		String hex = "";
		for (int i = 0; i < bArray.length; i++) {
			// 字节高4位
			hex = String.valueOf(hexStr.charAt((bArray[i] & 0xF0) >> 4));
			// 字节低4位
			hex += String.valueOf(hexStr.charAt(bArray[i] & 0x0F));
			result += hex; // +" "
		}
		return result;
	}

	/**
	 * @param hexString
	 * @return 将十六进制转换为二进制字节数组 16-2
	 */
	public static byte[] hexStr2ByteArr(String hexString) {
		// hexString的长度对2取整，作为bytes的长度
		int len = hexString.length() / 2;
		byte[] bytes = new byte[len];
		byte high = 0;// 字节高四位
		byte low = 0;// 字节低四位
		for (int i = 0; i < len; i++) {
			// 右移四位得到高位
			high = (byte) ((hexStr.indexOf(hexString.charAt(2 * i))) << 4);
			low = (byte) hexStr.indexOf(hexString.charAt(2 * i + 1));
			bytes[i] = (byte) (high | low);// 高地位做或运算
		}
		return bytes;
	}

	/**
	 *  如:2323011A  转换后:00100011001000110000000100011010
	 * @param hexString
	 * @return 将十六进制转换为二进制字符串 16-2
	 */
	public static String hexStr2BinStr(String hexString) {
		return byteArr2BinStr(hexStr2ByteArr(hexString));
	}

	/**
	 * 二进制字符串转换成16进制字符串
	 * 
	 * @param binStr
	 *            如二进制字符串:00100011001000110000000100011010
	 * @return 转换后的16进制为:2323011A
	 */
	public static String binStr2HexStr(String binStr) {
		StringBuffer sb = new StringBuffer();
		Splitter binArr = Splitter.fixedLength(4);
		List<String> splitToList = binArr.splitToList(binStr);
		splitToList.forEach(n -> {
			System.out.println(n);
			for (int i = 0; i < binaryArray.length; i++) {
				if (n.equals(binaryArray[i])) {
					sb.append(hexStr.charAt(i));
				}
			}
		});
		return sb.toString();
	}

	public static void main(String[] args) {
		byte[] hexStr2BinArr = hexStr2ByteArr("2323011A");
		String bin2HexStr = byteArr2HexStr(hexStr2BinArr);
		String hexStr2BinStr = hexStr2BinStr("2323011A");

		for (byte b : hexStr2BinArr) {
			System.out.println(b);
		}

		System.out.println(bin2HexStr); // 2323011A
		System.out.println(hexStr2BinStr);// 00100011001000110000000100011010
		String bytes2BinStr = byteArr2BinStr(hexStr2BinArr);
		System.out.println(bytes2BinStr); // 00100011001000110000000100011010
		String str = binStr2HexStr("ss");
		System.out.println(str);// 2323011A
	}
}
