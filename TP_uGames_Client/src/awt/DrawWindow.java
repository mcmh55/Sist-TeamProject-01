package awt;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import conn.SingletonClass;
import conn.WriterClass;


public class DrawWindow extends JFrame implements WindowListener, ActionListener{
   /////////////////////////////////////////////////////////
   // top
   Panel top = new Panel();   //상단
   
   // 상단 생성 패널   
   Panel Tbanner = new Panel(); // 상단 배너
   Panel TPhoto = new Panel(); // 회원 아이콘
   Panel Tinfo = new Panel(); // 이름 및 포인트 
      
   // 상단 레이블
   JLabel name = new  JLabel();   //회원 이름
   JLabel point = new  JLabel();   //회원 포인트
   
   // 상단 버튼
   JButton btnBack = new JButton("나가기");
   JButton log = new JButton("로그아웃"); //로그아웃
   
   
   // 메인 패널
   
   Panel main = new Panel();
   
   
   
   //////////////////////////////////////////////////////////   
   // draw
   private Panel pnDraw = new Panel();
   private JLabel lbl = new JLabel();
   private JButton btnClear = new JButton("지우기");
   private JTextField tf = new JTextField(10);
   private JButton btnSend = new JButton("보내기");
   private DrawCanvas dc = new DrawCanvas();
   
