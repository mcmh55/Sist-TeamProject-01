package conn;

import java.awt.TextField;
import java.io.IOException;
import java.io.ObjectOutputStream;

import dto.memberDTO;
import dto.objectDTO;

public class WriterClass {
	
	private String msg;
	private String emo;
	
	public WriterClass() {
		// TODO Auto-generated constructor stub
	}
	
	// ★ 추가: 입력한 문자를 저장
	public WriterClass(String msg) {
		this.msg = msg;
		this.emo = msg;
	}
	
	//------------------------------------------------------------------------------------------------------------
	// login 관련
	
	//로그인 시 아이디 비번 검사를 위하여 서버에 입력 아이디와 비번 전송
	// state '101'
	public void checkId(String id, String pw){
		try{
			SingletonClass scls = SingletonClass.getInstance();
			ObjectOutputStream oos = null;
			oos = new ObjectOutputStream(scls.socket.getOutputStream());
			
			objectDTO odto = new objectDTO();	
			
			memberDTO mdto = new memberDTO();
			mdto.setId(id);
			mdto.setPassword(pw);
			odto.setMdto(mdto);
			odto.setState(101);
			
			oos.writeObject(odto);			
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	//회원가입 시 중복검사를 위한 검사를 위하여 서버에 입력 아이디 전송
	// state '102'
	public void doubleIdCheck(String id){
		try{
			SingletonClass scls = SingletonClass.getInstance();
			ObjectOutputStream oos = null;
			oos = new ObjectOutputStream(scls.socket.getOutputStream());
			
			objectDTO odto = new objectDTO();	
			
			memberDTO mdto = new memberDTO();
			mdto.setId(id);
			odto.setMdto(mdto);
			odto.setState(102);
			
			oos.writeObject(odto);			
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	//회원가입 시 중복검사를 위한 검사를 위하여 서버에 입력 닉네임 전송
	// state '103'
	public void doubleNickCheck(String nickname) {
		try{
			SingletonClass scls = SingletonClass.getInstance();
			ObjectOutputStream oos = null;
			oos = new ObjectOutputStream(scls.socket.getOutputStream());
			
			objectDTO odto = new objectDTO();	
			
			memberDTO mdto = new memberDTO();
			mdto.setNickname(nickname);
			odto.setMdto(mdto);
			odto.setState(103);
			
			oos.writeObject(odto);			
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	
	//회원가입정보를 DB에 저장하기 위하여 클라이언트에서 서버로 전송
	// state '104'
	public void accountSave (String id, String password, String name, String nickname, String email, String sex, String recommend, String question, String answer) {
		try{
			SingletonClass scls = SingletonClass.getInstance();
			ObjectOutputStream oos = null;
			oos = new ObjectOutputStream(scls.socket.getOutputStream());
			
			objectDTO odto = new objectDTO();	
			
			memberDTO mdto = new memberDTO();
			
			mdto.setId(id);
			mdto.setPassword(password);
			mdto.setName(name);
			mdto.setNickname(nickname);
			mdto.setEmail(email);
			mdto.setSex(sex);
			mdto.setRecommend(recommend);
			mdto.setQuestion(question);
			mdto.setAnswer(answer);
			
			odto.setMdto(mdto);
			odto.setState(104);
			
			oos.writeObject(odto);			
			System.out.println("회원가입정보를 DB에 저장하기 위하여 클라이언트에서 서버로 전송");
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	// 가입질문을 로딩하기 위한 클라이언트에서 서버로 '이름' 전송
	// state '105'
	public void FindQestion(String name){
		try{
			SingletonClass scls = SingletonClass.getInstance();
			ObjectOutputStream oos = null;
			oos = new ObjectOutputStream(scls.socket.getOutputStream());
			
			objectDTO odto = new objectDTO();	
			
			memberDTO mdto = new memberDTO();
			mdto.setName(name);
			odto.setMdto(mdto);
			odto.setState(105);
			
			oos.writeObject(odto);			
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	//------------------------------------------------------------------------------------------------------------
	// login 관련
	// 경마게임 입장
	public void sendEnterHorseGame(){
		try{
			SingletonClass scls = SingletonClass.getInstance();
			ObjectOutputStream oos = null;
			oos = new ObjectOutputStream(scls.socket.getOutputStream());
			
			objectDTO odto = new objectDTO();
			odto.setState(400);
			
			oos.writeObject(odto);			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	// 경마게임 퇴장
	public void sendExitHorseGame(){
		try{
			SingletonClass scls = SingletonClass.getInstance();
			ObjectOutputStream oos = null;
			oos = new ObjectOutputStream(scls.socket.getOutputStream());
			
			objectDTO odto = new objectDTO();
			odto.setState(401);
			
			oos.writeObject(odto);			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	// 배팅
	public void sendBat(int bat, int choiceHorse){
		try{
			SingletonClass scls = SingletonClass.getInstance();
			ObjectOutputStream oos = null;
			oos = new ObjectOutputStream(scls.socket.getOutputStream());
			
			objectDTO odto = new objectDTO();
			odto.setState(424);
			odto.setBat(bat);
			odto.setChoiceHorse(choiceHorse);
			
			oos.writeObject(odto);			
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	// 배팅금 요청
	public void sendRequest(){
		try{
			SingletonClass scls = SingletonClass.getInstance();
			ObjectOutputStream oos = null;
			oos = new ObjectOutputStream(scls.socket.getOutputStream());
			
			objectDTO odto = new objectDTO();
			odto.setState(425);
			oos.writeObject(odto);
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
	//------------------------------------------------------------------------------------------------------------
	// chat 관련
	// ★ 추가: 입력한 문자를 서버로 전송
	public void serverSendMsgHorse() {
		try {
			SingletonClass scls = SingletonClass.getInstance();
			ObjectOutputStream oos = null;
			oos = new ObjectOutputStream(scls.socket.getOutputStream());
			
			objectDTO odto = new objectDTO();
			odto.setState(430);
			odto.setMsg(msg);
			odto.setMdto(scls.mdto);
			
			oos.writeObject(odto);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	//
	
	// ★ 추가: 선택한 이모티콘을 서버로 전송  ※ images/이미지 이름
	public void serverSendEmoHorse() {
		try {
			SingletonClass scls = SingletonClass.getInstance();
			ObjectOutputStream oos = null;
			oos = new ObjectOutputStream(scls.socket.getOutputStream());
			
			objectDTO odto = new objectDTO();
			odto.setState(431);
			odto.setMdto(scls.mdto);
			odto.setEmo(emo);
			
			oos.writeObject(odto);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// ★ 추가: 입력한 문자를 서버로 전송
	public void serverSendMsgDraw() {
		try {
			SingletonClass scls = SingletonClass.getInstance();
			ObjectOutputStream oos = null;
			oos = new ObjectOutputStream(scls.socket.getOutputStream());
			
			objectDTO odto = new objectDTO();
			odto.setState(530);
			odto.setMsg(msg);
			odto.setMdto(scls.mdto);
			
			oos.writeObject(odto);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	//
	
	// ★ 추가: 선택한 이모티콘을 서버로 전송  ※ images/이미지 이름
	public void serverSendEmoDraw() {
		try {
			SingletonClass scls = SingletonClass.getInstance();
			ObjectOutputStream oos = null;
			oos = new ObjectOutputStream(scls.socket.getOutputStream());
			
			objectDTO odto = new objectDTO();
			odto.setState(531);
			odto.setMdto(scls.mdto);
			odto.setEmo(emo);
			
			oos.writeObject(odto);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	//------------------------------------------------------------------------------------------------------------
	// draw game 관련
	// enter
	public void sendEnterDrawGame(){
		try{
			SingletonClass scls = SingletonClass.getInstance();
			ObjectOutputStream oos = null;
			oos = new ObjectOutputStream(scls.socket.getOutputStream());
			
			objectDTO odto = new objectDTO();
			odto.setState(500);
			
			oos.writeObject(odto);			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	// exit
	public void sendExitDrawGame(){
		try{
			SingletonClass scls = SingletonClass.getInstance();
			ObjectOutputStream oos = null;
			oos = new ObjectOutputStream(scls.socket.getOutputStream());
			
			objectDTO odto = new objectDTO();
			odto.setState(501);
			
			oos.writeObject(odto);			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void sendDrawList(){
		try{
			SingletonClass scls = SingletonClass.getInstance();
			ObjectOutputStream oos = null;
			oos = new ObjectOutputStream(scls.socket.getOutputStream());
			
			objectDTO odto = new objectDTO();
			odto.setState(521);
			odto.setVc(scls.vc);
			
			oos.writeObject(odto);			
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	// problem
	public void sendProblem(String problem){
		try{
			SingletonClass scls = SingletonClass.getInstance();
			ObjectOutputStream oos = null;
			oos = new ObjectOutputStream(scls.socket.getOutputStream());
			
			objectDTO odto = new objectDTO();
			odto.setState(523);
			odto.setMsg(problem);
			
			oos.writeObject(odto);			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	// clear
	public void sendClear(){
		try{
			SingletonClass scls = SingletonClass.getInstance();
			ObjectOutputStream oos = null;
			oos = new ObjectOutputStream(scls.socket.getOutputStream());
			
			objectDTO odto = new objectDTO();
			odto.setState(525);
			
			oos.writeObject(odto);			
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}

}
