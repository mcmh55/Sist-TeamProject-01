package dao;

import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import dto.EmoticonDTO;


public class ImageDAO {

	public ArrayList<EmoticonDTO> EmoticonList;
	EmoticonDTO edto;
	ImageIcon imgGif;
	
	public ImageDAO() {
		EmoticonList = new ArrayList<EmoticonDTO>();
		
		File projectPath = new File("");		// ������Ʈ ������ ��θ� ����
		String path = projectPath.getAbsolutePath() + "/images";	// ������Ʈ ������ 'images'������ ��θ��� ����
		
		File imgPath = new File(path);			// ������Ʈ ������ 'images' ���� ��θ� ����
		String fileList[] = imgPath.list();		// 'images' ������ ��� ���ϵ��� ����
		
		
		///// ���� �� 'Emo_Thum_xx.png', 'Emo_Play_xx.gif' ���ϸ� ����
		int imgIDX = 0;		// ����Ǵ� �̹����� �ε��� ��ȣ
		for (int i = 0; i < fileList.length; i++) {
			if (fileList[i].substring(0, 9).equals("Emo_Thum_")) {
				edto = new EmoticonDTO();
				
				edto.setThumName(fileList[i].substring(0));		// ����� �̹����� ���ϸ��� ����
				
				// ���� ��ȣ�� gif �̹����� ����
				int imgLenth = edto.getThumName().length();		// ����� �̹����� ���ϸ� ���̸� ����
				String temp = edto.getThumName().substring(imgLenth-6, imgLenth);	// ��) 'Emo_Thum_01.png'�� '01.png'�� ����
				edto.setGifName("Emo_Play_" + temp.substring(0, 2) + ".gif");		// '01' ���ڸ� ����
				
				EmoticonList.add(edto);
				
				imgIDX++;
			}
		}
	}
	
	// ��� �̸�Ƽ�� ��� �ʱ�ȭ
	public void refreshEmoList() {
		for (int i = 0; i < EmoticonList.size(); i++) {
			ImageIcon emoTemp = new ImageIcon("images/" + EmoticonList.get(i).getGifName());
			emoTemp.getImage().flush();
		}
	}
}