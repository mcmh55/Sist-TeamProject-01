package conn;

import java.awt.TextArea;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;



public class DrawGameClass {	
	private ArrayList<Socket> drawSocketList;
	private WriterClass wc;
	
	private String id;
	private String state;
	
	public DrawGameClass(ArrayList<Socket> drawSocketList) {
		this.drawSocketList = drawSocketList;
		wc = new WriterClass(null, drawSocketList);
		
		this.id = "";
		this.state = "";
	}
	
	public void init(Socket socket){
		SingletonClass scls = SingletonClass.getInstance();
		if(!scls.drawRun){
			if(drawSocketList.size()<2){
				this.drawWait();
				scls.drawSocket = drawSocketList.get(0);
			}else{
				scls.drawRun = true;
				this.drawReady();
			}
		}else{
			this.drawJoin(socket);
		}
	}
	
	public void drawWait(){
		wc.sendDrawLabel("�����ο� : " + drawSocketList.size() + "��        " + "���� �غ� ��...");
	}
	
	public void drawReady(){
		SingletonClass scls = SingletonClass.getInstance();
		state = "ready";
		
		wc.sendDrawLabel("�����ο� : " + drawSocketList.size() + "��        " + "���� �غ���...");
		
		wc.sendDrawTaget(scls.drawSocket, "�����ο� : " + drawSocketList.size() + "��        " + "����� ���� �Դϴ�! ������ ������ �ּ���");
	}
	
	public void drawStart(String id){
		SingletonClass scls = SingletonClass.getInstance();
		state = "start";
		
		wc.sendDrawLabel("�����ο� : " + drawSocketList.size() + "��        [" + id + "] ���� �����Դϴ�.");
		wc.sendDrawLabel(scls.drawSocket, "�����ο� : " + drawSocketList.size() + "��        [" + id + "] ���� ���� �Դϴ�.     ���� : " + scls.drawProblem);
	}
	
	public void drawNextRound(String id, Socket socket){
		SingletonClass scls = SingletonClass.getInstance();
		state = "next";
		
		wc.sendDrawLabel("�����ο� : " + drawSocketList.size() + "��        [" + id + "] �� �����Դϴ�!!");
		
		scls.drawProblem = "";
				
		//wc.sendDrawUser(drawSocketList.get(scls.drawIndex));
		wc.sendClearAll();
		
		scls.drawSocket = socket;
		
		scls.drawRun = false;
		
		/*
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		this.init(null);
	}
	
	
	public void drawJoin(Socket socket){
		SingletonClass scls = SingletonClass.getInstance();
		if(this.state.equals("ready")){
			wc.sendDrawLabel("�����ο� : " + drawSocketList.size() + "��        " + "���� �غ� ��...");
			wc.sendDrawLabel(scls.drawSocket, "�����ο� : " + drawSocketList.size() + "��        " + "����� ���� �Դϴ�! ������ ������ �ּ���");
		}else if(this.state.equals("start")){
			this.drawStart(id);
		}else if(this.state.equals("next")){
			wc.sendDrawLabel("�����ο� : " + drawSocketList.size() + "��        [" + id + "] �� �����Դϴ�!!");
		}
	}
	
	public void exitHorseGame(Socket socket){
		SingletonClass scls = SingletonClass.getInstance();
		for(int i = 0; i < drawSocketList.size(); i++){
			if(drawSocketList.get(i)==socket){
				drawSocketList.remove(i);
			}
		}
		
		if(drawSocketList.size()<2){
			scls.drawRun = false;
			this.init(null);
			return;
		}
		
		if(scls.drawSocket==socket){
			scls.drawSocket = drawSocketList.get(0);
			this.drawReady();
		}
		
	}
	
}
