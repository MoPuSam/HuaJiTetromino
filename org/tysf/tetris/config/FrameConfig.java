package org.tysf.tetris.config;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

public class FrameConfig {//读取frame的配置信息
	private static String title;
	private static int width;
	private static int height;
	//存贮层配置信息的列表
	private List<LayerConfig> layers=null;
	public FrameConfig(Element frameConfig) {
		//从配置信息中依次获取值，初始化属性
		layers=new ArrayList<>();
		this.title=frameConfig.attributeValue("title");
		this.width=Integer.parseInt(frameConfig.attributeValue("width"));
		this.height=Integer.parseInt(frameConfig.attributeValue("height"));
		//存放层信息的列表
		List<Element> layList=frameConfig.elements("layer");
		for(Element layer:layList){//读取layer的配置文件属性，创建LayerConfig对象，放入layers列表
			String className=layer.attributeValue("className");
			int x=Integer.parseInt(layer.attributeValue("x"));
			int y=Integer.parseInt(layer.attributeValue("y"));
			int w=Integer.parseInt(layer.attributeValue("w"));
			int h=Integer.parseInt(layer.attributeValue("h"));
			LayerConfig layc=new LayerConfig(className, x, y, w, h);
			layers.add(layc);
		}
	}
	public static String getTitle() {
		return title;
	}
	public static int getWidth() {
		return width;
	}
	public static int getHeight() {
		return height;
	}
	public List<LayerConfig> getLayers() {
		return layers;
	}
	
}
