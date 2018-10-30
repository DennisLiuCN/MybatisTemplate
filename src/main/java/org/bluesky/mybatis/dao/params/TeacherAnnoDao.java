package org.bluesky.mybatis.dao.params;

import java.util.List;
import java.util.Map;

import org.bluesky.mybatis.entity.Teacher;
import org.springframework.stereotype.Component;
//使用@Param注解需要先引入Param

/**
 * 注解方式的教师操作类接口
 * 
 * @author: liuyuefeng
 * @date: 2015-4-17 下午5:17:34
 * @version: V1.0
 * 
 */
/* @Component指定映射器名称为myTeacherDao，不知道时默认teacherDao */
@Component("myTeacherDao")
public interface TeacherAnnoDao {

	/**
	 * 根据教师ID查询教师实体
	 * @param id
	 * @return: Teacher
	 * @throws:
	 */
	public Teacher getById(int id);
	
	/**
	 * 1.分页查询教师信息
	 * 使用@Param("sort")注解，即可在SQL语句中以“#{sort}”的方式引用此方法的sort参数值。当然也可以在@Param中使用其他名称，如@Param("mysort")
	 * @param sort排序字段
	 * @param dir排序方向
	 * @param start起始记录
	 * @param limit记录条数
	 * @return
	 * @return: List<Teacher>
	 * @throws:
	 */
/*	public List<Teacher> findTeacherByPage(@Param("sort") String sort,
			@Param("dir") String dir, @Param("start") int start,
			@Param("limit") int limit);
*/	
	/**
	 * 2.分页查询教师信息
	 * 使用Map方式传递参数
	 * @param map查询条件
	 * @return
	 * @return: List<Teacher>
	 * @throws:
	 */
	// public List<Teacher> findTeacherByPage(Map<String, Object> map);

	/**
	 * 3.分页查询教师信息
	 * 使用混合方式传递参数
	 * @param map查询条件
	 * @param start起始记录
	 * @param limit记录条数
	 * @return
	 * @return: List<Teacher>
	 * @throws:
	 */
	public List<Teacher> findTeacherByPage(Map<String, Object> map, int start, int limit);

}
