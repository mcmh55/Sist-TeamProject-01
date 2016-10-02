package awt;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicScrollBarUI;

import conn.SingletonClass;


public class Emoticon extends JFrame implements MouseListener, ActionListener, WindowListener {
	
	JButton btnEmo;
	JLabel lbImg;
	ImageIcon emoGif;
	
	JPanel pnEmoList;
	JScrollPane pnScroll;
	
	Container ct;
	
	SingletonClass scls;
	
	public Emoticon() {
		scls = SingletonClass.getInstance();
		
		pnEmoList = new JPanel();
		pnEmoList.setLayout(new GridBagLayout());
		pnEmoList.setBackground(new Color(85, 65, 65));
		pnScroll = new JScrollPane(pnEmoList);
		pnScroll.getVerticalScrollBar().setUnitIncrement(10);;		// 수직 스크롤바 속도
		
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

			// 위로 이동시키는 버튼  ※ ↑
			@Override
	        protected JButton createDecreaseButton(int orientation) {
	            return createZeroButton();
	        }
			
			// 아래로 이동시키는 버튼  ※ ↓
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
		
		
		ct = getContentPane();
		ct.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		
		///// 버튼 생성
		c.weightx = 1.0;	// 버튼 가로 크기  ※ 화면 가로 영역 비율에 맞춤
//		c.ipadx = 10;
//		c.ipady = 10;		// 버튼 세로 크기
		
		int x = 0, y = 0;
		for (int i = 0; i < scls.idao.EmoticonList.size(); i++) {
			btnEmo = new JButton(new ImageIcon("images/" + scls.idao.EmoticonList.get(i).getThumName()));
			btnEmo.setFocusPainted(false);			// 기본 선택 시 테두리 숨김
			btnEmo.setBorderPainted(false);			// 테두리 숨김
			btnEmo.setContentAreaFilled(false);		// 투명 하이라이트 숨김
			btnEmo.setPreferredSize(new Dimension(100, 100));	// 버튼 크기
			btnEmo.setName(String.valueOf(i));
			
			// 액션, 마우스 반응 감지
			btnEmo.addMouseListener(this);
			btnEmo.addActionListener(this);
			
			if (x <= 2) {
				c.gridx = x;
				c.gridy = y;
				pnEmoList.add(btnEmo, c);
				x++;
			}

			if (x > 2) {
				x = 0;
				y++;
			}
		}
		
		// 버튼 영역
		c.weightx = 1.0;
		c.weighty = 1.0;
		ct.add(pnScroll, c);
		
		
		// 이모티콘 창 윈도우 반응 감지
		addWindowListener(this);
				
		
		///// 이모티콘 창 셋팅
		// 채팅 창 위에서 하단에 출력
		setBounds(scls.chatWindow.getLocation().x, scls.chatWindow.getLocation().y + 400, 320, 400);
		setUndecorated(true);		// 이동, 숨기기, 크기 변경, 닫기 불가
		setResizable(false);
		setVisible(true);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		JButton overBtn = (JButton)e.getSource();
		overBtn.setBackground(new Color(80, 50, 50));
		overBtn.setOpaque(true);
		
		String gifName = scls.idao.EmoticonList.get(
						Integer.parseInt(overBtn.getName())).getGifName();
		emoGif = new ImageIcon("images/" + gifName);
		
		overBtn.setRolloverIcon(emoGif);
		emoGif.getImage().flush();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JButton overBtn = (JButton)e.getSource();
		overBtn.setOpaque(false);
		overBtn.setBorderPainted(false);		// 테두리 숨김
		
		emoGif.getImage().flush();
	}

	// 이모티콘 클릭 시 채팅 창에 출력
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton overBtn = (JButton)e.getSource();
		
		String gifName = scls.idao.EmoticonList.get(Integer.parseInt(overBtn.getName())).getGifName();
		emoGif = new ImageIcon("images/" + gifName);
		
		scls.chatWindow.sendEmo(emoGif);
		scls.idao.refreshEmoList();

		dispose();
	}
	
	// 이모티콘 창 외의 영역을 클릭 시 창 닫기
	@Override
	public void windowDeactivated(WindowEvent e) {
		dispose();
	}
	
	
	///// 사용 안 함  ※ MouseListener, WindowListener 기본 구성
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
	}
	@Override
	public void windowClosed(WindowEvent e) {
	}
	@Override
	public void windowIconified(WindowEvent e) {
	}
	@Override
	public void windowDeiconified(WindowEvent e) {
	}
	@Override
	public void windowActivated(WindowEvent e) {
	}
}