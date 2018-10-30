package org.bluesky.mybatis.dao;

import java.util.List;

import org.bluesky.mybatis.entity.Course;
import org.bluesky.mybatis.entity.Student;

/**
 * 学生操作类接口
 * @author: liuyuefeng
 * @date: 2015-4-9 下午6:31:19
 * @version: V1.0
 * 
 */
public interface StudentDao {
	
	/**
	 * 根据学生ID查询学生实体
	 * @param id
	 * @return: Student
	 * @throws:
	 */
	public Student getById(int id) throws Exception;
	
	/**
	 * 根据学生姓名查询学生实体
	 * @param name
	 * @return: List
	 * @throws:
	 */
	public List<Student> getByName(String name) throws Exception;
	
	/**
	 * 增加一名学生
	 * @param student
	 * @return: void
	 * @throws:
	 */
	public void add(Student student) throws Exception;
	
	/**
	 * 修改学生信息
	 * @param student
	 * @return: void
	 * @throws:
	 */
	public void update(Student student) throws Exception;
	
	/**
	 * 删除学生信息
	 * @param id
	 * @return: void
	 * @throws:
	 */
	public void delete(int id) throws Exception;
	
	/**
	 * 级联方式下根据学生ID查询学生实体及关联教师
	 * @param id
	 * @return: Student
	 * @throws:
	 */
	public Student getByIdAsso(int id) throws Exception;
	
	/**
	 * 级联方式下增加一名学生
	 * @param student
	 * @return: void
	 * @throws:
	 */
	public void addAsso(Student student) throws Exception;
	
	/**
	 * 级联方式下根据教师ID查询学生实体及关联教师，并在教师实体中关联学生
	 * @param id
	 * @return: Student
	 * @throws:
	 */
	public List<Student> getByIdAssoColl(int id) throws Exception;
	
	/**
	 * 保持学生选课信息
	 * @param student
	 * @param course
	 * @return: void
	 * @throws:
	 */
	public void addElecCourse(Student student, Course course) throws Exception;
	
	/**
	 * 根据学生对象查询学生实体
	 * @param student
	 * @return: void
	 * @throws:
	 */
	public List<Student> getByStudentDynamicSQL(Student student) throws Exception;
	
	/**
	 * 根据学生对象List查询学生实体
	 * @param student
	 * @return: void
	 * @throws:
	 */
	public List<Student> getByListDynamicSQL(List<Student> studentList) throws Exception;
	
	/**
	 * 根据学生对象Array查询学生实体
	 * @param student
	 * @return: void
	 * @throws:
	 */
	public List<Student> getByArrayDynamicSQL(Object[] studentArray) throws Exception;
	
	/**
	 * 增加一名学生
	 * @param student
	 * @return: void
	 * @throws:
	 */
	public void addDynamicSQL(Student student) throws Exception;
	
	/**
	 * 修改学生信息
	 * @param student
	 * @return: void
	 * @throws:
	 */
	public void updateDynamicSQL(Student student) throws Exception;

}
