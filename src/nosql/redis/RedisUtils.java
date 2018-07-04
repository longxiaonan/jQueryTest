package nosql.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nosql.redis.client.RedisClient;

/**
 * redis工具类, 单例从spring中懒加载redis的客户端, 通过getInstance方法提供调用者
 * 
 * @author longxn
 *
 */
public class RedisUtils {

	static Logger logger = LoggerFactory.getLogger(RedisUtils.class);

	private static RedisClient shardClient = null;

	private static RedisClient clusterClient = null;

	private volatile static RedisUtils instance_ = null;

	
	public void setShardClient(RedisClient shardClient) {
		RedisUtils.shardClient = shardClient;
	}

	public void setClusterClient(RedisClient clusterClient) {
		RedisUtils.clusterClient = clusterClient;
	}

	private RedisUtils(){} 
	/**
	 * 单例获取RedisUtils对象, 该对象可用获取redisClient, redisClient用于操作redis
	 * <p>
	 * redisClient分两种:
	 * </p>
	 * <p>
	 * 1. redisClientShard: 分片模式的redis客户端
	 * </p>
	 * <p>
	 * 2. redisClientCluster: 集群模式的redis客户端
	 * </p>
	 * 
	 * @return redis客户端
	 */
	public static RedisUtils getInstance() {
		if (instance_ == null) {
			synchronized (RedisUtils.class) {
				if (instance_ == null) {
					instance_ = new RedisUtils();
				}
			}
		}
		return instance_;
	}

	/**
	 * 单例获取Redis 的shard Client对象, redisClient用于操作redis
	 * <p>
	 * redisClient分两种:
	 * </p>
	 * <p>
	 * 1. redisClientShard: 分片模式的redis客户端
	 * </p>
	 * <p>
	 * 2. redisClientCluster: 集群模式的redis客户端
	 * </p>
	 * 
	 * @return redis客户端
	 */
	public static RedisClient getShardClient() {
		return shardClient;
	}

	/**
	 * 单例获取Redis 的cluster Client对象, redisClient用于操作redis
	 * <p>
	 * redisClient分两种:
	 * </p>
	 * <p>
	 * 1. redisClientShard: 分片模式的redis客户端
	 * </p>
	 * <p>
	 * 2. redisClientCluster: 集群模式的redis客户端
	 * </p>
	 * 
	 * @return redis客户端
	 */
	public static RedisClient getClusterClient() {
		return clusterClient;
	}

}
