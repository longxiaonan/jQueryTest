package nosql.redis;
   /**
    * @ClassName: RedisConst
	* @Description:
    * @author 龙小南
    * @email longxiaonan@163.com
    * @date 2017年11月2日 上午11:53:20
    * @version 1.0
    */
public class RedisConst {
	/** redis client的bean名字常量, 按使用的实现类填写 */
	public static final String REDIS_ClIENT = "redisClientCluster";
	/** redisClientCluster的bean名字常量 */
	public static final String REDIS_CLIENT_CLUSTER =  "redisClientCluster";
	/** redisClientShard的bean名字常量 */
	public static final String REDIS_CLIENT_SHARD = "redisClientShard";
}
