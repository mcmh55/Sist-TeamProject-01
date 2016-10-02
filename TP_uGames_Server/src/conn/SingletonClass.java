package conn;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

import dto.drawDTO;
import dto.horseInfoDTO;
import dto.memberDTO;

/*
 * singleton
 */

public class SingletonClass {
private static SingletonClass single = null;

	// 회원정보
	public memberDTO mdto;
	
	// horseGame
	public boolean horseRun;
	public int horseRound;
	public int horseBatCount;
	public int horseWinner;
	public String[] horseNameList;
	public String[] horseList;
	public int[] horsePysical1;
	public int[] horsePysical2;
	public double[] horseDividendRate;
	public int[] horseMove;
	public ArrayList<horseInfoDTO> horseInfoList;
	public int[][] bat;
	
	// drawGame
	public Vector<drawDTO> vc;	
	public boolean drawRun;
	public int drawRound;
	public String drawProblem;
	//public int drawIndex;
	public Socket drawSocket;
	
	private SingletonClass() {		
		mdto = new memberDTO();
		
		this.horseRun = false;
		this.horseRound = 1;
		this.horseBatCount = 0;
		this.horseWinner = -1;
		this.horseNameList = new String[7];
		this.horseList = new String[7];
		this.horsePysical1 = new int[7];
		this.horsePysical2 = new int[7];
		this.horseDividendRate = new double[7];
		this.horseMove = new int[7];
		this.horseInfoList = new ArrayList<horseInfoDTO>();
		this.bat = new int[50][7];
		
		this.vc = new Vector<drawDTO>();		
		this.drawRun = false;
		this.drawProblem = "";
		//this.drawIndex = 0;
		this.drawSocket = null;
	}
	
	public static SingletonClass getInstance(){
		if(single == null){
			single = new SingletonClass();
		}
		return single;
	}	

}
