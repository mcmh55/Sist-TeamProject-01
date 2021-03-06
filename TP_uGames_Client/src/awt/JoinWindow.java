package awt;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import conn.SingletonClass;
import conn.WriterClass;

public class JoinWindow extends Frame implements ActionListener, WindowListener,ItemListener {
	//기본 판넬
	JPanel pan = new JPanel();
	
	//메인경마 이미지
	JLabel mainimage;
	BufferedImage inputimage;
	
	//라벨
	Label lbid;
	Label lbidnotice;
	Label lbpw;
	Label lbname;
	Label lbpwnotice;
	Label lbnickname;
	Label lbemail;
	Label lbsex;
	Label lbrecommend;
	Label lbquestion;
	Label lbanswer;
	
	//Text field
	TextField tfid;
	TextField tfpw;
	TextField tfname;
	TextField tfnickname;
	TextField tfemail;
	TextField tfrecommend;
	TextField tfanswer;
	
	//checkbox - 성별 사용
	CheckboxGroup sex = new CheckboxGroup();
	Checkbox man = new Checkbox("남자",sex,true);
	Checkbox woman = new Checkbox("여자", sex, false);
	String chsex = "남자";
	
	//choice - 질문 사용
	Choice ch = new Choice();
	String chquection = "나의 보물 1호는?";
	
	//버튼
	JButton b_idcheck;
	JButton b_nicknamecheck;
	JButton b_search;
	JButton b_join;
	
	boolean idcheck = true;
	boolean nicknamecheck = true;
	boolean recommendcheck = true;
	
	SingletonClass scls;
	
