package dto;

import java.io.Serializable;

public class horseInfoDTO implements Serializable{
	
	private int horseNum;
	private String horseName;
	private String horsePath;
	private int physical1;
	private int physical2;
	private double horseDividendRate;
	
		
	public int getHorseNum() {
		return horseNum;
	}
	public void setHorseNum(int horseNum) {
		this.horseNum = horseNum;
	}
	public String getHorseName() {
		return horseName;
	}
	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}
	public String getHorsePath() {
		return horsePath;
	}
	public void setHorsePath(String horsePath) {
		this.horsePath = horsePath;
	}
	public int getPhysical1() {
		return physical1;
	}
	public void setPhysical1(int physical1) {
		this.physical1 = physical1;
	}
	public double getHorseDividendRate() {
		return horseDividendRate;
	}
	public void setHorseDividendRate(double horseDividendRate) {
		this.horseDividendRate = horseDividendRate;
	}
	public int getPhysical2() {
		return physical2;
	}
	public void setPhysical2(int physical2) {
		this.physical2 = physical2;
	}	
}
