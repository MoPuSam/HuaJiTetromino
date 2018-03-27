package org.tysf.tetris.ui;

import java.awt.Graphics;
import java.awt.Image;

public class LayerChoose extends Layer{//游戏难度框图层
	private static final Image WINDOW=Img.HARD;
	public LayerChoose(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	public void paint(Graphics g) {
		int height=Img.RECT.getHeight(null);
		this.createWindow(g,WINDOW);
		g.drawImage(Img.HARD_CHOSE, this.x+16, this.y+16, null);
	}

}
