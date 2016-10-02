package awt;

import java.awt.Frame;
import java.awt.TextArea;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Timer;

import conn.DrawGameClass;
import conn.HorseGameClass;
import conn.ReadThread;
import conn.WriterClass;


public class ServerWindow extends Frame implements WindowListener {

	TextArea taServer;
	
	int port;
	ServerSocket srvSock;
	ArrayList<Socket> socketList;
	ArrayList<Socket> horseSocketList;
	ArrayList<Socket> drawSocketList;
	Socket socket;
	
	HorseGameClass horseGame;
	DrawGameClass drawGame;
	
	WriterClass wcDraw;
	
	public ServerWindow() throws IOException {
		taServer = new TextArea();
		
		port = Integer.parseInt("9001");
		srvSock = new ServerSocket(port);
		socketList = new ArrayList<Socket>();
		horseSocketList = new ArrayList<Socket>();
		drawSocketList = new ArrayList<Socket>();
		
		horseGame = new HorseGameClass(horseSocketList);
		drawGame = new DrawGameClass(drawSocketList);
		
		this.add(taServer);
		
		setTitle("Server");
		setBounds(0, 0, 300, 400);
		addWindowListener(this);
		setVisible(true);
		
		while(true){
			Socket socket = srvSock.accept();			
			
			taServer.append("¿¬°á IP : " + socket.getInetAddress() + "\n");
			taServer.append("Port : " + socket.getPort() + "\n");
			
			socketList.add(socket);
			new ReadThread(socket, socketList, horseSocketList, horseGame, drawSocketList, drawGame).start();
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		System.exit(0);
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
