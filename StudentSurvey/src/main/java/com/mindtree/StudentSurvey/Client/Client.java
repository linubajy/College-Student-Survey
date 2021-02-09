package com.mindtree.StudentSurvey.Client;

import java.util.List;
import java.util.Scanner;

import com.mindtree.StudentSurvey.Entity.College;
import com.mindtree.StudentSurvey.Entity.Student;
import com.mindtree.StudentSurvey.Exceptions.DAOException.CollegeAbsentException;
import com.mindtree.StudentSurvey.Exceptions.DAOException.CollegePresentException;
import com.mindtree.StudentSurvey.Exceptions.SeviceException.StudentSurveyServiceException;
import com.mindtree.StudentSurvey.Service.StudentSurveyService;
import com.mindtree.StudentSurvey.Service.Impl.StudentSurveyServiceImpl;

public class Client {

	private static StudentSurveyService StudentSurveyService = new StudentSurveyServiceImpl();
	private static Scanner sc = new Scanner(System.in);

	public static void displayMessage() {
		System.out.println();
		System.out.println("Welcome to Student Survey Site");
		System.out.println("==============================");
		System.out.println("1. Create College ");
		System.out.println("2. Create Student ");
		System.out.println("3. Fetch all college and student Data");
		System.out.println("4. Fetch student data based on college and subject");
		System.out.println("5. Exit");
		System.out.println("Enter your choice: ");
	}

	private static College getCreatedCollege() {
		StudentSurveyServiceImpl obj = new StudentSurveyServiceImpl();

		College college = null;
		System.out.println("Enter College name:");
		String college_input = sc.next();
		try {
			boolean pre = obj.checkCollegePresence(college_input);
			// System.out.println("present:"+pre);
			if (pre == false) {
				System.out.println("Enter College Strength:");
				int strength = sc.nextInt();
				college = new College(college_input, strength);
			}
		} catch (CollegePresentException e) {
			System.out.println(e.getMessage());
		}

		return college;

	}

	private static Student getCreatedStudents() {

		StudentSurveyServiceImpl obj = new StudentSurveyServiceImpl();
		Student student = null;

		System.out.println("Enter College name:");
		String college_input = sc.next();

		try {
			boolean pre = obj.checkCollegePresenceForStudent(college_input);
			if (pre == true) {
				System.out.println("Enter Student name: ");
				String name = sc.next();
				System.out.println("Enter the age:");
				int age = sc.nextInt();
				System.out.println("Enter the subject:");
				String subject = sc.next();
				student = new Student(name, age, subject, college_input);
			}

		} catch (CollegeAbsentException e) {
			System.out.println(e.getMessage());
		}

		return student;

	}

	public static void displayDetailsFromDB(List<Student> students)
	{
		if (students != null) 
		{
			System.out.println("NAME \t AGE \t SUBJECT \t COLLEGE \n");
			for (Student x : students) 
			{
				System.out.println(
						x.getStudentName() + "\t" + x.getAge() + "\t" + x.getSubject() + "\t" + x.getCollegeName());
			}
		}
	}

	private static void displayDetailsFromDB(List<Student> list, String college_input, String subject_input) {

		if (list != null) {
			System.out.println("NAME \t AGE \t SUBJECT \t COLLEGE \n");
			for (Student x : list) {
				System.out.println(
						x.getStudentName() + "\t" + x.getAge() + "\t" + x.getSubject() + "\t" + x.getCollegeName());
			}
		}

	}
	
	
	public static void displayAllDetails(List<Student> list) {
		if (list != null) {
			System.out.println("NAME \t AGE \t SUBJECT \t COLLEGE \t STRENGTH \n ");
			for (Student x : list) {
				System.out.println(
						x.getStudentName() + "\t" + x.getAge() + "\t" + x.getSubject() + "\t" + x.getCollegeName() );
			}
		}

	}

	public static void main(String[] args)
	{
		boolean isValid=true;
		Student student=null;
		College college;
	
		do 
		{
			displayMessage();
			int choice=sc.nextInt();
			switch(choice)
			{
				case 1:
					
				college = getCreatedCollege();
				if(college!=null) {
					boolean isInserted = false;
					try {
						isInserted = StudentSurveyService.insertCollegeToDb(college);
					} catch (StudentSurveyServiceException e) {
						System.out.println(e.getMessage());
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					if (isInserted == true) {
						System.out.println("Inserted Successfully");
					}
				}
				else
					continue;
				
				break;
				
				
				case 2:
					
					student = getCreatedStudents();
					if(student!=null) {
					boolean isInsertedStudent = false;
					try {
						isInsertedStudent = StudentSurveyService.insertStudentsToDb(student);
					} catch (StudentSurveyServiceException e) {
						System.out.println(e.getMessage());
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					if (isInsertedStudent == true) {
						System.out.println("Inserted Successfully");
					}
					}
					else 
						continue;
					
					break;
					
					
				case 3:
					
					
					try {
						List<Student> list = StudentSurveyService.getAllStudentsAndColleges();
						
					} catch (StudentSurveyServiceException e) {
						System.out.println(e.getMessage());
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
					
					
				case 4:
					System.out.println("Enter the name of the college:");
					String college_input = sc.next();
					System.out.println("Enter the subject:");
					String subject_input = sc.next();
					try 
					{
						List<Student> list = StudentSurveyService. getStudent(college_input,subject_input);
						displayDetailsFromDB(list,college_input,subject_input);
					} catch (StudentSurveyServiceException e) {
						System.out.println(e.getMessage());
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
					
					
				case 5:
					isValid = false;
					break;
				default:
					System.out.println("Invalid Choice, Please try Again");
			}
		}while(isValid);
		

					
			
	}			
			
}
