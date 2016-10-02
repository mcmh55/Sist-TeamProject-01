package conn;

import java.io.IOException;
import java.io.ObjectInputStream;

import awt.DrawWindow;
import awt.HorseWindow;
import awt.SelectWindow;
import dto.objectDTO;


public class ReadThread extends Thread {
	
	HorseWindow hw;
	DrawWindow dw;

	public ReadThread() {
		SingletonClass scls = SingletonClass.getInstance();
		this.hw = scls.hw;
		this.dw = scls.dw;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		ObjectInputStream ois = null;		// recv
		SingletonClass scls = SingletonClass.getInstance();	
		
		try {
			while(true){
				ois = new ObjectInputStream(scls.socket.getInputStream());
				objectDTO odto = (objectDTO)ois.readObject();
				
				// packet state에 따른 control
				switch(odto.getState()){
					//-------------------------------------------------------------------------------------------
					// login 관련
					// 100~
					case 101:
						System.out.println("상태확인 101");
						scls.mdto = odto.getMdto();
						System.out.println(scls.mdto.getId());
						
						if(!scls.mdto.getId().equals("ThereIsNoID")){
							System.out.println(scls.mdto.getId()+123);
							scls.displayMessage("환영합니다.");
							new SelectWindow();
						}else{
							scls.displayMessage("등록 되지 않은 회원입니다.");
							scls.lw.setVisible(true);
						}
						
						break;
					case 102:
						System.out.println("상태확인 102");
						scls.mdto = odto.getMdto();
						System.out.println(scls.mdto.getId());
						break;
					case 103:
						System.out.println("상태확인 103");
						scls.mdto = odto.getMdto();
						System.out.println(scls.mdto.getNickname());
						break;
					case 104:
						System.out.println("상태확인 104");
						scls.mdto = odto.getMdto();
						break;
					case 105:
						System.out.println("상태확인 105");
						scls.mdto = odto.getMdto();
						scls.idsw.getQestion();
						break;
					//-------------------------------------------------------------------------------------------
					// horseGame					
					case 400:
						// enter
						//scls.hw.setVisible(true);
						System.out.println("endter");
						hw.appendTxtA(odto.getMsg());
						hw.horseInit();
						break;
					case 401:
						// exit
						hw.appendTxtA(odto.getMsg());
						break;
					case 410:
						// setLabel
						hw.setOverwatch(odto.getMsg());
						break;
					case 411:
						// setButtonTrue
						hw.setButtonTrue();
						break;
					case 412:
						// setPoint
						scls.mdto.setPoint(odto.getMdto().getPoint());
						hw.printInfo();
						break;
					case 420:
						// init
						hw.horseInit();
						break;
					case 421:
						// horse list
						hw.horsePrintList(odto.getHdto());
						break;
					case 422:
						// horse move
						hw.horseMove(odto.getHdto());
						break;
					case 423:
						// win
						new WriterClass().sendRequest();
						scls.winner = odto.getWinner();
						hw.horseWin(scls.winner, odto.getMsg());
						break;
					case 430:
						scls.hiw.setHorse(odto.getHorseInfoList());
						break;
					case 431:
						scls.hiw.appendTa(odto.getMsg());
						break;
					case 300:
						// ★ 추가
						scls.chatWindow.printServerMsg(odto.getMdto(), odto.getMsg());
						break;
					case 301:
						// Emoticon							
						// ★ 추가
						scls.chatWindow.printServerEmo(odto.getMdto(), odto.getEmo());
						break;
					case 310:
						// 채팅 접속 인원
						System.out.println(odto.getMsg());
						scls.chatWindow.setlbconnectuser(Integer.parseInt(odto.getMsg()));
						break;
					//-------------------------------------------------------------------------------------------
					// drawGame
					case 510:
						// label
						dw.setLbl(odto.getMsg());
						break;
					case 521:
						// draw
						scls.vc = odto.getVc();
						dw.draw();
						break;
					case 512:
						// point
						scls.mdto.setPoint(odto.getMdto().getPoint());
						dw.printInfo();
						break;					
					case 522:
						// taget
						dw.setLbl(odto.getMsg());
						dw.setProblemTrue();
						break;					
					case 525:
						dw.clear();
						break;
					case 527:
						dw.setUser();
						break;
					case 550:
						scls.displayMessage(odto.getMsg()+"님이 정답을 맞췄습니다!");
						break;						
				}
			}
		
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
