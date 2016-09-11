package co.dev.outsider.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CORSFilter implements Filter 
{
	
	public CORSFilter() {}

		
	public final void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
	{
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		httpResponse.addHeader("Access-Control-Allow-Origin", "*");
		httpResponse.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");			
		httpResponse.addHeader("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, token");

		if("OPTIONS".equals(httpRequest.getMethod())){
			httpResponse.setStatus(200);
			return;
		}
		
		chain.doFilter(request, response);
	}


	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}


	@Override
	public void destroy() {}

}