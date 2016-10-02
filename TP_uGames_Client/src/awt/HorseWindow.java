package awt;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import conn.SingletonClass;
import conn.WriterClass;
import dto.horseDTO;


public class HorseWindow extends Frame implements ActionListener, WindowListener {

	
	JButton horseinfo = new JButton("말 정보"); // 배팅 말 정보보기 버튼
	JButton chat = new JButton("채팅"); //채팅
	JButton bord = new JButton("상황판"); // 상황판
	JButton log = new JButton("로그아웃"); //로그아웃
	JButton btnBat = new JButton("배팅");  // 배팅
	JButton btnBack = new JButton("나가기");
	
	CheckboxGroup Choice = new CheckboxGroup(); //배팅 말선택	
	Checkbox si1 = new Checkbox("1번말", Choice, true);
    Checkbox si2 = new Checkbox("2번말", Choice, true);
    Checkbox si3 = new Checkbox("3번말", Choice, true);
    Checkbox si4 = new Checkbox("4번말", Choice, true);
    Checkbox si5 = new Checkbox("5번말", Choice, true);
    Checkbox si6 = new Checkbox("6번말", Choice, true);
    Checkbox si7 = new Checkbox("7번말", Choice, true);
	
	JScrollPane scrollPane;
	JTextArea txtA = new JTextArea(); // 상황보기 텍스트 에리어
	JTextField tfTT = new JTextField();// 포인트 배팅하는 텍스트 필드

	
	//최초 패널들-------------------------------
	
	Panel top = new Panel();  //상단
	Panel center = new Panel(); // 중단
	Panel bottom = new Panel(); // 하단

	
	
	//상단 생성 패널
	
	Panel Tbanner = new Panel(); // 상단 배너
	Panel TPhoto = new Panel(); // 회원 아이콘
	Panel Tinfo = new Panel(); // 이름 및 포인트 
	
	//상단 레이블------------------------------------
	
	JLabel name = new  JLabel();	//회원 이름
	JLabel point = new  JLabel();	//회원 포인트
	
	
	//중단 생성 패널----------------------------------
	
	JPanel Cgame = new JPanel(); // 메인 게임 패널
	Panel Chorse = new Panel(); //	왼쪽 말 프로파일 패널

	//왼쪽 7개의 말 패널----------------------------------
	
	JPanel Horse1 = new JPanel();
	JPanel Horse2 = new JPanel();
	JPanel Horse3 = new JPanel();
	JPanel Horse4 = new JPanel();
	JPanel Horse5 = new JPanel();
	JPanel Horse6 = new JPanel();
	JPanel Horse7 = new JPanel();
	
	ImageIcon h1;
	ImageIcon h2;
	ImageIcon h3;
	ImageIcon h4;
	ImageIcon h5;
	ImageIcon h6;
	ImageIcon h7;
	
	JLabel Hor1 = new JLabel();
	JLabel Hor2 = new JLabel();
	JLabel Hor3 = new JLabel();
	JLabel Hor4 = new JLabel();
	JLabel Hor5 = new JLabel();
	JLabel Hor6 = new JLabel();
	JLabel Hor7 = new JLabel();
	
	
	//메인 게임 준빈물-------------------------------------
	
	
	int[] current ;
	JLabel[][] horse	;
	
	// 하단 패널---------------------------------------

	Panel Bbet = new Panel();		// 배팅 패털
	Panel Bwatch = new Panel();		//화면 패널
	Panel Bbutton = new Panel();	//버튼 패널
	
	//하단 레이블---------------------------------------------
	
	Label overwatch = new Label();	//	상황보기 위에 부분
	Label betbox = new Label("    배 팅 ");	//체크 배팅	타이틀

	// icon
	ImageIcon Field = new ImageIcon("image/Field.jpg");
	ImageIcon ho = new ImageIcon("image/MoveHorse2.gif");
	
	
	
