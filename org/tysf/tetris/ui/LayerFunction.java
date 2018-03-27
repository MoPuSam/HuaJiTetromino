package org.tysf.tetris.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

public class LayerFunction extends Layer {//功能框类
	private static final Image WINDOW=Img.WINDOW;
	public LayerFunction(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	public void paint(Graphics g) {
		this.createWindow(g,WINDOW);
		g.drawImage(Img.SCORE, this.x+16, this.y+16, null);
		g.setFont(new Font("黑体", Font.BOLD, 40));
		g.setColor(Color.RED);
		g.drawString(String.valueOf(this.dto.getScore()), this.x+Img.SCORE.getWidth(null)+20, this.y+50);
	}

}
