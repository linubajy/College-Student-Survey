package com.mindtree.StudentSurvey.Service.Impl;

import java.util.List;


import com.mindtree.StudentSurvey.DAO.StudentSurveyDAO;
import com.mindtree.StudentSurvey.DAO.Impl.StudentSurveyDAOImpl;
import com.mindtree.StudentSurvey.Entity.College;
import com.mindtree.StudentSurvey.Entity.Student;
import com.mindtree.StudentSurvey.Exceptions.StudentSurveyExceptions;
import com.mindtree.StudentSurvey.Exceptions.DAOException.CollegeAbsentException;
import com.mindtree.StudentSurvey.Exceptions.DAOException.CollegePresentException;
import com.mindtree.StudentSurvey.Exceptions.DAOException.StudentSurveyDAOExceptions;
import com.mindtree.StudentSurvey.Exceptions.SeviceException.StudentSurveyServiceException;
import com.mindtree.StudentSurvey.Service.StudentSurveyService;

public class StudentSurveyServiceImpl implements StudentSurveyService
{
	
	private static StudentSurveyDAO dao=new StudentSurveyDAOImpl();
	
	public boolean insertCollegeToDb(College colleges) throws StudentSurveyServiceException 
	{
		try
		{
			return dao.insertCollegeToDb(colleges);
		}
		catch(StudentSurveyDAOExceptions e)
		{
			throw new StudentSurveyServiceException("Something is wrong with the connection");
		}
	}
	

	public boolean insertStudentsToDb(Student students) throws StudentSurveyServiceException {
		try
		{
			return dao.insertStudentsToDb(students);
		}
		catch(StudentSurveyDAOExceptions e)
		{
			throw new StudentSurveyServiceException("Something is wrong with the connection");
		}
	}

	public List<Student> getAllStudentsAndColleges() throws StudentSurveyServiceException
	{
		try
		{
			return dao.getAllStudentsAndColleges();
		}
		catch(StudentSurveyDAOExceptions e)
		{
			throw new StudentSurveyServiceException("Something is wrong with the connection");
		}
	}

	public List<Student> getStudent(String college, String subject) throws StudentSurveyServiceException {
		
		try
		{
			return dao.getStudent(college,subject);
		}
		catch(StudentSurveyDAOExceptions e)
		{
			throw new StudentSurveyServiceException("Something is wrong with the connection");
		}
	}


	public boolean checkCollegePresence(String college_input) throws CollegePresentException 
	{
		try {
			return dao.checkCollegePresence(college_input);
		}
		catch(CollegePresentException e) {
			throw new CollegePresentException("College is present in database");
		}
		
	}


	public boolean checkCollegePresenceForStudent(String college_input) throws CollegeAbsentException 
	{
		try {
			return dao.checkCollegePresenceForStudent(college_input);
		}
		catch(CollegeAbsentException e) {
			throw new CollegeAbsentException("College is not present in database");
		}
		
	}
	


	
	

	


	
	

}
