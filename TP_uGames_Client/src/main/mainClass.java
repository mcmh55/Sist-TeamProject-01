package main;

import java.io.IOException;

import awt.LoginWindow;
import conn.SingletonClass;

public class mainClass {

	public static void main(String[] args) throws IOException{
		SingletonClass scls = SingletonClass.getInstance();
		scls.lw = new LoginWindow();
	}

}