	public HorseWindow(){
		
		super ("경마");
		
		 GridBagLayout gridbag = new GridBagLayout();
	     GridBagConstraints gc = new GridBagConstraints();
	  
	     
	    //  최초 패널 선언 ---------------------------------------------
	     top.setBackground(new Color(80,60,60));
		center.setBackground(new Color(250,200,60));
		bottom.setBackground(Color.gray);
		bottom.setLayout(new GridLayout(1,3));
		

	    
		 this.setLayout(gridbag);
		  gc.gridx =0; 
		  gc.gridy =0; 
		  gc.weightx =1; 
		  gc.weighty =0; 
		  gc.gridheight =1;
		  gc.gridwidth = 2;
		  gc.fill = GridBagConstraints.BOTH;
		  gridbag.setConstraints(top, gc);
		  this.add(top);
		  
		  gc.gridx =0;
		  gc.gridy =1; 
		  gc.weightx =1;
		  gc.weighty =2; 
		  gc.gridheight =1;
		  gc.gridwidth = 3;
		  gc.fill = GridBagConstraints.BOTH;
		  gridbag.setConstraints(center, gc); 
		  this.add(center);
		  
		  gc.gridx =0;
		   gc.gridy =2; 
		   gc.weightx =1;
		   gc.weighty =1; 
		   gc.gridheight =1;
		   gc.gridwidth = 3;
		   gc.fill = GridBagConstraints.BOTH;
		   gridbag.setConstraints(bottom, gc); 
		   this.add(bottom);
		
		//-----------------------------------------------
	
		   
		   
		   
		 //상단 메뉴 설정--------------------------------  
		
		   Tbanner.setBackground(new Color(80,60,60));
		   TPhoto.setBackground(new Color(50,50,50));
		   Tinfo.setBackground(new Color(50,50,50));
		   ImageIcon banner1 = new ImageIcon("image/banner6.jpg");
		  JLabel Lblbanner = new JLabel(banner1);
		  
		  Tbanner.add(Lblbanner);
		  
		  
		  JLabel face = new JLabel(new ImageIcon("image/profile.png"));

		  btnBack.setBackground(new Color(255,200,65));
		  btnBack.setBorder(BorderFactory.createLineBorder(new Color(80,60,60), 1));
		  	
		  
		  
		  TPhoto.setLayout(gridbag);
		  
		 
		  
		  		   
		 
		  gc.gridx =0; 
		   gc.gridy =0; 
		   gc.weightx =1; 
		   gc.weighty =0.8; 
		   gc.gridheight =1;
		   gc.gridwidth = 1;
		   gc.fill = GridBagConstraints.BOTH;
		   gridbag.setConstraints(face, gc);
		   TPhoto.add(face );
		   
		   
		   gc.gridx =0; 
		   gc.gridy =1; 
		   gc.weightx =1; 
		   gc.weighty =0.4; 
		   gc.gridheight =1;
		   gc.gridwidth = 1;
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
		   
		   /*
		   JLabel name = new  JLabel("홍길동"+"님 ");	//회원 이름
			JLabel point = new  JLabel("100000"+"pt");	//회원 포인트
		   */
		   
		   name.setBackground(Color.blue);
		   point.setBackground(Color.gray);
		   name.setForeground(Color.white);
		   point.setForeground(Color.white);
		   
		   //80,60,60
			log.setBackground(new Color(255,200,65));
			//log.setBorder(BorderFactory.createLineBorder(new Color(80,60,60), 1));
		   log.setBorderPainted(false);
			
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
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   //--------------------------메인 게임---------------------------
		   
			  
		   //current = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			horse = new JLabel[7][20];
		   ImageIcon Field = new ImageIcon("image/Field.jpg");
		   
		   Cgame.setLayout(new GridBagLayout());
		  

			for(int i = 0; i < 7; i++){
				for(int j = 0; j < 20; j++){
					
					
			
					horse[i][j] = new JLabel(new ImageIcon("image/Field.jpg"));
				
				
				
					
					
					gc.gridx = j;
					gc.gridy = i;
					Cgame.add(horse[i][j],gc);
					
					
				}
			
			}		
			//init();
		   
		   
		   
		   
		 //-------------------------------------------------------------
		   
		 //중단 패널 작업
		   
		   
		  /* 
		   Panel Cgame = new Panel(); // 메인 게임 패널
		   Panel Chorse = new Panel(); //	왼쪽 말 프로파일 패널
		   */
		  
		   Chorse.setBackground(Color.gray);
		   Cgame.setBackground(new Color(200,179,136));
		   
		   
		   center.setLayout(gridbag);
		   gc.gridx =0; 
		   gc.gridy =0; 
		   gc.weightx =4; 
		   gc.weighty =0; 
		   gc.gridheight =1;
		   gc.gridwidth = 1;
		   gc.fill = GridBagConstraints.BOTH;
		   gridbag.setConstraints(Chorse, gc);
		   center.add(Chorse);
		  
		   
		
		   gc.gridx =1; 
		   gc.gridy =0; 
		   gc.weightx =1; 
		   gc.weighty =0; 
		   gc.gridheight =1;
		   gc.gridwidth = 1;
		   gc.fill = GridBagConstraints.BOTH;
		   gridbag.setConstraints(Cgame, gc);
		   center.add(Cgame);
		   
		   Chorse.setLayout(new GridLayout(7,1));
		   
		   Horse1.setBackground(new Color(50, 50, 50));
		   Horse2.setBackground(new Color(50, 50, 50));
		   Horse3.setBackground(new Color(50, 50, 50));
		   Horse4.setBackground(new Color(50, 50, 50));
		   Horse5.setBackground(new Color(50, 50, 50));
		   Horse6.setBackground(new Color(50, 50, 50));
		   Horse7.setBackground(new Color(50, 50, 50));		   
		   		   		   
		   Horse1.add(Hor1);
		   Horse2.add(Hor2);
		   Horse3.add(Hor3);
		   Horse4.add(Hor4);
		   Horse5.add(Hor5);
		   Horse6.add(Hor6);
		   Horse7.add(Hor7);
		  
		   Chorse.add(Horse1);
		   Chorse.add(Horse2);
		   Chorse.add(Horse3);
		   Chorse.add(Horse4);
		   Chorse.add(Horse5);
		   Chorse.add(Horse6);
		   Chorse.add(Horse7);
		   
		   Hor1.isOptimizedDrawingEnabled();
		   Hor1.setDoubleBuffered(true);
		   Hor2.isOptimizedDrawingEnabled();
		   Hor2.setDoubleBuffered(true);
		   Hor3.isOptimizedDrawingEnabled();
		   Hor3.setDoubleBuffered(true);
		   Hor4.isOptimizedDrawingEnabled();
		   Hor4.setDoubleBuffered(true);
		   Hor5.isOptimizedDrawingEnabled();
		   Hor5.setDoubleBuffered(true);
		   Hor6.isOptimizedDrawingEnabled();
		   Hor6.setDoubleBuffered(true);
		   Hor7.isOptimizedDrawingEnabled();
		   Hor7.setDoubleBuffered(true);
		   
		   Horse1.setLayout(gridbag);
	          gc.weightx =1;
	         
	         gc.fill = GridBagConstraints.BOTH;
	         gridbag.setConstraints(Hor1, gc); 
	         Horse1.add(Hor1,gc);
	         Chorse.add(Horse1);
	         
	         Horse2.setLayout(gridbag);
	         gc.fill = GridBagConstraints.BOTH;
	         gridbag.setConstraints(Hor2, gc); 
	         Horse2.add(Hor2);
	         Chorse.add(Horse2);
	         
	         
	         Horse3.setLayout(gridbag);
	         gc.fill = GridBagConstraints.BOTH;
	         gridbag.setConstraints(Hor3, gc); 
	         Horse3.add(Hor3);
	         Chorse.add(Horse3);
	         
	         
	         Horse4.setLayout(gridbag);
	         gc.fill = GridBagConstraints.BOTH;
	         gridbag.setConstraints(Hor4, gc); 
	         Horse4.add(Hor4);
	         Chorse.add(Horse4);
	         
	         
	         Horse5.setLayout(gridbag);
	         gc.fill = GridBagConstraints.BOTH;
	         gridbag.setConstraints(Hor5, gc); 
	         Horse5.add(Hor5);
	         Chorse.add(Horse5);
	         
	         Horse6.setLayout(gridbag);
	         gc.fill = GridBagConstraints.BOTH;
	         gridbag.setConstraints(Hor6, gc); 
	         Horse6.add(Hor6);
	         Chorse.add(Horse6);
	         
	         
	         Horse7.setLayout(gridbag);
	         gc.fill = GridBagConstraints.BOTH;
	         gridbag.setConstraints(Hor7, gc); 
	         Horse7.add(Hor7);
	         Chorse.add(Horse7);
		
		   

		   
		//----------------하단 패널-------------------------------------------
		   /*
			Panel Bbet = new Panel();		// 배팅 패털
			Panel Bwatch = new Panel();		//화면 패널
			Panel Bbutton = new Panel();	//버튼 패널*/
			Bbet.setBackground(new Color(80,60,60));
			Bwatch.setBackground(Color.blue);
			Bbutton.setBackground(new Color(80,60,60));
			
			
			
			
			
			bottom.add(Bbet);
			 
			bottom.add(Bwatch);
			
			bottom.add(Bbutton);
		
			
		//------------------하단 버튼 설정 ------------------------------------
			
			
			Bbutton.setLayout(new GridLayout(3,1,15,15));
			   	chat.setBackground(new Color(255,200,65));
				bord.setBackground(new Color(255,200,65));
				horseinfo.setBackground(new Color(255,200,65));
				
				chat.setBorder(BorderFactory.createLineBorder(new Color(80,60,60), 1));
				 bord.setBorder(BorderFactory.createLineBorder(new Color(80,60,60), 1)); 
			     horseinfo.setBorder(BorderFactory.createLineBorder(new Color(80,60,60), 1));
			    // b4.setBorderPainted(false);
				
		
			Bbutton.add(chat);
			Bbutton.add(bord);
			//Bbutton.add(horseinfo);
				 
		
			
		//-------------하단 텍스트 필드-------------------------------	
			txtA = new JTextArea(); 
			txtA.setForeground(Color.white);
			 txtA.setBackground(new Color(50,50,50));
			 txtA.setBorder(new LineBorder(new Color(50,50,50)));
			 scrollPane = new JScrollPane(txtA);
			 scrollPane.setBorder(new LineBorder(new Color(50,50,50)));
			
			 //overwatch = new Label("접속인원:5명                                  배팅상황   2/5");
			overwatch.setBackground(new Color(255,200,65));
			//overwatch.setBorder(BorderFactory.createLineBorder(new Color(80,60,60), 1));
			  
			
			
			
		 
			Bwatch.setLayout(gridbag);
			
			   
			gc.gridx =0; 
			gc.gridy =0; 
			gc.weightx =1; 
			gc.weighty =0; 
			gc.gridheight =1;
			gc.gridwidth = 1;
			gc.fill = GridBagConstraints.BOTH;
			gridbag.setConstraints(overwatch, gc);
			Bwatch.add(overwatch);
			
			gc.gridx =0; 
			gc.gridy =1; 
			gc.weightx =1; 
			gc.weighty =1; 
			gc.gridheight =1;
			gc.gridwidth = 1;
			gc.fill = GridBagConstraints.BOTH;
			gridbag.setConstraints(scrollPane, gc);
			Bwatch.add(scrollPane);
			
			
		//----------------------하단 배팅 첵크 부분----------------------------------	
			betbox = new Label("배팅 첵크");
			betbox.setForeground(Color.BLACK);
			Bbet.setForeground(Color.white);
			betbox.setBackground(new Color(255,200,65));
			btnBat.setBackground(new Color(255,200,65));
			btnBat.setBorderPainted(false);
			

			/*
	         Checkbox si1 = new Checkbox("1번말", Choice, true);
	         Checkbox si2 = new Checkbox("2번말", Choice, true);
	         Checkbox si3 = new Checkbox("3번말", Choice, true);
	         Checkbox si4 = new Checkbox("4번말", Choice, true);
	         Checkbox si5 = new Checkbox("5번말", Choice, true);
	         Checkbox si6 = new Checkbox("6번말", Choice, true);
	         Checkbox si7 = new Checkbox("7번말", Choice, true);
	         */
			
	         Bbet.setLayout(gridbag);
	        
	         
	         
	     		gc.gridx =0; 
			   gc.gridy =0; 
			   gc.weightx =0; 
			   gc.weighty =0; 
			   gc.gridheight =1;
			   gc.gridwidth = 3;
			   gc.fill = GridBagConstraints.BOTH;
			   gridbag.setConstraints(betbox, gc);
			   Bbet.add(betbox);
			   
			   gc.gridx =0; 
			   gc.gridy =1; 
			   gc.weightx =0.5; 
			   gc.weighty =1; 
			   gc.gridheight =1;
			   gc.gridwidth = 1;
			   gc.fill = GridBagConstraints.BOTH;
			   gridbag.setConstraints(si1, gc);
			   Bbet.add(si1);
			   
			   gc.gridx =1; 
			   gc.gridy =1; 
			   gc.weightx =0.1; 
			   gc.weighty =1; 
			   gc.gridheight =1;
			   gc.gridwidth = 1;
			   gc.fill = GridBagConstraints.BOTH;
			   gridbag.setConstraints(si2, gc);
			   Bbet.add(si2);
			   
			   gc.gridx =2; 
			   gc.gridy =1; 
			   gc.weightx =1; 
			   gc.weighty =1; 
			   gc.gridheight =1;
			   gc.gridwidth = 1;
			   gc.fill = GridBagConstraints.BOTH;
			   gridbag.setConstraints(si3, gc);
			   Bbet.add(si3);
			   
			   gc.gridx =0; 
			   gc.gridy =2; 
			   gc.weightx =1; 
			   gc.weighty =1; 
			   gc.gridheight =1;
			   gc.gridwidth = 1;
			   gc.fill = GridBagConstraints.BOTH;
			   gridbag.setConstraints(si4, gc);
			   Bbet.add(si4);
			   
			   gc.gridx =1; 
			   gc.gridy =2; 
			   gc.weightx =1; 
			   gc.weighty =1; 
			   gc.gridheight =1;
			   gc.gridwidth = 1;
			   gc.fill = GridBagConstraints.BOTH;
			   gridbag.setConstraints(si5, gc);
			   Bbet.add(si5);
			   
			   gc.gridx =2; 
			   gc.gridy =2; 
			   gc.weightx =1; 
			   gc.weighty =1; 
			   gc.gridheight =1;
			   gc.gridwidth = 1;
			   gc.fill = GridBagConstraints.BOTH;
			   gridbag.setConstraints(si6, gc);
			   Bbet.add(si6);
			   
			   gc.gridx =0; 
			   gc.gridy =3; 
			   gc.weightx =1; 
			   gc.weighty =1; 
			   gc.gridheight =1;
			   gc.gridwidth = 1;
			   gc.fill = GridBagConstraints.BOTH;
			   gridbag.setConstraints(si7, gc);
			   Bbet.add(si7);
			   
			   gc.gridx =0; 
			   gc.gridy =4; 
			   gc.weightx =1; 
			   gc.weighty =1; 
			   gc.gridheight =1;
			   gc.gridwidth = 2;
			   gc.fill = GridBagConstraints.BOTH;
			   gridbag.setConstraints(tfTT, gc);
			   Bbet.add(tfTT);
			   
			   
			   
			   gc.gridx =2; 
			   gc.gridy =4; 
			   gc.weightx =1; 
			   gc.weighty =1; 
			   gc.gridheight =1;
			   gc.gridwidth = 1;
			   gc.fill = GridBagConstraints.BOTH;
			   gridbag.setConstraints(btnBat, gc);
			   Bbet.add(btnBat);
			   
			   
			 
			
			
			

		   
		 
		
		//--------------------------------------------------
		btnBat.addActionListener(this);
		btnBat.setEnabled(false);
		
		btnBack.addActionListener(this);
		log.addActionListener(this);
		chat.addActionListener(this);
		bord.addActionListener(this);
				
		setBounds(100,100,900,710);
		setBackground(new Color(255,255,255));
		setVisible(false);
		setResizable(false);
		addWindowListener(this);
		
		
		
		
	}
	
