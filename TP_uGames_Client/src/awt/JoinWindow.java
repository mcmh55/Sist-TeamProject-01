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
	//±âº» ÆÇ³Ú
	JPanel pan = new JPanel();
	
	//¸ÞÀÎ°æ¸¶ ÀÌ¹ÌÁö
	JLabel mainimage;
	BufferedImage inputimage;
	
	//¶óº§
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
	
	//checkbox - ¼ºº° »ç¿ë
	CheckboxGroup sex = new CheckboxGroup();
	Checkbox man = new Checkbox("³²ÀÚ",sex,true);
	Checkbox woman = new Checkbox("¿©ÀÚ", sex, false);
	String chsex = "³²ÀÚ";
	
	//choice - Áú¹® »ç¿ë
	Choice ch = new Choice();
	String chquection = "³ªÀÇ º¸¹° 1È£´Â?";
	
	//¹öÆ°
	JButton b_idcheck;
	JButton b_nicknamecheck;
	JButton b_search;
	JButton b_join;
	
	boolean idcheck = true;
	boolean nicknamecheck = true;
	boolean recommendcheck = true;
	
	SingletonClass scls;
	
	public JoinWindow() throws IOException{
		super("È¸¿ø°¡ÀÔ");
		setTitle("È¸¿ø°¡ÀÔ");
		
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
		
		b_idcheck = new JButton("ID Áßº¹È®ÀÎ");
		b_idcheck.setBackground(new Color(255, 200, 65));
		c.insets = new Insets(10, 10, 0, 10);
		c.gridx = 4;
		c.gridy = 2;
		c.gridwidth = 2;
		pan.add(b_idcheck, c);
		
		lbpw = new Label("ºñ¹Ð¹øÈ£ :", Label.CENTER);
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
		
		lbname = new Label("ÀÌ¸§ :", Label.CENTER);
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
		
		lbnickname = new Label("º°¸í :", Label.CENTER);
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
		
		b_nicknamecheck = new JButton("º°¸í Áßº¹È®ÀÎ");
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
		
		lbsex = new Label("¼ºº° :", Label.CENTER);
		lbsex.setForeground(Color.white);
		c.insets = new Insets(10, 0, 0, 0);
		c.gridx = 0;
		c.gridy = 7;
		c.gridwidth = 1;
		pan.add(lbsex, c);
		
		//¼ºº° checkbox
		man.setForeground(Color.white);
		c.gridx = 1;
		c.gridy = 7;
		pan.add(man, c);
		
		woman.setForeground(Color.white);
		c.gridx = 2;
		c.gridy = 7;
		pan.add(woman, c);
		
		lbrecommend = new Label("ÃßÃµÀÎ :", Label.CENTER);
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
		
		b_search = new JButton("È¸¿ø °Ë»ö");
		b_search.setBackground(new Color(255, 200, 65));
		c.insets = new Insets(10, 10, 0, 10);
		c.gridx = 4;
		c.gridy = 8;
		c.gridwidth = 2;
		pan.add(b_search, c);
		
		lbquestion = new Label("Áú¹® :",Label.CENTER);
		lbquestion.setForeground(Color.white);
		c.insets = new Insets(10, 0, 0, 0);
		c.gridx = 0;
		c.gridy = 9;
		c.gridwidth = 1;
		pan.add(lbquestion, c);
		
		//Choice
		ch.add("³ªÀÇ º¸¹° 1È£´Â?");
		ch.add("³ªÀÇ Ãâ½Å ÃÊµîÇÐ±³´Â?");
		ch.add("³ªÀÇ Ãâ½Å °íÇâÀº?");
		ch.add("³ªÀÇ ÀÌ»óÇüÀº?");
		ch.add("¾Æ¹öÁö ¼ºÇÔÀº?");
		ch.add("¾î¸Ó´Ï ¼ºÇÔÀº?");
		ch.add("°¡Àå ÁÁ¾ÆÇÏ´Â »ö±òÀº?");
		c.gridx = 1;
		c.gridy = 9;
		c.gridwidth = 3;
		pan.add(ch, c);
		
		lbanswer = new Label("´ä :", Label.CENTER);
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
		
		b_join = new JButton("È¸¿ø°¡ÀÔ");
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
		
		//È¸¿ø°¡ÀÔ
		
		//id Áßº¹ Ã¼Å©
	
		if(obj == b_idcheck){			
			WriterClass wc = new WriterClass();
		
			if(tfid.getText().equals("")){
				scls.displayMessage("¾ÆÀÌµð¸¦ ÀÔ·ÂÇØ ÁÖ¼¼¿ä.");
			}else if(tfid.getText().length() < 4 || tfid.getText().length() > 12 || tfid.getText().matches(".*[¤¡-¤¾¤¿-¤Ó°¡-ÆR]+.*")){
				scls.displayMessage("ÃÖ¼Ò 4ÀÚ ~ 12ÀÚ ÀÌ»ó ¿µ¾î »ç¿ë°¡´ÉÇÕ´Ï´Ù.");
			}else{
				
				wc.doubleIdCheck(tfid.getText());

				System.out.println("¾ÆÀÌµð Áßº¹ ¹æÁö¿ë id ¼­¹ö Àü¼Û");
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if(scls.mdto.getId().equals("IdDoesNotExist")){
					scls.displayMessage("»ç¿ë°¡´ÉÇÑ IDÀÔ´Ï´Ù.");
					this.idcheck = false;
				}else{
					System.out.println(scls.mdto.getId());
					scls.displayMessage("ÀÌ¹Ì Á¸ÀçÇÏ´Â IDÀÔ´Ï´Ù. ´Ù½Ã ÀÔ·ÂÇÏ¼¼¿ä.");
				}
			}
		}
		
		//º°¸í Áßº¹ Ã¼Å©
		else if(obj == b_nicknamecheck){		
			
			WriterClass wc = new WriterClass();
		
			if(tfnickname.getText().equals("")){
				scls.displayMessage("º°¸íÀ» ÀÔ·ÂÇØ ÁÖ¼¼¿ä");				
			}else{
				
				wc.doubleNickCheck(tfnickname.getText());

				System.out.println("´Ð³×ÀÓ Áßº¹ ¹æÁö¿ë ´Ð³×ÀÓ ¼­¹ö Àü¼Û");
				
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if(scls.mdto.getNickname().equals("NickDoesNotExist")){
					scls.displayMessage("»ç¿ë°¡´ÉÇÑ º°¸íÀÔ´Ï´Ù.");
					this.nicknamecheck = false;

				}else{
					scls.displayMessage("ÀÌ¹Ì Á¸ÀçÇÏ´Â º°¸íÀÔ´Ï´Ù. ´Ù½Ã ÀÔ·ÂÇÏ¼¼¿ä.");
				}
			}	
				
		}
			
		//ÃßÃµÀÎ È¸¿ø Ã£±â
		else if(obj == b_search){
		
			WriterClass wc = new WriterClass();
		
			
			wc.doubleIdCheck(tfrecommend.getText());
	
			System.out.println("ÃßÃµÀÎ È®ÀÎ¿ë id ¼­¹ö Àü¼Û");
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if(scls.mdto.getId().equals("IdDoesNotExist")){
				scls.displayMessage("ÇØ´ç È¸¿øÀÌ Á¸ÀçÇÏÁö ¾Ê½À´Ï´Ù.");
	
			}else{
				System.out.println(scls.mdto.getId());
				scls.displayMessage("ÃßÃµÈ¸¿øÀÌ È®ÀÎµÇ¾ú½À´Ï´Ù.");
			}
	
		}		
		//ºóÄ­ Ã³¸®
		else if(obj == b_join){
			if(tfpw.getText().equals("")){
				scls.displayMessage("ºóÄ­À» Ã¤¿öÁÖ¼¼¿ä.");
			}else if(tfname.getText().equals("")){
				scls.displayMessage("ºóÄ­À» Ã¤¿öÁÖ¼¼¿ä.");
			}else if(tfemail.getText().equals("")){
				scls.displayMessage("ºóÄ­À» Ã¤¿öÁÖ¼¼¿ä.");
			}else if(tfanswer.getText().equals("")){
				scls.displayMessage("ºóÄ­À» Ã¤¿öÁÖ¼¼¿ä.");
			}
			if(tfpw.getText().length() < 8 || tfpw.getText().length() > 20 || tfpw.getText().equals("") || !(tfpw.getText().contains("!"))
					&& !(tfpw.getText().contains("@")) && !(tfpw.getText().contains("#"))&& !(tfpw.getText().contains("~"))&&!(tfpw.getText().contains("*"))){
				scls.displayMessage("ÃÖ¼Ò 8~20ÀÚ, Æ¯¼ö ±âÈ£(!, @, #, ~, *)Æ÷ÇÔ ÇØ¾ß ÇÕ´Ï´Ù.");
			}else{
				if(idcheck == false && nicknamecheck == false){
					WriterClass wc = new WriterClass();
					wc.accountSave(tfid.getText(), tfpw.getText(), tfname.getText(),
								tfnickname.getText(), tfemail.getText(), chsex, 
								tfrecommend.getText(), chquection, tfanswer.getText());
					
					System.out.println("È¸¿ø°¡ÀÔ Á¤º¸¸¦ ¼­¹ö·Î Àü¼ÛÇÏ±â À§ÇÏ¿© WriterÅ¬·¡½º·Î Àü¼Û");
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					if(scls.mdto.getId().equals("CreationFailed")){
						scls.displayMessage("°èÁ¤»ý¼º¿¡ ½ÇÆÐÇÏ¿´½À´Ï´Ù.");

					}else{
						scls.displayMessage("È¸¿ø°¡ÀÔÀ» ÃàÇÏÇÕ´Ï´Ù.");
						scls.lw.setVisible(true);
						dispose();
					}
				}else{
					scls.displayMessage("Áßº¹È®ÀÎÀ» ÇØÁÖ¼¼¿ä.");
				}
			}
		}
	}
		

	@Override
	public void itemStateChanged(ItemEvent e) {
		//¼±ÅÃÇÑ Áú¹® StringÀ¸·Î ¹Þ±â
		if(ch == e.getSource()){
			chquection = ch.getSelectedItem();
			return;
		}
		//¼±ÅÃÇÑ ¼ºº° StringÀ¸·Î ¹Þ±â
		Checkbox ck1  = (Checkbox)e.getSource();
		ck1.getLabel();
			if(ck1.getLabel().equals("³²ÀÚ") || ck1.getLabel().equals("¿©ÀÚ")){
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
