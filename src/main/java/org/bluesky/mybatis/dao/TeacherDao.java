package org.bluesky.mybatis.dao;

import java.util.List;

import org.bluesky.mybatis.entity.Teacher;;

/**
 * 教师操作类接口
 * @author: liuyuefeng
 * @date: 2015-4-12 下午10:24:10
 * @version: V1.0
 * 
 */
public interface TeacherDao {
	/**
	 * 根据教师ID查询教师实体
	 * @param id
	 * @return: Teacher
	 * @throws:
	 */
	public Teacher getById(int id) throws Exception;
	
	/**
	 * 根据教师姓名查询教师实体
	 * @param name
	 * @return: List
	 * @throws:
	 */
	public List<Teacher> getByName(String name) throws Exception;
	
	/**
	 * 增加一名教师
	 * @param student
	 * @return: void
	 * @throws:
	 */
	public void add(Teacher teacher) throws Exception;
	
	/**
	 * 修改教师信息
	 * @param teacher
	 * @return: void
	 * @throws:
	 */
	public void update(Teacher teacher) throws Exception;
	
	/**
	 * 删除教师信息
	 * @param id
	 * @return: void
	 * @throws:
	 */
	public void delete(int id) throws Exception;
	
	/**
	 * 级联方式下根据教师ID查询教师实体及关联学生
	 * @param id
	 * @return: Student
	 * @throws:
	 */
	public Teacher getByIdColl(int id) throws Exception;
	
	/**
	 * 级联方式下根据教师ID查询教师实体及关联学生，并在学生实体中关联教师
	 * @param id
	 * @return: Student
	 * @throws:
	 */
	public Teacher getByIdAssoColl(int id) throws Exception;
	
}
