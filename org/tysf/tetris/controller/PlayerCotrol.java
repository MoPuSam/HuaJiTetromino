package org.tysf.tetris.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PlayerCotrol extends KeyAdapter {//玩家控制器
	private GameCotrol cotrol;//游戏控制器
	public PlayerCotrol(GameCotrol cotrol) {//传入游戏控制器
		this.cotrol=cotrol;
	}
	@Override
	public void keyReleased(KeyEvent e) {//键盘事件：当键盘被释放，获取方法
		this.cotrol.actionByKeyCode(e.getKeyCode());
	}
	@Override
	public void keyPressed(KeyEvent e){//一键下落
		if(e.getKeyCode()==69){
		this.cotrol.getGameServer().keyDown();
		}
	}
	public GameCotrol getCotrol() {
		return cotrol;
	}
	
}
