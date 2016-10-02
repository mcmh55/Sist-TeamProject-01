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
		
		// ä�� ȯ�漳��
		context = new StyleContext();
		document = new DefaultStyledDocument(context);
		style = context.getStyle(StyleContext.DEFAULT_STYLE);		// ���� ��Ÿ�� ����  �� �⺻
		
		// ���̾ƿ�
		ct = getContentPane();
		ct.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		
		// ������ ������
		lbconnectuser = new JLabel();
		lbconnectuser.setForeground(new Color(255, 255, 255));
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.insets = new Insets(5, 5, 5, 5);
		ct.add(lbconnectuser, c);
		

		///// ä�� ��� ����
		pnText = new JTextPane(document);
		pnText.setEditable(false);						// ���� �Ұ�
		pnText.setBackground(new Color(85, 65, 65));	// ��� ����
		pnScroll = new JScrollPane(pnText, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pnScroll.setBorder(new LineBorder(new Color(40, 40, 40)));		// �׵θ� ����
		
		// ���� ��ũ�ѹ� UI ������
		pnScroll.getVerticalScrollBar().setUI(new BasicScrollBarUI()
	    {   
			// ��ũ�ѹ� ��(bar) ������
			@Override 
	        protected void configureScrollBarColors(){
	            this.thumbColor = new Color(120, 100, 100);
	        }

			// ��ũ�ѹ� ��� ������
			@Override
			protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
				g.setColor(new Color(85, 65, 65));
				super.paintTrack(g, c, trackBounds);
			}

			// ���� �̵���Ű�� ��ư  �� ��
			@Override
	        protected JButton createDecreaseButton(int orientation) {
	            return createZeroButton();
	        }
			
			// �Ʒ��� �̵���Ű�� ��ư  �� ��
			@Override    
	        protected JButton createIncreaseButton(int orientation) {
	            return createZeroButton();
	        }

	        // ��ư ����  �� ����� ����(0), ����(0)���� ����
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
		
		
		///// ���� �Է� ����
		// �Է�ĭ
		taInputMsg = new JTextArea();
		taInputMsg.setLineWrap(true);		// �ڵ� �ٹٲ�
		pnScroll = new JScrollPane(taInputMsg, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pnScroll.setBorder(new LineBorder(Color.DARK_GRAY));	// �׵θ� ����
		
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 0.8;
		c.weighty = 0;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		ct.add(pnScroll, c);
		
		// ù �Է� ���ڸ� �ѱ۷� ����  �� Component�� ��
		inctLanguage = taInputMsg.getInputContext();
		if(inctLanguage != null) {
            Character.Subset[] subset ={Character.UnicodeBlock.HANGUL_SYLLABLES};
            inctLanguage.setCharacterSubsets(subset);
		}
		
		
		// ���� ��ư
		btnMsgSend = new JButton("����");
		btnMsgSend.setFont(new Font(null, Font.PLAIN, 12));
		btnMsgSend.setBackground(new Color(255, 200, 65));;
		btnMsgSend.setFocusPainted(false);
		btnMsgSend.setBorderPainted(false);
		btnMsgSend.setPreferredSize(new Dimension(60, 35));		// ��ư ũ�� ����
//		btnMsgSend.setBorder(BorderFactory.createLineBorder(Color.BLUE)); 	// �׵θ� ��
		c.gridx = 1;
		c.gridy = 2;
		c.weightx = 0;
		c.insets = new Insets(5, 0, 5, 5);
		ct.add(btnMsgSend, c);
		
		
		///// ä�� ���� ����
		pnChatTool = new JPanel();
		pnChatTool.setOpaque(false);
		pnChatTool.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
		
		// �̸�Ƽ�� ��ư
		btnEmoticon = new JButton(new ImageIcon("images/icon_btn_emoticon.png"));
		btnEmoticon.setRolloverIcon(new ImageIcon("images/icon_btn_emoticon_over.png"));
		btnEmoticon.setToolTipText("�̸�Ƽ��");					// �� ��
		btnEmoticon.setBorderPainted(false);					// �簢�� ����
		btnEmoticon.setContentAreaFilled(false);				// ���� ���̶���Ʈ ����
		btnEmoticon.setPreferredSize(new Dimension(20, 20));	// ��ư ũ�� ����
		pnChatTool.add(btnEmoticon);
		
		// ä��â ����� ��ư
		btnChatClear = new JButton(new ImageIcon("images/icon_btn_chatclear.png"));
		btnChatClear.setRolloverIcon(new ImageIcon("images/icon_btn_chatclear_over.png"));
		btnChatClear.setToolTipText("ä��â �����");				// �� ��
		btnChatClear.setBorderPainted(false);					// �簢�� ����
		btnChatClear.setContentAreaFilled(false);				// ���� ���̶���Ʈ ����
		btnChatClear.setPreferredSize(new Dimension(20, 20));	// ��ư ũ�� ����
		pnChatTool.add(btnChatClear);
		
		// ä�� ���� ���� ��ġ
		c.gridx = 0;
		c.gridy = 3;
		
		c.gridwidth = 2;
		c.insets = new Insets(5, 5, 10, 5);
		ct.add(pnChatTool, c);
		
		
		///// ȯ�� ����
		// �׼�, Ű����, ���콺 ���� ����
		btnEmoticon.addActionListener(this);
		btnChatClear.addActionListener(this);
		btnMsgSend.addActionListener(this);
		btnMsgSend.addMouseListener(this);
		taInputMsg.addKeyListener(this);
		
		// ä�� â ����
		setBounds(1005, 100, 320, 710);
//		setUndecorated(true);			// �̵�, �����, ũ�� ����, �ݱ� �Ұ�
		getContentPane().setBackground(new Color(80, 60, 60));
		setResizable(false);
		setVisible(false);
		
		taInputMsg.requestFocus();		// ���� �Է� ĭ�� Ŀ�� ��ġ
	}
	
	
	//////////Ŀ���� �޼ҵ� //////////
	// �⺻���� �ʱ�ȭ
	public void settingInit() {
		taInputMsg.requestFocus();							// Ŀ�� ��ġ�� ä�� �Է� ĭ���� ����
		pnText.setCaretPosition(document.getLength());		// ä�� ������ ��ũ���� ���� �Ʒ��� �̵�
	}
	
	
	// �ڽ��� ���� ���
	private void printMsg() {		
		scls = SingletonClass.getInstance();		
		String msg = taInputMsg.getText();		// �Է��� ���� ����
		
		// ���� ���¸� Ȯ���ϱ� ���� ����(����)�� ���� üũ
		int spaceCount = 0;
		for (int i = 0; i < msg.length(); i++) {
			if (msg.charAt(i) == ' ') {
				spaceCount++;
			}
		}
		
		// ������ ���� �Ұ� => �Էµ� ���� ���� + �Էµ� ���ڰ� ��� �������� �Ǿ�����
		if (!msg.equals("") && spaceCount != msg.length()) {
			try {
				// �ۼ��� ���
				// ������ ���� ĳ���� ���� �޶���
				Icon iface = null;
				if (scls.mdto.getSex().equals("����")) {
					iface = new ImageIcon("images/face_man.png");
				}
				else if(scls.mdto.getSex().equals("����")) {
					iface = new ImageIcon("images/face_woman.png");
				}
				
				lbUserName = new JLabel(scls.mdto.getNickname() + "(" + scls.mdto.getId() + "):", iface, JLabel.LEFT);
				lbUserName.setFont(new Font(null, Font.BOLD, 11));		// Font(�۲�(null = �⺻), ȿ��, ���� ũ��)
				lbUserName.setForeground(new Color(200, 200, 200));		// ���� ����(R, G, B)
				lbUserName.setPreferredSize(new Dimension(0, 30));		// ���� ����(�ٰ���)
				
				StyleConstants.setComponent(style, lbUserName);
				document.insertString(document.getLength(), "\n", style);
				
				// �Է��� ���� ���
				taChatText = new JTextArea();
				taChatText.setLineWrap(true);		// �ڵ� ���� ó��
				taChatText.append(msg);				// �Է��� ���ڸ� ä�� ������ ���
				taChatText.setFont(new Font(null, Font.PLAIN, 12));
				taChatText.setForeground(new Color(255, 255, 140));
				taChatText.setEditable(false);		// ���� �Ұ�
				taChatText.setOpaque(false);		// ��� ����
				
				StyleConstants.setComponent(style, taChatText);
				document.insertString(document.getLength(), "\n", style);
				
				// ���� ����
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
			
			// �⺻ ���� �ʱ�ȭ: Ŀ�� ��ġ�� ä�� �Է� ĭ���� ����, ä�� ������ ��ũ���� ���� �Ʒ��� �̵�
			settingInit();
		}
	}
	
	
	// �ڽ��� �̸�Ƽ�� ���
	public void sendEmo(ImageIcon emoGif) {
		scls = SingletonClass.getInstance();	
		try {
			// �ۼ��� ���  �� �ڽ��� ���� ��°� ����
			// ������ ���� ĳ���� ���� �޶���
			Icon iface = null;
			if (scls.mdto.getSex().equals("����")) {
				iface = new ImageIcon("images/face_man.png");
			}
			else if(scls.mdto.getSex().equals("����")) {
				iface = new ImageIcon("images/face_woman.png");
			}
			
			lbUserName = new JLabel(scls.mdto.getNickname() + "(" + scls.mdto.getId() + "):", iface, JLabel.LEFT);
			lbUserName.setFont(new Font(null, Font.BOLD, 11));
			lbUserName.setForeground(new Color(200, 200, 200));
			lbUserName.setPreferredSize(new Dimension(0, 30));		// ���� ����
			StyleConstants.setComponent(style, lbUserName);
			
			document.insertString(document.getLength(), "\n", style);
			
			// �̸�Ƽ�� ���
			lbImg = new JLabel(emoGif);
			StyleConstants.setComponent(style, lbImg);
			
			document.insertString(document.getLength(), "\n", style);
			
			// ���� ����
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
		
		// �⺻ ���� �ʱ�ȭ: Ŀ�� ��ġ�� ä�� �Է� ĭ���� ����, ä�� ������ ��ũ���� ���� �Ʒ��� �̵�
		settingInit();
	}
	
	
	// �������� ���� ���� ���
	public void printServerMsg(memberDTO mdto, String msg) {
		try {
			// �ۼ��� ���
			// ������ ���� ĳ���� ���� �޶���
			Icon iface = null;
			if (mdto.getSex().equals("����")) {
				iface = new ImageIcon("images/face_man.png");
			}
			else if(mdto.getSex().equals("����")) {
				iface = new ImageIcon("images/face_woman.png");
			}
			
			lbUserName = new JLabel(mdto.getNickname() + "(" + mdto.getId() + "):", iface, JLabel.LEFT);
			lbUserName.setFont(new Font(null, Font.PLAIN, 11));		// Font(�۲�(null = �⺻), ȿ��, ���� ũ��)
			lbUserName.setForeground(new Color(150, 150, 150));		// ���� ����(R, G, B)
			lbUserName.setPreferredSize(new Dimension(0, 30));		// ���� ����
			StyleConstants.setComponent(style, lbUserName);
			
			document.insertString(document.getLength(), "\n", style);
			
			// �Է��� ���� ���
			taChatText = new JTextArea();
			taChatText.setLineWrap(true);		// �ڵ� ���� ó��
			taChatText.append(msg);				// �Է��� ���ڸ� ä�� ������ ���
			taChatText.setFont(new Font(null, Font.PLAIN, 12));
			taChatText.setForeground(new Color(220, 220, 220));
			taChatText.setEditable(false);		// ���� �Ұ�
			taChatText.setOpaque(false);		// ��� ����
			StyleConstants.setComponent(style, taChatText);
			
			document.insertString(document.getLength(), "\n", style);
			
		}
		catch (BadLocationException ex) {
			ex.printStackTrace();
		}
		
		pnText.setCaretPosition(document.getLength());		// ä�� ������ ��ũ���� ���� �Ʒ��� �̵�
	}
	
	
	// �������� ���� �̸�Ƽ�� ���
	public void printServerEmo(memberDTO mdto, String EmoName) {
		scls = SingletonClass.getInstance();
		
		try {
			// �ۼ��� ���
			// ������ ���� ĳ���� ���� �޶���
			Icon iface = null;
			if (mdto.getSex().equals("����")) {
				iface = new ImageIcon("images/face_man.png");
			}
			else if(mdto.getSex().equals("����")) {
				iface = new ImageIcon("images/face_woman.png");
			}
			
			lbUserName = new JLabel(mdto.getNickname() + "(" + mdto.getId() + "):", iface, JLabel.LEFT);
			lbUserName.setFont(new Font(null, Font.PLAIN, 11));		// Font(�۲�(null = �⺻), ȿ��, ���� ũ��)
			lbUserName.setForeground(new Color(150, 150, 150));		// ���� ����(R, G, B)
			lbUserName.setPreferredSize(new Dimension(0, 30));		// ���� ����
			StyleConstants.setComponent(style, lbUserName);
			
			document.insertString(document.getLength(), "\n", style);
			
			// �̸�Ƽ�� ���
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
		
		pnText.setCaretPosition(document.getLength());		// ä�� ������ ��ũ���� ���� �Ʒ��� �̵�
	}
	
	// �����ο�
	public void setlbconnectuser(int i){
		lbconnectuser.setText("���� : " + i + "��");
	}
	
	
	
	////////// ���� �̺�Ʈ //////////
	// ��ư �׼� �̺�Ʈ
	@Override
	public void actionPerformed(ActionEvent e) {
		scls = SingletonClass.getInstance();
		
		Object objAction = e.getSource();
		
		// '�̸�Ƽ��'��ư Ŭ��
		if (objAction.equals(btnEmoticon)) {
			new Emoticon();
		}
		
		// '�����'��ư Ŭ��
		if (objAction.equals(btnChatClear)) {
			pnText.setText("");			// ä�� ���� ���� ���·� �ʱ�ȭ
			
			// �⺻ ���� �ʱ�ȭ: Ŀ�� ��ġ�� ä�� �Է� ĭ���� ����, ä�� ������ ��ũ���� ���� �Ʒ��� �̵�
			settingInit();
		}
		
		// '����'��ư Ŭ��
		if (objAction.equals(btnMsgSend)) {
			printMsg();					// ���� ����
			taInputMsg.setText("");		// �Է�ĭ ���� ���·� �ʱ�ȭ
			settingInit();				// �⺻ ���� �ʱ�ȭ
		}
	}

	// Ű���� Ű �̺�Ʈ
	@Override
	public void keyPressed(KeyEvent e) {
		Object objKey = e.getKeyCode();
		
		// 'Enter'Ű �Է�
		// ä�� �Է� ĭ�� �Էµ� ������ ä�� â�� ����
		if (objKey.equals(KeyEvent.VK_ENTER)) {
			printMsg();					// ���� ����
			taInputMsg.setText("");		// �Է�ĭ ���� ���·� �ʱ�ȭ
			e.consume();				// �� �̻� 'Key'�̺�Ʈ�� ���� ����  �� 'Enter' �⺻ ����� �����̱� ������ ���
		}
	}
	
	// ���콺 �̺�Ʈ
	@Override
	public void mouseEntered(MouseEvent e) {
		btnMsgSend.setBackground(new Color(255, 230, 65));;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		btnMsgSend.setBackground(new Color(255, 200, 65));;
	}
	

	///// ��� �� ��  �� KeyListener, MouseListener �⺻ ����
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