   public DrawWindow() throws IOException {   
      lbl.setBackground(new Color(255,200,80));
      ///////////////////////////////////////////////////////////////////////////////////
      //상단 메뉴 설정--------------------------------  
      GridBagLayout gridbag = new GridBagLayout();
       GridBagConstraints gc = new GridBagConstraints();
       
       top.setBackground(new Color(80,60,60));
      
      Tbanner.setBackground(new Color(50,50,50));
      TPhoto.setBackground(new Color(50,50,50));
      Tinfo.setBackground(new Color(50,50,50));
      
      ImageIcon banner1 = new ImageIcon("image/catban.jpg");
      JLabel Lblbanner = new JLabel(banner1);      
      Tbanner.add(Lblbanner);
      
      JLabel face = new JLabel(new ImageIcon("image/profile.png"));

      btnBack.setBackground(new Color(255,200,65));
      btnBack.setBorder(BorderFactory.createLineBorder(new Color(80,60,60), 1));
      
      // 상단 레이블-----------------------------------------------      
      name.setBackground(Color.blue);
      point.setBackground(Color.gray);
      name.setForeground(Color.white);
      point.setForeground(Color.white);
        
      //80,60,60
      log.setBackground(new Color(255,200,65));
      //log.setBorder(BorderFactory.createLineBorder(new Color(80,60,60), 1));
      log.setBorderPainted(false);
      
   /*    this.setLayout(gridbag);
        gc.gridx =0; 
        gc.gridy =0; 
        gc.weightx =1; 
        gc.weighty =0; 
        gc.gridheight =1;
        gc.gridwidth = 2;
        gc.fill = GridBagConstraints.BOTH;
        gridbag.setConstraints(top, gc);
              
        gc.gridx =1;
         gc.gridy =0; 
         gc.weightx =1;
         gc.weighty =1; 
         gc.gridheight =1;
         gc.gridwidth = 3;
         gc.fill = GridBagConstraints.BOTH;
         gridbag.setConstraints(main, gc); 
         this.add(main);*/
        
        
       
      
      //-----------------------------------------------------------
       
      
      
      
   /*   
      this.setLayout(new GridBagLayout());
      gc.gridx = 0;
      gc.gridy = 0;
      gc.weightx = 1;
      gc.weighty = 0.4;
      gc.gridwidth = 1;
      gc.gridheight = 1;
      this.add(top, gc);
      
      gc.gridx = 0;
      gc.gridy = 1;
      gc.weightx = 1;
      gc.weighty = 1;
      gc.gridwidth = 1;
      gc.gridheight = 1;
      this.add(pnDraw, gc);*/
      
      
      
      //gc.fill = GridBagConstraints.BOTH;
      
      /*top.setLayout(new GridBagLayout());
      
      gc.gridx = 0; 
      gc.gridy = 0; 
      gc.weightx = 0.6; 
      gc.weighty = 0; 
      gc.gridheight = 4;
      gc.gridwidth = 1;
      top.add(Tbanner, gc);
      
      gc.gridx = 1; 
      gc.gridy = 0; 
      gc.weightx = 0.2; 
      gc.weighty = 0; 
      gc.gridheight = 2;
      gc.gridwidth = 1;
      top.add(face, gc);
      
      gc.gridx = 1; 
      gc.gridy = 2; 
      gc.weightx = 0.2; 
      gc.weighty = 0; 
      gc.gridheight = 1;
      gc.gridwidth = 1;
      top.add(btnBack, gc);
      
      name.setText("회원 id");
      gc.gridx = 2; 
      gc.gridy = 0; 
      gc.weightx = 0.2; 
      gc.weighty = 0; 
      gc.gridheight = 1;
      gc.gridwidth = 1;
      top.add(name, gc);
      
      name.setText("회원 point");
      gc.gridx = 2; 
      gc.gridy = 1; 
      gc.weightx = 0.2; 
      gc.weighty = 0; 
      gc.gridheight = 1;
      gc.gridwidth = 1;
      top.add(point, gc);
      
      gc.gridx = 2; 
      gc.gridy = 2; 
      gc.weightx = 0.2; 
      gc.weighty = 0; 
      gc.gridheight = 1;
      gc.gridwidth = 1;
      top.add(log, gc);
      
      */
      
      Tinfo.setLayout(gridbag);
      
      gc.gridx =0; 
      gc.gridy =0; 
      gc.weightx =1; 
      gc.weighty =1; 
      gc.gridheight =1;
      gc.gridwidth = 2;
      gc.fill = GridBagConstraints.BOTH;
      gridbag.setConstraints(name, gc);
      Tinfo.add(name);         
       
      gc.gridx =0; 
      gc.gridy =2; 
      gc.weightx =1; 
      gc.weighty =0.8; 
      gc.gridheight =1;
      gc.gridwidth = 1;
      gc.fill = GridBagConstraints.BOTH;
      gridbag.setConstraints(point, gc);
      Tinfo.add(point);         
      
      gc.gridx =0; 
      gc.gridy =3; 
      gc.weightx =1; 
      gc.weighty =0.03; 
      gc.gridheight =1;
      gc.gridwidth = 1;
      gc.fill = GridBagConstraints.BOTH;
      gridbag.setConstraints(log, gc);
      Tinfo.add(log);
      
      
      //--------------------------------------------------
      
      TPhoto.setLayout(gridbag);
      gc.gridx =0; 
      gc.gridy =0; 
      gc.weightx =1; 
      gc.weighty =0.9; 
      gc.gridheight =1;
      gc.gridwidth = 1;
      gc.fill = GridBagConstraints.BOTH;
      gridbag.setConstraints(face, gc);
      TPhoto.add(face);
               
      gc.gridx =0; 
      gc.gridy =2; 
      gc.weightx =1; 
      gc.weighty =0.25; 
      gc.gridheight =1;
      gc.gridwidth = 0;
      gc.fill = GridBagConstraints.BOTH;
      gridbag.setConstraints(btnBack, gc);
      TPhoto.add(btnBack);
        
      //  -------------------------------------------------
      top.setLayout(gridbag);
      gc.gridx =0; 
      gc.gridy =0; 
      gc.weightx =0.6; 
      gc.weighty =1; 
      gc.gridheight =1;
      gc.gridwidth = 3;
      gc.fill = GridBagConstraints.BOTH;
      gridbag.setConstraints(Tbanner, gc);
      top.add(Tbanner);
   
      gc.gridx =3; 
      gc.gridy =0; 
      gc.weightx =0.2; 
      gc.weighty =1; 
      gc.gridheight =1;
      gc.gridwidth = 1;
      gc.fill = GridBagConstraints.BOTH;
      gridbag.setConstraints(TPhoto, gc);
      top.add(TPhoto);
         
      gc.gridx =4; 
      gc.gridy =0; 
      gc.weightx =0.2; 
      gc.weighty =1; 
      gc.gridheight =1;
      gc.gridwidth = 1;
      gc.fill = GridBagConstraints.BOTH;
      gridbag.setConstraints(Tinfo, gc);
      top.add(Tinfo);
      
         
   
      // 상단 레이블-----------------------------------------------      
      name.setBackground(Color.blue);
      point.setBackground(Color.gray);
      name.setForeground(Color.white);
      point.setForeground(Color.white);
        
      //80,60,60
      log.setBackground(new Color(255,200,65));
      //log.setBorder(BorderFactory.createLineBorder(new Color(80,60,60), 1));
      log.setBorderPainted(false);
      
      /*
      Tinfo.setLayout(gridbag);
      gc.gridx =0; 
      gc.gridy =0; 
      gc.weightx =6; 
      gc.weighty =0.8; 
      gc.gridheight =1;
      gc.gridwidth = 1;
      gc.fill = GridBagConstraints.BOTH;
      gridbag.setConstraints(name, gc);
      Tinfo.add(name);         
       
      gc.gridx =0; 
      gc.gridy =1; 
      gc.weightx =1; 
      gc.weighty =0.8; 
      gc.gridheight =1;
      gc.gridwidth = 1;
      gc.fill = GridBagConstraints.BOTH;
      gridbag.setConstraints(point, gc);
      Tinfo.add(point);         
      
      gc.gridx =0; 
      gc.gridy =2; 
      gc.weightx =1; 
      gc.weighty =0.03; 
      gc.gridheight =1;
      gc.gridwidth = 1;
      gc.fill = GridBagConstraints.BOTH;
      gridbag.setConstraints(log, gc);
      Tinfo.add(log);
      */
         
      /////////////////////////////////////////////////////////////////////////////////////////////
      
      btnClear.setBackground(new Color(255,200,65));
      btnClear.setBorder(BorderFactory.createLineBorder(new Color(80,60,60), 1));
      btnSend.setBackground(new Color(255,200,65));
      btnSend.setBorder(BorderFactory.createLineBorder(new Color(80,60,60), 1));
      lbl.setBackground(new Color(255,200,80));
      // draw            
      pnDraw.setLayout(new GridBagLayout());
      
   
      
      
      lbl.setText("환영합니다");
      gc.gridx = 0;
      gc.gridy = 0;
      gc.weightx = 3;
      gc.weighty = 0;
      gc.gridwidth = 1;
      gc.gridheight = 1;
      pnDraw.add(lbl, gc);
      
      gc.gridx = 0;
      gc.gridy = 1;
      gc.weightx = 1;
      gc.weighty = 10;
      gc.gridwidth = 3;
      gc.gridheight = 1;
      pnDraw.add(dc, gc);
      
      gc.gridx = 0;
      gc.gridy = 2;
      gc.weightx = 0.4;
      gc.weighty = 0;
      gc.gridwidth = 1;
      gc.gridheight = 1;
      pnDraw.add(btnClear, gc);
      
      gc.gridx = 1;
      gc.gridy = 2;
      gc.weightx = 0.3;
      gc.weighty = 0;
      gc.gridwidth = 1;
      gc.gridheight = 1;
      pnDraw.add(tf, gc);   
      
      gc.gridx = 2;
      gc.gridy = 2;
      gc.weightx = 0.3;
      gc.weighty = 0;
      gc.gridwidth = 1;
      gc.gridheight = 1;
      pnDraw.add(btnSend, gc);   
      
      btnClear.addActionListener(this);
      btnSend.addActionListener(this);      
      
      btnClear.setEnabled(false);
      tf.setEnabled(false);
      btnSend.setEnabled(false);
      
      btnBack.addActionListener(this);
      log.addActionListener(this);
      
      // window
      this.setLayout(new GridBagLayout());
      gc.gridx = 0;
      gc.gridy = 0;
      gc.weightx = 1;
      gc.weighty = 0.1;
            
      gc.gridwidth = 1;
      gc.gridheight = 1;
      this.add(top, gc);
      
      gc.gridx = 0;
      gc.gridy = 1;
      gc.weightx = 1;
      gc.weighty = 1;
      gc.gridwidth = 1;
      gc.gridheight = 1;
      this.add(pnDraw, gc);
      
      
      setTitle("Client");
      setBounds(100,100,900,710);
      addWindowListener(this);
      setResizable(false);
      setVisible(false);
   }
	
