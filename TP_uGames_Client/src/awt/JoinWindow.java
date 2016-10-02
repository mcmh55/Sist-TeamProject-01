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
	//�⺻ �ǳ�
	JPanel pan = new JPanel();
	
	//���ΰ渶 �̹���
	JLabel mainimage;
	BufferedImage inputimage;
	
	//��
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
	
	//checkbox - ���� ���
	CheckboxGroup sex = new CheckboxGroup();
	Checkbox man = new Checkbox("����",sex,true);
	Checkbox woman = new Checkbox("����", sex, false);
	String chsex = "����";
	
	//choice - ���� ���
	Choice ch = new Choice();
	String chquection = "���� ���� 1ȣ��?";
	
	//��ư
	JButton b_idcheck;
	JButton b_nicknamecheck;
	JButton b_search;
	JButton b_join;
	
	boolean idcheck = true;
	boolean nicknamecheck = true;
	boolean recommendcheck = true;
	
	SingletonClass scls;
	
	public JoinWindow() throws IOException{
		super("ȸ������");
		setTitle("ȸ������");
		
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
		
		b_idcheck = new JButton("ID �ߺ�Ȯ��");
		b_idcheck.setBackground(new Color(255, 200, 65));
		c.insets = new Insets(10, 10, 0, 10);
		c.gridx = 4;
		c.gridy = 2;
		c.gridwidth = 2;
		pan.add(b_idcheck, c);
		
		lbpw = new Label("��й�ȣ :", Label.CENTER);
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
		
		lbname = new Label("�̸� :", Label.CENTER);
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
		
		lbnickname = new Label("���� :", Label.CENTER);
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
		
		b_nicknamecheck = new JButton("���� �ߺ�Ȯ��");
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
		
		lbsex = new Label("���� :", Label.CENTER);
		lbsex.setForeground(Color.white);
		c.insets = new Insets(10, 0, 0, 0);
		c.gridx = 0;
		c.gridy = 7;
		c.gridwidth = 1;
		pan.add(lbsex, c);
		
		//���� checkbox
		man.setForeground(Color.white);
		c.gridx = 1;
		c.gridy = 7;
		pan.add(man, c);
		
		woman.setForeground(Color.white);
		c.gridx = 2;
		c.gridy = 7;
		pan.add(woman, c);
		
		lbrecommend = new Label("��õ�� :", Label.CENTER);
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
		
		b_search = new JButton("ȸ�� �˻�");
		b_search.setBackground(new Color(255, 200, 65));
		c.insets = new Insets(10, 10, 0, 10);
		c.gridx = 4;
		c.gridy = 8;
		c.gridwidth = 2;
		pan.add(b_search, c);
		
		lbquestion = new Label("���� :",Label.CENTER);
		lbquestion.setForeground(Color.white);
		c.insets = new Insets(10, 0, 0, 0);
		c.gridx = 0;
		c.gridy = 9;
		c.gridwidth = 1;
		pan.add(lbquestion, c);
		
		//Choice
		ch.add("���� ���� 1ȣ��?");
		ch.add("���� ��� �ʵ��б���?");
		ch.add("���� ��� ������?");
		ch.add("���� �̻�����?");
		ch.add("�ƹ��� ������?");
		ch.add("��Ӵ� ������?");
		ch.add("���� �����ϴ� ������?");
		c.gridx = 1;
		c.gridy = 9;
		c.gridwidth = 3;
		pan.add(ch, c);
		
		lbanswer = new Label("�� :", Label.CENTER);
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
		
		b_join = new JButton("ȸ������");
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
		
		//ȸ������
		
		//id �ߺ� üũ
	
		if(obj == b_idcheck){			
			WriterClass wc = new WriterClass();
		
			if(tfid.getText().equals("")){
				scls.displayMessage("���̵� �Է��� �ּ���.");
			}else if(tfid.getText().length() < 4 || tfid.getText().length() > 12 || tfid.getText().matches(".*[��-����-�Ӱ�-�R]+.*")){
				scls.displayMessage("�ּ� 4�� ~ 12�� �̻� ���� ��밡���մϴ�.");
			}else{
				
				wc.doubleIdCheck(tfid.getText());

				System.out.println("���̵� �ߺ� ������ id ���� ����");
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if(scls.mdto.getId().equals("IdDoesNotExist")){
					scls.displayMessage("��밡���� ID�Դϴ�.");
					this.idcheck = false;
				}else{
					System.out.println(scls.mdto.getId());
					scls.displayMessage("�̹� �����ϴ� ID�Դϴ�. �ٽ� �Է��ϼ���.");
				}
			}
		}
		
		//���� �ߺ� üũ
		else if(obj == b_nicknamecheck){		
			
			WriterClass wc = new WriterClass();
		
			if(tfnickname.getText().equals("")){
				scls.displayMessage("������ �Է��� �ּ���");				
			}else{
				
				wc.doubleNickCheck(tfnickname.getText());

				System.out.println("�г��� �ߺ� ������ �г��� ���� ����");
				
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if(scls.mdto.getNickname().equals("NickDoesNotExist")){
					scls.displayMessage("��밡���� �����Դϴ�.");
					this.nicknamecheck = false;

				}else{
					scls.displayMessage("�̹� �����ϴ� �����Դϴ�. �ٽ� �Է��ϼ���.");
				}
			}	
				
		}
			
		//��õ�� ȸ�� ã��
		else if(obj == b_search){
		
			WriterClass wc = new WriterClass();
		
			
			wc.doubleIdCheck(tfrecommend.getText());
	
			System.out.println("��õ�� Ȯ�ο� id ���� ����");
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if(scls.mdto.getId().equals("IdDoesNotExist")){
				scls.displayMessage("�ش� ȸ���� �������� �ʽ��ϴ�.");
	
			}else{
				System.out.println(scls.mdto.getId());
				scls.displayMessage("��õȸ���� Ȯ�εǾ����ϴ�.");
			}
	
		}		
		//��ĭ ó��
		else if(obj == b_join){
			if(tfpw.getText().equals("")){
				scls.displayMessage("��ĭ�� ä���ּ���.");
			}else if(tfname.getText().equals("")){
				scls.displayMessage("��ĭ�� ä���ּ���.");
			}else if(tfemail.getText().equals("")){
				scls.displayMessage("��ĭ�� ä���ּ���.");
			}else if(tfanswer.getText().equals("")){
				scls.displayMessage("��ĭ�� ä���ּ���.");
			}
			if(tfpw.getText().length() < 8 || tfpw.getText().length() > 20 || tfpw.getText().equals("") || !(tfpw.getText().contains("!"))
					&& !(tfpw.getText().contains("@")) && !(tfpw.getText().contains("#"))&& !(tfpw.getText().contains("~"))&&!(tfpw.getText().contains("*"))){
				scls.displayMessage("�ּ� 8~20��, Ư�� ��ȣ(!, @, #, ~, *)���� �ؾ� �մϴ�.");
			}else{
				if(idcheck == false && nicknamecheck == false){
					WriterClass wc = new WriterClass();
					wc.accountSave(tfid.getText(), tfpw.getText(), tfname.getText(),
								tfnickname.getText(), tfemail.getText(), chsex, 
								tfrecommend.getText(), chquection, tfanswer.getText());
					
					System.out.println("ȸ������ ������ ������ �����ϱ� ���Ͽ� WriterŬ������ ����");
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					if(scls.mdto.getId().equals("CreationFailed")){
						scls.displayMessage("���������� �����Ͽ����ϴ�.");

					}else{
						scls.displayMessage("ȸ�������� �����մϴ�.");
						scls.lw.setVisible(true);
						dispose();
					}
				}else{
					scls.displayMessage("�ߺ�Ȯ���� ���ּ���.");
				}
			}
		}
	}
		

	@Override
	public void itemStateChanged(ItemEvent e) {
		//������ ���� String���� �ޱ�
		if(ch == e.getSource()){
			chquection = ch.getSelectedItem();
			return;
		}
		//������ ���� String���� �ޱ�
		Checkbox ck1  = (Checkbox)e.getSource();
		ck1.getLabel();
			if(ck1.getLabel().equals("����") || ck1.getLabel().equals("����")){
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
