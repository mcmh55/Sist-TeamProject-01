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

    private  JPanel      panel1, panel2;//제목 레이블용
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
     

     // 1. 레이블 및 해당 패널 생성("경주마 정보")
     
     lbl1 = new JLabel("출전 경주마 정보 ");
     lbl1.setForeground(Color.YELLOW);
     lbl1.setFont(new Font("휴먼엑스포", Font.BOLD | Font.ITALIC, 15));
     panel1 = new JPanel();
     panel1.setBackground(new Color(80,60,60));
     
     
     panel1.add(lbl1);
     
     
  // Create columns names
     columnNames1 = new String[]{ "말 이름", "Column 1", "Column 2", "Column 3", "Column 4", "Column 5", "Column 6", "Column 7", "그외 6종"};
     
     // 세부 정보 입력
     dataValues1 = new String[][]
     {
        { "최고속도", "0", "0", "0", "0", "0", "0", "0", "???"},
        { "최저속도", "0", "0", "0", "0", "0", "0", "0", "???" },
        { "배당률", "0", "0", "0", "0", "0", "0", "0", "???"}
     };
     
     
     // 컬럼과 정보가 있는 테이블 생성 
     table1 = new JTable( dataValues1, columnNames1 );

     //테이블 내 글자 우측 정렬
     DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
     celAlignCenter.setHorizontalAlignment(JLabel.CENTER);//중앙 정렬 설정
     DefaultTableCellRenderer celAlignRight = new DefaultTableCellRenderer();
     celAlignRight.setHorizontalAlignment(JLabel.RIGHT);// 우측 정렬 설정
     

     table1.getColumn("말 이름").setCellRenderer(celAlignCenter);
     table1.getColumn("Column 1").setCellRenderer(celAlignCenter);
     table1.getColumn("Column 2").setCellRenderer(celAlignCenter);
     table1.getColumn("Column 3").setCellRenderer(celAlignCenter);
     table1.getColumn("Column 4").setCellRenderer(celAlignCenter);
     table1.getColumn("Column 5").setCellRenderer(celAlignCenter);
     table1.getColumn("Column 6").setCellRenderer(celAlignCenter);
     table1.getColumn("Column 7").setCellRenderer(celAlignCenter);
     table1.getColumn("그외 6종").setCellRenderer(celAlignCenter);
     

     
     // 스크롤에 테이블 올림
     scrollPane11 = new JScrollPane( table1 );
     
     DefaultTableModel dtm = new DefaultTableModel(dataValues1, columnNames1);
     table1.setModel(dtm);
     table1.setEnabled(false);
     
     
     
     
     
  ///////////////////////////////////////////////////////////////////////////////////////////////   
  // 2. 레이블 및 해당 패널 생성("전체 배팅 정보")
     
     lbl2 = new JLabel("전체 배팅 정보 ");
     lbl2.setForeground(Color.YELLOW);
     lbl2.setFont(new Font("휴먼엑스포", Font.BOLD | Font.ITALIC, 15));
     panel2 = new JPanel();
     panel2.setBackground(new Color(80,60,60));
     
     panel2.add(lbl2);
     

     
     // 3. TextArea 생성("전체 배팅 정보")
 
        ta = new JTextArea();
        ta.setEditable(false);
        
       // 스크롤에 TextArea 올림
       scrollPane12 = new JScrollPane( ta );
              
              
              
   ///////////////////////////////////////////////////////////////////////////////////////////////   
 
  
     //최종 배치
     
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
	   
	   //테이블 내 글자 우측 정렬
	   DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
	   celAlignCenter.setHorizontalAlignment(JLabel.CENTER);//중앙 정렬 설정
	   
	   table1.getColumn("말 이름").setCellRenderer(celAlignCenter);
	   for(int i = 0; i < horseInfoList.size(); i++){
		   table1.getColumn(horseInfoList.get(i).getHorseName()).setCellRenderer(celAlignCenter);
	   }
	   table1.getColumn("그외 6종").setCellRenderer(celAlignCenter);
   }
   
   public void taClear(){
	   ta.setText("");
   }
   
   public void appendTa(String msg){
	   ta.append(msg);
	   ta.append("------------------------------------------------------------\n");
   }


}