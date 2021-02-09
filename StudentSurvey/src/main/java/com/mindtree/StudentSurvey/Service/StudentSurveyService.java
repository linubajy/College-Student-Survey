package com.mindtree.StudentSurvey.Service;

import java.util.List;

import com.mindtree.StudentSurvey.Entity.College;
import com.mindtree.StudentSurvey.Entity.Student;
import com.mindtree.StudentSurvey.Exceptions.DAOException.CollegePresentException;
import com.mindtree.StudentSurvey.Exceptions.DAOException.StudentSurveyDAOExceptions;
import com.mindtree.StudentSurvey.Exceptions.SeviceException.StudentSurveyServiceException;

public interface StudentSurveyService 
{
	public boolean insertCollegeToDb(College colleges) throws StudentSurveyServiceException;
	 public boolean insertStudentsToDb(Student students) throws StudentSurveyServiceException;
	 
	 public List<Student> getAllStudentsAndColleges()throws StudentSurveyServiceException;	 
	 public List<Student> getStudent(String college,String subject) throws StudentSurveyServiceException;
}
