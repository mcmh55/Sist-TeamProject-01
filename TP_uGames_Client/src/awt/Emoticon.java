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
		pnScroll.getVerticalScrollBar().setUnitIncrement(10);;		// ���� ��ũ�ѹ� �ӵ�
		
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
		
		
		ct = getContentPane();
		ct.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		
		///// ��ư ����
		c.weightx = 1.0;	// ��ư ���� ũ��  �� ȭ�� ���� ���� ������ ����
//		c.ipadx = 10;
//		c.ipady = 10;		// ��ư ���� ũ��
		
		int x = 0, y = 0;
		for (int i = 0; i < scls.idao.EmoticonList.size(); i++) {
			btnEmo = new JButton(new ImageIcon("images/" + scls.idao.EmoticonList.get(i).getThumName()));
			btnEmo.setFocusPainted(false);			// �⺻ ���� �� �׵θ� ����
			btnEmo.setBorderPainted(false);			// �׵θ� ����
			btnEmo.setContentAreaFilled(false);		// ���� ���̶���Ʈ ����
			btnEmo.setPreferredSize(new Dimension(100, 100));	// ��ư ũ��
			btnEmo.setName(String.valueOf(i));
			
			// �׼�, ���콺 ���� ����
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
		
		// ��ư ����
		c.weightx = 1.0;
		c.weighty = 1.0;
		ct.add(pnScroll, c);
		
		
		// �̸�Ƽ�� â ������ ���� ����
		addWindowListener(this);
				
		
		///// �̸�Ƽ�� â ����
		// ä�� â ������ �ϴܿ� ���
		setBounds(scls.chatWindow.getLocation().x, scls.chatWindow.getLocation().y + 400, 320, 400);
		setUndecorated(true);		// �̵�, �����, ũ�� ����, �ݱ� �Ұ�
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
		overBtn.setBorderPainted(false);		// �׵θ� ����
		
		emoGif.getImage().flush();
	}

	// �̸�Ƽ�� Ŭ�� �� ä�� â�� ���
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton overBtn = (JButton)e.getSource();
		
		String gifName = scls.idao.EmoticonList.get(Integer.parseInt(overBtn.getName())).getGifName();
		emoGif = new ImageIcon("images/" + gifName);
		
		scls.chatWindow.sendEmo(emoGif);
		scls.idao.refreshEmoList();

		dispose();
	}
	
	// �̸�Ƽ�� â ���� ������ Ŭ�� �� â �ݱ�
	@Override
	public void windowDeactivated(WindowEvent e) {
		dispose();
	}
	
	
	///// ��� �� ��  �� MouseListener, WindowListener �⺻ ����
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