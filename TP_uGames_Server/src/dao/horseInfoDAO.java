package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conn.SingletonClass;
import db.DBclose;
import db.DBconnection;
import dto.horseInfoDTO;
import dto.memberDTO;

public class horseInfoDAO {
	Connection conn = null;
	Statement stmt = null;
	
	public horseInfoDAO() {}
	
	// 정보 가져오기
	public horseInfoDTO search(int horseNum){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		horseInfoDTO hidto = null;
		
		SingletonClass scls = SingletonClass.getInstance();
		
		String sql = "select *\n";
		   	   sql += "from horseInfo\n";
		   	   sql += "where horseNum = " + horseNum;
				
		conn  = DBconnection.makeConnection();
		System.out.println("1");
		System.out.println("DB접속완료");
		   	   
		try{
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				hidto = new horseInfoDTO();
				hidto.setHorseName(rs.getString("horseName"));
				hidto.setHorsePath(rs.getString("horsePath"));
				hidto.setPhysical1(rs.getInt("physical1"));
				hidto.setPhysical2(rs.getInt("physical2"));
				hidto.setHorseDividendRate(rs.getDouble("horseDividendRate"));
			}
		}catch(SQLException e){
			System.out.println(e. getMessage());
		}finally{
			DBclose.close(stmt, conn);
		}
		return hidto;
	}
}
