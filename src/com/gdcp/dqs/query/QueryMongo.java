package com.gdcp.dqs.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gdcp.common.logger.SimpleLogger;
import com.gdcp.common.mongo.MongoService;
import com.gdcp.common.util.StringUtil;
import com.gdcp.data.DataObj;
import com.gdcp.data.VDPConst;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * 对外的接口采用mongoDB查询，此类查询的集合名字为：rtData
 * 
 */
public class QueryMongo {

	private static SimpleLogger logger = new SimpleLogger(QueryMongo.class);
	/** rtData的通过obdTime倒序进行排序 */
	public static final MongoSortParams RTDATA_OBDTIME_DESC_SORT_PARAMS = MongoSortParams.getInstance()
			.addDesc("RECVTIME");

	/** rtData的通过obdTime正序进行排序 */
	public static final MongoSortParams RTDATA_OBDTIME_ASC_SORT_PARAMS = MongoSortParams.getInstance()
			.addAsc("RECVTIME");

	private static final QueryMongo instance = new QueryMongo();

	private QueryMongo() {
	}

	public static QueryMongo getInstance() {

		return instance;
	}

	/**
	 * 从Mongo中查询最新的Rt信息（有的话只返回一条）【可以批量查询，车机号码用英文逗号隔开，如：M110,M112,M119】
	 * 
	 * @param obdDevIds
	 *            车机号码
	 * @param msgType
	 *            消息ID
	 * @return 如果查询不到数据便返回null
	 * @throws Exception
	 */
	public List<DataObj> queryLatestDataBatch(String obdDevIds, String msgType) throws Exception {

		StringUtil.checkIsNullOrBlank(obdDevIds, "于Mongo中查询最新的RT信息传递的车机号码组不能为空");
		List<DataObj> objs = new ArrayList<DataObj>();
		String[] deviceArr = obdDevIds.split(",");

		if (deviceArr == null || deviceArr.length <= 0) {
			throw new RuntimeException("于Mongo中查询最新的RT信息传递的车机号码组不能为空");
		}
		for (String device : deviceArr) {

			DataObj obj = queryLatestData(device, msgType);
			if (obj != null)
				objs.add(obj);
		}
		return objs;
	}

	/**
	 * Mongo中查询最新的Rt信息，只能支持一次查询一个车机号码 （这个不需要修改，只能从非结构化出）
	 * 
	 * @param obdDevId
	 * @param msgType
	 * @return
	 * @throws Exception
	 */
	public DataObj queryLatestData(String obdDevId, String msgType) throws Exception {

		StringUtil.checkIsNullOrBlank(obdDevId, "于Mongo中查询最新的RT信息传递的车机号码不能为空");

		DBObject queryobj = new BasicDBObject();
		queryobj.put("DEVCODE", obdDevId);
		// 排序的DBObject,当然是倒序，取最新数据
		DBObject orderby = RTDATA_OBDTIME_DESC_SORT_PARAMS.toOrderbyDBObject();

		MongoService service = MongoService.getServiceByGDCPDBNameAndColName(msgType);

		List<DBObject> dataList = service.query(queryobj, orderby, 1);// 只取一条

		if (dataList == null || dataList.isEmpty()) {
			return null;
		}
		DataObj abs = new DataObj(dataList.get(0));
		return abs;
	}

	public List<DataObj> queryHisData(String obdDevId, String msgType, Date start, Date end) throws Exception {

		StringUtil.checkIsNullOrBlank(obdDevId, "于Mongo中查询最新的RT信息传递的车机号码不能为空");
		logger.logInfo("QUERY FOR HIS: {} {} {}", obdDevId,start,end);
		DBObject queryobj = new BasicDBObject();
		queryobj.put("DEVCODE", obdDevId);
		queryobj.put("RECVTIME", new BasicDBObject("$gte", start)
				.append("$lte", end));
		// 排序的DBObject,当然是倒序，取最新数据
		DBObject orderby = RTDATA_OBDTIME_DESC_SORT_PARAMS.toOrderbyDBObject();

		MongoService service = MongoService.getServiceByGDCPDBNameAndColName(msgType);

		List<DBObject> dataList = service.query(queryobj, orderby);
		List<DataObj> objList = new ArrayList<DataObj>();
		
		for (DBObject dbobj : dataList) {
			objList.add(new DataObj(dbobj));
		}
		return objList;
	}
	
	public List<DataObj> querySnapData(String msgType, Date start, Date end) throws Exception {

		StringUtil.checkIsNullOrBlank(msgType, "快照代码不能为空");

		DBObject queryobj = new BasicDBObject();
		queryobj.put("TIMEID", new BasicDBObject("$gte", start.getTime())
				.append("$lte", end.getTime()));
		// 排序的DBObject,当然是倒序，取最新数据
		DBObject orderby = RTDATA_OBDTIME_DESC_SORT_PARAMS.toOrderbyDBObject();

		MongoService service = MongoService.getServiceByGDCPDBNameAndColName(msgType);

		List<DBObject> dataList = service.query(queryobj, orderby);
		List<DataObj> objList = new ArrayList<DataObj>();
		
		for (DBObject dbobj : dataList) {
			objList.add(new DataObj(dbobj));
		}
		return objList;
	}
	
	public List<DataObj> queryRawData(String obdDevId, Date start, Date end) throws Exception {

		StringUtil.checkIsNullOrBlank(obdDevId, "于Mongo中查询最新的RT信息传递的车机号码不能为空");

		logger.logInfo("QUERY FOR RAW: {} {} {}", obdDevId,start,end);
		DBObject queryobj = new BasicDBObject();
		queryobj.put("devcode", obdDevId);
		queryobj.put("recvTime", new BasicDBObject("$gte", start)
				.append("$lte", end));
		// 排序的DBObject,当然是倒序，取最新数据
		DBObject orderby = RTDATA_OBDTIME_DESC_SORT_PARAMS.toOrderbyDBObject();

		MongoService service = MongoService.getServiceByGDCPDBNameAndColName(VDPConst.DATA_RAW);

		List<DBObject> dataList = service.query(queryobj, orderby);
		List<DataObj> objList = new ArrayList<DataObj>();
		
		for (DBObject dbobj : dataList) {
			DataObj obj = new DataObj();
			obj.setDevid(obdDevId);
			obj.setDataTag(VDPConst.DATA_RAW);
			obj.put("DATA", dbobj.get("data"));
			obj.put("FUNID", dbobj.get("funId"));
			obj.put("NAME", dbobj.get("name"));
			obj.put("RECVTIME", dbobj.get("recvTime"));
			obj.put("RESPONSE", dbobj.get("response"));
			obj.put("RESPONSETIME", dbobj.get("responseTime"));
			obj.put("SN", dbobj.get("sn"));
			objList.add(obj);
		}
		return objList;
	}
}
