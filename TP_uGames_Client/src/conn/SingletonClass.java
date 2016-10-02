package conn;

import java.io.IOException;
import java.net.Socket;
import java.util.Vector;

import awt.Chatting;
import awt.DrawWindow;
import awt.HorseInfoWindow;
import awt.HorseWindow;
import awt.IDSearchWindow;
import awt.LoginWindow;
import dao.ImageDAO;
import dto.drawDTO;
import dto.memberDTO;

/*
 * singleton
 */

public class SingletonClass {
private static SingletonClass single = null;
	
	public LoginWindow lw;
	public IDSearchWindow idsw;

	public Socket socket;
	public memberDTO mdto;
	
	public HorseWindow hw;
	public int[] current;
	public HorseInfoWindow hiw;
	
	public int winner;
	
	// chat
	public Chatting chatWindow;
	public ImageDAO idao;
	public int gameState;	// 0:horsegame 1:drawgame
	
	// draw
	public DrawWindow dw;
	public Vector<drawDTO> vc;
	
	private SingletonClass() {
		
		lw = null;
		idsw = null;
		
		socket = new Socket();
		mdto = new memberDTO();
		
		hw = new HorseWindow();
		hiw = new HorseInfoWindow();
		this.current = new int[7];
		this.winner = -1;
		
		chatWindow = new Chatting();
		idao = new ImageDAO();
		gameState = -1;
		
		try {
			dw = new DrawWindow();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		vc = new Vector<drawDTO>();
	}
	
	public static SingletonClass getInstance(){
		if(single == null){
			single = new SingletonClass();
		}
		return single;
	}
	
	public void displayMessage(String nameSupplied){
		javax.swing.JOptionPane.showMessageDialog(null, nameSupplied);
	}

}
