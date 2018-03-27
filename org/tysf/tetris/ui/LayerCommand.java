package org.tysf.tetris.ui;

import java.awt.Graphics;
import java.awt.Image;

public class LayerCommand extends Layer{//提示框图层
	private static final Image WINDOW=Img.WINDOW;
	public LayerCommand(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	public void paint(Graphics g) {
		this.createWindow(g,WINDOW);
		//在提示框中绘制下一个俄罗斯方块的信息
		if(dto.isStop()){
		this.drawImageAtCenter(Img.TISHI[dto.getNext()], this.w, this.h, g);
		}
	}
}
