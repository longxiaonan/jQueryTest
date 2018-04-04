package properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.gdcp.common.logger.SimpleLogger;

import common.utils.StringUtil;

/**
 * 操作属性文件的工具类
 * @author lijian@cstonline.cn
 *
 * @date 2014年6月15日 上午11:01:55
 */
public class PropertiesUtil {
	
	private static  SimpleLogger logger = new SimpleLogger(PropertiesUtil.class);

	private  PropertiesUtil(){
		throw new RuntimeException("haha,you can't do this.");
	}
	
	/**
	 * 通过传递文件的路径，以Map形式返回解析Properties的内容，key就是一行记录"="前面的内容
	 * @param filePath
	 * @return
	 * @throws Exception 
	 */
	public static Map<String,String> getProperties(String filePath)  {
		logger.logInfo("加载文件 {}", filePath);
		if(StringUtil.isNullOrBlank(filePath)) {
			logger.logError("传递Properties的文件路径名字不能为空");
			return Collections.emptyMap();
		}
		
		Properties prop = new Properties();
		
		Map<String,String> data = new HashMap<String, String>();
		
		InputStream is = null;
		
		try {
			
			//is = PropertiesUtil.class.getResourceAsStream(filePath);
			is = new FileInputStream(filePath);
			
			prop.load(is);
			Set<Object> keySet = prop.keySet();
			
			if(keySet == null || keySet.isEmpty()) {
				return Collections.emptyMap();
			}
			
			
			for(Object key : keySet) {
				
				String value = prop.getProperty(key.toString());
				
				data.put(key.toString(), value);
			}
			
			
		} catch(Exception e) {
			logger.logError("解析Properties出现异常，文件路径[{}]，异常信息[{}]", filePath,e);
		} finally {
			
			if(is != null) {
				try {
					is.close();
				} catch (IOException e) {
					logger.logError("关闭解析Properties的输出流出现异常，文件路径[{}]，异常信息[{}]", filePath,e);
					logger.logError(e);
				}
			}
		}
		
		return data;
	}
	
	
	/**
	 * just for test
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		System.out.println(getProperties("conf/mongo.properties"));
	}
	
}