	public JoinWindow() throws IOException{
		super("회원가입");
		setTitle("회원가입");
		
		pan.setLayout(new GridBagLayout());
		pan.setBackground(new Color(50,50,50));
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		
		/*
		inputimage = ImageIO.read(new File("images/mainLabel.png"));
		mainimage = new JLabel(new ImageIcon(inputimage),JLabel.CENTER);
		c.insets = new Insets(0, 75, 0, 20);
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 3;
		pan.add(mainimage, c);
		*/
		
		for(int i = 0;i < 6; i++){
			Panel p1 = new Panel();
			c.weightx = 1.0;	
			c.gridx = i;
			c.gridy = 1;
			c.gridwidth = 1;			
			pan.add(p1, c);
		}
		
		lbid = new Label("ID :", Label.CENTER);
		lbid.setForeground(Color.white);
		c.insets = new Insets(10, 0, 0, 0);
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		pan.add(lbid, c);
			
		tfid = new TextField();
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 3;
		pan.add(tfid, c);
		
		b_idcheck = new JButton("ID 중복확인");
		b_idcheck.setBackground(new Color(255, 200, 65));
		c.insets = new Insets(10, 10, 0, 10);
		c.gridx = 4;
		c.gridy = 2;
		c.gridwidth = 2;
		pan.add(b_idcheck, c);
		
		lbpw = new Label("비밀번호 :", Label.CENTER);
		lbpw.setForeground(Color.white);
		c.insets = new Insets(10, 0, 0, 0);
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		pan.add(lbpw, c);
		
		tfpw = new TextField();
		tfpw.setEchoChar('*');
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 3;
		pan.add(tfpw, c);
		
		lbname = new Label("이름 :", Label.CENTER);
		lbname.setForeground(Color.white);
		c.insets = new Insets(10, 0, 0, 0);
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 1;
		pan.add(lbname, c);
		
		tfname = new TextField();
		c.gridx = 1;
		c.gridy = 4;
		c.gridwidth = 3;
		pan.add(tfname, c);
		
		lbnickname = new Label("별명 :", Label.CENTER);
		lbnickname.setForeground(Color.white);
		c.insets = new Insets(10, 0, 0, 0);
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 1;
		pan.add(lbnickname, c);
		
		tfnickname = new TextField();
		c.gridx = 1;
		c.gridy = 5;
		c.gridwidth = 3;
		pan.add(tfnickname, c);
		
		b_nicknamecheck = new JButton("별명 중복확인");
		b_nicknamecheck.setBackground(new Color(255, 200, 65));
		c.insets = new Insets(10, 10, 0, 10);
		c.gridx = 4;
		c.gridy = 5;
		c.gridwidth = 2;
		pan.add(b_nicknamecheck, c);
		
		lbemail = new Label("e-mail :", Label.CENTER);
		lbemail.setForeground(Color.white);
		c.insets = new Insets(10, 0, 0, 0);
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 1;
		pan.add(lbemail, c);
		
		tfemail = new TextField();
		c.gridx = 1;
		c.gridy = 6;
		c.gridwidth = 3;
		pan.add(tfemail, c);
		
		lbsex = new Label("성별 :", Label.CENTER);
		lbsex.setForeground(Color.white);
		c.insets = new Insets(10, 0, 0, 0);
		c.gridx = 0;
		c.gridy = 7;
		c.gridwidth = 1;
		pan.add(lbsex, c);
		
		//성별 checkbox
		man.setForeground(Color.white);
		c.gridx = 1;
		c.gridy = 7;
		pan.add(man, c);
		
		woman.setForeground(Color.white);
		c.gridx = 2;
		c.gridy = 7;
		pan.add(woman, c);
		
		lbrecommend = new Label("추천인 :", Label.CENTER);
		lbrecommend.setForeground(Color.white);
		c.insets = new Insets(10, 0, 0, 0);
		c.gridx = 0;
		c.gridy = 8;
		c.gridwidth = 1;
		pan.add(lbrecommend, c);
		
		tfrecommend = new TextField();
		c.gridx = 1;
		c.gridy = 8;
		c.gridwidth = 3;
		pan.add(tfrecommend, c);
		
		b_search = new JButton("회원 검색");
		b_search.setBackground(new Color(255, 200, 65));
		c.insets = new Insets(10, 10, 0, 10);
		c.gridx = 4;
		c.gridy = 8;
		c.gridwidth = 2;
		pan.add(b_search, c);
		
		lbquestion = new Label("질문 :",Label.CENTER);
		lbquestion.setForeground(Color.white);
		c.insets = new Insets(10, 0, 0, 0);
		c.gridx = 0;
		c.gridy = 9;
		c.gridwidth = 1;
		pan.add(lbquestion, c);
		
		//Choice
		ch.add("나의 보물 1호는?");
		ch.add("나의 출신 초등학교는?");
		ch.add("나의 출신 고향은?");
		ch.add("나의 이상형은?");
		ch.add("아버지 성함은?");
		ch.add("어머니 성함은?");
		ch.add("가장 좋아하는 색깔은?");
		c.gridx = 1;
		c.gridy = 9;
		c.gridwidth = 3;
		pan.add(ch, c);
		
		lbanswer = new Label("답 :", Label.CENTER);
		lbanswer.setForeground(Color.white);
		c.insets = new Insets(10, 0, 0, 0);
		c.gridx = 0;
		c.gridy = 10;
		c.gridwidth = 1;
		pan.add(lbanswer, c);
		
		tfanswer = new TextField();
		c.gridx = 1;
		c.gridy = 10;
		c.gridwidth = 3;
		pan.add(tfanswer, c);
		
		b_join = new JButton("회원가입");
		b_join.setBackground(new Color(255, 200, 65));
		c.insets = new Insets(10, 0, 0, 10);
		c.gridx = 1;
		c.gridy = 11;
		c.gridwidth = 4;
		pan.add(b_join, c);
		
		setBounds(700, 200, 500, 500);
		setVisible(true);
		setResizable(false);
		
		b_idcheck.addActionListener(this);
		b_nicknamecheck.addActionListener(this);
		b_search.addActionListener(this);
		b_join.addActionListener(this);
		
		add(pan);
		addWindowListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		SingletonClass scls = SingletonClass.getInstance();
		
		//회원가입
		
		//id 중복 체크
	
		if(obj == b_idcheck){			
			WriterClass wc = new WriterClass();
		
			if(tfid.getText().equals("")){
				scls.displayMessage("아이디를 입력해 주세요.");
			}else if(tfid.getText().length() < 4 || tfid.getText().length() > 12 || tfid.getText().matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*")){
				scls.displayMessage("최소 4자 ~ 12자 이상 영어 사용가능합니다.");
			}else{
				
				wc.doubleIdCheck(tfid.getText());

				System.out.println("아이디 중복 방지용 id 서버 전송");
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if(scls.mdto.getId().equals("IdDoesNotExist")){
					scls.displayMessage("사용가능한 ID입니다.");
					this.idcheck = false;
				}else{
					System.out.println(scls.mdto.getId());
					scls.displayMessage("이미 존재하는 ID입니다. 다시 입력하세요.");
				}
			}
		}
		
		//별명 중복 체크
		else if(obj == b_nicknamecheck){		
			
			WriterClass wc = new WriterClass();
		
			if(tfnickname.getText().equals("")){
				scls.displayMessage("별명을 입력해 주세요");				
			}else{
				
				wc.doubleNickCheck(tfnickname.getText());

				System.out.println("닉네임 중복 방지용 닉네임 서버 전송");
				
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if(scls.mdto.getNickname().equals("NickDoesNotExist")){
					scls.displayMessage("사용가능한 별명입니다.");
					this.nicknamecheck = false;

				}else{
					scls.displayMessage("이미 존재하는 별명입니다. 다시 입력하세요.");
				}
			}	
				
		}
			
		//추천인 회원 찾기
		else if(obj == b_search){
		
			WriterClass wc = new WriterClass();
		
			
			wc.doubleIdCheck(tfrecommend.getText());
	
			System.out.println("추천인 확인용 id 서버 전송");
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if(scls.mdto.getId().equals("IdDoesNotExist")){
				scls.displayMessage("해당 회원이 존재하지 않습니다.");
	
			}else{
				System.out.println(scls.mdto.getId());
				scls.displayMessage("추천회원이 확인되었습니다.");
			}
	
		}		
		//빈칸 처리
		else if(obj == b_join){
			if(tfpw.getText().equals("")){
				scls.displayMessage("빈칸을 채워주세요.");
			}else if(tfname.getText().equals("")){
				scls.displayMessage("빈칸을 채워주세요.");
			}else if(tfemail.getText().equals("")){
				scls.displayMessage("빈칸을 채워주세요.");
			}else if(tfanswer.getText().equals("")){
				scls.displayMessage("빈칸을 채워주세요.");
			}
			if(tfpw.getText().length() < 8 || tfpw.getText().length() > 20 || tfpw.getText().equals("") || !(tfpw.getText().contains("!"))
					&& !(tfpw.getText().contains("@")) && !(tfpw.getText().contains("#"))&& !(tfpw.getText().contains("~"))&&!(tfpw.getText().contains("*"))){
				scls.displayMessage("최소 8~20자, 특수 기호(!, @, #, ~, *)포함 해야 합니다.");
			}else{
				if(idcheck == false && nicknamecheck == false){
					WriterClass wc = new WriterClass();
					wc.accountSave(tfid.getText(), tfpw.getText(), tfname.getText(),
								tfnickname.getText(), tfemail.getText(), chsex, 
								tfrecommend.getText(), chquection, tfanswer.getText());
					
					System.out.println("회원가입 정보를 서버로 전송하기 위하여 Writer클래스로 전송");
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					if(scls.mdto.getId().equals("CreationFailed")){
						scls.displayMessage("계정생성에 실패하였습니다.");

					}else{
						scls.displayMessage("회원가입을 축하합니다.");
						scls.lw.setVisible(true);
						dispose();
					}
				}else{
					scls.displayMessage("중복확인을 해주세요.");
				}
			}
		}
	}
		

	@Override
	public void itemStateChanged(ItemEvent e) {
		//선택한 질문 String으로 받기
		if(ch == e.getSource()){
			chquection = ch.getSelectedItem();
			return;
		}
		//선택한 성별 String으로 받기
		Checkbox ck1  = (Checkbox)e.getSource();
		ck1.getLabel();
			if(ck1.getLabel().equals("남자") || ck1.getLabel().equals("여자")){
				chsex = ck1.getLabel();
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
		SingletonClass scls = SingletonClass.getInstance();
		scls.lw.setVisible(true);
		this.dispose();
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
