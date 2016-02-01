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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yurc.jonas.render.JspRender;
import com.yurc.jonas.route.Route;
import com.yurc.jonas.route.RouteMatcher;
import com.yurc.jonas.route.Routers;
import com.yurc.jonas.util.PathUtil;
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
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest)servletRequest;
		HttpServletResponse response = (HttpServletResponse)servletResponse;
		
		String uri = PathUtil.getRelativePath(request);
		
		LOGGER.info("Request URI : " + uri);
		
		Route route = routeMatcher.findRoute(uri);
		
		if(route != null){
			handle(request,response,route);
		}else{
			chain.doFilter(request, response);
		}
	}

	

	@Override
	public void init(FilterConfig filerConfig) throws ServletException {
		// TODO Auto-generated method stub
		Jonas jonas = Jonas.me();
		
		if(!jonas.isInit()){
			String className = filerConfig.getInitParameter("bootstrap");
			Bootstrap bootstrap = this.getBootstrap(className);
			bootstrap.init(jonas);
			
			Routers routers = jonas.getRouters();
			
			if(null != routers){
				routeMatcher.setRoutes(routers.getRoutes());
			}
			servletContext = filerConfig.getServletContext();
			jonas.setInit(true);
		}
	}
	
	private Bootstrap getBootstrap(String className){
		if(null != className){
			try {
				Class<?> clazz = Class.forName(className);
				Bootstrap bootstrap = (Bootstrap)clazz.newInstance();
				return bootstrap;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		throw new RuntimeException("init bootstrap class error!");
	}
	
	
	private void handle(HttpServletRequest request,
			HttpServletResponse response, Route route) {
		// TODO Auto-generated method stub
		Request request = new Request(httpServletRequest);
	}
}
