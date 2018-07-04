package nosql.mongo.demo;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

public class aliyun {
	public static ServerAddress seed1 = new ServerAddress("dds-wz910f0f27cf3e641551-pub.mongodb.rds.aliyuncs.com",
			3717);
	public static ServerAddress seed2 = new ServerAddress("dds-wz910f0f27cf3e642656-pub.mongodb.rds.aliyuncs.com",
			3717);
	public static String username = "root";
	public static String password = "awatime";
	public static String ReplSetName = "mgset-4724587";
	public static String DEFAULT_DB = "admin";
	public static String DEMO_DB = "test";
	public static String DEMO_COLL = "testColl";

	public static MongoClient createMongoDBClient() {
		// 构建Seed列表
		List<ServerAddress> seedList = new ArrayList<ServerAddress>();
		seedList.add(seed1);
		seedList.add(seed2);
		// 构建鉴权信息
		List<MongoCredential> credentials = new ArrayList<MongoCredential>();
		credentials.add(MongoCredential.createScramSha1Credential(username, DEFAULT_DB, password.toCharArray()));
		// 构建操作选项，requiredReplicaSetName属性外的选项根据自己的实际需求配置，默认参数满足大多数场景
		MongoClientOptions options = MongoClientOptions.builder().requiredReplicaSetName(ReplSetName)
				.socketTimeout(2000).connectionsPerHost(1).build();
		return new MongoClient(seedList, credentials, options);
	}

	public static MongoClient createMongoDBClientWithURI() {
		// 另一种通过URI初始化
		// mongodb://[username:password@]host1[:port1][,host2[:port2],...[,hostN[:portN]]][/[database][?options]]
		MongoClientURI connectionString = new MongoClientURI("mongodb://" + username + ":" + password + "@" + seed1
				+ "," + seed2 + "/" + DEFAULT_DB + "?replicaSet=" + ReplSetName);
		return new MongoClient(connectionString);
	}

	public static void main(String args[]) {
		// MongoClient client = createMongoDBClient();
		// or
		MongoClient client = createMongoDBClientWithURI();
		try {
			// 取得Collecton句柄
			DB database = client.getDB("gdcp");
			// MongoDatabase database2 = client.getDatabase("gdcp");
			// MongoCollection<Document> collection2 =
			// database2.getCollection("RTINFO");
			// collection2.find().limit(10);
			DBCollection collection = database.getCollection("RTINFO");
			// 插入数据
			// BasicDBObject obj = new BasicDBObject();
			// obj.put("name", "longxn");
			// collection.insert(obj);
			// System.out.println("insert document: " + obj);
			// 读取数据
			DBCursor cursor = collection.find().limit(10);
			System.out.println(cursor);
			while (cursor.hasNext()) {
				System.out.println("find document: " + cursor.next());
			}
		} finally {
			// 关闭Client，释放资源
			client.close();
		}
		return;
	}
}