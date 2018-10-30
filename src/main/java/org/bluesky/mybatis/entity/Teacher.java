package org.bluesky.mybatis.entity;

import java.util.List;

/**
 * 教师实体类
 * @author: liuyuefeng
 * @date: 2015-4-12 下午9:46:13
 * @version: V1.0
 * 
 */
public class Teacher {

	private int id;
	private String name; // 姓名
	private String gender;// 性别
	private String researchArea;// 研究方向
	private String title;// 职称
	
	//collection级联，has-many关系
	private List<Student> supervisees;//指导学生

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

	public String getResearchArea() {
		return researchArea;
	}

	public void setResearchArea(String researchArea) {
		this.researchArea = researchArea;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Student> getSupervisees() {
		return supervisees;
	}

	public void setSupervisees(List<Student> supervisees) {
		this.supervisees = supervisees;
	}

	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", gender=" + gender
				+ ", researchArea=" + researchArea + ", title=" + title
				+ ", supervisees=" + supervisees + "]";
	}

}
