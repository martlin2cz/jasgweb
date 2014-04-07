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
	public void init(FilterConfig config) throws ServletException {
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		boolean logged = Login.isLoggedInStatic(request);

		if (!logged) {
			doFilterReload(request, response);
		}

		chain.doFilter(request, response);

	}

	private void doFilterReload(ServletRequest request, ServletResponse response)
			throws IOException {
		
		
		if (request instanceof HttpServletRequest
				&& response instanceof HttpServletResponse) {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			HttpServletResponse httpResponse = (HttpServletResponse) response;

			String requestPath;
			
			requestPath = httpRequest.getContextPath();
			httpResponse.sendRedirect(requestPath + "/" + Login.MAIN_PAGE);
			
			ResultReporter
					.reportWarning("Nejste přihlášen", "Nejste přihlášen");
			
			//httpRequest.getSession().getServletContext().get
			System.out.println("Nepřihlášen, provádím přesměrování ...");
		}
	}

}
