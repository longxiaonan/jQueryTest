package nosql.mongo.query;

import java.util.LinkedHashMap;
import java.util.Set;

import com.gdcp.common.util.StringUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * 向mongodb传入的查询排序的参数
 */
public class MongoSortParams {
	
	/**用LinkedHashMap是为了保证插入顺序和读取顺序一致*/
	private LinkedHashMap<String, MongoSortConstant> sortMap;
	
	/**缓存orderby DBObject，主要是作为：当第二次获取其orderby DBObject，直接用缓存即可*/
	private BasicDBObject orderby ;
	
	private MongoSortParams(LinkedHashMap<String, MongoSortConstant> sortMap){
		this.sortMap = sortMap;
	}
	
	/**
	 * 获取实例
	 * @return
	 */
	public static  MongoSortParams  getInstance() {
		return new MongoSortParams(new LinkedHashMap<String, MongoSortConstant>());
	}
	
	/**
	 * 加上正序排的字段
	 * @param filed 需要正序排列的字段
	 * @return
	 */
	public MongoSortParams addAsc(String filed) {
		
		if(StringUtil.isNullOrBlank(filed)) {
			return this;
		}
		
		sortMap.put(filed, MongoSortConstant.ASC_SORT);
		
		return this;
	}
	
	/**
	 * 加上倒序排的字段
	 * @param filed 需要倒序排列的字段
	 * @return
	 */
	public MongoSortParams addDesc(String filed) {
		
		if(StringUtil.isNullOrBlank(filed)) {
			return this;
		}
		
		sortMap.put(filed, MongoSortConstant.DESC_SORT);
		
		return this;
	}
	
	/**
	 * 转成排序的DBObject
	 * @return
	 */
	public DBObject toOrderbyDBObject() {
		
		if(sortMap == null || sortMap.isEmpty()) {
			return null;
		}
		
		//曾经计算过的话，就直接返回
		if(orderby != null) {
			return orderby;
		}
		
		//处理排序的DBObject
		Set<String> sortKeys = sortMap.keySet();
			
			if(sortKeys != null && !sortKeys.isEmpty()) {
				
				orderby = new BasicDBObject();
				
				for(String key : sortKeys) {
					//这里sortMap.get(key)应该不可能为null，但是还是判断一下吧
					orderby.put(key, sortMap.get(key) == null ? 1 : sortMap.get(key).getSortKey());
					
				}
			}
		
		return orderby;
	}

	@Override
	public String toString() {
		return "MongoDBQuerySortParams [sortMap=" + sortMap + "]";
	}
	
}
