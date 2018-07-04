package gdcp.dqi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gdcp.common.config.SpringContextUtil;
import com.gdcp.common.redis.RedisConst;
import com.gdcp.common.redis.client.RedisClient;
import com.gdcp.common.redis.client.RedisClientCluster;
import com.gdcp.data.DataObj;
import com.gdcp.data.cmd.QueryCmdKey;
import com.gdcp.dqi.GBCommandInterface;
import com.gdcp.dqi.Query;

/**
 * @version 1.0
 */
public class TestDqi {

	private static String[] strArr = { "LB9KB8KE3EENJL646", "LB9KB8KE7EENJL665", };
	private static Query query = new Query();

	public static void main(String[] args) throws Exception {
		// getHisGpsInfo();
		// getRtGpsInfo();
		// getOnlineDevcodeAll();
		// getHisGpsInfo();
		// getHisData();
//		getRawData();
//		 sendCmd();
//		connectState("LB9KB8KGXGENJL227");
//		getRtinfo();
//		getHisRtInfo();

//		int parseInt = Integer.parseInt(null);
		
		// DataObj rtInfo = query.getRtInfo("LB9KB8KE2EENJL766");
		// System.out.println(rtInfo);
		// ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
		// for(int i = 0; i < 100; i++){
		// final int index = i;
		// fixedThreadPool.execute(new Runnable() {
		// @Override
		// public void run() {
		// long id = Thread.currentThread().getId();
		// try {
		// getRtInfoFromRedis();
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// }
		//
		// });
		// }

	}
	
	public static void getHisRtInfo() throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "classpath*:META-INF/spring/*.xml", "classpath*:config/spring/*.xml", "classpath*:config/redis/*.xml"});
		
		SimpleDateFormat SDF_YYYYMMDDHHMMSS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String[] devcode = new String[]{"LB9KB8KE4DENJL105"};
		Query query = new Query();
		
//		DataObj rtInfo = query.getRtInfo("LY16C1234H0004241");
//		System.out.println(rtInfo);
		List<DataObj> dataList = query.getData("LB9KB8KE4EENJL784", "RTINFO", SDF_YYYYMMDDHHMMSS.parse("2018-03-20 16:00:00"), SDF_YYYYMMDDHHMMSS.parse("2018-03-20 16:30:02"));
		System.out.println(">>>>>>"+dataList);
		System.exit(0);
		
	}

	private static void getRtinfo() throws Exception{
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "classpath*:META-INF/spring/*.xml", "classpath*:config/spring/*.xml" });
		
//		Long start = System.currentTimeMillis();
//		DataObj carRtInfo = query.getRtInfo("LB9KB8KGXGENJL227");
//		System.out.println(carRtInfo);
//		Long end = System.currentTimeMillis();
//		
//		Optional.ofNullable(carRtInfo).map(n -> n.getJSONObj("VEHICLE")).map(m -> m.getString("SOC")).orElse(null);
//		String mileageStr = Optional.ofNullable(carRtInfo).map(n -> n.getJSONObj("VEHICLE")).map(m -> m.getString("MILEAGE")).orElse(null);
		
//		Query query = new Query();
//		boolean online = query.isOnline("LB9KB8KGXGENJL227");
//		System.out.println(online);
	}

	private static void getRawData() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String devcode = "LB9KB8KE9EENJL635";
		Date start = sdf.parse("2018-01-17 10:30:14");
		Date stop = sdf.parse("2018-01-17 10:59:20");
		List<DataObj> rawInfo = query.getRawInfo(devcode, start, stop);
		for (DataObj obj : rawInfo) {
			String dataString = obj.getString("DATA");
			String funIdString = obj.getString("FUNID");
			String timeString = obj.getStrValue("RECVTIME");
			Long timeLong = Long.valueOf(timeString);
			Date date = new Date(timeLong);
			System.out.printf("[%s] [%s] [%s] \n", sdf.format(date), funIdString, dataString);
		}
	}

	private static void sendCmd() {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "classpath*:META-INF/spring/*.xml", "classpath*:config/spring/*.xml", "classpath*:config/redis/*.xml"});
		
		GBCommandInterface command = new GBCommandInterface();
		ArrayList<Integer> aa = new ArrayList<>();
		aa.add(QueryCmdKey.PARAM_ID_TERMINAL_SN);//
		//{"CONNECTOR":"SC_ZSJ","CONNECT_SN":37531,"DATATAG":"QUERY","DEVCODE":"LB9KA8KJ2FENJL039","QUERY":{"PARAM_LIST":{"224":"60100000117361196"},"PARAM_NUM":1},"RECVTIME":1522204754228,"SN":2,"TIME":1522175953000}
