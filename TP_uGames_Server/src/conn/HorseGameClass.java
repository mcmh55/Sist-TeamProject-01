package conn;

import java.net.Socket;
import java.util.ArrayList;

import dao.horseInfoDAO;
import dto.horseDTO;
import dto.horseInfoDTO;

public class HorseGameClass {
	
	private ArrayList<Socket> horseSocketList;
	private WriterClass wc;
	private int[] move;
	private int[] moveLength;
	private boolean running;
	
	public HorseGameClass(ArrayList<Socket> horseSocketList) {
		this.horseSocketList = horseSocketList;
		this.wc = new WriterClass(null, horseSocketList);
		
		this.move = new int[7];
		this.moveLength = new int[7];
		this.running = false;
	}
	
	public void init(Socket socket){
		SingletonClass scls = SingletonClass.getInstance();		
		if(!scls.horseRun){
			scls.horseRun = true;
			scls.horseRound = 0;
			this.move = new int[7];
			scls.horseInfoList.clear();
			this.horseNextRound();
			//this.horseWait();
		}else{
			this.horseJoin(socket);
		}
	}
	
	
	
	public void horseWait(){
		//wc.sendSystemMsg("플레이어 대기중...");
		//wc.sendLabel("현재인원 : " + horseSocketList.size() + "명        " + "플레이어 대기중...");
		try {
			wc.sendHorseLabel("현재인원 : " + horseSocketList.size() + "명        " + "잠시 후 게임이 시작됩니다");
			Thread.sleep(3000);
			wc.sendHorseLabel("현재인원 : " + horseSocketList.size() + "명        " + "3초 후 게임이 시작됩니다");
			Thread.sleep(1000);
			wc.sendHorseLabel("현재인원 : " + horseSocketList.size() + "명        " + "2초 후 게임이 시작됩니다");
			Thread.sleep(1000);
			wc.sendHorseLabel("현재인원 : " + horseSocketList.size() + "명        " + "1초 후 게임이 시작됩니다");
			Thread.sleep(1000);
			this.horseNextRound();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void horseReady(){
		SingletonClass scls = SingletonClass.getInstance();
		scls.horseRun = true;
		
		// round 0로 셋팅
		scls.horseRound = 1;
		wc.sendHorseLabel("현재인원 : " + horseSocketList.size() + "명        " + "1라운드 준비중...      (" + scls.horseBatCount + "/" + horseSocketList.size() + ") 배팅 완료");
		
		
		// horse 선택
		boolean[] flag = new boolean[13];
		for(int i = 0; i < flag.length; i++){
			flag[i] = false;
		}
		
		horseInfoDAO hidao = new horseInfoDAO();
		int w = 0;
		while(w<7){
			int i = (int)(Math.random()*13);
			if(!flag[i]){
				horseInfoDTO hidto = hidao.search(i);
				scls.horseNameList[w] = hidto.getHorseName();
				scls.horseList[w] = hidto.getHorsePath();
				scls.horsePysical1[w] = hidto.getPhysical1();
				scls.horsePysical2[w] = hidto.getPhysical2();
				scls.horseDividendRate[w] = hidto.getHorseDividendRate();
				flag[i] = true;
				System.out.println("1번마" + scls.horseList[w]);
				scls.horseInfoList.add(hidto);
				w++;
			}		
		}
		
		// horseList
		horseDTO hdto = new horseDTO();
		hdto.setHorseNameList(scls.horseNameList);
		hdto.setHorseList(scls.horseList);
		wc.sendHorseList(hdto);
		
		
		// horseMove 0으로 셋팅
		for(int i = 0; i < scls.horseMove.length; i++){
			scls.horseMove[i] = 0;
			move[i] = 0;
		}
		
		// horseMove
		hdto.setHorseRound(scls.horseRound);
		hdto.setHorseMove(scls.horseMove);		
		wc.sendHorseMove(hdto);
		
		// horseButton
		wc.sendHorseButtonTrue();
		
		// 상황판 말정보		
		wc.sendHorseInfo();
	}
	
	public void horseNextRound(){
		SingletonClass scls = SingletonClass.getInstance();
		if(scls.horseRound == 0){
			this.horseReady();
		}else{
			scls.horseBatCount++;
			if(horseSocketList.size()>scls.horseBatCount){
				wc.sendHorseLabel("현재인원 : " + horseSocketList.size() + "명        " + scls.horseRound + "라운드 준비중...      (" + scls.horseBatCount + "/" + horseSocketList.size() + ") 배팅 완료");
				
				//wc.sendSystemMsg((scls.horseRound) + "라운드 준비중...      (" + all + "/" + horseSocketList.size() + ") 배팅 완료");
			}else{
				scls.horseRound++;
				this.running = true;
				
				wc.sendHorseLabel("현재인원 : " + horseSocketList.size() + "명        " + scls.horseRound + "라운드 시작!");
				
				// horseMove
				// 능력치 넣어줌
				for(int i = 0; i < scls.horseMove.length; i++){
					move[i] += (int)(Math.random()*scls.horsePysical1[i] + scls.horsePysical2[i]);
				}
				
				System.out.print(move[0] + " ");
				System.out.print(move[1] + " ");
				System.out.print(move[2] + " ");
				System.out.print(move[3] + " ");
				System.out.print(move[4] + " ");
				System.out.print(move[5] + " ");
				System.out.println(move[6] + " ");
				
				boolean[] flag = new boolean[7];
				for(int i = 0; i < flag.length; i++){
					flag[i] = false;
				}
				
				scls.horseWinner = -1;
				
				moveLength = new int[7];
				
				int w = 0;
				while(w < 7){
					// 말 움직임
					int r = (int)(Math.random()*7);				
					if(scls.horseMove[r]<move[r]){
						scls.horseMove[r]++;
						moveLength[r]++;
						//System.out.println(r + " : " + scls.horseMove[r]);
						if(scls.horseMove[r]>19){
							System.out.println("ddddd");
							scls.horseWinner = r;
							scls.horseMove[r] = 0;
							/*
							scls.horseWinner = r;							
							wc.sendWin(scls.horseWinner);
							wc.sendLabel("다음 게임 준비중...");
							try {
								Thread.sleep(5000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							scls.horseRun = false;
							this.running = false;
							scls.horseBatCount = 0;
							wc.sendInit();
							*/
							break;
						}else{
							horseDTO hdto = new horseDTO();
							hdto.setHorseRound(scls.horseRound);
							hdto.setHorseMove(scls.horseMove);
							
							
							wc.sendHorseMove(hdto);						
							try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}else if(scls.horseMove[r]>move[r]){
						scls.horseMove[r]--;
						moveLength[r]++;
						System.out.println(r + " : " + scls.horseMove[r]);
						if(scls.horseMove[r]<0){
							scls.horseMove[r] = 0;
							move[r] = 0;
						}
						horseDTO hdto = new horseDTO();
						hdto.setHorseRound(scls.horseRound);
						hdto.setHorseMove(scls.horseMove);
														
						wc.sendHorseMove(hdto);						
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else{
						flag[r] = true;
					}
					
					w = 0;				
					for(int i = 0; i < flag.length; i++){
						if(flag[i]){
							w++;
						}
					}
				}
				
				for(int i = 0; i < move.length; i++){
					if(move[i]<0){
						move[i] = 0;
					}
				}
				
				this.running = false;
				
				horseDTO hdto = new horseDTO();
				hdto.setHorseRound(scls.horseRound);
				hdto.setHorseMove(scls.horseMove);			
				wc.sendHorseMove(hdto);
				
				String result = (scls.horseRound-1) + "Round 결과\n";
				for(int i = 0; i < 7; i++){
					result += (i+1) + ". " + scls.horseInfoList.get(i).getHorseName() + "\t이동 거리 : " + moveLength[i] + "\t총 배팅금 : " + scls.bat[scls.horseRound-2][i] + "\n";
				}
				
				wc.sendResult(result);
				
				if(scls.horseWinner!=-1){
					
					wc.sendHorseWin(scls.horseWinner);
					wc.sendHorseLabel("다음 게임 준비중...");					
					
					scls.horseBatCount = 0;
					scls.horseRound = 0;
					scls.horseRun = false;
					scls.horseMove = new int[7];
					this.move = new int[7];
					this.init(null);
					return;
				}else{
					scls.horseBatCount = 0;
					wc.sendHorseLabel("현재인원 : " + horseSocketList.size() + "명        " + scls.horseRound + "라운드 종료       배팅해주세요");
					wc.sendHorseButtonTrue();
				}
			}
		}
	}
	
	
	public void horseJoin(Socket socket){
		SingletonClass scls = SingletonClass.getInstance();
		if(scls.horseRound == 0){
			return;
		}
		
		/*
		if(scls.horseRound == 1){
			horseDTO hdto = new horseDTO();
			hdto.setHorseList(scls.horseList);
			hdto.setHorseMove(scls.horseMove);
			hdto.setHorseRound(scls.horseRound);
			wc.sendHorseList(hdto);
			wc.sendHorseMove(hdto);
			wc.sendbuttonTrue();
			scls.horseBatCount--;
			this.horseNextRound();
		}
		*/
		
		if(this.running){
			wc.sendHorseLabel("현재인원 : " + horseSocketList.size() + "명        " + scls.horseRound + "라운드 시작!");
			
			horseDTO hdto = new horseDTO();
			hdto.setHorseRound(scls.horseRound);
			hdto.setHorseNameList(scls.horseNameList);
			hdto.setHorseList(scls.horseList);
			wc.sendHorseList(hdto);
			// 상황판 말정보		
			wc.sendHorseInfo();
		}else{
			//wc.sendLabel("현재인원 : " + horseSocketList.size() + "명        " + scls.horseRound + "라운드 준비중...      (" + scls.horseBatCount + "/" + horseSocketList.size() + ") 배팅 완료");
			
			horseDTO hdto = new horseDTO();
			hdto.setHorseRound(scls.horseRound);
			hdto.setHorseNameList(scls.horseNameList);
			hdto.setHorseList(scls.horseList);
			hdto.setHorseMove(scls.horseMove);
			wc.sendHorseList(hdto);
			wc.sendHorseMove(hdto);
			wc.sendHorseButtonTrue(socket);
			// 상황판 말정보		
			wc.sendHorseInfo();
			scls.horseBatCount--;
			this.horseNextRound();
		}
		/*
		SingletonClass scls = SingletonClass.getInstance();
		horseDTO hdto = new horseDTO();
		hdto.setHorseRound(scls.horseRound);
		hdto.setHorseList(scls.horseList);
		hdto.setHorseMove(scls.horseMove);
		
		wc.sendHorseList(hdto);
		scls.horseBatCount--;
		this.horseNextRound();
		*/		
		
	}
	
	public void exitHorseGame(Socket socket, boolean batFlag){		
		SingletonClass scls = SingletonClass.getInstance();
		if(horseSocketList.isEmpty()){
			scls.horseRun = false;
			scls.horseRound = 0;
			scls.horseBatCount = 0;
			scls.horseInfoList.clear();
			return;
		}
		
		if(scls.horseRound == 0){
			return;
		}		
		
		if(!this.running){
			if(batFlag){
				scls.horseBatCount--;
			}
			scls.horseBatCount--;
			this.horseNextRound();
			return;
		}else{
			
		}
		
	}

}