	public void clearTxtA(){
		txtA.setText("");
	}
	
	public void appendTxtA(String msg){
		txtA.append(msg + "\n");
	}
	
	public void setOverwatch(String msg){
		overwatch.setText(msg);
	}
	
	public void setButtonTrue(){
		btnBat.setEnabled(true);
	}
	
	public void printInfo(){
		SingletonClass scls = SingletonClass.getInstance();
		name.setText(scls.mdto.getId()+"님");
		point.setText(scls.mdto.getPoint()+"pt");
	}
	
	public void horseInit(){
		/*
		this.appendTxtA(hdto.getHorseRound() + "Round 준비중...");
		*/
		
		//System.out.println(horseList[0]);
		
		h1 = new ImageIcon("image/ready.gif");
		h2 = new ImageIcon("image/ready.gif");
		h3 = new ImageIcon("image/ready.gif");
		h4 = new ImageIcon("image/ready.gif");
		h5 = new ImageIcon("image/ready.gif");
		h6 = new ImageIcon("image/ready.gif");
		h7 = new ImageIcon("image/ready.gif");
	
		
		Hor1.setIcon(h1);
		Hor2.setIcon(h2);
		Hor3.setIcon(h3);
		Hor4.setIcon(h4);
		Hor5.setIcon(h5);
		Hor6.setIcon(h6);
		Hor7.setIcon(h7);
		
		SingletonClass scls = SingletonClass.getInstance();
		for(int i = 0; i < 7; i++){
			horse[i][scls.current[i]].setIcon(Field);
			scls.current[i] = 0;
			horse[i][scls.current[i]].isOptimizedDrawingEnabled();
			horse[i][scls.current[i]].setDoubleBuffered(true);
			
		}
		
		this.printInfo();
	}
	
