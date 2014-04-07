package cz.upol.jasgweb;

import java.io.IOException;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Session {

	private static Map<String, Object> getSessionMap() {
		try {
			FacesContext currentContext = FacesContext.getCurrentInstance();
			ExternalContext externalContext = currentContext
					.getExternalContext();
			Map<String, Object> session = externalContext.getSessionMap();

			return session;
		} catch (NullPointerException e) {
			return null;
		}
	}

	public static <T> T getBean(String beanName) {
		Map<String, Object> session = getSessionMap();

		if (session == null) {
			return null;
		}

		Object beanObject = session.get(beanName);

		@SuppressWarnings("unchecked")
		T bean = (T) beanObject;

		return bean;
	}

	public static <T> T getBean(ServletRequest request, String beanName) {
		if (request instanceof HttpServletRequest) {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			HttpSession session = httpRequest.getSession();
			Object beanObject = session.getAttribute(beanName);

			@SuppressWarnings("unchecked")
			T bean = (T) beanObject;

			return bean;
		}
		
		return null;
	}

	public static <T> void addBean(String beanName, T bean) {
		Map<String, Object> session = getSessionMap();

		if (session == null) {
			return;
		}

		Object beanObject = bean;

		session.put(beanName, beanObject);
	}

	public static void reloadTo(String name) {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		try {
			externalContext.redirect(name);
		} catch (IOException e) {
			ResultReporter.reportError("Cyhba při přesměrování",
					"Nepodařilo se přesměrovat na stránku přihlášení", e);
		}
		
		//org.ajax4jsf.Filter a;
	}
}
