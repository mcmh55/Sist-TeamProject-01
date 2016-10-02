package dto;

import java.io.Serializable;

public class horseDTO implements Serializable{	
	private int horseRound;
	private String[] horseNameList;
	private String[] horseList;
	private int[] horseMove;
	
	public int getHorseRound() {
		return horseRound;
	}
	public void setHorseRound(int horseRound) {
		this.horseRound = horseRound;
	}
	public String[] getHorseNameList() {
		return horseNameList;
	}
	public void setHorseNameList(String[] horseNameList) {
		this.horseNameList = horseNameList;
	}
	public String[] getHorseList() {
		return horseList;
	}
	public void setHorseList(String[] horseList) {
		this.horseList = horseList;
	}
	public int[] getHorseMove() {
		return horseMove;
	}
	public void setHorseMove(int[] horseMove) {
		this.horseMove = horseMove;
	}
	
}