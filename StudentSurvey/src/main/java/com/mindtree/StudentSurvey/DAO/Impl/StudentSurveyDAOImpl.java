package com.mindtree.StudentSurvey.DAO.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mindtree.StudentSurvey.DAO.StudentSurveyDAO;
import com.mindtree.StudentSurvey.Entity.College;
import com.mindtree.StudentSurvey.Entity.Student;
import com.mindtree.StudentSurvey.Exceptions.DAOException.CollegeAbsentException;
import com.mindtree.StudentSurvey.Exceptions.DAOException.CollegePresentException;
import com.mindtree.StudentSurvey.Exceptions.DAOException.StudentSurveyDAOExceptions;
import com.mindtree.StudentSurvey.Utility.JDBCConnection;

public class StudentSurveyDAOImpl implements StudentSurveyDAO{

	private static JDBCConnection connection = new JDBCConnection();
	
	public boolean insertCollegeToDb(College colleges) throws StudentSurveyDAOExceptions
	{
		boolean isInserted = false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = connection.getConnection();
			
			String insertQuery = "Insert into College values(?,?)";
			ps = con.prepareStatement(insertQuery);

			ps.setString(1, colleges.getCollegeName());
			ps.setInt(2, colleges.getCollegeStrength());
		

			ps.executeUpdate();
			isInserted = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			connection.closeResources(con);
			connection.closeResources(ps);
		}
		
		return isInserted;

	}

	public boolean insertStudentsToDb(Student students) throws StudentSurveyDAOExceptions 
	{
		
		boolean isInserted = false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = connection.getConnection();
			String insertQuery = "Insert into Student values(?,?,?,?)";
			ps = con.prepareStatement(insertQuery);

			ps.setString(1, students.getStudentName());
			ps.setInt(2, students.getAge());
			ps.setString(3, students.getSubject());
			ps.setString(4, students.getCollegeName());
			ps.executeUpdate();
			isInserted = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			connection.closeResources(con);
			connection.closeResources(ps);
		}
		
		return isInserted;
	}

	

	public void getCollegesAndSub(String collegeName, String subject) throws StudentSurveyDAOExceptions 
	{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		
		try {
			con = connection.getConnection();
			String query = "select subject,collegeName from Student where collegeName='"+collegeName+" ' and subject='"+subject+"'";
	
			ps = con.prepareStatement(query);

			rs = ps.executeQuery();
			while (rs.next()) 
			{
				String sub = rs.getString(1);
				String college=rs.getString(4);
				
				if(college.equals(collegeName)&& subject.contentEquals(sub))
				{
					getStudent(collegeName,subject);
				}
			}
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			connection.closeResources(con);
			connection.closeResources(ps);
		}	
	}
	
	
	public List<Student> getStudent(String collegeName, String subject) throws StudentSurveyDAOExceptions 
	{


		List<Student> students= new ArrayList<Student>();

		Connection con = null;
		ResultSet rs = null;
		Statement st = null;
		String query = "select studentName,age,subject,collegeName from Student where collegeName='"+collegeName+"' and subject='"+subject+"'";

		try {
			con = connection.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				
				Student student = new Student(rs.getString(1), rs.getInt(2), rs.getString(3),rs.getString(4));
				students.add(student);

			}
		} catch (SQLException e) {
			throw new StudentSurveyDAOExceptions();
		} finally {
			connection.closeResources(con);
			connection.closeResources(st);
			connection.closeResources(rs);
		}
		return students;

		
		
	}


	public List<Student> getAllStudentsAndColleges() throws StudentSurveyDAOExceptions
	{
		
		List<Student> students= new ArrayList<Student>();
		
		Connection con = null;
		ResultSet rs = null;
		Statement st = null;
		String query = "select studentName,age,subject,College.collegeName,strength from Student inner join College on Student.collegeName=College.collegeName order by strength,College.collegeName ";

		try {
			con = connection.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				
				String name=rs.getString(1);
				int age=rs.getInt(2);
				String subject=rs.getString(3);
				String collegeName=rs.getString(4);
				int strength=rs.getInt(5);
				
				System.out.println("Name"+name);
				System.out.println("Age"+age);
				System.out.println("Subject"+subject);
				System.out.println("College"+collegeName);
				System.out.println("Strength"+strength);
				

			}
		} catch (SQLException e) {
			throw new StudentSurveyDAOExceptions();
		} finally {
			connection.closeResources(con);
			connection.closeResources(st);
			connection.closeResources(rs);
		}
		
		return students;
		
		
	
	}

	public boolean checkCollegePresence(String college_input) throws CollegePresentException
	{
		int count = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		boolean present = false;
		try {

			con = connection.getConnection();
			String query = "Select count(*) from College Where collegeName='" + college_input+ "'";
			ps = con.prepareStatement(query);

			resultSet = ps.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
				if (count>0) 
				{
					present = true;
					//break;
				}
			}

			if (present == true) {
				throw new CollegePresentException("College is present in DB");
			}

			if (ps != null) {
				ps.close();
			}

		} catch (Exception e) {
			throw new CollegePresentException("College is present in DB");
		} finally {
			connection.closeResources(con);
			connection.closeResources(ps);
		}
		return present;
	}

	
	public boolean checkCollegePresenceForStudent(String college_input) throws CollegeAbsentException
	{
		int count = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		boolean present = false;
		try {

			con = connection.getConnection();
			String query = "Select count(*) from College Where collegeName='" + college_input+ "'";
			ps = con.prepareStatement(query);

			resultSet = ps.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
				if (count>0) 
				{
					present = true;
					//break;
				}
			}

			if (present == false) {
				throw new CollegeAbsentException("College is not present in DB");
			}

			if (ps != null) {
				ps.close();
			}

		} catch (Exception e) {
			throw new CollegeAbsentException("College is not present in DB");
		} finally {
			connection.closeResources(con);
			connection.closeResources(ps);
		}
		return present;
	}


	

	

}
