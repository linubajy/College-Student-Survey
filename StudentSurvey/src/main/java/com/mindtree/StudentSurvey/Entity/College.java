package com.mindtree.StudentSurvey.Entity;

public class College 
{
	private String collegeName;
	private int collegeStrength;
	
	public College() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public int getCollegeStrength() {
		return collegeStrength;
	}

	public void setCollegeStrength(int collegeStrength) {
		this.collegeStrength = collegeStrength;
	}

	public College(String collegeName, int collegeStrength) {
		super();
		this.collegeName = collegeName;
		this.collegeStrength = collegeStrength;
	}
	
	
	
}
