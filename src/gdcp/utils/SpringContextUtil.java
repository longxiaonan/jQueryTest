package gdcp.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public final class SpringContextUtil implements ApplicationContextAware {
	private static ApplicationContext ctx;
	
	public static Object getLocalBean(String beanName) {
		try {
			return ctx.getBean(beanName);
		} catch (BeansException e) {
			e.printStackTrace();
			throw new RuntimeException("BEAN【" + beanName + "】名称有误！");
		}
	}

	public static Object getBean(String beanName) {
		try {
			String remote = SysProperty.getInstance().getValue("remote", "false");
			boolean isremote = Boolean.parseBoolean(remote);
			if (isremote) {
				if(beanName.startsWith("Rpc")) {
					return ctx.getBean(beanName);
				} else {
					if (ctx.containsBean("Rpc" + beanName)) {
						return ctx.getBean("Rpc" + beanName);
					} else {
						return ctx.getBean(beanName);
					}
				}
			} else {
				if(beanName.startsWith("Rpc")) {
					return null;
				} else {
					return ctx.getBean(beanName);
				}
			}

		} catch (BeansException e) {
			e.printStackTrace();
			throw new RuntimeException("BEAN【" + beanName + "】名称有误！");
		}
	}

	public static boolean containsBean(String beanName) {
		return SpringContextUtil.ctx.containsBean(beanName);
	}

	public static ApplicationContext getApplicationContext() {
		return SpringContextUtil.ctx;
	}

	/**
	 * 实现ApplicationContextAware接口的回调方法，设置上下文环境
	 * 
	 * @param beanFactory
	 * @throws BeansException
	 */
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		SpringContextUtil.ctx = ctx;
	}
}
