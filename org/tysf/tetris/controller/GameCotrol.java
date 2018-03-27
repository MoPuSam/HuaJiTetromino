package org.tysf.tetris.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.tysf.tetris.dao.GameData;
import org.tysf.tetris.dto.Player;
import org.tysf.tetris.impl.DiskData;
import org.tysf.tetris.service.GameService;
import org.tysf.tetris.ui.JPanelTetris;
import org.tysf.thread.DownThread;

public class GameCotrol {//游戏控制器
	private JPanelTetris gamePanel;//面板
	private GameService gameServer;//业务逻辑
	private GameData diskData;//本地记录
	//定时器
	private Timer timer;
	private TimerTask task;
	
	private HashMap<Integer, Method> actionList;
	
	public GameCotrol(GameService gameServer) {//传入业务逻辑
		super();
		this.gameServer=gameServer;
		diskData=new DiskData();
		this.gameServer.getDto().setDistRecord(diskData.loadData());
		timer=new Timer();
		//将方法以键值对形式存入HashMap
		this.actionList=new HashMap<Integer, Method>();
		try {
			actionList.put(38, this.gameServer.getClass().getMethod("keyUp"));
			actionList.put(40, this.gameServer.getClass().getMethod("keyDown"));
			actionList.put(37, this.gameServer.getClass().getMethod("keyLeft"));
			actionList.put(39, this.gameServer.getClass().getMethod("keyRight"));
			actionList.put(32, this.gameServer.getClass().getMethod("test"));
			actionList.put(69, this.gameServer.getClass().getMethod("keyDown"));
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}

	public void test() {
		this.gamePanel.repaint();
		gameServer.test();
	}

	public JPanelTetris getGamePanel() {
		return gamePanel;
	}

	public GameService getGameServer() {
		return gameServer;
	}

	public void keyUp() {
		this.gameServer.keyUp();
	}

	public void keyDown() {
		this.gameServer.keyDown();
	}

	public void keyLeft() {
		this.gameServer.keyLeft();
	}

	public void keyRight() {
		this.gameServer.keyRight();
	}

	public void setGamePanel(JPanelTetris gamePanel) {
		this.gamePanel = gamePanel;
	}
	//将本地数据写入dto
	public void setDiskData(){
		int i=0;
		List<Player> list=diskData.loadData();
		list.add(new Player(gameServer.getDto().getName(),gameServer.getDto().getScore()));
		diskData.saveData(list);
	}

	public GameData getDiskData() {
		return diskData;
	}
	public void runThread(){//开启线程
		task=new DownThread(gameServer,gamePanel);
		timer.schedule(task, 500,gameServer.getDto().getChoose());
	}
 
	public void actionByKeyCode(int keyCode) {//获取service中的方法
		Method method=actionList.get(keyCode);
		try {
			if(method!=null){
			method.invoke(this.gameServer, null);
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
