package com.mindtree.StudentSurvey.Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mindtree.StudentSurvey.Exceptions.DAOException.*;

public class JDBCConnection {

	private static final String URL = "jdbc:mysql://localhost:3306/StudentSurvey";
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "ROOTPASSWORD";

	

	public static Connection getConnection() throws DBConnectionFailException {
		Connection con = null;
		try {
			con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
		} catch (SQLException e) {
			throw new DBConnectionFailException("Database could not be connected");
		}

		return con;
	}

	public void closeResources(Connection con) {

		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
	}

	public void closeResources(Statement st) {

		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
	}

	public void closeResources(PreparedStatement ps) {

		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
	}

	public void closeResources(ResultSet rs) {

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
	}

}
