package DPEMS;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class DPEMS {
	static Scanner sc = new Scanner(System.in);
	static Connection con;

	public static void connect() throws SQLException, ClassNotFoundException {
		String url = "jdbc:mysql://localhost:3306/DPEMS";
		String uname = "root";
		String upass = "";
		con = DriverManager.getConnection(url, uname, upass);
	}
}
