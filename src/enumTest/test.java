package enumTest;

import enumTest.EventStateConstKey.VSTSTE;

/**
    * @ClassName: test
	* @Description:
    * @author 龙小南
    * @email longxiaonan@163.com
    * @date 2017年11月14日 上午12:53:48
    * @version 1.0
    */
public class test {
	public static void main(String[] args) {
		System.out.println(EventStateConstKey.VSTSTE.STARTUP.getValue());//10001
		System.out.println(EventStateConstKey.VSTSTE.STALL.getValue());//10002
		System.out.println(EventStateConstKey.VSTSTE.INVALID.getValue());//10255
		
		System.out.println(EventStateConstKey.VSTSTE.INVALID.toString());//INVALID
		System.out.println(EventStateConstKey.VSTSTE.STARTUP.toString());//STARTUP
		
		VSTSTE[] values = EventStateConstKey.VSTSTE.values();
		for (VSTSTE vstste : values) {
			System.out.println(vstste.getValue());
			System.out.println(vstste.toString());
		}
	}
}
