package org.tysf.tetris.entity;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.tysf.tetris.dto.GameDto;

/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
public class SingleCell {//泡泡类
	//方块坐标
	private Point[] points;
	//方块颜色类型
	private int type;
	//存放方块形状的列表
	private static List<Point[]> pointsList;
	static{
		pointsList=new ArrayList<>();
		pointsList.add(new Point[]{//I
				new Point(3,0),
				new Point(4,0),
				new Point(5,0),
				new Point(6,0)
		});
		pointsList.add(new Point[]{//J
				new Point(3,0),
				new Point(4,0),
				new Point(5,0),
				new Point(5,1)
		});
		pointsList.add(new Point[]{//L
				new Point(3,0),
				new Point(4,0),
				new Point(5,0),
				new Point(3,1)
		});
		pointsList.add(new Point[]{//O
				new Point(3,0),
				new Point(4,0),
				new Point(3,1),
				new Point(4,1)
		});
		pointsList.add(new Point[]{//S
				new Point(4,0),
				new Point(5,0),
				new Point(3,1),
				new Point(4,1)
		});
		pointsList.add(new Point[]{//T
				new Point(3,0),
				new Point(4,0),
				new Point(5,0),
				new Point(4,1)
		});
		pointsList.add(new Point[]{//Z
				new Point(3,0),
				new Point(4,0),
				new Point(4,1),
				new Point(5,1)
		});
	}
	public SingleCell() {
		//初始化一开始的方块
		initCell(new Random().nextInt(7));
	}
	public void initCell(int type){//初始化方块坐标
		this.type=type;
		points=new Point[pointsList.get(type).length];
		for(int i=0;i<points.length;i++){
			points[i]=new Point(pointsList.get(type)[i].x,pointsList.get(type)[i].y);
		}
	}
	public Point[] getPoints() {
		return points;
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public boolean moveCell(int x,int y,boolean[][]map){
		//判断各个球能否移动
		for(int i=0;i<points.length;i++){
			int newX= points[i].x+x;
			int newY= points[i].y+y;
			if(isOverRange(newX, newY,map)){//如果有 一个球不能移动就返回
				return true;
			}
		}
		//移动球
		for(int i=0;i<points.length;i++){
			int newX= points[i].x+x;
			int newY= points[i].y+y;
			points[i].x=newX;
			points[i].y=newY;
		}
		return false;
	}
	/**
	 * 二维笛卡尔坐标系公式,
     * 假设点(x,y)绕(x0,y0)逆时针旋转a角后变成(x',y'),则(这里顺时针旋转90度，绕第而个球cell旋转)
     * x'-x0=(x-x0)cosa-(y-y0)sina
     * y'-y0=(x-x0)sina+(y-y0)cosa
	 */
	public void revolve(boolean[][] map){
/*		if(type==3){//若类型为O就不能旋转
			return;
		}*/
		//判断各个球能否旋转
		for(int i=0;i<points.length;i++){
			int newX=points[1].y+points[1].x-points[i].y;
			int newY=points[1].y-points[1].x+points[i].x;
			if(isOverRange(newX, newY,map)||type==3){//如果有 一个球不能移动就返回
				return ;
			}
		}
		//旋转球		
		for(int i=0;i<points.length;i++){
		int newX=points[1].y+points[1].x-points[i].y;
		int newY=points[1].y-points[1].x+points[i].x;
		points[i].x=newX;
		points[i].y=newY;
		}
	}
	//判断是否越界
	public boolean isOverRange(int x,int y,boolean[][] map){
		return x<0||x>9||y<0||y>17||map[x][y];
	}
	//消行，计分
	public void decline(boolean[][] map,GameDto dto){
		int c=0;
		for(int y=0;y<18;y++){
			for(int x=0;x<10;x++){
				if(map[x][y]){
					c++;
					if(c==10){
						for(int a=y;a>0;a--){
							for(int b=0;b<10;b++){
								map[b][a]=map[b][a-1];
							}
						}
						dto.setRemoveLine(dto.getRemoveLine()+1);
						dto.setScore(dto.getScore()+dto.getRemoveLine()*dto.getRate());
					}
				}
			}
			c=0;
		}
	}
	//判断是否死亡
	public boolean isOver(boolean[][] map,Point[] points){
		for(int i=0;i<points.length;i++){
			int newX=points[i].x;
			int newY=points[i].y;
			if(map[newX][newY]){//如果 一个球的位置为true则游戏结束
				return false;
			}
	}
		return true;
	
}}
