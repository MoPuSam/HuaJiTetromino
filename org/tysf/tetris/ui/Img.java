package org.tysf.tetris.ui;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Img {//存放图片数据
	//提示框
	public static final Image WINDOW=new ImageIcon("Picsrc/pointFrame.png").getImage();
	//游戏框
	public static final Image GAME=new ImageIcon("Picsrc/gameFrame.png").getImage();
	//本地记录框
	public static final Image DISK=new ImageIcon("Picsrc/diskFrame.png").getImage();
	//记录框2
	public static final Image HARD=new ImageIcon("Picsrc/recordFrame2.png").getImage();
	//选择难度框
	public static final Image HARD_CHOSE=new ImageIcon("Picsrc/choose.png").getImage();
	//分数字体
	public static final Image SCORE=new ImageIcon("Picsrc/score.png").getImage();
	//本地记录字体
	public static final Image RECORD=new ImageIcon("Picsrc/diskRecord.png").getImage();
	//记录框
	public static final Image TIAO=new ImageIcon("Picsrc/recordFrame.png").getImage();
	//方块条
	public static final Image HUAJI=new ImageIcon("Picsrc/huajitiao.png").getImage();
	//经验条
	public static final Image RECT=new ImageIcon("Picsrc/rect.png").getImage();
	//开始按钮
	public static final ImageIcon START= new ImageIcon("Picsrc/start.png");
	//停止按钮
	public static final ImageIcon PAUSE= new ImageIcon("Picsrc/stop.png");
	//退出按钮
	public static final ImageIcon QUIT= new ImageIcon("Picsrc/quit.png");
	public static final Image SF= new ImageIcon("Picsrc/stopFont.png").getImage();
	//下一个俄罗斯方块的形状
	public static final Image[] TISHI;
	static{
		TISHI=new Image[7];
		for(int i=0;i<7;i++){
			TISHI[i]=new ImageIcon("Picsrc/"+i+"c.png").getImage();
		}
	}
}
