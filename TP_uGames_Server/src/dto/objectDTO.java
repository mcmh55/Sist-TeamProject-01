package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

public class objectDTO implements Serializable {
	
	private int state;			// packet 상태
	private String msg;			// message
	private memberDTO mdto;		// member 정보
	private horseDTO hdto;		// horse 정보
	private Vector<drawDTO> vc;	// canvas 정보
	private ArrayList<horseInfoDTO> horseInfoList;
	
	// horse game
	private int bat;
	private int choiceHorse;
	private int winner;
	
	// chat
	// ★ 추가: 이모티콘 파일 이름
	private String emo;
	
	
	public objectDTO() {
		this.state = 0;
		this.msg = "";
		this.mdto = null;
		this.hdto = null;
		this.vc = null;
	}
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public memberDTO getMdto() {
		return mdto;
	}
	public void setMdto(memberDTO mdto) {
		this.mdto = mdto;
	}
	public horseDTO getHdto() {
		return hdto;
	}
	public void setHdto(horseDTO hdto) {
		this.hdto = hdto;
	}
	public int getBat() {
		return bat;
	}
	public void setBat(int bat) {
		this.bat = bat;
	}
	public int getChoiceHorse() {
		return choiceHorse;
	}
	public void setChoiceHorse(int choiceHorse) {
		this.choiceHorse = choiceHorse;
	}
	public int getWinner() {
		return winner;
	}
	public void setWinner(int winner) {
		this.winner = winner;
	}
	public String getEmo() {
		return emo;
	}
	public void setEmo(String emo) {
		this.emo = emo;
	}
	public Vector<drawDTO> getVc() {
		return vc;
	}
	public void setVc(Vector<drawDTO> vc) {
		this.vc = vc;
	}
	public ArrayList<horseInfoDTO> getHorseInfoList() {
		return horseInfoList;
	}
	public void setHorseInfoList(ArrayList<horseInfoDTO> horseInfoList) {
		this.horseInfoList = horseInfoList;
	}
}
