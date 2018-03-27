package org.tysf.tetris.config;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class GameConfig {//读取游戏配置信息
	
	private static  FrameConfig frameConfig=null;
	static{//静态代码块读取XML文件，获取frame的配置信息
		SAXReader reader=new SAXReader();
		try {
			Document doc=reader.read("Game.xml");
			Element game=(Element) doc.getRootElement();
			frameConfig=new FrameConfig(game.element("frame"));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static FrameConfig getFrameConfig(){//返回frame配置信息
		return frameConfig;
	}
}