	public void setLbl(String msg){
		lbl.setText(msg);
	}
	
	public void printInfo(){
		SingletonClass scls = SingletonClass.getInstance();
		name.setText(scls.mdto.getId()+"님");
		point.setText(scls.mdto.getPoint()+"pt");
	}
	
	public void setProblemTrue(){
		tf.setEnabled(true);
		btnSend.setEnabled(true);
	}
	
	public void setTaget(){
		dc.start();
		btnClear.setEnabled(true);
	}
	
	public void setUser(){
		dc.stop();
		btnClear.setEnabled(false);
	}
	
	public void clear(){
		dc.clear(dc.getGraphics());
	}
	
	public void draw(){
		dc.repaint();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		SingletonClass scls = SingletonClass.getInstance();
		if(e.getSource() == btnClear){
			dc.clear(dc.getGraphics());
			new WriterClass().sendClear();			
		}else if(e.getSource() == btnSend){
			if(tf.getText().equals("")){
				return;
			}else{
				new WriterClass().sendProblem(tf.getText());
				tf.setText("");
				tf.setEnabled(false);
				btnSend.setEnabled(false);
				
				this.setTaget();
			}
		}else if(e.getSource() == btnBack){
			new WriterClass().sendExitDrawGame();
			scls.dw.setVisible(false);
			scls.chatWindow.setVisible(false);
			new SelectWindow();
			this.dispose();
		}else if(e.getSource() == log){
			new WriterClass().sendExitDrawGame();
			scls.dw.setVisible(false);
			scls.lw.setVisible(true);
			scls.chatWindow.setVisible(false);
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