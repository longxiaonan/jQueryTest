package socket;

import java.io.File;

/**
    * @ClassName: FileTest
	* @Description:
    * @author 龙小南
    * @email longxiaonan@163.com
    * @date 2017年12月5日 下午11:48:46
    * @version 1.0
    */
public class FileTest {
	public static void main(String[] args) {
		File dir = new File("update");
		File file = new File(dir,"abc");	
		System.out.println(dir);
		System.out.println(file);
	}
}
