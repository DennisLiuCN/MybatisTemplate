package org.bluesky.mybatis.entity;

import java.util.List;

/**
 * 学生实体类
 * @author: liuyuefeng
 * @date: 2015-4-9 下午6:24:32
 * @version: V1.0
 * 
 */
public class Student {

	private int id;
	private String name; // 姓名
	private String gender; // 性别
	private String major; // 专业
	private String grade; // 年级
	
	// association级联，has-one关系
	private Teacher supervisor; // 指导教师

	// 多对多关联
	private List<Course> courses;// 所选的课程

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
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getMajor() {
		return major;
	}
	
	public void setMajor(String major) {
		this.major = major;
	}
	
	public String getGrade() {
		return grade;
	}
	
	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Teacher getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Teacher supervisor) {
		this.supervisor = supervisor;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", gender=" + gender
				+ ", major=" + major + ", grade=" + grade + ", supervisor="
				+ supervisor + ", courses=" + courses + "]";
	}

}
