package nosql.mongo.springdatamongoDB.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.InitBinder;

import nosql.mongo.springdatamongoDB.UserDao;
import nosql.mongo.springdatamongoDB.entity.User;

public class TestUser {

	private static UserDao userDaoImpl;
	private static ClassPathXmlApplicationContext app;
	private static String collectionName;

	
	static {
		try {
			app = new ClassPathXmlApplicationContext(new String[] { "classpath*:spring/spring-data-mongo.xml" });
			userDaoImpl = (UserDao) app.getBean("userDaoImpl");
			collectionName = "users";
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new TestUser().testAdd();
	}
	
	public void testAdd() {

		// 添加一百个user
		for (int i = 0; i < 100; i++) {
			User user = new User();
			user.setId("" + i);
			user.setAge(i);
			user.setName("zcy" + i);
			user.setPassword("zcy" + i);
			userDaoImpl.insert(user, collectionName);
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("maxAge", 50);
		List<User> list = userDaoImpl.findAll(params, collectionName);
		System.out.println("user.count()==" + list.size());
	}
}