package nosql.mongo.demo;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

/**
 * @ClassName: Test
 * @Description:
 * @author 龙小南
 * @email longxiaonan@163.com
 * @date 2017年11月12日 下午4:45:33
 * @version 1.0
 */
public class MongoTest {
	public static void main(String[] args) {
		 connectMongoByIpAndPort();
		// connectMongoByPassword();
		//3.0之后的jar调用方式
//		connectMongoByPasswordVersion3();
	}

	private static void connectMongoByPasswordVersion3() {
		try {
			// 连接到MongoDB服务 如果是远程连接可以替换“localhost”为服务器所在IP地址
			// ServerAddress()两个参数分别为 服务器地址 和 端口
			ServerAddress serverAddress = new ServerAddress("localhost", 27017);
			List<ServerAddress> addrs = new ArrayList<ServerAddress>();
			addrs.add(serverAddress);

			// MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码
			MongoCredential credential = MongoCredential.createScramSha1Credential("username", "databaseName",
					"password".toCharArray());
			List<MongoCredential> credentials = new ArrayList<MongoCredential>();
			credentials.add(credential);

			// 通过连接认证获取MongoDB连接
			MongoClient mongoClient = new MongoClient(addrs, credentials);

			// 连接到数据库
			MongoDatabase mongoDatabase = mongoClient.getDatabase("databaseName");
			System.out.println("Connect to database successfully");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	private static void connectMongoByIpAndPort() {
		try {
			// 连接到 mongodb 服务
			MongoClient mongoClient = new MongoClient("dds-wz910f0f27cf3e641551-pub.mongodb.rds.aliyuncs.com", 3717);

			// 连接到数据库
			DB mongoDatabase = mongoClient.getDB("Admin");
//			boolean authenticate = mongoDatabase.authenticate("root", "awatime".toCharArray());

			// System.out.println("认证情况:>>>"+authenticate);//认证情况:>>>false

			System.out.println(mongoDatabase);
			DBCollection rtinfoCol = mongoDatabase.getCollection("RTINFO");
			System.out.println(rtinfoCol);
			DBCursor cursor = rtinfoCol.find().limit(1);
			System.out.println(cursor);

			List<DBObject> data = new ArrayList<DBObject>(cursor.count());
			while (cursor.hasNext()) {
				DBObject obj = cursor.next();
				data.add(obj);
			}
			cursor.close();
			System.out.println(data);
			System.out.println("Connect to database successfully");

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	@SuppressWarnings("deprecation")
	private static void connectMongoByPassword() {
		try {
			// 连接到MongoDB服务 如果是远程连接可以替换“localhost”为服务器所在IP地址
			// ServerAddress()两个参数分别为 服务器地址 和 端口
			ServerAddress serverAddress = new ServerAddress("dds-wz910f0f27cf3e641551-pub.mongodb.rds.aliyuncs.com",
					3717);
			List<ServerAddress> addrs = new ArrayList<ServerAddress>();
			addrs.add(serverAddress);

			// MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码
			MongoCredential credential = MongoCredential.createMongoCRCredential("root", "gdcp",
					"awatime".toCharArray());
			List<MongoCredential> credentials = new ArrayList<MongoCredential>();
			credentials.add(credential);

			// 通过连接认证获取MongoDB连接
			MongoClient mongoClient = new MongoClient(addrs, credentials);

			// 连接到数据库
			DB mongoDatabase = mongoClient.getDB("gdcp");
			DBCollection rtinfo = mongoDatabase.getCollection("RTINFO");
			System.out.println(rtinfo);
			System.out.println(rtinfo.find().limit(1).toString());
			System.out.println("Connect to database successfully>>>" + mongoDatabase);

			// 插入数据
			// BasicDBObject document = new BasicDBObject();
			// document.put("name", "longxn");
			// rtinfo.insert(document);

			DBCursor cursor = rtinfo.find();
			cursor.limit(1);
			List<DBObject> data = new ArrayList<DBObject>(cursor.count());
			while (cursor.hasNext()) {
				DBObject obj = cursor.next();
				data.add(obj);
			}
			cursor.close();
			System.out.println(data);
		} catch (

		Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
}