	public void horsePrintList(horseDTO hdto){
		String[] horseList = hdto.getHorseList();
		
		h1 = new ImageIcon(horseList[0]);
		h2 = new ImageIcon(horseList[1]);
		h3 = new ImageIcon(horseList[2]);
		h4 = new ImageIcon(horseList[3]);
		h5 = new ImageIcon(horseList[4]);
		h6 = new ImageIcon(horseList[5]);
		h7 = new ImageIcon(horseList[6]);
		
		h1.getImage().flush();
		h2.getImage().flush();
		h3.getImage().flush();
		h4.getImage().flush();
		h5.getImage().flush();
		h6.getImage().flush();
		h7.getImage().flush();
		
		
		Hor1.setIcon(h1);
		Hor2.setIcon(h2);
		Hor3.setIcon(h3);
		Hor4.setIcon(h4);
		Hor5.setIcon(h5);
		Hor6.setIcon(h6);
		Hor7.setIcon(h7);
		
		
		String[] horseNameList = hdto.getHorseNameList();
		si1.setLabel("1. " + horseNameList[0]);
		si2.setLabel("2. " + horseNameList[1]);
		si3.setLabel("3. " + horseNameList[2]);
		si4.setLabel("4. " + horseNameList[3]);
		si5.setLabel("5. " + horseNameList[4]);
		si6.setLabel("6. " + horseNameList[5]);
		si7.setLabel("7. " + horseNameList[6]);
		
	}
	
