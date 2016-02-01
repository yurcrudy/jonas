package com.yurc.jonas;

import java.lang.reflect.Method;

import com.yurc.jonas.config.ConfigLoader;
import com.yurc.jonas.render.JspRender;
import com.yurc.jonas.render.Render;
import com.yurc.jonas.route.Routers;
import com.yurc.jonas.servlet.wrapper.Request;
import com.yurc.jonas.servlet.wrapper.Response;

public final class Jonas {
	
	/**
	 * 存放所有路由
	 * */
	private Routers routers;
	
	/**
	 * 配置加载器
	 * */
	private ConfigLoader configLoader;
	
	/**
	 * 框架是否已经加载
	 * */
	private boolean init = false;
	
	/**
	 * 渲染器
	 * */
	private Render render;
	
	private Jonas(){
		routers = new Routers();
		configLoader = new ConfigLoader();
		render = new JspRender();
	}
	
	public boolean isInit(){
		return init;
	}

	public void setInit(boolean init) {
		this.init = init;
	}
	
	private static class JonasHolder{
		private static Jonas ME = new Jonas();
	}
	
	public static Jonas me(){
		return JonasHolder.ME;
	}
	
	public Jonas loadConf(String conf){
		configLoader.load(conf);
		return this;
	}
	
	public Jonas setConf(String name,String value){
		configLoader.setConf(name, value);
		return this;
	}
	
	public String getConf(String name){
		return configLoader.getConf(name);
	}
	
	public Jonas addRoutes(Routers routers){
		this.routers.addRoute(routers.getRoutes());
		return this;
	}
	
	public Routers getRouters(){
		return routers;
	}
	
	/**
	 * 添加路由
	 * @param path   映射的path
	 * @param methodName 方法名称
	 * @param controller 控制器对象
	 * @return   返回Jonas
	 * */
	public Jonas addRoute(String path,String methodName,Object controller){
		try{
			Method method = controller.getClass().getMethod(methodName, Request.class,Response.class);
			this.routers.addRoute(path,method,controller);
		}catch(NoSuchMethodException e){
			e.printStackTrace();
		}catch(SecurityException e){
			e.printStackTrace();
		}
		return this;
	}
	
	public Render getRender(){
		return render;
	}
	
	public void setRender(Render render){
		this.render = render;
	}
	
}
