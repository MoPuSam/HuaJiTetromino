package org.tysf.tetris.ui;

import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.tysf.tetris.config.GameConfig;
import org.tysf.tetris.config.LayerConfig;
import org.tysf.tetris.controller.PlayerCotrol;
import org.tysf.tetris.dto.GameDto;
import org.tysf.tetris.service.GameService;

public class JPanelTetris extends JPanel{//面板类
	private List<Layer> layers;//存放层对象的列表
	private PlayerCotrol playerCotrol;
	private GameDto dto;
	private GameService gameServer;
	//弹框
	private JOptionPane jOptionPan=new JOptionPane("请选择", 0);
	public JPanelTetris(GameDto dto,PlayerCotrol playerCotrol,GameService gameServer){
		//传入面板数据
		this.dto=dto;
		//传入玩家控制器
		this.playerCotrol=playerCotrol;
		this.gameServer=gameServer;
		//添加监听器
		this.addKeyListener(playerCotrol);
		//初始化层
		initLayer();
	}
	public void initLayer() {
		//初始化列表
		layers=new ArrayList<Layer>();
		//存放LayerConfig对象的列表
		List<LayerConfig> layerConfigs=null;
		//从XML文件中读取layer层信息
		layerConfigs=GameConfig.getFrameConfig().getLayers();
		for(LayerConfig l:layerConfigs){//通过反射将Layer对象放进列表layers
			try {
				Class<?> class1=Class.forName(l.getClassName());
				Constructor<?> constructor=class1.getConstructor(int.class,int.class,int.class,int.class);
				Layer layer=(Layer) constructor.newInstance(l.getX(),l.getY(),l.getW(),l.getH());
				layer.setDto(playerCotrol.getCotrol().getGameServer().getDto());
				layers.add(layer);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(new ImageIcon("Picsrc/background.png").getImage(), 0, 0,this.getWidth(),this.getHeight(),50,10,1200,724,null);
		for(Layer l:layers){//遍历列表layer，分别执行层对象中的paint方法
			l.paint(g);
		}
		//设置面板布局为空
		this.setLayout(null);
		
		final JButton start=new JButton(Img.START);
		//让按钮随按钮上的图案变化
		start.setMargin(new Insets(0,0,0,0));
		//JButton去掉按钮的边框的设置
		start.setBorderPainted(false);
		start.setBounds(610, 350, 94, 50);
		//不启用
		start.setEnabled(false);
		start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dto.setStop(true);
				/*if(task==null){//开启线程
					task=new DownThread(gameServer, playerCotrol.getCotrol().getGamePanel());
					timer.schedule(task, 500,dto.getChoose());
				}*/
				playerCotrol.getCotrol().runThread();
			}
		});
		this.add(start);
		//暂停按钮
		JButton stop=new JButton(Img.PAUSE);
		stop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dto.setStop(!dto.isStop());
			}
		});
		//让按钮随按钮上的图案变化
		stop.setMargin(new Insets(0,0,0,0));
		//JButton去掉按钮的边框的设置
		stop.setBorderPainted(false);
		//设置按钮的位置
		stop.setBounds(610, 290, 94, 50);
		this.add(stop);
		//退出按钮
		JButton quit=new JButton(Img.QUIT);
		quit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {//退出
				System.exit(0);
				
			}
		});
		//让按钮随按钮上的图案变化
		quit.setMargin(new Insets(0,0,0,0));
		//JButton去掉按钮的边框的设置
		quit.setBorderPainted(false);
		quit.setBounds(720, 310, 120, 63);
		this.add(quit);
		//复选框
		String[] degree={"简单","普通","困难"}; 
		final JComboBox box=new JComboBox(degree);
		box.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int index=box.getSelectedIndex();
				//设置相应难度的下落速度与分数倍率
				if(index==0){
					dto.setChoose(500);
					dto.setRate(10);
					box.setEnabled(false);
					start.setEnabled(true);
				}else if(index==1){
					dto.setChoose(350);
					dto.setRate(20);
					box.setEnabled(false);
					start.setEnabled(true);
				}else{
					dto.setChoose(150);
					dto.setRate(50);
					box.setEnabled(false);
					start.setEnabled(true);
				}
				
			}
		});
		box.setBounds(625, 490, 200, 30);
		//失去焦点
		box.setFocusable(false);
		this.add(box);
		//对话框
		this.add(jOptionPan);
		//设置焦点
		this.requestFocus();
	}
	public List<Layer> getLayers() {
		return layers;
	}
	public void setLayers(List<Layer> layers) {
		this.layers = layers;
	}
	public JOptionPane getjOptionPan() {
		return jOptionPan;
	}
	public PlayerCotrol getPlayerCotrol() {
		return playerCotrol;
	}
}