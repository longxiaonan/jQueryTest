package gdcp.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public class SysProperty extends PropertyPlaceholderConfigurer {
	public static final String CONTEXT_PATH = "CONTEXT_PATH";

	Logger logger = LoggerFactory.getLogger(getClass());
	
	private static SysProperty instance = new SysProperty();

	private Resource[] custLocations;

	public void setCustLocations(Resource[] custLocations) {
		this.custLocations = custLocations;
	}

	public static SysProperty getInstance() {
		return instance;
	}

	private Properties prop;

	private Properties envProp;

	private Properties custProp;

	private Properties confProp;

	private List<Properties> loadCustProperties() throws IOException {
		List<Properties> propList = new ArrayList<Properties>();
		if (this.custLocations != null) {
			for (Resource location : this.custLocations) {
				logger.info("Loading properties file from " + location);
				Properties p = new Properties();
				p.load(new InputStreamReader(location.getInputStream(), "UTF-8"));
				propList.add(p);
			}
		}
		return propList;
	}

	protected Properties mergeProperties() throws IOException {
		prop = super.mergeProperties();
		this.initSysProp();
		this.initEnvCustProp();
		this.initConfProp();
		// 用户配置覆盖系统配置
		for (Object k : custProp.keySet()) {
			String pk = k.toString();
			prop.setProperty(pk, custProp.getProperty(pk));
		}
		// 用app的配置覆盖用户配置及系统配置
		for (Object k : envProp.keySet()) {
			String pk = k.toString();
			prop.setProperty(pk, envProp.getProperty(pk));
		}
		for (Object k : confProp.keySet()) {
			String pk = k.toString();
			prop.setProperty(pk, confProp.getProperty(pk));
		}
		return prop;
	}

	public Properties getProperties() {
		return prop;
	}

	public String getValue(String key) {
		if (prop == null) {
			return null;
		}
		return prop.getProperty(key);
	}

	private Properties initSysProp() {
		logger.info("加载系统配置......");
		Properties sysProp = new Properties();
		try {
			List<Properties> propList = this.loadCustProperties();
			for (Properties p : propList) {
				for (Object k : p.keySet()) {
					String pk = k.toString();
					if (sysProp.containsKey(pk)) {
						logger.warn("重复配置：" + pk + " 旧值：" + sysProp.getProperty(pk) + "新值：" + p.getProperty(pk));
					}
					sysProp.setProperty(pk, p.getProperty(pk));
					prop.setProperty(pk, p.getProperty(pk));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sysProp;
	}

	private void initEnvCustProp() {
		try {
			envProp = new Properties();
			ResourcePatternResolver resourceLoader = new PathMatchingResourcePatternResolver();
			Resource envRes = resourceLoader.getResource("classpath:app.properties");
			logger.info("加载用户配置参数......" + envRes.getURI());
			envProp.load(envRes.getInputStream());
			String location = envProp.getProperty("env", "dev");
			String encoding = envProp.getProperty("encoding", "utf-8");

			String sys_env = envProp.getProperty("sys_env");
			String file = "classpath:config/" + location + ".properties";
			String conf_home = null;
			if (!StringUtils.isEmpty(sys_env)) {
				conf_home = System.getenv(sys_env);
				if (!StringUtils.isEmpty(conf_home)) {
					file = "file:" + conf_home + "/" + location + ".properties";
				}
			}
			logger.info("加载用户配置......" + file);
			Resource confRes = resourceLoader.getResource(file);
			custProp = new Properties();
			custProp.load(new InputStreamReader(confRes.getInputStream(), encoding));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initConfProp() {
		try {
			confProp = new Properties();
			ResourcePatternResolver resourceLoader = new PathMatchingResourcePatternResolver();
			Resource[] rs = resourceLoader.getResources("file:conf/*.properties");
			String encoding = envProp.getProperty("encoding", "utf-8");

			for (Resource r : rs) {
				System.out.println(r.getURI());
				logger.info("加载程序配置参数......" + r.getURI());
				confProp.load(new InputStreamReader(r.getInputStream(), encoding));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void refreshSysProp() {
		this.initSysProp();
		// 用户配置覆盖系统配置
		for (Object k : custProp.keySet()) {
			String pk = k.toString();
			prop.setProperty(pk, custProp.getProperty(pk));
		}
		// 用app的配置覆盖用户配置及系统配置
		for (Object k : envProp.keySet()) {
			String pk = k.toString();
			prop.setProperty(pk, envProp.getProperty(pk));
		}
	}

	public String getValue(String key, String defaultValue) {
		String value = this.getValue(key);
		if (StringUtils.isEmpty(value)) {
			value = defaultValue;
		}
		return value;
	}

	public Set<Object> getKeySet() {
		if (this.prop != null) {
			return this.prop.keySet();
		} else {
			return null;
		}
	}

	public static void main(String[] args) throws IOException {
		ResourcePatternResolver resourceLoader = new PathMatchingResourcePatternResolver();
		Resource[] rs = resourceLoader.getResources("file:conf/*.properties");
		for (Resource r : rs) {
			System.out.println(r.getURI());
		}
	}
}
