package awt;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import conn.SingletonClass;
import conn.WriterClass;
import dto.drawDTO;

public class DrawCanvas extends Canvas implements MouseListener, MouseMotionListener{
	// ��ǥ
	private int x;
	private int y;
	private int x1;
	private int y1;
	
	private Color c;
	
	private WriterClass wc;	

	public DrawCanvas(){
		c = new Color(0, 0, 0);
		wc = new WriterClass();
		//this.start();
	}

	public void start(){		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);		
	}
	
	public void stop(){
		//this.getMouseListeners().notify();
		//this.getMouseMotionListeners().notify();
		this.removeMouseListener(this);
		this.removeMouseMotionListener(this);
	}
	
	public void paint(Graphics g){	
		//���� ���콺�� �巡�׵� ���������� �׸��� ǥ��
		//Color c = new Color(0, 0, 0);
		g.setColor(c);
		g.drawLine(x, y, x1, y1);
		
		SingletonClass scls = SingletonClass.getInstance();
			
		for(int i = 0; i < scls.vc.size(); i++){
			drawDTO ddto = (drawDTO) scls.vc.get(i);
			g.drawLine(ddto.getX(), ddto.getY(), ddto.getX1(), ddto.getY1());
		}
	}
	
	public void mouseClicked(MouseEvent e){
	
	}

	//���콺�� ���� ������ ���������� ���
	public void mousePressed(MouseEvent e){
		x = e.getX();
		y = e.getY();
	}

	//���콺�� �� ������ �������� ����Ѵ�. repaint()�޼��带 ȣ���Ͽ� �ٽ� �׸��� �׸���.
	public void mouseReleased(MouseEvent e){
		x1 = e.getX();
		y1 = e.getY();
		
		
		SingletonClass scls = SingletonClass.getInstance();
		drawDTO ddto = new drawDTO(x, y, x1, y1);
		scls.vc.add(ddto);
		this.repaint();
		wc.sendDrawList();
	}

	
	@Override
	public void update(Graphics g) {
		paint(g);
	}
	

	public void mouseEntered(MouseEvent e){
	
	}

	public void mouseExited(MouseEvent e){
	
	}

	public void mouseMoved(MouseEvent e){
	
	}

	/*
	* ���콺�� �븣���� ������ �����̴� ���������� �׸��� �׶� �׶� ǥ���Ǿ�� �ϱ� ������
	* �ش� �׸��� �׷��ش�.
	*/
	public void mouseDragged(MouseEvent e){
		x1 = e.getX();
		y1 = e.getY();
		
		//PEN�� ���� �Ǿ����� ��� �������� ���Ϳ� �����Ѵ�.		
		Color c = new Color(0, 0, 0);
		
		
			SingletonClass scls = SingletonClass.getInstance();
			drawDTO ddto = new drawDTO(x, y, x1, y1);
			scls.vc.add(ddto);
			x = x1;
			y = y1;		
			this.repaint();

			wc.sendDrawList();
	}
	
	
	public void clear(Graphics g){		
		SingletonClass scls = SingletonClass.getInstance();
		scls.vc.clear();
		x = 0;
		y = 0;
		x1 = 0;
		y1 = 0;
		this.repaint();
		super.update(g);
	}
	
}