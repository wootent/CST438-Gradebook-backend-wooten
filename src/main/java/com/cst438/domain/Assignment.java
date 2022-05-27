package com.cst438.domain;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Assignment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="course_id")
	private Course course;
	
	@OneToMany(mappedBy="assignment")
	private List<AssignmentGrade> assignmentGrades;
	
	private String name;
	private Date dueDate;
	private int needsGrading;  // 0 = false,  1= true (past due date and not all students have grades)
	
	// default constructor
	public Assignment() {};
	// added for REST API
	public Assignment(Course course, String name, Date dueDate, int needsGrading) {
		this.course = course;
		this.name = name;
		this.dueDate = dueDate;
		this.needsGrading = needsGrading;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public int getNeedsGrading() {
		return needsGrading;
	}
	public void setNeedsGrading(int needsGrading) {
		this.needsGrading = needsGrading;
	}
	
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	@Override
	public String toString() {
		return "Assignment [id=" + id + ", course_id=" + course.getCourse_id() + ", name=" + name + ", dueDate=" + dueDate
				+ ", needsGrading=" + needsGrading + "]";
	}
	
	// added function for REST API
	public ArrayList<AssignmentGrade> getAssignmentGrades() {
		return new ArrayList<>(this.assignmentGrades);
	}
	
	// added function for Junit-testing
	public void setAssignmentGrades(List<AssignmentGrade> gradesList) {
		this.assignmentGrades = gradesList;
	}
	
}
