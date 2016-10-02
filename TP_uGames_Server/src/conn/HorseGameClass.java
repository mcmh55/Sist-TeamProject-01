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
		//wc.sendSystemMsg("�÷��̾� �����...");
		//wc.sendLabel("�����ο� : " + horseSocketList.size() + "��        " + "�÷��̾� �����...");
		try {
			wc.sendHorseLabel("�����ο� : " + horseSocketList.size() + "��        " + "��� �� ������ ���۵˴ϴ�");
			Thread.sleep(3000);
			wc.sendHorseLabel("�����ο� : " + horseSocketList.size() + "��        " + "3�� �� ������ ���۵˴ϴ�");
			Thread.sleep(1000);
			wc.sendHorseLabel("�����ο� : " + horseSocketList.size() + "��        " + "2�� �� ������ ���۵˴ϴ�");
			Thread.sleep(1000);
			wc.sendHorseLabel("�����ο� : " + horseSocketList.size() + "��        " + "1�� �� ������ ���۵˴ϴ�");
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
		
		// round 0�� ����
		scls.horseRound = 1;
		wc.sendHorseLabel("�����ο� : " + horseSocketList.size() + "��        " + "1���� �غ���...      (" + scls.horseBatCount + "/" + horseSocketList.size() + ") ���� �Ϸ�");
		
		
		// horse ����
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
				System.out.println("1����" + scls.horseList[w]);
				scls.horseInfoList.add(hidto);
				w++;
			}		
		}
		
		// horseList
		horseDTO hdto = new horseDTO();
		hdto.setHorseNameList(scls.horseNameList);
		hdto.setHorseList(scls.horseList);
		wc.sendHorseList(hdto);
		
		
		// horseMove 0���� ����
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
		
		// ��Ȳ�� ������		
		wc.sendHorseInfo();
	}
	
	public void horseNextRound(){
		SingletonClass scls = SingletonClass.getInstance();
		if(scls.horseRound == 0){
			this.horseReady();
		}else{
			scls.horseBatCount++;
			if(horseSocketList.size()>scls.horseBatCount){
				wc.sendHorseLabel("�����ο� : " + horseSocketList.size() + "��        " + scls.horseRound + "���� �غ���...      (" + scls.horseBatCount + "/" + horseSocketList.size() + ") ���� �Ϸ�");
				
				//wc.sendSystemMsg((scls.horseRound) + "���� �غ���...      (" + all + "/" + horseSocketList.size() + ") ���� �Ϸ�");
			}else{
				scls.horseRound++;
				this.running = true;
				
				wc.sendHorseLabel("�����ο� : " + horseSocketList.size() + "��        " + scls.horseRound + "���� ����!");
				
				// horseMove
				// �ɷ�ġ �־���
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
					// �� ������
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
							wc.sendLabel("���� ���� �غ���...");
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
				
				String result = (scls.horseRound-1) + "Round ���\n";
				for(int i = 0; i < 7; i++){
					result += (i+1) + ". " + scls.horseInfoList.get(i).getHorseName() + "\t�̵� �Ÿ� : " + moveLength[i] + "\t�� ���ñ� : " + scls.bat[scls.horseRound-2][i] + "\n";
				}
				
				wc.sendResult(result);
				
				if(scls.horseWinner!=-1){
					
					wc.sendHorseWin(scls.horseWinner);
					wc.sendHorseLabel("���� ���� �غ���...");					
					
					scls.horseBatCount = 0;
					scls.horseRound = 0;
					scls.horseRun = false;
					scls.horseMove = new int[7];
					this.move = new int[7];
					this.init(null);
					return;
				}else{
					scls.horseBatCount = 0;
					wc.sendHorseLabel("�����ο� : " + horseSocketList.size() + "��        " + scls.horseRound + "���� ����       �������ּ���");
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
			wc.sendHorseLabel("�����ο� : " + horseSocketList.size() + "��        " + scls.horseRound + "���� ����!");
			
			horseDTO hdto = new horseDTO();
			hdto.setHorseRound(scls.horseRound);
			hdto.setHorseNameList(scls.horseNameList);
			hdto.setHorseList(scls.horseList);
			wc.sendHorseList(hdto);
			// ��Ȳ�� ������		
			wc.sendHorseInfo();
		}else{
			//wc.sendLabel("�����ο� : " + horseSocketList.size() + "��        " + scls.horseRound + "���� �غ���...      (" + scls.horseBatCount + "/" + horseSocketList.size() + ") ���� �Ϸ�");
			
			horseDTO hdto = new horseDTO();
			hdto.setHorseRound(scls.horseRound);
			hdto.setHorseNameList(scls.horseNameList);
			hdto.setHorseList(scls.horseList);
			hdto.setHorseMove(scls.horseMove);
			wc.sendHorseList(hdto);
			wc.sendHorseMove(hdto);
			wc.sendHorseButtonTrue(socket);
			// ��Ȳ�� ������		
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
