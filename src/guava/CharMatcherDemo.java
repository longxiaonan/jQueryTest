package guava;

import com.google.common.base.CharMatcher;

public class CharMatcherDemo {
	public static void main(String[] args) {
		String lettersAndNumbers = "foo989yxbar234";
		String retained = CharMatcher.JAVA_DIGIT.retainFrom(lettersAndNumbers); // 保留数字
		String s1 = CharMatcher.digit().retainFrom("abc 123 efg");
		System.out.println(retained);//989234
		String removed = CharMatcher.JAVA_DIGIT.removeFrom(lettersAndNumbers); // 移除数字
		System.out.println(removed);//fooyxbar
		// 通过Or组合多个匹配规则，再进行操作
		CharMatcher cm = CharMatcher.JAVA_LETTER.or(CharMatcher.WHITESPACE);
		String retained1 = cm.retainFrom("foo9 89y xbar 234");// 保留数字和空格
		System.out.println(retained1);//foo y xbar 
		
		boolean result = CharMatcher.inRange('a', 'z').or(CharMatcher.inRange('A', 'Z')).matches('K'); //true
		
		String s2 = CharMatcher.digit().removeFrom("abc 123 efg");
		
		String spaced = CharMatcher.WHITESPACE.trimAndCollapseFrom("abad2 34ggew45  ", '$');
		System.out.println(spaced);//abad2$34ggew45
		
		String keepAlex = CharMatcher.anyOf("alex").retainFrom("13alexgw4634wfw46");
		System.out.println(keepAlex);
	}
}
