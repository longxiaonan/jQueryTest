package nosql.mongo;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import com.gdcp.common.logger.SimpleLogger;
import com.gdcp.common.util.PropertiesUtil;
import com.gdcp.common.util.StringUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

/**
 * 操作mongo 数据库
 */
public class MongoService {

	private static SimpleLogger logger = new SimpleLogger(MongoService.class);

	/** 主机IP */
	private static final String HOST;

	/** 端口 */
	private static final int PORT;

	/** 保存的dbName */
	private String dbName;

	/** collectionName */
	private String collectionName;
	
	/**单例的MongoClient，因为在MongoClient中，getDB()是直接拿线程池中连接（在2.12.1版本中，里面配置的连接数好像是100个），而CRUD之后，会自动把连接释放到连接池中*/
	private static MongoClient mongoClient;

	static {
		Map<String, String> data = PropertiesUtil
				.getProperties("conf/mongo.properties");
		if (data == null || data.isEmpty()) {
			throw new RuntimeException(
					"在相对路径conf/mongo.properties，没有配置mongo的链接信息");
		}

		if (StringUtil.isNullOrBlank(data.get("HOST"))) {
			throw new RuntimeException("配置mongo的HOST不能为空");
		}

		if (data.get("PORT") == null) {
			throw new RuntimeException("配置mongo的PORT端口不能为空");
		}

		HOST = data.get("HOST");
		PORT = Integer.parseInt(data.get("PORT"));
		
		logger.logInfo("mongoDB系统配置参数:" + data);
		
	}

