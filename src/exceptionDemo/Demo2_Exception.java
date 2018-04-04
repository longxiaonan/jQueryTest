package exceptionDemo;
public class Demo2_Exception {
	/**		
		try:用来检测异常的
		catch:用来捕获异常的
		finally:释放资源
		
		世界上最真情的相依就是你在try我在catch,无论你发神马脾气,我都静静接受,默默处理
		当通过trycatch将问题处理了,程序会继续执行
	 */
	public static void main(String[] args) {
		Demo2 d = new Demo2();
		try{
			int x = d.div(10, 0);
			System.out.println(x);
		}catch(ArithmeticException a) {		//ArithmeticException a = new ArithmeticException();
			System.out.println("出错了,除数为零了");
			//如果抛出runtime异常, 那么catch后面的代码和try catch后面的代码都不会被执行
//			throw new RuntimeException("!!!!!!!");
			a.printStackTrace();
			System.out.println("000000000000");
		}
		System.out.println("1111111111111111");
	}
}

class Demo2 {
	/*
	 * 除法运算
	 */
	public int div(int a,int b) {		//a = 10,b = 0
		return a / b;					// 10 / 0  被除数是10,除数是0当除数是0的时候违背了算数运算法则,抛出异常
										//new ArithmeticException("/ by zero");
	}
}