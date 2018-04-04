import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import common.utils.BinAndHexConversion;
import utils.SenderUtil;

public class test {
	private static volatile Map<String, AtomicInteger> supportNum = new ConcurrentHashMap<>();
	public static void main(String[] args) throws UnsupportedEncodingException {
		String s = "23 23 80 01 4C 59 31 36 43 31 32 33 31 48 30 30 30 31 31 31 31 01 00 19 12 04 03 09 2C 14 01 E9 4C 59" 
+"31 36 43 31 32 33 31 48 30 30 30 31 31 31 31 55";
		byte[] hexStringToByteArray = SenderUtil.hexStringToByteArray(s);
		String string = new String(hexStringToByteArray,"UTF-8");
		System.out.println(string);
	}
}
