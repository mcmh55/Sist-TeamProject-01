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
	
	// �� �߰�: �Է��� ���ڸ� ����
	public WriterClass(String msg) {
		this.msg = msg;
		this.emo = msg;
	}
	
	//------------------------------------------------------------------------------------------------------------
	// login ����
	
	//�α��� �� ���̵� ��� �˻縦 ���Ͽ� ������ �Է� ���̵�� ��� ����
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
	
	//ȸ������ �� �ߺ��˻縦 ���� �˻縦 ���Ͽ� ������ �Է� ���̵� ����
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
	
	//ȸ������ �� �ߺ��˻縦 ���� �˻縦 ���Ͽ� ������ �Է� �г��� ����
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
	
	
	//ȸ������������ DB�� �����ϱ� ���Ͽ� Ŭ���̾�Ʈ���� ������ ����
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
			System.out.println("ȸ������������ DB�� �����ϱ� ���Ͽ� Ŭ���̾�Ʈ���� ������ ����");
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	// ���������� �ε��ϱ� ���� Ŭ���̾�Ʈ���� ������ '�̸�' ����
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
	// login ����
	// �渶���� ����
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
	
	// �渶���� ����
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
	
	// ����
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
	
	// ���ñ� ��û
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
	// chat ����
	// �� �߰�: �Է��� ���ڸ� ������ ����
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
	
	// �� �߰�: ������ �̸�Ƽ���� ������ ����  �� images/�̹��� �̸�
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
	
	// �� �߰�: �Է��� ���ڸ� ������ ����
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
	
	// �� �߰�: ������ �̸�Ƽ���� ������ ����  �� images/�̹��� �̸�
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
	// draw game ����
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
