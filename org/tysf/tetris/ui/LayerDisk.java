package org.tysf.tetris.ui;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Collections;
import java.util.List;

import org.tysf.tetris.dto.Player;

public class LayerDisk extends Layer {//本地记录图层
	private static final Image WINDOW=Img.DISK;
	public LayerDisk(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	public void paint(Graphics g) {
		int height=Img.RECT.getHeight(null);
		this.createWindow(g ,WINDOW);
		g.drawImage(Img.RECORD, this.x+32, this.y+200, null);
		List<Player> players = this.dto.getDistRecord();
		Collections.sort(players);
		//绘制本地记录
		for(int i=0;i<8;i++){
			double percent = (double)this.dto.getScore()/players.get(i).getScore();
			if(percent>=1){
				percent=1;
			}
			drawRect(this.y+250+i*(10+height),dto.getDistRecord().get(i).getName(),String.valueOf(players.get(i).getScore()),percent,g);
		}
	}

}
