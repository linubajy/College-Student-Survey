package com.mindtree.StudentSurvey.Entity;

public class Student 
{
	private String studentName;
	private int age;
	private String subject;
	private String collegeName;
	
	private College college;
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(String studentName, int age, String subject,String collegeName) {
		super();
		this.studentName = studentName;
		this.age = age;
		this.subject = subject;
		this.collegeName=collegeName;
		
	}
	
	
	
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public College getCollege() {
		return college;
	}
	public void setCollege(College college) {
		this.college = college;
	}
	public String getCollegeName() {
		return collegeName;
	}
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	
	
	
}
