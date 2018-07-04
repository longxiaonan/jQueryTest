package nosql.redis.listener;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import nosql.redis.RedisUtils;
import nosql.redis.client.RedisClient;

public class tes {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "classpath*:META-INF/spring/*.xml", "classpath*:config/mybatis/*.xml",
						"classpath*:spring/spring-base.xml", "classpath*:spring/spring-redis-*.xml",
						"classpath*:spring/spring-context-*.xml" });
		
		for (int i = 10; i <= 15; i++){
			RedisClient clusterClient = RedisUtils.getClusterClient();
			clusterClient.lpush("listenerTest", "vin"+i, "fff"+i);
		}
	}
}
