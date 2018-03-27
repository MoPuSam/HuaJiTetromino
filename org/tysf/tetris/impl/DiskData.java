package org.tysf.tetris.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.tysf.tetris.dao.GameData;
import org.tysf.tetris.dto.Player;

public class DiskData implements GameData{
	private static final String FILE_PATH="player.bat";
	@Override
	public List<Player> loadData(){//读取.bat中的数据
		ObjectInputStream ois=null;
		try {
			ois=new ObjectInputStream(new FileInputStream(new File(FILE_PATH)));
			List<Player> players=(List<Player>) ois.readObject();
			return players;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(ois!=null){
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
		
	}

	@Override
	public void saveData(List<Player> player) {//保存数据到.bat
		List<Player> players=this.loadData();
		ObjectOutputStream oos=null;
		try {
			oos=new ObjectOutputStream(new FileOutputStream(new File(FILE_PATH)));
			oos.writeObject(player);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(oos!=null){
				oos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		DiskData data=new DiskData();
		List<Player> players=new ArrayList<Player>();
		players.add(new Player("MPF", 7997));
		players.add(new Player("WY", 525));
		players.add(new Player("MoPuSam", 7678));
		players.add(new Player("GYQ", 3490));
		players.add(new Player("LYN", 734));
		players.add(new Player("AN", 234));
		players.add(new Player("路人甲", 567));
		players.add(new Player("路人乙", 2345));
		data.saveData(players);
	}
}
