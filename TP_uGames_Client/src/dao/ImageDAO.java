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
		
		File projectPath = new File("");		// 프로젝트 폴더의 경로를 저장
		String path = projectPath.getAbsolutePath() + "/images";	// 프로젝트 폴더의 'images'폴더를 경로명을 저장
		
		File imgPath = new File(path);			// 프로젝트 폴더의 'images' 폴더 경로를 저장
		String fileList[] = imgPath.list();		// 'images' 폴더의 모든 파일들을 저장
		
		
		///// 파일 중 'Emo_Thum_xx.png', 'Emo_Play_xx.gif' 파일만 저장
		int imgIDX = 0;		// 저장되는 이미지의 인덱스 번호
		for (int i = 0; i < fileList.length; i++) {
			if (fileList[i].substring(0, 9).equals("Emo_Thum_")) {
				edto = new EmoticonDTO();
				
				edto.setThumName(fileList[i].substring(0));		// 썸네일 이미지의 파일명을 저장
				
				// 같은 번호의 gif 이미지도 저장
				int imgLenth = edto.getThumName().length();		// 썸네일 이미지의 파일명 길이를 저장
				String temp = edto.getThumName().substring(imgLenth-6, imgLenth);	// 예) 'Emo_Thum_01.png'의 '01.png'를 저장
				edto.setGifName("Emo_Play_" + temp.substring(0, 2) + ".gif");		// '01' 숫자만 연결
				
				EmoticonList.add(edto);
				
				imgIDX++;
			}
		}
	}
	
	// 모든 이모티콘 재생 초기화
	public void refreshEmoList() {
		for (int i = 0; i < EmoticonList.size(); i++) {
			ImageIcon emoTemp = new ImageIcon("images/" + EmoticonList.get(i).getGifName());
			emoTemp.getImage().flush();
		}
	}
}