package org.tysf.tetris.main;

import org.tysf.tetris.controller.GameCotrol;
import org.tysf.tetris.controller.PlayerCotrol;
import org.tysf.tetris.dto.GameDto;
import org.tysf.tetris.service.GameService;
import org.tysf.tetris.ui.JPanelTetris;
import org.tysf.tetris.ui.JFrameTetris;
/**
 * 
 * @author MoPuSam
 *
 */
public class TetrisMain {//测试main
	public static void main(String[] args) {
		//游戏面板数据
		GameDto gameDto=new GameDto();
		//业务逻辑
		GameService gameServer=new GameService(gameDto);
		//游戏控制器
		GameCotrol cotrol=new GameCotrol(gameServer);
		//玩家控制器
		PlayerCotrol playerCotrol=new PlayerCotrol(cotrol);
		//游戏面板
		JPanelTetris gamePanel=new JPanelTetris(gameDto,playerCotrol,gameServer);
		//讲玩家控制器传入游戏面板
//		gamePanel.setPlayerCotrol(playerCotrol);
		cotrol.setGamePanel(gamePanel);
		//游戏窗口
		new JFrameTetris(gamePanel);
		/*//下落的线程
		Timer timer=new Timer();
		timer.schedule(new DownThread(gameServer, gamePanel), 500,500);*/
		
	}
}
