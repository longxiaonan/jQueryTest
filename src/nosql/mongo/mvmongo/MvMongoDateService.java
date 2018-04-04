package nosql.mongo.mvmongo;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import com.gdcp.common.db.DBService;
import com.gdcp.common.db.ResultSetCallBack;
import com.gdcp.common.logger.SimpleLogger;
import com.gdcp.common.util.StringUtil;
import com.gdcp.common.util.TimeScheduler;
import com.gdcp.common.util.date.DateUtil;

/**
 * 定时更新缓存中的某个集合的已经迁移（处理过）的最小、最大的迁移开始时间，并向外提供接口进行查询
 * @author lijian@cstonline.com
 *
 * @date 2015年4月9日 上午11:05:50
 */
public class MvMongoDateService {
	
	private  static final SimpleLogger logger = new SimpleLogger(MvMongoDateService.class);
	
	/**key:collection*/
	private static final ConcurrentHashMap<String,MvMongoDate>  mvMongoDateMap = new ConcurrentHashMap<String,MvMongoDate>();
	
	private static MvMongoDateService INSTANCE;
	
	private MvMongoDateService(){}
	
	public static MvMongoDateService getInstance() {
		
		if(INSTANCE == null) {
			
			synchronized (MvMongoDateService.class) {
				
				if(INSTANCE == null) {
					
					INSTANCE = new MvMongoDateService();
					
					//提交定时器
					commitScheduleTask();
				}
			}
			
		}
		return INSTANCE;
	}
	
	/**
	 * 定时更新数据
	 */
	private static void commitScheduleTask() {
		
		TimeScheduler.instance().registerScheduledTask(new Runnable() {
			
			@Override
			public void run() {
				
				try {
					
					Map<String,MvMongoDate> map = queryMvMongoDate();
					
					if(map == null || map.isEmpty()) {
						
						logger.logInfo("not find any MvMongoDate");
						return ;
					}
					
					for(String collection : map.keySet()) {
						
						mvMongoDateMap.put(collection, map.get(collection));
					}
					
					
					logger.logInfo("update MvMongoDate ok");
					
					
				}catch(Exception e) {
					
					logger.logError("定时更新最小、最大的迁移开始时间出现异常 ex[{}]", e);
					logger.logError(e);
				}
				
			}
		}, 2, 2 * 60 * 60, TimeUnit.SECONDS);  //俩个小时更新一次
	}
	
	
	@SuppressWarnings("unchecked")
	private static Map<String,MvMongoDate> queryMvMongoDate() throws Exception {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select  t.collectionname,MIN(t.query_time_gte) min_query_time_gte ,MAX(t.query_time_gte + 1) max_query_time_gte  ");
		sql.append(" FROM  TCPST_MOVE_MONGO t                                                                                        ");
		sql.append(" GROUP BY t.collectionname                                                                                       ");
		
		return (Map<String,MvMongoDate>)DBService.getServiceOfGDCP().query(sql.toString(), new ResultSetCallBack(){

			@Override
			public Object execute(ResultSet rs) throws Exception {
				
				Map<String,MvMongoDate> map = new HashMap<String, MvMongoDate>();
				
				while(rs.next()) {
					
					String collectionname = rs.getString("COLLECTIONNAME");
					
					Date minQueryTimeGte = rs.getDate("MIN_QUERY_TIME_GTE");
					
					Date maxQueryTimeGte = rs.getDate("MAX_QUERY_TIME_GTE");
					
					boolean  isBadData =  (StringUtil.isNullOrBlank(collectionname) || minQueryTimeGte == null || maxQueryTimeGte == null) ;
					
					if(!isBadData) {
					
						MvMongoDate mvMongoDate = new MvMongoDate();
						mvMongoDate.setCollectionname(collectionname);
						mvMongoDate.setDataTimeGte(minQueryTimeGte);
						mvMongoDate.setDataTimelt(maxQueryTimeGte);
						
						map.put(collectionname, mvMongoDate);
						
						logger.logInfo("find it : [{}]", mvMongoDate);
					}
					
				}
				
				return map;
			}});
	}
	
