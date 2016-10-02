package awt;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dto.horseInfoDTO;

public class HorseInfoWindow extends JFrame{
   
   // Instance attributes used in this example

    private  JPanel      panel1, panel2;//���� ���̺��
    private  JTable      table1;
    private  JScrollPane scrollPane11, scrollPane12;
    private   JLabel lbl1, lbl2;
    private   JTextArea ta;
   
   String columnNames1[];
   String dataValues1[][];   

   
  // Constructor of main frame
  public HorseInfoWindow()
  {

     setTitle( "Horse Information" );
     setBounds(1200, 100, 550, 500);
     setResizable(false);
     setLayout(new GridBagLayout());
     GridBagConstraints cg = new GridBagConstraints();
     cg.fill = GridBagConstraints.BOTH;
     setBackground(new Color(80,60,60));
     

     // 1. ���̺� �� �ش� �г� ����("���ָ� ����")
     
     lbl1 = new JLabel("���� ���ָ� ���� ");
     lbl1.setForeground(Color.YELLOW);
     lbl1.setFont(new Font("�޸տ�����", Font.BOLD | Font.ITALIC, 15));
     panel1 = new JPanel();
     panel1.setBackground(new Color(80,60,60));
     
     
     panel1.add(lbl1);
     
     
  // Create columns names
     columnNames1 = new String[]{ "�� �̸�", "Column 1", "Column 2", "Column 3", "Column 4", "Column 5", "Column 6", "Column 7", "�׿� 6��"};
     
     // ���� ���� �Է�
     dataValues1 = new String[][]
     {
        { "�ְ�ӵ�", "0", "0", "0", "0", "0", "0", "0", "???"},
        { "�����ӵ�", "0", "0", "0", "0", "0", "0", "0", "???" },
        { "����", "0", "0", "0", "0", "0", "0", "0", "???"}
     };
     
     
     // �÷��� ������ �ִ� ���̺� ���� 
     table1 = new JTable( dataValues1, columnNames1 );

     //���̺� �� ���� ���� ����
     DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
     celAlignCenter.setHorizontalAlignment(JLabel.CENTER);//�߾� ���� ����
     DefaultTableCellRenderer celAlignRight = new DefaultTableCellRenderer();
     celAlignRight.setHorizontalAlignment(JLabel.RIGHT);// ���� ���� ����
     

     table1.getColumn("�� �̸�").setCellRenderer(celAlignCenter);
     table1.getColumn("Column 1").setCellRenderer(celAlignCenter);
     table1.getColumn("Column 2").setCellRenderer(celAlignCenter);
     table1.getColumn("Column 3").setCellRenderer(celAlignCenter);
     table1.getColumn("Column 4").setCellRenderer(celAlignCenter);
     table1.getColumn("Column 5").setCellRenderer(celAlignCenter);
     table1.getColumn("Column 6").setCellRenderer(celAlignCenter);
     table1.getColumn("Column 7").setCellRenderer(celAlignCenter);
     table1.getColumn("�׿� 6��").setCellRenderer(celAlignCenter);
     

     
     // ��ũ�ѿ� ���̺� �ø�
     scrollPane11 = new JScrollPane( table1 );
     
     DefaultTableModel dtm = new DefaultTableModel(dataValues1, columnNames1);
     table1.setModel(dtm);
     table1.setEnabled(false);
     
     
     
     
     
  ///////////////////////////////////////////////////////////////////////////////////////////////   
  // 2. ���̺� �� �ش� �г� ����("��ü ���� ����")
     
     lbl2 = new JLabel("��ü ���� ���� ");
     lbl2.setForeground(Color.YELLOW);
     lbl2.setFont(new Font("�޸տ�����", Font.BOLD | Font.ITALIC, 15));
     panel2 = new JPanel();
     panel2.setBackground(new Color(80,60,60));
     
     panel2.add(lbl2);
     

     
     // 3. TextArea ����("��ü ���� ����")
 
        ta = new JTextArea();
        ta.setEditable(false);
        
       // ��ũ�ѿ� TextArea �ø�
       scrollPane12 = new JScrollPane( ta );
              
              
              
   ///////////////////////////////////////////////////////////////////////////////////////////////   
 
  
     //���� ��ġ
     
     cg.insets = new Insets(1, 3, 1, 3);
     
     cg.weightx = 1.0;
     cg.gridx = 0;
     cg.gridy = 0;
     add(panel1, cg);
     
     cg.gridx = 0;
     cg.gridy = 1;
     cg.weighty = 0.16;
     add(scrollPane11, cg);
     
     cg.gridx = 0;
     cg.gridy = 2;
     cg.weighty = 0;
     add(panel2, cg);
     
     cg.gridx = 0;
     cg.gridy = 3;
     cg.weighty = 1;
     add(scrollPane12, cg);
   }
   
   
   public void setHorse(ArrayList<horseInfoDTO> horseInfoList){
	   for(int i = 0; i < horseInfoList.size(); i++){
		   columnNames1[i+1] = horseInfoList.get(i).getHorseName();
		   dataValues1[0][i+1] = Integer.toString((horseInfoList.get(i).getPhysical1() + horseInfoList.get(i).getPhysical2() - 1));
		   System.out.println(horseInfoList.get(i).getPhysical1());
		   dataValues1[1][i+1] = Integer.toString(horseInfoList.get(i).getPhysical2());
		   dataValues1[2][i+1] = Double.toString(horseInfoList.get(i).getHorseDividendRate());
	   }
	   
	   DefaultTableModel dtm = new DefaultTableModel(dataValues1, columnNames1);
	   table1.setModel(dtm);
	   
	   //���̺� �� ���� ���� ����
	   DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
	   celAlignCenter.setHorizontalAlignment(JLabel.CENTER);//�߾� ���� ����
	   
	   table1.getColumn("�� �̸�").setCellRenderer(celAlignCenter);
	   for(int i = 0; i < horseInfoList.size(); i++){
		   table1.getColumn(horseInfoList.get(i).getHorseName()).setCellRenderer(celAlignCenter);
	   }
	   table1.getColumn("�׿� 6��").setCellRenderer(celAlignCenter);
   }
   
   public void taClear(){
	   ta.setText("");
   }
   
   public void appendTa(String msg){
	   ta.append(msg);
	   ta.append("------------------------------------------------------------\n");
   }


}