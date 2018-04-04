package nosql.mongo;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

/**
 * 自定义的，通过mongodb对象进行处理的callback
 *
 * @date 2014年6月15日 下午3:34:03
 */
public interface MongoExecuteCallBack {
	
	/**
	 * 通过MongoClient、DB、DBCollection、DBObject，进行处理。 
	 * @param mg
	 * @param db
	 * @param collection
	 * @param dbObj
	 * @return
	 * @throws Exception
	 */
	public Object execute(MongoClient mg,DB db,DBCollection collection,DBObject... dbObj) throws Exception ;

}
