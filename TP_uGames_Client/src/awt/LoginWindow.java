package awt;

import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import conn.ReadThread;
import conn.SingletonClass;
import conn.WriterClass;


public class LoginWindow extends Frame implements WindowListener, ActionListener {
	// connect
	int timeout;
	InetSocketAddress sockAddr;
	InetAddress inetAddr;
	
	// 기본 바탕 판넬
	JPanel pan = new JPanel();
	
	// 이미지 레이블
	JLabel mainimage;
	BufferedImage inputimage;
	
	// ID/PW 입력 
	TextField tfid = new TextField();
	TextField tfpw = new TextField();
	
	// 버튼
	Button b_login = new Button("Login");
	Button b_join = new Button("회원 가입");
	Button b_search = new Button("아이디 / 비밀번호 찾기");
	
	SingletonClass scls;
	
	public LoginWindow() throws IOException{
		super("로그인");
		
		conn();
			
		pan.setLayout(new GridBagLayout());
		pan.setBackground(new Color(50,50,50));
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		
//		inputimage = ImageIO.read(new File("images/Banner_Login.gif"));
		mainimage = new JLabel(new ImageIcon("images/Banner_Login.gif"),JLabel.CENTER);
		mainimage.setBackground(new Color(80,60,61));
		c.insets = new Insets(0, 0, 0, 10);
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 3;
		pan.add(mainimage, c);
		
		for(int i = 0;i < 5; i++){
			JPanel p1 = new JPanel();
			p1.setBackground(new Color(50, 50, 50));
			c.weightx = 1.0;	
			c.gridx = i;
			c.gridy = 1;
			c.gridwidth = 1;
			pan.add(p1, c);
		}
		
		// TextField("아이디");
		c.insets = new Insets(10, 10, 0, 10);
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 3;
		pan.add(tfid, c);
		
		// TextField("비밀번호");
		c.insets = new Insets(10, 10, 0, 10);
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 3;
		tfpw.setEchoChar('*');
		pan.add(tfpw, c);
		
		// Button("Login");
		b_login.setBackground(new Color(255, 200, 65));
		c.gridx = 3;
		c.gridy = 2;
		c.gridwidth = 2;
		c.gridheight = 2;
		pan.add(b_login, c);
		
		// Button("회원가입");
		b_join.setBackground(new Color(255, 200, 65));
		c.insets = new Insets(10, 10, 0, 0);
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 2;
		c.gridheight = 1;
		pan.add(b_join, c);
		
		// Button("아이디/비밀번호 찾기");
		b_search.setBackground(new Color(255, 200, 65));
		c.insets = new Insets(10, 10, 0, 10);
		c.gridx = 2;
		c.gridy = 5;
		c.gridwidth = 3;
		c.gridheight = 1;
		pan.add(b_search, c);
		
		b_join.addActionListener(this);
		b_login.addActionListener(this);
		b_search.addActionListener(this);
		
		setBounds(750, 300, 400, 300);
		setVisible(true);
		setResizable(false);
	
		add(pan);
		addWindowListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		SingletonClass scls = SingletonClass.getInstance();
		if(obj==b_login){
			if(tfid.getText().equals("")){
				scls.displayMessage("아이디를 입력해주세요");
				return;
			}else if(tfpw.getText().equals("")){
				scls.displayMessage("비밀번호를 입력해주세요");
				return;
			}
			
		
			WriterClass wc = new WriterClass();
			wc.checkId(tfid.getText(),tfpw.getText());
			
			tfid.setText("");
			tfpw.setText("");
			
			this.dispose();
			
			
			/*
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			*/
			
			

		}else if(obj==b_join){
			try {
				new JoinWindow();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			tfid.setText("");
			tfpw.setText("");
			scls.lw.setVisible(false);
		}else if(obj==b_search){
			try {
				scls.idsw = new IDSearchWindow();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			tfid.setText("");
			tfpw.setText("");
			scls.lw.setVisible(false);
		}
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
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
	
	public void conn() throws IOException{
		SingletonClass scls = SingletonClass.getInstance();
		timeout = 5000;		
		sockAddr = new InetSocketAddress("localhost", 9001);
		scls.socket.connect(sockAddr, timeout);
				
		//new ReadThread(lblQuestion, btnClear, taClient, dc).start();
		new ReadThread().start();
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//new WriterClass(taClient, tfClient).sendEnter();
	}

}
