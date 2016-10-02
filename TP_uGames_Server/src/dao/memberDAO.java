package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conn.SingletonClass;
import db.DBclose;
import db.DBconnection;
import dto.memberDTO;

public class memberDAO {
	Connection conn = null;
	Statement stmt = null;
	
	public memberDAO() {}
	
	//회원가입
	public memberDTO insert(String id, String password, String name, String nickname, String email, String sex, String recommend, String question, String answer, int point){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		SingletonClass scls = SingletonClass.getInstance();
		
		String sql1 = "insert into TP1_MEMBER(id, password, name, nickname, email, sex, recommend, question, answer, point) \n";
		   	   sql1 += "values ('" + id + "', '"+ password + "', '" + name + "', '" + nickname + "', '" + email +"', '"+ sex + "', '"
		   			   + recommend + "', '" + question + "', '" + answer + "', " + point + ")";
				System.out.println("sql1 = " + sql1);//정보저장
		   	   
		   	   
		String sql2 = "select *\n";
				sql2 += "from TP1_MEMBER\n";
				sql2 += "where id = '" + id + "' and password = '" + password + "'";
				System.out.println("sql2 = " + sql2);//저장테이블 확인
		
				
		conn  = DBconnection.makeConnection();
		System.out.println("2");
		System.out.println("DB접속완료");
		   	   
		try{
			stmt = conn.createStatement();
			stmt.executeUpdate(sql1);
			System.out.println("쿼리문1(정보저장) 실행");
			
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
			stmt.executeUpdate(sql2);
			System.out.println("쿼리문(저장테이블 확인) 실행");
			rs = stmt.executeQuery(sql2);
			
			scls.mdto.setId("CreationFailed");
			scls.mdto.setPassword("CreationFailed");

			while(rs.next()){				
				scls.mdto.setId(rs.getString("id"));
				scls.mdto.setPassword(rs.getString("password"));
				scls.mdto.setPoint(rs.getInt("point"));
			}

		}catch(SQLException e){
			System.out.println(e. getMessage());
		}finally{
			DBclose.close(stmt, conn);
		}
		return scls.mdto;
	}
	
	// login
		// 로그인
		public memberDTO login(String id, String password){
						
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			
			System.out.println("멤버DAO 실행");

			conn  = DBconnection.makeConnection();
			System.out.println("3");
			System.out.println("DB접속완료");

			
			String sql = "select *\n";
			sql += "from TP1_MEMBER\n";
			sql += "where id = '" + id + "' and password = '" + password + "'";
			System.out.println("sql=" + sql);

			SingletonClass scls = SingletonClass.getInstance();

			try {
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				System.out.println("쿼리문 실행");
				
				
				scls.mdto.setId("ThereIsNoID");
				scls.mdto.setPassword("ThereIsNoID");
				
				System.out.println("기존 ID는 " + scls.mdto.getId());
				while(rs.next()){				
					System.out.println("mdto세팅");

					scls.mdto.setId(rs.getString("id"));
					scls.mdto.setPassword(rs.getString("password"));
					scls.mdto.setName(rs.getString("name"));				
					scls.mdto.setNickname(rs.getString("nickname"));
					scls.mdto.setEmail(rs.getString("email"));
					scls.mdto.setSex(rs.getString("sex"));
					scls.mdto.setRecommend(rs.getString("recommend"));
					scls.mdto.setQuestion(rs.getString("question"));
					scls.mdto.setAnswer(rs.getString("answer"));
					scls.mdto.setPoint(rs.getInt("point"));
					System.out.println("로그인시 포인트 : " + scls.mdto.getPoint());
				}
					

				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				DBclose.close(stmt, conn, rs);
			}
			return scls.mdto;
		}

	//가입 시 id중복 검사
	public memberDTO idsame(String id) {
		SingletonClass scls = SingletonClass.getInstance();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		conn = DBconnection.makeConnection();
		System.out.println("4");
		
		String sql = "SELECT *\n";
		sql += "FROM TP1_MEMBER \n";
		sql += "WHERE ID = '" + id + "'";
		System.out.println("sql = " +sql);
		
		scls.mdto.setId("IdDoesNotExist");
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()){
				scls.mdto.setId(rs.getString("id"));
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBclose.close(stmt, conn, rs);
		}
		return scls.mdto;
	}
	
	//가입시 nickname중복 검사
		public memberDTO nicknamesame(String nickname) {
			SingletonClass scls = SingletonClass.getInstance();

			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			
			conn = DBconnection.makeConnection();
			System.out.println("5");
			
			String sql = "SELECT * \n";
			sql += "FROM TP1_MEMBER \n";
			sql += "WHERE NICKNAME = '" + nickname + "'";
			
			scls.mdto.setNickname("NickDoesNotExist");
			
			try {
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
			
				if(rs.next()){
					System.out.println("찾는 별명 있음");
					scls.mdto.setNickname(rs.getString("NICKNAME"));

				}
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				DBclose.close(stmt, conn, rs);
			}
			return scls.mdto;
		}
		
	//이름으로 질문 찾기
		public memberDTO idesearch_question(String name) {
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			
			SingletonClass scls = SingletonClass.getInstance();
				
			conn = DBconnection.makeConnection();
			System.out.println("6");
			
			String sql = "SELECT *\n";
			sql += "FROM TP1_MEMBER \n";
			sql += "WHERE NAME = '" + name + "'";
			
			try {
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
			
				if(rs.next()){
					System.out.println("가입 정보 있음");
					
					String checkId = rs.getString("ID");
					String checkPassword = rs.getString("PASSWORD");
					String checkQuestion = rs.getString("QUESTION");
					String checkAnswer = rs.getString("ANSWER");
					
					scls.mdto.setId(checkId); 
					scls.mdto.setPassword(checkPassword); 
					scls.mdto.setQuestion(checkQuestion); 
					scls.mdto.setAnswer(checkAnswer); 

				}
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				DBclose.close(stmt, conn, rs);
			}
			return scls.mdto;
		}
	
	//이름으로 답변 찾기
		public String idesearch_answer(String name) {
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
				
			conn = DBconnection.makeConnection();
			System.out.println("7");
			
			String sql = "SELECT *\n";
			sql += "FROM TP1_MEMBER \n";
			sql += "WHERE name = '" + name + "'";
			
			try {
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
			
				if(rs.next()){
					System.out.println("가입 정보 있음");
					String cheakanswer = rs.getString("answer");
					return cheakanswer;
				}
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				DBclose.close(stmt, conn, rs);
			}
			return null;
		}	
		//이름으로 비번 찾기
		public String idesearch_password(String name) {
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
				
			conn = DBconnection.makeConnection();
			System.out.println("8");
			
			String sql = "SELECT *\n";
			sql += "FROM TP1_MEMBER \n";
			sql += "WHERE name = '" + name + "'";
			
			try {
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
			
				if(rs.next()){
					System.out.println("가입 정보 있음");
					String cheakpassword = rs.getString("password");
					return cheakpassword;
				}
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				DBclose.close(stmt, conn, rs);
			}
			return null;
		}
		
	public void updatePoint(String id, int point){
		Connection conn = null;
		Statement stmt = null;
			
		conn = DBconnection.makeConnection();
		System.out.println("9");
		
		String sql = "update TP1_MEMBER set point = " + point + "\n";
		sql += "where id = '" + id + "'";
		
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DBclose.close(stmt, conn);
		}
	}
}
	
	
	
	