	/**
	 * 通过dbName和文档名字创建对象
	 * 
	 * @param dbName
	 * @param collectionName
	 */
	private MongoService(String dbName, String collectionName) {
		this.dbName = dbName;
		this.collectionName = collectionName;
	}
	
	
	/**
	 * 
	 * 初始化MongoClient
	 */
	private static void initMongoClient() {
		if(mongoClient == null) {
			synchronized (MongoService.class) {
				if(mongoClient == null) {
					try {
						mongoClient = new MongoClient(HOST, PORT);
						logger.logInfo("初始化MongoClient成功");
					} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException("初始化MongoClient失败");
					}
				}
				
			}
		}
	}
	

	/**
	 * 返回 通过MongoDBName枚举类型和文档名字创建的对象
	 * 
	 * @param dbName
	 * @param collectionName
	 * @return
	 */
	public static MongoService getServiceByDbNameAndColName(MongoDBName dbName,
			String collectionName) {

		return getServiceByDbNameAndColName(dbName.toString(), collectionName);

	}
	
	
	/**
	 * 返回，通过dbName为MongoDBName.GDCP[其值为gdcp]和文档名字创建的对象
	 * 
	 * @param collectionName
	 * @return
	 */
	public static MongoService getServiceByGDCPDBNameAndColName(
			String collectionName) {

		return getServiceByDbNameAndColName(MongoDBName.GDCP.toString(), collectionName);

	}
	
	/**
	 * <li>返回 通过dbName和文档名字创建的对象</li><br/>
	 * tip:最好是通过方法getServiceByDbNameAndColName（MongoDBName dbName,String collectionName），因为
	 * 这个可以保证你传入的DBName不会写错
	 * 
	 * @param dbName
	 * @param collectionName
	 * @return
	 */
	public static MongoService getServiceByDbNameAndColName(String dbName,String collectionName) {
		
		initMongoClient();

		StringUtil.checkIsNullOrBlank(dbName,"传递的dbName文档名字不能为空");
		StringUtil.checkIsNullOrBlank(collectionName,"传递的CollectionName文档名字不能为空");

		return new MongoService(dbName, collectionName);

	}
	
	/**
	 * 通过文档的ID查询，如果有的话，返回一条，无的话，返回null
	 * @param id
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public <T> T queryById(String id, final Class<T> clazz)throws Exception {
		
		if(StringUtil.isNullOrBlank(id)) {
			logger.logInfo("查询传入需要更新的id不能为空,直接返回");
			return null;
		}
		
		DBObject dbObj = new BasicDBObject("_id", new ObjectId(id));
		
		List<T> dataList = queryCanSortOrNotTemplate(dbObj,null,clazz);
		
		return (dataList == null || dataList.isEmpty()) ? null : dataList.get(0);
	}
	
	/**
	 * 查询，查询出来的每条记录[json]，转成传递的Class T [如果orderBy为null，则不排序]
	 * @param queryObj 查询的obj
	 * @param orderBy  排序的obj
	 * @param clazz    要转成的class
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> query(DBObject queryObj, DBObject orderBy, Class<T> clazz) throws Exception { 
		
		return queryCanSortOrNotTemplate( queryObj, orderBy,clazz);
	}
	
	/**
	 * 查询，查询出来的每条记录[json]，转成传递的Class T 
	 * @param queryObj 查询的obj
	 * @param clazz    要转成的class
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> query(DBObject queryObj,  Class<T> clazz) throws Exception { 
		
		return queryCanSortOrNotTemplate( queryObj, null,clazz);
	}
	

	/**
	 * 查询，查询出来的每条记录[json]，转成传递的Class T [如果orderBy为null，则不排序]
	 * 
	 * @param queryObj 查询obj
	 * @param orderBy  排序obj
	 * @param clazz
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	private <T> List<T> queryCanSortOrNotTemplate(DBObject queryObj,final DBObject orderBy, final Class<T> clazz) throws Exception {

		if (queryObj == null) {
			logger.logInfo("查询传入需要更新的DBObject不能为空,直接返回");
			return null;
		}

		Object obj = this.executeTemplate(new MongoExecuteCallBack() {

			@Override
			public Object execute(MongoClient mg, DB db,
					DBCollection collection, DBObject... dbObj)
					throws Exception {

				if (dbObj == null || dbObj.length != 1) {
					logger.logInfo("查询操作，传入的DBObject不合法，只能包含一个元素,直接返回");
					return 0;
				}
				
				DBCursor cursor = null;
				
				//增加排序
				if(orderBy != null) {
					cursor =  collection.find(dbObj[0]).sort(orderBy);
				} else {
					cursor =  collection.find(dbObj[0]);
				}

				List<T> data = new ArrayList<T>(cursor.count());

				while (cursor.hasNext()) {

					DBObject obj = cursor.next();
					T t = dbObjectToBean(obj, clazz);
					data.add(t);
				}
				
				cursor.close();

				logger.logInfo("DBName[{}],CollectionName[{}],操作[{}],查询出来的数量[{}]" ,dbName,collectionName,"查询query", data.size());

				return data;

			}
		}, queryObj);

		return (List<T>) obj;
	}
	
	/**
	 * 查询：根据查询对象查询结果的DBObject集合
	 * @param queryObj
	 * @return
	 * @throws Exception
	 */
	public List<DBObject> query(DBObject queryObj) throws Exception {
		
		return queryCanSortOrNotTemplate(queryObj,null,false,8888);
	}
	
	/**
	 * 查询：根据查询对象和限制取多少条的limit返回查询结果的DBObject集合
	 * @param queryObj		查询对象
	 * @param limitAmount   取多少条的limit的值
	 * @return
	 * @throws Exception
	 */
	public List<DBObject> query(DBObject queryObj,int limitAmount) throws Exception {
		
		return queryCanSortOrNotTemplate(queryObj,null,true,limitAmount);
	}
	
	/**
	 * 查询：根据查询对象和排序对象[如果排序对象为null的话，便不排序]返回查询结果的DBObject集合
	 * @param queryObj
	 * @return
	 * @throws Exception
	 */
	public List<DBObject> query(DBObject queryObj,DBObject orderBy) throws Exception {
		
		return queryCanSortOrNotTemplate(queryObj,orderBy,false,8888);
	}
	
	/**
	 * 查询：根据查询对象和排序对象[如果排序对象为null的话，便不排序]、限制取多少条的limit 返回查询结果的DBObject集合
	 * @param queryObj      查询对象
	 * @param orderBy		排序对象
	 * @param limitAmount   取多少条的limit的值
	 * @return
	 * @throws Exception
	 */
	public List<DBObject> query(DBObject queryObj,DBObject orderBy,int limitAmount) throws Exception {
		
		return queryCanSortOrNotTemplate(queryObj,orderBy,true,limitAmount);
	}
	
	
	/**
	 * 查询：根据查询对象和排序对象[如果排序对象为null的话，便不排序],是否启用limit，返回查询结果的DBObject集合
	 * @param queryObj      查询的obj
	 * @param orderBy		排序的obj
	 * @param isUseLimit    是否启用limit，true-启用，false-不启用
	 * @param limitAmount   limit的数量，当然如果isUseLimit为false，这个值是没有用的
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private List<DBObject> queryCanSortOrNotTemplate(DBObject queryObj,final DBObject orderBy,final boolean isUseLimit,final int limitAmount) throws Exception {

		if (queryObj == null) {
			logger.logInfo("查询传入需要更新的DBObject不能为空,直接返回");
			return null;
		}

		Object obj = this.executeTemplate(new MongoExecuteCallBack() {

			@Override
			public Object execute(MongoClient mg, DB db,
					DBCollection collection, DBObject... dbObj)
					throws Exception {

				if (dbObj == null || dbObj.length != 1) {
					logger.logInfo("查询操作，传入的DBObject不合法，只能包含一个元素,直接返回");
					return 0;
				}
				
				DBCursor cursor = null;
				
				//是否排序
				if(orderBy != null) {
					cursor = collection.find(dbObj[0]).sort(orderBy);
				} else {
					cursor = collection.find(dbObj[0]);
				}
				
				//是否limit
				if(isUseLimit) {
					cursor.limit(limitAmount);
				}
				
				List<DBObject> data = new ArrayList<DBObject>(cursor.count());

				while (cursor.hasNext()) {

					DBObject obj = cursor.next();
					
					data.add(obj);
				}

				cursor.close();
				
				logger.logInfo("DBName[{}],CollectionName[{}],操作[{}],查询出来的数量[{}]" ,dbName,collectionName,"查询query" +(orderBy != null ? " sort" : "") +(isUseLimit ? " limit":""), data.size());

				return data;

			}
		}, queryObj);

		return (List<DBObject>) obj;
	}
	
	
	

	/**
	 * DBObject转成具体的对象
	 * 
	 * @param dbObject
	 * @param clazz
	 * @return
	 */
	private <T> T dbObjectToBean(DBObject dbObject, Class<T> clazz) {
		T info = null;
		if (dbObject != null) {
			Object job = com.alibaba.fastjson.JSON.toJSON(dbObject);
			info = com.alibaba.fastjson.JSON.parseObject(job.toString(), clazz);
		}
		return info;
	}

	/**
	 * 更新：不存在创建，匹配多条，更新多条
	 * 
	 * @param oldObj
	 *            需要更新的DBObject
	 * @param newObj
	 *            更新后的DBObject
	 * @return
	 * @throws Exception 
	 */
	public int updateUpsertTrueMultiTrue(DBObject oldObj, DBObject newObj) throws Exception {

		return update(oldObj, newObj, true, true);
	}

	/**
	 * 更新：返回值为更新的数量
	 * 
	 * @param oldObj
	 *            需要更新的DBObject
	 * @param newObj
	 *            更新后的DBObject
	 * @param upsert
	 *            如果不存在是否创建，true为创建，false为不创建
	 * @param multi
	 *            如果存在多条，是否更新多条，true为更新多条，false为只更新一条
	 * @return
	 * @throws Exception 
	 */
	public int update(DBObject oldObj, DBObject newObj, final boolean upsert,
			final boolean multi) throws Exception {

		if (oldObj == null || newObj == null) {
			logger.logInfo("传入需要更新的DBObject和更新后的DBOBject不能为空,直接返回");
			return 0;
		}

		Object obj = this.executeTemplate(new MongoExecuteCallBack() {

			@Override
			public Object execute(MongoClient mg, DB db,
					DBCollection collection, DBObject... dbObj)
					throws Exception {

				if (dbObj == null || dbObj.length != 2) {
					logger.logInfo("更新操作，传入的DBObject不合法，不能为空，且只能含有二个元素,直接返回");
					return 0;
				}

				int updateAmount = collection.update(dbObj[0], dbObj[1],
						upsert, multi).getN();

				logger.logInfo("DBName[{}],CollectionName[{}],操作[{}],更新的数量：[{}]" ,dbName,collectionName,"更新update",updateAmount);

				return updateAmount;

			}
		}, oldObj, newObj);

		return (Integer) obj;
	}

	/**
	 * 通过DBObject进行删除：返回值为删除的数量
	 * 
	 * @param dbObj
	 * @return
	 * @throws Exception 
	 */
	public int delete(DBObject dbObj) throws Exception {

		if (dbObj == null) {
			logger.logInfo("禁止传入为null的DBObject进行全文档删除,直接返回");
			return 0;
		}

		Object obj = this.executeTemplate(new MongoExecuteCallBack() {

			@Override
			public Object execute(MongoClient mg, DB db,
					DBCollection collection, DBObject... dbObj)
					throws Exception {

				if (dbObj == null || dbObj.length != 1) {
					logger.logInfo("传入的DBObject不合法，不能为空，且只能含有一个元素,直接返回");
					return 0;
				}

				int removeAmount = collection.remove(dbObj[0]).getN();

				logger.logInfo("DBName[{}],CollectionName[{}],操作[{}],删除的数量：[{}]" ,dbName,collectionName,"delete",removeAmount);

				return removeAmount;

			}
		}, dbObj);

		return (Integer) obj;
	}

	/**
	 * 保存：返回本次插入mongodb数据库失败的数量
	 * 
	 * @param dbObj
	 * @return
	 * @throws Exception 
	 */
	public int save(DBObject... dbObj) throws Exception {

		if (dbObj == null || dbObj.length <= 0) {
			logger.logInfo("传递的DBObject为空或大小为0,直接返回");
			return 0;
		}

		Object obj = this.executeTemplate(new MongoExecuteCallBack() {

			@Override
			public Object execute(MongoClient mg, DB db,
					DBCollection collection, DBObject... dbObj)
					throws Exception {

				int amount = collection.insert(Arrays.asList(dbObj)).getN();
				logger.logInfo("DBName[{}],CollectionName[{}],操作[{}],本次插入数量:[{}],插入失败的数量[{}]",dbName,collectionName,"插入save",dbObj.length,amount);
				return amount;

			}
		}, dbObj);

		return (Integer) obj;
	}
	
	
	/**
	 * 创建索引：可以一次性创建多个索引
	 * @param dbObj
	 * @throws Exception
	 */
	public void createIndex(DBObject... dbObj)  throws Exception {
		
		if(dbObj == null || dbObj.length <= 0) {
			logger.logInfo("创建所以传递的索引DBObject为空或大小为0,直接返回");
			return;
		}
		
		this.executeTemplate(new MongoExecuteCallBack() {

			@Override
			public Object execute(MongoClient mg, DB db,
					DBCollection collection, DBObject... dbObj)
					throws Exception {

				for(DBObject indexDB : dbObj) {
					//此方法，如果已经存在了此索引，便不会创建，也不会抛出异常，故不需要判断是否存在了
					collection.createIndex(indexDB);
				}
				logger.logInfo("创建索引完毕，传递的创建索引的数量：" + dbObj.length);
				return dbObj.length;

			}
		}, dbObj);
		
		
	}
	
	/**
	 * 通过字段key进行去重，然后返回该集合的数量
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public int distinct(String key)  throws Exception {
		return distinctCountTemplage(key,null);
		
	}
	
	/**
	 * 通过字段key进行去重，并在查询条件query下，然后返回该集合的数量
	 * @param key
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public int distinct(String key,DBObject query)  throws Exception {
		return distinctCountTemplage(key,query);
		
	}
	
	
	/**
	 * 通过字段key进行去重，查询条件query[如果为空，则不添加查询条件]，然后返回该集合的数量
	 * @param key
	 * @param query
	 * @return
	 * @throws Exception
	 */
	private int distinctCountTemplage(final String key,final DBObject query)  throws Exception {
		
		if(StringUtil.isNullOrBlank(key)) {
			logger.logInfo("distinct操作，传入的distinct字段key不能为空或者空字符");
			return 0;
		}
		
		Object result = this.executeTemplate(new MongoExecuteCallBack(){

			@SuppressWarnings("unchecked")
			@Override
			public Object execute(MongoClient mg, DB db,
					DBCollection collection, DBObject... dbObj)
					throws Exception {
				
				List<Object> list = null;
				
				if(query == null ) {
					list = collection.distinct(key);
				 } else {
					 list = collection.distinct(key, query);
				 }
				
				int amount = (list == null || list.isEmpty()) ? 0 : list.size();
				
				logger.logInfo("DBName[{}],CollectionName[{}],操作[{}] 查询到的key[{}]distinct的数量为：[{}]",dbName,collectionName,"distinct",key,amount);
				
				return amount;
				
			}});
		
		
		
		return (Integer)result;
	}
	

	/**
	 * 执行模板：在MongoExecuteCallBack实现中，MongoClient mg不需要关闭，因为在此模板方法中已经关闭
	 * 
	 * @param executer
	 * @param dbObj
	 * @return
	 * @throws Exception 
	 */
	public Object executeTemplate(MongoExecuteCallBack executer,
			DBObject... dbObj) throws Exception {

		//执行开始时间
		Date start = new Date();

		DB db = null;

		DBCollection collection = null;

		Object returnObj = null;
		
		Date getDBAndCollectionStart = new Date();
		
		Date getDBAndCollectionEnd = null;

		try {

			db = mongoClient.getDB(this.getDbName());
			collection = db.getCollection(this.getCollectionName());
			
			getDBAndCollectionEnd = new Date();

			returnObj = executer.execute(mongoClient, db, collection, dbObj);

		} catch (Exception e) {

			e.printStackTrace();
			logger.logError("根据host[{}]和port[{}]执行Mongo操作失败[{}]", HOST,
					PORT, e);
			logger.logError(e);
			throw e;
		} 
		
		//执行结束时间
		Date end = new Date();
		
		logger.logInfo("======执行整个Mongo操作耗时[{}]毫秒,其中获取DB和Collections耗时[{}]",
				(end.getTime()-start.getTime()),
				(getDBAndCollectionEnd.getTime() - getDBAndCollectionStart.getTime()));

		return returnObj;

	}

	// ==============================sets and gets methods
	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getCollectionName() {
		return collectionName;
	}

	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}

	@Override
	public String toString() {
		return "MongoService [dbName=" + dbName + ", collectionName="
				+ collectionName + "]";
	}

}
