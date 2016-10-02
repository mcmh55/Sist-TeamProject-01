package conn;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

import dao.memberDAO;
import dto.memberDTO;
import dto.objectDTO;

public class ReadThread extends Thread {
	
	// conn
	private Socket socket;	
	private ArrayList<Socket> socketList;
	
	// member
	private String id;
	private String password;
	private String name;
	private String nickname;
	private String email;
	private String sex;
	private String recommend;
	private String question;
	private String answer;
	private int point;
	
	// horseGame
	private ArrayList<Socket> horseSocketList;
	private HorseGameClass horseGame;
	private int[][] horseDividends;
	private boolean isBat;
	
	// drawGame
	private ArrayList<Socket> drawSocketList;
	private DrawGameClass drawGame;
	
	// writer
	WriterClass wcAll;
	WriterClass wcHorse;
	WriterClass wcDraw;	
	
	// constructor
	public ReadThread(Socket socket, ArrayList<Socket> socketList, ArrayList<Socket> horseSocketList, HorseGameClass horseGame,
			ArrayList<Socket> drawSocketList, DrawGameClass drawGame) {
		this.socket = socket;
		this.socketList = socketList;
		
		this.horseSocketList = horseSocketList;
		this.horseGame = horseGame;
		this.horseDividends = new int[50][7];	// round/horse
		this.isBat = false;
		
		this.drawSocketList = drawSocketList;
		this.drawGame = drawGame;
		
		this.wcAll = new WriterClass(socket, socketList);
		this.wcHorse = new WriterClass(socket, horseSocketList);
		this.wcDraw = new WriterClass(socket, drawSocketList);	
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		// 선언			
		ObjectInputStream ois = null;		// recv
		memberDAO mdao = new memberDAO();
		
		try {
			while(true){
				try {
					ois = new ObjectInputStream(socket.getInputStream());
					objectDTO odto = (objectDTO)ois.readObject();
					SingletonClass scls = SingletonClass.getInstance();
					
					// packet state에 따른 control
					switch(odto.getState()){
						//-------------------------------------------------------------------------------------------
						// login 관련
						// 100~
						case 101:
							// System.out.println("dddd");							
							this.id = odto.getMdto().getId();
							this.password = odto.getMdto().getPassword();
							System.out.println("확인 요청받은 ID 와 PW = " + this.id + "+" + this.password);
							wcAll.sendInfo(mdao.login(id, password));
							this.point = scls.mdto.getPoint();
							System.out.println(point);
							break;
						case 102:
							// System.out.println("회원가입 아이디 중복체크");
							this.id = odto.getMdto().getId();
							System.out.println("확인 요청받은 ID = " + this.id);
							wcAll.sendID_DoubleCheck(mdao.idsame(id));
							break;
						case 103:
							// System.out.println("회원가입 닉네임 중복체크");
							this.nickname = odto.getMdto().getNickname();
							System.out.println("확인 요청받은 닉네임 = " + this.nickname);
							wcAll.sendNick_DoubleCheck(mdao.nicknamesame(nickname));
							break;
						case 104:
							// System.out.println("회원가입 정보를 DB에 저장");
							this.id = odto.getMdto().getId();
							this.password = odto.getMdto().getPassword();
							this.name = odto.getMdto().getName();
							this.nickname = odto.getMdto().getNickname();
							this.email = odto.getMdto().getEmail();
							this.sex = odto.getMdto().getSex();
							this.recommend = odto.getMdto().getRecommend();
							this.question = odto.getMdto().getQuestion();
							this.answer = odto.getMdto().getAnswer();
							this.point = 10000;
	
							wcAll.AccSaveInfo(mdao.insert(id, password, name, nickname, email, sex, recommend, question, answer, point));
							break;
						case 105:
							// System.out.println("가입질문 로딩을 위한 이름을 전송 받음");
							this.name = odto.getMdto().getName();
							System.out.println("확인 요청받은 이름 = " + this.name);
							memberDTO mdto = new memberDTO();
							mdto = mdao.idesearch_question(name);
							wcAll.QAInfo(mdto);
							
							//wcAll.QAInfo(mdao.idesearch_question(name));
							break;
						//-------------------------------------------------------------------------------------------
						// horseGame						
						case 400:
							// endter horsegame
							horseSocketList.add(socket);
							wcHorse.sendEnterHorseGame(this.id);
							wcHorse.sendHorsePoint(socket, point);
							wcHorse.sendLbconnectuser(Integer.toString(horseSocketList.size()));
							horseGame.init(socket);
							System.out.println(point);
							break;
						case 401:
							// exit horsegame
							for(int i = 0; i < horseSocketList.size(); i++){
								if(horseSocketList.get(i)==socket){
									horseSocketList.remove(i);
								}
							}
							boolean batFlag = false;
							if(scls.horseRound!=0){
								for(int i = 0; i < 7; i++){
									if(horseDividends[scls.horseRound-1][i]!=0){
										batFlag = true;
									}
								}
							}
							wcHorse.sendLbconnectuser(Integer.toString(horseSocketList.size()));
							horseGame.exitHorseGame(socket, batFlag);
							break;
						case 424:
							// bat
							horseDividends[scls.horseRound-1][odto.getChoiceHorse()-1] = odto.getBat();
							
							// 총 배팅금에 더해줌
							scls.bat[scls.horseRound-1][odto.getChoiceHorse()-1] += odto.getBat();
							this.point -= odto.getBat();
							mdao.updatePoint(this.id, this.point);
							wcHorse.sendHorsePoint(socket, this.point);
							
							// 상황판
							int a = (int)(odto.getBat() * scls.horseDividendRate[odto.getChoiceHorse()-1] * Math.pow(0.9, scls.horseRound-1));
							wcHorse.sendBatResult(socket, "내가 배팅한 말 : " + scls.horseInfoList.get(odto.getChoiceHorse()-1).getHorseName() + "\n배팅금액 : " + odto.getBat() +
												"\n" + scls.horseInfoList.get(odto.getChoiceHorse()-1).getHorseName() + "가 우승시 받는 배당금 : " + a + "\n");
							
							horseGame.horseNextRound();
							break;
						case 425:
							// 배당금
							int dividends = 0;
							for(int i = 0; i < 50; i++){
								dividends += (int)(horseDividends[i][scls.horseWinner] * scls.horseDividendRate[scls.horseWinner] * Math.pow(0.9, i));								
							}
							this.point += dividends;
							mdao.updatePoint(this.id, this.point);
							//wcHorse.sendDividends(socket, this.point);
							wcHorse.sendHorsePoint(socket, this.point);
							//horseGame.horseWin();
							this.horseDividends = new int[50][7];	// round/horse
							break;
						case 430:
							// chat
							//System.out.println("채팅들어옴");
							wcHorse.sendMsg(odto.getMdto(), odto.getMsg());
							break;
						case 431:
							wcHorse.sendEmo(odto.getMdto(), odto.getEmo());
							break;
						//-------------------------------------------------------------------------------------------
						// horseGame
							/*
						// ★ 추가: 채팅, 이모티콘 받기
						case 300:
							// chat
							System.out.println("채팅들어옴");
							new WriterClass(socket, socketList).sendMsg(odto.getMdto(), odto.getMsg());
							break;
						case 301:
							new WriterClass(socket, socketList).sendEmo(odto.getMdto(), odto.getEmo());
							break;
							*/
						// drawGame
						case 500:
							// endter drawgame
							drawSocketList.add(socket);
							wcDraw.sendDrawPoint(socket, this.point);
							wcDraw.sendLbconnectuser(Integer.toString(drawSocketList.size()));
							drawGame.init(socket);
							//wcDraw.sendEnterHorseGame(id);
							//wcHorse.sendPoint(socket, point);
							//horseGame.init(socket);
							//System.out.println(point);
							break;
						case 501:
							// exit
							//drawSocketList.remove(socket);
							wcDraw.sendLbconnectuser(Integer.toString(drawSocketList.size()));
							drawGame.exitHorseGame(socket);
							break;
						case 521:
							// list
							scls.vc = odto.getVc();
							wcDraw.sendList(socket);
							break;
						case 523:
							// problem
							scls.drawProblem = odto.getMsg();
							drawGame.drawStart(id);
							break;
						case 525:
							// clear
							wcDraw.sendClear(socket);
							scls.vc = null;
							break;
						case 530:
							// chat							
							if(odto.getMsg().equals(scls.drawProblem) && !scls.drawProblem.equals("") && this.socket!=scls.drawSocket){
								wcDraw.sendDrawUser(scls.drawSocket);
								wcDraw.sendClearAll();
								scls.vc = null;
								this.point += 1000;
								wcDraw.sendDrawPoint(socket, this.point);
								wcDraw.sendCorrect(this.id);
								drawGame.drawNextRound(id, socket);
								mdao.updatePoint(id, this.point);
							}
							wcDraw.sendMsg(odto.getMdto(), odto.getMsg());
							
							break;
						case 531:
							// emo
							wcDraw.sendEmo(odto.getMdto(), odto.getEmo());
							break;
						
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			System.out.println(socket.getInetAddress() + " 접속이 끊어졌습니다");
			//new WriterClass(null, socketList).sendExitHorseGame(id);
		} finally {
			// horse socket list
			for(int i = 0; i < horseSocketList.size(); i++){
				if(horseSocketList.get(i)==socket){
					horseSocketList.remove(i);
				}
			}
			
			SingletonClass scls = SingletonClass.getInstance();
			boolean batFlag = false;
			if(scls.horseRound!=0){
				for(int i = 0; i < 7; i++){
					if(horseDividends[scls.horseRound-1][i]!=0){
						batFlag = true;
					}
				}
			}
			
			horseGame.exitHorseGame(socket, batFlag);
			
			if(!horseSocketList.isEmpty()){
				wcHorse.sendExitHorseGame(socket, id);
			}
			
			// draw socket list
			for(int i = 0; i < drawSocketList.size(); i++){
				if(drawSocketList.get(i) == socket){
					drawGame.exitHorseGame(socket);
				}
			}
			
			// server socket list
			for(int i = 0; i < socketList.size(); i++){
				if(socketList.get(i) == this.socket){
					socketList.remove(i);
				}
			}
			
			
		}
		
	}
}
