package org.tysf.tetris.ui;

import java.awt.Graphics;
import java.awt.Point;

public class LayerGame extends Layer{//游戏框图层
	//有时dto还为null，
	///public Point[] cells=this.dto.getSingleCell().getPoints();
	private static final int ARC=32;
	public LayerGame(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	public void paint(Graphics g) {
		int type=dto.getSingleCell().getType();
		Point[] cells=this.dto.getSingleCell().getPoints();
		this.createWindow(g ,Img.GAME);
		//判断是否死亡
		if(!dto.getSingleCell().isOver(dto.getGameMap(),cells)){
			dto.setOver(true);
				return;
		}
		if(dto.isStop()){
		//绘制俄罗斯方块
		for(int i=0;i<cells.length;i++){
			g.drawImage(Img.HUAJI, 
					this.x+cells[i].x*ARC, 
					this.y+cells[i].y*ARC,
					this.x+cells[i].x*ARC+ARC,
					this.y+cells[i].y*ARC+ARC,
					type*ARC,0,type*ARC+32,ARC,null);
		}
		}
		//绘制落到底部的俄罗斯方块
		boolean[][] map=dto.getGameMap();
		for(int x=0;x<map.length;x++){
			for(int y=0;y<map[x].length;y++){
				if(map[x][y]){
				g.drawImage(Img.HUAJI, 
					this.x+x*ARC, 
					this.y+y*ARC,
					this.x+x*ARC+ARC,
					this.y+y*ARC+ARC,
					256,0,256+ARC,ARC,null);
				}
			}
		}
	}
}
