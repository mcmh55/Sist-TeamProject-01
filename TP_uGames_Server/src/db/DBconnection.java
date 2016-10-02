package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
	public DBconnection() {	}
	
	static{
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");	
			System.out.println("Driver Loading Sueccess!!!");
		}catch(ClassNotFoundException e){
			System.out.println(e.getMessage());
		}
	}
	
	public static Connection makeConnection(){
		Connection conn = null;
		
		try{
		conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
		System.out.println("DB Connection Success!!!");
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		System.out.println("데이터 베이스가 연결되었습니다.");
		
		return conn;
	}
}
