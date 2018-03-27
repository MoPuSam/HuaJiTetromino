package org.tysf.thread;

import java.util.TimerTask;

import javax.swing.JOptionPane;

import org.tysf.tetris.service.GameService;
import org.tysf.tetris.ui.Img;
import org.tysf.tetris.ui.JPanelTetris;
import org.tysf.tetris.ui.Layer;

public class DownThread extends TimerTask{//自行下落
	private GameService gameServer;
	private JPanelTetris jPanelTetris;
	//暂停字的宽高
	private static final int w=Img.SF.getWidth(null);
	private static final int h=Img.SF.getHeight(null);
	//面板坐标
	private int x;
	private int y;
	public DownThread(GameService gameServer,JPanelTetris jPanelTetris) {
		this.gameServer=gameServer;
		this.jPanelTetris=jPanelTetris;
	}
	@Override
	public void run() {
		//获取游戏层
		Layer gameLayer=jPanelTetris.getLayers().get(1);
		//获得面板宽高
		x=jPanelTetris.getWidth()-w>>1;
		y=jPanelTetris.getHeight()-h>>1;
		if(true){
		if(gameServer.getDto().isStop()&&!gameServer.getDto().isOver()){//游戏开始
			gameServer.keyDown();
			jPanelTetris.repaint();
			}else if(!gameServer.getDto().isStop()){//游戏暂停
				//绘制暂停字
				jPanelTetris.getGraphics().drawImage(Img.SF,x,y,x+w, y+h, 0, 0, w, h,null);
			}else if(gameServer.getDto().isOver()){//游戏结束
				//死亡，重新在地图上绘制结束时的方块
				gameLayer.deadMap(jPanelTetris.getGraphics());
				//获取弹框
				JOptionPane j1=jPanelTetris.getjOptionPan();
				Object[] options={"yes","no"};
				int answer=j1.showOptionDialog(null, "您的最终分数是："+gameServer.getDto().getScore()+",请选择是否继续",null, JOptionPane.YES_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
				String text=j1.showInputDialog("请输入用户名");
				boolean[][] map=gameServer.getDto().getGameMap();
				if(answer==0){//重新开始，清空map
					for(int x=0;x<map.length;x++){
						for(int y=0;y<map[x].length;y++){
							map[x][y]=false;
						}
					}
					//存储用户信息到.bat
					gameServer.getDto().setName(text);
					jPanelTetris.getPlayerCotrol().getCotrol().setDiskData();
					//重新加载本地记录
					gameServer.getDto().setDistRecord(jPanelTetris.getPlayerCotrol().getCotrol().getDiskData().loadData());
					//游戏重新开始
					gameServer.getDto().setStop(true);
					gameServer.getDto().setOver(false);
					gameServer.getDto().setScore(0);
				}else{//退出
					//存储用户数据
					gameServer.getDto().setName(text);
					jPanelTetris.getPlayerCotrol().getCotrol().setDiskData();
					System.exit(0);
				}
			}
		}
		
	}
}
