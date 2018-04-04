package nosql.redis;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import gdcp.utils.SpringContextUtil;
import nosql.redis.client.RedisClient;

public class RedisEventTest {
	static Logger logger = LoggerFactory.getLogger(RedisTest.class);
		public static void main(String[] args) {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
					new String[] { "classpath*:spring/spring-base.xml", "classpath*:spring/spring-redis-*.xml" });
//			Object bean = context.getBean("redisClientTemplate");
			RedisClient redisClient = (RedisClient) SpringContextUtil.getBean(RedisConst.REDIS_ClIENT);
			redisClient.set("aa", "11");
			System.out.println(redisClient.get("aa"));
			
//			redisClient.
	}
}