//		aa.add(QueryCmdKey.PARAM_ID_CAR_VIN);
		
		LinkedHashMap map = new LinkedHashMap();
		map.put(QueryCmdKey.PARAM_ID_CAR_VIN, "LB9KB8KE7EENJL665");
		try {
//			DataObj queryParamValueOfDevice = command.queryParamValueOfDevice("LB9KA8KJ2FENJL039", aa);
//			DataObj queryParamValueOfDevice = command.queryParamValueOfDevice("60100000117290128", aa);
//			DataObj queryParamValueOfDevice = command.queryParamValueOfDevice("LB9KB9KJ5EENJL426", aa);
			DataObj queryParamValueOfDevice = command.queryParamValueOfDevice("LB9KB8KGXGENJL227", aa);
//			DataObj queryParamValueOfDevice = command.queryParamValueOfDevice("LB9KB8KE2DENJL121", aa);
			System.out.println(queryParamValueOfDevice);
			// DataObj obj = command.setParamValueToDevice("60100000117361236",
			// map);
			// System.out.println(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * private static void getRtInfoFromRedis() throws Exception{ Long start =
	 * System.currentTimeMillis(); String[] devcodes = new
	 * String[]{"00000000000000003","00000000000000004","00000000000000005"};
	 * List<DataObj> rtInfoFromRedis = query.getRtInfoFromRedis(devcodes);
	 * System.out.println(rtInfoFromRedis); Long end =
	 * System.currentTimeMillis(); logger.debug("鎵归噺璇诲彇涓変釜杞︽満鐨勬椂闂撮棿闅斾负:" + (end
	 * - start)); }
	 */

	private static void getHisData() throws ParseException, Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date start = sdf.parse("2018-01-10 00:00:00");
		// Date stop = new Date();
		// List<DataObj> getHisData = query.getData("LB9KB9KG5GENJL139",
		// "RTINFO", start, stop);
		List<DataObj> getHisData = query.getData("LB9KB8KG0GENJL222", "RTINFO", start, new Date());
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>getHisData");
		System.out.println(">>>>>>>>>>>>> length >>>>>>" + getHisData.size());
		for (DataObj dataObj : getHisData) {
			System.out.println(dataObj);
		}
	}

	private static void getHisGpsInfo() throws ParseException, Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date start = sdf.parse("2017-12-14 14:00:00");
		Date stop = new Date();
		List<DataObj> hisGpsInfo = query.getHisGpsInfo("LB9KB8KGXGENJL258", start, stop);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>getHisGpsInfo");
		System.out.println(">>>>>>>>>>>>> length >>>>>>" + hisGpsInfo.size());
		for (DataObj dataObj : hisGpsInfo) {
			System.out.println(dataObj);
		}
	}

	public static boolean connectState(String devcode) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "classpath*:META-INF/spring/*.xml", "classpath*:config/spring/*.xml", "classpath*:config/redis/*.xml"});
		// 从VLR查询车机是否在线
		// DataSourceAgent agent =
		// DataSourceMgr.getInstance().getSource(devcode,
		// SvcTypeConst.VLR_QUERY.getSvcType());
		// VlrAgent vlrAgent = new VlrAgent(agent);
		// return vlrAgent.isOnline(devcode);

		// 2018-03-14 longxn改成从redis查询车机是否在线
		DataObj dataObj =  new DataObj();
		dataObj.put("ONLINE", false);
//		dataObj.put("ONLINE", true);
			RedisClient redisClient = (RedisClientCluster) SpringContextUtil.getBean(RedisConst.REDIS_CLIENT_CLUSTER);
			String objFromRedis = redisClient.hget(RedisConst.VLR_RT_INFO, devcode);
				dataObj = DataObj.formJSONString(objFromRedis);
				boolean booleanValue = dataObj.getData().getBooleanValue("ONLINE");
		return booleanValue;
	}
	
	private static void getOnlineDevcodeAll() throws Exception {
		List<DataObj> onlineDevcodeAll = query.getOnlineDevcodeAll();
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>getOnlineDevcodeAll");
		System.out.println(onlineDevcodeAll);
		System.out.println(onlineDevcodeAll.size());
	}

	private static void getRtGpsInfo() throws Exception {
		List<DataObj> rtGpsInfo = query.getRtGpsInfo(strArr);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>rtGpsInfo");
		System.out.println(rtGpsInfo);
	}
}
