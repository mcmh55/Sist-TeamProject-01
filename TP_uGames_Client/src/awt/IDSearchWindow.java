package awt;

import java.awt.Button;
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
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import conn.SingletonClass;
import conn.WriterClass;

public class IDSearchWindow extends Frame implements ActionListener, WindowListener {
	GridBagConstraints c ;
	
	//기본 판넬
	JPanel pan = new JPanel();
	
	//메인경마 이미지
	JLabel mainimage;
	BufferedImage inputimage;
	
	//라벨
	Label lbname;
	Label lbquestion[] = new Label[2];
	Label lbanswer;
	
	//텍스트필트
	TextField tfname;
	TextField tfanswer = new TextField();
	
	//버튼
	JButton b_search;
	JButton b_confirm = new JButton("확인");
	
	SingletonClass scls;
	
	public IDSearchWindow() throws IOException{
		super("아이디/비밀번호 찾기");
		
		pan.setLayout(new GridBagLayout());
		pan.setBackground(new Color(50,50,50));
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
	
		for(int i = 0;i < 6; i++){
			Panel p1 = new Panel();
			c.weightx = 1.0;	
			c.gridx = i;
			c.gridy = 1;
			pan.add(p1, c);
		}
		
		lbname = new Label("이름 :", Label.RIGHT);
		lbname.setForeground(Color.white);
		c.insets = new Insets(0, 0, 0, 10);
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		pan.add(lbname, c);
			
		tfname = new TextField();
		c.insets = new Insets(0, 10, 0, 0);
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 3;
		pan.add(tfname, c);
		
		b_search = new JButton("찾기");
		b_search.setBackground(new Color(255, 200, 65));
		c.insets = new Insets(0, 10, 0, 10);
		c.gridx = 4;
		c.gridy = 2;
		c.gridwidth = 1;
		pan.add(b_search, c);
		
		//찾기 누르면 질문이 나타나게 하기위한 준비
		lbquestion[0] = new Label();
		lbquestion[0].setAlignment(Label.RIGHT);
		lbquestion[0].setForeground(Color.white);
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		pan.add(lbquestion[0], c);
		
		lbquestion[1] = new Label();
		lbquestion[1].setForeground(Color.white);
		c.insets = new Insets(10, 10, 10, 10);
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 3;
		pan.add(lbquestion[1], c);
		
		lbanswer = new Label();
		lbanswer.setAlignment(Label.RIGHT);
		lbanswer.setForeground(Color.white);
		c.insets= new Insets(0, 0, 0, 10);
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 1;
		pan.add(lbanswer, c);

		b_confirm.addActionListener(this);
		b_search.addActionListener(this);
		
		setBounds(700, 200, 400, 300);
		setVisible(true);
//		setResizable(false);
		
		add(pan);
		addWindowListener(this);
		
	}
	
	public void getQestion(){
		SingletonClass scls = SingletonClass.getInstance();
		lbquestion[0].setText("질문 :");
        lbquestion[1].setText(scls.mdto.getQuestion());
        lbanswer.setText("답변 :");
        //Textfield
        c.insets = new Insets(0, 10, 0, 0);
        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 3;
        pan.add(tfanswer, c);
        //button
        b_confirm.setBackground(new Color(255, 200, 65));
        c.insets = new Insets(0, 10, 0, 10);
        c.gridx = 4;
        c.gridy = 4;
        c.gridwidth = 1;
        pan.add(b_confirm, c);
        setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		
		if(obj == b_search){
			SingletonClass scls = SingletonClass.getInstance();
	         //memberDao mdao = new memberDao();
	         
	         if(tfname.getText().equals("")){
	            scls.displayMessage("이름을 입력해 주세요.");
	         }
	         else{
	            
	            WriterClass wc = new WriterClass();
	            
	            wc.FindQestion(tfname.getText());

	            System.out.println("가입질문을 로딩하기 위한 클라이언트 Writer클래스로 '이름' 전송");
	            
	            
	         }
	      }
	      else if(obj == b_confirm){
	    	  SingletonClass scls = SingletonClass.getInstance();
	         if(scls.mdto.getAnswer().equals(tfanswer.getText())){
	            scls.displayMessage("찾으신 ID 는 " + scls.mdto.getId() + " 이며, PW는 " + scls.mdto.getPassword() + " 입니다.");
	            scls.lw.setVisible(true);
	            dispose();
	         }else{
	            scls.displayMessage("답변을 다시 확인하세요.");
	         }
	      }
	}
	
	@Override
	public void windowActivated(WindowEvent e){
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
		dispose();
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
