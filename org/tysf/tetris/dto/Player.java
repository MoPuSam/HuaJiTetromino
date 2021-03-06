package org.tysf.tetris.dto;

import java.io.Serializable;

public class Player implements Serializable,Comparable<Player>{
	private static final long serialVersionUID = 1L;
	private String name;
	private int score;
	
	public Player(String name, int score) {
		super();
		this.name = name;
		this.score = score;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	@Override
	public int compareTo(Player p) {
		return -(this.getScore()-p.getScore());
	}
	
}
