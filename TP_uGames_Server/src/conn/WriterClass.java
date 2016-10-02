package conn;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import dto.horseDTO;
import dto.memberDTO;
import dto.objectDTO;

public class WriterClass {
	private Socket socket;
	private ArrayList<Socket> socketList;
		
	public WriterClass(Socket socket, ArrayList<Socket> socketList) {
		this.socket = socket;
		this.socketList = socketList;
	}
	
	
	//---------------------------------------------------------------------------------------------------
	// login
	public void sendInfo(memberDTO mdto){		
		ObjectOutputStream oos = null;

			Socket sock = this.socket;
			try {
				oos = new ObjectOutputStream(sock.getOutputStream());
				System.out.println(mdto.getId());
				if (mdto.getId().equals("ThereIsNoID")) {
					mdto.setId("ThereIsNoID");
					String msg = "[System] : " + mdto.getId() + "님 서버 정보전송";
					System.out.println(msg);
					
					
					objectDTO odto = new objectDTO();
					odto.setState(101);
					odto.setMsg(msg);
					odto.setMdto(mdto);
					oos.writeObject(odto);
				}else{
					String msg = "[System] : " + mdto.getId() + "님 서버 정보전송";
					objectDTO odto = new objectDTO();
					odto.setState(101);
					odto.setMsg(msg);
					odto.setMdto(mdto);
					oos.writeObject(odto);
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
	}
	
	
	public void sendID_DoubleCheck(memberDTO mdto){//가입 아이디 중복 조회 결과 전송		
		ObjectOutputStream oos = null;
		Socket sock = this.socket;

			try {
				oos = new ObjectOutputStream(sock.getOutputStream());
				System.out.println(mdto.getId());
				if (mdto.getId().equals("IdDoesNotExist")) {
					String msg = "[System] : " + mdto.getId() + "님 서버 정보전송(중복아님)";
					
					objectDTO odto = new objectDTO();
					odto.setState(102);
					odto.setMsg(msg);
					odto.setMdto(mdto);
					oos.writeObject(odto);
					System.out.println(msg);

				}else{
					String msg = "[System] : " + mdto.getId() + " 서버 정보전송(ID존재)";
					objectDTO odto = new objectDTO();
					odto.setState(102);
					odto.setMsg(msg);
					odto.setMdto(mdto);
					oos.writeObject(odto);
					System.out.println(msg);

				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
	}
	
	
	public void sendNick_DoubleCheck(memberDTO mdto){//가입 닉네임 중복 조회 결과 전송		
		ObjectOutputStream oos = null;
		Socket sock = this.socket;

			try {
				oos = new ObjectOutputStream(sock.getOutputStream());
				System.out.println(mdto.getNickname());
				if (mdto.getNickname().equals("NickDoesNotExist")) {
					String msg = "[System] : " + mdto.getNickname() + " 서버 정보전송(중복아님)";
					objectDTO odto = new objectDTO();
					odto.setState(103);
					odto.setMsg(msg);
					odto.setMdto(mdto);
					oos.writeObject(odto);
					System.out.println(msg);

				}else{
					String msg = "[System] : " + mdto.getNickname() + " 서버 정보전송(Nickname존재)";
					objectDTO odto = new objectDTO();
					odto.setState(102);
					odto.setMsg(msg);
					odto.setMdto(mdto);
					oos.writeObject(odto);
					System.out.println(msg);

				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
	}
	
	
	public void AccSaveInfo(memberDTO mdto){//계정정보 저장의 성공유무를 서버로부터 클라이언트에 전송	
		ObjectOutputStream oos = null;

			Socket sock = this.socket;
			try {
				oos = new ObjectOutputStream(sock.getOutputStream());
				System.out.println(mdto.getId());
				if (mdto.getId().equals("CreationFailed") && mdto.getPassword().equals("CreationFailed")) {
					mdto.setId("CreationFailed");
					String msg = "[System] : " + mdto.getId();
					System.out.println(msg);
					
					objectDTO odto = new objectDTO();
					odto.setState(104);
					odto.setMsg(msg);
					odto.setMdto(mdto);
					oos.writeObject(odto);
				}else{
					String msg = "[System] : " + mdto.getId() + "님 서버 정보전송";
					objectDTO odto = new objectDTO();
					odto.setState(104);
					odto.setMsg(msg);
					odto.setMdto(mdto);
					oos.writeObject(odto);
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
	}
	
	public void QAInfo(memberDTO mdto){//ID,PW, 가입질문과 답변을 서버로부터 클라이언트에 전송	
		ObjectOutputStream oos = null;

		Socket sock = this.socket;
		try {
			oos = new ObjectOutputStream(sock.getOutputStream());
			System.out.println(mdto.getId());

				String msg = "[System] : " + mdto.getId() + "의 ID,PW, 가입질문과 답변 전송";
				System.out.println(msg);
				objectDTO odto = new objectDTO();
				odto.setState(105);
				odto.setMsg(msg);
				odto.setMdto(mdto);
				oos.writeObject(odto);


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	
	//---------------------------------------------------------------------------------------------------
	// horseGame
	
	// 경마게임 입장	
	public void sendEnterHorseGame(String id){
		System.out.println("enter");
		ObjectOutputStream oos = null;
		for(int i = 0; i < socketList.size(); i++){
			Socket sock = socketList.get(i);
			try {
				oos = new ObjectOutputStream(sock.getOutputStream());
				
				String msg = "[System] : " + id + "님 입장하셨습니다";
				objectDTO odto = new objectDTO();
				odto.setState(400);
				odto.setMsg(msg);
				
				oos.writeObject(odto);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
	}
	
	// 경마게임 퇴장
	public void sendExitHorseGame(Socket socket, String id){
		ObjectOutputStream oos = null;
		for(int i = 0; i < socketList.size(); i++){
			Socket sock = socketList.get(i);			
			try {
				if(sock!=socket){
					oos = new ObjectOutputStream(sock.getOutputStream());
					
					String msg = "[System] : " + id + "님 퇴장하셨습니다";
					objectDTO odto = new objectDTO();
					odto.setState(401);
					odto.setMsg(msg);
					
					oos.writeObject(odto);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
	}
	
	
	// sendLabel
	public void sendHorseLabel(String msg){
		ObjectOutputStream oos = null;
		for(int i = 0; i < socketList.size(); i++){
			Socket sock = socketList.get(i);
			try {
				oos = new ObjectOutputStream(sock.getOutputStream());
				objectDTO odto = new objectDTO();
				odto.setState(410);
				odto.setMsg(msg);
				
				oos.writeObject(odto);				
			} catch (SocketException e) {
				
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}			
		}	
	}
	
	// buttonTrue
	public void sendHorseButtonTrue(){
		ObjectOutputStream oos = null;
		for(int i = 0; i < socketList.size(); i++){
			Socket sock = socketList.get(i);
			try {
				oos = new ObjectOutputStream(sock.getOutputStream());
				objectDTO odto = new objectDTO();
				odto.setState(411);
				
				oos.writeObject(odto);				
			} catch (SocketException e) {
				
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
		}
	}
	
	// buttonTrue(join 시)
	public void sendHorseButtonTrue(Socket socket){
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
			objectDTO odto = new objectDTO();
			odto.setState(411);
			
			oos.writeObject(odto);				
		} catch (SocketException e) {
			
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}
	
	// send point
	public void sendHorsePoint(Socket socket, int point){
		ObjectOutputStream oos = null;
		
		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
			objectDTO odto = new objectDTO();
			odto.setState(412);
			memberDTO mdto = new memberDTO();
			mdto.setPoint(point);
			odto.setMdto(mdto);
			
			oos.writeObject(odto);				
		} catch (SocketException e) {
			
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}
	
	// horse init
	public void sendHorseInit(){
		SingletonClass scls = SingletonClass.getInstance();
		ObjectOutputStream oos = null;
		for(int i = 0; i < socketList.size(); i++){
			Socket sock = socketList.get(i);
			try {
				oos = new ObjectOutputStream(sock.getOutputStream());
				objectDTO odto = new objectDTO();
				odto.setState(420);		
				
				oos.writeObject(odto);				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	// horse list
	public void sendHorseList(horseDTO hdto){
		SingletonClass scls = SingletonClass.getInstance();
		ObjectOutputStream oos = null;
		for(int i = 0; i < socketList.size(); i++){
			Socket sock = socketList.get(i);
			try {
				oos = new ObjectOutputStream(sock.getOutputStream());				
				objectDTO odto = new objectDTO();
				odto.setState(421);
				odto.setHdto(hdto);
				
				oos.writeObject(odto);				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	// horse move
	public void sendHorseMove(horseDTO hdto){
		SingletonClass scls = SingletonClass.getInstance();
		ObjectOutputStream oos = null;
		for(int i = 0; i < socketList.size(); i++){
			Socket sock = socketList.get(i);
			try {
				oos = new ObjectOutputStream(sock.getOutputStream());				
				objectDTO odto = new objectDTO();
				odto.setState(422);
				odto.setHdto(hdto);
				
				oos.writeObject(odto);				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	// sendWin
	public void sendHorseWin(int winner){
		SingletonClass scls = SingletonClass.getInstance();
		ObjectOutputStream oos = null;
		for(int i = 0; i < socketList.size(); i++){
			Socket sock = socketList.get(i);
			try {
				oos = new ObjectOutputStream(sock.getOutputStream());				
				objectDTO odto = new objectDTO();
				odto.setState(423);
				odto.setWinner(winner);
				odto.setMsg(scls.horseNameList[winner]);
				
				oos.writeObject(odto);				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
	}
	
	public void sendHorseInfo(){
		SingletonClass scls = SingletonClass.getInstance();
		ObjectOutputStream oos = null;
		for(int i = 0; i < socketList.size(); i++){
			Socket sock = socketList.get(i);
			try {
				oos = new ObjectOutputStream(sock.getOutputStream());				
				objectDTO odto = new objectDTO();
				odto.setState(430);
				odto.setHorseInfoList(scls.horseInfoList);
				
				oos.writeObject(odto);				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void sendBatResult(Socket socket, String result){
		SingletonClass scls = SingletonClass.getInstance();
		ObjectOutputStream oos = null;
		for(int i = 0; i < socketList.size(); i++){
			Socket sock = socketList.get(i);
			try {
				if(sock == socket){
					oos = new ObjectOutputStream(sock.getOutputStream());				
					objectDTO odto = new objectDTO();
					odto.setState(431);
					odto.setMsg(result);
					
					oos.writeObject(odto);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void sendResult(String result){
		SingletonClass scls = SingletonClass.getInstance();
		ObjectOutputStream oos = null;
		for(int i = 0; i < socketList.size(); i++){
			Socket sock = socketList.get(i);
			try {
				oos = new ObjectOutputStream(sock.getOutputStream());				
				objectDTO odto = new objectDTO();
				odto.setState(431);
				odto.setMsg(result);
				
				oos.writeObject(odto);				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//---------------------------------------------------------------------------------------------------
	// chat
	// ★ 추가
	public void sendMsg(memberDTO mdto, String msg){		
		SingletonClass scls = SingletonClass.getInstance();
		ObjectOutputStream oos = null;
		for(int i = 0; i < socketList.size(); i++){
			Socket sock = socketList.get(i);
			try {
				if(sock != this.socket){
					oos = new ObjectOutputStream(sock.getOutputStream());
					System.out.println(msg);
					objectDTO odto = new objectDTO();
					odto.setState(300);
					odto.setMsg(msg);
					odto.setMdto(mdto);
					
					oos.writeObject(odto);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}		
	}
	//
	
	// ★ 추가
	public void sendEmo(memberDTO mdto, String emo) {
		SingletonClass scls = SingletonClass.getInstance();
		ObjectOutputStream oos = null;
		for(int i = 0; i < socketList.size(); i++){
			Socket sock = socketList.get(i);
			try {
				if(sock != this.socket){
					oos = new ObjectOutputStream(sock.getOutputStream());
					objectDTO odto = new objectDTO();
					odto.setState(301);
					odto.setEmo(emo);
					odto.setMdto(mdto);
					
					oos.writeObject(odto);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}	
	}
	
	
	
	//--------------------------------------------------------------------
	// drawGame
	// 그림게임 입장
	/*
	public void sendEnterDrawGame(String id){
		System.out.println("enter");
		ObjectOutputStream oos = null;
		for(int i = 0; i < socketList.size(); i++){
			Socket sock = socketList.get(i);
			try {
				oos = new ObjectOutputStream(sock.getOutputStream());
				
				String msg = "[System] : " + id + "님 입장하셨습니다";
				objectDTO odto = new objectDTO();
				odto.setState(400);
				odto.setMsg(msg);
				
				oos.writeObject(odto);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
	}
	*/
	
	/*
	// 경마게임 퇴장
	public void sendExitHorseGame(Socket socket, String id){
		ObjectOutputStream oos = null;
		for(int i = 0; i < socketList.size(); i++){
			Socket sock = socketList.get(i);			
			try {
				if(sock!=socket){
					oos = new ObjectOutputStream(sock.getOutputStream());
					
					String msg = "[System] : " + id + "님 퇴장하셨습니다";
					objectDTO odto = new objectDTO();
					odto.setState(401);
					odto.setMsg(msg);
					
					oos.writeObject(odto);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
	}
	*/
	
	// send point
	public void sendDrawPoint(Socket socket, int point){
		ObjectOutputStream oos = null;
		
		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
			objectDTO odto = new objectDTO();
			odto.setState(512);
			memberDTO mdto = new memberDTO();
			mdto.setPoint(point);
			odto.setMdto(mdto);
			
			oos.writeObject(odto);				
		} catch (SocketException e) {
			
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}
	
	// send correct!
	public void sendCorrect(String id){
		SingletonClass scls = SingletonClass.getInstance();
		ObjectOutputStream oos = null;
		for(int i = 0; i < socketList.size(); i++){
			Socket sock = socketList.get(i);
			try {
				oos = new ObjectOutputStream(sock.getOutputStream());
				objectDTO odto = new objectDTO();
				odto.setState(550);
				odto.setMsg(id);			
				oos.writeObject(odto);				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void sendList(Socket socket){
		SingletonClass scls = SingletonClass.getInstance();
		ObjectOutputStream oos = null;
		for(int i = 0; i < socketList.size(); i++){
			Socket sock = socketList.get(i);
			try {
				if(sock != socket){
					oos = new ObjectOutputStream(sock.getOutputStream());
					
					objectDTO odto = new objectDTO();
					odto.setState(521);
					odto.setVc(scls.vc);
					
					oos.writeObject(odto);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
	
	public void sendDrawLabel(String msg){
		ObjectOutputStream oos = null;
		for(int i = 0; i < socketList.size(); i++){
			Socket sock = socketList.get(i);			
			try {
				oos = new ObjectOutputStream(sock.getOutputStream());
				
				objectDTO odto = new objectDTO();
				odto.setState(510);
				odto.setMsg(msg);
				
				oos.writeObject(odto);
			} catch (IOException e) {
			}
		}
	}
	
	public void sendDrawLabel(Socket socket, String msg){
		ObjectOutputStream oos = null;
		for(int i = 0; i < socketList.size(); i++){
			Socket sock = socketList.get(i);			
			try {
				if(sock == socket){
					oos = new ObjectOutputStream(socket.getOutputStream());
					
					objectDTO odto = new objectDTO();
					odto.setState(510);
					odto.setMsg(msg);
					
					oos.writeObject(odto);
				}
			} catch (IOException e) {
			}
		}
	}
	
	public void sendDrawTaget(Socket socket, String msg){
		ObjectOutputStream oos = null;
		for(int i = 0; i < socketList.size(); i++){
			Socket sock = socketList.get(i);
			try {
				if(sock == socket){
					oos = new ObjectOutputStream(sock.getOutputStream());
					
					objectDTO odto = new objectDTO();
					odto.setState(522);
					odto.setMsg(msg);
					
					oos.writeObject(odto);
				}
			} catch (IOException e) {
			}
		}
	}
	
	public void sendDrawUser(Socket socket){
		ObjectOutputStream oos = null;
		for(int i = 0; i < socketList.size(); i++){
			Socket sock = socketList.get(i);
			try {
				if(sock == socket){
					oos = new ObjectOutputStream(sock.getOutputStream());
					
					objectDTO odto = new objectDTO();
					odto.setState(527);
					
					oos.writeObject(odto);
				}
			} catch (IOException e) {
			}
		}
	}
	
	
	public void sendClear(Socket socket){
		ObjectOutputStream oos = null;
		for(int i = 0; i < socketList.size(); i++){
			Socket sock = socketList.get(i);
			try {
				SingletonClass scls = SingletonClass.getInstance();
				if(sock != socket){
					oos = new ObjectOutputStream(sock.getOutputStream());
					
					objectDTO odto = new objectDTO();
					odto.setState(525);
					
					oos.writeObject(odto);
				}
			} catch (IOException e) {
			}
		}
	}
	
	public void sendClearAll(){
		ObjectOutputStream oos = null;
		for(int i = 0; i < socketList.size(); i++){
			Socket sock = socketList.get(i);
			try {
				oos = new ObjectOutputStream(sock.getOutputStream());
				
				objectDTO odto = new objectDTO();
				odto.setState(525);
				
				oos.writeObject(odto);
			} catch (IOException e) {
			}
		}
	}
	
	public void sendLbconnectuser(String msg){
		ObjectOutputStream oos = null;
		for(int i = 0; i < socketList.size(); i++){
			Socket sock = socketList.get(i);
			try {
				oos = new ObjectOutputStream(sock.getOutputStream());				
				objectDTO odto = new objectDTO();
				odto.setState(310);
				odto.setMsg(msg);
				
				oos.writeObject(odto);
			} catch (IOException e) {
			}
		}
	}
	
	
		
		
}