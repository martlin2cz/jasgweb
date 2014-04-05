package cz.upol.jasgweb;

import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class Session {

	private static Map<String, Object> getSessionMap() {
		FacesContext currentContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = currentContext.getExternalContext();
		Map<String, Object> session = externalContext.getSessionMap();
		return session;
	}

	public static <T> T getBean(String beanName) {
		Map<String, Object> session = getSessionMap();
		Object beanObject = session.get(beanName);

		@SuppressWarnings("unchecked")
		T bean = (T) beanObject;

		return bean;
	}

	public static <T> void addBean(String beanName, T bean) {
		Map<String, Object> session = getSessionMap();
		
		Object beanObject = bean;
		
		session.put(beanName, beanObject);
	}
}