	/**
	 * 确定从哪种类型的集合出（结构化 or 非结构化  or 结构化&非结构化）
	 * @param queryStartTime 查询数据的开始时间
	 * @param queryEndTime   查询数据的结束时间
	 * @param collection     查询哪个集合
	 * @return
	 */
	public FromWhichTypeCollection getWhichTypeCollection(Date queryStartTime,Date queryEndTime,String collection) {
		
		DateUtil.checkIsNull(queryStartTime, "开始时间不能为空");
		DateUtil.checkIsNull(queryEndTime, "结束时间不能为空");
		StringUtil.checkIsNullOrBlank(collection, "集合名称不能为空");
		
		if(queryStartTime.compareTo(queryEndTime) >0) {
			throw new RuntimeException("开始时间比结束时间大,开始时间:" + queryStartTime + ",结束时间：" + queryEndTime);
		}
		
		MvMongoDate currentResutl =  mvMongoDateMap.get(collection);
		
		//证明还未进行迁移过数据，查询数据只能从非结构化查询了
		if(currentResutl == null) {
			return FromWhichTypeCollection.ORIGINAL_TYPE;
		}
		
		
		/**
		 * 从结构化出
		 * 1. 查询的开始时间大于等于结构化数据的开始时间 
		 * 2. 查询的结束时间小于等于结构化数据的结束时间
		 * 
		 */
		if( queryStartTime.compareTo(currentResutl.getDataTimeGte()) >= 0  && queryEndTime.compareTo(currentResutl.getDataTimelt()) <= 0 ) {
			
			return FromWhichTypeCollection.HIS_TYPE;
		}
		
		/**
		 * 从非结构化和结构化共同出
		 * 
		 * 1.结构化数据的开始时间大于查询数据的开始时间并且小于查询数据的结束时间
		 * 
		 * 2.结构化数据的结束时间大于查询数据的结束时间
		 * 
		 */
		if(currentResutl.getDataTimeGte().compareTo(queryStartTime) > 0 && 
				currentResutl.getDataTimeGte().compareTo(queryEndTime) < 0 
 				&& currentResutl.getDataTimelt().compareTo(queryEndTime) > 0 
				) {
			
			return FromWhichTypeCollection.ORIGINAL_HIS_TYPE;
			
		}
		
		//其他的情况都是从非结构化出
		return FromWhichTypeCollection.ORIGINAL_TYPE;
	}
	
	/**
	 * 获取结构化和非结构化的时间段（前提是，要符合[从非结构化和结构化共同出]的条件，不然会抛出异常）
	 * @param queryStartTime 查询数据的开始时间
	 * @param queryEndTime   查询数据的结束时间
	 * @param collection     查询哪个集合
	 * @return
	 */
	public OriginalHisTimePeriod getOriginalHisTimePeriod(Date queryStartTime,Date queryEndTime,String collection) {
		
		if ( FromWhichTypeCollection.ORIGINAL_HIS_TYPE != getWhichTypeCollection( queryStartTime, queryEndTime, collection) ){
			
			throw new RuntimeException("不符合[从非结构化和结构化共同出]的条件，请确定");
		}
		
		//符合条件了，当然currentResutl不会为空
		MvMongoDate currentResutl =  mvMongoDateMap.get(collection);
		
		logger.logInfo("某个集合的已经迁移时间 [{}]", currentResutl);
		
		/**
		 * 结构化和非结构化的时间段设置为：
		 * 1. 非结构化的开始时间设置为查询的开始时间，非结构化的结束时间设置为[已经存在的迁移的时间段的开始时间]  （减去一秒）  
		 * 
		 * 2. 结构化的开始时间设置为 [已经存在的迁移的时间段的开始时间]，结构化的结束时间设置为查询的结束时间
		 * 
		 * 3. 要注意时间的交叉点，如果前面包括末尾点的化，后面的就不要包括开始点了
		 * 
		 */
		OriginalHisTimePeriod period = new OriginalHisTimePeriod();
		
		period.setOriginalStartTime(queryStartTime);
		period.setOriginalEndTime( new Date(currentResutl.getDataTimeGte().getTime()-1000));  //（减去一秒），因为查询都是小于等于  
		
		period.setHisStartTime(currentResutl.getDataTimeGte());
		period.setHisEndTime(queryEndTime);
		
		logger.logInfo("时间段 {}", period);
		
		return period;
	}
	
	
	/**
	 * 跨度时间，转化成天，如：2015-04-08 10:10:10   ~ 2015-04-10 10:10:10 ，转成：20150408、20150409、20150410这三天
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public  List<String>  toDistanceDays(Date startTime,Date endTime){
		
		Date start = DateUtil.parseYYYYMMDD(DateUtil.formatYYYYMMDD(startTime));  //转成年月日格式
		
		Date end = DateUtil.parseYYYYMMDD(DateUtil.formatYYYYMMDD(endTime));
		
		if(startTime.compareTo(endTime) > 0) {
			
			logger.logInfo("bad data,startTime > endTime");
			return null;
		} 
		
		if(start.compareTo(end) == 0) {
			
			return Arrays.asList(DateUtil.formatYYYYMMDDWithoutLine(start));
		}
		
		List<String> daysList = new ArrayList<String>();
		
		int days = 1;
		
		daysList.add(DateUtil.formatYYYYMMDDWithoutLine(start));
		
		while(true) {
			
			Date newDate = DateUtil.distanceDaysOfDate(start, days++);
			
			daysList.add(DateUtil.formatYYYYMMDDWithoutLine(newDate));
			
			//等于end值了，就退出
			if(newDate.compareTo(end) == 0) {
				
				break;
			}
		}
		
		return daysList;
		
	}
	
}
