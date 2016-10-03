package awt;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.im.InputContext;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import conn.SingletonClass;
import conn.WriterClass;
import dto.memberDTO;


public class Chatting extends JFrame implements ActionListener, KeyListener, MouseListener {

	InputContext inctLanguage;
	JTextArea taInputMsg;
	JTextArea taChatText;
	
	JButton btnEmoticon;
	JButton btnChatClear;
	JButton btnMsgSend;
	
	JLabel lbconnectuser;
	JLabel lbUserName;
	JLabel lbImg;
	
	JPanel pnChatTool;
	
	JTextPane pnText;
	JScrollPane pnScroll;
	Container ct;
	
	StyleContext context;
	StyledDocument document;
	Style style;
	
	SingletonClass scls;
	
	public Chatting() {
		
		// 채팅 환경설정
		context = new StyleContext();
		document = new DefaultStyledDocument(context);
		style = context.getStyle(StyleContext.DEFAULT_STYLE);		// 문서 스타일 셋팅  ※ 기본
		
		// 레이아웃
		ct = getContentPane();
		ct.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		
		// 접속한 유저수
		lbconnectuser = new JLabel();
		lbconnectuser.setForeground(new Color(255, 255, 255));
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.insets = new Insets(5, 5, 5, 5);
		ct.add(lbconnectuser, c);
		

		///// 채팅 출력 영역
		pnText = new JTextPane(document);
		pnText.setEditable(false);						// 수정 불가
		pnText.setBackground(new Color(85, 65, 65));	// 배경 색상
		pnScroll = new JScrollPane(pnText, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pnScroll.setBorder(new LineBorder(new Color(40, 40, 40)));		// 테두리 색상
		
		// 수직 스크롤바 UI 디자인
		pnScroll.getVerticalScrollBar().setUI(new BasicScrollBarUI()
	    {   
			// 스크롤바 바(bar) 디자인
			@Override 
	        protected void configureScrollBarColors(){
	            this.thumbColor = new Color(120, 100, 100);
	        }

			// 스크롤바 배경 디자인
			@Override
			protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
				g.setColor(new Color(85, 65, 65));
				super.paintTrack(g, c, trackBounds);
			}

			// 위로 이동시키는 버튼  ※ ▲
			@Override
	        protected JButton createDecreaseButton(int orientation) {
	            return createZeroButton();
	        }
			
			// 아래로 이동시키는 버튼  ※ ▼
			@Override    
	        protected JButton createIncreaseButton(int orientation) {
	            return createZeroButton();
	        }

	        // 버튼 숨김  ※ 사이즈를 가로(0), 세로(0)으로 만듦
			private JButton createZeroButton() {
	            JButton jbutton = new JButton();
	            jbutton.setPreferredSize(new Dimension(0, 0));
	            jbutton.setMinimumSize(new Dimension(0, 0));
	            jbutton.setMaximumSize(new Dimension(0, 0));
	            return jbutton;
	        }
	    });
		
		c.gridx = 0;
		c.gridy = 1;
		c.weighty = 1.0;
		c.insets = new Insets(0, 5, 0, 5);
		ct.add(pnScroll, c);
		
		
		///// 문자 입력 영역
		// 입력칸
		taInputMsg = new JTextArea();
		taInputMsg.setLineWrap(true);		// 자동 줄바꿈
		pnScroll = new JScrollPane(taInputMsg, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pnScroll.setBorder(new LineBorder(Color.DARK_GRAY));	// 테두리 설정
		
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 0.8;
		c.weighty = 0;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		ct.add(pnScroll, c);
		
		// 첫 입력 문자를 한글로 설정  ※ Component가 생
		inctLanguage = taInputMsg.getInputContext();
		if(inctLanguage != null) {
            Character.Subset[] subset ={Character.UnicodeBlock.HANGUL_SYLLABLES};
            inctLanguage.setCharacterSubsets(subset);
		}
		
		
		// 전송 버튼
		btnMsgSend = new JButton("전송");
		btnMsgSend.setFont(new Font(null, Font.PLAIN, 12));
		btnMsgSend.setBackground(new Color(255, 200, 65));;
		btnMsgSend.setFocusPainted(false);
		btnMsgSend.setBorderPainted(false);
		btnMsgSend.setPreferredSize(new Dimension(60, 35));		// 버튼 크기 설정
//		btnMsgSend.setBorder(BorderFactory.createLineBorder(Color.BLUE)); 	// 테두리 색
		c.gridx = 1;
		c.gridy = 2;
		c.weightx = 0;
		c.insets = new Insets(5, 0, 5, 5);
		ct.add(btnMsgSend, c);
		
		
		///// 채팅 도구 영역
		pnChatTool = new JPanel();
		pnChatTool.setOpaque(false);
		pnChatTool.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
		
		// 이모티콘 버튼
		btnEmoticon = new JButton(new ImageIcon("images/icon_btn_emoticon.png"));
		btnEmoticon.setRolloverIcon(new ImageIcon("images/icon_btn_emoticon_over.png"));
		btnEmoticon.setToolTipText("이모티콘");					// 툴 팁
		btnEmoticon.setBorderPainted(false);					// 사각형 숨김
		btnEmoticon.setContentAreaFilled(false);				// 투명 하이라이트 숨김
		btnEmoticon.setPreferredSize(new Dimension(20, 20));	// 버튼 크기 설정
		pnChatTool.add(btnEmoticon);
		
		// 채팅창 지우기 버튼
		btnChatClear = new JButton(new ImageIcon("images/icon_btn_chatclear.png"));
		btnChatClear.setRolloverIcon(new ImageIcon("images/icon_btn_chatclear_over.png"));
		btnChatClear.setToolTipText("채팅창 지우기");				// 툴 팁
		btnChatClear.setBorderPainted(false);					// 사각형 숨김
		btnChatClear.setContentAreaFilled(false);				// 투명 하이라이트 숨김
		btnChatClear.setPreferredSize(new Dimension(20, 20));	// 버튼 크기 설정
		pnChatTool.add(btnChatClear);
		
		// 채팅 도구 영역 위치
		c.gridx = 0;
		c.gridy = 3;
		
		c.gridwidth = 2;
		c.insets = new Insets(5, 5, 10, 5);
		ct.add(pnChatTool, c);
		
		
		///// 환경 설정
		// 액션, 키보드, 마우스 반응 감지
		btnEmoticon.addActionListener(this);
		btnChatClear.addActionListener(this);
		btnMsgSend.addActionListener(this);
		btnMsgSend.addMouseListener(this);
		taInputMsg.addKeyListener(this);
		
		// 채팅 창 셋팅
		setBounds(1005, 100, 320, 710);
//		setUndecorated(true);			// 이동, 숨기기, 크기 변경, 닫기 불가
		getContentPane().setBackground(new Color(80, 60, 60));
		setResizable(false);
		setVisible(false);
		
		taInputMsg.requestFocus();		// 문자 입력 칸에 커서 위치
	}
	
	
	////////// 커스텀 메소드 //////////
	// 기본셋팅 초기화
	public void settingInit() {
		taInputMsg.requestFocus();							// 커서 위치를 채팅 입력 칸으로 지정
		pnText.setCaretPosition(document.getLength());		// 채팅 내역의 스크롤을 가장 아래로 이동
	}
	
	
	// 자신의 문자 출력
	private void printMsg() {		
		scls = SingletonClass.getInstance();		
		String msg = taInputMsg.getText();		// 입력한 내용 저장
		
		// 공백 상태를 확인하기 위해 공백(띄어쓰기)의 개수 체크
		int spaceCount = 0;
		for (int i = 0; i < msg.length(); i++) {
			if (msg.charAt(i) == ' ') {
				spaceCount++;
			}
		}
		
		// 공백은 전송 불가 => 입력된 문자 없음 + 입력된 문자가 모두 공백으로 되어있음
		if (!msg.equals("") && spaceCount != msg.length()) {
			try {
				// 작성자 출력
				// 성별에 따라 캐릭터 얼굴이 달라짐
				Icon iface = null;
				if (scls.mdto.getSex().equals("남자")) {
					iface = new ImageIcon("images/face_man.png");
				}
				else if(scls.mdto.getSex().equals("여자")) {
					iface = new ImageIcon("images/face_woman.png");
				}
				
				lbUserName = new JLabel(scls.mdto.getNickname() + "(" + scls.mdto.getId() + "):", iface, JLabel.LEFT);
				lbUserName.setFont(new Font(null, Font.BOLD, 11));		// Font(글꼴(null = 기본), 효과, 글자 크기)
				lbUserName.setForeground(new Color(200, 200, 200));		// 글자 색상(R, G, B)
				lbUserName.setPreferredSize(new Dimension(0, 30));		// 세로 간격(줄간격)
				
				StyleConstants.setComponent(style, lbUserName);
				document.insertString(document.getLength(), "\n", style);
				
				// 입력한 문자 출력
				taChatText = new JTextArea();
				taChatText.setLineWrap(true);		// 자동 개행 처리
				taChatText.append(msg);				// 입력한 문자를 채팅 내역에 출력
				taChatText.setFont(new Font(null, Font.PLAIN, 12));
				taChatText.setForeground(new Color(255, 255, 140));
				taChatText.setEditable(false);		// 수정 불가
				taChatText.setOpaque(false);		// 배경 투명
				
				StyleConstants.setComponent(style, taChatText);
				document.insertString(document.getLength(), "\n", style);
				
				// 서버 전송
				WriterClass wtcls = new WriterClass(msg);
				if(scls.gameState==0){
					wtcls.serverSendMsgHorse();
				}else if(scls.gameState==1){
					wtcls.serverSendMsgDraw();
				}
				
			}
			catch (BadLocationException ex) {
				ex.printStackTrace();
			}
			
			// 기본 셋팅 초기화: 커서 위치를 채팅 입력 칸으로 지정, 채팅 내역의 스크롤을 가장 아래로 이동
			settingInit();
		}
	}
	
	
	// 자신의 이모티콘 출력
	public void sendEmo(ImageIcon emoGif) {
		scls = SingletonClass.getInstance();	
		try {
			// 작성자 출력  ※ 자신의 문자 출력과 동일
			// 성별에 따라 캐릭터 얼굴이 달라짐
			Icon iface = null;
			if (scls.mdto.getSex().equals("남자")) {
				iface = new ImageIcon("images/face_man.png");
			}
			else if(scls.mdto.getSex().equals("여자")) {
				iface = new ImageIcon("images/face_woman.png");
			}
			
			lbUserName = new JLabel(scls.mdto.getNickname() + "(" + scls.mdto.getId() + "):", iface, JLabel.LEFT);
			lbUserName.setFont(new Font(null, Font.BOLD, 11));
			lbUserName.setForeground(new Color(200, 200, 200));
			lbUserName.setPreferredSize(new Dimension(0, 30));		// 세로 간격
			StyleConstants.setComponent(style, lbUserName);
			
			document.insertString(document.getLength(), "\n", style);
			
			// 이모티콘 출력
			lbImg = new JLabel(emoGif);
			StyleConstants.setComponent(style, lbImg);
			
			document.insertString(document.getLength(), "\n", style);
			
			// 서버 전송
			WriterClass wtcls = new WriterClass(emoGif.getDescription());
			if(scls.gameState==0){
				wtcls.serverSendEmoHorse();
			}else if(scls.gameState==1){
				wtcls.serverSendEmoDraw();
			}
		}
		catch (BadLocationException ex) {
			ex.printStackTrace();
		}
		
		scls = SingletonClass.getInstance();
		
		scls.idao.refreshEmoList();
		
		// 기본 셋팅 초기화: 커서 위치를 채팅 입력 칸으로 지정, 채팅 내역의 스크롤을 가장 아래로 이동
		settingInit();
	}
	
	
	// 서버에서 받은 문자 출력
	public void printServerMsg(memberDTO mdto, String msg) {
		try {
			// 작성자 출력
			// 성별에 따라 캐릭터 얼굴이 달라짐
			Icon iface = null;
			if (mdto.getSex().equals("남자")) {
				iface = new ImageIcon("images/face_man.png");
			}
			else if(mdto.getSex().equals("여자")) {
				iface = new ImageIcon("images/face_woman.png");
			}
			
			lbUserName = new JLabel(mdto.getNickname() + "(" + mdto.getId() + "):", iface, JLabel.LEFT);
			lbUserName.setFont(new Font(null, Font.PLAIN, 11));		// Font(글꼴(null = 기본), 효과, 글자 크기)
			lbUserName.setForeground(new Color(150, 150, 150));		// 글자 색상(R, G, B)
			lbUserName.setPreferredSize(new Dimension(0, 30));		// 세로 간격
			StyleConstants.setComponent(style, lbUserName);
			
			document.insertString(document.getLength(), "\n", style);
			
			// 입력한 문자 출력
			taChatText = new JTextArea();
			taChatText.setLineWrap(true);		// 자동 개행 처리
			taChatText.append(msg);				// 입력한 문자를 채팅 내역에 출력
			taChatText.setFont(new Font(null, Font.PLAIN, 12));
			taChatText.setForeground(new Color(220, 220, 220));
			taChatText.setEditable(false);		// 수정 불가
			taChatText.setOpaque(false);		// 배경 투명
			StyleConstants.setComponent(style, taChatText);
			
			document.insertString(document.getLength(), "\n", style);
			
		}
		catch (BadLocationException ex) {
			ex.printStackTrace();
		}
		
		pnText.setCaretPosition(document.getLength());		// 채팅 내역의 스크롤을 가장 아래로 이동
	}
	
	
	// 서버에서 받은 이모티콘 출력
	public void printServerEmo(memberDTO mdto, String EmoName) {
		scls = SingletonClass.getInstance();
		
		try {
			// 작성자 출력
			// 성별에 따라 캐릭터 얼굴이 달라짐
			Icon iface = null;
			if (mdto.getSex().equals("남자")) {
				iface = new ImageIcon("images/face_man.png");
			}
			else if(mdto.getSex().equals("여자")) {
				iface = new ImageIcon("images/face_woman.png");
			}
			
			lbUserName = new JLabel(mdto.getNickname() + "(" + mdto.getId() + "):", iface, JLabel.LEFT);
			lbUserName.setFont(new Font(null, Font.PLAIN, 11));		// Font(글꼴(null = 기본), 효과, 글자 크기)
			lbUserName.setForeground(new Color(150, 150, 150));		// 글자 색상(R, G, B)
			lbUserName.setPreferredSize(new Dimension(0, 30));		// 세로 간격
			StyleConstants.setComponent(style, lbUserName);
			
			document.insertString(document.getLength(), "\n", style);
			
			// 이모티콘 출력
			ImageIcon emoGif = new ImageIcon(EmoName);
			emoGif.getImage().flush();
			lbImg = new JLabel(emoGif);
			StyleConstants.setComponent(style, lbImg);
			
			document.insertString(document.getLength(), "\n", style);
			
			scls.idao.refreshEmoList();
		}
		catch (BadLocationException ex) {
			ex.printStackTrace();
		}
		
		pnText.setCaretPosition(document.getLength());		// 채팅 내역의 스크롤을 가장 아래로 이동
	}
	
	// 접속인원
	public void setlbconnectuser(int i){
		lbconnectuser.setText("접속 : " + i + "명");
	}
	
	
	
	////////// 반응 이벤트 //////////
	// 버튼 액션 이벤트
	@Override
	public void actionPerformed(ActionEvent e) {
		scls = SingletonClass.getInstance();
		
		Object objAction = e.getSource();
		
		// '이모티콘'버튼 클릭
		if (objAction.equals(btnEmoticon)) {
			new Emoticon();
		}
		
		// '지우기'버튼 클릭
		if (objAction.equals(btnChatClear)) {
			pnText.setText("");			// 채팅 내역 공백 상태로 초기화
			
			// 기본 셋팅 초기화: 커서 위치를 채팅 입력 칸으로 지정, 채팅 내역의 스크롤을 가장 아래로 이동
			settingInit();
		}
		
		// '전송'버튼 클릭
		if (objAction.equals(btnMsgSend)) {
			printMsg();					// 문자 전송
			taInputMsg.setText("");		// 입력칸 공백 상태로 초기화
			settingInit();				// 기본 셋팅 초기화
		}
	}

	// 키보드 키 이벤트
	@Override
	public void keyPressed(KeyEvent e) {
		Object objKey = e.getKeyCode();
		
		// 'Enter'키 입력
		// 채팅 입력 칸에 입력된 내용을 채팅 창에 전송
		if (objKey.equals(KeyEvent.VK_ENTER)) {
			printMsg();					// 문자 전송
			taInputMsg.setText("");		// 입력칸 공백 상태로 초기화
			e.consume();				// 더 이상 'Key'이벤트를 받지 않음  ※ 'Enter' 기본 기능이 개행이기 때문에 잠금
		}
	}
	
	// 마우스 이벤트
	@Override
	public void mouseEntered(MouseEvent e) {
		btnMsgSend.setBackground(new Color(255, 230, 65));;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		btnMsgSend.setBackground(new Color(255, 200, 65));;
	}
	

	///// 사용 안 함  ※ KeyListener, MouseListener 기본 구성
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
	}
}