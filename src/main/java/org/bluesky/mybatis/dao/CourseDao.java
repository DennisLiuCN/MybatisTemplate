package org.bluesky.mybatis.dao;

import org.bluesky.mybatis.entity.Course;

/**
 * 课程操作类接口
 * @author: liuyuefeng
 * @date: 2015-4-18 下午10:25:38
 * @version: V1.0
 * 
 */
public interface CourseDao {
	/**
	 * 根据课程ID查询课程实体
	 * @param id
	 * @return: Course
	 * @throws:
	 */
	public Course getById(int id) throws Exception;
}
