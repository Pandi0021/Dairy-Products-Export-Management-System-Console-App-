package DPEMS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login extends DPEMS {

	boolean login(String user, String pass) {
		boolean result = false;
		try {
			String q = "Select * from AdminInfo where AdminName='" + user + "' AND PasswordHash='" + pass + "'";
			Statement st = con.createStatement();
			ResultSet rs;
			rs = st.executeQuery(q);
			while (rs.next()) {
				result = true;
			}
		} catch (SQLException e) {
			System.out.println("Something went wrong!!!");
		}
		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
