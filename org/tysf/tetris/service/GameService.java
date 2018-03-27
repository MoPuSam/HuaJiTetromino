package org.tysf.tetris.service;

import java.awt.Point;
import java.util.Random;

import org.tysf.tetris.dto.GameDto;
import org.tysf.tetris.entity.SingleCell;

public class GameService {
	private GameDto dto;
	public GameService(GameDto dto) {
		this.dto=dto;
		dto.setSingleCell(new SingleCell());
	}
	public void test() {//测试方法
		int s=dto.getScore();
		dto.setRemoveLine(dto.getRemoveLine()+20);
		if(dto.getRemoveLine()%20==0){
			s+=20;
		}
		dto.setScore(s);
	}
	public GameDto getDto() {
		return dto;
	}
	public void keyUp() {//按上
		this.getDto().getSingleCell().revolve(dto.getGameMap());
	}
	public void keyDown() {//按下
		if(this.getDto().getSingleCell().moveCell(0, 1,dto.getGameMap())){
			Point[] points=dto.getSingleCell().getPoints();
			boolean[][] map=dto.getGameMap();
			for(int i=0;i<points.length;i++){
				map[points[i].x][points[i].y]=true;
			}
			//产生下一个方块
			dto.getSingleCell().initCell(dto.getNext());
			int next=new Random().nextInt(7);
			dto.setNext(next);
		}
		this.getDto().getSingleCell().decline(dto.getGameMap(),dto);
	}
	public void keyLeft() {//按左
		this.getDto().getSingleCell().moveCell(-1, 0,dto.getGameMap());
	}
	public void keyRight() {//按右
		this.getDto().getSingleCell().moveCell(1, 0,dto.getGameMap());
	}
}
