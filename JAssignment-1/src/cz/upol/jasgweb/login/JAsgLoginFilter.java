package cz.upol.jasgweb.login;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cz.upol.jasgweb.ResultReporter;

public class JAsgLoginFilter implements Filter {

	public JAsgLoginFilter() {
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		Login login = Login.getLogin(request);
		boolean logged = login.isLoggedIn();

		if (!logged) {
			doFilterReload(request, response);
		}

		chain.doFilter(request, response);

	}

	private void doFilterReload(ServletRequest arg0, ServletResponse arg1)
			throws IOException {
		if (arg0 instanceof HttpServletRequest
				&& arg1 instanceof HttpServletResponse) {
			HttpServletRequest request = (HttpServletRequest) arg0;
			HttpServletResponse response = (HttpServletResponse) arg1;

			String requestPath;
			requestPath = request.getContextPath();
			response.sendRedirect(requestPath + "/" + Login.MAIN_PAGE);
			ResultReporter
					.reportWarning("Nejste přihlášen", "Nejste přihlášen");
		}
	}

}
