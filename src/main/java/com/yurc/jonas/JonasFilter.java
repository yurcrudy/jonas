package com.yurc.jonas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.yurc.jonas.route.Route;
import com.yurc.jonas.route.RouteMatcher;
/**
 * Jonas MVC核心处理器
 * */
public class JonasFilter implements Filter{

	private static final Logger LOGGER = Logger.getLogger(JonasFilter.class.getName());
	
	private RouteMatcher routeMatcher = new RouteMatcher(new ArrayList<Route>());
	
	private ServletContext servletContext;
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
