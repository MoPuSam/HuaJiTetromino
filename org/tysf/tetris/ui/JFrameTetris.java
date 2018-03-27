package org.tysf.tetris.ui;

import java.awt.Toolkit;

import javax.swing.JFrame;

import org.tysf.tetris.config.GameConfig;

public class JFrameTetris extends JFrame{//窗口
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//从xml中读取JFrame宽
	public static final int width=GameConfig.getFrameConfig().getWidth();
	//从xml中读取JFrame高
	public static final int height=GameConfig.getFrameConfig().getHeight();
	//面板
	private JPanelTetris panel;
	//获取该当前屏幕宽、高
	private static final int windowW=Toolkit.getDefaultToolkit().getScreenSize().width;
	private static final int windowH=Toolkit.getDefaultToolkit().getScreenSize().height;
	public JFrameTetris(JPanelTetris panel) {
		super("俄罗斯方块");
		//设置窗口大小不可变
		this.setResizable(false);
		//设置窗口可见
		this.setVisible(true);
		//设置窗口大小
		this.setSize(width, height);
		//设置窗口可关闭
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//设置窗口居中
		this.setLocation(windowW-width>>1, windowH-height>>1);
		//设置窗口默认面板
		this.setContentPane(panel);
	}
	
}

