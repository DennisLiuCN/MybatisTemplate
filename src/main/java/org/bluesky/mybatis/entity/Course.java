package org.bluesky.mybatis.entity;

import java.util.List;

/**
 * 课程实体类
 * @author: liuyuefeng
 * @date: 2015-4-18 下午10:14:28
 * @version: V1.0
 * 
 */
public class Course {

	private int id;
	private String courseCode; // 课程编号
	private String courseName;// 课程名称
	private List<Student> students;// 选课学生
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
}