	public void horseMove(horseDTO hdto){
		SingletonClass scls = SingletonClass.getInstance();		
		int[] horseMove = hdto.getHorseMove();
		
		for(int i = 0; i < 7; i++){
			if(scls.current[i]<horseMove[i]){
				horse[i][scls.current[i]].setIcon(Field);
			}
			horse[i][scls.current[i]].setIcon(Field);
			scls.current[i] = horseMove[i];
			horse[i][scls.current[i]].setIcon(ho);
		}
	}
	
	public void horseWin(int winner, String name){
		SingletonClass scls = SingletonClass.getInstance();
		horse[winner][scls.current[winner]].setIcon(Field);		
		btnBat.setEnabled(false);
		new WriterClass().sendRequest();
		this.appendTxtA((winner+1) + "번마 [" + name + "] 우승!");
		//scls.displayMessage((winner+1) + "번마 우승!");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		SingletonClass scls = SingletonClass.getInstance();
		
		if(obj == btnBat){
			if(tfTT.getText().equals("")){
				scls.displayMessage("배팅금액을 입력하세요");
				return;
			}else if(Integer.parseInt(tfTT.getText())>scls.mdto.getPoint()){
				scls.displayMessage("포인트가 부족합니다");
				return;
			}
			new WriterClass().sendBat(Integer.parseInt(tfTT.getText()), Choice.getSelectedCheckbox().getLabel().charAt(0)-'0');
			tfTT.setText("");
			btnBat.setEnabled(false);
			this.printInfo();
		}else if(obj == btnBack){
			new WriterClass().sendExitHorseGame();
			scls.hw.setVisible(false);
			scls.chatWindow.setVisible(false);
			scls.hiw.setVisible(false);
			new SelectWindow();
			this.dispose();
		}else if(obj == log){
			new WriterClass().sendExitHorseGame();
			scls.hw.setVisible(false);
			scls.lw.setVisible(true);
			scls.hiw.setVisible(false);
			scls.chatWindow.setVisible(false);
			this.dispose();
		}else if(obj == chat){
			if(scls.chatWindow.isVisible()){
				scls.chatWindow.setVisible(false);
			}else{
				scls.chatWindow.setVisible(true);
			}
		}else if(obj == bord){
			if(scls.hiw.isVisible()){
				scls.hiw.setVisible(false);
			}else{
				scls.hiw.setVisible(true);
			}
		}
	}
	
	@Override
	public void windowActivated(WindowEvent e) {
		
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		System.exit(0);
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
