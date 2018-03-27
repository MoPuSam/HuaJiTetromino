package org.tysf.tetris.dto;

import java.util.List;

import org.tysf.tetris.entity.SingleCell;

public class GameDto {
	//是否开始
	private boolean isStop=false;
	//是否结束
	private boolean isOver=false;
	//难度选择
	private int choose=500;
	//本地记录
	private List<Player> distRecord;
	//分数倍率
	private int rate=10;
	//消行数
	private int removeLine;
	//当前分数
	private int score;
	//玩家姓名
	private String name;
	//不能移动的球
	private boolean[][] gameMap;
	//能移动的球 
	private SingleCell singleCell;
	private int next;
	
	public GameDto() {
		gameMap=new boolean[10][18];
	}
	public int getChoose() {
		return choose;
	}
	public void setChoose(int choose) {
		this.choose = choose;
	}
	
	public int getRemoveLine() {
		return removeLine;
	}
	public void setRemoveLine(int removeLine) {
		this.removeLine = removeLine;
	}
	public SingleCell getSingleCell() {
		return singleCell;
	}
	public void setSingleCell(SingleCell points) {
		this.singleCell = points;
	}
	public boolean[][] getGameMap() {
		return gameMap;
	}
	public void setGameMap(boolean[][] gameMap) {
		this.gameMap = gameMap;
	}
	public int getNext() {
		return next;
	}
	public void setNext(int next) {
		this.next = next;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public List<Player> getDistRecord() {
		return distRecord;
	}
	public void setDistRecord(List<Player> distRecord) {
		this.distRecord = distRecord;
	}
	public boolean isStop() {
		return isStop;
	}
	public void setStop(boolean isStart) {
		this.isStop = isStart;
	}
	public boolean isOver() {
		return isOver;
	}
	public void setOver(boolean isOver) {
		this.isOver = isOver;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
