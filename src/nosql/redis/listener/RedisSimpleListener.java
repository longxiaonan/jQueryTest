package nosql.redis.listener;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import nosql.redis.RedisUtils;
import nosql.redis.client.RedisClient;

/**
 * 通过list进行简单的发布和订阅
 * @author longxn
 *
 */
public class RedisSimpleListener {

	public void start() {
		new Thread() {
			public void run() {
				while (true) {
					String base64str = RedisUtils.getClusterClient().brpop("listenerTest");
					System.out.println(base64str);
				}
			}
		}.start();
	}
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "classpath*:META-INF/spring/*.xml", "classpath*:config/mybatis/*.xml",
						"classpath*:spring/spring-base.xml", "classpath*:spring/spring-redis-*.xml",
						"classpath*:spring/spring-context-*.xml" });
		
		RedisSimpleListener rs = new RedisSimpleListener();
		rs.start();
		for (int i = 1; i <= 5; i++){
			RedisClient clusterClient = RedisUtils.getClusterClient();
			clusterClient.lpush("listenerTest", "vin"+i, "fff"+i);
		}
	}
}
