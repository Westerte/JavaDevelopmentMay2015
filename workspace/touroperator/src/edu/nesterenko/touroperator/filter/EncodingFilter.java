package edu.nesterenko.touroperator.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;


@WebFilter(urlPatterns = {"/*"}, 
		initParams = {
		@WebInitParam(name = "encoding", value = "UTF-8", description = "Encoding Param")}
)
public class EncodingFilter implements Filter {
	private String encoding;

	public void init(FilterConfig fConfig) throws ServletException {
		 encoding = fConfig.getInitParameter("encoding");
	}
	
	public void destroy() {
		encoding = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String requestEncoding = request.getCharacterEncoding();
		if(encoding != null && !encoding.equalsIgnoreCase(requestEncoding)) {
			request.setCharacterEncoding(encoding);
			response.setCharacterEncoding(encoding);
		}
		chain.doFilter(request, response);
	}
}
