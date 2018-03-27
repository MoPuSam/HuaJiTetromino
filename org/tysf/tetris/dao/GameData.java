package org.tysf.tetris.dao;

import java.util.List;

import org.tysf.tetris.dto.Player;

public interface GameData {//游戏数据
	public List<Player> loadData();
	public void saveData(List<Player> palyer);
}
