package org.bluesky.mybatis.params;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bluesky.mybatis.dao.params.TeacherAnnoDao;
import org.bluesky.mybatis.entity.Student;
import org.bluesky.mybatis.entity.Teacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 多参数传递之注解方式测试类
 * 
 * @author: liuyuefeng
 * @date: 2015-4-17 下午6:26:02
 * @version: V1.0
 * 
 */
public class AnnotationTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(AnnotationTest.class);
	
	private static ApplicationContext ctx;
	static {
		// 在类路径下寻找applicationContext.xml文件
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	}

	/**
	 * @param args
	 * @return: void
	 * @throws:
	 */
	public static void main(String[] args) {
		TeacherAnnoDao teacherDao = (TeacherAnnoDao) ctx.getBean("myTeacherDao");

		LOGGER.info("分页查询： ");
		// 查询教师分页信息。以name字段升序排序，从第0条记录开始查询，查询2条记录
		// 1.使用注解方式传递参数
		// List<Teacher> teachers = teacherDao.findTeacherByPage("name", "asc", 0, 2);
		
		// 2.使用Map方式传递参数
		// Map<String, Object> params = new HashMap<String, Object>();
		// params.put("sort", "name");
		// params.put("dir", "asc");
		// params.put("start", 0);
		// params.put("limit", 2);
		// params.put("title", "%教授");
		// List<Teacher> teachers = teacherDao.findTeacherByPage(params);
		
		// 3.使用混合方式传递参数
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sort", "name");
		params.put("dir", "asc");
		params.put("title", "%教授");
		List<Teacher> teachers = teacherDao.findTeacherByPage(params, 0, 2);

		if (teachers == null) {
			LOGGER.info("未找到相关教师信息。");
		} else {
			LOGGER.info("**********************************************");
			for (Teacher teacher : teachers) {
				LOGGER.info("教师姓名：" + "  " + teacher.getName());
				LOGGER.info("教师职称：" + "  " + teacher.getTitle());
				LOGGER.info("指导学生信息：");
				// 遍历指导的学生
				for (Student s : teacher.getSupervisees()) {
					LOGGER.info("**********************************************");
					LOGGER.info(s.getName() + "  " + s.getGender() + "  " + s.getGrade() + "  " + s.getMajor());
					// 从学生端访问教师
					LOGGER.info("指导教师研究方向：" + s.getSupervisor().getResearchArea());
				}
				LOGGER.info("**********************************************");
			}
		}
	}

}
