package com.mindtree.StudentSurvey.DAO;

import java.util.List;

import com.mindtree.StudentSurvey.Entity.College;
import com.mindtree.StudentSurvey.Entity.Student;
import com.mindtree.StudentSurvey.Exceptions.DAOException.CollegeAbsentException;
import com.mindtree.StudentSurvey.Exceptions.DAOException.CollegePresentException;
import com.mindtree.StudentSurvey.Exceptions.DAOException.StudentSurveyDAOExceptions;

public interface StudentSurveyDAO 
{

	public boolean insertCollegeToDb(College colleges) throws StudentSurveyDAOExceptions;
	 public boolean insertStudentsToDb(Student students) throws StudentSurveyDAOExceptions;
	 
	 public List<Student> getAllStudentsAndColleges()throws StudentSurveyDAOExceptions;
	 public List<Student> getStudent(String college,String subject) throws StudentSurveyDAOExceptions;
	 
	public boolean checkCollegePresence(String college_input) throws CollegePresentException;
	public boolean checkCollegePresenceForStudent(String college_input) throws CollegeAbsentException;
	
	
	 
	
	
}
