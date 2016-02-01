package com.yurc.jonas.render;

import java.io.Writer;

import com.yurc.jonas.Jonas;

public class JspRender implements Render {

	@Override
	public void render(String view, Writer writer) {
		// TODO Auto-generated method stub
		String viewPath = this.getViewPath(view);
	}

	
	private String getViewPath(String view){
		Jonas jonas = Jonas.me();
		
	}
}
