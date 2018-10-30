package org.bluesky.mybatis.dao.params;

import java.util.List;

import org.bluesky.mybatis.entity.Student;

/**
 * 注解方式的学生操作类接口
 * 
 * @author: liuyuefeng
 * @date: 2015-4-17 下午6:15:08
 * @version: V1.0
 * 
 */
public interface StudentAnnoDao {

	/**
	 * 根据学生指导教师ID查询学生实体
	 * 
	 * @param id
	 * @return: Student
	 * @throws:
	 */
	public List<Student> getStudents(int id);

}
