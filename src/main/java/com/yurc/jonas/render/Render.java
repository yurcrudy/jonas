package com.yurc.jonas.render;

import java.io.Writer;


/**
 * 视图渲染接口
 * */
public interface Render {

	/**
	 * 渲染到视图
	 * */
	public void render(String view,Writer writer);
}
