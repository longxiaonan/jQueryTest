package nosql.redis;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gdcp.common.redis.client.RedisClientCluster;

import gdcp.utils.SpringContextUtil;
import nosql.redis.client.RedisClient;
import nosql.redis.pipeline.JedisClusterPipeline;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

/**
 * @ClassName: RedisTest
 * @Description:
 * @author 龙小南
 * @email longxiaonan@163.com
 * @date 2017年10月31日 上午11:46:01
 * @version 1.0
 */
public class RedisTest {
	static Logger logger = LoggerFactory.getLogger(RedisTest.class);

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "classpath*:spring/spring-base.xml", "classpath*:spring/spring-redis-*.xml" });
		// Object bean = context.getBean("redisClientTemplate");
		// testShared();
//		 testCluster();
		// testPing();
		testPipeline();
	}

	private static void testPing() {
		Jedis j = new Jedis("192.168.3.201", 7003);
		System.out.println(j.ping());
	}

	private static void testCluster() {
		RedisClient redisClient = (RedisClient) SpringContextUtil.getBean(RedisConst.REDIS_ClIENT);
		redisClient.set("aaa", "a222222222222222");
		redisClient.set("bbb", "b22222222222");
		redisClient.set("ccc", "c22222222222");
		redisClient.set("ddd", "d222222222222");
		redisClient.set("eee", "e2222222222222");
		redisClient.set("fff", "f2222222222222");
		redisClient.set("ggg", "g2222222222222");
		System.out.println(redisClient.get("aaa"));
		System.out.println(redisClient.get("bbb"));
		System.out.println(redisClient.get("ccc"));
		System.out.println(redisClient.get("ddd"));
		System.out.println(redisClient.get("eee"));
		System.out.println(redisClient.get("fff"));
		System.out.println(redisClient.get("ggg"));
	}

	private static void testShared() {
		RedisClient redisClient = (RedisClient) SpringContextUtil.getBean("redisClientShard");
		redisClient.set("aaa", "a222222222222222");
		redisClient.set("bbb", "b22222222222");
		redisClient.set("ccc", "c22222222222");
		redisClient.set("ddd", "d222222222222");
		redisClient.set("eee", "e2222222222222");
		System.out.println(redisClient.get("aaa"));
		System.out.println(redisClient.get("bbb"));
		System.out.println(redisClient.get("ccc"));
		System.out.println(redisClient.get("ddd"));
		System.out.println(redisClient.get("eee"));
	}

	private static void testPipeline() {
//		Set<HostAndPort> nodes = new HashSet<HostAndPort>();
//		nodes.add(new HostAndPort("127.0.0.1", 9379));
//		nodes.add(new HostAndPort("127.0.0.1", 9380));
//
//		JedisCluster jc = new JedisCluster(nodes);
		
		long s = System.currentTimeMillis();

		JedisClusterPipeline jcp = JedisClusterPipeline.pipelined();
		jcp.refreshCluster();
		List<Object> batchResult = null;
		try {
			// batch write
			for (int i = 0; i < 10; i++) {
				jcp.set("k" + i, "v1" + i);
			}
			jcp.sync();

			// batch read
			for (int i = 0; i < 10; i++) {
				jcp.get("k" + i);
			}
			// batch del
			for (int i = 0; i < 10; i++) {
				jcp.del("k" + i);
			}
			batchResult = jcp.syncAndReturnAll();
		} finally {
			jcp.close();
//			try {
//				jc.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}

		// output time
		long t = System.currentTimeMillis() - s;
		System.out.println(t);

		System.out.println(batchResult.size());

	}
}
