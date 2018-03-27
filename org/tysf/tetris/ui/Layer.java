package org.tysf.tetris.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import org.tysf.tetris.dto.GameDto;

public abstract class Layer {//图层类
	public  int x;
	public  int y;
	protected  int w;
	protected  int h;
	protected  GameDto dto;
	/**
	   * 经验值槽图片的高度
	   */
	  protected static final int ARC = 32;
	 /**
	   * 经验值槽图片的高度
	   */
	  protected static final int IMG_RECT_H = Img.RECT.getHeight(null);
	  /**
	   * 经验值槽图片的宽度
	   */
	  private static final int IMG_RECT_W = Img.RECT.getWidth(null);
	  /**
	   * 经验值槽的宽度
	   */
	  private final int rectW;
	  /**
	   * 值槽默认字体
	   */
	private static final Font DEF_FONT= new Font("黑体", Font.BOLD, 20);
	 /**
	  * 获取当前屏幕宽高
	  */
	private static final int windowW=Toolkit.getDefaultToolkit().getScreenSize().width;
	private static final int windowH=Toolkit.getDefaultToolkit().getScreenSize().height;
	
	public Layer(int x,int y,int w,int h){
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		this.rectW=Img.RECT.getWidth(null);
	}
	public abstract void paint(Graphics g);
	public void createWindow(Graphics g,Image  img){//在层上绘制图片
		g.drawImage(img, this.x, this.y,this.x+w,this.y+h,0,0,img.getWidth(null),img.getHeight(null),null);
	}
	public GameDto getDto() {
		return dto;
	}
	public void setDto(GameDto dto) {
		this.dto = dto;
	}
	//在窗口中间绘制图片
	public void drawImageAtCenter(Image img,int w,int h,Graphics g){
		int imgW=img.getWidth(null);
		int imgH=img.getHeight(null);
		int x=w-imgW>>1;
		int y=h-imgH>>1;
		g.drawImage(img, this.x+x, this.y+y, null);
	}
	protected void drawRect(int y ,String title,String number,double percent,Graphics g){
	    //初始化值
	    int rect_x = this.x + 16;
	    int rect_y = this.y + y;
	    //绘制背景
	    g.setColor(Color.BLACK);
	    g.fillRect(rect_x, rect_y , rectW, IMG_RECT_H + 4);
	    g.setColor(Color.WHITE);
	    g.fillRect(rect_x + 1, rect_y  + 1, rectW - 2, IMG_RECT_H + 2);
	    g.setColor(Color.BLACK);
	    g.fillRect(rect_x + 2, rect_y  + 2, rectW - 4, IMG_RECT_H);
	    int w = (int)(percent *(this.rectW - 4));//计算宽度
	    //计算颜色
	    int subIdx =(int)( percent * IMG_RECT_W) - 1;
	    g.drawImage(Img.RECT,
	           rect_x + 2,rect_y + 2,
	           rect_x +2 +w, rect_y + 2 + IMG_RECT_H,
	           subIdx, 0,subIdx+1, IMG_RECT_H, null);
	    //绘制标题
	    g.setColor(Color.WHITE);
	    g.setFont(DEF_FONT);
	    g.drawString(title, rect_x+4, rect_y + 22);
	    if(number !=null){
	       g.drawString(number, rect_x+157, rect_y + 22);
	    }
	  }
	public void deadMap(Graphics g){
		//清空游戏地图
		boolean[][] map=dto.getGameMap();
		//死亡，重新在地图上绘制结束时的方块
		for(int x=0;x<map.length;x++){
			for(int y=0;y<map[x].length;y++){
				if(map[x][y]){
					g.drawImage(Img.HUAJI, 
					this.x+x*ARC, 
					this.y+y*ARC,
					this.x+x*ARC+ARC,
					this.y+y*ARC+ARC,
					9*ARC,0,9*ARC+32,ARC,null);
				}
			}
		}
	}

}
