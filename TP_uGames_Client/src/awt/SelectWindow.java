package awt;

import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import conn.SingletonClass;
import conn.WriterClass;

public class SelectWindow extends JFrame implements WindowListener, ActionListener {
	
	/*
	Button btnHorse;
	
	public SelectWindow() {
		
		
		btnHorse = new Button("경마게임 입장");
		
		this.add(btnHorse);
		btnHorse.addActionListener(this);
		
		setTitle("선택");
		setBounds(400, 300, 300, 200);
		addWindowListener(this);
		setVisible(true);
		
	}*/
	
	JPanel pnBanner = new JPanel();
	JPanel pnBottom = new JPanel();
	JPanel pnLogout = new JPanel();
	
	JLabel lnBanner1 = new JLabel();
	
	JButton btnHorse = new JButton();
	JButton btnCatch = new JButton();
	JButton btnLogout1 = new JButton();
	
	SingletonClass scls;
	
	public SelectWindow(){
		super("선택창");

		GridBagLayout gridbag = new GridBagLayout();
	    GridBagConstraints gc = new GridBagConstraints();
	     
	    ImageIcon banner2 = new ImageIcon("images/Banner_logo.gif");
		ImageIcon horbtn = new ImageIcon("images/btn_HorseRacing.png");
		ImageIcon catbtn = new ImageIcon("images/btn_CatchMind.png");
		ImageIcon logbtn = new ImageIcon("images/btn_Logouou.png");
		
		pnBanner.setBackground(new Color(250,200,65));
	    
		lnBanner1 = new JLabel(banner2);
		btnHorse = new JButton(horbtn);
		btnHorse.setFocusPainted(false);
		btnHorse.setBorderPainted(false);
		btnHorse.setBackground(new Color(50,50,50));
		btnHorse.setRolloverIcon(new ImageIcon("images/btn_HorseRacing_over.gif"));
		
		btnCatch = new JButton(catbtn );
		btnCatch.setFocusPainted(false);
		btnCatch.setBorderPainted(false);
		btnCatch.setBackground(new Color(50,50,50));
		btnCatch.setRolloverIcon(new ImageIcon("images/btn_CatchMind_over.gif"));
		
		btnLogout1 = new JButton(logbtn);
		btnLogout1.setFocusPainted(false);
		btnLogout1.setBorderPainted(false);
		btnLogout1.setBackground(new Color(80,60,60));
		btnLogout1.setRolloverIcon(new ImageIcon("images/btn_Logouou_over.png"));
		
		pnLogout.setBackground(new Color(80,60,60));


		this.setLayout(gridbag);
		gc.gridx =0;
		gc.gridy =0; 
		gc.weightx =0;
		gc.weighty =0; 
		gc.gridheight =1;
		gc.gridwidth = 2;
		gc.fill = GridBagConstraints.BOTH;
		gridbag.setConstraints(pnBanner, gc); 
		this.add(pnBanner);
		  
		gc.gridx =0;
		gc.gridy =1; 
		gc.weightx =1;
		gc.weighty =0; 
		gc.gridheight =1;
		gc.gridwidth = 1;
		gc.fill = GridBagConstraints.BOTH;
		gridbag.setConstraints(pnBottom, gc); 
		this.add(pnBottom);
		
		gc.gridx =0;
		gc.gridy =2; 
		gc.weightx =0.1;
		gc.weighty =1; 
		gc.gridheight =0;
		gc.gridwidth = 2;
		gc.fill = GridBagConstraints.BOTH;
		gridbag.setConstraints(pnLogout, gc); 
		this.add(pnLogout);
   
		pnBanner.add(lnBanner1);

		pnBottom.setLayout(new GridLayout(1,2));
		
		pnBottom.add(btnHorse);
		pnBottom.add(btnCatch);
		pnLogout.add(btnLogout1);
		
	     
	    // ---------------------------------------------------------------
	     
		btnHorse.addActionListener(this);
		btnCatch.addActionListener(this);
		btnLogout1.addActionListener(this);
	
		setBounds(750, 300, 400, 340);
		setBackground(new Color(255,255,255));
		setVisible(true);
		addWindowListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
	
		SingletonClass scls = SingletonClass.getInstance();
		if(obj == btnHorse){
			scls.gameState = 0;
			scls.hw.setVisible(true);
			scls.hw.clearTxtA();
			scls.chatWindow = new Chatting();
			scls.chatWindow.setVisible(true);
			new WriterClass().sendEnterHorseGame();			
			scls.hiw.taClear();
			this.dispose();
		}else if(obj == btnCatch){
			scls.gameState = 1;
			scls.dw.setVisible(true);
			scls.chatWindow = new Chatting();
			scls.chatWindow.setVisible(true);
			new WriterClass().sendEnterDrawGame();			
			this.dispose();
		}else if(obj == btnLogout1){
			scls.lw.setVisible(true);
			this.dispose();
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		this.dispose();

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
